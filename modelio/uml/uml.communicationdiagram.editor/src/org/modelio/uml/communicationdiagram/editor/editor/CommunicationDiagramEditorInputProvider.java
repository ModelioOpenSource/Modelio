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

package org.modelio.uml.communicationdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link CommunicationDiagram} metaclasses.
 */
@objid ("333d94ba-598e-11e2-ae45-002564c97630")
public class CommunicationDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("057c2929-599a-11e2-ae45-002564c97630")
    public CommunicationDiagramEditorInputProvider() {
        super();
    }

    @objid ("057c292b-599a-11e2-ae45-002564c97630")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        CommunicationDiagram diagram = (CommunicationDiagram) modelManager.getModelServices().findByRef(new MRef(CommunicationDiagram.MQNAME, diagramUID));
        return diagram != null ? new CommunicationDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("21a2360f-3abd-4b90-a66c-706771ed84d0")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new CommunicationGmDiagramCreator();
    }

}
