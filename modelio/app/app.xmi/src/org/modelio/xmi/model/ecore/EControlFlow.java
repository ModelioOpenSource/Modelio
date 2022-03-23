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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("e4736a5b-9bb4-497f-8f57-b96d20d926fa")
public class EControlFlow extends EActivityEdge {
    @objid ("4aff3546-0533-474a-ac76-a6ffc0180140")
    private org.eclipse.uml2.uml.ControlFlow ecoreElement = null;

    @objid ("b200bd92-7ed4-430c-834d-f5acfdab8a25")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.ActivityNode ecoreSource = this.ecoreElement.getSource();
        org.eclipse.uml2.uml.ActivityNode ecoreTarget = this.ecoreElement.getTarget();
        if ((ecoreSource != null) && (ecoreTarget != null)) {
            Object objingSource = ReverseProperties.getInstance().getMappedElement(ecoreSource);
            if ((objingSource != null) &&  (objingSource instanceof ActivityNode))
                return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createControlFlow();
        
        }
        return null;
    }

    @objid ("5e6149a4-37a8-4e66-ba8e-2ee2b1aedf25")
    public  EControlFlow(org.eclipse.uml2.uml.ControlFlow element) {
        super(element);
        this.ecoreElement = element;
        
    }

}
