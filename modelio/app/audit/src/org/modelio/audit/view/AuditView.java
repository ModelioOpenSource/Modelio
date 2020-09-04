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

package org.modelio.audit.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.providers.AuditProviderFactory.AuditViewMode;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;

/**
 * ModelProperty plugin main model class.
 * This class stores a reference to the current project, and listens to open/close events.
 */
@objid ("11fe7b04-6eb3-422c-a57e-8aa3c23edeba")
public class AuditView {
    @objid ("25684031-d023-4ab7-84da-1e65b125400a")
    public static final String VIEW_ID = "org.modelio.audit.AuditViewID";

    @objid ("4ba1fa15-57ca-4307-9474-3815514ed987")
    private static final String POPUPID = "org.modelio.audit.popup";

    @objid ("9c9f683b-eb60-46c2-8aa5-c111a3e715c0")
    protected AuditPanelProvider view;

    @objid ("dce1f96c-127f-4ff4-9073-99105de5b82f")
    protected GProject project;

    @objid ("251de8e3-ad5e-4b96-ad44-513ed0df31ef")
    protected IMModelServices modelService;

    @objid ("19ae0644-3c4e-4dcf-ae28-058857fe11c5")
    protected Composite parentComposite;

    /**
     * Called by the framework to create the view and initialize it.
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
    public void createControls(IProjectService projectService, @Optional IMModelServices mmService, IAuditService auditService, IModelioNavigationService navigationService, MApplication application, EModelService emService, EMenuService menuService, Composite parent) {
        this.parentComposite = parent;
        if (auditService.getAuditEngine() != null) {
            onProjectOpened(projectService.getOpenedProject(), mmService, auditService, navigationService, application, emService, menuService);
        }
    }

    /**
     * This method is called after a project opening.
     * Keep a reference to the modeling session and model services.
     */
    @objid ("6cb8ea67-11e7-4e5e-85f0-d8adcbba433f")
    @Optional
    @Inject
    public void onProjectOpened(@EventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject openedProject, @Optional final IMModelServices mmService, final IAuditService auditService, final IModelioNavigationService navigationService, final MApplication application, final EModelService emService, final EMenuService menuService) {
        if (openedProject != null) {
            this.project = openedProject;
            this.modelService = mmService;
        
            // Sometimes, the view is instantiated only after the project is opened
        
            if (this.view == null) {
                Display.getDefault().asyncExec(new Runnable() {
        
                    @Override
                    public void run() {
                        if (AuditView.this.project != null) {
                            // Create the view content
                            AuditView.this.view = new AuditPanelProvider(auditService, openedProject.getSession(), AuditView.this.modelService, navigationService, application, emService, "");
                            AuditView.this.view.createPanel(AuditView.this.parentComposite);
                            AuditView.this.parentComposite.layout();
                            AuditView.this.view.setInput(auditService.getAuditEngine().getAuditDiagnostic());
                            AuditView.this.view.refresh(auditService.getAuditEngine().getAuditDiagnostic());
                            menuService.registerContextMenu(AuditView.this.view.getTreeViewer().getTree(), AuditView.POPUPID);
                        }
                    }
                });
            } else {
                this.view.refresh(auditService.getAuditEngine().getAuditDiagnostic());
            }
        
        }
    }

    /**
     * Called when a project is closed.
     * On session close un-reference the modeling session and model services.
     */
    @objid ("8825a5b5-5b52-4679-96e7-05de3ba2f3e7")
    @Inject
    @Optional
    public void onProjectClosed(@UIEventTopic(ModelioEventTopics.PROJECT_CLOSED) final GProject closedProject) {
        if (closedProject != null) {
            AuditView.this.project = null;
            AuditView.this.modelService = null;
            AuditView.this.view.dispose();
        }
    }

    @objid ("b6790bce-129a-4f2d-bb6e-0057ed2f3dae")
    @Focus
    public void setFocus() {
        if (this.view != null) {
            this.view.getPanel().setFocus();
        }
    }

    @objid ("407156c0-a1e8-4520-bf70-13804034f189")
    public Object getSelectedDiagnosticEntry() {
        return this.view.getSelectedDiagnosticEntry();
    }

    @objid ("6e51b477-0fc0-4a01-ba95-2b34a55d395e")
    public boolean isAutoSelectInExplorer() {
        return this.view.isAutoSelectInExplorer();
    }

    @objid ("3563210d-5bf3-4a4e-854d-3a3c7e67e1f5")
    public void setAutoSelectInExplorer(boolean b) {
        this.view.setAutoSelectInExplorer(b);
    }

    @objid ("70b7506e-4f00-4462-823c-8d40091dc3d7")
    public void setAuditViewMode(AuditViewMode mode) {
        this.view.setAuditViewMode(mode);
        this.parentComposite.layout();
    }

    @objid ("49eeb021-3adf-4db1-a87b-ce46beabdcb6")
    public void refresh(IAuditDiagnostic diagnostic) {
        this.view.refresh(diagnostic);
    }

}
