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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ModelioPrimitiveTypeMapper;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.StringConverter;

/**
 * This class manages the export of Parameter elements
 * 
 * @author ebrosse
 */
@objid ("95a1771b-7243-445a-b042-82eb0da1ee35")
public class OParameter extends OModelElement {
    @objid ("ea27ece0-af77-48a0-9c67-4e141a4001bd")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLASSIFIERTEMPLATEPARAMETER)) {
            return UMLFactory.eINSTANCE.createClassifierTemplateParameter();
        } else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OPERATIONTEMPLATEPARAMETER)) {
            return UMLFactory.eINSTANCE.createOperationTemplateParameter();
        } else {
            return UMLFactory.eINSTANCE.createParameter();
        }
    }

    /**
     * Constructor
     * 
     * @param element : the exported Modelio Parameter
     */
    @objid ("86d59a04-e0e4-464e-9db7-3c7df19e831a")
    public OParameter(final Parameter element) {
        super(element);
    }

    @objid ("876f56a6-3b18-4f7f-8caa-a216830ec030")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingParent = getObjingElement().getCompositionOwner();
        
        org.eclipse.uml2.uml.Element ecoreParent = GenerationProperties.getInstance().getMappedElement(objingParent);
        
        if (ecoreParent != null) {
            if (objingParent instanceof Operation) {
                if (ecoreParent instanceof org.eclipse.uml2.uml.Operation) {
                    org.eclipse.uml2.uml.Operation ecoreOperation = (org.eclipse.uml2.uml.Operation) ecoreParent;
                    ecoreOperation.getOwnedParameters().add((org.eclipse.uml2.uml.Parameter) ecoreElt);
                } else if (ecoreParent instanceof org.eclipse.uml2.uml.Reception) {
                    org.eclipse.uml2.uml.Reception ecoreOperation = (org.eclipse.uml2.uml.Reception) ecoreParent;
                    ecoreOperation.getOwnedParameters().add((org.eclipse.uml2.uml.Parameter) ecoreElt);
                }
            }
        }
    }

    @objid ("aa5d8023-6502-4298-8b04-dc4c45971d5e")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!AbstractObjingModelNavigation
                .isReturnParameter(getObjingElement())) {
            super.setProperties(ecoreElt);
        }
        
        setParameterPassingMode((org.eclipse.uml2.uml.Parameter) ecoreElt);
        setMin((org.eclipse.uml2.uml.Parameter) ecoreElt);
        setMax((org.eclipse.uml2.uml.Parameter) ecoreElt);
        setDefaultValue((org.eclipse.uml2.uml.Parameter) ecoreElt);
        setClass((org.eclipse.uml2.uml.Parameter) ecoreElt);
        setOrdered((org.eclipse.uml2.uml.Parameter) ecoreElt);
        setUnique((org.eclipse.uml2.uml.Parameter) ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setTypeConstraintEAnnotation((org.eclipse.uml2.uml.Parameter) ecoreElt);
        }
    }

    @objid ("efe75c3a-2dde-4c2c-af3b-18af3a175b23")
    private void setParameterPassingMode(org.eclipse.uml2.uml.Parameter ecoreElt) {
        switch (getObjingElement().getParameterPassing()) {
        case IN:
            ecoreElt.setDirection(org.eclipse.uml2.uml.ParameterDirectionKind.IN_LITERAL);
            break;
        case OUT:
            ecoreElt.setDirection(org.eclipse.uml2.uml.ParameterDirectionKind.OUT_LITERAL);
            break;
        case INOUT:
            ecoreElt.setDirection(org.eclipse.uml2.uml.ParameterDirectionKind.INOUT_LITERAL);
            break;
        default:
            break;
        }
        if (AbstractObjingModelNavigation.isReturnParameter(getObjingElement())) {
            ecoreElt.setDirection(org.eclipse.uml2.uml.ParameterDirectionKind.RETURN_LITERAL);
        }
    }

    @objid ("3ff0904c-43fd-4b69-b1d7-575ca4f6eea9")
    private void setMin(org.eclipse.uml2.uml.Parameter ecoreElt) {
        String objingMultMin = getObjingElement().getMultiplicityMin();
        
        // If objingMultMin is "" then we don't set a lower multiplicity for the
        // UML2 element.
        if (!"".equals(objingMultMin)) {
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE
                    .equals(objingMultMin)) {
                ecoreElt.setLower(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            } else {
                StringConverter.setFilterEnabled(true);
                Integer min = StringConverter.getInteger(objingMultMin);
                if (min != null) {
                    ecoreElt.setLower(min);
                } else {
                    String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.multiplicityMinWithName", "Parameter", ecoreElt.getName());
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMin + "\"", "Integer", contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, getObjingElement());
                }
            }
        }
    }

    @objid ("ecc62ff1-aa5e-4cd3-bac4-8ce55912c822")
    private void setMax(org.eclipse.uml2.uml.Parameter ecoreElt) {
        String objingMultMax = getObjingElement().getMultiplicityMax();
        
        // If objingMultMax is "" then we don't set an upper multiplicity for
        // the UML2 element.
        if (!"".equals(objingMultMax)) {
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE
                    .equals(objingMultMax)) {
                ecoreElt.setUpper(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            } else {
                StringConverter.setFilterEnabled(true);
                Integer max = StringConverter.getInteger(objingMultMax);
                if (max != null) {
                    ecoreElt.setUpper(max);
                } else {
                    String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.multiplicityMaxWithName", "Parameter", ecoreElt.getName());
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMax + "\"", "Integer", contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, getObjingElement());
                }
            }
        }
    }

    @objid ("c6a498e9-5c77-4aa7-8548-9d9deed0e819")
    private void setTypeConstraintEAnnotation(org.eclipse.uml2.uml.Parameter ecoreElt) {
        ObjingEAnnotation.setTypeConstraint(ecoreElt, getObjingElement()
                .getTypeConstraint());
    }

    @objid ("4ae2e684-e61f-4539-971e-d59201a8e99d")
    private void setDefaultValue(org.eclipse.uml2.uml.Parameter ecoreParam) {
        if (AbstractObjingModelNavigation.haveInstanceValue(getObjingElement())) {
            InstanceValue value = UMLFactory.eINSTANCE.createInstanceValue();
            InstanceSpecification inst = (InstanceSpecification) GenerationProperties.getInstance().getMappedElement(
                    AbstractObjingModelNavigation.getInstanceValue(getObjingElement()));
            value.setInstance(inst);
            ecoreParam.setDefaultValue(value);
        
        } else {
        
            String objingDefaultValue = getObjingElement().getDefaultValue();
            GeneralClass objingType = getObjingElement().getType();
        
            IUMLTypes umlTypes = GenerationProperties.getInstance().getModelioTypes();
            // If objingValue is "" then we don't set a default value for the UML2
            // element.
            if (!("".equals(objingDefaultValue))) {
                if (objingType != null) {
                    if (ModelioPrimitiveTypeMapper.isPredefinedType(objingType)) {
        
                        DataType objingPredefinedType = (DataType) objingType;
        
                        if ((AbstractObjingModelNavigation.OBJING_NULL_VALUE != null)
                                && (AbstractObjingModelNavigation.OBJING_NULL_VALUE.equals(objingDefaultValue.toLowerCase()))) {
                            ecoreParam.setNullDefaultValue();
                        } else if ((umlTypes.getBOOLEAN() != null) && (umlTypes.getBOOLEAN().equals(objingPredefinedType))) {
                            // If the attribute is a Dynamic org.eclipse.uml2.uml.Dependency, we don't enable
                            // string filters:
                            StringConverter.setFilterEnabled(true);
                            Boolean bool = StringConverter
                                    .getBoolean(objingDefaultValue);
                            if (bool != null) {
                                ecoreParam.setBooleanDefaultValue(bool);
                            } else {
                                String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultValue", getObjingElement().getName(), "Parameter");
                                String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingDefaultValue + "\"", "Boolean", contextualMsg);
                                GenerationProperties.getInstance().addInfo(contextualMsg, getObjingElement(), message);
                                ecoreParam.setStringDefaultValue(objingDefaultValue);
                            }
                        } else if ((umlTypes.getCHAR() != null) && (umlTypes.getCHAR().equals(objingPredefinedType))) {
                            ecoreParam.setStringDefaultValue(objingDefaultValue);
                        } else if ((umlTypes.getSTRING() != null) && (umlTypes.getSTRING().equals(objingPredefinedType))) {
        
                            ecoreParam.setStringDefaultValue(objingDefaultValue);
                        } else if ((umlTypes.getINTEGER() != null) && (umlTypes.getINTEGER().equals(objingPredefinedType))) {
                            // If the attribute is a Dynamic org.eclipse.uml2.uml.Dependency, we don't enable
                            // string filters:
                            StringConverter.setFilterEnabled(true);
                            Integer objingIntValue = StringConverter
                                    .getInteger(objingDefaultValue);
                            if (objingIntValue != null) {
                                if (objingIntValue >= 0) {
                                    ecoreParam
                                    .setUnlimitedNaturalDefaultValue(objingIntValue);
                                } else {
                                    ecoreParam.setIntegerDefaultValue(objingIntValue);
                                }
                            } else {
                                String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultValue", getObjingElement().getName(), "Parameter");
                                String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingDefaultValue + "\"", "Integer", contextualMsg);
                                GenerationProperties.getInstance().addInfo(contextualMsg, getObjingElement(), message);
                                ecoreParam.setStringDefaultValue(objingDefaultValue);
                            }
                        } else {
                            ecoreParam.setStringDefaultValue(objingDefaultValue);
                        }
                    } else if ((objingType instanceof Enumeration)
                            && (AbstractObjingModelNavigation.isEnumerationliteral((Enumeration) objingType, objingDefaultValue))) {
        
                        InstanceValue value = UMLFactory.eINSTANCE.createInstanceValue();
        
                        Object ecoreType = GenerationProperties.getInstance().getMappedElement(objingType);
                        if (ecoreType instanceof org.eclipse.uml2.uml.Type) {
                            value.setType((org.eclipse.uml2.uml.Type) ecoreType);
                        }
        
                        Object ecoreInstance = GenerationProperties.getInstance().getMappedElement(AbstractObjingModelNavigation.getEnumerationliteral((Enumeration) objingType, objingDefaultValue));
                        if (ecoreInstance instanceof InstanceSpecification) {
                            value.setInstance((InstanceSpecification) ecoreInstance);
                        }
        
                        ecoreParam.setDefaultValue(value);
                    } else {
                        ecoreParam.setStringDefaultValue(objingDefaultValue);
                    }
                } else {
                    ecoreParam.setStringDefaultValue(objingDefaultValue);
                }
            } else {
                ecoreParam.setStringDefaultValue(objingDefaultValue);
            }
        }
    }

    @objid ("c06cd659-472c-4cfd-88b0-28a5ac56275d")
    private void setClass(org.eclipse.uml2.uml.Parameter ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        // Getting type of the org.eclipse.uml2.uml.Parameter:
        GeneralClass objingType = getObjingElement().getType();
        if (objingType != null) {
            if (ModelioPrimitiveTypeMapper.isPredefinedType(objingType)) {
                ModelioPrimitiveTypeMapper.setEcorePredefinedType(ecoreElt, (DataType) objingType);
            } else {
        
                if (objingType instanceof TemplateParameter) {
                    return;
        
                } else {
                    org.eclipse.uml2.uml.Element ecoreEltType = genProp.getMappedElement(objingType);
        
                    if (ecoreEltType instanceof org.eclipse.uml2.uml.Type) {
                        setType(ecoreElt, (org.eclipse.uml2.uml.Type) ecoreEltType);
                    }
                }
            }
        } else {
            ObjingEAnnotation.setIsNoType(ecoreElt);
        }
    }

    @objid ("fac8013b-cabd-49d9-b4b3-557e72d38699")
    private void setType(org.eclipse.uml2.uml.Parameter ecoreParam, org.eclipse.uml2.uml.Type type) {
        ecoreParam.setType(type);
    }

    @objid ("1123e756-93bb-4051-8829-3ac6e3348e44")
    private void setUnique(final org.eclipse.uml2.uml.Parameter ecoreElt) {
        ecoreElt.setIsUnique(getObjingElement().isIsUnique());
    }

    @objid ("f4d2c4d6-b718-4bb6-8f18-523fcdaa9120")
    private void setOrdered(final org.eclipse.uml2.uml.Parameter ecoreElt) {
        ecoreElt.setIsOrdered(getObjingElement().isIsOrdered());
    }

    @objid ("71208c5e-10ee-486d-bd0e-0c3bc07889f7")
    @Override
    public Parameter getObjingElement() {
        return (Parameter) super.getObjingElement();
    }

}
