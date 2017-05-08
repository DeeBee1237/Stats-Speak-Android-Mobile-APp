package com.something.dragos.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;



// Second Activity that starts when the button is pressed:
public class DisplayMessageActivity extends AppCompatActivity {

    private String calculationReceived;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // get the intent that started this Activity (every activity is started by an intent):
        Intent intent = getIntent();

        // create a textView setting the size and message:
        String results = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        this.calculationReceived = results;

        textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText(results);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

        Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar_2);
        setSupportActionBar(toolBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_for_sharing,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.share_feature) {
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_TEXT,this.calculationReceived);

            intent.setType("text/plain");

            startActivity(Intent.createChooser(intent, "Share Results"));

        }

        return super.onOptionsItemSelected(item);
    }
}
