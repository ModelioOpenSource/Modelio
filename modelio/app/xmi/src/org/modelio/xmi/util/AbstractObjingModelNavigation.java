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

package org.modelio.xmi.util;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * Services for the Modelio model navigation
 * @author ebrosse
 */
@objid ("8e24e53e-1671-4da2-a0e7-d4d3a44e3859")
public abstract class AbstractObjingModelNavigation {
    @objid ("52436fc9-99ef-45db-a039-427697f1a640")
    public static final String OBJING_NULL_VALUE = "null";

    @objid ("35d091b4-457b-4830-a047-08ad0524fcf1")
    public static final String OBJING_UNLIMITED_VALUE = "*";

    @objid ("817b0c5e-45ec-4794-a386-d94285139660")
    public static DataType getPrimitiveType(String ecoreTypeName) {
        if (ecoreTypeName != null) {
            IUMLTypes umlTypes = Modelio.getInstance().getModelingSession()
                    .getModel().getUmlTypes();
            DataType obBOOLEAN, obBYTE, obCHAR, obDATE, obDOUBLE, obFLOAT, obSHORT, obINTEGER, obLONG, obSTRING, obUNDEFINED;
        
            obBOOLEAN = umlTypes.getBOOLEAN();
            obBYTE = umlTypes.getBYTE();
            obCHAR = umlTypes.getCHAR();
            obINTEGER = umlTypes.getINTEGER();
            obDATE = umlTypes.getDATE();
            obDOUBLE = umlTypes.getDOUBLE();
            obFLOAT = umlTypes.getFLOAT();
            obLONG = umlTypes.getLONG();
            obSHORT = umlTypes.getSHORT();
            obSTRING = umlTypes.getSTRING();
            obUNDEFINED = umlTypes.getUNDEFINED();
        
            if ((obBOOLEAN != null) && (ecoreTypeName.equals(obBOOLEAN.getName()))){
                return obBOOLEAN;
            }else if  ((obCHAR != null) &&(ecoreTypeName.equals(obCHAR.getName()))){
                return obCHAR;
            }else if ((obINTEGER != null) && (ecoreTypeName.equals(obINTEGER.getName()))){
                return obINTEGER;
            }else if ((obBYTE != null) &&(ecoreTypeName.equals(obBYTE.getName()))){
                return obBYTE;
            }else if ((obDATE != null) &&(ecoreTypeName.equals(obDATE.getName()))){
                return obDATE;
            }else if ((obDOUBLE != null) &&(ecoreTypeName.equals(obDOUBLE.getName()))){
                return obDOUBLE;
            }else if ((obFLOAT != null) &&(ecoreTypeName.equals(obFLOAT.getName()))){
                return obFLOAT;
            }else if ((obFLOAT != null) &&(ecoreTypeName.equals("real"))){
                return obFLOAT;
            }else if ((obLONG != null) &&(ecoreTypeName.equals(obLONG.getName()))){
                return obLONG;
            }else if ((obSHORT != null) &&(ecoreTypeName.equals(obSHORT.getName()))){
                return obSHORT;
            }else if ((obSTRING != null) &&(ecoreTypeName.equals(obSTRING.getName()))){
                return obSTRING;
            }else if ((obUNDEFINED != null) &&(ecoreTypeName.equals(obUNDEFINED.getName()))){
                return obUNDEFINED;
            }
        }
        return null;
    }

    @objid ("160aa3a6-48e8-428b-9096-7d80a945940c")
    public static boolean isCreate(Operation operation) {
        return isStereotyped(operation, Xmi.I18N.getString("objing.java.stereotype.constructor"));
    }

    @objid ("b51df578-6c62-463e-9008-df45b4781f5a")
    public static boolean isDestroy(Operation operation) {
        return isStereotyped(operation, Xmi.I18N.getString("objing.java.stereotype.destructor"));
    }

    @objid ("a6c1b084-7e85-4e8e-9e72-d53d76db6e34")
    public static Operation getOwnerOperation(Parameter parameter) {
        Operation operation = parameter.getComposed();
        if (operation == null){
            operation = parameter.getReturned();
        }
        return operation;
    }

    @objid ("36d7880c-40f5-4035-8b02-1d510925e986")
    public static boolean isReturnParameter(Parameter parameter) {
        return ((parameter != null) &&  (parameter.getReturned() != null));
    }

