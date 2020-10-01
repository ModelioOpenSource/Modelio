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

package org.modelio.uml.activitydiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link ActivityDiagram} metaclasses.
 */
@objid ("2def47ec-58a2-11e2-9574-002564c97630")
public class ActivityDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    @objid ("2f18d639-58a2-11e2-9574-002564c97630")
    public ActivityDiagramEditorInputProvider() {
        super();
    }

    @objid ("2f18d63b-58a2-11e2-9574-002564c97630")
    @Override
    public DiagramEditorInput compute(String diagramUID, IModelManager modelManager) {
        ActivityDiagram diagram = (ActivityDiagram) modelManager.getModelServices().findByRef(new MRef(ActivityDiagram.MQNAME, diagramUID));
        return diagram != null ? new ActivityDiagramEditorInput(modelManager, diagram, getDiagramCreator()) : null;
    }

    @objid ("00b811a1-0424-4f55-82c1-22a82ddf2a4c")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new ActivityGmDiagramCreator();
    }

}
