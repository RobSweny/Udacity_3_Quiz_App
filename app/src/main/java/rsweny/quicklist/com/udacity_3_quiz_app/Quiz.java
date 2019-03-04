package rsweny.quicklist.com.udacity_3_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    // Declarations
    private TextView user_name_textview;
    private TextView question_textview;
    private Button answer_one_button;
    private Button answer_two_button;
    private Button answer_three_button;
    private Button answer_four_button;
    private CheckBox checkbox_answer_one;
    private CheckBox checkbox_answer_two;
    private CheckBox checkbox_answer_three;
    private CheckBox checkbox_answer_four;
    private Button checkbox_proceed_button;
    private RadioGroup radio_group;
    private RadioButton radio_button_one;
    private RadioButton radio_button_two;
    private RadioButton radio_button_three;
    private RadioButton radio_button_four;
    private Button radio_button_proceed_button;

    // Strings for holding questions
    private String[] questions;
    private String[] answers_one;
    private String[] answers_two;
    private String[] answers_three;
    private String[] answers_four;
    ArrayList<String> user_answers = new ArrayList<>();

    // Variables
    private int current_question_number;
    private Boolean end_of_quiz = false;
    private int user_checkbox_score;
    private int user_radiobutton_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        // Declarations
        TextView user_name_textview = findViewById(R.id.user_name_textview);
        question_textview = findViewById(R.id.question_textview);
        answer_one_button = findViewById(R.id.answer_one_button);
        answer_two_button = findViewById(R.id.answer_two_button);
        answer_three_button = findViewById(R.id.answer_three_button);
        answer_four_button = findViewById(R.id.answer_four_button);
        checkbox_answer_one = findViewById(R.id.checkbox_answer_one);
        checkbox_answer_two = findViewById(R.id.checkbox_answer_two);
        checkbox_answer_three = findViewById(R.id.checkbox_answer_three);
        checkbox_answer_four = findViewById(R.id.checkbox_answer_four);
        checkbox_proceed_button = findViewById(R.id.checkbox_proceed_button);
        radio_group = findViewById(R.id.radio_group);
        radio_button_one = findViewById(R.id.radio_button_one);
        radio_button_two = findViewById(R.id.radio_button_two);
        radio_button_three = findViewById(R.id.radio_button_three);
        radio_button_four = findViewById(R.id.radio_button_four);
        radio_button_proceed_button = findViewById(R.id.radio_button_proceed_button);
        user_name_textview = findViewById(R.id.user_name_textview);

        // Fill String arrays
        questions = getResources().getStringArray(R.array.questions);
        answers_one = getResources().getStringArray(R.array.answers_one);
        answers_two = getResources().getStringArray(R.array.answers_two);
        answers_three = getResources().getStringArray(R.array.answers_three);
        answers_four = getResources().getStringArray(R.array.answers_four);

        // Retrieve username from previous intent and set it as the opening message string
        Intent intent = getIntent();
        String username = intent.getStringExtra("user_name");
        String username_formatted = getResources().getString(R.string.welcome_quiz_textview, username);
        user_name_textview.setText(username_formatted);

        // update quiz
        updateQuiz();
    } // End onCreate

    // Listener for button clicks
    public void buttonClicked(View view) {
        if (view.getId() == R.id.answer_one_button) {
            user_answers.add(String.valueOf(1));
        } else if (view.getId() == R.id.answer_two_button) {
            user_answers.add(String.valueOf(2));
        } else if (view.getId() == R.id.answer_three_button) {
            user_answers.add(String.valueOf(3));
        } else if (view.getId() == R.id.answer_four_button) {
            user_answers.add(String.valueOf(4));
        } else if (view.getId() == R.id.checkbox_proceed_button) {
            // Check if none of the checkboxes are selected
            if (user_checkbox_score == 0) {
                Toast.makeText(this, "Please select a minimum of 1 checkbox", Toast.LENGTH_SHORT).show();
            } else {
                user_answers.add(String.valueOf(user_checkbox_score));
                Log.i("Checkbox score: ", String.valueOf(user_checkbox_score));

                // Hide the checkboxes once the user has completed.
                checkbox_answer_one.setVisibility(View.GONE);
                checkbox_answer_two.setVisibility(View.GONE);
                checkbox_answer_three.setVisibility(View.GONE);
                checkbox_answer_four.setVisibility(View.GONE);
                checkbox_proceed_button.setVisibility(View.GONE);

                // Show Radio buttons
                radio_group.setVisibility(View.VISIBLE);
                radio_button_one.setText(answers_one[current_question_number]);
                radio_button_two.setText(answers_two[current_question_number]);
                radio_button_three.setText(answers_three[current_question_number]);
                radio_button_four.setText(answers_four[current_question_number]);
                radio_button_proceed_button.setVisibility(View.VISIBLE);
            } // End if
        }  else if (view.getId() == R.id.radio_button_proceed_button) {
            if(user_radiobutton_score == 0){
                Toast.makeText(this, "Please select a minimum of 1 radiobutton", Toast.LENGTH_SHORT).show();
            } else {
                user_answers.add(String.valueOf(user_radiobutton_score));
                radio_group.setVisibility(View.GONE);
                radio_button_proceed_button.setVisibility(View.GONE);
            }
        }

        // Increment by 1
        current_question_number++;

        Log.i("Question Number: ", String.valueOf(current_question_number));
        Log.i("Total Questions: ", String.valueOf(questions.length));

        if (current_question_number == questions.length) {
            end_of_quiz = true;
            Toast.makeText(this, "Congrats on finishing the quiz", Toast.LENGTH_SHORT).show();
        } else if (current_question_number == 3) {
            // Hide previous buttons
            answer_one_button.setVisibility(View.GONE);
            answer_two_button.setVisibility(View.GONE);
            answer_three_button.setVisibility(View.GONE);
            answer_four_button.setVisibility(View.GONE);

            // Show Checkbox
            checkbox_answer_one.setVisibility(View.VISIBLE);
            checkbox_answer_two.setVisibility(View.VISIBLE);
            checkbox_answer_three.setVisibility(View.VISIBLE);
            checkbox_answer_four.setVisibility(View.VISIBLE);
            checkbox_proceed_button.setVisibility(View.VISIBLE);
            checkbox_answer_one.setText(answers_one[current_question_number]);
            checkbox_answer_two.setText(answers_two[current_question_number]);
            checkbox_answer_three.setText(answers_three[current_question_number]);
            checkbox_answer_four.setText(answers_four[current_question_number]);
        } else {
            updateQuiz();
        } // End if
    } // End buttonClicked

    public void onRadioClicked(View view) {
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radio_button_one) {
                    user_radiobutton_score = 1;
                } else if(checkedId == R.id.radio_button_two) {
                    user_radiobutton_score = 2;
                } else if(checkedId == R.id.radio_button_three){
                    user_radiobutton_score = 3;
                } else if(checkedId == R.id.radio_button_four){
                    user_radiobutton_score = 4;
                } // End if
            } // End onCheckedChanged
        });
    } // End onCheckboxClicked

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_answer_one:
                if (checked)
                    user_checkbox_score += 4;
                else
                    user_checkbox_score -= 4;
                break;

            case R.id.checkbox_answer_two:
                if (checked)
                    user_checkbox_score += 8;
                else
                    user_checkbox_score -= 8;
                break;

            case R.id.checkbox_answer_three:
                if (checked)
                    user_checkbox_score += 16;
                else
                    user_checkbox_score -= 16;
                break;

            case R.id.checkbox_answer_four:
                if (checked)
                    user_checkbox_score += 32;
                else
                    user_checkbox_score -= 32;
                break;
        }
    } // End onCheckboxClicked

    // Method to start quiz
    public void updateQuiz() {
        if (end_of_quiz) {
            // End of quiz
            // Bring to new activity to show score
            user_name_textview.setText("Quiz Completed!\n Final Score");
            question_textview.setVisibility(View.GONE);


        } else {
            question_textview.setText(questions[current_question_number]);
            answer_one_button.setText(answers_one[current_question_number]);
            answer_two_button.setText(answers_two[current_question_number]);
            answer_three_button.setText(answers_three[current_question_number]);
            answer_four_button.setText(answers_four[current_question_number]);
        } // End if
    } // End startQuiz
} // End quiz
