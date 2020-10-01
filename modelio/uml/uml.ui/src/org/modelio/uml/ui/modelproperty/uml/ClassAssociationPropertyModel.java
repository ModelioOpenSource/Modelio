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
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ClassAssociation</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ClassAssociation</i>
 * metaclass.
 */
@objid ("0c69eb20-161e-4917-9216-91ba8c1a6e0b")
public class ClassAssociationPropertyModel extends AbstractPropertyModel<ClassAssociation> {
    /**
     * Properties to display for <i>ClassAssociation</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("daec0b96-32b4-4dbb-8bfe-d3c4650e63e9")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "ClassPart" };

    /**
     * Create a new <i>ClassAssociation</i> data model from an
     * <i>ClassAssociation</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("e3ab25d8-cf86-4a17-b6de-b82ed56d2668")
    public ClassAssociationPropertyModel(ClassAssociation theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("4eb13629-4400-4dee-81f4-56fc918b5415")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("9d8a245a-63a1-4146-a8d1-c37cc3bbcf23")
    @Override
    public int getRowsNumber() {
        return ClassAssociationPropertyModel.PROPERTIES.length;
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
    @objid ("ef9ad914-3378-4acf-bde1-06e46d1bc2ba")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1: // Class part
                return this.theEditedElement.getClassPart();
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
    @objid ("d8aa5fa7-37e7-4b23-a242-4398b1d387ef")
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
                DefaultElementNatValue classType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Class.class));
                classType.setElementFilter(new AssociationClassTypeFilter());
                return classType;
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
    @objid ("29f376f4-bd18-479a-a964-61a217b5d24d")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1: // Class part
                this.theEditedElement.setClassPart((Class) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("5b521e05-826f-4b55-8de0-be735464fdcc")
    protected class AssociationClassTypeFilter implements IMObjectFilter {
        @objid ("0fc2dbc7-2553-4b54-9084-1505e5e11d56")
        @Override
        public boolean accept(final MObject element) {
            Class type = (Class) element;
            
            for (AssociationEnd end : getEditedElement().getAssociationPart().getEnd()) {
                if (type.equals(end.getSource())) {
                    return false;
                }
            }
            return true;
        }

    }

}
