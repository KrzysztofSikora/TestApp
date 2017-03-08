package pl.krzysztofsikora.testapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * Created by krzysztof on 08.03.17.
 */

public class BackgroundOperation extends AsyncTask<Void, Void, String> {

    Activity activity;
    ProgressBar progressBar;
    EditText whichPrime;

    public BackgroundOperation(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        whichPrime = (EditText) activity.findViewById(R.id.primeNumET);
        progressBar = (ProgressBar) activity.findViewById(R.id.primeNumPB);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        return primeNumCalculated();

    }




    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private String primeNumCalculated() {
        int which = Integer.parseInt(whichPrime.getText().toString());
        int m = 0;
        int candidate = 1;
        while (m <= which) {
            if (isPrime(candidate)) {
                m++;
                candidate++;
            } else
                candidate++;
        }
        candidate--;
        String result = which + " -> " + candidate;
        Snackbar.make(activity.getCurrentFocus(), result, Snackbar.LENGTH_INDEFINITE).show();
        return result;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;

    }
}
