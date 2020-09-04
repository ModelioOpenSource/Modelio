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

package org.modelio.audit.preferences.ui.editingsupports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;

/**
 * StyleEditingSupport provides EditingSupport implementation for the StyleViewer.
 * <p>
 * It must be able to provide a Label and a CellEditor for all the supported StyleKey value types. It must also be able to get and
 * set values during edition, again dealing with all the possible StyleKey value types.
 */
@objid ("739f155e-5fb7-4f92-93cf-6dcc3dc30e9e")
public class AuditActivationEditingSupport extends EditingSupport {
    @objid ("afc2355c-4f68-4620-ad6d-8419591cb3f5")
    private TreeViewer viewer;

    /**
     * Initialize the StylePropertyEditingSupport.
     * @param viewer The style viewer.
     */
    @objid ("ab284d50-5496-4420-9574-e6f975e2108f")
    public AuditActivationEditingSupport(TreeViewer viewer) {
        super(viewer);
        this.viewer = viewer;
    }

    @objid ("98817efe-9072-4521-93e5-7aa0c5711f70")
    @Override
    protected boolean canEdit(Object element) {
        return element instanceof AuditRule;
    }

    @objid ("d83381cd-0ce8-4878-93ca-ccdd795f0251")
    @Override
    protected CellEditor getCellEditor(Object element) {
        return new CheckboxCellEditor();
    }

    @objid ("e9e794e6-dd13-4369-b92d-33154752bd8d")
    @Override
    protected Object getValue(Object element) {
        AuditRule rule = ((AuditRule) element);
        return rule.isEnabled();
    }

    @objid ("1a853826-fce9-42b3-a3bb-67d9920c4ca4")
    @Override
    protected void setValue(Object element, Object value) {
        AuditRule rule = ((AuditRule) element);
        rule.setEnabled((Boolean) value);
        
        AuditRule newRule = ((AuditConfigurationModel) this.viewer.getInput()).get(rule.getId());
        newRule.setEnabled((Boolean) value);
        
        this.viewer.refresh();
    }

}
