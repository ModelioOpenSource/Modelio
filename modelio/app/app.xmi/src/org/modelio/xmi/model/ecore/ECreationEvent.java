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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("8d378d9d-44ca-4d56-9747-d92ed2246a5f")
public class ECreationEvent extends ENamedElement {
    @objid ("99e84cd8-1bc3-4481-aa70-6834c7d97a97")
    @Override
    public Element createObjingElt() {
        Object objingOwner = ReverseProperties.getInstance().getMappedElement(getEcoreElement().getOwner());
        
        if (objingOwner instanceof Behavior){      
            return UML2CreationEvent.create().getElement();
        }
        return null;
    }

    @objid ("0e66e4fe-1f70-4b97-a596-9b850372eab9")
    public ECreationEvent(org.eclipse.uml2.uml.CreationEvent element) {
        super(element);
    }

}
