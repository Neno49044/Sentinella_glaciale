package com.example.sentinellaglaciale.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDao {

    private final DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }


    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EMAIL, email);
        values.put(DatabaseHelper.COL_PASSWORD, hashPassword(password));

        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    public boolean login(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String hashedPassword = hashPassword(password);

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COL_EMAIL + "=? AND " + DatabaseHelper.COL_PASSWORD + "=?",
                new String[]{email, hashedPassword},
                null, null, null
        );

        boolean success = cursor.moveToFirst();
        cursor.close();
        db.close();

        return success;
    }
}
