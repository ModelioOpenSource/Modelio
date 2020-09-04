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

import java.util.Arrays;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * <i>InternalTransition</i> data model.
 * <p>
 * This class provides the list of properties for the <i>InternalTransition</i>
 * metaclass.
 */
@objid ("a5217e42-fd88-47d7-9430-a23ba0a192af")
public class InternalTransitionPropertyModel extends AbstractPropertyModel<InternalTransition> {
    /**
     * Properties to display for <i>InternalTransition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("6bb03d47-ff40-4b11-848a-060ccd0eeb27")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "ReceivedEvents",
			"Guard", "Effect", "SentEvents", "Post" };

    @objid ("cd4bb3bd-b79c-4bae-8da1-773c4106aa69")
    private TransitionEffectType transitionEffectType;

    @objid ("ac8dc1d7-4833-4224-827a-d5dff7a89814")
    private TransitionSentType transitionSentType;

    /**
     * Create a new <i>InternalTransition</i> data model from an
     * <i>InternalTransition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("9c384417-a771-4009-9530-384b4d3d0793")
    public InternalTransitionPropertyModel(InternalTransition theEditedElement) {
        super(theEditedElement);
        this.transitionEffectType = new TransitionEffectType();
        this.transitionSentType = new TransitionSentType();
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("4c56fa14-65ad-4925-bbb9-980cb9738427")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("b9dd4d8f-0bb1-4b83-ba04-b4d5cdbc310a")
    @Override
    public int getRowsNumber() {
        return InternalTransitionPropertyModel.PROPERTIES.length;
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
    @objid ("76ca4fc6-4cc3-49a3-8cd9-bd3b4230c663")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                // Received event
                String s = this.theEditedElement.getReceivedEvents();
                if (s.equals("Entry")) {
                    return ReceivedEventType.ENTRY;
                } else if (s.equals("Do")) {
                    return ReceivedEventType.DO;
                } else if (s.equals("Exit")) {
                    return ReceivedEventType.EXIT;
                } else {
                    return ReceivedEventType.DO;
                }
            case 2:
                return this.theEditedElement.getGuard();
            case 3:
                return TransitionEffectType.getValue(this.theEditedElement);
            case 4:
                return TransitionSentType.getValue(this.theEditedElement);
            case 5:
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
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("586936da-4789-4db3-b2a0-babf4201d63a")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
                return new DefaultStringNatValue((String) getValue(row, col), false); // **
                                                                                // Header
            case 1:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ReceivedEventType.class); // ReceivedEvents
            case 2:
                return new DefaultStringNatValue((String) getValue(row, col), false); // Guard
            case 3:
                return new DefaultHybridNatValue(getValue(row, col), true, Arrays.asList(Operation.class, Behavior.class), true); // Effect
            case 4:
                return new DefaultHybridNatValue(getValue(row, col), true, Collections.singletonList(Signal.class), true); // SentEvents
            case 5:
                return new DefaultStringNatValue((String) getValue(row, col), false); // Post
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
    @objid ("1cbd157d-e03f-4551-8b62-0aed8b378754")
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
                ReceivedEventType v = (ReceivedEventType) value;
                switch (v) {
                case ENTRY:
                    this.theEditedElement.setReceivedEvents("Entry");
                    break;
                case DO:
                    this.theEditedElement.setReceivedEvents("Do");
                    break;
                case EXIT:
                    this.theEditedElement.setReceivedEvents("Exit");
                    break;
                default:
                    break;
                }
        
                break;
            case 2:
                this.theEditedElement.setGuard((String) value);
                break;
            case 3:
                TransitionEffectType.setValue(this.theEditedElement, value);
                break;
            case 4:
                TransitionSentType.setValue(this.theEditedElement, value);
                break;
            case 5:
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
    @objid ("84287822-0789-4227-8a76-d0ee08bcdf57")
    protected static class TransitionEffectType {
        /**
         * Get the effect of a transition
         * 
         * @param t a Transition
         * @return a String, an Operation or a Behavior
         */
        @objid ("89a964b2-28fb-4cbf-b617-10db9c79d3ed")
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
         * 
         * @param t a Transition
         * @param value a String, an Operation or a Behavior
         */
        @objid ("24cc4799-e2da-4eeb-b0f3-f0615e993116")
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
     * Represents the Transition sent signals.
     * 
     * Merges the following Transition features: - SentEvents : string - Effects
     * : Signal
     * 
     * @author cmarin
     */
    @objid ("21d02a49-1de4-4010-835c-c4b3c1bd295c")
    protected static class TransitionSentType {
        /**
         * Get the sent signal of a transition
         * 
         * @param t a Transition
         * @return a String, or a Signal
         */
        @objid ("e7fe3713-b76b-4c28-aab6-46ff535fbd21")
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
         * 
         * @param t a Transition
         * @param value a String or a Signal
         */
        @objid ("e1423f41-8fab-4f1e-b5ed-815648ed8a28")
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

    /**
     * Predefined events for InternalTransition.ReceivedEvents
     * 
     * @author cmarin
     */
    @objid ("4adceafd-0ad5-46f7-b393-71dda9eda9f6")
    protected enum ReceivedEventType {
        DO,
        ENTRY,
        EXIT;
    }

}
