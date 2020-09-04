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

package org.modelio.diagram.editor.composite.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.editor.object.elements.objectdiagram.GmObjectDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("c2692f42-0f03-4546-b65c-e86bd7d46c7b")
public final class CompositeGmDiagramCreator implements GmDiagramCreator {
    @objid ("ada45bc4-085f-4a74-bcd2-a335e2c666f6")
    @Override
    public GmAbstractDiagram createDiagram(IModelManager modelManager, AbstractDiagram diagram) {
        if (diagram instanceof CompositeStructureDiagram) {
            return new GmObjectDiagram(modelManager, (CompositeStructureDiagram) diagram, new MRef(diagram));
        }
        return null;
    }

}
