// Used this tutorial to understand TTS:
// https://www.tutorialspoint.com/android/android_text_to_speech.htm
package com.joshtate.android.ttstester;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech mTextToSpeech;

    private TextView mTextView;
    private Button mButton;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mEditText = (EditText) findViewById(R.id.editText);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)
                    mTextToSpeech.setLanguage(Locale.JAPANESE);
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                mTextToSpeech.speak(tv.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,null, null);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
    }
}
