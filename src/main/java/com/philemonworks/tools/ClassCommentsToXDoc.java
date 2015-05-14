package com.philemonworks.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import com.philemonworks.writer.HTMLWriter;
import com.philemonworks.writer.Table;
import com.philemonworks.writer.XDocWriter;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

/**
 * ClassCommentsToXDoc takes a collection of com.thoughtworks.qdox.model.JavaSource objects
 * and generates an XDoc file with a table of class comments.
 * 
 * @usage java com.philemonworks.tools.ClassCommentsToXDoc <source dir> <javadoc dir> <target file>
 * 
 * @author E.M.Micklei
 */
public class ClassCommentsToXDoc {
	private String targetFilename;
	private String javadocDir;

	public static void main(String[] args){
		if (args.length == 0) {
			System.out.println("Usage: <source dir> <javadoc dir> <target file>");
			return;
		}
		JavaDocBuilder builder = new JavaDocBuilder();
		// Adding all .java files in a source dir.
		File dir = new File(args[0]);
		String[] files = dir.list();
		if (files == null) {
			System.err.println("no files in directory:" + dir);
			return;
		}
		for (int i = 0; i < files.length; i++) {
			try {
				String name = files[i];
				if (name.endsWith(".java"))
					builder.addSource(new File(dir,name));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}		
		ClassCommentsToXDoc tool = new ClassCommentsToXDoc();
		tool.setJavadocDir(args[1]);
		tool.setTargetFilename(args[2]);	 
		tool.extract(builder.getSources());		
		System.out.println("Class comments written to:" + args[2]);
	}

	public void extract(JavaSource[] sources) {
		try {
			OutputStream os = new FileOutputStream(targetFilename);
			XDocWriter xdoc = new XDocWriter(os);
			xdoc.raw(HTMLWriter.XMLHEADER);
			xdoc.tag("document");
			xdoc.tag("properties");
			xdoc.tagged("title","Class Comments",false);
			xdoc.end("properties");
			xdoc.tag("body");
			if (sources.length == 0)
				xdoc.tag("section");
			else
				xdoc.tag("section","name",sources[0].getPackage());
			Table table = new Table();
			int row = 1;
			table.put(row, 1, "Class");
			table.put(row, 2, "Comment");
			for (int i = 0; i < sources.length; i++) {
				JavaSource src = sources[i];
				JavaClass cls = src.getClasses()[0];
				row++;
				table.put(row, 1, this.javaDocReference(cls));
				table.put(row, 2, cls.getComment());
			}
			xdoc.table(table);
			xdoc.end(); // section
			xdoc.end(); // body
			xdoc.end(); // document
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String javaDocReference(JavaClass javaClass) {
		return "<a class=\"external\" href=\""
			+ javadocDir
			+ "/"
			+ javaClass.getFullyQualifiedName()
			+ "\">"
			+ javaClass.getName()
			+ "</a>";
	}

	/**
	 * @param string
	 */
	public void setTargetFilename(String string) {
		targetFilename = string;
	}

	/**
	 * @param string
	 */
	public void setJavadocDir(String string) {
		javadocDir = string;
	}

}
