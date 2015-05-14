package com.philemonworks.db.transaction;

/**
 * Insert the type's description here.
 * Creation date: (31-03-2000 13:29:20)
 * @author Ernest Micklei
 */
public class TransactionWithoutRollback extends Transaction {
/**
 * TransactionWithoutRollback constructor comment.
 */
public TransactionWithoutRollback() {
	super();
}
/**
 * TransactionWithoutRollback constructor comment.
 * @param itsName java.lang.String
 */
public TransactionWithoutRollback(String itsName) {
	super(itsName);
}
/**
 * Throw an exception because the receiver does not support rollbacks
 */
public void rollback() {

	throw new RuntimeException(this.toString());
}
/**
 * Comment see super
 * Changed from super: no clone creation
 * Creation date: (27-03-2000 16:30:02)
 */
public void update (Object objectToUpdate) {

	// is just created ?
	if (inserted.contains(objectToUpdate)) return;
	// is already registered for updating ?
	if (updated.contains(objectToUpdate)) return;
	// is registered for delete ?
	if (deleted.contains(objectToUpdate)) {
		throw new RuntimeException();
	}
	// do not store a shallow copy for rollbacks
	// register it
	updated.addElement(objectToUpdate);
	// register any aggregates too
	((IPersistent)objectToUpdate).updateAggregatesIn(this);
}
}
