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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("50c15a1d-a07c-45de-9343-64cf62432269")
public class ETimeEvent extends ENamedElement {
    @objid ("e38d0093-157d-42ca-89ac-11586dc4279d")
    private org.eclipse.uml2.uml.TimeEvent ecoreElement;

    @objid ("a0dab2f8-bfa9-413a-8e47-eae9c23e041b")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        Element objingOwner = (Element) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        if (objingOwner instanceof Behavior){
            Event result = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createEvent();
            result.setKind(org.modelio.metamodel.uml.behavior.commonBehaviors.EventType.TIMEEVENT);
            return result;
        }else
            return null;
        
    }

    @objid ("5a3622cc-a2b3-47a8-89a2-faefa3fc2ef0")
    public  ETimeEvent(org.eclipse.uml2.uml.TimeEvent element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("a6ca8d23-f8ae-4475-a0ae-8e435aa7b1c6")
    @Override
    public void attach(Element objingElt) {
        if (((Event) objingElt).getComposed() == null){
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
            if (ecoreOwner instanceof  org.eclipse.uml2.uml.Transition){
                StateMachine sm = (StateMachine) ReverseProperties.getInstance().getMappedElement(( (org.eclipse.uml2.uml.Transition) ecoreOwner).getContainer().getStateMachine());
                sm.getEComponent().add((Event)objingElt);
            }else{
                objingElt.delete();
            }
        }
        
    }

    @objid ("3f5f9e3b-a10d-462c-927c-7520ea887e21")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setExpression((Event)objingElt);
        
    }

    @objid ("3cefd2a2-2e58-4efd-b404-8279aa542373")
    private void setExpression(Event objingElt) {
        org.eclipse.uml2.uml.ValueSpecification value = this.ecoreElement.getWhen();
        
        if (value != null){
            String expr = value.stringValue();
            if (expr != null){
                objingElt.setExpression(expr);
            }
        }
        
    }

}
