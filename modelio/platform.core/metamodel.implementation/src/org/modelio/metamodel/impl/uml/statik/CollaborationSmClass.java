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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationData;
import org.modelio.metamodel.impl.uml.statik.CollaborationUseSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("35b0920b-939c-4bb9-8b77-434d6f784cd3")
public class CollaborationSmClass extends NameSpaceSmClass {
    @objid ("47af95ae-1bf8-4713-a835-7b4e595213b0")
    private SmAttribute isConcurrentAtt;

    @objid ("43a1fa6b-7917-40b5-b477-c75c848a2ce2")
    private SmDependency oRepresentedDep;

    @objid ("1a2af735-be61-4aa0-8f5e-84fd049215ba")
    private SmDependency bRepresentedDep;

    @objid ("d11e5c86-fe2e-4936-bfbe-a736b69533ee")
    private SmDependency occurrenceDep;

    @objid ("5c6ce0b4-7c5d-4d74-a787-481a3960157a")
    public CollaborationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("86b6a7ea-a9de-4ef7-92f3-1dc79687712f")
    @Override
    public String getName() {
        return "Collaboration";
    }

    @objid ("a641a11f-ccf4-4384-9882-6157452bf30e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("5c182873-64d4-4ee4-b1e2-5504035374ee")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Collaboration.class;
    }

    @objid ("1d278150-021e-4e75-aab6-5a35095915a7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("c883a152-1268-42ab-9fd3-085a8e363848")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("65dbc4df-0791-4428-acd9-587026979333")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(NameSpace.MQNAME);
        this.registerFactory(new CollaborationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isConcurrentAtt = new IsConcurrentSmAttribute();
        this.isConcurrentAtt.init("IsConcurrent", this, Boolean.class );
        registerAttribute(this.isConcurrentAtt);
        
        
        // Initialize and register the SmDependency
        this.oRepresentedDep = new ORepresentedSmDependency();
        this.oRepresentedDep.init("ORepresented", this, metamodel.getMClass(Operation.MQNAME), 0, 1 );
        registerDependency(this.oRepresentedDep);
        
        this.bRepresentedDep = new BRepresentedSmDependency();
        this.bRepresentedDep.init("BRepresented", this, metamodel.getMClass(Behavior.MQNAME), 0, 1 );
        registerDependency(this.bRepresentedDep);
        
        this.occurrenceDep = new OccurrenceSmDependency();
        this.occurrenceDep.init("Occurrence", this, metamodel.getMClass(CollaborationUse.MQNAME), 0, -1 );
        registerDependency(this.occurrenceDep);
    }

    @objid ("508b06cb-c4ed-40db-8eb7-91cfbfdca428")
    public SmAttribute getIsConcurrentAtt() {
        if (this.isConcurrentAtt == null) {
        	this.isConcurrentAtt = this.getAttributeDef("IsConcurrent");
        }
        return this.isConcurrentAtt;
    }

    @objid ("a3fd55d3-cd82-44fb-a1cb-e996ad6d0d2f")
    public SmDependency getORepresentedDep() {
        if (this.oRepresentedDep == null) {
        	this.oRepresentedDep = this.getDependencyDef("ORepresented");
        }
        return this.oRepresentedDep;
    }

    @objid ("6cd91992-6c46-4872-b734-af998af78acf")
    public SmDependency getBRepresentedDep() {
        if (this.bRepresentedDep == null) {
        	this.bRepresentedDep = this.getDependencyDef("BRepresented");
        }
        return this.bRepresentedDep;
    }

    @objid ("c859b7ba-ef52-4756-ae37-3fa4af2eeb8a")
    public SmDependency getOccurrenceDep() {
        if (this.occurrenceDep == null) {
        	this.occurrenceDep = this.getDependencyDef("Occurrence");
        }
        return this.occurrenceDep;
    }

    @objid ("841371e0-8682-4003-840d-1837ff1d5030")
    private static class CollaborationObjectFactory implements ISmObjectFactory {
        @objid ("c1ac5725-bc21-443e-91e1-a8609c7939c8")
        private CollaborationSmClass smClass;

        @objid ("4c19d04a-20a6-4c49-9159-43f2307e7e66")
        public CollaborationObjectFactory(CollaborationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ec582bf7-0531-496b-89c4-d53bc64c8e07")
        @Override
        public ISmObjectData createData() {
            return new CollaborationData(this.smClass);
        }

        @objid ("1105775e-591a-4ce3-b0ef-e0b9ec5808e6")
        @Override
        public SmObjectImpl createImpl() {
            return new CollaborationImpl();
        }

    }

    @objid ("951f9368-f01b-4cfd-9f28-56eb5ff67bdf")
    public static class IsConcurrentSmAttribute extends SmAttribute {
        @objid ("8af02ae6-1efd-404d-91de-2bda62ec172e")
        public Object getValue(ISmObjectData data) {
            return ((CollaborationData) data).mIsConcurrent;
        }

        @objid ("625e041f-4a52-4c3d-8a28-5cca5f01aa3b")
        public void setValue(ISmObjectData data, Object value) {
            ((CollaborationData) data).mIsConcurrent = value;
        }

    }

    @objid ("a39a68ea-d105-4aee-a773-c2d874812a80")
    public static class ORepresentedSmDependency extends SmSingleDependency {
        @objid ("cd9e5460-cfdb-472f-b042-8ce90d246ebc")
        private SmDependency symetricDep;

        @objid ("77084d26-6e5e-4307-bda4-f6e9a3631811")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CollaborationData) data).mORepresented;
        }

        @objid ("2ea2051c-c714-4bc5-9592-cf937a0379ea")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CollaborationData) data).mORepresented = value;
        }

        @objid ("2a5354c5-f0cb-48b4-8bcf-dabb7c7630dd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getExampleDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("52616a8f-1eb6-4840-b617-859c01c1f3ad")
    public static class BRepresentedSmDependency extends SmSingleDependency {
        @objid ("47a1e8dc-b554-4f12-a267-a755a8f5af6c")
        private SmDependency symetricDep;

        @objid ("ecc175dc-75c4-4519-8a29-9b8f5dc408dc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CollaborationData) data).mBRepresented;
        }

        @objid ("f4b1948c-caf2-49ae-a3d0-9580525cada2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CollaborationData) data).mBRepresented = value;
        }

        @objid ("309aa0db-84ed-483f-be30-e88187c02979")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getOwnedCollaborationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0bb9cfbf-38ea-4215-94d0-0c62861db73a")
    public static class OccurrenceSmDependency extends SmMultipleDependency {
        @objid ("6af12f42-3b4f-4307-80d3-f1da5a7f0e5d")
        private SmDependency symetricDep;

        @objid ("c32b6cd6-db0e-404c-b782-6869275e489b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CollaborationData)data).mOccurrence != null)? ((CollaborationData)data).mOccurrence:SmMultipleDependency.EMPTY;
        }

        @objid ("291938cd-2173-43fb-97de-7cdd565dfc34")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CollaborationData) data).mOccurrence = values;
        }

        @objid ("7faa5ad7-5881-49f1-901d-b6c3060d034f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationUseSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

}
