package droid.zaeem.notifierx.cachememory;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Droid on 1/28/2016.
 */
public class CacheModel {
   // Context context;
   private static TinyDB myDB;




    public static void activateDatabase(Context context)
    {

      myDB= new TinyDB(context);

    }


    // getter setter of single String

    public static void putString(String key,String Value)
    {
        myDB.putString(key,Value);
    }

/*    public static void putInteger(String key,int Value)
    {
       myDB.putInt(key,Value);
    }

    public static void putBooolean(String key,Boolean Value)
    {

        myDB.putBoolean(key,Value);
    }*/

    public  static String getString(String key)
    {
        return myDB.getString(key);
    }
/*    public  static int getInteger(String key)
    {
        return myDB.getInt(key);
    }*/
    public  static Boolean getBoolean(String key)
    {
        return myDB.getBoolean(key);
    }


    // getter setter of ArrayList



    public static void putStringList(String key, ArrayList<String> list)
    {
        myDB.putListString(key,list);
    }
/*
    public static void putInteger(String key,int Value)
    {
        myDB.putInt(key,Value);
    }*/

    public static void putBoolean(String key,Boolean Value)
    {

        myDB.putBoolean(key,Value);
    }

    public  static ArrayList<String> getStringList(String key)
    {
        return myDB.getListString(key);
    }
  /*  public  static int getInteger(String key)
    {
        return myDB.getInt(key);
    }
    public  static Boolean getBoolean(String key)
    {
        return myDB.getBoolean(key);
    }*/


}
