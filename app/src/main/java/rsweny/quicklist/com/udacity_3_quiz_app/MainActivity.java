package rsweny.quicklist.com.udacity_3_quiz_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Variables
    private String user_name;

    // Declarations
    private EditText enter_name_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Views
        enter_name_edittext = findViewById(R.id.enter_name_edittext);
        Button enter_quiz_button = findViewById(R.id.enter_quiz_button);

        // dev_mode was introduced to skip the first section of the app
        // set dev_mode to true to skip entering name
        Boolean dev_mode = true;
        Toast.makeText(this,"Dev mode: " + dev_mode,Toast.LENGTH_SHORT).show();

        if (dev_mode) {
            // Proceed to next activity
            user_name = "Developer";
            Intent myIntent = new Intent(MainActivity.this, Quiz.class);
            myIntent.putExtra("user_name", user_name); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } // End if dev_mode

        // OnClick for enter quiz button
        enter_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = String.valueOf(enter_name_edittext.getText());

                // Check if the user has entered a name into the editText
                if (user_name.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed to next activity
                    Intent myIntent = new Intent(MainActivity.this, Quiz.class);
                    myIntent.putExtra("user_name", user_name); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                } // End if
            } // End onClick
        }); // End Listener

        // Hide keyboard when user clicks outside edittext
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(MainActivity.this);
                return false;
            } // End onTouch
        });
    } // End onCreate

    // Method to hide keyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    } // End hideSoftKeyboard
} // End MainActivity
