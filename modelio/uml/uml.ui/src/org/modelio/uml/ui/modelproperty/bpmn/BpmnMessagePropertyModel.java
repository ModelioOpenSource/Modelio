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
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
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
 * <i>BpmnMessage</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnMessage</i> metaclass.
 */
@objid ("590298fa-91cf-4bf7-afbb-75708be2179b")
public class BpmnMessagePropertyModel extends AbstractPropertyModel<BpmnMessage> {
    /**
     * Properties to display for <i>BpmnMessage</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("08241c76-3a0b-4540-97a9-3f2b9e1df8a1")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Represented", "State" };

    @objid ("f0fe78a7-5884-467a-bf1c-62e72e2ab4ec")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnMessage</i> data model from an <i>BpmnMessage</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("863e3548-c11a-486f-ae7b-d00f182f05d0")
    public BpmnMessagePropertyModel(BpmnMessage theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
    }

    /**
     * Get all states defined in a class.
     */
    @objid ("96235800-18ff-401a-a98e-0337d5d65b7a")
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
    @objid ("3818d8c8-7697-43c4-8a9a-d3a3ce83f167")
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
    @objid ("fdeb370c-a296-482a-81ff-3af8485e4cdf")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("d92d1879-2443-4c78-a5d7-d0563f498489")
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
    @objid ("032fe622-a5c1-45b1-9a7b-52d75ba74b1a")
    @Override
    public int getRowsNumber() {
        return BpmnMessagePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("2eddb298-8f32-4eca-8cbd-2c4e287a7e46")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnMessagePropertyModel.PROPERTIES[row]);
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
    @objid ("ce7289bb-eb43-43bd-a76f-af5b107f7ce6")
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
                        return BpmnMessagePropertyModel.this.mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnMessagePropertyModel.this.theEditedElement, element);
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
    @objid ("935f185e-a5d9-4900-bb2c-3fd0436afb4a")
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
