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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("58a55940-9498-4658-b941-f5e8412560ac")
public class OAcceptSignalAction extends OActivityNode {
    @objid ("3358b0d2-1da9-42b6-adcf-cafe100bbb08")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createAcceptEventAction();
    }

    @objid ("0cff6ca0-b0fe-4f13-bff2-c1d9420677ab")
    public OAcceptSignalAction(AcceptSignalAction element) {
        super(element);
    }

    @objid ("7b4ef53e-6ff9-42ba-9c6d-0d5750ee31c6")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("28827c66-b44d-423a-8b94-735e375a0c53")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setAccepted( (org.eclipse.uml2.uml.AcceptEventAction) ecoreElt);
        setSignal( (org.eclipse.uml2.uml.AcceptEventAction) ecoreElt);
    }

    @objid ("88a57a71-7731-4bde-bfe4-de8d7a37755f")
    private void setAccepted(org.eclipse.uml2.uml.AcceptEventAction action) {
        for (Signal objingSignal : getObjingElement().getAccepted()) {
            org.eclipse.uml2.uml.Element ecoreSignal = GenerationProperties.getInstance().getMappedElement(objingSignal);
            if (ecoreSignal instanceof  org.eclipse.uml2.uml.Signal) {
                org.eclipse.uml2.uml.Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
                org.eclipse.uml2.uml.SignalEvent signalEvent = UMLFactory.eINSTANCE
                        .createSignalEvent();
                action.getTriggers().add(trigger);
                trigger.setEvent(signalEvent);
                signalEvent.setSignal( (org.eclipse.uml2.uml.Signal) ecoreSignal);
        
                // Attach the  org.eclipse.uml2.uml.Event to the model via composition relation:
                org.eclipse.uml2.uml.Package nearestPkg = action.getNearestPackage();
                if (nearestPkg != null)
                    nearestPkg.getPackagedElements().add(signalEvent);
                else
                    signalEvent.destroy();
            }
        }
    }

    @objid ("e2b9fc36-a6be-4a86-be32-1b8c4aa9d461")
    private void setSignal(final org.eclipse.uml2.uml.AcceptEventAction ecoreElt) {
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            ObjingEAnnotation.setSignal(ecoreElt, "signal");
        }
    }

    @objid ("ea0444c4-1a2a-4b2e-9361-76bd4a9b443f")
    @Override
    public AcceptSignalAction getObjingElement() {
        return (AcceptSignalAction) super.getObjingElement();
    }

}
