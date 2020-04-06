package com.barmej.gussetheanswer5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView mTextViewAnswerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        mTextViewAnswerDetails = findViewById(R.id.Text_View_Answer);
        String answer = getIntent().getStringExtra("Question_Answer");
        if (answer != null)
            mTextViewAnswerDetails.setText(answer);
    }
    public void onReturnClicked(View view){
        finish();
    }
}
