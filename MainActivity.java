package com.farmassist.accelerometersocket;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    Sensor acc;
    SensorManager sm;
    TextView et1, tv1;
    private Socket sk;
    private static final int SERVERPORT = 5321;
    private PrintWriter out;
    EditText etf1;
    Button bt1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.send);
        et1=(TextView)findViewById(R.id.textin);
        etf1=(EditText)findViewById(R.id.ipadr);
        tv1=(TextView) findViewById(R.id.tv1);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,acc,SensorManager.SENSOR_DELAY_GAME);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"running "+etf1.getText().toString(),Toast.LENGTH_LONG).show();
                new Thread(new ClientThread()).start();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        et1.setText(Float.toString(event.values[1]));
        if (out == null) {
            tv1.setText("NULL");
        } else {
            tv1.setText("NOT NULL!");
        }
        if (out != null){
            out.println(event.values[1]);
            //out.flush();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
   /* public void onClick(View view) {
        try {
            String str = etf1.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(
            new OutputStreamWriter(sk.getOutputStream())),
            true);
            out.println(event.values[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                sk = new Socket(etf1.getText().toString(), SERVERPORT);
                out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(sk.getOutputStream())), true);
                //out.println("Hello");
                //out.flush();
                //sk.close();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        if (sk != null) {
            try {
                sk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sm.unregisterListener(this);
        super.onDestroy();
    }
}