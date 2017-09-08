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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating references
        this.tipTextView= (TextView) findViewById(R.id.tv_tip);
        this.totalamtEditText= (EditText) findViewById(R.id.et_totalamt);
        this.ratingRatingBar= (RatingBar) findViewById(R.id.rb_service);
        final double[] values={0,0}; // represents total amount and tip percentage in that order

        this.ratingRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
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
                //String s= "You are giving "+ String.format("%.0f",values[1]*100)+"% of $ "+String.format("%.2f",values[0])+".\n" +
                //        "That is $ "+String.format("%.2f",(values[1]*values[0]))+". The total would be $ "+String.format("%.2f",(values[0]*(1+values[1])))+".";
                String s= "Tip Percent:\t%.0f%%\nCash Charged:\t$%.2f\nTip Amount:\t$%.2f\nTotal Sum:\t$%.2f\n";
                tipTextView.setText(String.format(s,values[1]*100,values[0],values[0]*values[1],values[0]*(1+values[1])));
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
                //tipTextView.setText(editable.toString());
                String e = editable.toString();
                if(!e.equals(""))
                    values[0]=Double.parseDouble(editable.toString());
                else
                    values[0]=0;
                String s= "Tip Percent:\t%.0f%%\nCash Charged:\t$%.2f\nTip Amount:\t$%.2f\nTotal Sum:\t$%.2f\n";
                tipTextView.setText(String.format(s,values[1]*100,values[0],values[0]*values[1],values[0]*(1+values[1])));
            }
        });
    }
}
