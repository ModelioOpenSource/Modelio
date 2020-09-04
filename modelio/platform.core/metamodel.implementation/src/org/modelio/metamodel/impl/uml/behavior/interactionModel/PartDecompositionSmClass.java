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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionUseSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.LifelineSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.PartDecompositionData;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
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

@objid ("82b10d49-a5cd-435e-b05c-423ff2ffd4c4")
public class PartDecompositionSmClass extends InteractionUseSmClass {
    @objid ("86069dbf-a8c9-44c3-82f3-2b8ae9f90e5c")
    private SmDependency decomposedDep;

    @objid ("817518ef-076b-42f2-bc0a-f865dbd1f63a")
    public PartDecompositionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3587a08d-0dd2-4355-97b2-5dcb37225b44")
    @Override
    public String getName() {
        return "PartDecomposition";
    }

    @objid ("37219420-1a3f-4f8c-ba47-e065a876ca58")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ab4ccbb8-7884-4756-b5c7-6d5342ce9bd5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PartDecomposition.class;
    }

    @objid ("5f74085d-1776-4561-8003-35f418ea29ff")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("f1d56a1e-f294-4e83-a805-77b1c762b44a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c727d5c0-6f9d-4c40-a107-41e47f7f4a61")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InteractionUse.MQNAME);
        this.registerFactory(new PartDecompositionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.decomposedDep = new DecomposedSmDependency();
        this.decomposedDep.init("Decomposed", this, metamodel.getMClass(Lifeline.MQNAME), 1, 1 );
        registerDependency(this.decomposedDep);
    }

    @objid ("d3d8ae4f-4d20-42f0-b4aa-dd4f1e84f01f")
    public SmDependency getDecomposedDep() {
        if (this.decomposedDep == null) {
        	this.decomposedDep = this.getDependencyDef("Decomposed");
        }
        return this.decomposedDep;
    }

    @objid ("304b5079-bf70-44e9-81df-cad4e700a117")
    private static class PartDecompositionObjectFactory implements ISmObjectFactory {
        @objid ("669c8dac-144b-4592-84ee-d81c067d434d")
        private PartDecompositionSmClass smClass;

        @objid ("a7a31bdf-7793-477e-bf2f-1a56bc4c6747")
        public PartDecompositionObjectFactory(PartDecompositionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4874e2ac-871d-495c-bca9-5a3e77a1ac70")
        @Override
        public ISmObjectData createData() {
            return new PartDecompositionData(this.smClass);
        }

        @objid ("747c6204-0a1a-4466-b7a1-1157b1d0ebfb")
        @Override
        public SmObjectImpl createImpl() {
            return new PartDecompositionImpl();
        }

    }

    @objid ("60e78d47-1b29-473a-8fa4-473792cee9aa")
    public static class DecomposedSmDependency extends SmSingleDependency {
        @objid ("b61bf100-90b8-41fc-a8bd-7ae44a7de80d")
        private SmDependency symetricDep;

        @objid ("cb6422f2-5675-4fa1-b311-1f0f9ac3dc5b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PartDecompositionData) data).mDecomposed;
        }

        @objid ("463ad8dc-db40-4ff4-a0c9-ede259f80b68")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PartDecompositionData) data).mDecomposed = value;
        }

        @objid ("1b5107c1-1583-4d30-b771-5fe9173b718a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LifelineSmClass)this.getTarget()).getDecomposedAsDep();
            }
            return this.symetricDep;
        }

    }

}
