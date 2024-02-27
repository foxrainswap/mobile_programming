package com.example.final_water_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int waterIntake = 0;
    private int caffeineIntake = 0;

    private TextView waterTextView;
    private TextView caffeineTextView;
    private static final int SETTINGS_REQUEST_CODE = 1;

    private EditText waterEditText;
    private EditText coffeeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waterTextView = findViewById(R.id.textViewWater);
        caffeineTextView = findViewById(R.id.textViewCaffeine);

        waterEditText = findViewById(R.id.editTextWater);
        coffeeEditText = findViewById(R.id.editTextCoffee);

        Button inputWaterButton = findViewById(R.id.btnInputWater);
        inputWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleWaterInput();
            }
        });

        Button inputCoffeeButton = findViewById(R.id.btnInputCoffee);
        inputCoffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCoffeeInput();
            }
        });

        Button resetButton = findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetIntake();
            }
        });

        Button settingsButton = findViewById(R.id.btnSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start SubActivity using explicit intent
                startActivityForResult(new Intent(MainActivity.this, SubActivity.class), SETTINGS_REQUEST_CODE);            }
        });
    }

    private void handleWaterInput() {
        String waterInput = waterEditText.getText().toString();

        if (!waterInput.isEmpty()) {
            int waterAmount = Integer.parseInt(waterInput);
            waterIntake += waterAmount;
            updateIntakeTextViews();
            waterEditText.getText().clear(); // Clear the input field
        }
    }

    private void handleCoffeeInput() {
        String coffeeInput = coffeeEditText.getText().toString();

        if (!coffeeInput.isEmpty()) {
            int coffeeAmount = Integer.parseInt(coffeeInput);
            caffeineIntake += (coffeeAmount * 80);
            updateIntakeTextViews();
            coffeeEditText.getText().clear(); // Clear the input field
        }
    }

    private void resetIntake() {
        waterIntake = 0;
        caffeineIntake = 0;
        updateIntakeTextViews();
        waterEditText.getText().clear(); // Clear the water input field
        coffeeEditText.getText().clear(); // Clear the coffee input field
    }

    private void updateIntakeTextViews() {
        waterTextView.setText("물 하루 섭취량: " + waterIntake + " ml");
        caffeineTextView.setText("카페인 하루 섭취량: " + caffeineIntake + " mg");
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
            // Handle data received from SubActivity
            String waterGoal = data.getStringExtra("waterGoal");
            String caffeineLimit = data.getStringExtra("caffeineLimit");

            // Update TextViews with received data
            TextView waterGoalTextView = findViewById(R.id.textViewWaterGoal);
            TextView caffeineLimitTextView = findViewById(R.id.textViewCaffeineLimit);

            waterGoalTextView.setText(waterGoal);
            caffeineLimitTextView.setText(caffeineLimit);
        }
    }
}