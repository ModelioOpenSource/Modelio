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
package org.modelio.vcore.model.filter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Metamodel association filter that can filter out objects.
 */
@objid ("f567103c-9e9f-11e1-a22d-001ec947ccaf")
public interface IObjectFilter {
    /**
     * Tells whether the given object must be included.
     * @param obj an object
     * @return <li><code>true</code> to include the object, <li>
     * <code>false</code> to filter it out.
     */
    @objid ("ac6c25ce-a419-11e1-aa98-001ec947ccaf")
    boolean accept(final MObject obj);
}

