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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;

@objid ("04c508bb-edb2-416d-a779-b4ac7af394e5")
public class BpmnIntermediateThrowEventEditPart extends PortContainerEditPart {
    @objid ("a038fe45-856f-49fb-88cc-0b94a127126c")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(ThrowCatchEventMatchingFeedbackEditPolicy.class.getName(), new ThrowCatchEventMatchingFeedbackEditPolicy());
    }

}
