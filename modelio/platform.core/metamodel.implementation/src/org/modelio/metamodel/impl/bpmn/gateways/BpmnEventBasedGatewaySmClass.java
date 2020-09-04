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
package org.modelio.metamodel.impl.bpmn.gateways;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGatewayType;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnGatewaySmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("e3c20f17-aba5-4406-9d83-4e9524117765")
public class BpmnEventBasedGatewaySmClass extends BpmnGatewaySmClass {
    @objid ("08602883-3cbd-4c40-bea1-cb55aa155a10")
    private SmAttribute instanciateAtt;

    @objid ("cb305978-37fb-45b4-929f-8cdf354672de")
    private SmAttribute eventGatewayTypeAtt;

    @objid ("27ae8c3e-8358-4d42-bb0b-e5bc838ff3f1")
    public BpmnEventBasedGatewaySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fb97a63b-728c-41ed-86ae-b39569824bbd")
    @Override
    public String getName() {
        return "BpmnEventBasedGateway";
    }

    @objid ("05d7529e-09d7-4534-ae0d-423db16ee7d1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c1c2aca9-d21d-4a36-8f0d-d493d70ee70b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnEventBasedGateway.class;
    }

    @objid ("d6cd64c7-a47b-4c36-9b6f-65f366e6852b")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("98751b25-4d37-4b56-8dd9-155cf5ba83e9")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("02064120-4c1a-482b-966b-4698cbee9112")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnGateway.MQNAME);
        this.registerFactory(new BpmnEventBasedGatewayObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.instanciateAtt = new InstanciateSmAttribute();
        this.instanciateAtt.init("Instanciate", this, Boolean.class );
        registerAttribute(this.instanciateAtt);
        
        this.eventGatewayTypeAtt = new EventGatewayTypeSmAttribute();
        this.eventGatewayTypeAtt.init("EventGatewayType", this, BpmnEventBasedGatewayType.class );
        registerAttribute(this.eventGatewayTypeAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("ccdb45af-c6fc-459d-9eeb-bc7262d4fcb0")
    public SmAttribute getInstanciateAtt() {
        if (this.instanciateAtt == null) {
        	this.instanciateAtt = this.getAttributeDef("Instanciate");
        }
        return this.instanciateAtt;
    }

    @objid ("46887b86-0abf-4b5f-ba98-0f248f6edb18")
    public SmAttribute getEventGatewayTypeAtt() {
        if (this.eventGatewayTypeAtt == null) {
        	this.eventGatewayTypeAtt = this.getAttributeDef("EventGatewayType");
        }
        return this.eventGatewayTypeAtt;
    }

    @objid ("c85973b3-96cf-4ee3-b78a-30263399787a")
    private static class BpmnEventBasedGatewayObjectFactory implements ISmObjectFactory {
        @objid ("67ce20c4-a8a7-47ea-ae7e-dff0e04f99fd")
        private BpmnEventBasedGatewaySmClass smClass;

        @objid ("fad5538a-c6f8-4d15-88dc-47d260173819")
        public BpmnEventBasedGatewayObjectFactory(BpmnEventBasedGatewaySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("706598da-f703-41ec-a7a3-a9418b2215cd")
        @Override
        public ISmObjectData createData() {
            return new BpmnEventBasedGatewayData(this.smClass);
        }

        @objid ("81e28751-81dc-4ec4-a343-7412343612ab")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnEventBasedGatewayImpl();
        }

    }

    @objid ("8ce6ef65-fe16-4086-a9a5-6c0cd407ca19")
    public static class InstanciateSmAttribute extends SmAttribute {
        @objid ("5397fc08-dda8-4b1e-ade7-6db7bb62e1be")
        public Object getValue(ISmObjectData data) {
            return ((BpmnEventBasedGatewayData) data).mInstanciate;
        }

        @objid ("1a814545-25ad-4d63-bba2-99fd9f5ca83e")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnEventBasedGatewayData) data).mInstanciate = value;
        }

    }

    @objid ("d0334ec8-0ca4-4545-8451-d1d8efec5012")
    public static class EventGatewayTypeSmAttribute extends SmAttribute {
        @objid ("eeb13883-46e3-44b9-b2dd-0a421aef9e44")
        public Object getValue(ISmObjectData data) {
            return ((BpmnEventBasedGatewayData) data).mEventGatewayType;
        }

        @objid ("b18cb236-f2fc-4a68-9514-b11577bb5f8d")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnEventBasedGatewayData) data).mEventGatewayType = value;
        }

    }

}
