package com.erics.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class LevelEasy extends AppCompatActivity {

    ArrayList<Field> redShapes = new ArrayList<>();
    ArrayList<Field> blueShapes = new ArrayList<>();
    ArrayList<Field> greenShapes = new ArrayList<>();
    ArrayList<Field> yellowShapes = new ArrayList<>();
    ArrayList<Field> orangeShapes = new ArrayList<>();

    ImageView[] imageViews = new ImageView[4];
    TextView[] colorNameTextView = new TextView[imageViews.length];
    Field[] drawablesFields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_easy);
        imageViews[0] = findViewById(R.id.imageView0);
        imageViews[1] = findViewById(R.id.imageView1);
        imageViews[2] = findViewById(R.id.imageView2);
        imageViews[3] = findViewById(R.id.imageView3);
        colorNameTextView[0] = findViewById(R.id.colorName0);
        colorNameTextView[1] = findViewById(R.id.colorName1);
        colorNameTextView[2] = findViewById(R.id.colorName2);
        colorNameTextView[3] = findViewById(R.id.colorName3);

        Random random = new Random();

        //Get All drawable elements.
        drawablesFields = com.erics.colorgame.R.drawable.class.getFields();
        String[] colorKeys = new String[]{
          "red4", "green4", "blue4", "yellow4", "orange4"
        };
        //ArrayList<Drawable> drawables = new ArrayList<>();

        for (int i = 0; i < drawablesFields.length; i++) {
            Field field = drawablesFields[i];
            try {
                if(field.getName().contains(colorKeys[0]))redShapes.add(field);
                if(field.getName().contains(colorKeys[1]))greenShapes.add(field);
                if(field.getName().contains(colorKeys[2]))blueShapes.add(field);
                if(field.getName().contains(colorKeys[3]))yellowShapes.add(field);
                if(field.getName().contains(colorKeys[4]))orangeShapes.add(field);
                //drawables.add(getResources().getDrawable(field.getInt(null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setImagesToImageViews();
    }

    private void setImagesToImageViews() {
        Random random = new Random();
        for(int i = 0; i < imageViews.length; ++i){

            try {
                switch(random.nextInt(5)){
                    case 0:
                        imageViews[i].setImageResource(redShapes.get(random.nextInt(redShapes.size())).getInt(null));
                        colorNameTextView[i].setTextColor(Color.RED);
                        colorNameTextView[i].setText("RED");
                        break;
                    case 1:
                        imageViews[i].setImageResource(blueShapes.get(random.nextInt(blueShapes.size())).getInt(null));
                        colorNameTextView[i].setTextColor(Color.rgb(0, 0, 255));
                        colorNameTextView[i].setText("BLUE");
                        break;
                    case 2:
                        imageViews[i].setImageResource(yellowShapes.get(random.nextInt(yellowShapes.size())).getInt(null));
                        colorNameTextView[i].setTextColor(Color.YELLOW);
                        colorNameTextView[i].setText("YELLOW");
                        break;
                    case 3:
                        imageViews[i].setImageResource(greenShapes.get(random.nextInt(greenShapes.size())).getInt(null));
                        colorNameTextView[i].setTextColor(Color.GREEN);
                        colorNameTextView[i].setText("GREEN");
                        break;
                    case 4:
                        imageViews[i].setImageResource(orangeShapes.get(random.nextInt(orangeShapes.size())).getInt(null));
                        colorNameTextView[i].setTextColor(Color.rgb(255, 165, 0));
                        colorNameTextView[i].setText("ORANGE");
                        break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNewImagesToImageViews(View v){
        setImagesToImageViews();
    }

    public void startTheTest(View view) {

    }
}