package com.android.debug.hv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity that handles a {@link LocalViewServer} in the corresponding lifecycle methods.
 * 
 * @author diego
 *
 */
public abstract class LocalViewServerActivity extends Activity {

    public static final String EXTRA_VIEW_SERVER_PORT = "com.dtmilano.android.viewserver.VIEW_SERVER_PORT";
    public static final String EXTRA_BIND_LOCAL_PORT_ONLY = "com.dtmilano.android.viewserver.BIND_LOCAL_PORT_ONLY";
    
    private int mPort = LocalViewServer.VIEW_SERVER_DEFAULT_PORT;
    private boolean mBindLocalPortOnly = LocalViewServer.BIND_LOCAL_PORT_ONLY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        if (intent != null) {
            mPort = intent.getIntExtra(EXTRA_VIEW_SERVER_PORT, mPort);
            mBindLocalPortOnly = intent.getBooleanExtra(EXTRA_BIND_LOCAL_PORT_ONLY, mBindLocalPortOnly);
        }
        LocalViewServer.get(this, mPort, mBindLocalPortOnly).addWindow(this);
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	LocalViewServer.get(this, mPort, mBindLocalPortOnly).removeWindow(this);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	LocalViewServer.get(this, mPort, mBindLocalPortOnly).setFocusedWindow(this);
    }
}