    @objid ("b3d96641-cfcb-49da-a6c3-ded760605a37")
    public static UmlModelElement getImportingElement(UmlModelElement mdlEltImport) {
        UmlModelElement importingElt = null;
        if (mdlEltImport != null) {
            if (mdlEltImport instanceof ElementImport) {
                ElementImport eltImport = (ElementImport) mdlEltImport;
                importingElt = eltImport.getImportingOperation();
                if (importingElt == null)
                    importingElt = eltImport.getImportingNameSpace();
            } else if (mdlEltImport instanceof PackageImport) {
                PackageImport pkgImport = (PackageImport) mdlEltImport;
                importingElt = pkgImport.getImportingOperation();
                if (importingElt == null)
                    importingElt = pkgImport.getImportingNameSpace();
            }
        }
        return importingElt;
    }

    @objid ("1410f018-ce98-4113-ab4a-0bd2a0307a18")
    public static boolean isIsClassAssociation(final Association assoc) {
        return  (assoc.getLinkToClass() != null);
    }

    @objid ("033bafea-490e-4abe-938d-d9b255e2e661")
    public static boolean isIsClassAssociation(final NaryAssociation assoc) {
        return  (assoc.getLinkToClass() != null);
    }

    @objid ("14492889-09fc-4822-8ef9-3fabae72767c")
    public static boolean isIsClassAssociation(final Class classe) {
        return (classe.getLinkToAssociation() != null);
    }

    @objid ("66dde93b-7d0a-454a-af93-d84763869364")
    public static boolean isBinary(Association assoc) {
        if (assoc != null) {
            List<AssociationEnd> memberEnds = assoc.getEnd();
            return ((memberEnds != null) && (memberEnds.size() == 2));
        }
        return false;
    }

    @objid ("76f1da06-f8e7-4acb-a126-2c5a2290312a")
    public static boolean isRepresentedByAUniqueClass(ClassAssociation classAssoc) {
        Class objingClass = classAssoc.getClassPart();
        if (objingClass != null) {
            ClassAssociation classAssocList = objingClass
                    .getLinkToAssociation();
        
            return (classAssocList != null);
        }
        return false;
    }

    @objid ("b0c3c3b2-c9c6-4c0a-8133-3edd45e78d1c")
    public static Package getNearestPackage(ModelTree modelTreeElt) {
        if (modelTreeElt != null) {
            ModelTree owner = modelTreeElt.getOwner();
            if (owner instanceof Package){
                return (Package) owner;
            }else{
                return getNearestPackage(owner);
            }
        }
        return null;
    }

    @objid ("2546e51a-b1d4-438d-894d-6ab2f8fdfad6")
    public static void infoOfUnsupportedOwnedWithEMF(MObject objingOwner, UmlModelElement objingElement, org.eclipse.uml2.uml.Element ecoreElt) {
        infoOfUnsupportedOwnedWithEMF(objingOwner,objingElement);
        if (ecoreElt != null){
            ecoreElt.destroy();
        }
    }

    @objid ("fe3fec0d-ff09-4a77-acd7-f4292d535aab")
    public static boolean isManifestationMappable(Manifestation manif) {
        UmlModelElement objingUtilizedElt = manif.getUtilizedElement();
        if (objingUtilizedElt != null) {
        
            // Gets the ecore utilized element:
            org.eclipse.uml2.uml.Element ecoreUtilizedElt = GenerationProperties.getInstance()
                    .getMappedElement(objingUtilizedElt);
        
            return ecoreUtilizedElt instanceof PackageableElement;
        }
        return false;
    }

    @objid ("f67a4682-8ce2-471c-a893-4043fa8d98ad")
    public static Message getReplyMessage(Message message) {
        boolean isSynchronous = false;
        MessageSort msgSort = message.getSortOfMessage();
        if (msgSort == MessageSort.SYNCCALL){
            isSynchronous = true;
        }
        
        if (isSynchronous) {
            MessageEnd receiveEnd = message.getReceiveEvent();
            Lifeline coveredLL = receiveEnd.getCovered().get(0);
        
            List<InteractionFragment> coveringFragments = coveredLL
                    .getCoveredBy();
            int index = coveringFragments.indexOf(receiveEnd);
            int fragmentsNumber = coveringFragments.size();
            int indexOfEOS = index + 1;
            InteractionFragment next = coveringFragments.get(indexOfEOS);
            while (indexOfEOS < fragmentsNumber - 1
                    && !(next instanceof ExecutionSpecification)) {
                next = coveringFragments.get(++indexOfEOS);
            }
        
            if (next instanceof ExecutionSpecification) {
                ExecutionOccurenceSpecification finishEnd = ((ExecutionSpecification) next)
                        .getFinish();
                return getMessage(finishEnd);
            }
        }
        return null;
    }

