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

package org.modelio.diagram.editor.object.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link ObjectDiagram} metaclasses.
 */
@objid ("a25fff12-5a62-11e2-9e33-00137282c51b")
public class ObjectDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("05df5d7c-5bed-11e2-9e33-00137282c51b")
    public ObjectDiagramEditorInputProvider() {
        super();
    }

    @objid ("05df5d7e-5bed-11e2-9e33-00137282c51b")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        AbstractDiagram diagram = (AbstractDiagram) modelManager.getModelServices().findByRef(new MRef(ObjectDiagram.MQNAME, diagramUID));
        return diagram != null ? new ObjectDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("715b5a88-43d9-4500-a17a-49e2319fcd17")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new ObjectGmDiagramCreator();
    }

}
