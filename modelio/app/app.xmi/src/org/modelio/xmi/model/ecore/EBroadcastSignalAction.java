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
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("e5b9ff4a-ade7-4a5c-80bd-6fc3af9326dd")
public class EBroadcastSignalAction extends EActivityNode {
    @objid ("b6917698-1949-42f0-a89d-b8a364ea0afd")
    private org.eclipse.uml2.uml.BroadcastSignalAction ecoreElement = null;

    @objid ("b01bb94a-90c3-4340-bce8-1d4fed981b15")
    @Override
    public Element createObjingElt() {
        return UML2BroadcastSignalAction.create().getElement();
    }

    @objid ("85e78ffa-c7a3-47d3-8922-bae09f47bd3d")
    public  EBroadcastSignalAction(org.eclipse.uml2.uml.BroadcastSignalAction element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("39d455db-93ae-4543-8ac5-1c3eec6b6dcf")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setSignal((SendSignalAction) objingElt);
        
    }

    @objid ("03abb2bd-286b-44de-a7d7-747a0b4afe00")
    private void setSignal(SendSignalAction action) {
        org.eclipse.uml2.uml.Signal ecoreSignal = this.ecoreElement.getSignal();
        
        if (ecoreSignal != null) {
            Object objingSignal =  ReverseProperties.getInstance().getMappedElement(ecoreSignal);
            if (objingSignal instanceof Signal)
                action.setSent((Signal) objingSignal);
        }
        
    }

}
