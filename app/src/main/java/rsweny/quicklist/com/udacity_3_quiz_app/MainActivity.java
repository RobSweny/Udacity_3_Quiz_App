package rsweny.quicklist.com.udacity_3_quiz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    } // End onCreate
} // End MainActivity
