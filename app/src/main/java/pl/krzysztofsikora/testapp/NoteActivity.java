package pl.krzysztofsikora.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    EditText et;
    String text = "";
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aktywnosc", "onCreate");
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et = (EditText) findViewById(R.id.editText);
//        if(!et.equals("")) {
//            et.setText(text);
//        }
et.setText(bundle.getString("et"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        Log.d("aktywnosc", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("aktywnosc", "onResume");

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("aktywnosc", "onPause");
//       text = et.getText().toString();
        bundle.putString("et", et.getText().toString());
        Log.d("test", text);

        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("aktywnosc", "onRestart");

        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("aktywnosc", "onStop");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("aktywnosc", "onDestroy");

        super.onDestroy();
    }
}
