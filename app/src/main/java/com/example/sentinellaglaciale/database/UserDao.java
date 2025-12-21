package com.example.sentinellaglaciale.database;

import android.annotation.SuppressLint;
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

    public boolean registerUser(String email,
                                String username,
                                String password,
                                String imageUri) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EMAIL, email);
        values.put(DatabaseHelper.COL_USERNAME, username);
        values.put(DatabaseHelper.COL_PASSWORD, hashPassword(password));
        values.put(DatabaseHelper.COL_IMAGE_URI, imageUri);

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
    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COL_EMAIL + "=?",
                new String[]{email},
                null, null, null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USERNAME));
            @SuppressLint("Range") String imageUri = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IMAGE_URI));
            user = new User(email, username, imageUri);
            cursor.close();
        }
        db.close();
        return user;
    }

}
