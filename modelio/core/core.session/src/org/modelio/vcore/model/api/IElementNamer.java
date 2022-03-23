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
package org.modelio.vcore.model.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service that gives a default name to a model element.
 * 
 * @author phv
 */
@objid ("00888498-030f-1035-9f91-001ec947cd2a")
public interface IElementNamer {
    /**
     * Returns a base name for a new created element of type <code>metaclass</code>
     * @param metaclass
     * @return a base name for a new created element of type metaclass
     */
    @objid ("00888cc2-030f-1035-9f91-001ec947cd2a")
    String getBaseName(MClass metaclass);

    /**
     * Compute a unique name for this element so that this name is unique in the element composition.
     * 
     * This is similar to calling getUniqueName(getBaseName(object, object).
     * @param object
     * @return a unique name for element
     */
    @objid ("0088a2f2-030f-1035-9f91-001ec947cd2a")
    String getUniqueName(MObject object);

    /**
     * Compute a unique name for this element so that this name is unique in the element composition.
     * The computed name is build from <code>basename</code>
     * @param basename
     * @param object
     * @return a unique name for element starting by basename
     */
    @objid ("0088bce2-030f-1035-9f91-001ec947cd2a")
    String getUniqueName(String basename, MObject object);

    /**
     * Returns a base name for a model element.
     * @param object
     * @return a base name for <code>object</code>
     */
    @objid ("7f4a595a-b1e9-4639-806f-ef3ef2eddc59")
    String getBaseName(MObject object);

}
