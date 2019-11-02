package edu.kriale.lab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class ExitDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage( R.string.exit_dialog_message )
                .setPositiveButton( R.string.exit_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MenuFragmentActivity) getActivity()).closeActivity();
                    }
                } )
                .setNegativeButton( R.string.exit_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing
                    }
                } );
        return builder.create();
    }
}
