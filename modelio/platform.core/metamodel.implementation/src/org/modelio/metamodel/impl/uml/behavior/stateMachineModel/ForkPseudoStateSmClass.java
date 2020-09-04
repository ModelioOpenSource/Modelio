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
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.AbstractPseudoStateSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("51ce5a8b-d368-44e6-9660-4fa2d173b067")
public class ForkPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("7d2ed97d-04e5-43d3-a1d5-61330e4a331c")
    public ForkPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("dd4637e5-e4f7-42ed-913d-3695dd585096")
    @Override
    public String getName() {
        return "ForkPseudoState";
    }

    @objid ("1c169033-73a8-41f8-9c29-921fde97a9df")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a07b90a1-a3f9-4c35-9770-ae752e17aa98")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ForkPseudoState.class;
    }

    @objid ("0cfdfa13-a2fe-4d0f-94e4-e53bb151d053")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("fdab6fa7-2d6e-45df-95d1-e92b1a783247")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7b0f11bd-b8a2-4fdc-919d-118a8cc0b500")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new ForkPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("b8eec8d2-7d8f-4091-89a4-ebf89f675fc3")
    private static class ForkPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("30cbe19b-4397-4938-b25b-4a1b7c0b5111")
        private ForkPseudoStateSmClass smClass;

        @objid ("7be20556-3390-4ccc-831c-39955936f1b5")
        public ForkPseudoStateObjectFactory(ForkPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("dcc6f288-1f1b-4f1d-b436-ca02126c2a3f")
        @Override
        public ISmObjectData createData() {
            return new ForkPseudoStateData(this.smClass);
        }

        @objid ("757055cb-d588-407d-a587-682393a63431")
        @Override
        public SmObjectImpl createImpl() {
            return new ForkPseudoStateImpl();
        }

    }

}
