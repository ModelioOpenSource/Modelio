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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c1b2286d-e337-4b9b-b057-6319bed82980")
public class CentralBufferNodeSmClass extends ObjectNodeSmClass {
    @objid ("e250f71c-6ab0-40cc-b271-c61952eebe34")
    public CentralBufferNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("431c391b-49ff-494d-9beb-a729001823cb")
    @Override
    public String getName() {
        return "CentralBufferNode";
    }

    @objid ("a6a42ecd-c8fc-4cee-b329-7ed22280153e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6c65c13a-9432-4ade-b900-174f2a101ef4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CentralBufferNode.class;
    }

    @objid ("838e4bbc-d73d-44d0-8cc0-d5ddc198fc91")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("07969b40-f081-4d94-9fe1-dadc2c3af7d0")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("53fe786a-8551-45d5-9cc3-fbf61bd39d36")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ObjectNode.MQNAME);
        this.registerFactory(new CentralBufferNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("f98b7244-88fb-405b-ab02-e77ae3931c55")
    private static class CentralBufferNodeObjectFactory implements ISmObjectFactory {
        @objid ("9454039d-4b81-446e-84d8-2c4545768957")
        private CentralBufferNodeSmClass smClass;

        @objid ("a287bb55-d545-4a83-989e-fc9b68f3f77f")
        public CentralBufferNodeObjectFactory(CentralBufferNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("7f39c0ec-6290-4a2c-8559-77aca208db64")
        @Override
        public ISmObjectData createData() {
            return new CentralBufferNodeData(this.smClass);
        }

        @objid ("6124b54c-e7dd-476c-aa7d-dbb8af75500d")
        @Override
        public SmObjectImpl createImpl() {
            return new CentralBufferNodeImpl();
        }

    }

}
