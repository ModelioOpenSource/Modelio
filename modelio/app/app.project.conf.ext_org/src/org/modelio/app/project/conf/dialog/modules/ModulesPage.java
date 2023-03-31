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
package org.modelio.app.project.conf.dialog.modules;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.modelio.app.project.conf.dialog.IProjectConfPage;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.modules.list.ModulesSection;
import org.modelio.app.project.conf.dialog.modules.parameters.ParameterSection;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.parts.module.GModule;

/**
 * Modules page
 */
@objid ("2f7f3ef4-7cd9-499b-9ded-ced4af367ecc")
public class ModulesPage implements IProjectConfPage {
    @objid ("878bb62a-8cb8-405f-a9f5-8f2dc5092579")
    protected ModulesSection modulesSection;

    @objid ("d078e80f-9e74-4269-a869-191eb1184a8c")
    protected ParameterSection parameterSection;

    @objid ("e299385a-6208-4082-9d17-bdd80025043b")
    ProjectModel projectAdapter;

    @objid ("39256e16-0353-4552-a5da-6b3c62fed0ba")
    private Composite mainComposite;

    /**
     * @param toolkit the form toolkit
     * @param application the application model
     * @param parent the parent composite
     * @return the created page
     */
    @objid ("a048de2e-337a-4f34-90e7-46a8ca2a059f")
    @Override
    public Composite createControls(FormToolkit toolkit, MApplication application, final Composite parent) {
        IEclipseContext applicationContext = application.getContext();
        this.mainComposite = toolkit.createComposite(parent, SWT.NONE);
        this.mainComposite.setLayout(new GridLayout());
        
        // The form
        ScrolledForm form = toolkit.createScrolledForm(this.mainComposite);
        form.getBody().setLayout(new TableWrapLayout());
        form.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // Modules Section
        this.modulesSection = new ModulesSection(applicationContext);
        Section s1 = this.modulesSection.createControls(toolkit, form.getBody());
        s1.setLayoutData(new TableWrapData(TableWrapData.FILL));
        
        // Parameters Section
        this.parameterSection = new ParameterSection(applicationContext);
        final Section s3 = this.parameterSection.createControls(toolkit, form.getBody());
        s3.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        
        // Parameter updater
        
        this.modulesSection.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    if (structuredSelection.isEmpty()) {
                        ModulesPage.this.parameterSection.setInput(null);
                        // force layout of the new zone
                        boolean expanded = s3.isExpanded();
                        s3.setExpanded(!expanded);
                        s3.setExpanded(expanded);
                    } else if (structuredSelection.size() == 1) {
                        Object obj = structuredSelection.getFirstElement();
                        if (obj instanceof GModule) {
                            GModule module = (GModule) obj;
                            ModulesPage.this.parameterSection.setInput(module);
                            // force layout of the new zone
                            boolean expanded = s3.isExpanded();
                            s3.setExpanded(!expanded);
                            s3.setExpanded(expanded);
                        }
                    }
                }
            }
        });
        return this.mainComposite;
    }

    /**
     * Set the data model.
     * @param projectAdapter the project data model.
     */
    @objid ("c453de73-acd7-4147-b72f-bf91ce833a18")
    @Override
    public void setInput(ProjectModel projectAdapter) {
        this.projectAdapter = projectAdapter;
        // update the different sections
        this.modulesSection.setInput(projectAdapter);
        
    }

    @objid ("cefcbeca-7b38-4b76-a7d7-38bee254fd1f")
    ProjectModel getProjectAdapter() {
        return this.projectAdapter;
    }

    @objid ("a9ad5de7-afc6-403c-874b-376a66b5e348")
    @Override
    public Control getControl() {
        return this.mainComposite;
    }

    @objid ("50af6bd1-924f-437e-981d-1aa54f538e4e")
    @Override
    public String getHelpTopic() {
        return AppProjectConfExt.I18N.getString("ModulesPages.HelpId");
    }

}
