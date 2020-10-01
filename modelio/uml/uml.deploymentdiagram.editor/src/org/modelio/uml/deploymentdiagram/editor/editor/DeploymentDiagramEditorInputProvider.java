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
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link DeploymentDiagram} metaclasses.
 */
@objid ("ea20fbce-5a6e-11e2-9e33-00137282c51b")
public class DeploymentDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("436dd5d4-5beb-11e2-9e33-00137282c51b")
    public DeploymentDiagramEditorInputProvider() {
        super();
    }

    @objid ("436dd5d6-5beb-11e2-9e33-00137282c51b")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        AbstractDiagram diagram = (AbstractDiagram) modelManager.getModelServices().findByRef(new MRef(DeploymentDiagram.MQNAME, diagramUID));
        return diagram != null ? new DeploymentDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("25caf93e-f324-40fa-8d58-94f5585633bf")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new DeploymentGmDiagramCreator();
    }

}
