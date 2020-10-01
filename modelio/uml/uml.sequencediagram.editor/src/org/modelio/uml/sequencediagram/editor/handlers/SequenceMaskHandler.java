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

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.handlers.MaskHandler;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d99f2cba-55b6-11e2-877f-002564c97630")
public class SequenceMaskHandler extends MaskHandler {
    @objid ("d99f2cbe-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
                if (selectedObject instanceof GraphicalEditPart) {
                    GraphicalEditPart selectedEditPart = (GraphicalEditPart) selectedObject;
        
                    final MObject finalElement = ((GmModel) selectedEditPart.getModel()).getRelatedElement();
                    if (finalElement != null) {
                        return (finalElement instanceof Constraint) | (finalElement instanceof Document) | (finalElement instanceof Note);
                    }
                }
            }
        }
        return false;
    }

}
