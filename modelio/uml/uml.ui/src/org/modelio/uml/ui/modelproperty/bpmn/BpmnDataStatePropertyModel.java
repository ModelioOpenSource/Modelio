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
package org.modelio.uml.ui.modelproperty.bpmn;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnDataState</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnDataState</i> metaclass.
 */
@objid ("b4726e39-09e1-43d6-9c8a-fa427f8395d0")
public class BpmnDataStatePropertyModel extends AbstractPropertyModel<BpmnDataState> {
    /**
     * Properties to display for <i>BpmnDataState</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("bc4eeb78-58a0-46c1-9d44-c8ab4244114c")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "State" };

    @objid ("39ff4de6-2220-4a4d-94c6-bde0f342ee17")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnDataState</i> data model from a <i>BpmnDataState</i> .
     * @param theEditedElement the model to edit.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("c890c40a-aca4-4125-9f2a-dcb0876b4117")
    public  BpmnDataStatePropertyModel(BpmnDataState theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("123fa214-bee1-413d-88e0-845b0542a159")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("5634c352-d004-4778-820c-87d28a6a747e")
    @Override
    public int getRowsNumber() {
        return BpmnDataStatePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("0c35e060-a825-48a4-bb41-fce19c4dea0e")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnDataStatePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return State.getTarget(this.theEditedElement);
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
     * This type will be used to choose an editor and a renderer for each cell of the properties table.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("bf4ff0ed-d3bf-49a0-a420-7d3da7dc2377")
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
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(State.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnDataStatePropertyModel.this.mdaExpert.canLink(State.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnDataStatePropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
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
    @objid ("fdb1a355-02b5-45cc-8427-fd143001d107")
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
                State.setTarget(this.theEditedElement, (ModelElement) value);
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
