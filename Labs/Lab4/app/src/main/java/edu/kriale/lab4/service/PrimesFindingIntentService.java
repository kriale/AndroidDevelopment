package edu.kriale.lab4.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.TimingLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrimesFindingIntentService extends IntentService {
    public static final String ACTION_FIND_PRIMES = "find_primes";
    public static final String BROADCAST_ACTION_PRIMES = "primes_broadcast";
    public static final String EXTRA_PARAM_NUMBER = "number";
    public static final String EXTRA_PARAM_PRIMES_NUMBER = "primes_count";
    public static final String EXTRA_PARAM_PRIMES = "primes";

    public PrimesFindingIntentService() {
        super( "PrimesFindingIntentService" );
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if ( ACTION_FIND_PRIMES.equalsIgnoreCase( action ) ) {
                int number = Integer.parseInt( intent.getStringExtra( EXTRA_PARAM_NUMBER ) );
                List<Integer> primes = findPrimes( number );
                sendAnswer( convertListToString( primes ), String.valueOf( primes.size() ) );
            } else {
                throw new UnsupportedOperationException( "Unsupported action for service" );
            }
        }
    }

    private void sendAnswer(String primes, String count) {
        Intent intent = new Intent( BROADCAST_ACTION_PRIMES );
        intent.putExtra( EXTRA_PARAM_PRIMES, primes );
        intent.putExtra( EXTRA_PARAM_PRIMES_NUMBER, count );
        LocalBroadcastManager.getInstance( this ).sendBroadcast( intent );
    }

    private List<Integer> findPrimes(int maxNumber) {
        List<Integer> primes = new LinkedList<>();
        if ( maxNumber >= 2 ) {
            primes.add( 2 );
            for ( int i = 3; i < maxNumber + 1; i += 2 ) {
                boolean isPrime = true;
                for ( int prime : primes ) {
                    if ( prime * prime - 1 > i ) {
                        break;
                    }
                    if ( i % prime == 0 ) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    primes.add( i );
                }
            }
        }
        return primes;
    }

    private <T> String convertListToString(List<T> list) {
        return Arrays.toString(list.toArray());
    }
}
