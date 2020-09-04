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

package org.modelio.audit.projectconf;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.ui.AuditPreferenceUi;
import org.modelio.audit.service.IAuditService;

/**
 * The audit management section.
 */
@objid ("a7334d7d-33f6-11e2-a514-002564c97630")
class AuditSection {
    @objid ("4b28d66b-43ac-11e2-a5d7-bc305ba4815c")
    private IAuditService auditService;

    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("a7334d7f-33f6-11e2-a514-002564c97630")
    private ProjectModel displayedProject;

    @objid ("a7334d82-33f6-11e2-a514-002564c97630")
    public AuditSection(IAuditService auditService) {
        this.auditService = auditService;
    }

    @objid ("a7334d85-33f6-11e2-a514-002564c97630")
    public void setInput(ProjectModel projectAdapter) {
        this.displayedProject = projectAdapter;
    }

    @objid ("a7334d88-33f6-11e2-a514-002564c97630")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(Audit.I18N.getString("AuditSection.SectionText")); //$NON-NLS-1$
        section.setDescription(Audit.I18N.getString("AuditSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
        composite.setLayout(new GridLayout(2, true));
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        
        AuditPreferenceUi pref = new AuditPreferenceUi(this.auditService);
        pref.createContents(composite);
        return section;
    }

}
