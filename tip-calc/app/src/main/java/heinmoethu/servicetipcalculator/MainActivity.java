package heinmoethu.servicetipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    private TextView tipTextView;
    private EditText totalamtEditText;
    private RatingBar ratingRatingBar;
    private final String s = "Tip Percent:\t%.0f%%\nCash Charged:\t$%.2f\nTip Amount:\t$%.2f\nTotal Sum:\t$%.2f\n";
    private final double[] values={0,0}; // represents total amount and tip percentage in that order

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating references
        this.tipTextView= (TextView) findViewById(R.id.tv_tip);
        this.totalamtEditText= (EditText) findViewById(R.id.et_totalamt);
        this.ratingRatingBar= (RatingBar) findViewById(R.id.rb_service);

        this.ratingRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //Setting tip percent based on rating
                if (v<=0.5)
                    values[1]=0;
                else if(v<=1.5)
                    values[1]=0.1;
                else if(v<=2.5)
                    values[1]=0.13;
                else if(v<=3.5)
                    values[1]=0.15;
                else if(v<=4.5)
                    values[1]=0.2;
                else
                    values[1]=0.25;

                updateText();
            }
        });

        this.totalamtEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String e = editable.toString();
                //updates the total charged
                if(!e.equals(""))
                    values[0]=Double.parseDouble(editable.toString());
                else
                    values[0]=0;

                updateText();
            }
        });
    }

    private void updateText(){//updates the textview with all new values
        tipTextView.setText(String.format(s,values[1]*100,values[0],values[0]*values[1],values[0]*(1+values[1])));
    }
}
