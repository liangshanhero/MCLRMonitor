package cn.edu.scau.cmi.rgbstudy.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.dingmouren.colorpicker.ColorPickerDialog;
import com.dingmouren.colorpicker.OnColorPickerListener;

import java.io.File;

import cn.edu.scau.cmi.rgbstudy.dao.Service;
import cn.edu.scau.cmi.rgbstudy.domain.Sample;
import cn.edu.scau.cmi.rgbstudy.ui.CustomizedSurfaceView;
import cn.edu.scau.cmi.rgbstudy.R;

public class SampleCollectActivity extends AppCompatActivity implements View.OnTouchListener{
    private static final int TAKE_PHOTO =  1;
    private static final int CHOOSE_PHOTO =  2;

    private String tableName;
    private ImageView rawImage;
    private Uri imageUri;
    private Bitmap bitmap;
    private String type;
    private int projectId;
    EditText red;
    EditText green;
    EditText blue;
    EditText alpha;
    EditText result;
    LinearLayout selectedColor;
    Spinner targets;

    CustomizedSurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = getIntent().getStringExtra("type");
        projectId = getIntent().getIntExtra("projectId",-1);
        getSupportActionBar().setTitle(getIntent().getStringExtra("projectName"));
        init();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(bitmap!=null){
                int x = (int)(event.getX()*((float)bitmap.getWidth()/rawImage.getWidth()));
                int y = (int)(event.getY()*((float)bitmap.getHeight()/rawImage.getHeight()));
                int argb = bitmap.getPixel(x, y);
                setRGB(argb);
            }
        }
        return true;
    }
// TODO,有误差，需要选择一个区域的平均值

    private void setRGB(int argb){
        alpha.setText(Color.alpha(argb)+"");
        red.setText(Color.red(argb)+"");
        green.setText(Color.green(argb)+"");
        blue.setText(Color.blue(argb)+"");
        selectedColor.setBackgroundColor(argb);
    }
    private void setRGBNull(){
        alpha.setText("");
        red.setText("");
        green.setText("");
        blue.setText("");
        result.setText("");
        selectedColor.setBackgroundColor(Color.WHITE);
    }
    public void openAlbum(){
        if(rawImage.getVisibility() != View.VISIBLE){
            rawImage.setVisibility(View.VISIBLE);
        }
        if(surfaceView.getVisibility() != View.GONE){
            surfaceView.setVisibility(View.GONE);
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    private void init() {
        rawImage = findViewById(R.id.collection_imageView);
        targets = findViewById(R.id.check_result_range);
        red  = findViewById(R.id.red);
        green  = findViewById(R.id.green);
        blue  = findViewById(R.id.blue);
        result  = findViewById(R.id.check_result);
        alpha = findViewById(R.id.alpha);
        selectedColor = findViewById(R.id.collection_color);
        rawImage.setOnTouchListener(this);
        surfaceView = findViewById(R.id.collection_surface);
        if(type.equals("定性")){
            targets.setVisibility(View.VISIBLE);
            ArrayAdapter arrayAdapter = new ArrayAdapter(SampleCollectActivity.this,android.R.layout.simple_list_item_1,
                    Service.getTargetsOfProject(projectId));
            targets.setAdapter(arrayAdapter);
        }else{
            result.setVisibility(View.VISIBLE);
        }
    }

    public void capture(View view){
        if(rawImage.getVisibility() != View.VISIBLE){
            rawImage.setVisibility(View.VISIBLE);
        }
        if(surfaceView.getVisibility() != View.GONE){
            surfaceView.setVisibility(View.GONE);
        }
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(SampleCollectActivity.this,"cn.edu.scau.cmi.fileprovider",outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent  = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }


    public void openMyCamera(View view){
        selectedColor.setBackgroundColor(0xffff);
        if(rawImage.getVisibility() != View.GONE){
            rawImage.setVisibility(View.GONE);
        }
        if(surfaceView.getVisibility() != View.VISIBLE){
            surfaceView.setVisibility(View.VISIBLE);
            surfaceView.setGRBTouch(new CustomizedSurfaceView.RGBTouch() {
                @Override
                public void displayRGB(int color) {
                    setRGB(color);
                }
                @Override
                public void showPicture(byte[] data) {

                }
            });
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
            {
                try{
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    Matrix matrix = new Matrix();
                    matrix.setRotate(90);
                    bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                    rawImage.setImageBitmap(bitmap);
                    setRGBNull();
                }catch (Exception e){
                    Toast.makeText(this, "未拍摄图片", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case CHOOSE_PHOTO:
            {
                if(resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT >= 19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if(uri.getAuthority().equals("com.android.providers.downloads.documents")){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }
    private void displayImage(String imagePath){
        if(imagePath != null){
            bitmap = BitmapFactory.decodeFile(imagePath);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            rawImage.setImageBitmap(bitmap);
            setRGBNull();
        }else{
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public void openImage(View view){
        if(rawImage.getVisibility() != View.VISIBLE){
            rawImage.setVisibility(View.VISIBLE);
        }
        if(surfaceView.getVisibility() != View.GONE){
            surfaceView.setVisibility(View.GONE);
        }

        setRGBNull();
        if(ContextCompat.checkSelfPermission(SampleCollectActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SampleCollectActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            openAlbum();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this, "打开相册功能异常", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    public void pickColor(View view){
        if(rawImage.getVisibility() != View.GONE){
            rawImage.setVisibility(View.GONE);
        }
        if(surfaceView.getVisibility() != View.GONE){
            surfaceView.setVisibility(View.GONE);
        }
        new ColorPickerDialog(SampleCollectActivity.this, 0, false, new OnColorPickerListener() {
            @Override
            public void onColorCancel(ColorPickerDialog dialog) {

            }
            @Override
            public void onColorChange(ColorPickerDialog dialog, int color) {
                setRGB(color);
            }
            @Override
            public void onColorConfirm(ColorPickerDialog dialog, int color) {
                setRGB(color);
            }
        }).show();
    }

    public void submit(View view) {
        Sample sample = new Sample();
        sample.project_id = projectId;
        sample.red = Integer.valueOf(red.getText().toString());
        sample.green = Integer.valueOf(green.getText().toString());
        sample.blue = Integer.valueOf(blue.getText().toString());
        if(type.equals("定性")){
            sample.setTarget(targets.getSelectedItem().toString());
            Service.addRangeSample(sample);
        }else{
            if(result.getText().length() == 0){
                Toast.makeText(this, "请输入result", Toast.LENGTH_SHORT).show();
                return ;
            }
            sample.result = Float.valueOf(result.getText().toString());
            Service.addLinearSample(sample);
        }
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        setRGBNull();
    }
}
