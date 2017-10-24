package com.xidian.qsf.touchauthencation.secure;

import android.view.MotionEvent;

import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.ArrayList;



/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class TouchEventLive extends TouchEvent{

    private ArrayList<TouchPoint> touchPoints = new ArrayList<TouchPoint>();

    public TouchEventLive(){
        fv = new FeatureVector(NUM_FEATURES);
    }

    @Override
    public int process(Object ev) {
        MotionEvent event = (MotionEvent) ev;
        int action = event.getAction();
        int num = 0;
        TouchPoint tp = new TouchPoint();
        switch(action) {
            case MotionEvent.ACTION_DOWN:  /* primary pointer */

                tp.xVal = event.getX();
                System.out.println("tttt "+tp.xVal);
                tp.yVal = event.getY();
                tp.pressure = event.getPressure();
                tp.width = event.getSize();
                tp.orientation = event.getOrientation();
                tp.eventTimestamp = event.getEventTime();
                this.touchPoints.add(tp);
            case MotionEvent.ACTION_POINTER_DOWN: /* any subsequent pointer */
				/*No need for a swipe ID*/
                break;
            case MotionEvent.ACTION_MOVE: /* any number of pointers move */
                for (int hIndx = 0; hIndx < event.getHistorySize(); hIndx++) {

                    for (int pIndex = 0; pIndex < event.getPointerCount();
                         pIndex++) {

                        tp.xVal = event.getHistoricalX(pIndex, hIndx);
                        tp.yVal = event.getHistoricalY(pIndex, hIndx);
                        tp.pressure = event.getHistoricalPressure(pIndex, hIndx);
                        tp.width = event.getHistoricalSize(pIndex, hIndx);
                        tp.orientation = event.getHistoricalOrientation(pIndex, hIndx);
                        tp.eventTimestamp = event.getHistoricalEventTime(hIndx);
                        this.touchPoints.add(tp);
                    }
                }

//                for (int pIndex = 0; pIndex < event.getPointerCount();
//                     pIndex++) {
//                    TouchPoint tp = new TouchPoint();
//                    tp.xVal = event.getX(pIndex);
//                    System.out.println("pos  "+tp.xVal);
//                    tp.yVal = event.getY(pIndex);
//                    tp.pressure = event.getPressure(pIndex);
//                    tp.width = event.getSize(pIndex);
//                    tp.eventTimestamp = event.getEventTime();
//                    tp.orientation = event.getOrientation(pIndex);
//                    this.touchPoints.add(tp);
//                }
                break;
            case MotionEvent.ACTION_POINTER_UP: /* all pointers are up */
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
				/* XXX return if length of swipe is less than 10 touchpoints*/
                System.out.println("touch: "+this.touchPoints.size());
                System.out.println("");
                num = this.touchPoints.size();
                if (num > 0 && num <= 10) {
                    fv = computeClickFeatureVector(touchPoints);
                }
                else if (num > 10){
                    fv = computeFeatureVector(touchPoints);
                }
                this.touchPoints.clear();
        }

        return num;
    }
}
