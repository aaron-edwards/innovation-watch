package com.ioof.innovation.ioofsuperfit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.ioof.innovation.ioofsuperfit.fundlist.FundListFragment;

public class MainWearActivity extends Activity{
    private static MySuperFragment mySuperFragment = new MySuperFragment();
    private static MyContributionsFragment myContributionsFragment = new MyContributionsFragment();
    private static FundListFragment fundListFragment = new FundListFragment();

    public static final Fragment[] FRAGMENTS = new Fragment[]{
            mySuperFragment,
            myContributionsFragment//,
//            fundListFragment
    };

    public static final int SWIPE_THRESHOLD = 150;

    public int currentIndex;
    private DismissOverlayView dismissOverlayView;
    private GestureDetector detector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);

        dismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        dismissOverlayView.setIntroText(R.string.closing_string);
        dismissOverlayView.showIntroIfNecessary();

        detector = createGuestureDetector();

        currentIndex = 0;
        slideViewLeft(currentIndex);

    }

    private GestureDetector createGuestureDetector() {
        return new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent event){
                dismissOverlayView.show();
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float delta_x = e1.getX() - e2.getX();
                if(delta_x > SWIPE_THRESHOLD){
                    if(currentIndex+1 < FRAGMENTS.length){
                        currentIndex++;
                        slideViewLeft(currentIndex);
                    }
                    return true;
                }else if(delta_x < SWIPE_THRESHOLD * -1){
                    if(currentIndex <= 0){
                        finish();
                    }else{
                        currentIndex--;
                        slideViewRight(currentIndex);
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void slideViewLeft(int index){

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.main_frame, FRAGMENTS[index]);
        ft.commit();
    }

    private void slideViewRight(int index){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        ft.replace(R.id.main_frame, FRAGMENTS[index]);
        ft.commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event) ||
                super.onTouchEvent(event);
    }

}
