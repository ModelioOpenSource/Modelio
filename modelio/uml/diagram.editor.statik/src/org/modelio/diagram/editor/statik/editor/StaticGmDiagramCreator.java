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

package org.modelio.diagram.editor.statik.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.editor.statik.elements.staticdiagram.GmStaticDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("248932a7-d29e-4e3e-90d9-2d854fd03f8b")
public class StaticGmDiagramCreator implements GmDiagramCreator {
    @objid ("4b3aef3b-7cbf-4226-afdb-bb21b84f4027")
    @Override
    public GmAbstractDiagram createDiagram(IModelManager modelManager, AbstractDiagram diagram) {
        if (diagram instanceof ClassDiagram) {
            return new GmStaticDiagram(modelManager, (ClassDiagram) diagram, new MRef(diagram));
        } else if (diagram.getMClass().getJavaInterface() == StaticDiagram.class) {
            return new GmStaticDiagram(modelManager, (StaticDiagram) diagram, new MRef(diagram));
        }
        return null;
    }

}
