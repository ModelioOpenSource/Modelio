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
package org.modelio.vstore.exml.common.index;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Indicates the AXML indexes cannot be opened and cannot be rebuilt.
 * <p>
 * This is a fatal error for an EXML repository.
 * 
 * @author cmarin
 * @since 3.5
 */
@objid ("f673b095-388a-485a-af74-e89bc0adf09f")
public class CannotOpenIndexException extends Exception {
    @objid ("ca738f34-e0d4-4be4-b935-86334eed8d51")
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     * @param cause
     */
    @objid ("843b518a-04b5-4143-8fea-5712c1e58651")
    public  CannotOpenIndexException(String message, Throwable cause) {
        super(message, cause);
    }

}
