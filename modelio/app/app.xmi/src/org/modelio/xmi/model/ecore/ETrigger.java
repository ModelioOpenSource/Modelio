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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("a8c19fa8-8b2e-46a4-add2-700e175bd947")
public class ETrigger extends ENamedElement {
    @objid ("f5af15b8-ef96-4cd9-a134-2d514bbbc864")
    private org.eclipse.uml2.uml.Trigger ecoreElement = null;

    @objid ("4d603b52-8a91-4ace-85aa-3ddb496ba5d7")
    @Override
    public Element createObjingElt() {
        createAndAttachEvent();
        return null;
    }

    @objid ("cbcd22d8-faaa-47a6-b56e-5d23a19188df")
    public ETrigger(org.eclipse.uml2.uml.Trigger element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("0a304634-9465-401a-b1d9-db5c3aff0646")
    private void createAndAttachEvent() {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreEvent = this.ecoreElement.getEvent();
        if (this.ecoreElement.getEvent() != null){
            Element objEvent = (Element) revProp.getMappedElement(ecoreEvent);
            if (objEvent != null){
                org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
                if (ecoreOwner instanceof  org.eclipse.uml2.uml.Transition){
                    StateMachine sm = (StateMachine) revProp.getMappedElement(EcoreModelNavigation.getMostEnclosingStateMachine( (org.eclipse.uml2.uml.Transition) ecoreOwner));
                    sm.getEComponent().add((Event)objEvent);
                    setEventProperties((Event)objEvent);
                }else if (ecoreOwner instanceof org.eclipse.uml2.uml.StateMachine){
                    StateMachine sm = (StateMachine) revProp.getMappedElement(ecoreOwner);
                    sm.getEComponent().add((Event)objEvent);
                    setEventProperties((Event)objEvent);
                }else{
                    objEvent.delete();
                }
            }
        }
    }

    @objid ("0a69fd70-c069-4949-8c6f-20e84c8cadc4")
    private void setEventProperties(Event objingElt) {
        org.eclipse.uml2.uml.Event event = this.ecoreElement.getEvent();
        
        if (event != null){
            setName(objingElt, event);
            if (event instanceof  org.eclipse.uml2.uml.SignalEvent){
                setSignal(objingElt, (org.eclipse.uml2.uml.SignalEvent)event);
            }else     if (event instanceof  org.eclipse.uml2.uml.CallEvent){
                setOperation(objingElt, (org.eclipse.uml2.uml.CallEvent)event);
            }else     if (event instanceof  org.eclipse.uml2.uml.ChangeEvent){
                setExpression(objingElt,  (org.eclipse.uml2.uml.ChangeEvent) event);
            }else     if (event instanceof  org.eclipse.uml2.uml.TimeEvent){
                setExpression(objingElt,  (org.eclipse.uml2.uml.TimeEvent) event);
            }
        }
    }

    @objid ("cbf24bef-4a86-4f5a-884e-9354807abe42")
    private void setName(Element objingElt, org.eclipse.uml2.uml.Event event) {
        String name = event.getName();
        if (name != null){
            ((Event) objingElt).setName(name);
        }
    }

    @objid ("7b845b58-e618-43f4-b110-cadcd87ab4e5")
    private void setSignal(Element objingElt, org.eclipse.uml2.uml.SignalEvent event) {
        org.eclipse.uml2.uml.Signal signal =    event.getSignal();
        if (signal != null){
            Element objSignal = (Element) ReverseProperties.getInstance().getMappedElement(signal);
            if (objSignal instanceof Signal){
                ((Event) objingElt).setModel((Signal) objSignal);
            }
        }
    }

    @objid ("fdbf5ff6-18d8-46bd-900a-82888b68efbb")
    private void setOperation(Element objingElt, org.eclipse.uml2.uml.CallEvent event) {
        org.eclipse.uml2.uml.Operation operation =    event.getOperation();
        if (operation != null){
            Element objOperation = (Element) ReverseProperties.getInstance().getMappedElement(operation);
            if (objOperation instanceof Operation){
                ((Event) objingElt).setCalled((Operation) objOperation);
            }
        }
    }

    @objid ("f1a8f259-ff0a-4c09-942f-d9db99052692")
    private void setExpression(Event objingElt, org.eclipse.uml2.uml.ChangeEvent event) {
        org.eclipse.uml2.uml.ValueSpecification value = event.getChangeExpression();
        
        if (value != null){
        
            String typeString = value.stringValue();
            if (typeString != null)
                objingElt.setExpression(typeString);
            else{
                boolean bool = value.booleanValue();
                if (bool)
                    objingElt.setExpression(String.valueOf(bool));
                else{
                    Integer inte = value.integerValue();
                    objingElt.setExpression(String.valueOf(inte));
                }
            }
        }
    }

    @objid ("5cc0e464-2ac4-4105-b88d-e8d9106685fe")
    private void setExpression(Event objingElt, org.eclipse.uml2.uml.TimeEvent event) {
        org.eclipse.uml2.uml.ValueSpecification value =    event.getWhen();
        
        if (value != null){
            String expr = value.stringValue();
            if (expr != null){
                objingElt.setExpression(expr);
            }
        }
    }

}
