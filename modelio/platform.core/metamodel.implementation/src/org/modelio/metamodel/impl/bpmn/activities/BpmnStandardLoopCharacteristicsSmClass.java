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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.impl.bpmn.activities.BpmnLoopCharacteristicsSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c015d3f2-1b6d-4184-822a-251f1714a2c3")
public class BpmnStandardLoopCharacteristicsSmClass extends BpmnLoopCharacteristicsSmClass {
    @objid ("9e8e33ca-de8d-42d1-837f-3f4a04f69b70")
    private SmAttribute testBeforeAtt;

    @objid ("613c4552-b0d8-4d10-80a9-d86532e59873")
    private SmAttribute loopConditionAtt;

    @objid ("1cadc3e6-63a1-4c2a-8c9b-c28357fa22fe")
    private SmAttribute loopMaximumAtt;

    @objid ("e21b6304-7166-4582-bfcb-a093efaa76db")
    public BpmnStandardLoopCharacteristicsSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cd663077-c74f-4315-a299-0a1784bc996e")
    @Override
    public String getName() {
        return "BpmnStandardLoopCharacteristics";
    }

    @objid ("86dcef59-03e1-4adb-b2fd-30ba55b4a09e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9c5ed1c0-552f-4eb6-b2ee-60cb573968d3")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnStandardLoopCharacteristics.class;
    }

    @objid ("fbb5e69b-b534-48ba-bbe7-96d8c179274e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("96e8e2ae-0988-416a-84b0-4efce3165560")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("b0299336-de2e-4fab-be42-260cb27822be")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnLoopCharacteristics.MQNAME);
        this.registerFactory(new BpmnStandardLoopCharacteristicsObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.testBeforeAtt = new TestBeforeSmAttribute();
        this.testBeforeAtt.init("TestBefore", this, Boolean.class );
        registerAttribute(this.testBeforeAtt);
        
        this.loopConditionAtt = new LoopConditionSmAttribute();
        this.loopConditionAtt.init("LoopCondition", this, String.class );
        registerAttribute(this.loopConditionAtt);
        
        this.loopMaximumAtt = new LoopMaximumSmAttribute();
        this.loopMaximumAtt.init("LoopMaximum", this, String.class );
        registerAttribute(this.loopMaximumAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("a290a92e-db5b-424d-983c-46f651e1cd8f")
    public SmAttribute getTestBeforeAtt() {
        if (this.testBeforeAtt == null) {
        	this.testBeforeAtt = this.getAttributeDef("TestBefore");
        }
        return this.testBeforeAtt;
    }

    @objid ("8a55c736-2373-4f16-af74-8bbd265ac9ea")
    public SmAttribute getLoopConditionAtt() {
        if (this.loopConditionAtt == null) {
        	this.loopConditionAtt = this.getAttributeDef("LoopCondition");
        }
        return this.loopConditionAtt;
    }

    @objid ("2d73660f-9814-4a1d-8baf-75b27fa8459b")
    public SmAttribute getLoopMaximumAtt() {
        if (this.loopMaximumAtt == null) {
        	this.loopMaximumAtt = this.getAttributeDef("LoopMaximum");
        }
        return this.loopMaximumAtt;
    }

    @objid ("20a4cab4-7885-49a5-bc69-58cca6c0ad1e")
    private static class BpmnStandardLoopCharacteristicsObjectFactory implements ISmObjectFactory {
        @objid ("836b63e3-95cf-4a62-aaf6-ec92653d375b")
        private BpmnStandardLoopCharacteristicsSmClass smClass;

        @objid ("0cb42a54-b573-4f36-a641-bc7c13693bbf")
        public BpmnStandardLoopCharacteristicsObjectFactory(BpmnStandardLoopCharacteristicsSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("56df0ecc-dfb3-4840-84be-40c304a5ad42")
        @Override
        public ISmObjectData createData() {
            return new BpmnStandardLoopCharacteristicsData(this.smClass);
        }

        @objid ("696b3959-92b2-4a6e-beeb-eae0dc97bde1")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnStandardLoopCharacteristicsImpl();
        }

    }

    @objid ("dff0729e-80ac-4e9f-bf7a-ee8df85166d3")
    public static class TestBeforeSmAttribute extends SmAttribute {
        @objid ("84a1a5f6-d3c7-4a3b-85af-c3be21506cf1")
        public Object getValue(ISmObjectData data) {
            return ((BpmnStandardLoopCharacteristicsData) data).mTestBefore;
        }

        @objid ("a64d4262-6540-40e5-b6a2-10614a0fac1f")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnStandardLoopCharacteristicsData) data).mTestBefore = value;
        }

    }

    @objid ("e647ab43-f6c1-4ebf-9fb5-ccbb0910e2b8")
    public static class LoopConditionSmAttribute extends SmAttribute {
        @objid ("48c0e520-918b-45e5-a331-8baadd8c4e98")
        public Object getValue(ISmObjectData data) {
            return ((BpmnStandardLoopCharacteristicsData) data).mLoopCondition;
        }

        @objid ("bceb3eae-1ad3-4104-8f75-86e3099b185e")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnStandardLoopCharacteristicsData) data).mLoopCondition = value;
        }

    }

    @objid ("8b25c9cd-bc4c-4af2-be39-e7a3fbcf1676")
    public static class LoopMaximumSmAttribute extends SmAttribute {
        @objid ("b0fe043a-b3f8-4627-8287-1548ffafd0bb")
        public Object getValue(ISmObjectData data) {
            return ((BpmnStandardLoopCharacteristicsData) data).mLoopMaximum;
        }

        @objid ("09f46264-663a-4a55-b002-98c99231d5f1")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnStandardLoopCharacteristicsData) data).mLoopMaximum = value;
        }

    }

}
