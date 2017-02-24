package pl.krzysztofsikora.testapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class NoteActivity extends AppCompatActivity {

    EditText et;
    String text = "";
    Bundle bundle = new Bundle();
    private String path = Environment.getExternalStorageDirectory().toString() + "/DigitalZombieLab";
//private String path = Environment.getExternalStorageDirectory().toString();

    private final int MEMORY_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aktywnosc", "onCreate");
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et = (EditText) findViewById(R.id.editText);
        et.setText(bundle.getString("et"));
        if(ActivityCompat.shouldShowRequestPermissionRationale(NoteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){

        }else {
           ActivityCompat.requestPermissions(NoteActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MEMORY_ACCESS:
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){

            }else {
                Toast.makeText(getApplicationContext(), "Jeśli nie zostanie wyrażona zgoda na dostęp do pamięci, nie będzie możliwości zapisania pliku!", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("save", "Button is good");

        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            createDir();
            createFile();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public void createDir() {
        File folder = new File(path);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createFile() {
        File file = new File(path + "/" + System.currentTimeMillis() + ".txt");
        FileOutputStream fOut;
        OutputStreamWriter myOutWriter;
        try {
            fOut = new FileOutputStream(file);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(et.getText());
            myOutWriter.close();
            fOut.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString() + "create file", Toast.LENGTH_LONG).show();

        }
    }
}
