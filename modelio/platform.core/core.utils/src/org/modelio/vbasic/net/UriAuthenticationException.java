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

import java.nio.file.AccessDeniedException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Indicates that:<ul>
 * <li> the caller must first authenticate to the target using an {@link IAuthData}.
 * <li> that authentication data was not valid (wrong user/password).
 * </ul>
 * @author cmarin
 */
@objid ("bdf9bbbe-c701-403c-9ad6-17a9cd1978ef")
public class UriAuthenticationException extends AccessDeniedException {
    @objid ("b3997ec9-bf89-4a01-9df7-f0f450f242d4")
    private static final long serialVersionUID = 1L;

    /**
     * @param file the URI that needs authentication
     * @param reason a message
     */
    @objid ("e35d0231-1a0d-49ca-be15-e884d032bb93")
    public UriAuthenticationException(String file, String reason) {
        super(file, null, reason);
    }

    /**
     * @param file the file that needs authentication
     */
    @objid ("3702750a-5059-4cd5-b4cf-aa9ec22f0cbf")
    public UriAuthenticationException(String file) {
        super(file);
    }

}
