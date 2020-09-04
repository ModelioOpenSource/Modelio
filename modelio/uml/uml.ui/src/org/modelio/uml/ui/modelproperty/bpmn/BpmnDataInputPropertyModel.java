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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.choice.DefaultElementChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnDataInput</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnDataInput</i> metaclass.
 */
@objid ("ac9bf24a-c742-4a0a-b4a1-749343af4e0a")
public class BpmnDataInputPropertyModel extends AbstractPropertyModel<BpmnDataInput> {
    /**
     * Properties to display for <i>BpmnDataInput</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("1c1ffda1-c2d6-4e51-87c8-aa55b52e4a90")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Represented", "State" };

    @objid ("ecf2889e-e895-45bf-b881-cd28928ba011")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnDataInput</i> data model from an <i>BpmnDataInput</i> .
     * @param theEditedElement the model to edit.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("5f3c52e0-1d87-49d0-a8bc-54bdada5e378")
    public BpmnDataInputPropertyModel(BpmnDataInput theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
    }

    /**
     * Get all states defined in a class.
     */
    @objid ("1f424d74-aefe-42f7-9f40-73bf862d0287")
    private List<ModelElement> getAvailableStates(ModelElement representedElement) {
        List<ModelElement> states = new ArrayList<>();
        
        if (representedElement instanceof Classifier) {
            Classifier type = (Classifier) representedElement;
        
            // Add states from owned state machines
            for (StateMachine sm : type.getOwnedBehavior(StateMachine.class)) {
                final Region topRegion = sm.getTop();
                states.addAll(getAvailableStates(topRegion));
            }
        
            // Add states from owned operations
            for (Operation op : type.getOwnedOperation()) {
                for (StateMachine sm : op.getOwnedBehavior(StateMachine.class)) {
                    final Region topRegion = sm.getTop();
                    states.addAll(getAvailableStates(topRegion));
                }
            }
        
            // Add states from owned classes
            for (GeneralClass sub : type.getOwnedElement(GeneralClass.class)) {
                states.addAll(getAvailableStates(sub));
            }
        }
        return states;
    }

    /**
     * Get all states defined in a region.
     */
    @objid ("c9222df7-5998-4b37-9466-3474034d549d")
    private Collection<? extends ModelElement> getAvailableStates(Region region) {
        List<ModelElement> states = new ArrayList<>();
        for (org.modelio.metamodel.uml.behavior.stateMachineModel.State s : region.getSub(org.modelio.metamodel.uml.behavior.stateMachineModel.State.class)) {
            states.add(s);
        
            for (Region subRegion : s.getOwnedRegion()) {
                states.addAll(getAvailableStates(subRegion));
            }
        }
        return states;
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("d7be4e87-a35f-458d-b673-6fcc2843ae56")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("c8dacb58-09df-48e3-9a75-62331b29bf18")
    private INatValue getInStateType(ModelElement value) {
        List<ModelElement> availableStates = getAvailableStates(Represents.getTarget(this.theEditedElement));
        if (availableStates.isEmpty()) {
            return new DefaultElementNatValue(value, true, Collections.singletonList(org.modelio.metamodel.uml.behavior.stateMachineModel.State.class));
        } else {
            return new DefaultElementChoiceNatValue(value, true, Collections.singletonList(org.modelio.metamodel.uml.behavior.stateMachineModel.State.class), availableStates);
        }
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("2d0f25e4-8ab7-46ca-9db6-2795b1b32f77")
    @Override
    public int getRowsNumber() {
        return BpmnDataInputPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("92e3b607-94ef-4514-94da-225b193c4d15")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnDataInputPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return Represents.getTarget(this.theEditedElement);
            case 3:
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
    @objid ("aec11a76-4d8c-4c05-a817-2eb760c7ddd1")
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
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Represents.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnDataInputPropertyModel.this.mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnDataInputPropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
            case 3:
                return getInStateType((ModelElement) getValue(row, col));
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
    @objid ("ac23a50e-9766-4ae3-aa0b-6027b984fd8f")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            ModelElement representedElement = Represents.getTarget(this.theEditedElement);
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                this.theEditedElement.setName((String) value);
                break;
            case 2:
                Represents.setTarget(this.theEditedElement, (ModelElement) value);
                if (!getAvailableStates(representedElement).contains(State.getTarget(this.theEditedElement))) {
                    State.setTarget(this.theEditedElement, null);
                }
                break;
            case 3:
                if (!getAvailableStates(representedElement).contains(value)) {
                    Represents.setTarget(this.theEditedElement, null);
                }
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
