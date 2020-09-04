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

package org.modelio.vstore.exml.common.index;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Indicates a failure in an EXML index operation.
 * 
 * @author cmarin
 */
@objid ("7ee47b3a-c507-456f-a1b7-9e924daaad0b")
public class IndexException extends Exception {
    @objid ("b05379aa-0f93-4e36-ab2d-5e555ca448b1")
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     * @param cause
     */
    @objid ("61153996-9b70-45e2-9a8a-9429ccbabea7")
    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

}
