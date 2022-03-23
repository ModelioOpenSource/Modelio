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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.comp.loader.FactoryHelper;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.connection.NoConnectException;
import com.sun.star.connection.XConnection;
import com.sun.star.connection.XConnector;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.XSingleServiceFactory;
import com.sun.star.registry.XRegistryKey;

/**
 * A component that implements the <code>XConnector</code> interface.
 * <p>
 * <i>Modified from ridl.jar to help debugging.</i>
 * <p>The <code>pipeConnector</code> is a specialized component that uses TCP
 * pipes for communication.  The <code>pipeConnector</code> is generally
 * used by the <code>com.sun.star.connection.Connector</code> service.</p>
 * <p>
 * 
 * @version <i>Modified from ridl.jar to help debugging.</i>
 * @see com.sun.star.connections.XAcceptor
 * @see com.sun.star.connections.XConnection
 * @see com.sun.star.connections.XConnector
 * @see com.sun.star.loader.JavaLoader
 * 
 * @since UDK 1.0
 */
@objid ("7cfb1b7f-eb33-4924-b1cf-2508fbb62fdd")
@SuppressWarnings("javadoc")
public final class pipeConnector implements XConnector {
    /**
     * The name of the service.
     * 
     * <p>The <code>JavaLoader</code> acceses this through reflection.</p>
     * 
     * @see com.sun.star.comp.loader.JavaLoader
     */
    @objid ("c24c90ec-7d26-451e-ad6a-408f6e5cd462")
    public static final String __serviceName = "com.sun.star.connection.pipeConnector";

    @objid ("aed822e6-f1d5-4d34-b223-269ab5773918")
    private boolean bConnected = false;

    /**
     * Returns a factory for creating the service.
     * 
     * <p>This method is called by the <code>JavaLoader</code>.</p>
     * @see com.sun.star.comp.loader.JavaLoader
     * @param implName the name of the implementation for which a service is
     * requested.
     * @param multiFactory the service manager to be used (if needed).
     * @param regKey the registry key.
     * @return an <code>XSingleServiceFactory</code> for creating the component.
     */
    @objid ("9658325a-de43-4cf0-9286-0aadcc1226b3")
    public static XSingleServiceFactory __getServiceFactory(final String implName, final XMultiServiceFactory multiFactory, final XRegistryKey regKey) {
        return implName.equals(pipeConnector.class.getName())
            ? FactoryHelper.getServiceFactory(pipeConnector.class,
                                              __serviceName, multiFactory,
                                              regKey)
            : null;
        
    }

    /**
     * Connects via the described pipe to a waiting server.
     * 
     * <p>
     * The connection description has the following format: <code><var>type</var></code><!-- -->*(
     * <code><var>key</var>=<var>value</var></code>), where <code><var>type</var></code> should be <code>pipe</code>
     * (ignoring case). Supported keys (ignoring case) currently are
     * <dl>
     * <dt><code>host</code>
     * <dd>The name or address of the server. Must be present.
     * <dt><code>port</code>
     * <dd>The TCP port number of the server (defaults to <code>6001</code>).
     * <dt><code>tcpnodelay</code>
     * <dd>A flag (<code>0</code>/<code>1</code>) enabling or disabling Nagle's algorithm on the resulting connection.
     * </dl>
     * </p>
     * @see com.sun.star.connections.XAcceptor
     * @see com.sun.star.connections.XConnection
     * @param connectionDescription the description of the connection.
     * @return an <code>XConnection</code> to the server.
     */
    @objid ("00fc90a0-f399-41a5-a23f-2cfe44b1cccb")
    @Override
    public synchronized XConnection connect(final String connectionDescription) throws ConnectionSetupException, NoConnectException {
        if (this.bConnected) {
            throw new ConnectionSetupException("alread connected");
        }
        
        try {
            XConnection xConn = new PipeConnection(connectionDescription);
            this.bConnected = true;
            return xConn;
        } catch (java.io.IOException e) {
            // Modifies original source here: attach the cause to the exception
            throw (NoConnectException) new NoConnectException(e.getMessage()).initCause(e);
        }
        
    }

}
