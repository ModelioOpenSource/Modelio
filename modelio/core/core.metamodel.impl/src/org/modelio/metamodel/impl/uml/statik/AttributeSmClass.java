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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeData;
import org.modelio.metamodel.impl.uml.statik.AttributeLinkSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.StructuralFeatureSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("2f1be0bc-d6ac-4f7e-87ff-4cf52bbe2a87")
public class AttributeSmClass extends StructuralFeatureSmClass {
    @objid ("c6f124d2-6901-4ce3-a60a-b802f98c8b16")
    private SmAttribute typeConstraintAtt;

    @objid ("96c3af1b-2c0b-4b12-891c-2d32ec99be6f")
    private SmAttribute valueAtt;

    @objid ("b11754ee-84d2-4170-abe7-bc7f2e567860")
    private SmAttribute targetIsClassAtt;

    @objid ("7705d9e2-149e-4cd3-b664-9d67151af610")
    private SmDependency typeDep;

    @objid ("f13e6b8a-9f8f-4005-a2d9-aede56d3c00d")
    private SmDependency ownerDep;

    @objid ("06e10a0b-c90b-4fb6-9bbb-033e42824a7b")
    private SmDependency occurenceDep;

    @objid ("969b2a10-082e-4162-9444-9be3717f33ce")
    private SmDependency representingObjectNodeDep;

    @objid ("13a574e3-47c7-478d-9917-24596fb26843")
    private SmDependency qualifiedDep;

    @objid ("93f57037-97fb-4066-8c10-0a6b3883ce13")
    public AttributeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6031a0b9-01dc-4a40-a247-e89660c46800")
    @Override
    public String getName() {
        return "Attribute";
    }

