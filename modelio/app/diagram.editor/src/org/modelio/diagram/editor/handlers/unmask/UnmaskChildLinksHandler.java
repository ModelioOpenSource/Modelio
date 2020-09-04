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

package org.modelio.diagram.editor.handlers.unmask;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Unmask child generalizations and realizations of the selection.
 * 
 * @author phv
 */
@objid ("6658fdca-33f7-11e2-95fe-001ec947c8cc")
public class UnmaskChildLinksHandler extends AbstractUnmaskHandler {
    @objid ("6658fdcc-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void unmask(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> secondarySelection, ICoreSession session) {
        try (ITransaction t = session.getTransactionSupport().createTransaction("UnmaskNonStructuringLinksAndNodes")) {
            GmModel primaryGm = (GmModel) primarySelection.getModel();
            unmaskManager.unmaskChildLink(primarySelection.getViewer(), primaryGm);
        
            for (GraphicalEditPart secondaryEditPart : secondarySelection) {
                GmModel secondaryGm = (GmModel) secondaryEditPart.getModel();
                unmaskManager.unmaskChildLink(secondaryEditPart.getViewer(), secondaryGm);
            }
        
            t.commit();
        } catch (Exception e) {
            DiagramEditor.LOG.error(e);
        }
    }

    /**
     * Accept only INameSpace
     */
    @objid ("6658fdd5-33f7-11e2-95fe-001ec947c8cc")
    @CanExecute
    public boolean isEnabled(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        List<GraphicalEditPart> secondaryEditParts = new ArrayList<>();
        GraphicalEditPart primaryEditPart = getSelection(selection, secondaryEditParts);
        
        if (primaryEditPart == null)
            return false;
        
        GmModel primaryGm = (GmModel) primaryEditPart.getModel();
        MObject primaryElt = primaryGm.getRepresentedElement();
        
        if (!(primaryElt instanceof NameSpace)) {
            return false;
        }
        
        for (GraphicalEditPart secondaryEditPart : secondaryEditParts) {
            GmModel secondaryGm = (GmModel) secondaryEditPart.getModel();
            MObject secondaryElt = secondaryGm.getRepresentedElement();
        
            if (!(secondaryElt instanceof NameSpace)) {
                return false;
            }
        }
        return true;
    }

}
