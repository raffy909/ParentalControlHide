package me.raffy909.parentalcontrolhide;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class ParentalControlHideService extends AccessibilityService {
    static final String TAG = "ParentalHideService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String msg = getString(R.string.app_restricted_message);

        Log.i(TAG, msg);

        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            String name = event.getClassName().toString();
            String text = event.getText().toString();

            if (name.contains("android.app.AlertDialog"))
                if (text.contains(msg))
                    performGlobalAction(GLOBAL_ACTION_BACK);
        }
    }

    @Override
    public void onInterrupt() {

    }
}
