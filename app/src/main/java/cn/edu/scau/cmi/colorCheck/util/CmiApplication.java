package cn.edu.scau.cmi.colorCheck.util;


import android.app.Application;
import android.content.Context;
import org.litepal.LitePal;

public class CmiApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        TODO what to do this class?
        LitePal.initialize(context);
    }

    public static Context getContext(){
        return  context;
    }
}
