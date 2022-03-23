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
package org.modelio.audit.preferences.ui.editingsupports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.platform.model.ui.treetable.combo.LabelsComboBoxCellEditor;

/**
 * StyleEditingSupport provides EditingSupport implementation for the StyleViewer.
 * <p>
 * It must be able to provide a Label and a CellEditor for all the supported StyleKey value types. It must also be able to get and
 * set values during edition, again dealing with all the possible StyleKey value types.
 */
@objid ("529d8baf-a69f-41bb-a73c-86af45cae807")
public class AuditSeverityEditingSupport extends EditingSupport {
    @objid ("8a039150-0f36-425c-b628-9f8708f392b4")
    private TreeViewer viewer;

    /**
     * Initialize the StylePropertyEditingSupport.
     * @param viewer The style viewer.
     */
    @objid ("5bb5b195-09f2-4cc5-82cf-c422b4771c05")
    public  AuditSeverityEditingSupport(TreeViewer viewer) {
        super(viewer);
        this.viewer = viewer;
        
    }

    @objid ("2bc74434-22a4-49e2-81ff-2f95e9607dd6")
    @Override
    protected boolean canEdit(Object element) {
        return element instanceof AuditRule;
    }

    @objid ("0789c270-10c2-4bc5-88dd-6bbe73ce1e35")
    @Override
    protected CellEditor getCellEditor(Object element) {
        return new LabelsComboBoxCellEditor(this.viewer.getTree(), AuditSeverity.getRuntimeValues(), SWT.SINGLE);
    }

    @objid ("b24e1bf2-fb24-4932-99fb-c4309c5e8733")
    @Override
    protected Object getValue(Object element) {
        AuditRule rule = ((AuditRule) element);
        return rule.getSeverity().getLabel();
    }

    @objid ("7e51fc2f-d84c-44c6-b1c4-eb7c13f2877b")
    @Override
    protected void setValue(Object element, Object value) {
        AuditRule rule = ((AuditRule) element);
        rule.setSeverity(AuditSeverity.findByLabel((String) value));
        
        AuditRule newRule = ((AuditConfigurationModel) this.viewer.getInput()).get(rule.getId());
        newRule.setSeverity(AuditSeverity.findByLabel((String) value));
        
        this.viewer.refresh();
        
    }

}
