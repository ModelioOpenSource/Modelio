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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * <i>PropertyDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the <i>PropertyDefinition</i>
 * metaclass.
 */
@objid ("96ccc9fe-c895-4c07-84e2-9629cd0fe01a")
public class PropertyDefinitionPropertyModel extends AbstractPropertyModel<PropertyDefinition> {
    /**
     * Properties to display for <i>PropertyDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("dcf60120-e41c-452b-a58d-c4f0929834db")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
	        "DefaultValue", "IsEditable", "Metaclass", "Stereotype" };

    /**
     * Create a new <i>PropertyDefinition</i> data model from an
     * <i>PropertyDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("624bb0e9-1c6c-4b63-8059-0f427f8a0d0c")
    public PropertyDefinitionPropertyModel(PropertyDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("20b4ab93-57e5-4f72-b91c-625233ae26b3")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("428db300-f67f-41ba-b64f-7af0465ca9f9")
    @Override
    public int getRowsNumber() {
        PropertyType type = this.theEditedElement.getType();
        if (type != null && (type.getBaseType() == PropertyBaseType.ELEMENT || type.getBaseType() == PropertyBaseType.MULTIELEMENT)) {
            return PropertyDefinitionPropertyModel.PROPERTIES.length;
        } else {
            // Ignore 'metaclass' and 'stereotype' fields for non-element properties
            return PropertyDefinitionPropertyModel.PROPERTIES.length - 2;
        }
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
    @objid ("7b169b5f-e87a-45fe-ae6c-fbfc70498220")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PropertyDefinitionPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getType();
            case 3:
                return this.theEditedElement.getDefaultValue();
            case 4:
                return this.theEditedElement.isIsEditable() ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return this.theEditedElement.getProperty("Constraints", "metaclass");
            case 6:
                String stereotypeRef = this.theEditedElement.getProperty("Constraints", "stereotype");
                if (stereotypeRef==null || stereotypeRef.isEmpty()) {
                    return null;
                }
                try {
                    CoreSession session = CoreSession.getSession(this.theEditedElement);
                    return session.getModel().findByRef(new MRef(stereotypeRef));
                } catch (final IllegalArgumentException e) {
                    ModelProperty.LOG.warning("Invalid 'stereotype=%s' property in 'Constraints' table of %s : %s", stereotypeRef, this.theEditedElement, e.toString());
                    ModelProperty.LOG.debug(e);
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
    @objid ("c5eb803d-f59d-4df1-a51b-543c76861897")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(PropertyType.class));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                List<String> metaclasses = new ArrayList<>();
        
                // Get all metaclasses inheriting ModelElement
                for (MClass metaclass : this.theEditedElement.getMClass().getMetamodel().getMClass(ModelElement.class)
                        .getSub(true)) {
                    metaclasses.add(metaclass.getName());
                }
        
                Collections.sort(metaclasses);
        
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, metaclasses, false);
            case 6:
                DefaultElementNatValue stereotypeValue = new DefaultElementNatValue((Element) getValue(row, col), false, Collections.singletonList(Stereotype.class));
                // stereotypeValue.setElementFilter(new IMObjectFilter() {
                // /**
                // * Accept dependency stereotypes belonging to the Analyst profile, or the 'trace' stereotype.
                // */
                // @Override
                // public boolean accept(MObject element) {
                // Stereotype stereotype = (Stereotype) element;
                // boolean isInAnalystProfile = stereotype.getOwner() != null ? stereotype.getOwner().getName().equals("Analyst") : false;
                // boolean isTrace = stereotype.getName().equals("trace");
                // boolean isDependency = stereotype.getBaseClassName().equals(Dependency.MNAME);
                // return isDependency && (isInAnalystProfile || isTrace);
                // }
                // });
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
    @objid ("31bca1b3-cceb-44a3-a9cb-7a3c2f4d535b")
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
                this.theEditedElement.setType((PropertyType) value);
                break;
            case 3:
                this.theEditedElement.setDefaultValue((String) value);
                break;
            case 4:
                this.theEditedElement.setIsEditable(((Boolean) value).booleanValue());
                break;
            case 5:
                this.theEditedElement.setProperty("Constraints", "metaclass", ((String) value));
                break;
            case 6:
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
