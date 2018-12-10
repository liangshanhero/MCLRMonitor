package cn.edu.scau.cmi.colorCheck.olderVersion.dao.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Mr_Chen on 2018/6/7.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static DatabaseHelper myDatabaseHelper;
    private static SQLiteDatabase sqLiteDatabase;


    public static SQLiteDatabase getSQLiteDatabase(){
        return sqLiteDatabase;
    }
    public static DatabaseHelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        if(myDatabaseHelper == null){
            myDatabaseHelper =  new DatabaseHelper(context, name, factory, version);
            sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        }
        return myDatabaseHelper;
    }

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

//在手机上创建本地数据库
    @Override
    public void onCreate(SQLiteDatabase db) {

        //项目检测类型，暂时有定性和定量
        db.execSQL("create table checktype(" +
                "id integer primary key autoincrement," +
                "name text not null unique)");


        db.execSQL("create table project(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "memo text ," +
                "type_id integer," +
                "FOREIGN KEY (type_id) references type(id))");


        db.execSQL("create table target(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "project_id integer,"+
                "FOREIGN KEY (project_id) references project(id))");

        db.execSQL("create table rule(" +
                "id integer primary key autoincrement," +
                "name text not null unique," +
                "num int not null,"+
                "type text not null,"+
                "create_date text not null," +
                "project_id integer,"+
                "FOREIGN KEY (project_id) references project(id))");

        db.execSQL("create table sample(" +
                "id integer primary key autoincrement," +
                "red int not null," +
                "green int not null,"+
                "blue int not null," +
                "result float not null,"+
                "project_id integer,"+
                "target_id integer,"+
                "FOREIGN KEY (project_id) references project(id),"+
                "FOREIGN KEY (target_id) references target(id))");

        db.execSQL("create table linear_rule(" +
                "id integer primary key autoincrement," +
                "k1 float not null,"+
                "k2 float not null,"+
                "k3 float not null,"+
                "b float not null,"+
                "rule_id integer," +
                "FOREIGN KEY (rule_id) references rule(id))");

        db.execSQL("create table range_rule(" +
                "id integer primary key autoincrement," +
                "r_start integer not null,"+
                "r_end integer not null,"+
                "g_start integer not null,"+
                "g_end integer not null,"+
                "b_start integer not null,"+
                "b_end integer not null,"+
                "rule_id integer ," +
                "target_id integer," +
                "FOREIGN KEY (target_id) references target(id)," +
                "FOREIGN KEY (rule_id) references rule(id))");


        Toast.makeText(mContext, "创建数据库成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
