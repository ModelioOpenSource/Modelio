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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("987f97c3-033b-4989-bec3-cb6d8b249f62")
public class BpmnBusinessRuleTaskSmClass extends BpmnTaskSmClass {
    @objid ("2c7dd471-314b-46f8-85bb-326804a1f96e")
    public BpmnBusinessRuleTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ae1cac3e-c07c-4691-8e23-de69a67ef630")
    @Override
    public String getName() {
        return "BpmnBusinessRuleTask";
    }

    @objid ("8a4b200b-ade9-4322-ac5e-ac876b694ba0")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("5fd0a351-c55c-4f48-8eae-b3129805e264")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnBusinessRuleTask.class;
    }

    @objid ("49d9ce8a-d8cd-49b3-a3c8-17477ab76426")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6bc0e213-23bc-4075-906f-68e2f8cc3c8f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("aa97bad8-1a29-42de-aa1f-ae36c7218870")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnBusinessRuleTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("df3af54f-0897-4d05-bb9f-8deef870a92c")
    private static class BpmnBusinessRuleTaskObjectFactory implements ISmObjectFactory {
        @objid ("d97589f5-896b-44bf-95ad-82ec03986846")
        private BpmnBusinessRuleTaskSmClass smClass;

        @objid ("fb102bd0-36c2-4bb1-b731-196da4610229")
        public BpmnBusinessRuleTaskObjectFactory(BpmnBusinessRuleTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b977c502-6061-493f-b14d-a893c2cf90dc")
        @Override
        public ISmObjectData createData() {
            return new BpmnBusinessRuleTaskData(this.smClass);
        }

        @objid ("37008e1b-f702-4645-bdd1-2150c8eaf2ab")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnBusinessRuleTaskImpl();
        }

    }

}
