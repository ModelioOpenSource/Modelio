/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lang.DisposedException;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.task.XInteractionHandler;
import com.sun.star.uno.RuntimeException;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.CloseVetoException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

/**
 * @requirement FUNC.PERF.LRN/0.6
 * @requirement FUNC.PERF.LOC/0.6
 * @requirement FUNC.PERF.FIX/0.6
 * This is the basic JavaBean for all OOo application modules.
 * 
 * @requirement FUNC.RES.OTH/0.2 No other resources are needed yet.
 * @since OOo 2.0.0
 */
@objid ("0199a0f6-25fb-4858-9112-ea4895d6f4d9")
public class SwtWinOOoBean extends Composite {
    /**
     * timeout values (milli secs)
     */
    @objid ("86b7dca5-058a-4216-a996-ca041bb44298")
     int nOOoStartTimeOut = 60000;

    @objid ("c7e53264-2839-4fb1-b121-4bab4c763dbc")
     int nOOoCallTimeOut = 3000;

    @objid ("7e0353ba-3759-43bf-81f1-3b695935196a")
     int nOOoCheckCycle = 2000;

    /**
     * properties
     */
    @objid ("266954f9-af5d-41dd-aec1-66039bc47eb9")
    private boolean bIgnoreVisibility = false; // to show even if already visible

    @objid ("20d9bf8b-b351-480b-a2c6-e15f111e7716")
    private boolean bMenuBarVisible = true;

    @objid ("dcaf22cb-d41c-4758-8ba1-d07a727c34a6")
    private boolean bStandardBarVisible = true;

    @objid ("4af4fe4f-520f-43de-95e2-ada5ba3393c0")
    private boolean bToolBarVisible = true;

    @objid ("259d9ea2-4cf7-4d3f-a307-e9bb1ed61f80")
    private boolean bStatusBarVisible = true;

    /**
     * application environment
     */
    @objid ("59e97ff2-3b63-4837-b059-dd8373a1258d")
    private transient com.sun.star.lang.XMultiServiceFactory xServiceFactory;

    @objid ("f4bf3eb3-8bf0-467e-975c-504833277bdd")
    private transient com.sun.star.frame.XDesktop xDesktop;

    /**
     * slot command execution environment.
     * <p>
     * XDispatchProvider provides XDispatch interfaces for certain functions which are useful at the UI.
     */
    @objid ("a5caf4b2-77f5-4f28-b852-d3cb5922732b")
    private transient com.sun.star.frame.XDispatchProvider xDispatcher;

    @objid ("91c0b93a-9a58-448e-a4cb-c2cb89590f66")
    private transient com.sun.star.util.XURLTransformer xURLTransformer;

    /**
     * This member contains the connection to an OOo instance if established.
     */
    @objid ("9570bd47-a48a-4456-a309-046808b5631c")
    private transient OfficeConnection iConnection;

    @objid ("fec99ed9-03ca-4da6-817a-d04af5c99b3d")
    private transient EventListener xConnectionListener;

    /**
     * @requirement FUNC.BEAN.VIEW/0.4
     * @requirement FUNC.BEAN.EDIT/0.4
     * This member contains the OOo window
     * if a connection is established.
     * It is a child of the OOoBean canvas.
     */
    @objid ("6efa7699-1a61-4b91-8697-070f956ba07c")
    private SwtWinOfficeWindow swtFrameWindow;

    /**
     * document and frame
     */
    @objid ("365d7891-c8c5-4253-a00f-627b3920a367")
    private transient Frame aFrame;

    @objid ("f519125b-a52a-4ad6-8645-c1b6b6c2e0f1")
    private transient Controller aController;

    @objid ("aee471b3-538c-4813-a1a6-0369507bd2ab")
    private transient OfficeDocument aDocument;

    @objid ("b30f55ff-29ad-4b0b-9064-e7d59a827977")
    private UnoCaller unoCaller;

    /**
     * debugging method
     * 
     * @param aMessage message to print
     */
    @objid ("140bb6e5-7a8b-454d-bf8d-bcf4102cbcfa")
    static void dbgPrint(final String aMessage) {
        System.err.println("SwtWinOOoBean: " + aMessage);
    }

    /**
     * Generic constructor of the OOoBean.
     * 
     * Neither a connection is established nor any document loaded.
     * @see SWT#NO_BACKGROUND
     * @see SWT#NO_FOCUS
     * @see SWT#NO_MERGE_PAINTS
     * @see SWT#NO_REDRAW_RESIZE
     * @see SWT#NO_RADIO_GROUP
     * @see SWT#EMBEDDED
     * @see SWT#DOUBLE_BUFFERED
     * @see Widget#getStyle
     * 
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param style the style of widget to construct
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
     * </ul>
     */
    @objid ("b25f37d6-21ad-4dc2-a5a5-20d80b02a196")
    public SwtWinOOoBean(final Composite parent, final int style) {
        super(parent, style);
        setLayout(new FillLayout());
        
        // Note: Don't redefine dispose(), it is never called.
        addDisposeListener(new DisposeListener() {
        
            @Override
            public void widgetDisposed(DisposeEvent e) {
                stopOOoConnection();
            }
        });
    }

    /**
     * Sets the timeout for methods which launch OOo in milli seconds.
     * 
     * This method does not need a connection to an OOo instance.
     * 
     * @param nMilliSecs the timeout
     */
    @objid ("173494a1-f4bc-4f4d-8d23-2ceee52047ac")
    public void setOOoStartTimeOut(final int nMilliSecs) {
        this.nOOoStartTimeOut = nMilliSecs;
    }

    /**
     * Sets the timeout for normal OOO methods calls in milli seconds.
     * 
     * This method does not need a connection to an OOo instance.
     * 
     * @param nMilliSecs the timeout
     */
    @objid ("bf6822ab-00c4-4b07-8b46-e0880ca9d14a")
    public void setOOoCallTimeOut(final int nMilliSecs) {
        this.nOOoCallTimeOut = nMilliSecs;
    }

    /**
     * Sets the period length in milli seconds to check the OOo connection.
     * 
     * This method does not need a connection to an OOo instance.
     * 
     * @param nMilliSecs the timeout
     */
    @objid ("1fad958b-e712-45d4-8920-260094f630fc")
    public void setOOoCheckCycle(final int nMilliSecs) {
        this.nOOoCheckCycle = nMilliSecs;
    }

    /**
     * Sets the a connection to an OOo instance.
     * @internal
     */
    @objid ("acc1dd7c-8478-41d6-bdf0-3de4ddac0de2")
    private synchronized void setOOoConnection(final OfficeConnection iNewConnection) throws HasConnectionException, NoConnectionException {
        // the connection cannot be exchanged
        if (this.iConnection != null) {
            throw new HasConnectionException();
        }
        
        // is there a real connection, not just the proxy?
        com.sun.star.uno.XComponentContext xComponentContext = null;
        try {
            xComponentContext = iNewConnection.getComponentContext();
        } catch (java.lang.Throwable aExc) {
            throw new NoConnectionException(aExc);
        }
        if (xComponentContext == null) {
            throw new NoConnectionException();
        }
        
        // set the connection
        this.iConnection = iNewConnection;
        
        // get notified when connection dies
        if (this.xConnectionListener != null) {
            this.xConnectionListener.end();
        }
        
        this.xConnectionListener = new EventListener("setOOoConnection");
        
        // Reset unoCaller
        if (this.unoCaller != null) {
            this.unoCaller.setDisposed();
            this.unoCaller = null;
        }
    }

