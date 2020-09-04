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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("24e5cedc-b302-4fc2-80db-e1502a837ead")
public class EState extends ENamedElement {
    @objid ("e22b8858-7278-46b1-8119-6fee5617d225")
    private org.eclipse.uml2.uml.State ecoreElement = null;

    @objid ("422c5230-1d74-400f-b874-d2184324ca37")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createState();
    }

    @objid ("9bfe93e2-7acf-45b5-9cac-e04d35788b08")
    public EState(org.eclipse.uml2.uml.State element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("f3bce808-f774-430c-9341-99f67ee6e0c0")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Region ecoreContainer = this.ecoreElement.getContainer();
        
        if (ecoreContainer != null) {
            Object objingContainer = ReverseProperties.getInstance().getMappedElement(ecoreContainer);
        
            if ((objingContainer instanceof Region) && (objingElt instanceof State)){
                ((Region) objingContainer).getSub().add((State) objingElt);
            }else{
                objingElt.delete();
                String message = "Owner of state was " + ecoreContainer.getClass().getSimpleName();
                ReverseProperties.getInstance().addError(message);
            }
        
        }else{
            objingElt.delete();
            String message = "The state named " + this.ecoreElement.getName() + " has not owner";
            ReverseProperties.getInstance().addError(message);
        }
    }

    @objid ("220ff27e-36c0-42be-850a-6b07057e9939")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof State) {            
            setSubMachine((State) objingElt);
        }
    }

    @objid ("77423b40-cd10-45de-9f31-731366346113")
    private void setSubMachine(State state) {
        org.eclipse.uml2.uml.StateMachine ecoreSubMachine = this.ecoreElement.getSubmachine();
        if (ecoreSubMachine != null) {
            Object objingSubMachine = ReverseProperties.getInstance().getMappedElement(ecoreSubMachine);
            if (objingSubMachine instanceof StateMachine) {
                state.setSubMachine((StateMachine) objingSubMachine);
            }
        }
    }

}
