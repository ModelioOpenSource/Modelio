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
package org.modelio.model.property.panel.data.standard.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Generic data model designed for Dynamic metaclasses.
 */
@objid ("f40f6d6f-acc2-4653-b2c4-f1550a0396db")
@SuppressWarnings("unchecked")
public class DynamicPropertyModel extends AbstractPropertyModel<MObject> {
    @objid ("5889283c-ba41-4674-ab8b-780f86e607eb")
    private List<MAttribute> attributes;

    @objid ("7f3cb5df-8f27-466e-bb39-f39d4a2246a5")
    private List<MDependency> dependencies;

    /**
     * Create a new data model from any MObject.
     * @param theEditedElement The <i>Element</i> that corresponds to this data model.
     */
    @objid ("5124f58b-9c5d-4fd0-8b25-b6f2e6fc533f")
    public  DynamicPropertyModel(MObject theEditedElement) {
        super(theEditedElement);
        
        this.attributes = new ArrayList<>();
        for (MAttribute mAtt : theEditedElement.getMClass().getAttributes(true)) {
            // Filter "status" attribute only
            if (!mAtt.getName().equals("status")) {
                this.attributes.add(0, mAtt);
            }
        }
        
        this.dependencies = new ArrayList<>();
        for (MDependency mDep : theEditedElement.getMClass().getDependencies(true)) {
            if (((SmDependency) mDep).isPartOf() && !mDep.isComposition() && !mDep.isSharedComposition()) {
                this.dependencies.add(0, mDep);
            }
        }
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("8b4cda94-318b-40f5-9a72-810af1d2bf11")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("00d48f2c-6fee-4ec0-b279-7608d10deacd")
    private INatValue getPropertyType(MAttribute mAtt) {
        Class<?> type = mAtt.getType();
        
        // Get the correct PropertyDefinition:
        if (type.isEnum()) {
            return new DefaultJavaEnumNatValue((Enum<?>) this.theEditedElement.mGet(mAtt), (Class<? extends Enum<?>>) type);
        } else if (type == Boolean.class) {
            return new DefaultBooleanNatValue((Boolean) this.theEditedElement.mGet(mAtt));
        } else if (type == Integer.class) {
            return new DefaultIntegerNatValue((Integer) this.theEditedElement.mGet(mAtt));
        } else {
            // Unknown property type, treat as a text
            return new DefaultStringNatValue((String) this.theEditedElement.mGet(mAtt), true);
        }
        
    }

    @objid ("25584cd4-2faa-446b-ab76-9aaf818fca7e")
    private INatValue getPropertyType(MDependency mDep) {
        MClass type = mDep.getTarget();
        
        // Get the correct PropertyDefinition:
        List<MObject> value = this.theEditedElement.mGet(mDep);
        if (mDep.getMaxCardinality() == 1) {
            return new DefaultElementNatValue(value.isEmpty() ? null : value.get(0), mDep.getMinCardinality() == 0,
                    Collections.singletonList(type.getJavaInterface()));
        } else {
            return new DefaultMultiElementNatValue(value, mDep.getMinCardinality() == 0,
                    Collections.singletonList((Class<? extends Element>) type.getJavaInterface()));
        }
        
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("c4aa59d5-87ca-49a1-9eba-87b9d1137804")
    @Override
    public int getRowsNumber() {
        return 1 + this.dependencies.size() + this.attributes.size();
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("4e6e3d60-a320-4ad1-a0b5-aa135a26b0d9")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            switch (row) {
            case 0: // Header
                return this.theEditedElement.getMClass().getName();
            default:
                if (row <= this.dependencies.size()) {
                    final MDependency mDep = this.dependencies.get(row - 1);
                    return mDep.getName();
                } else {
                    final MAttribute mAtt = this.attributes.get(row - this.dependencies.size() - 1);
                    return mAtt.getName();
                }
            }
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            default:
                if (row <= this.dependencies.size()) {
                    final MDependency mDep = this.dependencies.get(row - 1);
                    final List<MObject> values = this.theEditedElement.mGet(mDep);
                    if (mDep.getMaxCardinality() == 1) {
                        return values.isEmpty() ? null : values.get(0);
                    } else {
                        return values;
                    }
                } else {
                    final MAttribute mAtt = this.attributes.get(row - this.dependencies.size() - 1);
                    return this.theEditedElement.mGet(mAtt);
                }
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
    @objid ("73b63e73-60c6-4758-851c-ef8685f13e26")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                if (row <= this.dependencies.size()) {
                    final MDependency mDep = this.dependencies.get(row - 1);
                    return getPropertyType(mDep);
                } else {
                    final MAttribute mAtt = this.attributes.get(row - this.dependencies.size() - 1);
                    return getPropertyType(mAtt);
                }
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
    @objid ("dfc353f0-673c-4445-9626-9ecefdcfafc4")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            default:
                if (row <= this.dependencies.size()) {
                    final MDependency mDep = this.dependencies.get(row - 1);
                    final List<MObject> values = this.theEditedElement.mGet(mDep);
                    if (mDep.getMaxCardinality() == 1) {
                        values.clear();
                        if (value != null) {
                            values.add((MObject) value);
                        }
                    } else {
                        values.clear();
                        if (value != null) {
                            values.addAll((List<? extends MObject>) value);
                        }
                    }
                } else {
                    final MAttribute mAtt = this.attributes.get(row - this.dependencies.size() - 1);
                    if (mAtt.getType() == Integer.class) {
                        // There is no 'integer' type, cast the String instead
                        this.theEditedElement.mSet(mAtt, value);
                    } else {
                        this.theEditedElement.mSet(mAtt, value);
                    }
                }
            }
            break;
        default:
            return;
        }
        
    }

}
