package com.example.provider.client;

import android.net.Uri;
import android.provider.BaseColumns;

//BaseColumn  _id
public class User implements BaseColumns {

	public static final String	AUTHORITY	= "com.june.MyContentProvider";

	public static final Uri		CONTENT_URI	= Uri.parse("content://com.june.MyContentProvider");
	public static final String	USER_NAME	= "USER_NAME";
}
