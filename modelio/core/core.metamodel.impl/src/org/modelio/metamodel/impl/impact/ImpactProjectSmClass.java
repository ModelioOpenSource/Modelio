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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.impl.impact.ImpactModelSmClass;
import org.modelio.metamodel.impl.impact.ImpactProjectData;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectSmClass;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("7875d3e1-c5c0-4d92-9338-3d44834765e3")
public class ImpactProjectSmClass extends AbstractProjectSmClass {
    @objid ("27c4c2c6-1b4c-409d-9a5e-17b2f3e67682")
    private SmDependency modelDep;

    @objid ("7949905d-5446-4711-85a9-7b1eac281a13")
    public ImpactProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9679f0d5-1258-4dc3-8d63-416f671a085b")
    @Override
    public String getName() {
        return "ImpactProject";
    }

    @objid ("a3f2c491-c779-4326-88a6-a74047133a81")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("b2c18130-9c8d-4f87-a003-7b5b8ab6c8e9")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactProject.class;
    }

    @objid ("0a5adbe9-d09e-4bd9-968e-fe0a1cda6ee4")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("44faebff-1c25-4ad8-8ea9-9f063a04388f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("d4a21cec-51c5-4389-9258-91246f2102ff")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractProject.MQNAME);
        this.registerFactory(new ImpactProjectObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("model", this, metamodel.getMClass(ImpactModel.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.modelDep);
    }

    @objid ("04634412-1f27-4035-b202-4651746d44a9")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("model");
        }
        return this.modelDep;
    }

    @objid ("3de7e55e-5b05-49d1-a2dc-c460c9fa2c74")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("69fdc5dd-733a-4fc8-ba42-f9540d8b3989")
    private static class ImpactProjectObjectFactory implements ISmObjectFactory {
        @objid ("b05574ab-aceb-4097-9be9-f94167f2d912")
        private ImpactProjectSmClass smClass;

        @objid ("ff5b1c53-be56-42bf-b200-aa4da2fe7687")
        public ImpactProjectObjectFactory(ImpactProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a763f030-12f7-4790-82b4-dc76526d6b11")
        @Override
        public ISmObjectData createData() {
            return new ImpactProjectData(this.smClass);
        }

        @objid ("db7b08b2-129e-4b46-9718-96996458572a")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactProjectImpl();
        }

    }

    @objid ("f2d8809b-f0b4-4730-b537-2e17d53e9e4f")
    public static class ModelSmDependency extends SmMultipleDependency {
        @objid ("0775c1e4-87d3-444d-ae9f-a4f0deaa1cc9")
        private SmDependency symetricDep;

        @objid ("19a4ec08-bc40-4260-9256-190efc9b2c48")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactProjectData)data).mModel != null)? ((ImpactProjectData)data).mModel:SmMultipleDependency.EMPTY;
        }

        @objid ("9dce7f9d-847b-4991-bebd-27484303f7bb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactProjectData) data).mModel = values;
        }

        @objid ("3240de08-87da-4eef-a6a7-ec3658d82264")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactModelSmClass)this.getTarget()).getProjectDep();
            }
            return this.symetricDep;
        }

    }

}
