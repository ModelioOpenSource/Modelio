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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkEndData;
import org.modelio.metamodel.impl.uml.statik.LinkSmClass;
import org.modelio.metamodel.impl.uml.statik.ProvidedInterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.RequiredInterfaceSmClass;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
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

@objid ("707721c4-21c9-43f3-b94b-301e87a7913f")
public class LinkEndSmClass extends UmlModelElementSmClass {
    @objid ("68ffe18a-0313-4d6f-8a8c-f662fd5d39c4")
    private SmAttribute isOrderedAtt;

    @objid ("18244109-748d-4a22-8d1b-117315950e76")
    private SmAttribute isUniqueAtt;

    @objid ("6ab790a6-ccfd-483f-aeb0-3590d398f6e8")
    private SmAttribute multiplicityMaxAtt;

    @objid ("960474e3-32be-43e5-8cfb-fd3dae0010b4")
    private SmAttribute multiplicityMinAtt;

    @objid ("f9436014-ecb6-423f-b2e5-7d9b9ed2c413")
    private SmDependency linkDep;

    @objid ("d1510fe5-a28c-4e0f-96ae-d2839237396a")
    private SmDependency targetDep;

    @objid ("bcabf58a-04a2-4f58-aa8d-26e6cf81bfd0")
    private SmDependency oppositeOwnerDep;

    @objid ("e6434e1d-23ce-4da4-a368-a561f224f996")
    private SmDependency realizedInformationFlowDep;

    @objid ("938adb3f-8b1c-4a6a-a4d1-a8ddc8010e98")
    private SmDependency modelDep;

    @objid ("0bf74946-deab-435d-baea-1a182a46658f")
    private SmDependency consumerDep;

    @objid ("ca8ea69e-18d8-46fb-96c3-11decef06b5d")
    private SmDependency oppositeDep;

    @objid ("fb02d989-da8e-49f3-9aae-4df1e679895c")
    private SmDependency sourceDep;

    @objid ("8ca722e0-ce12-41a3-9cd2-70cd2812f834")
    private SmDependency providerDep;

    @objid ("d669d32d-e78e-431b-a816-790e64338622")
    public LinkEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("48cde3f8-7fd7-466d-b195-7aa7d5b6deb8")
    @Override
    public String getName() {
        return "LinkEnd";
    }

    @objid ("f89a4ccc-d0c5-45bd-a9b7-94ecf5816168")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("879c184e-660a-44d2-9757-c56228cf2ce4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return LinkEnd.class;
    }

    @objid ("259016f8-dd9a-40a3-9537-3e08e7d1c3dd")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cc53351d-e310-4b6c-90db-a8c2b23a2cd2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("1f280e63-0833-452e-8d28-03197feee391")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new LinkEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isOrderedAtt = new IsOrderedSmAttribute();
        this.isOrderedAtt.init("IsOrdered", this, Boolean.class );
        registerAttribute(this.isOrderedAtt);
        
        this.isUniqueAtt = new IsUniqueSmAttribute();
        this.isUniqueAtt.init("IsUnique", this, Boolean.class );
        registerAttribute(this.isUniqueAtt);
        
        this.multiplicityMaxAtt = new MultiplicityMaxSmAttribute();
        this.multiplicityMaxAtt.init("MultiplicityMax", this, String.class );
        registerAttribute(this.multiplicityMaxAtt);
        
        this.multiplicityMinAtt = new MultiplicityMinSmAttribute();
        this.multiplicityMinAtt.init("MultiplicityMin", this, String.class );
        registerAttribute(this.multiplicityMinAtt);
        
        
        // Initialize and register the SmDependency
        this.linkDep = new LinkSmDependency();
        this.linkDep.init("Link", this, metamodel.getMClass(Link.MQNAME), 0, 1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.linkDep);
        
