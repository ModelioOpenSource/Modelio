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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
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
    @objid ("34e7f721-5bdd-4e00-a122-947fb1c8f269")
    private SmDependency modelDep;

    @objid ("05136748-93b8-408b-9a1b-e6e6d828927d")
    public  ImpactProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("45bb10e9-c825-494b-ba71-e1b344d7890d")
    @Override
    public String getName() {
        return "ImpactProject";
        
    }

    @objid ("9b32f095-a75b-48fd-8431-61fa1e1c706b")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("f9da9d8c-5506-4068-baed-d65ef6e52aca")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactProject.class;
        
    }

    @objid ("9f1d88ba-a03d-4032-8fc2-808dba7fb07c")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("0458b8c5-64b8-41f5-b4e5-cb2988cc83b4")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ea0478cc-5c95-494b-9a00-dc7fb4f79c91")
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

    @objid ("bf4949c3-c97a-484a-b5ce-0c811fbf0c36")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("model");
        }
        return this.modelDep;
    }

    @objid ("8688a755-1ddb-41f9-87ae-191ef54c9d9a")
    @Override
    public boolean areOrphansAllowed() {
        return true;
        
    }

    @objid ("69fdc5dd-733a-4fc8-ba42-f9540d8b3989")
    private static class ImpactProjectObjectFactory implements ISmObjectFactory {
        @objid ("2162b005-7b1d-4faf-9f80-56e732ca6874")
        private ImpactProjectSmClass smClass;

        @objid ("85c2579b-0528-44aa-b514-ebda8bebc973")
        public  ImpactProjectObjectFactory(ImpactProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("54498bee-6ad6-49ad-a273-4bfce3798f6c")
        @Override
        public ISmObjectData createData() {
            return new ImpactProjectData(this.smClass);
        }

        @objid ("63894d97-5822-40d4-b20c-8529a56dd1b9")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactProjectImpl();
        }

    }

    @objid ("f2d8809b-f0b4-4730-b537-2e17d53e9e4f")
    public static class ModelSmDependency extends SmMultipleDependency {
        @objid ("dc68b333-6b29-4b65-88b4-5120fd497b7c")
        private SmDependency symetricDep;

        @objid ("cea3403e-b83e-4885-b9f1-e4e53259259c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactProjectData)data).mModel != null)? ((ImpactProjectData)data).mModel:SmMultipleDependency.EMPTY;
        }

        @objid ("19835a66-b5dc-4595-85aa-85540174cb40")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactProjectData) data).mModel = values;
            
        }

        @objid ("5e807c09-8ac3-42b4-ba42-693ef254657f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactModelSmClass)this.getTarget()).getProjectDep();
            }
            return this.symetricDep;
            
        }

    }

}
