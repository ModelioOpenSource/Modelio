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

package org.modelio.audit.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.providers.AuditProviderFactory.AuditViewMode;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * AuditView: the audit view in Modelio application.
 * 
 * This view displays the current Audit results (see AuditPanelProvider) along with a view toolbar providing commands to choose the results contents and presentation.
 */
@objid ("11fe7b04-6eb3-422c-a57e-8aa3c23edeba")
public class AuditView {
    @objid ("25684031-d023-4ab7-84da-1e65b125400a")
    public static final String VIEW_ID = "org.modelio.audit.AuditViewID";

    @objid ("4ba1fa15-57ca-4307-9474-3815514ed987")
    private static final String POPUPID = "org.modelio.audit.popup";

    @objid ("7510ccec-5c38-4831-83a6-f3ba1ddc6298")
    private boolean synchronizedSelectionMode;

    @objid ("4cd1ccab-98cd-4212-a385-fa6ecbccd41f")
    private static final String jobId = "ON_SYNCHRONIZED_SELECTION";

    @objid ("28c80855-b077-4695-8b61-9b31b87b37f0")
    @Inject
    @Optional
    private ESelectionService selectionService;

    @objid ("9c9f683b-eb60-46c2-8aa5-c111a3e715c0")
    private AuditPanelProvider auditResultsPanel;

    @objid ("251de8e3-ad5e-4b96-ad44-513ed0df31ef")
    private IMModelServices modelService;

    @objid ("7c708898-4bdb-4975-af07-42e1438717f9")
    private IAuditService auditService;

    @objid ("c93f0a8b-09a2-4f15-9d91-f28b12f18e94")
    protected Composite parentComposite;

    /**
     * Called by the framework to create the view and initialize it.
     * 
     * @param projectService the project service.
     * @param mmService the model service.
     * @param auditService the audit service
     * @param navigationService the navigation service
     * @param application the E4 application model
     * @param emService the E4 model service
     * @param menuService the E4 menu service
     * @param parent the composite the view must add its content into.
     */
    @objid ("efb0aecd-1227-401c-9ee4-802f7ecf6188")
    @PostConstruct
    public void createControls(final IProjectService projectService, @Optional final IMModelServices mmService, final IAuditService auditService, final IModelioNavigationService navigationService, final MApplication application, final EModelService emService, final EMenuService menuService, final Composite parent) {
        this.parentComposite = parent;
        this.auditService = auditService;
        
        if (projectService.getOpenedProject() != null) {
            onProjectOpened(projectService.getOpenedProject(), mmService, auditService, navigationService, application, emService, menuService);
        }
    }

    /**
     * This method is called after a project opening. Keep a reference to the modeling session and model services.
     */
    @objid ("6cb8ea67-11e7-4e5e-85f0-d8adcbba433f")
    @Optional
    @Inject
    public void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject openedProject, @Optional final IMModelServices mmService, final IAuditService auditService, final IModelioNavigationService navigationService, final MApplication application, final EModelService emService, final EMenuService menuService) {
        this.modelService = mmService;
        
        // Sometimes, the view is instantiated only after the project is opened
        if (this.auditResultsPanel == null) {
            // Create the view content
            this.auditResultsPanel = new AuditPanelProvider(auditService,
                                                            openedProject.getSession(),
                                                            AuditView.this.modelService,
                                                            navigationService,
                                                            application,
                                                            emService);
            this.auditResultsPanel.createPanel(AuditView.this.parentComposite);
            this.parentComposite.layout();
            this.auditResultsPanel.setInput(auditService.getAuditEngine().getAuditDiagnostic());
            this.auditResultsPanel.refresh(auditService.getAuditEngine().getAuditDiagnostic());
            menuService.registerContextMenu(AuditView.this.auditResultsPanel.getTreeViewer().getTree(), AuditView.POPUPID);
        } else {
            this.auditResultsPanel.refresh(auditService.getAuditEngine().getAuditDiagnostic());
        }
    }

    /**
     * Called when a project is closed. On session close un-reference the modeling session and model services.
     */
    @objid ("8825a5b5-5b52-4679-96e7-05de3ba2f3e7")
    @Inject
    @Optional
    public void onProjectClosed(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSED) final GProject closedProject) {
        if (closedProject != null) {
            this.modelService = null;
            this.auditResultsPanel.dispose();
        }
    }

    @objid ("b6790bce-129a-4f2d-bb6e-0057ed2f3dae")
    @Focus
    public void setFocus() {
        if (this.auditResultsPanel != null) {
            this.auditResultsPanel.getPanel().setFocus();
        }
    }

    @objid ("407156c0-a1e8-4520-bf70-13804034f189")
    public Object getSelectedDiagnosticEntry() {
        return this.auditResultsPanel.getSelectedDiagnosticEntry();
    }

    @objid ("70b7506e-4f00-4462-823c-8d40091dc3d7")
    public void setAuditViewMode(final AuditViewMode mode) {
        this.auditResultsPanel.setViewMode(mode);
        this.parentComposite.layout();
    }

    @objid ("49eeb021-3adf-4db1-a87b-ce46beabdcb6")
    public void refresh(final IAuditDiagnostic diagnostic) {
        this.auditResultsPanel.refresh(diagnostic);
    }

    /**
     * Called when application selection changes.<br/>
     * This method does nothing special unless the 'synchronizedSelectionMode' flag is set and the global audit results are displayed.<br/>
     * When the 'synchronizedSelectionMode' flag is set:
     * <ol>
     * <li>a partial audit is run on the selected elements</li>
     * <li>the displayed results are only those of the partial audit</li>
     * </ol>
     * The 'synchronizedSelectionMode' flag is actually controlled by a checkbox button in the view toolbar.
     */
    @objid ("1d2cda0d-801b-4969-8f00-1795b03b393a")
    @Optional
    @Inject
    public void onSelectionChanged(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        // Protect agains't dumb case : 'audit view not yet rendered'.
        if (this.auditResultsPanel == null)
            return;
        
        if (this.synchronizedSelectionMode) {
            List<MObject> elements = SelectionHelper.toList(selection, MObject.class);
            this.auditService.interuptCheck(jobId);
            this.auditService.getAuditEngine().getAuditDiagnostic().purgeJob(jobId);
            this.auditService.checkElementTree(elements, jobId);
            this.auditResultsPanel.setScope(elements, jobId);
        } else {
            this.auditResultsPanel.setScope(null, null);
        }
    }

    @objid ("e4a188ea-c440-4031-9f8b-a0729d2056fc")
    public void setSynchronizedSelectionMode(boolean synchronizedSelectionMode) {
        if (this.synchronizedSelectionMode != synchronizedSelectionMode) {
            this.synchronizedSelectionMode = synchronizedSelectionMode;
            if (! this.synchronizedSelectionMode) {
                this.auditService.interuptCheck(jobId);
                this.auditResultsPanel.setScope(null, null);
            }
        }
    }

}
