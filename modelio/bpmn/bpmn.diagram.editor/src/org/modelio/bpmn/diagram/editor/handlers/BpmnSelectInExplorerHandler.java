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

package org.modelio.bpmn.diagram.editor.handlers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.handlers.SelectInExplorerHandler;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e10060f8-3278-4d9a-adb9-8559afaa980a")
public class BpmnSelectInExplorerHandler extends SelectInExplorerHandler {
    @objid ("c3002d3c-d86d-4191-b175-8d28d4804a12")
    @Override
    public List<MObject> getSelection(ISelection iSelection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (iSelection instanceof IStructuredSelection) {
            List<?> selectionAsList = ((IStructuredSelection) iSelection).toList();
            for (Object selectedObject : selectionAsList) {
                if (selectedObject instanceof GraphicalEditPart) {
                    GraphicalEditPart editPart = (GraphicalEditPart) selectedObject;
                    
                    final MObject elt = editPart.getAdapter(MObject.class);
                    
                    if (elt != null && !(elt instanceof BpmnGateway || elt instanceof BpmnSequenceFlow || elt instanceof BpmnDataAssociation))
                        selectedElements.add(elt);
                }                
            }
        }
        return selectedElements;
    }

}
