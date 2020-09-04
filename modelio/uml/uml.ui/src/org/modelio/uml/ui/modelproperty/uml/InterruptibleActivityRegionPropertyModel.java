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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>InterruptibleActivityRegion</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>InterruptibleActivityRegion</i> metaclass.
 */
@objid ("c82004c1-f7a6-4d57-a152-afd171b2ac37")
public class InterruptibleActivityRegionPropertyModel extends AbstractPropertyModel<InterruptibleActivityRegion> {
    /**
     * Properties to display for <i>InterruptibleActivityRegion</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("e4c69809-cc95-4337-b02e-3268f990247b")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
			"InterruptingEdge" };

    /**
     * Create a new <i>InterruptibleActivityRegion</i> data model from an
     * <i>InterruptibleActivityRegion</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("460f67fa-b15b-460c-9c0c-3ccd88f28fd8")
    public InterruptibleActivityRegionPropertyModel(InterruptibleActivityRegion theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     */
    @objid ("d94d5714-ea6c-4cec-b412-d9780a8adfcf")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     */
    @objid ("6b9204a5-a609-42fb-889b-818c408df86d")
    @Override
    public int getRowsNumber() {
        return InterruptibleActivityRegionPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     */
    @objid ("574bb577-a0d4-40cd-b7ab-cae1a925e7b8")
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
                return this.theEditedElement.getInterruptingEdge();
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
     */
    @objid ("b14313c7-ad27-4f31-bef0-d3d9eefc83cc")
    @SuppressWarnings("unchecked")
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
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(ActivityEdge.class));
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
     */
    @objid ("1750e036-0d65-410e-95ae-9d2a4f97fc6a")
    @Override
    @SuppressWarnings("unchecked")
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
        
                for (ActivityEdge e : new ArrayList<>(this.theEditedElement.getInterruptingEdge())) {
                    this.theEditedElement.getInterruptingEdge().remove(e);
                }
        
                List<ActivityEdge> l = (List<ActivityEdge>) value;
                for (ActivityEdge e : l) {
                    this.theEditedElement.getInterruptingEdge().add(e);
                }
        
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
