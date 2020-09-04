/* 
 * Copyright 2013-2019 Modeliosoft
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnSequenceFlow</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnSequenceFlow</i> metaclass.
 */
@objid ("e6d4a2b1-162a-440b-b9cc-3d484631759e")
public class BpmnSequenceFlowPropertyModel extends AbstractPropertyModel<BpmnSequenceFlow> {
    /**
     * Properties to display for <i>BpmnSequenceFlow</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("198b2626-cf9a-4b51-9b41-791edf63d615")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Default Flow",
            "Guard", "Immediate", "DataObject" };

    @objid ("d9bea949-26da-4b29-b402-d656d28fd613")
    private IMModelServices modelService;

    /**
     * Create a new <i>BpmnSequenceFlow</i> data model from an <i>BpmnSequenceFlow</i>.
     * 
     * @param theEditedElement the flow to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("6fde7cdb-dfd4-4bcd-97d6-27e7d116b90f")
    public BpmnSequenceFlowPropertyModel(BpmnSequenceFlow theEditedElement, IMModelServices modelService) {
        super(theEditedElement);
        this.modelService = modelService;
    }

    @objid ("37eed1eb-b6cd-4f67-92a8-ad468a8a76af")
    private void addDataObject(IMModelServices mmService, final BpmnDataObject dataobject) {
        IStandardModelFactory modelFactory = mmService.getModelFactory().getFactory(IStandardModelFactory.class);
        
        final BpmnFlowNode source = this.theEditedElement.getSourceRef();
        final BpmnFlowNode target = this.theEditedElement.getTargetRef();
        
        final BpmnDataAssociation sourceAssociation = modelFactory.createBpmnDataAssociation();
        if (source instanceof BpmnActivity) {
            sourceAssociation.setStartingActivity((BpmnActivity) source);
        } else if (source instanceof BpmnCatchEvent) {
            sourceAssociation.setEndingEvent((BpmnCatchEvent) source);
        } else if (source instanceof BpmnThrowEvent) {
            // this case shouldn't be possible here, but the metamodel has mixed-up role names...
            sourceAssociation.setStartingEvent((BpmnThrowEvent) source);
        }
        sourceAssociation.setTargetRef(dataobject);
        
        final BpmnDataAssociation targetAssociation = modelFactory.createBpmnDataAssociation();
        if (target instanceof BpmnActivity) {
            targetAssociation.setEndingActivity((BpmnActivity) target);
        } else if (target instanceof BpmnThrowEvent) {
            targetAssociation.setStartingEvent((BpmnThrowEvent) target);
        } else if (target instanceof BpmnCatchEvent) {
            // this case shouldn't be possible here, but the metamodel has mixed-up role names...
            targetAssociation.setEndingEvent((BpmnCatchEvent) target);
        }
        targetAssociation.getSourceRef().add(dataobject);
        
        final BpmnSequenceFlowDataAssociation sequenceFlowAssociation = modelFactory.createBpmnSequenceFlowDataAssociation();
        sequenceFlowAssociation.setConnected(this.theEditedElement);
        sequenceFlowAssociation.getDataAssociation().add(sourceAssociation);
        sequenceFlowAssociation.getDataAssociation().add(targetAssociation);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("26822cc4-69a5-4719-b748-d2a1223c70bf")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("fdd57a36-a89a-48c8-828c-971141147f6f")
    @Override
    public int getRowsNumber() {
        return BpmnSequenceFlowPropertyModel.PROPERTIES.length;
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
    @objid ("78bc2b63-69a1-4891-acfd-8a88ae5e16b5")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnSequenceFlowPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getDefaultFrom() != null
                        || this.theEditedElement.getDefaultOfExclusive() != null
                        || this.theEditedElement.getDefaultOfInclusive() != null
                        || this.theEditedElement.getDefaultOfComplex() != null;
            case 3:
                return this.theEditedElement.getConditionExpression();
            case 4:
                return this.theEditedElement.isIsImmediate() ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                final List<BpmnDataObject> element = new ArrayList<>();
                for (final BpmnSequenceFlowDataAssociation assoc : this.theEditedElement.getConnector()) {
                    for (final BpmnDataAssociation dataassoc : assoc.getDataAssociation()) {
                        final BpmnItemAwareElement ita = dataassoc.getTargetRef();
                        if (ita instanceof BpmnDataObject) {
                            element.add((BpmnDataObject) ita);
                        }
                    }
                }
                return element;
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
    @objid ("a6ff6351-5af1-429e-af49-04cc4748f2b1")
    @SuppressWarnings ("unchecked")
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
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(BpmnDataObject.class));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("2af6d4fc-ed39-484a-a4ee-4a84a68f61d3")
    private void removeDataObject(final BpmnDataObject dataobject) {
        final List<BpmnDataAssociation> dataAssociations = new ArrayList<>();
        dataAssociations.addAll(dataobject.getSourceOfDataAssociation());
        dataAssociations.addAll(dataobject.getTargetOfDataAssociation());
        
        final Set<MObject> toDelete = new HashSet<>();
        
        // Gather BpmnDataAssociation and BpmnSequenceFlowDataAssociation connected to the edited sequence flow
        for (final BpmnDataAssociation dataAssociation : dataAssociations) {
            for (final BpmnSequenceFlowDataAssociation sfda : dataAssociation.getVisualShortCut().toArray(new BpmnSequenceFlowDataAssociation[0])) {
                if (sfda.getConnected() != null && sfda.getConnected().equals(this.theEditedElement)) {
                    toDelete.add(sfda);
                    toDelete.add(dataAssociation);
                }
            }
        }
        
        // Delete unwanted elements
        for (MObject mObject : toDelete) {
            mObject.delete();
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
    @objid ("2c723405-6b3e-48fe-bae0-e095f2bec439")
    @Override
    @SuppressWarnings ("unchecked")
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
                if ((Boolean) value) {
                    if (this.theEditedElement.getSourceRef() instanceof BpmnActivity) {
                        this.theEditedElement.setDefaultFrom((BpmnActivity) this.theEditedElement.getSourceRef());
                    } else if (this.theEditedElement.getSourceRef() instanceof BpmnExclusiveGateway) {
                        this.theEditedElement
                                .setDefaultOfExclusive((BpmnExclusiveGateway) this.theEditedElement.getSourceRef());
                    } else if (this.theEditedElement.getSourceRef() instanceof BpmnInclusiveGateway) {
                        this.theEditedElement
                                .setDefaultOfInclusive((BpmnInclusiveGateway) this.theEditedElement.getSourceRef());
                    } else if (this.theEditedElement.getSourceRef() instanceof BpmnComplexGateway) {
                        this.theEditedElement
                                .setDefaultOfComplex((BpmnComplexGateway) this.theEditedElement.getSourceRef());
                    }
                } else {
                    this.theEditedElement.setDefaultFrom(null);
                    this.theEditedElement.setDefaultOfExclusive(null);
                    this.theEditedElement.setDefaultOfInclusive(null);
                    this.theEditedElement.setDefaultOfComplex(null);
                }
        
                break;
            case 3:
                this.theEditedElement.setConditionExpression((String) value);
                break;
            case 4:
                this.theEditedElement.setIsImmediate((Boolean) value);
                break;
            case 5:
                final List<BpmnDataObject> element = new ArrayList<>();
                for (final BpmnSequenceFlowDataAssociation assoc : this.theEditedElement.getConnector()) {
                    for (final BpmnDataAssociation dataassoc : assoc.getDataAssociation()) {
                        final BpmnItemAwareElement ita = dataassoc.getTargetRef();
                        if (ita instanceof BpmnDataObject) {
                            element.add((BpmnDataObject) ita);
                        }
                    }
                }
        
                final List<BpmnDataObject> newcontent = (List<BpmnDataObject>) value;
                for (final BpmnDataObject s : element) {
                    if (!newcontent.contains(s)) {
                        removeDataObject(s);
                    }
                }
        
                for (final BpmnDataObject s : newcontent) {
                    if (!element.contains(s)) {
                        addDataObject(this.modelService, s);
                    }
                }
        
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("bf2703d6-0483-4080-827c-77eb5ad50a67")
    @Override
    public boolean isEditable(int row, int col) {
        boolean isEditable = super.isEditable(row, col);
        if (col == 1 && row == 5) {
            final BpmnFlowNode source = this.theEditedElement.getSourceRef();
            final BpmnFlowNode target = this.theEditedElement.getTargetRef();
        
            boolean isValidSource = source instanceof BpmnActivity || source instanceof BpmnCatchEvent;
            boolean isValidTarget = target instanceof BpmnActivity || target instanceof BpmnThrowEvent;
            return isEditable && isValidSource && isValidTarget;
        } else {
            return isEditable;
        }
    }

}
