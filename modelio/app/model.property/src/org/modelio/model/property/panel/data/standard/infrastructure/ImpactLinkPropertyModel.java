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
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ImpactLink</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ImpactLink</i>
 * metaclass.
 */
@objid ("9fbd597b-40fc-4ccc-9dba-d5c71515d4ed")
public class ImpactLinkPropertyModel extends AbstractPropertyModel<ImpactLink> {
    /**
     * Properties to display for <i>ImpactLink</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("6784fe03-c98d-4712-a23d-136f324c448e")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Causes" };

    /**
     * Create a new <i>ImpactLink</i> data model from an <i>ImpactLink</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("2e93e6fb-a2d7-4787-8c6f-cf5d79c2220f")
    public ImpactLinkPropertyModel(ImpactLink theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("a27fa999-dd1b-4217-8c46-23bf80d64283")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("cf216d30-e10f-4946-a211-6fb71d294058")
    @Override
    public int getRowsNumber() {
        return 1 + this.theEditedElement.getCauses().size();
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
    @objid ("384470bb-ec24-4f90-b4a7-d064919ee9d8")
    private Object getValue(int row, int col) {
        if (col == 0) {
            if (row == 0) {
                return getPropertyI18n(ImpactLinkPropertyModel.PROPERTIES[row]);
            } else if (row == 1) {
                return getPropertyI18n(ImpactLinkPropertyModel.PROPERTIES[row]);
            } else {
                return null;
            }
        } else if (col == 1) {
            if (row == 0) {
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            } else {
                return this.theEditedElement.getCauses().get(row - 1);
            }
        } else {
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
    @objid ("535f9e59-dee6-4953-b63c-4371f27e4079")
    @Override
    public INatValue getValueAt(int row, int col) {
        if (col == 0) {
            return new DefaultStringNatValue((String) getValue(row, col), false);
        } else if (col == 1) {
            if (row == 0) {
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else {
                return new DefaultElementNatValue((MObject) getValue(row, col), false, Collections.emptyList());
            }
        } else {
            return null;
        }
    }

    @objid ("23e2177b-0972-478b-aec6-bfff03d6c445")
    @Override
    public boolean isEditable(int row, int col) {
        return false;
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
    @objid ("a9379a12-916a-4260-a4b3-439a76e07a42")
    @Override
    public void setValueAt(int row, int col, Object value) {
        return;
    }

}
