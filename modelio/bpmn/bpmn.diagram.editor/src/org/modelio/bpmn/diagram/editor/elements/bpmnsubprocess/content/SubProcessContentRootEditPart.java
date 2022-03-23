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
package org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.content;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.common.embeddeddiagram.EmbeddedDiagramRootEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;

/**
 * Edit part for the {@link GmBpmnSubProcessContent}.
 */
@objid ("dd5a6459-2c38-4af5-bd8d-983ed266b056")
public class SubProcessContentRootEditPart extends EmbeddedDiagramRootEditPart {
    @objid ("5ab1a18c-a5f6-470e-8284-751668df55a7")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AutoExpandLayoutEditPolicy.class, new AutoExpandLayoutEditPolicy());
        
    }

    @objid ("4e1e5d20-9b24-4a5b-a5a8-f13ba78e6788")
    public  SubProcessContentRootEditPart(EditPart parent, Object model) {
        super(parent, model);
    }

}
