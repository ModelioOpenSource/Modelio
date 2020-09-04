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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>PackageImport</i> data model.
 * <p>
 * This class provides the list of properties for the <i>PackageImport</i>
 * metaclass.
 */
@objid ("4a56cd99-ad49-42b1-8a0b-3bb5af2ee9cf")
public class PackageImportPropertyModel extends AbstractPropertyModel<PackageImport> {
    /**
     * Properties to display for <i>PackageImport</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("04490dfc-faaa-4c1c-be9d-c2ec9fa06ade")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Alias", "Visibility",
			"ImportedPackage" };

    /**
     * Create a new <i>PackageImport</i> data model from an <i>PackageImport</i>
     * .
     * @param theEditedElement the model to edit.
     */
    @objid ("31479730-cefc-426c-8e21-d42824b048a3")
    public PackageImportPropertyModel(PackageImport theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("188831d8-f2f5-4103-be48-e0438825d1ce")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("93f9464e-6fb5-4a88-a3a8-83652b5ae2ef")
    @Override
    public int getRowsNumber() {
        return PackageImportPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("ef42fb1b-d624-437c-a4c8-b7b94ebff017")
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
                return this.theEditedElement.getImportedPackage();
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
    @objid ("a8bafba7-bf8e-44cd-9af2-549d8491f1c4")
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
                        Collections.singletonList(Package.class));
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("5c28d23a-3d43-482f-aea8-03698e456326")
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
                this.theEditedElement.setImportedPackage((Package) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("9f2ccba3-22e5-4e75-b443-27d2150abf1b")
    protected static class ImportedElementFilter implements IMObjectFilter {
        @objid ("667d19f1-4659-4d13-9c55-fc8fff7ab5e1")
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
