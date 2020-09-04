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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionData;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
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

@objid ("38d2f50c-e36d-499e-9001-50707f981eb7")
public class BpmnEventDefinitionSmClass extends BpmnBaseElementSmClass {
    @objid ("835e3457-e6cf-43cf-b80f-cafee3394d25")
    private SmDependency definedDep;

    @objid ("91985d6c-9406-4a86-9f57-f657f9483e40")
    private SmDependency loopRefDep;

    @objid ("6a7d2270-3d52-4c80-9d16-f1732ecd725b")
    public BpmnEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0d22df07-354a-4513-ae7b-647120c5086a")
    @Override
    public String getName() {
        return "BpmnEventDefinition";
    }

    @objid ("93158474-61fc-4663-8fce-52e052b33a2c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f06228f3-2297-4dca-aa2d-4d12fa5d7bf3")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnEventDefinition.class;
    }

    @objid ("ce82ba33-c4ca-4993-940a-99385c486de2")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e4db35f0-983a-4f48-9503-352423430840")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("cf89ebfe-7a42-4c14-b852-d27a7827dde4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.definedDep = new DefinedSmDependency();
        this.definedDep.init("Defined", this, metamodel.getMClass(BpmnEvent.MQNAME), 0, 1 );
        registerDependency(this.definedDep);
        
        this.loopRefDep = new LoopRefSmDependency();
        this.loopRefDep.init("LoopRef", this, metamodel.getMClass(BpmnMultiInstanceLoopCharacteristics.MQNAME), 0, -1 );
        registerDependency(this.loopRefDep);
    }

    @objid ("a68b5c5e-af43-499a-8c17-51c838a1087c")
    public SmDependency getDefinedDep() {
        if (this.definedDep == null) {
        	this.definedDep = this.getDependencyDef("Defined");
        }
        return this.definedDep;
    }

    @objid ("52d38a02-6c6d-4600-974c-7bd430839865")
    public SmDependency getLoopRefDep() {
        if (this.loopRefDep == null) {
        	this.loopRefDep = this.getDependencyDef("LoopRef");
        }
        return this.loopRefDep;
    }

    @objid ("37a1899a-1102-4a93-9c62-eb6241e7cdd5")
    private static class BpmnEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("ec139bce-b498-41f8-940c-fec367f31d77")
        private BpmnEventDefinitionSmClass smClass;

        @objid ("f23e863b-3265-4239-9171-a719aa3f051a")
        public BpmnEventDefinitionObjectFactory(BpmnEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("45288e32-591f-4598-aa5d-bf6d13ddc09f")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("72a1b6d5-c801-4c9e-87f1-09776fde18df")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("266dbfb8-57ed-49c6-b659-a25c34bd1237")
    public static class DefinedSmDependency extends SmSingleDependency {
        @objid ("66b2513e-a08d-454d-a374-b53ee11b6440")
        private SmDependency symetricDep;

        @objid ("35e7a274-51ad-4f99-952e-dfd45b9d1a77")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnEventDefinitionData) data).mDefined;
        }

        @objid ("f05c63bb-b368-4865-b79b-765132cba316")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnEventDefinitionData) data).mDefined = value;
        }

        @objid ("1cc755b2-c58b-4f3c-8d8e-b2d6986c73b8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnEventSmClass)this.getTarget()).getEventDefinitionsDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("103eab20-3fa1-4d52-a76a-294832bc74a8")
    public static class LoopRefSmDependency extends SmMultipleDependency {
        @objid ("aeefb6ef-e196-41e8-b024-02c8c1eae303")
        private SmDependency symetricDep;

        @objid ("fe756f0b-9afb-4026-9654-c0177260f874")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnEventDefinitionData)data).mLoopRef != null)? ((BpmnEventDefinitionData)data).mLoopRef:SmMultipleDependency.EMPTY;
        }

        @objid ("6815698c-4516-457a-b561-12231e5734c2")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnEventDefinitionData) data).mLoopRef = values;
        }

        @objid ("cefd6323-4ebc-47e9-87a8-aae3227913a3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMultiInstanceLoopCharacteristicsSmClass)this.getTarget()).getCompletionEventRefDep();
            }
            return this.symetricDep;
        }

    }

}
