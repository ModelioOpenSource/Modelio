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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ConnectorEnd</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ConnectorEnd</i>
 * metaclass.
 */
@objid ("fb80cfce-18b2-4ef9-8b4e-468cad1cd939")
public class ConnectorEndPropertyModel extends AbstractPropertyModel<ConnectorEnd> {
    /**
     * Properties to display for <i>ConnectorEnd</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for other rows the values usually match the meta-attributes and roles
     * names of the metaclass
     * </ul>
     */
    @objid ("341764ef-ba0a-4a54-80ea-f5d8cc03578c")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
			"MultiplicityMax", "MultiplicityMin", "IsNavigable", "IsOrdered", "IsUnique", "RepresentedFeature" };

    /**
     * Create a new <i>ConnectorEnd</i> data model from an <i>ConnectorEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("3c7dc97b-0de2-4c1e-9db8-8c542bc075ee")
    public ConnectorEndPropertyModel(ConnectorEnd theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("6b36a86f-5e7f-41c8-888a-454ecffa7286")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("44b9e7dd-fa72-4180-999b-d901e40a3a2f")
    @Override
    public int getRowsNumber() {
        return ConnectorEndPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("ef7aa2c1-753c-4144-9370-1346faf4e4f4")
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
                return this.theEditedElement.getMultiplicityMax();
            case 3:
                return this.theEditedElement.getMultiplicityMin();
            case 4:
                return this.theEditedElement.isNavigable();
            case 5:
                return this.theEditedElement.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return this.theEditedElement.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return this.theEditedElement.getRepresentedFeature();
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
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("e0641518-0a8a-4b90-90de-2c2cef39abb3")
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
            case 6:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 7:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(UmlModelElement.class));
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
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("1ca15df6-9ae4-4a66-b3a4-a480380e7c3e")
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
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 3:
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 4:
                this.theEditedElement.setNavigable((Boolean) value);
                break;
            case 5:
                this.theEditedElement.setIsOrdered(((Boolean) value).booleanValue());
                break;
            case 6:
                this.theEditedElement.setIsUnique(((Boolean) value).booleanValue());
                break;
            case 7:
                this.theEditedElement.setRepresentedFeature((UmlModelElement) value);
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
