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
package org.modelio.uml.sequencediagram.editor.handlers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.handlers.SelectInExplorerHandler;
import org.modelio.uml.sequencediagram.editor.elements.lifeline.GmLifeline;
import org.modelio.uml.sequencediagram.editor.elements.lifeline.LifelineEditPart;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.SequenceDiagramEditPart;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d99f2ccf-55b6-11e2-877f-002564c97630")
public class SequenceSelectInExplorerHandler extends SelectInExplorerHandler {
    @objid ("d99f2cd3-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute(ISelection selection) {
        List<MObject> listSelection = getSelection(selection);
        return listSelection.size() > 0;
    }

    @objid ("fa97c3db-a374-4174-becc-4f5ce11fc5fa")
    @Override
    public List<MObject> getSelection(ISelection iSelection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (iSelection instanceof IStructuredSelection) {
            List<?> selectionAsList = ((IStructuredSelection) iSelection).toList();
            for (Object selectedObject : selectionAsList) {
                if (selectedObject instanceof LifelineEditPart) {                    
                    GmLifeline lifelineModel = (GmLifeline) ((LifelineEditPart)selectedObject).getModel();                    
                    
                    if (lifelineModel.getRepresentedElement() != null && lifelineModel.getRepresentedElement().getRepresented() != null) {
                        selectedElements.add(lifelineModel.getRepresentedElement().getRepresented());
                    }                  
                }
                else if (selectedObject instanceof SequenceDiagramEditPart) {
                    SequenceDiagramEditPart sdep = (SequenceDiagramEditPart) selectedObject;
                    GmSequenceDiagram sequenceDiagramModel = (GmSequenceDiagram)sdep.getModel();
                    if (sequenceDiagramModel.getRepresentedElement() != null) {
                        selectedElements.add(sequenceDiagramModel.getRepresentedElement());
                    }
                }
            }
        }
        return selectedElements;
    }

}
