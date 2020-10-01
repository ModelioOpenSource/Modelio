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
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ElementImport</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ElementImport</i>
 * metaclass.
 */
@objid ("9721ea80-e477-4db1-a742-22cd18533af2")
public class ElementImportPropertyModel extends AbstractPropertyModel<ElementImport> {
    /**
     * Properties to display for <i>ElementImport</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("0bc5c869-0846-41cb-9e12-f9d55c2633c5")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Alias", "Visibility",
			"ImportedElement" };

    /**
     * Create a new <i>ElementImport</i> data model from an <i>ElementImport</i>
     * .
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("0cf0db65-d75d-466c-92b6-9cf7b6eb7e4f")
    public ElementImportPropertyModel(ElementImport theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("1bde19d0-448f-47c9-9ee2-1e152489671f")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("904a140d-aeaf-4cf8-8e65-79272a815f5f")
    @Override
    public int getRowsNumber() {
        return ElementImportPropertyModel.PROPERTIES.length;
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
    @objid ("2c2c7a27-b5f7-4a5c-842e-d3c462ea6713")
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
                return this.theEditedElement.getImportedElement();
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
    @objid ("69f13fde-776c-4979-8ffa-ca0d1272588c")
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
                DefaultElementNatValue importedElementType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(NameSpace.class));
                importedElementType.setElementFilter(new ImportedElementFilter());
                return importedElementType;
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
    @objid ("6facbb37-e8ad-4dc5-9d15-b03558ea0e29")
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
                this.theEditedElement.setImportedElement((NameSpace) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("d702542f-1d14-4581-b28c-57765fa5da3f")
    protected static class ImportedElementFilter implements IMObjectFilter {
        @objid ("29d856e3-b074-4e40-b7ac-4f8daaad7ee3")
        @Override
        public boolean accept(final MObject element) {
            if (element instanceof NameSpace) {
                NameSpace type = (NameSpace) element;
                if (type.getName().equals(PredefinedTypes.UNDEFINED_NAME)) {
                    return false;
                } else {
                    return true;
                }
            }
            // else
            return false;
        }

    }

}
