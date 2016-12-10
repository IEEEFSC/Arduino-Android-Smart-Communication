package com.developers.sparshasahasridharswain.android_arduino_app;

import android.content.Context;

import java.util.Date;

/**
 * Created by Sparsha Saha on 12/2/2016.
 */

public class CallReceiver extends Check_Call_Incoming {
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
    }


}
