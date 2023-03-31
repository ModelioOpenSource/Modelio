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

/**
 * Interface for HTTP related URI failures mapped to {@link FileSystemException}.
 * <p>
 * All subclasses must inherit directly or not from {@link FileSystemException}.
 * {@link FileSystemException#getReason()} is expected to contain the HTTP response body
 * or the status message.
 * <p>
 * Expected exception class depending on HTTP status :
 * <ul>
 * <li> 401, 407 : use {@link HttpUriAuthenticationException}
 * <li> 403 : use {@link AccessDeniedException}
 * <li> 404 : use {@link NoSuchFileException}
 * <li> others : use {@link HttpUriException}
 * </ul>
 * <p>
 * Use {@link HttpErrorMapper} to instantiate the right exception from an HTTP status code.
 * 
 * @since Wyrm 4.0.1
 */
@objid ("20613b3b-6fe6-4afc-8a5f-3782e58a130a")
public interface IHttpUriException {
    /**
     * @return the HTTP status code
     */
    @objid ("4b5b9335-7d47-4c77-9a59-90732e5f765e")
    int getHttpStatus();
}

