/**
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 */
package com.philemonworks.video;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * SRTReader is
 * 
 * @author E.M.Micklei
 */
public class SRTReader {
	public static void main(String[] args) {
		String f = "n:/movies/appleseed.2004.NL.srt";
		String f1 = "n:/movies/Appleseed.2004.CD1.srt";
		String f2 = "n:/movies/Appleseed.2004.CD2.srt";
		try {
			InputStream i = new FileInputStream(f);
			List entries = new SRTReader().readFrom(i);
			i.close();
			int index = 0;
			int sub = 1;
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f1));
			Time split = Time.readFrom("00:49:05,000");
			SRTEntry entry = (SRTEntry) entries.get(index);
			while (entry.before(split)) {
				dos.writeBytes(String.valueOf(sub));
				dos.writeBytes("\n\r");
				dos.writeBytes(entry.toString());
				dos.writeBytes("\n\r");
				index++;
				sub++;
				entry = (SRTEntry) entries.get(index);
			}
			dos.close();
			Time zeroTime = (Time)((SRTEntry)entries.get(index-1)).to.clone();
			sub = 1;
			dos = new DataOutputStream(new FileOutputStream(f2)); 
			while (index < entries.size()) {
				entry = (SRTEntry) entries.get(index);
				entry.from.subtract(zeroTime);
				entry.to.subtract(zeroTime);
				dos.writeBytes(String.valueOf(sub));
				dos.writeBytes("\n\r");
				dos.writeBytes(entry.toString());
				dos.writeBytes("\n\r");
				index++;
				sub++;
			}
			dos.close();			
			
		} catch (Exception ex) {
			// bummer
			ex.printStackTrace(System.err);
		}
	}
	public List readFrom(InputStream is) throws Exception {
		List entries = new ArrayList();
		DataInputStream dis = new DataInputStream(is);
		while (dis.available() > 0) {
			SRTEntry entry = new SRTEntry();
			int index = Integer.parseInt(dis.readLine().trim());
			String[] parts = dis.readLine().trim().split(" ");
			entry.from = Time.readFrom(parts[0]);
			entry.to = Time.readFrom(parts[2]);
			String line = dis.readLine().trim();
			while (line.length() > 0) {
				entry.add(line);
				line = dis.readLine().trim();
			}
			entries.add(entry);
		}
		return entries;
	}
}
