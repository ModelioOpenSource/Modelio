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
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Dependency</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Dependency</i>
 * metaclass.
 */
@objid ("5c91e7d1-81a1-40eb-9c4e-bb984d6e7789")
public class DependencyPropertyModel extends AbstractPropertyModel<Dependency> {
    /**
     * Properties to display for <i>Dependency</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("2f40de19-ff03-4bf5-bcd7-6de4eb648949")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "DependsOn" };

    /**
     * Create a new <i>Dependency</i> data model from an <i>Dependency</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("6df3e243-ed32-4b98-8897-db05c30e4641")
    public DependencyPropertyModel(Dependency theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("6f3acfa6-5f65-4880-91ab-6656de500ed4")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("acf4ed91-42b5-4d15-9b78-19cd75c35e68")
    @Override
    public int getRowsNumber() {
        return DependencyPropertyModel.PROPERTIES.length;
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
    @objid ("bbe967a8-906a-443d-b8ca-2d204e929447")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(DependencyPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
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
    @objid ("853de6d8-6c1f-4d87-be78-a1f0f8bae9e4")
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
                DefaultElementNatValue dependsOnType = new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(ModelElement.class));
                dependsOnType.setElementFilter(new DependsOnTypeFilter());
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
    @objid ("e6c2b91f-f567-40da-8fdc-526100fb7f40")
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

    @objid ("fcbcccc9-4f72-4900-a6b1-1c4049cf67c7")
    protected static class DependsOnTypeFilter implements IMObjectFilter {
        @objid ("b0fb5b39-bfe4-4f0b-8d70-1c00ca4df79c")
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
            // else
            return false;
        }

    }

}
