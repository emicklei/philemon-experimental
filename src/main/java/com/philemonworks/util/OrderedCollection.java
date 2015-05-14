package com.philemonworks.util;
import java.util.Vector;

public class OrderedCollection extends java.util.Vector {

	Block sortBlock;
	/**
	 * Vector constructor comment.
	 */
	public OrderedCollection() {
		super();
	}
	/**
	 * Vector constructor comment.
	 */
	public OrderedCollection(Block twoArgBlock) {
		super();
		sortBlock = twoArgBlock;
	}
	public static OrderedCollection with(Object element1) {

		OrderedCollection collection = new OrderedCollection();
		collection.add(element1);
		return collection;
	}
	public void addElements(Object[] array) {

		for (int i = 0; i < array.length; i++)
			this.addElement(array[i]);
	}
	public Object[] asArray() {

		Object[] result = new Object[this.size()];
		for (int i = 0; i < this.size(); i++)
			result[i] = this.elementAt(i);
		return result;
	}
	private void bubbleDownFromToUsing(
		int anIndex,
		int heapTop,
		Object parent,
		Block aBlock) {
		/*
			 Reset the heap criterium between parent and child nodes
		
			Starting with the element at Integer index anIndex,
			 exchange it with the 'greater' of its children, as determined
			 by the criterium coded in sortBlock.  Continue exchanges until
			 the leaves of the heap are encountered.
		*/
		int swapIndex = anIndex;
		int parentIndex, childIndex1, childIndex2;
		Object temp;

		while ((childIndex1 = (parentIndex = swapIndex) + parentIndex)
			<= heapTop) {
			if ((childIndex2 = childIndex1 + 1) > heapTop) {
				swapIndex = childIndex1;
				temp = elementAt(swapIndex - 1);
			} else if (
				((Boolean) aBlock
					.value(
						elementAt(childIndex1 - 1),
						elementAt(childIndex2 - 1)))
					.booleanValue())
				temp = elementAt((swapIndex = childIndex2) - 1);
			else
				temp = elementAt((swapIndex = childIndex1) - 1);
			if (((Boolean) aBlock.value(parent, temp)).booleanValue())
				setElementAt(temp, parentIndex - 1);
			else {
				setElementAt(parent, parentIndex - 1);
				return;
			}
		}
		setElementAt(parent, parentIndex - 1);
	}
	public Vector collect(Block aBlock) {

		Vector result = new Vector();
		java.util.Enumeration enumi = this.elements();
		while (enumi.hasMoreElements())
			result.addElement(aBlock.value(enumi.nextElement()));
		return result;
	}
	public Object detect(Block aBlock) {

		java.util.Enumeration enumi = this.elements();
		while (enumi.hasMoreElements()) {
			Object each = enumi.nextElement();
			if (((Boolean) aBlock.value(each)).booleanValue())
				return each;
		}
		return null;
	}
	public void doWith(Block aBlock, Vector otherVector) {

		java.util.Enumeration enumi = this.elements();
		java.util.Enumeration otherEnum = otherVector.elements();
		while (enumi.hasMoreElements())
			aBlock.value(enumi.nextElement(), otherEnum.nextElement());
	}
	public void elementsDo(Block aBlock) {

		java.util.Enumeration enumi = this.elements();
		while (enumi.hasMoreElements())
			aBlock.value(enumi.nextElement());
	}
	public Object injectInto(Object initialValue, Block aBlock) {

		Object blockValue = initialValue;
		java.util.Enumeration enumi = this.elements();
		while (enumi.hasMoreElements()) {
			Object each = enumi.nextElement();
			blockValue = aBlock.value(blockValue, each);
		}
		return blockValue;
	}
	public Vector reject(Block aBlock) {

		Vector result = new Vector();
		java.util.Enumeration enumi = this.elements();
		while (enumi.hasMoreElements()) {
			Object each = enumi.nextElement();
			if (!((Boolean) aBlock.value(each)).booleanValue())
				result.addElement(each);
		}
		return result;
	}
	public Vector select(Block aBlock) {

		Vector result = new Vector();
		java.util.Enumeration enumi = this.elements();
		while (enumi.hasMoreElements()) {
			Object each = enumi.nextElement();
			if (((Boolean) aBlock.value(each)).booleanValue())
				result.addElement(each);
		}
		return result;
	}
	public void sort() {
		this.sort(sortBlock);
	}
	public void sort(Block aBlock) {
		/*
			"Perform a sort on the heap."
		
			"The heap structure is such that the element at the root is the
			 'largest'.  Exchange this element with the last leaf in the heap
			 and reduce the size of the heap by 1.  Now bubble down from the
			 root, exhanging with the 'largest' sibling.  The next largest
			 element will now appear at the root.  Continue doing this
			 until all elements are sorted.  Be sure to reset the size variable
			 to its original value."
		*/
		int heapTop = size();
		int count = size();
		while (count-- != 0) {
			Object temp = elementAt(heapTop - 1);
			this.setElementAt(elementAt(1 - 1), heapTop - 1);
			heapTop--;
			this.bubbleDownFromToUsing(1, heapTop, temp, aBlock);
		}
	}
}
