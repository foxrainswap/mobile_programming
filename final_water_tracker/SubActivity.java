package com.example.final_water_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    private TextView waterGoalTextView;
    private TextView caffeineLimitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        waterGoalTextView = findViewById(R.id.textViewWaterGoal);
        caffeineLimitTextView = findViewById(R.id.textViewCaffeineLimit);

        Button calculateButton = findViewById(R.id.btnCalculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateGoals();
            }
        });

        Button saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoalsAndReturn();
            }
        });
    }

    private void calculateGoals() {
        EditText ageEditText = findViewById(R.id.editTextAge);
        EditText heightEditText = findViewById(R.id.editTextHeight);
        EditText weightEditText = findViewById(R.id.editTextWeight);

        String ageInput = ageEditText.getText().toString();
        String heightInput = heightEditText.getText().toString();
        String weightInput = weightEditText.getText().toString();

        if (!ageInput.isEmpty() && !heightInput.isEmpty() && !weightInput.isEmpty()) {
            int age = Integer.parseInt(ageInput);
            int height = Integer.parseInt(heightInput);
            int weight = Integer.parseInt(weightInput);

            // Calculate water goal: (height + weight) * 10
            int waterGoal = (height + weight) * 10;
            waterGoalTextView.setText("물 섭취 목표: " + waterGoal + " ml");

            // Calculate caffeine limit: If age is 20 or below, weight * 1.5; else, 400 mg
            int caffeineLimit = (age <= 20) ? (int) (weight * 2.5) : 400;
            caffeineLimitTextView.setText("카페인 제한 목표: " + caffeineLimit + " mg");
        }
    }

    private void saveGoalsAndReturn() {
        // Retrieve calculated values
        String waterGoalText = waterGoalTextView.getText().toString();
        String caffeineLimitText = caffeineLimitTextView.getText().toString();

        // Create an intent to send the data back to MainActivity
        Intent intent = new Intent();
        intent.putExtra("waterGoal", waterGoalText);
        intent.putExtra("caffeineLimit", caffeineLimitText);

        // Set the result to OK and attach the intent
        setResult(RESULT_OK, intent);

        // Finish the current activity to return to MainActivity
        finish();
    }

}