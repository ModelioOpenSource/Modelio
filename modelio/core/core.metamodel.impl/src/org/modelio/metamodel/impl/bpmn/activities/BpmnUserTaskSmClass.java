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

package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("cafae0b5-d001-4ebb-8240-c405612f41d7")
public class BpmnUserTaskSmClass extends BpmnTaskSmClass {
    @objid ("c2d33377-9a6d-4224-a1bc-aae8f89490e8")
    private SmAttribute implementationAtt;

    @objid ("7cea262b-8149-4176-9c2d-4f1a5e22b769")
    public  BpmnUserTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("70cb3502-655b-44cd-ab79-b242fe5590ca")
    @Override
    public String getName() {
        return "BpmnUserTask";
        
    }

    @objid ("11b7ce25-5782-4298-b9e6-0c013ad20d39")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("64013daf-0e32-443d-87a6-d91cb8f15a0a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnUserTask.class;
        
    }

    @objid ("440714f0-e80e-46a4-8a29-6bb0da9a7e18")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("407a1d4b-3560-48b9-bfc7-665639d174bc")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("c10ecfb5-dd15-48a9-90b5-40b438306105")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnUserTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.implementationAtt = new ImplementationSmAttribute();
        this.implementationAtt.init("Implementation", this, String.class );
        registerAttribute(this.implementationAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("ac72bda8-592b-47db-a35a-427202a8c176")
    public SmAttribute getImplementationAtt() {
        if (this.implementationAtt == null) {
        	this.implementationAtt = this.getAttributeDef("Implementation");
        }
        return this.implementationAtt;
    }

    @objid ("0483cc8e-a109-4931-801c-83c5250b9ecb")
    private static class BpmnUserTaskObjectFactory implements ISmObjectFactory {
        @objid ("9a627056-c235-425b-ae15-04af0d4d8351")
        private BpmnUserTaskSmClass smClass;

        @objid ("5a64d4b2-0f32-4e86-9608-8fbf4095324f")
        public  BpmnUserTaskObjectFactory(BpmnUserTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("abf539c3-787f-4562-b94c-10603a60845a")
        @Override
        public ISmObjectData createData() {
            return new BpmnUserTaskData(this.smClass);
        }

        @objid ("2df5a80a-100d-42e3-a026-64626f3ae0e7")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnUserTaskImpl();
        }

    }

    @objid ("e94826de-7be1-4e2b-9929-7555651c3778")
    public static class ImplementationSmAttribute extends SmAttribute {
        @objid ("23cf2f4f-3bdb-4201-97f9-09747861f6f4")
        public Object getValue(ISmObjectData data) {
            return ((BpmnUserTaskData) data).mImplementation;
        }

        @objid ("5b52fb87-dccc-4d8f-8f31-f3d5d6356528")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnUserTaskData) data).mImplementation = value;
        }

    }

}