    /**
     * Disconnects from the connected OOo instance.
     * <p>
     * If there was no connection yet or anymore, this method can be called anyway.
     * <p>
     * When the OOoBean is displayed in an applet by a web browser, then this method must be called from within
     * java.applet.Applet.stop.
     * @requirement FUNC.CON.STOP/0.4
     */
    @objid ("d6223e77-205e-4f20-a07d-e48a6952127c")
    public synchronized void stopOOoConnection() {
        // Stop the connection hang listener
        if (this.xConnectionListener != null) {
            this.xConnectionListener.end();
            this.xConnectionListener = null;
        }
        
        // clear OOo document, frame etc.
        clear();
        
        // Try release the process. Other EventListeners may prevent it if other documents are open.
        if (!tryTerminate()) {
            // cut the connection
            OfficeConnection iExConnection = this.iConnection;
            if (this.iConnection != null) {
                this.iConnection = null;
                iExConnection.dispose();
            }
        }
        
        if (this.unoCaller != null) {
            this.unoCaller.setDisposed();
            this.unoCaller = null;
        }
        
        this.iConnection = null;
        this.xDesktop = null;
        this.xServiceFactory = null;
    }

    /**
     * Returns the a connection to an OOo instance.
     * <p>
     * If no connection exists, a default connection will be created.
     * An OfficeConnection can be used to register
     * listeners of type com.sun.star.lang.EventListener, which are notified when the connection to the office dies.
     * <p>
     * One should not call the dispose method, because this may result in receiving com.sun.star.lang.DisposedExceptions
     * when calling {@link #stopOOoConnection()} or other API methods. If other instances share the same
     * connection then they will stop function properly, because they loose their connection as well. <br/>
     * The recommended way to end the connection is calling {@link #stopOOoConnection()}.
     * 
     * @return a connection to an OOo instance.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection can be established
     * @requirement FUNC.CON.STOP/0.4 (via XComponent.dispose())
     * @requirement FUNC.CON.NTFY/0.4 (via XComponent.addEventListener())
     */
    @objid ("dc335778-d2ca-46c5-952a-8fe4c226ee33")
    public synchronized OfficeConnection getOOoConnection() throws NoConnectionException {
        if (this.iConnection == null) {
            try {
                setOOoConnection(new LocalOfficeConnection());
            } catch (HasConnectionException aExc) { /* impossible here */
            }
        }
        if (this.iConnection.getComponentContext() == null) {
            throw new NoConnectionException();
        }
        return this.iConnection;
    }

    /**
     * Returns the service factory used by this OOoBean instance.
     * 
     * @return the service factory used by this OOoBean instance.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established and no default connection can be established.
     */
    @objid ("1a04f543-6194-4134-95c6-91ac98aa8d50")
    public synchronized com.sun.star.lang.XMultiServiceFactory getMultiServiceFactory() throws NoConnectionException {
        if (this.xServiceFactory == null) {
            // avoid concurrent access from multiple threads
            final OfficeConnection iConn = getOOoConnection();
        
            try {
                this.xServiceFactory = CompletableFuture.supplyAsync(() -> {
                    XMultiComponentFactory aFactory = iConn.getComponentContext()
                            .getServiceManager();
                    return UnoRuntime.queryInterface(com.sun.star.lang.XMultiServiceFactory.class,
                            aFactory);
                }).get(this.nOOoStartTimeOut, TimeUnit.MILLISECONDS);
        
            } catch (java.lang.InterruptedException e) {
                throw new NoConnectionException("Interruption while asking for the LibreOffice service factory.", e);
            } catch (ExecutionException e) {
                throw new NoConnectionException(e.getCause().toString(), e);
            } catch (TimeoutException e) {
                throw new NoConnectionException("Time out while asking for the LibreOffice service factory.", e);
            }
        
            if (this.xServiceFactory == null) {
                throw new NoConnectionException("No service factory found");
            }
        }
        return this.xServiceFactory;
    }

    /**
     * Returns the XDesktop interface of the OOo instance used by this OOoBean.
     * 
     * @return the XDesktop interface
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established and no default connection can be established.
     */
    @objid ("cb4a073c-e779-4d58-a46c-95277a5cf0f6")
    public synchronized com.sun.star.frame.XDesktop getOOoDesktop() throws NoConnectionException {
        if (this.xDesktop == null) {
            try {
                Object aObject = getMultiServiceFactory().createInstance("com.sun.star.frame.Desktop");
                this.xDesktop = UnoRuntime.queryInterface(com.sun.star.frame.XDesktop.class, aObject);
            } catch (com.sun.star.uno.Exception aExc) {
            } // TBD: what if no connection exists?
        }
        return this.xDesktop;
    }

