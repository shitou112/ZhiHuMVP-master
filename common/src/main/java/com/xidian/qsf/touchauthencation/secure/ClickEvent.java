package com.xidian.qsf.touchauthencation.secure;

import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.ArrayList;

/**
 * Created by Qian Shaofeng on 2017/9/7.
 */

public abstract class ClickEvent extends Generator{
    /**
     * 触摸点类，方便计算特征向量
     */
    public class ClickPoint{
        public long eventTimestamp;
        public double width;
        public double xVal,yVal;
    }

    public static final String featureList[] = {
            "x", //0
            "y", //1
            "timestamp" //2
    };

    public static final int NUM_FEATURES = featureList.length;

    protected FeatureVector computeFeatureVector(
            ArrayList<ClickPoint> clickPoints) {
        FeatureVector fv = new FeatureVector(NUM_FEATURES);



        int numPoints = clickPoints.size();
        double mean_x = (clickPoints.get(0).xVal + clickPoints.get(numPoints-1).xVal) / 2;
        double mean_y = (clickPoints.get(0).yVal + clickPoints.get(numPoints-1).yVal) / 2;
        double timestamp = clickPoints.get(numPoints-1).eventTimestamp - clickPoints.get(0).eventTimestamp;
        fv.set(0, mean_x);
        fv.set(1, mean_y);
        fv.set(2, timestamp);

        return fv;
    }
}