    @objid ("eb5ce13a-6faf-4603-b2e0-260a774b5f18")
    public static Message getMessage(MessageEnd end) {
        Message message = end.getSentMessage();
        if (message == null){
            message = end.getReceivedMessage();
        }
        return message;
    }

    @objid ("89459e81-c29a-4243-8efc-1d8932954ad4")
    public static InteractionFragment getConnectedFragment(UmlModelElement sequenceElt) {
        InteractionFragment connectedFragment = null;
        if (sequenceElt instanceof Message) {
            MessageEnd end = ((Message) sequenceElt).getReceiveEvent();
            if (end == null){
                end = ((Message) sequenceElt).getSendEvent();
            }
        
            if (end != null){
                connectedFragment = end;
            }
        }
        return connectedFragment;
    }

    @objid ("414e1951-9b28-4a38-a385-e154bfbc1030")
    public static Boolean IsInstanceOwner(final BindableInstance bindableInstance) {
        BindableInstance temp = bindableInstance;
        MObject owner = temp.getCompositionOwner();
        
        while ((owner != null) && (owner instanceof BindableInstance)){
            temp = (BindableInstance) owner;
            owner = temp.getCompositionOwner();
        }
        return (owner instanceof Instance);
    }

    @objid ("64dc957d-913b-480e-bc89-cc56171674a7")
    public static MObject getBindableInstanceOwner(Element instance) {
        MObject owner = instance.getCompositionOwner();
        while ((owner != null) && (owner instanceof BindableInstance)){
            owner = owner.getCompositionOwner();
        }
        return owner;
    }

    @objid ("dd9137b4-6c63-496d-ba1a-b98402680d63")
    public static MObject getLinkEndOwner(LinkEnd linkEnd) {
        return getBindableInstanceOwner(linkEnd.getLink());
    }

    @objid ("41ce3b34-f738-4db0-952f-91e0c8b559d5")
    public static MObject getEnclosingElement(MObject child, MClass typeOfEnclosing) {
        MObject owner = child.getCompositionOwner();
        
        while (owner != null) {
            if ((typeOfEnclosing.getSub(true).contains(owner.getMClass()) ||
                    typeOfEnclosing.equals(owner.getMClass()) )){
                return owner;
            }else{
                owner = owner.getCompositionOwner();
            }
        }
        return null;
    }

    @objid ("6d27627d-1f9a-436f-bdfc-3f100c36e148")
    public static List<Parameter> getRelatedParameters(ActivityAction objingOwner) {
        List<Parameter> paramList = new ArrayList<>();
        
        if (objingOwner instanceof CallOperationAction) {
            Operation operation = ((CallOperationAction) objingOwner)
                    .getCalled();
            if (operation != null){
                paramList.addAll(operation.getIO());
                if (operation.getReturn() != null){
                    paramList.add(operation.getReturn());
                }
            }
        
        } else if (objingOwner instanceof CallBehaviorAction) {
            Behavior behavior = ((CallBehaviorAction) objingOwner)
                    .getCalled();
            if (behavior != null) {
                paramList.addAll(behavior.getParameter());
            }
        }
        return paramList;
    }

    @objid ("d49d8ba3-39f5-4d48-b8b8-f368de534925")
    public static boolean isMergeNode(DecisionMergeNode node) {
        return ((node.getIncoming().size() > 1) && (node.getOutgoing().size() <= 1));
    }

    @objid ("664b43d5-385d-4da6-8a57-d58b813f966b")
    public static boolean isDecisionNode(DecisionMergeNode node) {
        return (node.getIncoming().size() <= 1);
    }

    @objid ("670bcf92-ed3f-46b2-8440-33f20a3811df")
    public static boolean isJoinNode(ForkJoinNode node) {
        return ((node.getIncoming().size()) > 1 && (node.getOutgoing().size() <= 1));
    }

