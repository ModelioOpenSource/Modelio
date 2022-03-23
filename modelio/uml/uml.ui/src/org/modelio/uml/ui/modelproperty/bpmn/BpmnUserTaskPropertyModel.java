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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnUserTask</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnUserTask</i>
 * metaclass.
 */
@objid ("04c61af6-cbae-420e-b5ea-f4b446247d6a")
public class BpmnUserTaskPropertyModel extends AbstractPropertyModel<BpmnUserTask> {
    @objid ("cb8405d9-77d7-4406-81be-445b6ec655a0")
    private List<String> properties = new ArrayList<>();

    @objid ("b1a968b5-312d-4bca-8d37-76e5fc89f50b")
    private IMModelServices modelService;

    /**
     * Create a new <i>BpmnUserTask</i> data model from an <i>BpmnUserTask</i>.
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("b3be86b0-2e9f-4fc2-95cf-3bf76f0baa5f")
    public  BpmnUserTaskPropertyModel(BpmnUserTask theEditedElement, IMModelServices modelService) {
        super(theEditedElement);
        this.modelService = modelService;
        initPropertyModel();
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("c5f86f8a-9a6d-4671-87b5-a9da9fef5954")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("5f67d608-8b8f-4658-b2f3-266a08b6abf5")
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
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("f6d81e45-8495-4fbb-9337-609101de3a73")
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
                return this.theEditedElement.isIsGlobal() ? Boolean.TRUE : Boolean.FALSE;
            } else if (row == 6) {
                return this.theEditedElement.getImplementation();
            } else if (row == 7) {
                return LoopType.getType(this.theEditedElement);
            } else if (row > 7) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 8) {
                        return caracteristic.isTestBefore() ? Boolean.TRUE : Boolean.FALSE;
                    } else if (row == 9) {
                        return caracteristic.getLoopCondition();
                    } else if (row == 10) {
                        return caracteristic.getLoopMaximum();
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 8) {
                        return caracteristic.getBehavior();
                    } else if (row == 9) {
                        return caracteristic.getLoopCardinality();
                    } else if (row == 10) {
                        return caracteristic.getCompletionCondition();
                    } else if (row == 11) {
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("65b105ed-a855-4a99-872a-f2a3236ea702")
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
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else if (row == 7) {
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), LoopType.class);
            } else if (row > 7) {
                if (type == LoopType.Standard) {
                    if (row == 8) {
                        return new DefaultBooleanNatValue((Boolean) getValue(row, col));
                    } else if (row == 9) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 10) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    if (row == 8) {
                        return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), MultiInstanceBehavior.class);
                    } else if (row == 9) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 10) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 11) {
                        return new DefaultElementNatValue((MObject) getValue(row, col), true,
                                Collections.singletonList(BpmnEventDefinition.class));
                    }
                }
            }
        }
        return null;
    }

    @objid ("eb8817f7-6d6d-4f92-a4e2-eb64d03c8b2f")
    private void initPropertyModel() {
        this.properties.clear();
        this.properties.add("UserTask");
        this.properties.add("Name");
        this.properties.add("ForCompensation");
        this.properties.add("StartQuantity");
        this.properties.add("CompletionQuantity");
        this.properties.add("Global");
        this.properties.add("Implementation");
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("0da6d5ea-bda7-4170-ac00-5bac6a28f8b2")
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
                this.theEditedElement.setIsGlobal((Boolean) value);
            } else if (row == 6) {
                this.theEditedElement.setImplementation((String) value);
            } else if (row == 7) {
                LoopType.setType(this.modelService, (LoopType) value, this.theEditedElement);
                initPropertyModel();
            } else if (row > 7) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 8) {
                        caracteristic.setTestBefore((Boolean) value);
                    } else if (row == 9) {
                        caracteristic.setLoopCondition((String) value);
                    } else if (row == 10) {
                        caracteristic.setLoopMaximum((String) value);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 8) {
                        caracteristic.setBehavior((MultiInstanceBehavior) value);
                    } else if (row == 9) {
                        caracteristic.setLoopCardinality((String) value);
                    } else if (row == 10) {
                        caracteristic.setCompletionCondition((String) value);
                    } else if (row == 11) {
                        caracteristic.setCompletionEventRef((BpmnEventDefinition) value);
                    }
                }
            }
        }
        
    }

}
