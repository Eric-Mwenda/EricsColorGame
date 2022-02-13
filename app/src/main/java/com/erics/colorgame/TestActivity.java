package com.erics.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestActivity extends AppCompatActivity {

    Button[] answerButtons = new Button[5];
    Button changeImageButton;
    ImageView questionImage;
    TextView congratulationsTextView;
    Random random = new Random();
    String[] colorNamesArray, congratulationsMessages;
    String answerColor;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        colorNamesArray = new String[]{"Green","Red","Orange", "Yellow", "Blue"};
        congratulationsMessages = new String[] {"Congratulations", "Amazing", "Very Good", "Well Done", "Brilliant"};

        answerButtons[0] = findViewById(R.id.colorButton1);
        answerButtons[1] = findViewById(R.id.colorButton2);
        answerButtons[2] = findViewById(R.id.colorButton3);
        answerButtons[3] = findViewById(R.id.colorButton4);
        answerButtons[4] = findViewById(R.id.colorButton6);
        changeImageButton = findViewById(R.id.colorButton5);
        congratulationsTextView = findViewById(R.id.congratulationsTextView);
        changeImageButton.setVisibility(View.GONE);

        questionImage = findViewById(R.id.questionImage);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cheering);
        changeImage();
        setRandomNameToButton();
    }

    private void setRandomNameToButton(){
        reArrangeTheColorArray();
        for(int i = 0; i < answerButtons.length; ++i){
            answerButtons[i].setText(colorNamesArray[i]);
        }
    }

    private void reArrangeTheColorArray(){
        Random randomShuffle = ThreadLocalRandom.current();
        for (int i = colorNamesArray.length - 1; i > 0; i--)
        {
            int index = randomShuffle.nextInt(i + 1);
            String temporaryHolder = colorNamesArray[index];
            colorNamesArray[index] = colorNamesArray[i];
            colorNamesArray[i] = temporaryHolder;
        }
    }

    private void changeImage(){
        try {
            disableAllOtherAnswers(false);
            changeImageButton.setVisibility(View.GONE);
            congratulationsTextView.setText("");
            setARandomImageToImageView();
            setRandomNameToButton();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    int randomColorIndex;
    String correctColor;
    private void setARandomImageToImageView() throws IllegalAccessException {
        randomColorIndex = random.nextInt(5);
        switch(randomColorIndex){
            case 0:
                questionImage.setImageResource(LevelEasy.redShapes.get(random.nextInt(LevelEasy.redShapes.size())).getInt(null));
                correctColor = "RED";
                break;
            case 1:
                questionImage.setImageResource(LevelEasy.blueShapes.get(random.nextInt(LevelEasy.blueShapes.size())).getInt(null));
                correctColor = "BLUE";
                break;
            case 2:
                questionImage.setImageResource(LevelEasy.greenShapes.get(random.nextInt(LevelEasy.greenShapes.size())).getInt(null));
                correctColor = "GREEN";
                break;
            case 3:
                questionImage.setImageResource(LevelEasy.yellowShapes.get(random.nextInt(LevelEasy.yellowShapes.size())).getInt(null));
                correctColor = "YELLOW";
                break;
            case 4:
                questionImage.setImageResource(LevelEasy.orangeShapes.get(random.nextInt(LevelEasy.orangeShapes.size())).getInt(null));
                correctColor = "ORANGE";
                break;
        }
    }

    private void checkIfColorIsCorrect(String thisColor){
        if(thisColor.equalsIgnoreCase(correctColor)){
            //Toast.makeText(getApplicationContext(), "CORRECT COLOR! CLICK NEXT!", Toast.LENGTH_LONG).show();
            mediaPlayer.start();
            disableAllOtherAnswers(true);
            changeImageButton.setVisibility(View.VISIBLE);
            congratulationsTextView.setText(congratulationsMessages[random.nextInt(congratulationsMessages.length)]);
        }
        else{
            Toast.makeText(getApplicationContext(), "WRONG COLOR! TRY AGAIN!", Toast.LENGTH_SHORT).show();
        }
    }

    private void disableAllOtherAnswers(boolean activeStatus){
        if(activeStatus){
            for (Button answerButton: answerButtons) {
                answerButton.setVisibility(View.GONE);
            }
        }else{
            for (Button answerButton: answerButtons) {
                answerButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void changeImageFromClick(View v){
        if(mediaPlayer.isPlaying())return;
        changeImage();
    }

    public void button5Clicked(View view) {
        answerColor = answerButtons[4].getText().toString();
        checkIfColorIsCorrect(answerColor);
    }

    public void button4Clicked(View view) {
        answerColor = answerButtons[3].getText().toString();
        checkIfColorIsCorrect(answerColor);
    }

    public void button3Clicked(View view) {
        answerColor = answerButtons[2].getText().toString();
        checkIfColorIsCorrect(answerColor);
    }

    public void button2Clicked(View view) {
        answerColor = answerButtons[1].getText().toString();
        checkIfColorIsCorrect(answerColor);
    }

    public void button1Clicked(View view) {
        answerColor = answerButtons[0].getText().toString();
        checkIfColorIsCorrect(answerColor);
    }

}