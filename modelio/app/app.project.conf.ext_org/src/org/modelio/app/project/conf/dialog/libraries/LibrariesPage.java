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

package org.modelio.app.project.conf.dialog.libraries;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.modelio.app.project.conf.dialog.IProjectConfPage;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.libraries.distant.DistantLibrariesSection;
import org.modelio.app.project.conf.dialog.libraries.local.LocalLibrariesSection;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;

@objid ("a739683a-33f6-11e2-a514-002564c97630")
public class LibrariesPage implements IProjectConfPage {
    @objid ("a739683b-33f6-11e2-a514-002564c97630")
    protected LocalLibrariesSection mcLibSection;

    @objid ("a739683c-33f6-11e2-a514-002564c97630")
    protected DistantLibrariesSection distantLibSection;

    @objid ("b1b0c64d-f757-42c1-b1e0-ebd1aaec7899")
    private ScrolledForm form;

    @objid ("a739683e-33f6-11e2-a514-002564c97630")
    @Override
    public ScrolledForm createControls(final FormToolkit toolkit, final MApplication application, final Composite parent) {
        // The form
        this.form = toolkit.createScrolledForm(parent);
        this.form.getBody().setLayout(new TableWrapLayout());
        
        // Local libraries Section
        this.mcLibSection = new LocalLibrariesSection(application.getContext());
        Section section = this.mcLibSection.createControls(toolkit, this.form.getBody());
        section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        
        // Distant libraries Section
        this.distantLibSection = new DistantLibrariesSection(application.getContext());
        section = this.distantLibSection.createControls(toolkit, this.form.getBody());
        section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        return this.form;
    }

    @objid ("a73aeeb2-33f6-11e2-a514-002564c97630")
    @Override
    public void setInput(final ProjectModel projectAdapter) {
        // update the different sections
        this.mcLibSection.setInput(projectAdapter);
        this.distantLibSection.setInput(projectAdapter);
    }

    @objid ("1c4b48bd-75e0-443c-9577-7acfab5fdd08")
    @Override
    public Control getControl() {
        // TODO Auto-generated method stub
        return this.form;
    }

    @objid ("5b82f7ea-2de1-4c0c-9903-5c1e11cde410")
    @Override
    public String getHelpTopic() {
        return AppProjectConfExt.I18N.getString("LibrariesPage.HelpId");
    }

}
