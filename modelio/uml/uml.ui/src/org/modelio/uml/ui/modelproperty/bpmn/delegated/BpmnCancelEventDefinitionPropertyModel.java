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

package org.modelio.uml.ui.modelproperty.bpmn.delegated;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnCancelEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnCancelEventDefinition</i> metaclass.
 */
@objid ("57ac02a4-323d-4e79-8614-550e083596d4")
public class BpmnCancelEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnCancelEventDefinition> {
    /**
     * Properties to display for <i>BpmnCancelEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("5a3467e8-a8e2-45ce-a3f0-aa034234c809")
    private static final String[] PROPERTIES = new String[] {};

    /**
     * Create a new <i>BpmnCancelEventDefinition</i> data model from an
     * <i>BpmnCancelEventDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("f297961c-ea4a-4da5-a4cf-67b8e8230c74")
    public BpmnCancelEventDefinitionPropertyModel(BpmnCancelEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("be9865ef-5251-4364-baca-32b42390de69")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("b9c7d519-305c-4f0c-bfed-856940c65006")
    @Override
    public int getRowsNumber() {
        return BpmnCancelEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("d7414d3c-cd36-49cf-aa09-8032900557ae")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
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
    @objid ("0041921b-7fab-4ddb-92ae-6a8dc3522a8a")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
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
    @objid ("4bd9c8cd-45dd-4e0b-bbae-5dd4fb807973")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            default:
                return;
            }
        default:
            return;
        }
    }

}
