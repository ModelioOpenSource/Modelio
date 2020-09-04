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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * IllegalModelManipulationException is thrown whenever a modification of the
 * model would break the model integrity or conformity to the modeling rules
 * currently applicable (metamodel structure, metamodel constraints and so
 * on...).
 */
@objid ("000fd944-2e97-1036-812a-001ec947cd2a")
public class IllegalModelManipulationException extends RuntimeException {
    @objid ("005bcbec-2fed-1036-812a-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    @objid ("00543bc0-3b15-1037-812a-001ec947cd2a")
    private final int errorCode;

    @objid ("0054441c-3b15-1037-812a-001ec947cd2a")
    private final MObject object;

    @objid ("00035cf0-3b63-1037-812a-001ec947cd2a")
    private final Object closure;

    @objid ("006bb1a6-3b26-1036-812a-001ec947cd2a")
    public IllegalModelManipulationException(int errorCode, MObject object, Object closure) {
        super();
        this.errorCode = errorCode;
        this.object = object;
        this.closure = closure;
    }

    @objid ("00539fbc-3b6b-1037-812a-001ec947cd2a")
    public MObject getObject() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.object;
    }

    @objid ("0000a1ae-3b6f-1037-812a-001ec947cd2a")
    public Object getClosure() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.closure;
    }

    @objid ("0015617a-3b73-1037-812a-001ec947cd2a")
    public int getErrorCode() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.errorCode;
    }

    @objid ("64620424-bfb3-4a37-98c6-41bbe73013ad")
    @Override
    public String toString() {
        return String.format("IllegalModelManipulationException [Error: %s, object=%s, closure=%s]",
                                getErrorCode(), getObject(), getClosure());
    }

    @objid ("b63a4efb-ca4c-484c-b51e-2394e4476958")
    @Override
    public String getMessage() {
        return String.format("Error %s on %s: closure=%s",
                                getErrorCode(), getObject(), getClosure());
    }

}
