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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Operation</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Operation</i>
 * metaclass.
 */
@objid ("a37748b6-b61b-4fd7-9093-751397f4a0f2")
public class OperationPropertyModel extends AbstractPropertyModel<Operation> {
    /**
     * Properties to display for <i>Operation</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("de1edaf8-e684-433c-9d40-aa69e0bba723")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Visibility",
			"IsAbstract", "IsClass", "Final", "Passing", "Redefines" };

    /**
     * Create a new <i>Operation</i> data model from an <i>Operation</i>.
     * @param theEditedElement The edited element.
     */
    @objid ("d54b150f-06b9-4100-a7ff-d7716e01a817")
    public OperationPropertyModel(Operation theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("7b4e74fe-ab62-4975-ab11-fab81b288866")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("f1884a8e-7daa-4dc5-a7a3-aa32b32d6571")
    @Override
    public int getRowsNumber() {
        if (this.theEditedElement.isIsClass()) {
            return OperationPropertyModel.PROPERTIES.length - 1;
        }
        return OperationPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("e214fc31-3f16-4189-8d12-b49319e21ef3")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getVisibility();
            case 3:
                return this.theEditedElement.isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.isIsClass() ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return this.theEditedElement.isFinal() ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return this.theEditedElement.getPassing();
            case 7:
                return this.theEditedElement.getRedefines();
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
    @objid ("0c5290c1-4bb4-492c-9102-e632af9d81e4")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), VisibilityMode.class);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 6:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), MethodPassingMode.class);
            case 7:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(Operation.class));
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
    @objid ("346e34ac-f536-45cb-8439-043377552051")
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
                this.theEditedElement.setVisibility((VisibilityMode) value);
                break;
            case 3:
                this.theEditedElement.setIsAbstract(((Boolean) value).booleanValue());
                break;
            case 4:
                this.theEditedElement.setIsClass(((Boolean) value).booleanValue());
                break;
            case 5:
                this.theEditedElement.setFinal(((Boolean) value).booleanValue());
                break;
            case 6:
                this.theEditedElement.setPassing((MethodPassingMode) value);
                break;
            case 7:
                final Operation newValue = (Operation) value;
                if (!Objects.equals(newValue, this.theEditedElement)) {
                    this.theEditedElement.setRedefines(newValue);
                }
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

}
