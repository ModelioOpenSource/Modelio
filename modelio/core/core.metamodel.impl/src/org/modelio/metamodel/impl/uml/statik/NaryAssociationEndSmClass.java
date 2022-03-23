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

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.StructuralFeature;
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

@objid ("8b52c6d8-b2a1-483b-9534-357edc70ea1e")
public class NaryAssociationEndSmClass extends StructuralFeatureSmClass {
    @objid ("a9738528-cc84-4ebb-9f5e-5ba27d289af3")
    private SmDependency naryAssociationDep;

    @objid ("95a1cf50-70af-44ab-bdd2-9eb8ce9be064")
    private SmDependency ownerDep;

    @objid ("6051fbb5-db6b-4a0b-b313-422e39cc5053")
    public  NaryAssociationEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2a9c2cc2-9dfb-407f-8a67-f092115f862a")
    @Override
    public String getName() {
        return "NaryAssociationEnd";
        
    }

    @objid ("ad726f9b-7d6c-4fdb-a785-e97f53247e86")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("93ba9f9a-eb18-466e-b337-f553e59077ce")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NaryAssociationEnd.class;
        
    }

    @objid ("087e8320-ce23-40f3-a62e-fa92427b55be")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("64edf9b7-92fc-424c-a662-055d5fcf0d7b")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("d9b6efc7-4589-4859-86e4-511f721f4235")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StructuralFeature.MQNAME);
        this.registerFactory(new NaryAssociationEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.naryAssociationDep = new NaryAssociationSmDependency();
        this.naryAssociationDep.init("NaryAssociation", this, metamodel.getMClass(NaryAssociation.MQNAME), 1, 1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.naryAssociationDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Classifier.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        
    }

    @objid ("6317c51f-1d99-4d4a-bb71-ab41e2e3ab00")
    public SmDependency getNaryAssociationDep() {
        if (this.naryAssociationDep == null) {
        	this.naryAssociationDep = this.getDependencyDef("NaryAssociation");
        }
        return this.naryAssociationDep;
    }

    @objid ("5bb17956-a528-4eed-8a41-3d555fb7dc0b")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("e6a43f06-7934-4bfb-92d3-ffab8fe431d3")
    private static class NaryAssociationEndObjectFactory implements ISmObjectFactory {
        @objid ("85ecdd51-34ea-4d0e-bb92-2f199f02b387")
        private NaryAssociationEndSmClass smClass;

        @objid ("e98b7096-622b-421e-b713-bc44f6454471")
        public  NaryAssociationEndObjectFactory(NaryAssociationEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b2ccd8cc-85eb-4f80-9db6-7f0405f2ca68")
        @Override
        public ISmObjectData createData() {
            return new NaryAssociationEndData(this.smClass);
        }

        @objid ("522914f0-b495-4074-addb-b8bee0edb395")
        @Override
        public SmObjectImpl createImpl() {
            return new NaryAssociationEndImpl();
        }

    }

    @objid ("0e0baf00-9a7d-4293-8d1b-8fb714119cb5")
    public static class NaryAssociationSmDependency extends SmSingleDependency {
        @objid ("9a541600-0177-4166-bcb0-7eb175536b6a")
        private SmDependency symetricDep;

        @objid ("88fd6a2c-3bb3-4d97-aa0e-e9e02d44ca9d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryAssociationEndData) data).mNaryAssociation;
        }

        @objid ("a3264d69-8a81-4a38-86c4-7d4118cf5cd0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryAssociationEndData) data).mNaryAssociation = value;
        }

        @objid ("836e65f9-ff3d-4c1c-91ba-7bd219f0e2d6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryAssociationSmClass)this.getTarget()).getNaryEndDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6f3e8882-8768-4454-916a-1bd701bd766a")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("f17b18ee-d340-4513-b933-6186bfa5d19c")
        private SmDependency symetricDep;

        @objid ("a2556d66-6d82-4a7e-b02c-decd0d447d77")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryAssociationEndData) data).mOwner;
        }

        @objid ("1e6128ef-1a6f-479a-8997-d93d15e94753")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryAssociationEndData) data).mOwner = value;
        }

        @objid ("859dbf82-2116-40fa-afda-52598d6056df")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getOwnedNaryEndDep();
            }
            return this.symetricDep;
            
        }

    }

}
