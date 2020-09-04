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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.impl.bpmn.activities.BpmnComplexBehaviorDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsData;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataInputSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataOutputSmClass;
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

@objid ("728eb919-46e7-481f-99ed-6fb053421570")
public class BpmnMultiInstanceLoopCharacteristicsSmClass extends BpmnLoopCharacteristicsSmClass {
    @objid ("82e1382d-cf01-4385-acb1-bed2718dc424")
    private SmAttribute isSequencialAtt;

    @objid ("e8eb59e7-5829-49a8-9d56-59ea13455aac")
    private SmAttribute behaviorAtt;

    @objid ("ede11ff5-9626-4f7e-afff-9d5fa8c7d3aa")
    private SmAttribute loopCardinalityAtt;

    @objid ("0cd65a85-d931-49aa-baba-d8f8b535e088")
    private SmAttribute completionConditionAtt;

    @objid ("9bd7fef5-3005-4bca-83ab-e33c928a507a")
    private SmDependency loopDataInputDep;

    @objid ("2466b30b-d26c-4006-8280-1a599cbb3327")
    private SmDependency loopDataOutputRefDep;

    @objid ("461fa039-6434-4a7b-84f6-b6dea5262f3d")
    private SmDependency completionEventRefDep;

    @objid ("bac4a734-5dfe-4ea7-8ac6-e6c4c12e455d")
    private SmDependency complexBehaviorDefinitionDep;

    @objid ("698a0ad0-3128-4d9e-9398-c513f5abb361")
    public BpmnMultiInstanceLoopCharacteristicsSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d25d4b1c-d314-4d19-bf18-44370def5183")
    @Override
    public String getName() {
        return "BpmnMultiInstanceLoopCharacteristics";
    }

