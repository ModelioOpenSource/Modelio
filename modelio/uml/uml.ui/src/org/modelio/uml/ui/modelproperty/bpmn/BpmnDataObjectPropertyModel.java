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
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
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
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnDataObject</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnDataObject</i> metaclass.
 */
@objid ("6fdaff89-9380-40af-889e-2b5af04a1aab")
public class BpmnDataObjectPropertyModel extends AbstractPropertyModel<BpmnDataObject> {
    /**
     * Properties to display for <i>BpmnDataObject</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("fdfcd512-2fd5-4991-b08a-c3b67206b02b")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Represented", "State", "Collection" };

    @objid ("1f478752-d8b9-4fa0-84cb-1c57f4f29c74")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnDataObject</i> data model from an <i>BpmnDataObject</i>.
     * @param theEditedElement the model to edit.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("d06c5b30-e5c6-42d1-963e-fdc9b17f45b5")
    public  BpmnDataObjectPropertyModel(BpmnDataObject theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
        
    }

    /**
     * Get all states defined in a class.
     */
    @objid ("08498798-6849-46e3-b189-cb7d47fa7f61")
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
    @objid ("386a90cb-82cf-4d79-ac8f-ea8509b4e513")
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
    @objid ("36ee791b-ed24-4873-9043-8c4249d7794c")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("9436a2fb-96cb-43ea-8d31-fcad57ba6d32")
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
    @objid ("10ea74ed-f6ee-4128-857a-d5db492beb73")
    @Override
    public int getRowsNumber() {
        return BpmnDataObjectPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("b3389ee5-76d4-487d-b0fc-debfbaeffc79")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnDataObjectPropertyModel.PROPERTIES[row]);
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
                return this.theEditedElement.isIsCollection();
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
    @objid ("290738bb-e9ea-4462-99cf-9cc794cd8d8a")
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
                        return BpmnDataObjectPropertyModel.this.mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnDataObjectPropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
            case 3:
                return getInStateType((ModelElement) getValue(row, col));
            case 4:
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
    @objid ("0d281377-cad4-423e-90a3-c2f421d0ea94")
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
                this.theEditedElement.setIsCollection((Boolean) value);
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
