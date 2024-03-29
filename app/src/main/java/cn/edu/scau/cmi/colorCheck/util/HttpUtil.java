package cn.edu.scau.cmi.colorCheck.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil<T> {
//    R2700X的IP地址
//    private static final String serverContext = "http://192.168.31.83:8080/colorCheckServer/";

//     E31230V3地址
//    private static final String serverContext = "http://192.168.31.16:8080/colorCheckServer/";
//    阿里云服务器地址
    private static final String serverContext = "http://139.159.188.31:8888/colorCheckServer/";
//    4790K地址
//    private static final String serverContext = "http://172.18.94.140:8080/colorCheckServer/";
    //    Dell server
//    private static final String serverContext = "http://192.168.253.11:8080/colorCheckServer/";
//    private static final String serverContext = "http://192.168.253.1:8080/colorCheckServer/";

    private static final String uploadFileRequestString = "uploadFile";
    private static final String getAllCommitedColorCheckBitmaps = "getAllCommitedColorCheckBitmaps";
    private static final String TAG = "-----HttpUtil测试消息------";
//不要在代码里改配置，到res/alues/string.xml修改
//    CmiApplication.getContext().getString(R.string.prefix_url);
//    prefix_url是数据所在的网址的前缀，postURL是具体的数据后缀。

    private static String getCompleteURLString(String postfixURL){
//        String completeUrlString=CmiApplication.getContext().getString(R.string.prefix_url) + postfixURL;
        String completeUrlString= serverContext + postfixURL;
        return  completeUrlString;
    }
//**** 从服务器得到数据，可以删除掉了******
    @Deprecated
    public static String gainJsonResultFromServer(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();//同步获取数据，但是是异步类调用
        return response.body().string();
    }
    //**** 新的方法，从服务器得到数据******
    public static String gainJsonResultFromServer(String request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String result = client.newCall(getGetRequest(request)).execute().body().string();//同步获取数据，但是是异步类调用
        System.out.println("在HttpUtil中使用新的获取数据的数据是："+result);
        return result;
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
        System.out.println("完整的网络请求是："+getCompleteURLString(postfixURL));
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
    File[] allColorCheckBitmapFilesInPhone = FileUtil.getAllColorCheckBitmapFiles();
    String responseStringOfCommitedBitmap = gainJsonResultFromServer(getGetRequest(getAllCommitedColorCheckBitmaps));
    HashSet<String> commitedBitmpaFileNameSet = new Gson().fromJson(responseStringOfCommitedBitmap, new TypeToken<HashSet<String>>() {}.getType());
    for(File file:allColorCheckBitmapFilesInPhone){
        if(!commitedBitmpaFileNameSet.contains(file.getName())){
            uploadFileToServer(file);//提交文件到服务器。
        }
    }
}

//  上传指定的文件到服务器，有的是Check文件，有的是Sample图片文件
    public static void uploadFileToServer(File file) throws IOException {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("image", file.getName(), fileBody).build();
        Request request = new Request.Builder().url(getCompleteURLString(uploadFileRequestString)).post(requestBody).build();
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder.connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();

        //        进入上传文件队列
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAG+"------文件没有上传成功------");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "------文件上传成功------");
            }
        });
    }

//TODO  更好的办法：有待测试 获取所有的实体项集合，使用泛型，不能用静态方法，需要使用实例方法。
    public Set<T> getAllRequiredTypeData(String jsonResultRequestString) {
        Set<T> allProjectSet=new HashSet<>();
        try {
            String result = gainJsonResultFromServer(jsonResultRequestString);
            allProjectSet = new Gson().fromJson(result, new TypeToken<HashSet<T>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  allProjectSet;
    }

    public static Set<Project> getAllProjectData(String jsonResultRequest) {
        Set<Project> allProjectSet=new HashSet<Project>();
        try {
            String result = gainJsonResultFromServer(jsonResultRequest);
            allProjectSet = new Gson().fromJson(result, new TypeToken<HashSet<Project>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  allProjectSet;
    }
}