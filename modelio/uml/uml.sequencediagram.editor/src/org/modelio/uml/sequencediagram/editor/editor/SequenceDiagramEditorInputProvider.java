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
package org.modelio.uml.sequencediagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link SequenceDiagram} metaclasses.
 */
@objid ("ca208a23-456f-4f52-9961-77613258a92b")
public class SequenceDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("732318c6-0de1-41e8-9e18-d39b2a2b49af")
    public  SequenceDiagramEditorInputProvider() {
        super();
    }

    @objid ("cee89d60-79d0-4612-9037-b3c9c7775d08")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        SequenceDiagram diagram = (SequenceDiagram) modelManager.getModelServices().findByRef(new MRef(SequenceDiagram.MQNAME, diagramUID));
        return diagram != null ? new SequenceDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("5caa542a-84a7-408b-8e0e-39972711815f")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new SequenceGmDiagramCreator();
    }

}
