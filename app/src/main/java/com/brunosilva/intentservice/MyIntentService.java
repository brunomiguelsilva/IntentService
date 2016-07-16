package com.brunosilva.intentservice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Silva on 16/07/2016.
 */
public class MyIntentService extends IntentService{
    public static final String ACTION = "com.brunosilva.MyIntentService";

    public MyIntentService(){
        super(ACTION);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int number = Integer.valueOf(intent.getStringExtra("number"));
        int fatorial = number;
        for(int i =(number - 1); i > 1; i--){
            fatorial = fatorial * i;
        }
        Intent in = new Intent(ACTION);
        in.putExtra("resultCode", Activity.RESULT_OK);
        in.putExtra("resultValue", fatorial);
        sendBroadcast(in);
    }
}
