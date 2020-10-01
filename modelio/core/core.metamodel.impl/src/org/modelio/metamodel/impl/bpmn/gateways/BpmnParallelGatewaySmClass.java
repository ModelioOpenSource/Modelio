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
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnGatewaySmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("231f222a-7f41-42fd-bde0-c4ecba43f073")
public class BpmnParallelGatewaySmClass extends BpmnGatewaySmClass {
    @objid ("5c7e39e6-8a15-4936-a2cc-6962c6fb65a3")
    public BpmnParallelGatewaySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3445da46-f5ed-489b-a42a-a55b318c46b6")
    @Override
    public String getName() {
        return "BpmnParallelGateway";
    }

    @objid ("43e9b2b0-10ec-4456-ad6f-0733c1c5b98f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("73c3c461-ba14-4265-aefa-aa3b539e3f8b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnParallelGateway.class;
    }

    @objid ("56633657-4a10-4da6-8629-b3e28e54ae7e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("85f2beac-1c26-4dbc-a515-abba4858d82e")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("aa9bfe44-ae88-4ba8-98d2-315d845020fa")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnGateway.MQNAME);
        this.registerFactory(new BpmnParallelGatewayObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("f8a4ef36-b9a1-4885-9e29-58a6d04439e8")
    private static class BpmnParallelGatewayObjectFactory implements ISmObjectFactory {
        @objid ("33ba1f94-6712-4d8d-b4e2-11916a95b53a")
        private BpmnParallelGatewaySmClass smClass;

        @objid ("0a8ded2c-764d-44e1-bb88-cd59ce556974")
        public BpmnParallelGatewayObjectFactory(BpmnParallelGatewaySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("129aea89-7f4f-4da0-95ba-0f6be0cd5153")
        @Override
        public ISmObjectData createData() {
            return new BpmnParallelGatewayData(this.smClass);
        }

        @objid ("175f1009-597e-4985-8f70-ec9586449308")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnParallelGatewayImpl();
        }

    }

}
