package com.xidian.qsf.touchauthencation.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.secure.SecureBase;
import com.xidian.qsf.touchauthencation.secure.SecureBaseInit;
import com.xidian.qsf.touchauthencation.secure.TouchEventLive;


public class TouchAuthActivity extends AppCompatActivity {
    public FeatureVector fv = null;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        SecureBase.getTouchAuth().getDispatcher().process(ev, this);
        return super.dispatchTouchEvent(ev);
    }

    public void initSecureAuth(){
        SecureBaseInit mTouchAuthInit = new SecureBaseInit(new TouchEventLive());
        mTouchAuthInit.start();
    }


}
