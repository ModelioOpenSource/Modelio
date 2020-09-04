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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.AbstractPseudoStateSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("e9c56d81-716f-45be-aff4-213f05dba235")
public class DeepHistoryPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("6b9ac15a-6061-4b8b-b4f3-3039ad4870b9")
    public DeepHistoryPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b579456c-c76a-4af9-8a73-39f77845e867")
    @Override
    public String getName() {
        return "DeepHistoryPseudoState";
    }

    @objid ("786b96b3-dcaf-492b-a252-5366192cbc77")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1fc5fbc1-157a-475b-886e-587cefbd877c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DeepHistoryPseudoState.class;
    }

    @objid ("188e5621-a5a6-46b4-b1cc-2bb0d51a657a")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e0f8031c-8396-4220-976a-7a49baed8357")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("1458878e-98bc-428f-9223-6f5eaeb971e6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new DeepHistoryPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("da8f2c61-8306-4e23-b813-5fb03a2eca69")
    private static class DeepHistoryPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("c67f7c1f-0ad6-4f69-a77d-5d0a851e0568")
        private DeepHistoryPseudoStateSmClass smClass;

        @objid ("2387694d-7f35-40f8-ba62-08689017bda3")
        public DeepHistoryPseudoStateObjectFactory(DeepHistoryPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ae61c869-dbfa-4795-bb70-b0dae69f7aa8")
        @Override
        public ISmObjectData createData() {
            return new DeepHistoryPseudoStateData(this.smClass);
        }

        @objid ("1090ae89-7bca-4fd6-b07e-bfe4877de833")
        @Override
        public SmObjectImpl createImpl() {
            return new DeepHistoryPseudoStateImpl();
        }

    }

}
