package com.philemonworks.db.transaction;

/**
 * Insert the type's description here.
 * Creation date: (27-03-2000 13:59:26)
 * @author Ernest Micklei
 */
import java.util.Vector;
import java.util.Enumeration;

public class Transaction {

		// identification
		public String name = "";
		// hierarchy
		private Transaction parent = null;
		private Vector children = new Vector();
		// state
		protected Vector inserted = new Vector();
		protected Vector updated = new Vector();
		protected Vector deleted = new Vector();
		// state for rollbacks
		private Vector updateCopies = new Vector();
		// event support
		protected transient java.beans.PropertyChangeSupport propertyChange;
/**
 * Transaction constructor comment.
 */
public Transaction() {

	super();
	name = "Transaction " + hashCode();
}
/**
 * Transaction constructor comment.
 */
public Transaction(java.lang.String itsName) {

	super();	
	name = itsName;
}
/**
 * Insert the method's description here.
 * Creation date: (27-03-2000 16:30:02)
 */
private void addAbsentDeleted (Object newObject) {

	if (deleted.contains(newObject)) return;
	deleted.addElement(newObject);
}
/**
 * The addPropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().addPropertyChangeListener(listener);
}
/**
 * Commit all registered changes to
 * - to the database using the object's dataaccessor
 * - the parent transaction if the receiver was a child
 */
public void commit() throws Throwable {

	this.fireBeforeCommit();
	if (this.isRoot()) {
		this.fireBeforeRootCommit();
		// dispatch the request to the connection manager
		this.getConnectionManager().handleProcessCommitFor(this);
		this.fireAfterRootCommit();
	} else {
		this.commitToParent();
	}
	if (!this.isRoot())
		parent.removeChild(this);
	this.fireAfterCommit();
}
/**
 * Insert the method's description here.
 * Creation date: (27-03-2000 15:55:34)
 */
private void commitToParent() {

	Object each, parentEach,key,updateCopyEach;
	
	// put inserted objects in the parent
	Enumeration enum = inserted.elements();
	while (enum.hasMoreElements()) {
		parent.insert(enum.nextElement());
	}
	// handle updates
	enum = updated.elements();
	while (enum.hasMoreElements()) {
		each = enum.nextElement();
		key = ((IPersistent)each).getPrimaryKey();
		parentEach = parent.updatedAtKey (key);
		if (parentEach == null) {
			parentEach = parent.insertedAtKey (key);
			if (parentEach == null) {
				// move copy and each to the parent
				updateCopyEach = this.updateCopiesAtKey (key);
				parent.updateCopies.addElement(updateCopyEach);
				parent.updated.addElement(each);
			}	
		}
	}
	enum = deleted.elements();
	while (enum.hasMoreElements()) {
		parent.addAbsentDeleted(enum.nextElement());
	}
}
/**
 * Register the object for delete
 */
public void delete (Object obsoleteObject) throws Throwable {

	// was it created in the receiver ?
	if (inserted.removeElement(obsoleteObject)) return;
	// already registered for delete ?
	if (deleted.contains(obsoleteObject)) return;
	// was is updated in the receiver ?
	updated.removeElement(obsoleteObject);
	updateCopies.removeElement(obsoleteObject);
	// delete any aggregates
	((IPersistent)obsoleteObject).deleteAggregatesIn(this);
	// register for delete
	deleted.addElement(obsoleteObject);
}
/**
 * Return the object or null whose key equals @key
 * Creation date: (27-03-2000 16:30:02)
 */
private Object deletedAtKey (Object key) {

	Enumeration enum = deleted.elements();
	Object each;
	
	while(enum.hasMoreElements()) {
		each = enum.nextElement();
		if ( ((IPersistent)each).getPrimaryKey().equals(key) ) return each;
	}
	return null;	
}
/**
 */
private void fireAfterCommit() {

	if (propertyChange == null) return; // if nobody listens, we don't care
	this.getPropertyChange().firePropertyChange("afterCommit", (Object)this, (Object)this);

}
/**
 */
private void fireAfterRollback() {

	if (propertyChange == null) return; // if nobody listens, we don't care
	this.getPropertyChange().firePropertyChange("afterRollback", (Object)this, (Object)this);

}
/**
 */
private void fireAfterRootCommit() {

	if (propertyChange == null) return; // if nobody listens, we don't care
	this.getPropertyChange().firePropertyChange("afterRootCommit", (Object)this, (Object)this);

}
/**
 */
private void fireBeforeCommit() {

	if (propertyChange == null) return; // if nobody listens, we don't care	
	this.getPropertyChange().firePropertyChange("beforeCommit", (Object)this, (Object)this);

}
/**
 */
private void fireBeforeRollback() {

	if (propertyChange == null) return; // if nobody listens, we don't care	
	this.getPropertyChange().firePropertyChange("beforeRollback", (Object)this, (Object)this);

}
/**
 */
private void fireBeforeRootCommit() {

	if (propertyChange == null) return; // if nobody listens, we don't care
	this.getPropertyChange().firePropertyChange("beforeRootCommit", (Object)this, (Object)this);

}
/**
 * 
 */
public static IConnectionManagement getConnectionManager (){

	return null;
}
/**
 * Accessor for the propertyChange field.
 */
protected java.beans.PropertyChangeSupport getPropertyChange() {
	if (propertyChange == null) {
		propertyChange = new java.beans.PropertyChangeSupport(this);
	};
	return propertyChange;
}
/**
 * Answer whether any object is registered
 */
public boolean hasChanges() {

	if (inserted.size() > 0) return true;
	if (updated.size() > 0) return true;
	if (deleted.size() > 0) return true;
	return false;
}
/**
 * Register the object for insert (typically for new instances)
 */
public void insert (Object newObject) {

	// already inserted ?
	if (inserted.contains(newObject)) return;
	// is registered for delete ?
	if (deleted.contains(newObject)) {
		throw new RuntimeException(newObject.toString());
	}
	// add to inserted
	inserted.addElement(newObject);
}
/**
 * Return the object or null whose key equals @key
 * Creation date: (27-03-2000 16:30:02)
 */
private Object insertedAtKey (Object key) {

	Enumeration enum = inserted.elements();
	Object each;
	
	while(enum.hasMoreElements()) {
		each = enum.nextElement();
		if ( ((IPersistent)each).getPrimaryKey().equals(key) ) return each;
	}
	return null;	
}
/**
 * Insert the method's description here.
 * Creation date: (27-03-2000 15:55:34)
 */
private boolean isRoot() {

	return parent == null;
}
/**
 * Answer a new transaction that becomes a child of the receiver
 */
public Transaction newSubTransaction() {

	Transaction tx = new Transaction();
	
	children.addElement (tx);
	tx.parent = this;
	return tx;
}
/**
 * Answer a new transaction with name @subName that becomes a child of the receiver
 */
public Transaction newSubTransaction(String subName) {

	Transaction tx = new Transaction(subName);
	
	children.addElement (tx);
	tx.parent = this;
	return tx;
}
/**
 * Insert the method's description here.
 * Creation date: (27-03-2000 15:55:34)
 */
public void processCommit() throws Throwable  {

	Object each;

	Enumeration enum = inserted.elements();
	while (enum.hasMoreElements()) {
		each = enum.nextElement();
		((IDataAccessor)((IPersistent)each).getDataAccessor()).processInsert(each);
	}	
	enum = updated.elements();
	while (enum.hasMoreElements()) {
		each = enum.nextElement();
		((IDataAccessor)((IPersistent)each).getDataAccessor()).processUpdate(each);
	}
	enum = deleted.elements();
	while (enum.hasMoreElements()) {
		each = enum.nextElement();
		((IDataAccessor)((IPersistent)each).getDataAccessor()).processDelete(((IPersistent) each).getPrimaryKey());

	}	
}
/**
 * Insert the method's description here.
 * Creation date: (27-03-2000 15:55:34)
 */
private void processRollback() {

	Enumeration enum = updated.elements();
	Enumeration copyEnum = updateCopies.elements();
	Object each, eachCopy;

	while (enum.hasMoreElements()) {
		each = (IPersistent)enum.nextElement();
		eachCopy = (IPersistent)copyEnum.nextElement();
		((IPersistent)each).rollbackFromClone (eachCopy);
	}
}
/**
 * Insert the method's description here.
 * Creation date: (27-03-2000 15:55:34)
 */
private void removeChild(Transaction tx) {

	children.removeElement(tx);
	tx.parent = null;
}
/**
 * Undo any changes to registered objects
 * Inserted and deleted objects are discarded
 * Updated objects will restore their state
 */
public void rollback() {

	this.fireBeforeRollback();
	if (this.isRoot()) {
		this.processRollback();
	} else {
		this.rollbackToParent();
	}
	if (!this.isRoot())
		parent.removeChild(this);
	this.fireAfterRollback();	
}
/**
 * if ( the updated is not included in the parent state )
 		use the source copy from updateCopies
 		from the receiver to restore state
 	else
 		use th source copy from the parent's updateCopies
 * Creation date: (27-03-2000 15:55:34)
 */
private void rollbackToParent() {

	Enumeration enum = updated.elements();
	Object each, parentEach, oldEach, key;

	while (enum.hasMoreElements()) {
		each = enum.nextElement();
		key = ((IPersistent)each).getPrimaryKey();
		parentEach = parent.updatedAtKey (key);
		if (parentEach == null) { // no updated in parent
			oldEach = this.updateCopiesAtKey (key);
		} else {
			oldEach = parent.updateCopiesAtKey (key);
		}
		((IPersistent)each).rollbackFromClone(oldEach);
	}
}
/**
 * Answer a string representation of the receiver
 */
public String toString() {

	String myString;
	if (this.isRoot()) {
		myString = "ROOT-";
	} else {
		myString = "SUB-";
	}
	return myString + this.name + ": " + super.hashCode();
}
/**
 * Register an object for update
 */
public void update (Object objectToUpdate) {

	// is just created ?
	if (inserted.contains(objectToUpdate)) return;
	// is already registered for updating ?
	if (updated.contains(objectToUpdate)) return;
	// is registered for delete ?
	if (deleted.contains(objectToUpdate)) {
		throw new RuntimeException(objectToUpdate.toString());
	}
	// store a shallow copy for rollbacks
	try {
		updateCopies.addElement( ((IPersistent)objectToUpdate).cloneForRollback() );
	} catch (CloneNotSupportedException ex) {
		throw new RuntimeException(objectToUpdate.toString());
	}
	// register it
	updated.addElement(objectToUpdate);
	// register any aggregates too
	((IPersistent)objectToUpdate).updateAggregatesIn(this);
}
/**
 * Return the object or null whose key equals @key
 * Creation date: (27-03-2000 16:30:02)
 */
protected Object updateCopiesAtKey (Object key) {

	Enumeration enum = updateCopies.elements();
	Object each;
	
	while(enum.hasMoreElements()) {
		each = enum.nextElement();
		if ( ((IPersistent)each).getPrimaryKey().equals(key) ) return each;
	}
	return null;	
}
/**
 * Return the object or null whose key equals @key
 * Creation date: (27-03-2000 16:30:02)
 */
private Object updatedAtKey (Object key) {

	Enumeration enum = updated.elements();
	Object each;
	
	while(enum.hasMoreElements()) {
		each = enum.nextElement();
		if ( ((IPersistent)each).getPrimaryKey().equals(key) ) return each;
	}
	return null;	
}
}
