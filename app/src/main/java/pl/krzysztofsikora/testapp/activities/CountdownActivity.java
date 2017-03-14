package pl.krzysztofsikora.testapp.activities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.krzysztofsikora.testapp.R;

public class CountdownActivity extends AppCompatActivity {

    EditText title, body, value;
    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title = (EditText) findViewById(R.id.NotifyTitleET);
        body = (EditText) findViewById(R.id.NotifyBodyET);
        value = (EditText) findViewById(R.id.NotifyValueET);
        start = (Button) findViewById(R.id.startBtn);
    }


    public void click(View view) {
        long startTime = Long.parseLong(value.getText().toString());
        startTime = startTime * 1000;
        new CountDownTimer(startTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                start.setEnabled(false);
                value.setEnabled(false);
                value.setText("" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                createNotification();
                start.setEnabled(true);
                value.setEnabled(true);

            }
        }.start();

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void createNotification() {
        Notification notifi = new Notification.Builder(this)
                .setContentTitle(title.getText())
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText(body.getText()).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notifi);


    }


}
