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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ProvidedInterface</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ProvidedInterface</i>
 * metaclass.
 */
@objid ("307df1a4-0ec2-43c3-b866-cdf95720a774")
public class ProvidedInterfacePropertyModel extends AbstractPropertyModel<ProvidedInterface> {
    /**
     * Properties to display for <i>ProvidedInterface</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("4dc37429-80dc-4943-a90b-aa897f4b49f4")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "ProvidedElement" };

    /**
     * Create a new <i>ProvidedInterface</i> data model from an
     * <i>ProvidedInterface</i>.
     * 
     * @param theEditedElement the edited element.
     */
    @objid ("e12937b2-4b63-4c01-822f-4c20dafd3eae")
    public ProvidedInterfacePropertyModel(ProvidedInterface theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("d0df2fbc-3850-4564-95d1-a1b3c2ecfca7")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("370e533d-f370-411f-85f4-9c453776a103")
    @Override
    public int getRowsNumber() {
        return ProvidedInterfacePropertyModel.PROPERTIES.length;
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
    @objid ("4ed9aa0a-a101-47f8-98b8-3408fe208cb1")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getProvidedElement();
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
    @objid ("35b731ac-acc0-4058-a5a5-5f0bf904662d")
    @SuppressWarnings("unchecked")
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
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(Interface.class));
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
    @objid ("f55c3fa0-c01c-47a2-99a4-4652fbc91eaa")
    @Override
    @SuppressWarnings("unchecked")
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                EList<Interface> currentContent = this.theEditedElement.getProvidedElement();
                List<Interface> newcontent = (List<Interface>) value;
                if (!newcontent.equals(currentContent)) {
                    currentContent.clear();
                    currentContent.addAll(newcontent);
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
