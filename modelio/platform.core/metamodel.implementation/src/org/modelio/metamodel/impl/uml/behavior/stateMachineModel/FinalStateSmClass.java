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
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.FinalState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("33cd240c-7179-433e-8497-c01cf1563bf1")
public class FinalStateSmClass extends StateSmClass {
    @objid ("93a3bd1f-7c79-4a35-8560-883a9de8c343")
    public FinalStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2ca6d38a-716b-4db3-85cd-3526bc8269f8")
    @Override
    public String getName() {
        return "FinalState";
    }

    @objid ("5de3547e-b17d-467d-8dc9-179c7adcad0d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("cca0a0c6-eebd-4f2a-aa45-6a4afa64bb3d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return FinalState.class;
    }

    @objid ("1d5e2fdc-183e-44d8-ad8d-2af095c0c4d7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("d5366351-95ca-4b72-b193-fb32973ab01d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("cd7aef75-6976-43c1-95be-3a884342cbd6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(State.MQNAME);
        this.registerFactory(new FinalStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("437f4304-39aa-43ce-ba1b-70ef405e4e46")
    private static class FinalStateObjectFactory implements ISmObjectFactory {
        @objid ("9ba2ba8d-8a82-4817-aa96-336d90f32bf5")
        private FinalStateSmClass smClass;

        @objid ("3a7d9889-eff4-434b-a644-3b595afe1a3a")
        public FinalStateObjectFactory(FinalStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d39ea408-206f-4f58-a8d6-dfd3d5781fa3")
        @Override
        public ISmObjectData createData() {
            return new FinalStateData(this.smClass);
        }

        @objid ("42ee3a53-b9b1-4218-b309-b5b66efa28a9")
        @Override
        public SmObjectImpl createImpl() {
            return new FinalStateImpl();
        }

    }

}
