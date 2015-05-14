package com.philemonworks.tag;

import java.io.InputStream;

/**
 * A parser used to construct objects which are declared by a tagged language
 * (such as XML) By providing the parser with a class map (hashtable) that
 * associates a class for each tag used, the parser will create instances of
 * these classes for each occurrence of such tag.*
 * 
 * @author: Ernest Micklei & Richard Blank
 */
public class TaggedDataParser {
    private java.util.Hashtable classMap;
    private java.util.Stack stack = new java.util.Stack();
    private TaggedDataReader reader;
    private java.util.Vector objects = new java.util.Vector();

    /**
     * TaggedDataParser constructor comment.
     */
    public TaggedDataParser(java.util.Hashtable aMap) {
        super();
        classMap = aMap;
    }
    /**
     * Answer with the collection of objects that have been parsed by the
     * receiver
     */
    public java.util.Vector getObjects() {
        return objects;
    }
    /**
     * Parse a string containing tagged data
     */
    public void parse(String s) {
        reader = new TaggedDataReader(s);
        try {
            while (reader.hasMoreTags())
                this.parseStep();
        } catch (Throwable t) {
            System.out.println("Error parsing:" + s + t);
        }
    }
    public void parse(InputStream input) {
        reader = new TaggedDataReader(input);
        try {
            while (reader.hasMoreTags())
                this.parseStep();
        } catch (Throwable t) {
            System.out.println("Error parsing:" + input + t);
        }
    }
    /**
     * Consumes one tag and data if required If the tag has a class associated
     * then push a new instance If the tag represents a field for the top of
     * stack then set its value Otherwise consume any data to be ignored
     */
    private void parseStep() throws Throwable {
        Class objectClass = null;
        String tag = reader.readTag();
        if (reader.isEndTag) {
            this.setValueFromStackTop(tag);
            return;
        }
        objectClass = (Class) classMap.get(tag.toLowerCase());
        if (objectClass == null) {
            // unmapped field or class tag
            if (stack.empty()) {
                // unmapped class tag
                this.skipUntil(tag);
                return;
            } else {
                // field for containing object
                String data = reader.strippedQuotes(reader.readData());
                ((ITaggedAccessible) (stack.peek())).setValueForTag(tag.toLowerCase(), data);
                this.reader.readTag();
            }
        } else {
            stack.push(objectClass.newInstance());
            if (reader.hasOptions())
                this.setValuesFromOptions();
            if (((ITaggedAccessible) stack.peek()).isEmptyElement()) {
                this.setValueFromStackTop(tag.toLowerCase());
            }
        }
        if (reader.isEmptyTag) {
            this.setValueFromStackTop(tag);
        }
    }
    /**
     * Handle the object on top of stack
     */
    private void setValueFromStackTop(String tag) throws Throwable {

        Object last = stack.pop();
        if (stack.empty())
            objects.addElement(last);
        else
            ((ITaggedAccessible) (stack.peek())).setValueForTag(tag, last);
    }
    // options of the current class tag are handled like fields

    private void setValuesFromOptions() {

        java.util.Enumeration tags = reader.getOptions().keys();
        while (tags.hasMoreElements()) {
            String tag = (String) tags.nextElement();
            String data = reader.strippedQuotes((String) reader.getOptions().get(tag));
            try {
                ((ITaggedAccessible) (stack.peek())).setValueForTag(tag.toLowerCase(), data);
            } catch (Throwable t) {
                System.out.println(t);
            }
        }
    }
    //  Consume all tags until the entag has been read

    private void skipUntil(String tag) throws Throwable {

        String lowerTag = tag.toLowerCase();
        while (reader.hasMoreTags()) {
            String next = reader.readTag().toLowerCase();
            if (next.equals(tag))
                break;
        }
    }
}