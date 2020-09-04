/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Exception throw when a MClass is needed but was not found from its name.
 */
@objid ("69e0b3cc-16e3-11e2-b24b-001ec947ccaf")
public class UnknownMetaclassException extends IllegalArgumentException {
    @objid ("109826d6-16e7-11e2-b24b-001ec947ccaf")
    private static final long serialVersionUID = 1L;

    @objid ("10c7d58e-16e7-11e2-b24b-001ec947ccaf")
    private final String mc;

    /**
     * @param className the missing metaclass name
     */
    @objid ("109a892e-16e7-11e2-b24b-001ec947ccaf")
    public UnknownMetaclassException(String className) {
        super("Unknown '"+className+"' metaclass.");
        this.mc = className;
    }

    /**
     * Get the name of the missing metaclass.
     * 
     * @return the metaclass not found.
     */
    @objid ("109a8931-16e7-11e2-b24b-001ec947ccaf")
    public String getMc() {
        return this.mc;
    }

}
