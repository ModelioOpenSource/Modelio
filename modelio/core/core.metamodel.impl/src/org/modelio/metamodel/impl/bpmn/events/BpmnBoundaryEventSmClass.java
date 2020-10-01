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
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnBoundaryEventData;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("433e47df-dc16-440a-be26-aecc715e5788")
public class BpmnBoundaryEventSmClass extends BpmnCatchEventSmClass {
    @objid ("543efe5c-d3ca-47b5-b3ed-9188ef3359e9")
    private SmAttribute cancelActivityAtt;

    @objid ("2660a541-ae88-44aa-8a89-ace59b545440")
    private SmDependency attachedToRefDep;

    @objid ("2ab3a1fe-9b03-457f-b3c0-412b90f6d9a8")
    public BpmnBoundaryEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b19fa5dc-6355-4a35-924f-b8b1623bf977")
    @Override
    public String getName() {
        return "BpmnBoundaryEvent";
    }

    @objid ("ab83b9ff-beb3-4406-a1b9-9d7bfb146805")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2e231fc5-f3a7-4f90-8696-011728a23379")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnBoundaryEvent.class;
    }

    @objid ("e0167d8a-638f-43cb-83c4-5ea1157a9b90")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("3507c1e4-e111-4536-9601-2922806394a2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e3b5c63c-5bbf-45b8-aeb0-205db070d776")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnCatchEvent.MQNAME);
        this.registerFactory(new BpmnBoundaryEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.cancelActivityAtt = new CancelActivitySmAttribute();
        this.cancelActivityAtt.init("CancelActivity", this, Boolean.class );
        registerAttribute(this.cancelActivityAtt);
        
        
        // Initialize and register the SmDependency
        this.attachedToRefDep = new AttachedToRefSmDependency();
        this.attachedToRefDep.init("AttachedToRef", this, metamodel.getMClass(BpmnActivity.MQNAME), 1, 1 );
        registerDependency(this.attachedToRefDep);
    }

    @objid ("00ec2a82-e7b1-4361-a1e8-7c81adbd5f3d")
    public SmAttribute getCancelActivityAtt() {
        if (this.cancelActivityAtt == null) {
        	this.cancelActivityAtt = this.getAttributeDef("CancelActivity");
        }
        return this.cancelActivityAtt;
    }

    @objid ("52d93f0e-294c-40fa-aa61-bf838132a945")
    public SmDependency getAttachedToRefDep() {
        if (this.attachedToRefDep == null) {
        	this.attachedToRefDep = this.getDependencyDef("AttachedToRef");
        }
        return this.attachedToRefDep;
    }

    @objid ("11514c9c-a3a1-4ef9-9f45-f4b358449a9e")
    private static class BpmnBoundaryEventObjectFactory implements ISmObjectFactory {
        @objid ("c9d3022f-f376-4982-9242-0962ce5ff7f9")
        private BpmnBoundaryEventSmClass smClass;

        @objid ("2e920b25-fbdb-411a-aa50-702c0db19634")
        public BpmnBoundaryEventObjectFactory(BpmnBoundaryEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("20f3d3f4-8c79-4973-9e24-96de9a03852d")
        @Override
        public ISmObjectData createData() {
            return new BpmnBoundaryEventData(this.smClass);
        }

        @objid ("a3b68c88-c1c7-41bf-86f3-602facf0d997")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnBoundaryEventImpl();
        }

    }

    @objid ("6f83f4a3-ef48-46f3-8660-041f6d6d6722")
    public static class CancelActivitySmAttribute extends SmAttribute {
        @objid ("c39d56b1-b7ec-4847-b622-33dc569d4541")
        public Object getValue(ISmObjectData data) {
            return ((BpmnBoundaryEventData) data).mCancelActivity;
        }

        @objid ("b6b20d73-a58f-45d3-b105-2bd8cf8814cc")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnBoundaryEventData) data).mCancelActivity = value;
        }

    }

    @objid ("b2ef7dca-6bd0-4249-87eb-495b5d5aad08")
    public static class AttachedToRefSmDependency extends SmSingleDependency {
        @objid ("711ac5ba-e046-4a70-a4d3-4a97649d2989")
        private SmDependency symetricDep;

        @objid ("9ba71840-4521-4e20-bbeb-4b9323447cbb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnBoundaryEventData) data).mAttachedToRef;
        }

        @objid ("2b9e94aa-1fa9-4025-92f9-24f1840477b6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnBoundaryEventData) data).mAttachedToRef = value;
        }

        @objid ("af48c3c5-cb99-4ee4-9721-338439a1eca3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getBoundaryEventRefDep();
            }
            return this.symetricDep;
        }

    }

}
