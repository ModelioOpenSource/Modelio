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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationItemSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.SubstitutionSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeSmClass;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierData;
import org.modelio.metamodel.impl.uml.statik.ComponentRealizationSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.RaisedExceptionSmClass;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
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

@objid ("c6d08e12-abc9-48e2-9aad-856b7d6bec01")
public class ClassifierSmClass extends NameSpaceSmClass {
    @objid ("5226318b-f862-439d-b10e-a908750dad71")
    private SmDependency ownedOperationDep;

    @objid ("6d31dc5f-0039-4890-a6fb-8b99be54b58f")
    private SmDependency representationDep;

    @objid ("6611df74-7c01-4204-a74c-91b5ca7a998d")
    private SmDependency substituedDep;

    @objid ("052b99e3-db2b-42b5-8860-1c5d2251d3c1")
    private SmDependency ownedAttributeDep;

    @objid ("f6450e81-b282-46a1-97e3-4774c5f465b5")
    private SmDependency ownedNaryEndDep;

    @objid ("85691f18-69b6-4eee-adcc-85894bf9c1f0")
    private SmDependency conveyerDep;

    @objid ("768182af-b641-4a90-b5ad-86cebe63f96e")
    private SmDependency substitutingSubstitutionDep;

    @objid ("a99855d4-9b0f-474f-86cd-48b3f90b7b51")
    private SmDependency targetingEndDep;

    @objid ("8fd15c7a-584f-41b2-9896-102cad15e62d")
    private SmDependency ownedEndDep;

    @objid ("d6c7a19a-8a7d-4263-9020-29ec6d29992a")
    private SmDependency throwingDep;

    @objid ("9c3a821f-2be3-4bc5-a405-db75ec920b69")
    private SmDependency internalStructureDep;

    @objid ("d28bf2f3-1fd8-41cc-8601-0e3c95df7eb4")
    private SmDependency realizedComponentDep;

    @objid ("4e4c7d7d-5efa-4a74-a34b-da65feceb565")
    public ClassifierSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("83652cea-f9d5-4794-847e-795b840e6d00")
    @Override
    public String getName() {
        return "Classifier";
    }

    @objid ("f76d205e-6051-4a68-8137-2ee36f812ce2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("aac9fdc7-8d8d-44c0-b9e0-281fa1d834cf")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Classifier.class;
    }

