/* 
 * Copyright 2013-2018 Modeliosoft
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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnThrowEventSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataAssociationData;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemAwareElementSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnSequenceFlowDataAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("60a0168a-f7a2-4200-952d-c4446d0864aa")
public class BpmnDataAssociationSmClass extends BpmnBaseElementSmClass {
    @objid ("5de96da2-0319-46d0-bc02-a9ba49b7efc2")
    private SmAttribute assignmentAtt;

    @objid ("da2131b7-9be8-41de-994d-3553d817aea7")
    private SmAttribute transfomationAtt;

    @objid ("af1ba591-9888-4103-b18c-812d698e6eb5")
    private SmAttribute languageAtt;

    @objid ("b03c4800-6f2b-4384-adbf-aa7c58a21229")
    private SmDependency sourceRefDep;

    @objid ("f5d4f8a6-d301-4f30-8089-58b80f32e2e0")
    private SmDependency targetRefDep;

    @objid ("4fc2caa5-632a-4d1d-9b95-86e9c41caa77")
    private SmDependency endingActivityDep;

    @objid ("dd525f28-ec96-4f54-94e1-81d61dfa8364")
    private SmDependency startingActivityDep;

    @objid ("fa554939-b766-4697-aa63-07a758e9e669")
    private SmDependency startingEventDep;

    @objid ("0de845a5-4dd9-4181-9103-74b47372170c")
    private SmDependency visualShortCutDep;

    @objid ("cd1dac3e-6181-4633-ad3e-c3e53d2b5920")
    private SmDependency endingEventDep;

    @objid ("a02fed58-d3fb-448e-b7b8-b36f335764ce")
    public BpmnDataAssociationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("51576242-d871-45b9-8ff7-3b6c35c27369")
    @Override
    public String getName() {
        return "BpmnDataAssociation";
    }

    @objid ("859fb5b5-fab4-41e3-8832-1df30e08f74e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("278c808d-370f-4eba-9ada-fb4a4baa6dc2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnDataAssociation.class;
    }

    @objid ("03e9d623-1577-49c9-b95b-9e105784ce97")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("83db1ac6-4457-41a0-8558-bf1852169acb")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7cdfe2a4-bfbc-4b06-bbc8-0b0544ace3c0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnDataAssociationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.assignmentAtt = new AssignmentSmAttribute();
        this.assignmentAtt.init("Assignment", this, String.class );
        registerAttribute(this.assignmentAtt);
        
        this.transfomationAtt = new TransfomationSmAttribute();
        this.transfomationAtt.init("Transfomation", this, String.class );
        registerAttribute(this.transfomationAtt);
        
        this.languageAtt = new LanguageSmAttribute();
        this.languageAtt.init("Language", this, String.class );
        registerAttribute(this.languageAtt);
        
        
        // Initialize and register the SmDependency
        this.sourceRefDep = new SourceRefSmDependency();
        this.sourceRefDep.init("SourceRef", this, metamodel.getMClass(BpmnItemAwareElement.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.sourceRefDep);
        
        this.targetRefDep = new TargetRefSmDependency();
        this.targetRefDep.init("TargetRef", this, metamodel.getMClass(BpmnItemAwareElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.targetRefDep);
        
        this.endingActivityDep = new EndingActivitySmDependency();
        this.endingActivityDep.init("EndingActivity", this, metamodel.getMClass(BpmnActivity.MQNAME), 0, 1 );
        registerDependency(this.endingActivityDep);
        
        this.startingActivityDep = new StartingActivitySmDependency();
        this.startingActivityDep.init("StartingActivity", this, metamodel.getMClass(BpmnActivity.MQNAME), 0, 1 );
        registerDependency(this.startingActivityDep);
        
        this.startingEventDep = new StartingEventSmDependency();
        this.startingEventDep.init("StartingEvent", this, metamodel.getMClass(BpmnThrowEvent.MQNAME), 0, 1 );
        registerDependency(this.startingEventDep);
        
        this.visualShortCutDep = new VisualShortCutSmDependency();
        this.visualShortCutDep.init("VisualShortCut", this, metamodel.getMClass(BpmnSequenceFlowDataAssociation.MQNAME), 0, -1 );
        registerDependency(this.visualShortCutDep);
        
        this.endingEventDep = new EndingEventSmDependency();
        this.endingEventDep.init("EndingEvent", this, metamodel.getMClass(BpmnCatchEvent.MQNAME), 0, 1 );
        registerDependency(this.endingEventDep);
    }

    @objid ("c02530ea-1ef1-497b-a00c-fef9f3c65b2d")
    public SmAttribute getAssignmentAtt() {
        if (this.assignmentAtt == null) {
        	this.assignmentAtt = this.getAttributeDef("Assignment");
        }
        return this.assignmentAtt;
    }

    @objid ("31b58f5f-124a-4795-82e4-bb504d5614b9")
    public SmAttribute getTransfomationAtt() {
        if (this.transfomationAtt == null) {
        	this.transfomationAtt = this.getAttributeDef("Transfomation");
        }
        return this.transfomationAtt;
    }

    @objid ("858ebe2d-5fd1-4a1e-883f-6ac2cfbcf7b8")
    public SmAttribute getLanguageAtt() {
        if (this.languageAtt == null) {
        	this.languageAtt = this.getAttributeDef("Language");
        }
        return this.languageAtt;
    }

    @objid ("76a8a573-c9bb-47b0-b152-be8163a7243c")
    public SmDependency getSourceRefDep() {
        if (this.sourceRefDep == null) {
        	this.sourceRefDep = this.getDependencyDef("SourceRef");
        }
        return this.sourceRefDep;
    }

    @objid ("95f03666-413c-4e35-a71b-331f24049c01")
    public SmDependency getTargetRefDep() {
        if (this.targetRefDep == null) {
        	this.targetRefDep = this.getDependencyDef("TargetRef");
        }
        return this.targetRefDep;
    }

    @objid ("ce4cf619-090b-4d51-b255-63dfd42f952d")
    public SmDependency getEndingActivityDep() {
        if (this.endingActivityDep == null) {
        	this.endingActivityDep = this.getDependencyDef("EndingActivity");
        }
        return this.endingActivityDep;
    }

    @objid ("29800e3d-0ca8-49b1-a0af-386ed9eb6ccd")
    public SmDependency getStartingActivityDep() {
        if (this.startingActivityDep == null) {
        	this.startingActivityDep = this.getDependencyDef("StartingActivity");
        }
        return this.startingActivityDep;
    }

    @objid ("3d156c12-e352-4a5b-9aca-ffe9d191a143")
    public SmDependency getStartingEventDep() {
        if (this.startingEventDep == null) {
        	this.startingEventDep = this.getDependencyDef("StartingEvent");
        }
        return this.startingEventDep;
    }

    @objid ("9f2545c6-c2cf-49ba-a3f7-64b1c9491eb5")
    public SmDependency getVisualShortCutDep() {
        if (this.visualShortCutDep == null) {
        	this.visualShortCutDep = this.getDependencyDef("VisualShortCut");
        }
        return this.visualShortCutDep;
    }

    @objid ("3a6eab14-0495-4ea7-97b6-dee294b6a8c0")
    public SmDependency getEndingEventDep() {
        if (this.endingEventDep == null) {
        	this.endingEventDep = this.getDependencyDef("EndingEvent");
        }
        return this.endingEventDep;
    }

    @objid ("ca57127e-5b57-4ffa-9ad4-090ed8d9810b")
    private static class BpmnDataAssociationObjectFactory implements ISmObjectFactory {
        @objid ("13021d49-9eb9-4aa2-9869-d466b055b8f7")
        private BpmnDataAssociationSmClass smClass;

        @objid ("9f0c9a9d-f0f4-4e86-b956-b219779bc70d")
        public BpmnDataAssociationObjectFactory(BpmnDataAssociationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0cd3b9de-16eb-4cb7-b54d-fcd028d6f5bc")
        @Override
        public ISmObjectData createData() {
            return new BpmnDataAssociationData(this.smClass);
        }

        @objid ("faba4d2b-45a6-4039-9827-70b1fef1001d")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnDataAssociationImpl();
        }

    }

    @objid ("42d862f7-c6a3-4e76-b02b-3f4a2d709c2d")
    public static class AssignmentSmAttribute extends SmAttribute {
        @objid ("127bfdd3-7237-4445-bbb7-92177a64aa32")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mAssignment;
        }

        @objid ("1bf0e47a-f2b4-46e6-9d8a-15999df9c880")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataAssociationData) data).mAssignment = value;
        }

    }

    @objid ("93fcdcbe-7aac-4924-b9cc-defc1f7d5b2c")
    public static class TransfomationSmAttribute extends SmAttribute {
        @objid ("d147fdc7-9205-48a4-8cc1-2c2ebc5345ec")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mTransfomation;
        }

        @objid ("389f1bd7-be52-45a7-88c5-7aaeb413162b")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataAssociationData) data).mTransfomation = value;
        }

    }

    @objid ("c7e04443-686c-4f77-8315-7a33fe4d72a4")
    public static class LanguageSmAttribute extends SmAttribute {
        @objid ("d3ef79c3-5d6a-4574-b029-1a24b3736073")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mLanguage;
        }

        @objid ("6edb5676-e2c8-420f-a00f-a29cddf313a5")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataAssociationData) data).mLanguage = value;
        }

    }

    @objid ("56d4102d-09d0-4f63-aa3e-307280b9aab7")
    public static class SourceRefSmDependency extends SmMultipleDependency {
        @objid ("7b0b6fe9-e475-46f5-9e3b-6f19cf529835")
        private SmDependency symetricDep;

        @objid ("df0bcc30-d4e2-451e-96bc-49c2c0eb1ca9")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnDataAssociationData)data).mSourceRef != null)? ((BpmnDataAssociationData)data).mSourceRef:SmMultipleDependency.EMPTY;
        }

        @objid ("9e1fdafe-20ef-4df8-b350-e697eb63f30b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnDataAssociationData) data).mSourceRef = values;
        }

        @objid ("b772ba81-4e59-4e7a-afb8-2908e54da3a5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemAwareElementSmClass)this.getTarget()).getSourceOfDataAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bfad54b5-7b34-486b-be97-416d6bd7002c")
    public static class TargetRefSmDependency extends SmSingleDependency {
        @objid ("988bcee6-6b5a-41d1-87ff-1b3181fce6f2")
        private SmDependency symetricDep;

        @objid ("3e748629-c54f-4a6a-aa75-44a9094f8679")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mTargetRef;
        }

        @objid ("66818e53-99ac-4d42-8867-b33f72de6bca")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataAssociationData) data).mTargetRef = value;
        }

        @objid ("2892692e-6466-466f-856d-7d4b9690d7f4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemAwareElementSmClass)this.getTarget()).getTargetOfDataAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c37b2a49-e3b6-4bd1-a244-88bd4a6e5c84")
    public static class EndingActivitySmDependency extends SmSingleDependency {
        @objid ("f5ce18e7-6e52-42d9-b3a5-e58471234fe3")
        private SmDependency symetricDep;

        @objid ("9bd097f0-6fc9-483f-8bbb-350f4abcd5f5")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mEndingActivity;
        }

        @objid ("6e42c7ae-29de-4204-a778-03c2e826be48")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataAssociationData) data).mEndingActivity = value;
        }

        @objid ("068d9c7e-6074-41a9-8408-65c6e0593f44")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getDataOutputAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4e9835e2-8528-4474-a5f3-750320ab2f52")
    public static class StartingActivitySmDependency extends SmSingleDependency {
        @objid ("9bea7ddf-5b4d-4c13-ae4a-2bf6c1e2cd4d")
        private SmDependency symetricDep;

        @objid ("92e01cbd-8cde-4c15-9097-4aae7e09cb65")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mStartingActivity;
        }

        @objid ("f8043b90-c5e8-4425-ad1b-dd1f671eb5b4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataAssociationData) data).mStartingActivity = value;
        }

        @objid ("dec0ea3c-f58a-481d-9d20-c11bb48f076d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getDataInputAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("73c7a97e-0ae7-4fe2-9d5a-668c6598fd9a")
    public static class StartingEventSmDependency extends SmSingleDependency {
        @objid ("45c7a42a-0894-457d-8d43-5869c31ea3ac")
        private SmDependency symetricDep;

        @objid ("0d2873f7-22b4-44af-93a4-a26b6002172a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mStartingEvent;
        }

        @objid ("954586d9-f0a8-4f76-a605-bcb814a62b4c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataAssociationData) data).mStartingEvent = value;
        }

        @objid ("62e14c45-dc9f-4e28-88ca-af499286b41c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnThrowEventSmClass)this.getTarget()).getDataInputAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("866f7c6e-8201-45f8-b2ca-67baa92df961")
    public static class VisualShortCutSmDependency extends SmMultipleDependency {
        @objid ("bcfb4f6b-05b6-4b28-bc2e-36424bf538e8")
        private SmDependency symetricDep;

        @objid ("806b6f97-076c-4d4e-839b-54a73c00b02c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnDataAssociationData)data).mVisualShortCut != null)? ((BpmnDataAssociationData)data).mVisualShortCut:SmMultipleDependency.EMPTY;
        }

        @objid ("998e0257-c9ee-4cce-85b6-7f1bcc79114b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnDataAssociationData) data).mVisualShortCut = values;
        }

        @objid ("d7bc13ca-e161-41ef-a03f-dc21691f980c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowDataAssociationSmClass)this.getTarget()).getDataAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c3722a64-6e68-4872-849e-4c301f56acd7")
    public static class EndingEventSmDependency extends SmSingleDependency {
        @objid ("16d5f718-dfe1-4ca6-ae38-bf8d8b888dc4")
        private SmDependency symetricDep;

        @objid ("91ac7113-c562-469b-a047-0f4eb9afa6fe")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataAssociationData) data).mEndingEvent;
        }

        @objid ("12e40786-1a8e-4b4b-9289-8764567c598d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataAssociationData) data).mEndingEvent = value;
        }

        @objid ("e6f18293-31e9-4dcb-8669-57720e49b377")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCatchEventSmClass)this.getTarget()).getDataOutputAssociationDep();
            }
            return this.symetricDep;
        }

    }

}
