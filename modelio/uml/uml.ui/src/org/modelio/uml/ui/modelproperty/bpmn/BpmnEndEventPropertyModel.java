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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.modelproperty.UmlPropertyModelProvider.UmlPropertyModelVisitor;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnEndEvent</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnEndEvent</i> metaclass.
 */
@objid ("93d821b7-2736-4712-a085-1d9b457e49cc")
public class BpmnEndEventPropertyModel extends AbstractPropertyModel<BpmnEndEvent> {
    @objid ("79d9623b-e4d2-437e-8127-0143d434f98b")
    private IMModelServices modelService;

    @objid ("3c611008-13f3-460f-87f8-8e1e55446b0f")
    private List<AbstractPropertyModel<BpmnEventDefinition>> delegatedPropertyModel;

    @objid ("3b2ebbd1-091c-4599-8647-95944240768d")
    private UmlPropertyModelVisitor umlPropertyModelVisitor;

    @objid ("0b9d5b86-06ce-4ab7-a227-beca289e4b78")
    private List<INatValue> labelList;

    @objid ("61548df2-1962-412a-b92a-c7fb5ffb7d3e")
    private List<INatValue> fieldList;

    /**
     * Create a new <i>BpmnEndEvent</i> data model from an <i>BpmnEndEvent</i>.
     * 
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("afc942a6-9249-4950-8b7a-edb61f37f03c")
    public BpmnEndEventPropertyModel(BpmnEndEvent theEditedElement, IMModelServices modelService, UmlPropertyModelVisitor umlPropertyModelVisitor) {
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
    @objid ("43664f50-51ca-41f1-859d-13c57a7a43ce")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("ccceae29-a582-4637-a085-686d512e32a2")
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
    @objid ("3862d819-662d-48c8-9711-70d29a5868d6")
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
    @objid ("2c2ab76b-c90b-4e1f-8c04-6ccc3f116bea")
    @Override
    public void setValueAt(int row, int col, Object value) {
        if (row == 1) {
            this.theEditedElement.setName((String) value);
            return;
        } else {
            int idx = 1;
            for (EventType evt : EventType.values()) {
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
        
        }
    }

    @objid ("566461ce-bb6e-49f3-8e37-1890319e0159")
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
        this.fieldList.add(new DefaultStringNatValue(this.theEditedElement.getName(), false)); // Name
        
        for (EventType evt : EventType.values()) {
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
    }

    @objid ("bd9c78ee-9c77-469a-a0f8-d89a4477c85f")
    private void createEventDefinition(EventType evt) {
        IStandardModelFactory modelFactory = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnEventDefinition event_definition = (BpmnEventDefinition) modelFactory.createElement(EventType.getMetaclass(evt));
        event_definition.setName(this.modelService.getElementNamer().getBaseName(event_definition.getMClass()));
        event_definition.setDefined(this.theEditedElement);
    }

    @objid ("00b0134c-0493-4f5e-bc35-db6e3c602de1")
    private enum EventType {
        Cancel,
        Compensate,
        Error,
        Escalation,
        Message,
        Signal,
        Terminate;

        @objid ("f531664d-acd9-44c8-80dc-e7f8ea34f0ec")
        public static Class<? extends Element> getMetaclass(EventType event) {
            if (event == EventType.Message) {
                return BpmnMessageEventDefinition.class;
            } else if (event == EventType.Error) {
                return BpmnErrorEventDefinition.class;
            } else if (event == EventType.Escalation) {
                return BpmnEscalationEventDefinition.class;
            } else if (event == EventType.Cancel) {
                return BpmnCancelEventDefinition.class;
            } else if (event == EventType.Compensate) {
                return BpmnCompensateEventDefinition.class;
            } else if (event == EventType.Signal) {
                return BpmnSignalEventDefinition.class;
            } else if (event == EventType.Terminate) {
                return BpmnTerminateEventDefinition.class;
            }
            return null;
        }

        @objid ("65a5bb06-6aaf-4e3a-9775-4f90353953ef")
        public static EventType getType(BpmnEventDefinition definition) {
            if (definition instanceof BpmnMessageEventDefinition) {
                return Message;
            } else if (definition instanceof BpmnErrorEventDefinition) {
                return Error;
            } else if (definition instanceof BpmnEscalationEventDefinition) {
                return Escalation;
            } else if (definition instanceof BpmnCancelEventDefinition) {
                return Cancel;
            } else if (definition instanceof BpmnCompensateEventDefinition) {
                return Compensate;
            } else if (definition instanceof BpmnSignalEventDefinition) {
                return Signal;
            } else if (definition instanceof BpmnTerminateEventDefinition) {
                return Terminate;
            }
            return null;
        }

    }

}
