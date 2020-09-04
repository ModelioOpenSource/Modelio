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

package org.modelio.model.property.panel.data.standard.infrastructure;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * <i>DynamicPropertyDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the <i>DynamicPropertyDefinition</i>
 * metaclass.
 */
@objid ("7177a85c-f474-48f4-9ece-63bb05b5212d")
public class DynamicPropertyDefinitionPropertyModel extends AbstractPropertyModel<DynamicPropertyDefinition> {
    /**
     * Properties to display for <i>DynamicPropertyDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("e64134a7-5821-40de-a70b-9e418cfb5d09")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "IsEditable", "AllowedStereotype" };

    /**
     * Create a new <i>DynamicPropertyDefinition</i> data model from an
     * <i>DynamicPropertyDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("22bcf49f-e52a-46f9-b0cb-160e54031953")
    public DynamicPropertyDefinitionPropertyModel(DynamicPropertyDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("460ec6a1-b64e-475c-836c-b86fcbda23b4")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("568b05cd-5d63-440d-8c4d-f7880bfafd80")
    @Override
    public int getRowsNumber() {
        return DynamicPropertyDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("b2e36a5f-3e3b-4d61-8c14-97951335c054")
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
                return this.theEditedElement.isIsEditable() ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                String stereotypeRef = this.theEditedElement.getProperty("Constraints", "stereotype");
                try {
                    CoreSession session = CoreSession.getSession(this.theEditedElement);
                    return session.getModel().findByRef(new MRef(stereotypeRef));
                } catch (@SuppressWarnings("unused") final IllegalArgumentException|NullPointerException e) {
                    return null;
                }
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
    @objid ("ce40087d-3f73-455b-bfa0-a249b33d0508")
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
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 3:
                DefaultElementNatValue stereotypeValue = new DefaultElementNatValue((Element) getValue(row, col), false, Collections.singletonList(Stereotype.class));
                stereotypeValue.setElementFilter(new IMObjectFilter() {
                    /**
                     * Accept dependency stereotypes belonging to the Analyst profile, or the 'trace' stereotype.
                     */
                    @Override
                    public boolean accept(MObject element) {
                        Stereotype stereotype = (Stereotype) element;
                        boolean isInAnalystProfile = stereotype.getOwner() != null ? stereotype.getOwner().getName().equals("Analyst") : false;
                        boolean isTrace = stereotype.getName().equals("trace");
                        String baseClassName = stereotype.getBaseClassName();
                        boolean isDependency = baseClassName.equals(Dependency.MNAME) || baseClassName.equals(Dependency.MQNAME);
                        return isDependency && (isInAnalystProfile || isTrace);
                    }
                });
                return stereotypeValue;
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
    @objid ("5027c019-2e4b-4e5a-8c91-91b1f1bfebda")
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
                this.theEditedElement.setIsEditable(((Boolean) value).booleanValue());
                break;
            case 3:
                this.theEditedElement.setProperty("Constraints", "stereotype", ((Element) value).toString());
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
