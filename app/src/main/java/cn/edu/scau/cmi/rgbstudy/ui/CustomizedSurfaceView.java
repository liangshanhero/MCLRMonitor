package cn.edu.scau.cmi.rgbstudy.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import java.io.IOException;

/**
 * Created by Mr_Chen on 2018/4/3.
 *
 * 取景的界面
 */

public class CustomizedSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private RGBTouch rgbTouch;
    float touchX, touchY;
    public interface RGBTouch{
        void displayRGB(int color);
        void showPicture(byte[] data);
    }
    public void setGRBTouch(RGBTouch rgbTouch){
        this.rgbTouch = rgbTouch;
    }
    public CustomizedSurfaceView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        camera = openCamera();
        initCameraParams();
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    touchX = event.getX();
                    touchY = event.getY();
                   // Toast.makeText(context, event.getAction()+"点击了一下"+event.getX()+","+event.getY(), Toast.LENGTH_SHORT).show();
                    capture();
                }
                return true;
            }
        });
    }


    private void initCameraParams()
    {
        Camera.Parameters parameters = camera.getParameters();
        //parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setJpegQuality(100);
        camera.setParameters(parameters);
        Log.d("sursize", this.getWidth()+" "+this.getHeight());
        //parameters.setPreviewSize(this.getWidth(),this.getHeight());
       // parameters.setPictureSize(this.getWidth(),this.getHeight());
        //        Log.d("presize", parameters.getPreviewSize().width+" "+parameters.getPreviewSize().height);
//        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
//        for(Camera.Size size  : sizes){
//            Log.d("size", size.width+" "+size.height);
//        }
//        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
//
//        for(Camera.Size size  : sizes){
//            Log.d("size", size.width+" "+size.height);
//        }
    }
    private void capture(){
        camera.setPreviewCallback(null);
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if(success){
                    camera.takePicture(null,null,pc);
                    camera.setPreviewCallback(new Camera.PreviewCallback() {
                        @Override
                        public void onPreviewFrame(byte[] data, Camera camera) {
                            camera.autoFocus(null);
                        }
                    });
                }
            }
        });


    }
    private Camera.PictureCallback pc = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            int x = (int)(touchX*((float)bitmap.getWidth()/getWidth()));
            int y = (int)(touchY*((float)bitmap.getHeight()/getHeight()));
            int argb = bitmap.getPixel(x, y);
             rgbTouch.displayRGB(argb);

        }
    };



    private Camera openCamera(){

        if(camera == null){
            return  Camera.open();
        }
        return camera;
    }
    private void startPreview(SurfaceHolder surfaceHolder){
        if(camera == null){
            camera = openCamera();
        }else{
            try {
                camera.setPreviewCallback(new Camera.PreviewCallback() {
                    @Override
                    public void onPreviewFrame(byte[] data, Camera camera) {
                        camera.autoFocus(null);
                    }
                });
                camera.setPreviewDisplay(surfaceHolder);
                camera.setDisplayOrientation(90);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void releaseCamera(){
        if(camera!=null){
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
    private void stopPreView(){
        if(camera != null){
            camera.stopPreview();
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startPreview(surfaceHolder);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        stopPreView();
        startPreview(surfaceHolder);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
