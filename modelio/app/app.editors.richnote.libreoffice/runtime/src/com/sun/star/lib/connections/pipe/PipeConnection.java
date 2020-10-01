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

package com.sun.star.lib.connections.pipe;

import java.io.IOException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.connection.XConnection;
import com.sun.star.connection.XConnectionBroadcaster;
import com.sun.star.io.XStreamListener;
import com.sun.star.lib.util.NativeLibraryLoader;

/**
 * The PipeConnection implements the <code>XConnection</code> interface and is uses by the <code>PipeConnector</code>
 * and the <code>PipeAcceptor</code>. This class is not part of the provided <code>api</code>.
 * <p>
 * 
 * <i>Copied without modification from ridl.jar to help debugging.</i>
 * 
 * @version $Revision: 1.7 $ $ $Date: 2008-04-11 11:13:00 $
 * @author Kay Ramme
 * @see com.sun.star.comp.connections.PipeAcceptor
 * @see com.sun.star.comp.connections.PipeConnector
 * @see com.sun.star.connections.XConnection
 * @since UDK1.0
 */
@objid ("dfd70595-935d-433a-ba33-c79bfc6f0c22")
@SuppressWarnings("javadoc")
public class PipeConnection implements XConnection, XConnectionBroadcaster {
    /**
     * When set to true, enables various debugging output.
     */
    @objid ("44d5a789-349e-4ce9-af97-6552c859c732")
    public static final boolean DEBUG = false;

    @objid ("62b513d4-fdf3-495f-9f85-259d0c6ebfb8")
    protected String _aDescription;

    @objid ("e5cba5dd-a79c-4238-9dfc-a4a5cb4a64b6")
    protected long _nPipeHandle;

    @objid ("3e9a747b-e6ab-4ce2-82d8-84332dcdc18a")
    protected boolean _bFirstRead;

    @objid ("4c112426-f8c7-4235-8470-f15327fdd6d7")
    protected Vector<XStreamListener> _aListeners;

    /**
     * Constructs a new <code>PipeConnection</code>.
     * <p>
     * @param pipe
     * the pipe of the connection
     * 
     * @param description the description of the connection
     */
    @objid ("9a772359-aa8f-4f5d-aff0-e76da20b1d28")
    public PipeConnection(final String description) throws IOException {
        if (DEBUG)
            System.err.println("##### " + getClass().getName() + " - instantiated " + description);
        
        this._aListeners = new Vector<>();
        this._bFirstRead = true;
        
        // get pipe name from pipe descriptor
        String aPipeName = null;
        StringTokenizer aTokenizer = new StringTokenizer(description, ",");
        if (aTokenizer.hasMoreTokens()) {
            String aConnType = aTokenizer.nextToken();
            if (!aConnType.equals("pipe"))
                throw new RuntimeException("invalid pipe descriptor: does not start with 'pipe,'");
        
            String aPipeNameParam = aTokenizer.nextToken();
            if (!aPipeNameParam.substring(0, 5).equals("name="))
                throw new RuntimeException("invalid pipe descriptor: no 'name=' parameter found");
            aPipeName = aPipeNameParam.substring(5);
        } else
            throw new RuntimeException("invalid or empty pipe descriptor");
        
        // create the pipe
        try {
            createJNI(aPipeName);
        } catch (java.lang.NullPointerException aNPE) {
            throw new IOException(aNPE.getMessage());
        } catch (com.sun.star.io.IOException aIOE) {
            throw new IOException(aIOE.getMessage());
        } catch (java.lang.Exception aE) {
            throw new IOException(aE.getMessage());
        }
    }

    @objid ("b7868338-95b3-4451-ad84-3f5ea1415ea5")
    @Override
    public void addStreamListener(final XStreamListener aListener) throws com.sun.star.uno.RuntimeException {
        this._aListeners.addElement(aListener);
    }

    @objid ("4961d268-aa43-4851-85f1-b980bc4dbd16")
    @Override
    public void removeStreamListener(final XStreamListener aListener) throws com.sun.star.uno.RuntimeException {
        this._aListeners.removeElement(aListener);
    }

