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
package org.modelio.metamodel.impl.uml.informationFlow;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityEdgeSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationMessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkSmClass;
import org.modelio.metamodel.impl.uml.statik.StructuralFeatureSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("9a08c956-ddd8-4831-b050-a8b0220aff38")
public class InformationFlowSmClass extends UmlModelElementSmClass {
    @objid ("378f266a-c98c-45f1-bcad-5672e66943aa")
    private SmDependency ownerDep;

    @objid ("b1043a9f-9bbf-48b1-bc60-677054d6964c")
    private SmDependency informationSourceDep;

    @objid ("354ba408-6bed-4081-aeec-4078ed721833")
    private SmDependency informationTargetDep;

    @objid ("5cc47b6e-72ba-440e-8a41-07b7992e52e6")
    private SmDependency realizingActivityEdgeDep;

    @objid ("e7f741ac-e377-4cc8-97bc-097a871ace23")
    private SmDependency realizingCommunicationMessageDep;

    @objid ("cb1b6685-3207-4303-8652-f05df1edd875")
    private SmDependency realizingFeatureDep;

    @objid ("70b42e07-32dc-4241-aff1-0e4e05c6c0e0")
    private SmDependency realizingLinkDep;

    @objid ("19a48dee-f582-46eb-8dd7-44111f1f9939")
    private SmDependency realizingMessageDep;

    @objid ("a8d49a13-5a70-4676-a345-495f310fe5a7")
    private SmDependency realizingNaryLinkDep;

    @objid ("540a6364-87d3-4ac9-9c2d-552613012239")
    private SmDependency conveyedDep;

    @objid ("b2836a99-89d1-466e-b537-3c5936d05ce7")
    private SmDependency channelDep;

    @objid ("7d6dd2d1-36c7-491c-beed-0fa786db0d96")
    public InformationFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("873dd4b0-1384-4cfb-8397-1b498b1a779d")
    @Override
    public String getName() {
        return "InformationFlow";
    }

    @objid ("fe38f372-4d48-49ef-a54d-985561738d6b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e940a34d-f6fe-4efd-a6c5-5daf4acf239a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InformationFlow.class;
    }

    @objid ("bc54339e-1030-44eb-9186-c2f6e396b029")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("fda3f762-d5a8-42d2-a043-28d0a414e479")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("609c54a1-138a-4eb8-aa1b-9b2c57b12f09")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new InformationFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.informationSourceDep = new InformationSourceSmDependency();
        this.informationSourceDep.init("InformationSource", this, metamodel.getMClass(UmlModelElement.MQNAME), 1, -1 , SmDirective.SMCDLINKSOURCE, SmDirective.SMCDPARTOF);
        registerDependency(this.informationSourceDep);
        
        this.informationTargetDep = new InformationTargetSmDependency();
        this.informationTargetDep.init("InformationTarget", this, metamodel.getMClass(UmlModelElement.MQNAME), 1, -1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.informationTargetDep);
        
