package com.example.acceleratorimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Global Variables
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate;
    // private ImageView mDrawable;
    private FragmentContainerView fragMentView;
    public static int x = 0;
    public static int y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting fragmentContainer to a variable.
        fragMentView = (FragmentContainerView) findViewById(R.id.fragmentContainer);

        // mDrawable = (ImageView) findViewById(R.id.imageView);

        // Initiating SensorManager and getting the accelerometer from services.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // Used to define the x & y position based on data from the sensor.
            x -= (int) sensorEvent.values[0];
            y += (int) sensorEvent.values[1];

            fragMentView.setY(y);
            fragMentView.setX(x);

        //  mDrawable.setY(y);
        //  mDrawable.setX(x);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}