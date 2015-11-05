package com.june.example;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.contentprovider.server.R;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		query();
		insert();
		query();
	}

	private void insert() {
		System.out.println("insert()");
		ContentValues values = new ContentValues();
		values.put(User.USER_NAME, "june-" + System.currentTimeMillis());
		getContentResolver().insert(User.CONTENT_URI, values);
	}

	private void query() {
		System.out.println("query()");
		Cursor cursor = getContentResolver().query(User.CONTENT_URI, new String[] { User.USER_NAME }, null, null, null);
		if (cursor.getCount() > 0) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				String username = cursor.getString(cursor.getColumnIndex(User.USER_NAME));
				System.out.println(username);
			}
		}
	}
}