    @objid ("4c8317d8-08fd-41f0-abbe-9d1fd3d9c863")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2ee47232-e367-40c3-9484-f1d6e82ea731")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Attribute.class;
    }

    @objid ("891d0766-dd71-4960-b6e5-4f9e5579d549")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e7641107-8eeb-4852-9d2c-7dd3d580c1fe")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f7d67a98-83f9-4c3f-be0d-47fd110cbce6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StructuralFeature.MQNAME);
        this.registerFactory(new AttributeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.typeConstraintAtt = new TypeConstraintSmAttribute();
        this.typeConstraintAtt.init("TypeConstraint", this, String.class );
        registerAttribute(this.typeConstraintAtt);
        
        this.valueAtt = new ValueSmAttribute();
        this.valueAtt.init("Value", this, String.class );
        registerAttribute(this.valueAtt);
        
        this.targetIsClassAtt = new TargetIsClassSmAttribute();
        this.targetIsClassAtt.init("TargetIsClass", this, Boolean.class );
        registerAttribute(this.targetIsClassAtt);
        
        
        // Initialize and register the SmDependency
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(GeneralClass.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Classifier.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.occurenceDep = new OccurenceSmDependency();
        this.occurenceDep.init("Occurence", this, metamodel.getMClass(AttributeLink.MQNAME), 0, -1 );
        registerDependency(this.occurenceDep);
        
        this.representingObjectNodeDep = new RepresentingObjectNodeSmDependency();
        this.representingObjectNodeDep.init("RepresentingObjectNode", this, metamodel.getMClass(ObjectNode.MQNAME), 0, -1 );
        registerDependency(this.representingObjectNodeDep);
        
        this.qualifiedDep = new QualifiedSmDependency();
        this.qualifiedDep.init("Qualified", this, metamodel.getMClass(AssociationEnd.MQNAME), 0, 1 );
        registerDependency(this.qualifiedDep);
    }

    @objid ("8c09022b-f21b-4a11-a0b1-975505f3b2df")
    public SmAttribute getTypeConstraintAtt() {
        if (this.typeConstraintAtt == null) {
        	this.typeConstraintAtt = this.getAttributeDef("TypeConstraint");
        }
        return this.typeConstraintAtt;
    }

    @objid ("904f1462-fa65-4e5a-b54d-fa9ab86fe710")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("e0675ae8-ca40-41a2-b70d-81c2bf3e28ad")
    public SmAttribute getTargetIsClassAtt() {
        if (this.targetIsClassAtt == null) {
        	this.targetIsClassAtt = this.getAttributeDef("TargetIsClass");
        }
        return this.targetIsClassAtt;
    }

    @objid ("c2c2af10-7951-4e55-a6bd-84b639a90518")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("50e215a2-132f-4fd1-800a-4564d1c5f019")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("4626fed0-e9d6-4b90-8749-05b3f3522cbd")
    public SmDependency getOccurenceDep() {
        if (this.occurenceDep == null) {
        	this.occurenceDep = this.getDependencyDef("Occurence");
        }
        return this.occurenceDep;
    }

    @objid ("0956ad1c-3d98-4095-89cc-1b1e813c2c46")
    public SmDependency getRepresentingObjectNodeDep() {
        if (this.representingObjectNodeDep == null) {
        	this.representingObjectNodeDep = this.getDependencyDef("RepresentingObjectNode");
        }
        return this.representingObjectNodeDep;
    }

    @objid ("15216a61-5ad8-475a-a159-ca9debff225b")
    public SmDependency getQualifiedDep() {
        if (this.qualifiedDep == null) {
        	this.qualifiedDep = this.getDependencyDef("Qualified");
        }
        return this.qualifiedDep;
    }

    @objid ("9a80f0bc-70c7-47b4-b14c-5f3afbff35fa")
    private static class AttributeObjectFactory implements ISmObjectFactory {
        @objid ("00b008a2-6bd8-4655-b3a6-54a004fc89ca")
        private AttributeSmClass smClass;

        @objid ("6840009e-96ad-4a9d-9d53-03ec5f4e69e6")
        public AttributeObjectFactory(AttributeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ff2113d6-fbf1-4ec4-aa3c-e1ee1d75dddb")
        @Override
        public ISmObjectData createData() {
            return new AttributeData(this.smClass);
        }

        @objid ("290c9aa0-6ad6-410c-9f8b-7f1fa2dce9ef")
        @Override
        public SmObjectImpl createImpl() {
            return new AttributeImpl();
        }

    }

    @objid ("41ca74dd-0997-41d6-8fd8-ce3170dd3dd3")
    public static class TypeConstraintSmAttribute extends SmAttribute {
        @objid ("b484346b-0dd6-436d-8e68-2b876b89d156")
        public Object getValue(ISmObjectData data) {
            return ((AttributeData) data).mTypeConstraint;
        }

        @objid ("4c2a7c92-d5ab-4e87-a6b6-69eed765c486")
        public void setValue(ISmObjectData data, Object value) {
            ((AttributeData) data).mTypeConstraint = value;
        }

    }

    @objid ("48c2acb0-3a7d-4c24-a6bb-015f84be077a")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("d62a8a34-d412-43d6-877c-6dbd628887f2")
        public Object getValue(ISmObjectData data) {
            return ((AttributeData) data).mValue;
        }

        @objid ("7f074159-0d2a-46c0-8a62-5b616717c964")
        public void setValue(ISmObjectData data, Object value) {
            ((AttributeData) data).mValue = value;
        }

    }

    @objid ("d3f5be59-3d87-4541-912b-b9fddf024dfb")
    public static class TargetIsClassSmAttribute extends SmAttribute {
        @objid ("e55cc52f-f2a5-44fc-8ca7-c7720575183d")
        public Object getValue(ISmObjectData data) {
            return ((AttributeData) data).mTargetIsClass;
        }

        @objid ("4d83b814-6cb2-442b-b5bc-f60ee8098b10")
        public void setValue(ISmObjectData data, Object value) {
            ((AttributeData) data).mTargetIsClass = value;
        }

    }

    @objid ("8897271b-c0f3-4ad2-92f9-a21d04cebcb0")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("edf00dd3-59e5-46b8-afc9-6af4c9fbe5c7")
        private SmDependency symetricDep;

        @objid ("bb2fc2dc-aa48-4ce2-9205-09965e91a8a4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AttributeData) data).mType;
        }

        @objid ("ceb6b049-0b88-4223-b0ad-bdd232ba3f31")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AttributeData) data).mType = value;
        }

        @objid ("2e8fb63d-8812-4ee6-b054-cba83afc8421")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralClassSmClass)this.getTarget()).getObjectDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e7c37e2c-4cb8-4613-b6ab-1e31af183544")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("ff186701-8606-4f34-97cd-0689f3db031e")
        private SmDependency symetricDep;

        @objid ("e4cd46eb-3fd4-42a3-832d-6d8eba5bc0d6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AttributeData) data).mOwner;
        }

        @objid ("f4531fb2-ab4f-4575-b930-ba9977305b35")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AttributeData) data).mOwner = value;
        }

        @objid ("d7a6ce7d-984b-45a0-8e4d-7f2bc84795a6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getOwnedAttributeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9ec16a8b-66b6-4af3-b1c3-95bd19cd7f13")
    public static class OccurenceSmDependency extends SmMultipleDependency {
        @objid ("e78c06bc-1115-4e81-aeff-a72107f5ba5a")
        private SmDependency symetricDep;

        @objid ("a6690d02-74e7-452b-8653-fba0e5d15e4b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AttributeData)data).mOccurence != null)? ((AttributeData)data).mOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("293b23a7-b45b-4a1e-a57a-f16e6724d441")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AttributeData) data).mOccurence = values;
        }

        @objid ("7258fdc4-8967-4fbb-a6bc-88ea46458ef8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeLinkSmClass)this.getTarget()).getBaseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9a711e99-228a-4ccb-8ad5-d2d6edba48b7")
    public static class RepresentingObjectNodeSmDependency extends SmMultipleDependency {
        @objid ("2955afcb-9aea-4eea-ac0c-1b34be683185")
        private SmDependency symetricDep;

        @objid ("99e2a91a-706c-40fd-a957-95a280bbcb37")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AttributeData)data).mRepresentingObjectNode != null)? ((AttributeData)data).mRepresentingObjectNode:SmMultipleDependency.EMPTY;
        }

        @objid ("2faa2a64-7c09-44f7-9ce5-215cace4f2e3")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AttributeData) data).mRepresentingObjectNode = values;
        }

        @objid ("250677a3-a1ba-4398-9475-510cef578c5e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ObjectNodeSmClass)this.getTarget()).getRepresentedAttributeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("68f08dd2-65f1-4bc0-8c89-d49a1177025b")
    public static class QualifiedSmDependency extends SmSingleDependency {
        @objid ("aac1571d-36f8-4ea6-add9-09d670a15b18")
        private SmDependency symetricDep;

        @objid ("553ce51c-a33c-441e-8176-f5a26f8af9e3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AttributeData) data).mQualified;
        }

        @objid ("98b77ea6-8f40-4ad2-8d84-85e55757bddf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AttributeData) data).mQualified = value;
        }

        @objid ("15ab9976-1fe9-4367-8b30-8dc30876794c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getQualifierDep();
            }
            return this.symetricDep;
        }

    }

}
