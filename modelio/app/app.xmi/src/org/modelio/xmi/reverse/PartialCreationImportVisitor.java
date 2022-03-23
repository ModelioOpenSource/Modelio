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
package org.modelio.xmi.reverse;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.EcorePrimitiveTypeMapper;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;

@objid ("aba6ace8-c1f5-4bb7-8751-6d25a5e5d550")
public class PartialCreationImportVisitor {
    @objid ("9e3c9543-937a-40a1-8332-0a9becea6f30")
    private final int defaut = 42;

    @objid ("34cc51c3-5c11-4090-b350-16a1673459a0")
    Object objingElt = null;

    @objid ("db25f58f-fa87-4d8e-8fe7-26a41bb7cbd1")
    private CreationImportMapper mapper;

    @objid ("863f8a03-06f0-4a27-94d9-b680dd5374fc")
    private PartialImportMap partialMap;

    @objid ("73c92685-7d18-41ae-a80e-f615a857e706")
    public  PartialCreationImportVisitor() {
        this.objingElt = null;
        this.mapper = new CreationImportMapper();
        this.partialMap = PartialImportMap.getInstance();
        
    }

    @objid ("02d82373-2d55-4b5d-a2e0-03d014e09adc")
    public CreationImportMapper getMapper() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.mapper;
    }

    @objid ("80a1b157-ee8d-4fc1-97eb-85baee9be8ec")
    public PartialImportMap getPartialMap() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.partialMap;
    }

    @objid ("7b3004d1-d807-48b5-a032-0733de619367")
    public Object createPartialObjingElt(final org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null) {
            this.objingElt = null;
            Object objingEltFromMap = this.partialMap.get(ecoreElt);
        
            if (objingEltFromMap == null) {
                this.mapper.accept(ecoreElt);
                this.partialMap.put(ecoreElt, this.objingElt);
                objingEltFromMap = this.objingElt;
            }else {
                this.partialMap.put(ecoreElt, objingEltFromMap);
            }
            return objingEltFromMap;
        } else{
            Xmi.LOG.warning(Xmi.PLUGIN_ID, "Ecore element is null.");
            throw new RuntimeException("Ecore element is null.");
        }
        
    }

    @objid ("6b8638a0-dc92-4be8-b7cd-857dd71f1829")
    public Object getObjingElt() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.objingElt;
    }

    @objid ("d647d571-ba11-42f9-8d3c-fba0811d852a")
    private class CreationImportMapper extends UMLSwitch<Object> {
        //        @objid ("ca796f8a-1ab6-4935-aabb-e98afc62dc2f")
        //        boolean isRoundTrip = ReverseProperties.getInstance().isRoundtripEnabled();
        @objid ("987b0449-63c8-41f7-8098-42e1cf2bc70b")
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();

        @objid ("aaf4284e-6fa8-41f7-bce1-f7bdc2658719")
        IStandardModelFactory factory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);

        @objid ("9460dad8-e353-4e5e-af28-5629226e0adc")
        public void accept(org.eclipse.uml2.uml.Element ecoreElt) {
            doSwitch(ecoreElt);
        }

        @objid ("fa9de6bb-4f2f-4540-b678-9b999928e21b")
        @Override
        public Object caseAbstraction(org.eclipse.uml2.uml.Abstraction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = new ArrayList<Abstraction>();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2abcc752-f504-49c4-800c-46d2dfe3c559")
        @Override
        public Object caseAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptCallEventAction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b36c46db-0793-451e-b76f-3d58028738a0")
        @Override
        public Object caseAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction ecoreElt) {
            if (EcoreModelNavigation.hasReceiveOperationEvent(ecoreElt)) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptCallEventAction();
            
            } else if (EcoreModelNavigation.hasSignalEvent(ecoreElt)) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptSignalAction();
            
            } else if (EcoreModelNavigation.hasChangeEvent(ecoreElt)) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptChangeEventAction();
            
            } else if (EcoreModelNavigation.hasTimeEvent(ecoreElt)) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptTimeEventAction();
            
            }else if (ReverseProperties.getInstance().isRoundtripEnabled()){
                String signal = ObjingEAnnotation.getSignal(ecoreElt);
                if ((signal != null) && (signal.equals("signal")))
                    PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptSignalAction();
            }else
                PartialCreationImportVisitor.this.objingElt = this.factory.createAcceptCallEventAction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("533e280c-ba52-4834-b930-53b2e33791d8")
        @Override
        public Object caseActionExecutionSpecification(org.eclipse.uml2.uml.ActionExecutionSpecification ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExecutionSpecification();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("361c5322-5f7f-4eaa-a109-bc62e81b58b4")
        @Override
        public Object caseActionInputPin(org.eclipse.uml2.uml.ActionInputPin ecoreElt) {
            InputPin result = this.factory.createInputPin();
            
            try {
                result.getExtension().add(this.mmServices
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ACTIONINPUTPIN, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("08d58484-d827-4114-b5f6-db5ee3ca97d3")
        @Override
        public Object caseActivity(org.eclipse.uml2.uml.Activity ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createActivity();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7af2cb43-5e45-4595-915d-585e04dbf08f")
        @Override
        public Object caseActivityFinalNode(org.eclipse.uml2.uml.ActivityFinalNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createActivityFinalNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("1cc732d1-2a3c-4ae9-ac6a-ddf6e98b6821")
        @Override
        public Object caseActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createActivityParameterNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("48286b1a-df47-43b7-b281-c1165445f1ca")
        @Override
        public Object caseActivityPartition(org.eclipse.uml2.uml.ActivityPartition ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createActivityPartition();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("264055d2-550f-4c74-97f1-69c8a8848e56")
        @Override
        public Object caseActor(org.eclipse.uml2.uml.Actor ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createActor();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("5ca4b4c1-6e30-41a9-a875-c49acefc3227")
        @Override
        public Object caseAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ADDSTRUCTURALFEATUREVALUEACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a4a62f4b-6c2f-44ae-953f-9952562c043e")
        @Override
        public Object caseAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ADDVARIABLEVALUEACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("90e64ec8-b87e-427c-a898-904ee4d0ce29")
        @Override
        public Object caseAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent ecoreElt) {
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("dd4a218e-9b0d-4e3e-aa3b-f33c76d0c39d")
        @Override
        public Object caseArtifact(org.eclipse.uml2.uml.Artifact ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createArtifact();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("f1f0a5b9-6f50-4e9f-b36f-4232f65e4f77")
        @Override
        public Object caseAssociation(org.eclipse.uml2.uml.Association ecoreElt) {
            int endNumber = EcoreModelNavigation.getValidEndNumber(ecoreElt);
            
            if (endNumber == 2){
                PartialCreationImportVisitor.this.objingElt = this.factory.createAssociation();
            }else if (endNumber > 2){
                PartialCreationImportVisitor.this.objingElt = this.factory.createNaryAssociation();
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ae494d50-61cc-48d0-bb34-2b053caa5a39")
        @Override
        public Object caseAssociationClass(org.eclipse.uml2.uml.AssociationClass ecoreElt) {
            // Creation of the association class
            ClassAssociation objingClassAssociation = this.factory.createClassAssociation();
            // Creation of the Class
            Class objingClass = this.factory.createClass();
            // Creation of the org.eclipse.uml2.uml.Association
            Association objingAssociation = this.factory.createAssociation();
            // Set of the class and org.eclipse.uml2.uml.Association
            objingClassAssociation.setClassPart(objingClass);
            objingClassAssociation.setAssociationPart(objingAssociation);
            PartialCreationImportVisitor.this.objingElt = objingClass;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("42a3d7e7-ae78-45ec-a7e0-a9c04611f570")
        @Override
        public Object caseBehaviorExecutionSpecification(org.eclipse.uml2.uml.BehaviorExecutionSpecification ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExecutionSpecification();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9b636292-5be4-4882-aa27-9efdaac0f24c")
        @Override
        public Object caseBroadcastSignalAction(org.eclipse.uml2.uml.BroadcastSignalAction ecoreElt) {
            SendSignalAction result = this.factory.createSendSignalAction();
            
            try {
                result.getExtension().add(this.mmServices
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2BROADCASTSIGNALACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("8ab77085-c1f1-458d-88cb-2fc191f4fd4d")
        @Override
        public Object caseCallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createCallBehaviorAction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("22a60c1a-037c-4106-9392-ad4cfbf07112")
        @Override
        public Object caseCallEvent(org.eclipse.uml2.uml.CallEvent ecoreElt) {
            ecoreElt.getOwner();
            
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            result.setKind(EventType.CALLEVENT);
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("14b050b1-0582-4c49-856e-1ef26b95cd38")
        @Override
        public Object caseCallOperationAction(org.eclipse.uml2.uml.CallOperationAction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createCallOperationAction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("da001dcb-9db2-4045-ad1b-c4eee90cf29b")
        @Override
        public Object caseCentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode ecoreElt) {
            if (ReverseProperties.getInstance().isRoundtripEnabled()){
                String type = ObjingEAnnotation.getType(ecoreElt);
                if  ((type != null) && (type.equals("InstanceNode")))
                    PartialCreationImportVisitor.this.objingElt =  this.factory.createInstanceNode();
                else
                    PartialCreationImportVisitor.this.objingElt = this.factory.createCentralBufferNode();
            }else 
            
                PartialCreationImportVisitor.this.objingElt = this.factory.createCentralBufferNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("72533615-b0f2-46c1-89b6-88777e97dc78")
        @Override
        public Object caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent ecoreElt) {
            ecoreElt.getOwner();
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            result.setKind(EventType.CHANGEEVENT);
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("0ef145c6-7d4d-4b41-9d66-8a1576d4861c")
        @Override
        public Object caseClass(org.eclipse.uml2.uml.Class ecoreElt) {
            if (!EcoreModelNavigation.isMetaclass(ecoreElt)){
                PartialCreationImportVisitor.this.objingElt = this.factory.createClass();
            //                //fix for import of non navigeable Association
            //                EClass eclass = new EClass(ecoreElt);
            //                eclass.attach((Class)PartialCreationImportVisitor.this.objingElt);
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7b511acc-f1db-4daa-a103-b42cbe24e3b0")
        @Override
        public Object caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter ecoreElt) {
            UmlModelElement result = this.factory.createTemplateParameter();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLASSIFIERTEMPLATEPARAMETER, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt =  result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d13ca74b-d925-42e0-b5a2-41b3aaa6f8ec")
        @Override
        public Object caseClause(org.eclipse.uml2.uml.Clause ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createClause();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("40774aec-2738-4103-b7be-b5fa5f494fc9")
        @Override
        public Object caseClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLEARASSOCIATIONACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9fcd51e7-cfe0-444d-925d-18d06c9ca310")
        @Override
        public Object caseClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLEARSTRUCTURALFEATUREACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("192e50f6-4923-4b94-85e4-ea08e995701b")
        @Override
        public Object caseClearVariableAction(org.eclipse.uml2.uml.ClearVariableAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLEARVARIABLEACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("bbaaa7e5-9f66-41b2-adc2-ae6c36879bc4")
        @Override
        public Object caseCollaboration(org.eclipse.uml2.uml.Collaboration ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createCollaboration();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("3b0eab3e-c609-4c66-8ca6-d92ddf058c03")
        @Override
        public Object caseCollaborationUse(org.eclipse.uml2.uml.CollaborationUse ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createCollaborationUse();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("8db99cbb-6030-47aa-8fce-11b66be31dc3")
        @Override
        public Object caseCombinedFragment(org.eclipse.uml2.uml.CombinedFragment ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createCombinedFragment();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7f03c3e4-d119-4dac-8bae-2dc7eaf1baaa")
        @Override
        public Object caseComment(org.eclipse.uml2.uml.Comment ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = new ArrayList<Note>();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("622cd92f-fcda-4a8f-a529-1edaae02be0b")
        @Override
        public Object caseCommunicationPath(org.eclipse.uml2.uml.CommunicationPath ecoreElt) {
            int endNumber = EcoreModelNavigation.getValidEndNumber(ecoreElt);
            
            if (endNumber == 2){
                Association result = this.factory.createAssociation();     
            
                try {
                    result.getExtension().add(
                            this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2COMMUNICATIONPATH, result.getMClass()));
                } catch (IllegalArgumentException | ElementNotUniqueException e) {
                    Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
                }
            
                PartialCreationImportVisitor.this.objingElt = result; 
            
            } else                  
                PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("0afb29ea-eb9b-486d-8f38-75085b6586d0")
        @Override
        public Object caseComponent(org.eclipse.uml2.uml.Component ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createComponent();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7fd73986-3989-46f5-a8ff-bc0d866d0349")
        @Override
        public Object caseComponentRealization(org.eclipse.uml2.uml.ComponentRealization ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createComponentRealization();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("60b775a8-ba57-43b5-97a0-a329d81aeda6")
        @Override
        public Object caseConditionalNode(org.eclipse.uml2.uml.ConditionalNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createConditionalNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("6e1e63f7-3efe-4b72-bd08-236d9625f03f")
        @Override
        public Object caseConnectableElementTemplateParameter(org.eclipse.uml2.uml.ConnectableElementTemplateParameter ecoreElt) {
            TemplateParameter result = this.factory.createTemplateParameter();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CONNECTABLEELEMENTTEMPLATEPARAMETER, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ffe81bf8-3a37-407a-b4d3-2443552d9077")
        @Override
        public Object caseConnectionPointReference(org.eclipse.uml2.uml.ConnectionPointReference ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createConnectionPointReference();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("6bdbaded-3ee9-4628-9711-3dbbe152f477")
        @Override
        public Object caseConnector(org.eclipse.uml2.uml.Connector ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createConnector();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("76f74148-1ca8-4dd6-bd18-26cd70d45f3a")
        @Override
        public Object caseConnectorEnd(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createConnectorEnd();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b3e1b787-4987-4a96-b6f4-25956b72d7ec")
        @Override
        public Object caseConsiderIgnoreFragment(org.eclipse.uml2.uml.ConsiderIgnoreFragment ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createCombinedFragment();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("8dc64f29-9cf5-4686-b462-20ac757cf5a7")
        @Override
        public Object caseConstraint(org.eclipse.uml2.uml.Constraint ecoreElt) {
            if ((! EcoreModelNavigation.isCondition(ecoreElt)) 
                    && ((ecoreElt.getConstrainedElements().size() > 0))){
            
                EList<org.eclipse.uml2.uml.Element> ecoreConstrainedElts = ecoreElt.getConstrainedElements();
            
                for (org.eclipse.uml2.uml.Element ecoreConstrainedElt : ecoreConstrainedElts) {
            
                    Object object = ReverseProperties.getInstance().getMappedElement(ecoreConstrainedElt);
            
                    if (object instanceof UmlModelElement){
                        UmlModelElement objingConstrainedElt = (UmlModelElement) object;
                        Constraint result = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createConstraint();
                        result.getConstrainedElement().add(objingConstrainedElt);
                        PartialCreationImportVisitor.this.objingElt = result;
            
                    }else if (object instanceof List<?>){
                        List<?> list = (List<?>) object;
                        Constraint result = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createConstraint();
                        for (Object objingConstrainedElt : list){
                            if (objingConstrainedElt instanceof UmlModelElement){
                                result.getConstrainedElement().add((UmlModelElement)objingConstrainedElt);
                            }
                        }
            
                        PartialCreationImportVisitor.this.objingElt = result;
                    }
                }
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("27eacd2b-085d-4dc1-b316-3d20cc1152a9")
        @Override
        public Object caseContinuation(org.eclipse.uml2.uml.Continuation ecoreElt) {
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("c6db4b32-26e0-48b2-b0ae-d6fdf0973743")
        @Override
        public Object caseControlFlow(org.eclipse.uml2.uml.ControlFlow ecoreElt) {
            org.eclipse.uml2.uml.ActivityNode ecoreSource = ecoreElt.getSource();
            org.eclipse.uml2.uml.ActivityNode ecoreTarget = ecoreElt.getTarget();
            if ((ecoreSource != null) && (ecoreTarget != null)) {
                Object objingSource = ReverseProperties.getInstance().getMappedElement(ecoreSource);
                if (objingSource != null){
                    if (objingSource instanceof ActivityNode)
                        PartialCreationImportVisitor.this.objingElt = this.factory.createControlFlow();
                }
            }
            PartialCreationImportVisitor.this.objingElt =  null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("4379fa4b-1245-4625-8a15-3fc75f6e9f01")
        @Override
        public Object caseCreateLinkAction(org.eclipse.uml2.uml.CreateLinkAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CREATELINKACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("61b6873a-b0ad-4ce7-97dc-f26f3ebc9b7c")
        @Override
        public Object caseCreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CREATELINKOBJECTACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b5af8f48-55a7-4b95-b677-7b70140d920a")
        @Override
        public Object caseCreateObjectAction(org.eclipse.uml2.uml.CreateObjectAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CREATEOBJECTACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ea0fd956-65e7-4bca-8ef9-8ab560474fbe")
        @Override
        public Object caseCreationEvent(org.eclipse.uml2.uml.CreationEvent ecoreElt) {
            ecoreElt.getOwner();
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CREATIONEVENT, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9a6924b0-2630-4905-b177-4012b7866445")
        @Override
        public Object caseDataStoreNode(org.eclipse.uml2.uml.DataStoreNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createDataStoreNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ef76f989-997e-49d6-be8f-0fe81aeb0e21")
        @Override
        public Object caseDataType(org.eclipse.uml2.uml.DataType ecoreElt) {
            if (EcorePrimitiveTypeMapper.isPredefinedType(ecoreElt))
                PartialCreationImportVisitor.this.objingElt =  EcorePrimitiveTypeMapper.getPredefinedType(ecoreElt);
            else 
                PartialCreationImportVisitor.this.objingElt = this.factory.createDataType();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("cc026fcd-4539-43fd-920f-840d31a5f1cc")
        @Override
        public Object caseDecisionNode(org.eclipse.uml2.uml.DecisionNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createDecisionMergeNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ecf281f3-cf37-49a6-bb83-5ee402e4b3d4")
        @Override
        public Object caseDependency(org.eclipse.uml2.uml.Dependency ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = new ArrayList<Dependency>();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("21bd144f-7237-4e5c-8f7e-8e8a2d648d18")
        @Override
        public Object caseDeployment(org.eclipse.uml2.uml.Deployment ecoreElt) {
            Dependency result = this.factory.createDependency();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DEPLOYMENT, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9c8ae581-8f2e-4b5d-969d-51162bf2ab35")
        @Override
        public Object caseDeploymentSpecification(org.eclipse.uml2.uml.DeploymentSpecification ecoreElt) {
            Artifact result = this.factory.createArtifact();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DEPLOYMENTSPECIFICATION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("57cd1d51-437f-4ef2-8420-e2760f0481b2")
        @Override
        public Object caseDestroyLinkAction(org.eclipse.uml2.uml.DestroyLinkAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DESTROYLINKACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("c6532750-6444-4b99-ad1b-af1d1694cf4b")
        @Override
        public Object caseDestroyObjectAction(org.eclipse.uml2.uml.DestroyObjectAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DESTROYOBJECTACTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e88e143a-3f07-43ac-a09a-4e958886bd90")
        @Override
        public Object caseDestructionEvent(org.eclipse.uml2.uml.DestructionEvent ecoreElt) {
            ecoreElt.getOwner();
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DESTRUCTIONEVENT, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("23bdc35a-6bf7-447a-aab6-ceb95a1b1400")
        @Override
        public Object caseDevice(org.eclipse.uml2.uml.Device ecoreElt) {
            Node result = this.factory.createNode();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DEVICE, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("118dffa6-b624-4c02-bacd-98daf3586f2c")
        @Override
        public Object caseDuration(org.eclipse.uml2.uml.Duration ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e12aa89b-96f4-4659-9f3d-10fe2d4919d4")
        @Override
        public Object caseDurationConstraint(org.eclipse.uml2.uml.DurationConstraint ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createDurationConstraint();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9e1f3bd2-cfd5-4b98-8d69-cb609fcaf3aa")
        @Override
        public Object caseDurationInterval(org.eclipse.uml2.uml.DurationInterval ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ef803236-77d1-4c09-ba19-8f2828ee8da2")
        @Override
        public Object caseDurationObservation(org.eclipse.uml2.uml.DurationObservation ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ae5baf91-8ef2-4e00-b2f1-75fb709f44a2")
        @Override
        public Object caseElementImport(org.eclipse.uml2.uml.ElementImport ecoreElt) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            
            org.eclipse.uml2.uml.PackageableElement ecoreImported = ecoreElt.getImportedElement();
            ecoreElt.getImportingNamespace();
            
            // with the ecore  take de ModelioElement
            NameSpace objingImported = (NameSpace) revProp
                    .getMappedElement(ecoreImported);
            //  set to the PartialCreationImportVisitor.this.objingElt Imported Importing previousely find
            if ((objingImported != null)  && !(objingImported instanceof Profile)){
            
                PartialCreationImportVisitor.this.objingElt = this.factory.createElementImport();
            }else {
                PartialCreationImportVisitor.this.objingElt = null;
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a8e37a0c-9c63-4dc9-945e-31fbb294fc56")
        @Override
        public Object caseEnumeration(org.eclipse.uml2.uml.Enumeration ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createEnumeration();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("cf4e50a9-6fc4-4bb7-a4c2-88b3f30e9cbe")
        @Override
        public Object caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createEnumerationLiteral();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9073953d-b04a-4756-a525-f78a21688693")
        @Override
        public Object caseExceptionHandler(ExceptionHandler ecoreElt) {
            PartialCreationImportVisitor.this.objingElt =  this.factory.createExceptionHandler();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("513fe905-325b-4d22-a324-ad4baa707cf1")
        @Override
        public Object caseExecutionEnvironment(org.eclipse.uml2.uml.ExecutionEnvironment ecoreElt) {
            Node result = this.factory.createNode();
            
            try {
                result.getExtension().add(this.mmServices
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2EXECUTIONENVIRONMENT, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("74e59700-f859-4dc6-8387-807a685ba6f7")
        @Override
        public Object caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent ecoreElt) {
            ecoreElt.getOwner();     
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2EXECUTIONEVENT, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("bdafdcf8-d490-4e71-aa84-26f129dd6348")
        @Override
        public Object caseExecutionOccurrenceSpecification(org.eclipse.uml2.uml.ExecutionOccurrenceSpecification ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExecutionOccurenceSpecification();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("23420961-71f5-4379-9bf5-8d80d814a672")
        @Override
        public Object caseExpansionNode(org.eclipse.uml2.uml.ExpansionNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExpansionNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9c07aa55-25a9-4ffb-a0de-9e2bb0a0340c")
        @Override
        public Object caseExpansionRegion(org.eclipse.uml2.uml.ExpansionRegion ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExpansionRegion();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9aee725b-c001-4b8f-be0f-409aef81792f")
        @Override
        public Object caseExpression(org.eclipse.uml2.uml.Expression ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("70d68daa-c4e7-42e0-9ed9-384542d1d068")
        @Override
        public Object caseExtend(org.eclipse.uml2.uml.Extend ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createUseCaseDependency();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7fa98935-e98e-4a96-83b7-366e467c77a2")
        @Override
        public Object caseExtension(org.eclipse.uml2.uml.Extension ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("471aa103-6d20-4b44-98f1-8ca239c7eb78")
        @Override
        public Object caseExtensionEnd(org.eclipse.uml2.uml.ExtensionEnd ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("89c3f7d4-64cf-4888-9354-e4bd74958f67")
        @Override
        public Object caseExtensionPoint(org.eclipse.uml2.uml.ExtensionPoint ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExtensionPoint();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("c97571db-5d00-4d73-b878-3e7b9bc6a9d7")
        @Override
        public Object caseFinalState(FinalState ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createFinalState();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("1193d45a-136a-4a1f-8752-1675ded7e3b5")
        @Override
        public Object caseFlowFinalNode(org.eclipse.uml2.uml.FlowFinalNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createFlowFinalNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("42445692-a71b-4e78-8bc6-5af6cf0ee6c3")
        @Override
        public Object caseForkNode(org.eclipse.uml2.uml.ForkNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createForkJoinNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("aa6ae848-b642-4ceb-9201-637a0927f7b6")
        @Override
        public Object caseFunctionBehavior(org.eclipse.uml2.uml.FunctionBehavior ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("5e345f37-0825-4067-9bd7-9b929db09888")
        @Override
        public Object caseGate(org.eclipse.uml2.uml.Gate ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createGate();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2b7e0a3e-ef7f-48ca-81e6-ffc7ad68417d")
        @Override
        public Object caseGeneralOrdering(org.eclipse.uml2.uml.GeneralOrdering ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createGeneralOrdering();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("609323ed-e349-4cb8-ae60-169210a46c8d")
        @Override
        public Object caseGeneralization(org.eclipse.uml2.uml.Generalization ecoreElt) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            
            org.eclipse.uml2.uml.Classifier ecoreGeneral = ecoreElt.getGeneral();
            org.eclipse.uml2.uml.Classifier ecoreSpecific = ecoreElt.getSpecific();
            
            Element objingGeneral = (Element) revProp
                    .getMappedElement(ecoreGeneral);
            Element objingSpecific = (Element) revProp
                    .getMappedElement(ecoreSpecific);
            
            if (objingGeneral instanceof NameSpace
                    && objingSpecific instanceof NameSpace
                    && (!EcorePrimitiveTypeMapper.isPredefinedType(ecoreGeneral))) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createGeneralization();
            }else{
                String message = Xmi.I18N.getMessage("logFile.warning.unsupportedEnds", " "
                        , "Generalization", objingGeneral.getClass().getSimpleName(), objingSpecific.getClass().getSimpleName());
                ReverseProperties.getInstance().addWarning(message);
                PartialCreationImportVisitor.this.objingElt = null;
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2c2658a3-354f-4d43-abc4-d4eda17b9fcb")
        @Override
        public Object caseGeneralizationSet(org.eclipse.uml2.uml.GeneralizationSet ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("88526f8c-1364-4367-9796-64768dea7d5a")
        @Override
        public Object caseImage(org.eclipse.uml2.uml.Image ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("14ab6b9a-e9d1-41b7-b20b-abbf6b53bebb")
        @Override
        public Object caseInclude(org.eclipse.uml2.uml.Include ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createUseCaseDependency();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2dae643d-21be-4483-9eac-0d1a4eb51767")
        @Override
        public Object caseInformationFlow(org.eclipse.uml2.uml.InformationFlow ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInformationFlow();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("bf9bb6a9-983d-4e93-8c4c-c0c2f7c96635")
        @Override
        public Object caseInformationItem(org.eclipse.uml2.uml.InformationItem ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInformationItem();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("69eed587-bf19-47b0-8c7f-4f08c374b34a")
        @Override
        public Object caseInitialNode(org.eclipse.uml2.uml.InitialNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInitialNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("6153e2f6-f0e0-4c38-b6de-5b6982489d31")
        @Override
        public Object caseInputPin(org.eclipse.uml2.uml.InputPin ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInputPin();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("f0108fc1-7878-4524-b5ed-174636c5db8d")
        @Override
        public Object caseInstanceSpecification(InstanceSpecification ecoreElt) {
            if (ReverseProperties.getInstance().isRoundtripEnabled()){
                if (ObjingEAnnotation.isConnector(ecoreElt)){
                    PartialCreationImportVisitor.this.objingElt =  this.factory.createConnector();
                    return PartialCreationImportVisitor.this.defaut;
                }else if (ObjingEAnnotation.isLink(ecoreElt)){
                    int nbEnds = ecoreElt.getSlots().size();
                    if (nbEnds == 2){
                        PartialCreationImportVisitor.this.objingElt =  this.factory.createLink();
                        return PartialCreationImportVisitor.this.defaut;
                    }else if (nbEnds > 2){
                        PartialCreationImportVisitor.this.objingElt =  this.factory.createNaryLink();
                        return PartialCreationImportVisitor.this.defaut;
                    }
                }
            
                PartialCreationImportVisitor.this.objingElt =  this.factory.createInstance();
                return PartialCreationImportVisitor.this.defaut;
            
            }else{
            
                if (EcoreModelNavigation.isAssocInstance(ecoreElt)){
                    if (EcoreModelNavigation.isConnector(ecoreElt)){
                        PartialCreationImportVisitor.this.objingElt =  this.factory.createConnector();
                        return PartialCreationImportVisitor.this.defaut;
                    }else  if (EcoreModelNavigation.isAssocInstance(ecoreElt)){
                        PartialCreationImportVisitor.this.objingElt =  this.factory.createLink();
                        return PartialCreationImportVisitor.this.defaut;
                    }
            
                }
                PartialCreationImportVisitor.this.objingElt =  this.factory.createInstance();
                return PartialCreationImportVisitor.this.defaut;
            }
            
        }

        @objid ("6fe35662-87ae-4159-b718-05877105ea7b")
        @Override
        public Object caseInstanceValue(InstanceValue ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("bc0f1779-8ff5-4474-b74b-8dfe8c61ff47")
        @Override
        public Object caseInteraction(org.eclipse.uml2.uml.Interaction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInteraction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e733fee1-7eb4-4b60-9bf5-983a37da244f")
        @Override
        public Object caseInteractionConstraint(org.eclipse.uml2.uml.InteractionConstraint ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2b9e9f5e-d24a-44e6-b46b-e5eab37f063f")
        @Override
        public Object caseInteractionOperand(org.eclipse.uml2.uml.InteractionOperand ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInteractionOperand();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("fdf29ad0-56f4-43d7-9b4a-7d8fec6d1c1d")
        @Override
        public Object caseInteractionUse(org.eclipse.uml2.uml.InteractionUse ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInteractionUse();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("5e1307ca-2bd3-49ff-bb16-dd82c8c1156f")
        @Override
        public Object caseInterface(org.eclipse.uml2.uml.Interface ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInterface();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("1b10f6ed-4487-4822-a3dc-33b44836024c")
        @Override
        public Object caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization ecoreElt) {
            if ((ecoreElt.getClients().size() > 0) && (ecoreElt.getClients().get(0) instanceof org.eclipse.uml2.uml.Port))
                PartialCreationImportVisitor.this.objingElt = this.factory.createProvidedInterface();
            else if (((ecoreElt.getClients().size() > 0) && (ecoreElt.getClients().get(0) instanceof org.eclipse.uml2.uml.Interface))
                    || (ecoreElt.getContract() != null))
                PartialCreationImportVisitor.this.objingElt =  this.factory.createInterfaceRealization();
            else 
                PartialCreationImportVisitor.this.objingElt =  null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("733e4661-a4e3-4673-81f0-752b58d1c1e5")
        @Override
        public Object caseInterruptibleActivityRegion(org.eclipse.uml2.uml.InterruptibleActivityRegion ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createInterruptibleActivityRegion();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("61342a43-a016-4fda-a8f9-3141c49a6558")
        @Override
        public Object caseInterval(org.eclipse.uml2.uml.Interval ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("cfd9b904-97bd-414f-907a-89c8445c02c9")
        @Override
        public Object caseIntervalConstraint(org.eclipse.uml2.uml.IntervalConstraint ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("c79c5f8d-6ca2-4131-a9b2-afee8ffb45a0")
        @Override
        public Object caseJoinNode(org.eclipse.uml2.uml.JoinNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createForkJoinNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("8d69daad-a340-4e35-b6f5-8ca69f053a7c")
        @Override
        public Object caseLifeline(org.eclipse.uml2.uml.Lifeline ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createLifeline();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("09f7c0da-a08b-4453-a62e-d2281fa4a957")
        @Override
        public Object caseLinkEndCreationData(org.eclipse.uml2.uml.LinkEndCreationData ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("65d789e1-1da0-4e6a-9897-1903fb4b31f8")
        @Override
        public Object caseLinkEndData(org.eclipse.uml2.uml.LinkEndData ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9905446b-6080-4b3e-aa4c-c77444d06ec6")
        @Override
        public Object caseLinkEndDestructionData(org.eclipse.uml2.uml.LinkEndDestructionData ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("fd47974b-4efd-4dce-ae9a-55843dde574e")
        @Override
        public Object caseLiteralBoolean(org.eclipse.uml2.uml.LiteralBoolean ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b19174c0-80ec-4582-ab70-2672cf91c784")
        @Override
        public Object caseLiteralInteger(org.eclipse.uml2.uml.LiteralInteger ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b37e658d-2569-4f44-a12a-2bb6e121c0cd")
        @Override
        public Object caseLiteralNull(org.eclipse.uml2.uml.LiteralNull ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("756e1502-3ecc-4a12-97ec-c6cbfef2362d")
        @Override
        public Object caseLiteralString(org.eclipse.uml2.uml.LiteralString ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ba821de9-44f5-40b0-bc1e-eecf35ea4e7c")
        @Override
        public Object caseLiteralUnlimitedNatural(org.eclipse.uml2.uml.LiteralUnlimitedNatural ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("bbdaa933-641a-4108-b747-6ae11f91138a")
        @Override
        public Object caseLoopNode(org.eclipse.uml2.uml.LoopNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createLoopNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("078f50e9-d1d3-46d3-89e2-58215efd9016")
        @Override
        public Object caseManifestation(org.eclipse.uml2.uml.Manifestation ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = new ArrayList<Manifestation>();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d47aa9eb-9f1b-4be8-83de-49219e43d5f6")
        @Override
        public Object caseMergeNode(org.eclipse.uml2.uml.MergeNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createDecisionMergeNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2bfd30d5-a603-4f4e-801b-a2efea495724")
        @Override
        public Object caseMessage(org.eclipse.uml2.uml.Message ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createMessage();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a4f04dc2-ac84-4a65-8517-f61937fe9874")
        @Override
        public Object caseMessageOccurrenceSpecification(org.eclipse.uml2.uml.MessageOccurrenceSpecification ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExecutionOccurenceSpecification();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ac60030a-6b5b-4d34-83cd-9477fe69000e")
        @Override
        public Object caseModel(org.eclipse.uml2.uml.Model ecoreElt) {
            boolean root = false;
            for (org.eclipse.uml2.uml.Package ecoremodel : ReverseProperties.getInstance().getEcoreModels()){
                if (ecoreElt.equals(ecoremodel)){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createPackage();
                    root = true;
                }
            }
            
            if (!root) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createPackage();
                ((org.modelio.metamodel.uml.statik.Package)PartialCreationImportVisitor.this.objingElt).setName(ecoreElt.getName());
                ((org.modelio.metamodel.uml.statik.Package)PartialCreationImportVisitor.this.objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage());
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("6e432f37-4913-4b4f-a0dd-159e4976c55f")
        @Override
        public Object caseNode(org.eclipse.uml2.uml.Node ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("3f46962e-e74e-4434-9385-d5e0dcbb5924")
        @Override
        public Object caseObjectFlow(ObjectFlow ecoreElt) {
            org.eclipse.uml2.uml.ActivityNode ecoreSource = ecoreElt.getSource();
            org.eclipse.uml2.uml.ActivityNode ecoreTarget = ecoreElt.getTarget();
            if ((ecoreSource != null) && (ecoreTarget != null)) {
                Object objingSource = ReverseProperties.getInstance().getMappedElement(ecoreSource);
                if ((objingSource != null)&& (objingSource instanceof ActivityNode)){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createObjectFlow();
                }else{
                    PartialCreationImportVisitor.this.objingElt =  null;
                }
            }else{
                PartialCreationImportVisitor.this.objingElt =  null;
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d2cc2824-12a5-4c21-9aab-c25792202ce3")
        @Override
        public Object caseOccurrenceSpecification(org.eclipse.uml2.uml.OccurrenceSpecification ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createExecutionOccurenceSpecification();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("da8ee471-cf67-4f0d-97fc-3e1df77ac224")
        @Override
        public Object caseOpaqueAction(org.eclipse.uml2.uml.OpaqueAction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createOpaqueAction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("682e90a3-44d9-421d-abb1-8b7a9f4911b9")
        @Override
        public Object caseOpaqueBehavior(org.eclipse.uml2.uml.OpaqueBehavior ecoreElt) {
            org.eclipse.uml2.uml.Element ecoreOwner = ecoreElt.getOwner();
            ReverseProperties revProp = ReverseProperties.getInstance();
            Object objOwner = revProp.getMappedElement(ecoreOwner);
            
            if (objOwner instanceof Transition){                    
                PartialCreationImportVisitor.this.objingElt = null;
            }else if ((objOwner instanceof State) && (ecoreOwner instanceof org.eclipse.uml2.uml.State)){                               
                PartialCreationImportVisitor.this.objingElt = null;
            }else
                PartialCreationImportVisitor.this.objingElt = this.factory.createOpaqueBehavior();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ff5d1a02-25b2-4d26-97de-e63ed8ffb0e1")
        @Override
        public Object caseOpaqueExpression(org.eclipse.uml2.uml.OpaqueExpression ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a216ed2e-6b1f-4b31-951c-78602554270c")
        @Override
        public Object caseOperation(org.eclipse.uml2.uml.Operation ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createOperation();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("680d6ecf-430c-40b4-873a-2a17f2fe56b4")
        @Override
        public Object caseOperationTemplateParameter(org.eclipse.uml2.uml.OperationTemplateParameter ecoreElt) {
            Parameter result = this.factory.createParameter();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OPERATIONTEMPLATEPARAMETER, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("233f19d6-5cc1-474c-8e74-9e3fbe6627b4")
        @Override
        public Object caseOutputPin(org.eclipse.uml2.uml.OutputPin ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createOutputPin();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("6a12a31b-3c14-4891-894d-b02f33dff502")
        @Override
        public Object casePackage(org.eclipse.uml2.uml.Package ecoreElt) {
            //            for (Package ecoremodel : ReverseProperties.getInstance().getEcoreModels()){
            //                if (ecoreElt.equals(ecoremodel)){
            //                    PartialCreationImportVisitor.this.objingElt = this.factory.createPackage();
            //                }
            //            }
            //            
            //            if (ObjingEAnnotation.isRequirementContainer(ecoreElt))
            //                PartialCreationImportVisitor.this.objingElt = this.factory.createPackage();
            //            else{
            PartialCreationImportVisitor.this.objingElt = this.factory.createPackage();
            //            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("c3515fc4-e471-46f8-b880-2b6858392414")
        @Override
        public Object casePackageImport(org.eclipse.uml2.uml.PackageImport ecoreElt) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            org.eclipse.uml2.uml.Package ecoreImported = ecoreElt.getImportedPackage();
            
            org.eclipse.uml2.uml.Package model = EcoreModelNavigation.getRoot(ecoreImported);
            
            if  ((model != null)  && revProp.getEcoreModels().contains(model) ){
                PartialCreationImportVisitor.this.objingElt = this.factory.createPackageImport();
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e9c2d2e5-56e0-46af-9931-82bc1c33b848")
        @Override
        public Object casePackageMerge(org.eclipse.uml2.uml.PackageMerge ecoreElt) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            
            //  take the ecore Imported and Importing
            org.eclipse.uml2.uml.Package ecoreMergedPackage = ecoreElt.getMergedPackage();
            org.eclipse.uml2.uml.Package ecoreReceivingPackage = ecoreElt.getReceivingPackage();
            
            org.eclipse.uml2.uml.Package objingMergedPackage = (org.eclipse.uml2.uml.Package) revProp
                    .getMappedElement(ecoreMergedPackage);
            org.eclipse.uml2.uml.Package objingReceivingPackage = (org.eclipse.uml2.uml.Package) revProp
                    .getMappedElement(ecoreReceivingPackage);
            if ((objingMergedPackage != null) && (objingReceivingPackage != null))
                PartialCreationImportVisitor.this.objingElt = this.factory.createPackageMerge();
            else 
                PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7ce87805-de82-44d9-850a-0ef7c6519e54")
        @Override
        public Object caseParameter(org.eclipse.uml2.uml.Parameter ecoreElt) {
            boolean isBehavior = !(ReverseProperties.getInstance().getMappedElement(ecoreElt.getOwner()) instanceof Operation);
            
            if (isBehavior)
                PartialCreationImportVisitor.this.objingElt = this.factory.createBehaviorParameter();
            else 
                PartialCreationImportVisitor.this.objingElt = this.factory.createParameter();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("0045a7ed-5511-4d2a-8699-73de01f62687")
        @Override
        public Object caseParameterSet(org.eclipse.uml2.uml.ParameterSet ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("2d3e576e-bca9-4122-9b70-f2c82c17970f")
        @Override
        public Object casePartDecomposition(org.eclipse.uml2.uml.PartDecomposition ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createPartDecomposition();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("5ddf70f3-0830-4095-a562-54045130ba27")
        @Override
        public Object casePin(org.eclipse.uml2.uml.Pin ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d67aade1-d7a3-431a-ace8-455010d3235a")
        @Override
        public Object casePort(org.eclipse.uml2.uml.Port ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createPort();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("70fbe1f2-e0ca-4085-8e32-1332fe0cb0dc")
        @Override
        public Object casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType ecoreElt) {
            if (EcorePrimitiveTypeMapper.isPredefinedType(ecoreElt))
                PartialCreationImportVisitor.this.objingElt = EcorePrimitiveTypeMapper.getPredefinedType(ecoreElt);
            else 
                PartialCreationImportVisitor.this.objingElt = this.factory.createDataType();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b723b4d6-6fc8-4b6e-b40b-476af06097be")
        @Override
        public Object caseProfile(org.eclipse.uml2.uml.Profile ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = ProfileUtils.createObjProfile(ecoreElt);
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e831bf02-5dc1-4a14-aa70-e11156335b1e")
        @Override
        public Object caseProfileApplication(org.eclipse.uml2.uml.ProfileApplication ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("51d9b84b-8b99-4685-b6c3-2a3e005d6c97")
        @Override
        public Object caseProperty(Property ecoreElt) {
            org.eclipse.uml2.uml.Element ecoreOwner = ecoreElt.getOwner();
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Stereotype){
            
                ProfileUtils.visitProperty(ecoreElt);
                PartialCreationImportVisitor.this.objingElt = null;
            
            }else{
                List<UmlModelElement> result = new ArrayList<>();
            
                if (EcoreModelNavigation.isAssocEnd(ecoreElt)) {    
            
                    int endNumber = EcoreModelNavigation.getValidEndNumber(ecoreElt.getAssociation());
            
                    if (endNumber == 2){
                        result.add(this.factory.createAssociationEnd());
                    }  else if (endNumber > 2){
                        result.add(this.factory.createNaryAssociationEnd());
                    }
                } 
            
                if (EcoreModelNavigation.isPart(ecoreElt) ){
                    BindableInstance inst = this.factory.createBindableInstance();
                    if (EcoreModelNavigation.isAssocEnd(ecoreElt)){
                        inst.setRepresentedFeature(result.get(0));
                    }
                    result.add(inst);           
                } else if (EcoreModelNavigation.isPort(ecoreElt)){
                    result.add(this.factory.createPort());
                }
            
                if (EcoreModelNavigation.isConnectorEnd(ecoreElt)){
                    result.add(this.factory.createBindableInstance());
                }
            
                if (result.size() == 0){
            
                    Element objingOwner = (Element) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            
                    if ((objingOwner instanceof Classifier) ||  
                            ((ecoreOwner instanceof Property) 
                                    && (EcoreModelNavigation.isAssocEnd((Property)ecoreOwner))))
                        result.add(this.factory.createAttribute());
                    else 
                        result.add(this.factory.createBindableInstance());
                }
            
                PartialCreationImportVisitor.this.objingElt = result;
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("f255fe48-6ea0-4cc6-ad28-d9892c2ae75c")
        @Override
        public Object caseProtocolConformance(org.eclipse.uml2.uml.ProtocolConformance ecoreElt) {
            Dependency result = this.factory.createDependency();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2PROTOCOLCONFORMANCE, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("77687df6-3bdb-4ac3-ad7b-78bd284ece33")
        @Override
        public Object caseProtocolStateMachine(org.eclipse.uml2.uml.ProtocolStateMachine ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createStateMachine();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9ca1c689-383b-40cb-96ab-dd53dad4c395")
        @Override
        public Object caseProtocolTransition(org.eclipse.uml2.uml.ProtocolTransition ecoreElt) {
            if (EcoreModelNavigation.isInternalTransition(ecoreElt)) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createInternalTransition();
            } else {
                PartialCreationImportVisitor.this.objingElt = this.factory.createTransition();
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9e5ef6b3-51ea-44e5-8aba-3dabb32e9d8a")
        @Override
        public Object casePseudostate(org.eclipse.uml2.uml.Pseudostate ecoreElt) {
            switch (ecoreElt.getKind().getValue()) {
            case org.eclipse.uml2.uml.PseudostateKind.INITIAL:
                PartialCreationImportVisitor.this.objingElt = this.factory.createInitialPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.DEEP_HISTORY:
                PartialCreationImportVisitor.this.objingElt = this.factory.createDeepHistoryPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.SHALLOW_HISTORY:
                PartialCreationImportVisitor.this.objingElt = this.factory.createShallowHistoryPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.JOIN:
                PartialCreationImportVisitor.this.objingElt = this.factory.createJoinPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.FORK:
                PartialCreationImportVisitor.this.objingElt = this.factory.createForkPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.JUNCTION:
                PartialCreationImportVisitor.this.objingElt = this.factory.createJunctionPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.CHOICE:
                PartialCreationImportVisitor.this.objingElt = this.factory.createChoicePseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.ENTRY_POINT:
                PartialCreationImportVisitor.this.objingElt = this.factory.createEntryPointPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.EXIT_POINT:
                PartialCreationImportVisitor.this.objingElt = this.factory.createExitPointPseudoState();
            
                break;
            case org.eclipse.uml2.uml.PseudostateKind.TERMINATE:
                PartialCreationImportVisitor.this.objingElt = this.factory.createTerminatePseudoState();
            
                break;
            default:
                PartialCreationImportVisitor.this.objingElt = this.factory.createInitialPseudoState();
                break;
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("5efb92bb-f845-4a91-b06b-50fcde326605")
        @Override
        public Object caseQualifierValue(org.eclipse.uml2.uml.QualifierValue ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("63f4ec10-1c88-4e7a-8f2e-a99392134156")
        @Override
        public Object caseRaiseExceptionAction(org.eclipse.uml2.uml.RaiseExceptionAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RAISEEXCEPTIONACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("84118758-c1d2-4c17-8821-b33ebd2bfc2b")
        @Override
        public Object caseReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READEXTENTACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b816c409-d4b6-4943-85dd-ee70b1af9d7c")
        @Override
        public Object caseReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READISCLASSIFIEROBJECTACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("77edc8cb-f920-45a3-8e9a-28ec7ffd1e0d")
        @Override
        public Object caseReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READLINKACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("cd1c6271-b1f8-489c-ab16-fc495338f7aa")
        @Override
        public Object caseReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READLINKOBJECTENDQUALIFIERACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("92249c27-22ec-4b2a-bba5-3f46aa569fd7")
        @Override
        public Object caseReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READLINKOBJECTENDQUALIFIERACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("032cf3d2-0644-48ad-b376-e0244264c95a")
        @Override
        public Object caseReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READSELFACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("3d49e012-ce7b-45bd-8071-dadbb7d96909")
        @Override
        public Object caseReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READSTRUCTURALFEATUREACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("4889d11f-d500-48c8-9979-16614c776060")
        @Override
        public Object caseReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2READVARIABLEACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("8da10c3e-57aa-434e-8e43-6047f9f6cdd2")
        @Override
        public Object caseRealization(org.eclipse.uml2.uml.Realization ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = new ArrayList<ElementRealization>();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7fa754a7-9790-4cee-8ca0-8f768092b32d")
        @Override
        public Object caseReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d0594151-ab5a-4339-aaab-6e2f3e49ba86")
        @Override
        public Object caseReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("00318dfe-8b76-4b44-8208-70d089694e1a")
        @Override
        public Object caseReception(org.eclipse.uml2.uml.Reception ecoreElt) {
            Operation result = this.factory.createOperation();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RECEPTION, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ab6213db-6db9-40b9-82e9-02209eaf6ee8")
        @Override
        public Object caseReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RECLASSIFYOBJECTACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("7f415685-8ea0-49c8-aafd-6d3b3058a07f")
        @Override
        public Object caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature ecoreElt) {
            org.eclipse.uml2.uml.Element ecoreOwner = ecoreElt.getOwner();
            Object objingOwner =  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            if ((objingOwner instanceof Classifier)  && !(objingOwner instanceof Enumeration)){
            
                Operation result = this.factory.createOperation();
            
                try {
                    result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REDEFINABLETEMPLATESIGNATURE, result.getMClass()));
                } catch (IllegalArgumentException | ElementNotUniqueException e) {
                    Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
                }
            
                PartialCreationImportVisitor.this.objingElt = result;
            }else 
                PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("32bac097-0689-4424-a2ad-b184f5e52033")
        @Override
        public Object caseReduceAction(org.eclipse.uml2.uml.ReduceAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REDUCEACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e2a0c07c-a33e-4fa0-a8d7-96f110fdd6e1")
        @Override
        public Object caseRegion(org.eclipse.uml2.uml.Region ecoreElt) {
            org.eclipse.uml2.uml.Element ecoreOwner = ecoreElt.getOwner();
            
            if  ( ecoreOwner instanceof org.eclipse.uml2.uml.StateMachine){
                PartialCreationImportVisitor.this.objingElt = ((StateMachine) ReverseProperties.getInstance().getMappedElement(ecoreOwner)).getTop();
            }else
                PartialCreationImportVisitor.this.objingElt = this.factory.createRegion();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("1ec17250-0955-496a-a6d0-8e9834b11551")
        @Override
        public Object caseRemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REMOVESTRUCTURALFEATUREACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d409af36-e534-494b-9a8c-97b55d8cfa58")
        @Override
        public Object caseRemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REMOVEVARIABLEVALUEACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("3137e59b-879e-40d3-bace-034a1bde7807")
        @Override
        public Object caseReplyAction(org.eclipse.uml2.uml.ReplyAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REPLYACTION,   result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("10b348bd-8a26-44ee-a865-e65832c48f14")
        @Override
        public Object caseSendObjectAction(org.eclipse.uml2.uml.SendObjectAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2SENDOBJECTACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("3a837308-701d-49d7-b036-af6eb5896371")
        @Override
        public Object caseSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b3856b1c-73db-4de6-9886-769adadc5d09")
        @Override
        public Object caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createSendSignalAction();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a881e691-bd07-4e1d-9ab8-2361e3eaea07")
        @Override
        public Object caseSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("980a0bcd-d2a3-4852-80ac-38361265f202")
        @Override
        public Object caseSequenceNode(org.eclipse.uml2.uml.SequenceNode ecoreElt) {
            StructuredActivityNode result =  this.factory.createStructuredActivityNode();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2SEQUENCENODE, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("64a0534a-afb4-4189-88ea-044652a7be3f")
        @Override
        public Object caseSignal(org.eclipse.uml2.uml.Signal ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createSignal();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("c7f3bfe4-6f52-4dfc-b3c9-71ec4673553e")
        @Override
        public Object caseSignalEvent(org.eclipse.uml2.uml.SignalEvent ecoreElt) {
            ecoreElt.getOwner();
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            result.setKind(EventType.SIGNALEVENT);
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("99e21472-2596-420b-bc7f-48a875c6a4a2")
        @Override
        public Object caseSlot(org.eclipse.uml2.uml.Slot ecoreElt) {
            InstanceSpecification ecoreOwner = ecoreElt.getOwningInstance();
            org.eclipse.uml2.uml.Element feature = ecoreElt.getDefiningFeature();
            
            if (ReverseProperties.getInstance().isRoundtripEnabled()){
                if (ObjingEAnnotation.isPort(ecoreElt)){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createPort();
                    return PartialCreationImportVisitor.this.defaut;
                }else if (ObjingEAnnotation.isBindableInstance(ecoreElt)){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createBindableInstance();
                    return PartialCreationImportVisitor.this.defaut;
                }else if (ObjingEAnnotation.isConnector(ecoreOwner)){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createConnectorEnd();
                    return PartialCreationImportVisitor.this.defaut;
                }else if (ObjingEAnnotation.isLink(ecoreOwner)){
                    if (ecoreOwner.getSlots().size() == 2 ){
                        PartialCreationImportVisitor.this.objingElt = this.factory.createLinkEnd();
                        return PartialCreationImportVisitor.this.defaut;
                    }else{ 
                        PartialCreationImportVisitor.this.objingElt = this.factory.createNaryLinkEnd();
                        return PartialCreationImportVisitor.this.defaut;
                    }
                }else if (ObjingEAnnotation.isAttributeLink(ecoreElt)){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createAttributeLink();
                }
            }
            
            if (feature != null){
            
                Object objFeature = ReverseProperties.getInstance().getMappedElement(feature);
            
                if  (feature instanceof org.eclipse.uml2.uml.Port){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createPort();
                }else if (objFeature  instanceof Attribute){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createAttributeLink();
                }else if (EcoreModelNavigation.isConnector(ecoreOwner) ){
                    PartialCreationImportVisitor.this.objingElt = this.factory.createConnectorEnd();
                }else if (EcoreModelNavigation.isAssocInstance(ecoreOwner)){
                    if (ecoreOwner.getSlots().size() == 2)
                        PartialCreationImportVisitor.this.objingElt = this.factory.createLinkEnd();
                    else 
                        PartialCreationImportVisitor.this.objingElt = this.factory.createNaryLinkEnd();  
                }else {
                    PartialCreationImportVisitor.this.objingElt = this.factory.createBindableInstance();
                }
            } else {
                PartialCreationImportVisitor.this.objingElt = this.factory.createBindableInstance();
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9a3b76c0-5d65-4b2e-a100-36047586a73f")
        @Override
        public Object caseStartClassifierBehaviorAction(org.eclipse.uml2.uml.StartClassifierBehaviorAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2STARTCLASSIFIERBEHAVIORACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("3d85d9fc-ceff-4082-acf5-6f05d738c3ce")
        @Override
        public Object caseState(org.eclipse.uml2.uml.State ecoreElt) {
            if (ecoreElt.getContainer() != null)
                PartialCreationImportVisitor.this.objingElt = this.factory.createState();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e8409e73-99ac-4f07-a0c0-348bef3cc1bd")
        @Override
        public Object caseStateInvariant(org.eclipse.uml2.uml.StateInvariant ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createStateInvariant();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("38b4150c-2e32-49dc-af6e-c392336162e0")
        @Override
        public Object caseStateMachine(org.eclipse.uml2.uml.StateMachine ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createStateMachine();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a175391d-bd2e-449f-9cc5-f36ca3114188")
        @Override
        public Object caseStereotype(org.eclipse.uml2.uml.Stereotype ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("fae2d7c7-812f-40c8-a338-c1e4c6fa24be")
        @Override
        public Object caseStringExpression(StringExpression ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("11e22218-6c62-4a38-a0c8-7e9b5b42c715")
        @Override
        public Object caseStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createStructuredActivityNode();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("01f8f0c8-aa16-4d92-9f29-399c79c8b440")
        @Override
        public Object caseSubstitution(org.eclipse.uml2.uml.Substitution ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createSubstitution();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9e058997-53c7-4086-b6fe-57d052112486")
        @Override
        public Object caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createTemplateBinding();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("54e31796-3dc5-4815-ba59-bd2e907e8a4f")
        @Override
        public Object caseTemplateParameter(org.eclipse.uml2.uml.TemplateParameter ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createTemplateParameter();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("5d7a8345-f143-4ca0-aead-19440e289de0")
        @Override
        public Object caseTemplateParameterSubstitution(org.eclipse.uml2.uml.TemplateParameterSubstitution ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createTemplateParameterSubstitution();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("56360787-83bc-4286-9dc7-cb970b6840ab")
        @Override
        public Object caseTemplateSignature(org.eclipse.uml2.uml.TemplateSignature ecoreElt) {
            if (ObjingEAnnotation.isDeleted(ecoreElt)){
                PartialCreationImportVisitor.this.objingElt = null;
            }else{
                org.eclipse.uml2.uml.Element ecoreOwner = ecoreElt.getOwner();
                Object objingOwner =  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
                if ((objingOwner instanceof Classifier)  && !(objingOwner instanceof Enumeration)){
                    Operation result = this.factory.createOperation();
            
                    try {
                        result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TEMPLATESIGNATURE, result.getMClass()));
                    } catch (ElementNotUniqueException e) {
                        Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
                    }
            
                    PartialCreationImportVisitor.this.objingElt = result;
                }
                PartialCreationImportVisitor.this.objingElt = null;
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("9f5a8e2a-598f-4d3b-aee0-262147f4f1f0")
        @Override
        public Object caseTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TESTIDENTITYACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("d54896f9-998d-46da-929d-0d7357ab5976")
        @Override
        public Object caseTimeConstraint(org.eclipse.uml2.uml.TimeConstraint ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("882bc93b-5f47-465d-8468-72470f74e52a")
        @Override
        public Object caseTimeEvent(org.eclipse.uml2.uml.TimeEvent ecoreElt) {
            ecoreElt.getOwner();
            org.modelio.metamodel.uml.behavior.commonBehaviors.Event result = this.factory.createEvent();
            result.setKind(EventType.TIMEEVENT);
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("ab564ad8-2c85-4724-8b72-e116274b41e5")
        @Override
        public Object caseTimeExpression(org.eclipse.uml2.uml.TimeExpression ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("bcc2630a-aa5b-4914-8c0e-6f5cdfb2c20b")
        @Override
        public Object caseTimeInterval(org.eclipse.uml2.uml.TimeInterval ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("a520c257-c376-4281-80f1-70b4dbf42ce8")
        @Override
        public Object caseTimeObservation(org.eclipse.uml2.uml.TimeObservation ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("0a33fbf3-412f-49d9-9316-12430a9ff52c")
        @Override
        public Object caseTransition(org.eclipse.uml2.uml.Transition ecoreElt) {
            if (EcoreModelNavigation.isInternalTransition(ecoreElt)) {
                PartialCreationImportVisitor.this.objingElt = this.factory.createInternalTransition();
            } else {
                PartialCreationImportVisitor.this.objingElt = this.factory.createTransition();
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("062378cc-1577-4a16-b5e6-d0db2109d8da")
        @Override
        public Object caseTrigger(org.eclipse.uml2.uml.Trigger ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = null;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("50aed903-7878-4b3b-9ebc-a208a40f1b80")
        @Override
        public Object caseUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2UNMARSHALLACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("0d93e8f8-5c6f-4bef-a135-5c851502b80f")
        @Override
        public Object caseUsage(org.eclipse.uml2.uml.Usage ecoreElt) {
            PartialCreationImportVisitor.this.objingElt =  new ArrayList<UmlModelElement>();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("85f1164b-51fc-4bb6-8c76-af20169de7e4")
        @Override
        public Object caseUseCase(org.eclipse.uml2.uml.UseCase ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createUseCase();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("4225f0c9-83a8-4c41-8640-519b3bb44449")
        @Override
        public Object caseValuePin(org.eclipse.uml2.uml.ValuePin ecoreElt) {
            PartialCreationImportVisitor.this.objingElt = this.factory.createValuePin();
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("b50f52b8-3e79-40c0-9b60-56b54c56473f")
        @Override
        public Object caseValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2VALUESPECIFICATIONACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("fb20a8fd-6996-4a95-83a9-17aac19c9227")
        @Override
        public Object caseVariable(org.eclipse.uml2.uml.Variable ecoreElt) {
            InstanceNode result = this.factory.createInstanceNode();
            
            try {
                result.getExtension().add(this.mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2VARIABLE, result.getMClass()));
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

        @objid ("e4c00aed-3704-4860-b369-5f773ab3826c")
        public  CreationImportMapper() {
            
        }

        @objid ("79bda372-ee0b-4992-8196-04aa3f2e6c99")
        @Override
        public Object caseStartObjectBehaviorAction(org.eclipse.uml2.uml.StartObjectBehaviorAction ecoreElt) {
            OpaqueAction result = this.factory.createOpaqueAction();
            
            try {
                Stereotype stereo = ReverseProperties.getInstance().getMModelServices()
                        .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2STARTOBJECTBEHAVIORACTION, result.getMClass());
                result.getExtension().add(stereo);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
            }
            
            PartialCreationImportVisitor.this.objingElt = result;
            return PartialCreationImportVisitor.this.defaut;
        }

    }

}
