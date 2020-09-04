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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("735a9ae1-c2ef-475e-a288-47426280d4b6")
public class EFunctionBehavior extends ENamedElement {
    @objid ("18f448ea-4773-4e04-8a6b-57b69365d6d2")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("8dc0943a-df9b-4c1c-a6ef-66c7a873614c")
    public EFunctionBehavior(org.eclipse.uml2.uml.FunctionBehavior element) {
        super(element);
    }

}
