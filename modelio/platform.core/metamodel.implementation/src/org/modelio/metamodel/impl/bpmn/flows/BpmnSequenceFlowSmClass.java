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
package org.modelio.metamodel.impl.bpmn.flows;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowData;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnComplexGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnExclusiveGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnInclusiveGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnSequenceFlowDataAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeSmClass;
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

@objid ("eaa10c45-5666-4efc-ac31-887820f6c9d1")
public class BpmnSequenceFlowSmClass extends BpmnFlowElementSmClass {
    @objid ("05f8a303-947d-4a33-a626-ca4c5b6aead0")
    private SmAttribute isImmediateAtt;

    @objid ("826cc179-e20d-472f-8fa1-5f6b8900f051")
    private SmAttribute conditionExpressionAtt;

    @objid ("57d092ae-4486-458b-bee7-acf953efd695")
    private SmDependency sourceRefDep;

    @objid ("1545ffcf-5673-492c-bc41-0b1a6ac1ecea")
    private SmDependency targetRefDep;

    @objid ("6086702c-ad89-4e8a-a6c4-2a626cbf16e2")
    private SmDependency defaultOfInclusiveDep;

    @objid ("5fe4f423-40ec-4e09-aa74-e4391689464a")
    private SmDependency defaultFromDep;

    @objid ("8d5a072d-11cb-4e7c-9c5b-5aa3d5fc00bd")
    private SmDependency defaultOfExclusiveDep;

    @objid ("a16ba77b-641e-4952-9181-cb581d30c55e")
    private SmDependency connectorDep;

    @objid ("8ab2e8f8-300b-4255-93dd-96809b107110")
    private SmDependency defaultOfComplexDep;

    @objid ("02cb7dc2-1682-43e5-ba7f-d3dd68ea0403")
    public BpmnSequenceFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ddd6b1e1-1fe2-4798-abd2-9bb5a6d151ab")
    @Override
    public String getName() {
        return "BpmnSequenceFlow";
    }

    @objid ("3a84e5f4-1840-484b-8830-9c7c7678bde7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9426ad25-bd99-4b1e-864a-c9ba33a3f4a5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSequenceFlow.class;
    }

