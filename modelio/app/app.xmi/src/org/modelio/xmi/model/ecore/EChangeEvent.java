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
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("106b3af4-d671-4e51-959a-60456d7d6f57")
public class EChangeEvent extends ENamedElement {
    @objid ("302183e0-182c-47cb-8797-988b615cbc79")
    private org.eclipse.uml2.uml.ChangeEvent ecoreElement;

    @objid ("ed15aeb3-bef1-418f-84e7-0c7196d0c2f6")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        Element objingOwner = (Element) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        if (objingOwner instanceof Behavior){
            Event result = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createEvent();
            result.setKind(EventType.CHANGEEVENT);
            return result;
        }
        return null;
    }

    @objid ("cd37a170-f5b1-41df-8e77-d12dd5269b59")
    public  EChangeEvent(org.eclipse.uml2.uml.ChangeEvent element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("7b707694-5d5e-46e3-92ea-d82f07843a38")
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

    @objid ("5ca84467-2f95-44f9-8f05-8d2d0fa50fb6")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setExpression((Event) objingElt);
        
    }

    @objid ("9d798cca-c993-4447-8488-6ed3f8b20cdd")
    private void setExpression(Event objingElt) {
        org.eclipse.uml2.uml.ValueSpecification value = this.ecoreElement.getChangeExpression();
        
        if (value != null){
        
            String typeString = value.stringValue();
            if (typeString != null)
                objingElt.setExpression(typeString);
            else{
                boolean bool = value.booleanValue();
                if (bool)
                    objingElt.setExpression(String.valueOf(bool));
                else{
                    objingElt.setExpression(String.valueOf(value.integerValue()));
                }
            }
        }
        
    }

}
