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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
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

@objid ("352b8b30-069d-4357-94ed-0f8b4e7fd2a7")
public class CallBehaviorActionSmClass extends CallActionSmClass {
    @objid ("8a1cadeb-a571-4303-90e5-a9fb17bed0a7")
    private SmDependency calledDep;

    @objid ("793a45cd-2c70-42b4-91fe-99f0f39e6a27")
    public  CallBehaviorActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d013d5d9-f017-48dc-9205-6cf972540c17")
    @Override
    public String getName() {
        return "CallBehaviorAction";
        
    }

    @objid ("7e3fe7d9-f52d-41f1-a477-fb386010b411")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ac052e79-17bb-410a-ba29-45f3f6553926")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CallBehaviorAction.class;
        
    }

    @objid ("1e62b65d-bf5d-4e64-93ad-15e51bb8da39")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("3cf899e1-771e-40bf-8f1c-bc138dafd533")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("4202d136-2096-4482-a32f-e6d75a4b3d6f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(CallAction.MQNAME);
        this.registerFactory(new CallBehaviorActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.calledDep = new CalledSmDependency();
        this.calledDep.init("Called", this, metamodel.getMClass(Behavior.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.calledDep);
        
        
    }

    @objid ("d8311350-9b21-47c5-931e-4335e4d3dc11")
    public SmDependency getCalledDep() {
        if (this.calledDep == null) {
        	this.calledDep = this.getDependencyDef("Called");
        }
        return this.calledDep;
    }

    @objid ("dc60546a-9c1c-43c7-9b3e-e61f528cb641")
    private static class CallBehaviorActionObjectFactory implements ISmObjectFactory {
        @objid ("943c3ac3-50cb-48b4-bb9a-aaa468f53504")
        private CallBehaviorActionSmClass smClass;

        @objid ("8ba48465-d201-42b0-bb34-6e773cf7efec")
        public  CallBehaviorActionObjectFactory(CallBehaviorActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b7933666-65ab-4df4-8758-be64773c2228")
        @Override
        public ISmObjectData createData() {
            return new CallBehaviorActionData(this.smClass);
        }

        @objid ("e182f6fb-231b-4db7-a654-5e2907434180")
        @Override
        public SmObjectImpl createImpl() {
            return new CallBehaviorActionImpl();
        }

    }

    @objid ("c9960fbb-9345-47ab-bfbe-1fd8e4272486")
    public static class CalledSmDependency extends SmSingleDependency {
        @objid ("8def3c06-03bb-4626-9971-2d11a4bb4651")
        private SmDependency symetricDep;

        @objid ("50d8a1bf-2531-419b-bbda-4ecf18098233")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CallBehaviorActionData) data).mCalled;
        }

        @objid ("63831348-58dc-4ff0-b51e-99ed73ee2835")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CallBehaviorActionData) data).mCalled = value;
        }

        @objid ("06b94bef-8ea0-4ca1-ac88-a131f63c4ee0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getCallerDep();
            }
            return this.symetricDep;
            
        }

    }

}
