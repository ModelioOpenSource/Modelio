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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Indicates that:<ul>
 * <li> the caller must first authenticate to the target using an {@link IAuthData} (HTTP 401).
 * <li> that server or proxy authentication data was not valid (wrong user/password) (HTTP 401/407).
 * </ul>
 * <p>
 * Note this exception does not derive from {@link HttpUriException} but from {@link java.nio.file.AccessDeniedException AccessDeniedException}
 * to be compatible with non <i>org.modelio.vbasic.net</i> aware callers.
 * <p>
 * Use {@link HttpErrorMapper} to instantiate the right exception from an HTTP status code.
 * @author cmarin
 * @since Wyrm 4.0.1
 */
@objid ("48fd5fc4-6ed1-41d3-8952-72adb27146ac")
public class HttpUriAuthenticationException extends UriAuthenticationException implements IHttpUriException {
    @objid ("b360a158-cc66-42e0-aa20-796941bfeac7")
    private static final long serialVersionUID = 1L;

    @objid ("66e72023-439e-449e-abc4-6cd6ac1e41ce")
    private int httpStatus;

    /**
     * @param httpStatus the HTTP status code
     * @param file the URI that needs authentication
     * @param reason a message. It should be the HTTP response body or the HTTP status line message.
     */
    @objid ("bf9b7d2d-d64a-492b-ae06-79495c6a3673")
    public HttpUriAuthenticationException(int httpStatus, String file, String reason) {
        super(file, reason);
        this.httpStatus = httpStatus;
    }

    @objid ("603cca94-4161-4ee9-a7b2-006cf713da24")
    @Override
    public int getHttpStatus() {
        return this.httpStatus;
    }

    /**
     * @param httpStatus the HTTP status code
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param file the URI that needs authentication
     * @param reason a message. It should be the HTTP response body or the HTTP status line message.
     */
    @objid ("213eea6d-809d-43fb-935f-25ef45ff1868")
    public HttpUriAuthenticationException(int httpStatus, Throwable cause, String file, String reason) {
        super(cause, file, reason);
        this.httpStatus = httpStatus;
    }

}
