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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class provide the data to display module parameter properties.
 */
@objid ("6150236d-38a9-4a7f-89c9-891f0fbe8981")
public class ModuleParameterPropertyModel extends AbstractPropertyModel<ModuleParameter> {
    @objid ("05e0951c-2c59-4db5-baed-daca94173ff0")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
	        "EnumType", "DefaultValue" };

    /**
     * Instantiate the profile properties view.
     * 
     * @param theEditedElement the current profile.
     */
    @objid ("dc294bb5-5fa5-4900-8819-189c9017a704")
    public ModuleParameterPropertyModel(ModuleParameter theEditedElement) {
        super(theEditedElement);
    }

    @objid ("a5ca1aa0-f7e1-47e8-a8a3-99b4339e03d5")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("856d3cef-a6be-46ed-a6b2-8bb57191f2c1")
    @Override
    public int getRowsNumber() {
        return ModuleParameterPropertyModel.PROPERTIES.length;
    }

    @objid ("847c5d59-7e8e-46e9-a5ca-67451bc544a0")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ModuleParameterPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getType();
            case 3:
                return this.theEditedElement.getEnumType();
            case 4:
                return this.theEditedElement.getDefaultValue();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("2cd2691e-aa14-43d1-948f-eec48a51dab7")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ModuleParameterType.class);
            case 3:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(EnumeratedPropertyType.class));
            case 4:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("ea78491a-c96a-443a-b6b2-c49be1e01bed")
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
            case 2:
                this.theEditedElement.setType((ModuleParameterType) value);
                return;
            case 3:
                this.theEditedElement.setEnumType((EnumeratedPropertyType) value);
                return;
            case 4:
                this.theEditedElement.setDefaultValue((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
