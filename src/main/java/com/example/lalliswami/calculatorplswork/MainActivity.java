package com.example.lalliswami.calculatorplswork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView display;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button clear;
    Button plus;
    Button minus;
    Button star;
    Button equal;
    Button slash;
    String disp_string;
    ArrayList<String> opHold = new ArrayList<>();
    ArrayList<Double> doubHold = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.id_text_display);
        one = findViewById(R.id.id_button_one);
        two = findViewById(R.id.id_button_two);
        three = findViewById(R.id.id_button_three);
        four = findViewById(R.id.id_button_four);
        five = findViewById(R.id.id_button_five);
        six = findViewById(R.id.id_button_six);
        seven = findViewById(R.id.id_button_seven);
        eight = findViewById(R.id.id_button_eight);
        nine = findViewById(R.id.id_button_nine);
        zero = findViewById(R.id.id_button_zero);
        clear = findViewById(R.id.id_button_clear);
        plus = findViewById(R.id.id_button_plus);
        minus = findViewById(R.id.id_button_minus);
        star = findViewById(R.id.id_button_multiply);
        slash = findViewById(R.id.id_button_divide);
        equal = findViewById(R.id.id_button_equals);
        disp_string = "";
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                opHold.clear();
                doubHold.clear();
                disp_string = "";
            }
        });
        clicker(one);
        clicker(two);
        clicker(three);
        clicker(four);
        clicker(five);
        clicker(six);
        clicker(seven);
        clicker(eight);
        clicker(nine);
        clicker(zero);
        opClicker(plus);
        opClicker(minus);
        opClicker(star);
        opClicker(slash);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    calculate();
                }
                catch(Exception e)
                {
                    display.setText("ERROR");
                }
                doubHold.clear();
            }
        });
    }
    public void clicker(final Button button)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display.getText().equals("0"))
                {
                    disp_string = (String)button.getText();
                }
                else {
                    CharSequence tot = display.getText() + "" + button.getText();
                    disp_string  =(String)tot;
                }
                display.setText(disp_string);
            }
        });
    }
    public void opClicker(final Button button)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display.getText().equals("0"))
                {
                    disp_string = "Invalid Character";
                }
                else {
                    CharSequence tot = display.getText() + "" + button.getText();
                    disp_string  =(String)tot;
                }
                display.setText(disp_string);

            }
        });
    }
            public void calculate()
            {
                double r =0;
                separateThing();
                for(int k=0;;k++){
                    if(opHold.contains("*")|| opHold.contains("/"))
                    {
                        for(int x=0;x<opHold.size();x++)
                        {
                            if(opHold.get(x).equals("*"))
                            {
                                r = (doubHold.get(x)*doubHold.get(x+1));
                                opHold.remove(x);
                                doubHold.set(x+1, r);
                                doubHold.remove(x);
                            }
                            else if(opHold.get(x).equals("/"))
                            {
                                    try
                                    {

                                        if(doubHold.get(x+1) == 0) {

                                            display.setText("DIVIDE BY ZERO ERROR");
                                            return;
                                        }
                                        r = (doubHold.get(x) / doubHold.get(x + 1));
                                        opHold.remove(x);
                                        doubHold.set(x + 1, r);
                                        doubHold.remove(x);
                                    }catch(ArithmeticException e)
                                    {
                                        display.setText("ERROR");
                                    }

                            }
                        }
                    }
                    else
                    {
                        for(int x=0;x<opHold.size();x++)
                        {
                            if(opHold.get(x).equals("+"))
                            {
                                r = (doubHold.get(x)+doubHold.get(x+1));
                                opHold.remove(x);
                                doubHold.set(x+1, r);
                                doubHold.remove(x);
                            }
                            else if(opHold.get(x).equals("-"))
                            {
                                r=(doubHold.get(x)-doubHold.get(x+1));
                                opHold.remove(x);
                                doubHold.set(x+1, r);
                                doubHold.remove(x);
                            }
                            x--;
                        }
                    }
                    if(opHold.size()==0)
                        break;
                }
                display.setText(""+Double.toString(r));
                //return r;
            }
    public void separateThing()
    {
        int hold =0;
        for(int x=0;x<disp_string.length();x++)
        {
            if(x==disp_string.length()-1)
            {
                String numStr = disp_string.substring(hold, x+1);
                doubHold.add(Double.parseDouble(numStr));
            }
            else{
                if(!number(x)){
                    opHold.add(disp_string.substring(x, x+1));
                    String numStr = disp_string.substring(hold, x);
                    doubHold.add(Double.parseDouble(numStr));
                    hold = x+1;
                }
            }
        }
    }
    public boolean number(int x)
    {
        return ((disp_string.charAt(x) >= 48 && disp_string.charAt(x)<=57) || disp_string.charAt(x) == 46);
    }
}