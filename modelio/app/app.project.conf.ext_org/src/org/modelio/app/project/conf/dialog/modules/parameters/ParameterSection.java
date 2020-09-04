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

package org.modelio.app.project.conf.dialog.modules.parameters;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.api.module.IModule;
import org.modelio.app.project.conf.dialog.modules.parameters.standard.DefaultParameterPanelProvider;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.module.GModule;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.mda.infra.service.IRTModule.ModuleRuntimeState;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Manage the modules parameters.
 */
@objid ("13d3dc19-2795-4d29-862d-39903888bc86")
public class ParameterSection {
    @objid ("f515fb0f-b8d7-4fff-befa-545c158c8a08")
    protected IModuleManagementService moduleService;

    @objid ("89e6f69e-1fff-4106-ab67-97270dcb3900")
    private IPanelProvider parameterViewer;

    @objid ("feeb14c7-68b0-45c9-bf3f-0a1e0d9d4201")
    private Composite composite;

    @objid ("668b5388-d5c2-4c13-8041-47614a520aad")
    public ParameterSection(IEclipseContext applicationContext) {
        this.moduleService = applicationContext.get(IModuleManagementService.class);
        this.parameterViewer = null;
    }

    @objid ("d932f097-3519-427d-918b-888f3cc4e5be")
    public void setInput(GModule module) {
        if (this.parameterViewer != null) {
            this.parameterViewer.dispose();
            this.parameterViewer = null;
        }
        
        // Get the runtime module from the static one
        if (module != null && module.getModuleElement() != null) {
            IRTModule irtModule = this.moduleService.getIRTModule(module);
            if (irtModule != null && irtModule.getState() == ModuleRuntimeState.Started) {
                IModule iModule = irtModule.getIModule();
        
                final IPanelProvider customPanel = iModule.getParametersEditionPanel();
                this.parameterViewer = customPanel != null ? customPanel : new DefaultParameterPanelProvider();
                Control control = (Control) this.parameterViewer.createPanel(this.composite);
        
                GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
                gd.minimumHeight = 200;
                control.setLayoutData(gd);
        
                this.parameterViewer.setInput(Arrays.asList(iModule));
            }
        }
        
        this.composite.layout(true, true);
    }

    @objid ("a3f59ff8-1fb7-4d6b-950a-938cdfff9bf2")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        final Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(AppProjectConfExt.I18N.getString("ParameterSection.SectionText")); //$NON-NLS-1$
        section.setDescription(AppProjectConfExt.I18N.getString("ParameterSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        this.composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        this.composite.setLayout(layout);
        
        toolkit.paintBordersFor(this.composite);
        section.setClient(this.composite);
        return section;
    }

}
