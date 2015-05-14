package com.philemonworks.db.transaction;

/**
 * Interface for classes that acess the database
 * Creation date: (31-03-2000 10:16:27)
 */
public interface IDataAccessor {
/**
 * Retrieve an object whose primary key is @key
 */
public Object findByPrimaryKey(Object key) throws Throwable;
/**
 * Process the delete of an object against the database
 */
void processDelete(Object anObject) throws Throwable;
/**
 * Process the insert of an object against the database
 */
void processInsert(Object anObject) throws Throwable;
/**
 * Process the update of an object against the database
 */
void processUpdate(Object anObject) throws Throwable;
}