        this.realizingActivityEdgeDep = new RealizingActivityEdgeSmDependency();
        this.realizingActivityEdgeDep.init("RealizingActivityEdge", this, metamodel.getMClass(ActivityEdge.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingActivityEdgeDep);
        
        this.realizingCommunicationMessageDep = new RealizingCommunicationMessageSmDependency();
        this.realizingCommunicationMessageDep.init("RealizingCommunicationMessage", this, metamodel.getMClass(CommunicationMessage.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingCommunicationMessageDep);
        
        this.realizingFeatureDep = new RealizingFeatureSmDependency();
        this.realizingFeatureDep.init("RealizingFeature", this, metamodel.getMClass(StructuralFeature.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingFeatureDep);
        
        this.realizingLinkDep = new RealizingLinkSmDependency();
        this.realizingLinkDep.init("RealizingLink", this, metamodel.getMClass(LinkEnd.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingLinkDep);
        
        this.realizingMessageDep = new RealizingMessageSmDependency();
        this.realizingMessageDep.init("RealizingMessage", this, metamodel.getMClass(Message.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingMessageDep);
        
        this.realizingNaryLinkDep = new RealizingNaryLinkSmDependency();
        this.realizingNaryLinkDep.init("RealizingNaryLink", this, metamodel.getMClass(NaryLink.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingNaryLinkDep);
        
        this.conveyedDep = new ConveyedSmDependency();
        this.conveyedDep.init("Conveyed", this, metamodel.getMClass(Classifier.MQNAME), 1, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.conveyedDep);
        
        this.channelDep = new ChannelSmDependency();
        this.channelDep.init("Channel", this, metamodel.getMClass(AssociationEnd.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.channelDep);
    }

    @objid ("848e8cf7-8fe0-432a-be17-9191071cf47d")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("5f57a3b1-6e94-4a55-ae64-03bb599c2bf2")
    public SmDependency getInformationSourceDep() {
        if (this.informationSourceDep == null) {
        	this.informationSourceDep = this.getDependencyDef("InformationSource");
        }
        return this.informationSourceDep;
    }

    @objid ("2506faa7-dcec-4b43-94ea-42c6c7f39f4c")
    public SmDependency getInformationTargetDep() {
        if (this.informationTargetDep == null) {
        	this.informationTargetDep = this.getDependencyDef("InformationTarget");
        }
        return this.informationTargetDep;
    }

    @objid ("da44b002-71ea-45e9-853d-67bf751fe4c0")
    public SmDependency getRealizingActivityEdgeDep() {
        if (this.realizingActivityEdgeDep == null) {
        	this.realizingActivityEdgeDep = this.getDependencyDef("RealizingActivityEdge");
        }
        return this.realizingActivityEdgeDep;
    }

    @objid ("4e63881b-8f45-4099-a117-efaf101b3b5f")
    public SmDependency getRealizingCommunicationMessageDep() {
        if (this.realizingCommunicationMessageDep == null) {
        	this.realizingCommunicationMessageDep = this.getDependencyDef("RealizingCommunicationMessage");
        }
        return this.realizingCommunicationMessageDep;
    }

    @objid ("b09ec5b6-f761-482a-b2d2-bb2a08795d1f")
    public SmDependency getRealizingFeatureDep() {
        if (this.realizingFeatureDep == null) {
        	this.realizingFeatureDep = this.getDependencyDef("RealizingFeature");
        }
        return this.realizingFeatureDep;
    }

    @objid ("70a28974-a3a5-4f00-9dbe-3bffb53cac80")
    public SmDependency getRealizingLinkDep() {
        if (this.realizingLinkDep == null) {
        	this.realizingLinkDep = this.getDependencyDef("RealizingLink");
        }
        return this.realizingLinkDep;
    }

    @objid ("d35bab96-dedd-4dad-be77-b4a9a474fd58")
    public SmDependency getRealizingMessageDep() {
        if (this.realizingMessageDep == null) {
        	this.realizingMessageDep = this.getDependencyDef("RealizingMessage");
        }
        return this.realizingMessageDep;
    }

    @objid ("02cdb6ae-3d8f-4e10-8a87-55ed9a375ae5")
    public SmDependency getRealizingNaryLinkDep() {
        if (this.realizingNaryLinkDep == null) {
        	this.realizingNaryLinkDep = this.getDependencyDef("RealizingNaryLink");
        }
        return this.realizingNaryLinkDep;
    }

    @objid ("de04e4da-be83-4b4e-952f-990185ae1de9")
    public SmDependency getConveyedDep() {
        if (this.conveyedDep == null) {
        	this.conveyedDep = this.getDependencyDef("Conveyed");
        }
        return this.conveyedDep;
    }

    @objid ("3525ce73-a761-40d3-92f9-e8db844e3546")
    public SmDependency getChannelDep() {
        if (this.channelDep == null) {
        	this.channelDep = this.getDependencyDef("Channel");
        }
        return this.channelDep;
    }

    @objid ("1220e8f2-c476-4fd5-bb12-2590a2107e49")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("5657ac11-7bbc-47b5-9528-e42bb69bab91")
    private static class InformationFlowObjectFactory implements ISmObjectFactory {
        @objid ("4d874095-eca3-462b-9459-b613eb145935")
        private InformationFlowSmClass smClass;

        @objid ("eb4df5ae-4809-479d-8261-334b1b53064d")
        public InformationFlowObjectFactory(InformationFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("12f22d1b-2b0c-4c99-ad6f-e56f2f0e76f0")
        @Override
        public ISmObjectData createData() {
            return new InformationFlowData(this.smClass);
        }

        @objid ("665c0d2a-b0f0-449c-b3cd-0678049419e0")
        @Override
        public SmObjectImpl createImpl() {
            return new InformationFlowImpl();
        }

    }

    @objid ("9098f48a-7d4f-4762-beea-8ffd858b78dd")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("9deb448d-2f8a-4832-9b90-cfc806a97bac")
        private SmDependency symetricDep;

        @objid ("fbb820ac-ca13-4851-8711-d3e8f0d4ad34")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InformationFlowData) data).mOwner;
        }

        @objid ("29030ae0-7fd0-4fdb-a5d2-128434bffa0a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InformationFlowData) data).mOwner = value;
        }

        @objid ("7c8124d9-1aee-4f0c-82f0-e5681eb33693")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getOwnedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("fb9e54ec-d0b1-4cc1-8575-cc520de079e4")
    public static class InformationSourceSmDependency extends SmMultipleDependency {
        @objid ("83f38bdf-9389-4da9-aaa8-7a3af4c36822")
        private SmDependency symetricDep;

        @objid ("f41dc8c3-345f-4ab4-a353-9849081cb03f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mInformationSource != null)? ((InformationFlowData)data).mInformationSource:SmMultipleDependency.EMPTY;
        }

        @objid ("0812843a-0180-45e5-be72-a9e193aa8d0b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mInformationSource = values;
        }

        @objid ("b1ab7752-1106-49ef-908d-589c558c66ac")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getSentInfoDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("246e0257-f0cb-425a-9259-04c5bcd549b5")
    public static class InformationTargetSmDependency extends SmMultipleDependency {
        @objid ("42e9537f-7d9e-4bd4-b1bc-57b421c41006")
        private SmDependency symetricDep;

        @objid ("ddde5bf3-978f-43e7-9de7-4853bd697a05")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mInformationTarget != null)? ((InformationFlowData)data).mInformationTarget:SmMultipleDependency.EMPTY;
        }

        @objid ("7486c2db-98f1-4bf4-baef-455b02073267")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mInformationTarget = values;
        }

        @objid ("2a119957-70b8-4f19-bd97-afe830d00b90")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getReceivedInfoDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("58236bd1-d64d-4e69-be5f-430d0f30e3c2")
    public static class RealizingActivityEdgeSmDependency extends SmMultipleDependency {
        @objid ("dbe70c1f-a04a-4817-b461-9b60266a3577")
        private SmDependency symetricDep;

        @objid ("8291e084-2900-49ff-a772-50ff0d096d9e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mRealizingActivityEdge != null)? ((InformationFlowData)data).mRealizingActivityEdge:SmMultipleDependency.EMPTY;
        }

        @objid ("dbb38e11-f02a-4f9b-b65f-97c402b840ea")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mRealizingActivityEdge = values;
        }

        @objid ("d5b0a372-7dbb-4fce-8399-17edd87801be")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityEdgeSmClass)this.getTarget()).getRealizedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2cd10d62-df91-4024-afb0-f5094d433309")
    public static class RealizingCommunicationMessageSmDependency extends SmMultipleDependency {
        @objid ("140157ee-7603-474a-a4d0-55f4aadb8573")
        private SmDependency symetricDep;

        @objid ("f580637a-fe44-4c5e-81d5-486915f09893")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mRealizingCommunicationMessage != null)? ((InformationFlowData)data).mRealizingCommunicationMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("ead6eaa5-6451-4ded-b522-98d63f153782")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mRealizingCommunicationMessage = values;
        }

