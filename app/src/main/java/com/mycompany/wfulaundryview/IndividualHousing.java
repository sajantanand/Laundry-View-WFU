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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mycompany.wfulaundryview.Remote.LaundryViewHelper;
import com.mycompany.wfulaundryview.Remote.MachineList;
import com.mycompany.wfulaundryview.Remote.XMLParser;

import java.util.ArrayList;
import java.util.List;


public class IndividualHousing extends ActionBarActivity {

    private TextView text1;
    LaundryViewHelper helper = new LaundryViewHelper();

    List<MachineList> building = new ArrayList<>();
    private ProgressDialog progressDialog;

    String returnValue;

    String[] buildingCode;

    TableLayout rl1,rl2;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        //fileToPlay = intent.getStringExtra(OverallHousing.EXTRA_MESSAGE);

        buildingCode = intent.getStringArrayExtra(OverallHousing.EXTRA_MESSAGE);

        setContentView(R.layout.activity_individual_housing);

        setTitle(buildingCode[0]);

        //text1 = (TextView) findViewById(R.id.textView2);
        //text1.setText(fileToPlay[1]);

        rl1=(TableLayout) findViewById(R.id.rl);
        sv=new ScrollView(IndividualHousing.this);
        rl2=new TableLayout(IndividualHousing.this);

        showProgressDialog();
        new RetrieveBuildingInfo().execute((Void)null);



    }



  private void completeEntryLoad(Boolean success) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (success == true) {
            //text1.setText(building.siz);
            setTableLayout();
            //text1.setText(returnValue);
        } else {
            //text1.setText("Formatting error in returned response. Please try again.");
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
                building = helper.getMachines(buildingCode[1]);

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

    private void setTableLayout() {

        /*TableLayout MainLayout = new TableLayout(this);
        MainLayout.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
        MainLayout.setStretchAllColumns(true);
        MainLayout.setPadding(16, 16, 16, 16);


        for(int i=0;i<roomList.size();i++)
        {

            TableRow row1 = new TableRow(this);
            row1.setPadding(0, 5, 0, 5);
            TextView text2 = new TextView(this);
            TextView text3 = new TextView(this);
            CheckBox box1 = new CheckBox(this);

            text2.setText(building.get(i).type + " " + building.get(i).label);
            text3.setText(building.get(i).timeRemaining);
            text3.setGravity(Gravity.CENTER_HORIZONTAL);
            box1.setGravity(Gravity.RIGHT);
            row1.addView(text2);
            row1.addView(text3);
            row1.addView(box1);
            MainLayout.addView(row1);
        }

        *//*sv.addView(rl2);
        rl1.addView(sv);*//*
        setContentView(MainLayout);*/



        rl1.setStretchAllColumns(true);
        rl2.setStretchAllColumns(true);

        TableRow row1 = new TableRow(this);
        TextView text2 = new TextView(this);
        TextView text3 = new TextView(this);
        TextView text4 = new TextView(this);

        text2.setText("Machine Type");
        text2.setGravity(Gravity.LEFT);

        text3.setText("Status");
        text3.setGravity(Gravity.RIGHT);

        text4.setText("Reminder");
        text4.setGravity(Gravity.CENTER_HORIZONTAL);

        row1.addView(text2);
        row1.addView(text3);
        row1.addView(text4);

        rl2.addView(row1);

        for(int i=0;i<building.size();i++)
        {
            /*
           // b[i]=new Button(this);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(
                    (int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin=50;
            params.topMargin=sum;
            b[i].setText("Button "+i);
            b[i].setLayoutParams(params);
            rl2.addView(b[i]);
            sum=sum+100;*//*
            */

            row1 = new TableRow(this);
            row1.setPadding(0, 10, 0, 10);
            text2 = new TextView(this);
            text3 = new TextView(this);
            ImageView image1 = new ImageView(this);
            CheckBox box1 = new CheckBox(this);
            String available;

            text2.setText(building.get(i).type + " " + building.get(i).label);
            available = building.get(i).timeRemaining;
            if (available.contains("cycle"))
            {
            available = "available";
            }
            else if (available.startsWith("est."))
            {
                int n = available.length();
                char[] test = new char[1];
                int j = 1;
                do {
                    available.getChars(n-5-j,n-4-j,test, 0);
                    j++;
                } while (Character.isDigit(test[0]));
                available = available.substring(n-6);
            }

            text3.setText(available);

            //image1.setImageDrawable(getDrawable(R.drawable.available));
            //image1.setBackgroundResource(R.drawable.available32);
            image1.setImageResource(R.drawable.available32);
            //RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams((int)LayoutParams.WRAP_CONTENT,(int)LayoutParams.WRAP_CONTENT);
            //image1.setLayoutParams(new ViewGroup.LayoutParams((int) ViewGroup.LayoutParams.FILL_PARENT,(int)ViewGroup.LayoutParams.FILL_PARENT));
            //image1.setMaxWidth(30);
            //image1.setMaxHeight(30);
            //image1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT));
            //TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

            text3.setGravity(Gravity.CENTER_HORIZONTAL);

            if (available.equals("available"))
            {
                box1.setEnabled(false);
            }

            box1.setGravity(Gravity.END);
            //box1.setPadding(0, 0, 0, -5);
            //box1.setScrollY(-5);
            //box1.setHeight(-5);
            //box1.s

            //int height = row
            row1.addView(text2);

            //text3.setText(row1.getHeight() + "");

            //int height = row1.getHeight();
            //TableRow.LayoutParams params2 = new TableRow.LayoutParams(2, 50);
            //TableRow.LayoutParams params = new TableRow.LayoutParams(height, height);
            //image1.setLayoutParams(params2);
            //image1.setScaleType(ImageView.ScaleType.FIT_CENTER);


            //LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(20, 20);
            //image1.setLayoutParams(new ViewGroup.LayoutParams(10, 10));

            //image1.getLayoutParams().width = row1.getHeight();

            if (available.equals("available")) {
                row1.addView(image1);
/*                TableRow.LayoutParams params2 = new TableRow.LayoutParams(height, height);
                image1.setLayoutParams(params2);
                image1.getLayoutParams().height = 20;
                image1.getLayoutParams().width = 20;*/
            }
            else {
                row1.addView(text3);
            }

            row1.addView(box1);

            //TableRow row1 = new TableRow(this);
            //Button text1 = new Button(this);
            //text1.setWidth(sv.getWidth());
            //final int j = i;
            /*text1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] entry = {rooms.get(j).laundry_room_name, rooms.get(j).location};
                    sendMessage(entry);
                }
            });*/
            //text1.setText(rooms.get(i).laundry_room_name);
            //row1.addView(text1);
            row1.setBackgroundResource(R.drawable.border);
            row1.setGravity(Gravity.CENTER_VERTICAL);
            rl2.addView(row1);
        }
        rl2.setStretchAllColumns(true);
        sv.addView(rl2);

        //rl1.

        rl1.addView(sv);
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