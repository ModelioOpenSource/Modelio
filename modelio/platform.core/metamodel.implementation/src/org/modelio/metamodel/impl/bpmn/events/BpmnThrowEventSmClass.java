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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnThrowEventData;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataInputSmClass;
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

@objid ("bb9f5409-3f70-4767-a919-f7d44f27a835")
public class BpmnThrowEventSmClass extends BpmnEventSmClass {
    @objid ("ce93acb6-1017-4710-9e67-c1d383814b90")
    private SmDependency dataInputAssociationDep;

    @objid ("9dc75cd6-8595-49ad-95d7-40bd023f8106")
    private SmDependency dataInputDep;

    @objid ("33e72da7-515c-472a-9537-4afb9f329e32")
    public BpmnThrowEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("934a81ad-5402-4856-a084-a2070910aa5f")
    @Override
    public String getName() {
        return "BpmnThrowEvent";
    }

    @objid ("22308cb4-39f9-4635-aaf5-52f11ac5657a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("85454520-2e6a-4ade-8495-5cc74e9d1ad8")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnThrowEvent.class;
    }

    @objid ("f543b718-852e-4cfb-80c5-1914bd09091d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("df2e4869-c479-467b-9aaa-1936a34462ff")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("5f1997ea-4bcc-4cbb-bf88-ca14ec085a08")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEvent.MQNAME);
        this.registerFactory(new BpmnThrowEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.dataInputAssociationDep = new DataInputAssociationSmDependency();
        this.dataInputAssociationDep.init("DataInputAssociation", this, metamodel.getMClass(BpmnDataAssociation.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.dataInputAssociationDep);
        
        this.dataInputDep = new DataInputSmDependency();
        this.dataInputDep.init("DataInput", this, metamodel.getMClass(BpmnDataInput.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.dataInputDep);
    }

    @objid ("fb982e8d-fdd7-4d11-b52d-ffa18f335773")
    public SmDependency getDataInputAssociationDep() {
        if (this.dataInputAssociationDep == null) {
        	this.dataInputAssociationDep = this.getDependencyDef("DataInputAssociation");
        }
        return this.dataInputAssociationDep;
    }

    @objid ("15629401-a20a-4a15-9f11-ac3ab7ecea29")
    public SmDependency getDataInputDep() {
        if (this.dataInputDep == null) {
        	this.dataInputDep = this.getDependencyDef("DataInput");
        }
        return this.dataInputDep;
    }

    @objid ("f5e5ce5a-63e6-482e-92ab-49d81c680f5a")
    private static class BpmnThrowEventObjectFactory implements ISmObjectFactory {
        @objid ("5af62e53-4805-484a-89f4-09a86389b252")
        private BpmnThrowEventSmClass smClass;

        @objid ("d1387782-1f94-486e-8778-70ea5cb4ed83")
        public BpmnThrowEventObjectFactory(BpmnThrowEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9e4d44ba-7333-4a93-b9af-18370d548ccb")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("c8f1a2b6-1d0d-40df-b4b0-b348a214fcf6")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("319cee27-f52a-4f6f-afa1-e03f54f65639")
    public static class DataInputAssociationSmDependency extends SmMultipleDependency {
        @objid ("fc4959f8-938c-40bc-9b38-5caed1111b24")
        private SmDependency symetricDep;

        @objid ("27cfa8d6-e16a-47f8-843c-6cf332428e23")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnThrowEventData)data).mDataInputAssociation != null)? ((BpmnThrowEventData)data).mDataInputAssociation:SmMultipleDependency.EMPTY;
        }

        @objid ("52075025-240c-4c6c-a53f-bf490f33909e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnThrowEventData) data).mDataInputAssociation = values;
        }

        @objid ("9f5a91d7-55ef-4ca0-a7f6-373c0ca6545f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataAssociationSmClass)this.getTarget()).getStartingEventDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8a15fd92-ebd5-4d71-b909-142e614213c3")
    public static class DataInputSmDependency extends SmSingleDependency {
        @objid ("e39031b4-08be-49bf-9d8d-4546c31b9d5c")
        private SmDependency symetricDep;

        @objid ("9a7ead2e-e0b1-4fdf-a168-2b7c1716d3e9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnThrowEventData) data).mDataInput;
        }

        @objid ("48f33c02-1aa1-4d9d-91b3-187206f5f05a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnThrowEventData) data).mDataInput = value;
        }

        @objid ("8a23da40-c362-4636-9110-030d1ce5ce2a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataInputSmClass)this.getTarget()).getOwnerThrowEventDep();
            }
            return this.symetricDep;
        }

    }

}
