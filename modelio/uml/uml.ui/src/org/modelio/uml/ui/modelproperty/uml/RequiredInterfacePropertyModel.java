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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>RequiredInterface</i> data model.
 * <p>
 * This class provides the list of properties for the <i>RequiredInterface</i>
 * metaclass.
 */
@objid ("4a82ebeb-2940-4e78-bb98-158e7a224463")
public class RequiredInterfacePropertyModel extends AbstractPropertyModel<RequiredInterface> {
    /**
     * Properties to display for <i>RequiredInterface</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("543fdf5f-31f3-4c9e-8c50-885d936edf84")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "RequiredElement" };

    /**
     * Create a new <i>RequiredInterface</i> data model from an
     * <i>RequiredInterface</i>.
     * @param theEditedElement the edited element.
     */
    @objid ("03a522b0-7025-409c-b325-85c48c0eaa52")
    public  RequiredInterfacePropertyModel(RequiredInterface theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("c0d0adf0-3735-4e37-9a5c-be048473dc1d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("b5cca619-7fb5-426d-8ab3-ead6c6663491")
    @Override
    public int getRowsNumber() {
        return RequiredInterfacePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("07b23c94-d02c-45a8-9e66-e3d327a308a2")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getRequiredElement();
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
    @objid ("30e29920-ca0a-4495-8bfa-8e0edd96a19c")
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("d09c4405-df25-4b41-91de-f69470fcee82")
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
                EList<Interface> currentContent = this.theEditedElement.getRequiredElement();
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
