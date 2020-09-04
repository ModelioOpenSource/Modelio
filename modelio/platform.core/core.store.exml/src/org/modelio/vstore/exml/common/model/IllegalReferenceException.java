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

package org.modelio.vstore.exml.common.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Tells an illegal reference was found in an EXML file.
 */
@objid ("7ba22e86-aad5-4afb-bfe0-6034dc1fd9f3")
public class IllegalReferenceException extends Exception {
    @objid ("18e1ebd4-260e-4cc4-a7d9-5107ac229c5b")
    private static final long serialVersionUID = 1L;

    /**
     * @param string the message
     */
    @objid ("d5a82ab6-d9fb-456a-bb8a-0f20e653c4fc")
    public IllegalReferenceException(String string) {
        super(string);
    }

}
