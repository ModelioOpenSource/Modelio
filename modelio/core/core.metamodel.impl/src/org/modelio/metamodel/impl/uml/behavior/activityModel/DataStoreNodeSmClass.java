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
import org.modelio.metamodel.impl.uml.behavior.activityModel.CentralBufferNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("be9410e9-9e10-4db8-b745-4ffd5e050416")
public class DataStoreNodeSmClass extends CentralBufferNodeSmClass {
    @objid ("5b0fd61a-eb8a-4d7a-ae4e-1ca69ddf0d27")
    public DataStoreNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ec0fecb9-9bef-4ded-acb3-2b4a705c6f45")
    @Override
    public String getName() {
        return "DataStoreNode";
    }

    @objid ("643e7c02-fb7b-4225-86b3-398506babd8d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f0f5dcd9-e6f3-4c8d-97e3-4018ce82c294")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DataStoreNode.class;
    }

    @objid ("0adff813-5d72-4893-9979-6166e8f0700e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("080228ce-bf68-4723-b8cd-396375a605d4")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f9b1ebb8-fce1-4f59-940a-afc3c66180bd")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(CentralBufferNode.MQNAME);
        this.registerFactory(new DataStoreNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("5cb68af6-7da1-445f-bb41-2a46f32ee128")
    private static class DataStoreNodeObjectFactory implements ISmObjectFactory {
        @objid ("9e082281-ee86-4cfd-ad50-ff719391fcf1")
        private DataStoreNodeSmClass smClass;

        @objid ("977e3397-a114-480a-a738-d34a14262850")
        public DataStoreNodeObjectFactory(DataStoreNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ce2b8b4c-5121-4595-a748-a102ba3972c5")
        @Override
        public ISmObjectData createData() {
            return new DataStoreNodeData(this.smClass);
        }

        @objid ("cb7d29eb-fa06-452d-83f6-2bd530fa981a")
        @Override
        public SmObjectImpl createImpl() {
            return new DataStoreNodeImpl();
        }

    }

}
