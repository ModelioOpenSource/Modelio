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
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Port</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Port</i> metaclass.
 */
@objid ("125c3688-f54b-4370-bf7c-5171ee0d3d3f")
public class PortPropertyModel extends AbstractPropertyModel<Port> {
    /**
     * Properties to display for <i>Port</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("6b0e6057-a562-446d-8d33-e3a535a45e1e")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Base",
			"Value", "MultiplicityMin", "MultiplicityMax", "IsBehavior", "IsService",
			"IsConstant", "RepresentedFeature", "IsConjugated", "Direction" };

    /**
     * Create a new <i>Port</i> data model from an <i>Port</i>.
     * 
     * @param theEditedElement the port to build a model for
     */
    @objid ("79aa73f6-f30b-44e7-9db4-b99548f48ae3")
    public PortPropertyModel(Port theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("5cfac5f1-2d2d-4217-9593-513063feb161")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("e6ee3968-0ff3-4eb0-8878-4220e963eb84")
    @Override
    public int getRowsNumber() {
        return PortPropertyModel.PROPERTIES.length;
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
    @objid ("c5c0e23a-4d33-40fe-a5af-eb1503370e35")
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
            case 4:
                return this.theEditedElement.getMultiplicityMin();
            case 5:
                return this.theEditedElement.getMultiplicityMax();
            case 6:
                return this.theEditedElement.isIsBehavior() ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return this.theEditedElement.isIsService() ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return this.theEditedElement.isIsConstant() ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return this.theEditedElement.getRepresentedFeature();
            case 10:
                return this.theEditedElement.isIsConjugated() ? Boolean.TRUE : Boolean.FALSE;
            case 11:
                return this.theEditedElement.getDirection();
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
    @objid ("694f8c44-645a-411d-aea6-947487267417")
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
                        Collections.singletonList(NameSpace.class));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                List<String> cardinalityValues = new ArrayList<>();
                cardinalityValues.add("0");
                cardinalityValues.add("1");
                cardinalityValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityValues, true);
            case 5:
                cardinalityValues = new ArrayList<>();
                cardinalityValues.add("0");
                cardinalityValues.add("1");
                cardinalityValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityValues, true);
            case 6:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 7:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 8:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 9:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(UmlModelElement.class));
            case 10:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 11:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), PortOrientation.class);
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
    @objid ("b83d2c0f-48d5-4920-8bb7-e4e2cbc5bf33")
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
                this.theEditedElement.setValue((String) value);
                break;
            case 4:
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 5:
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 6:
                this.theEditedElement.setIsBehavior(((Boolean) value).booleanValue());
                break;
            case 7:
                this.theEditedElement.setIsService(((Boolean) value).booleanValue());
                break;
            case 8:
                this.theEditedElement.setIsConstant(((Boolean) value).booleanValue());
                break;
            case 9:
                this.theEditedElement.setRepresentedFeature((UmlModelElement) value);
                break;
            case 10:
                this.theEditedElement.setIsConjugated(((Boolean) value).booleanValue());
                break;
            case 11:
                this.theEditedElement.setDirection((PortOrientation) value);
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
