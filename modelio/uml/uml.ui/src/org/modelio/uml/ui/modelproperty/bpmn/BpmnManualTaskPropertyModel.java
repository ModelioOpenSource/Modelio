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
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
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
 * <i>BpmnManualTask</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnManualTask</i>
 * metaclass.
 */
@objid ("fef31dfc-81c7-41ab-a993-7a9e621324b7")
public class BpmnManualTaskPropertyModel extends AbstractPropertyModel<BpmnManualTask> {
    @objid ("16ed9393-f1ac-4db0-838a-31f6fdb35e47")
    private List<String> properties = new ArrayList<>();

    @objid ("47235ff5-c91c-49de-bb5a-eb3f566d74b0")
    private IMModelServices modelService;

    /**
     * Create a new <i>BpmnManualTask</i> data model from an
     * <i>BpmnManualTask</i>.
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     */
    @objid ("382475ab-ebbd-4800-a122-ab14b20de5c3")
    public  BpmnManualTaskPropertyModel(BpmnManualTask theEditedElement, IMModelServices modelService) {
        super(theEditedElement);
        this.modelService = modelService;
        initPropertyModel();
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("122c39c0-a689-47f7-94e1-4339bec77903")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("62e25d8b-1e74-443c-8eea-cb95f4cb0236")
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
    @objid ("f4d81aeb-b50d-4c85-a9e0-a84cf9a5e909")
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
                return LoopType.getType(this.theEditedElement);
            } else if (row > 6) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 7) {
                        return caracteristic.isTestBefore() ? Boolean.TRUE : Boolean.FALSE;
                    } else if (row == 8) {
                        return caracteristic.getLoopCondition();
                    } else if (row == 9) {
                        return caracteristic.getLoopMaximum();
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 7) {
                        return caracteristic.getBehavior();
                    } else if (row == 8) {
                        return caracteristic.getLoopCardinality();
                    } else if (row == 9) {
                        return caracteristic.getCompletionCondition();
                    } else if (row == 10) {
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
    @objid ("bd158dde-0fdb-4cee-a307-e2358d9b80ae")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), LoopType.class);
            } else if (row > 6) {
                if (type == LoopType.Standard) {
                    if (row == 7) {
                        return new DefaultBooleanNatValue((Boolean) getValue(row, col));
                    } else if (row == 8) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 9) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    if (row == 7) {
                        return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), MultiInstanceBehavior.class);
                    } else if (row == 8) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 9) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 10) {
                        return new DefaultElementNatValue((MObject) getValue(row, col), true,
                                Collections.singletonList(BpmnEventDefinition.class));
                    }
                }
            }
        }
        return null;
    }

    @objid ("37c690bd-aec8-41b2-8269-79c258450e0e")
    private void initPropertyModel() {
        this.properties.clear();
        this.properties.add("ManualTask");
        this.properties.add("Name");
        this.properties.add("ForCompensation");
        this.properties.add("StartQuantity");
        this.properties.add("CompletionQuantity");
        this.properties.add("Global");
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
    @objid ("c52d2973-c1e5-4bf6-b5b3-3940f3c63f54")
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
                LoopType.setType(this.modelService, (LoopType) value, this.theEditedElement);
                initPropertyModel();
            } else if (row > 6) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 7) {
                        caracteristic.setTestBefore((Boolean) value);
                    } else if (row == 8) {
                        caracteristic.setLoopCondition((String) value);
                    } else if (row == 9) {
                        caracteristic.setLoopMaximum((String) value);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 7) {
                        caracteristic.setBehavior((MultiInstanceBehavior) value);
                    } else if (row == 8) {
                        caracteristic.setLoopCardinality((String) value);
                    } else if (row == 9) {
                        caracteristic.setCompletionCondition((String) value);
                    } else if (row == 10) {
                        caracteristic.setCompletionEventRef((BpmnEventDefinition) value);
                    }
                }
            }
        }
        
    }

}
