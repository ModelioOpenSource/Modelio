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
package org.modelio.metamodel.impl.uml.informationFlow;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.DataFlowData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
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

@objid ("5e287ca6-25cf-4a81-bd76-c3652252c3ae")
public class DataFlowSmClass extends UmlModelElementSmClass {
    @objid ("559bce4d-3d28-4b42-8706-99f5c5009724")
    private SmDependency destinationDep;

    @objid ("07858337-4533-41f8-af65-5d3341d05ece")
    private SmDependency originDep;

    @objid ("e4ccb604-d36f-432a-bf44-6de29e026987")
    private SmDependency ownerDep;

    @objid ("a28fed6c-02e9-4902-9f56-210cda08067a")
    private SmDependency sModelDep;

    @objid ("10f7f256-c8e5-4c58-b888-d36eb98980bc")
    public DataFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("438ba936-295f-4d61-843a-986c6f821aa6")
    @Override
    public String getName() {
        return "DataFlow";
    }

    @objid ("f73bcf96-bc1c-4629-a0f1-d9c59fb26051")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6a06f036-ecc6-49a0-b2c4-a6f5e6868899")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DataFlow.class;
    }

    @objid ("2985ea86-c520-44bd-a571-74e9d2b34efc")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("74d8f09a-de52-4813-90d1-fe13994b6519")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("59a35aa5-69c0-4df6-84fd-1f42708bdf12")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new DataFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.destinationDep = new DestinationSmDependency();
        this.destinationDep.init("Destination", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.destinationDep);
        
        this.originDep = new OriginSmDependency();
        this.originDep.init("Origin", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.originDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(NameSpace.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        this.sModelDep = new SModelSmDependency();
        this.sModelDep.init("SModel", this, metamodel.getMClass(Signal.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.sModelDep);
    }

    @objid ("df8e47ee-09e1-44cd-99ed-8c7ff9bc6861")
    public SmDependency getDestinationDep() {
        if (this.destinationDep == null) {
        	this.destinationDep = this.getDependencyDef("Destination");
        }
        return this.destinationDep;
    }

    @objid ("38a520bb-614e-4e16-9eec-b3ac690453db")
    public SmDependency getOriginDep() {
        if (this.originDep == null) {
        	this.originDep = this.getDependencyDef("Origin");
        }
        return this.originDep;
    }

    @objid ("db9658af-eebf-451b-b566-a0ed0471d0e1")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("7c247212-b7a8-45b8-9ab1-0ea2b524ec03")
    public SmDependency getSModelDep() {
        if (this.sModelDep == null) {
        	this.sModelDep = this.getDependencyDef("SModel");
        }
        return this.sModelDep;
    }

    @objid ("f98ebf3a-6a2b-4362-a0b1-d76635ba931c")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("ed334312-1f9f-4246-a411-90b790e90110")
    private static class DataFlowObjectFactory implements ISmObjectFactory {
        @objid ("8214b61f-ed42-45a6-89cf-e4f7da953882")
        private DataFlowSmClass smClass;

        @objid ("0918b7c6-9754-49e1-8488-3d0b88fdcf7a")
        public DataFlowObjectFactory(DataFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ee32b0c7-7c6b-42a8-a6a0-4ee7dea64652")
        @Override
        public ISmObjectData createData() {
            return new DataFlowData(this.smClass);
        }

        @objid ("b2752fb8-0bec-4405-89f2-735469fd7eb1")
        @Override
        public SmObjectImpl createImpl() {
            return new DataFlowImpl();
        }

    }

    @objid ("df7d12c1-5172-424c-9495-c4d5ac2c757e")
    public static class DestinationSmDependency extends SmSingleDependency {
        @objid ("324d5336-ce8c-41a9-b209-f933991ab801")
        private SmDependency symetricDep;

        @objid ("157122e4-66c4-4b2f-a1bc-75d3f2ba75cb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DataFlowData) data).mDestination;
        }

        @objid ("f92f8bff-944b-483d-8976-5488ece39618")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DataFlowData) data).mDestination = value;
        }

        @objid ("5b886db5-1837-49b3-94ac-b47d860e5bf9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getReceivedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("82ada976-ba32-4c6c-92a1-f7143b1672c4")
    public static class OriginSmDependency extends SmSingleDependency {
        @objid ("7466ea30-51b5-4ca1-82db-f574be24dda2")
        private SmDependency symetricDep;

        @objid ("eb3c696c-c91b-4b26-9828-385591fc1c5c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DataFlowData) data).mOrigin;
        }

        @objid ("0603a34a-e8d6-431a-ac6c-e3789cc655ec")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DataFlowData) data).mOrigin = value;
        }

        @objid ("303951c2-a0aa-46ad-93f4-708a33b12c92")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getSentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("d725250d-a748-4e36-83b5-17efdbec0593")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("cf609a7e-652a-46bf-b120-f54eb46728ef")
        private SmDependency symetricDep;

        @objid ("69998c2a-1750-4936-b57f-c6a1e204db1d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DataFlowData) data).mOwner;
        }

        @objid ("a15a3bae-03fa-4f5a-9cec-9538250b50c2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DataFlowData) data).mOwner = value;
        }

        @objid ("d85216d5-1157-4910-894e-af7353f8af57")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getOwnedDataFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("28f75e18-6b56-46b3-ad15-8b5beb6fea85")
    public static class SModelSmDependency extends SmSingleDependency {
        @objid ("32881d85-4a31-41a3-bbc3-9fb5caaee109")
        private SmDependency symetricDep;

        @objid ("d232e99c-7e46-4e4a-9000-97b40c3cbe54")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DataFlowData) data).mSModel;
        }

        @objid ("6ebe899e-7a26-482a-a5c4-1ff529c76160")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DataFlowData) data).mSModel = value;
        }

        @objid ("3430b78b-ceb8-44da-a51e-dd2cc4f25a02")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getDOccurenceDep();
            }
            return this.symetricDep;
        }

    }

}
