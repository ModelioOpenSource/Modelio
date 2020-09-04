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
import org.modelio.metamodel.impl.uml.statik.TemplateBindingSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSubstitutionData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("4be06bc1-c4cd-476a-beed-ac5652682111")
public class TemplateParameterSubstitutionSmClass extends UmlModelElementSmClass {
    @objid ("2f235626-6bb5-4495-bcd4-2db170124e3a")
    private SmAttribute valueAtt;

    @objid ("9a17f72b-783e-4aa6-beb1-5d46615fb20f")
    private SmDependency ownerDep;

    @objid ("6fc31449-ad45-403c-8e5d-63f39c109f85")
    private SmDependency actualDep;

    @objid ("668d97ad-4da5-4aa8-a04a-8404c982291f")
    private SmDependency formalParameterDep;

    @objid ("a496d087-0a30-4583-9dd9-b43d899bd4e6")
    public TemplateParameterSubstitutionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("07f35351-9f12-4e41-8702-e398807bff9b")
    @Override
    public String getName() {
        return "TemplateParameterSubstitution";
    }

    @objid ("7e656be2-68ab-4859-a7b3-c33f2f5410c7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c7a6be25-d7a0-4a34-8211-a4ed66b58ac0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TemplateParameterSubstitution.class;
    }

    @objid ("3b06991f-5b33-4f0e-a04c-95bfc21ee83f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("71e94a98-2b9b-49f2-a297-b4c6fb6945a8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("883ab253-d26d-469e-ad2f-e61648d2b62c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new TemplateParameterSubstitutionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.valueAtt = new ValueSmAttribute();
        this.valueAtt.init("Value", this, String.class );
        registerAttribute(this.valueAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(TemplateBinding.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        this.actualDep = new ActualSmDependency();
        this.actualDep.init("Actual", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.actualDep);
        
        this.formalParameterDep = new FormalParameterSmDependency();
        this.formalParameterDep.init("FormalParameter", this, metamodel.getMClass(TemplateParameter.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.formalParameterDep);
    }

    @objid ("bf696bae-58ac-41f9-a5ac-214f0a9fb5cf")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("ffc9dc02-f97c-401d-a88f-5b330ca5037a")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("3cf89d76-4537-498d-95e1-1a6fd5f33a16")
    public SmDependency getActualDep() {
        if (this.actualDep == null) {
        	this.actualDep = this.getDependencyDef("Actual");
        }
        return this.actualDep;
    }

    @objid ("583ee72a-5153-41d7-a33c-3f2ea096ee66")
    public SmDependency getFormalParameterDep() {
        if (this.formalParameterDep == null) {
        	this.formalParameterDep = this.getDependencyDef("FormalParameter");
        }
        return this.formalParameterDep;
    }

    @objid ("589a4bd2-962b-4f44-843f-26743d79dd04")
    private static class TemplateParameterSubstitutionObjectFactory implements ISmObjectFactory {
        @objid ("3486c349-6386-473c-b343-58f0adeb1ad6")
        private TemplateParameterSubstitutionSmClass smClass;

        @objid ("67708d76-3585-4144-bd88-9e967a11e51f")
        public TemplateParameterSubstitutionObjectFactory(TemplateParameterSubstitutionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("22376251-7acf-4f44-a0b0-e055a7f72dfb")
        @Override
        public ISmObjectData createData() {
            return new TemplateParameterSubstitutionData(this.smClass);
        }

        @objid ("12fd1a7c-2d01-436a-b688-c1722fa43845")
        @Override
        public SmObjectImpl createImpl() {
            return new TemplateParameterSubstitutionImpl();
        }

    }

    @objid ("a8b27f99-56d1-45d3-9310-37cc0352dbd9")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("50bf8145-6cd3-4ac9-aca1-2bc724a27e42")
        public Object getValue(ISmObjectData data) {
            return ((TemplateParameterSubstitutionData) data).mValue;
        }

        @objid ("a5c4da78-3dc2-431e-a666-c22120e0012b")
        public void setValue(ISmObjectData data, Object value) {
            ((TemplateParameterSubstitutionData) data).mValue = value;
        }

    }

    @objid ("853891e5-b3d6-45df-8be1-7e6d12ffd916")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("8d327c98-e1b8-4ebd-b785-165f6344dc58")
        private SmDependency symetricDep;

        @objid ("3d1f5e0e-f6de-4851-9943-bb545be3a99e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterSubstitutionData) data).mOwner;
        }

        @objid ("9c2cca41-2199-4003-98c3-593f477428f0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterSubstitutionData) data).mOwner = value;
        }

        @objid ("bf0c144c-a048-4052-8ca8-74a81030789c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateBindingSmClass)this.getTarget()).getParameterSubstitutionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("31665b4f-4977-48e2-9794-2279b0a17488")
    public static class ActualSmDependency extends SmSingleDependency {
        @objid ("483f763f-e46b-49db-940f-caffb4f216d8")
        private SmDependency symetricDep;

        @objid ("214b8593-943a-4124-8ce3-b44291ac3fc0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterSubstitutionData) data).mActual;
        }

        @objid ("e680874d-a5fb-4098-a6b7-4c644ac0c8c2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterSubstitutionData) data).mActual = value;
        }

        @objid ("c2cf0a39-8ae0-470d-8ab5-363e06726926")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getTemplateSubstitutionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("fbfa80a2-f7b7-4a05-9c02-f3ecbef64e19")
    public static class FormalParameterSmDependency extends SmSingleDependency {
        @objid ("5c7c8aeb-c3d9-4eb3-917d-0cf6ee07b883")
        private SmDependency symetricDep;

        @objid ("6a9858fa-c25a-4dd8-9144-b56632fece6e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterSubstitutionData) data).mFormalParameter;
        }

        @objid ("ee7bc7f6-9784-478b-86ce-e14d64fd901a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterSubstitutionData) data).mFormalParameter = value;
        }

        @objid ("8b2b0917-2164-477a-ac7f-9dcd12e6d8e4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSmClass)this.getTarget()).getParameterSubstitutionDep();
            }
            return this.symetricDep;
        }

    }

}