    @objid ("7278760a-669f-4ebb-9f1b-22c5899a73a1")
    public static boolean isForkNode(ForkJoinNode node) {
        return (node.getIncoming().size() <= 1);
    }

    @objid ("9354d4cf-78ef-4d63-8b01-4ef7b7544c7d")
    public static boolean isProtocolStateMachine(StateMachine element) {
        boolean isProtocol = false;
        switch (element.getKind()) {
        case PROTOCOL:
            isProtocol = true;
            break;
        default:
            isProtocol = false;
            break;
        }
        return isProtocol;
    }

    @objid ("1ae60ec6-a6a8-4b38-ba72-a3ccdd70f011")
    public static boolean isJunctionState(StateVertex state) {
        return ((state.getIncoming().size() > 1) && (state.getOutGoing().size() <= 1));
    }

    @objid ("8c4c10c6-d014-467e-b4d9-13c1911b5bfc")
    public static boolean isChoiceState(StateVertex state) {
        return (state.getIncoming().size() <= 1);
    }

    @objid ("9704a64d-ec2d-4d3f-a933-bdfb3fbe783d")
    public static boolean isProtocolTransition(Transition transition) {
        if (transition instanceof InternalTransition){
            return false;
        }
        
        StateMachine sm =  (StateMachine) getEnclosingElement(transition, transition.getMClass().getMetamodel().getMClass("StateMachine"));
        return ((!(transition instanceof InternalTransition))
                                                                                        && (transition.getProcessed() != null)
                                                                                        && (sm != null)
                                                                                        && (sm.getKind().equals(KindOfStateMachine.PROTOCOL)));
    }

    @objid ("1188e00d-1028-4b0b-ac54-9e7ab2b2079e")
    public static StateMachine getOwnerStateMachine(State state) {
        StateMachine sm = state.getSubMachine();
        if (sm == null){
            sm = getOwnerStateMachine(state.getParent());
        }
        return sm;
    }

    @objid ("04c40601-214d-4fd8-99bf-894d2eb2f809")
    public static StateMachine getOwnerStateMachine(Region region) {
        StateMachine sm = region.getRepresented();
        if (sm == null){
            sm = getOwnerStateMachine(region.getParent());
        }
        return sm;
    }

    @objid ("78488ac7-2a00-451c-b1c8-c85040cc4c86")
    public static Package getNearestPackage(Element element) {
        MObject newOwner = element.getCompositionOwner();
        while (!(newOwner instanceof Package) ){
            newOwner = newOwner.getCompositionOwner();
        }
        return (Package) newOwner;
    }

    @objid ("186e576d-90d9-4099-b5b2-b5408cbe57b3")
    public static boolean isOwnedByActor(final Association assoc) {
        for (AssociationEnd assocEnd : assoc.getEnd()){
            if (!(assocEnd.getOwner() instanceof Actor)){
                return false;
            }
        }
        return true;
    }

    @objid ("92c15cd9-b89d-42fe-b9cc-20307b6e2f19")
    public static boolean mustBeExported(Stereotype obStereotype) {
        Profile profile =  obStereotype.getOwner();
        if (profile == null)
             return false;
        
        if (profile.getOwnerModule().getName().equals(IModelerModulePeerModule.MODULE_NAME)  
                && profile.getName().equals("default")
                && ((obStereotype.getName().equals(IModelerModuleStereotypes.EXTEND))
                        || (obStereotype.getName().equals(IModelerModuleStereotypes.INCLUDE)
                        || (obStereotype.getName().equals(IModelerModuleStereotypes.DERIVE))
                        || (obStereotype.getName().equals(IModelerModuleStereotypes.SATISFY))
                        || (obStereotype.getName().equals(IModelerModuleStereotypes.PART))
                        || (obStereotype.getName().equals(IModelerModuleStereotypes.VERIFY))))){
            return false;
        }
        return !(profile.getUuid().toString().equals("01ec045c-0000-3739-0000-000000000000"));
    }

    @objid ("9f15dfb7-a6b1-4ca2-b113-d8ec53f26249")
    public static boolean mustBeExported(MetaclassReference metaclass) {
        Profile profile =  metaclass.getOwnerProfile();
        return !(profile.getUuid().toString().equals("01ec045c-0000-3739-0000-000000000000"));
    }

