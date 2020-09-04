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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.InternalTransitionData;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
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

@objid ("e2239663-1b21-4104-82a3-0efc9a374bb3")
public class InternalTransitionSmClass extends TransitionSmClass {
    @objid ("89f084ab-d56d-4336-b2c2-bb702188589d")
    private SmDependency sComposedDep;

    @objid ("67aafc73-94b9-43d4-933c-00da348fbd68")
    public InternalTransitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9fb5e8bc-f652-4f84-bd21-d89eb782e21f")
    @Override
    public String getName() {
        return "InternalTransition";
    }

    @objid ("ab8a4945-4336-4f92-add8-a3f4164b0899")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3bfc694d-8ca5-4032-b927-d1f066052f6a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InternalTransition.class;
    }

    @objid ("0bffc170-2644-4489-895e-2411faa4db7e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("399cbebf-b6cf-4863-adbb-a8aee1c5f198")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("2494cafe-9df3-4e21-8daa-4d89210d0ba4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Transition.MQNAME);
        this.registerFactory(new InternalTransitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.sComposedDep = new SComposedSmDependency();
        this.sComposedDep.init("SComposed", this, metamodel.getMClass(State.MQNAME), 0, 1 );
        registerDependency(this.sComposedDep);
    }

    @objid ("dfd7c655-94c1-407e-8ad9-79407c63ab85")
    public SmDependency getSComposedDep() {
        if (this.sComposedDep == null) {
        	this.sComposedDep = this.getDependencyDef("SComposed");
        }
        return this.sComposedDep;
    }

    @objid ("46ff8e85-6472-4df9-83ad-0a8328b7ac84")
    private static class InternalTransitionObjectFactory implements ISmObjectFactory {
        @objid ("1f948455-8f34-4e24-aefe-43b96426739a")
        private InternalTransitionSmClass smClass;

        @objid ("9569b1d1-5b27-43d1-925b-04955dd0897b")
        public InternalTransitionObjectFactory(InternalTransitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("dccaffad-b2d9-435b-8b1c-5b1262cd7cfb")
        @Override
        public ISmObjectData createData() {
            return new InternalTransitionData(this.smClass);
        }

        @objid ("5806104d-7802-4a8f-b990-7f038f25daaa")
        @Override
        public SmObjectImpl createImpl() {
            return new InternalTransitionImpl();
        }

    }

    @objid ("d1778794-d244-4c90-9346-d63caceb55a3")
    public static class SComposedSmDependency extends SmSingleDependency {
        @objid ("838a89c2-caae-4ea9-92ab-95f9ee32a55c")
        private SmDependency symetricDep;

        @objid ("57103509-2f5f-4d74-9381-acc60da710b0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InternalTransitionData) data).mSComposed;
        }

        @objid ("a4b2aefb-4bf3-455b-8d99-b9488e9b11ee")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InternalTransitionData) data).mSComposed = value;
        }

        @objid ("81661895-40c1-4262-9bc0-e5cff77132c7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getInternalDep();
            }
            return this.symetricDep;
        }

    }

}
