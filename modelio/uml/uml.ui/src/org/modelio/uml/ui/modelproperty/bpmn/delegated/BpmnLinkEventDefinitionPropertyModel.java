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

package org.modelio.uml.ui.modelproperty.bpmn.delegated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnLinkEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnLinkEventDefinition</i> metaclass.
 */
@objid ("7e39555b-6f93-435f-8572-53a51dceff55")
public class BpmnLinkEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnLinkEventDefinition> {
    /**
     * Properties to display for <i>BpmnLinkEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("93022cc3-1d1c-474f-b2a1-f0266829806d")
    private static final String[] PROPERTIES = new String[] { "Target" };

    /**
     * Create a new <i>BpmnLinkEventDefinition</i> data model from an <i>BpmnLinkEventDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("26b07366-7093-4ef3-8194-bfdb86ce5eb9")
    public BpmnLinkEventDefinitionPropertyModel(BpmnLinkEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("fb3c0506-c071-4b28-85a8-17260e617521")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("d8078a49-742d-4105-981b-985af84c3b07")
    @Override
    public int getRowsNumber() {
        return BpmnLinkEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("28ae4573-624a-4708-975a-a02a6e5ffc8f")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                if (this.theEditedElement.getCompositionOwner() instanceof BpmnIntermediateCatchEvent) {
                    BpmnLinkEventDefinition def = this.theEditedElement.getTarget();
                    if (def != null) {
                        return def.getCompositionOwner();
                    }
                } else if (this.theEditedElement.getCompositionOwner() instanceof BpmnIntermediateThrowEvent) {
                    List<MObject> events = new ArrayList<>();
                    for (BpmnLinkEventDefinition def : this.theEditedElement.getSource()) {
                        events.add(def.getCompositionOwner());
                    }
                    return events;
                }
                return null;
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
    @objid ("a0a6ad21-dc33-4f24-a397-0f77de5b036d")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
                if (this.theEditedElement.getCompositionOwner() instanceof BpmnIntermediateCatchEvent) {
                    return new DefaultElementNatValue((MObject) getValue(row, col), true, Collections.singletonList(BpmnThrowEvent.class));
                } else {
                    return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true, Collections.singletonList(BpmnCatchEvent.class));
                }
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
    @objid ("e3b66613-785d-4b71-8c50-2302c5212c42")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                if (this.theEditedElement.getCompositionOwner() instanceof BpmnIntermediateCatchEvent) {
                    Optional<BpmnEventDefinition> opdef = ((BpmnThrowEvent) value).getEventDefinitions().stream().filter(def -> def instanceof BpmnLinkEventDefinition).findFirst();
                    if (opdef.isPresent()) {
                        this.theEditedElement.setTarget((BpmnLinkEventDefinition) opdef.get());
                    }
                } else {
                    List<BpmnLinkEventDefinition> requiredLinks = new ArrayList<>();
                    for(BpmnCatchEvent event : (List<BpmnCatchEvent>) value){
                        Optional<BpmnEventDefinition> opdef = event.getEventDefinitions().stream().filter(def -> def instanceof BpmnLinkEventDefinition).findFirst();
                        if (opdef.isPresent()) {
                            requiredLinks.add((BpmnLinkEventDefinition) opdef.get());            
                        }
                    }
                    
                    // Add new links
                    for(BpmnLinkEventDefinition def : requiredLinks){
                        if(!this.theEditedElement.getSource().contains(def)){
                            this.theEditedElement.getSource().add(def);
                        }
                    }
                    // Remove old links
                    for(BpmnLinkEventDefinition def : new ArrayList<>(this.theEditedElement.getSource())){
                  
                        if(!requiredLinks.contains(def)){
                            def.delete();
                        }
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

}
