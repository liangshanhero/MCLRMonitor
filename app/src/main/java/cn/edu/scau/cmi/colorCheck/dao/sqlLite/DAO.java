package cn.edu.scau.cmi.colorCheck.dao.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;

/**
 * Created by Mr_Chen on 2018/6/28.
 */

public class DAO {
    private static SQLiteDatabase sqLiteDatabase = DatabaseHelper.getSQLiteDatabase();
    private static ContentValues objectToContentValues(Object object){
        Class<?> clazz = object.getClass();
        ContentValues contentValues = new ContentValues();
        for(Field field : clazz.getFields()){
            String name = field.getName();
            if(name.equals("id")){
                continue;
            }
            try{

                if("String".equals(field.getType().getSimpleName())){
                    contentValues.put(name, field.get(object).toString());
                }else if("int".equals(field.getType().getSimpleName())){

                    contentValues.put(name, field.getInt(object));
                }else if("float".equals(field.getType().getSimpleName())){
                    contentValues.put(name, field.getFloat(object));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return contentValues;
    }

    public static void insert(Object object){
        ContentValues contentValues = objectToContentValues(object);
        String tableName = object.getClass().getSimpleName().toLowerCase();
        sqLiteDatabase.insert(tableName,null,contentValues);
    }

    public static Cursor query(String table, String[] columns, String selection, String[] selectionArgs){
        return sqLiteDatabase.query(table,columns,selection,selectionArgs,null,null,null);
    }

    public static void delete(String table, String whereClasue,String[] args){
        sqLiteDatabase.delete(table,whereClasue,args);
    }
    public static int findLastId(String table){
        Cursor cursor = sqLiteDatabase.rawQuery("select id from "+table+" desc limit 1",null);
        while(cursor.moveToNext()){
            return cursor.getInt(cursor.getColumnIndex("id"));
        }
        return -1;

    }
    public static Cursor rawQuery(String sql,String[] args){
        return sqLiteDatabase.rawQuery(sql,args);
    }
}
