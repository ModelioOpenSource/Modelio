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
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ModelUtils;

/**
 * This class handles the import of Ecore Opaque org.eclipse.uml2.uml. Behavior
 * @author ebrosse
 */
@objid ("c77eb15b-69ea-4781-938b-b3c2508c8cfe")
public class EOpaqueBehavior extends ENamedElement {
    @objid ("58f43136-4f98-4877-be5f-f05197342fab")
    private org.eclipse.uml2.uml.OpaqueBehavior ecoreElement = null;

    @objid ("ab3f1ce7-6e13-455f-ab46-37fe1e815f94")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        Object objOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
        if (objOwner instanceof Transition){                
            ModelUtils.transitionMapping(this.ecoreElement, (Transition) objOwner);
            return null;
        }else if ((objOwner instanceof State) && (ecoreOwner instanceof org.eclipse.uml2.uml.State)){            
            ModelUtils.stateMapping(this.ecoreElement, (State) objOwner, (org.eclipse.uml2.uml.State) ecoreOwner);
            return null;
        }else
            return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueBehavior();
    }

    @objid ("cb8f2e40-7e47-4f14-b43c-830f47819bc4")
    public EOpaqueBehavior(org.eclipse.uml2.uml.OpaqueBehavior element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("41ce48fa-5f97-4480-9ecf-c8ef1d83be51")
    @Override
    public void attach(Element objingElt) {
        NameSpace objingOwner = EcoreModelNavigation.getNearestOpaqueBehaviorOwner(this.ecoreElement);
        
        if (objingOwner != null){
            ((OpaqueBehavior) objingElt).setOwner(objingOwner);
        }else{
            objingElt.delete();
        }
    }

    @objid ("ce06d0ae-3c51-4625-abcc-ff61f22b45a9")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setBehaviorEffect((OpaqueBehavior) objingElt);
        setBody((OpaqueBehavior) objingElt);
    }

    @objid ("2ae49882-00a3-4511-ab93-63f69685c7bd")
    private void setBody(OpaqueBehavior objingElt) {
        StringBuffer body = new StringBuffer();
        for (String ecoreBody : this.ecoreElement.getBodies()){
            body.append(ecoreBody);
        }
        
        if (objingElt != null){
            objingElt.setBody(body.toString());
        }
    }

    @objid ("6ced2331-33dd-45e7-b14e-c7a5585269d3")
    private void setBehaviorEffect(OpaqueBehavior objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        if (ecoreOwner != null) {
            Object objingOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            if ((objingOwner instanceof Transition)) {
                ((Transition) objingOwner).setBehaviorEffect(objingElt);
            }
        }
    }

}
