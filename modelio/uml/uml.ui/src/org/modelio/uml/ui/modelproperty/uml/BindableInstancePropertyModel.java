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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BindableInstance</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BindableInstance</i>
 * metaclass.
 */
@objid ("4f949a7f-0107-41ac-94b0-e1762e50324b")
public class BindableInstancePropertyModel extends AbstractPropertyModel<BindableInstance> {
    /**
     * Properties to display for <i>BindableInstance</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("7e0d1448-9f18-4238-a343-e9143454ffcc")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Base",
			"MultiplicityMin", "MultiplicityMax", "Value", "IsConstant", "RepresentedFeature" };

    /**
     * Create a new <i>BindableInstance</i> data model from an
     * <i>BindableInstance</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("26fabbec-e1ee-4bfe-b6e0-bc3440d27a2c")
    public BindableInstancePropertyModel(BindableInstance theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("10133ad8-4d56-41ce-b885-60079c3c8b84")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("6b84d3b3-2fdc-4913-bf48-42332b44e1a3")
    @Override
    public int getRowsNumber() {
        return BindableInstancePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("62e5251b-6dfb-423f-9145-d3b5bb6b6799")
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
                return this.theEditedElement.getBase();
            case 3:
                return this.theEditedElement.getMultiplicityMin();
            case 4:
                return this.theEditedElement.getMultiplicityMax();
            case 5:
                return this.theEditedElement.getValue();
            case 6:
                return this.theEditedElement.isIsConstant() ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return this.theEditedElement.getRepresentedFeature();
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
    @objid ("517f3b11-00ba-48b8-92fe-60042d96ab6f")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(NameSpace.class));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 5:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 6:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 7:
                List<java.lang.Class<? extends MObject>> allowedMetaclasses = new ArrayList<>();
                allowedMetaclasses.add(Attribute.class);
                allowedMetaclasses.add(AssociationEnd.class);
                allowedMetaclasses.add(BindableInstance.class);
                
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        allowedMetaclasses);                        
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
    @objid ("c546b263-9488-4e51-88ea-af9442926cd4")
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
                this.theEditedElement.setBase((NameSpace) value);
                break;
            case 3:
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 4:
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 5:
                this.theEditedElement.setValue((String) value);
                break;
            case 6:
                this.theEditedElement.setIsConstant(((Boolean) value).booleanValue());
                break;
            case 7:
                this.theEditedElement.setRepresentedFeature((UmlModelElement) value);
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
