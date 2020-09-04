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

package org.modelio.app.project.conf.dialog.libraries;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.libraries.distant.DistantLibrariesSection;
import org.modelio.app.project.conf.dialog.libraries.local.LocalLibrariesSection;

@objid ("a739683a-33f6-11e2-a514-002564c97630")
public class LibrariesPage {
    @objid ("a739683b-33f6-11e2-a514-002564c97630")
    protected LocalLibrariesSection mcLibSection;

    @objid ("a739683c-33f6-11e2-a514-002564c97630")
    protected DistantLibrariesSection distantLibSection;

    @objid ("a739683e-33f6-11e2-a514-002564c97630")
    public ScrolledForm createControls(FormToolkit toolkit, MApplication application, final Composite parent) {
        // The form
        ScrolledForm form = toolkit.createScrolledForm(parent);
        form.getBody().setLayout(new TableWrapLayout());
        
        // Local libraries Section
        this.mcLibSection = new LocalLibrariesSection(application.getContext());
        Section section = this.mcLibSection.createControls(toolkit, form.getBody());
        section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        
        // Distant libraries Section
        this.distantLibSection = new DistantLibrariesSection(application.getContext());
        section = this.distantLibSection.createControls(toolkit, form.getBody());
        section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        return form;
    }

    @objid ("a73aeeb2-33f6-11e2-a514-002564c97630")
    public void setInput(ProjectModel projectAdapter) {
        // update the different sections
        this.mcLibSection.setInput(projectAdapter);
        this.distantLibSection.setInput(projectAdapter);
    }

}
