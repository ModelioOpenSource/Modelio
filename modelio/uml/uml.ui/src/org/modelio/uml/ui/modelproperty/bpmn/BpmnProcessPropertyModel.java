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

package org.modelio.uml.ui.modelproperty.bpmn;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcessType;
import org.modelio.metamodel.bpmn.processCollaboration.OptionalBoolean;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnProcess</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnProcess</i> metaclass.
 */
@objid ("8583b3b7-34bb-4d6a-8a66-35d8d446f29e")
public class BpmnProcessPropertyModel extends AbstractPropertyModel<BpmnProcess> {
    /**
     * Properties to display for <i>BpmnProcess</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("8283f0b4-86b6-49a7-bdae-af9c01236c77")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "ProcessType",
            "Closed", "Executable", "Process" };

    @objid ("e2ec921f-e56a-4442-88f4-b5e9256e28d1")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnProcess</i> data model from an <i>BpmnProcess</i>.
     * @param theEditedElement the model to edit.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("0a4a40e5-e6b8-48b5-8468-3390cb6ee2ba")
    public BpmnProcessPropertyModel(BpmnProcess theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("e2017c97-f09f-4686-b057-a5e7be2a3c8f")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("772cf3ee-151d-40b2-810e-e8b85e06c8db")
    @Override
    public int getRowsNumber() {
        return BpmnProcessPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("b3e37e8f-4642-4680-80c6-1980f4a465f3")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnProcessPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getProcessType();
            case 3:
                return this.theEditedElement.isIsClosed() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.getIsExecutable();
            case 5:
                return Process.getTarget(this.theEditedElement);
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
    @objid ("7a0150d9-aa54-4335-ac93-10a2e12f6511")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), BpmnProcessType.class);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), OptionalBoolean.class);
            case 5:
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Process.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnProcessPropertyModel.this.mdaExpert.canLink(Process.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnProcessPropertyModel.this.theEditedElement, element);
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
    @objid ("a57b22c1-eb20-4481-bffd-8a5c9cec983f")
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
                this.theEditedElement.setProcessType((BpmnProcessType) value);
                break;
            case 3:
                this.theEditedElement.setIsClosed((Boolean) value);
                break;
            case 4:
                this.theEditedElement.setIsExecutable((OptionalBoolean) value);
                break;
            case 5:
                Process.setTarget(this.theEditedElement, (ModelElement) value);
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
