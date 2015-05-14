package com.philemonworks.util;
/**
 * This String tokenizer class is an optimization of the standard Java StringTokenizer.
 * The delimiter must be a char and not a string
 */
public class StringTokenizer {
	private int currentPosition;
	private int maxPosition;
	private String str;
	private char delimiter;

	/**
	 * Constructs a char tokenizer for the specified string. The
	 * character specified by the <code>delim</code> argument is
	 * the delimiter for separating tokens.
	 * @param   str            a string to be parsed.
	 * @param   delim          the delimiter.
	 */
	public StringTokenizer(String str, char delim) {
		currentPosition = 0;
		this.str = str;
		maxPosition = str.length();
		delimiter = delim;
	}
	/**
	 * Calculates the number of times that this tokenizer's
	 * <code>nextToken</code> method can be called before it generates an
	 * exception.
	 *
	 * @return  the number of tokens remaining in the string using the current
	 *          delimiter.
	 */
	public int countTokens() {
		int count = 0;
		int currpos = currentPosition;

		while (currpos < maxPosition) {
			/*
			 * This is just skipDelimiters(); but it does not affect currentPosition.
			 */
			while ((currpos < maxPosition) && (delimiter == (str.charAt(currpos)))) {
				currpos++;
			}

			if (currpos >= maxPosition) {
				break;
			}

			currpos = str.indexOf(delimiter, currpos);
			if (currpos < 0)
				currpos = maxPosition;
			count++;

		}
		return count;
	}
	/**
	 * Calculates the number of tokens.
	 * For this method if the last element of the string is the char delimiter
	 * then we count also a last empty string token.
	 */
	public int exactCountTokens() {
		int count = 0;
		int currpos = currentPosition;

		while (currpos < maxPosition) {
			while ((currpos < maxPosition) && (delimiter == (str.charAt(currpos)))) {
				currpos++;
			}

			if (currpos >= maxPosition) {
				count++;
				break;
			}

			currpos = str.indexOf(delimiter, currpos);
			if (currpos < 0)
				currpos = maxPosition;
			count++;

		}
		return count;
	}
	/**
	 * Returns the same value as the <code>hasMoreTokens</code>
	 * method. It exists so that this class can implement the
	 * <code>Enumeration</code> interface.
	 *
	 * @return  <code>true</code> if there are more tokens;
	 *          <code>false</code> otherwise.
	 */
	public boolean hasMoreElements() {
		return hasMoreTokens();
	}
	/**
	 * Tests if there are more tokens available from this tokenizer's string.
	 * @return  <code>true</code> if there are more tokens available; <code>false</code> otherwise.
	 */
	public boolean hasMoreTokens() {
		skipDelimiters();
		return (currentPosition < maxPosition);
	}
	/**
	 * Returns the same value as the <code>nextToken</code> method,
	 * except that its declared return value is <code>Object</code> rather than
	 * <code>String</code>. It exists so that this class can implement the
	 * <code>Enumeration</code> interface.
	 *
	 * @return     the next token in the string.
	 * @exception  NoSuchElementException  if there are no more tokens
	 */
	public Object nextElement() {
		return nextToken();
	}
	/**
	 * Returns the next token from this string tokenizer.
	 * @return     the next token from this string tokenizer.
	 * @exception  NoSuchElementException  if there are no more tokens in this
	 *               tokenizer's string.
	 */
	public String nextToken() {
		skipDelimiters();

		if (currentPosition >= maxPosition)
			return null;

		int start = currentPosition;
		currentPosition = str.indexOf(delimiter, currentPosition);
		if (currentPosition < 0)
			currentPosition = maxPosition;
		return str.substring(start, currentPosition);
	}
	/**
	 * Skips delimiters.
	 */
	private void skipDelimiters() {
		while ((currentPosition < maxPosition) && (delimiter == (str.charAt(currentPosition)))) {
			currentPosition++;
		}
	}
	/**
	 * Same method as strictNextToken but this method returns an object
	 */
	public Object strictNextElement() {
		return strictNextToken();
	}
	/**
	 * Return the next token, null if such a token does not exist.
	 * Only one delimiter is skipped.
	 * This method avoids the skipDelimiters.
	 */
	public String strictNextToken() {
		if (currentPosition >= maxPosition)
			return null;

		int start = currentPosition;
		currentPosition = str.indexOf(delimiter, currentPosition);
		if (currentPosition < 0)
			currentPosition = maxPosition;
		currentPosition = currentPosition + 1;
		return str.substring(start, currentPosition - 1);
	}

}
