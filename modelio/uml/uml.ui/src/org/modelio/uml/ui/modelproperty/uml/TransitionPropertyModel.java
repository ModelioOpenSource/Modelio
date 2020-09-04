/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * <i>Transition</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Transition</i>
 * metaclass.
 */
@objid ("c0d85ae5-9faa-4f28-b1a9-9f3105724f1e")
public class TransitionPropertyModel extends AbstractPropertyModel<Transition> {
    /**
     * Properties to display for <i>Transition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("724c2fbc-661a-4a9e-9dec-e00a30da65e3")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
			"ReceivedEvents", "Guard", "Effect", "SentEvents", "Post" };

    /**
     * Create a new <i>Transition</i> data model from an <i>Transition</i>.
     * @param theEditedElement the transition to build a model for.
     */
    @objid ("a8c9363e-40a1-474d-b716-3e1dba2e850d")
    public TransitionPropertyModel(Transition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("90ea10fe-26e4-415a-84dc-d8f3e05a6c0d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("6db1275e-ae78-448c-8255-111013d10b0d")
    @Override
    public int getRowsNumber() {
        return TransitionPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("e61b6bd0-2e64-42e3-852d-55da1402abbf")
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
                return TransitionReceivedType.getValue(this.theEditedElement);
            case 3:
                return this.theEditedElement.getGuard();
            case 4:
                return TransitionEffectType.getValue(this.theEditedElement);
            case 5:
                return TransitionSentType.getValue(this.theEditedElement);
            case 6:
                return this.theEditedElement.getPostCondition();
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("b2a523bd-be4b-4226-b12b-2902d6b748e8")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
                // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                // Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2:
                // ReceivedEvents
                return new DefaultHybridNatValue(getValue(row, col), true, Arrays.asList(Operation.class, Event.class), true);
            case 3:
                // Guard
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                // Effect
                return new DefaultHybridNatValue(getValue(row, col), true, Arrays.asList(Operation.class, Behavior.class), true);
            case 5:
                // SentEvents
                return new DefaultHybridNatValue(getValue(row, col), true, Arrays.asList(Signal.class), true);
            case 6:
                // Post
                return new DefaultStringNatValue((String) getValue(row, col), false);
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
    @objid ("c5f7ea27-f76f-46f3-9f8e-295bdd94fd45")
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
                TransitionReceivedType.setValue(this.theEditedElement, value);
                break;
            case 3:
                this.theEditedElement.setGuard((String) value);
                break;
            case 4:
                TransitionEffectType.setValue(this.theEditedElement, value);
                break;
            case 5:
                TransitionSentType.setValue(this.theEditedElement, value);
                break;
            case 6:
                this.theEditedElement.setPostCondition((String) value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    /**
     * Represents the Transition effects.
     * 
     * Merges the following Transition features: - Effect : string - Processed :
     * Operation - BehaviorEffect : Behavior
     * 
     * @author cmarin
     */
    @objid ("7f2a35ef-0d7c-4260-b50c-121d9e72edc4")
    protected static class TransitionEffectType {
        /**
         * Get the effect of a transition
         * @param t a Transition
         * @return a String, an Operation or a Behavior
         */
        @objid ("8c834d62-9b6f-436d-a63c-8361ebc95e59")
        public static Object getValue(Transition t) {
            String sEffect = t.getEffect();
            if (sEffect != null && !sEffect.isEmpty()) {
                return sEffect;
            }
            
            Operation op = t.getProcessed();
            if (op != null) {
                return op;
            }
            
            Behavior b = t.getBehaviorEffect();
            return b;
        }

        /**
         * Set the effect of a Transition
         * @param t a Transition
         * @param value a String, an Operation or a Behavior
         */
        @objid ("c8cf1f67-d049-41be-ae96-ca5dab07af78")
        public static void setValue(Transition t, Object value) {
            // Erase old value or exit if old value is new value
            String old1 = t.getEffect();
            if (old1 != null && !old1.isEmpty()) {
                if (old1.equals(value)) {
                    return;
                }
                t.setEffect("");
            } else {
                Operation old2 = t.getProcessed();
                if (old2 != null) {
                    if (old2.equals(value)) {
                        return;
                    }
                    t.setProcessed(null);
                } else {
                    Behavior old3 = t.getBehaviorEffect();
                    if (old3 != null) {
                        if (old3.equals(value)) {
                            return;
                        }
                        t.setBehaviorEffect(null);
                    }
                }
            }
            
            if (value != null) {
                // Set new value
                if (String.class.isAssignableFrom(value.getClass())) {
                    t.setEffect((String) value);
                } else if (Operation.class.isAssignableFrom(value.getClass())) {
                    t.setProcessed((Operation) value);
                } else if (Behavior.class.isAssignableFrom(value.getClass())) {
                    t.setBehaviorEffect((Behavior) value);
                } else {
                    throw new IllegalArgumentException("value must be a String, an Operation or a Behavior but not a "
                            + value.getClass().getCanonicalName());
                }
            }
        }

    }

    /**
     * Represents the Transition received events.
     * 
     * Merges the following Transition features: - Received : string - Trigger :
     * Event
     * 
     * @author cmarin
     */
    @objid ("f59ff762-425d-41c8-9305-0b2dcda45b25")
    protected static class TransitionReceivedType {
        /**
         * Get the trigger of a transition
         * @param t a Transition
         * @return a String, or an Event
         */
        @objid ("d555744d-a0c6-4f8a-b8dc-a4b839d5b8e1")
        public static Object getValue(Transition t) {
            String sEffect = t.getReceivedEvents();
            if (sEffect != null && !sEffect.isEmpty()) {
                return sEffect;
            }
            
            Event op = t.getTrigger();
            return op;
        }

        /**
         * Set the trigger of a Transition
         * @param t a Transition
         * @param value a String, or an Event
         */
        @objid ("8fc927ec-b9df-4b7c-b35b-3e1a5ae2760a")
        public static void setValue(Transition t, Object value) {
            // Erase old value or exit if old value is new value
            String old1 = t.getReceivedEvents();
            if (old1 != null && !old1.isEmpty()) {
                if (old1.equals(value)) {
                    return;
                }
                t.setReceivedEvents("");
            } else {
                Event old2 = t.getTrigger();
                if (old2 != null) {
                    if (old2.equals(value)) {
                        return;
                    }
                    t.setTrigger(null);
                }
            }
            
            if (value != null) {
                // Set new value
                if (String.class.isAssignableFrom(value.getClass())) {
                    t.setReceivedEvents((String) value);
                } else if (Event.class.isAssignableFrom(value.getClass())) {
                    t.setTrigger((Event) value);
                } else {
                    throw new IllegalArgumentException(
                            "value must be a String or a Event but not a " + value.getClass().getCanonicalName());
                }
            }
        }

    }

    /**
     * Represents the Transition sent signals.
     * 
     * Merges the following Transition features: - SentEvents : string - Effects
     * : Signal
     * 
     * @author cmarin
     */
    @objid ("3bedfe2a-0299-40b6-b694-983da1f0e856")
    protected static class TransitionSentType {
        /**
         * Get the sent signal of a transition
         * @param t a Transition
         * @return a String, or a Signal
         */
        @objid ("e4065729-6415-4966-8542-1d7260ec364c")
        public static Object getValue(Transition t) {
            String sEffect = t.getSentEvents();
            if (sEffect != null && !sEffect.isEmpty()) {
                return sEffect;
            }
            
            Signal op = t.getEffects();
            return op;
        }

        /**
         * Set the signal sent from a Transition
         * @param t a Transition
         * @param value a String or a Signal
         */
        @objid ("a920b41b-4116-4b66-9536-ba189745de38")
        public static void setValue(Transition t, Object value) {
            // Erase old value or exit if old value is new value
            String old1 = t.getSentEvents();
            if (old1 != null && !old1.isEmpty()) {
                if (old1.equals(value)) {
                    return;
                }
                t.setSentEvents("");
            } else {
                Signal old2 = t.getEffects();
                if (old2 != null) {
                    if (old2.equals(value)) {
                        return;
                    }
                    t.setEffects(null);
                }
            }
            
            if (value != null) {
                // Set new value
                if (String.class.isAssignableFrom(value.getClass())) {
                    t.setSentEvents((String) value);
                } else if (Signal.class.isAssignableFrom(value.getClass())) {
                    t.setEffects((Signal) value);
                } else {
                    throw new IllegalArgumentException(
                            "value must be a String or a Signal but not a " + value.getClass().getCanonicalName());
                }
            }
        }

    }

}
