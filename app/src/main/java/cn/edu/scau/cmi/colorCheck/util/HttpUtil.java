package cn.edu.scau.cmi.colorCheck.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

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

    public static String getJsonDataFromWeb(Request request) throws IOException {
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

public static void uploadColorCheckBitmaps(SharedPreferences sharePreferences) throws Exception{
/*["d:\\colorCheckServer\\checkBitMap\\20181126101602.png","d:\\colorCheckServer\\checkBitMap\\20181127043739.png"]
    List<File> fileList = new Gson().fromJson(responseString, new TypeToken<List<File>>() {}.getType());
     使用String[]传回来的数据是,利用GSon封装为文件名列表。
    ["20181126101602.png","20181127043739.png"]
    wrap all bitmap into a hashset, type is String.
*/
    File[] allColorCheckBitmapFilesInPhone = FileUtil.getAllColorCheckBitmaps();
    String responseStringOfCommitedBitmap = getJsonDataFromWeb(getGetRequest(getAllCommitedColorCheckBitmaps));
    HashSet<String> commitedBitmpaFileNameSet = new Gson().fromJson(responseStringOfCommitedBitmap, new TypeToken<HashSet<String>>() {}.getType());
    for(File file:allColorCheckBitmapFilesInPhone){
        if(!commitedBitmpaFileNameSet.contains(file.getName())){
            commitFileToServer(file);
        }
    }
}

//  提交文件到服务器
    private static void commitFileToServer(File file) throws IOException {
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
                System.out.println("恭喜你，上传成功");
                System.out.println("uploadColorCheckBitmaps() response=" + response.body().string());
                Log.i(TAG, "uploadColorCheckBitmaps() response=" + response.body().string());
            }
        });
    }

    //  TODO 第二种方法：上传图片文件，待测试20181124，服务端已完成，android有待测试！！！
    //    address是网络地址？
    public static void postFile(okhttp3.Callback callback, Map<String,String> map)
    {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (map!=null)
        {
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                builder.addFormDataPart(entry.getKey(),entry.getValue());
            }
        }
        //测试的时候暂时用固定的文件，看这个文件在那个目录

        String uploadImagFile="hs_user.txt";
        File file = new File(uploadImagFile);
        Log.e("色彩检测：","---------------要上传文件的绝对路径是："+file.getAbsolutePath());
        if(file.exists()){
            Log.d(TAG, "postFile: 文件存在");
            String TYPE = "application/octet-stream";
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE),file);

            RequestBody requestBody = builder.setType(MultipartBody.FORM).addFormDataPart("detail_image",file.getName(),fileBody).build();

            Request request = new Request.Builder().url(uploadImage_url).post(requestBody).addHeader("Authorization","Bearer ").build();
            client.newCall(request).enqueue(callback);
        }else {
            Log.d(TAG, "postFile: 文件不存在");
            RequestBody requestBody = builder.setType(MultipartBody.FORM).build();
            Request request = new Request.Builder().url(uploadImage_url).post(requestBody).addHeader("Authorization","Bearer ").build();
            client.newCall(request).enqueue(callback);
        }
    }
//TODO 待测试是否成功
    public static void postFile2Server(){
        OkHttpClient client=new OkHttpClient();

//一种：参数请求体
//        FormBody paramsBody=new FormBody.Builder()
//                .add("id",currentPlan.getPlanId()+"")
//                .add("name",currentPlan.getName())
//                .add("volume",currentPlan.getVolume())
//                .add("type",currentPlan.getType()+"")
//                .add("mode",currentPlan.getMode()+"")
//                .build();


//二种：文件请求体
//        MediaType type=MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"
//        File file=new File("/data/data/com.example.company/files/plan/plans.xml");
//        RequestBody fileBody=RequestBody.create(type,file);


//三种：混合参数和文件请求
        RequestBody paramsBody=null;
        RequestBody fileBody=null;
        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.ALTERNATIVE)
                //一样的效果
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"params\"")
                        ,paramsBody)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"file\"; filename=\"plans.xml\"")
                        , fileBody)
                //一样的效果
                /*.addFormDataPart("id",currentPlan.getPlanId()+"")
                .addFormDataPart("name",currentPlan.getName())
                .addFormDataPart("volume",currentPlan.getVolume())
                .addFormDataPart("type",currentPlan.getType()+"")
                .addFormDataPart("mode",currentPlan.getMode()+"")
                .addFormDataPart("params","plans.xml",fileBody)*/
                .build();

        Request request=new Request.Builder().url("http://192.168.1.121:8080/Server/Service")
                .addHeader("User-Agent","android")
                .header("Content-Type","text/html; charset=utf-8;")
                .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i("xxx","1、连接的消息"+response.message());
                if(response.isSuccessful()){
                    Log.i("xxx","2、连接成功获取的内容"+response.body().string());
                }
            }
        });
    }
}