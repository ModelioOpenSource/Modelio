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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnLinkEventDefinitionData;
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

@objid ("7350b35c-294e-4083-ad55-23949813f2c1")
public class BpmnLinkEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("8435a1ef-063e-4471-9762-b2478f919cd5")
    private SmDependency sourceDep;

    @objid ("e4984aaa-0e60-4ece-8816-8d0c06de9819")
    private SmDependency targetDep;

    @objid ("288a2943-8c52-4bb9-b99c-81ad474ca6fb")
    public BpmnLinkEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("859f4d2c-bf41-433c-b433-a64a7c60c4a9")
    @Override
    public String getName() {
        return "BpmnLinkEventDefinition";
    }

    @objid ("dc6089e8-1af3-481f-9008-e60896444054")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("087f4d91-5a27-48cb-a0ba-a168901fa7ba")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnLinkEventDefinition.class;
    }

    @objid ("2c46092f-4bd7-4398-b511-c3f70bf6a83e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("91a95afc-979d-4cef-a5e9-f3da968ee774")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7e346bfa-126f-42da-8051-7b491e3b6c03")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnLinkEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.sourceDep = new SourceSmDependency();
        this.sourceDep.init("Source", this, metamodel.getMClass(BpmnLinkEventDefinition.MQNAME), 1, -1 );
        registerDependency(this.sourceDep);
        
        this.targetDep = new TargetSmDependency();
        this.targetDep.init("Target", this, metamodel.getMClass(BpmnLinkEventDefinition.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.targetDep);
    }

    @objid ("94156334-e3d5-45ea-acca-c0e91d104dd6")
    public SmDependency getSourceDep() {
        if (this.sourceDep == null) {
        	this.sourceDep = this.getDependencyDef("Source");
        }
        return this.sourceDep;
    }

    @objid ("86b27fb6-ae0d-46bb-aa25-c38b0717d823")
    public SmDependency getTargetDep() {
        if (this.targetDep == null) {
        	this.targetDep = this.getDependencyDef("Target");
        }
        return this.targetDep;
    }

    @objid ("49174d0a-8f63-4321-b375-236b28d981ca")
    private static class BpmnLinkEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("f87f4812-b599-4ae4-8c4a-306bf13ff1ad")
        private BpmnLinkEventDefinitionSmClass smClass;

        @objid ("5a1d2eef-afad-41c5-81cf-9a2fc68154e6")
        public BpmnLinkEventDefinitionObjectFactory(BpmnLinkEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("53bb6751-6659-443b-9439-6e92d39b7c10")
        @Override
        public ISmObjectData createData() {
            return new BpmnLinkEventDefinitionData(this.smClass);
        }

        @objid ("0c9ac067-6543-4ab5-9876-e55787bd3aab")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnLinkEventDefinitionImpl();
        }

    }

    @objid ("31d8c948-8f67-4b2a-b2b3-e3cc74e8ce06")
    public static class SourceSmDependency extends SmMultipleDependency {
        @objid ("907e79e3-9aae-4c22-94a4-98ce1f1555ed")
        private SmDependency symetricDep;

        @objid ("5b319f5c-0860-4b65-aa73-68c736208df4")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnLinkEventDefinitionData)data).mSource != null)? ((BpmnLinkEventDefinitionData)data).mSource:SmMultipleDependency.EMPTY;
        }

        @objid ("68009fa0-e733-4b5d-a94c-ef07851b20a6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnLinkEventDefinitionData) data).mSource = values;
        }

        @objid ("b3e47875-1c6d-47a2-b0d6-ac785ea776d0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLinkEventDefinitionSmClass)this.getTarget()).getTargetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e4e89453-8523-42ec-8d67-487c49fe01f0")
    public static class TargetSmDependency extends SmSingleDependency {
        @objid ("916f7198-b308-474c-b1d7-fd7cd2b7a32b")
        private SmDependency symetricDep;

        @objid ("f61c3d6d-18f8-41ba-a5ed-c8798e2b8b50")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLinkEventDefinitionData) data).mTarget;
        }

        @objid ("fc5e531c-0ab5-479f-bc61-df40635b0e97")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLinkEventDefinitionData) data).mTarget = value;
        }

        @objid ("33932f4b-8e63-4cc6-8bbd-07e3802d3e10")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLinkEventDefinitionSmClass)this.getTarget()).getSourceDep();
            }
            return this.symetricDep;
        }

    }

}
