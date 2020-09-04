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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityGroupData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivitySmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("22b4d7a0-d812-413f-9aa3-c308aab80b35")
public class ActivityGroupSmClass extends UmlModelElementSmClass {
    @objid ("adc2d567-d192-4dd3-957f-317ae4b03b05")
    private SmDependency inActivityDep;

    @objid ("7e980fcb-e746-4c3f-9a6e-67912c375a1f")
    public ActivityGroupSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("54bb145d-7312-4ea6-8f43-6d1f10e5a53e")
    @Override
    public String getName() {
        return "ActivityGroup";
    }

    @objid ("ad047723-18ca-4286-bfd2-19f07dafba9d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2fc8eb69-e549-4273-87d3-bcf11c830a01")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityGroup.class;
    }

    @objid ("9b1916bb-549e-4705-b041-361eb50e1576")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a60bc9b1-081c-45f6-9c94-992378cc57aa")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("637715cd-2f7a-4c1a-b168-2ffbb85996e0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ActivityGroupObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.inActivityDep = new InActivitySmDependency();
        this.inActivityDep.init("InActivity", this, metamodel.getMClass(Activity.MQNAME), 0, 1 );
        registerDependency(this.inActivityDep);
    }

    @objid ("a81a2333-0a49-41cf-b3ad-a982c9ef1b66")
    public SmDependency getInActivityDep() {
        if (this.inActivityDep == null) {
        	this.inActivityDep = this.getDependencyDef("InActivity");
        }
        return this.inActivityDep;
    }

    @objid ("29092251-e120-4984-8fe8-954de5a2d621")
    private static class ActivityGroupObjectFactory implements ISmObjectFactory {
        @objid ("c8200f06-c731-453d-ab61-7a7078579c73")
        private ActivityGroupSmClass smClass;

        @objid ("95a5979e-bb94-43f3-9214-f67893046c80")
        public ActivityGroupObjectFactory(ActivityGroupSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("451f1a5d-0afa-47fd-b77e-31f046e368a7")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("a63814ea-c55d-4e4f-886e-70dbe5af2027")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("a6db40ef-10ad-4e36-ae6a-5b4e34b0bc23")
    public static class InActivitySmDependency extends SmSingleDependency {
        @objid ("d76b2101-2614-416b-92e4-172ec4e8b888")
        private SmDependency symetricDep;

        @objid ("a6423585-60da-4f17-9ac6-25c334b0d8e4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityGroupData) data).mInActivity;
        }

        @objid ("4534030d-1b73-4859-ac3d-44a28c5cf785")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityGroupData) data).mInActivity = value;
        }

        @objid ("7baa84c4-7ac1-4cbb-b77a-fb06b095a322")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivitySmClass)this.getTarget()).getOwnedGroupDep();
            }
            return this.symetricDep;
        }

    }

}
