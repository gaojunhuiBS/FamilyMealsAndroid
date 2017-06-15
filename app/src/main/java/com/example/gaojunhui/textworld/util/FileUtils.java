package com.example.gaojunhui.textworld.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/9.
 */
public class FileUtils {
    public static File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        try {
            File imageFile = File.createTempFile(imageFileName,  /* prefix */
                    ".png",         /* suffix */
                    Environment.getExternalStorageDirectory()      /* directory */);
            return imageFile;
        } catch (IOException e) {
            //do noting
            return null;
        }
    }

    public static String creatImagePath(String imageName) {
        String local_file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IceWorld";
        File f = new File(local_file);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f.getAbsolutePath() + "/" + imageName + ".png";

    }

    public static boolean isFileExist(File file) {
        return file != null && file.exists() && file.length() > 0;
    }

    /**
     * 当SD卡存在或者SD卡不可被移除的时候，
     * 就调用getExternalCacheDir()方法来获取缓存路径，
     * 否则就调用getCacheDir()方法来获取缓存路径。
     * 前者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，
     * 而后者获取到的是 /data/data/<application package>/cache 这个路径。
     *
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 检查sdCard是否存在
     *
     * @return
     */
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建文件夹
     *
     * @param context
     * @return
     */

    public static String setMkdir(Context context) {
        String filePath;
        if (checkSDCard()) {
            filePath = Environment.getExternalStorageDirectory() + File.separator + "myWorld/";
        } else {
            filePath = context.getCacheDir().getAbsolutePath() + File.separator + "myWorld/";
        }
        File file = new File(filePath);
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Log.e("file", "文件不存在  创建文件 " + b);
        } else {
            Log.e("file", "文件存在");
        }
        return filePath;
    }

    /**
     * 文件转化为字节数组
     */
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** */
    /**
     * 把字节数组保存为一个文件
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }
    /** */
    /**
     * 从字节数组获取对象
     */
    public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }
    /**
     * 从对象获取一个字节数组
     */
    public static byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
    /**
     * bitmap转byte[]
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutPutStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutPutStream);
        return byteArrayOutPutStream.toByteArray();
    }
    /**
     * 将File输出到指定路径
     */
    public static void outFile2Path(File f, String path) throws Exception {

            byte[] bytes = getBytesFromFile(f);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
            fileOutputStream.close();

    }

    /**
     * 保存文件到私有路径data/data/files
     *
     * @param mode     模式
     * @throws Exception
     */
    public static void save(Context context, String fileName, File file, int mode) {
        FileOutputStream fos = null;
        byte[] bytes = null;
        try {
            bytes = getBytesFromFile(file);
            fos = context.openFileOutput(fileName, mode);
            //写入数据
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭输出流
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 保存文件，以私有模式保存
     *
     * @throws Exception
     */
    public static void save(Context context, String fileName, File file) {
        save(context, fileName, file, Context.MODE_PRIVATE);
    }

    /**
     * 删除私有路径下的文件
     */
    public static void delete(Context context, String fileName) {
        if (context.deleteFile(fileName)){
            Log.e("deleteFile","deleteFile: "+ fileName + " sucessfully");
        }else{
            Log.e("deleteFile","deleteFile: "+ fileName + " failed");
        }
    }
    /**
     * 列出所有已创建的文件
     */
    public static void listFile(Context context){
        String[] strings=context.fileList();
        for (String f: strings){
            Log.e("listFile", "listFile: "+f );
        }
    }
    public static String[] listFile_return(Context context){
        String[] strings=context.fileList();
        return strings;
    }
    /**
     * 往存储卡中写入数据
     *
     * @param filename
     * @param content
     * @throws Exception
     */
    public void saveToSDCard(String filename, String content) throws Exception {
        //SDcard路径在2.2以前是/sdcard，在2.2以上版本中是/mnt/sdcard，最好采用下面的方式灵活获取，适用于所有版本
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        FileOutputStream fos = new FileOutputStream(file);
        //写入数据
        fos.write(content.getBytes());
        //关闭输出流
        fos.close();

    }

    public String readFile(Context context, String filename) throws Throwable {
        FileInputStream fis = context.openFileInput(filename);
        byte[] buf = new byte[1024];
        int len = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //读取数据
        while ((len = fis.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        byte[] data = baos.toByteArray();
        //关闭流
        baos.close();
        fis.close();
        return new String(data);
    }
    /**
     *
     * @param fromPath 被复制的文件路径
     * @param toPath 复制的目录文件路径
     * @param rewrite 是否重新创建文件
     *
     * <p>文件的复制操作方法
     */
    public static void copyfile(String fromPath, String toPath, Boolean rewrite ){

        File fromFile = new File(fromPath);
        File toFile = new File(toPath);

        if(!fromFile.exists()){
            return;
        }
        if(!fromFile.isFile()){
            return;
        }
        if(!fromFile.canRead()){
            return;
        }
        if(!toFile.getParentFile().exists()){
            toFile.getParentFile().mkdirs();
        }
        if(toFile.exists() && rewrite){
            toFile.delete();
        }

        try {
            FileInputStream fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);

            byte[] bt = new byte[1024];
            int c;
            while((c=fosfrom.read(bt)) > 0){
                fosto.write(bt,0,c);
            }
            //关闭输入、输出流
            fosfrom.close();
            fosto.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}


