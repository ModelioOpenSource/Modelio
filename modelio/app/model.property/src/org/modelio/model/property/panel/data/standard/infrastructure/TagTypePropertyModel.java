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
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.TagType;

/**
 * <i>TagType</i> data model.
 * <p>
 * This class provides the list of properties for the <i>TagType</i> metaclass.
 */
@objid ("ad66d7d2-f2e4-4976-81fe-a137499f88f0")
public class TagTypePropertyModel extends AbstractPropertyModel<TagType> {
    @objid ("7e8e5d75-0bd1-47c3-a403-af335f5ab88c")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Label",
	        "ParamNumber", "IsHidden", "BelongToPrototype" };

    /**
     * Instantiate the tag type properties view.
     * 
     * @param theEditedElement the current tag type.
     */
    @objid ("b926989c-67c0-4f3d-9b21-265a1737b0a6")
    public TagTypePropertyModel(TagType theEditedElement) {
        super(theEditedElement);
    }

    @objid ("798f291f-d47a-4179-8236-7ae0b5f40f88")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("e2f340cf-799e-48cb-b204-ae23d980d215")
    @Override
    public int getRowsNumber() {
        return TagTypePropertyModel.PROPERTIES.length;
    }

    @objid ("81da5769-5194-49d2-8511-0c44665a9c05")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(TagTypePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getLabelKey();
            case 3:
                return this.theEditedElement.getParamNumber();
            case 4:
                return this.theEditedElement.isIsHidden() ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return this.theEditedElement.isBelongToPrototype() ? Boolean.TRUE : Boolean.FALSE;
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("c2601e2d-eb95-463b-94ac-eca7fe7893c0")
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
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("28e2306b-7a3d-4593-99ec-63c728cd7ac8")
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
                this.theEditedElement.setLabelKey((String) value);
                return;
            case 3:
                this.theEditedElement.setParamNumber((String) value);
                return;
            case 4:
                this.theEditedElement.setIsHidden(((Boolean) value).booleanValue());
                return;
            case 5:
                this.theEditedElement.setBelongToPrototype(((Boolean) value).booleanValue());
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
