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
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("3e7401c4-a13c-4a3d-9a47-78c5df842a53")
public class ESendSignalAction extends EActivityNode {
    @objid ("931ae417-0fb9-4412-97b9-5df4effef765")
    private org.eclipse.uml2.uml.SendSignalAction ecoreElement = null;

    @objid ("15762331-bc07-49e9-9703-cf858ca8f9a5")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createSendSignalAction();
    }

    @objid ("cb1c6c3e-2c0f-4ee5-83fe-850fe3431482")
    public ESendSignalAction(org.eclipse.uml2.uml.SendSignalAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("1ece8fe7-86f9-45c6-a634-12dc40a26322")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setSignal((SendSignalAction) objingElt);
    }

    @objid ("1168d8d5-fc01-44c9-af37-6255f76a1d28")
    private void setSignal(SendSignalAction action) {
        org.eclipse.uml2.uml.Signal ecoreSignal = this.ecoreElement.getSignal();
               
                if (ecoreSignal != null) {
           Object objingSignal = ReverseProperties.getInstance().getMappedElement(ecoreSignal);
           if (objingSignal instanceof Signal)
               action.setSent((Signal) objingSignal);
                }
    }

}
