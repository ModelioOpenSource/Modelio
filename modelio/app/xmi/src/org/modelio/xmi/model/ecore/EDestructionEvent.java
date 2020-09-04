/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("a74c1765-9fae-4e20-9aa1-ace01f164cec")
public class EDestructionEvent extends ENamedElement {
    @objid ("d20d75da-a3c2-46d0-b42b-a7e72a486e85")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        Element objingOwner = (Element) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        if (objingOwner instanceof Behavior){
        
            IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
            Event result = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createEvent();
        
            try {
                result.getExtension().add(mmServices.getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DESTRUCTIONEVENT, objingOwner.getMClass().getMetamodel().getMClass(Event.class)));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(e);
            }
        
            return result;
        }
        return null;
    }

    @objid ("e5451475-b325-4b02-829e-4dade34e8636")
    public EDestructionEvent(org.eclipse.uml2.uml.DestructionEvent element) {
        super(element);
    }

}
