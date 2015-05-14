package com.philemonworks.tag;

import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * A reader used to tokenize a tagged language (such as XML) in which tags are
 * enclosed by the characters < and >. A tag may have options specified. Special
 * character entities are converted to their human-readable format.
 * 
 * Example:
 * 
 * TaggedDataReader r = new TaggedDataReader( " <message
 * language=\"english\">The xml notation for less than is &lt; </message>");
 * while(r.hasMoreTags()) { System.out.println(r.readTag()); if (r.hasOptions())
 * System.out.println(r.getOptions()); System.out.println(r.readData());
 * System.out.println(r.readTag()); }
 * 
 * @author: Ernest Micklei & Richard Blank
 */
public class TaggedDataReader {

    private java.io.PushbackInputStream charReader;
    boolean isEndTag = false;
    boolean isEmptyTag = false;
    private java.util.Hashtable options;
    private static java.util.Hashtable specialMap;

    static {
        specialMap = new java.util.Hashtable();
        specialMap.put("lt", "<");
        specialMap.put("gt", ">");
        specialMap.put("quot", "'");
    }

    /**
     * Answer a new reader for tags on the argument #input
     */
    public TaggedDataReader(String input) {
        this(new java.io.ByteArrayInputStream(input.getBytes()));
    }
    public TaggedDataReader(InputStream stream){
        super();
        charReader = new PushbackInputStream(stream);
    }
    /**
     *  
     */
    private void addOption(String attribute) {

        int i = attribute.indexOf('=');
        if (i == -1) {
            if (attribute.length() > 0)
                options.put(attribute, "");
        } else
            options.put(attribute.substring(0, i), attribute.substring(i + 1, attribute.length()));
    }
    /**
     * Answer the table of options for the last tag
     */
    public java.util.Hashtable getOptions() {
        return options;
    }
    /**
     * Answer whether more tags are available
     */
    public boolean hasMoreTags() {
        try {
            this.skipUpTo('<');
            return charReader.available() > 0;
        } catch (java.io.IOException ex) {
            return false;
        }
    }
    /**
     * Answer whether the last tage had any options specified
     */
    public boolean hasOptions() {

        return options.size() > 0;
    }
    /**
     * Answer all data until a tag is encountered Replace any special character
     * entity read
     */
    public String readData() {
        StringBuffer buffer = new StringBuffer(32);
        StringBuffer special = new StringBuffer(32);
        boolean done = false;
        boolean ampersand = false;
        boolean inString = false;
        try {
            int i;
            while (charReader.available() > 0 && !done) {
                i = charReader.read();
                char ch = (char) i;
                if (ch == '"')
                    inString = !inString;
                if (inString)
                    buffer.append(ch);
                else
                    switch (ch) {
                    case '<':
                        done = true;
                        charReader.unread(i);
                        if (ampersand)
                            buffer.append('&');
                        buffer.append(special.toString());
                        break;
                    case '&':
                        if (ampersand) {
                            buffer.append('&');
                            buffer.append(special.toString());
                            special = new StringBuffer();
                        }
                        ampersand = true;
                        break;
                    case ';':
                        String replacement = null;
                        if (ampersand)
                            replacement = (String) specialMap.get(special.toString());
                        if (replacement == null) {
                            buffer.append(special.toString());
                            buffer.append(';');
                        } else {
                            buffer.append(replacement);
                        }
                        special = new StringBuffer();
                        ampersand = false;
                        break;
                    default:
                        if (ampersand)
                            special.append(ch);
                        else
                            buffer.append(ch);
                        break;
                    }
            }
        } catch (java.io.IOException ex) {
            throw new RuntimeException("error reading data");
        }
        return buffer.toString();
    }
    /**
     * Answer the name of the tag. After returning the name, the receiver can be
     * requested for: - hasOptions - getOptions - isEndTag - isEmptyTag
     */
    public String readTag() {

        isEndTag = false;
        isEmptyTag = false;
        options = new java.util.Hashtable();
        StringBuffer buffer = new StringBuffer();
        boolean done = false;
        boolean option = false;
        boolean comment = false;
        boolean inString = false;
        char lastCh = (char) 0;
        StringBuffer attribute = new StringBuffer();
        try {
            this.skipUntil('<');
            while (charReader.available() > 0 && !done) {
                char ch = (char) charReader.read();
                if (ch == '"')
                    inString = !inString;
                if (inString) {
                    if (option)
                        attribute.append(ch);
                    else
                        buffer.append(ch);
                } else
                    switch (ch) {
                    case '!':
                        if (lastCh == (char) 0) {
                            skipUntil('<');
                            ch = (char) 0;
                        }
                        break;
                    case '?':
                        if (lastCh == (char) 0) {
                            skipUntil('<');
                            ch = (char) 0;
                        }
                        break;
                    case '>':
                        if (option) {
                            this.addOption(attribute.toString());
                            attribute = new StringBuffer();
                        }
                        isEmptyTag = lastCh == '/';
                        done = true;
                        break;
                    case '/':
                        isEndTag = buffer.length() == 0;
                        break;
                    case ' ':
                        if (option) {
                            this.addOption(attribute.toString());
                            attribute = new StringBuffer();
                        } else
                            option = true;
                        break;
                    case '\t':
                        if (option) {
                            this.addOption(attribute.toString());
                            attribute = new StringBuffer();
                        } else
                            option = true;
                        break;
                    case (char) 13:
                        if (option) {
                            this.addOption(attribute.toString());
                            attribute = new StringBuffer();
                        } else
                            option = true;
                        break;
                    case (char) 10:
                        if (option) {
                            this.addOption(attribute.toString());
                            attribute = new StringBuffer();
                        } else
                            option = true;
                        break;
                    default:
                        if (option)
                            attribute.append(ch);
                        else
                            buffer.append(ch);
                        break;
                    }
                lastCh = ch;
            }
        } catch (java.io.IOException ex) {
            throw new RuntimeException("error reading tag");
        }
        return buffer.toString();
    }
    /**
     *  
     */
    private void skipUntil(char ch) throws java.io.IOException {
        // consume all characters until #ch is read

        int i = 0;
        boolean done = false;
        while (charReader.available() > 0 && !done) {
            i = charReader.read();
            done = i == -1 || (char) i == ch;
        }
    }
    /**
     *  
     */
    private void skipUpTo(char ch) throws java.io.IOException {
        // consume all characters until #ch is reached (but not read)

        int i = 0;
        boolean done = false;
        while (charReader.available() > 0 && !done) {
            i = charReader.read();
            done = i == -1 || (char) i == ch;
        }
        if (charReader.available() > 0 && done)
            charReader.unread(i);
    }
    /**
     *  
     */
    public String strippedQuotes(String aString) {

        int from = 0;
        int to = aString.length() - 1;

        if (aString.length() == 0)
            return aString;
        if (aString.charAt(0) == '"')
            from++;
        if (aString.charAt(to) == '"')
            to--;
        return aString.substring(from, to + 1);
    }
}