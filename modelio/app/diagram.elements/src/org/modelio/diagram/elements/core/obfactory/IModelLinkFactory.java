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

package org.modelio.diagram.elements.core.obfactory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Factory that creates link Elements between a source MObject and a target Element.
 */
@objid ("80a8a46c-1dec-11e2-8cad-001ec947c8cc")
public interface IModelLinkFactory {
    /**
     * Create a link model element from the given source to the destination.
     * 
     * @param metaclass The metaclass of the link to create.
     * @param source The source element
     * @param target The destination element
     * @return The created link element
     * @throws java.lang.IllegalArgumentException if the asked metaclass is not a link metaclass
     * @throws java.lang.ClassCastException if one of the source or destination element is a bad class for the link.
     */
    @objid ("80a8a46e-1dec-11e2-8cad-001ec947c8cc")
    MObject createLink(MClass metaclass, MObject source, MObject target) throws ClassCastException, IllegalArgumentException;

}
