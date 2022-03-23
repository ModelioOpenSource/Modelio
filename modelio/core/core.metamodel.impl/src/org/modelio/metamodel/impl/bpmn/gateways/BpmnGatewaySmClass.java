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

package org.modelio.metamodel.impl.bpmn.gateways;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b7157dbd-377c-40c8-8473-97ad79ef088f")
public class BpmnGatewaySmClass extends BpmnFlowNodeSmClass {
    @objid ("4ee987ba-71cf-4f42-9c28-f59f928e2aa1")
    private SmAttribute gatewayDirectionAtt;

    @objid ("7a04f727-3200-4926-8c7c-1c57c213d138")
    public  BpmnGatewaySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1a7cf412-f9ae-4663-86be-55ec296c2c32")
    @Override
    public String getName() {
        return "BpmnGateway";
        
    }

    @objid ("8a5251cf-68b2-433d-89a0-0d8ea08c3346")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("143f9a7e-85cb-421d-877e-98f9c951ff17")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnGateway.class;
        
    }

    @objid ("b2aa0495-60b3-479d-9687-30352de55d0c")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("f336c7e7-d978-40d5-9cb9-e8864d1daf84")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("c68ecf77-636e-4691-a1c0-c0fbec8d175e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnFlowNode.MQNAME);
        this.registerFactory(new BpmnGatewayObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.gatewayDirectionAtt = new GatewayDirectionSmAttribute();
        this.gatewayDirectionAtt.init("GatewayDirection", this, BpmnGatewayDirection.class );
        registerAttribute(this.gatewayDirectionAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("5c45f2ec-cab3-4d87-bcad-c889bfabcd12")
    public SmAttribute getGatewayDirectionAtt() {
        if (this.gatewayDirectionAtt == null) {
        	this.gatewayDirectionAtt = this.getAttributeDef("GatewayDirection");
        }
        return this.gatewayDirectionAtt;
    }

    @objid ("eedd07aa-057b-4272-9bb8-e011f7d04534")
    private static class BpmnGatewayObjectFactory implements ISmObjectFactory {
        @objid ("63bd5c93-a558-4450-b971-3b392eefbc26")
        private BpmnGatewaySmClass smClass;

        @objid ("8726a9dc-8a62-412c-b1f1-8d3dcf2f7b76")
        public  BpmnGatewayObjectFactory(BpmnGatewaySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b381a207-e9a0-4aed-93e7-6a156803e11d")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("87134019-8757-4688-bd4d-2e4ac6bff4ab")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("bd15ebda-c1ed-4eb1-8bc6-a6c4a9f20043")
    public static class GatewayDirectionSmAttribute extends SmAttribute {
        @objid ("9e3e451d-3404-4237-86ed-c4e6ca1fd579")
        public Object getValue(ISmObjectData data) {
            return ((BpmnGatewayData) data).mGatewayDirection;
        }

        @objid ("524ec6e3-a255-41dc-a740-a1d1d9e22f7c")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnGatewayData) data).mGatewayDirection = value;
        }

    }

}
