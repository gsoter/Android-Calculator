package a.android.curso.calculadoraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fathzer.soft.javaluator.DoubleEvaluator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText01, editText02;
    private String expression = "";
    private String text = "";
    private Double result = 0.0;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        final Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        final Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        final Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        final Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        final Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        final Button btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        final Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        final Button btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        final Button btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

        final Button btnFloat = findViewById(R.id.btnFloat);
        btnFloat.setOnClickListener(this);

        final Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        final Button btnSinal = findViewById(R.id.btnSinal);
        btnSinal.setOnClickListener(this);

        final Button btnPercent = findViewById(R.id.btnPercent);
        btnPercent.setOnClickListener(this);

        final Button btnDiv = findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(this);

        final Button btnMult = findViewById(R.id.btnMult);
        btnMult.setOnClickListener(this);

        final Button btnSub = findViewById(R.id.btnSub);
        btnSub.setOnClickListener(this);

        final Button btnSoma = findViewById(R.id.btnSoma);
        btnSoma.setOnClickListener(this);

        final Button btnResult = findViewById(R.id.btnEqual);
        btnResult.setOnClickListener(this);

        editText01 = findViewById(R.id.txt01);
        editText02 = findViewById(R.id.txt02);


        editText02.setText("0");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn0:
                editText02.setText(editText02.getText() + "0");
                break;

            case R.id.btn1:
                editText02.setText(editText02.getText() + "1");
                break;

            case R.id.btn2:
                editText02.setText(editText02.getText() + "2");
                break;

            case R.id.btn3:
                editText02.setText(editText02.getText() + "3");
                break;

            case R.id.btn4:
                editText02.setText(editText02.getText() + "4");
                break;

            case R.id.btn5:
                editText02.setText(editText02.getText() + "5");
                break;

            case R.id.btn6:
                editText02.setText(editText02.getText() + "6");
                break;

            case R.id.btn7:
                editText02.setText(editText02.getText() + "7");
                break;

            case R.id.btn8:
                editText02.setText(editText02.getText() + "8");
                break;

            case R.id.btn9:
                editText02.setText(editText02.getText() + "9");
                break;

            case R.id.btnFloat:
                if (count == 0 && editText02.length() != 0) {
                    editText02.setText(editText02.getText() + ".");
                    count++;
                }
                break;

            case R.id.btnSoma:
                setOperador("+");
                break;

            case R.id.btnSub:
                setOperador("-");
                break;

            case R.id.btnMult:
                setOperador("*");
                break;

            case R.id.btnDiv:
                setOperador("/");
                break;

            case R.id.btnSinal:
                if (editText02.length() != 0) {
                    String s = editText02.getText().toString();
                    char sequencia[] = s.toCharArray();
                    if (sequencia[0] == '-')
                        editText02.setText(s.substring(1, s.length()));
                    else
                        editText02.setText("-" + s);
                }
                break;

            case R.id.btnEqual:
                if (editText02.length() != 0) {
                    text = editText02.getText().toString();
                    expression = editText01.getText().toString() + text;
                }
                editText01.setText("");

                if (expression.length() == 0)
                    expression = "0.0";
                DoubleEvaluator evaluator = new DoubleEvaluator();
                try {
                    result = evaluator.evaluate(expression);
                    editText02.setText(result + "");
                } catch (Exception e) {
                    editText02.setText("Expressão Inválida");
                    editText01.setText("");
                    expression = "";
                    e.printStackTrace();
                }
                break;

//            case R.id.btnPercent:
//                if (editText02.length() != 0) {
//                    Double num2 = Double.parseDouble(editText01.getText().getChars(editText01.length()-1), editText01.length()-2);
//                    Double num = Double.parseDouble(editText02.getText().toString());
//                    Double percent = (num/100)*num2;
//                }
//                break;

            case R.id.btnClear:

                text = editText02.getText().toString();

                if (text.length() > 0) {
                    if (text.endsWith(".")) {
                        count = 0;
                    }

                    String newText = text.substring(0, text.length() - 1);
                    char a[] = text.toCharArray();
                    int pos = a.length - 2;

                    for (int i = a.length - 2; i >= 0; i--) {
                        if (a[i] == '.') {
                            count = 0;
                            pos = i;
                            break;
                        }
                    }
                    newText = text.substring(0, pos);
                    if (newText.equals("-")) {
                        newText = "";
                    }
                    editText02.setText(newText);
                }
                break;
        }
    }

    private void setOperador(String operador) {
        if (editText02.length() != 0) {
            String text = editText02.getText().toString();
            editText01.setText(editText01.getText() + text + operador);
            editText02.setText("");
            count = 0;
        } else {
            String text = editText01.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + operador;
                editText01.setText(newText);
            }
        }

    }
}
