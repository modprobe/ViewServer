
package com.android.debug.hv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class StopLocalViewServerActivity extends FragmentActivity {

    private static final String TAG = "StopLocalViewServerActivity";

    public static class ConfirmAction extends DialogFragment {

        private static final String TITLE_ID = "title_id";

        public static ConfirmAction newInstance(int titleId) {
            final ConfirmAction fragment = new ConfirmAction();
            final Bundle args = new Bundle();
            args.putInt(TITLE_ID, titleId);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final int title = getArguments().getInt(TITLE_ID);

            return new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setMessage(R.string.stop_localviewserver)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((StopLocalViewServerActivity) getActivity()).stopViewServer();
                                }
                            }
                    )
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    getActivity().finish();
                                }
                            })
                    .create();
        }
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        (ConfirmAction.newInstance(R.string.confirm_action_dialog_title)).show(
                getSupportFragmentManager(), "confirm_action");
    }

    /**
     * Stops the <em>LocalViewServer</em>.
     */
    public void stopViewServer() {
        LocalViewServer.get(this).stop();
        finish();
    }
}
