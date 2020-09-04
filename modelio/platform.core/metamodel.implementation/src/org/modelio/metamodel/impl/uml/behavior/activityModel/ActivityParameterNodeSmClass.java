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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b0385a6c-1cd1-4e16-a2d8-4b18d6303053")
public class ActivityParameterNodeSmClass extends ObjectNodeSmClass {
    @objid ("eecaaac9-9444-441e-ab38-5a6f139b2c89")
    public ActivityParameterNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("33c7acbf-3966-49f2-8365-50867e280aec")
    @Override
    public String getName() {
        return "ActivityParameterNode";
    }

    @objid ("2bd2c7c5-5648-4202-8aed-127bebe92dff")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("678e9cb7-48cf-4ed4-8888-04c6c3c2a35d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityParameterNode.class;
    }

    @objid ("61f7e535-4503-4e7d-ab49-c2f7a043fd7b")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e34ca4e7-28dd-4a56-ac9d-088efc8583d0")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("17d43054-eb36-4bf1-a113-38af4bc3786d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ObjectNode.MQNAME);
        this.registerFactory(new ActivityParameterNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("9eec084b-0001-4717-98c8-17dee8a26c2b")
    private static class ActivityParameterNodeObjectFactory implements ISmObjectFactory {
        @objid ("0f53a5e9-c0eb-43ce-b0e7-cc63601eb4b9")
        private ActivityParameterNodeSmClass smClass;

        @objid ("6c0356fb-c37e-4825-a1ec-604da94008e3")
        public ActivityParameterNodeObjectFactory(ActivityParameterNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("128979e6-12c7-4279-92be-91570ccd8d1a")
        @Override
        public ISmObjectData createData() {
            return new ActivityParameterNodeData(this.smClass);
        }

        @objid ("9f04c6ab-e217-406f-8366-fd5522e98466")
        @Override
        public SmObjectImpl createImpl() {
            return new ActivityParameterNodeImpl();
        }

    }

}
