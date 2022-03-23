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
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
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
 * <i>BpmnIntermediateCatchEvent</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnIntermediateCatchEvent</i> metaclass.
 */
@objid ("efea0253-1384-4328-8988-ad5b4b1484bd")
public class BpmnIntermediateCatchEventPropertyModel extends AbstractPropertyModel<BpmnIntermediateCatchEvent> {
    @objid ("5bd355cf-a71d-48f0-9c70-d3e4ce01556c")
    private IMModelServices modelService;

    @objid ("3cd1a629-6fa1-4390-9da0-f2c7ec56da1b")
    private List<AbstractPropertyModel<BpmnEventDefinition>> delegatedPropertyModel;

    @objid ("5e55cb86-abba-41b3-969d-b5136184d521")
    private UmlPropertyModelVisitor umlPropertyModelVisitor;

    @objid ("e082c8fb-9cad-4d80-844b-111fa32b20e3")
    private List<INatValue> labelList;

    @objid ("cd73231f-5227-40c4-8e74-132153d9368f")
    private List<INatValue> fieldList;

    /**
     * Create a new <i>BpmnIntermediateCatchEvent</i> data model from an <i>BpmnIntermediateCatchEvent</i>.
     * @param delegatedPropertyModel
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("3f603c8e-d12b-4e83-930a-780cb0209bc7")
    public  BpmnIntermediateCatchEventPropertyModel(BpmnIntermediateCatchEvent theEditedElement, IMModelServices modelService, UmlPropertyModelVisitor umlPropertyModelVisitor) {
        super(theEditedElement);
        this.modelService = modelService;
        this.umlPropertyModelVisitor = umlPropertyModelVisitor;
        updateFieldsLists();
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("24f0b245-a22f-4f3e-89d2-de33fdc93195")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("3fe59083-5e0a-4ecc-90c9-adc4f7e5e750")
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("f555a2c8-cb4b-4765-a269-92e16452e86d")
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("c69758da-7f86-4ea8-b7b7-c4912b0519ce")
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
        
            // Row not found in event types, update ParallelMultiple
            this.theEditedElement.setParallelMultiple(Boolean.parseBoolean(Objects.toString(value)));
        }
        
    }

    @objid ("ee655706-7b05-40ab-972b-d61875e6c7e8")
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
        
        if (this.delegatedPropertyModel.size() >= 2) {
            this.labelList.add(new DefaultStringNatValue(getPropertyI18n("ParallelMultiple"), false)); // ParallelMultiple
            this.fieldList.add(new DefaultBooleanNatValue(this.theEditedElement.isParallelMultiple()));
        }
        
    }

    @objid ("5b9c3c25-b4c2-4a2e-8bb9-a661c16dec05")
    private void createEventDefinition(EventType evt) {
        IStandardModelFactory modelFactory = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnEventDefinition event_definition = (BpmnEventDefinition) modelFactory.createElement(EventType.getMetaclass(evt));
        event_definition.setName(this.modelService.getElementNamer().getBaseName(event_definition.getMClass()));
        event_definition.setDefined(this.theEditedElement);
        
    }

    /**
     * Properties to display for <i>BpmnIntermediateCatchEvent</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     * 
     * Event Type
     */
    @objid ("3eab2911-550a-419b-992a-7d88279a42ab")
    private enum EventType {
        @objid ("9a9041e0-4c0f-45fa-931f-ba2d59b48dc1")
        Conditional,
        @objid ("eaf2a000-e5e2-472f-9e52-b0724da3cf57")
        Link,
        @objid ("cf820ba2-7d23-4213-85be-a32bde2f78dd")
        Message,
        @objid ("b541544b-863b-4106-be11-8571960e8cf6")
        Signal,
        @objid ("ffffd567-6f7f-47f9-bfa3-91da2dcc8e78")
        Timer;

        @objid ("bad3ed40-9d5d-43d5-aa1f-850a73187e0e")
        public static Class<? extends Element> getMetaclass(EventType event) {
            if (event == EventType.Message) {
                return BpmnMessageEventDefinition.class;
            } else if (event == EventType.Timer) {
                return BpmnTimerEventDefinition.class;
            } else if (event == EventType.Conditional) {
                return BpmnConditionalEventDefinition.class;
            } else if (event == EventType.Link) {
                return BpmnLinkEventDefinition.class;
            } else if (event == EventType.Signal) {
                return BpmnSignalEventDefinition.class;
            }
            return null;
        }

        @objid ("9a5fbe0c-5ef3-415f-91de-9f7465f3670e")
        public static EventType getType(BpmnEventDefinition definition) {
            if (definition instanceof BpmnMessageEventDefinition) {
                return Message;
            } else if (definition instanceof BpmnTimerEventDefinition) {
                return Timer;
            } else if (definition instanceof BpmnConditionalEventDefinition) {
                return Conditional;
            } else if (definition instanceof BpmnLinkEventDefinition) {
                return Link;
            } else if (definition instanceof BpmnSignalEventDefinition) {
                return Signal;
            }
            return null;
        }

    }

}
