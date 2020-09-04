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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("f00f1a75-3d2a-4621-8996-769b69a93ff9")
public class ECallEvent extends ENamedElement {
    @objid ("9086cb58-bcd2-45d1-923c-a50689d0ab40")
    private org.eclipse.uml2.uml.CallEvent ecoreElement;

    @objid ("6ff6a063-9b60-4d52-ad57-dcbe684c55ff")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Transition){
            Event result = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createEvent();
            result.setKind(org.modelio.metamodel.uml.behavior.commonBehaviors.EventType.CALLEVENT);
            return result;
        }
        return null;
    }

    @objid ("8eae270c-1178-4279-bacb-894ec81174fe")
    public ECallEvent(org.eclipse.uml2.uml.CallEvent element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("b7f9ff28-8134-409e-8079-8f3a744d52c9")
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

    @objid ("4664265f-c75a-4ad6-a778-36edc21b9532")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setOperation(objingElt);
    }

    @objid ("f453a4d4-18dc-4888-9038-81ddef56e546")
    private void setOperation(Element objingElt) {
        org.eclipse.uml2.uml.Operation operation = this.ecoreElement.getOperation();
                if (operation != null){
           Element objOperation = (Element) ReverseProperties.getInstance().getMappedElement(operation);
           if (objOperation instanceof Operation){
               ((Event) objingElt).setCalled((Operation) objOperation);
           }
                }
    }

}
