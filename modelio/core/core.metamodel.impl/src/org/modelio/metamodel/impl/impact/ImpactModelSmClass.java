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
    @objid ("c268cb2e-b199-4580-a913-a26317ede514")
    private SmDependency projectDep;

    @objid ("19736be7-d468-41f3-99f2-e0aaad2d9fa8")
    private SmDependency ownedLinksDep;

    @objid ("a3b1b6a1-e9b5-4e3e-a549-d4e583167034")
    public  ImpactModelSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fb3eecf6-8640-48aa-ad95-744226f189be")
    @Override
    public String getName() {
        return "ImpactModel";
        
    }

    @objid ("7e64ab26-6fa3-485b-8d8a-ede17b4e959a")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("82471315-4be9-447f-a533-2076284cc180")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactModel.class;
        
    }

    @objid ("a50fffc8-e3ef-4430-bfdc-cb27af80d330")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("ad5222b2-b0a3-4350-ba70-85d94c16cec5")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b54fdd39-4840-4bfa-91f1-a087cda0c109")
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

    @objid ("b33ddc3c-5fb9-4f0f-8e2f-764817a0d7b4")
    public SmDependency getProjectDep() {
        if (this.projectDep == null) {
        	this.projectDep = this.getDependencyDef("project");
        }
        return this.projectDep;
    }

    @objid ("9377cc87-5390-4443-8edb-f4d31c9e3ecd")
    public SmDependency getOwnedLinksDep() {
        if (this.ownedLinksDep == null) {
        	this.ownedLinksDep = this.getDependencyDef("ownedLinks");
        }
        return this.ownedLinksDep;
    }

    @objid ("6ad072f8-a75b-4975-bee3-d7a4313d1c08")
    private static class ImpactModelObjectFactory implements ISmObjectFactory {
        @objid ("79156c57-6d96-4414-864a-fcfa7813134a")
        private ImpactModelSmClass smClass;

        @objid ("5b681eec-fc11-4b14-9390-260d4f749883")
        public  ImpactModelObjectFactory(ImpactModelSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c246d240-75c0-433f-9848-cece12c2ab43")
        @Override
        public ISmObjectData createData() {
            return new ImpactModelData(this.smClass);
        }

        @objid ("08ed4fff-c52c-4f4b-8267-9c59fce0f4a0")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactModelImpl();
        }

    }

    @objid ("91ddafd3-c54c-45c5-8d46-8f11e227b3c0")
    public static class ProjectSmDependency extends SmSingleDependency {
        @objid ("56a9289b-7941-4d6f-9d38-ffa483225e4a")
        private SmDependency symetricDep;

        @objid ("89687c84-7644-4898-9ad0-fe21eacca53f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactModelData) data).mProject;
        }

        @objid ("67825eb4-423a-4277-8c9f-7ffd29448f98")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactModelData) data).mProject = value;
        }

        @objid ("95d83ff5-4f09-48cb-b099-59a5587ad0eb")
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
        @objid ("8784c452-5e79-4c5f-a425-970d03b8c7e1")
        private SmDependency symetricDep;

        @objid ("d7060017-ca33-403c-b9b1-bda0c2221544")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactModelData)data).mOwnedLinks != null)? ((ImpactModelData)data).mOwnedLinks:SmMultipleDependency.EMPTY;
        }

        @objid ("8e4de1ad-5dfd-4f57-a0b3-ee240ad394eb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactModelData) data).mOwnedLinks = values;
            
        }

        @objid ("009e3bac-9817-4e0c-959f-e68cbd5c2ff6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
