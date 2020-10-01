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

@objid ("d6fe86ea-dfc0-4f62-9712-0f93aeef3d4a")
public class AttachOutputPinToOwnerVisitor {
    @objid ("c9916c76-e5bb-4023-9496-f9efde8c94fd")
     org.modelio.metamodel.uml.behavior.activityModel.OutputPin _obOutputPin = null;

    @objid ("8d19c955-48c3-4ed4-a18d-f6987468462f")
     OutputPinLinker linker = null;

    @objid ("581b746c-8a6b-4cc6-8749-ea43312b33c3")
     org.eclipse.uml2.uml.OutputPin _outputPin = null;

    @objid ("02271e0a-b358-4230-8938-83cc5faa68ea")
    public AttachOutputPinToOwnerVisitor() {
        this.linker = new OutputPinLinker();
    }

    @objid ("99e81ab6-4e96-4b0e-8fa0-c146b3604aac")
    public void attachOutputPin(org.eclipse.uml2.uml.OutputPin outputPin, org.modelio.metamodel.uml.behavior.activityModel.OutputPin obOutputPin, org.eclipse.uml2.uml.Element owner) {
        this._outputPin = outputPin;
        this._obOutputPin = obOutputPin;
        this.linker.accept(owner);
    }

    @objid ("5b088e5a-0bb4-4a7c-a1e8-01b904a33992")
    private class OutputPinLinker extends UMLSwitch<Object> {
        @objid ("1a8cd06a-3209-45b2-9e80-ceb5a8162bcd")
        private static final int result = 42;

        @objid ("c59aee15-dd3b-4d58-a0ab-73c5d6ba2aed")
        public void accept(org.eclipse.uml2.uml.Element ecoreElt) {
            doSwitch(ecoreElt);
        }

        @objid ("f4c8dd4e-3ed8-4c06-8700-49e2737b85c6")
        @Override
        public Object caseAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction owner) {
            if ((owner.getReturnInformation() == null)
                    && (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RETURNINFORMATION))){   
                owner.setReturnInformation(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("8ecb5608-ef78-4921-bc2d-badbef41ddd7")
        @Override
        public Object caseAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction owner) {
            if (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT)){   
                owner.getResults().add(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("3999f0c1-fd61-4b23-a6c8-59eced754c25")
        @Override
        public Object caseAction(org.eclipse.uml2.uml.Action owner) {
            AttachOutputPinToOwnerVisitor.this._outputPin.destroy();
            String  message = Xmi.I18N.getMessage("logFile.warning.notOwner", AttachOutputPinToOwnerVisitor.this._obOutputPin.getName());    
            GenerationProperties.getInstance().addWarning(message, AttachOutputPinToOwnerVisitor.this._obOutputPin);
            return result;
        }

        @objid ("b0155a20-3665-4bce-81cb-efb8f148332c")
        @Override
        public Object caseCallAction(org.eclipse.uml2.uml.CallAction owner) {
            owner.getResults().add(AttachOutputPinToOwnerVisitor.this._outputPin);
            return result;
        }

        @objid ("2ad1563d-e571-427c-b843-bd1078ba709a")
        @Override
        public Object caseConditionalNode(org.eclipse.uml2.uml.ConditionalNode owner) {
            if (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT)){   
                owner.getResults().add(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("de5ff16b-5ef5-43b2-bf97-e3eeb26a9cd3")
        @Override
        public Object caseCreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("a8764a89-c28c-4a22-b924-6f2174c41cd2")
        @Override
        public Object caseCreateObjectAction(org.eclipse.uml2.uml.CreateObjectAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("44a9cf4e-2e49-4c8f-9ffc-f74938357736")
        @Override
        public Object caseLoopNode(org.eclipse.uml2.uml.LoopNode owner) {
            if (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT)){   
                owner.getResults().add(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            } else if (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DECIDER)){  
                owner.setDecider(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("50108cde-1798-4526-ac6a-242e197a8a4e")
        @Override
        public Object caseOpaqueAction(org.eclipse.uml2.uml.OpaqueAction owner) {
            owner.getOutputValues().add(AttachOutputPinToOwnerVisitor.this._outputPin);
            return result;
        }

        @objid ("75e25fc9-1471-4cee-a0bc-f000d45a44c4")
        @Override
        public Object caseReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("3dfdd97d-17d7-4fe6-8f62-c431c471aac7")
        @Override
        public Object caseReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("fd2e8742-e2c1-4e9c-99e0-31bc0bc40105")
        @Override
        public Object caseReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("8b702919-e9f5-4488-80a4-af4a528c1fae")
        @Override
        public Object caseReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("f982d937-9e53-4c6f-9751-ac92b691e53d")
        @Override
        public Object caseReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("6a05e0d9-6c29-4c67-a003-7897798d272e")
        @Override
        public Object caseReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("8123257e-132f-422a-a761-8dfbccf73b2a")
        @Override
        public Object caseReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("0e8c3c5d-4d4a-4378-b83e-828b14da7d29")
        @Override
        public Object caseReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction owner) {
            if (owner.getResult() == null) {
                     owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("432e770a-41ed-4239-aa24-95dc3df6c549")
        @Override
        public Object caseReduceAction(org.eclipse.uml2.uml.ReduceAction owner) {
            if (owner.getResult() == null) {
                 owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("32a7e3ee-6630-4305-b13b-7ce0aa0efa3a")
        @Override
        public Object caseStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode owner) {
            owner.getNodes().add(AttachOutputPinToOwnerVisitor.this._outputPin);
            return result;
        }

        @objid ("f46f7072-686d-40d1-8e82-a8c83aabf75a")
        @Override
        public Object caseTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("a8662d82-88d9-4aaa-be99-0dbad593d775")
        @Override
        public Object caseUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction owner) {
            if (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT)){   
                owner.getResults().add(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("9368c496-da2a-4312-9a4d-bce4b9f2536d")
        @Override
        public Object caseValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("31aab515-92c3-4871-8962-ee6caa9ef4c3")
        @Override
        public Object caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction owner) {
            // pas supporter en UML2 
            AttachOutputPinToOwnerVisitor.this._outputPin.destroy();
            return null;
        }

        @objid ("88de026e-d26e-4abd-ae21-5935ebc1f770")
        @Override
        public Object caseAddStructuralFeatureValueAction(final org.eclipse.uml2.uml.AddStructuralFeatureValueAction owner) {
            if ((owner.getResult() == null)
                    && (AttachOutputPinToOwnerVisitor.this._obOutputPin.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT))){   
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("c77ec9af-2d73-4ed2-a9d2-9e9bc2a8e62b")
        @Override
        public Object caseClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

        @objid ("e149feed-c885-4cbd-a42d-4f71d531b4fc")
        @Override
        public Object caseWriteStructuralFeatureAction(final org.eclipse.uml2.uml.WriteStructuralFeatureAction owner) {
            if (owner.getResult() == null) {
                owner.setResult(AttachOutputPinToOwnerVisitor.this._outputPin);
                return result;
            }
            return null;
        }

    }

}
