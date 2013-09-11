package com.android.debug.hv;

import java.io.IOException;

import android.app.Activity;
import android.view.View;

public interface ViewServer extends Runnable {

    /**
     * Starts the server.
     * 
     * @return True if the server was successfully created, or false if it
     *         already exists.
     * @throws IOException
     *             If the server cannot be created.
     * 
     * @see #stop()
     * @see #isRunning()
     * @see WindowManagerService#startViewServer(int)
     */
    public abstract boolean start() throws IOException;

    /**
     * Stops the server.
     * 
     * @return True if the server was stopped, false if an error occurred or if
     *         the server wasn't started.
     * 
     * @see #start()
     * @see #isRunning()
     * @see WindowManagerService#stopViewServer()
     */
    public abstract boolean stop();

    /**
     * Indicates whether the server is currently running.
     * 
     * @return True if the server is running, false otherwise.
     * 
     * @see #start()
     * @see #stop()
     * @see WindowManagerService#isViewServerRunning()
     */
    public abstract boolean isRunning();

    /**
     * Invoke this method to register a new view hierarchy.
     * 
     * @param activity
     *            The activity whose view hierarchy/window to register
     * 
     * @see #addWindow(View, String)
     * @see #removeWindow(Activity)
     */
    public abstract void addWindow(Activity activity);

    /**
     * Invoke this method to unregister a view hierarchy.
     * 
     * @param activity
     *            The activity whose view hierarchy/window to unregister
     * 
     * @see #addWindow(Activity)
     * @see #removeWindow(View)
     */
    public abstract void removeWindow(Activity activity);

    /**
     * Invoke this method to register a new view hierarchy.
     * 
     * @param view
     *            A view that belongs to the view hierarchy/window to register
     * @name name The name of the view hierarchy/window to register
     * 
     * @see #removeWindow(View)
     */
    public abstract void addWindow(View view, String name);

    /**
     * Invoke this method to unregister a view hierarchy.
     * 
     * @param view
     *            A view that belongs to the view hierarchy/window to unregister
     * 
     * @see #addWindow(View, String)
     */
    public abstract void removeWindow(View view);

    /**
     * Invoke this method to change the currently focused window.
     * 
     * @param activity
     *            The activity whose view hierarchy/window hasfocus, or null to
     *            remove focus
     */
    public abstract void setFocusedWindow(Activity activity);

    /**
     * Invoke this method to change the currently focused window.
     * 
     * @param view
     *            A view that belongs to the view hierarchy/window that has
     *            focus, or null to remove focus
     */
    public abstract void setFocusedWindow(View view);

}