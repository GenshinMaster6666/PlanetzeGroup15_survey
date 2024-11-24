package com.b07group15.plantze;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RadioGroup optionsGroup;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionsGroup = findViewById(R.id.optionsGroup);
        Button submitButton = findViewById(R.id.submitButton);
        database = FirebaseDatabase.getInstance().getReference();


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = optionsGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedOption = findViewById(selectedId);
                    String answer = selectedOption.getText().toString();

                    long timestamp = System.currentTimeMillis();


                    UserAnswer userAnswer = new UserAnswer(answer, timestamp);


                    database.child("answers").push().setValue(userAnswer)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(MainActivity.this, "Your answer has been recorded.", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(MainActivity.this, "Failed to record your answer. Please try again.", Toast.LENGTH_SHORT).show();
                                Log.e("MainActivity", "Firebase Error: ", e);
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Please select an option.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static class UserAnswer {
        public String answer;
        public long timestamp;


        public UserAnswer() {
        }


        public UserAnswer(String answer, long timestamp) {
            this.answer = answer;
            this.timestamp = timestamp;
        }
    }
}
