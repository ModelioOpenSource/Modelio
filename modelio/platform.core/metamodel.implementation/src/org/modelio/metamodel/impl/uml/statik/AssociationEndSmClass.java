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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndData;
import org.modelio.metamodel.impl.uml.statik.AssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.StructuralFeatureSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.StructuralFeature;
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

@objid ("b262271d-e4c3-46de-a5e0-480accedf845")
public class AssociationEndSmClass extends StructuralFeatureSmClass {
    @objid ("ac73c567-2390-4065-9afc-8c6cce7fcbf2")
    private SmAttribute aggregationAtt;

    @objid ("33334201-f969-4ebc-b6fc-a3be7fb99294")
    private SmAttribute isChangeableAtt;

    @objid ("e5cd9f48-6cb1-4b42-af79-7c10d5a46870")
    private SmDependency targetDep;

    @objid ("dd70711a-ad55-4951-a913-472cabb25d13")
    private SmDependency oppositeOwnerDep;

    @objid ("f7c4995e-cd25-41be-b50c-2a7c92276e1a")
    private SmDependency sourceDep;

    @objid ("7f0c32a4-70d6-46a3-8ba4-d8e621058ff0")
    private SmDependency occurenceDep;

    @objid ("b685e5f6-30b0-4eed-915c-ea21f4e8b1f0")
    private SmDependency sentDep;

    @objid ("35a1a3d4-0133-4bbf-951d-5e33e983c0e6")
    private SmDependency qualifierDep;

    @objid ("51ca5927-c30d-41b5-b60b-ac38cb1739c9")
    private SmDependency oppositeDep;

    @objid ("9f47a70c-15ed-488d-893a-1e0250af94b8")
    private SmDependency representingObjectNodeDep;

    @objid ("fbebce67-564b-4346-b999-63340e924354")
    private SmDependency associationDep;

    @objid ("651f569c-296b-48df-8512-57c2426a5ef0")
    public AssociationEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ec32274c-fc22-4cce-abb0-4776e408b7ab")
    @Override
    public String getName() {
        return "AssociationEnd";
    }

    @objid ("752182b4-18bb-4de6-abfa-416abff2115d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("65c3232a-bc1c-462d-9de7-deaa2ea2713e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AssociationEnd.class;
    }

