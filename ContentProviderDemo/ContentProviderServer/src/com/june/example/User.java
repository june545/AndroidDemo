package com.june.example;

import android.net.Uri;
import android.provider.BaseColumns;

//BaseColumn类中已经包含了 _id字段
public class User implements BaseColumns {

	public static final String AUTHORITY = "com.june.MyContentProvider";

	public static final Uri CONTENT_URI = Uri.parse("content://com.june.MyContentProvider");
	// 表数据列
	public static final String USER_NAME = "USER_NAME";
}
