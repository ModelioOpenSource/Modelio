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

package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("ac434936-9b12-44ec-8c01-64565dedc4fa")
public class AttachInputPinToOwnerVisitor {
    @objid ("94802832-5149-488b-8429-ffa09c634270")
     org.modelio.metamodel.uml.behavior.activityModel.InputPin _obInputPin = null;

    @objid ("f3f1a427-6c22-4c6c-afcf-98790f459d6c")
    private InputPinLinker _linker = null;

    @objid ("818c51c6-4b44-49f2-8c07-786b63753768")
     org.eclipse.uml2.uml.InputPin _inputPin = null;

    @objid ("c69b01a5-18de-428e-a477-0e9abde60509")
    public AttachInputPinToOwnerVisitor() {
        this._linker = new InputPinLinker();
    }

    @objid ("84dfd77b-f614-4e68-9876-ea21fc71a6eb")
    public void attachInputPin(org.eclipse.uml2.uml.InputPin inputPin, org.eclipse.uml2.uml.Element owner, org.modelio.metamodel.uml.behavior.activityModel.InputPin obInputPin) {
        this._inputPin = inputPin;
        this._obInputPin = obInputPin;
        this._linker.accept(owner);
    }

    @objid ("add5c07a-49ff-410f-aa1f-96efb68f00ee")
    class InputPinLinker extends UMLSwitch<Object> {
        @objid ("78481d98-c29a-4d55-8110-a301655fa038")
        private static final int result = 42;

        @objid ("985af50e-2d3a-462c-9e11-556f971093e4")
        public void accept(org.eclipse.uml2.uml.Element ecoreElt) {
            doSwitch(ecoreElt);
        }

        @objid ("e65e6bf8-9b3a-486e-a7a3-d15bd4879624")
        @Override
        public Object caseAction(org.eclipse.uml2.uml.Action owner) {
            AttachInputPinToOwnerVisitor.this._inputPin.destroy();
            String  message = Xmi.I18N.getMessage("logFile.warning.notOwner", AttachInputPinToOwnerVisitor.this._obInputPin.getName());    
            GenerationProperties.getInstance().addWarning(message, AttachInputPinToOwnerVisitor.this._obInputPin);
            return result;
        }

        @objid ("d11f6ff6-f9f6-42ab-9cda-3c29fcfaa16c")
        @Override
        public Object caseAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction owner) {
            if (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSERTAT)
                    && (owner.getInsertAt() == null )){ 
                owner.setInsertAt(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("00c66afe-590b-4cd7-9397-402dd93f95a7")
        @Override
        public Object caseAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction owner) {
            if ((owner.getInsertAt() == null ) 
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSERTAT))){
                owner.setInsertAt(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("21d210df-95ae-4043-a7f4-5322fb1a6199")
        @Override
        public Object caseCallOperationAction(org.eclipse.uml2.uml.CallOperationAction owner) {
            if ((owner.getTarget() == null ) 
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TARGET))){
                owner.setTarget(AttachInputPinToOwnerVisitor.this._inputPin);    
                return result;
            }
            return null;
        }

