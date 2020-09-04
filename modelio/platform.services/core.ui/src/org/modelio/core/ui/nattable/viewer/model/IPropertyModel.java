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

package org.modelio.core.ui.nattable.viewer.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface defines the contract needed to implement a specific element property model.
 * <p>
 * Property models are designed to work as tables, with row/column coordinates.
 * </p>
 * @param <T> the type of element this property model edits.
 */
@objid ("73a6d87e-2f68-48e8-81ed-3d167db95439")
public interface IPropertyModel<T extends MObject> {
    /**
     * Get the number of columns to be displayed for the edited element.
     * @return a positive integer.
     */
    @objid ("b3334b01-11a7-407e-8830-215991cdb8ab")
    int getColumnNumber();

    /**
     * @return the currently edited element.
     */
    @objid ("92707380-61a9-48ce-a9d3-e256a16f1995")
    T getEditedElement();

    /**
     * Get the number of rows to be displayed for the edited element.
     * @return a positive integer.
     */
    @objid ("6de786af-8aca-435b-ba4b-a3c3352c2cbd")
    int getRowsNumber();

    /**
     * Get the {@link INatValue} at coordinates (row, col) for the edited element.
     * @param row the row index.
     * @param col the column index.
     * @return an {@link INatValue}. Might be <code>null</code>.
     */
    @objid ("829ee92a-2923-47aa-84f6-380b7e3c9d41")
    INatValue getValueAt(int row, int col);

    /**
     * Ask if the cell at coordinates (row, col) can be edited for the edited element.
     * @param row the row index.
     * @param col the column index.
     * @return <code>true</code> if the cell can be edited.
     */
    @objid ("21cfd518-b73e-4e8a-8cc3-d215273ccdfe")
    boolean isEditable(int row, int col);

    /**
     * Set a new value in the cell at coordinates (row, col) for the edited element.
     * @param row the row index.
     * @param col the column index.
     * @param value an {@link Object} consistent with the {@link INatValue} returned by {@link #getValueAt(int, int)}. Might be <code>null</code>.
     */
    @objid ("3afd44d1-f2ae-48d9-8f96-b91c38680d8c")
    void setValueAt(int row, int col, Object value);

}
