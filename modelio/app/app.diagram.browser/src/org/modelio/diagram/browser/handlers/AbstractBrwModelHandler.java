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

package org.modelio.diagram.browser.handlers;

import java.util.Collections;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.browser.plugin.DiagramBrowser;
import org.modelio.diagram.browser.view.DiagramBrowserView;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

/**
 * This abstract class is a helper to develop handlers for commands that may manipulate (ie modify) the diagram model exposed in the
 * browser.
 * <p>
 * This class deals with transaction management and selected element fetching.<br>
 * The real semantic of the handler is implemented by subclasses in their {@link #doExecute(ExecutionEvent, List, ICoreSession)
 * doExecute(...)} method.
 * 
 * @author pvlaemyn
 */
@objid ("001c5b7e-0d4f-10c6-842f-001ec947cd2a")
public abstract class AbstractBrwModelHandler {
    @objid ("001c6e16-0d4f-10c6-842f-001ec947cd2a")
    @Execute
    public final Object execute(MPart part, IProjectService projectService, @Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        GProject openedProject = projectService.getOpenedProject();
        
        final DiagramBrowserView browserView = (DiagramBrowserView) part.getObject();
        
        // Get the modeling session and open a transaction
        ICoreSession session = openedProject.getSession();
        
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Diagram browser action")) {
            // Delegate the main creation task.
            doExecute(browserView, getSelected(selection), session);
        
            // Commit the transaction.
            transaction.commit();
        } catch (final Exception e) {
            // Something went wrong... Show an error box
            DiagramBrowser.LOG.warning(e.toString());
            String title = DiagramBrowser.I18N.getMessage("AbstractBrwModelHandler.Error.title");
        
            MessageDialog.openError(null, title, e.getLocalizedMessage());
        }
        return null;
    }

    @objid ("001c9756-0d4f-10c6-842f-001ec947cd2a")
    protected List<Object> getSelected(IStructuredSelection selection) {
        
        return selection != null ? selection.toList() : Collections.emptyList();
    }

    @objid ("001cbe02-0d4f-10c6-842f-001ec947cd2a")
    protected abstract void doExecute(DiagramBrowserView browserView, List<Object> selectedObjects, ICoreSession session);

    @objid ("c9f9b044-4b58-11e2-a4d3-002564c97630")
    public AbstractBrwModelHandler() {
        super();
    }

}
