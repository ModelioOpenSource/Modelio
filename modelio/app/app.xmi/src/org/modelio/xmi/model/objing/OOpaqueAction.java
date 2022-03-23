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
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("cd9144df-04f4-412a-8b79-44ecbc476562")
public class OOpaqueAction extends OActivityNode {
    @objid ("9e40d7df-8233-4c6b-b58f-4417907425fc")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2VALUESPECIFICATIONACTION))
            return UMLFactory.eINSTANCE.createValueSpecificationAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CREATEOBJECTACTION))
            return UMLFactory.eINSTANCE.createCreateObjectAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CREATELINKACTION))
            return UMLFactory.eINSTANCE.createCreateLinkAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READLINKACTION))
            return UMLFactory.eINSTANCE.createReadLinkAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DESTROYLINKACTION))
            return UMLFactory.eINSTANCE.createDestroyLinkAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLEARASSOCIATIONACTION))
            return UMLFactory.eINSTANCE.createClearAssociationAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ADDSTRUCTURALFEATUREVALUEACTION))
            return UMLFactory.eINSTANCE.createAddStructuralFeatureValueAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READSTRUCTURALFEATUREACTION))
            return UMLFactory.eINSTANCE.createReadStructuralFeatureAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READEXTENTACTION))
            return UMLFactory.eINSTANCE.createReadExtentAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DESTROYOBJECTACTION))
            return UMLFactory.eINSTANCE.createDestroyObjectAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ADDVARIABLEVALUEACTION))
            return UMLFactory.eINSTANCE.createAddVariableValueAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLEARSTRUCTURALFEATUREACTION))
            return UMLFactory.eINSTANCE.createClearStructuralFeatureAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLEARVARIABLEACTION))
            return UMLFactory.eINSTANCE.createClearVariableAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RAISEEXCEPTIONACTION))
            return UMLFactory.eINSTANCE.createRaiseExceptionAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READISCLASSIFIEROBJECTACTION))
            return UMLFactory.eINSTANCE.createReadIsClassifiedObjectAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READLINKOBJECTENDACTION))
            return UMLFactory.eINSTANCE.createReadLinkObjectEndAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READLINKOBJECTENDQUALIFIERACTION))
            return UMLFactory.eINSTANCE.createReadLinkObjectEndQualifierAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READSELFACTION))
            return UMLFactory.eINSTANCE.createReadSelfAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READVARIABLEACTION))
            return UMLFactory.eINSTANCE.createReadVariableAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RECLASSIFYOBJECTACTION))
            return UMLFactory.eINSTANCE.createReclassifyObjectAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REDUCEACTION))
            return UMLFactory.eINSTANCE.createReduceAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REMOVESTRUCTURALFEATUREACTION))
            return UMLFactory.eINSTANCE.createRemoveStructuralFeatureValueAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REMOVEVARIABLEVALUEACTION))
            return UMLFactory.eINSTANCE.createRemoveVariableValueAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REPLYACTION))
            return UMLFactory.eINSTANCE.createReplyAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2SENDOBJECTACTION))
            return UMLFactory.eINSTANCE.createSendObjectAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2STARTCLASSIFIERBEHAVIORACTION))
            return UMLFactory.eINSTANCE.createStartClassifierBehaviorAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TESTIDENTITYACTION))
            return UMLFactory.eINSTANCE.createTestIdentityAction();
        else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2UNMARSHALLACTION))
            return UMLFactory.eINSTANCE.createUnmarshallAction();
        else
            return UMLFactory.eINSTANCE.createOpaqueAction();
        
    }

    @objid ("d689dec3-c673-404b-92da-1b0629dd7bf1")
    public  OOpaqueAction(OpaqueAction element) {
        super(element);
    }

    @objid ("09cce92c-c9fb-44a4-b0a5-adc116285745")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.OpaqueAction){
            setBody((org.eclipse.uml2.uml.OpaqueAction) ecoreElt);
        }else if (ecoreElt instanceof  org.eclipse.uml2.uml.ValueSpecificationAction){
            setValue( (org.eclipse.uml2.uml.ValueSpecificationAction) ecoreElt);
        }else if (ecoreElt instanceof org.eclipse.uml2.uml.CreateObjectAction){
            setClassifier((org.eclipse.uml2.uml.CreateObjectAction) ecoreElt);
        }else if (ecoreElt instanceof  org.eclipse.uml2.uml.ReadExtentAction){
            setClassifier( (org.eclipse.uml2.uml.ReadExtentAction) ecoreElt);
        }else if (ecoreElt instanceof  org.eclipse.uml2.uml.ClearAssociationAction){
            setAssociation( (org.eclipse.uml2.uml.ClearAssociationAction) ecoreElt);
        }else if (ecoreElt instanceof org.eclipse.uml2.uml.StructuralFeatureAction){
            setFeature((org.eclipse.uml2.uml.StructuralFeatureAction) ecoreElt);
        }
        
    }

    @objid ("5cdd4c4f-daa8-43e0-a932-e71094e9ad70")
    private void setBody(org.eclipse.uml2.uml.OpaqueAction action) {
        action.getBodies().add(getObjingElement().getBody());
    }

    @objid ("07fb8d53-8f57-446b-83d8-615697e40e4c")
    private void setValue(org.eclipse.uml2.uml.ValueSpecificationAction ecoreElt) {
        if (getObjingElement().getOutput().size() > 0){
        
            GeneralClass primitiveType = getObjingElement().getOutput().get(0).getType();
        
            IUMLTypes umlTypes = GenerationProperties.getInstance().getModelioTypes();
            DataType  obINTEGER= umlTypes.getINTEGER();
            DataType obSTRING = umlTypes.getSTRING();
        
            String tagValue = "";
            for (TaggedValue tag : getObjingElement().getTag()){
                if (tag.getDefinition().getName().equals("Value")){
                    for (TagParameter params : tag.getActual()){
                        tagValue = params.getValue();
                        break;
                    }
                }
            }
        
            if (!tagValue.equals("")){
                
                if ((obINTEGER != null) &&  obINTEGER.equals(primitiveType)){
                    try{
                        LiteralInteger literal = UMLFactory.eINSTANCE.createLiteralInteger();
                        literal.setValue(Integer.valueOf(tagValue));
                        ecoreElt.setValue(literal);
                    }catch(NumberFormatException e){
                        Xmi.LOG.warning(e);
                        org.eclipse.uml2.uml.LiteralString literal = UMLFactory.eINSTANCE.createLiteralString();
                        literal.setValue(tagValue);
                        ecoreElt.setValue(literal);
                    }
        
        
                } else if ((obSTRING != null) &&  obSTRING.equals(primitiveType)){
                    org.eclipse.uml2.uml.LiteralString literal = UMLFactory.eINSTANCE.createLiteralString();
                    literal.setValue(tagValue);
                    ecoreElt.setValue(literal);
                }else{
                     org.eclipse.uml2.uml.OpaqueExpression opaqueExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
                    opaqueExpression.getBodies().add(tagValue);
                    ecoreElt.setValue(opaqueExpression);
                }
            }
        }
        
    }

    @objid ("07817664-0145-46b3-9885-c52b383500b8")
    private void setFeature(org.eclipse.uml2.uml.StructuralFeatureAction ecoreElt) {
        for (Dependency depend : getObjingElement().getDependsOnDependency()){
            if (depend.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2STRUCTURALFEATUREREFERENCE)){
                org.eclipse.uml2.uml.Element structuralFeature = GenerationProperties.getInstance().getMappedElement(depend.getDependsOn());
                if (structuralFeature instanceof org.eclipse.uml2.uml.StructuralFeature){
                    ecoreElt.setStructuralFeature((org.eclipse.uml2.uml.StructuralFeature)structuralFeature);
                }
            }
        }
        
    }

    @objid ("11efb652-28f1-4519-b506-1705c74ca03d")
    private void setAssociation(org.eclipse.uml2.uml.ClearAssociationAction ecoreElt) {
        for (Dependency depend : getObjingElement().getDependsOnDependency()){
            if (depend.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ASSOCIATIONREFERENCE)){
                org.eclipse.uml2.uml.Element classifier = GenerationProperties.getInstance().getMappedElement(depend.getDependsOn());
                if (classifier instanceof org.eclipse.uml2.uml.Association){
                    ecoreElt.setAssociation((org.eclipse.uml2.uml.Association)classifier);
                }
            }
        }
        
    }

    @objid ("9310efce-052b-4a34-ad5e-726044f7f48b")
    private void setClassifier(org.eclipse.uml2.uml.ReadExtentAction ecoreElt) {
        for (Dependency depend : getObjingElement().getDependsOnDependency()){
            if (depend.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLASSIFIERREFERENCE)){
                org.eclipse.uml2.uml.Element classifier = GenerationProperties.getInstance().getMappedElement(depend.getDependsOn());
                if (classifier instanceof org.eclipse.uml2.uml.Classifier){
                    ecoreElt.setClassifier( (org.eclipse.uml2.uml.Classifier)classifier);
                }
            }
        }
        
    }

    @objid ("41c57fa9-1193-439a-9be9-a4ad13601473")
    private void setClassifier(org.eclipse.uml2.uml.CreateObjectAction ecoreElt) {
        for (Dependency depend : getObjingElement().getDependsOnDependency()){
            if (depend.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLASSIFIERREFERENCE)){
                org.eclipse.uml2.uml.Element classifier = GenerationProperties.getInstance().getMappedElement(depend.getDependsOn());
                if (classifier instanceof org.eclipse.uml2.uml.Classifier){
                    ecoreElt.setClassifier( (org.eclipse.uml2.uml.Classifier)classifier);
                }
            }
        }
        
    }

    @objid ("7d154d94-eeaa-4b1e-a7a4-f86b0eb04a71")
    @Override
    public OpaqueAction getObjingElement() {
        return (OpaqueAction) super.getObjingElement();
    }

}
