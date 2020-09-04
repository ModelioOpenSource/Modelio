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

package org.modelio.app.project.conf.dialog.audit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.audit.service.IAuditService;

@objid ("a731c6cb-33f6-11e2-a514-002564c97630")
public class AuditPage {
    @objid ("a7334d70-33f6-11e2-a514-002564c97630")
    private AuditSection auditSection;

    @objid ("53eedb6e-dc07-4455-be33-f3e4506411a1")
    private ScrolledForm form;

    @objid ("a7334d72-33f6-11e2-a514-002564c97630")
    public ScrolledForm createControls(FormToolkit toolkit, MApplication application, IAuditService auditService, final Composite parent) {
        this.form = toolkit.createScrolledForm(parent);
        
        TableWrapLayout formlayout = new TableWrapLayout();
        this.form.getBody().setLayout(formlayout);
        formlayout.makeColumnsEqualWidth = true;
        
        // Audit Section
        this.auditSection = new AuditSection(auditService);
        Section s1 = this.auditSection.createControls(toolkit, this.form.getBody());
        s1.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        return this.form;
    }

    @objid ("a7334d7a-33f6-11e2-a514-002564c97630")
    public void setInput(ProjectModel projectAdapter) {
        this.auditSection.setInput(projectAdapter);
    }

}
