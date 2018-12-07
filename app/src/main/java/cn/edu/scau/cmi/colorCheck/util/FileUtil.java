package cn.edu.scau.cmi.colorCheck.util;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    //系统相册目录,Coolpad放置的地方是手机存储盘：/storage/emulated/0/DCIM/Camera/20181126095938.png
    static String colorCheckBitmapDirectoryName = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM  + File.separator + "colorCheck" + File.separator;
    static File colorCheckBitmapDirectory=new File(colorCheckBitmapDirectoryName);
    //    如果手机指定的色彩检测保存路径不空，就创建该目录。
    static{
        if(!colorCheckBitmapDirectory.exists()){
            colorCheckBitmapDirectory.mkdirs();
        }
    }

    public static void saveColorCheckBitmap(Bitmap bmp, String picName) throws IOException {
        File file = new File(colorCheckBitmapDirectory, picName + ".png");
        FileOutputStream outStream = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, outStream);
        outStream.flush();
        outStream.close();
    }

//获取所有的指导目录下的图片，从properties文件中或者那里获取呢？
//    利用SharedPreferences记录已经上传的文件
    public static File[] getAllColorCheckBitmaps(){
        File gallery=new File(colorCheckBitmapDirectoryName);
        return gallery.listFiles();
    }

    public static String getFileName(String projectName,String bitMapType,String result) {
        String fileName=new SimpleDateFormat("_yyyyMMddhhmmss_").format(new Date());
        return  projectName+"_"+bitMapType+fileName+result;
    }
}