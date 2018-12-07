package cn.edu.scau.cmi.colorCheck.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil<T> {
//    TODO 测试的时候暂时固定，待以后在修复,不能正常获取URI
    private static final String colorCheckServerSite = "http://139.159.188.31:8888/colorCheckServer/";
    private static final String uploadImage_url = "springUpload";
    private static final String getAllCommitedColorCheckBitmaps = "getAllCommitedColorCheckBitmaps";
    private static final String TAG = "-----HttpUtil测试消息------";
//不要在代码里改配置，到res/alues/string.xml修改
//    CmiApplication.getContext().getString(R.string.prefix_url);
//    prefix_url是数据所在的网址的前缀，postURL是具体的数据后缀。

    private static String getCompleteURLString(String postfixURL){
//        String completeUrlString=CmiApplication.getContext().getString(R.string.prefix_url) + postfixURL;
        String completeUrlString= colorCheckServerSite + postfixURL;
        return  completeUrlString;
    }
//**** 从服务器得到数据******
    public static String gainJsonResultFromServer(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();



        Response response = client.newCall(request).execute();//同步获取数据，但是是异步类调用
        return response.body().string();
    }

    public static Request getParamsPostRequest(String postURL, String params){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), params);
        return new Request.Builder().post(requestBody).url(getCompleteURLString(postURL)).build();
    }

    public static Request getJsonPostRequest(String postfixURL, String json){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return  new Request.Builder().post(requestBody).url(getCompleteURLString(postfixURL)).build();
    }

    public static Request getGetRequest(String postfixURL){
        return  new Request.Builder().url(getCompleteURLString(postfixURL)).build();
    }
//上传所有的图片
    public static void uploadAllBitmapInColorCheckDirectory(SharedPreferences sharePreferences) throws Exception{
/*["d:\\colorCheckServer\\checkBitMap\\20181126101602.png","d:\\colorCheckServer\\checkBitMap\\20181127043739.png"]
    List<File> fileList = new Gson().fromJson(responseString, new TypeToken<List<File>>() {}.getType());
     使用String[]传回来的数据是,利用GSon封装为文件名列表。
    ["20181126101602.png","20181127043739.png"]
    wrap all bitmap into a hashset, type is String.
*/
    File[] allColorCheckBitmapFilesInPhone = FileUtil.getAllColorCheckBitmaps();
    String responseStringOfCommitedBitmap = gainJsonResultFromServer(getGetRequest(getAllCommitedColorCheckBitmaps));
    HashSet<String> commitedBitmpaFileNameSet = new Gson().fromJson(responseStringOfCommitedBitmap, new TypeToken<HashSet<String>>() {}.getType());
    for(File file:allColorCheckBitmapFilesInPhone){
        if(!commitedBitmpaFileNameSet.contains(file.getName())){
            uploadFileToServer(file);//提交文件到服务器。
        }
    }
}

//  提交文件到服务器
    public static void uploadFileToServer(File file) throws IOException {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("image", file.getName(), fileBody).build();
        Request request = new Request.Builder().url(getCompleteURLString(uploadImage_url)).post(requestBody).build();
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder.connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("很遗憾地告诉你，没有上传成功");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "uploadColorCheckBitmaps() response=" + response.body().string());
            }
        });
    }

//TODO  有待测试 获取所有的项目集合，使用泛型，不能用静态方法，需要使用实例方法。
    public Set<T> getAllRequiredTypeData(String jsonResultRequest) {
        Request request = getGetRequest(jsonResultRequest);
        Set<T> allProjectSet=new HashSet<>();
        try {
            String result = gainJsonResultFromServer(request);
            allProjectSet = new Gson().fromJson(result, new TypeToken<HashSet<T>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  allProjectSet;
    }

    public static Set<Project> getAllProjectData(String jsonResultRequest) {
        Request request = getGetRequest(jsonResultRequest);
        Set<Project> allProjectSet=new HashSet<Project>();
        try {
            String result = gainJsonResultFromServer(request);
            allProjectSet = new Gson().fromJson(result, new TypeToken<HashSet<Project>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  allProjectSet;
    }



}