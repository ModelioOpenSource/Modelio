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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.task.XInteractionHandler;
import com.sun.star.uno.RuntimeException;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.CloseVetoException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;

/**
 * @requirement FUNC.PERF.LRN/0.6
 * @requirement FUNC.PERF.LOC/0.6
 * @requirement FUNC.PERF.FIX/0.6
 * This is the basic JavaBean for all OOo application modules.
 * 
 * @requirement FUNC.RES.OTH/0.2 No other resources are needed yet.
 * @since OOo 2.0.0
 */
@objid ("cbc569a5-a14f-46e5-b60f-57ca13e74eb2")
@SuppressWarnings ("unused")
public class SwtLinuxOOoBean extends Composite {
    /**
     * timeout values (milli secs)
     */
    @objid ("d1a2ac43-8a44-4cd2-88d8-a9573e5ca5e6")
     int nOOoStartTimeOut = 60000;

    @objid ("df3dbe6c-42af-44f2-b30c-f73d9367e33b")
     int nOOoCallTimeOut = 3000;

    @objid ("abbb48cd-3e9c-410a-ade7-c2bb1c805bcc")
     int nOOoCheckCycle = 2000;

    /**
     * properties
     */
    @objid ("2131b039-e0dc-41dc-95fa-c119c70e36b1")
    private boolean bIgnoreVisibility = false; // to show even if already visible

    @objid ("817baf8a-ee0d-43d7-b818-04a84c3b5b92")
    private boolean bMenuBarVisible = true;

    @objid ("d451d7ba-09da-4568-9f75-113d28dcbb8d")
    private boolean bStandardBarVisible = true;

    @objid ("05df07d5-afaa-45be-bb01-f3734de67b45")
    private boolean bToolBarVisible = true;

    @objid ("2960f13b-3969-41d2-b7bc-90019072d0dd")
    private boolean bStatusBarVisible = true;

    /**
     * application environment
     */
    @objid ("59c0f959-e202-4827-8a8b-49114222da95")
    private transient com.sun.star.lang.XMultiServiceFactory xServiceFactory;

    @objid ("05583ebe-023e-4df9-8043-d3846d990265")
    private transient com.sun.star.frame.XDesktop xDesktop;

    @objid ("94ef0c28-762c-4ca4-8935-9b8c5836cc19")
    private transient com.sun.star.util.XURLTransformer xURLTransformer;

    /**
     * This member contains the connection to an OOo instance if established.
     */
    @objid ("99cd1705-c5ae-4ff8-880b-24eb75c9d624")
    private transient OfficeConnection iConnection;

    @objid ("610716a3-7717-4965-8ff8-34f79ae38b53")
    private transient EventListener xConnectionListener;

    /**
     * @requirement FUNC.BEAN.VIEW/0.4
     * @requirement FUNC.BEAN.EDIT/0.4
     * This member contains the OOo window
     * if a connection is established.
     * It is a child of the OOoBean canvas.
     */
    @objid ("5929b88f-30da-4562-8ac3-6bcecc011902")
    private SwtLinuxOfficeWindow swtFrameWindow;

    /**
     * document and frame
     */
    @objid ("62937860-55e9-4522-91e3-56687fb885d8")
    private transient Frame aFrame;

    @objid ("0c16c053-92bc-43dd-b9a1-fa645442d95a")
    private transient Controller aController;

    @objid ("3a8346d8-65a3-4e17-8357-7e7444351cd7")
    private transient OfficeDocument aDocument;

