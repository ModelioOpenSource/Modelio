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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnParticipant</i> data model.
 * <p>
 * This class provides the list of properties for the {@link BpmnParticipant} metaclass.
 */
@objid ("1741d011-adc2-4b27-bfe6-a1e34eece9aa")
public class BpmnParticipantPropertyModel extends AbstractPropertyModel<BpmnParticipant> {
    /**
     * Properties to display for {@link BpmnParticipant}.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("ad03e07d-cb76-43c7-b343-9ceb5d82b641")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
            "Process", "MultiplicityMin", "MultiplicityMax", "Represents", "Reference" };

    @objid ("6fe5ede3-6d49-4e09-978d-1ad4a0f5c57e")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnParticipant</i> data model from an <i>BpmnParticipant</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("2a202e1e-57a5-45bd-90e5-b6d135f5c89d")
    public BpmnParticipantPropertyModel(BpmnParticipant theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("1c78d8b0-29db-46ba-b00f-543a6d3f515f")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("0a85b291-907d-4912-9e65-eccabf5c51bb")
    @Override
    public int getRowsNumber() {
        return BpmnParticipantPropertyModel.PROPERTIES.length;
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
    @objid ("c924bf7a-d9a1-4cd7-8b82-4395813100ab")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnParticipantPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getProcess();
            case 3:
                return this.theEditedElement.getMultiplicityMin();
            case 4:
                return this.theEditedElement.getMultiplicityMax();
            case 5:
                return Represents.getTarget(this.theEditedElement);
            case 6:
                return Reference.getTarget(this.theEditedElement);
            case 7:
                return this.theEditedElement.getInterfaceRefs();
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
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("48f8f561-0d73-4af9-94a4-2af03cb41c5b")
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
                DefaultElementNatValue processNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, Arrays.asList(BpmnProcess.class));
                processNatValue.setElementFilter(process -> {
                    // Filter processes that are already referenced in the current collaboration
                    for (BpmnParticipant participant : this.theEditedElement.getContainer().getParticipants()) {
                        if (process.equals(participant.getProcess())) {
                            return this.theEditedElement.equals(participant);
                        }
                    }
                    return true;
                });
                return processNatValue;
            case 3:
                return new DefaultIntegerNatValue((Integer) getValue(row, col));
            case 4:
                return new DefaultIntegerNatValue((Integer) getValue(row, col));
            case 5:
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Represents.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnParticipantPropertyModel.this.mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnParticipantPropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
            case 6:
                allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Reference.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnParticipantPropertyModel.this.mdaExpert.canLink(Reference.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnParticipantPropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
            case 7:
                @SuppressWarnings ("unchecked")
                Collection<MObject> coll = (Collection<MObject>) getValue(row, col);
                return new DefaultMultiElementNatValue(coll, true, Arrays.asList(BpmnInterface.class));
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
    @objid ("b4884f2c-5e0d-47f8-ac1d-e3b919bb1d19")
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
                // Process & Represents are exclusive
                this.theEditedElement.setProcess((BpmnProcess) value);
                Represents.setTarget(this.theEditedElement, null);
                break;
            case 3:
                this.theEditedElement.setMultiplicityMin((Integer) value);
                break;
            case 4:
                this.theEditedElement.setMultiplicityMax((Integer) value);
                break;
            case 5:
                // Process & Represents are exclusive
                Represents.setTarget(this.theEditedElement, (ModelElement) value);
                this.theEditedElement.setProcess(null);
                break;
            case 6:
                Reference.setTarget(this.theEditedElement, (ModelElement) value);
                break;
            case 7:
                this.theEditedElement.getInterfaceRefs().clear();
                @SuppressWarnings ("unchecked")
                Collection<? extends BpmnInterface> newColl = (Collection<? extends BpmnInterface>) value;
                this.theEditedElement.getInterfaceRefs().addAll(newColl);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("d8caeb97-cd01-48e3-8394-ef72c5c73a28")
    @Override
    public boolean isEditable(int row, int col) {
        final boolean isEditable = super.isEditable(row, col);
        if (col == 1 && row == 1) {
            // Name column
            return isEditable && !isReferencedProcess();
        } else {
            return isEditable;
        }
    }

    /**
     * @return <code>true</code> if the participant refers to a {@link BpmnProcess}.
     */
    @objid ("f4539b98-ee07-4794-990e-86ffa872206d")
    private boolean isReferencedProcess() {
        final BpmnProcess process = this.theEditedElement.getProcess();
        return process != null;
    }

}
