package com.philemonworks.db.transaction;

import java.sql.Connection;

public interface IConnectionManagement {
/**
 * Insert the method's description here.
 * Creation date: (18-04-2000 11:58:14)
 */
public void connect() throws Throwable;
/**
 * Insert the method's description here.
 * Creation date: (28.07.2000 18:48:42)
 */
public void disconnect() throws Throwable;
/**
 * Insert the method's description here.
 * Creation date: (18-04-2000 11:58:14)
 */
public Connection getConnection();
/**
 * Insert the method's description here.
 * Creation date: (18-04-2000 11:58:14)
 */
void handleProcessCommitFor (Transaction aTransaction) throws Throwable ;
/**
 * Insert the method's description here.
 * Creation date: (18-04-2000 11:58:14)
 */
public boolean hasConnection();
/**
 * Insert the method's description here.
 * Creation date: (18-04-2000 11:58:14)
 */
public void setConnection(Connection newConnection);
}
