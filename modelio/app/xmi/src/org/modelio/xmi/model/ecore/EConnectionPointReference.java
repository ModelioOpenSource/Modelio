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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("2c4d0e08-4ea5-4c9a-be3b-d6e7a0a29ed2")
public class EConnectionPointReference extends ENamedElement {
    @objid ("ee2bebe6-729a-425d-85af-4e3b9b9d9b79")
    private org.eclipse.uml2.uml.ConnectionPointReference ecoreElement = null;

    @objid ("74a235b7-b86e-4044-933e-7cee2d4c92ba")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createConnectionPointReference();
    }

    @objid ("1d2d240e-5ca1-4e29-a9fc-be9131ec5d1f")
    public EConnectionPointReference(org.eclipse.uml2.uml.ConnectionPointReference element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("ad08e104-4f07-4b4c-995c-38475e76215b")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.State ecoreOwner = this.ecoreElement.getState();
        if (ecoreOwner != null){
            Object temp =  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            if( temp instanceof State){
                ((ConnectionPointReference) objingElt).setOwnerState((State) temp);
            }
            
        }
    }

    @objid ("cb8f5a30-651b-46f5-86b6-b18a60bbcc23")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setExit((ConnectionPointReference) objingElt);
        setEntry((ConnectionPointReference) objingElt);
    }

    @objid ("a5e35647-cf01-4595-83b7-a8914a54f348")
    private void setEntry(ConnectionPointReference objingElt) {
        for(Object connection : this.ecoreElement.getEntries()){
            Object temp =  ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)connection);
            if ((temp != null) && (temp instanceof EntryPointPseudoState)){
                objingElt.setEntry((EntryPointPseudoState) temp);
            }
        }
    }

    @objid ("41f27dd3-6a7a-4ff0-b056-0cf70ac76963")
    private void setExit(ConnectionPointReference objingElt) {
        for(Object connection : this.ecoreElement.getExits()){
            Object temp =  ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)connection);
            if ((temp != null) && (temp instanceof ExitPointPseudoState)){
                objingElt.setExit((ExitPointPseudoState) temp);
            }
        }
    }

}
