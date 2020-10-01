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

package org.modelio.uml.deploymentdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Deployment Diagram editor input.
 */
@objid ("970faf5e-55b6-11e2-877f-002564c97630")
public class DeploymentDiagramEditorInput extends DiagramEditorInput {
    /**
     * Initialize the editor input.
     * <p>
     * Creates the diagram graphic model and load it from the diagram model element.
     * 
     * @param modelManager the link between the Gm model and the Ob model.
     * @param diagram the diagram to edit.
     * @param gmDiagramCreator a small factory to instanciate the Gm diagram itself.
     */
    @objid ("970faf62-55b6-11e2-877f-002564c97630")
    public DeploymentDiagramEditorInput(IModelManager modelManager, AbstractDiagram diagram, GmDiagramCreator gmDiagramCreator) {
        super(diagram, modelManager, gmDiagramCreator);
    }

    @objid ("d32ce559-4711-4a27-9431-e773b0c80d1c")
    @Override
    public String getEditorId() {
        return DeploymentDiagramEditor.ID;
    }

}
