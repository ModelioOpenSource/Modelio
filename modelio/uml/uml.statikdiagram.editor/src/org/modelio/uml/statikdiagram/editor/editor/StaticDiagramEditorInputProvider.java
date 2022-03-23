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
package org.modelio.uml.statikdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditorInputProvider;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram input provider for {@link ClassDiagram} and {@link StaticDiagram} metaclasses.
 */
@objid ("cb21235a-5a75-11e2-9e33-00137282c51b")
public class StaticDiagramEditorInputProvider implements IDiagramEditorInputProvider {
    /**
     * Initialize the provider.
     */
    @objid ("6557991c-5bd5-11e2-9e33-00137282c51b")
    public  StaticDiagramEditorInputProvider() {
        super();
    }

    @objid ("6557991e-5bd5-11e2-9e33-00137282c51b")
    @Override
    public DiagramEditorInput compute(String diagramUID, org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager modelManager) {
        AbstractDiagram diagram = (AbstractDiagram) modelManager.getModelServices().findByRef(new MRef(StaticDiagram.MQNAME, diagramUID));
        if (diagram == null) {
            return null;
        }
        
        Class<? extends MObject> mClass = diagram.getMClass().getJavaInterface();
        if (mClass == ClassDiagram.class) {
            return new StaticDiagramEditorInput(modelManager, diagram, getDiagramCreator());
        } else if (mClass == StaticDiagram.class) {
            return new StaticDiagramEditorInput(modelManager, diagram, getDiagramCreator());
        }
        return null;
    }

    @objid ("63073220-602b-47a2-b92d-a35169df22fb")
    @Override
    public GmDiagramCreator getDiagramCreator() {
        return new StaticGmDiagramCreator();
    }

}
