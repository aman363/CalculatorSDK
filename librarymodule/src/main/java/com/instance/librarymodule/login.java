package com.instance.librarymodule;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.lang.reflect.Field;

public class login extends AppCompatActivity implements View.OnClickListener {

    TextView resultTV, solutionTV;
    MaterialButton buttonC, buttonOpenBrac, buttonCloseBrac, buttonAC, buttonDot;
    MaterialButton buttonPlus, buttonSubract, buttonDivide, buttonMultiply, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        assignId("buttonC", R.id.button_c);
        assignId("buttonOpenBrac", R.id.button_open_bracket);
        assignId("buttonCloseBrac", R.id.button_close_bracket);
        assignId("buttonPlus", R.id.button_plus);
        assignId("buttonDot", R.id.button_dot);
        assignId("buttonAC", R.id.button_ac);
        assignId("buttonSubract", R.id.button_subtract);
        assignId("buttonDivide", R.id.button_divide);
        assignId("buttonMultiply", R.id.button_multiply);
        assignId("buttonEquals", R.id.button_equal);
        assignId("button0", R.id.button_zero);
        assignId("button1", R.id.button_1);
        assignId("button2", R.id.button_2);
        assignId("button3", R.id.button_3);
        assignId("button4", R.id.button_4);
        assignId("button5", R.id.button_5);
        assignId("button6", R.id.button_6);
        assignId("button7", R.id.button_7);
        assignId("button8", R.id.button_8);
        assignId("button9", R.id.button_9);


        MaterialButton backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "Exited from the module", Toast.LENGTH_SHORT).show();
                finish(); // Finish the activity
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void assignId(String buttonFieldName, int id) {
        try {
            Field field = login.class.getDeclaredField(buttonFieldName);
            field.setAccessible(true);
            MaterialButton button = findViewById(id);
            field.set(this, button);
            button.setOnClickListener(this); // Assuming MainActivity implements OnClickListener
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }

        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTV.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Error")) {
            resultTV.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}
