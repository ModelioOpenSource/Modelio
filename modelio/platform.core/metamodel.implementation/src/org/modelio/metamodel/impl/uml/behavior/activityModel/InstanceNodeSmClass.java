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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("862cba32-9690-4ed1-9234-5f4f1162a549")
public class InstanceNodeSmClass extends ObjectNodeSmClass {
    @objid ("6fc34028-53c3-41ef-869f-5b66cc139c11")
    public InstanceNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("aef5a478-3824-472a-a981-9f8f7de0ecdf")
    @Override
    public String getName() {
        return "InstanceNode";
    }

    @objid ("d29d5488-ec01-4b7f-a623-d04d24e86311")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7752cf42-ab61-48b0-b3de-428f647d622b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InstanceNode.class;
    }

    @objid ("fa569ec4-29bf-498d-b5d9-6c511add2dfa")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("d3d6fee0-03ee-4d5c-bd58-5a26c528e98a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8d939408-e50a-477a-a8a9-0e74e84a78c5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ObjectNode.MQNAME);
        this.registerFactory(new InstanceNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("75e3ae5c-0c46-4af1-a783-7d9df7c9caad")
    private static class InstanceNodeObjectFactory implements ISmObjectFactory {
        @objid ("0858ea2e-9edf-4f62-b9ae-ef85118a8317")
        private InstanceNodeSmClass smClass;

        @objid ("f2826048-e952-429e-83a4-25b5834f6788")
        public InstanceNodeObjectFactory(InstanceNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("22d1b57c-e6bf-47fa-ba7f-12a7d4cc3f12")
        @Override
        public ISmObjectData createData() {
            return new InstanceNodeData(this.smClass);
        }

        @objid ("fd915f12-e50d-4ecb-b23f-db3941607bb6")
        @Override
        public SmObjectImpl createImpl() {
            return new InstanceNodeImpl();
        }

    }

}
