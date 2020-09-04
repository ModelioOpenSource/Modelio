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
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("3d3e7fe2-486d-4d0f-b2ca-d1bc83a1705d")
public class EMessageOccurrenceSpecification extends EOccurrenceSpecification {
    @objid ("e0fce75b-0015-432d-880a-5889053c87bc")
    public EMessageOccurrenceSpecification(org.eclipse.uml2.uml.MessageOccurrenceSpecification element) {
        super(element);
    }

    @objid ("a8eb54c7-a84b-4f75-8708-f261fcf904ab")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setStartOrEnd(objingElt);
    }

    @objid ("bbf5febf-a345-4540-aad5-c9d1b8e7673a")
    private void setStartOrEnd(Element objingElt) {
        org.eclipse.uml2.uml.MessageOccurrenceSpecification ecoreElt = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) getEcoreElement();
        org.eclipse.uml2.uml.Message ecoreMessage = ecoreElt.getMessage(); 
        Object objMessage = ReverseProperties.getInstance().getMappedElement(ecoreMessage);
        
        if ((ecoreMessage != null)
                && (objMessage != null)
                && (objMessage instanceof Message)
                && (objingElt instanceof MessageEnd)){
            
            if (ecoreMessage.getSendEvent().equals(ecoreElt)){
                ((Message) objMessage).setSendEvent((MessageEnd) objingElt);
            }else{
                ((Message) objMessage).setReceiveEvent((MessageEnd) objingElt);
            }
            
        }
    }

}