        this.targetDep = new TargetSmDependency();
        this.targetDep.init("Target", this, metamodel.getMClass(Instance.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetDep);
        
        this.oppositeOwnerDep = new OppositeOwnerSmDependency();
        this.oppositeOwnerDep.init("OppositeOwner", this, metamodel.getMClass(LinkEnd.MQNAME), 1, 1 );
        registerDependency(this.oppositeOwnerDep);
        
        this.realizedInformationFlowDep = new RealizedInformationFlowSmDependency();
        this.realizedInformationFlowDep.init("RealizedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.realizedInformationFlowDep);
        
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("Model", this, metamodel.getMClass(AssociationEnd.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.modelDep);
        
        this.consumerDep = new ConsumerSmDependency();
        this.consumerDep.init("Consumer", this, metamodel.getMClass(RequiredInterface.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.consumerDep);
        
        this.oppositeDep = new OppositeSmDependency();
        this.oppositeDep.init("Opposite", this, metamodel.getMClass(LinkEnd.MQNAME), 1, 1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.oppositeDep);
        
        this.sourceDep = new SourceSmDependency();
        this.sourceDep.init("Source", this, metamodel.getMClass(Instance.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE, SmDirective.SMCDPARTOF);
        registerDependency(this.sourceDep);
        
        this.providerDep = new ProviderSmDependency();
        this.providerDep.init("Provider", this, metamodel.getMClass(ProvidedInterface.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.providerDep);
    }

    @objid ("70c8e35f-8a96-4c39-a282-e6597b087b1c")
    public SmAttribute getIsOrderedAtt() {
        if (this.isOrderedAtt == null) {
        	this.isOrderedAtt = this.getAttributeDef("IsOrdered");
        }
        return this.isOrderedAtt;
    }

    @objid ("a4864af4-69e7-4c31-aecf-44a3e096ee63")
    public SmAttribute getIsUniqueAtt() {
        if (this.isUniqueAtt == null) {
        	this.isUniqueAtt = this.getAttributeDef("IsUnique");
        }
        return this.isUniqueAtt;
    }

    @objid ("aeee3ad3-3d1b-4c4f-8cd3-21c18afa0dea")
    public SmAttribute getMultiplicityMaxAtt() {
        if (this.multiplicityMaxAtt == null) {
        	this.multiplicityMaxAtt = this.getAttributeDef("MultiplicityMax");
        }
        return this.multiplicityMaxAtt;
    }

    @objid ("0a914d4d-807d-4fad-a641-95bd8b07ad06")
    public SmAttribute getMultiplicityMinAtt() {
        if (this.multiplicityMinAtt == null) {
        	this.multiplicityMinAtt = this.getAttributeDef("MultiplicityMin");
        }
        return this.multiplicityMinAtt;
    }

    @objid ("66330095-6c9d-4c3b-a9c2-5063638c102d")
    public SmDependency getLinkDep() {
        if (this.linkDep == null) {
        	this.linkDep = this.getDependencyDef("Link");
        }
        return this.linkDep;
    }

    @objid ("5e6c62f1-17c6-40ba-b7b5-2f09105a5df2")
    public SmDependency getTargetDep() {
        if (this.targetDep == null) {
        	this.targetDep = this.getDependencyDef("Target");
        }
        return this.targetDep;
    }

    @objid ("073612e0-5c62-44d0-846b-f52c00bcf99d")
    public SmDependency getOppositeOwnerDep() {
        if (this.oppositeOwnerDep == null) {
        	this.oppositeOwnerDep = this.getDependencyDef("OppositeOwner");
        }
        return this.oppositeOwnerDep;
    }

    @objid ("dc95c87d-6b68-43a2-b126-1d9b3ecea2e5")
    public SmDependency getRealizedInformationFlowDep() {
        if (this.realizedInformationFlowDep == null) {
        	this.realizedInformationFlowDep = this.getDependencyDef("RealizedInformationFlow");
        }
        return this.realizedInformationFlowDep;
    }

    @objid ("4b212141-90df-47b8-a9f5-f7a7b664e6af")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("46feff02-1bf2-4b7f-aeaf-6cb23a9deb97")
    public SmDependency getConsumerDep() {
        if (this.consumerDep == null) {
        	this.consumerDep = this.getDependencyDef("Consumer");
        }
        return this.consumerDep;
    }

    @objid ("c4a6d590-2ff5-4c02-920a-3cffd7c688b5")
    public SmDependency getOppositeDep() {
        if (this.oppositeDep == null) {
        	this.oppositeDep = this.getDependencyDef("Opposite");
        }
        return this.oppositeDep;
    }

    @objid ("161aaaf9-f5b1-4a6d-b540-c3cab2190fca")
    public SmDependency getSourceDep() {
        if (this.sourceDep == null) {
        	this.sourceDep = this.getDependencyDef("Source");
        }
        return this.sourceDep;
    }

    @objid ("873bca8d-991d-49e5-841a-e4965c4d52f8")
    public SmDependency getProviderDep() {
        if (this.providerDep == null) {
        	this.providerDep = this.getDependencyDef("Provider");
        }
        return this.providerDep;
    }

    @objid ("10e9a08b-ae3b-49c1-9db9-97f4c13fd281")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("0dbf02c6-8bcd-4802-941a-112cb9771d10")
    private static class LinkEndObjectFactory implements ISmObjectFactory {
        @objid ("cc62083a-cddf-41ba-8f98-1d83508aca3e")
        private LinkEndSmClass smClass;

        @objid ("6d9507da-9c88-4580-8833-daf077603fc6")
        public LinkEndObjectFactory(LinkEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("07260db2-8084-490c-bc69-4010f4322dc0")
        @Override
        public ISmObjectData createData() {
            return new LinkEndData(this.smClass);
        }

        @objid ("8119b98c-b47a-43a7-b402-1370d09718a4")
        @Override
        public SmObjectImpl createImpl() {
            return new LinkEndImpl();
        }

    }

    @objid ("299fad1f-91c3-4ed1-81c0-0a7a8b70612c")
    public static class IsOrderedSmAttribute extends SmAttribute {
        @objid ("f6db3de8-7cf7-4eee-9866-ea5a70810b92")
        public Object getValue(ISmObjectData data) {
            return ((LinkEndData) data).mIsOrdered;
        }

        @objid ("dd2b7177-29e4-4c37-8081-f5315fb5bfb0")
        public void setValue(ISmObjectData data, Object value) {
            ((LinkEndData) data).mIsOrdered = value;
        }

    }

    @objid ("2742c533-1eaa-4930-901d-077f0caebec6")
    public static class IsUniqueSmAttribute extends SmAttribute {
        @objid ("c67fde79-fe72-46fb-9aea-7dbf997dfc4e")
        public Object getValue(ISmObjectData data) {
            return ((LinkEndData) data).mIsUnique;
        }

        @objid ("fdb4b2fb-4d42-4105-b853-b20ce931d405")
        public void setValue(ISmObjectData data, Object value) {
            ((LinkEndData) data).mIsUnique = value;
        }

    }

    @objid ("a738be67-4580-4056-9574-6e1b73080a0c")
    public static class MultiplicityMaxSmAttribute extends SmAttribute {
        @objid ("1cb8c235-1b0e-441a-b266-f0b41d341558")
        public Object getValue(ISmObjectData data) {
            return ((LinkEndData) data).mMultiplicityMax;
        }

        @objid ("b9e3230f-d792-45ab-aa3b-bc2cee692c02")
        public void setValue(ISmObjectData data, Object value) {
            ((LinkEndData) data).mMultiplicityMax = value;
        }

    }

    @objid ("22099628-d0a1-4014-a4d1-2ce52778401f")
    public static class MultiplicityMinSmAttribute extends SmAttribute {
        @objid ("88e6c05c-9f96-46e4-a479-6757789fb238")
        public Object getValue(ISmObjectData data) {
            return ((LinkEndData) data).mMultiplicityMin;
        }

        @objid ("1dc39579-ca97-4c06-9346-7d9d31e4d7b4")
        public void setValue(ISmObjectData data, Object value) {
            ((LinkEndData) data).mMultiplicityMin = value;
        }

    }

    @objid ("d17555ce-3740-4aa5-9ade-25822af830a6")
    public static class LinkSmDependency extends SmSingleDependency {
        @objid ("b53b23fb-101a-4e2c-bd5b-d5a72d738073")
        private SmDependency symetricDep;

        @objid ("19260ff3-7e15-405d-9a5e-15592332905e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mLink;
        }

        @objid ("fb184315-1380-4a44-8bcd-3db93b4cb956")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mLink = value;
        }

        @objid ("296f7652-f648-4b67-8a69-7f394a4d3704")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkSmClass)this.getTarget()).getLinkEndDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7333ac88-12e1-48e8-8afa-69c18168c2e8")
    public static class TargetSmDependency extends SmSingleDependency {
        @objid ("835d8edf-2c56-4025-ba9b-a0d49a3214d4")
        private SmDependency symetricDep;

        @objid ("18f69bf9-298a-4498-a383-f0f8216f752a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mTarget;
        }

        @objid ("619a9368-4b2b-4995-b11e-3171df33cd18")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mTarget = value;
        }

        @objid ("86dd2df8-e342-4385-af7a-d47dd2280309")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getTargetingEndDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2e384bfc-4bd8-4196-bee7-4721ab3aba73")
    public static class OppositeOwnerSmDependency extends SmSingleDependency {
        @objid ("c4e8264b-2ec9-40ce-8a95-ed08ceca4cc0")
        private SmDependency symetricDep;

        @objid ("74fd512f-f5b6-4bcb-b364-5368924dd5dd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mOppositeOwner;
        }

        @objid ("91db5a1e-ebf5-4ce8-9840-8d939b153cb0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mOppositeOwner = value;
        }

        @objid ("4de39760-1325-4292-a367-db577f43e284")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getOppositeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("49d2526b-54b4-490a-b68b-ade03ac8866f")
    public static class RealizedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("6c6c5e1b-39c2-4604-a341-e6c6df4c6b2f")
        private SmDependency symetricDep;

        @objid ("2b2d4229-6cf8-462a-9d71-5d633f02c39d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((LinkEndData)data).mRealizedInformationFlow != null)? ((LinkEndData)data).mRealizedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("c0118058-7fba-4161-8bf7-f6bebc32b7ea")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((LinkEndData) data).mRealizedInformationFlow = values;
        }

        @objid ("52bbb110-0d24-4ca6-ba17-217bccb17ec8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getRealizingLinkDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("298fabc5-94af-4e2e-9e34-d7ddb880439d")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("ad9706e1-0bb9-4245-9ef0-23c874362e2f")
        private SmDependency symetricDep;

        @objid ("4b8438c5-121b-4445-b4e0-ff8ee2f17e96")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mModel;
        }

        @objid ("04922622-fee0-4b89-91c7-77e5d855ebf6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mModel = value;
        }

        @objid ("11714f3d-39d6-4cbc-ac30-cb0eb244ae39")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getOccurenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("781286cb-a624-44e8-8b53-af56aa499c33")
    public static class ConsumerSmDependency extends SmSingleDependency {
        @objid ("f3d47205-fa9b-4efb-b019-fa48586951d8")
        private SmDependency symetricDep;

        @objid ("074906c2-0b23-4610-9dcf-f600459d072c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mConsumer;
        }

        @objid ("90525e88-8453-4727-af86-9000ec351c67")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mConsumer = value;
        }

        @objid ("3c27feb7-64da-44da-a87d-b1786b179e2d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RequiredInterfaceSmClass)this.getTarget()).getProviderDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("3cb1820a-a179-4e3b-a327-143152831488")
    public static class OppositeSmDependency extends SmSingleDependency {
        @objid ("1a0881ff-ed53-474b-a210-dc3b3a298c4c")
        private SmDependency symetricDep;

        @objid ("b23d242a-dd71-4846-8470-c0e8526d413a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mOpposite;
        }

        @objid ("8a6acb77-dd7c-4959-83d6-a8153b7a147d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mOpposite = value;
        }

        @objid ("0f8597bf-32ed-4f62-a1ed-c7b92f9b2bed")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getOppositeOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7ca8c9c3-7829-4ee8-96be-990fed573006")
    public static class SourceSmDependency extends SmSingleDependency {
        @objid ("cf78ce79-2e0a-44c8-8b34-2753086c1c1c")
        private SmDependency symetricDep;

        @objid ("8a192835-15eb-4a95-8673-2ffb9244723c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mSource;
        }

        @objid ("26be33fe-cfd1-467a-99bc-a9fb3ef9c911")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mSource = value;
        }

        @objid ("23a41d1e-b0b3-48a3-aae1-0ad8944897aa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getOwnedEndDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5c6a4514-555a-4ef9-92f6-b2ad381fd54f")
    public static class ProviderSmDependency extends SmSingleDependency {
        @objid ("28e396c2-c9d2-446d-b8b2-87205c15744d")
        private SmDependency symetricDep;

        @objid ("5ad66431-9347-4720-a91e-d0377a8040a1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkEndData) data).mProvider;
        }

        @objid ("f5d35653-4115-4788-99fe-0ce2d0315b2e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkEndData) data).mProvider = value;
        }

        @objid ("67678de9-ec86-48a8-b5c5-fe0d628b79e0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProvidedInterfaceSmClass)this.getTarget()).getConsumerDep();
            }
            return this.symetricDep;
        }

    }

}
