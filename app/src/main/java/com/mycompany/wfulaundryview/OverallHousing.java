package com.mycompany.wfulaundryview;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mycompany.wfulaundryview.Remote.LongmanAPIHelper;

//Hello

public class OverallHousing extends ActionBarActivity {

    LongmanAPIHelper helper = new LongmanAPIHelper();
    private ProgressDialog progressDialog;
    private TextView tvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_housing);

        tvWord = ((TextView) findViewById(R.id.textView1));
        showProgressDialog();
        new RetrieveDictionaryEntryTask().execute((Void)null);
    }

    private void completeEntryLoad(String entry) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (entry != null) {
            tvWord.setText(entry);
        } else {
            tvWord.setText("Formatting error in returned response. Please try again.");
        }
    }

    private void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", "Getting Definition...", true);
    }

    /**
     * AsyncTask for retrieval of Definition.
     */
    class RetrieveDictionaryEntryTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... arg0) {
            try {
                return helper.getXML();
            } catch (Exception e) {
                Log.w(Settings.LOG_TAG, e.getClass().getSimpleName() + ", " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String entry) {
            completeEntryLoad(entry);
        }

    }





































    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overall_housing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
