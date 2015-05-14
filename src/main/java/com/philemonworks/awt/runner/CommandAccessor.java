package com.philemonworks.awt.runner;

/**
 * Insert the type's description here.
 * Creation date: (16-12-2003 13:18:03)
 * @author: Ernest Micklei
 */
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

public class CommandAccessor {

	String fileName = "c:\\temp\\commands.xml";
	
/**
 * CommandAccessor constructor comment.
 */
public CommandAccessor() {
	super();
}

  static String escapeXML(String s) {
    StringBuffer str = new StringBuffer();
    int len = (s != null) ? s.length() : 0;
    for (int i=0; i<len; i++) {
       char ch = s.charAt(i);
       switch (ch) {
       case '<': str.append("&lt;"); break;
       case '>': str.append("&gt;"); break;
       case '&': str.append("&amp;"); break;
       case '"': str.append("&quot;"); break;
       case '\'': str.append("&apos;"); break;
       default: str.append(ch);
     }
    }
    return str.toString();
  }
public void initializeCommandFrom(Command command, String key, String valueString) {

	if (key.equals("id")) command.target=valueString;
	else if (key.equals("method")) command.methodName=valueString;
	else if (key.regionMatches(0,"string",0,6)) command.addStringParameter(valueString);
	else if (key.regionMatches(0,"boolean",0,7)) command.addBooleanParameter(valueString);	
}
public ArrayList readCommands() {

    org.w3c.dom.Document doc = null;
    try {
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = parser.parse(fileName);
    } catch (Exception ex) {
        ex.printStackTrace(System.out);
        return null;
    }
    ArrayList commands = new ArrayList();

    org.w3c.dom.Node node = doc.getDocumentElement().getFirstChild();
    while (node != null && !node.getNodeName().equals("command"))
        node = node.getNextSibling();

    while (node != null) {
        Command command = new Command();

        NamedNodeMap attrs = node.getAttributes();
        int len = attrs.getLength();
        for (int i = 0; i < len; i++) {
            Attr attributes = (Attr) attrs.item(i);
            this.initializeCommandFrom(
                command,
                attributes.getNodeName(),
                attributes.getNodeValue());
        }
        commands.add(command);
        node = node.getNextSibling();
        while (node != null && !node.getNodeName().equals("command"))
            node = node.getNextSibling();
    }

    return commands;
}
public void writeCommands(ArrayList commands) {
	// write XML in UNIX format (not Unicode)
    try {
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeBytes("<?xml version=\"1.0\" ?>\n");
        dos.writeBytes("<scenario>\n");
        for (int c=0;c<commands.size();c++){
	        dos.writeBytes(((Command)commands.get(c)).toXML());
	        dos.writeBytes("\n");
        }
        dos.writeBytes("</scenario>\n");
        fos.close();
    } catch (Exception ex) {
        ex.printStackTrace(System.out);
    }

}
}
