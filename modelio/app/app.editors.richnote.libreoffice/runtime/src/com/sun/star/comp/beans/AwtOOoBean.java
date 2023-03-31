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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.task.XInteractionHandler;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

/**
 * This is the basic JavaBean for all OOo application modules.
 * @since OOo 2.0.0
 */
@objid ("17e975ae-b331-463d-88e3-ef6f894c7a01")
public class AwtOOoBean extends java.awt.Container implements java.io.Externalizable {
    /**
     * timeout values (milli secs)
     */
    @objid ("768169f5-c1cb-4a8a-9337-addd55f97ba4")
    int nOOoStartTimeOut = 60000;

    @objid ("0dc5e8c8-e7db-4da1-9bd6-9629219630f2")
    int nOOoCallTimeOut = 3000;

    @objid ("8552a2a4-65c0-4d0e-91fa-df21c7ec9e78")
    int nOOoCheckCycle = 1000;

    /**
     * properties
     */
    @objid ("9ae1b102-94c9-40f1-af50-2124b9dc2d05")
    private boolean bIgnoreVisibility = false; // to show even if already visible
    

    @objid ("e20d966c-6f4f-466f-9213-c57c40c187b0")
    private boolean bMenuBarVisible = true;

    @objid ("d8fa0ce2-1ae3-433a-a9b3-856c79ae2f5a")
    private boolean bStandardBarVisible = true;

    @objid ("03d88054-a9ad-4e6d-8f21-47d8b582a185")
    private boolean bToolBarVisible = true;

    @objid ("0fe6138f-f6e9-435f-8cd8-d6075b3689ca")
    private boolean bStatusBarVisible = true;

    /**
     * application environment
     */
    @objid ("a71a6e52-5ca5-4733-bf4c-7f998331b097")
    private transient com.sun.star.lang.XMultiServiceFactory xServiceFactory;

    @objid ("23ae15cc-aa75-4455-9bad-2912aa8bfbf9")
    private transient com.sun.star.frame.XDesktop xDesktop;

    /**
     * slot command execution environment
     */
    @objid ("72af6621-4c28-4f0b-aaf6-7e2647c04154")
    private transient com.sun.star.frame.XDispatchProvider xDispatcher;

    @objid ("d938970c-f117-4138-86b1-c2832665217b")
    private transient com.sun.star.util.XURLTransformer xURLTransformer;

    /**
     * This member contains the connection to an OOo instance if established.
     */
    @objid ("b5806d99-091e-45dd-8b8e-5fff753f857f")
    private transient OfficeConnection iConnection;

    @objid ("8273865d-db9d-459a-ba6e-93176e551ca5")
    private transient EventListener xConnectionListener;

    /**
     * @requirement FUNC.BEAN.VIEW/0.4
     * @requirement FUNC.BEAN.EDIT/0.4
     * This member contains the OOo window
     * if a connection is established.
     * It is a child of the OOoBean canvas.
     */
    @objid ("288f7193-05dc-45bf-8127-fa1e5c0a38e1")
    private OfficeWindow xFrameWindow;

    /**
     * document and frame
     */
    @objid ("5ca75964-f791-45d1-a4d8-eb737d3335e1")
    private transient Frame aFrame;

    @objid ("84c65c0d-f77c-421f-99d6-f7248f5adf9b")
    private transient Controller aController;

    @objid ("b099c795-a7b5-42bb-bc5c-3e084b5d5c1a")
    private transient OfficeDocument aDocument;

    /**
     * debugging method
     * @param aMessage message to print
     */
    @objid ("e8e76374-865f-4d90-a44f-b6edc0a33aeb")
    void dbgPrint(final String aMessage) {
        // System.err.println( "OOoBean: " + aMessage );
    }

    /**
     * @requirement FUNC.PER/0.2
     * @internal
     * @deprecated
     */
    @objid ("82741291-944b-44dc-8627-48bb71241308")
    @Override
    @Deprecated
    public void writeExternal(final java.io.ObjectOutput aObjOut) {
        // TBD
    }

