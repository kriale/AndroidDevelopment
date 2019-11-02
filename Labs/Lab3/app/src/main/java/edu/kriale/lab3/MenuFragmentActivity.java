package edu.kriale.lab3;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class MenuFragmentActivity extends FragmentActivity {
    private static final String EXIT_DIALOG_TAG = "exitDialog";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_options, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showWelcome:
                Toast.makeText( getApplicationContext(), R.string.welcome, Toast.LENGTH_LONG )
                        .show();
                return true;
            case R.id.exit:
                ExitDialog dialog = new ExitDialog();
                dialog.show( this.getSupportFragmentManager(), EXIT_DIALOG_TAG );
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    public abstract void closeActivity();
}
