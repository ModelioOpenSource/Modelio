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
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
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

@objid ("b033b513-64ec-4756-a171-630a637d92e6")
public class BpmnEscalationEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("ea741fd9-a7e2-46e1-a1f1-52499780eef7")
    private SmAttribute escalationCodeAtt;

    @objid ("5378a601-14d4-4c11-9147-f33ce391e532")
    public  BpmnEscalationEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1c38251b-038f-4c5f-a334-e0e6a9caa7e5")
    @Override
    public String getName() {
        return "BpmnEscalationEventDefinition";
        
    }

    @objid ("35f6586b-357c-43a3-a8fa-494d5fcac522")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d62c3e77-c2ee-4ceb-baa5-b24188bad501")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnEscalationEventDefinition.class;
        
    }

    @objid ("646c592a-89e8-4c51-b65e-82443cb7ae2f")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("3e94ed30-7ade-44ac-8d63-a4926840542c")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("96b1dcac-111f-4bdd-97e2-6a6945e08c14")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnEscalationEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.escalationCodeAtt = new EscalationCodeSmAttribute();
        this.escalationCodeAtt.init("EscalationCode", this, String.class );
        registerAttribute(this.escalationCodeAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("5713cbf3-598e-4451-b6fe-f8dc53e0a5c1")
    public SmAttribute getEscalationCodeAtt() {
        if (this.escalationCodeAtt == null) {
        	this.escalationCodeAtt = this.getAttributeDef("EscalationCode");
        }
        return this.escalationCodeAtt;
    }

    @objid ("8e715810-1930-4ea8-9f7c-1e4c31f247b4")
    private static class BpmnEscalationEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("16952069-51d6-4e16-b300-d66887fbacbe")
        private BpmnEscalationEventDefinitionSmClass smClass;

        @objid ("f0bc3753-ccab-4539-9142-23e4430f75bf")
        public  BpmnEscalationEventDefinitionObjectFactory(BpmnEscalationEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4d316b79-04bc-403c-b1bd-c5f683644f47")
        @Override
        public ISmObjectData createData() {
            return new BpmnEscalationEventDefinitionData(this.smClass);
        }

        @objid ("3d4e0d85-e6ea-408e-92a3-a1db15ab3a64")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnEscalationEventDefinitionImpl();
        }

    }

    @objid ("df6c8e9c-c38c-4144-be62-54b9aeb500af")
    public static class EscalationCodeSmAttribute extends SmAttribute {
        @objid ("772671eb-8a40-4666-94cb-065c42f3b318")
        public Object getValue(ISmObjectData data) {
            return ((BpmnEscalationEventDefinitionData) data).mEscalationCode;
        }

        @objid ("ee3ff62e-43a5-4265-a21a-7b78fcbf7f84")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnEscalationEventDefinitionData) data).mEscalationCode = value;
        }

    }

}
