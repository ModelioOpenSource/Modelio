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

package org.modelio.creation.wizard.ext.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.creation.wizard.dialog.CreationWizardDialog;
import org.modelio.creation.wizard.dialog.CreationWizardModel;
import org.modelio.creation.wizard.ext.plugin.CreationWizardOrg;
import org.modelio.creation.wizard.impl.CreationContributorManager;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * E4 handler to open diagram creation wizard.
 */
@objid ("83df37bb-4305-4660-aaea-50c822526a79")
public class DiagramCreationHandler {
    @objid ("d0010088-72e8-4702-8230-857265769e7f")
    @Inject
    private IEclipseContext context;

    @objid ("5be09842-d507-4ee6-9b6c-f937514bb059")
    @Inject
    protected IModelioEventService eventService;

    @objid ("2ca89168-93d5-4b7a-9b74-a1f738d31b39")
    @Inject
    protected IModelioPickingService pickingService;

    @objid ("2cb228a3-8c19-4321-b7a5-8e11b9cf3caf")
    @Inject
    protected IProjectService projectService;

    @objid ("9eb1b60a-ac9e-4361-80bf-68a99a4f2b6a")
    @Inject
    protected IModelioNavigationService selectionService;

    @objid ("95e1bd6f-a5df-40f2-adaa-b95658c4598e")
    private CreationWizardDialog dialog = null;

    /**
     * @param selection the E4 selection
     * @param contributorName asked contributor name
     * @return true to allow execution else false.
     */
    @objid ("cba0745d-2d74-45f3-867d-3a09fd25c73a")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) final Object selection, @Optional @Named ("contributor") final String contributorName) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        ModelElement selectedElement = DiagramCreationHandler.getSelectedElement(selection);
        
        // Trivial cases
        if (selectedElement == null || selectedElement.getStatus().isRamc()) {
            return false;
        }
        
        // Search for the first compatible contributor which accepts the
        // selected element
        for (IWizardContributor contributor : getPossibleContributors(IDiagramWizardContributor.class)) {
            if (contributorName == null || contributor.getClass().getSimpleName().equals(contributorName)) {
                if (contributor.accept(selectedElement)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The command has been executed, so extract the needed information from the application context.
     * @param selection the E4 selection
     * @param contributorName the asked "contributor" name
     * @param activeShell the application's active shell.
     */
    @objid ("063fc6cc-b682-4692-8a18-93244d60445d")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final Object selection, @Optional @Named ("contributor") final String contributorName, @Named (IServiceConstants.ACTIVE_SHELL) final Shell activeShell) {
        ModelElement selectedElement = DiagramCreationHandler.getSelectedElement(selection);
        // As the dialog box is NOT modal because it supports picking, this
        // handler might be called while the dialog is opened
        if (this.dialog == null || this.dialog.getShell() == null) {
            // Create an Open dialog
            CreationWizardModel resultModel = new CreationWizardModel(getCreationContributorManager().getContributorsMap());
            resultModel.setContext(selectedElement);
            resultModel.setTypeFilter(IDiagramWizardContributor.class);
        
            IWizardContributor effectiveContributor = findPreselectedContributor(contributorName);
        
            if (effectiveContributor == null) {
                // No preselected contributor => prompt user
                effectiveContributor = promptUser(activeShell, resultModel);
            } else {
                // The command being executed has provided a contributor, no
                // user prompt
                resultModel.setName(effectiveContributor.getLabel());
                resultModel.setSelectedContributor(effectiveContributor);
            }
        
            // The effectiveContributor is null if we prompted the user and the
            // user cancelled the operation
            if (effectiveContributor != null) {
        
                ICoreSession session = this.projectService.getSession();
                try (ITransaction t = session.getTransactionSupport().createTransaction("Create element");) {
                    MObject elt = effectiveContributor.actionPerformed(resultModel.getContext(), resultModel.getName(),
                            resultModel.getDescription());
        
                    t.commit();
                    if (elt != null) {
                        openEditor(elt);
                        this.selectionService.fireNavigate(elt);
                    }
                }
            }
            // Get rid of the dialog
            this.dialog = null;
        } else {
            // dialog already opened
            this.dialog.getShell().setFocus();
        }
    }

    @objid ("b1a6a0fa-f9e6-4f57-9d96-6dafd25dd943")
    protected void openEditor(final MObject elt) {
        Display.getDefault().asyncExec(
                () -> this.eventService.postAsyncEvent(
                        () -> "openEditor",
                        ModelioEvent.EDIT_ELEMENT,
                        elt));
    }

    @objid ("fb2a9037-b820-45a7-a878-1c06c9138828")
    private IWizardContributor findPreselectedContributor(String contributorName) {
        if (contributorName == null) {
            return null;
        }
        
        for (IWizardContributor contributor : getPossibleContributors(IDiagramWizardContributor.class)) {
            if (contributor.getClass().getSimpleName().equals(contributorName)) {
                return contributor;
            }
        }
        return null;
    }

    @objid ("ec053532-7cb8-4047-ad32-a162884f6fe1")
    private List<IWizardContributor> getPossibleContributors(java.lang.Class<? extends IWizardContributor> typeFilter) {
        return new ArrayList<>(getCreationContributorManager().getAllContributors(typeFilter));
    }

    @objid ("d044b705-de22-429c-b367-57bbff5c172d")
    private static ModelElement getSelectedElement(final Object selection) {
        if (selection instanceof ModelElement) {
            return (ModelElement) selection;
        } else if (selection instanceof IStructuredSelection) {
            return SelectionHelper.getFirst((IStructuredSelection) selection, ModelElement.class);
        } else {
            return null;
        }
    }

    @objid ("0ec1e7de-264c-45d3-8905-819bbddbc52d")
    private IWizardContributor promptUser(Shell activeShell, CreationWizardModel dataModel) {
        dataModel.setShowInvalid(false);
        this.dialog = new CreationWizardDialog(activeShell, dataModel, this.projectService, this.pickingService) {
            @Override
            protected String getHelpId() {
                IWizardContributor selectedContributor = dataModel.getSelectedContributor();
                String helpUrl = selectedContributor != null ? selectedContributor.getHelpUrl() : null;
                return helpUrl != null ? helpUrl : CreationWizardOrg.I18N.getMessage("Ui.CreationWizard.Diagram.HelpTopic");
            }
        };
        
        if (this.dialog.open() == IDialogConstants.OK_ID) {
            return dataModel.getSelectedContributor();
        }
        return null;
    }

    @objid ("dfc0bf82-57f6-4f19-91cc-bcd7bde309cd")
    private CreationContributorManager getCreationContributorManager() {
        return Objects.requireNonNull(this.context.get(CreationContributorManager.class));
    }

}
