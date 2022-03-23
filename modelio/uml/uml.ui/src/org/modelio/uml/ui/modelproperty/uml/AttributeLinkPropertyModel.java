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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.choice.DefaultElementChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>AttributeLink</i> data model.
 * <p>
 * This class provides the list of properties for the <i>AttributeLink</i>
 * metaclass.
 */
@objid ("6b1e5814-65ea-4634-85c8-48e5ab5e5478")
public class AttributeLinkPropertyModel extends AbstractPropertyModel<AttributeLink> {
    /**
     * Properties to display for <i>AttributeLink</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("60cbd498-fa15-4192-9630-c8902b4e0740")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Base", "Value" };

    /**
     * Create a new <i>AttributeLink</i> data model from an <i>AttributeLink</i>
     * .
     * @param theEditedElement the model to edit.
     */
    @objid ("3dc46852-d623-43e3-9ede-bdd7868e4980")
    public  AttributeLinkPropertyModel(AttributeLink theEditedElement) {
        super(theEditedElement);
    }

    @objid ("2cc037a1-7387-44f8-bea3-cf4b381bf1a6")
    private INatValue getAttributeLinkBaseType(Attribute attribute) {
        // FIXME
        Instance parentInstance = this.theEditedElement.getAttributed();
        if (parentInstance == null) {
            return new DefaultElementNatValue(parentInstance, true, Collections.singletonList(Attribute.class));
        }
        
        NameSpace parentBase = parentInstance.getBase();
        if (!(parentBase instanceof Classifier)) {
            return new DefaultElementNatValue(parentBase, true, Collections.singletonList(Attribute.class));
        }
        
        Classifier parentClassifierBase = (Classifier) parentBase;
        
        List<MObject> availableAttributes = getAvailableAttributes(parentClassifierBase);
        return new DefaultElementChoiceNatValue(attribute, true, Collections.singletonList(Attribute.class),
                                                availableAttributes);
    }

    @objid ("88d8fd26-b4f5-4011-8c50-ad5ff52b5c27")
    private List<MObject> getAvailableAttributes(Classifier classifier) {
        List<MObject> attributesList = new ArrayList<>();
        
        if (classifier != null) {
            EList<Attribute> classifierOperations = classifier.getOwnedAttribute();
        
            for (Feature feature : classifierOperations) {
                attributesList.add(feature);
            }
        
            EList<Generalization> generalizations = classifier.getParent();
        
            for (Generalization generalization : generalizations) {
                NameSpace parentNameSpace = generalization.getSuperType();
                if (parentNameSpace instanceof Classifier) {
                    attributesList.addAll(getAvailableAttributes((Classifier) parentNameSpace));
                }
            }
        }
        return attributesList;
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("1b5610a6-19dd-4a1f-9c84-99f6151ad9cb")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("452ad150-b621-463a-b20b-88edb7dc9b99")
    @Override
    public int getRowsNumber() {
        return AttributeLinkPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("b61fba08-599d-4345-a5e0-ed10a82ca09a")
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
                return this.theEditedElement.getValue();
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
    @objid ("90b48c75-6354-4586-80e5-7b14705b7a7a")
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
                return getAttributeLinkBaseType((Attribute) getValue(row, col));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
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
    @objid ("b6c0c19b-4d8d-46ea-80fd-521bb1d831cc")
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
                this.theEditedElement.setBase((Attribute) value);
                break;
            case 3:
                this.theEditedElement.setValue((String) value);
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
