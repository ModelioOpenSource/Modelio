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

package org.modelio.diagram.editor.bpmn.elements.participant.content;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.common.embeddeddiagram.EmbeddedDiagramRootEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;

@objid ("0228169e-3c04-4931-b6d3-43832744944b")
public class ParticipantContentRootEditPart extends EmbeddedDiagramRootEditPart {
    @objid ("3399079b-9615-42a4-945e-dc3b3de21c93")
    public ParticipantContentRootEditPart(EditPart parent, Object model) {
        super(parent, model);
    }

    @objid ("9a566ade-2ba8-4a36-8945-95d160170805")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AutoExpandLayoutEditPolicy.class, new AutoExpandLayoutEditPolicy());
    }

}
