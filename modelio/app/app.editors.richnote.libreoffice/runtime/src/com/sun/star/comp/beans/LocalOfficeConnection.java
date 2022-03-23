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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.bridge.XBridge;
import com.sun.star.bridge.XBridgeFactory;
import com.sun.star.connection.XConnection;
import com.sun.star.connection.XConnector;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lib.uno.helper.UnoUrl;
import com.sun.star.lib.util.NativeLibraryLoader;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

/**
 * This class represents a connection to the local office application.
 * 
 * @since OOo 2.0.0
 */
@objid ("be62c23e-b4a4-4e86-9ef0-5977a7e7908d")
public class LocalOfficeConnection implements OfficeConnection {
    /**
     * Name of "soffice" executable.
     */
    @objid ("53c3df77-dd85-418f-a2c3-c6be9e2ceb29")
    public static final String OFFICE_APP_NAME = "soffice";

    /**
     * Name of "officebean" .jar file.
     */
    @objid ("daba407e-133d-43ea-bb1e-cc43055e9fef")
    public static final String OFFICE_LIB_NAME = "officebean";

    @objid ("0df702c5-f678-4f24-94da-1a16f3f30660")
    private static final String OFFICE_ID_SUFFIX = "_Office";

    @objid ("eb64366a-673e-4e70-979a-6564701f7874")
    private static String mProgramPath;

    @objid ("18efb4e2-695b-402d-89ed-f8d6d482f6e5")
    private String mURL;

    @objid ("12b5d41d-0bc4-4307-bc1e-2224b9e190ca")
    private String mConnType;

    @objid ("079aeaab-e759-4cdf-9b01-aef1897985b8")
    private String mPipe;

    @objid ("e7ad7021-1ce1-4472-a737-0ab4bb1f81e1")
    private String mPort;

    @objid ("d01d937e-87ad-4d32-87f7-175f90003010")
    private String mProtocol;

    @objid ("d2fe321b-b48f-4552-bcc0-700b9fd67646")
    private String mInitialObject;

    @objid ("550657e1-b976-4435-8bde-564e47bac5c0")
    private XComponentContext mContext;

    @objid ("53886dfe-03f7-45b7-9bee-a2c17a5dcafe")
    private XBridge mBridge;

    @objid ("532905ff-d0cf-48ef-b131-22191de80ee9")
    private List<XEventListener> mComponents = new Vector<>();

    /**
     * The launched OpenOffice process.
     */
    @objid ("a9157531-a037-4b1c-a8a6-43793bdd7fd3")
    private static Process mProcess = null;

    @objid ("b9b6bf4a-bc5b-4274-afbc-2cb5076fb362")
    private static AtomicLong m_nBridgeCounter = new AtomicLong();

