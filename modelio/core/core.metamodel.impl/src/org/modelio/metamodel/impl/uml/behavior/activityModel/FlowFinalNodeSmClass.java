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
import org.modelio.metamodel.impl.uml.behavior.activityModel.FinalNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.FinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("245b4da0-6037-452b-8cc7-3a353155b4f3")
public class FlowFinalNodeSmClass extends FinalNodeSmClass {
    @objid ("03bc6afb-1d0e-4be7-a70e-b706ecf57456")
    public FlowFinalNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a2313219-de4a-4b34-86d1-143a340dd2d1")
    @Override
    public String getName() {
        return "FlowFinalNode";
    }

    @objid ("12e5b748-c585-4c40-b6d1-93d12432f8d2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("446ee0dc-3de3-4cf2-bd54-d8e88652ccaa")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return FlowFinalNode.class;
    }

    @objid ("311c8d69-ae71-447f-9b66-03c99d4ac8b2")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("84da6da8-9b70-46d9-ad1e-bb48ac96e776")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("78a5c850-5d82-4c48-8d3c-7c38e89bb16d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(FinalNode.MQNAME);
        this.registerFactory(new FlowFinalNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("d5afa96e-6488-4ff7-a60d-2afc0630bec1")
    private static class FlowFinalNodeObjectFactory implements ISmObjectFactory {
        @objid ("c2048015-2ea1-40db-aa37-882922c7c5b5")
        private FlowFinalNodeSmClass smClass;

        @objid ("84cd0326-823a-4fd4-8c46-728877e34ff3")
        public FlowFinalNodeObjectFactory(FlowFinalNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("40e7bfd6-a1e9-4857-99e2-c3f6d861470c")
        @Override
        public ISmObjectData createData() {
            return new FlowFinalNodeData(this.smClass);
        }

        @objid ("ed68b7ce-81de-4146-be49-ff6eaf3b3530")
        @Override
        public SmObjectImpl createImpl() {
            return new FlowFinalNodeImpl();
        }

    }

}
