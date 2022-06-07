package ru.mirea.sulokhin.mireaproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    TextView operationField;    // текстовое поле для вывода знака операции
    Double operand = null;  // операнд операции
    String lastOperation = "="; // последняя операция

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        // получаем все поля по id из fragment_calculator.xml
        resultField = view.findViewById(R.id.resultField);
        numberField = view.findViewById(R.id.numberField);
        operationField = view.findViewById(R.id.operationField);

        View.OnClickListener onNumClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick(view);
            }
        };

        View.OnClickListener onOperationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperationClick(view);
            }
        };

        view.findViewById(R.id.digit0).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit1).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit2).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit3).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit4).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit5).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit6).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit7).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit8).setOnClickListener(onNumClickListener);
        view.findViewById(R.id.digit9).setOnClickListener(onNumClickListener);

        view.findViewById(R.id.symbolDot).setOnClickListener(onNumClickListener);

        view.findViewById(R.id.operatorMinus).setOnClickListener(onOperationClickListener);
        view.findViewById(R.id.operatorPlus).setOnClickListener(onOperationClickListener);
        view.findViewById(R.id.operatorDivision).setOnClickListener(onOperationClickListener);
        view.findViewById(R.id.operatorMultiply).setOnClickListener(onOperationClickListener);
        view.findViewById(R.id.operatorEqual).setOnClickListener(onOperationClickListener);

    }

    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view){

        Button button = (Button)view;
        numberField.append(button.getText());

        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }
    // обработка нажатия на кнопку операции
    public void onOperationClick(View view){

        Button button = (Button)view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        // если введенно что-нибудь
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation){

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}