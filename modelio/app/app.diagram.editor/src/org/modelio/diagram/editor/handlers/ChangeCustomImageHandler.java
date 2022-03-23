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
package org.modelio.diagram.editor.handlers;

import java.net.URL;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1c42cfa8-a74f-4908-9cb4-54153a0f8512")
public class ChangeCustomImageHandler {
    @objid ("e2b59314-7201-4449-8d9c-baf5814154a3")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List <AbstractNodeEditPart> editParts = SelectionHelper.toList(selection, AbstractNodeEditPart.class);
        URL itemUrl = editParts.get(0).getCustomImageUrl();
        
        for (AbstractNodeEditPart editPart : editParts) {
            GmNodeModel gmModel = editPart.getModel();
            MObject obElement = gmModel.getRelatedElement();
            ICoreSession session = CoreSession.getSession(obElement);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update Custom Image")) {
                editPart.changeCustomImage(gmModel, itemUrl);
                t.commit();
            }
        }
        
    }

    @objid ("34380b82-309a-467b-ada8-36e8078a72d8")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<AbstractNodeEditPart> editParts = SelectionHelper.toList(selection, AbstractNodeEditPart.class);
        for (AbstractNodeEditPart editPart : editParts) {
            GmNodeModel gmModel = editPart.getModel();
            MObject obElement = gmModel.getRelatedElement();
            TagType tagType;
            try {
                tagType = gmModel.getDiagram().getModelManager().getModelServices().getTagType("ModelerModule", ".*", "userDiagramImage", obElement.getMClass());
            } catch (ElementNotUniqueException e) {
                return false;
            }
            if (tagType == null) {
                return false;
            }
            if (gmModel instanceof GmPortContainer) {
                if (((GmPortContainer) gmModel).getMainNode().getRepresentationMode() != RepresentationMode.USER_IMAGE) {
                    return false;
                }
            } else if (gmModel.getRepresentationMode() != RepresentationMode.USER_IMAGE) {
                return false;
            }
        }
        return editParts.size() > 0;
    }

}
