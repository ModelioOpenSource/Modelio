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

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;

/**
 * This specific handler creates an element of the requested metaclass and attaches it to its composition owner using
 * the requested dependency. If provided, the stereotype is applied to the created element.
 */
@objid ("65afb5cc-33f7-11e2-95fe-001ec947c8cc")
public class ChangeLinkRouterHandler {
    @objid ("de8e054e-12de-4880-8192-6ab0f583d80f")
    private ISelection selection;

    @objid ("65afb5ce-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(IProjectService projectService, final ExecutionEvent event, @Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        this.selection = iSelection;
        
        // Get the value of the parameters:
        final String routerId = event.getParameter("routerId");
        final List<LinkEditPart> selectedLinks = getLinksToProcess();
        
        // Get the modelling session and open a transaction
        final ICoreSession modellingSession = projectService.getSession(); 
        final ITransactionSupport transactionManager = modellingSession.getTransactionSupport();
        
        try (ITransaction transaction = transactionManager.createTransaction("Set router " + routerId)){
            for (final LinkEditPart linkEditpart : selectedLinks) {
                final ConnectionRouterId routerKind = ConnectionRouterId.valueOf(routerId);
                final GmLink link = linkEditpart.getModel();
                final StyleKey styleKey = link.getStyleKey(MetaKey.CONNECTIONROUTER);
                if (styleKey != null) {
                    link.getDisplayedStyle().setProperty(styleKey, routerKind);
                }
            }
        }
        return null;
    }

    @objid ("65afb5d5-33f7-11e2-95fe-001ec947c8cc")
    protected List<LinkEditPart> getLinksToProcess() {
        final List<LinkEditPart> selectedLinks = new ArrayList<>();
        if (this.selection instanceof IStructuredSelection) {
            final List<?> selectedObjects = ((IStructuredSelection) this.selection).toList();
            for (final Object selectedObject : selectedObjects) {
                if (selectedObject instanceof LinkEditPart) {
                    selectedLinks.add((LinkEditPart) selectedObject);
                }
            }
        }
        return selectedLinks;
    }

    @objid ("65afb5db-33f7-11e2-95fe-001ec947c8cc")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        this.selection = iSelection;
        
        final List<LinkEditPart> selectedLinks = getLinksToProcess();
        // At least one link must be selected
        if (selectedLinks.isEmpty())
            return false;
        
        // At least one link must support changing router
        for (final LinkEditPart linkEditpart : selectedLinks) {
            final GmLink link = linkEditpart.getModel();
            final StyleKey styleKey = link.getStyleKey(MetaKey.CONNECTIONROUTER);
            if (styleKey != null)
                return true;
        }
        return false;
    }

}
