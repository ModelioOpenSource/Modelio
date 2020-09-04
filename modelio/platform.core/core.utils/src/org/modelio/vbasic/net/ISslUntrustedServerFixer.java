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

package org.modelio.vbasic.net;

import java.net.URI;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLHandshakeException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Service interface to use when SSL certificate validation for a server fails with a {@link SSLHandshakeException}.
 * <p>
 * Implementations may display a dialog box allowing the user to accept temporarily or permanently
 * the invalid certificate.
 */
@objid ("930061ee-0d47-4436-b4f0-cfaf84a1da5a")
public interface ISslUntrustedServerFixer {
    /**
     * Allows the user to fix the server trust problem.
     * <p>
     * If the method returns <code>true</code> the caller may try the connection again.
     * 
     * @param uri the server URI
     * @param chain the invalid certificate chain
     * @param ex the connection error.
     * @return <code>true</code> if connection can be tried again, <code>false</code> if it should be aborted.
     */
    @objid ("48f32ca7-9353-4774-8de8-41c4d5b7e14e")
    boolean fixUntrustedServer(final URI uri, X509Certificate[] chain, Throwable ex);

}
