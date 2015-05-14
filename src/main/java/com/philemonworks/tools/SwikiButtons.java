package com.philemonworks.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class SwikiButtons {
	public static void main(String[] args) {
		new SwikiButtons().doit();
	}
	public void doit() {
		try {
			String there = "D:\\squeak37\\swiki\\default\\files\\schemes\\gradient";
			InputStream is = new FileInputStream(there + "\\buttons.png");
			BufferedImage image = ImageIO.read(is);
			System.out.println("Image[width="+image.getWidth()+",height="+image.getHeight()+"]");
			List images = this.blueImages();
			for (int s = 0; s < images.size(); s++) {
				SubImage sub = (SubImage) images.get(s);
				sub.extractWrite(image, there);
			}
			images = this.greenImages();
			for (int s = 0; s < images.size(); s++) {
				SubImage sub = (SubImage) images.get(s);
				sub.setTarget(sub.getTarget() + "ie");
				sub.extractWrite(image, there);
			}
			images = this.redImages();
			for (int s = 0; s < images.size(); s++) {
				SubImage sub = (SubImage) images.get(s);
				sub.setTarget(sub.getTarget() + "act");
				sub.extractWrite(image, there);
			}
			images = this.blueImages();
			for (int s = 0; s < images.size(); s++) {
				SubImage sub = (SubImage) images.get(s);
				sub.setTarget(sub.getTarget() + "help");
				sub.extractWrite(image, there);
			}
			images = this.grayImages();
			for (int s = 0; s < images.size(); s++) {
				SubImage sub = (SubImage) images.get(s);
				sub.setTarget(sub.getTarget() + "bw");
				sub.extractWrite(image, there);
			}			
			this.before().extractWrite(image,there);
			this.after().extractWrite(image,there);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String bounds(){
		return "0 0 617 39"; // left top right bottom
	}
	
	public List blueImages() {
		return SubImage.fromSpecs(bounds(),
		"view 46 edit 84 upload 148 history 206 top 240 recent 306 search 362 help 402 unlock 457 lock 496 links 539 edit 577 editlock 617" // (name right)*
		);
	}
	public List greenImages() {
		List images = this.blueImages();
		for (int i = 0; i < images.size(); i++)
			 ((SubImage) images.get(i)).setTop(40);
		return images;
	}
	public List redImages() {
		List images = this.blueImages();
		for (int i = 0; i < images.size(); i++)
			 ((SubImage) images.get(i)).setTop(80);
		return images;
	}
	public List grayImages() {
		List images = this.blueImages();
		for (int i = 0; i < images.size(); i++)
			 ((SubImage) images.get(i)).setTop(120);
		return images;
	}
	public SubImage before(){
		return (SubImage)SubImage.fromSpecs(bounds(), "before 4").get(0);
	}
	public SubImage after(){
		return (SubImage)SubImage.fromSpecs(bounds(), "after 4").get(0);
	}
}
