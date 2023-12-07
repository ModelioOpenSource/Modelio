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
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("976546ff-9a0d-424d-9e1f-27a870d9ea1d")
public class ImpactModelSmClass extends ModelElementSmClass {
    @objid ("ebb8c20c-1f5f-47e6-889d-a19e12c626e6")
    private SmDependency projectDep;

    @objid ("46bc58cd-51e2-46f1-ae2a-8fa83621fec5")
    private SmDependency ownedLinksDep;

    @objid ("bbe3a397-023e-42fb-adf7-8afedec61a8b")
    public  ImpactModelSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9ecccbf3-1811-418d-a427-f3c9e2d3b612")
    @Override
    public String getName() {
        return "ImpactModel";
        
    }

    @objid ("85378810-e7da-4f43-b2a4-475db42317f2")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("1ab30fba-4f1a-4986-8d15-a1baf7811ca0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactModel.class;
        
    }

    @objid ("1927cf55-7fd2-4a01-b163-a11e9c79cd72")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("ca924a75-ddd7-40b2-837d-5c89aad0e892")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("8b537a4b-8354-4b39-a267-15562ff5eb60")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ImpactModelObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.projectDep = new ProjectSmDependency();
        this.projectDep.init("project", this, metamodel.getMClass(ImpactProject.MQNAME), 1, 1 );
        registerDependency(this.projectDep);
        
        this.ownedLinksDep = new OwnedLinksSmDependency();
        this.ownedLinksDep.init("ownedLinks", this, metamodel.getMClass(ImpactLink.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC, SmDirective.SMCDTODELETE);
        registerDependency(this.ownedLinksDep);
        
        
    }

    @objid ("e3d1e976-4c49-4ae5-aeba-71088ba9e0a6")
    public SmDependency getProjectDep() {
        if (this.projectDep == null) {
        	this.projectDep = this.getDependencyDef("project");
        }
        return this.projectDep;
    }

    @objid ("63014da9-f2be-4865-b517-a8f5f98a1070")
    public SmDependency getOwnedLinksDep() {
        if (this.ownedLinksDep == null) {
        	this.ownedLinksDep = this.getDependencyDef("ownedLinks");
        }
        return this.ownedLinksDep;
    }

    @objid ("6ad072f8-a75b-4975-bee3-d7a4313d1c08")
    private static class ImpactModelObjectFactory implements ISmObjectFactory {
        @objid ("9ed501b9-765c-426f-adf6-8ddd61f85bb8")
        private ImpactModelSmClass smClass;

        @objid ("567f3248-e0b5-42e7-a0c5-c67865e3fa4f")
        public  ImpactModelObjectFactory(ImpactModelSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1aaa0502-d043-4200-bc62-860e29179ef3")
        @Override
        public ISmObjectData createData() {
            return new ImpactModelData(this.smClass);
        }

        @objid ("22cf299b-62b4-4776-ba7b-239f9d873b2d")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactModelImpl();
        }

    }

    @objid ("91ddafd3-c54c-45c5-8d46-8f11e227b3c0")
    public static class ProjectSmDependency extends SmSingleDependency {
        @objid ("8f1d83e2-f77f-467e-9832-0e908eb8c3ae")
        private SmDependency symetricDep;

        @objid ("d4dcf215-7fe1-473a-ae28-9e2472380ed5")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactModelData) data).mProject;
        }

        @objid ("9fe2188a-0f73-4814-99c2-46b3d291b1d9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactModelData) data).mProject = value;
        }

        @objid ("8a3aaa5c-3aea-4db6-8cf3-504812c3302b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactProjectSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("418e6734-3265-4ad7-ae7d-b8354022a8ea")
    public static class OwnedLinksSmDependency extends SmMultipleDependency {
        @objid ("7e9e2391-6518-4238-8216-0f4e5acfd103")
        private SmDependency symetricDep;

        @objid ("e8982e27-7795-4769-b723-f514c8136e2a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactModelData)data).mOwnedLinks != null)? ((ImpactModelData)data).mOwnedLinks:SmMultipleDependency.EMPTY;
        }

        @objid ("d7e70a67-db6f-4251-a933-dfec997e8aac")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactModelData) data).mOwnedLinks = values;
            
        }

        @objid ("4877767c-e3f5-4cff-9049-2db8939bf810")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
