/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vbasic.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Same as {@link java.net.URLConnection} but for URI.
 * <p>
 * Instantiate them with UriConnections#createConnection(URI)
 * 
 * @see java.net.URLConnection
 * @see java.net.URLStreamHandler
 * @see java.net.URLStreamHandlerFactory
 */
@objid ("d11ac497-9a36-4077-8537-004029ceed9e")
public abstract class UriConnection {
    @objid ("8ea8e953-2d3e-4835-b1a3-53510c1d35fa")
    private int connectTimeout;

    /**
     * Same as {@link java.net.URLConnection#getInputStream()}.
     * @throws UnknownServiceException  if the protocol does not support input.
     * @return an input stream that writes to this connection.
     * @throws java.io.IOException if an I/O error occurs while creating the input stream.
     */
    @objid ("ec047508-b1cd-4cf8-bf82-6720d1b4a9f7")
    public abstract InputStream getInputStream() throws IOException;

    /**
     * Same as {@link java.net.URLConnection#getOutputStream()}.
     * @throws UnknownServiceException  if the protocol does not support
     * output.
     * @return an output stream that writes to this connection.
     * @throws java.io.IOException if an I/O error occurs while creating the output stream.
     */
    @objid ("4d6c68da-97c7-4400-91ae-883efc9234aa")
    public abstract OutputStream getOutputStream() throws IOException;

    /**
     * Same as {@link java.net.URLConnection#setRequestProperty(String, String)}.
     * Sets the general request property. If a property with the key already
     * exists, overwrite its value with the new value.
     * 
     * <p> NOTE: HTTP requires all request properties which can
     * legally have multiple instances with the same key
     * to use a comma-seperated list syntax which enables multiple
     * properties to be appended into a single property.
     * @param   key     the keyword by which the request is known
     * (e.g., "<code>Accept</code>").
     * @param   value   the value associated with it.
     * @throws IllegalStateException if already connected
     * @throws NullPointerException if key is <code>null</code>
     * @see java.net.URLConnection#setRequestProperty(String, String)
     */
    @objid ("a32aadb8-a91b-4aff-ade8-094963b8eed5")
    public abstract void setRequestProperty(String key, String value);

    /**
     * Same as {@link java.net.URLConnection#setDoInput(boolean)}.
     * <p>
     * A URI connection can be used for input and/or output.  Set the DoOutput
     * flag to true if you intend to use the URI connection for output,
     * <code>false</code> if not.  The default is <code>false</code>.
     * @param   dooutput   the new value.
     * @throws IllegalStateException if already connected
     */
    @objid ("ad12f463-2d07-4528-843f-6cd198bd3ed0")
    public abstract void setDoOutput(boolean dooutput);

    /**
     * A URI connection can be used for input and/or output.  Set the DoInput
     * flag to true if you intend to use the URI connection for input,
     * <code>false</code> if not.  The default is <code>true</code>.
     * @param   doinput   the new value.
     * @throws IllegalStateException if already connected
     */
    @objid ("254620ad-e6ee-4118-bd4d-b0a49a8d9f35")
    public abstract void setDoInput(boolean doinput);

    /**
     * Sets a specified timeout value, in milliseconds, to be used
     * when opening a communications link to the resource referenced
     * by this URLConnection.  If the timeout expires before the
     * connection can be established, a
     * java.net.SocketTimeoutException is raised. A timeout of zero is
     * interpreted as an infinite timeout.
     * 
     * <p> Some non-standard implementation of this method may ignore
     * the specified timeout. To see the connect timeout set, please
     * call getConnectTimeout().
     * @see #getConnectTimeout()
     * @param timeout an <code>int</code> that specifies the connect
     * timeout value in milliseconds
     * @throws java.lang.IllegalArgumentException if the timeout parameter is negative
     */
    @objid ("1d3bfb36-becf-4e77-b0b2-78de276dbfd1")
    public abstract void setConnectTimeout(int timeout) throws IllegalArgumentException;

    /**
     * Returns setting for connect timeout.
     * <p>
     * 0 return implies that the option is disabled
     * (i.e., timeout of infinity).
     * @see #setConnectTimeout(int)
     * @return an <code>int</code> that indicates the connect timeout
     * value in milliseconds
     */
    @objid ("c7cadc82-d37b-4aca-97e0-9ad7c3c658dd")
    public abstract int getConnectTimeout();

    /**
     * Tells to skip connection if the remote file stamp is the given one.
     * @see URLConnection#setIfModifiedSince(long)
     * @param stamp the file stamp
     */
    @objid ("5bfc8e7c-45e8-4c92-b1cb-51aa3d1290ec")
    public abstract void setIfNotStamp(String stamp);

    /**
     * @return the remote file stamp.
     */
    @objid ("31481ee7-5ade-43d0-aee3-7feda332b958")
    public abstract String getStamp();

    /**
     * Set the authentication data.
     * @param auth the authentication data.
     */
    @objid ("25f65fc8-f013-4193-b9ec-658bcf163d34")
    public abstract void setAuthenticationData(IAuthData auth);

    /**
     * Same as {@link java.net.URLConnection#getContentType()}.
     * @throws UnknownServiceException  if the protocol does not support input.
     * @return the content type of the resource that the URI references, or <i>null</i> if not known..
     * @throws java.io.IOException if an I/O error occurs while creating the input stream.
     */
    @objid ("9d39f515-e509-4ea5-a224-cc69c49af322")
    public abstract String getContentType() throws IOException;

}
