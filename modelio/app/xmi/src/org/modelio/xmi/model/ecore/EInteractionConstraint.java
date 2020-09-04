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

@objid ("002f8b83-e7c8-444a-962d-f313a1b9ebe1")
public class EInteractionConstraint extends ENamedElement {
    @objid ("9e706169-18e9-4ea4-8af1-a57df1001478")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("58028c98-f69c-47d7-a987-1d32273549b1")
    public EInteractionConstraint(org.eclipse.uml2.uml.InteractionConstraint element) {
        super(element);
    }

}
