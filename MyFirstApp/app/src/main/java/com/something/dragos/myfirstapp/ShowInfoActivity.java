package com.something.dragos.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText(generateAppInfoText());

        ViewGroup layoutForThisActivity = (ViewGroup) findViewById(R.id.activity_show_info);
        layoutForThisActivity.addView(textView);

    }


    /**
     * Returns a string that will inform the user about how to use the app
     * so basically instructions:
     *
     * @return
     */
    private String generateAppInfoText () {
        String text = " Stats Reporter Info: " + "\n" + "\n" + "\n" + "\n";

        // give the user the general instructions here:
        text +=  "• Basic Functionality: " + "\n" + "\n" + "\n" + "\n";
        text += "Enter your data set in the text field. Values must be separated by at least one space," +
                " and the calculator only accepts pure numbers. Using the radio buttons select what type of " +
        " calculation you want to perform, and use the 'Working' toggle button to select whether you want" +
        " the answer, as well as a step by step explanation, or just the answer by itself. Press calculate once you are done.";

        // give the user info about menu bar items here:
        text += "\n" + "\n" + "\n" + "\n" + "• Menu Bar Items: " + "\n" + "\n" + "\n" + "\n";
        text += "The first screen has 5 icons: A delete icon (press it to clear the data currently entered)" +
                " Left and right arrows (use them to navigate through all the deleted data sets that were removed " +
                "using the delete icon. A number will also appear indicating the order which that data set was deleted) " +
                "A voice icon (Please see 'Voice Recognition Feature' bellow) and the info button." + "\n" +
        "The second screen has one icon, it is used to share the calculators results for your data set via other mobile apps" +
                " in text format.";

        // give the user info about the voice recognition feature here:
        text += "\n" + "\n" + "\n" + "\n" + "• Voice Recognition Feature:" + "\n" + "\n" + "\n" + "\n";
        text += "The voice icon on the very first screen can be used to speak your numbers into the calculator. Say 'comma' " +
                " between each number. Once you" +
                " are finished, the calculator will display your numbers on the screen as if you typed them. Note: Commas " +
                "are not shown, but you must say 'comma' in order to separate each number in your data";

        return text;
    }
}
