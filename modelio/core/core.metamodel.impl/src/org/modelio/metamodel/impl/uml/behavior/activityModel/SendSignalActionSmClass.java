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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.SendSignalActionData;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
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

@objid ("a68b21b3-a195-4b30-a47c-2032fc3d33f5")
public class SendSignalActionSmClass extends ActivityActionSmClass {
    @objid ("43f243f4-216e-41ad-92c7-4eb90c963b51")
    private SmDependency sentDep;

    @objid ("2c7a5219-b44f-4833-8f54-20dd8ac54ec3")
    public SendSignalActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d4f1f737-614f-4992-85c6-0f0ce520dd87")
    @Override
    public String getName() {
        return "SendSignalAction";
    }

    @objid ("4dac1f97-b260-40c8-bb43-2113ddc99ce9")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a1ba5657-cafb-48d9-977b-19f271df6849")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return SendSignalAction.class;
    }

    @objid ("502dae2b-267b-4831-9fb9-0ecbcfc74342")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("2bd7bff9-e69d-40e2-b1e7-88c043ef7386")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("3531f37b-9e92-4f40-b76d-171b0eaa780d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new SendSignalActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.sentDep = new SentSmDependency();
        this.sentDep.init("Sent", this, metamodel.getMClass(Signal.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.sentDep);
    }

    @objid ("ff1ccfb1-0255-479d-83a4-fe3335f7f1c4")
    public SmDependency getSentDep() {
        if (this.sentDep == null) {
        	this.sentDep = this.getDependencyDef("Sent");
        }
        return this.sentDep;
    }

    @objid ("11a986af-450f-42bc-8607-d14629549aa0")
    private static class SendSignalActionObjectFactory implements ISmObjectFactory {
        @objid ("21ae486b-84b6-46e3-82fc-85be05d6825b")
        private SendSignalActionSmClass smClass;

        @objid ("bc2925bd-1bdb-4454-ac4e-c6ae736f6996")
        public SendSignalActionObjectFactory(SendSignalActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("15a49afb-ec01-45bc-a97b-af93b2dece95")
        @Override
        public ISmObjectData createData() {
            return new SendSignalActionData(this.smClass);
        }

        @objid ("2ce97b22-764b-4e5f-9d29-a31dabbeb54c")
        @Override
        public SmObjectImpl createImpl() {
            return new SendSignalActionImpl();
        }

    }

    @objid ("7131c85b-74ba-4f05-b9ff-6f4d27a7461a")
    public static class SentSmDependency extends SmSingleDependency {
        @objid ("0f670827-64c3-4beb-b17e-ce630790e332")
        private SmDependency symetricDep;

        @objid ("75dad31d-1379-4a3a-bda7-4dd2a93a5d07")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((SendSignalActionData) data).mSent;
        }

        @objid ("1e73f543-d035-4cee-aea6-62c4a4d4d6fb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((SendSignalActionData) data).mSent = value;
        }

        @objid ("fbcbcebf-cd3f-4a76-99eb-a1d69f8569fc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getSenderDep();
            }
            return this.symetricDep;
        }

    }

}
