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
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.statik.Operation;
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

@objid ("d745d283-1cfe-4a72-b6e7-c1e455ade573")
public class AcceptCallEventActionSmClass extends ActivityActionSmClass {
    @objid ("4ebf3892-de11-49d2-a4e2-087da72bd0f4")
    private SmDependency calledDep;

    @objid ("68618de2-8950-4ac4-a0c5-0b0fdd2bddc7")
    public  AcceptCallEventActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4908de0d-8afa-4215-851a-ca4e705d87ba")
    @Override
    public String getName() {
        return "AcceptCallEventAction";
        
    }

    @objid ("e3bb6d3c-c54d-465b-a98a-892a8a7a1e78")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("70d401a5-98d5-4b8e-999b-5eb510b30897")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AcceptCallEventAction.class;
        
    }

    @objid ("4e8b1c4b-1606-42cb-ac3a-7623f1391edd")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("fe71accf-cd23-4a11-b98a-43a40ca4aab2")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("0d794e01-ea46-4d0d-afe1-cd798436748d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new AcceptCallEventActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.calledDep = new CalledSmDependency();
        this.calledDep.init("Called", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.calledDep);
        
        
    }

    @objid ("55dee06f-6000-43d0-8c86-974bd5b919f4")
    public SmDependency getCalledDep() {
        if (this.calledDep == null) {
        	this.calledDep = this.getDependencyDef("Called");
        }
        return this.calledDep;
    }

    @objid ("2b607cea-df9d-43c5-b676-905053bd3f83")
    private static class AcceptCallEventActionObjectFactory implements ISmObjectFactory {
        @objid ("e5992aac-01b5-492d-8d59-c725702db18c")
        private AcceptCallEventActionSmClass smClass;

        @objid ("90db8626-0769-48a1-94b2-4690043d383d")
        public  AcceptCallEventActionObjectFactory(AcceptCallEventActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3d36c9bd-0aab-4f51-be6e-af8ee40cca3d")
        @Override
        public ISmObjectData createData() {
            return new AcceptCallEventActionData(this.smClass);
        }

        @objid ("eb2be30e-253d-427f-9174-ac9b439a86f8")
        @Override
        public SmObjectImpl createImpl() {
            return new AcceptCallEventActionImpl();
        }

    }

    @objid ("404455ae-67dc-46e8-a845-e84a92f99d64")
    public static class CalledSmDependency extends SmSingleDependency {
        @objid ("000f4e1f-77d6-4d81-b96b-0088a332fd2a")
        private SmDependency symetricDep;

        @objid ("7baa26e2-ee21-46ef-ac41-5813cd985598")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AcceptCallEventActionData) data).mCalled;
        }

        @objid ("0fca2942-c2db-4025-b7f8-f996e35b376d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AcceptCallEventActionData) data).mCalled = value;
        }

        @objid ("25a7121f-957c-4f9d-8309-7f1be816008b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getEntryPointActionDep();
            }
            return this.symetricDep;
            
        }

    }

}
