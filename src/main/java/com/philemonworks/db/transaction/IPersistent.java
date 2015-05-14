package com.philemonworks.db.transaction;

/**
 * Interface for classes that are persistent used in memory transactions
 * Creation date: (28-03-2000 15:32:47)
 * @author Ernest Micklei, PhilemonWorks
 */
public interface IPersistent extends Cloneable {
/*
public void updateAggregatesIn(Transaction tx) {
	on default the receiver does not aggregate
}
*/
/**
 * Answer a new object like the receiver that can be
 * used to restore state in case of a rollback request
 * to a transaction in which the receiver is registered
 * for update
 */
Object cloneForRollback () throws CloneNotSupportedException;
/*
public Object cloneForRollback() throws CloneNotSupportedException {
	return super.clone();
}
*/
/**
 * Redefine this method in case the receive
 * has aggregate objects that should be
 * deleted in response to a delete request
 * send to a transaction
 */
void deleteAggregatesIn(Transaction tx) throws Throwable;
/*
public void deleteAggregatesIn(Transaction tx) {
	on default the receiver does not aggregate
}
*/
/**
 * This comparison method is used for tracking
 * cloned objects in the collection of updateCopies
 * as part of a transaction. 
 */
public boolean equals(IPersistent anObject);
/**
 * Answer the accessor for processing changes to receiver
 */
public IDataAccessor getDataAccessor();
/**
 * Answer the receiver's keyclass instance
 */
public Object getPrimaryKey();
/**
 * Because equals() is reimplemented, 
 * the receiver needs to answer a different hashCode value
 */
public int hashCode ();
/**
 * Restore the state of the receiver using the backupObject
 * Use set methods to allow property change events to be fired
 */
void rollbackFromClone (Object backupObject);
/*
public void rollbackFromClone (Object backupObject);

	this.setSomeField(((CD)backupObject).fieldSomeField);
}
*/
/**
 * Set the keyclass instance for the receiver
 */
public void setPrimaryKey(Object key);
/**
 * Redefine this method in case the receive
 * has aggregate objects that should be
 * updated in response to an update request
 * send to a transaction
 */
void updateAggregatesIn(Transaction tx);
}
