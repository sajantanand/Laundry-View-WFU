package com.mycompany.wfulaundryview;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mycompany.wfulaundryview.Remote.LaundryViewHelper;
import com.mycompany.wfulaundryview.Remote.XMLParser;

import java.util.List;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

//Hello Try again

public class OverallHousing extends ActionBarActivity {

    LaundryViewHelper helper = new LaundryViewHelper();
    private ProgressDialog progressDialog;
    //private TextView tvWord;
    List<XMLParser.LaundryRoom> rooms;
    Intent startingIntent;

    TableLayout rl1,rl2;
    ScrollView sv;


    public final static String EXTRA_MESSAGE = "com.mycompany.wfulaundryview.OverallHousing.Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_housing);
        startingIntent = getIntent();

        rl1=(TableLayout) findViewById(R.id.rl);
        sv=new ScrollView(OverallHousing.this);
        rl2=new TableLayout(OverallHousing.this);

        //tvWord = ((TextView) findViewById(R.id.textView1));
        showProgressDialog();
        new RetrieveBuildingInfo().execute((Void)null);
    }

    private void completeEntryLoad(Boolean failure) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (failure != true) {
            //tvWord.setText(entry);
            setTableLayout();
        } else {
            //tvWord.setText("Formatting error in returned response. Please try again.");
        }
    }

    private void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", "Getting Dorm Information. . .", true);
    }

    /**
     * AsyncTask for retrieval of Definition.
     */
    class RetrieveBuildingInfo extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... arg0) {
            try {
                rooms = helper.getXML();
                Boolean entry;
                try {
                    entry = (rooms.size() == 0);
                } catch (NullPointerException n)
                {
                    entry = true;
                }
                return entry;
            } catch (Exception e) {
                Log.w(Settings.LOG_TAG, e.getClass().getSimpleName() + ", " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Boolean entry) {
            completeEntryLoad(entry);
        }

    }

    private void setTableLayout() {

        rl1.setStretchAllColumns(true);

        for(int i=0;i<rooms.size();i++)
        {
           /* b[i]=new Button(this);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(
                    (int)LayoutParams.WRAP_CONTENT,(int)LayoutParams.WRAP_CONTENT);
            params.leftMargin=50;
            params.topMargin=sum;
            b[i].setText("Button "+i);
            b[i].setLayoutParams(params);
            rl2.addView(b[i]);
            sum=sum+100;*/

            TableRow row1 = new TableRow(this);
            Button text1 = new Button(this);
            text1.setWidth(sv.getWidth());
            final int j = i;
            text1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] entry = {rooms.get(j).laundry_room_name, rooms.get(j).location};
                    sendMessage(entry);
                }
            });
            text1.setText(rooms.get(i).laundry_room_name);
            row1.addView(text1);
            //row1.setBackground(Drawable.createFromPath(R.drawable.border));
            //row1.
            rl2.addView(row1);
        }

        rl2.setStretchAllColumns(true);
        sv.addView(rl2);
        rl1.addView(sv);
    }

    private void sendMessage(String[] entry)
    {
        Intent intent = new Intent(this,IndividualHousing.class);
        intent.putExtra(EXTRA_MESSAGE, entry);
        startActivity(intent);
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