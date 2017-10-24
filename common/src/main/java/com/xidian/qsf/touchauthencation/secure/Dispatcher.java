package com.xidian.qsf.touchauthencation.secure;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.MotionEvent;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.dao.DatabaseHelper;
import com.xidian.qsf.touchauthencation.entity.ClickData;
import com.xidian.qsf.touchauthencation.entity.User;
import com.xidian.qsf.touchauthencation.entity.UserBehavoir;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class Dispatcher {
    private Generator mGenerator;
    private Generator clickGenerator;
    private FeatureVector fv = null;
    private static int num = 0;
    private Context activity;
    private int pointNum;


    public Dispatcher(Generator generator){
        this.mGenerator = generator;
    }
    public void process(Object ev, final Context context){
        this.activity = context;
        this.pointNum = mGenerator.process(ev);
        if (pointNum > 0) {

            if (pointNum > 10) {
                fv = mGenerator.getFeatureVector();
                DatabaseHelper dbh = DatabaseHelper.getInstance(activity);
                UserBehavoir userBehavoir = UserBehavoir.setValues(UserBehavoir.getUid(), fv.getAll());
                User user = dbh.queryUser();
                if (user != null) {
                    System.out.println(user.getUsername());
                    userBehavoir.setUid(user.getUsername());
                } else
                    userBehavoir.setUid("testname");

                SQLiteDatabase db = dbh.getWritableDatabase();
                dbh.onCreate(db);
                dbh.insert(userBehavoir);

                System.out.println("success!");
            } else if (pointNum > 0) {
                fv = mGenerator.getFeatureVector();
                DatabaseHelper dbh = DatabaseHelper.getInstance(activity);
                ClickData clickData = ClickData.setValues(ClickData.getUid(), fv.getAll());
                User user = dbh.queryUser();
                if (user != null) {
                    System.out.println(user.getUsername());
                    clickData.setUid(user.getUsername());
                } else
                    clickData.setUid("testname");

                SQLiteDatabase db = dbh.getWritableDatabase();
                dbh.onCreate(db);
                dbh.insertClickData(clickData);

                System.out.println("click data insert success!");

            }

        }



    }
}
