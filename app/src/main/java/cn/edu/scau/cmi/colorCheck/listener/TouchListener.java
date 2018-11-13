package cn.edu.scau.cmi.colorCheck.listener;

import android.graphics.Bitmap;
//使用这个接口，在不同的Activety类中实现业务
public interface TouchListener {
//    显示选中点的RGB的值
    void displayRgb(int color);
//    显示图片截取后的特征点的坐标
    void displayRgbOnCoordinate(Bitmap bitmap);

    void showPicture(Bitmap bitmap);
    void showPicture(byte[] data);

}
