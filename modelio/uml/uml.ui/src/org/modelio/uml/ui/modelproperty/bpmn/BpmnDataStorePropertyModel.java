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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.choice.DefaultElementChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnDataStore</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnDataStore</i> metaclass.
 */
@objid ("9726a24b-10aa-4b2e-87e8-3d1dd95e6cc3")
public class BpmnDataStorePropertyModel extends AbstractPropertyModel<BpmnDataStore> {
    /**
     * Properties to display for <i>BpmnDataStore</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("93bb81bd-5573-4ad6-959f-e9e818fd2afe")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Represented", "State", "Capacity", "Unlimited" };

    @objid ("2d679836-379f-4012-ba46-dbb184676df6")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnDataStore</i> data model from an <i>BpmnDataStore</i> .
     * @param theEditedElement the model to edit.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("fbd8166e-f662-4ba6-b0ee-eb996b4a204e")
    public  BpmnDataStorePropertyModel(BpmnDataStore theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
        
    }

    /**
     * Get all states defined in a class.
     */
    @objid ("28130d7f-986d-4ef0-8b66-a1554155c4ae")
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
    @objid ("71aa4b5d-77cd-4c56-aace-cad94a727c6d")
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
    @objid ("75445fff-f2ac-4559-9968-6077e066adf1")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("8a2987ec-3eb9-45d9-a97f-b5cc50686842")
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
    @objid ("0dba0c57-50c4-4374-aec0-3652bca6a5d3")
    @Override
    public int getRowsNumber() {
        return BpmnDataStorePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("c05796a1-3d36-4211-8700-a2bd65c79455")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnDataStorePropertyModel.PROPERTIES[row]);
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
            case 4:
                return this.theEditedElement.getCapacity();
            case 5:
                return this.theEditedElement.isIsUnlimited() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("7cf7fae0-6149-4fd0-a3bb-5c57df277bab")
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
                        return BpmnDataStorePropertyModel.this.mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnDataStorePropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
            case 3:
                return getInStateType((ModelElement) getValue(row, col));
            case 4:
                return new DefaultIntegerNatValue((Integer) getValue(row, col));
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
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
    @objid ("b07568cd-34bb-45b9-89a1-a271c1badb89")
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
            case 4:
                this.theEditedElement.setCapacity((Integer) value);
                break;
            case 5:
                this.theEditedElement.setIsUnlimited((Boolean) value);
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
