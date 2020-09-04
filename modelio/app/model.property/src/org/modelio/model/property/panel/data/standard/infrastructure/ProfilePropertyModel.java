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
import org.modelio.metamodel.uml.infrastructure.Profile;

/**
 * The properties model of the Profile element.
 */
@objid ("46296b37-d7d3-4fce-9fa9-10ae7a6b508c")
public class ProfilePropertyModel extends AbstractPropertyModel<Profile> {
    @objid ("bffa2c0d-d46e-40f1-b7be-1619a7855be5")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name" };

    /**
     * Instantiate the profile properties view.
     * 
     * @param theEditedElement the current profile.
     */
    @objid ("46ca4c30-6f85-4cb3-8ab0-deb41fe62e87")
    public ProfilePropertyModel(Profile theEditedElement) {
        super(theEditedElement);
    }

    @objid ("e5257ee4-660b-4ffc-ac31-cb127c43bd1d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("ed5ab5f7-4048-46d0-90ad-7d494af32d5c")
    @Override
    public int getRowsNumber() {
        return ProfilePropertyModel.PROPERTIES.length;
    }

    @objid ("4b76a088-5e06-41b0-8ebf-72cb120470b8")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ProfilePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("39bed1c1-f483-47a7-8bc4-f0240a7839ce")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("5d9a43d0-904f-4766-9e46-625281bf0d91")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            return false;
        }
        String profileName = this.theEditedElement.getName();
        if (profileName != null && profileName.equals("LocalProfile")) {
            return false;
        }
        return super.isEditable(row, col);
    }

    @objid ("39264eca-dfc2-4492-aad5-183747fad0c3")
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

}
