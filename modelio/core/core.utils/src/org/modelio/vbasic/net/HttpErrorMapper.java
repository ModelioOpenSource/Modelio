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

import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.apache.http.HttpStatus;

/**
 * Service to map HTTP errors to {@link FileSystemException}.
 * @author cma
 * @since Wyrm 4.0.1
 */
@objid ("c7ebf33f-c318-41f7-9ddf-b8322dc7ca3a")
public class HttpErrorMapper {
    /**
     * @param statusCode the HTTP status code
     * @param url the URL whose access failed.
     * @param reason a message. It should be the HTTP response body or the HTTP status line message.
     * @param cause the optional cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method).  (A {@code null} value is permitted,
     * in this case the cause is not initialized and be be set later.
     * @return the mapped exception
     */
    @objid ("3b6baa7d-8ca6-4799-b5b1-50293568e360")
    public static FileSystemException create(int statusCode, String url, String reason, Throwable cause) {
        FileSystemException error;
        
        switch (statusCode) {
        case HttpStatus.SC_FORBIDDEN:
            error = new AccessDeniedException(url, null, reason);
            break;
        case HttpStatus.SC_UNAUTHORIZED:
        case HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED:
            error = new HttpUriAuthenticationException(statusCode, url, reason);
            break;
        case HttpStatus.SC_NOT_FOUND:
            error = new NoSuchFileException(url,  null, reason);
            break;
        default:
            error = new HttpUriException(statusCode, url, reason);
            break;
        }
        
        if (cause != null) {
            error.initCause(cause);
        }
        return error;
    }

}
