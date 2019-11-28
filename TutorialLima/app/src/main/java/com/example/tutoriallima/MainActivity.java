package com.example.tutoriallima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button btnExplicitBroadcast;
    ConnectionReceiver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnExplicitBroadcast = findViewById(R.id.btnExplicitBroadcast);
    btnExplicitBroadcast.setOnClickListener(this);
    cr = new ConnectionReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(cr, filter);
    }

    public void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction("IntentA");
        intent.setComponent(new ComponentName(getPackageName(),"com.example.applicationbroadcastreceiver.ConnectionReceiver"));
        getApplicationContext().sendBroadcast(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cr);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExplicitBroadcast:
                broadcastIntent();break;
        }
    }
}
