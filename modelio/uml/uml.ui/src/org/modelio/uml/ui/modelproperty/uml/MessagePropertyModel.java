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
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.choice.DefaultElementChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Message</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Message</i> metaclass.
 */
@objid ("d6df685b-3597-4df5-b9b1-1a9a11aea7da")
public class MessagePropertyModel extends AbstractPropertyModel<Message> {
    /**
     * Properties to display for <i>Message</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("31486f37-d1d5-4524-b74b-51568d54e8e5")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Invoked",
			"Argument", "SignalSignature" };

    /**
     * Create a new <i>Message</i> data model from an <i>Message</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("02177845-a005-4ccb-871e-5e0fb1bd3407")
    public MessagePropertyModel(Message theEditedElement) {
        super(theEditedElement);
    }

    @objid ("b7e71e00-8cca-4e37-91f9-7eb91f4bf2d9")
    private List<ModelElement> getAvailableOperations(Classifier classifier) {
        List<ModelElement> operationsList = new ArrayList<>();
        
        if (classifier != null) {
            // Get operations from the classifier itself:
            for (Operation op : classifier.getOwnedOperation()) {
                operationsList.add(op);
            }
        
            // Get operations from parents:
            for (Generalization generalization : classifier.getParent()) {
                NameSpace parentNameSpace = generalization.getSuperType();
                if (parentNameSpace instanceof Classifier) {
                    operationsList.addAll(getAvailableOperations((Classifier) parentNameSpace));
                }
            }
        
            // Get operations from realized interfaces:
            for (InterfaceRealization realization : classifier.getRealized()) {
                Interface parentNameSpace = realization.getImplemented();
                operationsList.addAll(getAvailableOperations(parentNameSpace));
            }
        
            // Get operations from provided interfaces:
            for (BindableInstance bindableInstance : classifier.getInternalStructure()) {
                NameSpace biNs = bindableInstance.getBase();
                if (biNs instanceof Classifier && !biNs.equals(classifier)) {
                    operationsList.addAll(getAvailableOperations((Classifier) biNs));
                }
                if (bindableInstance instanceof Port) {
                    Port port = (Port) bindableInstance;
                    EList<ProvidedInterface> providedInterfaces = port.getProvided();
                    for (ProvidedInterface providedInterface : providedInterfaces) {
                        EList<Interface> interfaces = providedInterface.getProvidedElement();
                        for (Interface itf : interfaces) {
                            operationsList.addAll(getAvailableOperations(itf));
                        }
                    }
                }
            }
        }
        return operationsList;
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("e3d91e9d-4b0d-4b9e-ad31-78706e7f4248")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("3d30c1ba-4b8d-4e92-98ab-2fdc56488e3e")
    private INatValue getInvokedOperationType(Operation operation) {
        DefaultElementNatValue defaultInvokedType = new DefaultElementNatValue(operation, true,
                Collections.singletonList(Operation.class));
        
        MessageEnd message = this.theEditedElement.getReceiveEvent();
        if (message == null) {
            return defaultInvokedType;
        }
        
        EList<Lifeline> lifelines = message.getCovered();
        if (lifelines.size() != 1) {
            return defaultInvokedType;
        }
        
        Lifeline lifeline = lifelines.get(0);
        if (lifeline == null) {
            return defaultInvokedType;
        }
        
        Instance instance = lifeline.getRepresented();
        if (instance == null) {
            return defaultInvokedType;
        }
        
        NameSpace ns = instance.getBase();
        if (!(ns instanceof Classifier)) {
            return defaultInvokedType;
        }
        
        Classifier classifier = (Classifier) ns;
        
        List<ModelElement> availableOperations = getAvailableOperations(classifier);
        return new DefaultElementChoiceNatValue(operation, true, Collections.singletonList(Operation.class),
                                                availableOperations);
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("196ce8b4-7ccb-4a8f-8328-8ec57e9fc48e")
    @Override
    public int getRowsNumber() {
        return MessagePropertyModel.PROPERTIES.length;
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
    @objid ("fd7dd545-8684-41d9-99e3-5ae173cabae9")
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
                return this.theEditedElement.getInvoked();
            case 3:
                return this.theEditedElement.getArgument();
            case 4:
                return this.theEditedElement.getSignalSignature();
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
    @objid ("18b8c2ee-81c2-4752-a6df-a9ce4e2f9ee7")
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
                return getInvokedOperationType((Operation) getValue(row, col));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultElementNatValue((MObject) getValue(row, col), true, Collections.singletonList(Signal.class));
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
    @objid ("67c09e2e-dcad-4afc-9cfa-88a32d50cd63")
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
                this.theEditedElement.setInvoked((Operation) value);
                break;
            case 3:
                this.theEditedElement.setArgument((String) value);
                break;
            case 4:
                this.theEditedElement.setSignalSignature((Signal) value);
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
