package com.philemonworks.db.util;


/**
 * QueryBuilder is the class that constructs all the neccessary objects to communicate with the
 * database. This class interacts with the IBM Data Access Beans. It can be constructed with
 * two parameters namely a formatted SQL statement and values for the variables. 
 * After having constructed aQueryBuilder, this object can perform either an execute() or an
 * process(). Execute has to be invoked for a SELECT query and process is called for a INSERT, 
 * UPDATE or DELETE function.
 * This requires the following rules:
 * a. SQL format:
 * 		All variables have to be formatted like this: :variableName:javaDataType:DB2DataType:.
 *		For example :ovkid:3:3:.
 * b. The values can be captured in either a double String array, a hashtable or a object of the class com.ey.standard.model.Search.
 *		For performance sake a double String is the fastest way but the other capturers are fast also.
 *		Names of the variables have to be equal to variable in the SQL statement. For the example mentioned above the variable 'ovkid'
 *		has to be found in the valueCapturer with 'ovkid'. For a searchObject its field has to have the name 'fieldOvkid' or 'ovkid'.
 *
 * The Querybuilder can be used in three ways. Some examples are shown to demonstrate its functionality.
 * 1. Performing a query on the database with a fixed number of variables.
 * 		String aSQLStatement = "SELECT * FROM 'tableName' alias WHERE alias.columnName = :var:12:12:"; // String is represented by 12
 * 		String [][] arguments = {{"var", "SOMEVALUE"}};
 * 		QueryBuilder builder = new QueryBuilder(aSQLStatement, arguments);
 * 		builder.execute();
 *
 * 2. Performing a query on the database with a variable number of variables.
 * 		String aSQLStatement = "SELECT * FROM 'tableName' alias 
 *											WHERE	alias.columnName1 = :var:12:12:" 				// 12 represents java.util.String
 *											AND		alias.columnName2 = :otherVar:3:3:"			// 3  represents java.math.BigDecimal
 * 											AND		alias.columnName3 = :anotherVar:91:91:";	// 91 represents java.sql.Date
 * 		com.ey.standard.model.Search searchObject = new com.ey.standard.model.Search();
 * 		searchObject.setVar("SOMEVALUE");
 * 		searchObject.setOtherVar("SOMEOTHERVALUE");
 * 		searchObject.setAnotherVar(null);
 * 		QueryBuilder builder = new QueryBuilder(aSQLStatement, searchObject);
 *		// because anotherVar has the value null, the statement is parsed without the last condition
 * 		builder.execute();
 *
 * 3. Performing a modification on the database.
 * 		String aSQLStatement = "INSERT INTO 'tableName' (var, otherVar) VALUES (:var:3:3:, :otherVar:12:12:)";
 * 		String [][] arguments = {{"var", "SOMEVALUE"}, {"otherVar", "SOMEOTHERVALUE"}};
 * 		QueryBuilder builder = new QueryBuilder(aSQLStatement, arguments);
 * 		builder.process();
 *
 * @author: Richard Blank & Ernest Micklei, PhilemonWorks
 */
 
