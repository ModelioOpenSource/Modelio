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

package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Tells a metaclass already exists in the metamodel.
 * <p>
 * May occur if 2 threads try to add the same fake metaclass concurrently.
 */
@objid ("3e14a9d0-cfa8-4f37-ab37-783cbd6f7af8")
public class MetaclassAlreadyExistException extends RuntimeException {
    @objid ("dc1c0e52-82d9-48b4-a08f-963d0b8eeb04")
    private static final long serialVersionUID = 1L;

    @objid ("1a006017-287c-412c-956b-63877ed9cfba")
    private MClass existingClass;

    /**
     * @param existingClass the existing metaclass
     */
    @objid ("bbebedd9-c02e-4d64-b27d-e7723472dc64")
    public MetaclassAlreadyExistException(MClass existingClass) {
        this.existingClass = existingClass;
    }

    @objid ("9bc23482-ec3b-4672-917a-ec049316f26d")
    @Override
    public String getMessage() {
        return "The '"+this.existingClass+"' metaclass already exist.";
    }

    /**
     * @return the existing metaclass.
     */
    @objid ("cfefb8ab-1da1-4d24-87c5-618535feb242")
    public MClass getExistingMetaclass() {
        return this.existingClass;
    }

}