    /**
     * Resets the OOoBean to an empty status.
     * 
     * Any loaded document is unloaded, no matter whether it is modified or not. After calling this method, the OOoBean
     * has no office document and no frame anymore. The connection will stay, though.
     * 
     * This method works with or without an established connection.
     */
    @objid ("e10840d7-f83e-45e4-9824-efdb31eb1a12")
    private synchronized void clear() {
        dbgPrint("clear()");
        
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "clear");
        try {
            // By closing the frame we avoid that dialogs are displayed, for example when
            // the document is modified.
            final com.sun.star.util.XCloseable xCloseable = UnoRuntime.queryInterface(com.sun.star.util.XCloseable.class,
                    this.aFrame);
            if (xCloseable != null) {
                try {
                    this.unoCaller.call(() -> {
                        try {
                            xCloseable.close(true);
                            return true;
                        } catch (com.sun.star.util.CloseVetoException exc) {
                            // a print job may be running
                            return false;
                        } catch (com.sun.star.lang.DisposedException e) {
                            // It sometimes happen on Linux when many documents are opens.
                            return true;
                        }
                    });
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        
            this.aDocument = null;
            this.xDispatcher = null;
            this.aFrame = null;
        
            // clear xFrameWindow
            clearSwtFrameWindow();
        
            // clear xURLTransformer
            if (this.xURLTransformer != null) {
                try {
                    com.sun.star.lang.XComponent xComp = UnoRuntime.queryInterface(com.sun.star.lang.XComponent.class,
                            this.xURLTransformer);
                    if (xComp != null) {
                        xComp.dispose();
                    }
                } catch (java.lang.Throwable aExc) {
                } // ignore
                this.xURLTransformer = null;
            }
        
            // this.xServiceFactory = null;
        } finally {
            aCallWatchThread.cancel();
        }
    }

    /**
     * @requirement FUNC.PAR.LWP/0.4
     * This method causes the office window to be displayed.
     * 
     * If no document is loaded and the instance is added to a Java container that is showing, then this method needs
     * not to be called. If later one of the methods {@link #loadFromURL loadFromURL}, {@link #loadFromStream
     * loadFromStream1}, or {@link #loadFromByteArray loadFromByteArray} is called, then the document is automatically
     * displayed.
     * 
     * Should one of the load methods have been called before the Java container was showing, then this method needs to
     * be called after the container window was made visible (java.lang.Component.setVisible(true)).
     * <p>
     * Another scenario is that a OOoBean contains a document and is removed from a Java container and later added
     * again. Then aquireSystemWindow needs to be called after the container window is displayed.
     * 
     * @throws com.sun.star.comp.beans.SystemWindowException if no system window can be aquired.
     * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
     */
    @objid ("ce0869b7-36ed-44f4-afd6-352ddf04f227")
    void _aquireSystemWindow() throws NoConnectionException, SystemWindowException {
        if (this.iConnection == null) {
            throw new NoConnectionException();
        }
        if (!isVisible()) {
            throw new SystemWindowException();
        }
        
        if (this.swtFrameWindow != null) {
            this.swtFrameWindow.setVisible(true);
        }
        layout();
    }

    /**
     * Loads the bean from the given URL.
     * <p>
     * If a document is already loaded and the content modified, the changes are dismissed.
     * <p>
     * If no connection exists, a default connection is established.
     * @throws com.sun.star.util.CloseVetoException
     * if the currently displayed document cannot be closed because it is still be used, for example it is
     * printed.
     * 
     * @param aURL document URL
     * @param aArguments loading arguments
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection can be established.
     */
    @objid ("d9d500a6-b6be-402a-9f6f-3e8640c5f3cf")
    public void loadFromURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, NoConnectionException, com.sun.star.util.CloseVetoException {
        dbgPrint("loadFromURL()");
        // try loading
        try {
            boolean bLoaded = false;
            while (!bLoaded) {
                // watch loading in a thread with a timeout (if OOo hangs)
                CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoStartTimeOut, "loadFromURL");
        
                try {
                    // get window from OOo on demand
                    if (this.swtFrameWindow == null) {
                        initSWTOfficeWindow();
                    }
        
                    // create the document frame from UNO window.
                    if (this.aFrame == null) {
                        initXFrame();
                    }
        
                    // Initializes the slot command execution environment.
                    this.xURLTransformer = UnoRuntime.queryInterface(com.sun.star.util.XURLTransformer.class,
                            this.xServiceFactory.createInstance("com.sun.star.util.URLTransformer"));
        
                    try {
                        this.xDispatcher = UnoRuntime.queryInterface(com.sun.star.frame.XDispatchProvider.class,
                                this.aFrame);
                    } catch (Exception e) {
                        /* ignore! */
                    }
        
                    // get XComponentLoader from frame
                    com.sun.star.frame.XComponentLoader xLoader = UnoRuntime.queryInterface(com.sun.star.frame.XComponentLoader.class,
                            this.aFrame);
                    if (xLoader == null) {
                        throw new java.lang.RuntimeException("com.sun.star.frame.Frame(" +
                                this.aFrame +
                                ") without com.sun.star.frame.XComponentLoader");
                    }
        
                    // Avoid Dialog 'Document changed' while reloading
                    if (this.aDocument != null) {
                        try {
                            this.aDocument.setModified(false);
                        } catch (com.sun.star.beans.PropertyVetoException ep) {
                            // it dosn't make sense to throw the exception here. The interface does not
                            // offer a way to add/remove respective listeners.
                        } catch (com.sun.star.lang.DisposedException ed) {
                            // can be disposed if user closed document via UI
                        }
        
                        com.sun.star.frame.XController xOldController = null;
                        if (this.aFrame != null) {
                            xOldController = this.aFrame.getController();
                        }
        
                        try {
        
                            if (this.aFrame != null && xOldController != null) {
                                if (xOldController.suspend(true) == false) {
                                    throw new com.sun.star.util.CloseVetoException("Dokument is still being used and cannot be closed.",
                                            this);
                                }
                            }
        
                        } catch (java.lang.IllegalStateException exp) {
                        }
                    }
        
                    // Load the document.
                    com.sun.star.beans.PropertyValue aArgs[] = addArgument(aArguments,
                            new com.sun.star.beans.PropertyValue("MacroExecutionMode",
                                    -1,
                                    new Short(com.sun.star.document.MacroExecMode.USE_CONFIG),
                                    com.sun.star.beans.PropertyState.DIRECT_VALUE));
                    com.sun.star.lang.XComponent xComponent;
                    xComponent = callLoadComponentFromUrl(aURL, xLoader, aArgs);
        
                    // nothing loaded?
                    if (xComponent == null && this.aDocument != null) {
                        // reactivate old document
                        if (this.aFrame != null && this.aFrame.getController() != null) {
                            this.aFrame.getController().suspend(false);
                        }
                        this.aDocument.setModified(true);
        
                        // throw exception
                        throw new java.io.IOException("Can not load a document: \"" + aURL + "\"");
                    }
        
                    // Get document's XModifiable interface if any.
                    this.aDocument = new OfficeDocument(UnoRuntime.queryInterface(com.sun.star.frame.XModel.class,
                            xComponent));
                    bLoaded = true;
                } catch (NoConnectionException aExc) {
                    aExc.printStackTrace();
                    // stop, clear and retry
                    stopOOoConnection();
                } catch (com.sun.star.lang.DisposedException aExc) {
                    // stop, clear and retry
                    stopOOoConnection();
                } catch (com.sun.star.uno.Exception aExc) {
                    // TDB: handling failure in createInstance
                    throw new java.io.IOException(aExc);
                }
        
                aCallWatchThread.cancel();
                if (this.xServiceFactory == null) {
                    throw new NoConnectionException();
                }
            }
        
            if (this.iConnection == null) {
                throw new NoConnectionException();
            }
        
            setToolVisible("private:resource/toolbar/viewerbar", false, true);
            setStandardBarVisible(false);
            // applyToolVisibilities();
        } catch (java.lang.InterruptedException aExc) {
            throw new NoConnectionException(aExc);
        }
    }

