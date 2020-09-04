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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Binding</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Binding</i> metaclass.
 */
@objid ("673fe908-0241-40a5-95aa-b547d78d8d07")
public class BindingPropertyModel extends AbstractPropertyModel<Binding> {
    /**
     * Properties to display for <i>Binding</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("6c65a61d-4e73-4a27-8fea-8efdba14fbe1")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Role",
			"RepresentedFeature" };

    @objid ("dbc84bb2-e19b-48bf-998c-b48d9f8c195e")
    private static UmlModelElement getRole(Binding el) {
        UmlModelElement ret = el.getConnectorEndRole();
        if (ret != null) {
            return ret;
        }
        
        ret = el.getConnectorRole();
        if (ret != null) {
            return ret;
        }
        
        ret = el.getRole();
        return ret;
    }

    @objid ("a5f54ee3-6f2c-4e3a-a1a6-8611724c04e6")
    private static void setRole(Binding theEditedElement, Object value) {
        // Erase old value or exit if old value is new value
        ConnectorEnd old1 = theEditedElement.getConnectorEndRole();
        if (old1 != null) {
            if (old1.equals(value)) {
                return;
            }
            theEditedElement.setConnectorEndRole(null);
        } else {
            NaryConnector old2 = theEditedElement.getConnectorRole();
            if (old2 != null) {
                if (old2.equals(value)) {
                    return;
                }
                theEditedElement.setConnectorRole(null);
            } else {
                BindableInstance old3 = theEditedElement.getRole();
                if (old3 != null) {
                    if (old3.equals(value)) {
                        return;
                    }
                    theEditedElement.setRole(null);
                }
            }
        }
        
        if (value != null) {
            // Set new value
            if (ConnectorEnd.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setConnectorEndRole((ConnectorEnd) value);
            } else if (NaryConnector.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setConnectorRole((NaryConnector) value);
            } else if (BindableInstance.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRole((BindableInstance) value);
            }
        }
    }

    /**
     * Create a new <i>Binding</i> data model from an <i>Binding</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("7cd13029-2b04-42ab-b919-c5a9577a83a5")
    public BindingPropertyModel(Binding theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("d0107a3e-6106-45a8-852e-e791cd71acd5")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("5a4dd633-a520-4e45-b67f-07ebee91d6f5")
    @Override
    public int getRowsNumber() {
        return BindingPropertyModel.PROPERTIES.length;
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
    @objid ("7e6f9061-9878-4382-b3d4-af487c2d032c")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return getRole(this.theEditedElement);
            case 2:
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
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("19343cd3-b66a-4f95-8389-27d66f733025")
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
                List<java.lang.Class<? extends MObject>> roleTypes = new ArrayList<>();
                roleTypes.add(BindableInstance.class);
                roleTypes.add(ConnectorEnd.class);
                return new DefaultElementNatValue((MObject) getValue(row, col), true, roleTypes);
            case 2:
                List<java.lang.Class<? extends MObject>> representingTypes = new ArrayList<>();
                representingTypes.add(BindableInstance.class);
                representingTypes.add(Attribute.class);
                representingTypes.add(Parameter.class);
                representingTypes.add(AssociationEnd.class);
                representingTypes.add(NaryAssociation.class);
                representingTypes.add(LinkEnd.class);
                return new DefaultElementNatValue((MObject) getValue(row, col), false, representingTypes);
        
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
    @objid ("5686dfdb-de72-484e-b2c3-5235ba6681ae")
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
                setRole(this.theEditedElement, value);
                break;
            case 2:
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
