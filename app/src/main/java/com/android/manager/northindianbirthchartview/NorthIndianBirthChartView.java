package com.android.manager.northindianbirthchartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import androidx.annotation.NonNull;

public class NorthIndianBirthChartView extends View {

    private int[][] houseSigns = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
            {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1},
            {0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2},
            {0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3},
            {0, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4},
            {0, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5},
            {0, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6},
            {0, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7},
            {0, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8},
            {0, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {0, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {0, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}
    };
    private Paint paintOutline = new Paint();
    private Paint paintSigns = new Paint();
    private Paint paintPlanets = new Paint();
    private int ascendantSign = 1;
    private ArrayList<String> planets = new ArrayList<>();
    private int w;
    private int h;
    private int SIGN_PADDING = 25;
    private int PLANET_PADDING = 35;

    private void init() {
        //PAINT PLANETS
        paintPlanets.setColor(Color.BLACK);
        paintPlanets.setStrokeWidth(8);
        paintPlanets.setTextSize(20);

        //PAINT OUTLINE
        paintOutline.setColor(Color.RED);
        paintOutline.setStrokeWidth(1);
        paintOutline.setStyle(Paint.Style.STROKE);

        //PAINT SIGNS
        paintSigns.setColor(Color.BLACK);
        paintSigns.setStrokeWidth(5);
        paintSigns.setTextSize(20);
        paintPlanets.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        //DEFAULT PLANETS
        planets.clear();
        for (int i = 1; i <= 12; i++) {
            planets.add("Su,Mo,Ma,Ju,Me,Ve,Sa");
        }
    }

    public NorthIndianBirthChartView(Context context) {
        super(context);
        init();
    }

    public NorthIndianBirthChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NorthIndianBirthChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setPlanets(int ascendantSign, @NonNull ArrayList<String> planets) {
        if (planets.size() > 12 || ascendantSign < 1 || ascendantSign > 12) return;
        this.ascendantSign = ascendantSign;
        this.planets = planets;
        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        w = getWidth();
        h = getHeight();
        drawOutline(canvas);
        drawPlanets(canvas);
        super.draw(canvas);
    }

    private void drawPlanets(Canvas canvas) {
        drawHouseSigns(canvas);
        ArrayList<String> temp = new ArrayList<>();
        for(String s : planets){
            s = format(s);
            temp.add(s);
        }
        planets.clear();
        planets.addAll(temp);
        //1
        canvas.drawText(planets.get(0), w / 4f + PLANET_PADDING, h / 4f, paintPlanets);
        //4
        canvas.drawText(planets.get(3), PLANET_PADDING, h / 2f, paintPlanets);
        //7
        canvas.drawText(planets.get(6), w / 4f + PLANET_PADDING, h - h / 4f, paintPlanets);
        //10
        canvas.drawText(planets.get(9), w / 2f + PLANET_PADDING + SIGN_PADDING, h / 2f, paintPlanets);
        //2
        canvas.drawText(planets.get(1), PLANET_PADDING + 10, PLANET_PADDING, paintPlanets);
        //12
        canvas.drawText(planets.get(11), w / 2f + PLANET_PADDING + 10, PLANET_PADDING, paintPlanets);
        //6
        canvas.drawText(planets.get(5), PLANET_PADDING + 20, h - PLANET_PADDING, paintPlanets);
        //8
        canvas.drawText(planets.get(7), w / 2f + PLANET_PADDING + 20, h - PLANET_PADDING, paintPlanets);
        //3
        canvas.drawText(planets.get(2).substring(0, 5), 5, 0.175f * h, paintPlanets);
        canvas.drawText(planets.get(2).substring(6, 14), 5, 0.25f * h, paintPlanets);
        canvas.drawText(planets.get(2).substring(15), 5, 0.325f * h, paintPlanets);
        //11
        canvas.drawText(planets.get(10).substring(0, 5), w - 2f * PLANET_PADDING, 0.175f * h, paintPlanets);
        canvas.drawText(planets.get(10).substring(6, 14), w - 3f * PLANET_PADDING, 0.25f * h, paintPlanets);
        canvas.drawText(planets.get(10).substring(15), w - 2f * PLANET_PADDING, 0.325f * h, paintPlanets);
        //5
        canvas.drawText(planets.get(4).substring(0, 5), 5, h - 0.175f * h, paintPlanets);
        canvas.drawText(planets.get(4).substring(6, 14), 5, h - 0.25f * h, paintPlanets);
        canvas.drawText(planets.get(4).substring(15), 5, h - 0.325f * h, paintPlanets);
        //9
        canvas.drawText(planets.get(8).substring(0, 5), w - 2f * PLANET_PADDING, h - 0.175f * h, paintPlanets);
        canvas.drawText(planets.get(8).substring(6, 14), w - 3f * PLANET_PADDING, h - 0.25f * h, paintPlanets);
        canvas.drawText(planets.get(8).substring(15), w - 2f * PLANET_PADDING, h - 0.325f * h, paintPlanets);

    }

    private void drawHouseSigns(Canvas canvas) {
        //CENTRAL HOUSES
        //1
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][1])
                , w / 2f, h / 2f - SIGN_PADDING,
                paintSigns);
        //4
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][4])
                , w / 2f - SIGN_PADDING, h / 2f,
                paintSigns);
        //7
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][7])
                , w / 2f, h / 2f + SIGN_PADDING,
                paintSigns);
        //10
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][10]),
                w / 2f + SIGN_PADDING, h / 2f,
                paintSigns);
        //2
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][2])
                , w / 4f, h / 4f - SIGN_PADDING,
                paintSigns);
        //3
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][3])
                , w / 4f - SIGN_PADDING, h / 4f,
                paintSigns);
        //5
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][5])
                , w / 4f - SIGN_PADDING, h - h / 4f,
                paintSigns);
        //6
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][6])
                , w / 4f, h - h / 4f + SIGN_PADDING,
                paintSigns);
        //8
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][8])
                , w - w / 4f, h - h / 4f + SIGN_PADDING,
                paintSigns);
        //9
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][9])
                , w - w / 4f + SIGN_PADDING, h - h / 4f,
                paintSigns);
        //11
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][11])
                , w - w / 4f + SIGN_PADDING, h / 4f,
                paintSigns);
        //12
        canvas.drawText(String.valueOf(houseSigns[ascendantSign][12])
                , w - w / 4f, h / 4f - SIGN_PADDING,
                paintSigns);
    }

    private void drawOutline(Canvas canvas) {
        //OUTER RECTANGLE
        canvas.drawRect(0, 0, w, h, paintOutline);
        //DIAGONAL +45
        canvas.drawLine(0, h, w, 0, paintOutline);
        //DIAGONAL -45
        canvas.drawLine(0, 0, w, h, paintOutline);
        //INNER RECTANGLE
        canvas.drawLine(0, h / 2f, w / 2f, 0, paintOutline);
        canvas.drawLine(w / 2f, 0, w, h / 2f, paintOutline);
        canvas.drawLine(w, h / 2f, w / 2f, h, paintOutline);
        canvas.drawLine(w / 2f, h, 0, h / 2f, paintOutline);
    }


    private String format(String str) {
        StringBuilder strBuilder = new StringBuilder(str);
        for (int i = strBuilder.length(); i <= 16; i++)
            strBuilder.append(" ");
        str = strBuilder.toString();
        return str;
    }

}