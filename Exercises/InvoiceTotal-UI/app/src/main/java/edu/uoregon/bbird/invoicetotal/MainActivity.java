package edu.uoregon.bbird.invoicetotal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import java.text.NumberFormat;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity
    implements OnEditorActionListener {

    private TextView totalTextView;
    private EditText subtotalEditText;
    private TextView discountPercentTextView;
    private TextView discountAmountTextView;

    private SharedPreferences storedValues;
    private final String SUBTOTAL_KEY = "subtotalAmountString";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the widgets
        subtotalEditText = (EditText)findViewById(R.id.subtotalEditText);
        discountPercentTextView = (TextView)findViewById(R.id.discountPercentTextView);
        discountAmountTextView = (TextView)findViewById(R.id.discountAmountTextView);
        totalTextView = (TextView)findViewById(R.id.totalTextView);

        // Set the listener
        subtotalEditText.setOnEditorActionListener(this);

        // Get SharedPreferences object for this app
        storedValues = getSharedPreferences("invoiceSettings", MODE_PRIVATE);

    }

    // When the user enters a new subtotal, calculate and display the invoice values
    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if(id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_ACTION_UNSPECIFIED)
        {
           calcAndDisplay();
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        subtotalEditText.setText(storedValues.getString(SUBTOTAL_KEY, ""));
        calcAndDisplay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Get an Editor object
        Editor editor = storedValues.edit();
        editor.putString(SUBTOTAL_KEY, subtotalEditText.getText().toString());
        editor.commit();
    }

    private void calcAndDisplay() {
        Invoice invoice = new Invoice();

        // Convert user input to a double, handle empty string
        double subtotal = 0.0;
        String subtotalText = subtotalEditText.getText().toString();
        if (!subtotalText.equals("") )
            subtotal = Double.parseDouble(subtotalText);

        double total = invoice.calcTotal(subtotal);

        // Display invoice values
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        totalTextView.setText(currency.format(total));
        discountAmountTextView.setText(currency.format(invoice.getDiscountAmount()));
        NumberFormat percent = NumberFormat.getPercentInstance();
        discountPercentTextView.setText(percent.format(invoice.getDiscountPercent()));

    }
}
