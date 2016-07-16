package com.brunosilva.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void execService(View view) {
        Log.i("MainActivity", "execService");
        Intent i = new Intent(this, MyIntentService.class);
        EditText et = (EditText) findViewById(R.id.editText);
        i.putExtra("number", et.getText().toString());
        startService(i);
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                int resultValue = intent.getIntExtra("resultValue", 0);
                Toast.makeText(MainActivity.this,
                        "Resultado do serviço: " + String.valueOf(resultValue),
                        Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(MyIntentService.ACTION);
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }
}
