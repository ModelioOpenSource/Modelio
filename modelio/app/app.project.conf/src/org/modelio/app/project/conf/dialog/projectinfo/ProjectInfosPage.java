/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.app.project.conf.dialog.projectinfo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.IProjectConfPage;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConf;

/**
 * This view displays information about the project currently selected in the workspace project list as a set of 'sections'. Practically, the view has to distinguish between two cases:
 * <ul>
 * <li>the displayed project is the currently opened project
 * <li>the displayed project is NOT the currently opened project
 * 
 * In the first case, most sections will allow modifying the project configuration while such modifications are forbidden in the second case.
 */
@objid ("a745c46e-33f6-11e2-a514-002564c97630")
public class ProjectInfosPage implements IProjectConfPage {
    @objid ("a745c470-33f6-11e2-a514-002564c97630")
    private FragmentsSection fragmentsSection;

    @objid ("a745c471-33f6-11e2-a514-002564c97630")
    private ScrolledForm form;

    @objid ("a745c472-33f6-11e2-a514-002564c97630")
    private ModulesSection modulesSection;

    @objid ("a745c473-33f6-11e2-a514-002564c97630")
    private StorageSection storageSection;

    @objid ("a745c475-33f6-11e2-a514-002564c97630")
    private GeneralSection generalSection;

    /**
     * Creates the SWT controls.
     * <p>
     * Called by E4 injection.
     * 
     * @param toolkit a form toolkit
     * @param application the E4 application model
     * @param parent the parent composite.
     * @return the created form
     */
    @objid ("a745c477-33f6-11e2-a514-002564c97630")
    @Override
    public ScrolledForm createControls(FormToolkit toolkit, MApplication application, final Composite parent) {
        // The form
        this.form = toolkit.createScrolledForm(parent);
        
        GridLayout formlayout = new GridLayout();
        this.form.getBody().setLayout(formlayout);
        formlayout.numColumns = 2;
        formlayout.makeColumnsEqualWidth = true;
        
        // General section
        this.generalSection = new GeneralSection(this.form.getMessageManager());
        Section section = this.generalSection.createControls(toolkit, this.form.getBody());
        GridData twd = new GridData(SWT.FILL, SWT.FILL, true, false);
        section.setLayoutData(twd);
        
        // Storage section
        this.storageSection = new StorageSection();
        section = this.storageSection.createControls(toolkit, this.form.getBody());
        twd = new GridData(SWT.FILL, SWT.TOP, false, false);
        section.setLayoutData(twd);
        
        // Fragment Section
        this.fragmentsSection = new FragmentsSection(application.getContext());
        section = this.fragmentsSection.createControls(toolkit, this.form.getBody());
        twd = new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1);
        section.setLayoutData(twd);
        
        // Modules Section
        this.modulesSection = new ModulesSection(application.getContext());
        section = this.modulesSection.createControls(toolkit, this.form.getBody());
        twd = new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1);
        section.setLayoutData(twd);
        return this.form;
    }

    @objid ("a745eb5d-33f6-11e2-a514-002564c97630")
    @Override
    public void setInput(ProjectModel projectAdapter) {
        // update the different sections
        this.generalSection.setInput(projectAdapter);
        this.storageSection.setInput(projectAdapter);
        this.fragmentsSection.setInput(projectAdapter);
        this.modulesSection.setInput(projectAdapter);
    }

    @objid ("b8cdd418-fe2f-490d-af80-d15bdfc4a6f1")
    @Override
    public Control getControl() {
        // TODO Auto-generated method stub
        return this.form;
    }

    @objid ("2a2a3ac4-c66f-48f2-ad4c-c5d64e2b2d80")
    @Override
    public String getHelpTopic() {
        return AppProjectConf.I18N.getString("ProjectInfoPage.HelpId");
    }

}
