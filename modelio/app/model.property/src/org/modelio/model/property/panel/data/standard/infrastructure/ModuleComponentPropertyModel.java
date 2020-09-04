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

package org.modelio.model.property.panel.data.standard.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.mda.ModuleComponent;

/**
 * The properties model of the Profile element.
 */
@objid ("c2bd5261-f104-46e7-9c49-94fc170b2951")
public class ModuleComponentPropertyModel extends AbstractPropertyModel<ModuleComponent> {
    @objid ("9c33b77a-e120-468a-a505-7fda6784eafd")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name" , "Version"};

    @objid ("898bf570-de27-4c87-b0df-c96f24d2b7d9")
    private static final String[] LOCAL_MODULE_PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name" };

    /**
     * Instantiate the project properties view.
     * 
     * @param theEditedElement the current project.
     */
    @objid ("d3d0e292-18bb-4866-afdc-43fe666993d3")
    public ModuleComponentPropertyModel(ModuleComponent theEditedElement) {
        super(theEditedElement);
    }

    @objid ("3a40887f-2dff-4286-bc84-2af1df9c7d0b")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("d99d6af8-915a-478e-9d22-5509bc9b1dc5")
    @Override
    public int getRowsNumber() {
        return getProperties().length;
    }

    @objid ("90c875f7-a968-4a6f-98cb-afcce39f1fba")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(getProperties()[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getMajVersion() + "."+this.theEditedElement.getMinVersion() + "." + this.theEditedElement.getMinMinVersion();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("1bc9eb23-e668-4aa9-a960-1925741bb915")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
            case 1:
            case 2:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("d92db6ed-7541-447e-b1c5-b647260e2bad")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            return false;
        }
        
        if (row == 1) {
            if (isLocalModule()) {
                // LocalModule name and attributes are not editable.
                return false;
            }
            return super.isEditable(row, col);
        }
        
        // Nothing else is editable
        return false;
    }

    @objid ("ef24b4bf-abc7-405f-8eeb-9f25110621f2")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                this.theEditedElement.setName((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

    @objid ("a8b8dd76-dfd2-4333-8b14-092a3bbb16ba")
    private String[] getProperties() {
        if (isLocalModule()) {
            return LOCAL_MODULE_PROPERTIES;
        } else {
            return PROPERTIES;
        }
    }

    @objid ("cb66d21f-7158-4cb1-beab-4b9fcbf397c6")
    private boolean isLocalModule() {
        String moduleName = this.theEditedElement.getName();
        return (moduleName != null && moduleName.equals("LocalModule"));
    }

}
