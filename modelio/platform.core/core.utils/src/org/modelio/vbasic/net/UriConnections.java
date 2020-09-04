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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Same as {@link java.net.URLStreamHandlerFactory} but here we can add easily other stream handlers.
 * <p>
 * This is a work around for the too strict limitations of the java.net.URL API.
 * @see java.net.URLStreamHandlerFactory
 * @see java.net.URLConnection
 * @see URL#openConnection()
 */
@objid ("16aba2ab-05b1-4ec1-aac8-f86b8c64da12")
public class UriConnections {
    @objid ("9d4d65aa-d553-4f68-8fcc-b7ae08adf1f7")
    private static List<IUriConnectionFactory> handlers = new ArrayList<>();

    /**
     * Service class, no instance.
     */
    @objid ("7a113b9e-262a-439a-bd27-67f5187d9de5")
    private UriConnections() {
        // no instance
    }

    /**
     * Same as {@link URL#openStream()}
     * @param uri an URI
     * @return an input stream for reading from the URI connection.
     * @throws java.net.MalformedURLException if the URI is not supported
     * @throws java.io.IOException if an I/O exception occurs.
     * @deprecated Better use {@link #openInputStream(URI, IAuthData)}
     */
    @objid ("5308ce01-a0e5-400a-8ab1-f3a423ecbbd9")
    @Deprecated
    public static InputStream openInputStream(URI uri) throws IOException, MalformedURLException {
        return createConnection(uri).getInputStream();
    }

    /**
     * Same as {@link URL#openConnection()}.
     * @param uri an URI
     * @return a UriConnection linking to the URI.
     * @throws java.net.MalformedURLException if the URI is not supported or not absolute.
     * @throws java.io.IOException if an I/O exception occurs.
     */
    @objid ("b0f68300-2420-4079-8758-8974eed096c4")
    public static UriConnection createConnection(URI uri) throws IOException, MalformedURLException {
        if (! uri.isAbsolute())
            throw new MalformedURLException("'"+uri+ "' is not absolute.");
        
        for (IUriConnectionFactory h : handlers) {
            if (h.supports(uri))
                return h.createConnection(uri);
        }
        
        throw new MalformedURLException(uri+" not supported.");
    }

    /**
     * Add an URI connection handler.
     * <p>
     * The added handler takes priority from existing handlers.
     * @param handler a new URI handler.
     */
    @objid ("73150088-8f5d-4529-82ef-13515c6c4802")
    public static void addHandler(IUriConnectionFactory handler) {
        handlers.add(0, handler);
    }

    /**
     * Same as {@link URL#openStream()}
     * @param uri an URI
     * @param authData authentication data.
     * @return an input stream for reading from the URI connection.
     * @throws java.net.MalformedURLException if the URI is not supported
     * @throws java.io.IOException if an I/O exception occurs.
     */
    @objid ("7110beb6-8b10-4a6f-8022-529e185d403f")
    public static InputStream openInputStream(URI uri, IAuthData authData) throws IOException, MalformedURLException {
        UriConnection conn = createConnection(uri);
        conn.setAuthenticationData(authData);
        return conn.getInputStream();
    }


static {
        // Add handler for https URIs
        //handlers.add(new HttpsUriConnection.Factory());

        // Add HTTP & HTTPS handler that uses Apache "httpclient" library.
        handlers.add(new ApacheUriConnection.ApacheUriConnectionFactory());
        
        // Add the default URI handler that support already supported URL.
        handlers.add(new UrlUriHandler());
    }
    /**
     * URI Handler for URIs that are supported as URL.
     */
    @objid ("187a3c30-b9bf-4e39-94ae-5f21cbee7d0f")
    private static final class UrlUriHandler implements IUriConnectionFactory {
        @objid ("292d7757-594b-4dfe-8a0e-94f3b674c419")
        UrlUriHandler() {
            // nothing
        }

        @objid ("a45ca759-614a-4346-b3d4-5a114e60bec5")
        @Override
        public boolean supports(URI uri) {
            // https is handled by another handler
            if ("https".equals(uri.getScheme()))
                return false;
            
            try {
                // Test conversion to URL don't throw exception
                @SuppressWarnings("unused")
                URL url = uri.toURL();
                return true;
            } catch (MalformedURLException e) {
                // Not supported
                return false;
            }
        }

        @objid ("3e7a96ae-dbde-4908-b710-0920b6583fd3")
        @Override
        public UriConnection createConnection(URI uri) throws IOException {
            return new UrlUriConnection(uri);
        }

    }

}
