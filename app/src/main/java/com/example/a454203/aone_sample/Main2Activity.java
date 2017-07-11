package com.example.a454203.aone_sample;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_layout);
        setUpUIContents();
    }

    void setUpUIContents() {
        Button button = (Button) findViewById(R.id.activity2Button);
        button.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    void handleButtonClick(Button button) {
        TextView textView = (TextView) findViewById(R.id.firstTopText);
        textView.setText(R.string.TextOnClick_FirstTop);
    }
    void handleButtonClick2(Button button) {
        TextView textView = (TextView) findViewById(R.id.firstTopText);
        textView.setText(R.string.button2ShowText);
    }

    @Override
    public void onClick(View v) {
       Button button  = (Button) v;

        int id = button.getId();

        switch (id) {
            case R.id.activity2Button:
                handleButtonClick(button);
                break;
            case R.id.button2:
                handleButtonClick2(button);
                break;
            default:
               break;
        }

    }
}
