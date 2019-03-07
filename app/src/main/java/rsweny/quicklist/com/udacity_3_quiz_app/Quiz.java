package rsweny.quicklist.com.udacity_3_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

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
    private LinearLayout checkbox_linear_layout;
    private Button quiz_app_reset;

    // Strings for holding questions
    private String[][] questions;
    ArrayList<Integer> user_answers = new ArrayList<>();
    ArrayList correct_answers = new ArrayList<>(Arrays.asList(1, 2, 2, 12, 3));


    // Variables
    private int current_question_number;
    private int user_checkbox_score;
    private int user_radiobutton_score;
    private int final_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        // Declarations
        user_name_textview = findViewById(R.id.user_name_textview);
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
        checkbox_linear_layout = findViewById(R.id.checkbox_linear_layout);
        quiz_app_reset = findViewById(R.id.quiz_app_reset);

        questions = new String[][]{{
                "\"You're gonna need a bigger boat\"", "Jaws", "Titanic", "Castaway", "Gladiator"
        }, {
                "\"You had me at hello\"", "Casablanca", "Jerry Maguire", "The Shining", "Gone with the wind"
        }, {
                "\"They're here\"", "The Ring", "The Poltergeist", "The Others", "The Grudge"
        }, {
                "Select the actors who voices\nin the movie Toy Story", "Tom Hanks", "Tim Allen", "George Clooney", "Robin Williams"
        }, {
                "\"I'm not that bad,\nIm just drawn that way\"", "Toy Story", "Finding Nemo", "Who framed\nRoger Rabbit?", "Badly Drawn Boy"
        }};

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
            user_answers.add(1);

            current_question_number++;
            updateQuiz();
        } else if (view.getId() == R.id.answer_two_button) {
            user_answers.add(2);

            current_question_number++;
            updateQuiz();
        } else if (view.getId() == R.id.answer_three_button) {
            user_answers.add(3);

            current_question_number++;
            updateQuiz();
        } else if (view.getId() == R.id.answer_four_button) {
            user_answers.add(4);

            current_question_number++;
            updateQuiz();
        } else if (view.getId() == R.id.checkbox_proceed_button) {
            // Check if none of the checkboxes are selected
            if (user_checkbox_score == 0) {
                Toast.makeText(this, "Please select a minimum of 1 checkbox", Toast.LENGTH_SHORT).show();
            } else {
                user_answers.add(user_checkbox_score);
                current_question_number++;

                // Hide the checkboxes once the user has completed.
                checkbox_linear_layout.setVisibility(View.GONE);

                // Show Radio buttons
                radio_group.setVisibility(View.VISIBLE);
                question_textview.setText(questions[current_question_number][0]);
                radio_button_one.setText(questions[current_question_number][1]);
                radio_button_two.setText(questions[current_question_number][2]);
                radio_button_three.setText(questions[current_question_number][3]);
                radio_button_four.setText(questions[current_question_number][4]);
                radio_button_proceed_button.setVisibility(View.VISIBLE);
            } // End if
        } else if (view.getId() == R.id.radio_button_proceed_button) {
            user_answers.add(user_radiobutton_score);
            current_question_number++;

            radio_group.setVisibility(View.GONE);
            radio_button_proceed_button.setVisibility(View.GONE);
        } else if (view.getId() == R.id.quiz_app_reset) {
            user_answers.clear();
            user_radiobutton_score = 0;
            user_checkbox_score = 0;
            current_question_number = 0;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        // Checking Values
        Log.i("Question Number: ", String.valueOf(current_question_number));
        Log.i("Total Questions: ", String.valueOf(questions.length));

        if (current_question_number == questions.length) {
            Toast.makeText(this, "Congrats on finishing the quiz", Toast.LENGTH_SHORT).show();
            user_name_textview.setText("Quiz Completed!\n Final Score: ");
            question_textview.setVisibility(View.GONE);
            radio_button_proceed_button.setVisibility(View.GONE);
            radio_group.setVisibility(View.GONE);
            quiz_app_reset.setVisibility(View.VISIBLE);

            // Compare user answers to correct answers
            for (int i = 0; i < correct_answers.size(); i++) {
                if (user_answers.get(i) == correct_answers.get(i)) {
                    Log.i("User Guess: ", String.valueOf(user_answers.get(i)));
                    Log.i("Correct Answer: ", String.valueOf(correct_answers.get(i)));
                    final_score++;
                }

            }

            question_textview.setText(String.valueOf(final_score));
            question_textview.setVisibility(View.VISIBLE);
        } else if (current_question_number == 3) {
            // Hide previous buttons
            answer_one_button.setVisibility(View.GONE);
            answer_two_button.setVisibility(View.GONE);
            answer_three_button.setVisibility(View.GONE);
            answer_four_button.setVisibility(View.GONE);

            // Show Checkbox
            checkbox_linear_layout.setVisibility(View.VISIBLE);

            question_textview.setText(questions[current_question_number][0]);
            checkbox_answer_one.setText(questions[current_question_number][1]);
            checkbox_answer_two.setText(questions[current_question_number][2]);
            checkbox_answer_three.setText(questions[current_question_number][3]);
            checkbox_answer_four.setText(questions[current_question_number][4]);
        } else {
            updateQuiz();
        } // End if
    } // End buttonClicked


    public void onRadioClicked(View view) {
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_one:
                        user_radiobutton_score = 1;
                        break;
                    case R.id.radio_button_two:
                        user_radiobutton_score = 2;
                        break;
                    case R.id.radio_button_three:
                        user_radiobutton_score = 3;
                        break;
                    case R.id.radio_button_four:
                        user_radiobutton_score = 4;
                        break;
                } // End Switch
            } // End onCheckedChanged
        });
    } // End onCheckboxClicked

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        // There is no possible combination that a user can make to get the same score
        // without hitting the correct combination
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
        // Set Text
        question_textview.setText(questions[current_question_number][0]);
        answer_one_button.setText(questions[current_question_number][1]);
        answer_two_button.setText(questions[current_question_number][2]);
        answer_three_button.setText(questions[current_question_number][3]);
        answer_four_button.setText(questions[current_question_number][4]);
    } // End startQuiz
} // End quiz
