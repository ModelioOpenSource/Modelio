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
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.uml.ui.modelproperty.UmlPropertyModelProvider.UmlPropertyModelVisitor;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnStartEvent</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnStartEvent</i> metaclass.
 */
@objid ("d8ec5cdd-6c83-456c-b8e1-a0dcf55abf70")
public class BpmnStartEventPropertyModel extends AbstractPropertyModel<BpmnStartEvent> {
    /**
     * Properties to display for <i>BpmnStartEvent</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("129677d9-aed2-4812-bd52-b679db55ecf9")
    private IMModelServices modelService;

    @objid ("5b824ff8-6bbd-4c1e-a709-f1abd5ade70f")
    private List<AbstractPropertyModel<BpmnEventDefinition>> delegatedPropertyModel;

    @objid ("77eca69b-fd96-4256-aa60-e9c4252cb6b9")
    private UmlPropertyModelVisitor umlPropertyModelVisitor;

    @objid ("9f52b0f7-66d7-4496-9ece-b8ab07973d49")
    private List<INatValue> labelList;

    @objid ("4411a5ee-649b-4098-8580-bc586d4edf38")
    private List<INatValue> fieldList;

    /**
     * Create a new <i>BpmnStartEvent</i> data model from an <i>BpmnStartEvent</i>.
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     * @param umlPropertyModelVisitor the property model visitor, needed for the event definition.
     */
    @objid ("f143090b-ee22-411f-be18-7cddd111f7be")
    public  BpmnStartEventPropertyModel(BpmnStartEvent theEditedElement, IMModelServices modelService, UmlPropertyModelVisitor umlPropertyModelVisitor) {
        super(theEditedElement);
        this.modelService = modelService;
        this.umlPropertyModelVisitor = umlPropertyModelVisitor;
        updateFieldsLists();
        
    }

    @objid ("82e6a1d0-dddc-4663-978d-886a7c98a9d8")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("3e80c042-6e28-4953-8151-13d05a6e5b8c")
    @Override
    public int getRowsNumber() {
        updateFieldsLists();
        return this.labelList.size();
    }

    @objid ("a6b4cea0-28a1-4e4d-be5a-c332f90ea303")
    @Override
    public INatValue getValueAt(int row, int col) {
        if (col == 0) {
            return this.labelList.get(row);
        } else {
            return this.fieldList.get(row);
        }
        
    }

    @objid ("d99b629a-075a-4417-aee1-b06a03912ac3")
    @Override
    public void setValueAt(int row, int col, Object value) {
        if (row == 1) {
            this.theEditedElement.setName((String) value);
            return;
        } else if (row == 2) {
            this.theEditedElement.setIsInterrupting((Boolean) value);
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

    @objid ("0337b611-208e-4984-a1a3-c10dbd1de8d3")
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
        
        this.labelList.add(new DefaultStringNatValue(getPropertyI18n("Interrupting"), false)); // Interrupting
        this.fieldList.add(new DefaultBooleanNatValue(this.theEditedElement.isIsInterrupting()));
        
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

    @objid ("5ec4b475-cdaf-44f6-9322-b8dd4b07ae5e")
    private void createEventDefinition(EventType evt) {
        IStandardModelFactory modelFactory = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnEventDefinition event_definition = (BpmnEventDefinition) modelFactory.createElement(EventType.getMetaclass(evt));
        event_definition.setName(this.modelService.getElementNamer().getBaseName(event_definition.getMClass()));
        event_definition.setDefined(this.theEditedElement);
        
    }

    @objid ("c5f0279c-c4ef-48fd-923a-ea0a9bf36843")
    private enum EventType {
        @objid ("985c8575-0803-4833-99e2-2e388f9a17ea")
        Message,
        @objid ("92cd3ea5-bf79-48ec-8231-c594b1a55c0b")
        Timer,
        @objid ("c6485d56-7c1b-4bdb-802a-63dd52f319c6")
        Conditional,
        @objid ("6b1c0917-8da2-4a99-98d6-576353d76ec3")
        Signal,
        @objid ("dae1e0bf-ab72-4354-bdb7-178e485b5905")
        Error,
        @objid ("1cd28747-a40e-49be-bb7d-fd7ff9998355")
        Escalation,
        @objid ("e3d8242c-e7f9-4ef5-a334-bd17b36d34b4")
        Compensation;

        @objid ("7f66e5a7-a587-4796-b72a-b22c289509ee")
        public static Class<? extends Element> getMetaclass(EventType event) {
            if (event == EventType.Message) {
                return BpmnMessageEventDefinition.class;
            } else if (event == EventType.Timer) {
                return BpmnTimerEventDefinition.class;
            } else if (event == EventType.Conditional) {
                return BpmnConditionalEventDefinition.class;
            } else if (event == EventType.Signal) {
                return BpmnSignalEventDefinition.class;
            } else if (event == EventType.Error) {
                return BpmnErrorEventDefinition.class;
            } else if (event == EventType.Escalation) {
                return BpmnEscalationEventDefinition.class;
            } else if (event == EventType.Compensation) {
                return BpmnCompensateEventDefinition.class;
            }
            return null;
        }

        @objid ("f22b2177-c466-4e71-8dd1-a7536ec39592")
        public static EventType getType(BpmnEventDefinition definition) {
            if (definition instanceof BpmnMessageEventDefinition) {
                return Message;
            } else if (definition instanceof BpmnTimerEventDefinition) {
                return Timer;
            } else if (definition instanceof BpmnErrorEventDefinition) {
                return Error;
            } else if (definition instanceof BpmnSignalEventDefinition) {
                return Signal;
            } else if (definition instanceof BpmnConditionalEventDefinition) {
                return Conditional;
            } else if (definition instanceof BpmnEscalationEventDefinition) {
                return Escalation;
            } else if (definition instanceof BpmnCompensateEventDefinition) {
                return Compensation;
            }
            return null;
        }

        @objid ("690f699f-80fb-4dd1-ae79-5d08e26d6516")
        public static List<EventType> getValues(BpmnStartEvent theEditedElement) {
            List<EventType> result = new ArrayList<>();
            if (theEditedElement.getSubProcess() != null) {
                if (theEditedElement.isIsInterrupting()) {
                    result.add(Message);
                    result.add(Timer);
                    result.add(Error);
                    result.add(Signal);
                    result.add(Conditional);
                    result.add(Escalation);
                    result.add(Compensation);
                } else {
                    result.add(Message);
                    result.add(Timer);
                    result.add(Signal);
                    result.add(Conditional);
                    result.add(Escalation);
                    result.add(Compensation);
                }
            } else {
                result.add(Message);
                result.add(Timer);
                result.add(Signal);
                result.add(Conditional);
            }
            return result;
        }

    }

}
