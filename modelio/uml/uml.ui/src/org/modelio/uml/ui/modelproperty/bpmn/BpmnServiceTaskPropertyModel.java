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
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnServiceTask</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnServiceTask</i> metaclass.
 */
@objid ("74e8778d-2ff6-4520-90ac-c7606990680e")
public class BpmnServiceTaskPropertyModel extends AbstractPropertyModel<BpmnServiceTask> {
    @objid ("03ebd1ad-4a00-45c0-bdde-334f5b64b8f5")
    private List<String> properties = new ArrayList<>();

    @objid ("f813ffe8-85d7-49cb-be0b-b1d2e2018a13")
    private IMModelServices modelService;

    @objid ("b2b7117a-7384-4f2e-be8e-84d4008f0580")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnServiceTask</i> data model from an <i>BpmnServiceTask</i>.
     * @param theEditedElement the model to edit.
     * @param modelService the model service needed to find elements.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("bcd01578-115a-4348-89a6-33ca1bd1b31b")
    public  BpmnServiceTaskPropertyModel(BpmnServiceTask theEditedElement, IMModelServices modelService, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.modelService = modelService;
        this.mdaExpert = mdaExpert;
        initPropertyModel();
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("9dfa31ed-423e-4054-891d-6a0696fc3f24")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("43c5d48c-5f06-4139-89ad-524fd14155b6")
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
    @objid ("22762349-ff24-4525-83a0-0bbe19629411")
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
                return Called.getTarget(this.theEditedElement);
            } else if (row == 8) {
                return LoopType.getType(this.theEditedElement);
            } else if (row > 8) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 9) {
                        return caracteristic.isTestBefore() ? Boolean.TRUE : Boolean.FALSE;
                    } else if (row == 10) {
                        return caracteristic.getLoopCondition();
                    } else if (row == 11) {
                        return caracteristic.getLoopMaximum();
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 9) {
                        return caracteristic.getBehavior();
                    } else if (row == 10) {
                        return caracteristic.getLoopCardinality();
                    } else if (row == 11) {
                        return caracteristic.getCompletionCondition();
                    } else if (row == 12) {
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
     * This type will be used to choose an editor and a renderer for each cell of the properties table.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("8b8899b3-9923-4039-95b6-76533661cffe")
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
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Called.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnServiceTaskPropertyModel.this.mdaExpert.canLink(Called.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnServiceTaskPropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
            } else if (row == 8) {
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), LoopType.class);
            } else if (row > 8) {
                if (type == LoopType.Standard) {
                    if (row == 9) {
                        return new DefaultBooleanNatValue((Boolean) getValue(row, col));
                    } else if (row == 10) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 11) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    if (row == 9) {
                        return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), MultiInstanceBehavior.class);
                    } else if (row == 10) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 11) {
                        return new DefaultStringNatValue((String) getValue(row, col), false);
                    } else if (row == 12) {
                        return new DefaultElementNatValue((MObject) getValue(row, col), true,
                                Collections.singletonList(BpmnEventDefinition.class));
                    }
                }
            }
        }
        return null;
    }

    @objid ("320ea6a0-fe19-4f55-a3e1-65e27d56dbff")
    private void initPropertyModel() {
        this.properties.clear();
        this.properties.add("ServiceTask");
        this.properties.add("Name");
        this.properties.add("ForCompensation");
        this.properties.add("StartQuantity");
        this.properties.add("CompletionQuantity");
        this.properties.add("Global");
        this.properties.add("Implementation");
        this.properties.add("Operation");
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
    @objid ("08ea5038-1ffa-4e14-a524-0ae6216dd4bc")
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
                Called.setTarget(this.theEditedElement, (ModelElement) value);
            } else if (row == 8) {
                LoopType.setType(this.modelService, (LoopType) value, this.theEditedElement);
                initPropertyModel();
            } else if (row > 8) {
                if (type == LoopType.Standard) {
                    BpmnStandardLoopCharacteristics caracteristic = (BpmnStandardLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 9) {
                        caracteristic.setTestBefore((Boolean) value);
                    } else if (row == 10) {
                        caracteristic.setLoopCondition((String) value);
                    } else if (row == 11) {
                        caracteristic.setLoopMaximum((String) value);
                    }
                } else if (type == LoopType.MultiInstanceParallel || type == LoopType.MultiInstanceSequential) {
                    BpmnMultiInstanceLoopCharacteristics caracteristic = (BpmnMultiInstanceLoopCharacteristics) this.theEditedElement
                            .getLoopCharacteristics();
                    if (row == 9) {
                        caracteristic.setBehavior((MultiInstanceBehavior) value);
                    } else if (row == 10) {
                        caracteristic.setLoopCardinality((String) value);
                    } else if (row == 11) {
                        caracteristic.setCompletionCondition((String) value);
                    } else if (row == 12) {
                        caracteristic.setCompletionEventRef((BpmnEventDefinition) value);
                    }
                }
            }
        }
        
    }

}
