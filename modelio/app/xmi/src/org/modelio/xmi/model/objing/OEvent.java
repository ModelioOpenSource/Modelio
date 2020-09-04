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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("81806c76-d9a2-4ce0-85de-683203b9b685")
public class OEvent extends OModelElement {
    @objid ("80f35277-703a-4f13-b158-72cb9ec13b59")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        EventType eventType = getObjingElement().getKind();
        if (eventType != null){
            switch (eventType){
                case CALLEVENT :
                    return UMLFactory.eINSTANCE.createCallEvent();
                case SIGNALEVENT :
                    return UMLFactory.eINSTANCE.createSignalEvent();
                case TIMEEVENT :
                    return UMLFactory.eINSTANCE.createTimeEvent();
                case CHANGEEVENT :
                    return UMLFactory.eINSTANCE.createChangeEvent();
            default:
                return UMLFactory.eINSTANCE.createCallEvent();
        
            }
        }
        
        if (getObjingElement().isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CREATIONEVENT)){
            return UMLFactory.eINSTANCE.createCreationEvent();
        }else if (getObjingElement().isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DESTRUCTIONEVENT)){
            return UMLFactory.eINSTANCE.createDestructionEvent();
        }else if (getObjingElement().isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXECUTIONEVENT)){
            return UMLFactory.eINSTANCE.createExecutionEvent();
        }
        return null;
    }

    @objid ("bf3e02b6-5a4d-43d6-b1a4-a1a9198a66c5")
    public OEvent(Event event) {
        super(event);
    }

    @objid ("53e82951-8555-423c-ba3c-bd4e0957acfc")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Behavior oldOwner = getObjingElement().getComposed();
        
        Package newOwner = AbstractObjingModelNavigation.getNearestPackage(oldOwner);
        
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(oldOwner);
        
        if ((newOwner != null) &&
                ((ecoreOwner != null) && ((ecoreOwner instanceof org.eclipse.uml2.uml.BehavioredClassifier) || (ecoreOwner instanceof  org.eclipse.uml2.uml.Transition)
                        || (ecoreOwner instanceof org.eclipse.uml2.uml.State) || (ecoreOwner instanceof  org.eclipse.uml2.uml.AcceptEventAction) ))){
        
            org.eclipse.uml2.uml.Element newEcoreOwner = GenerationProperties.getInstance().getMappedElement(newOwner);
        
            if ((newEcoreOwner != null) && (newEcoreOwner instanceof org.eclipse.uml2.uml.Package))
                ((org.eclipse.uml2.uml.Package) newEcoreOwner).getPackagedElements().add((org.eclipse.uml2.uml.Event)ecoreElt);
        
        }
    }

    @objid ("72d42797-76b6-4b76-afa8-ebbd738404ef")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof  org.eclipse.uml2.uml.Event ){
            super.setProperties(ecoreElt);
            if (ecoreElt instanceof  org.eclipse.uml2.uml.CallEvent ){
                setCallProperties( (org.eclipse.uml2.uml.CallEvent) ecoreElt);
            }else     if (ecoreElt instanceof  org.eclipse.uml2.uml.SignalEvent ){
                setSignalProperties( (org.eclipse.uml2.uml.SignalEvent) ecoreElt);
            }else     if (ecoreElt instanceof  org.eclipse.uml2.uml.TimeEvent ){
                setTimeProperties( (org.eclipse.uml2.uml.TimeEvent) ecoreElt);
            }else     if (ecoreElt instanceof  org.eclipse.uml2.uml.ChangeEvent ){
                setChangeProperties( (org.eclipse.uml2.uml.ChangeEvent) ecoreElt);
            }
        }
    }

    @objid ("bb37a16f-8c94-409a-9666-49ad43fbe789")
    private void setChangeProperties(org.eclipse.uml2.uml.ChangeEvent ecoreElt) {
        String changeValue = getObjingElement().getExpression();
        if ((changeValue != null) && (!changeValue.equals(""))){
            org.eclipse.uml2.uml.LiteralString literalString = UMLFactory.eINSTANCE.createLiteralString();
             org.eclipse.uml2.uml.ValueSpecification ecoreValue = ecoreElt.createChangeExpression("",
                     GenerationProperties.getInstance().getEcoreUMLTypes().getString(), literalString.eClass());
            ((org.eclipse.uml2.uml.LiteralString)ecoreValue).setValue(changeValue);
        }
    }

    @objid ("e9d2c019-db10-45e9-ae88-a091e46a8663")
    private void setTimeProperties(org.eclipse.uml2.uml.TimeEvent ecoreElt) {
        String timeValue = getObjingElement().getExpression();
        if ((timeValue != null)&& (!timeValue.equals(""))){
            org.eclipse.uml2.uml.TimeExpression ecoreValue = ecoreElt.createWhen("",
                    GenerationProperties.getInstance().getEcoreUMLTypes().getString());
            org.eclipse.uml2.uml.LiteralString value = UMLFactory.eINSTANCE.createLiteralString();
            value.setValue(timeValue);
            ecoreValue.setExpr(value);
        }
    }

    @objid ("851d63b8-9a35-4923-b93c-07c5a7d8b7a7")
    private void setSignalProperties(org.eclipse.uml2.uml.SignalEvent ecoreElt) {
        Signal objSignal = getObjingElement().getModel();
        if (objSignal != null) {
            org.eclipse.uml2.uml.Element ecoreSignal = GenerationProperties.getInstance().getMappedElement(objSignal);
            if (ecoreSignal instanceof  org.eclipse.uml2.uml.Signal)
                ecoreElt.setSignal( (org.eclipse.uml2.uml.Signal) ecoreSignal);
        }
    }

    @objid ("36613bac-fe13-4431-b431-5a0e18f592f2")
    private void setCallProperties(org.eclipse.uml2.uml.CallEvent ecoreElt) {
        Operation objOperation = getObjingElement().getCalled();
        if (objOperation != null) {
            org.eclipse.uml2.uml.Element ecoreOperation = GenerationProperties.getInstance().getMappedElement(objOperation);
            if (ecoreOperation instanceof  org.eclipse.uml2.uml.Operation)
                ecoreElt.setOperation( (org.eclipse.uml2.uml.Operation) ecoreOperation);
        }
    }

    @objid ("ef84b970-79c5-4842-909b-3a1ade20a35b")
    @Override
    public Event getObjingElement() {
        return (Event) super.getObjingElement();
    }

}