    /**
     * Constructor.
     * Sets up paths to the office application and native libraries if
     * values are available in <code>OFFICE_PROP_FILE</code> in the user
     * home directory.<br />
     * "com.sun.star.beans.path" - the office application directory;<br/>
     * "com.sun.star.beans.libpath" - native libraries directory.
     */
    @objid ("5d22d4a0-af5f-46ee-8d29-6f721f227824")
    public  LocalOfficeConnection() {
        // init member vars
        try {
            setUnoUrl("uno:pipe,name=" + getPipeName() + ";urp;StarOffice.ServiceManager");
        } catch (java.net.MalformedURLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        
    }

    /**
     * protected Constructor
     * Initialize a LocalOfficeConnection with an already running office.
     * This C'Tor is only used in complex tests at the moment.
     * @param xContext a XComponentContext
     */
    @objid ("1b84ffce-4d7b-4dcb-ad14-aced630656ee")
    protected  LocalOfficeConnection(final com.sun.star.uno.XComponentContext xContext) {
        this.mContext = xContext;
    }

    /**
     * Sets a connection URL. This implementation accepts a UNO URL with following format:<br />
     * 
     * <pre>
     * url    := uno:localoffice[,&lt;params&gt;];urp;StarOffice.ServiceManager
     * params := &lt;path&gt;[,&lt;pipe&gt;]
     * path   := path=&lt;pathv&gt;
     * pipe   := pipe=&lt;pipev&gt;
     * pathv  := platform_specific_path_to_the_local_office_distribution
     * pipev  := local_office_connection_pipe_name
     * </pre>
     * @param url This is UNO URL which describes the type of a connection.
     * @throws MalformedURLException if the URL is invalid.
     */
    @objid ("d4b009a3-0583-422d-9ed1-d5ebb0a2737d")
    @Override
    public void setUnoUrl(final String url) throws MalformedURLException {
        this.mURL = null;
        
        String prefix = "uno:localoffice";
        if (url.startsWith(prefix)) {
            parseUnoUrlWithOfficePath(url, prefix);
        } else {
            try {
                UnoUrl aURL = UnoUrl.parseUnoUrl(url);
                LocalOfficeConnection.mProgramPath = null;
                this.mConnType = aURL.getConnection();
                this.mPipe = (String) aURL.getConnectionParameters().get("pipe");
                this.mPort = (String) aURL.getConnectionParameters().get("port");
                this.mProtocol = aURL.getProtocol();
                this.mInitialObject = aURL.getRootOid();
            } catch (com.sun.star.lang.IllegalArgumentException eIll) {
                MalformedURLException e2 = new java.net.MalformedURLException(
                        "Invalid UNO connection URL:" + eIll.getLocalizedMessage());
                e2.initCause(eIll);
                throw e2;
            }
        }
        this.mURL = url;
        
    }

    /**
     * Retrieves the UNO component context.
     * <p>
     * Establishes a connection if necessary and initializes the
     * UNO service manager if it has not already been initialized.
     * This method can return <code>null</code> if it fails to connect
     * to the office application.
     * @return The office UNO component context.
     * @deprecated May establishes a new connection if disconnected.
     */
    @objid ("8e5950e2-a314-4951-bc2e-38d6de70cc8d")
    @Deprecated
    @Override
    public synchronized XComponentContext getComponentContext() {
        if (this.mContext == null) {
            try {
                this.mContext = connect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.mContext;
    }

    /**
     * Closes the connection.
     */
    @objid ("abf7f5eb-01b4-4597-a75d-7036a7738a04")
    @Override
    public void dispose() {
        Iterator<XEventListener> itr = this.mComponents.iterator();
        while (itr.hasNext() == true) {
            // ignore runtime exceptions in dispose
            try {
                itr.next().disposing(null);
            } catch (RuntimeException aExc) {
            }
        }
        this.mComponents.clear();
        
        // Terminate the bridge. It turned out that this is necessary for the bean
        // to work properly when displayed in an applet within Internet Explorer.
        // When navigating off the page which is showing the applet and then going
        // back to it, then the Java remote bridge is damaged. That is the Java threads
        // do not work properly anymore. Therefore when Applet.stop is called the connection
        // to the office including the bridge needs to be terminated.
        if (this.mBridge != null) {
            XComponent comp = UnoRuntime.queryInterface(XComponent.class, this.mBridge);
            if (comp != null) {
                comp.dispose();
            } else {
                System.err.println("LocalOfficeConnection: could not dispose bridge!");
            }
        
            this.mBridge = null;
        }
        
        this.mContext = null;
        
    }

    /**
     * Adds an event listener to the object.
     * @param listener is a listener object.
     */
    @objid ("3c316219-6095-4216-a551-d25c74160d84")
    @Override
    public void addEventListener(final XEventListener listener) {
        this.mComponents.add(listener);
    }

    /**
     * Removes an event listener from the listener list.
     * @param listener is a listener object.
     */
    @objid ("c1fce687-8cef-4263-aef3-bbe2c6c9bc62")
    @Override
    public void removeEventListener(final XEventListener listener) {
        this.mComponents.remove(listener);
    }

    /**
     * Establishes the connection to the office.
     * @throws IOException in case of failure.
     */
    @objid ("501347f2-0f98-4e67-806d-ebc9af8d0afc")
    private XComponentContext connect() throws IOException {
        try {
            // create default local component context
            XComponentContext xLocalContext;
            try {
                xLocalContext = com.sun.star.comp.helper.Bootstrap.createInitialComponentContext(null);
            } catch (RuntimeException e) {
                throw e;
            } catch (Error e) {
                throw e;
            } catch (Exception e1) {
                throw new IOException(e1);
            }
        
            // initial serviceManager
            @SuppressWarnings ("unused")
            XMultiComponentFactory xLocalServiceManager = xLocalContext.getServiceManager();
        
            // try to connect to soffice
            Object aInitialObject = null;
            try {
                aInitialObject = resolve(xLocalContext, this.mURL);
            } catch (com.sun.star.connection.NoConnectException e) {
                // Kill non responding OpenOffice
                if (LocalOfficeConnection.mProcess != null) {
                    LocalOfficeConnection.mProcess.destroy();
                }
        
                // launch soffice
                OfficeService aSOffice = new OfficeService();
                aSOffice.startupService();
        
                // wait until soffice is started
                long nMaxMillis = System.currentTimeMillis() + 1000 * aSOffice.getStartupTime();
                while (aInitialObject == null) {
                    try {
                        Thread.sleep(100);
                        // try to connect to soffice
                        aInitialObject = resolve(xLocalContext, this.mURL);
                    } catch (com.sun.star.connection.NoConnectException aEx) {
                        // soffice did not start in time
                        if (System.currentTimeMillis() > nMaxMillis) {
                            throw aEx;
                        }
        
                    }
                }
            }
        
            // XComponentContext
            XPropertySet xPropertySet = UnoRuntime.queryInterface(XPropertySet.class, aInitialObject);
            Object xContext = xPropertySet.getPropertyValue("DefaultContext");
            XComponentContext xComponentContext = UnoRuntime.queryInterface(XComponentContext.class, xContext);
        
            return xComponentContext;
        
        } catch (com.sun.star.connection.NoConnectException e) {
            throw new IOException("Couldn't connect to remote server:\n" + e.getMessage(), e);
        } catch (com.sun.star.connection.ConnectionSetupException e) {
            throw new IOException("Couldn't access necessary local resource to establish the interprocess connection:" + e.getMessage(), e);
        } catch (com.sun.star.lang.IllegalArgumentException e) {
            throw new IOException("uno-url is syntactical illegal ( " + this.mURL + " ):\n" + e.getMessage(), e);
        } catch (com.sun.star.uno.RuntimeException e) {
            throw new IOException(e.getMessage(), e);
        } catch (IOException e) {
            throw e;
        } catch (UnknownPropertyException e) {
            throw new IOException(e.getMessage(), e);
        } catch (WrappedTargetException e) {
            throw new IOException(String.valueOf(e.TargetException), e);
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
        
    }

    /**
     * The function is copied and adapted from the UrlResolver.resolve() .
     * We cannot use the URLResolver because we need
     * access to the bridge which has to be disposed when Applet.stop is called.
     */
    @objid ("fc389dd5-57e1-48b4-939d-948f9127f026")
    private Object resolve(final XComponentContext xLocalContext, final String dcp) throws com.sun.star.connection.ConnectionSetupException, com.sun.star.connection.NoConnectException, com.sun.star.lang.IllegalArgumentException {
        String conDcp = null;
        String protDcp = null;
        String rootOid = null;
        
        if (dcp.indexOf(';') == -1) {// use old style
            conDcp = dcp;
            protDcp = "iiop";
            rootOid = "classic_uno";
        } else { // new style
            String s = dcp;
            int index = s.indexOf(':');
            // String url = dcp.substring(0, index).trim();
            s = s.substring(index + 1).trim();
        
            index = s.indexOf(';');
            conDcp = s.substring(0, index).trim();
            s = s.substring(index + 1).trim();
        
            index = s.indexOf(';');
            protDcp = s.substring(0, index).trim();
            s = s.substring(index + 1).trim();
        
            rootOid = s.trim().trim();
        }
        
        Object rootObject = null;
        XBridgeFactory xBridgeFactory = null;
        
        XMultiComponentFactory xLocalServiceManager = xLocalContext.getServiceManager();
        try {
            xBridgeFactory = UnoRuntime.queryInterface(XBridgeFactory.class,
                    xLocalServiceManager.createInstanceWithContext("com.sun.star.bridge.BridgeFactory", xLocalContext));
        } catch (com.sun.star.uno.Exception e) {
            throw new com.sun.star.uno.RuntimeException(e.getMessage(), e);
        }
        
        synchronized (this) {
            CallWatchThread watcher = new CallWatchThread(3000, "LocalOfficeConnection.resolve()");
            try {
                if (this.mBridge == null) {
                    Object connector = null;
                    try {
                        connector = xLocalServiceManager.createInstanceWithContext("com.sun.star.connection.Connector", xLocalContext);
                    } catch (com.sun.star.uno.Exception e) {
                        throw new com.sun.star.uno.RuntimeException(e.getMessage());
                    }
                    XConnector connector_xConnector = UnoRuntime.queryInterface(XConnector.class, connector);
                    // connect to the server
                    XConnection xConnection = connector_xConnector.connect(conDcp);
                    // create the bridge name. This should not be necessary if we pass an
                    // empty string as bridge name into createBridge. Then we should always get
                    // a new bridge. This does not work because of (i51323). Therefore we
                    // create unique bridge names for the current process.
                    String sBridgeName = "OOoBean_private_bridge_" + String.valueOf(m_nBridgeCounter.getAndIncrement());
                    try {
                        this.mBridge = xBridgeFactory.createBridge(sBridgeName, protDcp, xConnection, null);
                    } catch (com.sun.star.bridge.BridgeExistsException e) {
                        throw new com.sun.star.uno.RuntimeException(e.getMessage());
                    }
                }
        
                rootObject = this.mBridge.getInstance(rootOid);
        
                return rootObject;
            } catch (com.sun.star.uno.RuntimeException e) {
                com.sun.star.connection.NoConnectException e2 = new com.sun.star.connection.NoConnectException(e.getMessage());
                e2.initCause((e.getCause()));
                throw e2;
            } finally {
                watcher.cancel();
            }
        }
        
    }

    /**
     * Retrieves a path to the office program folder.
     * @return The path to the office program folder.
     */
    @objid ("1a1b9711-75c3-4188-8574-239429e18aa8")
    private static String getProgramPath() {
        if (LocalOfficeConnection.mProgramPath == null) {
            // determine name of executable soffice
            String aExec = getProgramName();
        
            // find soffice executable relative to this class's class loader:
            File path = NativeLibraryLoader.getResource(
                    LocalOfficeConnection.class.getClassLoader(), aExec);
            if (path != null) {
                LocalOfficeConnection.mProgramPath = path.getParent();
            }
        
            // default is ""
            if (LocalOfficeConnection.mProgramPath == null) {
                LocalOfficeConnection.mProgramPath = "";
            }
        }
        return LocalOfficeConnection.mProgramPath;
    }

    /**
     * Get the system dependent office program name.
     * @return the office program name.
     */
    @objid ("127239ee-e3ea-4ff3-889c-b4eb1bdfe9b9")
    private static String getProgramName() {
        String aExec = LocalOfficeConnection.OFFICE_APP_NAME; // default for UNIX
        String aOS = System.getProperty("os.name");
        
        // running on Windows?
        if (aOS.startsWith("Windows")) {
            aExec = LocalOfficeConnection.OFFICE_APP_NAME + ".exe";
        }
        
        // add other non-UNIX operating systems here
        // ...
        return aExec;
    }

    /**
     * Parses a connection URL.
     * This method accepts a UNO URL with following format:<br />
     * 
     * <pre>
     * url    := uno:localoffice[,&lt;params&gt;];urp;StarOffice.NamingService
     * params := &lt;path&gt;[,&lt;pipe&gt;]
     * path   := path=&lt;pathv&gt;
     * pipe   := pipe=&lt;pipev&gt;
     * pathv  := platform_specific_path_to_the_local_office_distribution
     * pipev  := local_office_connection_pipe_name
     * </pre>
     * 
     * <h4>Examples</h4>
     * <ul>
     * <li>"uno:localoffice,pipe=xyz_Office,path=/opt/openoffice11/program;urp;StarOffice.ServiceManager";
     * <li>"uno:socket,host=localhost,port=8100;urp;StarOffice.ServiceManager";
     * </ul>
     * @param url This is UNO URL which describes the type of a connection.
     * @exception java.net.MalformedURLException when inappropreate URL was
     * provided.
     */
    @objid ("db1826c8-4e15-4ea6-9018-723e8252ffd2")
    private void parseUnoUrlWithOfficePath(final String url, final String prefix) throws MalformedURLException {
        // Extruct parameters.
        int idx = url.indexOf(";urp;StarOffice.NamingService");
        if (idx < 0) {
            throw new java.net.MalformedURLException(
                    "Invalid UNO connection URL.");
        }
        String params = url.substring(prefix.length(), idx + 1);
        
        // Parse parameters.
        String name = null;
        String path = null;
        String pipe = null;
        char ch;
        int state = 0;
        StringBuffer buffer = new StringBuffer();
        for (idx = 0; idx < params.length(); idx += 1) {
            ch = params.charAt(idx);
            switch (state) {
            case 0: // initial state
                switch (ch) {
                case ',':
                    buffer.delete(0, buffer.length());
                    state = 1;
                    break;
        
                case ';':
                    state = 7;
                    break;
        
                default:
                    buffer.delete(0, buffer.length());
                    buffer.append(ch);
                    state = 1;
                    break;
                }
                break;
        
            case 1: // parameter name
                switch (ch) {
                case ' ':
                case '=':
                    name = buffer.toString();
                    state = (ch == ' ') ? 2 : 3;
                    break;
        
                case ',':
                case ';':
                    state = -6; // error: invalid name
                    break;
        
                default:
                    buffer.append(ch);
                    break;
                }
                break;
        
            case 2: // equal between the name and the value
                switch (ch) {
                case '=':
                    state = 3;
                    break;
        
                case ' ':
                    break;
        
                default:
                    state = -1; // error: missing '='
                    break;
                }
                break;
        
            case 3: // value leading spaces
                switch (ch) {
                case ' ':
                    break;
        
                default:
                    buffer.delete(0, buffer.length());
                    buffer.append(ch);
                    state = 4;
                    break;
                }
                break;
        
            case 4: // value
                switch (ch) {
                case ' ':
                case ',':
                case ';':
                    idx -= 1; // put back the last read character
                    state = 5;
                    if ("path".equals(name)) {
                        if (path == null) {
                            path = buffer.toString();
                        } else {
                            state = -3; // error: more then one 'path'
                        }
                    } else if ("pipe".equals(name)) {
                        if (pipe == null) {
                            pipe = buffer.toString();
                        } else {
                            state = -4; // error: more then one 'pipe'
                        }
                    } else {
                        state = -2; // error: unknown parameter
                    }
                    buffer.delete(0, buffer.length());
                    break;
        
                default:
                    buffer.append(ch);
                    break;
                }
                break;
        
            case 5: // a delimeter after the value
                switch (ch) {
                case ' ':
                    break;
        
                case ',':
                    state = 6;
                    break;
        
                case ';':
                    state = 7;
                    break;
        
                default:
                    state = -5; // error: ' ' inside the value
                    break;
                }
                break;
        
            case 6: // leading spaces before next parameter name
                switch (ch) {
                case ' ':
                    break;
        
                default:
                    buffer.delete(0, buffer.length());
                    buffer.append(ch);
                    state = 1;
                    break;
                }
                break;
        
            default:
                throw new java.net.MalformedURLException(
                        "Invalid UNO connection URL.");
            }
        }
        if (state != 7) {
            throw new java.net.MalformedURLException(
                    "Invalid UNO connection URL.");
        }
        
        // Set up the connection parameters.
        if (path != null) {
            LocalOfficeConnection.mProgramPath = path;
        }
        if (pipe != null) {
            this.mPipe = pipe;
        }
        
    }

    @objid ("5cdd8ee2-f8f1-43c4-a3e2-5c62a51a6f3d")
    private static String replaceAll(final String aString, final String aSearch, final String aReplace) {
        StringBuffer aBuffer = new StringBuffer(aString);
        
        int nPos = aString.length();
        int nOfs = aSearch.length();
        
        while ((nPos = aString.lastIndexOf(aSearch, nPos - 1)) > -1) {
            aBuffer.replace(nPos, nPos + nOfs, aReplace);
        }
        return aBuffer.toString();
    }

    /**
     * creates a unique pipe name.
     */
    @objid ("714f52d6-218a-48fa-a8d5-4a768420eac1")
    @SuppressWarnings ("deprecation")
    static String getPipeName() {
        // turn user name into a URL and file system safe name (% chars will not work)
        String aPipeName = System.getProperty("user.name") + LocalOfficeConnection.OFFICE_ID_SUFFIX;
        aPipeName = replaceAll(aPipeName, "_", "%B7");
        return replaceAll(replaceAll(java.net.URLEncoder.encode(aPipeName), "+", "%20"), "%", "_");
    }

    /**
     * -------------------------------------------------------------------------
     * 
     * This is an implementation of the native office service.
     */
    @objid ("d314cf85-8980-4999-af98-5658c81c8185")
    private class OfficeService implements NativeService {
        /**
         * Default constructor.
         */
        @objid ("992e046a-8945-4dee-9ae9-8b4127ed9f90")
        public  OfficeService() {
            
        }

        /**
         * Retrieve the office service identifier.
         * @return The identifier of the office service.
         */
        @objid ("f456ffc4-5aef-4f91-b78a-3a34451112dc")
        @SuppressWarnings ("synthetic-access")
        @Override
        public String getIdentifier() {
            if (LocalOfficeConnection.this.mPipe == null) {
                return getPipeName();
            } else {
                return LocalOfficeConnection.this.mPipe;
            }
            
        }

        /**
         * Starts the office process.
         */
        @objid ("b03caae3-e4c5-41f5-86ce-e65b496f394d")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void startupService() throws IOException {
            int nSizeCmdArray = 4;
            String sOption = null;
            // examine if user specified command-line options in system properties.
            // We may offer later a more sophisticated way of providing options if
            // the need arises. Currently this is intended to ease the pain during
            // development with pre-release builds of OOo where one wants to start
            // OOo with the -norestore options. The value of the property is simple
            // passed on to the Runtime.exec call.
            try {
                sOption = System.getProperty("com.sun.star.officebean.Options");
                if (sOption != null) {
                    nSizeCmdArray++;
                }
            } catch (java.lang.SecurityException e) {
                e.printStackTrace();
            }
            // create call with arguments
            List<String> cmdArray = new ArrayList<>(nSizeCmdArray);
            
            // read UNO_PATH environment variable to get path to soffice binary
            String unoPath = System.getProperty("UNO_PATH");
            if (unoPath == null) {
                throw new java.io.IOException("UNO_PATH system property is not set (required system path to the office program directory)");
            }
            
            // cmdArray[i++] = (new File(getProgramPath(), OFFICE_APP_NAME)).getPath();
            cmdArray.add((new File(unoPath, getProgramName())).getPath());
            cmdArray.add("--nologo");
            cmdArray.add("--nodefault");
            cmdArray.add("--invisible");
            if (LocalOfficeConnection.this.mConnType.equals("pipe")) {
                cmdArray.add("--accept=pipe,name=" + getIdentifier() + ";" +
                        LocalOfficeConnection.this.mProtocol + ";" + LocalOfficeConnection.this.mInitialObject);
            } else if (LocalOfficeConnection.this.mConnType.equals("socket")) {
                cmdArray.add("--accept=socket,port=" + LocalOfficeConnection.this.mPort + ";urp");
            } else {
                throw new java.io.IOException("no connection type specified");
            }
            
            if (sOption != null) {
                cmdArray.add(sOption);
            }
            
            // start process
            LocalOfficeConnection.mProcess = Runtime.getRuntime().exec(cmdArray.toArray(new String[cmdArray.size()]));
            if (LocalOfficeConnection.mProcess == null) {
                throw new RuntimeException("cannot start soffice: " + cmdArray);
            }
            
            new StreamProcessor(LocalOfficeConnection.mProcess.getInputStream(), System.out, "OfficeService.out redirector");
            new StreamProcessor(LocalOfficeConnection.mProcess.getErrorStream(), System.err, "OfficeService.err redirector");
            
        }

        /**
         * Retrieves the amount of time to wait for the startup.
         * @return The amount of time to wait in seconds(?).
         */
        @objid ("9a7e5340-3842-4efd-920a-cc8f8b39ae06")
        @Override
        public int getStartupTime() {
            return 60;
        }

    }

    @objid ("4f3a6cdd-f698-45d0-9b89-465d43474054")
    private static class StreamProcessor extends Thread {
        @objid ("91e4ec85-967f-447b-a628-fc1dd4843d28")
        final java.io.InputStream m_in;

        @objid ("0d7e2302-322c-4992-8c5d-e281284e5d9d")
        final java.io.PrintStream m_print;

        @objid ("cdcf67ae-e714-46ab-a4cc-a9b00d167f99")
        public  StreamProcessor(final java.io.InputStream in, final java.io.PrintStream out, final String name) {
            super(name);
            this.m_in = in;
            this.m_print = out;
            start();
            
        }

        @objid ("de96d5d1-1651-4e9b-9529-beb3bfc57466")
        @Override
        public void run() {
            java.io.BufferedReader r = new java.io.BufferedReader(
                    new java.io.InputStreamReader(this.m_in));
            try {
                for (;;) {
                    String s = r.readLine();
                    if (s == null) {
                        break;
                    }
                    this.m_print.println(s);
                }
            } catch (java.io.IOException e) {
                e.printStackTrace(System.err);
            }
            
        }

    }

}
