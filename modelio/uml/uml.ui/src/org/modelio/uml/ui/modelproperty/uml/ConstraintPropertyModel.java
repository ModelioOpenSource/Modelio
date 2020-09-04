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

import java.util.Arrays;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Constraint</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Constraint</i>
 * metaclass.
 */
@objid ("03e5751f-dfed-4db9-a5e8-49ffd0d756ac")
public class ConstraintPropertyModel extends AbstractPropertyModel<Constraint> {
    /**
     * Properties to display for <i>Constraint</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("2002322c-2e47-4de6-88fc-ededf4294fda")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Body",
			"Language", "ConstrainedElement" };

    /**
     * Create a new <i>Constraint</i> data model from an <i>Constraint</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("06f1f418-de9d-4dfa-beaf-936546cccf87")
    public ConstraintPropertyModel(Constraint theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("62379257-31dd-444d-8fd3-5ad014c9fc9f")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("66d3790e-ae26-4cec-9de9-147c68c9f1c4")
    @Override
    public int getRowsNumber() {
        return ConstraintPropertyModel.PROPERTIES.length;
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
    @objid ("6354cbeb-70b0-4cfb-823c-c0e038236ab9")
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
                return this.theEditedElement.getBody();
            case 3:
                return this.theEditedElement.getLanguage();
            case 4:
                return this.theEditedElement.getConstrainedElement();
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
    @objid ("9745da22-1f53-46e6-bfd7-a4a60be67fb8")
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
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), false, Arrays.asList(UmlModelElement.class));
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
    @objid ("74aaca27-23c7-487d-8ee4-aae391dcc68c")
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
                this.theEditedElement.setBody((String) value);
                break;
            case 3:
                this.theEditedElement.setLanguage((String) value);
                break;
            case 4:
                this.theEditedElement.getConstrainedElement().clear();
                this.theEditedElement.getConstrainedElement().addAll((Collection<? extends UmlModelElement>) value);
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
