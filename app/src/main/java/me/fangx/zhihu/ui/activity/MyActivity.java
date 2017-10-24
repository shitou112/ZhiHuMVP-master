package me.fangx.zhihu.ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xidian.qsf.touchauthencation.dao.DatabaseHelper;
import com.xidian.qsf.touchauthencation.entity.User;

import me.fangx.common.ui.activity.BaseSwipeBackCompatActivity;
import me.fangx.common.util.eventbus.EventCenter;
import me.fangx.common.util.netstatus.NetUtils;
import me.fangx.zhihu.R;
import me.fangx.zhihu.ui.fragment.MyInfoFragment;

/**
 * Created by fangxiao on 16/1/27.
 */
public class MyActivity extends AppCompatActivity {

    private EditText username;
    private EditText passwd;
    private TextView usernameTextView;
    DatabaseHelper dbh;
    SQLiteDatabase sqLiteDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        MyInfoFragment fragment = MyInfoFragment.newInstance();
        usernameTextView = (TextView) findViewById(R.id.usernameTV);
        dbh = DatabaseHelper.getInstance(this);
        sqLiteDatabase = dbh.getReadableDatabase();
        dbh.onCreate(sqLiteDatabase);
        User user = dbh.queryUser();
        if (user != null)
            usernameTextView.setText(user.getUsername());

        username = (EditText) findViewById(R.id.username);
        passwd = (EditText) findViewById(R.id.passwd);
    }

    public void click_event(View view){
        User user = new User();
        switch (view.getId()){
            case R.id.insert_user:
                sqLiteDatabase = dbh.getWritableDatabase();
                user.setUsername(username.getText().toString());
                user.setPassword(passwd.getText().toString());
                System.out.println(user.getUsername());
                dbh.insertUserInfo(sqLiteDatabase, user);
                break;
            case R.id.update_user:
                sqLiteDatabase = dbh.getWritableDatabase();
                user.setUsername(username.getText().toString());
                user.setPassword(passwd.getText().toString());
                System.out.println(user.getUsername());
                dbh.insertUserInfo(sqLiteDatabase, user);
                break;
        }
    }
}
