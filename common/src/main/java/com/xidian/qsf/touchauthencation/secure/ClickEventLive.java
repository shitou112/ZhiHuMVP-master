package com.xidian.qsf.touchauthencation.secure;

import android.view.MotionEvent;

import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.ArrayList;

/**
 * Created by Qian Shaofeng on 2017/9/7.
 */

public class ClickEventLive extends ClickEvent{
    private ArrayList<ClickPoint> clickPoints = new ArrayList<ClickPoint>();

    public ClickEventLive(){
        fv = new FeatureVector(NUM_FEATURES);
    }

    @Override
    public int process(Object ev) {
        MotionEvent event = (MotionEvent) ev;
        int action = event.getAction();
        int num = 0;
        switch(action) {
            case MotionEvent.ACTION_DOWN:  /* primary pointer */
            case MotionEvent.ACTION_POINTER_DOWN: /* any subsequent pointer */
				/*No need for a swipe ID*/
                break;
            case MotionEvent.ACTION_MOVE: /* any number of pointers move */

                for (int hIndx = 0; hIndx < event.getHistorySize(); hIndx++) {
                    for (int pIndex = 0; pIndex < 1;
                         pIndex++) {
                        ClickPoint cp = new ClickPoint();
                        cp.xVal = event.getHistoricalX(pIndex, hIndx);
                        System.out.println("ppppp  "+cp.xVal);
                        cp.yVal = event.getHistoricalY(pIndex, hIndx);
                        cp.width = event.getHistoricalSize(pIndex, hIndx);
                        cp.eventTimestamp = event.getHistoricalEventTime(hIndx);
                        this.clickPoints.add(cp);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP: /* all pointers are up */
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
				/* XXX return if length of swipe is less than 6 touchpoints*/
                if ( this.clickPoints.size() >0&& this.clickPoints.size() <= 5) {
                    fv = computeFeatureVector(clickPoints);
                    num = clickPoints.size();
                    clickPoints.clear();
                }
                else {
                    clickPoints.clear();
                }
        }
        System.out.println("click----"+num);
        return num;
    }
}
