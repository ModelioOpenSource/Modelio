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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.activities.TransactionMethod;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("f4b44df4-bd16-4021-a24a-6da3ceb2e68d")
public class BpmnTransactionSmClass extends BpmnSubProcessSmClass {
    @objid ("ad264da4-166a-425a-8c04-a0d5248ef68b")
    private SmAttribute methodAtt;

    @objid ("2c927b05-d378-47dc-9fd6-b999e3d06162")
    public BpmnTransactionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("73e1bdd9-e117-4da1-9e59-53902094c050")
    @Override
    public String getName() {
        return "BpmnTransaction";
    }

    @objid ("ac0bb305-5f92-403b-ae94-29af61ab3aef")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d062afb2-c366-45d0-b622-37a13ad7c7f6")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnTransaction.class;
    }

    @objid ("6d6caff7-bebf-4a2a-a559-6866ad9d39e0")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4f2c880e-6e4a-4dff-8c15-e86ae3a5fdda")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a07976a5-a23c-4853-8c2a-8a50677f535d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSubProcess.MQNAME);
        this.registerFactory(new BpmnTransactionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.methodAtt = new MethodSmAttribute();
        this.methodAtt.init("Method", this, TransactionMethod.class );
        registerAttribute(this.methodAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("5852e449-16e4-47fd-972f-48c5c762ac30")
    public SmAttribute getMethodAtt() {
        if (this.methodAtt == null) {
        	this.methodAtt = this.getAttributeDef("Method");
        }
        return this.methodAtt;
    }

    @objid ("f5f7a9ed-f8f6-408a-a373-6a4913fd572c")
    private static class BpmnTransactionObjectFactory implements ISmObjectFactory {
        @objid ("fea11467-7733-4074-b274-4d518e3831df")
        private BpmnTransactionSmClass smClass;

        @objid ("7cb39cc3-a5ca-40b0-9b23-0d3c60f2bb59")
        public BpmnTransactionObjectFactory(BpmnTransactionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ddd5746b-b030-4188-8c8f-e5b6386929f1")
        @Override
        public ISmObjectData createData() {
            return new BpmnTransactionData(this.smClass);
        }

        @objid ("5492e34c-f324-413a-a4bd-07ba1302afca")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnTransactionImpl();
        }

    }

    @objid ("b7dafd94-c8ee-46dc-8525-a978af0fa68c")
    public static class MethodSmAttribute extends SmAttribute {
        @objid ("69802368-90d4-44ec-bc98-55b5c595130d")
        public Object getValue(ISmObjectData data) {
            return ((BpmnTransactionData) data).mMethod;
        }

        @objid ("101ad2ed-4130-47b7-9259-946dcb2bf03f")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnTransactionData) data).mMethod = value;
        }

    }

}
