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

import java.nio.file.FileSystemException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * {@link FileSystemException} that indicates that access to an HTTP URI failed due to a non OK HTTP status code,
 * <p>
 * Instantiate this exception for HTTP errors except those documented in {@link IHttpUriException}.
 * <p>
 * Use {@link HttpErrorMapper} to instantiate the right exception from an HTTP status code.
 * 
 * @since Wyrm 4.0.1
 * @see IHttpUriException
 * @see HttpErrorMapper
 * @author cmarin
 */
@objid ("3d1c5cb9-2f61-4700-b9e0-e5867af8061d")
public class HttpUriException extends FileSystemException implements IHttpUriException {
    @objid ("904be7fc-3853-4a08-bc4e-ae6afcbf553b")
    private static final long serialVersionUID = 1L;

    @objid ("dbd4123e-ef43-421e-8916-4b0c9fc6995b")
    private final int httpStatus;

    /**
     * @param httpStatus the HTTP status code
     * @param file the URI that needs authentication
     * @param reason a message. It should be the HTTP response body or the HTTP status line message.
     */
    @objid ("f0178015-be07-4716-b9c9-b2c1fd46cee9")
    public HttpUriException(int httpStatus, String file, String reason) {
        super(file, null, reason);
        this.httpStatus = httpStatus;
    }

    /**
     * @param httpStatus the HTTP status code
     * @param file the file that needs authentication
     */
    @objid ("e2a58139-befe-4bab-aead-d539b35ff19d")
    public HttpUriException(int httpStatus, String file) {
        super(file);
        this.httpStatus = httpStatus;
    }

    @objid ("f75f384d-7485-4973-9593-26d5036d6284")
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
    @objid ("d891a386-d6be-46ed-b585-df9446373351")
    public HttpUriException(int httpStatus, Throwable cause, String file, String reason) {
        this(httpStatus, file, reason);
    }

}
