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

package org.modelio.diagram.editor.composite.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link CompositeStructureDiagram} metaclasses.
 */
@objid ("a80425ba-5a44-11e2-9e33-00137282c51b")
public class CompositeDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("b72410aa-5a44-11e2-9e33-00137282c51b")
    public CompositeDiagramEditorInputProvider() {
        super();
    }

    @objid ("b72410ac-5a44-11e2-9e33-00137282c51b")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        AbstractDiagram diagram = (AbstractDiagram) modelManager.getModelServices().findByRef(new MRef(CompositeStructureDiagram.MQNAME, diagramUID));
        return diagram != null ? new CompositeDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("cbb9e9f9-97c5-4e15-96a0-01dea584cdbf")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new CompositeGmDiagramCreator();
    }

}
