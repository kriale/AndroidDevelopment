package edu.kriale.lab4.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.kriale.lab4.R;
import edu.kriale.lab4.service.PrimesFindingIntentService;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver primesReceiver = new ResultPrimesBroadcastReceiver();
    private IntentFilter resultPrimesIntentFilter = new IntentFilter(
            PrimesFindingIntentService.BROADCAST_ACTION_PRIMES );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        LocalBroadcastManager.getInstance(this).registerReceiver(
                primesReceiver,
                resultPrimesIntentFilter);
    }

    public void onClick_FindPrimes(View view) {
        EditText numberEditText = findViewById( R.id.numberEditView );
        String numberString = numberEditText.getText().toString();
        if (!numberString.isEmpty()) {
            int number = Integer.parseInt( numberString );
            startFindingPrimesService( getBaseContext(), number );
            blockFindPrimesButton();
        } else {
            showErrorMessage();
        }
    }

    private void blockFindPrimesButton() {
        Button button = findViewById( R.id.findPrimesButton );
        button.setClickable( false );
    }

    private void unblockFindPrimesButton() {
        Button button = findViewById( R.id.findPrimesButton );
        button.setClickable( true );
    }

    private void showErrorMessage() {
        Toast.makeText( getBaseContext(),
                R.string.number_is_empty_error_message,
                Toast.LENGTH_SHORT ).show();
    }

    private static void startFindingPrimesService(Context context, Integer maxNumber) {
        Intent intent = new Intent( context, PrimesFindingIntentService.class );
        intent.setAction( PrimesFindingIntentService.ACTION_FIND_PRIMES );
        intent.putExtra( PrimesFindingIntentService.EXTRA_PARAM_NUMBER,
                String.valueOf( maxNumber ) );
        context.startService( intent );

    }

    private void showAnswer(String primes, String primesNumber) {
        setPrimes( primes );
        setPrimesNumber( primesNumber );
        unblockFindPrimesButton();
    }

    private void setPrimes(String primes) {
        setTextForTextViewById(R.id.primesTextView, primes);
    }

    private void setPrimesNumber(String primesNumber) {
        setTextForTextViewById(R.id.primesNumberTextView, primesNumber);
    }

    private void setTextForTextViewById(int id, String text) {
        TextView textView = findViewById( id );
        textView.setText( text );
    }

    public class ResultPrimesBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String primes = intent.getStringExtra(
                    PrimesFindingIntentService.EXTRA_PARAM_PRIMES );
            String primesNumber = intent.getStringExtra(
                    PrimesFindingIntentService.EXTRA_PARAM_PRIMES_NUMBER );
            showAnswer(primes, primesNumber);
        }
    }
}
