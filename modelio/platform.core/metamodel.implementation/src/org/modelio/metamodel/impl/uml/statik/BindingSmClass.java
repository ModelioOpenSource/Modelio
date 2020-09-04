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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.BindingData;
import org.modelio.metamodel.impl.uml.statik.CollaborationUseSmClass;
import org.modelio.metamodel.impl.uml.statik.ConnectorEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryConnectorSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;
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

@objid ("3652073c-5639-419d-bc73-dfae88421d53")
public class BindingSmClass extends UmlModelElementSmClass {
    @objid ("054a76e3-7a99-4288-bb3f-31f4931f9d55")
    private SmDependency connectorEndRoleDep;

    @objid ("75dd8894-690c-46ab-8873-37a31d8551ff")
    private SmDependency connectorRoleDep;

    @objid ("c3083d34-667c-4a44-81cd-a1c0b16c67d6")
    private SmDependency roleDep;

    @objid ("274e64a1-a859-4c7f-83d5-977f85b7807b")
    private SmDependency representedFeatureDep;

    @objid ("fd8e1092-ef67-4ca4-814f-88abdc809c53")
    private SmDependency ownerDep;

    @objid ("72fc8162-1633-4ec8-880b-6d808c469851")
    public BindingSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1574b048-12c7-43d8-87f9-b32afd0bdf9e")
    @Override
    public String getName() {
        return "Binding";
    }

    @objid ("09a3dc4f-0683-4a09-acc8-8900b220a6ee")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("494c9eac-0753-4264-bf8e-968b2bcfdeb4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Binding.class;
    }

