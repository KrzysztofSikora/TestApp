package pl.krzysztofsikora.testapp;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FilenameFilter;

public class FishesActivity extends AppCompatActivity implements CommonColors {


    private TextView fiches;
    private String path = Environment.getExternalStorageDirectory().toString() + "/DigitalZombieLab/Druga/";
    private String interlude = "\n\n\n///---///----////\n\n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiches);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNaviBarColor();
        fiches = (TextView) findViewById(R.id.fichesTextView);
        fiches.setText(getAllContent());
    }

    @Override
    public void setNaviBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private String getAllContent() {

        try {
            File file = new File(path);
            String[] paths = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".txt");
                }
            });

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {


                String str = FileUtils.readFileToString(new File(path + paths[i]), "UTF-8");

                sb.append(str);
                sb.append(interlude);
            }
            return sb.toString();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Bug" + e.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }


}
