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
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataAssociationSmClass;
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

@objid ("ceb33b5e-17bc-4df5-a2f8-26a0da3670c6")
public class BpmnCatchEventSmClass extends BpmnEventSmClass {
    @objid ("9684cce7-66f5-451e-9fc0-845f032cdaef")
    private SmAttribute parallelMultipleAtt;

    @objid ("3fe62a1e-5bca-49dd-b38a-f58335a55e1a")
    private SmDependency dataOutputAssociationDep;

    @objid ("d64123e6-b360-480c-b08c-464fca097ba8")
    private SmDependency dataOutputDep;

    @objid ("8ffeb6be-1820-460c-b371-ced6e0b40f70")
    public  BpmnCatchEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("07a8e552-7119-4f6c-9ec9-388fe86dd18f")
    @Override
    public String getName() {
        return "BpmnCatchEvent";
        
    }

    @objid ("9ddc1fbc-50c4-448d-bdda-cf92e491e2d5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f32d3bf7-f8fa-4498-b6ca-91d43899e71a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnCatchEvent.class;
        
    }

    @objid ("3c1b1b12-fb62-4337-9d9e-95f7c7b187e2")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("289fa0d2-254b-4ea9-8aea-b6020ba74378")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("12cf58ea-fc05-4742-a6a5-e22959f3c374")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEvent.MQNAME);
        this.registerFactory(new BpmnCatchEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.parallelMultipleAtt = new ParallelMultipleSmAttribute();
        this.parallelMultipleAtt.init("ParallelMultiple", this, Boolean.class );
        registerAttribute(this.parallelMultipleAtt);
        
        
        // Initialize and register the SmDependency
        this.dataOutputAssociationDep = new DataOutputAssociationSmDependency();
        this.dataOutputAssociationDep.init("DataOutputAssociation", this, metamodel.getMClass(BpmnDataAssociation.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.dataOutputAssociationDep);
        
        this.dataOutputDep = new DataOutputSmDependency();
        this.dataOutputDep.init("DataOutput", this, metamodel.getMClass(BpmnDataOutput.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.dataOutputDep);
        
        
    }

    @objid ("1c7f22a0-8a6e-4ae9-a667-b1749efea647")
    public SmAttribute getParallelMultipleAtt() {
        if (this.parallelMultipleAtt == null) {
        	this.parallelMultipleAtt = this.getAttributeDef("ParallelMultiple");
        }
        return this.parallelMultipleAtt;
    }

    @objid ("3cc9615e-361c-4f59-9e77-60c46ddcfcb5")
    public SmDependency getDataOutputAssociationDep() {
        if (this.dataOutputAssociationDep == null) {
        	this.dataOutputAssociationDep = this.getDependencyDef("DataOutputAssociation");
        }
        return this.dataOutputAssociationDep;
    }

    @objid ("160971b3-7c36-4802-b864-1cedeca43dc2")
    public SmDependency getDataOutputDep() {
        if (this.dataOutputDep == null) {
        	this.dataOutputDep = this.getDependencyDef("DataOutput");
        }
        return this.dataOutputDep;
    }

    @objid ("68606b46-bb57-4698-8791-bd4153817fa8")
    private static class BpmnCatchEventObjectFactory implements ISmObjectFactory {
        @objid ("13cfc8eb-65ee-43f5-aaa6-5fcb5842b4f1")
        private BpmnCatchEventSmClass smClass;

        @objid ("c384b61e-30ef-4280-b374-069d2382e2ae")
        public  BpmnCatchEventObjectFactory(BpmnCatchEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6591da27-2f80-49a0-9abb-f9fc89479dcb")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("09a284ea-c5b8-4ed5-a34b-78b31514f22c")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("72153169-5128-4378-a1e8-3fe3decb6946")
    public static class ParallelMultipleSmAttribute extends SmAttribute {
        @objid ("01a3e27d-c19d-42ac-8942-974c2978050f")
        public Object getValue(ISmObjectData data) {
            return ((BpmnCatchEventData) data).mParallelMultiple;
        }

        @objid ("f2f78610-e868-4f0d-a188-f8bc800b99d7")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnCatchEventData) data).mParallelMultiple = value;
        }

    }

    @objid ("c41c6528-82cb-4c14-b048-218aa92c20f2")
    public static class DataOutputAssociationSmDependency extends SmMultipleDependency {
        @objid ("09bae91f-3bf5-4cac-8e94-79d673f53ae6")
        private SmDependency symetricDep;

        @objid ("95df911e-b601-41dc-bf3a-589f57a99d0b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnCatchEventData)data).mDataOutputAssociation != null)? ((BpmnCatchEventData)data).mDataOutputAssociation:SmMultipleDependency.EMPTY;
        }

        @objid ("ee026f88-a6fd-42fa-ba88-56311c34b67b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnCatchEventData) data).mDataOutputAssociation = values;
            
        }

        @objid ("364e2033-14e9-40a5-95db-f4e04cd48832")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataAssociationSmClass)this.getTarget()).getEndingEventDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("24e94ebc-652a-48fa-95a7-539170e99a62")
    public static class DataOutputSmDependency extends SmSingleDependency {
        @objid ("4d1c18f5-821b-4087-a3a7-8ec898860c64")
        private SmDependency symetricDep;

        @objid ("b0536e95-9caa-498f-8d06-305c34fcdedd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnCatchEventData) data).mDataOutput;
        }

        @objid ("6d243e0b-49ea-499c-a542-46e744f83a9a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnCatchEventData) data).mDataOutput = value;
        }

        @objid ("5ea169c0-9631-4072-bacd-bf3188fb54d3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataOutputSmClass)this.getTarget()).getCatchedDep();
            }
            return this.symetricDep;
            
        }

    }

}
