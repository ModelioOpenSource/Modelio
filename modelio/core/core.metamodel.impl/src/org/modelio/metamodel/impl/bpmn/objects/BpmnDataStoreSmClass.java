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

package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("82e03360-b636-46dc-9bf6-0bd95319cbe0")
public class BpmnDataStoreSmClass extends BpmnItemAwareElementSmClass {
    @objid ("7820fb15-4359-446b-a312-050c9624152d")
    private SmAttribute capacityAtt;

    @objid ("9f684703-8ea9-43fc-b186-049859d850a7")
    private SmAttribute isUnlimitedAtt;

    @objid ("79efa508-f67c-4813-881a-11f31bb3e119")
    public  BpmnDataStoreSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6c844f39-4b99-4812-8c17-21bbde5ed3ae")
    @Override
    public String getName() {
        return "BpmnDataStore";
        
    }

    @objid ("5bbb7271-79b7-4e86-ae06-f22de6fb8077")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("59567d65-9851-45c2-870b-295abea25c17")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnDataStore.class;
        
    }

    @objid ("5b2902bc-650f-4d70-b6c9-b7f580cf042e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("6c3aa58a-3381-431d-9b7c-d6ce6c3fe5f6")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("61979f5b-7f63-4ad5-ab84-73162941d4d3")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnItemAwareElement.MQNAME);
        this.registerFactory(new BpmnDataStoreObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.capacityAtt = new CapacitySmAttribute();
        this.capacityAtt.init("Capacity", this, Integer.class );
        registerAttribute(this.capacityAtt);
        
        this.isUnlimitedAtt = new IsUnlimitedSmAttribute();
        this.isUnlimitedAtt.init("IsUnlimited", this, Boolean.class );
        registerAttribute(this.isUnlimitedAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("bc67cd85-27ae-4654-936f-0da7b3e419b9")
    public SmAttribute getCapacityAtt() {
        if (this.capacityAtt == null) {
        	this.capacityAtt = this.getAttributeDef("Capacity");
        }
        return this.capacityAtt;
    }

    @objid ("f41ea051-e8a4-4d8d-9d93-0a00bbf01dd3")
    public SmAttribute getIsUnlimitedAtt() {
        if (this.isUnlimitedAtt == null) {
        	this.isUnlimitedAtt = this.getAttributeDef("IsUnlimited");
        }
        return this.isUnlimitedAtt;
    }

    @objid ("d4b8a89e-d3e0-4f08-ac58-573fc94ee5cd")
    private static class BpmnDataStoreObjectFactory implements ISmObjectFactory {
        @objid ("cab4f579-bf90-4e18-a032-f7600562636c")
        private BpmnDataStoreSmClass smClass;

        @objid ("a2386cd2-f6c7-4b3c-903b-24d91cede7a5")
        public  BpmnDataStoreObjectFactory(BpmnDataStoreSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("19bb360f-2c46-4757-8283-6c16a2193cf2")
        @Override
        public ISmObjectData createData() {
            return new BpmnDataStoreData(this.smClass);
        }

        @objid ("ce3b252e-02f2-4410-a12d-cb35582d6f9d")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnDataStoreImpl();
        }

    }

    @objid ("83fbc10d-f307-4e42-8ae4-b19bcb18a860")
    public static class CapacitySmAttribute extends SmAttribute {
        @objid ("24cec915-925d-4ac1-8d80-0dff4b123f25")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataStoreData) data).mCapacity;
        }

        @objid ("41cec441-3abb-4195-8c3d-7b4b22e4f065")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataStoreData) data).mCapacity = value;
        }

    }

    @objid ("b7b85ecf-cbf3-40f7-b641-b51570ee0a94")
    public static class IsUnlimitedSmAttribute extends SmAttribute {
        @objid ("eedc49d6-c9b6-489f-9814-a8c4d2e90c21")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataStoreData) data).mIsUnlimited;
        }

        @objid ("c2314111-41bc-4797-9405-0718c6897872")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataStoreData) data).mIsUnlimited = value;
        }

    }

}
