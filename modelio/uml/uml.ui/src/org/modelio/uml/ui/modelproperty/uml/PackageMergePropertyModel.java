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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>PackageMerge</i> data model.
 * <p>
 * This class provides the list of properties for the <i>PackageMerge</i>
 * metaclass.
 */
@objid ("a3a99184-71a9-4156-b7a2-3df020e15483")
public class PackageMergePropertyModel extends AbstractPropertyModel<PackageMerge> {
    /**
     * Properties to display for <i>PackageMerge</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("efafac33-23c8-4942-8a9a-3d8b40b2f77a")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "MergedPackage" };

    /**
     * Create a new <i>PackageMerge</i> data model from an <i>PackageMerge</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("92f2675c-1821-46b9-ada1-b17d2e092410")
    public  PackageMergePropertyModel(PackageMerge theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("25a136c8-ac48-4fbd-b5f9-570208f1c5d8")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("9a811676-ba2d-44c4-a673-f7cfd619d1c8")
    @Override
    public int getRowsNumber() {
        return PackageMergePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("c02abbce-a469-42e7-9813-67256af6adea")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getMergedPackage();
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
    @objid ("1e97c369-b092-4cef-be7c-a20d70312d00")
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
                DefaultElementNatValue mergedPackageType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Package.class));
                mergedPackageType.setElementFilter(new MergedPackageFilter());
                return mergedPackageType;
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
    @objid ("387940dc-7de7-4788-8527-6e704c721d3a")
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
                this.theEditedElement.setMergedPackage((Package) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
        
    }

    @objid ("cbba7a18-9ac9-4c86-a1cd-48ae82ba5641")
    protected static class MergedPackageFilter implements IMObjectFilter {
        @objid ("e586196f-5001-40c1-8c83-14de60218d34")
        @Override
        public boolean accept(final MObject element) {
            if (element instanceof Package) {
                Package type = (Package) element;
                if (type.getName().equals(PredefinedTypes.UNDEFINED_NAME)) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        }

    }

}
