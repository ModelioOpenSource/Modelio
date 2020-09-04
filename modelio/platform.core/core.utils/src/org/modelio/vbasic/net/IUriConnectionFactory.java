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
import java.net.URI;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Same as {@link java.net.URLStreamHandler} but for URIs.
 * @see java.net.URLStreamHandlerFactory
 * @see java.net.URLConnection
 * @see URL#openConnection()
 */
@objid ("c000792d-937f-4045-875f-e574a1da3b4c")
public interface IUriConnectionFactory {
    /**
     * Tells whether this handler supports this URI.
     * @param uri an URI
     * @return <code>true</code> if this handler supports the URI else <code>false</code>.
     */
    @objid ("613e64ad-df49-41e9-8e25-8418850351dc")
    boolean supports(URI uri);

    /**
     * Instantiate a connection for the given URI.
     * <p>
     * No connection to the server is created yet when instantiating the connection,
     * it will be done only when needed.
     * @param uri a supported URI.
     * @return a connection.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("2e31e97f-78bc-413b-92ea-baa72755ecda")
    UriConnection createConnection(URI uri) throws IOException;

}
