package com.xidian.qsf.touchauthencation.secure;


/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class SecureBaseInit extends SecureBase {

    public SecureBaseInit(TouchEvent touchEvent) {
        super();
        setDispatcher(new Dispatcher(touchEvent));
//        useClassifier(new BpDeepClassifier(new int[]{25,30,2}, 0.15, 0.8));
//        useClassifier(new SVMClassifier(TouchEvent.NUM_FEATURES));
//        useClassifier(new KNNClassifier(7, TouchEvent.NUM_FEATURES));
    }
}
