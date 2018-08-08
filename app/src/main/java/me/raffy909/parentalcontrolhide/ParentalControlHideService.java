package me.raffy909.parentalcontrolhide;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class ParentalControlHideService extends AccessibilityService {
    static final String TAG = "ParentalHideService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo source = event.getSource();

        if(event.getEventType()==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (source != null) {
                int childNum = source.getChildCount();
                for (int i = 0; i < childNum; i++) {
                    AccessibilityNodeInfo c = source.getChild(i);
                    if(c.getClassName().toString().contains("TextView")) {
                        if (c.getText().toString().contains("L'azione richiesta non è consentita. " +
                                "Controlla le impostazioni di Parental Control e riprova."))
                            performGlobalAction(GLOBAL_ACTION_BACK);
                    }
                }
            }

        }
    }

    @Override
    public void onInterrupt() {

    }
}
