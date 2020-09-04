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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("2f63a229-7bed-4137-91ae-8558d497558a")
public class BpmnErrorEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("904e0bc9-eed7-4b67-b446-0162be7d46b8")
    private SmAttribute errorCodeAtt;

    @objid ("960e98d2-2e3f-41a8-b49f-f890e39a3762")
    public BpmnErrorEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fa69972c-1fdc-4c1f-b2b7-4c18d48ac628")
    @Override
    public String getName() {
        return "BpmnErrorEventDefinition";
    }

    @objid ("e46a8752-a057-464f-96f1-ff4676e91f1c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("378e7958-a853-450a-b1c3-f5c85e9a579d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnErrorEventDefinition.class;
    }

    @objid ("2f75ea81-6565-44fd-9528-d362e897769b")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("95b37329-9ed1-4d37-8443-34e97eafc97a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("de14afcf-e0fb-41a4-a6e0-b6fb04ece513")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnErrorEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.errorCodeAtt = new ErrorCodeSmAttribute();
        this.errorCodeAtt.init("ErrorCode", this, String.class );
        registerAttribute(this.errorCodeAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("405dba4b-555f-4934-a6bf-6b7a4f71283f")
    public SmAttribute getErrorCodeAtt() {
        if (this.errorCodeAtt == null) {
        	this.errorCodeAtt = this.getAttributeDef("ErrorCode");
        }
        return this.errorCodeAtt;
    }

    @objid ("30273785-3c93-46d1-b582-70446a9ef8f2")
    private static class BpmnErrorEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("9bd0321e-9c5c-4ca8-b433-9f80408cde76")
        private BpmnErrorEventDefinitionSmClass smClass;

        @objid ("252e141a-bf77-477e-b1dd-6c82aa9fe0f5")
        public BpmnErrorEventDefinitionObjectFactory(BpmnErrorEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("354a9e76-e058-472f-80b6-7e6b767c5685")
        @Override
        public ISmObjectData createData() {
            return new BpmnErrorEventDefinitionData(this.smClass);
        }

        @objid ("a08ce357-ccfe-468e-a1cf-e33b433b521d")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnErrorEventDefinitionImpl();
        }

    }

    @objid ("7407a3bd-92c2-4c46-acc9-168f4a9175ff")
    public static class ErrorCodeSmAttribute extends SmAttribute {
        @objid ("b46a23c8-6486-4f9b-909b-641c2e1b4089")
        public Object getValue(ISmObjectData data) {
            return ((BpmnErrorEventDefinitionData) data).mErrorCode;
        }

        @objid ("b361c4a0-0b6b-4ff9-b6d8-5459aac9f27c")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnErrorEventDefinitionData) data).mErrorCode = value;
        }

    }

}
