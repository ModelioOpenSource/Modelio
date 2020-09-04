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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.LifelineSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeLinkSmClass;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceData;
import org.modelio.metamodel.impl.uml.statik.LinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkEndSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
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

@objid ("d02f7ac8-f93a-44a1-acfd-0f9ced01cb4c")
public class InstanceSmClass extends UmlModelElementSmClass {
    @objid ("8ee63be7-a833-4966-881f-b2a2037b450d")
    private SmAttribute isConstantAtt;

    @objid ("bcb53ff2-34a9-42f1-a0d7-2b8eae91eb25")
    private SmAttribute multiplicityMinAtt;

    @objid ("b403a134-a166-4ceb-9309-aab8c613f2c4")
    private SmAttribute multiplicityMaxAtt;

    @objid ("1c98e6c7-bb4b-4de1-a290-9c62958ed095")
    private SmAttribute valueAtt;

    @objid ("324889c2-2378-4d79-af19-24d6e271b236")
    private SmDependency representedCommunicationNodeDep;

    @objid ("7df5b2f9-822a-4038-a168-5aaddd60e88e")
    private SmDependency ownedEndDep;

    @objid ("bf9be979-66e8-45b1-ac09-0ed7d1cb628c")
    private SmDependency baseDep;

    @objid ("fbe98c18-0cdd-42c2-9a51-1c61bbfb36d7")
    private SmDependency representingObjectNodeDep;

    @objid ("7f0ac2c0-cf28-4e80-a37d-0e98707e89dd")
    private SmDependency ownerDep;

    @objid ("a90de685-d58c-49d9-8be6-4d79d7f5b58f")
    private SmDependency ownedNaryEndDep;

    @objid ("2469543a-bacf-4566-b185-8a3655b41c9e")
    private SmDependency representedLifeLineDep;

    @objid ("34cb8540-ca6b-4f39-88c5-df586fc2c15e")
    private SmDependency slotDep;

    @objid ("378666fa-f026-4eac-ac97-5f0e4bf708d6")
    private SmDependency partDep;

    @objid ("304a348a-5663-4b2f-a99c-85af75f9abde")
    private SmDependency targetingEndDep;

    @objid ("4a65a225-ab66-4fe3-b936-ad4d85d1ce91")
    public InstanceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5107c192-c3a3-4917-978f-5fb840bbb737")
    @Override
    public String getName() {
        return "Instance";
    }

