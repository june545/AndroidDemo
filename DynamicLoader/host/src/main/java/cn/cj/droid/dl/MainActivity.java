package cn.cj.droid.dl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
	TextView textView;
	Button   change;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textview);
		change = (Button) findViewById(R.id.change);

		textView.setBackgroundResource(R.drawable.background_);
		change.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.change:
				change();
				break;
		}
	}

	private void change()  {
		try {
			String dest = getExternalCacheDir().getAbsolutePath() + "/tmp.apk";
			AssetsUtil.move(this, "resource-debug.apk", dest);

			Resources skinRes = getSkinRes(this, dest);
			Drawable drawable = skinRes.getDrawable(skinRes.getIdentifier("background_", "drawable", pname));
			textView.setBackgroundDrawable(drawable);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
String pname ;
	private Resources getSkinRes(Context context, String pkgPath) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi = pm.getPackageArchiveInfo(pkgPath, PackageManager.GET_ACTIVITIES);
		pname = pi.packageName;

		Log.d("---", "packageName : " + pname);

		AssetManager am = AssetManager.class.newInstance();
		Method addAssetPath = am.getClass().getMethod("addAssetPath", String.class);
		addAssetPath.invoke(am, pkgPath);

		Resources superRes = context.getResources();
		Resources skinRes = new Resources(am, superRes.getDisplayMetrics(), superRes.getConfiguration());
		return skinRes;
	}
}