        @objid ("ee64d19e-fa5a-4d17-b9de-bc0ccfff2b48")
        @Override
        public Object caseClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction owner) {
            if (owner.getObject() == null ){
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("9575f84b-6b9b-4215-8bdd-a0576e672be9")
        @Override
        public Object caseDestroyObjectAction(org.eclipse.uml2.uml.DestroyObjectAction owner) {
            if (owner.getTarget() == null ){          
                owner.setTarget(AttachInputPinToOwnerVisitor.this._inputPin);           
                return result;
            }
            return null;
        }

        @objid ("274b623b-39c3-4072-8ff9-618da911bf3f")
        @Override
        public Object caseInvocationAction(org.eclipse.uml2.uml.InvocationAction owner) {
            owner.getArguments().add(AttachInputPinToOwnerVisitor.this._inputPin);
            return result;
        }

        @objid ("d4984007-6d5e-4c32-b66d-ad21d0ac704e")
        @Override
        public Object caseLinkAction(org.eclipse.uml2.uml.LinkAction owner) {
            owner.getInputValues().add(AttachInputPinToOwnerVisitor.this._inputPin);
            return result;
        }

        @objid ("615073d1-a498-4b2d-983a-30cbba5b8086")
        @Override
        public Object caseLoopNode(org.eclipse.uml2.uml.LoopNode owner) {
            if (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2LOOPVARIABLEINPUT)){  
                owner.getLoopVariableInputs().add(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("b07b1495-925e-4376-827b-ca6e53721294")
        @Override
        public Object caseOpaqueAction(org.eclipse.uml2.uml.OpaqueAction owner) {
            owner.getInputValues().add(AttachInputPinToOwnerVisitor.this._inputPin);
            return result;
        }

        @objid ("61ff8af4-81ff-4171-9796-c7872df7dc51")
        @Override
        public Object caseRaiseExceptionAction(org.eclipse.uml2.uml.RaiseExceptionAction owner) {
            if (owner.getException() == null ){  
                owner.setException(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("ee67ddf0-748d-4a86-a7f7-a046b738290a")
        @Override
        public Object caseReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("718e8303-ca25-4d73-8ada-a6f7a747c859")
        @Override
        public Object caseReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("9b418abd-cbc2-4c90-afda-d58d620ff18a")
        @Override
        public Object caseReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("6922ccaf-4def-4c57-b4ca-8c3449881fa2")
        @Override
        public Object caseReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("3a0e85a3-9414-4bbd-bb6e-6d8ccb174d68")
        @Override
        public Object caseReduceAction(org.eclipse.uml2.uml.ReduceAction owner) {
            if ((owner.getCollection() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2COLLECTION))){  
                owner.setCollection(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("6a16c869-2733-4993-862f-28410d6d336c")
        @Override
        public Object caseRemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction owner) {
            if ((owner.getRemoveAt() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REMOVEAT))){  
                owner.setRemoveAt(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("7ca5be10-a4b4-4a5f-a84a-dfbfbbbec4df")
        @Override
        public Object caseRemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction owner) {
            if ((owner.getRemoveAt() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REMOVEAT))){  
                owner.setRemoveAt(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("858adfe5-6fee-4d9e-b590-40b91a06cbac")
        @Override
        public Object caseReplyAction(org.eclipse.uml2.uml.ReplyAction owner) {
            if ( (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REPLYVALUE))){  
                owner.getReplyValues().add(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            else if ( (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RETURNINFORMATION)
                    && (owner.getReturnInformation() == null))){  
                owner.setReturnInformation(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("83dbe62a-c47e-4ab4-a3e2-c9470cd25616")
        @Override
        public Object caseSendObjectAction(org.eclipse.uml2.uml.SendObjectAction owner) {
            if ((owner.getRequest() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2REQUEST))){  
                owner.setRequest(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            } else if ((owner.getTarget() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TARGET))){  
                owner.setTarget(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("73457d7b-3a3a-43fb-be1c-77e433ef8d38")
        @Override
        public Object caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction owner) {
            if ((owner.getTarget() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TARGET))){  
                owner.setTarget(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("1bdc8aa4-5378-4bcc-a918-2bbb75619865")
        @Override
        public Object caseStartClassifierBehaviorAction(org.eclipse.uml2.uml.StartClassifierBehaviorAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("0ff996fb-f66f-42d8-ab7b-d001b247e1a7")
        @Override
        public Object caseStructuralFeatureAction(org.eclipse.uml2.uml.StructuralFeatureAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("e76d0729-7654-483d-96fd-8be90dcc783d")
        @Override
        public Object caseStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode owner) {
            if (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2NODE)){  
                owner.getNodes().add(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("20e43f64-aa98-484a-af5a-5bbd1af64a4e")
        @Override
        public Object caseTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction owner) {
            if ((owner.getFirst() == null) 
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2FIRST))){
                owner.setFirst(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }else if ((owner.getSecond() == null) 
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2SECOND))){
                owner.setSecond(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("8120533b-b564-4683-ac34-a41c67ca88b4")
        @Override
        public Object caseUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction owner) {
            if (owner.getObject() == null ){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("e790a878-75d9-428b-8716-a8a1c2dfb111")
        @Override
        public Object caseWriteStructuralFeatureAction(org.eclipse.uml2.uml.WriteStructuralFeatureAction owner) {
            if ((owner.getValue() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2VALUE))){  
                owner.setValue(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("f2a212b0-4d26-496c-b7d7-ecaf5ee8b266")
        @Override
        public Object caseWriteVariableAction(org.eclipse.uml2.uml.WriteVariableAction owner) {
            if (owner.getValue() == null ){  
                owner.setValue(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("d665fa4e-599e-43d2-8aa8-f10ac5383f44")
        @Override
        public Object caseReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

        @objid ("186e9d66-578a-43cc-9496-425b888e54c8")
        @Override
        public Object caseStartObjectBehaviorAction(org.eclipse.uml2.uml.StartObjectBehaviorAction owner) {
            if ((owner.getObject() == null )   
                    && (AttachInputPinToOwnerVisitor.this._obInputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2OBJECT))){  
                owner.setObject(AttachInputPinToOwnerVisitor.this._inputPin);
                return result;
            }
            return null;
        }

    }

}