    @objid ("fdefd40c-f66c-417b-84cf-45496026dadd")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("615efdc9-243f-46d0-8069-7c2b92bdce2c")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("06bec0ae-426b-4d61-a35a-1947bf1830a4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(NameSpace.MQNAME);
        this.registerFactory(new ClassifierObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownedOperationDep = new OwnedOperationSmDependency();
        this.ownedOperationDep.init("OwnedOperation", this, metamodel.getMClass(Operation.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedOperationDep);
        
        this.representationDep = new RepresentationSmDependency();
        this.representationDep.init("Representation", this, metamodel.getMClass(InformationItem.MQNAME), 0, -1 );
        registerDependency(this.representationDep);
        
        this.substituedDep = new SubstituedSmDependency();
        this.substituedDep.init("Substitued", this, metamodel.getMClass(Substitution.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.substituedDep);
        
        this.ownedAttributeDep = new OwnedAttributeSmDependency();
        this.ownedAttributeDep.init("OwnedAttribute", this, metamodel.getMClass(Attribute.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedAttributeDep);
        
        this.ownedNaryEndDep = new OwnedNaryEndSmDependency();
        this.ownedNaryEndDep.init("OwnedNaryEnd", this, metamodel.getMClass(NaryAssociationEnd.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedNaryEndDep);
        
        this.conveyerDep = new ConveyerSmDependency();
        this.conveyerDep.init("Conveyer", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.conveyerDep);
        
        this.substitutingSubstitutionDep = new SubstitutingSubstitutionSmDependency();
        this.substitutingSubstitutionDep.init("SubstitutingSubstitution", this, metamodel.getMClass(Substitution.MQNAME), 0, -1 );
        registerDependency(this.substitutingSubstitutionDep);
        
        this.targetingEndDep = new TargetingEndSmDependency();
        this.targetingEndDep.init("TargetingEnd", this, metamodel.getMClass(AssociationEnd.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.targetingEndDep);
        
        this.ownedEndDep = new OwnedEndSmDependency();
        this.ownedEndDep.init("OwnedEnd", this, metamodel.getMClass(AssociationEnd.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedEndDep);
        
        this.throwingDep = new ThrowingSmDependency();
        this.throwingDep.init("Throwing", this, metamodel.getMClass(RaisedException.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.throwingDep);
        
        this.internalStructureDep = new InternalStructureSmDependency();
        this.internalStructureDep.init("InternalStructure", this, metamodel.getMClass(BindableInstance.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.internalStructureDep);
        
        this.realizedComponentDep = new RealizedComponentSmDependency();
        this.realizedComponentDep.init("RealizedComponent", this, metamodel.getMClass(ComponentRealization.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.realizedComponentDep);
    }

    @objid ("2c37f8d9-3a5c-471e-9258-251471f80c38")
    public SmDependency getOwnedOperationDep() {
        if (this.ownedOperationDep == null) {
        	this.ownedOperationDep = this.getDependencyDef("OwnedOperation");
        }
        return this.ownedOperationDep;
    }

    @objid ("76bec2e5-33d8-4766-a815-b9f3eb157855")
    public SmDependency getRepresentationDep() {
        if (this.representationDep == null) {
        	this.representationDep = this.getDependencyDef("Representation");
        }
        return this.representationDep;
    }

    @objid ("1506d32a-a1c9-4851-85de-3591dfbd9830")
    public SmDependency getSubstituedDep() {
        if (this.substituedDep == null) {
        	this.substituedDep = this.getDependencyDef("Substitued");
        }
        return this.substituedDep;
    }

    @objid ("d592e3bb-3a5a-4765-9280-8576cd4632f5")
    public SmDependency getOwnedAttributeDep() {
        if (this.ownedAttributeDep == null) {
        	this.ownedAttributeDep = this.getDependencyDef("OwnedAttribute");
        }
        return this.ownedAttributeDep;
    }

    @objid ("57d0c785-04db-4771-8b0c-46900a71d06e")
    public SmDependency getOwnedNaryEndDep() {
        if (this.ownedNaryEndDep == null) {
        	this.ownedNaryEndDep = this.getDependencyDef("OwnedNaryEnd");
        }
        return this.ownedNaryEndDep;
    }

    @objid ("f8525d2d-cf57-4fb2-a7dc-7f0419421f96")
    public SmDependency getConveyerDep() {
        if (this.conveyerDep == null) {
        	this.conveyerDep = this.getDependencyDef("Conveyer");
        }
        return this.conveyerDep;
    }

    @objid ("f035c52e-9bdf-459e-b465-fdb7309ebeaf")
    public SmDependency getSubstitutingSubstitutionDep() {
        if (this.substitutingSubstitutionDep == null) {
        	this.substitutingSubstitutionDep = this.getDependencyDef("SubstitutingSubstitution");
        }
        return this.substitutingSubstitutionDep;
    }

    @objid ("aef9c4be-d8b2-46a2-b4e9-ada8325b90cb")
    public SmDependency getTargetingEndDep() {
        if (this.targetingEndDep == null) {
        	this.targetingEndDep = this.getDependencyDef("TargetingEnd");
        }
        return this.targetingEndDep;
    }

    @objid ("08b940b7-a803-4a32-bb1b-2762802ebc63")
    public SmDependency getOwnedEndDep() {
        if (this.ownedEndDep == null) {
        	this.ownedEndDep = this.getDependencyDef("OwnedEnd");
        }
        return this.ownedEndDep;
    }

    @objid ("de3ea8ee-7c9f-4d3f-bb31-c53d3a6fa8a9")
    public SmDependency getThrowingDep() {
        if (this.throwingDep == null) {
        	this.throwingDep = this.getDependencyDef("Throwing");
        }
        return this.throwingDep;
    }

    @objid ("a07c0fb7-b182-4055-9089-c076a7eb003a")
    public SmDependency getInternalStructureDep() {
        if (this.internalStructureDep == null) {
        	this.internalStructureDep = this.getDependencyDef("InternalStructure");
        }
        return this.internalStructureDep;
    }

    @objid ("a9fb2b8c-8263-4044-8df8-f52bc5e91eb0")
    public SmDependency getRealizedComponentDep() {
        if (this.realizedComponentDep == null) {
        	this.realizedComponentDep = this.getDependencyDef("RealizedComponent");
        }
        return this.realizedComponentDep;
    }

    @objid ("685c54f1-2371-4e8d-bed2-df269cfb7b47")
    private static class ClassifierObjectFactory implements ISmObjectFactory {
        @objid ("69ff09a4-b73f-4cff-8fbb-1401491875e2")
        private ClassifierSmClass smClass;

        @objid ("ce73ff47-a9a9-495f-91ec-fbe072d3cda2")
        public ClassifierObjectFactory(ClassifierSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1b4387f8-4c2b-4197-81dc-e401cca8e89a")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("59609f4e-c940-46da-8f17-5b3fdba86122")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("ef1083a1-245d-4a28-bc2d-02cac01e6333")
    public static class OwnedOperationSmDependency extends SmMultipleDependency {
        @objid ("42d6baf8-7700-4eef-ab53-37e6ff1b10a6")
        private SmDependency symetricDep;

        @objid ("eb6e6b79-6916-4e1c-8bb7-dc334c7afac7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mOwnedOperation != null)? ((ClassifierData)data).mOwnedOperation:SmMultipleDependency.EMPTY;
        }

        @objid ("abd50f0d-105c-4e9f-ae86-b95efe8cdad4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mOwnedOperation = values;
        }

        @objid ("8f031908-991b-4ae6-8b90-64b36d7fed5c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1dba1f3a-1542-4f02-862f-e25814682565")
    public static class RepresentationSmDependency extends SmMultipleDependency {
        @objid ("d0a3aea2-72ed-4f6e-b4f3-18602f9ef106")
        private SmDependency symetricDep;

        @objid ("3a122574-0c51-416b-9553-76f9c3f8d37a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mRepresentation != null)? ((ClassifierData)data).mRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("595f0efb-c99b-4dc5-81df-c9737f7e9fb6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mRepresentation = values;
        }

        @objid ("e4522630-452f-458f-8cf5-db81797da45d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationItemSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2a64a01a-a561-4e14-a820-bb6cbf9fe7c4")
    public static class SubstituedSmDependency extends SmMultipleDependency {
        @objid ("86c70e5c-3991-4291-9053-9d5c83b9d67c")
        private SmDependency symetricDep;

        @objid ("d4322bb7-7637-41a7-95d1-0fd6c5d4ebab")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mSubstitued != null)? ((ClassifierData)data).mSubstitued:SmMultipleDependency.EMPTY;
        }

        @objid ("7c77df2d-58be-4157-a314-f3493cb0db45")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mSubstitued = values;
        }

        @objid ("6951cac7-4ddd-440c-957c-4d05f1bda523")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SubstitutionSmClass)this.getTarget()).getSubstitutingClassifierDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0e1896c2-4d72-482e-9f36-9a60625c795b")
    public static class OwnedAttributeSmDependency extends SmMultipleDependency {
        @objid ("d724ccac-e398-42c3-bcc0-a28afd97fec8")
        private SmDependency symetricDep;

        @objid ("427c90ce-1001-4dab-8934-4ee1c221e194")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mOwnedAttribute != null)? ((ClassifierData)data).mOwnedAttribute:SmMultipleDependency.EMPTY;
        }

        @objid ("7f8e74c3-bef4-4e2d-af5f-f00957649cdc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mOwnedAttribute = values;
        }

        @objid ("c5e396ca-b583-4db9-9c65-56e6ba5002f7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("00ed1591-25bb-45a0-852d-763512aaea5a")
    public static class OwnedNaryEndSmDependency extends SmMultipleDependency {
        @objid ("81bc50bc-a46f-405c-ba60-e9b9cf819a17")
        private SmDependency symetricDep;

        @objid ("2aaff162-87b0-4101-90a3-9a0cd9b65c1e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mOwnedNaryEnd != null)? ((ClassifierData)data).mOwnedNaryEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("ecd1d430-74cf-47e4-b873-bbdd5987301c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mOwnedNaryEnd = values;
        }

        @objid ("15607171-50e0-4f49-86d5-0282e7c59da0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryAssociationEndSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("766999c8-5745-4b32-b382-58d9c73c3614")
    public static class ConveyerSmDependency extends SmMultipleDependency {
        @objid ("c187e170-60ed-4e53-b377-1ed0287a875b")
        private SmDependency symetricDep;

        @objid ("15547ea7-0cb3-4957-85b4-5a407d5b4424")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mConveyer != null)? ((ClassifierData)data).mConveyer:SmMultipleDependency.EMPTY;
        }

        @objid ("bce7f80e-2e8f-481c-810c-05302714d430")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mConveyer = values;
        }

        @objid ("7382f45e-5da9-430a-9169-3271909ad6a3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getConveyedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e506e0ae-fa8b-4e43-9a0d-0ca173d0a686")
    public static class SubstitutingSubstitutionSmDependency extends SmMultipleDependency {
        @objid ("55c810f3-28c9-4b8d-bffb-110bbfe44b1c")
        private SmDependency symetricDep;

        @objid ("a9fd5dd7-a6ff-4786-8fe1-1a958eaa2382")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mSubstitutingSubstitution != null)? ((ClassifierData)data).mSubstitutingSubstitution:SmMultipleDependency.EMPTY;
        }

        @objid ("f0d623ae-8f6c-4806-ac10-2e8c06cabba7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mSubstitutingSubstitution = values;
        }

        @objid ("01b3ac30-ff34-4607-b2b2-00ee77e2ba33")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SubstitutionSmClass)this.getTarget()).getContractDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("383f66b0-471b-4db6-9219-dc17487c2aad")
    public static class TargetingEndSmDependency extends SmMultipleDependency {
        @objid ("c163c07a-88dc-4c8e-8937-26bc5dcaab6c")
        private SmDependency symetricDep;

        @objid ("54c82a92-73f8-40e8-bae8-06f363ff743c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mTargetingEnd != null)? ((ClassifierData)data).mTargetingEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("e98bfc83-44f2-468d-9c98-f84cc3158e54")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mTargetingEnd = values;
        }

        @objid ("c54b7062-ac58-482b-b21a-c1585eb3fae5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getTargetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4cd4a23f-4e3b-40d7-afbc-3982ebb7d722")
    public static class OwnedEndSmDependency extends SmMultipleDependency {
        @objid ("dfb71331-eb7f-4e0a-80a4-1eaaa3b28b76")
        private SmDependency symetricDep;

        @objid ("0150038d-b636-4189-a450-df7a9c5cd1a2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mOwnedEnd != null)? ((ClassifierData)data).mOwnedEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("27857469-1c6f-4699-b012-d4c56dab18c6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mOwnedEnd = values;
        }

        @objid ("3921584d-3eff-4ac4-9d9a-9895926fbfee")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getSourceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("434128d1-5af0-4052-92e0-d7327252d3c5")
    public static class ThrowingSmDependency extends SmMultipleDependency {
        @objid ("01d59fd2-a239-4df1-8a42-c5ec6b133f5e")
        private SmDependency symetricDep;

        @objid ("4352fc1b-1efc-4f49-9189-095633b196ee")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mThrowing != null)? ((ClassifierData)data).mThrowing:SmMultipleDependency.EMPTY;
        }

        @objid ("586c1c7c-b3fc-4f99-87ba-7082fd1e9726")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mThrowing = values;
        }

        @objid ("b411f2ea-eef4-4d02-995a-5960dc5646f6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RaisedExceptionSmClass)this.getTarget()).getThrownTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b847a639-1e06-4ff5-8224-df0b693d2b8e")
    public static class InternalStructureSmDependency extends SmMultipleDependency {
        @objid ("0431da80-0f14-4051-9595-1c6a98e1fed1")
        private SmDependency symetricDep;

        @objid ("d552fe1b-1cf4-4809-a379-760694108d17")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mInternalStructure != null)? ((ClassifierData)data).mInternalStructure:SmMultipleDependency.EMPTY;
        }

        @objid ("1be73526-20cf-4482-b898-d55034148fa5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mInternalStructure = values;
        }

        @objid ("25d4111f-9bb1-4c2f-a368-b407d2dda0b1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindableInstanceSmClass)this.getTarget()).getInternalOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("81c54bea-7d1a-41e3-a29d-def781c5a6d8")
    public static class RealizedComponentSmDependency extends SmMultipleDependency {
        @objid ("ea4379ac-375e-4fbc-b33d-8b272a67355c")
        private SmDependency symetricDep;

        @objid ("4b732aa3-6104-46da-b13d-dec154f7effe")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClassifierData)data).mRealizedComponent != null)? ((ClassifierData)data).mRealizedComponent:SmMultipleDependency.EMPTY;
        }

        @objid ("b725729b-0305-41c2-962f-2df5d4e15aea")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClassifierData) data).mRealizedComponent = values;
        }

        @objid ("4c409f6b-a26c-4740-80f0-dd7bf7d6c435")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ComponentRealizationSmClass)this.getTarget()).getRealizingClassifierDep();
            }
            return this.symetricDep;
        }

    }

}
