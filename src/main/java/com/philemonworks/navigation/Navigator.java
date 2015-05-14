package com.philemonworks.navigation;

import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import com.philemonworks.tag.EmptySpec;
import com.philemonworks.tag.HTMLCompositeSpec;
import com.philemonworks.tag.TaggedDataParser;

public class Navigator extends Applet {
	java.util.Vector imageSpecs = new java.util.Vector();
	java.util.Vector mapSpecs = new java.util.Vector();
	private NavigatorPanel ivjMap = null;

	/**
	 * Called whenever the part throws an exception.
	 * 
	 * @param exception
	 *        java.lang.Throwable
	 */
	public void clickedArea(Area anArea) {
		try {
			String file = anArea.spec.href;
			if (!file.startsWith("http"))
				file = getCodeBase() + file;
			this.getAppletContext().showDocument(new java.net.URL(file), anArea.spec.target);
		} catch (java.net.MalformedURLException ex) {
			this.handleException(ex);
		}
	}
	/**
	 * Geeft een floating point number (float)
	 */
	public static float floatFromString(String numberString) {
		int intpart;
		int fractionpart;
		float number;
		int dot = numberString.indexOf(".");
		if (dot == -1) {
			intpart = Integer.parseInt(numberString);
			fractionpart = 0;
		} else {
			intpart = Integer.parseInt(numberString.substring(0, dot));
			fractionpart = Integer.parseInt(numberString.substring(dot + 1, numberString.length()));
		}
		number = (float) fractionpart;
		while (number > 1)
			number = number / 10f;
		return (float) intpart + number;
	}
	public void follow(String xypairsString) {
		StringTokenizer tokenizer = new StringTokenizer(xypairsString, ",");
		getMap().activeArea = null;
		while (tokenizer.hasMoreTokens()) {
			int x = Integer.parseInt(tokenizer.nextToken());
			int y = Integer.parseInt(tokenizer.nextToken());
			this.getMap().position(x, y);
			try {
				Thread.sleep(getMap().delay);
			} catch (Throwable t) {
			}
		}
	}
	/**
	 * Returns information about this applet.
	 * 
	 * @return a string of information about this applet
	 */
	public String getAppletInfo() {
		return "Navigator\n" + "\n" + "\n" + "";
	}
	/**
	 * Return the Map property value.
	 * 
	 * @return com.philemonworks.navigation.NavigatorPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	public NavigatorPanel getMap() {
		if (ivjMap == null) {
			try {
				ivjMap = new com.philemonworks.navigation.NavigatorPanel(null);
				ivjMap.setName("Map");
				ivjMap.setLayout(null);
				ivjMap.setBounds(0, 0, getSize().width, getSize().height);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjMap;
	}
	/**
	 * Called whenever the part throws an exception.
	 * 
	 * @param exception
	 *        java.lang.Throwable
	 */
	protected void handleException(java.lang.Throwable exception) {
		/* Uncomment the following lines to print uncaught exceptions to stdout */
		System.out.println("--------- UNCAUGHT EXCEPTION ---------");
		exception.printStackTrace(System.out);
	}
	/**
	 * Initializes the applet.
	 * 
	 * @see #start
	 * @see #stop
	 * @see #destroy
	 */
	public void init() {
		try {
			super.init();
			setName("Navigator");
			setLayout(null);
			setSize(getSize().width, getSize().height);
			add(getMap(), getMap().getName());
			// user code begin {1}
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			this.loadParameters();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	public void loadImages() {
		// buffer image
		int w = getSize().width;
		int h = getSize().height;
		getMap().backImage = this.createImage(w, h);
		MediaTracker tracker = new MediaTracker(this);
		// image maps
		for (int i = 0; i < imageSpecs.size(); i++) {
			ImageSpec spec = (ImageSpec) imageSpecs.elementAt(i);
			try {
				Image newImage = this.getImage(new java.net.URL(getCodeBase(), spec.source));
				tracker.addImage(newImage, 0);
				spec.image = newImage;
			} catch (java.net.MalformedURLException ex) {
				System.out.println("Bad url:" + spec.source);
			}
		}
		showStatus("Bezig met laden van afbeeldingen...");
		try {
			tracker.waitForAll();
		} catch (Exception e) {
			this.handleException(e);
			return;
		}
		ImageSpec spec = (ImageSpec) imageSpecs.elementAt(0);
		getMap().setMap(spec.image, spec.image.getWidth(this), spec.image.getHeight(this));
	}
	/**
	 * keyPressed method comment.
	 */
	public void loadParameters() {
		getMap().delay = Integer.parseInt(getParameter("delay"));
		getMap().grid = Integer.parseInt(getParameter("grid"));
		getMap().marge = Integer.parseInt(getParameter("marge"));
		getMap().maxZoom = Float.valueOf(getParameter("maxzoom")).floatValue();
		getMap().followMouse = Boolean.valueOf(getParameter("followmouse")).booleanValue();
		getMap().moveStep = Integer.parseInt(getParameter("movestep"));
		getMap().backColor = AreaSpec.readColorFrom(getParameter("background"));
		float preferredZoom = Float.valueOf(getParameter("zoom")).floatValue();
		this.loadSpecs();
		this.loadImages();
		getMap().start();
		getMap().setZoomFactor(preferredZoom);
		this.position(getParameter("position"));
	}
	public void loadSpecs() {
		try {
			// load HTML map
			java.net.URL url = new java.net.URL(getCodeBase(), getParameter("map"));
			java.io.InputStream input = (java.io.InputStream) url.getContent();
			StringBuffer content = new StringBuffer();
			while (input.available() > 0) {
				content.append((char) input.read());
			}
			// parse HTML content
			java.util.Hashtable classMap = new java.util.Hashtable();
			classMap.put("html", HTMLCompositeSpec.class);
			classMap.put("body", HTMLCompositeSpec.class);
			classMap.put("img", ImageSpec.class);
			classMap.put("map", MapSpec.class);
			classMap.put("area", AreaSpec.class);
			classMap.put("br", EmptySpec.class);
			classMap.put("hr", EmptySpec.class);
			classMap.put("p", EmptySpec.class);
			TaggedDataParser parser = new TaggedDataParser(classMap);
			parser.parse(content.toString());
			HTMLCompositeSpec html = (HTMLCompositeSpec) parser.getObjects().elementAt(0);
			HTMLCompositeSpec body = (HTMLCompositeSpec) html.get("body");
			Object obj;
			obj = body.get("img");
			if (obj instanceof java.util.Vector)
				imageSpecs = (java.util.Vector) obj;
			else
				imageSpecs.addElement(obj);
			obj = body.get("map");
			if (obj instanceof java.util.Vector)
				mapSpecs = (java.util.Vector) obj;
			else
				mapSpecs.addElement(obj);
			getMap().mapSpec = (MapSpec) mapSpecs.elementAt(0);
		} catch (Throwable t) {
			System.err.println("Fout in HTML bestand met map definitie(s)");
			this.handleException(t);
		}
	}
	/**
	 * main entrypoint - starts the part when it is run as an application
	 * 
	 * @param args
	 *        java.lang.String[]
	 */
	public static void main(java.lang.String[] args) {
		try {
			Frame frame = new java.awt.Frame();
			Navigator aNavigator;
			Class iiCls = Class.forName("com.philemonworks.navigation.Navigator");
			ClassLoader iiClsLoader = iiCls.getClassLoader();
			aNavigator = (Navigator) java.beans.Beans.instantiate(iiClsLoader, "com.philemonworks.navigation.Navigator");
			frame.add("Center", aNavigator);
			frame.setSize(aNavigator.getSize());
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				};
			});
			frame.setVisible(true);
		} catch (Throwable exception) {
			System.err.println("Exception occurred in main() of java.applet.Applet");
			exception.printStackTrace(System.out);
		}
	}
	/*
	 * Set the focus to the position denoted by xyString e.g. "12,34"
	 */
	public void position(String xyString) {
		StringTokenizer tokenizer = new StringTokenizer(xyString, ",");
		int x = Integer.parseInt(tokenizer.nextToken());
		int y = Integer.parseInt(tokenizer.nextToken());
		this.getMap().activeArea = null;
		this.getMap().position(x, y);
	}
	/*
	 * Set the focus to the center of the area that can be found by alt
	 */
	public void show(String alt) {
		// dispatch it
		this.showStatus("Op zoek naar " + alt);
		this.getMap().show(alt);
	}
	/**
	 * run method comment.
	 */
	public void stop() {
		getMap().stop();
	}
	/*
	 * Set the zoom factor
	 */
	public void zoom(String floatString) {
		float zoom = 1.0f;
		try {
			zoom = floatFromString(floatString);
		} catch (Throwable t) {
		}
		this.getMap().setZoomFactor(zoom);
		this.getMap().repaint();
	}
}
