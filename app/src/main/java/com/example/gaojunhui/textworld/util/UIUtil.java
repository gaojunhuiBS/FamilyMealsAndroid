package com.example.gaojunhui.textworld.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * des:屏幕相关辅助类
 */
public class UIUtil {
	private UIUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	public static boolean APP_DBG = false; // 是否是debug模式

	public static void init(Context context){
		APP_DBG = isApkDebugable(context);
	}

	/**
	 * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
	 * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
	 * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
	 * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
	 * @param context
	 * @return
	 * @date   2015-8-7
	 */
	public static boolean isApkDebugable(Context context) {
		try {
			ApplicationInfo info= context.getApplicationInfo();
			return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
		} catch (Exception e) {

		}
		return false;
	}
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(Context context,float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/*
	获取屏幕密度
	 */
	public static int getDeviceDip(Context context) {
		return context.getResources().getDisplayMetrics().densityDpi;
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 * @return
	 */
	public static int dip2px( Context context ,float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(Context context,float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context,float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 直接获取控件的宽、高
	 * @param view
	 * @return int[]
	 */
	public static int[] getWidgetWH(final View view){
	    ViewTreeObserver vto2 = view.getViewTreeObserver();
	    vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	        @Override
	        public void onGlobalLayout() {
	        	view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	        }
	    });
		return new int[]{view.getWidth(),view.getHeight()};
	}

	/**
	 * 直接获取控件的高
	 * @param view
	 * @return int[]
	 */
	public static int getViewHeight(final View view){
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		return view.getHeight();
	}

	/**
	 * 直接获取控件的宽
	 * @param view
	 * @return int[]
	 */
	public static int getViewWidth(final View view){
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		return view.getWidth();
	}
	
	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获取控件的宽
	 * @param view
	 * @return
	 */
	public static int getWidgetWidth(View view){
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);//先度量  
		int width = view.getMeasuredWidth();  
		return width;
	}
	/**
	 * 获取控件的高
	 * @param view
	 * @return
	 */
	public static int getWidgetHeight(View view){
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);//先度量  
		int height = view.getMeasuredHeight();
		return height;
	}
	/**
	 * 设置控件宽
	 * @param view
	 * @param width
	 */
	public static void setWidgetWidth(View view, int width){
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
		params.width = width;
		view.setLayoutParams(params);
	}
	/**
	 * 设置控件高
	 * @param view
	 * @param height
	 */
	public static void setWidgetHeight(View view, int height){
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
		params.height = height;
		view.setLayoutParams(params);
	}
	//获得状态栏的高度
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resId > 0) {
			result = context.getResources().getDimensionPixelOffset(resId);
		}
		return result;
	}
	/**
	 * SD卡判断
	 *
	 * @return
	 */
	public static boolean isSDCardAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 是否有网
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 返回版本名字
	 * 对应build.gradle中的versionName
	 *
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = null;
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionName = packInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 返回版本号
	 * 对应build.gradle中的versionCode
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context) {
		String versionCode = null;
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionCode = String.valueOf(packInfo.versionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 获取设备的唯一标识，deviceId
	 *
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		if (deviceId == null) {
			return "";
		} else {
			return deviceId;
		}
	}

	/**
	 * 获取手机品牌
	 *
	 * @return
	 */
	public static String getPhoneBrand() {
		return android.os.Build.BRAND;
	}

	/**
	 * 获取手机型号
	 *
	 * @return
	 */
	public static String getPhoneModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取手机Android API等级（22、23 ...）
	 *
	 * @return
	 */
	public static int getBuildLevel() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 获取手机Android 版本（4.4、5.0、5.1 ...）
	 *
	 * @return
	 */
	public static String getBuildVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取当前App进程的id
	 *
	 * @return
	 */
	public static int getAppProcessId() {
		return android.os.Process.myPid();
	}

	/**
	 * 获取当前App进程的Name
	 *
	 * @param context
	 * @param processId
	 * @return
	 */
	public static String getAppProcessName(Context context, int processId) {
		String processName = null;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		// 获取所有运行App的进程集合
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = context.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == processId) {
					CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));

					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
				Log.e(UIUtil.class.getName(), e.getMessage(), e);
			}
		}
		return processName;
	}

	/**
	 * 创建App文件夹
	 *
	 * @param appName
	 * @param application
	 * @return
	 */
	public static String createAPPFolder(String appName, Application application) {
		return createAPPFolder(appName, application, null);
	}

	/**
	 * 创建App文件夹
	 *
	 * @param appName
	 * @param application
	 * @param folderName
	 * @return
	 */
	public static String createAPPFolder(String appName, Application application, String folderName) {
		File root = Environment.getExternalStorageDirectory();
		File folder;
		/**
		 * 如果存在SD卡
		 */
		if (UIUtil.isSDCardAvailable() && root != null) {
			folder = new File(root, appName);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		} else {
			/**
			 * 不存在SD卡，就放到缓存文件夹内
			 */
			root = application.getCacheDir();
			folder = new File(root, appName);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
		if (folderName != null) {
			folder = new File(folder, folderName);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
		return folder.getAbsolutePath();
	}

	/**
	 * 通过Uri找到File
	 *
	 * @param context
	 * @param uri
	 * @return
	 */
	public static File uri2File(Activity context, Uri uri) {
		File file;
		String[] project = {MediaStore.Images.Media.DATA};
		Cursor actualImageCursor = context.getContentResolver().query(uri, project, null, null, null);
		if (actualImageCursor != null) {
			int actual_image_column_index = actualImageCursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			actualImageCursor.moveToFirst();
			String img_path = actualImageCursor
					.getString(actual_image_column_index);
			file = new File(img_path);
		} else {
			file = new File(uri.getPath());
		}
		if (actualImageCursor != null) actualImageCursor.close();
		return file;
	}

	/**
	 * 获取AndroidManifest.xml里 的值
	 * @param context
	 * @param name
	 * @return
	 */
	public static String getMetaData(Context context, String name) {
		String value = null;
		try {
			ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			value = appInfo.metaData.getString(name);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return value;
	}


}