    @objid ("d5d2c4e8-62da-4c38-bb3b-7c9111a438ce")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("af58d63d-24bc-4c49-8efb-e14de81b0543")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnMultiInstanceLoopCharacteristics.class;
    }

    @objid ("105786ec-fd57-47a1-818f-530c1f1776d6")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("81f4d630-64b9-4bc5-82d3-7ae4c2248edb")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("d0c9ef0d-5dae-4adc-aebd-b37944a739fa")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnLoopCharacteristics.MQNAME);
        this.registerFactory(new BpmnMultiInstanceLoopCharacteristicsObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isSequencialAtt = new IsSequencialSmAttribute();
        this.isSequencialAtt.init("IsSequencial", this, Boolean.class );
        registerAttribute(this.isSequencialAtt);
        
        this.behaviorAtt = new BehaviorSmAttribute();
        this.behaviorAtt.init("Behavior", this, MultiInstanceBehavior.class );
        registerAttribute(this.behaviorAtt);
        
        this.loopCardinalityAtt = new LoopCardinalitySmAttribute();
        this.loopCardinalityAtt.init("LoopCardinality", this, String.class );
        registerAttribute(this.loopCardinalityAtt);
        
        this.completionConditionAtt = new CompletionConditionSmAttribute();
        this.completionConditionAtt.init("CompletionCondition", this, String.class );
        registerAttribute(this.completionConditionAtt);
        
        
        // Initialize and register the SmDependency
        this.loopDataInputDep = new LoopDataInputSmDependency();
        this.loopDataInputDep.init("LoopDataInput", this, metamodel.getMClass(BpmnDataInput.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.loopDataInputDep);
        
        this.loopDataOutputRefDep = new LoopDataOutputRefSmDependency();
        this.loopDataOutputRefDep.init("LoopDataOutputRef", this, metamodel.getMClass(BpmnDataOutput.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.loopDataOutputRefDep);
        
        this.completionEventRefDep = new CompletionEventRefSmDependency();
        this.completionEventRefDep.init("CompletionEventRef", this, metamodel.getMClass(BpmnEventDefinition.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.completionEventRefDep);
        
        this.complexBehaviorDefinitionDep = new ComplexBehaviorDefinitionSmDependency();
        this.complexBehaviorDefinitionDep.init("ComplexBehaviorDefinition", this, metamodel.getMClass(BpmnComplexBehaviorDefinition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.complexBehaviorDefinitionDep);
    }

    @objid ("5e8f5247-e4bf-4019-a08b-6724f117f7c0")
    public SmAttribute getIsSequencialAtt() {
        if (this.isSequencialAtt == null) {
        	this.isSequencialAtt = this.getAttributeDef("IsSequencial");
        }
        return this.isSequencialAtt;
    }

    @objid ("e91f1fcf-4afd-4192-879d-9b30c4ac93a6")
    public SmAttribute getBehaviorAtt() {
        if (this.behaviorAtt == null) {
        	this.behaviorAtt = this.getAttributeDef("Behavior");
        }
        return this.behaviorAtt;
    }

    @objid ("c8eac9e1-6409-426d-bd96-32489fb1ba1d")
    public SmAttribute getLoopCardinalityAtt() {
        if (this.loopCardinalityAtt == null) {
        	this.loopCardinalityAtt = this.getAttributeDef("LoopCardinality");
        }
        return this.loopCardinalityAtt;
    }

    @objid ("127863eb-8cf8-4752-875c-6300341ee918")
    public SmAttribute getCompletionConditionAtt() {
        if (this.completionConditionAtt == null) {
        	this.completionConditionAtt = this.getAttributeDef("CompletionCondition");
        }
        return this.completionConditionAtt;
    }

    @objid ("5ab16df3-8793-4890-a1a7-8f917db9a43c")
    public SmDependency getLoopDataInputDep() {
        if (this.loopDataInputDep == null) {
        	this.loopDataInputDep = this.getDependencyDef("LoopDataInput");
        }
        return this.loopDataInputDep;
    }

    @objid ("5eefd683-9b47-4c90-9fb8-729e336d2b1a")
    public SmDependency getLoopDataOutputRefDep() {
        if (this.loopDataOutputRefDep == null) {
        	this.loopDataOutputRefDep = this.getDependencyDef("LoopDataOutputRef");
        }
        return this.loopDataOutputRefDep;
    }

    @objid ("3b53f80f-37be-45d6-b2f0-b3c13429cc3b")
    public SmDependency getCompletionEventRefDep() {
        if (this.completionEventRefDep == null) {
        	this.completionEventRefDep = this.getDependencyDef("CompletionEventRef");
        }
        return this.completionEventRefDep;
    }

    @objid ("d1840d82-87b6-4470-8c2e-fde515fda60d")
    public SmDependency getComplexBehaviorDefinitionDep() {
        if (this.complexBehaviorDefinitionDep == null) {
        	this.complexBehaviorDefinitionDep = this.getDependencyDef("ComplexBehaviorDefinition");
        }
        return this.complexBehaviorDefinitionDep;
    }

    @objid ("aab2d464-9675-4cd7-be87-07cad63c2e93")
    private static class BpmnMultiInstanceLoopCharacteristicsObjectFactory implements ISmObjectFactory {
        @objid ("d5cf4570-86ff-45bd-acfe-711f91a5e9fe")
        private BpmnMultiInstanceLoopCharacteristicsSmClass smClass;

        @objid ("0751bea2-3687-47c7-8bb5-ee73fefa0b15")
        public BpmnMultiInstanceLoopCharacteristicsObjectFactory(BpmnMultiInstanceLoopCharacteristicsSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("444855d7-68e3-4afe-9bfc-accc235c2850")
        @Override
        public ISmObjectData createData() {
            return new BpmnMultiInstanceLoopCharacteristicsData(this.smClass);
        }

        @objid ("8eba589e-6732-4ada-ac1a-cde2031eb77f")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnMultiInstanceLoopCharacteristicsImpl();
        }

    }

    @objid ("e3706e54-232a-4e57-9012-0c1a2eda559b")
    public static class IsSequencialSmAttribute extends SmAttribute {
        @objid ("98122989-1d59-4c5f-a36b-53b52d14f372")
        public Object getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mIsSequencial;
        }

        @objid ("493650d8-a7ce-4c5e-bdd8-056ecb360da4")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mIsSequencial = value;
        }

    }

    @objid ("4da80f46-d54f-4d4c-b9f6-a121218c86a5")
    public static class BehaviorSmAttribute extends SmAttribute {
        @objid ("0d53c285-681a-462a-b517-301ddaa56038")
        public Object getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mBehavior;
        }

        @objid ("0d4eb03a-8e7b-4ba4-8e4b-0aad5e6b2fc5")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mBehavior = value;
        }

    }

    @objid ("77e8bd81-ca50-4150-8fe8-1a35f2e4f9c4")
    public static class LoopCardinalitySmAttribute extends SmAttribute {
        @objid ("5e78c194-c20c-439a-8370-8b45bf3a4158")
        public Object getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mLoopCardinality;
        }

        @objid ("2d1b7ed6-d15b-451d-bf24-ae6b3101db17")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mLoopCardinality = value;
        }

    }

    @objid ("3eaf4979-d31a-4493-8c40-1c5b0458f8b5")
    public static class CompletionConditionSmAttribute extends SmAttribute {
        @objid ("2897e924-86b2-4686-9df7-fc7482f7db6d")
        public Object getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mCompletionCondition;
        }

        @objid ("2c7c1577-3b2f-48a4-a54e-5dbf172ba3a9")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mCompletionCondition = value;
        }

    }

    @objid ("e050e52e-81bf-4f54-9f0a-3765855dafb9")
    public static class LoopDataInputSmDependency extends SmSingleDependency {
        @objid ("a4475c7e-4621-4a98-b3b9-1b22827117e6")
        private SmDependency symetricDep;

        @objid ("016129a3-90cd-42c3-90e8-54c957134244")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mLoopDataInput;
        }

        @objid ("b4310294-0847-414b-afc1-fe8e42e07b1c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mLoopDataInput = value;
        }

        @objid ("226d1eb5-fa75-4b0b-8bc5-3c03ac28a5a3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataInputSmClass)this.getTarget()).getOwnerLoopCharacteristicsDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2f7564e8-faa9-4d9b-98d5-db8234433109")
    public static class LoopDataOutputRefSmDependency extends SmSingleDependency {
        @objid ("d5dbe96d-fd69-4214-92cd-6466eba458f7")
        private SmDependency symetricDep;

        @objid ("ee4cecb8-1356-4960-b040-355966ff4060")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mLoopDataOutputRef;
        }

        @objid ("87bf7e12-28c0-4f43-b89e-4ab67e8cc1c0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mLoopDataOutputRef = value;
        }

        @objid ("b79a8e4b-cdf7-4009-a6e3-99708d0fb563")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataOutputSmClass)this.getTarget()).getOwnerLoopCharacteristicsDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("617ca00c-dd2d-4ebe-89cd-cad1a68db1a0")
    public static class CompletionEventRefSmDependency extends SmSingleDependency {
        @objid ("f9704b56-6d6f-44fd-aacc-132c44d47d81")
        private SmDependency symetricDep;

        @objid ("233d2f42-ff26-4881-bcde-114638e569a6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMultiInstanceLoopCharacteristicsData) data).mCompletionEventRef;
        }

        @objid ("79a69dc4-2d79-49b3-a28e-2af4477760b8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mCompletionEventRef = value;
        }

        @objid ("6bca3f7e-0015-4f23-a285-95baf84f7448")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnEventDefinitionSmClass)this.getTarget()).getLoopRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("175960fb-79c5-4cc5-94ad-bbf5eff52359")
    public static class ComplexBehaviorDefinitionSmDependency extends SmMultipleDependency {
        @objid ("e4e65ee1-457f-4dff-ac96-3b69a2a4200c")
        private SmDependency symetricDep;

        @objid ("264217a0-dd47-46ed-9a6a-f2c805a133d3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMultiInstanceLoopCharacteristicsData)data).mComplexBehaviorDefinition != null)? ((BpmnMultiInstanceLoopCharacteristicsData)data).mComplexBehaviorDefinition:SmMultipleDependency.EMPTY;
        }

        @objid ("61fa86c0-b5af-483c-8bfc-e5f86d647ba5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMultiInstanceLoopCharacteristicsData) data).mComplexBehaviorDefinition = values;
        }

        @objid ("45cf989e-f13c-4194-9c5f-c785073bec3b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnComplexBehaviorDefinitionSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