public class QueryBuilder {
//	private Query query;
//	private String sqlStatement;
//	private StatementMetaData spec = new StatementMetaData();
//	private String[][] values;
//	private Select select;
//	private Statement modify;
//	private Vector variableNames = new Vector();
//	private Vector searchVariableNames = new Vector();
//	private Hashtable tempProcessed = new Hashtable();
//	private int loop = 0;
//	private Hashtable searchValues = new Hashtable();
//	private StringTokenizer tokenizer;
//	public static boolean Trace = false;
///**
// * The fastest way to use the QueryBuilder.
// */
//public QueryBuilder(String newSqlStatement, String[][] newParameterValues) {
//	super();
//	sqlStatement = newSqlStatement;
//	values = newParameterValues;
//}
///**
// * Easy way for dynamic building of SQL-statements. When fields are not set the conditions
// * in the statement will be deleted.
// */
//public QueryBuilder(String newSqlStatement, Search aSearchObject) throws Throwable {
//	super();
//	this.setParameterValuesFrom(aSearchObject);
//	sqlStatement = SQLTokenizer.editStatement(newSqlStatement, searchVariableNames);
//	this.setValuesFromSearchValues();
//}
///**
// * QueryBuilder construction with Hashtable of values.
// *	The table contains key-value pairs which are strings.
// * This table will be transformed into a double String Array.
// */
//public QueryBuilder(String newSqlStatement, Hashtable table) {
//	super();
//	sqlStatement = newSqlStatement;
//	String[][] newParameterValues = new String[table.size()][2];
//	int index = 0;
//	Enumeration enum = table.keys();
//	while (enum.hasMoreElements()) {
//		String key = (String)enum.nextElement();
//		String value = (String)(table.get(key));
//		newParameterValues[index][0] = key;
//		newParameterValues[index][1] = value;
//		index++;
//	}
//	values = newParameterValues;
//}
///**
// * Check to see if a variableName has already been added
// * to the variableNames. This check is not case sensitive.
// *
// * @return: boolean
// */
// private boolean contains(String parameterName) {
//	for (int i = 0; i < variableNames.size(); i++) {
//		if (((String) variableNames.elementAt(i)).equalsIgnoreCase(parameterName))
//			return true;
//	}
//	return false;
//}
///**
// * Checks whether a variableName has already been added
// * to the variableNames. This check is not case sensitive.
// *
// * @return: variableName String. This is the variableName already added to the collection
// */
//private String detect(String variableName) {
//	String each;
//	if (variableNames.contains(variableName))
//		return variableName;
//	Enumeration enum = variableNames.elements();
//	while (enum.hasMoreElements()) {
//		each = (String) enum.nextElement();
//		if (each.equalsIgnoreCase(variableName))
//			return each;
//	}
//	return null;
//}
///**
// * Performing an action (query) on the database. This will return a SelectResult 
// * object when this query answers one or more rows. Otherwise null is returned.
// *
// * Special note: because the garbage collection cannot clean up the created
// * IBM Data Access Beans objects it will be released when no result was found.
// * When the result is not empty then the release method has to be invoked when 
// * all data have been read from the SelectResult object.
// */
//public SelectResult execute() throws Throwable {
//	SelectResult result;
//	select = new Select();
//	this.parse();
//	select.setQuery(new Query(this.getConnection(), spec));
//	this.setParameters();
//	//new com.deltalloyd.standard.util.QueryExplainer(select)
//	select.execute();
//	result = select.getResult();
//	result.close();
//	if (result.getNumRows() == 0) {
//		// helping the garbage collector....
//		((ReleaseableSelectResult)result).release();	
//		return null;
//	}
//	return result;
//}
///**
// * Get the current connection from the connection manager.
// * @return com.ibm.db.DatabaseConnection
// */
//public DatabaseConnection getConnection() {
//	return ConnectionManager.getDefault().getConnection();
//}
///**
// * Parsing of the SQL statement's variables. All retrieved variables in the statement
// * have to exist in the values collection. Otherwise this variable will not be processed
// * beacuse there is no value for it. If a variable occurs two or more times in a statement
// * it has to be processed again with a different name.
// */
//private void parse() throws Throwable {
//	spec.setSQL(sqlStatement);
//	if (values.length > 0) {
//		String name = null;
//		String javaType = null;
//		String sqlType = null;
//		tokenizer = new java.util.StringTokenizer(sqlStatement, ":");
//		tokenizer.nextToken();
//		while (tokenizer.hasMoreTokens()) {
//			name = tokenizer.nextToken().toLowerCase();
//			javaType = tokenizer.nextToken();
//			sqlType = tokenizer.nextToken();
//			if(Trace)
//			System.out.println("name: " + name + '\n' + "javaType: " + javaType + '\n' + "sqlType: " + sqlType);
//			if(!this.contains(name)) {
//				this.processParameters(name, javaType, sqlType);
//			}else{
//				// When the same parameter is used more than once in a statement
//				// it has to be added as many times it is used. However it cannot be added
//				// with the same name, so we put a number before for distinction.
//				if(tempProcessed.get(name) == null) {
//					tempProcessed.put(name, new Vector());
//				}
//				((Vector) tempProcessed.get(name)).addElement(name);
//				int nr = ((Vector) tempProcessed.get(name)).size();
//				this.processParameters(String.valueOf(nr) + name, javaType, sqlType);
//				
//				if(Trace)
//					System.out.println("This is already defined: " + name + "(" + nr + ")");
//			}
//			if(tokenizer.hasMoreTokens()) tokenizer.nextToken();
//		}
//	}
//}
///**
// * This method performs a modification on the database.
// *
// * @return: numberOfAffectedRows int
// */
//public int process() throws Throwable {
//	modify = new SQLStatement();
//	this.parse();
//	modify.setMetaData(spec);
//	modify.setConnection(this.getConnection());
//	this.setParameters();
//	//new com.deltalloyd.standard.util.QueryExplainer(modify)
//	modify.execute();
//	return modify.getNumAffectedRows();
//}
///**
// * Process the parameter into the IBM Data Access Beans and store it that is has been processed.
// */
//private void processParameters(String variableName, String javaType, String sqlType) throws Throwable {
//	spec.addParameter(variableName, new Integer(javaType).intValue(), new Integer(sqlType).intValue());
//	variableNames.addElement(variableName);
//}
///**
// * Setting of all the filtered variables with its value in the IBM Data Access Beans.
// */
//private void setParameters() throws Throwable {
//	for (int i = 0; i < values.length; i++) {
//		for (int j = 0; j < 1; ++j) {
//			String name = values[i][j];
//			name = this.detect(name);
//			String value = values[i][j + 1];
//			if(select != null) {
//				select.setParameterFromString(name, value);
//				// When the same parameter is used more than once in a statement
//				// it has to be added as many times it is used. However it cannot be added
//				// with the same name, so we put a number before for distinction.
//				// Here is the place we retrieve these names to put the value
//				if(tempProcessed.get(name) != null) {
//					int size = ((Vector) tempProcessed.get(name)).size();
//					for(int s = 1; s <= size; s++) {
//						select.setParameterFromString(String.valueOf(s) + name, value);
//					}
//				}
//			}else {
//				modify.setParameterFromString(name, value);
//				// See comment above
//				if(tempProcessed.get(name) != null) {
//					int size = ((Vector) tempProcessed.get(name)).size();
//					for(int s = 1; s <= size; s++) {
//						modify.setParameterFromString(String.valueOf(s) + name, value);
//					}
//				}
//			}
//		}
//	}
//}
///**
// * Reading the values of all fields of aSearchObject. When a value is not null
// * the name of this field is stored into a collection and also its name with its value 
// * into antoher collection.
// */
//private void setParameterValuesFrom(Search aSearchObject) throws Throwable {
//	Class aClass = aSearchObject.getClass();
//	java.lang.reflect.Field[] fields = aClass.getDeclaredFields();
//
//	for(int i = 0; i < fields.length; i++) {
//		String fieldName = fields[i].getName();
//		fieldName = fieldName.substring(5,fieldName.length());
//		String methodName = "get" + fieldName;
//		java.lang.reflect.Method method = aClass.getMethod(methodName, new Class[0]);
//		Object value = method.invoke(aSearchObject, new Object[0]);
//		if(value != null) {
//			if(((String) value).length() != 0) {
//				if( !((String) value).substring(0,1).equals(" ")) {
//					fieldName = fieldName.toLowerCase();
//					searchVariableNames.addElement(fieldName.toLowerCase());
//					searchValues.put(fieldName, value);
//				}
//			}
//		}
//	}
//}
///**
// * Filling the values (Sting[][]) from searchValues (Hashtable) .
// */
// 
//private void setValuesFromSearchValues() {
//	Enumeration enum = searchValues.keys();
//	values = new String[searchValues.size()][2];
//	String each = null;
//	int loop = 0;
//	while (enum.hasMoreElements()) {
//		each = (String) enum.nextElement();
//		values[loop][0] = each;
//		values[loop++][1] = (String) searchValues.get(each);
//	}
//}
///**
// * Returns a String that represents the value of this object.
// * @return a SQL statement
// */
//public String toString() {
//	return sqlStatement;
//}
}
