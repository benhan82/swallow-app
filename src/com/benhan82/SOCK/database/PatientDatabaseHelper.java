package com.benhan82.SOCK.database;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Adapted by Ben Han. Original code from 
 * 
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Android SQLite database and content provider - Tutorial - 
 * by Lars Vogel
 *
 */	
public class PatientDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "patients.db";
	private static final int DATABASE_VERSION = 1;
	
	public PatientDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// Create database when this class is instantiated in the MyApp class
		PatientTable.onCreate(database);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Clear the table
		PatientTable.onUpgrade(db, oldVersion, newVersion);
	}
//	
//	/**
//	 * CRUD operations
//	 * 
//	 * "Create, Read, Update and Delete"
//	 * or in our respective methods called:
//	 * addPatient(), getPatient(), updatePatient(), deletePatient();
//	 * 
//	 */
//	
//	/**
//	 * addPatient(Patient patient)
//	 * 
//	 * Adds the given Patient object to the database.
//	 * 
//	 * @param patient
//	 * @return the row id of the database entry
//	 */
//	public int addPatient(Patient patient) {
//
//		// log transaction
//		Log.d("patient", "addPatient:" + patient.toString());
//		
//		// get reference to database
//    	SQLiteDatabase db = getWritableDatabase();
//		
//    	// create ContentValues to add key "column"/value
//    	ContentValues values = new ContentValues();
//    	values.put(COLUMN_NOTES, patient.getNotes());
//    	values.put(COLUMN_FIRSTNAME, patient.getFirstName());
//    	values.put(COLUMN_LASTNAME, patient.getLastName());
//    	// get a reference to the boolean array
//    	boolean checkBoxes[] = patient.getCheckBoxes();
//    	// if the checkBoxes[i] is true, then save 1 to database, or 0 for false
//    	values.put(COLUMN_CB1, checkBoxes[0] ? 1 : 0);
//    	values.put(COLUMN_CB2, checkBoxes[1] ? 1 : 0);
//    	values.put(COLUMN_CB3, checkBoxes[2] ? 1 : 0);
//    	values.put(COLUMN_CB4, checkBoxes[3] ? 1 : 0);
//    	values.put(COLUMN_CB5, checkBoxes[4] ? 1 : 0);
//    	values.put(COLUMN_CB6, checkBoxes[5] ? 1 : 0);
//    	values.put(COLUMN_CB7, checkBoxes[6] ? 1 : 0);
//    	values.put(COLUMN_CB8, checkBoxes[7] ? 1 : 0);
//    	values.put(COLUMN_CB9, checkBoxes[8] ? 1 : 0);
//    	values.put(COLUMN_CB10, checkBoxes[9] ? 1 : 0);
//    	
//    	// insert contentValues into the database
//    	int rowId = (int) db.insert(TABLE_PATIENTS, null, values);
//    	
//    	// close database
//    	db.close(); 
//    	
//    	if (rowId == -1) {
//    		Log.d("exception", "Error adding patient to the database.");
//    	}
//    	return rowId;
//	}
//		
//	/**
//	 * getPatient(int id)
//	 * 
//	 * Method to retrieve a Patient object from the database. If a patient with an ID matching
//	 * the one given, then null is returned.
//	 * 
//	 * NOTE: The indexing for the database starts at 1, NOT 0. 
//	 * i.e. when the first patient is added, that is assigned an ID of 1, then increments
//	 * 		by 1 with each patient added. If any entries are deleted, their ID's remain the
//	 * 		same.
//	 * 
//	 * 
//	 * @param id
//	 * @return patient
//	 */
//	public Patient getPatient(int id) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // SQLite command string
//        String query = "SELECT * FROM " + TABLE_PATIENTS + " WHERE " + COLUMN_ID + " = " + id;
//        Log.d("patient", "The query command is: "+query);
//        
//        // query database
//        Cursor cursor = db.rawQuery(query, null);
//        
//        // if we got results then move cursor to the first row, should only be one row 
//        // returned from the query, otherwise exit function returning null.
//        if (cursor != null) 
//            cursor.moveToFirst();
//        else
//        	return null;
//                
//        // Build a new Patient object from database info
//        Patient p = new Patient();
//        
//        try {
//        	p.setId(cursor.getInt(PatientDatabaseHelper.ID_INDEX));
//		    p.setNotes(cursor.getString(PatientDatabaseHelper.NOTES_INDEX));
//	        p.setFirstName(cursor.getString(PatientDatabaseHelper.FIRST_INDEX));
//	        p.setLastName(cursor.getString(PatientDatabaseHelper.LAST_INDEX));
//		} catch (Exception e) {
//			Log.d("exception", e.getMessage());
//			e.printStackTrace();
//		}
//        
//        // recreate the check box array by iterating through the columns
//        // if cursor.getInt(i) returns non-zero, then set the corresponding array 
//        // element to true, otherwise false.
//        boolean checkBoxes[] = new boolean[SIZE_OF_CB_ARRAY];
//        for (int i=0; i<SIZE_OF_CB_ARRAY; i++) {
//        	try {
//				checkBoxes[i] = (cursor.getInt(i) != 0) ? true : false;
//			} catch (Exception e) {
//				Log.d("exception", e.getMessage());
//				e.printStackTrace();
//			}
//        }
//        p.setCheckBoxes(checkBoxes);
//        
//        //log 
//        Log.d("patient", "getPatient:" + p.toString());
//        
//        return p;
//	}
//	
//	public List<Patient> getAllPatients() {
//		
//		List<Patient> patients = new LinkedList<Patient>();
//		  
//        // 1. build the query
//        String query = "SELECT * FROM " + TABLE_PATIENTS;
//  
//        // 2. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//  
//        // 3. go over each row, build patient and add it to list
//        Patient p = null;
//        if (cursor.moveToFirst()) {
//            do {
//            	p = new Patient();
//                p.setId(cursor.getInt(0));
//                p.setNotes(cursor.getString(1));
//                p.setFirstName(cursor.getString(2));
//                p.setLastName(cursor.getString(3));
//                // recreate the check box array by iterating through the columns
//                // if cursor.getInt(i) returns non-zero, then set the corresponding array 
//                // element to true, otherwise false.
//                boolean checkBoxes[] = new boolean[SIZE_OF_CB_ARRAY];
//                for (int i=0; i<SIZE_OF_CB_ARRAY; i++) {
//                	checkBoxes[i] = cursor.getInt(i + CB_OFFSET)!=0 ? true : false;
//                }
//                p.setCheckBoxes(checkBoxes);
//  
//                // Add p to patients
//                patients.add(p);
//            } while (cursor.moveToNext());
//        }
//
//        Log.d("patient", "getAllPatients: " + patients.toString());
//  
//        return patients;
//	}
//	
//	public int updatePatient(Patient patient) {
//
//        // 1. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
//     
//        // 2. create ContentValues to add key "column"/value
//        ContentValues values = new ContentValues();
//    	values.put(COLUMN_NOTES, patient.getNotes());
//    	values.put(COLUMN_FIRSTNAME, patient.getFirstName());
//    	values.put(COLUMN_LASTNAME, patient.getLastName());
//    	// create boolean array
//    	boolean checkBoxes[] = patient.getCheckBoxes();
//    	// if the checkBoxes[i] is true, then save 1 to database, or 0 for false
//    	values.put(COLUMN_CB1, checkBoxes[0] ? 1 : 0);
//    	values.put(COLUMN_CB2, checkBoxes[1] ? 1 : 0);
//    	values.put(COLUMN_CB3, checkBoxes[2] ? 1 : 0);
//    	values.put(COLUMN_CB4, checkBoxes[3] ? 1 : 0);
//    	values.put(COLUMN_CB5, checkBoxes[4] ? 1 : 0);
//    	values.put(COLUMN_CB6, checkBoxes[5] ? 1 : 0);
//    	values.put(COLUMN_CB7, checkBoxes[6] ? 1 : 0);
//    	values.put(COLUMN_CB8, checkBoxes[7] ? 1 : 0);
//    	values.put(COLUMN_CB9, checkBoxes[8] ? 1 : 0);
//    	values.put(COLUMN_CB10, checkBoxes[9] ? 1 : 0);
//     
//        // 3. updating row
//        int i = db.update(TABLE_PATIENTS, //table
//                values, // column/value
//                COLUMN_ID + " = ?", // selections
//                new String[] { String.valueOf(patient.getId()) }); //selection args
//     
//        // 4. close
//        db.close();
//        
//        Log.d("patient", "updatePatient:" + patient.toString());
//        
//        return i;
//	}
//	
//	public void deletePatient(int id) {
//
//        // 1. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        // 2. delete
//        Log.d("patient", "deletePatient:" + getPatient(id).toString());
//        
//        db.delete(TABLE_PATIENTS, //table name
//        		COLUMN_ID + " = ?",  // selections
//                new String[] { String.valueOf(id) }); //selections args
// 
//        // 3. close
//        db.close();
// 
//	}
//	
//	public void clearTable() {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
//			onCreate(db);
//			Log.d("patient", "The table \"" + TABLE_PATIENTS + "\" has been cleared. All entries removed.");
//		} catch (SQLException e) {
//			Log.d("exception", e.getMessage());
//			e.printStackTrace();
//		}
//	}
		
}
