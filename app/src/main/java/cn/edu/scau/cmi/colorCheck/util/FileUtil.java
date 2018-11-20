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
    public static void saveMyBitmap(Bitmap bmp, String picName) throws IOException {
        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;


        File file = new File(galleryPath, picName + ".png");
        FileOutputStream outStream = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, outStream);
        outStream.flush();
        outStream.close();
    }
}