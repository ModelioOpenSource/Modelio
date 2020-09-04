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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.activities.AdHocOrdering;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnAdHocSubProcess</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnAdHocSubProcess</i>
 * metaclass.
 */
@objid ("5ac08f20-8535-4365-8740-140bd118798c")
public class BpmnAdHocSubProcessPropertyModel extends AbstractPropertyModel<BpmnAdHocSubProcess> {
    @objid ("665f1ccb-d882-4332-8a05-e6f7870c6c49")
    private final List<String> properties = new ArrayList<>();

    @objid ("7e78e7f1-e229-4433-b7ba-b161a9072b17")
    private final IMModelServices modelService;

    /**
     * Create a new <i>BpmnAdHocSubProcess</i> data model from an
     * <i>BpmnAdHocSubProcess</i>.
     * 
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("a02df68f-a0cf-4735-a655-832f62f2fac1")
    public BpmnAdHocSubProcessPropertyModel(BpmnAdHocSubProcess theEditedElement, IMModelServices modelService) {
        super(theEditedElement);
        this.modelService = modelService;
        initPropertyModel();
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("cdb3b478-873e-4b81-ab58-44d5e191ccb0")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("1d650358-c030-4749-9775-5073e70b1b43")
    @Override
    public int getRowsNumber() {
        // Init properties here to avoid problems with UNDO
        initPropertyModel();
        return this.properties.size();
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
    @objid ("1a04e153-8226-4e5a-be65-67884c66387f")
    private Object getValue(int row, int col) {
        LoopType type = LoopType.getType(this.theEditedElement);
        if (col == 0) {
            return getPropertyI18n(this.properties.get(row));
        }
        
        // else
        if (col == 1) // col 1 is the property value
        {
            if (row == 0) {
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            } else if (row == 1) {
                return this.theEditedElement.getName();
            } else if (row == 2) {
                return this.theEditedElement.isIsForCompensation() ? Boolean.TRUE : Boolean.FALSE;
            } else if (row == 3) {
                return this.theEditedElement.getStartQuantity();
            } else if (row == 4) {
                return this.theEditedElement.getCompletionQuantity();
            } else if (row == 5) {
                return this.theEditedElement.isTriggeredByEvent() ? Boolean.TRUE : Boolean.FALSE;
            } else if (row == 6) {
                return this.theEditedElement.getOrdering();
            } else if (row == 7) {
                return this.theEditedElement.isCancelRemainingInstances() ? Boolean.TRUE : Boolean.FALSE;
            } else if (row == 8) {
                return this.theEditedElement.getCompletionCondition();
            } else if (row == 9) {
                return LoopType.getType(this.theEditedElement);
            } else if (row > 9) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 10) {
                        return caracteristic.isTestBefore() ? Boolean.TRUE : Boolean.FALSE;
                    } else if (row == 11) {
                        return caracteristic.getLoopCondition();
                    } else if (row == 12) {
                        return caracteristic.getLoopMaximum();
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 10) {
                        return caracteristic.getBehavior();
                    } else if (row == 11) {
                        return caracteristic.getLoopCardinality();
                    } else if (row == 12) {
                        return caracteristic.getCompletionCondition();
                    } else if (row == 13) {
                        return caracteristic.getCompletionEventRef();
                    }
                }
            }
        }
        return null;
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
    @objid ("602cf330-4f78-4d65-a0d7-b8283b25d6cf")
    @Override
    public INatValue getValueAt(int row, int col) {
        LoopType type = LoopType.getType(this.theEditedElement);
        if (col == 0) {
            return new DefaultStringNatValue((String) getValue(row, col), false);
        }
        
        // else
        if (col == 1) // col 1 is the property value
        {
            if (row == 0) {
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else if (row == 1) {
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else if (row == 2) {
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            } else if (row == 3) {
                return new DefaultIntegerNatValue((Integer) getValue(row, col));
            } else if (row == 4) {
                return new DefaultIntegerNatValue((Integer) getValue(row, col));
            } else if (row == 5) {
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            } else if (row == 6) {
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), AdHocOrdering.class);
            } else if (row == 7) {
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            } else if (row == 8) {
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else if (row == 9) {
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), LoopType.class);
            } else if (row > 9) {
                if (type == LoopType.Standard) {
                    if (row == 10) {
                        return new DefaultBooleanNatValue((Boolean) getValue(row, col));
                    } else if (row == 11) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 12) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    if (row == 10) {
                        return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), MultiInstanceBehavior.class);
                    } else if (row == 11) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 12) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 13) {
                        return new DefaultElementNatValue((MObject) getValue(row, col), true,
                                Collections.singletonList(BpmnEventDefinition.class));
                    }
                }
            }
        }
        return null;
    }

    @objid ("1a288723-5785-4867-921c-21fe8a64ddc0")
    private void initPropertyModel() {
        this.properties.clear();
        this.properties.add("AdHocSubProcess");
        this.properties.add("Name");
        this.properties.add("ForCompensation");
        this.properties.add("StartQuantity");
        this.properties.add("CompletionQuantity");
        this.properties.add("TriggeredByEvent");
        this.properties.add("Ordering");
        this.properties.add("CancelRemainingInstances");
        this.properties.add("CompletionCondition");
        this.properties.add("LoopCharacteristics");
        
        LoopType type = LoopType.getType(this.theEditedElement);
        if (type == LoopType.Standard) {
            this.properties.add("TestBefore");
            this.properties.add("LoopCondition");
            this.properties.add("LoopMaximum");
        } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
            this.properties.add("Behavior");
            this.properties.add("LoopCardinality");
            this.properties.add("CompletionCondition");
            this.properties.add("EventDefinition");
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
    @objid ("aa7f472d-015e-4487-95f4-351c4f60d814")
    @Override
    public void setValueAt(int row, int col, Object value) {
        LoopType type = LoopType.getType(this.theEditedElement);
        if (col == 0) {
            return;
        }
        
        if (col == 1) // col 1 is the property value
        {
            if (row == 0) {
                return;
            } else if (row == 1) {
                this.theEditedElement.setName((String) value);
            } else if (row == 2) {
                this.theEditedElement.setIsForCompensation((Boolean) value);
            } else if (row == 3) {
                this.theEditedElement.setStartQuantity((Integer) value);
            } else if (row == 4) {
                this.theEditedElement.setCompletionQuantity((Integer) value);
            } else if (row == 5) {
                this.theEditedElement.setTriggeredByEvent((Boolean) value);
            } else if (row == 6) {
                this.theEditedElement.setOrdering((AdHocOrdering) value);
            } else if (row == 7) {
                this.theEditedElement.setCancelRemainingInstances((Boolean) value);
            } else if (row == 8) {
                this.theEditedElement.setCompletionCondition((String) value);
            } else if (row == 9) {
                LoopType.setType(this.modelService, (LoopType) value, this.theEditedElement);
                initPropertyModel();
            } else if (row > 9) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 10) {
                        caracteristic.setTestBefore((Boolean) value);
                    } else if (row == 11) {
                        caracteristic.setLoopCondition((String) value);
                    } else if (row == 12) {
                        caracteristic.setLoopMaximum((String) value);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 10) {
                        caracteristic.setBehavior((MultiInstanceBehavior) value);
                    } else if (row == 11) {
                        caracteristic.setLoopCardinality((String) value);
                    } else if (row == 12) {
                        caracteristic.setCompletionCondition((String) value);
                    } else if (row == 13) {
                        caracteristic.setCompletionEventRef((BpmnEventDefinition) value);
                    }
                }
            }
        }
    }

}