        @objid ("f4686098-ef1c-4c8e-acd5-7536f80ae4e5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationMessageSmClass)this.getTarget()).getRealizedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("55f2a795-d517-4ac4-b485-45983cec2670")
    public static class RealizingFeatureSmDependency extends SmMultipleDependency {
        @objid ("31d6d637-a885-465f-a14c-8dcd97b3e48d")
        private SmDependency symetricDep;

        @objid ("b9dff6b6-6955-4e49-8e7b-d98dcf14620d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mRealizingFeature != null)? ((InformationFlowData)data).mRealizingFeature:SmMultipleDependency.EMPTY;
        }

        @objid ("d830fb83-42f6-4d81-b791-2d2f548bd541")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mRealizingFeature = values;
        }

        @objid ("84181e7b-de4f-496b-b3e5-213daf1f1c00")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StructuralFeatureSmClass)this.getTarget()).getRealizedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("caf54015-3f2a-48bf-941f-fc292c5eb4aa")
    public static class RealizingLinkSmDependency extends SmMultipleDependency {
        @objid ("028d811a-1adb-4894-a36d-6242d5aef3fd")
        private SmDependency symetricDep;

        @objid ("1a8e8e37-80a3-4e79-8589-a564901d4cba")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mRealizingLink != null)? ((InformationFlowData)data).mRealizingLink:SmMultipleDependency.EMPTY;
        }

        @objid ("dc0df6de-2d98-44ac-956e-2056ab8982cb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mRealizingLink = values;
        }

        @objid ("356adc80-72d1-40f6-b18f-c59ab48d3d70")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getRealizedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2c2451e3-a03c-4cea-9c7f-16df43f65821")
    public static class RealizingMessageSmDependency extends SmMultipleDependency {
        @objid ("f67e2b23-485e-4a1f-8539-992be888b8c3")
        private SmDependency symetricDep;

        @objid ("16373941-8303-458d-bab8-220b0f656e95")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mRealizingMessage != null)? ((InformationFlowData)data).mRealizingMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("abd7b743-2b8f-4c9f-8cb4-fc72ce47fe42")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mRealizingMessage = values;
        }

        @objid ("991d12cc-db70-40af-bc61-395e3b80287f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageSmClass)this.getTarget()).getRealizedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c4ef2d14-24ac-4930-9a85-0bf47097b9fb")
    public static class RealizingNaryLinkSmDependency extends SmMultipleDependency {
        @objid ("fa3f94a2-8855-4282-b609-693b9827d2c3")
        private SmDependency symetricDep;

        @objid ("c954e7d9-5d60-4839-b387-08e239dce69d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mRealizingNaryLink != null)? ((InformationFlowData)data).mRealizingNaryLink:SmMultipleDependency.EMPTY;
        }

        @objid ("f5dd39de-c111-4bcd-8d6d-430bf544d4ac")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mRealizingNaryLink = values;
        }

        @objid ("cfe37500-ce27-460a-bfde-7ea2e05be71d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkSmClass)this.getTarget()).getRealizedInformationFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b4fe35ab-3302-4c44-9743-d4c3d96f314b")
    public static class ConveyedSmDependency extends SmMultipleDependency {
        @objid ("56ecb5cb-b0ab-40f0-ab5d-63cffc395531")
        private SmDependency symetricDep;

        @objid ("ac55bda8-9a02-47d2-b3eb-0ed4534c95f0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationFlowData)data).mConveyed != null)? ((InformationFlowData)data).mConveyed:SmMultipleDependency.EMPTY;
        }

        @objid ("5b36f79a-2c7e-4996-a2a0-0a736f49cf33")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationFlowData) data).mConveyed = values;
        }

        @objid ("940b1c67-a16e-4e19-b755-cfed215f3f6a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getConveyerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7c6bb86c-58fe-4909-a0d5-02a4608de8ab")
    public static class ChannelSmDependency extends SmSingleDependency {
        @objid ("9c1afd5f-1ca5-4567-8cda-c7e99e4e76f6")
        private SmDependency symetricDep;

        @objid ("50a9ee69-b483-4bed-8633-7496a9983b60")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InformationFlowData) data).mChannel;
        }

        @objid ("ec64e1ae-261e-436d-a647-185124848004")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InformationFlowData) data).mChannel = value;
        }

        @objid ("2577934b-8b6d-422f-840b-f8d27373229e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getSentDep();
            }
            return this.symetricDep;
        }

    }

}
