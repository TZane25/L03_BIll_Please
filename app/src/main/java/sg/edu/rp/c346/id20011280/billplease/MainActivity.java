package sg.edu.rp.c346.id20011280.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declaring variables
        EditText AmountBox;
        EditText PaxBox;
        ToggleButton tbSVS;
        ToggleButton tbGST;
        EditText DiscountNumber;
        RadioGroup radioGroupPayment;
        Button SplitButton;
        Button ResetButton; // buttons
        EditText OUTPUT;

        // linking field variables to UI components in the layout

        AmountBox = findViewById(R.id.Amountbox);
        PaxBox = findViewById(R.id.PaxBox);
        tbSVS = findViewById(R.id.tbSVS);
        tbGST = findViewById(R.id.tbGST);
        DiscountNumber = findViewById(R.id.DiscountNumber);
        radioGroupPayment = findViewById(R.id.radioGroupPaymentMode);
        SplitButton = findViewById(R.id.SplitButton);
        ResetButton = findViewById(R.id.ResetButton);
        OUTPUT = findViewById(R.id.OUTPUT);

        //CODE FOR ACTIONS

        // for splitting the billn // singular button
        SplitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View V)
            {
                if(AmountBox.getText().toString().trim().length() != 0 && PaxBox.getText().toString().trim().length() !=0)
                {
                    double amount = 0.0;

                    if (tbSVS.isChecked() == false && tbGST.isChecked() == false) // if no gst or service charge
                    {
                        amount = Double.parseDouble(AmountBox.getText().toString());
                    }
                    else if(tbSVS.isChecked() == true && tbGST.isChecked() == false) // if no gst but have service charge
                    {
                        amount = Double.parseDouble(AmountBox.getText().toString()) * 1.1;
                    }
                    else if(tbSVS.isChecked() == false && tbGST.isChecked() == true) // if gst but have no service charge
                    {
                        amount = Double.parseDouble(AmountBox.getText().toString()) * 1.07;
                    }
                    else
                    {
                        amount = Double.parseDouble(AmountBox.getText().toString()) * 1.17; // if got gst and service charge
                    }

                    if(DiscountNumber.getText().toString().trim().length() != 0)
                    {
                        amount *= 1 - (Double.parseDouble(DiscountNumber.getText().toString())) / 100;
                    }

                    OUTPUT.setText("Total Bill : $  " + String.format("$.2f", amount));

                    int NumberOfpeople = Integer.parseInt(PaxBox.getText().toString());
                    if(NumberOfpeople != 1)
                    {
                        OUTPUT.setText("Each pays : $" + String.format("%.2f", amount/NumberOfpeople));

                    }
                    else
                    {
                        OUTPUT.setText("Please pay : $" + amount);
                    }
                    // reset button

                    ResetButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            AmountBox.setText("");
                            PaxBox.setText("");
                            tbSVS.setChecked(false);
                            tbGST.setChecked(false);
                            DiscountNumber.setText("");
                        }
                    });


                }


            }

        });
















    }
}
