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
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Manifestation</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Manifestation</i>
 * metaclass.
 */
@objid ("9eab660c-ce64-4fee-8bc2-bf71f19dd3c9")
public class ManifestationPropertyModel extends AbstractPropertyModel<Manifestation> {
    /**
     * Properties to display for <i>Manifestation</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("ac0efa4b-bb03-42f6-a090-1aaf07eac57f")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "UtilizedElement" };

    /**
     * Create a new <i>Manifestation</i> data model from an <i>Manifestation</i>
     * .
     * @param theEditedElement the model to edit.
     */
    @objid ("6eac8d55-1238-4061-9a21-0a398687d560")
    public ManifestationPropertyModel(Manifestation theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("320c1164-b929-4a78-b481-dd2568a467dd")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("5d22475f-9516-4dab-9f86-e8d029fa37e9")
    @Override
    public int getRowsNumber() {
        return ManifestationPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("9c0881b6-b120-4cbb-9d40-08634dc8067a")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getUtilizedElement();
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
    @objid ("83024092-beab-40b7-80e6-ddaa097400e0")
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
                DefaultElementNatValue manifestedElementType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(UmlModelElement.class));
                manifestedElementType.setElementFilter(new ManifestedElementFilter());
                return manifestedElementType;
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
    @objid ("1d335c3f-72ba-4230-9dd9-c89e8e1c6613")
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
                this.theEditedElement.setUtilizedElement((UmlModelElement) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("582b9c68-1190-4f47-8d43-1ab53a9fee5a")
    protected static class ManifestedElementFilter implements IMObjectFilter {
        @objid ("f09d58f9-0907-40f8-bd93-b0d852c2cf0d")
        @Override
        public boolean accept(final MObject element) {
            if (element instanceof UmlModelElement) {
                UmlModelElement type = (UmlModelElement) element;
            
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