    @objid ("052cb0da-1603-4c83-927a-272475b32ff8")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("bb2b1568-4d3e-4121-9845-eda12a7eacf6")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a0432bbc-cffb-4d32-92f5-9d340160ad16")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnFlowElement.MQNAME);
        this.registerFactory(new BpmnSequenceFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isImmediateAtt = new IsImmediateSmAttribute();
        this.isImmediateAtt.init("IsImmediate", this, Boolean.class );
        registerAttribute(this.isImmediateAtt);
        
        this.conditionExpressionAtt = new ConditionExpressionSmAttribute();
        this.conditionExpressionAtt.init("ConditionExpression", this, String.class );
        registerAttribute(this.conditionExpressionAtt);
        
        
        // Initialize and register the SmDependency
        this.sourceRefDep = new SourceRefSmDependency();
        this.sourceRefDep.init("SourceRef", this, metamodel.getMClass(BpmnFlowNode.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE, SmDirective.SMCDPARTOF);
        registerDependency(this.sourceRefDep);
        
        this.targetRefDep = new TargetRefSmDependency();
        this.targetRefDep.init("TargetRef", this, metamodel.getMClass(BpmnFlowNode.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetRefDep);
        
        this.defaultOfInclusiveDep = new DefaultOfInclusiveSmDependency();
        this.defaultOfInclusiveDep.init("DefaultOfInclusive", this, metamodel.getMClass(BpmnInclusiveGateway.MQNAME), 0, 1 );
        registerDependency(this.defaultOfInclusiveDep);
        
        this.defaultFromDep = new DefaultFromSmDependency();
        this.defaultFromDep.init("DefaultFrom", this, metamodel.getMClass(BpmnActivity.MQNAME), 0, 1 );
        registerDependency(this.defaultFromDep);
        
        this.defaultOfExclusiveDep = new DefaultOfExclusiveSmDependency();
        this.defaultOfExclusiveDep.init("DefaultOfExclusive", this, metamodel.getMClass(BpmnExclusiveGateway.MQNAME), 0, 1 );
        registerDependency(this.defaultOfExclusiveDep);
        
        this.connectorDep = new ConnectorSmDependency();
        this.connectorDep.init("Connector", this, metamodel.getMClass(BpmnSequenceFlowDataAssociation.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.connectorDep);
        
        this.defaultOfComplexDep = new DefaultOfComplexSmDependency();
        this.defaultOfComplexDep.init("DefaultOfComplex", this, metamodel.getMClass(BpmnComplexGateway.MQNAME), 0, 1 );
        registerDependency(this.defaultOfComplexDep);
    }

    @objid ("13fd7e89-979d-489d-8121-22941d971831")
    public SmAttribute getIsImmediateAtt() {
        if (this.isImmediateAtt == null) {
        	this.isImmediateAtt = this.getAttributeDef("IsImmediate");
        }
        return this.isImmediateAtt;
    }

    @objid ("5cd25e35-7c71-44c9-b76e-2f0e8a36df37")
    public SmAttribute getConditionExpressionAtt() {
        if (this.conditionExpressionAtt == null) {
        	this.conditionExpressionAtt = this.getAttributeDef("ConditionExpression");
        }
        return this.conditionExpressionAtt;
    }

    @objid ("aa154fe0-3b6c-48aa-9d56-d7f62d7ac1ac")
    public SmDependency getSourceRefDep() {
        if (this.sourceRefDep == null) {
        	this.sourceRefDep = this.getDependencyDef("SourceRef");
        }
        return this.sourceRefDep;
    }

    @objid ("1dcd3e86-dc6f-4f57-813a-646a026f8e07")
    public SmDependency getTargetRefDep() {
        if (this.targetRefDep == null) {
        	this.targetRefDep = this.getDependencyDef("TargetRef");
        }
        return this.targetRefDep;
    }

    @objid ("4e8fd648-af65-4502-a1b9-cd8df8aa6621")
    public SmDependency getDefaultOfInclusiveDep() {
        if (this.defaultOfInclusiveDep == null) {
        	this.defaultOfInclusiveDep = this.getDependencyDef("DefaultOfInclusive");
        }
        return this.defaultOfInclusiveDep;
    }

    @objid ("efe6f0e4-77d3-459a-b4cb-c5fc9a73c4b3")
    public SmDependency getDefaultFromDep() {
        if (this.defaultFromDep == null) {
        	this.defaultFromDep = this.getDependencyDef("DefaultFrom");
        }
        return this.defaultFromDep;
    }

    @objid ("7d21e644-1eaf-48ad-bb35-fce8dd592eec")
    public SmDependency getDefaultOfExclusiveDep() {
        if (this.defaultOfExclusiveDep == null) {
        	this.defaultOfExclusiveDep = this.getDependencyDef("DefaultOfExclusive");
        }
        return this.defaultOfExclusiveDep;
    }

    @objid ("5cfa0b78-62bf-4656-8750-e4e5eabd4333")
    public SmDependency getConnectorDep() {
        if (this.connectorDep == null) {
        	this.connectorDep = this.getDependencyDef("Connector");
        }
        return this.connectorDep;
    }

    @objid ("147b0a30-8fe7-4609-8ebc-715c02959b97")
    public SmDependency getDefaultOfComplexDep() {
        if (this.defaultOfComplexDep == null) {
        	this.defaultOfComplexDep = this.getDependencyDef("DefaultOfComplex");
        }
        return this.defaultOfComplexDep;
    }

    @objid ("5a8f6b1b-6926-48f8-b6a8-009724a29696")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("da86b57c-768f-4f42-aebe-5d2f5c87fe2c")
    private static class BpmnSequenceFlowObjectFactory implements ISmObjectFactory {
        @objid ("8bd3e18b-4050-4ed5-9396-a439702c0271")
        private BpmnSequenceFlowSmClass smClass;

        @objid ("c510490f-be34-4577-a2ae-66e4c57f2248")
        public BpmnSequenceFlowObjectFactory(BpmnSequenceFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("16c4510c-1ac1-424d-afc2-62059393da25")
        @Override
        public ISmObjectData createData() {
            return new BpmnSequenceFlowData(this.smClass);
        }

        @objid ("c94f021c-3eb5-40cb-a6cc-1c254271418b")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSequenceFlowImpl();
        }

    }

    @objid ("640509bd-c1a4-4617-89e8-da54855bef72")
    public static class IsImmediateSmAttribute extends SmAttribute {
        @objid ("51f3fb5c-5100-4dd8-9900-2576466c66a2")
        public Object getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mIsImmediate;
        }

        @objid ("aaf32b4b-e41f-4410-b24f-ff7008ba5a3a")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnSequenceFlowData) data).mIsImmediate = value;
        }

    }

    @objid ("28e77bfe-b80f-47c5-8873-1acaaa061f88")
    public static class ConditionExpressionSmAttribute extends SmAttribute {
        @objid ("23f0cfb5-5fe2-4746-9ebf-8d517f9e04e2")
        public Object getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mConditionExpression;
        }

        @objid ("589f06d1-ca70-46bc-99c1-2cfe29aa6280")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnSequenceFlowData) data).mConditionExpression = value;
        }

    }

    @objid ("8a4037db-f1b5-4389-8421-495338e4b686")
    public static class SourceRefSmDependency extends SmSingleDependency {
        @objid ("38109b32-c0ea-412c-864c-d26177c6f083")
        private SmDependency symetricDep;

        @objid ("6f04e7ba-d09d-4cd1-b5bb-395a52f3e053")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mSourceRef;
        }

        @objid ("5c5da7a2-de00-472f-ab1f-29d1cb275428")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowData) data).mSourceRef = value;
        }

        @objid ("34bbbe63-3171-432c-b0c2-de9bdab9c200")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowNodeSmClass)this.getTarget()).getOutgoingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1c75ba25-cc84-4dc7-a6e5-7f1e3858c9d2")
    public static class TargetRefSmDependency extends SmSingleDependency {
        @objid ("90bd34f3-e99f-48f2-8280-7d117fa99ff2")
        private SmDependency symetricDep;

        @objid ("acfe0292-62fd-40e7-bc00-6387468c3220")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mTargetRef;
        }

        @objid ("4c3e9383-9566-404e-9b47-1abf19156aa2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowData) data).mTargetRef = value;
        }

        @objid ("7df58a0f-182d-46ca-b11e-2fde0ee821c6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowNodeSmClass)this.getTarget()).getIncomingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bfad618d-687c-4829-a2e5-e7af5ffe1768")
    public static class DefaultOfInclusiveSmDependency extends SmSingleDependency {
        @objid ("6e95866b-377f-4382-95c1-9e5e1ef299bb")
        private SmDependency symetricDep;

        @objid ("1c660e81-6f19-467e-8dfc-fcd66dad9cc7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mDefaultOfInclusive;
        }

        @objid ("35679c10-18cf-4b8f-9cf5-f363cab07fe2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowData) data).mDefaultOfInclusive = value;
        }

        @objid ("1d48df76-e9f6-4bc7-98e0-10d463b45ec6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnInclusiveGatewaySmClass)this.getTarget()).getDefaultFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4a7daf9c-1c38-46d0-b98e-9c729f088e3f")
    public static class DefaultFromSmDependency extends SmSingleDependency {
        @objid ("94eb4d8c-2bd7-4e05-868a-feb7f4cdf4f3")
        private SmDependency symetricDep;

        @objid ("127d308a-4b89-46ec-86f8-854e8c6095dd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mDefaultFrom;
        }

        @objid ("376389a2-d29e-4d54-8235-67f1d417f011")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowData) data).mDefaultFrom = value;
        }

        @objid ("ca353799-91de-4ca3-add5-4edd1b632c64")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getDefaultFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("63f423c5-f26b-4a1f-b524-0a828d189bbf")
    public static class DefaultOfExclusiveSmDependency extends SmSingleDependency {
        @objid ("a552f0e5-d8de-49e0-a105-4dca5b121362")
        private SmDependency symetricDep;

        @objid ("ca2829af-8505-4aab-a750-8ed05679ee6a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mDefaultOfExclusive;
        }

        @objid ("9464b319-6047-460f-a4d4-6d487b0d64b0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowData) data).mDefaultOfExclusive = value;
        }

        @objid ("454ec3e1-f69b-4cda-8ec2-28274bfec128")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnExclusiveGatewaySmClass)this.getTarget()).getDefaultFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f42b4ddc-65af-43b6-9fec-1cd4ac02d69f")
    public static class ConnectorSmDependency extends SmMultipleDependency {
        @objid ("3b889d05-e9a7-4975-96bd-d2ac3e47e790")
        private SmDependency symetricDep;

        @objid ("e81d2716-fffe-4af8-b1cd-083aea350c7e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnSequenceFlowData)data).mConnector != null)? ((BpmnSequenceFlowData)data).mConnector:SmMultipleDependency.EMPTY;
        }

        @objid ("9e845dab-8e24-434b-8587-5242427fe243")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnSequenceFlowData) data).mConnector = values;
        }

        @objid ("c436f7ed-6149-4800-ab6a-05214e3b0b1a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowDataAssociationSmClass)this.getTarget()).getConnectedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f6c795f9-47b0-4d42-a03b-be9c5414b9c8")
    public static class DefaultOfComplexSmDependency extends SmSingleDependency {
        @objid ("ee7b22bd-4a93-4557-b2e3-b00f0642468b")
        private SmDependency symetricDep;

        @objid ("f723cde5-8326-4419-aad7-5ad951699a67")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowData) data).mDefaultOfComplex;
        }

        @objid ("42d92d6d-89de-45d1-b816-ceeb1517e73f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowData) data).mDefaultOfComplex = value;
        }

        @objid ("cd529762-78cb-4f2e-a57d-7f94aae2ece3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnComplexGatewaySmClass)this.getTarget()).getDefaultFlowDep();
            }
            return this.symetricDep;
        }

    }

}
