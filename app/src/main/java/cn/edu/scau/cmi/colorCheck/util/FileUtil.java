package cn.edu.scau.cmi.colorCheck.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtil {

    static String galleryPath = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM + File.separator + "colorCheck" + File.separator;
    static File colorCheckBitmapDirectory=new File(galleryPath);
    static {
        if(!colorCheckBitmapDirectory.exists()){
            colorCheckBitmapDirectory.mkdirs();
        }
    }

    public static void saveMyBitmap(Bitmap bmp, String picName) throws IOException {
        File file = new File(colorCheckBitmapDirectory.getAbsolutePath(), picName + ".png");
        FileOutputStream outStream = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, outStream);
        outStream.flush();
        outStream.close();
    }
    
    public  static File[] getAllColorCheckBitmaps(){
        File[] allColorCheckBitmaps = colorCheckBitmapDirectory.listFiles();
        return allColorCheckBitmaps;
        
    }




}