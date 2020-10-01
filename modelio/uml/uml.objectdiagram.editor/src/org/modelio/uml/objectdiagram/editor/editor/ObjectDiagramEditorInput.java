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

package org.modelio.uml.objectdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Use Case Diagram editor input.
 */
@objid ("9d5eaced-55b6-11e2-877f-002564c97630")
public class ObjectDiagramEditorInput extends DiagramEditorInput {
    /**
     * Initialize the editor input.
     * <p>
     * Creates the diagram graphic model and load it from the diagram model element.
     * 
     * @param modelManager the link between the Gm model and the Ob model.
     * @param diagram the diagram to edit.
     * @param gmDiagramCreator a small factory to instanciate the Gm diagram itself.
     */
    @objid ("9d5eacfe-55b6-11e2-877f-002564c97630")
    public ObjectDiagramEditorInput(IModelManager modelManager, AbstractDiagram diagram, GmDiagramCreator gmDiagramCreator) {
        super(diagram, modelManager, gmDiagramCreator);
    }

    @objid ("7e8eb6b8-5095-42fb-8ab6-08708f06187e")
    @Override
    public String getEditorId() {
        return ObjectDiagramEditor.ID;
    }

}
