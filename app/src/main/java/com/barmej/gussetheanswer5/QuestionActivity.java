package com.barmej.gussetheanswer5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    private TextView mTextViewQuestion;

    private static final String[] QUESTIONS = {
            "عدد الدول العربية في قارة أفريقيا 10؟",
            "سورة الكافرون هي السورة التي تعدل ربع القرآن الكريم؟",
            "حجر الألماس هو أقوى أنواع الحجارة؟",
            "يلقب صغير الدب ب دب العسل؟",
            "صوت الضفدع يسمى نقيق؟",
            "أول من كتب بسم الله الرحمن الرحيم هو نبي الله داود-عليه السلام؟"
    };
    private static final Boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false
    };
    private static final String[] ANSWERS_DETAILS = {
            "عدد الدول العربية في قارة أفريقيا تسعة دول",
            "سورة الكافرون تعدل ربع القرآن الكريم",
            "حجر الألماس هو أقوى أنواع الحجارة",
            "يلقب صغير الدب ب ديسم",
            "صوت الضفدع يسمى نقيق",
            "أول من كتب بسم الله الرحمن الرحيم هو نبي الله سليمان بن داود- عليهما السلام"
    };
    private String mCurrentQuestion, mCurrentAnswerDetails;
    private Boolean mCurrentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_PREF,MODE_PRIVATE);
        String applang = sharedPreferences.getString(Constants.APP_LANG,"");
        if (!applang.equals(""))
            LocalHelper.setlocale(this,applang);

        setContentView(R.layout.activity_main);
        mTextViewQuestion = findViewById(R.id.textview_question);
        showNewQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuChangeLang) {
            showLanguageDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void showLanguageDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.Languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language ="ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                            case 2:
                                language = "fr";
                                break;
                        }
                        saveLanguage(language);
                        LocalHelper.setlocale(QuestionActivity.this,language);
                        Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        }



                }).create();
        alertDialog.show();
    }
    private void saveLanguage(String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang",lang);
        editor.apply();
    }

    private void showNewQuestion (){
        Random random = new Random();
        int randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS[randomQuestionIndex];
        mCurrentAnswer = ANSWERS[randomQuestionIndex];
        mCurrentAnswerDetails = ANSWERS_DETAILS[randomQuestionIndex];
        mTextViewQuestion.setText(mCurrentQuestion);
    }
    public void onChangeQuestionClicked(View view){
        showNewQuestion();
    }
    public void onTrueClicked(View view){
        if (mCurrentAnswer == true){
            Toast.makeText(this,"إجابة صحيحة",Toast.LENGTH_SHORT).show();
            showNewQuestion();
        }else {
            Toast.makeText(this,"إجابة خاطئة",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this,AnswerActivity.class);
            intent.putExtra("Question_Answer",mCurrentAnswerDetails);
            startActivity(intent);
        }

    }
    public void onFalseClicked(View view){
        if (mCurrentAnswer == false){
            Toast.makeText(this,"إجابة صحيحة",Toast.LENGTH_SHORT).show();
            showNewQuestion();
        }else{
            Toast.makeText(this,"إجابة خاطئة",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this,AnswerActivity.class);
            intent.putExtra("Question_Answer",mCurrentAnswerDetails);
            startActivity(intent);
        }
    }
    public void onShareQuestionClicked(View view){
        Intent intent = new Intent(QuestionActivity.this,ShareActivity.class);
        intent.putExtra("question text extra",mCurrentQuestion);
        startActivity(intent);
    }
}
