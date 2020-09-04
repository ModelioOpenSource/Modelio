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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.ArtifactSmClass;
import org.modelio.metamodel.impl.uml.statik.ManifestationData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
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

@objid ("4f4fd4c5-a171-4fa9-899d-85c75ae4e972")
public class ManifestationSmClass extends UmlModelElementSmClass {
    @objid ("8a2e1cb8-2fa1-48dd-8eff-2c95b5e97ed7")
    private SmDependency utilizedElementDep;

    @objid ("4f058e85-d263-4394-8827-5fcfb42a23b2")
    private SmDependency ownerDep;

    @objid ("f9a2d852-d7fe-49cc-846e-ffca4239085f")
    public ManifestationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("15243969-4168-45c6-9274-09b2e352a8c3")
    @Override
    public String getName() {
        return "Manifestation";
    }

    @objid ("521a61ee-ffa3-4c14-aa44-59b70d61b58c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c99a7652-55b9-440a-8b02-fc924d66428f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Manifestation.class;
    }

    @objid ("f2e4ea4f-b04e-481a-8038-340735aad5d7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("1693d488-bc8c-4c26-9d01-38f6c1e98a63")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a59fe9cb-1e3a-481a-9036-4bf226f09ebe")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ManifestationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.utilizedElementDep = new UtilizedElementSmDependency();
        this.utilizedElementDep.init("UtilizedElement", this, metamodel.getMClass(UmlModelElement.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.utilizedElementDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Artifact.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.ownerDep);
    }

    @objid ("43541339-539f-4701-8ad9-e63baf6933b1")
    public SmDependency getUtilizedElementDep() {
        if (this.utilizedElementDep == null) {
        	this.utilizedElementDep = this.getDependencyDef("UtilizedElement");
        }
        return this.utilizedElementDep;
    }

    @objid ("cd7a946e-4838-4c01-8a11-a60cef64ab58")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("f0002fe8-8bca-45a3-9cd7-fa81c2b143dd")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("ec781bc6-b111-4eaa-bb35-03c5bfc49845")
    private static class ManifestationObjectFactory implements ISmObjectFactory {
        @objid ("228f9c79-d9c0-4d8c-b7ac-a7e2d5028d22")
        private ManifestationSmClass smClass;

        @objid ("8f8878d3-d3ce-4224-bc98-572af09f6f04")
        public ManifestationObjectFactory(ManifestationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("56a98616-514e-4737-a01f-a7bd12a72e92")
        @Override
        public ISmObjectData createData() {
            return new ManifestationData(this.smClass);
        }

        @objid ("445e3f38-371d-4902-9efe-caf3a4172253")
        @Override
        public SmObjectImpl createImpl() {
            return new ManifestationImpl();
        }

    }

    @objid ("7f539b62-4e7f-4509-81d1-f3f73f6973b8")
    public static class UtilizedElementSmDependency extends SmSingleDependency {
        @objid ("e9bf6bcf-d451-4a02-9b62-8107d7847868")
        private SmDependency symetricDep;

        @objid ("c4eade63-5035-4f41-aec7-00db5febb5d8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ManifestationData) data).mUtilizedElement;
        }

        @objid ("4e414f22-0e86-41fe-af09-92881d557cb7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ManifestationData) data).mUtilizedElement = value;
        }

        @objid ("075a36b1-6c4e-41de-a094-de59b3a6bf05")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getManifestingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e6a86151-286b-49e0-a62e-62ccc2cdef1d")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("e46b5296-425b-4584-932f-3a93a390627e")
        private SmDependency symetricDep;

        @objid ("f3e49f4f-b8e3-4b96-a642-7a11b4054b2b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ManifestationData) data).mOwner;
        }

        @objid ("125a19ed-6ce7-4051-ac10-759ed330e130")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ManifestationData) data).mOwner = value;
        }

        @objid ("aef26708-b7e3-497c-84a7-d6f5c8739138")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ArtifactSmClass)this.getTarget()).getUtilizedDep();
            }
            return this.symetricDep;
        }

    }

}
