/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;

/**
 * Helper class to watch calls into OOo with a timeout in milliseconds.
 * <p>
 * Do not add the thread instances to a threadgroup. When testing the bean in
 * an applet it turned out the the ThreadGroup was in an inconsistent state
 * after navigating off the site that contains the applet and back to it.
 * That was tested with a Sun JRE 1.4.2_06
 */
@objid ("0364fbd4-8b25-410b-8d09-a5fe23498417")
public class CallWatchThread extends Thread {
    @objid ("5e2d72df-5f79-4568-a599-b3fc79e18c1a")
    private static boolean DEBUG = false;

    @objid ("c0d7af5d-3f73-4061-8ff7-df02f4bf7b2c")
    private String aTag;

    @objid ("fb7095cb-950c-4205-9c53-19f627a2e63b")
    private boolean bAlive;

    @objid ("838cb6a8-2770-4b60-8459-f17c9a76dd8e")
    private long nTimeout;

    @objid ("633978c2-e3fd-4398-a563-dc6471a7d4d5")
    private Thread aWatchedThread;

    /**
     * Initialize and starts the thread watcher.
     * 
     * @param nTimeout timeout in milliseconds
     */
    @objid ("9b990e2b-8f71-4303-be52-ded6feffa29b")
    public CallWatchThread(final long nTimeout) {
        this(nTimeout, "");
    }

    /**
     * Initialize and starts the thread watcher.
     * 
     * @param nTimeout timeout in milliseconds
     * @param aTag name of the watcher for debug.
     */
    @objid ("03595517-79e5-44ed-9db3-331423c7f050")
    public CallWatchThread(final long nTimeout, final String aTag) {
        super("CallWatchThread: " + aTag);
        this.aWatchedThread = Thread.currentThread();
        this.nTimeout = nTimeout;
        
        this.aTag = aTag;
        setDaemon(true);
        dbgPrint("CallWatchThread(" + this + ").start(" + aTag + ")");
        start();
    }

    /**
     * Cancel the watch dog.
     */
    @objid ("72b5b449-303a-483c-a607-3c4238f18302")
    public synchronized void cancel() {
        dbgPrint("CallWatchThread(" + this + ".cancel(" + this.aTag + ")");
        if (this.aWatchedThread != null && this.aWatchedThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("wrong thread");
        }
        
        this.aWatchedThread = null;
    }

    /**
     * Reset the counter and watch the thread again.
     * 
     * @throws java.lang.InterruptedException if the thread was interrupted.
     */
    @objid ("d69e6950-4b51-44c1-ab7a-0fa52ab8fc9e")
    public synchronized void restart() throws InterruptedException {
        // dbgPrint( "CallWatchThread(" + this + ".restart(" + this.aTag + ")" );
        if (this.aWatchedThread != null && this.aWatchedThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("wrong thread");
        }
        
        this.bAlive = true;
        
        if (interrupted()) {
            throw new InterruptedException();
        }
        notify();
    }

    @objid ("d02567fa-b5f6-482f-8dbc-0ab782f95f9e")
    @Override
    public void run() {
        dbgPrint("CallWatchThread(" + this + ".run(" + this.aTag + ") ***** STARTED *****");
        // long n = 0;
        synchronized (this) {
            while (this.aWatchedThread != null) {
                // dbgPrint( "CallWatchThread(" + this + ").run(" + this.aTag + ") running #" + ++n );
                this.bAlive = false;
                try {
                    // remember: this.wait() releases sync lock on this while waiting
                    wait(this.nTimeout);
                } catch (java.lang.InterruptedException aExc) {
                    this.bAlive = false;
                }
        
                if (!this.bAlive && this.aWatchedThread != null) {
                    // cancel() or restart() not called.
                    // ==> watched thread seems to be dead (not answering)?
                    dbgPrint("CallWatchThread(" + this + ").run(" + this.aTag + ") interrupting");
                    if (false) {
                        Throwable watchedStack = new Throwable(this.aWatchedThread.getName() + " stack trace");
                        watchedStack.setStackTrace(this.aWatchedThread.getStackTrace());
                        LibreOfficeEditors.LOG.warning(new Throwable("CallWatchThread(" + this + ").run(" + this.aTag + ") interrupting", watchedStack));
                    }
        
                    this.aWatchedThread.interrupt();
                    this.aWatchedThread = null;
                }
            }
        }
        
        dbgPrint("CallWatchThread(" + this + ").run(" + this.aTag + ") terminated");
    }

    @objid ("31db19c3-e1c4-4f34-b5c4-5cdfce16e4ba")
    private void dbgPrint(final String aMessage) {
        if (CallWatchThread.DEBUG) {
            System.err.println("OOoBean: " + aMessage);
        }
    }

}
