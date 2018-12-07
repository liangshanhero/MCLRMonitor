package cn.edu.scau.cmi.colorCheck.util;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    //系统相册目录,Coolpad放置的地方是手机存储盘：/storage/emulated/0/DCIM/Camera/20181126095938.png
    static String colorCheckBitmapDirectory = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM  + File.separator + "colorCheck" + File.separator;
//    如果指定的色彩不空，就创建该目录。
    static{
        File colorCheckBitmapDirctory=new File(colorCheckBitmapDirectory);
        if(!colorCheckBitmapDirctory.exists()){
            colorCheckBitmapDirctory.mkdirs();
        }
    }

    public static void saveColorCheckBitmap(Bitmap bmp, String picName) throws IOException {
        File file = new File(colorCheckBitmapDirectory, picName + ".png");
        FileOutputStream outStream = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, outStream);
        System.out.println("文件放到的地方是："+file.getAbsolutePath());
        Log.e("图片存放的地方是：",file.getAbsolutePath());
        outStream.flush();
        outStream.close();
    }
//获取所有的指导目录下的图片，从properties文件中或者那里获取呢？
//    利用SharedPreferences记录已经上传的文件
    public static File[] getAllColorCheckBitmaps(){
        File gallery=new File(colorCheckBitmapDirectory);
        return gallery.listFiles();
    }

    public static String getFileName(String bitMapType) {
        String fileName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        return  fileName+bitMapType;
    }
}