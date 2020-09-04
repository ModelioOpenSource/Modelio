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
import org.modelio.metamodel.impl.uml.behavior.activityModel.FinalNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.FinalNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("0e2fb08a-7baa-443b-b80d-f9ba53347a78")
public class ActivityFinalNodeSmClass extends FinalNodeSmClass {
    @objid ("af8670c9-afa0-4946-8599-913f44f7d9f0")
    public ActivityFinalNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0ad26e06-5520-41db-8f38-d89f1b1ec124")
    @Override
    public String getName() {
        return "ActivityFinalNode";
    }

    @objid ("91900ea3-5c2a-41fe-81f7-1544a3c99d60")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("15999d80-c9fe-4914-8c04-431d64f86d7d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityFinalNode.class;
    }

    @objid ("2c7ab435-7ea4-4a02-a056-749e424b4cd2")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5d3c28f6-46ef-4cb5-a6c1-0f2799d4f8b9")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ab030448-13a8-4162-9f7d-a65cc8ca5907")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(FinalNode.MQNAME);
        this.registerFactory(new ActivityFinalNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("64be6c38-208f-4d34-862e-12df73372e9b")
    private static class ActivityFinalNodeObjectFactory implements ISmObjectFactory {
        @objid ("2d37f4af-9c49-4f73-9e8f-1a24a28ea763")
        private ActivityFinalNodeSmClass smClass;

        @objid ("92e574cc-b38b-4a74-98fb-127fdef30650")
        public ActivityFinalNodeObjectFactory(ActivityFinalNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("150d978e-4f61-40f7-87d1-7be57ec41b2a")
        @Override
        public ISmObjectData createData() {
            return new ActivityFinalNodeData(this.smClass);
        }

        @objid ("28334254-923c-402c-b897-c314e55a95f1")
        @Override
        public SmObjectImpl createImpl() {
            return new ActivityFinalNodeImpl();
        }

    }

}
