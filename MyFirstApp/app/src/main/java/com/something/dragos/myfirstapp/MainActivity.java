package com.something.dragos.myfirstapp;

import java.util.List;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.support.v7.widget.Toolbar;
// for special stuff:
import com.something.dragos.myfirstapp.DataSetStorage.DataStorer;
import com.something.dragos.myfirstapp.ErrorCheckAndDisplay.Displayer;
import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.MeanCalculator;
import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.StandardDeviationCalculator;
import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.VarianceCalculator;
import com.something.dragos.myfirstapp.StatCalculators.MedianCalculators.LowerQuartileCalculator;
import com.something.dragos.myfirstapp.StatCalculators.MedianCalculators.UpperQuartileCalculator;
import com.something.dragos.myfirstapp.StatCalculators.ModeCalculator;
import com.something.dragos.myfirstapp.StatCalculators.MedianCalculators.MedianCalculator;
import com.something.dragos.myfirstapp.StatCalculators.RangeCalculator;

public class MainActivity extends AppCompatActivity {

    // keys for sending a string (using an intent) to the next activity:
    public final static String EXTRA_MESSAGE = "com.something.myfirstapp.MESSAGE";

    private static final int SPECH_REQUEST_CODE = 0;

    private DataStorer dataStorer = new DataStorer();

    // toggle button object that will be used to turn the working on and off:
    private ToggleButton workingSwitch;

    private Displayer displayer = new Displayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        // TODO: make a subclass of CompoundButton.OnCheckedChangeListener in order to clean this code up:

        this.workingSwitch = (ToggleButton) findViewById(R.id.working_on_off);
        this.workingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // on:
                    displayer.setDoesDisplayWorking(true);
                }
                else {
                    // off:
                    displayer.setDoesDisplayWorking(false);
                }
            }
        });

    }

    // create a new intent to launch the speech recognition:
    private void startSpeechRecognizer () {

        Intent intent = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        startActivityForResult(intent,SPECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speakersInput = results.get(0);

            EditText textField = (EditText) findViewById(R.id.edit_message);

            String getTextFieldsCurrentText = textField.getText().toString();
            // since the spoken text will be numbers separated by commas, I will get the numbers first:
            String [] numbers = speakersInput.split(",");

            String textToDisplay = "";
            for (String number: numbers)
                textToDisplay += number + " ";

            textField.setText(getTextFieldsCurrentText + textToDisplay);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_for_app,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // obtain a reference to the Edit Text object:
        EditText textField = (EditText) findViewById(R.id.edit_message);

        if (item.getItemId() == R.id.clear_feature) {
            // obtain the data currently enetered by the user:
            String dataEntered = textField.getText().toString();

            // check if clearing white space, and don't add it to the data history:
            if (dataEntered.trim().isEmpty()) {
                // clear the text field:
                textField.setText("");
                return false;
            }

           this.dataStorer.addData(dataEntered);

            // clear the text field:
            textField.setText("");
        }

        if (item.getItemId() == R.id.previous_data) {

            if (this.dataStorer.isEmpty()) {
                Toast.makeText(MainActivity.this,"There are no previous data entries", Toast.LENGTH_LONG).show();
                return false;
            }

            this.dataStorer.moveBack();


            Toast.makeText(MainActivity.this, "deleted data set number: " + (this.dataStorer.getPossitionOfCurrentDataSet() + 1),
                        Toast.LENGTH_SHORT).show();


            textField.setText(this.dataStorer.getCurrentDataSet());

        }

        if (item.getItemId() == R.id.next_data) {

            if (this.dataStorer.isEmpty()) {
                Toast.makeText(MainActivity.this,"There are no previous data entries", Toast.LENGTH_SHORT).show();
                return false;
            }

            this.dataStorer.moveForward();

            Toast.makeText(MainActivity.this, "deleted data set number: " + (this.dataStorer.getPossitionOfCurrentDataSet() + 1),
                    Toast.LENGTH_SHORT).show();

            textField.setText(this.dataStorer.getCurrentDataSet());

        }

        if (item.getItemId() == R.id.voice_numbers)
            startSpeechRecognizer();

        // if the info menu item is selected, start a new activity, showing instructions for
        // how to use the app:
        if (item.getItemId() == R.id.info) {
            Intent intent = new Intent(this,ShowInfoActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * For Radio Button selection:
     * @param view
     */
    public void radioButtonSelected (View view) {

        boolean radioButtonSelected = ((RadioButton)view).isChecked();


        switch (view.getId()) {

            case R.id.calculate_mean:
                if (radioButtonSelected)
                    this.displayer.setCalculator(new MeanCalculator());
                break;

            case R.id.calculate_variance:

                if (radioButtonSelected)
                    this.displayer.setCalculator(new VarianceCalculator());

                break;

            case R.id.calculate_standard_deviation:

                if (radioButtonSelected)
                    this.displayer.setCalculator(new StandardDeviationCalculator());

                break;

            case R.id.calculate_median:
                if (radioButtonSelected)
                    this.displayer.setCalculator(new MedianCalculator());
                break;

            case R.id.calculate_range:
                if (radioButtonSelected)
                    this.displayer.setCalculator(new RangeCalculator());
                break;

            case R.id.calculate_mode:
                if (radioButtonSelected)
                    this.displayer.setCalculator(new ModeCalculator());
                break;

            case R.id.calculate_lq:
                if (radioButtonSelected)
                    this.displayer.setCalculator(new LowerQuartileCalculator());
                break;

            case R.id.calculate_uq:
                if (radioButtonSelected)
                    this.displayer.setCalculator(new UpperQuartileCalculator());
                break;


        }
    }

    // s1, s2, s3, s4
    /**
     * For when the "Calculate" button is pressed:
     * @param view
     */
    public void calculate (View view) {
        // an Intent object is a runtime link between two components such as activities
        // Intent takes a context (this class is a subclass of Context) and the class to which the intent
        // should be delivered (a new activity that will be started):
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText editText = (EditText) findViewById(R.id.edit_message);

        // obtain the string from the editText object here. This is the sample data:
        String sampleData = editText.getText().toString();

        // the result of the calculation is obtained:
        String resultMessage = this.displayer.displayResultsOfCalculation(sampleData);

        // putExtra() adds the editText's value to the intent (?)
        // now the intent will send the result message of the calculation
        // to the new activity:

        // send both strings with the intent:
        intent.putExtra(EXTRA_MESSAGE, resultMessage);

        startActivity(intent);
    }


}
