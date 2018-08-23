package easypaste.example.com.easypaste;



import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button exitButton;
    Button currentTimeButton;
    TextView currentTime;

    Button btnTakePicture, btnScanBarcode;

    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, BackgroundService.class);
        startService(intent);

        initViews();

        context = getApplicationContext();

    }

    public static Context getContext(){
        return context;
    }

    private void initViews() {
        currentTime = findViewById(R.id.currentTime);
        exitButton = findViewById(R.id.exitButton);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        currentTimeButton = findViewById(R.id.currentTimeButton);

        currentTimeButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        btnTakePicture.setOnClickListener(this);
        btnScanBarcode.setOnClickListener(this);

        // Hide Temporarily

        btnScanBarcode.setVisibility(View.INVISIBLE);
    }

    public void setCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        currentTime.setText(strDate);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnTakePicture:
                startActivity(new Intent(MainActivity.this, PictureBarcodeActivity.class));
                break;
            case R.id.btnScanBarcode:
                startActivity(new Intent(MainActivity.this, ScannedBarcodeActivity.class));
                break;

            case R.id.exitButton:
                Log.v("Button", "service stopped");
                stopService( new Intent(this, BackgroundService.class));
//                disableReciever(getApplicationContext());
                break;

            case R.id.currentTimeButton:
//                setCurrentTime();
//                volleyGetRequest();
//                volleyPostRequest("Message", "http://192.168.0.101/android");
                break;

        }

    }
}
