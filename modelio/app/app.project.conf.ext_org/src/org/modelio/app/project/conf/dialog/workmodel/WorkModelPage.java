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
package org.modelio.app.project.conf.dialog.workmodel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.IProjectConfPage;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.workmodel.local.LocalModelSection;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;

@objid ("ee8b6e88-212d-43a8-b095-08cda4351d2f")
public class WorkModelPage implements IProjectConfPage {
    @objid ("d175e1dc-abda-4c53-8e89-fa3a4342a346")
    protected LocalModelSection localModelSection;

    @objid ("12c00998-609b-4421-8457-dca968ebc19b")
    private ScrolledForm form;

    @objid ("cf7f56ad-1200-4379-8244-8bea63b3a916")
    public  WorkModelPage() {
        //
    }

    @objid ("2687b78e-475c-4a1b-930d-5c9cbde2ffa3")
    @Override
    public ScrolledForm createControls(FormToolkit toolkit, MApplication application, final Composite parent) {
        // The form
        this.form = toolkit.createScrolledForm(parent);
        this.form.getBody().setLayout(new GridLayout());
        GridDataFactory wrappedLayoutData = GridDataFactory.fillDefaults().grab(true, false).hint(50, SWT.DEFAULT);
        
        // Local model Section
        this.localModelSection = new LocalModelSection(application.getContext());
        Section section = this.localModelSection.createControls(toolkit, this.form.getBody());
        wrappedLayoutData.applyTo(section);
        return this.form;
    }

    @objid ("b32de113-fab4-41be-a1e4-8815c0629f7d")
    @Override
    public void setInput(ProjectModel projectAdapter) {
        // update the different sections
        this.localModelSection.setInput(projectAdapter);
        
    }

    @objid ("a1c8b842-af04-4d51-9c2c-374d8d834eb7")
    @Override
    public Control getControl() {
        return this.form;
    }

    @objid ("d7cfe4be-7694-4775-8aaf-c40f3bf4a745")
    @Override
    public String getHelpTopic() {
        return AppProjectConfExt.I18N.getString("WorkModelPages.HelpId");
    }

}