    /**
     * @requirement FUNC.PER/0.2
     * @internal
     * @deprecated
     */
    @objid ("bf437680-d4f7-45e7-8d4e-7b8607524425")
    @Override
    @Deprecated
    public void readExternal(final java.io.ObjectInput aObjIn) {
        // TBD
    }

    /**
     * Generic constructor of the OOoBean.
     * 
     * Neither a connection is established nor any document loaded.
     */
    @objid ("1c00784c-5089-4b85-b7a8-905ece88b02f")
    public  AwtOOoBean() {
        String libFileName = System.mapLibraryName("officebean");
        URL url = getClass().getClassLoader().getResource(libFileName);
        if (url == null) {
            throw new UnsatisfiedLinkError(libFileName + " library not found.");
        }
        try {
            // toto
            System.load(new java.io.File(new URI("file", url.getPath(), null)).getAbsolutePath());
        } catch (URISyntaxException e) {
            UnsatisfiedLinkError e2 = new UnsatisfiedLinkError(e.getMessage());
            e2.initCause(e);
            throw e2;
        }
        
    }

    /**
     * @requirement FUNC.CON.MULT/0.3
     * Constructor for an OOoBean which uses a specific office connection.
     * 
     * The connection must be established but no document is loaded.
     * @param iConnection a specific office connection.
     * @throws NoConnectionException if the connection is not established.
     * @deprecated Clients could use the getOOoConnection to obtain an OfficeConnection and use it as argument in a
     * constructor for another OOoBean instance. Calling the dispose method of the OfficeConnection or the
     * OOoBean's stopOOoConnection method would make all instances of OOoBean stop working.
     */
    @objid ("e04bcca3-1a70-4555-9f34-2b2c79fff2e8")
    @Deprecated
    public  AwtOOoBean(final OfficeConnection iConnection) throws NoConnectionException {
        try {
            setOOoConnection(iConnection);
        } catch (HasConnectionException aExc) { /* impossible here */
        }
        
    }

    /**
     * Sets the timeout for methods which launch OOo in milli seconds.
     * 
     * This method does not need a connection to an OOo instance.
     * @param nMilliSecs the timeout
     */
    @objid ("d926496b-108b-4489-9af5-5bba0e788d4b")
    public void setOOoStartTimeOut(final int nMilliSecs) {
        this.nOOoStartTimeOut = nMilliSecs;
    }

    /**
     * Sets the timeout for normal OOO methods calls in milli seconds.
     * 
     * This method does not need a connection to an OOo instance.
     * @param nMilliSecs the timeout
     */
    @objid ("ccaca015-5883-4dc6-ba88-eadd5cf38171")
    public void setOOoCallTimeOut(final int nMilliSecs) {
        this.nOOoCallTimeOut = nMilliSecs;
    }

    /**
     * Sets the period length in milli seconds to check the OOo connection.
     * 
     * This method does not need a connection to an OOo instance.
     * @param nMilliSecs the timeout
     */
    @objid ("8d851c33-9e42-4847-bd11-fed11ecfaca8")
    public void setOOoCheckCycle(final int nMilliSecs) {
        this.nOOoCheckCycle = nMilliSecs;
    }

