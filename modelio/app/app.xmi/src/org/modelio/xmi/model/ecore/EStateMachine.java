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
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("cbd05ffc-789d-4b54-bde5-8fb996479112")
public class EStateMachine extends ENamedElement {
    @objid ("24da3d7d-ee8b-4301-bf03-7af802292a42")
    private org.eclipse.uml2.uml.StateMachine ecoreElement = null;

    @objid ("629b459b-0c53-4fa5-9e93-51d5c7b8fad8")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createStateMachine();
    }

    @objid ("cf4053f0-7a12-4805-b119-262fc2dd62eb")
    public EStateMachine(org.eclipse.uml2.uml.StateMachine element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("b2f936ab-07a0-4d32-8f4d-27f0ff1f757c")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        Element objingOwner = (Element) ReverseProperties.getInstance()
                .getMappedElement(ecoreOwner);
        if (ecoreOwner != null) {
            if (objingOwner instanceof NameSpace){
                ((Behavior) objingElt).setOwner((NameSpace)objingOwner);
            }else if (objingOwner instanceof Operation){
                ((Behavior) objingElt).setOwnerOperation((Operation)objingOwner);
            }else{
                objingOwner = EcoreModelNavigation.getNearestStateMachineOwner(this.ecoreElement);
        
                if (objingOwner instanceof NameSpace){
                    ((Behavior) objingElt).setOwner((NameSpace)objingOwner);
                }else if (objingOwner instanceof Operation){
                    ((Behavior) objingElt).setOwnerOperation((Operation)objingOwner);
                }
        
            }
        }
    }

    @objid ("cb08ebb3-f8b6-4316-a078-21646403330c")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setKind((StateMachine) objingElt);
        if (ReverseProperties.getInstance().isRoundtripEnabled())
            setReentrant((StateMachine) objingElt);
    }

    @objid ("9f91b136-a542-4e34-98ab-61905eec13f0")
    private void setReentrant(StateMachine objingElt) {
        objingElt.setIsReentrant(ObjingEAnnotation.isReentrant(this.ecoreElement));
    }

    @objid ("d89bbe7d-0be3-466f-b1b3-e45c3725847b")
    private void setKind(StateMachine machine) {
        if (this.ecoreElement instanceof org.eclipse.uml2.uml.ProtocolStateMachine)
            machine.setKind(KindOfStateMachine.PROTOCOL);
        else
            machine.setKind(KindOfStateMachine.DYNAMIC);
    }

}
