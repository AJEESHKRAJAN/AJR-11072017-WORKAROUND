package com.example.a454203.aone_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ProvideInformationActivity extends AppCompatActivity {

    public static final String CLASS_NAME_EXTRA = "classNameExtra";
    public static final String PERSON_NAME_EXTRA = "personNameExtra";
    public static final String PERSON_EMAIL_EXTRA = "personEmailExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_information);
    }

    private void setUpViews() {
        Button doneButton = (Button) findViewById(R.id.btnDone);
        doneButton.setOnClickListener(s -> handleDoneButton((Button) s));
    }

    private void handleDoneButton(Button s) {
        String className = getSelectedRadioGroupValue(R.id.rdoGroup);
        String personName = getEditTextValue(R.id.txtWrtName);
        String personEmail = getEditTextValue(R.id.txtWrtEmail);

        Intent setResultIntent = new Intent();
        setResultIntent.putExtra(CLASS_NAME_EXTRA, className);
        setResultIntent.putExtra(PERSON_NAME_EXTRA, personName);
        setResultIntent.putExtra(PERSON_EMAIL_EXTRA, personEmail);

        setResult(RESULT_OK,setResultIntent);
        finish();
    }

    private String getEditTextValue(int textViewId) {
        EditText editText = (EditText) findViewById(textViewId);
        Editable editable = editText.getText();

        return (editable != null) ? String.valueOf(editable) : "";
    }

    private String getSelectedRadioGroupValue(int groudId) {
        String selectedText;

        RadioGroup radioGroup = (RadioGroup) findViewById(groudId);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(radioButtonId);
        selectedText = String.valueOf(radioButton.getText());

        return selectedText;
    }
}