    /**
     * Loads a document from a Java stream.
     * 
     * See loadFromURL() for further information.
     * @throws com.sun.star.util.CloseVetoException if the open document refused to close.
     * 
     * @param iInStream input stream containing the document.
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws java.io.IOException if an IO error occurs reading the resource.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     */
    @objid ("7634e8f6-43ea-4a7a-87e5-9d45c0078af0")
    public void loadFromStream(final java.io.InputStream iInStream, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, NoConnectionException, com.sun.star.util.CloseVetoException {
        // wrap Java stream into UNO stream
        /*
         * com.sun.star.io.XInputStream xStream =
         * new com.sun.star.lib.uno.adapter.InputStreamToXInputStreamAdapter(
         * iInStream );
         */
        // copy stream....
        
        int s = 4096;
        int r = 0, n = 0;
        byte[] buffer = new byte[s];
        byte[] newBuffer = null;
        while ((r = iInStream.read(buffer, n, buffer.length - n)) > 0) {
            n += r;
            if (iInStream.available() > buffer.length - n) {
                newBuffer = new byte[buffer.length * 2];
                System.arraycopy(buffer, 0, newBuffer, 0, n);
                buffer = newBuffer;
            }
        }
        if (buffer.length != n) {
            newBuffer = new byte[n];
            System.arraycopy(buffer, 0, newBuffer, 0, n);
            buffer = newBuffer;
        }
        com.sun.star.io.XInputStream xStream = new com.sun.star.lib.uno.adapter.ByteArrayToXInputStreamAdapter(buffer);
        
        // add stream to arguments
        com.sun.star.beans.PropertyValue[] aExtendedArguments = addArgument(aArguments,
                new com.sun.star.beans.PropertyValue("InputStream",
                        -1,
                        xStream,
                        com.sun.star.beans.PropertyState.DIRECT_VALUE));
        
        // call normal load method
        loadFromURL("private:stream", aExtendedArguments);
    }

    /**
     * Loads a document from a byte array.
     * 
     * See loadFromURL() for further information.
     * @throws com.sun.star.util.CloseVetoException if the open document refused to close.
     * 
     * @param aInBuffer the buffer containing the document.
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws java.io.IOException if an IO error occurs reading the resource.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     */
    @objid ("264828f1-d580-4745-a5d2-4618a8f8528e")
    public void loadFromByteArray(final byte[] aInBuffer, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, NoConnectionException, com.sun.star.util.CloseVetoException {
        // wrap byte arrray into UNO stream
        com.sun.star.io.XInputStream xStream = new com.sun.star.lib.uno.adapter.ByteArrayToXInputStreamAdapter(aInBuffer);
        
        // add stream to arguments
        com.sun.star.beans.PropertyValue[] aExtendedArguments = addArgument(aArguments,
                new com.sun.star.beans.PropertyValue("InputStream",
                        -1,
                        xStream,
                        com.sun.star.beans.PropertyState.DIRECT_VALUE));
        
        // call normal load method
        loadFromURL("private:stream", aExtendedArguments);
    }

    /**
     * Stores a document to the given URL.
     * <p>
     * Due a bug (50651) calling this method may cause the office to crash, when at the same time the office writes a
     * backup of the document. This bug also affects {@link #storeToByteArray storeToByteArray} and
     * {@link #storeToStream storeToStream}. The workaround is to start the office with the option -norestore, which
     * disables the automatic backup and recovery mechanism. OOoBean offers currently no supported way of providing
     * startup options for OOo. But it is possible to set a Java property when starting Java, which is examined by
     * OOoBean:
     * 
     * <pre>
     * java -Dcom.sun.star.officebean.Options=-norestore  ...
     * </pre>
     * 
     * It is planned to offer a way of specifying startup options in a future version. The property can be used until
     * then. When using this property only one option can be provided.
     * 
     * @param aURL the destination
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
     */
    @objid ("3f84846c-d297-4443-aeca-9227e87e9960")
    public void storeToURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, IllegalArgumentException, NoConnectionException, NoDocumentException {
        // no document available?
        if (this.aDocument == null) {
            throw new NoDocumentException();
        }
        
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "storeToURL");
        try {
        
            final OfficeDocument doc = this.aDocument;
            // store the document
            getUnoCaller().call(() -> {
                try {
                    doc.storeToURL(aURL, aArguments);
                } catch (com.sun.star.task.ErrorCodeIOException e) {
                    throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
                } catch (com.sun.star.io.IOException aExc) {
                    throw new java.io.IOException(aExc);
                }
                return null;
            });
        
        } catch (InvocationTargetException e) {
            throw new IOException(e);
        } finally {
            // end runtime timeout
            aCallWatchThread.cancel();
        }
    }

    /**
     * Stores a document to a stream.
     * 
     * See {@link #storeToURL storeToURL} for further information.
     * @see #storeToURL storeToURL
     * 
     * @param aOutStream the destination stream.
     * @param aArguments arguments
     * @return the same output stream.
     * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
     */
    @objid ("05e4b0f2-22fe-4a45-aa0f-7fa0892bc11c")
    public java.io.OutputStream storeToStream(final java.io.OutputStream aOutStream, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, IllegalArgumentException, NoConnectionException, NoDocumentException {
        // wrap Java stream into UNO stream
        com.sun.star.lib.uno.adapter.OutputStreamToXOutputStreamAdapter aStream = new com.sun.star.lib.uno.adapter.OutputStreamToXOutputStreamAdapter(aOutStream);
        
        // add stream to arguments
        com.sun.star.beans.PropertyValue[] aExtendedArguments = addArgument(aArguments,
                new com.sun.star.beans.PropertyValue("OutputStream",
                        -1,
                        aStream,
                        com.sun.star.beans.PropertyState.DIRECT_VALUE));
        
        // call normal store method
        storeToURL("private:stream", aExtendedArguments);
        
        // get byte array from document stream
        try {
            aStream.closeOutput();
        } catch (com.sun.star.io.NotConnectedException aExc) { /* TDB */
        } catch (com.sun.star.io.BufferSizeExceededException aExc) { /* TDB */
        } catch (com.sun.star.io.IOException aExc) {
            throw new java.io.IOException(aExc);
        }
        return aOutStream;
    }

    /**
     * Stores a document to a byte array.
     * 
     * See {@link #storeToURL storeToURL} for further information.
     * @see #storeToURL storeToURL
     * 
     * @param aOutBuffer the byte array.
     * @param aArguments some arguments
     * @return the buffer containing the saved document, should be 'aOutBuffer'.
     * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
     */
    @objid ("17c7107d-ae7a-4cb6-a33e-09799995fc3c")
    public byte[] storeToByteArray(final byte[] aOutBuffer, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, IllegalArgumentException, NoConnectionException, NoDocumentException {
        // wrap byte arrray into UNO stream
        com.sun.star.lib.uno.adapter.XOutputStreamToByteArrayAdapter aStream = new com.sun.star.lib.uno.adapter.XOutputStreamToByteArrayAdapter(aOutBuffer);
        
        // add stream to arguments
        com.sun.star.beans.PropertyValue[] aExtendedArguments = addArgument(aArguments,
                new com.sun.star.beans.PropertyValue("OutputStream",
                        -1,
                        aStream,
                        com.sun.star.beans.PropertyState.DIRECT_VALUE));
        
        // call normal store method
        storeToURL("private:stream", aExtendedArguments);
        
        // get byte array from document stream
        try {
            aStream.closeOutput();
        } catch (com.sun.star.io.NotConnectedException aExc) { /* TDB */
        } catch (com.sun.star.io.BufferSizeExceededException aExc) { /* TDB */
        } catch (com.sun.star.io.IOException aExc) {
            throw new java.io.IOException();
        }
        return aStream.getBuffer();
    }

    /**
     * @requirement FUNC.BEAN.PROG/0.5
     * @requirement API.SIM.SEAP/0.2
     * returns the <type scope="com::sun::star::frame">Frame</a> of the bean.
     * 
     * @return a Java class which implements all interfaces which the service <type
     * scope="com::sun::star::frame">Frame</a> implements. Thus, methods can be called directly without
     * queryInterface. This feature might be implemented by UNO or explicitely coded.
     * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
     */
    @objid ("eee85576-74cb-43b3-8847-a9629bb42f6f")
    public Frame getFrame() throws NoConnectionException {
        if (this.iConnection == null) {
            throw new NoConnectionException();
        }
        return this.aFrame;
    }

    /**
     * @requirement FUNC.BEAN.PROG/0.5
     * @requirement API.SIM.SEAP/0.2
     * returns the <type scope="com::sun::star::frame::Controller"> of the bean.
     * 
     * @return a Java class which implements all interfaces which the service &lt;type
     * scope="com::sun::star::frame">Controller&lt;/a> implements. Thus, methods can be called directly without
     * queryInterface. This feature might be implemented by UNO or explicitly coded.
     * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
     */
    @objid ("6f71dfa4-3a7d-472e-9fab-0af845432793")
    public Controller getController() throws NoConnectionException {
        if (this.iConnection == null) {
            throw new NoConnectionException();
        }
        if (this.aController == null) {
            this.aController = new Controller(this.aFrame.getController());
        }
        return this.aController;
    }

    /**
     * @requirement FUNC.BEAN.PROG/0.5
     * @requirement FUNC.BEAN.STOR/0.4
     * @requirement FUNC.BEAN.PRNT/0.4
     * @requirement API.SIM.SEAP/0.2
     * returns the <type scope="com::sun::star::document::OfficeDocument"> of the bean.
     * 
     * @return a Java class which implements all interfaces which the service <type
     * scope="com::sun::star::document">OfficeDocument</a> implements. Thus, methods can be called directly
     * without queryInterface. This feature might be implemented by UNO or explicitely coded.
     * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
     */
    @objid ("0e515155-a4df-4334-88a6-ec96fdfb20a9")
    public synchronized OfficeDocument getDocument() throws NoConnectionException {
        if (this.iConnection == null) {
            throw new NoConnectionException();
        }
        return this.aDocument;
    }

    /**
     * Sets visibility of all tool bars known by this OOoBean version.
     * 
     * Initially all tool bars are visible. By hiding all tool bars utilizing this method, it is possible to turn just a
     * subset of tool bars on afterwards, no matter whether all available tool bars are known or not.
     * <p>
     * If an older OOoBean instance is used with a newer OOo instance, some tool bars might not be affected by this
     * method.
     * <p>
     * If no connection is established or no document is loaded, the setting is memorized until a document is loaded.
     * Same is valid when the connection dies within this function call.
     * 
     * @param bVisible the toolbars visibility
     * 
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. For example:
     * 
     * <pre>
     * com.sun.star.beans.XPropertySet xPropSet = (com.sun.star.beans.XPropertySet) UnoRuntime.queryInterface(com.sun.star.beans.XPropertySet.class,
     * aFrame);
     * com.sun.star.frame.XLayoutManager xLayoutManager = (com.sun.star.frame.XLayoutManager) UnoRuntime.queryInterface(com.sun.star.frame.XLayoutManager.class,
     * xPropSet.getPropertyValue(&quot;LayoutManager&quot;));
     * xLayoutManager.showElement(&quot;private:resource/menubar/menubar&quot;);
     * </pre>
     */
    @objid ("fb77f96b-b6a6-4b06-b83f-71faeac22a8d")
    @Deprecated
    public void setAllBarsVisible(final boolean bVisible) {
        this.bIgnoreVisibility = true;
        setMenuBarVisible(bVisible);
        setStandardBarVisible(bVisible);
        setToolBarVisible(bVisible);
        setStatusBarVisible(bVisible);
        this.bIgnoreVisibility = false;
    }

    /**
     * --------------------------------------------------------------------------
     * Applies all tool visibilities to the real thing.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible setAllBarsVisible}.
     */
    @objid ("e322e59b-839f-47e9-b770-f2e7704cedb4")
    @Deprecated
    protected void applyToolVisibilities() {
        this.bIgnoreVisibility = true;
        setMenuBarVisible(this.bMenuBarVisible);
        setStandardBarVisible(this.bStandardBarVisible);
        setToolBarVisible(this.bToolBarVisible);
        setStatusBarVisible(this.bStatusBarVisible);
        this.bIgnoreVisibility = false;
    }

    /**
     * Helper method to set tool bar visibility.
     * @param aProperty property to change
     * 
     * @param aResourceURL property owner
     * @param bOldValue old visibility ?
     * @param bNewValue If false, the tool bar is disabled, If true, the tool bar is visible.
     * @return the new value
     * @throws java.lang.InterruptedException if OpenOffice crashed
     * @deprecated Clients should use the service {@link com.sun.star.frame.XLayoutManager}, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("805d7566-1c11-4505-8b12-a5c8a3a6168f")
    @Deprecated
    protected boolean setToolVisible(final String aResourceURL, final boolean bOldValue, final boolean bNewValue) throws InterruptedException {
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "setToolVisible");
        
        // Does a frame exist?
        if (this.aFrame != null) {
            if (this.bIgnoreVisibility || bOldValue != bNewValue) {
        
                try {
                    final Frame frame = this.aFrame;
                    getUnoCaller().call(() -> {
                        try {
                            com.sun.star.beans.XPropertySet xPropSet = UnoRuntime.queryInterface(com.sun.star.beans.XPropertySet.class,
                                    frame);
                            com.sun.star.frame.XLayoutManager xLayoutManager = UnoRuntime.queryInterface(com.sun.star.frame.XLayoutManager.class,
                                    xPropSet.getPropertyValue("LayoutManager"));
                            if (bNewValue) {
                                if (xLayoutManager.getElement(aResourceURL) == null) {
                                    xLayoutManager.createElement(aResourceURL);
                                }
        
                                xLayoutManager.showElement(aResourceURL);
                                xLayoutManager.doLayout();
                            } else {
                                xLayoutManager.hideElement(aResourceURL);
                            }
                        } catch (com.sun.star.beans.UnknownPropertyException aExc1) {
                            throw new RuntimeException("No layout manager found in the frame.", aExc1);
                        } catch (com.sun.star.lang.WrappedTargetException aExc2) {
                            throw new RuntimeException("Error finding layout manager:" + aExc2.getMessage(), aExc2);
                        }
                        return null;
                    });
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e.getCause().getLocalizedMessage(), e);
                }
        
                // notify change
                getDisplay().syncExec(
                        () -> getParent().layout(new Control[] { SwtWinOOoBean.this }));
        
            }
        }
        
        // end runtime timeout
        aCallWatchThread.cancel();
        
        // the new value will be stored by caller
        return bNewValue;
    }

    /**
     * Sets the visibility of the menu bar.
     * 
     * Initially the menu bar is visible.
     * <p>
     * If not connected or no document loaded, the value is stored and automatically applied to the document after it is
     * loaded. Same is valid when the connection dies within this function call.
     * 
     * @param bVisible If false, the menu bar is disabled, If true, the menu bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("01f75e61-d702-45b0-8ea8-65fa2388a300")
    @Deprecated
    public void setMenuBarVisible(final boolean bVisible) {
        try {
            this.bMenuBarVisible = setToolVisible("private:resource/menubar/menubar",
                    this.bMenuBarVisible,
                    bVisible);
        } catch (java.lang.InterruptedException aExc) {
            this.bMenuBarVisible = bVisible;
        }
    }

    /**
     * Returns the visibility of the menu bar.
     * 
     * This method works independently from a connetion or loaded document. If no connection is established or no
     * document is loaded, this method just returns a memorized status.
     * 
     * @return True if the menu bar is visible, false if the menu bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("96284580-01bf-4667-b6ce-38c53be40f9e")
    @Deprecated
    public boolean isMenuBarVisible() {
        return this.bMenuBarVisible;
    }

    /**
     * Sets the main function bar visibilty.
     * 
     * Initially the standard bar is visible.
     * 
     * If not connected or no document loaded, the value is stored and automatically applied to the document after it is
     * loaded. Same is valid when the connection dies within this function call.
     * 
     * @param bVisible If false, the main function bar is disabled, If true, the main function bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("77099e28-484f-44f5-b154-306b62466889")
    @Deprecated
    public void setStandardBarVisible(final boolean bVisible) {
        try {
            this.bStandardBarVisible = setToolVisible("private:resource/toolbar/standardbar",
                    this.bStandardBarVisible,
                    bVisible);
        } catch (java.lang.InterruptedException aExc) {
            this.bMenuBarVisible = bVisible;
        }
    }

    /**
     * Returns the visibility of the main function bar.
     * 
     * This method works independently from a connetion or loaded document. If no connection is established or no
     * document is loaded, this method just returns a memorized status.
     * 
     * @return True if the main function bar is visible, false if the main function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("221f9d21-d4ee-4d1f-b9b2-186cf5d00d91")
    @Deprecated
    public boolean isStandardBarVisible() {
        return this.bStandardBarVisible;
    }

    /**
     * Sets the tool function bar visibilty.
     * 
     * Initially the tool bar is visible.
     * 
     * If not connected or no document loaded, the value is stored and automatically applied to the document after it is
     * loaded. Same is valid when the connection dies within this function call.
     * 
     * @param bVisible If false, the tool function bar is disabled, If true, the tool function bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("a338a5e2-3cf0-4083-ab6f-c6164a732a8f")
    @Deprecated
    public void setToolBarVisible(final boolean bVisible) {
        try {
            this.bToolBarVisible = setToolVisible("private:resource/toolbar/toolbar",
                    this.bToolBarVisible,
                    bVisible);
        } catch (java.lang.InterruptedException aExc) {
            this.bMenuBarVisible = bVisible;
        }
    }

    /**
     * Returns the visibility of the tool function bar.
     * 
     * This method works independently from a connetion or loaded document. If no connection is established or no
     * document is loaded, this method just returns a memorized status.
     * 
     * @return True if the tool function bar is visible, false if the tool function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("a5465e2c-989d-447f-bfdf-69dcd1797105")
    @Deprecated
    public boolean isToolBarVisible() {
        return this.bToolBarVisible;
    }

    /**
     * Sets the status function bar visibilty.
     * 
     * Initially the status bar is visible.
     * 
     * If not connected or no document loaded, the value is stored and automatically applied to the document after it is
     * loaded. Same is valid when the connection dies within this function call.
     * 
     * @param bVisible If false, the status function bar is disabled, If true, the status function bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("861cbf7d-bc37-472b-9dd2-ae588dcb6f33")
    @Deprecated
    public void setStatusBarVisible(final boolean bVisible) {
        try {
            this.bStatusBarVisible = setToolVisible("private:resource/statusbar/statusbar",
                    this.bStatusBarVisible,
                    bVisible);
        } catch (java.lang.InterruptedException aExc) {
            this.bMenuBarVisible = bVisible;
        }
    }

    /**
     * Returns the visibility of the status function bar.
     * 
     * This method works independently from a connetion or loaded document. If no connection is established or no
     * document is loaded, this method just returns a memorized status.
     * 
     * @return True if the status function bar is visible, false if the status function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("dc176742-8f04-4168-aab2-ae294938d5ae")
    @Deprecated
    public boolean isStatusBarVisible() {
        return this.bStatusBarVisible;
    }

    @objid ("e3c7130e-a3a4-4205-b37a-02338a64c041")
    @Override
    public void drawBackground(final GC gc, final int x, final int y, final int width, final int height, final int offsetX, final int offsetY) {
        // TODO Auto-generated method stub
        // super.drawBackground(gc, x, y, width, height, offsetX, offsetY);
    }

    /**
     * Adds a single argument to an array of arguments.
     * 
     * If the argument by its name is already in aArguments it is exchanged and aArguments is returned.
     * <p>
     * If the argument by its name is not yet in aArguments, a new array is created, aArgument added and the new array
     * returned.
     */
    @objid ("e45b67ab-400a-4561-a8ef-d1745dbd0159")
    @SuppressWarnings ("null")
    protected com.sun.star.beans.PropertyValue[] addArgument(final com.sun.star.beans.PropertyValue[] aArguments, final com.sun.star.beans.PropertyValue aArgument) {
        // get number of current arguments
        int nNumArgs = 0;
        if (aArguments != null) {
            nNumArgs = aArguments.length;
        }
        
        // is new argument already set?
        for (int n = 0; n < nNumArgs; ++n) {
            if (aArguments[n].Name == aArgument.Name) {
                // substitute this argument
                aArguments[n] = aArgument;
        
                // return current array
                return aArguments;
            }
        }
        
        // create extended arguments
        com.sun.star.beans.PropertyValue[] aExtendedArguments = new com.sun.star.beans.PropertyValue[nNumArgs + 1];
        
        // copy current arguments
        for (int n = 0; n < nNumArgs; ++n) {
            aExtendedArguments[n] = aArguments[n];
        }
        
        // add new argument
        aExtendedArguments[nNumArgs] = aArgument;
        
        // return new arguments
        return aExtendedArguments;
    }

    /**
     * Creating a correct File URL that OpenOffice can handle. This is
     * necessary to be platform independent.
     * 
     * @param newfile a file path
     * @return the OpenOffice compatible URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if not connected to OpenOffice
     */
    @objid ("b517c044-348d-4bad-8990-35ffe35adb1f")
    public String createUNOFileURL(final java.io.File newfile) throws NoConnectionException {
        java.net.URL before = null;
        try {
            before = newfile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
        
        XComponentContext xRemoteContext;
        
        try {
            xRemoteContext = getOOoConnection().getComponentContext();
        } catch (java.lang.Throwable aExc) {
            throw new NoConnectionException(aExc);
        }
        if (xRemoteContext == null) {
            throw new NoConnectionException();
        }
        
        // Create a URL, which can be used by UNO
        String myUNOFileURL = com.sun.star.uri.ExternalUriReferenceTranslator
                .create(xRemoteContext).translateToInternal(before.toExternalForm());
        
        if (myUNOFileURL.length() == 0 && newfile.getPath().length() > 0) {
            System.out.println("File URL conversion faild. Filelocation " +
                    "contains illegal characters: " + newfile.getPath());
        }
        return myUNOFileURL;
    }

    /**
     * Stores a document to the given URL.
     * <p>
     * Due a bug (50651) calling this method may cause the office to crash, when at the same time the office writes a
     * backup of the document. This bug also affects {@link #storeToByteArray storeToByteArray} and
     * {@link #storeToStream storeToStream}. The workaround is to start the office with the option -norestore, which
     * disables the automatic backup and recovery mechanism. OOoBean offers currently no supported way of providing
     * startup options for OOo. But it is possible to set a Java property when starting Java, which is examined by
     * OOoBean:
     * 
     * <pre>
     * java -Dcom.sun.star.officebean.Options=-norestore  ...
     * </pre>
     * 
     * It is planned to offer a way of specifying startup options in a future version. The property can be used until
     * then. When using this property only one option can be provided.
     * 
     * @param aURL the destination
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
     */
    @objid ("f13a1dcc-4fd7-4a4c-ae24-3237f168d033")
    public void storeAsURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IOException, IllegalArgumentException, NoConnectionException, NoDocumentException {
        // no document available?
        if (this.aDocument == null) {
            throw new NoDocumentException();
        }
        
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "storeAsURL");
        
        try {
            // store the document
            try {
                this.aDocument.storeAsURL(aURL, aArguments);
            } catch (com.sun.star.task.ErrorCodeIOException e) {
                throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
            } catch (com.sun.star.io.IOException aExc) {
                throw new java.io.IOException(aExc);
            } catch (RuntimeException e) {
                throw new java.io.IOException(e);
            }
        } finally {
            // end runtime timeout
            aCallWatchThread.cancel();
        }
    }

    /**
     * Get the interaction handler able to handle some problems.
     * 
     * @return the interaction handler
     * @throws com.sun.star.comp.beans.NoConnectionException if the specified connection cannot be established
     */
    @objid ("ab6e3af7-74a6-43f5-bf42-eb5661b65b6e")
    public XInteractionHandler getInteractionHandler() throws NoConnectionException {
        // the connection cannot be exchanged
        if (this.iConnection == null) {
            throw new NoConnectionException();
            // XComponentContext xContext = Bootstrap.bootstrap();
        }
        
        com.sun.star.uno.XComponentContext xContext = this.iConnection.getComponentContext();
        com.sun.star.lang.XMultiComponentFactory xMCF = xContext.getServiceManager();
        
        // Get a desktop
        Object desktop;
        try {
            desktop = xMCF.createInstanceWithContext("com.sun.star.frame.Desktop", xContext);
        } catch (com.sun.star.uno.Exception e) {
            throw new NoConnectionException(e);
        }
        return UnoRuntime.queryInterface(XInteractionHandler.class, desktop);
    }

    /**
     * Store the currently edited document.
     * 
     * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
     */
    @objid ("e207e54a-ca93-4dc9-bb30-d02b36af0225")
    public void store() throws IOException, IllegalArgumentException, NoConnectionException, NoDocumentException {
        // no document available?
        if (this.aDocument == null) {
            throw new NoDocumentException();
        }
        
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "storeToURL");
        
        // store the document
        try {
            this.aDocument.store();
        } catch (com.sun.star.task.ErrorCodeIOException e) {
            throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
        } catch (com.sun.star.io.IOException aExc) {
            throw new java.io.IOException(aExc);
        } finally {
            // end runtime timeout
            aCallWatchThread.cancel();
        }
    }

    /**
     * This method causes the office window to be displayed.
     * <p>
     * If no document is loaded and the instance is added to a Java container that is showing, then this method needs
     * not to be called. If later one of the methods {@link #loadFromURL loadFromURL}, {@link #loadFromStream
     * loadFromStream1}, or {@link #loadFromByteArray loadFromByteArray} is called, then the document is automatically
     * displayed.
     * <p>
     * Should one of the load methods have been called before the Java container was showing, then this method needs to
     * be called after the container window was made visible {@link Composite#setVisible(boolean) Composite.setvisible(true)}.
     * <p>
     * Another scenario is that a SwtWinOOoBean contains a document and is removed from a Java container and later added
     * again. Then <code>aquireSystemWindow</code> needs to be called after the container window is displayed.
     * 
     * @throws com.sun.star.comp.beans.SystemWindowException if no system window can be aquired.
     * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
     */
    @objid ("3152d22a-a8f6-43a4-98ba-0a7128af15d4")
    public synchronized void aquireSystemWindow() throws NoConnectionException, SystemWindowException {
        try {
            getDisplay().syncExec(new Runnable() {
        
                @Override
                public void run() {
                    try {
                        _aquireSystemWindow();
                    } catch (NoConnectionException e) {
                        SWT.error(SWT.ERROR_FAILED_EXEC, e);
                    } catch (SystemWindowException e) {
                        SWT.error(SWT.ERROR_FAILED_EXEC, e);
                    }
                }
            });
        } catch (SWTException e) {
            Throwable c = e.getCause();
            if (c instanceof SystemWindowException) {
                throw (SystemWindowException) c;
            } else if (c instanceof NoConnectionException) {
                throw (NoConnectionException) c;
            } else {
                throw e;
            }
        }
    }

    /**
     * @return the XComponentContext.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection to OpenOffice
     */
    @objid ("6d660da6-ecec-4beb-9592-610a2564b664")
    public XComponentContext getComponentContext() throws NoConnectionException {
        return this.getOOoConnection().getComponentContext();
    }

    /**
     * Dispose asynchronously the xFrameWindow and forget it.
     */
    @objid ("aa21a2eb-47b2-439f-b5a1-888f4f48bf99")
    private void clearSwtFrameWindow() {
        final SwtWinOfficeWindow swtWindow = this.swtFrameWindow;
        
        if (swtWindow != null && !swtWindow.isDisposed()) {
            swtWindow.getDisplay().asyncExec(new Runnable() {
        
                @Override
                public void run() {
                    if (!swtWindow.isDisposed()) {
                        swtWindow.dispose();
                    }
                }
            });
        }
        this.swtFrameWindow = null;
    }

    /**
     * Close the document.
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("351848ce-5644-4041-9464-10aabdcc865f")
    public void closeDocument() throws IOException {
        try {
            getUnoCaller().call(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return doCloseDocument();
                }
            });
        } catch (InvocationTargetException e) {
            throw new IOException(e.getCause());
        }
    }

    /**
     * Implementation that closes the document.
     */
    @objid ("b89ebdaa-fd66-4fcc-8c0c-fea70c787552")
    synchronized boolean doCloseDocument() throws CloseVetoException {
        this.aDocument.close(true);
        this.aDocument = null;
        this.aController = null;
        this.aFrame = null;
        clearSwtFrameWindow();
        return true;
    }

    /**
     * Try to terminate the OpenOffice/LibreOffice process.
     * <p>
     * Other EventListeners may prevent it if other documents are open.
     */
    @objid ("ada9fb80-db91-4e7a-9644-77d4d8319dab")
    private boolean tryTerminate() {
        try {
            if (this.xDesktop == null) {
                // dbgPrint("OOO already forgot.");
                return true;
            } else if (this.xDesktop.terminate()) {
                // dbgPrint("OOO terminated.");
                return true;
            } else {
                // dbgPrint("OOO still running.");
                return false;
            }
        } catch (com.sun.star.lang.DisposedException e) {
            dbgPrint("OOO connection already disposed.");
            return false;
        } catch (com.sun.star.uno.RuntimeException e) {
            dbgPrint("OOO connection close failed: " + e.toString());
            return false;
        }
    }

    /**
     * @param aURL
     * @param xLoader
     * @param aArgs
     * @param xComponent
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws IllegalArgumentException
     */
    @objid ("18f37e52-42ed-4a25-a1d3-c0079e874865")
    private com.sun.star.lang.XComponent callLoadComponentFromUrl(final String aURL, final com.sun.star.frame.XComponentLoader xLoader, final com.sun.star.beans.PropertyValue[] aArgs) throws IllegalArgumentException, java.io.IOException {
        try {
            return getUnoCaller().call(() -> {
                com.sun.star.lang.XComponent xComponent;
                try {
                    xComponent = xLoader.loadComponentFromURL(aURL, /* aFrame.getName() */
                            "_self",
                            0,
                            aArgs);
                } catch (com.sun.star.lang.IllegalArgumentException e1) {
                    if (e1.getMessage().contains("URL seems to be an unsupported one.") ||
                            e1.getMessage().contains("Unsupported URL")) {
                        throw new IllegalArgumentException("'" + aURL + "' URL is not supported.", e1);
                    } else {
                        throw new java.io.IOException(e1);
                    }
                } catch (com.sun.star.io.IOException e2) {
                    throw new java.io.IOException(e2);
                }
        
                return xComponent;
            });
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw (IOException) cause;
            } else if (cause instanceof java.lang.RuntimeException) {
                throw (java.lang.RuntimeException) cause;
            } else {
                throw new IOException(cause);
            }
        }
    }

    /**
     * Create the OOO XFrame.
     * @throws com.sun.star.uno.Exception
     * @throws NoConnectionException
     */
    @objid ("bda5485d-26ff-46d9-8d0d-6d8e6c78c27c")
    private void initXFrame() throws NoConnectionException, com.sun.star.uno.Exception {
        // create the frame
        com.sun.star.awt.XWindow xWindow = UnoRuntime.queryInterface(com.sun.star.awt.XWindow.class,
                this.swtFrameWindow.getUNOWindowPeer());
        Object xFrame = this.xServiceFactory.createInstance("com.sun.star.frame.Frame");
        this.aFrame = new Frame(UnoRuntime.queryInterface(com.sun.star.frame.XFrame.class,
                xFrame));
        this.aFrame.initialize(xWindow);
        this.aFrame.setName(this.aFrame.toString());
        
        // register the frame at the desktop
        com.sun.star.frame.XFrames xFrames = (UnoRuntime.queryInterface(com.sun.star.frame.XFramesSupplier.class,
                getOOoDesktop())).getFrames();
        xFrames.append(this.aFrame);
    }

    /**
     * Initialize the SWTOfficeWindow.
     * @throws NoConnectionException
     */
    @objid ("74e8313f-7f10-4677-9cf7-cb719b9d52e1")
    private void initSWTOfficeWindow() throws NoConnectionException {
        // Establish the connection by request of the ServiceFactory.
        getMultiServiceFactory();
        
        // remove existing child windows
        getDisplay().syncExec(() -> {
            for (Control c : getChildren()) {
                c.dispose();
            }
        
            // Create the OfficeWindow.
            try {
                this.swtFrameWindow = new SwtWinOfficeWindow(getOOoConnection(), SwtWinOOoBean.this, getUnoCaller());
            } catch (NoConnectionException e) {
                DisposedException e2 = new com.sun.star.lang.DisposedException();
                e2.initCause(e);
                throw e2;
            }
        });
    }

    /**
     * Get the LibreOffice synchronization object .
     * 
     * @return the UNO synchronization object.
     */
    @objid ("952c1cc2-00b2-409e-81b6-aa41fe71ac61")
    public UnoCaller getUnoCaller() {
        if (this.unoCaller == null) {
            try {
                this.unoCaller = new UnoCaller(getComponentContext());
            } catch (NoConnectionException e) {
                throw new IllegalStateException(e);
            }
        }
        return this.unoCaller;
    }

// ===========================================================================
// Helper Methods / Internal Methods
// ---------------------------------------------------------------------------
    /**
     * ===========================================================================
     * Helper Classes
     * ---------------------------------------------------------------------------
     * Helper class to listen on the connection to learn when it dies.
     * 
     * @internal
     */
    @objid ("876ccfb0-849e-4223-87e5-d0d5f586025d")
    private class EventListener extends Thread implements com.sun.star.frame.XTerminateListener {
        @objid ("58d8cc66-599e-4f74-afc2-56514b421489")
         String aTag;

        @objid ("02de6f1d-1eb6-43c6-ab4d-ebc0832a2ee9")
        @SuppressWarnings ("synthetic-access")
        EventListener(final String aTag) throws NoConnectionException {
            super(aTag);
            
            // init members
            this.aTag = aTag;
            
            // listen on a dying connection
            SwtWinOOoBean.this.iConnection.addEventListener(this);
            
            // listen on a terminating OOo
            getOOoDesktop().addTerminateListener(this);
            
            // start this thread as a daemon
            setDaemon(true);
            start();
        }

        @objid ("6ffd3e0b-90cb-4245-aa01-43e8f39f8a7a")
        @SuppressWarnings ("synthetic-access")
        public void end() {
            dbgPrint("EventListener(" + this.aTag + ").end()");
            
            // do not listen on a dying connection anymore
            try {
                SwtWinOOoBean.this.iConnection.removeEventListener(this);
            } catch (Throwable aExc) {
                // ignore
            }
            
            // do not listen on a terminating OOo anymore
            CallWatchThread aCallWatchThread = new CallWatchThread(SwtWinOOoBean.this.nOOoCallTimeOut, "EventListener(" + this.aTag + ").end()");
            try {
                getOOoDesktop().removeTerminateListener(this);
            } catch (Throwable aExc) {
                // ignore
            } finally {
                aCallWatchThread.cancel();
            }
            
            // stop thread
            this.interrupt();
        }

        /**
         * / gets called when the connection dies
         */
        @objid ("97627b79-22f5-4d73-bb0d-2cd3429dcb62")
        @Override
        public void disposing(final com.sun.star.lang.EventObject Source) {
            dbgPrint("EventListener(" + this.aTag + ").disposing()");
            // empty the OOoBean and cut the connection
            stopOOoConnection();
        }

        /**
         * / gets called when the user wants to terminate OOo
         */
        @objid ("64a4c131-5619-40fe-88d8-9e780067b780")
        @Override
        public void queryTermination(final com.sun.star.lang.EventObject Event) throws com.sun.star.frame.TerminationVetoException {
            // disallow termination of OOo while a OOoBean exists
            throw new com.sun.star.frame.TerminationVetoException();
        }

        /**
         * / gets called when OOo terminates
         */
        @objid ("bf83c42a-ff02-4be3-9edf-6d6db024b761")
        @Override
        public void notifyTermination(final com.sun.star.lang.EventObject Event) {
            dbgPrint("EventListener(" + this.aTag + ").notifyTermination()");
            // empty the OOoBean and cut the connection
            stopOOoConnection();
        }

        /**
         * watching the connection
         */
        @objid ("0484e044-02ca-49e9-8196-572aca0785fa")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void run() {
            dbgPrint("EventListener(" + this.aTag + ").run()");
            
            // remote call might hang => watch try
            CallWatchThread aCallWatchThread = new CallWatchThread(SwtWinOOoBean.this.nOOoCallTimeOut,
                    "EventListener(" + this.aTag + ")");
            
            // continue to trying to connect the OOo instance
            long n = 0;
            while (isInterrupted() == false &&
                    SwtWinOOoBean.this.iConnection != null &&
                    SwtWinOOoBean.this.iConnection.getComponentContext() != null) {
                // dbgPrint("EventListener(" + this.aTag + ").running() #" + ++n);
            
                // still alive?
                try {
                    // an arbitrary (but cheap) call into OOo
                    @SuppressWarnings ("unused")
                    XMultiComponentFactory xServiceManager = SwtWinOOoBean.this.iConnection.getComponentContext().getServiceManager();
            
                    // Test the document is alive
                    /*
                     * try {
                     * OfficeDocument doc = SwtWinOOoBean.this.getDocument();
                     * if (doc != null)
                     * doc.isModified();
                     * } catch (NoConnectionException e) {
                     * // Ignore
                     * }
                     */
            
                    // call successfully performed, restart watch for next loop
                    try {
                        aCallWatchThread.restart();
                    } catch (java.lang.InterruptedException aExc) {
                        // ignore late interrupt
                    }
                } catch (java.lang.RuntimeException aExc) {
                    dbgPrint("EventListener(" + this.aTag + ").running() #" + n + ": hung!");
                    aExc.printStackTrace();
                    // hung
                    OfficeConnection iDeadConn = SwtWinOOoBean.this.iConnection;
                    SwtWinOOoBean.this.iConnection = null;
                    iDeadConn.dispose();
                }
            
                // sleep
                try {
                    sleep(SwtWinOOoBean.this.nOOoCheckCycle);
                } catch (java.lang.InterruptedException aExc) {
                    dbgPrint("EventListener(" + this.aTag + ") interupted.");
                    // thread can be ended by EvendListener.end();
                    break;
                }
            }
            dbgPrint("EventListener(" + this.aTag + ").run() finished");
        }

    }

}