    @objid ("07030840-c929-4cc6-8101-d717a58d3192")
    public static boolean mustBeExported(TagType tagType) {
        if (tagType.getOwnerStereotype() != null){
            return mustBeExported(tagType.getOwnerStereotype());
        }else if (tagType.getOwnerReference() != null){
            return mustBeExported(tagType.getOwnerReference());
        }else
            return false;
    }

    @objid ("f9980de4-ffbf-4000-a0c2-6e2b726618e3")
    public static boolean mustBeExported(TaggedValue taggedValue) {
        return mustBeExported(taggedValue.getDefinition());
    }

    @objid ("dc4aa6bf-11b5-4eba-a09d-626385a2d799")
    public static String getTaggedValue(UmlModelElement owner, String tagType) {
        String result = "";
        for (TaggedValue tagged : owner.getTag()){
            if (tagged.getDefinition().getName().equals(tagType)){
                if (tagged.getActual().size() > 0){
                    return tagged.getActual().get(0).getValue();
                }
            }
        }
        return result;
    }

    @objid ("8dfc0500-bca2-47f9-86bc-8fcdf6d3533d")
    public static Type getType(AttributeLink attributeLink) {
        Type result = null;
        Attribute attribute  = attributeLink.getBase();
        if (attribute != null){
            attribute.getType();
        }
        return result;
    }

    @objid ("52fa49be-4f01-4218-8091-ab64aa6ead94")
    public static BindableInstance copyAssocEndToBindableInstance(AssociationEnd initial, Property ecoreElt) {
        BindableInstance copy = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createBindableInstance();
        setInstance(copy, ecoreElt);
        copy.setOwner(initial.getOwner());
        copy.setRepresentedFeature(initial);
        return copy;
    }

    @objid ("fca87529-f345-478a-8213-a5348c47961d")
    public static void attachPort(Element objingElt, org.eclipse.uml2.uml.Port ecoreElement) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreOwner = ecoreElement.getOwner();
        
        if (revProp.isRoundtripEnabled() && (ObjingEAnnotation.getOwner(ecoreElement) != null)){
            String ownerId = ObjingEAnnotation.getOwner(ecoreElement);
            for (Object object : ( (org.eclipse.uml2.uml.Classifier)ecoreOwner).getAllAttributes()){
                Property property = (Property) object;
                List<String> ids = ObjingEAnnotation.getObjingIDs(property);
                if (ids.size() > 0){
                    String id =  ids.get(0);
                    if (id.equals(ownerId)){
                        Object objOwner = revProp.getMappedElement(property);
                        if (objOwner instanceof BindableInstance)
                            if (objingElt instanceof BindableInstance){
                                if (((BindableInstance) objingElt).getInternalOwner()== null){
                                    ((BindableInstance) objingElt).setCluster((Instance)objOwner);
                                }
                                break;
                            }else{
                                ((AttributeLink) objingElt).setAttributed((Instance)objOwner);
                                break;
                            }
                    }
                }
            }
        }
        
