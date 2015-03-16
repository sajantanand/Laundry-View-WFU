package com.mycompany.wfulaundryview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mycompany.wfulaundryview.Remote.LaundryViewHelper;
import com.mycompany.wfulaundryview.Remote.XMLParser;

import java.util.ArrayList;
import java.util.List;


public class IndividualHousing extends ActionBarActivity {

    private TextView text1;
    LaundryViewHelper helper = new LaundryViewHelper();

    List<XMLParser.MachineList> building = new ArrayList<>();
    private ProgressDialog progressDialog;

    String returnValue;

    String[] fileToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        //fileToPlay = intent.getStringExtra(OverallHousing.EXTRA_MESSAGE);

        fileToPlay = intent.getStringArrayExtra(OverallHousing.EXTRA_MESSAGE);

        setContentView(R.layout.activity_individual_housing);

        setTitle(fileToPlay[0]);

        text1 = (TextView) findViewById(R.id.textView2);
        text1.setText(fileToPlay[1]);

        showProgressDialog();
        new RetrieveBuildingInfo().execute((Void)null);



    }



  private void completeEntryLoad(Boolean success) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (success == true) {
            //text1.setText(building.siz);
            setTableLayout(building);
            //text1.setText(returnValue);
        } else {
            text1.setText("Formatting error in returned response. Please try again.");
        }
    }

    private void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", "Getting Machine Information. . .", true);
    }

    /**
     * AsyncTask for retrieval of Definition.
     */
    class RetrieveBuildingInfo extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... arg0) {
            try {
                building = helper.getMachines(fileToPlay[1]);

                //returnValue = helper.getMachines(fileToPlay);
                Boolean entry = true;
                if(building.size() == 0)
                {
                    entry = false;
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

    private void setTableLayout(List<XMLParser.MachineList> roomList) {

        TableLayout MainLayout = new TableLayout(this);
        MainLayout.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
        MainLayout.setStretchAllColumns(true);
        MainLayout.setPadding(16, 16, 16, 16);

       /* //Create the first row and add two text views
        TableRow row1 = new TableRow(this);
        TextView text1 = new TextView(this);
        text1.setText("Test1");
        TextView text2 = new TextView(this);
        text2.setText("Test2");
        text2.setGravity(android.view.Gravity.RIGHT);
        row1.addView(text1);
        row1.addView(text2);
        MainLayout.addView(row1);

        //Create the second row and add two text views
        TableRow row2 = new TableRow(this);
        TextView text3 = new TextView(this);
        text3.setText("Test3");
        TextView text4 = new TextView(this);
        text4.setText("Test4");
        text4.setGravity(android.view.Gravity.RIGHT);
        row2.addView(text3);
        row2.addView(text4);
        MainLayout.addView(row2);

        //Set the view
        setContentView(MainLayout);*/

        for(int i=0;i<roomList.size();i++)
        {

            TableRow row1 = new TableRow(this);
            row1.setPadding(0, 5, 0, 5);
            TextView text2 = new TextView(this);
            TextView text3 = new TextView(this);

            text2.setText(building.get(i).type + " " + building.get(i).label);
            text3.setText(building.get(i).timeRemaining);
            text3.setGravity(Gravity.RIGHT);
            row1.addView(text2);
            row1.addView(text3);
            MainLayout.addView(row1);

           /* b[i]=new Button(this);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(
                    (int)LayoutParams.WRAP_CONTENT,(int)LayoutParams.WRAP_CONTENT);
            params.leftMargin=50;
            params.topMargin=sum;
            b[i].setText("Button "+i);
            b[i].setLayoutParams(params);
            rl2.addView(b[i]);
            sum=sum+100;*/

            /*TableRow row1 = new TableRow(this);
            Button text1 = new Button(this);
            text1.setWidth(sv.getWidth());
            final int j = i;
            text1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String entry = rooms.get(j).location;
                    sendMessage(entry);
                }
            });
            text1.setText(rooms.get(i).laundry_room_name);
            row1.addView(text1);
            rl2.addView(row1);*/
        }

        /*sv.addView(rl2);
        rl1.addView(sv);*/
        setContentView(MainLayout);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_individual_housing, menu);
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
