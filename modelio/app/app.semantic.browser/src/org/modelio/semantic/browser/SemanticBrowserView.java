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
package org.modelio.semantic.browser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.semantic.browser.panel.SmBrowserPanelProvider;

@objid ("7cd10a89-7bd6-4888-95cd-f4cf7a8b3149")
public class SemanticBrowserView {
    @objid ("aa68f866-34ab-46b1-a107-333c4d8539d5")
    private SmBrowserPanelProvider panel;

    @objid ("c1f7ac9d-70c4-4402-be5a-94f1a3dd38d0")
    @Inject
    IEclipseContext eclipseContext;

    @objid ("3cf26590-f9d2-44c2-b97b-94f67a4f0a2a")
    @PostConstruct
    public void postConstruct(Composite parent, final MPart part) {
        // With Eclipse 4.18, the toolbar is messed up, force it right manually...
        part.getToolbar().setVisible(true);
        
        this.panel = new SmBrowserPanelProvider();
        ContextInjectionFactory.inject(this.panel, this.eclipseContext);
        this.panel.createPanel(parent);
        
        // The view might be instantiated when a project is already opened
        IProjectService projectService = this.eclipseContext.get(IProjectService.class);
        if (projectService != null) {
            IGProject project = projectService.getOpenedProject();
        
            if (project != null) {
                onProjectOpened(project);
            }
        }
        
    }

    @objid ("a79d0511-b5a4-4505-9b8b-6beef95b3f8d")
    @Focus
    public void setFocus() {
        // nothing to do
    }

    @objid ("5d456c87-b5d5-4ced-afb9-8a47833ba094")
    @PreDestroy
    public void preDestroy() {
        if (this.panel != null) {
            this.panel.dispose();
            this.panel = null;
        }
        
    }

    @objid ("927dbf00-20bb-476a-a260-4bc9ff3c5c4f")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final IGProject openedProject) {
        this.panel.setInput(openedProject);
    }

    @objid ("e711c201-04ca-49f0-9ff9-b0f9dca196e7")
    @Inject
    @Optional
    void onProjectClosed(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSED) final IGProject project) {
        this.panel.setInput(null);
    }

    @objid ("8b21f399-18e1-4898-965a-a17c379213d3")
    public SmBrowserPanelProvider getSemanticBrowser() {
        return this.panel;
    }

}
