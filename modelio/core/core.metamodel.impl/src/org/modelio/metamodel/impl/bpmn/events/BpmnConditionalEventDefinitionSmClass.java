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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("2b15ed8a-3d33-499c-91c7-4c52a79fdacd")
public class BpmnConditionalEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("7f6ae11a-5c7c-44c9-844f-ba4e885c4873")
    private SmAttribute conditionAtt;

    @objid ("36af8266-7ba7-4472-88b0-d5439b7171d8")
    public  BpmnConditionalEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9d74f9b8-a2fe-4409-92e8-4df5588306ca")
    @Override
    public String getName() {
        return "BpmnConditionalEventDefinition";
        
    }

    @objid ("db7431eb-843f-42d3-a37c-df50726779f2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b278eddd-abd9-41ec-96d8-cc12d5b4ac4d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnConditionalEventDefinition.class;
        
    }

    @objid ("5016dbb6-69ca-406c-96a4-5c043639dd31")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("9140699d-cfb6-4472-9d00-5fedf1725859")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("a83890cd-867a-499f-928c-e84ab391ab15")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnConditionalEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.conditionAtt = new ConditionSmAttribute();
        this.conditionAtt.init("Condition", this, String.class );
        registerAttribute(this.conditionAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("a5937c13-cfd2-4c2a-8d05-c272ff1854f2")
    public SmAttribute getConditionAtt() {
        if (this.conditionAtt == null) {
        	this.conditionAtt = this.getAttributeDef("Condition");
        }
        return this.conditionAtt;
    }

    @objid ("eaeacb93-cfac-416f-903b-80e8fc16399e")
    private static class BpmnConditionalEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("b7a152a1-c35e-48b2-bd6a-760b5245b954")
        private BpmnConditionalEventDefinitionSmClass smClass;

        @objid ("aaba4b21-8b92-4ae2-b7dd-bac18f9f3a7e")
        public  BpmnConditionalEventDefinitionObjectFactory(BpmnConditionalEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8b358d74-fc72-4a46-af5d-e10b34b1e903")
        @Override
        public ISmObjectData createData() {
            return new BpmnConditionalEventDefinitionData(this.smClass);
        }

        @objid ("84100d35-9aba-40a6-8277-d653154acd11")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnConditionalEventDefinitionImpl();
        }

    }

    @objid ("2e301ae9-c151-4e66-ba9d-759ca7217a22")
    public static class ConditionSmAttribute extends SmAttribute {
        @objid ("e735e8c1-ec7d-4752-8c7e-226a9e15ecb1")
        public Object getValue(ISmObjectData data) {
            return ((BpmnConditionalEventDefinitionData) data).mCondition;
        }

        @objid ("a8da79c3-d6ec-4f5e-9f7b-942067903d6f")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnConditionalEventDefinitionData) data).mCondition = value;
        }

    }

}