    @objid ("981384c5-0578-44c3-bfdd-2eba21e7a890")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5900c9d0-519e-4978-8ab5-cebcf8f319b7")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8727c504-b5c8-4933-8e56-36c1b4bb0cf3")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StructuralFeature.MQNAME);
        this.registerFactory(new AssociationEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.aggregationAtt = new AggregationSmAttribute();
        this.aggregationAtt.init("Aggregation", this, AggregationKind.class );
        registerAttribute(this.aggregationAtt);
        
        this.isChangeableAtt = new IsChangeableSmAttribute();
        this.isChangeableAtt.init("IsChangeable", this, Boolean.class );
        registerAttribute(this.isChangeableAtt);
        
        
        // Initialize and register the SmDependency
        this.targetDep = new TargetSmDependency();
        this.targetDep.init("Target", this, metamodel.getMClass(Classifier.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetDep);
        
        this.oppositeOwnerDep = new OppositeOwnerSmDependency();
        this.oppositeOwnerDep.init("OppositeOwner", this, metamodel.getMClass(AssociationEnd.MQNAME), 1, 1 );
        registerDependency(this.oppositeOwnerDep);
        
        this.sourceDep = new SourceSmDependency();
        this.sourceDep.init("Source", this, metamodel.getMClass(Classifier.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE, SmDirective.SMCDPARTOF);
        registerDependency(this.sourceDep);
        
        this.occurenceDep = new OccurenceSmDependency();
        this.occurenceDep.init("Occurence", this, metamodel.getMClass(LinkEnd.MQNAME), 0, -1 );
        registerDependency(this.occurenceDep);
        
        this.sentDep = new SentSmDependency();
        this.sentDep.init("Sent", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.sentDep);
        
        this.qualifierDep = new QualifierSmDependency();
        this.qualifierDep.init("Qualifier", this, metamodel.getMClass(Attribute.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.qualifierDep);
        
        this.oppositeDep = new OppositeSmDependency();
        this.oppositeDep.init("Opposite", this, metamodel.getMClass(AssociationEnd.MQNAME), 1, 1 , SmDirective.SMCDSHAREDCOMPONENT, SmDirective.SMCDPARTOF);
        registerDependency(this.oppositeDep);
        
        this.representingObjectNodeDep = new RepresentingObjectNodeSmDependency();
        this.representingObjectNodeDep.init("RepresentingObjectNode", this, metamodel.getMClass(ObjectNode.MQNAME), 0, -1 );
        registerDependency(this.representingObjectNodeDep);
        
        this.associationDep = new AssociationSmDependency();
        this.associationDep.init("Association", this, metamodel.getMClass(Association.MQNAME), 0, 1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.associationDep);
    }

    @objid ("48ad77aa-5911-4ad0-8dca-1305e6015066")
    public SmAttribute getAggregationAtt() {
        if (this.aggregationAtt == null) {
        	this.aggregationAtt = this.getAttributeDef("Aggregation");
        }
        return this.aggregationAtt;
    }

    @objid ("7f8d11af-8614-4b1f-b8f4-3e2797714e6c")
    public SmAttribute getIsChangeableAtt() {
        if (this.isChangeableAtt == null) {
        	this.isChangeableAtt = this.getAttributeDef("IsChangeable");
        }
        return this.isChangeableAtt;
    }

    @objid ("dd9f2f65-6d7c-4b0f-a54f-1c6b5fad6539")
    public SmDependency getTargetDep() {
        if (this.targetDep == null) {
        	this.targetDep = this.getDependencyDef("Target");
        }
        return this.targetDep;
    }

    @objid ("700080e4-221d-44db-bb17-883464b5b3e1")
    public SmDependency getOppositeOwnerDep() {
        if (this.oppositeOwnerDep == null) {
        	this.oppositeOwnerDep = this.getDependencyDef("OppositeOwner");
        }
        return this.oppositeOwnerDep;
    }

    @objid ("43b79045-49dd-4d2a-8494-94a877942061")
    public SmDependency getSourceDep() {
        if (this.sourceDep == null) {
        	this.sourceDep = this.getDependencyDef("Source");
        }
        return this.sourceDep;
    }

    @objid ("35d1e329-453c-49af-91d6-e145c5e61c56")
    public SmDependency getOccurenceDep() {
        if (this.occurenceDep == null) {
        	this.occurenceDep = this.getDependencyDef("Occurence");
        }
        return this.occurenceDep;
    }

    @objid ("1ffae73a-f40f-40e9-bbc5-d244cb8b21c4")
    public SmDependency getSentDep() {
        if (this.sentDep == null) {
        	this.sentDep = this.getDependencyDef("Sent");
        }
        return this.sentDep;
    }

    @objid ("4f90a57d-5657-4136-b3e7-e0a12fc14824")
    public SmDependency getQualifierDep() {
        if (this.qualifierDep == null) {
        	this.qualifierDep = this.getDependencyDef("Qualifier");
        }
        return this.qualifierDep;
    }

    @objid ("feb6ded4-8932-4350-b821-df681b1eafcf")
    public SmDependency getOppositeDep() {
        if (this.oppositeDep == null) {
        	this.oppositeDep = this.getDependencyDef("Opposite");
        }
        return this.oppositeDep;
    }

    @objid ("24c39fb0-f592-4778-9535-0418ed9eb81c")
    public SmDependency getRepresentingObjectNodeDep() {
        if (this.representingObjectNodeDep == null) {
        	this.representingObjectNodeDep = this.getDependencyDef("RepresentingObjectNode");
        }
        return this.representingObjectNodeDep;
    }

    @objid ("f84fdf84-80e9-4934-8ea3-8354f74fd65e")
    public SmDependency getAssociationDep() {
        if (this.associationDep == null) {
        	this.associationDep = this.getDependencyDef("Association");
        }
        return this.associationDep;
    }

    @objid ("b7304dc1-c0b6-44e4-bc8a-c928ce682d74")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("c755cf6a-9176-4b3e-84ee-ce0e6653cee0")
    private static class AssociationEndObjectFactory implements ISmObjectFactory {
        @objid ("b3f546a7-3579-46ac-b363-c7c5df5d7c1f")
        private AssociationEndSmClass smClass;

        @objid ("277855e7-0c44-4554-9c8d-871fb2a2f86b")
        public AssociationEndObjectFactory(AssociationEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("92e5e90f-5f38-4396-8319-ac5627a54baf")
        @Override
        public ISmObjectData createData() {
            return new AssociationEndData(this.smClass);
        }

        @objid ("722e3a7f-dbd6-4121-8de8-3cd395de4e53")
        @Override
        public SmObjectImpl createImpl() {
            return new AssociationEndImpl();
        }

    }

    @objid ("e22b9317-a200-4003-a22b-ac84fe9b529d")
    public static class AggregationSmAttribute extends SmAttribute {
        @objid ("fe9d4514-82a3-47aa-bb58-6bc6025741af")
        public Object getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mAggregation;
        }

        @objid ("900f4660-7721-4d84-996a-57e5adeac4f2")
        public void setValue(ISmObjectData data, Object value) {
            ((AssociationEndData) data).mAggregation = value;
        }

    }

    @objid ("85827985-6af9-4831-8c50-97c044471d81")
    public static class IsChangeableSmAttribute extends SmAttribute {
        @objid ("5718059d-3ca5-4325-aabd-e54b3a6659ff")
        public Object getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mIsChangeable;
        }

        @objid ("06e11544-807d-4cbb-a1e4-9652e81e7d84")
        public void setValue(ISmObjectData data, Object value) {
            ((AssociationEndData) data).mIsChangeable = value;
        }

    }

    @objid ("a808be23-d37a-4bd8-bc63-467f3365b1f2")
    public static class TargetSmDependency extends SmSingleDependency {
        @objid ("ad76914f-8603-4100-a60a-161c0204b9d7")
        private SmDependency symetricDep;

        @objid ("d50fe8ca-8e0f-4124-9dc7-e84b2e4507fc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mTarget;
        }

        @objid ("97838a25-cef3-4fce-a478-b44d527cde09")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AssociationEndData) data).mTarget = value;
        }

        @objid ("4a6b48f7-8ade-4d29-a7c3-43dfad37b8b2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getTargetingEndDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("29ec0581-183b-4158-9042-6cc1b1e92245")
    public static class OppositeOwnerSmDependency extends SmSingleDependency {
        @objid ("d258e042-7032-4b89-bd5a-c7a22c197c20")
        private SmDependency symetricDep;

        @objid ("9fae96f9-ba73-4360-9dd6-dce9ab96a328")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mOppositeOwner;
        }

        @objid ("f3cd5cc6-7d71-4695-9ed3-51b00bf97430")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AssociationEndData) data).mOppositeOwner = value;
        }

        @objid ("587ec5cf-12ad-4ce6-93ee-c549277cf66b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getOppositeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c357fa21-8b52-42d1-a3b4-f8a5c1315418")
    public static class SourceSmDependency extends SmSingleDependency {
        @objid ("2b7c8d58-4bea-48ea-870b-b71db26968fb")
        private SmDependency symetricDep;

        @objid ("82dc9e8c-4130-443d-b22e-f4d0371e4043")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mSource;
        }

        @objid ("f5bb672e-7139-4400-b432-18606b3a7ae8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AssociationEndData) data).mSource = value;
        }

        @objid ("1e7063b7-019b-46f4-a93b-0d764d30da54")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getOwnedEndDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("26b7b734-c54b-4bde-8f7b-e3ac58833ffe")
    public static class OccurenceSmDependency extends SmMultipleDependency {
        @objid ("bea7b3b7-5bc3-45b8-b26d-b2c68a0039de")
        private SmDependency symetricDep;

        @objid ("3fd5c859-4cd1-46e3-9636-14432030598f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AssociationEndData)data).mOccurence != null)? ((AssociationEndData)data).mOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("9ea75de2-a78b-4826-bfd3-4112582184fa")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AssociationEndData) data).mOccurence = values;
        }

        @objid ("310a45e0-d192-4b18-8d48-1b401a4b76a4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9ed8e04f-12fa-4bfa-ba44-0bef5a276229")
    public static class SentSmDependency extends SmMultipleDependency {
        @objid ("d991ab26-c409-494f-8c12-e8a4b0707df5")
        private SmDependency symetricDep;

        @objid ("d3ccda52-34c0-4a41-b90a-c5ce91fa2cb0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AssociationEndData)data).mSent != null)? ((AssociationEndData)data).mSent:SmMultipleDependency.EMPTY;
        }

        @objid ("19c4e4fe-2b87-4f33-ae4e-6c4438a0a728")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AssociationEndData) data).mSent = values;
        }

        @objid ("748f7fde-766b-4f98-975d-f69acd0153fa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getChannelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9b8e6644-4d3d-4dd2-b0aa-9d17fa9c0ec6")
    public static class QualifierSmDependency extends SmMultipleDependency {
        @objid ("8be0dfef-0ae3-4a5f-9ef9-93bdffa02a39")
        private SmDependency symetricDep;

        @objid ("951758a9-98f9-40aa-84fd-31c962768b17")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AssociationEndData)data).mQualifier != null)? ((AssociationEndData)data).mQualifier:SmMultipleDependency.EMPTY;
        }

        @objid ("5b17ff24-e52c-446c-ae83-a06a5676ffbf")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AssociationEndData) data).mQualifier = values;
        }

        @objid ("a9dd902d-2c19-44ea-ae09-110f6ecbedc2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeSmClass)this.getTarget()).getQualifiedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f8e98a0d-6a7c-4187-8ca7-42ab46974e22")
    public static class OppositeSmDependency extends SmSingleDependency {
        @objid ("1cf7adcf-6c9b-4b6a-a322-4342b9e72047")
        private SmDependency symetricDep;

        @objid ("ba820b84-16e9-4e42-9060-b72ee45197a2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mOpposite;
        }

        @objid ("da5421ef-d658-4dd9-ac97-fd49639da9c7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AssociationEndData) data).mOpposite = value;
        }

        @objid ("74fa1333-71e1-47ac-85ad-44eb7a373f9d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getOppositeOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2eea3770-c667-49c7-9f8e-16d470bef17e")
    public static class RepresentingObjectNodeSmDependency extends SmMultipleDependency {
        @objid ("b57e5aed-1af8-4798-85e0-1d29fa889208")
        private SmDependency symetricDep;

        @objid ("7fbe2086-15f5-4624-9b94-20712490ab32")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AssociationEndData)data).mRepresentingObjectNode != null)? ((AssociationEndData)data).mRepresentingObjectNode:SmMultipleDependency.EMPTY;
        }

        @objid ("7f0c1683-d538-4573-af48-afbe8cc5eb58")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AssociationEndData) data).mRepresentingObjectNode = values;
        }

        @objid ("191f7d93-a631-4403-add5-f4b108db6c84")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ObjectNodeSmClass)this.getTarget()).getRepresentedRoleDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ea8bef16-1dc9-46ac-a473-bf81ef42a1e1")
    public static class AssociationSmDependency extends SmSingleDependency {
        @objid ("ed0e8d6e-fbc7-4fb1-8a7e-2e0f7377e736")
        private SmDependency symetricDep;

        @objid ("fe9bc8e8-cd83-48b9-b375-0772bea51db0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AssociationEndData) data).mAssociation;
        }

        @objid ("9c439e91-ef68-456e-a2d6-4318154b7b77")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AssociationEndData) data).mAssociation = value;
        }

        @objid ("8b8e0c63-46c2-4564-927b-bc667c2b5b03")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationSmClass)this.getTarget()).getEndDep();
            }
            return this.symetricDep;
        }

    }

}
