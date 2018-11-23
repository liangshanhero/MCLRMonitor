package cn.edu.scau.cmi.colorCheck.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

//利用服务启动数据库的连接，传递检测相片


public class DatabaseService extends Service {
    public DatabaseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
