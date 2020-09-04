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

package org.modelio.diagram.editor.bpmn.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess.GmBpmnSubProcessDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("12216caf-756b-488a-b4fb-379b675d61f3")
public final class BpmnGmDiagramCreator implements GmDiagramCreator {
    @objid ("d52eee90-ee18-4269-92b7-8ae8966833f3")
    @Override
    public GmAbstractDiagram createDiagram(IModelManager modelManager, AbstractDiagram diagram) {
        if (diagram instanceof BpmnCollaborationDiagram) {
            return new GmBpmnProcessCollaborationDiagram(modelManager, (BpmnCollaborationDiagram) diagram, new MRef(diagram));
        } else if (diagram instanceof BpmnProcessDesignDiagram) {
            return new GmBpmnProcessDesignDiagram(modelManager, (BpmnProcessDesignDiagram) diagram, new MRef(diagram));
        } else if (diagram instanceof BpmnSubProcessDiagram) {
            return new GmBpmnSubProcessDiagram(modelManager, (BpmnSubProcessDiagram) diagram, new MRef(diagram));
        }
        return null;
    }

}
