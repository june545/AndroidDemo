package com.example.provider.client;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.contentprovider.client.R;

public class ClientActivity extends Activity {
	private Button		query;
	private TextView	text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		query = (Button) findViewById(R.id.query);
		text = (TextView) findViewById(R.id.text);

		query.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				query();
			}
		});
	}

	private void query() {
		System.out.println("query()");
		Cursor cursor = getContentResolver().query(User.CONTENT_URI, new String[] { User.USER_NAME }, null, null, null);
		if (null != cursor && cursor.getCount() > 0) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				String username = cursor.getString(cursor.getColumnIndex(User.USER_NAME));
				text.setText(text.getText().toString() + "\n" + username);
			}
		}
	}
}
