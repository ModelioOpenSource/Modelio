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
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.modelproperty.UmlPropertyModelProvider.UmlPropertyModelVisitor;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnIntermediateThrowEvent</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnIntermediateThrowEvent</i> metaclass.
 */
@objid ("ffc65f17-91da-4789-9c32-2f4c23d22a45")
public class BpmnIntermediateThrowEventPropertyModel extends AbstractPropertyModel<BpmnIntermediateThrowEvent> {
    @objid ("0a9461bc-3b62-4fd3-82aa-29b9d360e058")
    private IMModelServices modelService;

    @objid ("e5b922ed-d01b-4d89-9912-ed89b8869dbf")
    private List<AbstractPropertyModel<BpmnEventDefinition>> delegatedPropertyModel;

    @objid ("643e96e3-f084-4936-988d-b54468abad5b")
    private UmlPropertyModelVisitor umlPropertyModelVisitor;

    @objid ("2dc6b4c6-4432-4f5b-9546-36666a8be861")
    private List<INatValue> labelList;

    @objid ("7b1f7424-0294-4d35-96d9-e05ab56fae1d")
    private List<INatValue> fieldList;

    /**
     * Create a new <i>BpmnIntermediateThrowEvent</i> data model from an <i>BpmnIntermediateThrowEvent</i>.
     * @param definitionPropertyModel
     * 
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("a784bf35-218a-46b9-98f1-ff25de6b8fa1")
    public BpmnIntermediateThrowEventPropertyModel(BpmnIntermediateThrowEvent theEditedElement, IMModelServices modelService, UmlPropertyModelVisitor umlPropertyModelVisitor) {
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
    @objid ("8dc62747-5c60-4b08-a51e-130110d24ff9")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("520941c5-fa03-4ec7-bdab-220106f1699a")
    private Class<? extends Enum<?>> getEnumeration() {
        return EventType.class;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("db5b016e-96ed-465b-bbd8-25828dd4c5ba")
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
    @objid ("dc990286-1c39-4dd3-b701-49ee3d2a09e8")
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
    @objid ("86c35503-7bc9-4667-9414-bc29762717e0")
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

    @objid ("893de830-c6e1-4402-b477-2db714b95d54")
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

    @objid ("57485120-5c7d-4d8c-b395-ee7300bb8733")
    private void createEventDefinition(EventType evt) {
        IStandardModelFactory modelFactory = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnEventDefinition event_definition = (BpmnEventDefinition) modelFactory.createElement(EventType.getMetaclass(evt));
        event_definition.setName(this.modelService.getElementNamer().getBaseName(event_definition.getMClass()));
        event_definition.setDefined(this.theEditedElement);
    }

    @objid ("c2de2d78-b7e8-4c71-a517-28545c0a08ef")
    private enum EventType {
        Compensate,
        Escalation,
        Link,
        Message,
        Signal;

        @objid ("4190acfd-f0bd-40d4-92a2-400f0949c8f0")
        public static Class<? extends Element> getMetaclass(EventType event) {
            if (event == EventType.Message) {
                return BpmnMessageEventDefinition.class;
            } else if (event == EventType.Escalation) {
                return BpmnEscalationEventDefinition.class;
            } else if (event == EventType.Compensate) {
                return BpmnCompensateEventDefinition.class;
            } else if (event == EventType.Link) {
                return BpmnLinkEventDefinition.class;
            } else if (event == EventType.Signal) {
                return BpmnSignalEventDefinition.class;
            }
            return null;
        }

        @objid ("3fe0aacc-d423-4e3e-957c-16713bc53a45")
        public static EventType getType(BpmnEventDefinition definition) {
            if (definition instanceof BpmnMessageEventDefinition) {
                return Message;
            } else if (definition instanceof BpmnEscalationEventDefinition) {
                return Escalation;
            } else if (definition instanceof BpmnCompensateEventDefinition) {
                return Compensate;
            } else if (definition instanceof BpmnLinkEventDefinition) {
                return Link;
            } else if (definition instanceof BpmnSignalEventDefinition) {
                return Signal;
            }
            return null;
        }

    }

}
