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

package org.modelio.diagram.editor.bpmn.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.handlers.MaskHandler;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.genericlink.GenericLinkEditPart;
import org.modelio.diagram.elements.common.genericnode.GenericNodeEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialized mask handler for Bpmn diagrams.
 * <p>
 * This handler forbids masking BPMN elements where the whole workflow HAS to be displayed and where partial representation resulting of masking, would lead to a non-sense diagram.
 * </p>
 */
@objid ("a2a0d935-028f-44f1-94a9-b60560775795")
public class BpmnMaskHandler extends MaskHandler {
    @objid ("77750d23-f445-4c92-865b-dee8056e9984")
    @Override
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        if (!super.canExecute(selection)) {
            return false;
        } else {
            if (selection instanceof IStructuredSelection) {
                for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
                    if (selectedObject instanceof GraphicalEditPart && !(selectedObject instanceof AbstractDiagramEditPart)) {
                        GraphicalEditPart editPart = (GraphicalEditPart) selectedObject;
                        if (editPart instanceof GenericNodeEditPart || editPart instanceof GenericLinkEditPart) {
                            continue;
                        }
                        Object model = editPart.getModel();
                        if (model instanceof GmModel) {
                            GmModel gmModel = ((GmModel) model);
                            MObject elt = gmModel.getRepresentedElement();
                            if (elt instanceof BpmnItemAwareElement) {
                                if (gmModel.getDiagram().getAllGMRepresenting(new MRef(elt)).size() <= 1) {
                                    return false;
                                }
                            } else if (elt instanceof BpmnBaseElement && !(elt instanceof BpmnParticipant) && !(elt instanceof BpmnMessageFlow) && !(elt instanceof BpmnMessage)) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

}
