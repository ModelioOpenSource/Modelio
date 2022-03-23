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
package org.modelio.model.browser.view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MPopupMenu;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.panel.IPanelProvider;

@objid ("6d0d8bfc-45bf-49e9-b94d-ce1829e8af96")
public class BrowserView {
    @objid ("427f2e11-d438-4883-8784-be5b70194cda")
    public static final String CONTENTPROVIDER_EXTENSION_POINT_ID = "org.modelio.app.model.browser.view.contentprovider";

    @objid ("de2b74d8-0e3c-4f58-80a8-7a0c6ce532b7")
    public static final String LABEL_PROVIDER_EXTENSION_POINT_ID = "org.modelio.platform.model.ui.labelprovider";

    @objid ("b9f7ac4a-668a-454f-95d9-9f0be4844d02")
    private static final String PANELPROVIDER_EXTENSION_POINT_ID = "org.modelio.app.model.browser.view.panelprovider";

    /**
     * Id of browser view's {@link MPart} in the e4.xmi model.
     */
    @objid ("16c0804a-def2-4108-b45b-2fe04a0521b2")
    public static final String PART_ID = "org.modelio.model.browser.view.part";

    /**
     * Id of browser view's {@link MPopupMenu} in the e4.xmi model.
     */
    @objid ("281f7c46-3997-489e-91c7-c482c5e15532")
    public static final String POPUP_ID = "org.modelio.model.browser.view.popupmenu";

    /**
     * Id of browser view's ViewMenu in the e4.xmi model.
     */
    @objid ("6d8830e5-78e2-47a6-ae7f-cb061116280f")
    public static final String VIEWMENU_ID = "org.modelio.model.browser.view.viewmenu";

    @objid ("af8097c3-bfe0-4ef9-aec9-5d93522c7108")
    private IPanelProvider contributedPanel;

    @objid ("b14341f2-a530-46d7-802a-7462894cf930")
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("0d1c7e20-6a9c-4e08-95da-9ded12089328")
    public IPanelProvider getContributedPanel() {
        return this.contributedPanel;
    }

    @objid ("e515f626-907b-4a4c-8def-10f7b28483a8")
    @PostConstruct
    public void postConstruct(final Composite parent) {
        // Read the extensions to find the effective browser panel
        // As only one panel is currently expected, just pick the first available one and post a LOG warning message for ignored definitions.
        this.contributedPanel = null;
        final IExtensionRegistry registry = this.eclipseContext.get(IExtensionRegistry.class);
        for (final IConfigurationElement entry : registry.getConfigurationElementsFor(BrowserView.PANELPROVIDER_EXTENSION_POINT_ID)) {
            try {
                final IPanelProvider panel = (IPanelProvider) entry.createExecutableExtension("class");
        
                if (this.contributedPanel == null) {
                    this.contributedPanel = panel;
                } else {
                    BrowserViewActivator.LOG.warning("Ignored browser view contribution: %s", entry.getDeclaringExtension());
                }
        
            } catch (ClassCastException | InvalidRegistryObjectException | CoreException e) {
                BrowserViewActivator.LOG.error(e);
            }
        }
        
        if (this.contributedPanel != null) {
            ContextInjectionFactory.inject(this.contributedPanel, this.eclipseContext);
            this.contributedPanel.createPanel(parent);
        } else {
            BrowserViewActivator.LOG.error("No browser view contributed panel found !");
        }
        
        final IProjectService projectService = this.eclipseContext.get(IProjectService.class);
        if (projectService != null) {
            final GProject project = projectService.getOpenedProject();
        
            if (project != null) {
                onProjectOpened(project);
            }
        }
        
    }

    @objid ("8e13286f-89b9-4614-9102-e0f25c408cbb")
    @PreDestroy
    public void preDestroy() {
        if (this.contributedPanel != null) {
            this.contributedPanel.dispose();
            this.contributedPanel = null;
        }
        
    }

    @objid ("041f6a72-ce7c-43ba-ac6e-cb5f41b0f0f9")
    @Focus
    public void setFocus() {
        if (this.contributedPanel != null) {
            final Control panel = (Control) this.contributedPanel.getPanel();
            if (panel.isDisposed()) {
                panel.setFocus();
            }
        }
        
    }

    @objid ("b8580884-a6ce-4f91-85a4-49bd00f91fa3")
    @Inject
    @SuppressWarnings("unused")
    @Optional
    void onFragmentEvents(@UIEventTopic(ModelioEventTopics.FRAGMENT_EVENTS) final IProjectFragment fragment) {
        if (this.contributedPanel != null
                && !((Control) this.contributedPanel.getPanel()).isDisposed()) {
            this.contributedPanel.setInput(BrowserView.this.contributedPanel.getInput());
        }
        
    }

    @objid ("912b01d1-f1f7-4f02-8ef8-8068e8f027c1")
    @Inject
    @SuppressWarnings("unused")
    @Optional
    void onModuleEvents(@UIEventTopic(ModelioEventTopics.MODULE_EVENTS) final GModule module) {
        if (this.contributedPanel != null
                && !((Control) this.contributedPanel.getPanel()).isDisposed()) {
            this.contributedPanel.setInput(BrowserView.this.contributedPanel.getInput());
        }
        
    }

    @objid ("7c518f29-5c40-46e8-a281-adada704a8df")
    @Inject
    @Optional
    void onProjectClosed(@SuppressWarnings("unused")
    @UIEventTopic(ModelioEventTopics.PROJECT_CLOSED) final GProject project) {
        this.contributedPanel.setInput(null);
    }

    @objid ("a050354a-95e7-4676-99a2-ed77fce97338")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject openedProject) {
        this.contributedPanel.setInput(openedProject);
    }

}
