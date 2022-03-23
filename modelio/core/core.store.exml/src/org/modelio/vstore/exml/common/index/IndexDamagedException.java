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
/**
 * 
 */
package org.modelio.vstore.exml.common.index;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Tells the EXML indexes are damaged and must be rebuilt.
 * @author cmarin
 * @since 3.5
 */
@objid ("3c65a843-8285-4952-83d9-7f01b96ffbac")
public class IndexDamagedException extends IOException {
    @objid ("fda50a87-b87f-40d2-96b4-a8783a6ed1f0")
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    @objid ("97d36761-3b25-4e3c-980a-6caba265cab8")
    public  IndexDamagedException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    @objid ("2f8d5374-86b9-457e-8a19-2d2e2a9a2a3b")
    public  IndexDamagedException(String message, Throwable cause) {
        super(message, cause);
    }

}
