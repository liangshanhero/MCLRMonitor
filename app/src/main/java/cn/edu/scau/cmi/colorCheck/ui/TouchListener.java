package cn.edu.scau.cmi.colorCheck.ui;

import android.graphics.Bitmap;

public interface TouchListener {
    void displayRGB(int color);
    void showPicture(Bitmap bitmap);
    void showPicture(byte[] data);

}
