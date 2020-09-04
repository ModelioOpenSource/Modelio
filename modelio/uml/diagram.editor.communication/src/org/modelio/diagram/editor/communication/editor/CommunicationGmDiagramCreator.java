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

package org.modelio.diagram.editor.communication.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.editor.communication.elements.communicationdiagram.GmCommunicationDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("9179b699-391c-4c82-a4bf-0da63577410b")
public class CommunicationGmDiagramCreator implements GmDiagramCreator {
    @objid ("84333e99-f4dc-408e-91a4-b2e639ce9566")
    @Override
    public GmAbstractDiagram createDiagram(IModelManager modelManager, AbstractDiagram diagram) {
        if (diagram instanceof CommunicationDiagram) {
            return new GmCommunicationDiagram(modelManager, (CommunicationDiagram) diagram, new MRef(diagram));
        }
        return null;
    }

}
