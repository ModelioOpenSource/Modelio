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

package org.modelio.bpmn.diagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link BpmnProcessCollaborationDiagram} and {@link BpmnSubProcessDiagram} metaclasses.
 */
@objid ("3209cddb-5973-11e2-ae45-002564c97630")
public class BpmnDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("c4a34411-59a6-11e2-ae45-002564c97630")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        AbstractDiagram diagram = (AbstractDiagram) modelManager.getModelServices().findByRef(new MRef(BpmnProcessCollaborationDiagram.MQNAME, diagramUID));
        if (diagram == null) {
            diagram = (AbstractDiagram) modelManager.getModelServices().findByRef(new MRef(BpmnSubProcessDiagram.MQNAME, diagramUID));
        }
        return diagram != null ? new BpmnDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("e06c5ac9-4c2d-4730-8755-343223a34815")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new BpmnGmDiagramCreator();
    }

}