    @objid ("12a4c7b3-71ae-480c-ace6-35b019586bcd")
    private void notifyListeners_open() {
        Enumeration<XStreamListener> elements = this._aListeners.elements();
        while (elements.hasMoreElements()) {
            XStreamListener xStreamListener = elements.nextElement();
            xStreamListener.started();
        }
    }

    @objid ("c0747758-cbf5-4ccd-ae9d-7996a2136a90")
    private void notifyListeners_close() {
        Enumeration<XStreamListener> elements = this._aListeners.elements();
        while (elements.hasMoreElements()) {
            XStreamListener xStreamListener = elements.nextElement();
            xStreamListener.closed();
        }
    }

    @objid ("b6ce95fe-c9c0-40ca-9eeb-3d880f8115d9")
    private void notifyListeners_error(final com.sun.star.uno.Exception exception) {
        Enumeration<XStreamListener> elements = this._aListeners.elements();
        while (elements.hasMoreElements()) {
            XStreamListener xStreamListener = elements.nextElement();
            xStreamListener.error(exception);
        }
    }

    /**
     * JNI implementation to create the pipe
     */
    @objid ("e745b96a-ebf3-4593-a2db-6dda8e2d2810")
    private native int createJNI(final String name) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    /**
     * JNI implementation to read from the pipe
     */
    @objid ("3ab894dc-743d-4f4b-ab79-188726a89b98")
    private native int readJNI(final byte[][] bytes, final int nBytesToRead) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    /**
     * JNI implementation to write to the pipe
     */
    @objid ("362ac1f2-3852-46ce-8941-3c6a2014b8a9")
    private native void writeJNI(final byte[] aData) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    /**
     * JNI implementation to flush the pipe
     */
    @objid ("5599cd50-2851-46bf-af16-ee8ab002746e")
    private native void flushJNI() throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    /**
     * JNI implementation to close the pipe
     */
    @objid ("9f53575c-adc7-4ee9-8f6a-1afbfa7a4cb3")
    private native void closeJNI() throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    /**
     * Read the required number of bytes.
     * <p>
     * @param aReadBytes
     * the outparameter, where the bytes have to be placed
     * @see com.sun.star.connections.XConnection#read
     * 
     * @param nBytesToRead the number of bytes to read
     * @return the number of bytes read
     */
    @objid ("59c2d7cf-d945-4b49-86fd-79ea45da561d")
    @Override
    public int read(final byte[][] bytes, final int nBytesToRead) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException {
        if (this._bFirstRead) {
            this._bFirstRead = false;
        
            notifyListeners_open();
        }
        return readJNI(bytes, nBytesToRead);
    }

    /**
     * Write bytes.
     * <p>
     * @see com.sun.star.connections.XConnection#write
     * 
     * @param aData the bytes to write
     */
    @objid ("e1d44d1a-8d7e-4cdc-b58f-502182e37709")
    @Override
    public void write(final byte[] aData) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException {
        writeJNI(aData);
    }

    /**
     * Flushes the buffer.
     * <p>
     * @see com.sun.star.connections.XConnection#flush
     */
    @objid ("3dc565b8-465a-4908-877e-8a330b8fea40")
    @Override
    public void flush() throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException {
        flushJNI();
    }

    /**
     * Closes the connection.
     * <p>
     * @see com.sun.star.connections.XConnection#close
     */
    @objid ("c81b24bd-b009-4543-bf14-89c108d7e2d5")
    @Override
    public void close() throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException {
        if (DEBUG)
            System.out.print("PipeConnection::close() ");
        closeJNI();
        notifyListeners_close();
        if (DEBUG)
            System.out.println("done");
    }

    /**
     * Gives a description of the connection.
     * <p>
     * @see com.sun.star.connections.XConnection#getDescription
     * 
     * @return the description
     */
    @objid ("12aeadf7-8f5b-4266-9f5e-3af6d66ec0a4")
    @Override
    public String getDescription() throws com.sun.star.uno.RuntimeException {
        return this._aDescription;
    }


static {
        // load shared library for JNI code
        NativeLibraryLoader.loadLibrary(PipeConnection.class.getClassLoader(), "jpipe");
    }
}
