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
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.modelproperty.UmlPropertyModelProvider.UmlPropertyModelVisitor;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnBoundaryEvent</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnBoundaryEvent</i> metaclass.
 */
@objid ("3ec1dbd9-5da2-4491-b4fe-7f7c1ee121b3")
public class BpmnBoundaryEventPropertyModel extends AbstractPropertyModel<BpmnBoundaryEvent> {
    @objid ("6205fce8-3b69-4917-b9c3-bea202d37afa")
    private IMModelServices modelService;

    @objid ("71a074ce-5af2-49c5-b522-50b2c34c7f2a")
    private List<AbstractPropertyModel<BpmnEventDefinition>> delegatedPropertyModel;

    @objid ("c5f019a7-9741-4009-b211-c72d342bbc50")
    private UmlPropertyModelVisitor umlPropertyModelVisitor;

    @objid ("ad870304-a878-4a7d-b402-0f906c1ab73d")
    private List<INatValue> labelList;

    @objid ("304e5c94-3a94-4c2d-be4c-56ca1e9c58af")
    private List<INatValue> fieldList;

    /**
     * Create a new <i>BpmnBoundaryEvent</i> data model from an <i>BpmnBoundaryEvent</i>.
     * 
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     * @param umlPropertyModelVisitor the property model visitor, needed for the event definition.
     */
    @objid ("f0db519b-a9a8-47d5-8bb2-1ce7a7665ba8")
    public BpmnBoundaryEventPropertyModel(BpmnBoundaryEvent theEditedElement, IMModelServices modelService, UmlPropertyModelVisitor umlPropertyModelVisitor) {
        super(theEditedElement);
        this.modelService = modelService;
        this.umlPropertyModelVisitor = umlPropertyModelVisitor;
        updateFieldsLists();
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("5691678d-a6eb-481c-869f-71068892598b")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("de839e95-9a2d-471f-96b0-655d7d133770")
    @Override
    public int getRowsNumber() {
        updateFieldsLists();
        return this.labelList.size();
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
    @objid ("a0b81bd2-3c8f-487e-af9f-3336b9c23b0c")
    @Override
    public INatValue getValueAt(int row, int col) {
        if (col == 0) {
            return this.labelList.get(row);
        } else {
            return this.fieldList.get(row);
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
    @objid ("26a0f518-7963-43e7-8beb-80fc87ff9832")
    @Override
    public void setValueAt(int row, int col, Object value) {
        if (row == 1) {
            this.theEditedElement.setName((String) value);
            return;
        } else if (row == 2) {
            this.theEditedElement.setCancelActivity((Boolean) value);
            return;
        } else {
            int idx = 2;
            for (EventType evt : EventType.getValues(this.theEditedElement)) {
                AbstractPropertyModel<BpmnEventDefinition> tdef = null;
                for (AbstractPropertyModel<BpmnEventDefinition> def : this.delegatedPropertyModel) {
                    if (evt == EventType.getType(def.getEditedElement())) {
                        tdef = def;
                    }
                }
                if (tdef == null) {
                    if (row == ++idx) {
                        createEventDefinition(evt);
                        updateFieldsLists();
                        return;
                    }
                } else {
                    if (row == ++idx) {
                        tdef.getEditedElement().delete();
                        updateFieldsLists();
                        return;
                    }
                    for (int i = 0; i < tdef.getRowsNumber(); i++) {
                        if (row == ++idx) {
                            tdef.setValueAt(i, 1, value);
                            return;
                        }
                    }
                }
            }
        
            // Row not found in event types, update ParallelMultiple
            this.theEditedElement.setParallelMultiple(Boolean.parseBoolean(Objects.toString(value)));
        }
    }

    @objid ("9dbfe90d-55ad-4c10-bf26-4e575804df3a")
    private void updateFieldsLists() {
        // Find delegated definition based on EventDefinitions of the Event.
        this.delegatedPropertyModel = new ArrayList<>();
        for (BpmnEventDefinition definition : this.theEditedElement.getEventDefinitions()) {
            this.delegatedPropertyModel.add((AbstractPropertyModel<BpmnEventDefinition>) definition.accept(this.umlPropertyModelVisitor));
        }
        
        this.labelList = new ArrayList<>();
        this.fieldList = new ArrayList<>();
        
        this.labelList.add(new DefaultStringNatValue(getPropertyI18n(AbstractPropertyModel.PROPERTY_ID), false)); // Header
        this.fieldList.add(new DefaultStringNatValue(getPropertyI18n(AbstractPropertyModel.VALUE_ID), false)); // Header
        
        this.labelList.add(new DefaultStringNatValue(getPropertyI18n("Name"), false)); // Name
        this.fieldList.add(new DefaultStringNatValue(this.theEditedElement.getName(), false));
        
        this.labelList.add(new DefaultStringNatValue(getPropertyI18n("Interrupting"), false)); // CancelActivity
        this.fieldList.add(new DefaultBooleanNatValue(this.theEditedElement.isCancelActivity()));
        
        for (EventType evt : EventType.getValues(this.theEditedElement)) {
            AbstractPropertyModel<BpmnEventDefinition> tdef = null;
            for (AbstractPropertyModel<BpmnEventDefinition> def : this.delegatedPropertyModel) {
                if (evt == EventType.getType(def.getEditedElement())) {
                    tdef = def;
                }
            }
            if (tdef == null) {
                this.labelList.add(new DefaultStringNatValue(UmlUi.I18N.getMessage("BpmnEventDefinitionType.label", evt.name()), false));
                this.fieldList.add(new DefaultBooleanNatValue(false));
            } else {
                this.labelList.add(new DefaultStringNatValue(UmlUi.I18N.getMessage("BpmnEventDefinitionType.label", evt.name()), false));
                this.fieldList.add(new DefaultBooleanNatValue(true));
                for (int i = 0; i < tdef.getRowsNumber(); i++) {
                    this.labelList.add(tdef.getValueAt(i, 0));
                    this.fieldList.add(tdef.getValueAt(i, 1));
                }
            }
        }
        
        if (this.delegatedPropertyModel.size() >= 2) {
            this.labelList.add(new DefaultStringNatValue(getPropertyI18n("ParallelMultiple"), false)); // ParallelMultiple
            this.fieldList.add(new DefaultBooleanNatValue(this.theEditedElement.isParallelMultiple()));
        }
    }

    @objid ("9b7d9099-4b7e-4e2c-a896-742be6448838")
    private void createEventDefinition(EventType evt) {
        IStandardModelFactory modelFactory = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnEventDefinition event_definition = (BpmnEventDefinition) modelFactory.createElement(EventType.getMetaclass(evt));
        event_definition.setName(this.modelService.getElementNamer().getBaseName(event_definition.getMClass()));
        event_definition.setDefined(this.theEditedElement);
    }

    @objid ("510d3298-c4e2-492d-9225-a7b84b4bd297")
    private enum EventType {
        Cancel,
        Compensate,
        Conditional,
        Error,
        Escalation,
        Message,
        Signal,
        Timer;

        @objid ("8c2c2b10-60a2-4342-b2a1-1e5a9262d4c9")
        public static Class<? extends Element> getMetaclass(EventType event) {
            if (event == EventType.Message) {
                return BpmnMessageEventDefinition.class;
            } else if (event == EventType.Timer) {
                return BpmnTimerEventDefinition.class;
            } else if (event == EventType.Error) {
                return BpmnErrorEventDefinition.class;
            } else if (event == EventType.Escalation) {
                return BpmnEscalationEventDefinition.class;
            } else if (event == EventType.Cancel) {
                return BpmnCancelEventDefinition.class;
            } else if (event == EventType.Compensate) {
                return BpmnCompensateEventDefinition.class;
            } else if (event == EventType.Conditional) {
                return BpmnConditionalEventDefinition.class;
            } else if (event == EventType.Signal) {
                return BpmnSignalEventDefinition.class;
            }
            return null;
        }

        @objid ("4fcb70ae-1548-4d2e-89ba-54e6392aa16b")
        public static EventType getType(BpmnEventDefinition definition) {
            if (definition instanceof BpmnMessageEventDefinition) {
                return Message;
            } else if (definition instanceof BpmnTimerEventDefinition) {
                return Timer;
            } else if (definition instanceof BpmnErrorEventDefinition) {
                return Error;
            } else if (definition instanceof BpmnEscalationEventDefinition) {
                return Escalation;
            } else if (definition instanceof BpmnCancelEventDefinition) {
                return Cancel;
            } else if (definition instanceof BpmnCompensateEventDefinition) {
                return Compensate;
            } else if (definition instanceof BpmnConditionalEventDefinition) {
                return Conditional;
            } else if (definition instanceof BpmnSignalEventDefinition) {
                return Signal;
            }
            return null;
        }

        @objid ("bbc196c9-970a-456b-b2cb-65da2d7cdcd9")
        public static List<EventType> getValues(BpmnBoundaryEvent theEditedElement) {
            List<EventType> result = new ArrayList<>();
            if (theEditedElement.isCancelActivity()) {
                result.add(Message);
                result.add(Timer);
                result.add(Error);
                result.add(Signal);
                result.add(Conditional);
                result.add(Cancel);
                result.add(Compensate);
                result.add(Escalation);
            } else {
                result.add(Message);
                result.add(Timer);
                result.add(Signal);
                result.add(Conditional);
                result.add(Escalation);
            }
            return result;
        }

    }

}
