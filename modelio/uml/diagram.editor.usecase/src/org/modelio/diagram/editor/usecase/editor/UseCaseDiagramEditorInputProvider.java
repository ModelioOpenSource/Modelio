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

package org.modelio.diagram.editor.usecase.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link UseCaseDiagram} metaclasses.
 */
@objid ("7b9bb527-5eff-11e2-b9cc-001ec947c8cc")
public class UseCaseDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("7bea42e8-5eff-11e2-b9cc-001ec947c8cc")
    public UseCaseDiagramEditorInputProvider() {
        super();
    }

    @objid ("7bea42ea-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        UseCaseDiagram diagram = (UseCaseDiagram) modelManager.getModelServices().findByRef(new MRef(UseCaseDiagram.MQNAME, diagramUID));
        return diagram != null ? new UseCaseDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("e5ba2c47-7a6f-4fc8-bb9a-54049ddc44e2")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new UseCaseGmDiagramCreator();
    }

}
