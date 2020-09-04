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

package org.modelio.model.property.panel.data.standard.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.model.property.plugin.ModelProperty;

/**
 * <i>ImpactModel</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ImpactModel</i>
 * metaclass.
 */
@objid ("a85eeb54-def1-419d-b1d9-cf3dc4f92967")
public class ImpactModelPropertyModel extends AbstractPropertyModel<ImpactModel> {
    /**
     * Properties to display for <i>ImpactModel</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("34d12481-5636-42b0-bd4d-58ed4340dd99")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "ImpactMode" };

    /**
     * Create a new <i>ImpactModel</i> data model from an <i>ImpactModel</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("6c15cf31-82a9-4025-ac35-3965ccd2b0f5")
    public ImpactModelPropertyModel(ImpactModel theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("f275f770-f239-400e-9d37-7169ab56eadf")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("5d2492dc-520c-49a2-a7ee-abbfa56e886d")
    @Override
    public int getRowsNumber() {
        return ImpactModelPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("7908e2d5-940d-41fa-a7bf-75d2b82274bf")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ImpactModelPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return getPropertyI18n(getMode(this.theEditedElement));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Return the type of the element displayed at the specified row and column.
     * <p>
     * This type will be used to choose an editor and a renderer for each cell
     * of the properties table.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("2ac6be71-104a-47f5-942e-e2d6f2704ec1")
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
            case 2:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("9a5b3bcb-4008-43e7-a0e5-9370001f1c01")
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
                break;
            case 2:
                setMode(this.theEditedElement, (String) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("d47eaedb-7659-4564-950c-4bedc19ec314")
    private String getMode(ImpactModel impactModel) {
        String impactMode = impactModel.getLocalProperty("ImpactMode");
        return impactMode != null ? impactMode : "MANUAL";
    }

    @objid ("3d65736f-9a10-4f97-8203-32ede35781fa")
    private void setMode(ImpactModel impactModel, String mode) {
        impactModel.setLocalProperty("ImpactMode", mode);
    }

    @objid ("4f3bee9c-ff79-4e13-9931-f55efa0b5fbd")
    @Override
    public boolean isEditable(int row, int col) {
        switch (col) {
        case 1: // col 1 is the property value
            switch (row) {
            case 2:
                // Mode is not editable
                return false;
            default:
            }
            break;
        default:
        }
        return super.isEditable(row, col);
    }

    @objid ("1a05ad85-7ed1-4bbf-9199-de2fd94a9343")
    @Override
    protected String getPropertyI18n(String key) {
        return ModelProperty.I18N.getString("Impact." + key);
    }

}
