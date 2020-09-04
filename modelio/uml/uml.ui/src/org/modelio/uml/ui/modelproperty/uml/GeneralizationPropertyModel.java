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
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Generalization</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Generalization</i>
 * metaclass.
 */
@objid ("2d37622c-5ea5-4b9e-84d8-a43c9b9b7214")
public class GeneralizationPropertyModel extends AbstractPropertyModel<Generalization> {
    /**
     * Properties to display for <i>Generalization</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("d0521e71-8e69-4066-b051-9846881ff552")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "SuperType",
			"Discriminator" };

    /**
     * Create a new <i>Generalization</i> data model from an
     * <i>Generalization</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("7e577159-a9d2-4ea9-92c1-67afeaa28367")
    public GeneralizationPropertyModel(Generalization theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("938b77c6-e550-4f82-9f1f-f5872388b836")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("045acfcc-b117-4823-860a-970db0e15228")
    @Override
    public int getRowsNumber() {
        return GeneralizationPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("00e6cb57-d6ae-40c4-9923-23607f6baf35")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getSuperType();
            case 2:
                return this.theEditedElement.getDiscriminator();
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
    @objid ("998a2b3f-2b82-4137-a7e7-52d0e0d33380")
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
                NameSpace subType = this.theEditedElement.getSubType();
                if (subType == null) {
                    return null;
                } else if (subType.getClass() == Signal.class) {
                    List<java.lang.Class<? extends MObject>> allowedClasses = new ArrayList<>();
                    allowedClasses.add(Signal.class);
                    allowedClasses.add(Class.class);
                    return new DefaultElementNatValue((MObject) getValue(row, col), false, allowedClasses);
                } else {
                    return new DefaultElementNatValue((MObject) getValue(row, col), false, Collections
                            .singletonList(this.theEditedElement.getSubType().getMClass().getJavaInterface()));
                }
            case 2:
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
    @objid ("a9dbfb78-110c-4481-b7de-e734f0dd2ee4")
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
                this.theEditedElement.setSuperType((NameSpace) value);
                break;
            case 2:
                this.theEditedElement.setDiscriminator((String) value);
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