    @objid ("029cedfb-6a4a-44d1-9ca6-f20ce30c4cff")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("153589a1-8fee-4b12-8a9e-6605a0cc9177")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0fbd833c-f197-474e-b655-b231a11a5485")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new BindingObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.connectorEndRoleDep = new ConnectorEndRoleSmDependency();
        this.connectorEndRoleDep.init("ConnectorEndRole", this, metamodel.getMClass(ConnectorEnd.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.connectorEndRoleDep);
        
        this.connectorRoleDep = new ConnectorRoleSmDependency();
        this.connectorRoleDep.init("ConnectorRole", this, metamodel.getMClass(NaryConnector.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.connectorRoleDep);
        
        this.roleDep = new RoleSmDependency();
        this.roleDep.init("Role", this, metamodel.getMClass(BindableInstance.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.roleDep);
        
        this.representedFeatureDep = new RepresentedFeatureSmDependency();
        this.representedFeatureDep.init("RepresentedFeature", this, metamodel.getMClass(UmlModelElement.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedFeatureDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(CollaborationUse.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("1648d93d-175d-4bd0-903b-6eeb2666654f")
    public SmDependency getConnectorEndRoleDep() {
        if (this.connectorEndRoleDep == null) {
        	this.connectorEndRoleDep = this.getDependencyDef("ConnectorEndRole");
        }
        return this.connectorEndRoleDep;
    }

    @objid ("9a5a5ade-739c-40f8-9b96-183a88a17f87")
    public SmDependency getConnectorRoleDep() {
        if (this.connectorRoleDep == null) {
        	this.connectorRoleDep = this.getDependencyDef("ConnectorRole");
        }
        return this.connectorRoleDep;
    }

    @objid ("dfd4ddbf-67ad-4ed2-9f7d-c03fc2c85e8f")
    public SmDependency getRoleDep() {
        if (this.roleDep == null) {
        	this.roleDep = this.getDependencyDef("Role");
        }
        return this.roleDep;
    }

    @objid ("833b8693-fe60-453a-a2da-7e556eb9d636")
    public SmDependency getRepresentedFeatureDep() {
        if (this.representedFeatureDep == null) {
        	this.representedFeatureDep = this.getDependencyDef("RepresentedFeature");
        }
        return this.representedFeatureDep;
    }

    @objid ("2eab51e1-2877-4db0-a888-d609cac6d13f")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("7c2e7ba9-5331-44fb-a2d1-6aeb7bd0af8c")
    private static class BindingObjectFactory implements ISmObjectFactory {
        @objid ("da0f14bd-d8bf-4d1b-a7a4-e24a9de3c59a")
        private BindingSmClass smClass;

        @objid ("7afc1b2e-5f63-402c-b4cb-ee4d0c3916a3")
        public BindingObjectFactory(BindingSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("04b1b83c-6c25-45a6-8cc4-f6de212826a2")
        @Override
        public ISmObjectData createData() {
            return new BindingData(this.smClass);
        }

        @objid ("c232fe9c-da3f-419a-8734-141d9e0658ce")
        @Override
        public SmObjectImpl createImpl() {
            return new BindingImpl();
        }

    }

    @objid ("176837b3-990c-4ae3-bce8-e11914227802")
    public static class ConnectorEndRoleSmDependency extends SmSingleDependency {
        @objid ("0038671a-69e2-4e90-9d44-fede9ac7f257")
        private SmDependency symetricDep;

        @objid ("31f389a0-4edc-40bd-a5d3-565597c63c9a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindingData) data).mConnectorEndRole;
        }

        @objid ("a6944859-a15c-4797-9b66-c24b2b62c59b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindingData) data).mConnectorEndRole = value;
        }

        @objid ("3216e42f-cbba-484c-826a-fa8bb5f32a4f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConnectorEndSmClass)this.getTarget()).getRepresentationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("10ab29c3-3873-4e2b-a0a1-84ed0df4d76a")
    public static class ConnectorRoleSmDependency extends SmSingleDependency {
        @objid ("bb1f373b-4a5f-441f-a193-ddc1c257e481")
        private SmDependency symetricDep;

        @objid ("523985ad-31e3-443f-b9f0-310b7cdbc952")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindingData) data).mConnectorRole;
        }

        @objid ("472184b6-5bd9-4b27-aa47-2da8204fa87e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindingData) data).mConnectorRole = value;
        }

        @objid ("ecbda4f3-399f-420e-ba88-27682b6a35df")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryConnectorSmClass)this.getTarget()).getRepresentationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5cd327e7-5172-4142-9e97-3d461a164432")
    public static class RoleSmDependency extends SmSingleDependency {
        @objid ("d47a258b-2bd6-4419-ac16-f7c5b37bac81")
        private SmDependency symetricDep;

        @objid ("a913e2cd-d64d-4947-be04-2dcd2afb6c9b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindingData) data).mRole;
        }

        @objid ("757b889b-11d3-43e8-8d8a-99305d2cc69d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindingData) data).mRole = value;
        }

        @objid ("4361c70d-627d-4571-80bf-89ecc0a19e88")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindableInstanceSmClass)this.getTarget()).getRepresentationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("cccad6aa-a50c-4203-b53d-43693557ab87")
    public static class RepresentedFeatureSmDependency extends SmSingleDependency {
        @objid ("bbb5a600-9293-4a0a-8ead-0f6a4ea3aeb1")
        private SmDependency symetricDep;

        @objid ("d933653d-a28a-49c3-90fb-ed0318393695")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindingData) data).mRepresentedFeature;
        }

        @objid ("12563224-ed2d-4de3-8c65-2be29d3578bf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindingData) data).mRepresentedFeature = value;
        }

        @objid ("4250aeca-b879-4b70-afdc-8c506633f267")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getRepresentsDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0e1cb824-4a4f-4b23-8a9f-34b475747f70")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("dc03cf9d-2c07-428f-b568-bbd30a589613")
        private SmDependency symetricDep;

        @objid ("b2bbc0d8-1f95-4f7e-886e-5d37082c917a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindingData) data).mOwner;
        }

        @objid ("5a55e4bb-2f58-4c55-bfa7-be17ae9f70ae")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindingData) data).mOwner = value;
        }

        @objid ("7480bd3f-8c4f-498d-8d21-162ee41d869c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationUseSmClass)this.getTarget()).getRoleBindingDep();
            }
            return this.symetricDep;
        }

    }

}
