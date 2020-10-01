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

package org.modelio.bpmn.diagram.editor.elements.bpmnintermediatecatchevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent.ThrowCatchEventMatchingFeedbackEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;

@objid ("b9410114-e4eb-435d-b0e3-70c735d967cd")
public class BpmnIntermediateCatchEventEditPart extends PortContainerEditPart {
    @objid ("6924f621-893d-43dc-b699-bc49b8150a00")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(ThrowCatchEventMatchingFeedbackEditPolicy.class.getName(), new ThrowCatchEventMatchingFeedbackEditPolicy());
    }

}
