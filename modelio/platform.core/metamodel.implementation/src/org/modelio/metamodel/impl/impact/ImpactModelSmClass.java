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
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.impl.impact.ImpactLinkSmClass;
import org.modelio.metamodel.impl.impact.ImpactModelData;
import org.modelio.metamodel.impl.impact.ImpactProjectSmClass;
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
    @objid ("b451e8ec-335d-4026-89a6-06fbd7c1e951")
    private SmDependency projectDep;

    @objid ("c20b4f8d-a2d7-47d9-8b9e-cf99aad98632")
    private SmDependency ownedLinksDep;

    @objid ("c83665bf-d0f9-461f-806e-ad1f7c57522a")
    public ImpactModelSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("dc28ad1c-c063-47ea-b91d-2ab73fba64bc")
    @Override
    public String getName() {
        return "ImpactModel";
    }

    @objid ("16c9ff1c-3391-4265-b4c6-263b4824296d")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("11e7acdf-dd32-4e72-8de4-a770a97d6baa")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactModel.class;
    }

    @objid ("8b806906-58b8-4ed4-9dd7-0a7d23cdeb83")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("1ef7eb0b-5437-4f9f-a5bf-4cdc0f598977")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("00a84185-6dc4-496a-b430-9d8f7a6fc9b9")
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

    @objid ("d39a070f-a83d-4c50-a04b-045ee6cc515c")
    public SmDependency getProjectDep() {
        if (this.projectDep == null) {
        	this.projectDep = this.getDependencyDef("project");
        }
        return this.projectDep;
    }

    @objid ("041654e7-69d0-4661-af58-d4e835830428")
    public SmDependency getOwnedLinksDep() {
        if (this.ownedLinksDep == null) {
        	this.ownedLinksDep = this.getDependencyDef("ownedLinks");
        }
        return this.ownedLinksDep;
    }

    @objid ("6ad072f8-a75b-4975-bee3-d7a4313d1c08")
    private static class ImpactModelObjectFactory implements ISmObjectFactory {
        @objid ("71acc13f-5f81-4c1a-ab7c-4fd5e920883f")
        private ImpactModelSmClass smClass;

        @objid ("9fe5a0f9-87bd-49d4-a966-c62e60ab6f07")
        public ImpactModelObjectFactory(ImpactModelSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("424d0a12-2b47-4554-b0f3-611c20990476")
        @Override
        public ISmObjectData createData() {
            return new ImpactModelData(this.smClass);
        }

        @objid ("1fe188fc-5161-43b8-93df-c815ae188461")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactModelImpl();
        }

    }

    @objid ("91ddafd3-c54c-45c5-8d46-8f11e227b3c0")
    public static class ProjectSmDependency extends SmSingleDependency {
        @objid ("7fa04db3-2876-4744-b8c8-2ba29b8d039a")
        private SmDependency symetricDep;

        @objid ("2ebc35ee-a6f3-49cf-a978-3404940a401a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactModelData) data).mProject;
        }

        @objid ("842432eb-62b6-436a-b825-0421047d69e4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactModelData) data).mProject = value;
        }

        @objid ("42551fdc-6b44-4ae1-8bd5-291051bb848a")
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
        @objid ("542a1952-881b-4a3a-8581-e9d46b4e89c9")
        private SmDependency symetricDep;

        @objid ("00933973-45e4-4220-98c7-2c32b8014e0a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactModelData)data).mOwnedLinks != null)? ((ImpactModelData)data).mOwnedLinks:SmMultipleDependency.EMPTY;
        }

        @objid ("f8e69141-0a7b-4ffa-97c2-d4a3be08f4f8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactModelData) data).mOwnedLinks = values;
        }

        @objid ("08e2a622-74e1-4808-82a2-134bfbffa230")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
