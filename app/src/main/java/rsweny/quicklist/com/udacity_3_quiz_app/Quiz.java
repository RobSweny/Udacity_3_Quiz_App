package rsweny.quicklist.com.udacity_3_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    // Strings for holding questions
    private String[] questions_one = {"AAA", "BBB", "CCC", "DDD", "EEE"};
    private String[] questions_two = {"AAA", "BBB", "CCC", "DDD", "EEE"};
    private String[] questions_three = {"AAA", "BBB", "CCC", "DDD", "EEE"};
    private String[] questions_four = {"AAA", "BBB", "CCC", "DDD", "EEE"};

    // Variables
    private String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        // Declarations
        TextView user_name_textview = findViewById(R.id.user_name_textview);

        // Retrieve username from previous intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("user_name");


        user_name_textview.setText("Welcome " + username + ", to my quiz app!");

    } // End onCreate
}
