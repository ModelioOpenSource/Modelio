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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Event</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Event</i> metaclass.
 */
@objid ("e0715405-8292-4451-aac1-43883eef941a")
public class EventPropertyModel extends AbstractPropertyModel<Event> {
    /**
     * Properties to display for <i>Event</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("0207c04a-211e-4911-8fde-aa7aa6f05c63")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Kind" };

    @objid ("fafb3e9e-b131-4804-bc73-2c785089ab94")
    private EventKindType eventKindType;

    /**
     * Create a new <i>Event</i> data model from an <i>Event</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("25519e48-ae6f-4277-983e-9970fa05c56d")
    public EventPropertyModel(Event theEditedElement) {
        super(theEditedElement);
        
        this.eventKindType = new EventKindType();
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("ea3cefdc-0f13-4207-ba97-24c52fb36d20")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("97ba749f-69c2-45af-891d-8e96cf04d74f")
    @Override
    public int getRowsNumber() {
        return EventPropertyModel.PROPERTIES.length + 1;
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
    @objid ("4340d9dd-0b1c-4867-8a6d-f213d13ddaec")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            switch (row) {
            case 3:
                return EventKindType.getLabel(this.theEditedElement);
            default:
                return getPropertyI18n(PROPERTIES[row]);
            }
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getKind();
            case 3:
                return EventKindType.getValue(this.theEditedElement);
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
    @objid ("21f4d5d3-90ec-4b16-aee2-f01984fcaf35")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), EventType.class);
            case 3:
                DefaultHybridNatValue hybridNatValue = new DefaultHybridNatValue(getValue(row, col), true, EventKindType.getTypes(this.theEditedElement), EventKindType.acceptStringValue(this.theEditedElement));
                return hybridNatValue;
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
    @objid ("97d17f7d-3a36-4664-8967-396463f7951d")
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
                this.theEditedElement.setKind((EventType) value);
                EventKindType.resetValue(this.theEditedElement);
                break;
            case 3:
                EventKindType.setValue(this.theEditedElement, value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    @objid ("3cf009c9-4cf0-4615-9909-ae90b77d9ee4")
    protected static class EventKindType {
        @objid ("6218b541-c62a-41fe-91f1-99cb4077d90c")
        public static Object getLabel(Event e) {
            switch (e.getKind()) {
            case CALLEVENT:
                return "Called";
            case CHANGEEVENT:
                return "Expression";
            case SIGNALEVENT:
                return "Model";
            case TIMEEVENT:
                return "Expression";
            default:
                return "?";
            }
        }

        @objid ("245defaf-2d92-4d00-9b13-633abf87c0de")
        public static Object getValue(Event e) {
            switch (e.getKind()) {
            case CALLEVENT:
                return e.getCalled();
            case CHANGEEVENT:
            case TIMEEVENT:
                return e.getExpression();
            case SIGNALEVENT:
                return e.getModel();
            default:
                return null;
            }
        }

        @objid ("af609eaf-04bb-4ce3-a2eb-de3e780cda71")
        public static void resetValue(Event e) {
            switch (e.getKind()) {
            case CALLEVENT:
                e.setExpression("");
                e.setModel(null);
                break;
            case SIGNALEVENT:
                e.setExpression("");
                e.setCalled(null);
                break;
            case CHANGEEVENT:
            case TIMEEVENT:
                e.setCalled(null);
                e.setModel(null);
                break;
            }
        }

        @objid ("fbf1b986-92d1-4426-a403-013d301eca65")
        public static void setValue(Event e, Object value) {
            // Erase old value or exit if old value is new value
            e.setCalled(null);
            e.setExpression("");
            e.setModel(null);
            
            switch (e.getKind()) {
            case CALLEVENT:
                e.setCalled((Operation) value);
                break;
            case SIGNALEVENT:
                e.setModel((Signal) value);
                break;
            case CHANGEEVENT:
            case TIMEEVENT:
                e.setExpression((String) value);
                break;
            }
        }

        @objid ("e5308ae5-95d7-487f-894a-2b5d3f082812")
        public static boolean acceptStringValue(Event event) {
            switch (event.getKind()) {
            case CHANGEEVENT:
            case TIMEEVENT:
                return true;
            case SIGNALEVENT:
            case CALLEVENT:
                return false;
            }
            return false;
        }

        @objid ("8d8e469d-686e-4413-93d5-d37eddbc1f48")
        public static List<Class<? extends MObject>> getTypes(Event event) {
            List<Class<? extends MObject>> types = new ArrayList<>();
            
            switch (event.getKind()) {
            case CALLEVENT:
                types.add(Operation.class);
                break;
            case SIGNALEVENT:
                types.add(Signal.class);
                break;
            case CHANGEEVENT:
            case TIMEEVENT:
                break;
            }
            return types;
        }

    }

}
