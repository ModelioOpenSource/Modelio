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
package org.modelio.audit.projectconf;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
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
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.service.IAuditService;

@objid ("a731c6cb-33f6-11e2-a514-002564c97630")
public class AuditPage implements IProjectConfPage {
    @objid ("fb21dbbb-4670-420c-95c2-75bc952d7349")
    private ScrolledForm form;

    @objid ("a7334d70-33f6-11e2-a514-002564c97630")
    private AuditSection auditSection;

    @objid ("a7334d72-33f6-11e2-a514-002564c97630")
    @Override
    public ScrolledForm createControls(FormToolkit toolkit, MApplication application, Composite parent) {
        IEclipseContext applicationContext = application.getContext();
        this.form = toolkit.createScrolledForm(parent);
        
        TableWrapLayout formlayout = new TableWrapLayout();
        this.form.getBody().setLayout(formlayout);
        formlayout.makeColumnsEqualWidth = true;
        
        // Audit Section
        this.auditSection = new AuditSection(applicationContext.get(IAuditService.class));
        Section s1 = this.auditSection.createControls(toolkit, this.form.getBody());
        s1.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        return this.form;
    }

    @objid ("a7334d7a-33f6-11e2-a514-002564c97630")
    @Override
    public void setInput(ProjectModel projectAdapter) {
        this.auditSection.setInput(projectAdapter);
    }

    @objid ("4ff8b66e-5ae8-46a5-8b5f-0bd455b580e0")
    @Override
    public Control getControl() {
        return this.form;
    }

    @objid ("10d1d398-4076-4ab1-bc6a-e5c57fb7046f")
    @Override
    public String getHelpTopic() {
        return Audit.I18N.getString("AuditPage.HelpId");
    }

}
