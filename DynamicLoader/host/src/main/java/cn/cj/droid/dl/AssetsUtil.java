package cn.cj.droid.dl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by June on 2016/8/18.
 */
public class AssetsUtil {

	public static String read(Context context, String filename) {
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			in = context.getAssets().open(filename);
			isr = new InputStreamReader(in);
			br = new BufferedReader(isr);
			String sline;
			StringBuffer sb = new StringBuffer();
			while ((sline = br.readLine()) != null) {
				sb.append(sline);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static boolean move(Context context, String filename, String destPath) {
		InputStream in = null;
		DataInputStream dis = null;
		try {
			in = context.getAssets().open(filename);
			dis = new DataInputStream(in);

			File file = new File(destPath);
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);

			byte[] buff = new byte[2048];
			int count;
			while ((count = dis.read(buff)) != -1){
				dos.write(buff, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

}
