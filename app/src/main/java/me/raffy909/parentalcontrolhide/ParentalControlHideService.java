package me.raffy909.parentalcontrolhide;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;


public class ParentalControlHideService extends AccessibilityService {
    static final String TAG = "ParentalHideService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String msg = getString(R.string.app_restricted_message);

        if(event.getEventType()==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            String name = event.getClassName().toString();
            String text = event.getText().toString();

            //Log.i(TAG, name + ':' + text);
            if(name.contains("android.app.AlertDialog"))
                if(text.contains(msg))
                    performGlobalAction(GLOBAL_ACTION_BACK);
        }

    }

    @Override
    public void onInterrupt() {

    }
}
