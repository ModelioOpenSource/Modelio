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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Usage</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Usage</i> metaclass.
 */
@objid ("c6577df7-9bd6-4749-9283-126999428d5c")
public class UsagePropertyModel extends AbstractPropertyModel<Usage> {
    /**
     * Properties to display for <i>Usage</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("3221b7cd-3091-4400-b0ca-582e1a46dea2")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "DependsOn" };

    /**
     * Create a new <i>Usage</i> data model from an <i>Usage</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("512a2cd2-5839-4b73-a74b-bee8bdc7934d")
    public UsagePropertyModel(Usage theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("b534ca9f-4c12-49ed-b961-4b7e04033865")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("32391047-5c4f-4e87-817a-e09affd4f125")
    @Override
    public int getRowsNumber() {
        return UsagePropertyModel.PROPERTIES.length;
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
    @objid ("27ede536-9ce9-4242-864e-e9888d06f470")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getDependsOn();
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
    @objid ("33537c0b-b5d8-4a08-9ce2-f990cf0fa876")
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
                DefaultElementNatValue dependsOnType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(ModelElement.class));
                dependsOnType.setElementFilter(new DependsOnFilter());
                return dependsOnType;
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
    @objid ("14c4ac95-397c-4029-a12e-be73f3d9c301")
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
                this.theEditedElement.setDependsOn((ModelElement) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("24943b8a-4986-4c04-9338-cb43f59c161d")
    protected static class DependsOnFilter implements IMObjectFilter {
        @objid ("8a5a1a85-4882-483b-8809-e07f5dcdcd55")
        @Override
        public boolean accept(final MObject element) {
            if (element instanceof ModelElement) {
                ModelElement type = (ModelElement) element;
            
                if (type instanceof Project) {
                    return false;
                } else if (type.getName().equals(PredefinedTypes.UNDEFINED_NAME)) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        }

    }

}
