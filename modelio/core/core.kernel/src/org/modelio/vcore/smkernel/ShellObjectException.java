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
 * Exception that indicates the given model object is an unresolved reference.
 */
@objid ("ce9c8c2c-3d34-11e2-ab11-001ec947ccaf")
public class ShellObjectException extends IllegalStateException {
    @objid ("8f7cadac-3d36-11e2-ab11-001ec947ccaf")
    private static final long serialVersionUID = 1L;

    @objid ("8f7cadae-3d36-11e2-ab11-001ec947ccaf")
    private MObject shellObj;

    /**
     * Initialize the exception.
     * 
     * @param obj the shell object.
     */
    @objid ("8f7cadaf-3d36-11e2-ab11-001ec947ccaf")
    public ShellObjectException(MObject obj) {
        this.shellObj = obj;
    }

    /**
     * Get the unresolved object.
     * 
     * @return the unresolved reference.
     */
    @objid ("8f7cadb3-3d36-11e2-ab11-001ec947ccaf")
    public MObject getShellObject() {
        return this.shellObj;
    }

    @objid ("8f7cadb8-3d36-11e2-ab11-001ec947ccaf")
    @Override
    public String getMessage() {
        return this.shellObj + " is an unresolved reference.";
    }

}
