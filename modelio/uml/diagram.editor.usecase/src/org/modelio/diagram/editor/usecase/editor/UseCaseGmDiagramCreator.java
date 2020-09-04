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

package org.modelio.diagram.editor.usecase.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.editor.usecase.elements.usecasediagram.GmUseCaseDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("bc839eed-df53-41ee-8a4a-2d265c24de69")
public final class UseCaseGmDiagramCreator implements GmDiagramCreator {
    @objid ("a6f39be3-eb0b-4b77-a8c4-89c498cd1d51")
    @Override
    public GmAbstractDiagram createDiagram(IModelManager modelManager, AbstractDiagram diagram) {
        if (diagram instanceof UseCaseDiagram) {
            return new GmUseCaseDiagram(modelManager, (UseCaseDiagram) diagram, new MRef(diagram));
        }
        return null;
    }

}