    @objid ("caf96c3e-0fac-4118-92b4-f5ba8b413575")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("fff97d1c-55bf-4ccb-89d0-3c921a4845d7")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Instance.class;
    }

    @objid ("853cdf2f-9e7b-4ab8-9dab-44082391cf19")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6f3776de-7ae2-43a3-872b-31b2ca2da06c")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ce0daa3b-984e-4bf2-ae7b-00450cefa193")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new InstanceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isConstantAtt = new IsConstantSmAttribute();
        this.isConstantAtt.init("IsConstant", this, Boolean.class );
        registerAttribute(this.isConstantAtt);
        
        this.multiplicityMinAtt = new MultiplicityMinSmAttribute();
        this.multiplicityMinAtt.init("MultiplicityMin", this, String.class );
        registerAttribute(this.multiplicityMinAtt);
        
        this.multiplicityMaxAtt = new MultiplicityMaxSmAttribute();
        this.multiplicityMaxAtt.init("MultiplicityMax", this, String.class );
        registerAttribute(this.multiplicityMaxAtt);
        
        this.valueAtt = new ValueSmAttribute();
        this.valueAtt.init("Value", this, String.class );
        registerAttribute(this.valueAtt);
        
        
        // Initialize and register the SmDependency
        this.representedCommunicationNodeDep = new RepresentedCommunicationNodeSmDependency();
        this.representedCommunicationNodeDep.init("RepresentedCommunicationNode", this, metamodel.getMClass(CommunicationNode.MQNAME), 0, -1 );
        registerDependency(this.representedCommunicationNodeDep);
        
        this.ownedEndDep = new OwnedEndSmDependency();
        this.ownedEndDep.init("OwnedEnd", this, metamodel.getMClass(LinkEnd.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedEndDep);
        
        this.baseDep = new BaseSmDependency();
        this.baseDep.init("Base", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.baseDep);
        
        this.representingObjectNodeDep = new RepresentingObjectNodeSmDependency();
        this.representingObjectNodeDep.init("RepresentingObjectNode", this, metamodel.getMClass(ObjectNode.MQNAME), 0, -1 );
        registerDependency(this.representingObjectNodeDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.ownedNaryEndDep = new OwnedNaryEndSmDependency();
        this.ownedNaryEndDep.init("OwnedNaryEnd", this, metamodel.getMClass(NaryLinkEnd.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedNaryEndDep);
        
        this.representedLifeLineDep = new RepresentedLifeLineSmDependency();
        this.representedLifeLineDep.init("RepresentedLifeLine", this, metamodel.getMClass(Lifeline.MQNAME), 0, -1 );
        registerDependency(this.representedLifeLineDep);
        
        this.slotDep = new SlotSmDependency();
        this.slotDep.init("Slot", this, metamodel.getMClass(AttributeLink.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.slotDep);
        
        this.partDep = new PartSmDependency();
        this.partDep.init("Part", this, metamodel.getMClass(BindableInstance.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.partDep);
        
        this.targetingEndDep = new TargetingEndSmDependency();
        this.targetingEndDep.init("TargetingEnd", this, metamodel.getMClass(LinkEnd.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.targetingEndDep);
    }

    @objid ("ebde7609-8474-48e9-b0f7-b8e30adbd668")
    public SmAttribute getIsConstantAtt() {
        if (this.isConstantAtt == null) {
        	this.isConstantAtt = this.getAttributeDef("IsConstant");
        }
        return this.isConstantAtt;
    }

    @objid ("e9f38f6f-5a4b-4772-bf61-0ea5597acf92")
    public SmAttribute getMultiplicityMinAtt() {
        if (this.multiplicityMinAtt == null) {
        	this.multiplicityMinAtt = this.getAttributeDef("MultiplicityMin");
        }
        return this.multiplicityMinAtt;
    }

    @objid ("d8fff5da-99c0-4e5e-9e32-1146ac5f9e17")
    public SmAttribute getMultiplicityMaxAtt() {
        if (this.multiplicityMaxAtt == null) {
        	this.multiplicityMaxAtt = this.getAttributeDef("MultiplicityMax");
        }
        return this.multiplicityMaxAtt;
    }

    @objid ("e9f69348-f7fe-4d67-9f7e-43f1103ddefd")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("1c8b7979-8624-471d-823b-c36d857ed9ff")
    public SmDependency getRepresentedCommunicationNodeDep() {
        if (this.representedCommunicationNodeDep == null) {
        	this.representedCommunicationNodeDep = this.getDependencyDef("RepresentedCommunicationNode");
        }
        return this.representedCommunicationNodeDep;
    }

    @objid ("f663637f-42a4-4430-ae0b-41b78c41a238")
    public SmDependency getOwnedEndDep() {
        if (this.ownedEndDep == null) {
        	this.ownedEndDep = this.getDependencyDef("OwnedEnd");
        }
        return this.ownedEndDep;
    }

    @objid ("8dddf6fd-fbdc-4cfc-b181-1f92b6442d8b")
    public SmDependency getBaseDep() {
        if (this.baseDep == null) {
        	this.baseDep = this.getDependencyDef("Base");
        }
        return this.baseDep;
    }

    @objid ("16c2a617-f7c2-432a-9ab2-15d555d891f2")
    public SmDependency getRepresentingObjectNodeDep() {
        if (this.representingObjectNodeDep == null) {
        	this.representingObjectNodeDep = this.getDependencyDef("RepresentingObjectNode");
        }
        return this.representingObjectNodeDep;
    }

    @objid ("f3fa4003-aa87-4c71-aba0-dec0f421d3e4")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("00d4f590-dd61-4e9c-9353-e8e82795425a")
    public SmDependency getOwnedNaryEndDep() {
        if (this.ownedNaryEndDep == null) {
        	this.ownedNaryEndDep = this.getDependencyDef("OwnedNaryEnd");
        }
        return this.ownedNaryEndDep;
    }

    @objid ("deb3fea2-64ad-45ca-8bb6-f210699b3520")
    public SmDependency getRepresentedLifeLineDep() {
        if (this.representedLifeLineDep == null) {
        	this.representedLifeLineDep = this.getDependencyDef("RepresentedLifeLine");
        }
        return this.representedLifeLineDep;
    }

    @objid ("82a7e9fb-2063-452e-872d-2e0579c5cf9a")
    public SmDependency getSlotDep() {
        if (this.slotDep == null) {
        	this.slotDep = this.getDependencyDef("Slot");
        }
        return this.slotDep;
    }

    @objid ("6f42f6f5-c00d-4b9a-9875-f98ac38b7cb3")
    public SmDependency getPartDep() {
        if (this.partDep == null) {
        	this.partDep = this.getDependencyDef("Part");
        }
        return this.partDep;
    }

    @objid ("700749e6-072a-4796-b48f-3258be0d306b")
    public SmDependency getTargetingEndDep() {
        if (this.targetingEndDep == null) {
        	this.targetingEndDep = this.getDependencyDef("TargetingEnd");
        }
        return this.targetingEndDep;
    }

    @objid ("0b7b2259-6a68-412d-aba2-26b82397f9eb")
    private static class InstanceObjectFactory implements ISmObjectFactory {
        @objid ("6811335f-70ec-43b0-b260-56f33788a438")
        private InstanceSmClass smClass;

        @objid ("38bc121a-3d61-46e7-a360-e7074e6b774c")
        public InstanceObjectFactory(InstanceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8fafc548-ee15-44ff-a36f-98f8d642c748")
        @Override
        public ISmObjectData createData() {
            return new InstanceData(this.smClass);
        }

        @objid ("ddcfde3b-8c94-4afe-b134-ea8f9c0c2460")
        @Override
        public SmObjectImpl createImpl() {
            return new InstanceImpl();
        }

    }

    @objid ("c76732f4-e46f-433d-bca8-18dba8b627a2")
    public static class IsConstantSmAttribute extends SmAttribute {
        @objid ("20d312ce-b630-4df7-8cb4-6f31e74108c0")
        public Object getValue(ISmObjectData data) {
            return ((InstanceData) data).mIsConstant;
        }

        @objid ("0d78a451-b983-4418-b663-f9b1ffe610ef")
        public void setValue(ISmObjectData data, Object value) {
            ((InstanceData) data).mIsConstant = value;
        }

    }

    @objid ("980ac8d4-0c0c-4885-ba13-7aabe3e5e774")
    public static class MultiplicityMinSmAttribute extends SmAttribute {
        @objid ("eed0f90c-b296-4609-9c99-cfe438672cbc")
        public Object getValue(ISmObjectData data) {
            return ((InstanceData) data).mMultiplicityMin;
        }

        @objid ("b8c9e3a4-bdf4-47ca-a4e2-c8268cdfa0b9")
        public void setValue(ISmObjectData data, Object value) {
            ((InstanceData) data).mMultiplicityMin = value;
        }

    }

    @objid ("4ddbc9d6-3976-446f-b358-65982b9d280a")
    public static class MultiplicityMaxSmAttribute extends SmAttribute {
        @objid ("a0f45c54-b550-4ced-b3a9-c7edbe0e2c8b")
        public Object getValue(ISmObjectData data) {
            return ((InstanceData) data).mMultiplicityMax;
        }

        @objid ("5eea2c90-a632-4c64-aad5-04a56a1489e4")
        public void setValue(ISmObjectData data, Object value) {
            ((InstanceData) data).mMultiplicityMax = value;
        }

    }

    @objid ("b36af97c-baae-4d9e-8e1c-f67f0d5fe4c3")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("fe894e5c-18f0-44aa-a170-4573430df188")
        public Object getValue(ISmObjectData data) {
            return ((InstanceData) data).mValue;
        }

        @objid ("4b55d16c-1899-45f3-a839-8a3f465699b8")
        public void setValue(ISmObjectData data, Object value) {
            ((InstanceData) data).mValue = value;
        }

    }

    @objid ("27067c97-b964-4a9e-ac0c-ded93bf0fe30")
    public static class RepresentedCommunicationNodeSmDependency extends SmMultipleDependency {
        @objid ("b52f942d-ea9f-416a-8d49-41a2967777fc")
        private SmDependency symetricDep;

        @objid ("8119d6ca-fbe0-405e-85fe-9b9b885cff1a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mRepresentedCommunicationNode != null)? ((InstanceData)data).mRepresentedCommunicationNode:SmMultipleDependency.EMPTY;
        }

        @objid ("511e8977-b903-4522-a8b9-f3d0f07c6512")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mRepresentedCommunicationNode = values;
        }

        @objid ("ea114270-d397-43f8-ad10-b2a3ebf6f818")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationNodeSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("66c8d762-7ac3-41e7-bb5e-d1f6a60ce346")
    public static class OwnedEndSmDependency extends SmMultipleDependency {
        @objid ("8b736496-450f-4a72-9d8c-3fa942c97ded")
        private SmDependency symetricDep;

        @objid ("8cd53bd8-a5bc-4c5b-ab3d-836c3e3d31b2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mOwnedEnd != null)? ((InstanceData)data).mOwnedEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("843ef690-2639-48b9-b2dd-37ba228220f7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mOwnedEnd = values;
        }

        @objid ("ea6f3db5-056b-4be6-8167-6031da682c91")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getSourceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f1656f3c-2481-4d00-a2d3-2846c1313062")
    public static class BaseSmDependency extends SmSingleDependency {
        @objid ("89f12295-6b89-41a4-8aec-4db9b7ac3afd")
        private SmDependency symetricDep;

        @objid ("e4e089a1-bc49-4576-8b61-18e3cdf82f47")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InstanceData) data).mBase;
        }

        @objid ("cc48b1db-71e4-417e-ae2e-f92964c74274")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InstanceData) data).mBase = value;
        }

        @objid ("93bcc25e-3a6e-4a0b-9177-ae7ad40f858a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getRepresentingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b6fb8d6e-2ecd-4e25-8ddc-39c3949caaf1")
    public static class RepresentingObjectNodeSmDependency extends SmMultipleDependency {
        @objid ("467f760d-65d8-44b6-ba75-1168c03b4088")
        private SmDependency symetricDep;

        @objid ("ada9a80c-4618-4b2e-b017-63a8c521e1ba")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mRepresentingObjectNode != null)? ((InstanceData)data).mRepresentingObjectNode:SmMultipleDependency.EMPTY;
        }

        @objid ("ee7a3605-79b1-4208-860a-8cd928b18b1d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mRepresentingObjectNode = values;
        }

        @objid ("141791cd-4557-4358-8e5e-3226083b6b90")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ObjectNodeSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ed9bc567-141c-4f22-818a-136818e828a8")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("7ea9aa19-f6de-493c-83d0-39366f2bce83")
        private SmDependency symetricDep;

        @objid ("656ad38b-12ad-4515-92cb-97d074669fc9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InstanceData) data).mOwner;
        }

        @objid ("34d18951-a419-45b5-9ab2-f6e3e54038a8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InstanceData) data).mOwner = value;
        }

        @objid ("3c28ff36-9463-4303-ae55-c7d4c0ba4f34")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getDeclaredDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9085a2ab-e74f-461a-8a4d-6a32f01d3009")
    public static class OwnedNaryEndSmDependency extends SmMultipleDependency {
        @objid ("e2019a42-45c4-420d-a692-dffcf62f995e")
        private SmDependency symetricDep;

        @objid ("fcd48995-cd3a-4921-a63d-e6dce9d8bd4d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mOwnedNaryEnd != null)? ((InstanceData)data).mOwnedNaryEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("2b2f375e-51aa-4da5-b4c5-3d1993bffc86")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mOwnedNaryEnd = values;
        }

        @objid ("aa9b8ab3-1070-4df0-a46d-b35b2ae86273")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkEndSmClass)this.getTarget()).getSourceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("edbee7b2-d614-4c3f-bcc8-dbc48c34a1e2")
    public static class RepresentedLifeLineSmDependency extends SmMultipleDependency {
        @objid ("487255b5-1217-4944-8f9a-37d6e69183e0")
        private SmDependency symetricDep;

        @objid ("330803cc-3771-4ead-a75e-ed69df6efb9f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mRepresentedLifeLine != null)? ((InstanceData)data).mRepresentedLifeLine:SmMultipleDependency.EMPTY;
        }

        @objid ("5c506f09-b6ab-47ae-aac9-29da81271960")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mRepresentedLifeLine = values;
        }

        @objid ("474afdee-7279-4edd-8b9d-68f03218a480")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LifelineSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9ddfd5f2-8378-4266-b2fc-329f0ddd1c2a")
    public static class SlotSmDependency extends SmMultipleDependency {
        @objid ("0afe3c99-778f-4f21-a07e-9b8a40aa796d")
        private SmDependency symetricDep;

        @objid ("87f70b39-e2ec-47b0-9e4f-bdd47700c585")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mSlot != null)? ((InstanceData)data).mSlot:SmMultipleDependency.EMPTY;
        }

        @objid ("8c782826-0885-45a0-b7f1-dd4ea21cf537")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mSlot = values;
        }

        @objid ("fc3007f7-3ef2-4c04-bb2e-399e33714c92")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeLinkSmClass)this.getTarget()).getAttributedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2e30ef5f-7b96-4696-9205-718e44ba77ad")
    public static class PartSmDependency extends SmMultipleDependency {
        @objid ("d4a4e0cb-322b-4874-ad9b-8f17ee09a07a")
        private SmDependency symetricDep;

        @objid ("d50a870d-e566-4978-b315-966eb3b635c6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mPart != null)? ((InstanceData)data).mPart:SmMultipleDependency.EMPTY;
        }

        @objid ("f5582daf-61b5-486a-b2bb-7124d89d5bee")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mPart = values;
        }

        @objid ("adc45403-674a-49ec-9963-dabb76e4b077")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindableInstanceSmClass)this.getTarget()).getClusterDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("74095a21-6d7b-4865-83c5-345404b9158b")
    public static class TargetingEndSmDependency extends SmMultipleDependency {
        @objid ("e59e5ef7-24f3-4414-90aa-0beab33c3139")
        private SmDependency symetricDep;

        @objid ("9c6c48d0-a001-4b8e-9793-658fced41625")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InstanceData)data).mTargetingEnd != null)? ((InstanceData)data).mTargetingEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("9b776dcf-e50f-41b1-abc4-be5eddc50ab1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InstanceData) data).mTargetingEnd = values;
        }

        @objid ("d739c4ba-259b-4b09-a4c0-f252bf7d3900")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getTargetDep();
            }
            return this.symetricDep;
        }

    }

}
