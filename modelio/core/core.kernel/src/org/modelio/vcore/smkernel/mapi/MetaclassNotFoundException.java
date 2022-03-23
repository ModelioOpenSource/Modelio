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
package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Tells a metaclass has not been found.
 */
@objid ("3e3dca6f-2f0b-11e2-8f81-001ec947ccaf")
public class MetaclassNotFoundException extends Exception {
    @objid ("8416b16a-2f0f-11e2-8f81-001ec947ccaf")
    private static final long serialVersionUID = 1L;

    @objid ("845970a4-2f0f-11e2-8f81-001ec947ccaf")
    private String className;

    /**
     * @param className the missing metaclass
     */
    @objid ("8416b16d-2f0f-11e2-8f81-001ec947ccaf")
    public  MetaclassNotFoundException(String className) {
        this.className = className;
    }

    @objid ("8416b171-2f0f-11e2-8f81-001ec947ccaf")
    @Override
    public String getMessage() {
        return "The '"+this.className+"' metaclass does not exist.";
    }

}
