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
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("094a69f0-ca69-4bf2-a30b-3e7a335c2488")
public class InitialNodeSmClass extends ControlNodeSmClass {
    @objid ("da18ea44-3ea6-4ecb-889b-15980fd0361d")
    public  InitialNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("85a62109-81c5-46ac-94e6-2fd9be7b3166")
    @Override
    public String getName() {
        return "InitialNode";
        
    }

    @objid ("6d9c40d2-38e9-47f8-b0ed-7f1c2ec85de8")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("66fec7ca-84b0-4c65-890b-e5f5e720aa9f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InitialNode.class;
        
    }

    @objid ("2c3b4e8b-f194-4599-80ed-9703ce4af709")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("6366d71e-6746-45cb-8513-3d44eed34012")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("5f9abdaa-0702-4e35-af72-59e9b7546446")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ControlNode.MQNAME);
        this.registerFactory(new InitialNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("7cd33510-8e9b-49a1-b1bb-7e1458304a18")
    private static class InitialNodeObjectFactory implements ISmObjectFactory {
        @objid ("5d947b73-49d1-43a6-ba09-ffcd8b80a089")
        private InitialNodeSmClass smClass;

        @objid ("3b7ba8d7-7ad7-4e4d-9c8b-bc62015a07ac")
        public  InitialNodeObjectFactory(InitialNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fc3dc516-74a5-480f-9fb6-9a3d09914849")
        @Override
        public ISmObjectData createData() {
            return new InitialNodeData(this.smClass);
        }

        @objid ("e6e007e0-16b7-4a19-98ba-52b1fc69cfd9")
        @Override
        public SmObjectImpl createImpl() {
            return new InitialNodeImpl();
        }

    }

}
