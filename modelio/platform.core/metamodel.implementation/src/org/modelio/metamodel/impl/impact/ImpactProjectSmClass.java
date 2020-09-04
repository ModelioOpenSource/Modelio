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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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
    @objid ("22bc157f-714e-44ea-99b4-133f0ff2aecb")
    private SmDependency modelDep;

    @objid ("3e9ec433-cea5-485f-9569-daa990259f7e")
    public ImpactProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5be53929-68b5-4aed-8fff-06f5372d92c2")
    @Override
    public String getName() {
        return "ImpactProject";
    }

    @objid ("6869d745-baa0-488d-a76c-efb683888d90")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("98276f4f-0b27-48d5-b5c0-48ad9011ca30")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactProject.class;
    }

    @objid ("01f64073-9a2c-4cea-888d-72385acc391e")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("0c76b6f8-1121-4e34-9b37-d76226136f33")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("fff42047-3f2b-45db-ade4-2066f64100d5")
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

    @objid ("64fc5fc3-ecd6-4b83-9659-84afda7a8c02")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("model");
        }
        return this.modelDep;
    }

    @objid ("4e278f73-87b1-4141-b3bc-168264df14f9")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("69fdc5dd-733a-4fc8-ba42-f9540d8b3989")
    private static class ImpactProjectObjectFactory implements ISmObjectFactory {
        @objid ("5c0aad83-f6fa-42de-befd-d878bddef64d")
        private ImpactProjectSmClass smClass;

        @objid ("116059dc-20f2-4913-ae8d-bf26131eb5c1")
        public ImpactProjectObjectFactory(ImpactProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a26c4c07-2851-4929-9205-5192270254c9")
        @Override
        public ISmObjectData createData() {
            return new ImpactProjectData(this.smClass);
        }

        @objid ("2b445655-7103-4c3a-b9d3-7e771f67e23d")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactProjectImpl();
        }

    }

    @objid ("f2d8809b-f0b4-4730-b537-2e17d53e9e4f")
    public static class ModelSmDependency extends SmMultipleDependency {
        @objid ("5a563d7c-bfca-49bb-882a-8790720aa460")
        private SmDependency symetricDep;

        @objid ("515d1b40-6238-4b6c-a0ae-2c6ff5cbb3fb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactProjectData)data).mModel != null)? ((ImpactProjectData)data).mModel:SmMultipleDependency.EMPTY;
        }

        @objid ("30c2dbeb-efde-4b6b-8e8e-f308a99538e3")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactProjectData) data).mModel = values;
        }

        @objid ("a533ddaa-35c7-4158-b4df-18f761c0a1c2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactModelSmClass)this.getTarget()).getProjectDep();
            }
            return this.symetricDep;
        }

    }

}
