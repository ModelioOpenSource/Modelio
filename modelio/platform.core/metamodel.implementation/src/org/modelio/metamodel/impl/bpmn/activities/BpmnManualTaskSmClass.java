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
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
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

@objid ("46a6f085-4ff9-4de6-a623-b74277c418be")
public class BpmnManualTaskSmClass extends BpmnTaskSmClass {
    @objid ("22e79a59-99f8-4eb3-9a7f-aa90d7c0eb54")
    public BpmnManualTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("780b96f6-9a0e-47f8-a01e-0342f3506f47")
    @Override
    public String getName() {
        return "BpmnManualTask";
    }

    @objid ("8490b3e8-a027-441c-9713-e402a7ca09bd")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("05515ae0-7134-4ced-8517-c3b682b2d49c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnManualTask.class;
    }

    @objid ("594ac8aa-ff8a-46c1-b19c-d02ae510befe")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5180d660-5e67-4874-a0c0-1d220f037ead")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("cc44e5f8-9e80-4830-bfd9-a43aa5784cde")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnManualTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("8a4179bb-790d-4af2-91cf-a0883c96cb85")
    private static class BpmnManualTaskObjectFactory implements ISmObjectFactory {
        @objid ("3b7981a4-3642-4001-9de8-5936b1fda791")
        private BpmnManualTaskSmClass smClass;

        @objid ("eb2b08f8-5b72-4708-869c-b3bacdc01ddd")
        public BpmnManualTaskObjectFactory(BpmnManualTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e497dafc-d669-4331-a571-0be2d4289106")
        @Override
        public ISmObjectData createData() {
            return new BpmnManualTaskData(this.smClass);
        }

        @objid ("8489a265-f226-49be-a96d-7a607590e955")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnManualTaskImpl();
        }

    }

}
