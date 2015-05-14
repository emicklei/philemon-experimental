package com.philemonworks.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class SubImage {
	private String target = "target";
	private String format = "png";
	private int left = 0;
	private int top = 0;
	private int width = 0;
	private int height = 0;

	public static List fromSpecs(String sizeSpec, String separationSpec) {
		StringTokenizer sizes = new StringTokenizer(sizeSpec, " ");
		int left = readInt(sizes);
		int top = readInt(sizes);
		int right = readInt(sizes);
		int bottom = readInt(sizes);
		StringTokenizer subs = new StringTokenizer(separationSpec, " ");
		int l = left;
		ArrayList images = new ArrayList();
		while (subs.hasMoreTokens()) {
			SubImage each = new SubImage();
			each.setLeft(l);
			each.setTop(top);
			each.setBottom(bottom);
			each.setTarget(subs.nextToken());
			each.setRight(readInt(subs));
			images.add(each);
			l = each.getRight();
		}
		return images;
	}
	public void extractWrite(BufferedImage sourceImage, String path) throws IOException {
		System.out.println("Exporting " + this + " to:" + path + File.separator + this.getFilename());
		BufferedImage subImage = sourceImage.getSubimage(this.getLeft(), this.getTop(), this.getWidth(), this.getHeight());		
		ImageIO.write(subImage, this.getFormat(), new File(path + File.separator + this.getFilename()));
	}

	public String getFilename() {
		return target + "." + format;
	}

	public static int readInt(StringTokenizer tokenizer) {
		return Integer.valueOf(tokenizer.nextToken()).intValue();
	}
	/**
	 * Pre: left is known
	 * @param right
	 */
	public void setRight(int right) {
		this.setWidth(right - this.getLeft() + 1);
	}
	public int getRight() {
		return this.getLeft() + this.getWidth() - 1;
	}
	/**
	 * Pre: top is known
	 * @param bottom
	 */
	public void setBottom(int bottom) {
		this.setHeight(bottom - this.getTop() + 1);
	}
	/**
	 * @return
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @return
	 */
	public int getTop() {
		return top;
	}

	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param string
	 */
	public void setFormat(String string) {
		format = string;
	}

	/**
	 * @param i
	 */
	public void setHeight(int i) {
		height = i;
	}

	/**
	 * @param i
	 */
	public void setLeft(int i) {
		left = i;
	}

	/**
	 * @param string
	 */
	public void setTarget(String string) {
		target = string;
	}

	/**
	 * @param i
	 */
	public void setTop(int i) {
		top = i;
	}

	/**
	 * @param i
	 */
	public void setWidth(int i) {
		width = i;
	}
	public String toString(){
		return "SubImage[left="+left+",top="+top+",width="+width+",height="+height+",format="+format+",target="+target+"]";
	}
}