        if (ecoreOwner != null) {
            Object objOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            if (objOwner instanceof Classifier){
                if (((BindableInstance) objingElt).getCluster()== null){
                    ((Port) objingElt).setInternalOwner(((Classifier) objOwner));
                }
            }else if (objOwner instanceof BindableInstance){
                if (((BindableInstance) objingElt).getInternalOwner()== null){
                    ((Port) objingElt).setCluster((BindableInstance) objOwner);
                }else if  (objOwner instanceof AssociationEnd){
                    objOwner = copyAssocEndToBindableInstance(
                            (AssociationEnd) objOwner, (Property) ecoreOwner);
                    if (((BindableInstance) objingElt).getInternalOwner()== null){
                        ((Port) objingElt).setCluster((BindableInstance) objOwner);
                    }
        
                }
            }else{
                objingElt.delete();
            }
        
        }
    }

    @objid ("9de45bba-9e96-4045-9886-718dbdb06716")
    private static void setBase(Instance objingElt, Property ecoreElt) {
        Type type =  ecoreElt.getType();
        if (type != null){
            Element base = (Element) ReverseProperties.getInstance().getMappedElement(type);
            if (base instanceof NameSpace){
                objingElt.setBase((NameSpace) base);
            }
        }
    }

    @objid ("b4c778f4-45eb-4547-a966-b89ffacbeed4")
    public static void setRepresentedFeature(Instance objingElt, Property ecoreElt) {
        if (objingElt instanceof BindableInstance){
        
            EList<?> listProperty =  ecoreElt.getRedefinedProperties();
        
            if ((listProperty != null) && (listProperty.size() > 0) ){
                Property represented = (Property) listProperty.get(0);
                Object base = ReverseProperties.getInstance().getMappedElement(represented);
        
                if ((base instanceof UmlModelElement) && (!base.equals(objingElt)))
                    ((BindableInstance) objingElt).setRepresentedFeature((UmlModelElement) base);
                else if (base instanceof List<?> ){
                    ((BindableInstance) objingElt).setRepresentedFeature(((List<? extends UmlModelElement>) base).get(0));
                }
            }
        }
    }

    @objid ("f4f1e091-2a1c-45ec-ae51-657e82eb6d1b")
    private static void setIsConstant(Instance objingElt, Property ecoreElt) {
        objingElt.setIsConstant(ObjingEAnnotation.isConstant(ecoreElt));
    }

    @objid ("3b89c3ea-e642-4a81-acf2-fcb94f762e6e")
    public static void setMultiMax(Instance objingElt, Property ecoreElt) {
        String value =  ObjingEAnnotation.getMultiMax(ecoreElt);
        if (value != null){
            objingElt.setMultiplicityMax(value);
        }
    }

    @objid ("f21a3ed2-f403-4118-a090-a28839845521")
    private static void setValue(Instance objingElt, Property ecoreElt) {
        String value =  ObjingEAnnotation.getValue(ecoreElt);
        if (value != null){
            objingElt.setValue(value);
        }
    }

    @objid ("e3f1c41a-14bc-415a-891a-9a36b8ce7be7")
    public static void setMultiMin(Instance objingElt, Property ecoreElt) {
        String value =  ObjingEAnnotation.getMultiMin(ecoreElt);
        if (value != null){
            objingElt.setMultiplicityMin(value);
        }
    }

    @objid ("ae2d9b02-a6f7-4daf-9fc0-341413564685")
    private static void setMultiplicity(Instance objingElt, Property ecoreElt) {
        String multMin = EcoreModelNavigation.getMultiplicityMin(ecoreElt);
        String multMax = EcoreModelNavigation.getMultiplicityMax(ecoreElt);
        
        if (!("".equals(multMin))){
            objingElt.setMultiplicityMin(multMin);
        }
        
        if (!("".equals(multMax))){
            objingElt.setMultiplicityMax(multMax);
        }
    }

    @objid ("250bfce3-e043-43e6-981c-7c983449c2bf")
    public static void setInstance(Instance objingElt, Property ecoreElt) {
        setBase(objingElt, ecoreElt);
        setRepresentedFeature(objingElt, ecoreElt);
        setMultiplicity(objingElt, ecoreElt);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setIsConstant(objingElt, ecoreElt);
            setValue(objingElt, ecoreElt);
            setMultiMax(objingElt, ecoreElt);
            setMultiMin(objingElt, ecoreElt);
        }
    }

    @objid ("9fdf1064-c596-4149-90ec-c6d36af28906")
    public static void infoOfUnsupportedOwnedWithEMF(MObject objingOwner, UmlModelElement objingElement) {
        String eltName = objingElement.getName();
        String eltClassName = objingElement.getClass().getSimpleName();
        String ownerClassName = objingOwner.getClass().getSimpleName();
        String message = Xmi.I18N.getMessage("logFile.warning.unsupportedOwnerExport",
                        eltClassName, eltName, ownerClassName);
        GenerationProperties.getInstance().addError(message, objingElement);
    }

    @objid ("ac25ba19-3f77-48df-821d-0c9127e45663")
    public static List<Interface> getAllProvidedInterface(final Port port) {
        List<Interface> result = new ArrayList<>();
        
        NameSpace type = port.getBase();
        
        if (type != null){
            if (type instanceof Interface){
                result.add((Interface)type);
            }else {
                result.addAll(getAllProvidedInterfaces(type));
            }
        }
        return result;
    }

    @objid ("3c765bba-708e-4248-a78a-668936ebad80")
    public static EnumerationLiteral getEnumerationliteral(final Enumeration enumeration, final String stringToTest) {
        for (EnumerationLiteral literal : enumeration.getValue()){
            if (literal.getName().equals(stringToTest)){
                return literal;
            }
        }
        return null;
    }

    @objid ("dbc26698-8567-4b1b-8bec-300c5cac91cd")
    public static boolean haveInterfaceRealization(final NameSpace eltToTest, final Interface inter) {
        for (InterfaceRealization realization : eltToTest.getRealized() ){
            if (realization.getImplemented().equals(inter)){
                return true;
            }
        }
        return false;
    }

    @objid ("d9e0b466-7379-499b-aea5-1f76c90d4056")
    public static boolean haveInterfaceRealizationParent(final NameSpace eltToTest, final Interface inter) {
        if (haveInterfaceRealization(eltToTest, inter)){
            return true;
        }else{
            for (Generalization generalization : eltToTest.getParent()){
                if (haveInterfaceRealizationParent(generalization.getSuperType(), inter)){
                    return true;
                }
            }
        }
        return false;
    }

    @objid ("a0a5d68b-53e1-4627-8373-658a1ec780b7")
    public static boolean isEnumerationliteral(final Enumeration enumeration, final String stringToTest) {
        for (EnumerationLiteral literal : enumeration.getValue()){
            if (literal.getName().equals(stringToTest)){
                return true;
            }
        }
        return false;
    }

    @objid ("83874491-be59-4c1f-a438-17ed88ef900a")
    public static boolean isNotNullOrEmpty(final String toTest) {
        return ((toTest != null) &&  (!toTest.equals("")));
    }

    @objid ("c8030860-9093-4d9c-b80d-543a4b3874ed")
    public static boolean isStereotyped(final UmlModelElement elt, final String stereotypeName) {
        for (Stereotype stereo : elt.getExtension()) {
            if (stereo.getName().equals(stereotypeName)){
                return true;
            }
        }
        return false;
    }

    @objid ("9aff0937-5212-4406-adf7-781371bf4a92")
    private static List<Interface> getAllProvidedInterfaces(final NameSpace parent) {
        List<Interface> result = new ArrayList<>();
        
        result.addAll(getProvidedInterfaces(parent));
        
        for (Generalization generalization : parent.getParent()){
            result.addAll(getAllProvidedInterfaces( generalization.getSuperType()));
        }
        return result;
    }

    @objid ("c7439776-0c37-455e-a907-dbc65fb57353")
    private static List<Interface> getProvidedInterfaces(final NameSpace parent) {
        List<Interface> result = new ArrayList<>();
        
        for (InterfaceRealization interfaceRealization : parent.getRealized()){
            result.add(interfaceRealization.getImplemented());
        }
        return result;
    }

    @objid ("dfa3853e-f683-4e93-ae61-1d81c054e513")
    public static boolean haveInstanceValue(UmlModelElement elt) {
        for (Dependency dep: elt.getDependsOnDependency()){
            if (dep.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSTANCEVALUE)){
                return true;
            }
        }
        return false;
    }

    @objid ("634bafd1-e9f2-4350-a110-295b26fb9de8")
    public static Instance getInstanceValue(UmlModelElement elt) {
        for (Dependency dep: elt.getDependsOnDependency()){
            if (dep.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSTANCEVALUE)
                    && (dep.getDependsOn() instanceof Instance)){
                return (Instance) dep.getDependsOn();
            }
        }
        return null;
    }

    @objid ("941be815-a318-4861-9d14-6d6128cad7a2")
    public static boolean isValidConnector(Link link) {
        MObject firstOwner = null;
        
        for (LinkEnd connectorEnd : link.getLinkEnd()){
            MObject temp = getBindableInstanceOwner(connectorEnd.getOwner());
        
            if (firstOwner == null){
                firstOwner = temp;
            }else {
                if (firstOwner.equals(temp)){
                    return (firstOwner instanceof GeneralClass)
                            || (firstOwner instanceof Collaboration);
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    @objid ("c409015f-01e4-444d-9d0e-9ad5065bb3ed")
    public static boolean isValidLink(Link link) {
        for (LinkEnd connectorEnd : link.getLinkEnd()){
            MObject temp = getBindableInstanceOwner(connectorEnd.getOwner());
        
            if ((temp != null) && (temp instanceof Package)){
                return true;
            }
        }
        return false;
    }

    @objid ("daf1a02f-aa92-4deb-9aad-a5bdc760270d")
    public static MObject getConnectorOwner(Link connector) {
        MObject firstOwner = null;
        MObject secondOwner = null;
        
        for (LinkEnd connectorEnd : connector.getLinkEnd()){
            secondOwner = getBindableInstanceOwner(connectorEnd.getOwner());
        
            if (firstOwner == null){
                firstOwner = secondOwner;
            }else {
                if (!(firstOwner.equals(secondOwner))
                        || ((!(secondOwner instanceof GeneralClass) )
                                && (!(secondOwner instanceof Collaboration)))){
                    return null;
                }
            }
        }
        return secondOwner;
    }

    @objid ("2f68b94f-fb2c-4e4f-a2da-ede4bffc0429")
    public static Package getLinkOwner(Link link) {
        MObject owner = null;
        for (LinkEnd connectorEnd : link.getLinkEnd()){
            owner = connectorEnd.getOwner().getCompositionOwner();
        
            if ((owner == null) || (!(owner instanceof Package))){
                return null;
            }
        }
        return (Package) owner;
    }

    @objid ("2b254927-1bb9-4106-b6b9-6ea94313a8cd")
    public static List<InteractionFragment> getOrderedFragments(Interaction param) {
        List<InteractionFragment> unsorted = new ArrayList<>(param.getFragment());
        List<InteractionFragment> result = new ArrayList<>();
        InteractionFragment temp = null;
        
        while (unsorted.size() > 0){
            temp = unsorted.get(0);
        
            for (InteractionFragment tested : unsorted){
                if (temp.getLineNumber() > tested.getLineNumber()) {
                    temp = tested;
                }
            }
        
            unsorted.remove(temp);
            result.add(temp);
        }
        return result;
    }

    @objid ("34eb67a8-8f5e-4b16-a03d-a3266b37e94d")
    public static boolean isIsClassAssociation(final NaryAssociationEnd assocEnd) {
        return ((assocEnd != null) && (assocEnd.getNaryAssociation().getLinkToClass() != null));
    }

    @objid ("0616fef9-ccc8-4efa-8b31-77ebb00ac137")
    public static boolean isOwnedByActor(final NaryAssociation assoc) {
        int nbNoActor = 0;
        for (NaryAssociationEnd assocEnd : assoc.getNaryEnd()){
            if (assocEnd.getOwner() instanceof Actor){
                nbNoActor++;
            }
        }
        return nbNoActor >=2 ;
    }

    @objid ("338c5241-f43b-467e-bc30-06c87a55e9dd")
    public static boolean isIsClassAssociation(final AssociationEnd assocEnd) {
        return ((assocEnd != null) && (assocEnd.getAssociation() != null) && (assocEnd.getAssociation().getLinkToClass() != null));
    }

    @objid ("323eed05-a971-4061-810b-9e5bd92aebb3")
    public static MObject getNaryConnectorOwner(NaryLink connector) {
        MObject firstOwner = null;
        MObject secondOwner = null;
        
        for (NaryLinkEnd connectorEnd : connector.getNaryLinkEnd()){
            secondOwner = connectorEnd.getSource();
        
            if (firstOwner == null){
                firstOwner = secondOwner;
            }else {
                if (!(firstOwner.equals(secondOwner))
                        || ((!(secondOwner instanceof GeneralClass) )
                                && (!(secondOwner instanceof Collaboration)))){
                    return null;
                }
            }
        }
        return secondOwner;
    }

    @objid ("17d5d6d3-1337-4ca7-bc41-1c3cfd8d2671")
    public static Package getNaryLinkOwner(NaryLink link) {
        MObject owner = null;
        
        for (NaryLinkEnd connectorEnd : link.getNaryLinkEnd()){
            owner = connectorEnd.getCompositionOwner().getCompositionOwner();
        
            if ((owner == null) || (!(owner instanceof Package))){
                return null;
            }
        }
        return (Package) owner;
    }

    @objid ("c55ebfbc-5ec8-411b-9bdc-3fb3e4f94de7")
    public static boolean isSubType(NameSpace superType, NameSpace subType) {
        for (Generalization generalization : subType.getParent()){
            NameSpace currentSuperType = generalization.getSuperType();
            if ((superType.equals(currentSuperType)) ||  isSubType(superType, currentSuperType))
               return true;
        }
        return false;
    }

}
