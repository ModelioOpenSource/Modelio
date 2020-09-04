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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("cd33ec80-9db4-447d-9a03-38f1a80bde7a")
public class EAcceptCallAction extends EAcceptEventAction {
    @objid ("97b771db-32a0-455f-8e26-4150ba9c5f54")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createAcceptCallEventAction();
    }

    @objid ("599fb3ae-685c-4ffc-9e6a-d516860a197e")
    public EAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction element) {
        super(element);
    }

    @objid ("c3d2a4d0-1a43-4405-905b-4f049d3e2ab4")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setCalled((AcceptCallEventAction) objingElt);
    }

    @objid ("e30fb12a-a5ea-4a25-9d22-5d8d5f0b5aa4")
    private void setCalled(AcceptCallEventAction action) {
        for (Object trigger :  ( (org.eclipse.uml2.uml.AcceptEventAction) getEcoreElement()).getTriggers()) {
            org.eclipse.uml2.uml.Event event = ( (org.eclipse.uml2.uml.Trigger) trigger).getEvent();
            if (event instanceof  org.eclipse.uml2.uml.CallEvent) {
                org.eclipse.uml2.uml.Operation ecoreOperation = ( (org.eclipse.uml2.uml.CallEvent) event)
                        .getOperation();
                if (ecoreOperation != null) {
                    Object objingOperation =  ReverseProperties.getInstance()
                            .getMappedElement(ecoreOperation);
                    if (objingOperation instanceof Operation) {
                        action.setCalled((Operation) objingOperation);
                        break;
                    }
                }
            }
        }
    }

}