    /**
     * debugging method
     * 
     * @param aMessage message to print
     */
    @objid ("6c919523-361c-49dc-a38c-b8463dbdec9d")
    void dbgPrint(final String aMessage) {
        LibreOfficeEditors.LOG.debug("SwtOOoBean: " + aMessage);
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
    @objid ("130df596-4afa-42da-972d-347b2a4b0e76")
    public SwtLinuxOOoBean(final Composite parent, final int style) {
        super(parent, style);
        setLayout(new FillLayout());
        
        // Note: Don't redefine dispose(), it is never called.
        addDisposeListener(new DisposeListener() {
        
            @Override
            public void widgetDisposed(DisposeEvent e) {
                stopOOoConnection();
            }
        });
        
        try {
            // Establish the connection by requesting the ServiceFactory.
            getMultiServiceFactory();
        
            // get window from OOo on demand
            initSWTOfficeWindow();
        
            // create the document frame from UNO window.
            // initXFrame();
        } catch (NoConnectionException e) {
            throw (SWTException) new SWTException(e.getMessage()).initCause(e);
            // } catch (com.sun.star.uno.Exception e) {
            // throw (SWTException) new SWTException(e.getMessage()).initCause(e);
        }
    }

    /**
     * Sets the timeout for methods which launch OOo in milli seconds.
     * 
     * This method does not need a connection to an OOo instance.
     * 
     * @param nMilliSecs the timeout
     */
    @objid ("74356d01-cabe-4772-9a72-988e05396438")
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
    @objid ("d089e006-7360-492e-9387-dc11bfedd672")
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
    @objid ("a30ebf1b-208a-47d8-968f-74212ef1b514")
    public void setOOoCheckCycle(final int nMilliSecs) {
        this.nOOoCheckCycle = nMilliSecs;
    }

    /**
     * Sets the a connection to an OOo instance.
     * @internal
     */
    @objid ("5f3e7c44-a595-4fed-865b-e3d80327ffc4")
    private synchronized void initConnection() throws NoConnectionException {
        // the connection cannot be exchanged
        if (this.iConnection != null) {
            throw new IllegalStateException("There is already a connection.");
        }
        
        OfficeConnection iNewConnection = new LocalOfficeConnection();
        
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
    @objid ("85d932fe-3a20-45d6-9fb9-32d39f6da7e4")
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
    @objid ("01270dc6-89aa-4436-bf81-bdd8c96c6d8a")
    public synchronized OfficeConnection getOOoConnection() throws NoConnectionException {
        if (this.iConnection == null) {
            initConnection();
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
    @objid ("9cffd3b6-4c5d-4cce-a91d-6bab973f440b")
    public synchronized XMultiServiceFactory getMultiServiceFactory() throws NoConnectionException {
        if (this.xServiceFactory == null) {
            // avoid concurrent access from multiple threads
            final OfficeConnection iConn = getOOoConnection();
        
            try {
                this.xServiceFactory = CompletableFuture
                        .supplyAsync(() -> {
                            XMultiComponentFactory aFactory = iConn.getComponentContext().getServiceManager();
                            return UnoRuntime.queryInterface(XMultiServiceFactory.class, aFactory);
        
                        })
                        .get(this.nOOoStartTimeOut, TimeUnit.MILLISECONDS);
            } catch (java.lang.InterruptedException e) {
                throw new NoConnectionException("Interruption while asking for the LibreOffice service factory.", e);
            } catch (TimeoutException e) {
                throw new NoConnectionException("Time out while asking for the LibreOffice service factory.", e);
            } catch (ExecutionException e) {
                throw new NoConnectionException(e);
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
    @objid ("3ec13de6-6c66-4161-80d0-0ebd2b1340b9")
    public synchronized com.sun.star.frame.XDesktop getOOoDesktop() throws NoConnectionException {
        if (this.xDesktop == null) {
            try {
                Object aObject = getMultiServiceFactory().createInstance("com.sun.star.frame.Desktop");
                this.xDesktop = UnoRuntime.queryInterface(com.sun.star.frame.XDesktop.class, aObject);
            } catch (com.sun.star.uno.Exception aExc) {
                // TBD: what if no connection exists?
            }
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
    @objid ("73c7f0d2-c047-4b34-a930-3cca44404c0c")
    private synchronized void clear() {
        dbgPrint("clear()");
        
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "clear");
        try {
            // By closing the frame we avoid that dialogs are displayed, for example when
            // the document is modified.
            try {
                final com.sun.star.util.XCloseable xCloseable = UnoRuntime.queryInterface(com.sun.star.util.XCloseable.class,
                        this.aFrame);
                if (xCloseable != null) {
                    xCloseable.close(true);
                }
            } catch (com.sun.star.util.CloseVetoException exc) {
                // a print job may be running
            } catch (com.sun.star.lang.DisposedException e) {
                // It sometimes happen on Linux when many documents are opens.
            }
        
            this.aDocument = null;
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
                } catch (java.lang.Throwable aExc) {// ignore
                }
                this.xURLTransformer = null;
            }
        
            // this.xServiceFactory = null;
        } finally {
            aCallWatchThread.cancel();
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
     * 
     * @param aURL document URL
     * @param aArguments loading arguments
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection can be established.
     */
    @objid ("07aac08c-161a-474b-b03b-f69f957c2680")
    public void loadFromURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws com.sun.star.util.CloseVetoException, IOException, NoConnectionException {
        dbgPrint("loadFromURL()");
        // try loading
        try {
            boolean bLoaded = false;
            while (!bLoaded) {
                // watch loading in a thread with a timeout (if OOo hangs)
                CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoStartTimeOut * 100, "loadFromURL");
        
                try {
                    initXFrame();
        
                    // Initializes the slot command execution environment.
                    this.xURLTransformer = UnoRuntime.queryInterface(com.sun.star.util.XURLTransformer.class,
                            getMultiServiceFactory().createInstance("com.sun.star.util.URLTransformer"));
        
                    // Setup the call
                    String frameName;
                    com.sun.star.frame.XComponentLoader xLoader;
                    com.sun.star.beans.PropertyValue aArgs[] = aArguments;
                    if (aURL.startsWith("private:")) {
                        frameName = "_blank";
                        aArgs = addArgument(aArgs,
                                new com.sun.star.beans.PropertyValue("Hidden",
                                        -1,
                                        true,
                                        com.sun.star.beans.PropertyState.DIRECT_VALUE));
                        xLoader = UnoRuntime.queryInterface(com.sun.star.frame.XComponentLoader.class,
                                getOOoDesktop());
                    } else {
                        frameName = "_self";
                        // get XComponentLoader from frame
                        xLoader = UnoRuntime.queryInterface(com.sun.star.frame.XComponentLoader.class,
                                this.aFrame);
                    }
        
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
                                    throw new com.sun.star.util.CloseVetoException("Document is still being used and cannot be closed.",
                                            this);
                                }
                            }
        
                        } catch (java.lang.IllegalStateException exp) { // ignore
                        }
                    }
        
                    // Load the document
                    // -----------------
                    aArgs = addArgument(aArgs,
                            new com.sun.star.beans.PropertyValue("MacroExecutionMode",
                                    -1,
                                    new Short(com.sun.star.document.MacroExecMode.USE_CONFIG),
                                    com.sun.star.beans.PropertyState.DIRECT_VALUE));
        
                    com.sun.star.lang.XComponent xLoadedComponent = callLoadComponentFromUrl(aURL, frameName, xLoader, aArgs);
        
                    // Nothing loaded?
                    if (xLoadedComponent == null && this.aDocument != null) {
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
                            xLoadedComponent));
                    bLoaded = true;
                } catch (com.sun.star.lang.DisposedException aExc) {
                    // stop, clear and retry
                    LibreOfficeEditors.LOG.warning(aExc.toString());
                    LibreOfficeEditors.LOG.debug(aExc);
                    stopOOoConnection();
                } catch (com.sun.star.uno.Exception aExc) {
                    // TDB: handling failure in createInstance
                    throw new java.io.IOException(aExc);
                } finally {
                    aCallWatchThread.cancel();
                }
        
                // if (this.xServiceFactory == null)
                // throw new NoConnectionException();
            }
        
            if (this.iConnection == null) {
                throw new NoConnectionException();
            }
        
            setToolVisible("private:resource/toolbar/viewerbar", false, true);
            // setStandardBarVisible(false);
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
    @objid ("4d89ade2-512a-4946-b619-c42a020b0712")
    public void loadFromStream(final java.io.InputStream iInStream, final com.sun.star.beans.PropertyValue[] aArguments) throws com.sun.star.util.CloseVetoException, IOException, NoConnectionException {
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
    @objid ("da81b05e-9dbc-47f4-b336-9c61133c9d97")
    public void loadFromByteArray(final byte[] aInBuffer, final com.sun.star.beans.PropertyValue[] aArguments) throws com.sun.star.util.CloseVetoException, IOException, NoConnectionException {
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
    @objid ("69d5d7f5-c4d2-424c-b73a-924f8f4241d6")
    public void storeToURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
        // no document available?
        if (this.aDocument == null) {
            throw new NoDocumentException();
        }
        
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "storeToURL");
        // store the document
        try {
            this.aDocument.storeToURL(aURL, aArguments);
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
    @objid ("a877a1c5-2b39-4614-ae33-ae1a693ed76f")
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
     * 
     * @param aOutBuffer the byte array.
     * @param aArguments some arguments
     * @return the buffer containing the saved document, should be 'aOutBuffer'.
     * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
     * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
     */
    @objid ("a3832bf6-0f98-4ccc-a296-36c942c77e02")
    public byte[] storeToByteArray(final byte[] aOutBuffer, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
        // wrap byte arrray into UNO stream
        com.sun.star.lib.uno.adapter.XOutputStreamToByteArrayAdapter aStream = new com.sun.star.lib.uno.adapter.XOutputStreamToByteArrayAdapter(aOutBuffer);
        
        // add stream to arguments
        com.sun.star.beans.PropertyValue arg = new com.sun.star.beans.PropertyValue("OutputStream",
                -1,
                aStream,
                com.sun.star.beans.PropertyState.DIRECT_VALUE);
        com.sun.star.beans.PropertyValue[] aExtendedArguments = addArgument(aArguments, arg);
        
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
    @objid ("acff5a23-c399-4ce9-9d51-f8395a96cccc")
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
    @objid ("ed0ed186-06c1-485b-a2ab-970426a91445")
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
    @objid ("9a57c890-e6fa-4222-bccb-633ff1647a75")
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
    @objid ("85cdfb7b-5b67-401b-b9b4-e5e6e5ada845")
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
    @objid ("7ff762f5-389f-4295-9f12-306aa6215cd5")
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
     * 
     * @param aResourceURL property owner
     * @param bOldValue old visibility ?
     * @param bNewValue If false, the tool bar is disabled, If true, the tool bar is visible.
     * @return the new value
     * @throws java.lang.InterruptedException if OpenOffice crashed
     * @deprecated Clients should use the service {@link com.sun.star.frame.XLayoutManager}, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("ba661300-5a89-41d5-8dbc-33fdbd3002a1")
    @Deprecated
    public boolean setToolVisible(final String aResourceURL, final boolean bOldValue, final boolean bNewValue) throws InterruptedException {
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "setToolVisible");
        
        // Does a frame exist?
        if (this.aFrame != null) {
            if (this.bIgnoreVisibility || bOldValue != bNewValue) {
        
                final Frame frame = this.aFrame;
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
        
                    /*
                     * System.out.println("=> "+aResourceURL+"' => "+bNewValue);
                     * for (XUIElement ui : xLayoutManager.getElements()) {
                     * System.out.println("- "+ui.getResourceURL()+"' = "+xLayoutManager.isElementVisible(ui.getResourceURL()));
                     * }
                     * System.out.println("-----------");
                     */
                } catch (com.sun.star.beans.UnknownPropertyException aExc) {
                    throw new RuntimeException("No layout manager found in the frame.", aExc);
                } catch (com.sun.star.lang.WrappedTargetException aExc) {
                    throw new RuntimeException("Error finding layout manager:" + aExc.getMessage(), aExc);
                }
        
                // notify change
                getParent().layout(new Control[] { this });
        
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
    @objid ("31bad753-085a-40ba-9355-71d8e969ade8")
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
    @objid ("006b1daf-7df7-47a5-be48-3147dc9f0007")
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
    @objid ("8e9dd29d-84cf-44a3-ba91-c3d6a17913b8")
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
    @objid ("e90f0074-c79b-49f6-a84a-c2c61a94117a")
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
    @objid ("929ec331-cc5a-484e-b1af-38a80281ad6a")
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
     * This method works independently from a connection or loaded document. If no connection is established or no
     * document is loaded, this method just returns a memorized status.
     * 
     * @return True if the tool function bar is visible, false if the tool function bar is hidden.
     * @deprecated Clients should use the service com.sun.star.frame.LayoutManager, which can be obtained from a frame,
     * to control toolbars. See also {@link #setAllBarsVisible}.
     */
    @objid ("1e28cdd5-a4b2-40cf-8a9f-c3c0d25bf524")
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
    @objid ("c7012ec1-34b6-43e7-b61c-20d1da9c8ba0")
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
    @objid ("f549873d-9397-4a79-b4b7-151060341efe")
    @Deprecated
    public boolean isStatusBarVisible() {
        return this.bStatusBarVisible;
    }

/*
     * @Override
     * public void drawBackground(final GC gc, final int x, final int y, final int width, final int height, final int offsetX, final int offsetY) {
     * // TODO Auto-generated method stub
     * //super.drawBackground(gc, x, y, width, height, offsetX, offsetY);
     * }
     */
    /**
     * Adds a single argument to an array of arguments.
     * 
     * If the argument by its name is already in aArguments it is exchanged and aArguments is returned.
     * <p>
     * If the argument by its name is not yet in aArguments, a new array is created, aArgument added and the new array
     * returned.
     */
    @objid ("9eac3881-9085-4a2e-9cd0-9fca7d8e6a25")
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
    @objid ("bbcf5a66-793e-4b88-a1e4-1b50ab8df020")
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
    @objid ("629c335b-ba52-4850-9cdf-fe4bd2d15288")
    public void storeAsURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws IllegalArgumentException, IOException, NoConnectionException, NoDocumentException {
        // no document available?
        if (this.aDocument == null) {
            throw new NoDocumentException();
        }
        
        // start runtime timeout
        CallWatchThread aCallWatchThread = new CallWatchThread(this.nOOoCallTimeOut, "storeAsURL");
        
        // store the document
        try {
            this.aDocument.storeAsURL(aURL, aArguments);
        } catch (com.sun.star.task.ErrorCodeIOException e) {
            throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
        } catch (com.sun.star.io.IOException aExc) {
            throw new java.io.IOException(aExc);
        } catch (RuntimeException e) {
            throw new java.io.IOException(e);
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
    @objid ("ba0377e2-ce79-4e11-a181-48f21e6745fa")
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
    @objid ("29873458-1712-46df-b96d-655c55c36a51")
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
     * Another scenario is that a SwtOOoBean contains a document and is removed from a Java container and later added
     * again. Then <code>aquireSystemWindow</code> needs to be called after the container window is displayed.
     * 
     * @throws com.sun.star.comp.beans.SystemWindowException if no system window can be aquired.
     * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
     */
    @objid ("8dd64e93-810a-4423-afdc-b41be64abc70")
    public synchronized void aquireSystemWindow() throws SystemWindowException, NoConnectionException {
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
     * @return the XComponentContext.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection to OpenOffice
     */
    @objid ("b7062462-eb52-469e-abd3-808e169e08ff")
    public XComponentContext getComponentContext() throws NoConnectionException {
        return getOOoConnection().getComponentContext();
    }

    /**
     * Dispose asynchronously the xFrameWindow and forget it.
     */
    @objid ("82221da2-61ae-45c1-9b7d-94eaecf9298e")
    private void clearSwtFrameWindow() {
        final SwtLinuxOfficeWindow swtWindow = this.swtFrameWindow;
        
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
    @objid ("26c4ff57-428e-4fd2-8d5f-9a757faa4740")
    public void closeDocument() throws IOException {
        try {
            doCloseDocument();
        } catch (CloseVetoException e) {
            throw new IOException(e.getCause());
        }
    }

    /**
     * Implementation that closes the document.
     */
    @objid ("44adf22f-6561-4189-bd02-55665e0f8ee6")
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
    @objid ("591632b3-ed8d-4ff9-8c39-d8f08aea0da5")
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
        }
    }

    @objid ("d08c51df-9ed5-405b-888c-50a7ffd970b0")
    private com.sun.star.lang.XComponent callLoadComponentFromUrl(final String aURL, final String frameName, final com.sun.star.frame.XComponentLoader xLoader, final com.sun.star.beans.PropertyValue[] aArgs) throws IllegalArgumentException, java.io.IOException {
        try {
            return xLoader.loadComponentFromURL(aURL,
                    frameName,
                    0,
                    aArgs);
        } catch (com.sun.star.io.IOException e) {
            throw new java.io.IOException(e);
        } catch (com.sun.star.lang.IllegalArgumentException c) {
            if (c.getMessage().contains("URL seems to be an unsupported one.") ||
                    c.getMessage().contains("Unsupported URL")) {
                throw new IllegalArgumentException("'" + aURL + "' URL is not supported.", c);
            } else {
                throw new java.io.IOException(c);
            }
        }
    }

    /**
     * Create the OOO XFrame.
     * @throws com.sun.star.uno.Exception if com.sun.star.frame.Frame couldn't be instantiated
     * 
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection
     */
    @objid ("54742821-d680-47ee-a340-6152ea5dbecc")
    private void initXFrame() throws com.sun.star.uno.Exception, NoConnectionException {
        // Ensure SWT frame exists. null happens if closeDocument() is called
        if (this.swtFrameWindow == null) {
            initSWTOfficeWindow();
        }
        
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
        
        this.pack(true);
        this.layout(true);
    }

    /**
     * Initialize the SWTOfficeWindow.
     * 
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established and no default connection can be established.
     */
    @objid ("11496fa0-bd5c-415d-8c16-7d0c423c8854")
    private void initSWTOfficeWindow() throws NoConnectionException {
        // remove existing child windows
        for (Control c : getChildren()) {
            c.dispose();
        }
        
        // Create the OfficeWindow.
        this.swtFrameWindow = new SwtLinuxOfficeWindow(getOOoConnection(), this);
        
        this.pack(true);
        this.layout(true);
        getDisplay().update();
    }

// ===========================================================================
// Helper Methods / Internal Methods
// ---------------------------------------------------------------------------
    /**
     * Helper class to listen on the connection to learn when it dies.
     * 
     * @internal
     */
    @objid ("1615a827-e370-4730-847a-d2a46be8e220")
    private class EventListener extends Thread implements com.sun.star.frame.XTerminateListener {
        @objid ("460778b5-13e9-4de2-a15d-b4098870e66c")
         String aTag;

        @objid ("ddd5f8d3-bca2-4692-8666-27b54d4a4541")
        @SuppressWarnings ("synthetic-access")
        EventListener(final String aTag) throws NoConnectionException {
            super(aTag);
            
            // init members
            this.aTag = aTag;
            
            // listen on a dying connection
            SwtLinuxOOoBean.this.iConnection.addEventListener(this);
            
            // listen on a terminating OOo
            getOOoDesktop().addTerminateListener(this);
            
            // start this thread as a daemon
            setDaemon(true);
            start();
        }

        @objid ("877c0515-56d6-4114-8b98-51d9dcf25fc5")
        @SuppressWarnings ("synthetic-access")
        public void end() {
            dbgPrint("EventListener(" + this.aTag + ").end()");
            
            // do not listen on a dying connection anymore
            try {
                OfficeConnection lConnection = SwtLinuxOOoBean.this.iConnection;
                if (lConnection != null) {
                    lConnection.removeEventListener(this);
                }
            } catch (java.lang.RuntimeException aExc) {
                // ignore
            }
            
            // do not listen on a terminating OOo anymore
            try {
                CallWatchThread aCallWatchThread = new CallWatchThread(SwtLinuxOOoBean.this.nOOoCallTimeOut, "EventListener(" + this.aTag + ").end()");
            
                getOOoDesktop().removeTerminateListener(this);
            
                aCallWatchThread.cancel();
            } catch (Exception aExc) {
                // ignore
            }
            
            // stop thread
            interrupt();
        }

        /**
         * gets called when the connection dies
         */
        @objid ("d0704c67-e1e1-4d35-be3a-b818c95f8aee")
        @Override
        public void disposing(final com.sun.star.lang.EventObject Source) {
            dbgPrint("EventListener(" + this.aTag + ").disposing()");
            // empty the OOoBean and cut the connection
            stopOOoConnection();
        }

        /**
         * gets called when the user wants to terminate OOo
         */
        @objid ("366e08da-82b9-4803-acb8-528219b8f30d")
        @Override
        public void queryTermination(final com.sun.star.lang.EventObject Event) throws com.sun.star.frame.TerminationVetoException {
            // disallow termination of OOo while a OOoBean exists
            throw new com.sun.star.frame.TerminationVetoException();
        }

        /**
         * / gets called when OOo terminates
         */
        @objid ("3ab13826-7c08-480d-8d1b-6e628545dab6")
        @Override
        public void notifyTermination(final com.sun.star.lang.EventObject Event) {
            dbgPrint("EventListener(" + this.aTag + ").notifyTermination()");
            // empty the OOoBean and cut the connection
            stopOOoConnection();
        }

        /**
         * watching the connection
         */
        @objid ("d585abd9-7f32-4dc8-b563-9064c27e2434")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void run() {
            dbgPrint("EventListener(" + this.aTag + ").run()");
            
            // remote call might hang => watch try
            CallWatchThread aCallWatchThread = new CallWatchThread(SwtLinuxOOoBean.this.nOOoCallTimeOut,
                    "EventListener(" + this.aTag + ")");
            
            // continue to trying to connect the OOo instance
            long n = 0;
            while (isInterrupted() == false &&
                    SwtLinuxOOoBean.this.iConnection != null &&
                    SwtLinuxOOoBean.this.iConnection.getComponentContext() != null) {
                // dbgPrint("EventListener(" + this.aTag + ").running() #" + ++n);
            
                // still alive?
                try {
                    // an arbitrary (but cheap) call into OOo
                    @SuppressWarnings ("unused")
                    XMultiComponentFactory xServiceManager = SwtLinuxOOoBean.this.iConnection.getComponentContext().getServiceManager();
            
                    // Test the document is alive
                    /*
                     * try {
                     * OfficeDocument doc = SwtOOoBean.this.getDocument();
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
                    OfficeConnection iDeadConn = SwtLinuxOOoBean.this.iConnection;
                    SwtLinuxOOoBean.this.iConnection = null;
                    iDeadConn.dispose();
                }
            
                // sleep
                try {
                    sleep(SwtLinuxOOoBean.this.nOOoCheckCycle);
                } catch (java.lang.InterruptedException aExc) {
                    dbgPrint("EventListener(" + this.aTag + ") interupted.");
                    // thread can be ended by EvendListener.end();
                    break;
                }
            }
            
            aCallWatchThread.cancel();
            dbgPrint("EventListener(" + this.aTag + ").run() finished");
        }

    }

}