    /**
     * Sets the a connection to an OOo instance.
     * @internal
     */
    @objid ("131a17c9-b5f6-4852-af46-dba0d2cd6702")
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
        this.xConnectionListener = this.new EventListener("setOOoConnection");
        
    }

    /**
     * @requirement FUNC.CON.STRT/0.4
     * Starts a connection to an OOo instance which is launched if not running.
     * @param aConnectionURL a connection URL
     * @throws MalformedURLException if the URL is invalid.
     * @throws HasConnectionException if a connection was already established.
     * @throws NoConnectionException if the specified connection cannot be established
     */
    @objid ("ece2aa10-4ccb-403d-ab57-7c525189e171")
    public void startOOoConnection(final String aConnectionURL) throws MalformedURLException, HasConnectionException, NoConnectionException {
        // create a new connection from the given connection URL
        LocalOfficeConnection aConnection = new LocalOfficeConnection();
        aConnection.setUnoUrl(aConnectionURL);
        setOOoConnection(aConnection);
        
    }

    /**
     * Returns true if this OOoBean is connected to an OOo instance, false otherwise.
     * @requirement FUNC.CON.CHK/0.7
     * @return <code>true</code> if this OOoBean is connected to an OOo instance, false otherwise
     * @deprecated This method is not useful in a multithreaded environment. Then all threads accessing the instance
     * would have to be synchronized in order to make is method work. It is better to call OOoBean's methods
     * and be prepared to catch a NoConnectionException.
     */
    @objid ("0c2b8a60-7b8f-4c80-8473-4d7ebdd48c29")
    @Deprecated
    public boolean isOOoConnected() {
        return this.iConnection != null;
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
    @objid ("c8386647-46eb-47a9-8024-322bfcd652bd")
    public synchronized void stopOOoConnection() {
        // clear OOo document, frame etc.
        clear();
        
        // cut the connection
        OfficeConnection iExConnection = this.iConnection;
        if (this.iConnection != null) {
            if (this.xConnectionListener != null) {
                this.xConnectionListener.end();
            }
            this.iConnection = null;
            iExConnection.dispose();
        }
        
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
     * @return a connection to an OOo instance.
     * @throws NoConnectionException if no connection can be established
     * @requirement FUNC.CON.STOP/0.4 (via XComponent.dispose())
     * @requirement FUNC.CON.NTFY/0.4 (via XComponent.addEventListener())
     */
    @objid ("4be4f239-3455-45e0-9add-37f0efa121af")
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
     * @return the service factory used by this OOoBean instance.
     * @throws NoConnectionException if no connection is established and no default connection can be established.
     */
    @objid ("3731d64f-7878-4a79-b912-a716297bb577")
    public synchronized com.sun.star.lang.XMultiServiceFactory getMultiServiceFactory() throws NoConnectionException {
        if (this.xServiceFactory == null) {
            // avoid concurrent access from multiple threads
            final OfficeConnection iConn = getOOoConnection();
        
            Thread aConnectorThread = new Thread() {
                @SuppressWarnings ("synthetic-access")
                @Override
                public void run() {
                    com.sun.star.lang.XMultiComponentFactory aFactory = iConn.getComponentContext()
                            .getServiceManager();
                    AwtOOoBean.this.xServiceFactory = UnoRuntime.queryInterface(com.sun.star.lang.XMultiServiceFactory.class,
                            aFactory);
                }
            };
        
            aConnectorThread.start();
            try {
                aConnectorThread.join(this.nOOoStartTimeOut);
            } catch (java.lang.InterruptedException aExc) {
                throw new NoConnectionException("Time out while asking for the LibreOffice service factory.");
            }
            if (this.xServiceFactory == null) {
                throw new NoConnectionException("No service factory found");
            }
        }
        return this.xServiceFactory;
    }

    /**
     * Returns the XDesktop interface of the OOo instance used by this OOoBean.
     * @return the XDesktop interface
     * @throws NoConnectionException if no connection is established and no default connection can be established.
     */
    @objid ("585ed468-b401-462e-8eae-c99a0abaedd4")
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
     * Resets this bean to an empty document.
     * 
     * If a document is loaded and the content modified, the changes are dismissed. Otherwise nothing happens.
     * 
     * This method is intended to be overridden in derived classes. This implementation simply calls clear.
     * @param bClearStateToo Not only the document content but also the state of the bean, like visibility of child components is
     * cleared.
     * @deprecated There is currently no way to dismiss changes, except for loading of the unchanged initial document.
     * Furthermore it is unclear how derived classes handle this and what exactly their state is (e.g. what
     * members make up their state). Calling this method on a derived class requires knowledge about their
     * implementation. Therefore a deriving class should declare their own clearDocument if needed. Clients
     * should call the clearDocument of the deriving class or {@link #clear} which discards the currently
     * displayed document.
     */
    @objid ("38a60369-4d1c-4d9d-b5ab-b405f501602a")
    @Deprecated
    public synchronized void clearDocument(final boolean bClearStateToo) {
        // TBD
        clear();
        
    }

    /**
     * Resets the OOoBean to an empty status.
     * 
     * Any loaded document is unloaded, no matter whether it is modified or not. After calling this method, the OOoBean
     * has no office document and no frame anymore. The connection will stay, though.
     * 
     * This method works with or without an established connection.
     */
    @objid ("32e0ac2e-d3c8-4d47-95e8-f0dd7459eac1")
    public synchronized void clear() {
        dbgPrint("clear()");
        
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "clear");
        try {
            // By closing the frame we avoid that dialogs are displayed, for example when
            // the document is modified.
            com.sun.star.util.XCloseable xCloseable = UnoRuntime.queryInterface(com.sun.star.util.XCloseable.class,
                    this.aFrame);
            if (xCloseable != null) {
                try {
                    xCloseable.close(true);
                } catch (com.sun.star.util.CloseVetoException exc) { // a print job may be running
                }
            }
        
            this.aDocument = null;
            this.xDispatcher = null;
            this.aFrame = null;
        
            // clear xFrameWindow
            if (this.xFrameWindow != null) {
                try {
                    releaseSystemWindow();
                } catch (NoConnectionException aExc) {
                } // ignore
                catch (SystemWindowException aExc) {
                } // ignore
                remove(this.xFrameWindow.getAWTComponent());
                this.xFrameWindow = null;
            }
        
            // clear xURTTransformer
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
        
            this.xDesktop = null;
            this.xServiceFactory = null;
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
     * @throws SystemWindowException if no system window can be aquired.
     * @throws NoConnectionException if the connection is not established.
     */
    @objid ("d9ec8b8c-77f9-4279-9f3d-c972dea9d146")
    public synchronized void aquireSystemWindow() throws SystemWindowException, NoConnectionException {
        if (this.iConnection == null) {
            throw new NoConnectionException();
        }
        if (!isShowing()) {
            throw new SystemWindowException();
        }
        
        if (this.xFrameWindow != null) {
            this.xFrameWindow.getAWTComponent().setVisible(true);
        }
        doLayout();
        
    }

    /**
     * This method must be called when the OOoBean before the system window may be released by it's parent AWT/Swing
     * component.
     * <p>
     * This is the case when java.awt.Component.isDisplayable() returns true. This is definitely the case when the
     * OOoBean is removed from it's parent container.
     * @requirement FUNC.PAR.RWL/0.4
     * @estimation 16h
     * @throws SystemWindowException if system window is not acquired.
     * @throws NoConnectionException if the connection is not established.
     * @deprecated When {@link java.awt.Component#removeNotify() Component.removeNotify()} of the parent window of the actual office window is called, then the
     * actions are performed for which this method needed to be called previously.
     */
    @objid ("25a07cb8-6dc3-4baa-88fc-d7b50befcfdc")
    @Deprecated
    public synchronized void releaseSystemWindow() throws SystemWindowException, NoConnectionException {
        if (this.iConnection == null) {
            throw new NoConnectionException();
        }
        
        try {
            this.xFrameWindow.getAWTComponent().setVisible(false);
        } catch (com.sun.star.lang.DisposedException aExc) {
            throw new NoConnectionException();
        }
        
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
     * @param aURL document URL
     * @param aArguments loading arguments
     * @throws IOException if an IO error occurs reading the resource specified by the URL.
     * @requirement FUNC.BEAN.LOAD/0.4
     * @requirement FUNC.CON.AUTO/0.3
     * @throws NoConnectionException if no connection can be established.
     */
    @objid ("b47c3b76-fc91-44ed-a32b-4bcf8bdd7735")
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
                    if (this.xFrameWindow == null) {
                        // Establish the connection by request of the ServiceFactory.
                        getMultiServiceFactory();
        
                        // remove existing child windows
                        removeAll();
        
                        // Create the OfficeWindow.
        
                        this.xFrameWindow = new LocalOfficeWindow(getOOoConnection());
                        add(this.xFrameWindow.getAWTComponent());
                    }
        
                    // create the document frame from UNO window.
                    if (this.aFrame == null) {
                        // create the frame
                        com.sun.star.awt.XWindow xWindow = UnoRuntime.queryInterface(com.sun.star.awt.XWindow.class,
                                this.xFrameWindow.getUNOWindowPeer());
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
        
                    // load the document.
                    com.sun.star.beans.PropertyValue aArgs[] = addArgument(aArguments,
                            new com.sun.star.beans.PropertyValue("MacroExecutionMode",
                                    -1,
                                    new Short(com.sun.star.document.MacroExecMode.USE_CONFIG),
                                    com.sun.star.beans.PropertyState.DIRECT_VALUE));
                    // String fn = aFRame.getName();
                    com.sun.star.lang.XComponent xComponent;
                    try {
                        xComponent = xLoader.loadComponentFromURL(aURL, /* aFrame.getName() */
                                "_self",
                                0,
                                aArgs);
                    } catch (com.sun.star.lang.IllegalArgumentException e) {
                        if (e.getMessage().contains("URL seems to be an unsupported one.")) {
                            throw new IllegalArgumentException("'" + aURL + "' URL is not supported.", e);
                        } else {
                            throw e;
                        }
                    }
        
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
                    // mDocumentURL = aURL; TBD: still needed?
        
                    // Get document's XModifiable interface if any.
                    this.aDocument = new OfficeDocument(UnoRuntime.queryInterface(com.sun.star.frame.XModel.class,
                            xComponent));
                    bLoaded = true;
                } catch (NoConnectionException aExc) {
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
        
            applyToolVisibilities();
        } catch (java.lang.InterruptedException aExc) {
            throw new NoConnectionException();
        }
        
    }

    /**
     * Loads a document from a Java stream.
     * 
     * See loadFromURL() for further information.
     * @throws com.sun.star.util.CloseVetoException if the open document refused to close.
     * @param iInStream input stream containing the document.
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws IOException if an IO error occurs reading the resource.
     * @throws NoConnectionException if no connection is established.
     */
    @objid ("bac526fd-75a4-4110-8a9f-372d4f82aa84")
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
     * @param aInBuffer the buffer containing the document.
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws IOException if an IO error occurs reading the resource.
     * @throws NoConnectionException if no connection is established.
     */
    @objid ("0e039bfe-ecb2-435a-adc6-544ceeda26c0")
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
     * @param aURL the destination
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws IOException if an IO error occurs reading the resource specified by the URL.
     * @throws NoConnectionException if no connection is established.
     * @throws NoDocumentException if no document is loaded
     */
    @objid ("9cd020f9-c18a-41d5-bc88-b7d6492bba0f")
    public void storeToURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
        // no document available?
        if (this.aDocument == null) {
            throw new NoDocumentException();
        }
        
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "storeToURL");
        
        try {
            // store the document
            try {
                this.aDocument.storeToURL(aURL, aArguments);
            } catch (com.sun.star.task.ErrorCodeIOException e) {
                throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
            } catch (com.sun.star.io.IOException aExc) {
                throw new java.io.IOException(aExc);
            }
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
     * @param aOutStream the destination stream.
     * @param aArguments arguments
     * @return the same output stream.
     * @throws IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws IOException if an IO error occurs reading the resource specified by the URL.
     * @throws NoConnectionException if no connection is established.
     * @throws NoDocumentException if no document is loaded
     */
    @objid ("ab9755e8-59bd-47aa-b3fb-fcc08bd749c4")
    public java.io.OutputStream storeToStream(final java.io.OutputStream aOutStream, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
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
     * @param aOutBuffer the byte array.
     * @param aArguments some arguments
     * @return the buffer containing the saved document, should be 'aOutBuffer'.
     * @throws IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws IOException if an IO error occurs reading the resource specified by the URL.
     * @throws NoConnectionException if no connection is established.
     * @throws NoDocumentException if no document is loaded
     */
    @objid ("33b0a3dc-b3a7-4387-b239-6fb79ad2d36d")
    public byte[] storeToByteArray(final byte[] aOutBuffer, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
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
     * @return a Java class which implements all interfaces which the service <type
     * scope="com::sun::star::frame">Frame</a> implements. Thus, methods can be called directly without
     * queryInterface. This feature might be implemented by UNO or explicitely coded.
     * @throws NoConnectionException if the connection is not established.
     */
    @objid ("b040f1be-d7ac-4816-909b-177a3bc5ad35")
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
     * @return a Java class which implements all interfaces which the service &lt;type
     * scope="com::sun::star::frame">Controller&lt;/a> implements. Thus, methods can be called directly without
     * queryInterface. This feature might be implemented by UNO or explicitly coded.
     * @throws NoConnectionException if the connection is not established.
     */
    @objid ("659a991b-16df-4cd1-b4ec-39f1d070b7cd")
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
     * @return a Java class which implements all interfaces which the service <type
     * scope="com::sun::star::document">OfficeDocument</a> implements. Thus, methods can be called directly
     * without queryInterface. This feature might be implemented by UNO or explicitely coded.
     * @throws NoConnectionException if the connection is not established.
     */
    @objid ("49f1156c-8573-44a9-8610-56e0e2529813")
    public OfficeDocument getDocument() throws NoConnectionException {
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
    @objid ("b486659a-f48f-4229-b3bc-4004008794ad")
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
    @objid ("af09049e-515e-4e6a-ba16-c8908265e0c0")
    @Deprecated
    protected void applyToolVisibilities() throws InterruptedException {
        this.bIgnoreVisibility = true;
        setMenuBarVisible(this.bMenuBarVisible);
        setStandardBarVisible(this.bStandardBarVisible);
        setToolBarVisible(this.bToolBarVisible);
        setStatusBarVisible(this.bStatusBarVisible);
        this.bIgnoreVisibility = false;
        
    }

    /**
     * Helper method to set tool bar visibility.
     * @param aProperty
     * @param aResourceURL
     * @param bOldValue
     * @param bNewValue If false, the tool bar is disabled, If true, the tool bar is visible.
     * @return
     * @throws InterruptedException @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("abeada57-3942-4365-948e-244888aeb76d")
    @Deprecated
    protected boolean setToolVisible(final String aProperty, final String aResourceURL, final boolean bOldValue, final boolean bNewValue) throws InterruptedException {
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "setToolVisible");
        
        // Does a frame exist?
        if (this.aFrame != null) {
            if (this.bIgnoreVisibility || bOldValue != bNewValue) {
                try {
                    com.sun.star.beans.XPropertySet xPropSet = UnoRuntime.queryInterface(com.sun.star.beans.XPropertySet.class,
                            this.aFrame);
                    com.sun.star.frame.XLayoutManager xLayoutManager = UnoRuntime.queryInterface(com.sun.star.frame.XLayoutManager.class,
                            xPropSet.getPropertyValue("LayoutManager"));
                    if (bNewValue) {
                        xLayoutManager.showElement(aResourceURL);
                    } else {
                        xLayoutManager.hideElement(aResourceURL);
                    }
                } catch (com.sun.star.beans.UnknownPropertyException aExc) {
                    throw new RuntimeException("not layout manager found");
                } catch (com.sun.star.lang.WrappedTargetException aExc) {
                    throw new RuntimeException("not layout manager found");
                }
        
                // notify change
                firePropertyChange(aProperty, new Boolean(bOldValue), new Boolean(bNewValue));
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
     * @param bVisible If false, the menu bar is disabled, If true, the menu bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("f3011cb7-b0d8-4b85-ac60-22c2a37bb081")
    @Deprecated
    public void setMenuBarVisible(final boolean bVisible) {
        try {
            this.bMenuBarVisible = setToolVisible("MenuBarVisible",
                    "private:resource/menubar/menubar",
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
     * @return True if the menu bar is visible, false if the menu bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("364bc6d4-c0d3-42a3-a22b-50eb0f60e6de")
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
     * @param bVisible If false, the main function bar is disabled, If true, the main function bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("1fc030c6-919f-4c56-bc6b-c5bfcbb183aa")
    @Deprecated
    public void setStandardBarVisible(final boolean bVisible) {
        try {
            this.bStandardBarVisible = setToolVisible("StandardBarVisible",
                    "private:resource/toolbar/standardbar",
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
     * @return True if the main function bar is visible, false if the main function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("c87651e3-1b62-42af-9adc-7524d8d59e7f")
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
     * @param bVisible If false, the tool function bar is disabled, If true, the tool function bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("4fb93453-fdcd-41db-a9ec-475b7045fcf3")
    @Deprecated
    public void setToolBarVisible(final boolean bVisible) {
        try {
            this.bToolBarVisible = setToolVisible("ToolBarVisible",
                    "private:resource/toolbar/toolbar",
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
     * @return True if the tool function bar is visible, false if the tool function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("08cab576-0365-4513-bc45-c68760cdacbe")
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
     * @param bVisible If false, the status function bar is disabled, If true, the status function bar is visible.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("70ddd32f-a601-4e4f-b7f5-d4e798ad8008")
    @Deprecated
    public void setStatusBarVisible(final boolean bVisible) {
        try {
            this.bStatusBarVisible = setToolVisible("StatusBarVisible",
                    "private:resource/statusbar/statusbar",
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
     * @return True if the status function bar is visible, false if the status function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("4edeb085-e0c0-4dd2-8db5-5d3235fefed5")
    @Deprecated
    public boolean isStatusBarVisible() {
        return this.bStatusBarVisible;
    }

    @objid ("465bb2b8-21f2-4cde-a614-aba0fea6e39f")
    @Override
    public void paint(final java.awt.Graphics aGraphics) {
        
    }

    /**
     * Adds a single argument to an array of arguments.
     * 
     * If the argument by its name is already in aArguments it is exchanged and aArguments is returned.
     * <p>
     * If the argument by its name is not yet in aArguments, a new array is created, aArgument added and the new array
     * returned.
     */
    @objid ("7bedeb62-f8d3-4f36-ab16-baf76eff50c4")
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
     * @param newfile a file path
     * @return the OpenOffice compatible URL.
     * @throws NoConnectionException if not connected to OpenOffice
     */
    @objid ("c98dfa1e-8694-4306-bf64-cbfa13bc2a18")
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
     * @param aURL the destination
     * @param aArguments MediaDescriptor properties. see <a href="http://api.openoffice.org/docs/common/ref/com/sun/star/document/MediaDescriptor.html#CharacterSet">MediaDescriptor documentation</a>
     * @throws IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws IOException if an IO error occurs reading the resource specified by the URL.
     * @throws NoConnectionException if no connection is established.
     * @throws NoDocumentException if no document is loaded
     */
    @objid ("d18eb12c-7cfe-4c61-9896-fe5f00b7dc46")
    public void storeAsURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
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
     * @return the interaction handler
     * @throws NoConnectionException if the specified connection cannot be established
     */
    @objid ("8ae53f38-8988-4d56-9e99-649bf8843ecf")
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
     * @throws IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws IOException if an IO error occurs reading the resource specified by the URL.
     * @throws NoConnectionException if no connection is established.
     * @throws NoDocumentException if no document is loaded
     */
    @objid ("f1f04e9e-d6f7-4760-a14c-c87bba5e1cfc")
    public void store() throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
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

{
            setLayout(new java.awt.BorderLayout());
        }
    
    /**
     * ===========================================================================
     * Helper Classes
     * ---------------------------------------------------------------------------
     * Helper class to listen on the connection to learn when it dies.
     * 
     * @internal
     */
    @objid ("7e34e713-be79-4bf8-971f-9d7db93e70d3")
    private class EventListener extends Thread implements com.sun.star.frame.XTerminateListener {
        @objid ("043ed3c0-5ba5-4876-8869-9e69b6837fe8")
        String aTag;

        @objid ("2be0e0b6-aa4b-4e38-9c70-11fba8ae7603")
        @SuppressWarnings ("synthetic-access")
         EventListener(final String aTag) throws NoConnectionException {
            // init members
            this.aTag = aTag;
            
            // listen on a dying connection
            AwtOOoBean.this.iConnection.addEventListener(this);
            
            // listen on a terminating OOo
            getOOoDesktop().addTerminateListener(this);
            
            // start this thread as a daemon
            setDaemon(true);
            start();
            
        }

        @objid ("47ded82b-ddb7-4157-987b-b4443759d2d8")
        @SuppressWarnings ("synthetic-access")
        public void end() {
            // do not listen on a dying connection anymore
            try {
                AwtOOoBean.this.iConnection.removeEventListener(this);
            } catch (Throwable aExc) {
            }
            
            // do not listen on a terminating OOo anymore
            try {
                getOOoDesktop().removeTerminateListener(this);
            } catch (Throwable aExc) {
            }
            
            // stop thread
            this.interrupt();
            
        }

        /**
         * / gets called when the connection dies
         */
        @objid ("28ad5e5f-113f-4d54-8846-2fb6483bc43d")
        @Override
        public void disposing(final com.sun.star.lang.EventObject Source) {
            // empty the OOoBean and cut the connection
            stopOOoConnection();
            
        }

        /**
         * / gets called when the user wants to terminate OOo
         */
        @objid ("7b313a59-0eb4-4219-8438-28928d25788f")
        @Override
        public void queryTermination(final com.sun.star.lang.EventObject Event) throws com.sun.star.frame.TerminationVetoException {
            // disallow termination of OOo while a OOoBean exists
            throw new com.sun.star.frame.TerminationVetoException();
            
        }

        /**
         * / gets called when OOo terminates
         */
        @objid ("809e8d42-444e-4c53-b73a-e4b51b8afa14")
        @Override
        public void notifyTermination(final com.sun.star.lang.EventObject Event) {
            // empty the OOoBean and cut the connection
            stopOOoConnection();
            
        }

        /**
         * watching the connection
         */
        @objid ("b485e97d-050c-48aa-9a15-9c6f36d9474e")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void run() {
            dbgPrint("EventListener(" + this.aTag + ").run()");
            
            // remote call might hang => watch try
            CallWatchThread aCallWatchThread = new CallWatchThread(AwtOOoBean.this.nOOoCallTimeOut,
                    "EventListener(" + this.aTag + ")");
            
            // continue to trying to connect the OOo instance
            long n = 0;
            while (isInterrupted() == false &&
                    AwtOOoBean.this.iConnection != null &&
                    AwtOOoBean.this.iConnection.getComponentContext() != null) {
                dbgPrint("EventListener(" + this.aTag + ").running() #" + ++n);
            
                // still alive?
                com.sun.star.lang.XMultiComponentFactory xServiceManager = null;
                try {
                    // an arbitrary (but cheap) call into OOo
                    xServiceManager = AwtOOoBean.this.iConnection.getComponentContext().getServiceManager();
            
                    // call successfully performed, restart watch for next loop
                    try {
                        aCallWatchThread.restart();
                    } catch (java.lang.InterruptedException aExc) {
                        // ignore late interrupt
                    }
                } catch (java.lang.RuntimeException aExc) {
                    // hung
                    OfficeConnection iDeadConn = AwtOOoBean.this.iConnection;
                    AwtOOoBean.this.iConnection = null;
                    iDeadConn.dispose();
                }
            
                // sleep
                try {
                    sleep(AwtOOoBean.this.nOOoCheckCycle);
                } catch (java.lang.InterruptedException aExc) {
                    dbgPrint("EventListener(" + this.aTag + ") interupted.");
                    // thread can be ended by EvendListener.end();
                    break;
                }
            }
            
        }

    }

}
