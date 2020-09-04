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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.DataFlowSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelTreeSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationUseSmClass;
import org.modelio.metamodel.impl.uml.statik.ElementImportSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralizationSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.InterfaceRealizationSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceData;
import org.modelio.metamodel.impl.uml.statik.PackageImportSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateBindingSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("13b9e8d9-1cce-4eb0-886d-3ddd04faefcc")
public class NameSpaceSmClass extends ModelTreeSmClass {
    @objid ("a5c00e0e-bc2d-429d-8af8-734475d70e7e")
    private SmAttribute isAbstractAtt;

    @objid ("adfa3c3a-00ff-415a-8a2f-8d27a79a2346")
    private SmAttribute isLeafAtt;

    @objid ("798a4f99-b4ed-4712-9118-433a109a530f")
    private SmAttribute isRootAtt;

    @objid ("af632a50-d43c-46ec-97a2-2f2533a6cdfe")
    private SmAttribute visibilityAtt;

    @objid ("ea916434-d04c-4505-b564-e2d363eae3b4")
    private SmDependency parentDep;

    @objid ("9475712a-5a4e-4512-918f-917f02b491ed")
    private SmDependency templateInstanciationDep;

    @objid ("406b53cd-fc22-4558-9d91-a4cb4d4839e7")
    private SmDependency representingDep;

    @objid ("31cf17bc-e373-4156-8e57-accaa1d8e5c2")
    private SmDependency ownedBehaviorDep;

    @objid ("7146483b-76e6-45d5-ac35-091ece6bfe77")
    private SmDependency receivedDep;

    @objid ("5e2d4074-cf99-479c-b466-265cc9818b9b")
    private SmDependency ownedInformationFlowDep;

    @objid ("8bdf3b0f-9223-454a-9a37-9ef96a2befe2")
    private SmDependency importingDep;

    @objid ("45f9d2df-6d52-4e73-bcea-3dbd6ff734d7")
    private SmDependency sentDep;

    @objid ("a607cdd3-80fc-4cb6-b6b9-6d3b40c34174")
    private SmDependency ownedDataFlowDep;

    @objid ("6ee060fd-6c09-45a3-89d9-b063b373ad7b")
    private SmDependency ownedCollaborationUseDep;

    @objid ("fa611f4b-adba-4eb7-9b50-3259acdea303")
    private SmDependency ownedPackageImportDep;

    @objid ("82dcda65-0cf0-4e8a-b7fc-da0b03c8ae10")
    private SmDependency templateDep;

    @objid ("10964de3-eb79-48cf-8c49-b2b3cee9cc9b")
    private SmDependency specializationDep;

    @objid ("66d4234c-b280-4774-97f6-2fd5a274886b")
    private SmDependency realizedDep;

    @objid ("8ea5ae9d-0ed2-4de0-be10-497e6d6b573d")
    private SmDependency declaredDep;

    @objid ("9729e4a7-0e4a-4fa1-b132-54b263178348")
    private SmDependency instanciatingBindingDep;

    @objid ("acab672a-9722-422f-bbf3-e82f13f3daa9")
    private SmDependency ownedImportDep;

    @objid ("87055698-9ac9-4601-a3de-4de993f9e1b0")
    public NameSpaceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5338f0fb-3142-4ad4-acb1-e6040191934a")
    @Override
    public String getName() {
        return "NameSpace";
    }

    @objid ("923a2bfd-0e17-4b84-8ec9-74dbbcce6510")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("bb8975b9-5148-4270-b87d-6d916a3cf484")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NameSpace.class;
    }

    @objid ("dcf04889-9c35-4e9b-974f-b95296d6ca35")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5ed2122e-0d78-485b-8c8b-85443012e84f")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("810cb867-1ad2-43c6-9946-71b11aadfee2")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelTree.MQNAME);
        this.registerFactory(new NameSpaceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isAbstractAtt = new IsAbstractSmAttribute();
        this.isAbstractAtt.init("IsAbstract", this, Boolean.class );
        registerAttribute(this.isAbstractAtt);
        
        this.isLeafAtt = new IsLeafSmAttribute();
        this.isLeafAtt.init("IsLeaf", this, Boolean.class );
        registerAttribute(this.isLeafAtt);
        
        this.isRootAtt = new IsRootSmAttribute();
        this.isRootAtt.init("IsRoot", this, Boolean.class );
        registerAttribute(this.isRootAtt);
        
        this.visibilityAtt = new VisibilitySmAttribute();
        this.visibilityAtt.init("Visibility", this, VisibilityMode.class );
        registerAttribute(this.visibilityAtt);
        
        
        // Initialize and register the SmDependency
        this.parentDep = new ParentSmDependency();
        this.parentDep.init("Parent", this, metamodel.getMClass(Generalization.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.parentDep);
        
        this.templateInstanciationDep = new TemplateInstanciationSmDependency();
        this.templateInstanciationDep.init("TemplateInstanciation", this, metamodel.getMClass(TemplateBinding.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.templateInstanciationDep);
        
        this.representingDep = new RepresentingSmDependency();
        this.representingDep.init("Representing", this, metamodel.getMClass(Instance.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.representingDep);
        
        this.ownedBehaviorDep = new OwnedBehaviorSmDependency();
        this.ownedBehaviorDep.init("OwnedBehavior", this, metamodel.getMClass(Behavior.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedBehaviorDep);
        
        this.receivedDep = new ReceivedSmDependency();
        this.receivedDep.init("Received", this, metamodel.getMClass(DataFlow.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.receivedDep);
        
        this.ownedInformationFlowDep = new OwnedInformationFlowSmDependency();
        this.ownedInformationFlowDep.init("OwnedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedInformationFlowDep);
        
        this.importingDep = new ImportingSmDependency();
        this.importingDep.init("Importing", this, metamodel.getMClass(ElementImport.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.importingDep);
        
        this.sentDep = new SentSmDependency();
        this.sentDep.init("Sent", this, metamodel.getMClass(DataFlow.MQNAME), 0, -1 , SmDirective.SMCDPARTOF, SmDirective.SMCDTODELETE);
        registerDependency(this.sentDep);
        
        this.ownedDataFlowDep = new OwnedDataFlowSmDependency();
        this.ownedDataFlowDep.init("OwnedDataFlow", this, metamodel.getMClass(DataFlow.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedDataFlowDep);
        
        this.ownedCollaborationUseDep = new OwnedCollaborationUseSmDependency();
        this.ownedCollaborationUseDep.init("OwnedCollaborationUse", this, metamodel.getMClass(CollaborationUse.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedCollaborationUseDep);
        
        this.ownedPackageImportDep = new OwnedPackageImportSmDependency();
        this.ownedPackageImportDep.init("OwnedPackageImport", this, metamodel.getMClass(PackageImport.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedPackageImportDep);
        
        this.templateDep = new TemplateSmDependency();
        this.templateDep.init("Template", this, metamodel.getMClass(TemplateParameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.templateDep);
        
        this.specializationDep = new SpecializationSmDependency();
        this.specializationDep.init("Specialization", this, metamodel.getMClass(Generalization.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.specializationDep);
        
        this.realizedDep = new RealizedSmDependency();
        this.realizedDep.init("Realized", this, metamodel.getMClass(InterfaceRealization.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.realizedDep);
        
        this.declaredDep = new DeclaredSmDependency();
        this.declaredDep.init("Declared", this, metamodel.getMClass(Instance.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.declaredDep);
        
        this.instanciatingBindingDep = new InstanciatingBindingSmDependency();
        this.instanciatingBindingDep.init("InstanciatingBinding", this, metamodel.getMClass(TemplateBinding.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.instanciatingBindingDep);
        
        this.ownedImportDep = new OwnedImportSmDependency();
        this.ownedImportDep.init("OwnedImport", this, metamodel.getMClass(ElementImport.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedImportDep);
    }

    @objid ("5cc33061-829a-456b-ab62-5ef3c52d3346")
    public SmAttribute getIsAbstractAtt() {
        if (this.isAbstractAtt == null) {
        	this.isAbstractAtt = this.getAttributeDef("IsAbstract");
        }
        return this.isAbstractAtt;
    }

    @objid ("42f36668-5d3a-42d5-a507-3bc29684dc25")
    public SmAttribute getIsLeafAtt() {
        if (this.isLeafAtt == null) {
        	this.isLeafAtt = this.getAttributeDef("IsLeaf");
        }
        return this.isLeafAtt;
    }

    @objid ("a07e7d09-fa56-48bd-877b-b6cfb8c0ced6")
    public SmAttribute getIsRootAtt() {
        if (this.isRootAtt == null) {
        	this.isRootAtt = this.getAttributeDef("IsRoot");
        }
        return this.isRootAtt;
    }

    @objid ("02402158-a85c-40ae-ae4b-17156cc0e683")
    public SmAttribute getVisibilityAtt() {
        if (this.visibilityAtt == null) {
        	this.visibilityAtt = this.getAttributeDef("Visibility");
        }
        return this.visibilityAtt;
    }

    @objid ("b04b7b36-9f83-40e6-b4e0-f50cd9f034be")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("24cd400a-1421-4669-a57b-a58cc2f48e11")
    public SmDependency getTemplateInstanciationDep() {
        if (this.templateInstanciationDep == null) {
        	this.templateInstanciationDep = this.getDependencyDef("TemplateInstanciation");
        }
        return this.templateInstanciationDep;
    }

    @objid ("11c9ea4d-cbae-4859-abb9-967dc196eb1d")
    public SmDependency getRepresentingDep() {
        if (this.representingDep == null) {
        	this.representingDep = this.getDependencyDef("Representing");
        }
        return this.representingDep;
    }

    @objid ("1a9571a5-3aaf-4b3a-b4ad-c9e296abff04")
    public SmDependency getOwnedBehaviorDep() {
        if (this.ownedBehaviorDep == null) {
        	this.ownedBehaviorDep = this.getDependencyDef("OwnedBehavior");
        }
        return this.ownedBehaviorDep;
    }

    @objid ("8334d4cb-def8-436f-b1ff-f9f8442318ab")
    public SmDependency getReceivedDep() {
        if (this.receivedDep == null) {
        	this.receivedDep = this.getDependencyDef("Received");
        }
        return this.receivedDep;
    }

    @objid ("4348b569-dc77-43b3-8da1-6052d1d1df97")
    public SmDependency getOwnedInformationFlowDep() {
        if (this.ownedInformationFlowDep == null) {
        	this.ownedInformationFlowDep = this.getDependencyDef("OwnedInformationFlow");
        }
        return this.ownedInformationFlowDep;
    }

    @objid ("79276335-d5b2-446c-9365-5cdb66a9a70b")
    public SmDependency getImportingDep() {
        if (this.importingDep == null) {
        	this.importingDep = this.getDependencyDef("Importing");
        }
        return this.importingDep;
    }

    @objid ("b4ea87a1-390f-43ac-9663-02ef1269d0c1")
    public SmDependency getSentDep() {
        if (this.sentDep == null) {
        	this.sentDep = this.getDependencyDef("Sent");
        }
        return this.sentDep;
    }

    @objid ("ec418ac3-a7c2-4b30-8244-b49594f372f7")
    public SmDependency getOwnedDataFlowDep() {
        if (this.ownedDataFlowDep == null) {
        	this.ownedDataFlowDep = this.getDependencyDef("OwnedDataFlow");
        }
        return this.ownedDataFlowDep;
    }

    @objid ("24e5335b-1109-4742-9aa5-ec68d483eafe")
    public SmDependency getOwnedCollaborationUseDep() {
        if (this.ownedCollaborationUseDep == null) {
        	this.ownedCollaborationUseDep = this.getDependencyDef("OwnedCollaborationUse");
        }
        return this.ownedCollaborationUseDep;
    }

    @objid ("7dcfcdf0-87b5-4ab4-b982-21c627036b7e")
    public SmDependency getOwnedPackageImportDep() {
        if (this.ownedPackageImportDep == null) {
        	this.ownedPackageImportDep = this.getDependencyDef("OwnedPackageImport");
        }
        return this.ownedPackageImportDep;
    }

    @objid ("b2d9d06b-a099-4e84-99a8-e4b445419a3d")
    public SmDependency getTemplateDep() {
        if (this.templateDep == null) {
        	this.templateDep = this.getDependencyDef("Template");
        }
        return this.templateDep;
    }

    @objid ("8d2e8b94-4452-466b-9a10-b92790e12c78")
    public SmDependency getSpecializationDep() {
        if (this.specializationDep == null) {
        	this.specializationDep = this.getDependencyDef("Specialization");
        }
        return this.specializationDep;
    }

    @objid ("eb4f92ec-ac2e-4940-8b37-a4e40df63e69")
    public SmDependency getRealizedDep() {
        if (this.realizedDep == null) {
        	this.realizedDep = this.getDependencyDef("Realized");
        }
        return this.realizedDep;
    }

    @objid ("db26c9c3-0c41-48c4-9723-d4429471a587")
    public SmDependency getDeclaredDep() {
        if (this.declaredDep == null) {
        	this.declaredDep = this.getDependencyDef("Declared");
        }
        return this.declaredDep;
    }

    @objid ("192a465c-d700-409d-ae6e-be72e45418fa")
    public SmDependency getInstanciatingBindingDep() {
        if (this.instanciatingBindingDep == null) {
        	this.instanciatingBindingDep = this.getDependencyDef("InstanciatingBinding");
        }
        return this.instanciatingBindingDep;
    }

    @objid ("15e7fae0-faf5-4b68-a8f1-67fc8b0096fc")
    public SmDependency getOwnedImportDep() {
        if (this.ownedImportDep == null) {
        	this.ownedImportDep = this.getDependencyDef("OwnedImport");
        }
        return this.ownedImportDep;
    }

    @objid ("0c2c93bd-97dc-4339-9b61-858238a53613")
    private static class NameSpaceObjectFactory implements ISmObjectFactory {
        @objid ("5357d1d1-bdef-4fdc-ac4e-b02b4e2bbc6e")
        private NameSpaceSmClass smClass;

        @objid ("b79281ea-9e4b-4317-a98f-c3fb1664fce8")
        public NameSpaceObjectFactory(NameSpaceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8c44fd51-0d63-475e-9365-024b5b93710a")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("316d9258-2c3f-469b-890d-81bc9efe563f")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("e467ac03-2614-4bd1-b888-9e1d86a19212")
    public static class IsAbstractSmAttribute extends SmAttribute {
        @objid ("4b86a538-d6d5-489d-a7da-30242e4ef782")
        public Object getValue(ISmObjectData data) {
            return ((NameSpaceData) data).mIsAbstract;
        }

        @objid ("32dc48cd-4bbe-4faf-ab2b-7ed0fff871d4")
        public void setValue(ISmObjectData data, Object value) {
            ((NameSpaceData) data).mIsAbstract = value;
        }

    }

    @objid ("6699d79c-dbe6-4334-8053-a581c5134078")
    public static class IsLeafSmAttribute extends SmAttribute {
        @objid ("850fc691-8036-4898-beb2-c0cdcc60055e")
        public Object getValue(ISmObjectData data) {
            return ((NameSpaceData) data).mIsLeaf;
        }

        @objid ("9fe3ed6a-8180-49e5-bdcb-c18359f21cd9")
        public void setValue(ISmObjectData data, Object value) {
            ((NameSpaceData) data).mIsLeaf = value;
        }

    }

    @objid ("619c1ac4-a2cc-4f07-b976-420301036df0")
    public static class IsRootSmAttribute extends SmAttribute {
        @objid ("416996db-703c-4166-b019-b7a909d93610")
        public Object getValue(ISmObjectData data) {
            return ((NameSpaceData) data).mIsRoot;
        }

        @objid ("d4e0bc58-8360-4f2c-9498-4ef14d431cb9")
        public void setValue(ISmObjectData data, Object value) {
            ((NameSpaceData) data).mIsRoot = value;
        }

    }

    @objid ("873c7fa8-ff6e-49fc-b78f-faa1725dc77c")
    public static class VisibilitySmAttribute extends SmAttribute {
        @objid ("cb8e27bc-5346-4ad8-984b-0c632a8e31e2")
        public Object getValue(ISmObjectData data) {
            return ((NameSpaceData) data).mVisibility;
        }

        @objid ("ee874ac9-8f70-485e-a5a5-dae96349c422")
        public void setValue(ISmObjectData data, Object value) {
            ((NameSpaceData) data).mVisibility = value;
        }

    }

    @objid ("ce9d691b-cbbb-4ddf-a343-1c5007dece6c")
    public static class ParentSmDependency extends SmMultipleDependency {
        @objid ("77a224da-f316-45f6-b40f-34ccb4892abb")
        private SmDependency symetricDep;

        @objid ("5e50418a-a862-4750-b06c-cea25963de81")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mParent != null)? ((NameSpaceData)data).mParent:SmMultipleDependency.EMPTY;
        }

        @objid ("fd9210da-e8b5-4607-a013-fc3d3babcc17")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mParent = values;
        }

        @objid ("ac0c26f7-9533-409e-a762-34ee05c36fd4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralizationSmClass)this.getTarget()).getSubTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1ee90518-fb64-4c52-8a70-fd10a1e6f5a6")
    public static class TemplateInstanciationSmDependency extends SmMultipleDependency {
        @objid ("524b844b-87f1-44e4-99a5-2055d49daebf")
        private SmDependency symetricDep;

        @objid ("c6ecf1e8-21ad-412c-a6c4-1a40ecb8f86b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mTemplateInstanciation != null)? ((NameSpaceData)data).mTemplateInstanciation:SmMultipleDependency.EMPTY;
        }

        @objid ("8206a11a-2f5d-44ec-94b3-14799f4fcd09")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mTemplateInstanciation = values;
        }

        @objid ("51e6c362-02e7-43a5-a359-8f4875e09389")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateBindingSmClass)this.getTarget()).getBoundElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("34c20884-94c0-4e85-b18b-6a047025e976")
    public static class RepresentingSmDependency extends SmMultipleDependency {
        @objid ("7cdb2ed3-4993-4fab-82aa-21f3f0cd36f3")
        private SmDependency symetricDep;

        @objid ("542a0146-695d-4dce-9b25-ecf0878c32fb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mRepresenting != null)? ((NameSpaceData)data).mRepresenting:SmMultipleDependency.EMPTY;
        }

        @objid ("6f8a9156-8cf1-44a0-bc4b-7ec009ec36da")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mRepresenting = values;
        }

        @objid ("d3e8cd17-c7c1-40e4-9712-a84c210a88bb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getBaseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("309f4387-5f19-40d7-9a95-cec2ab8276c7")
    public static class OwnedBehaviorSmDependency extends SmMultipleDependency {
        @objid ("e629d7c8-1ca6-4711-bd6e-42f7334e697a")
        private SmDependency symetricDep;

        @objid ("6d78af44-9f8c-440b-b726-ae896c23dffc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mOwnedBehavior != null)? ((NameSpaceData)data).mOwnedBehavior:SmMultipleDependency.EMPTY;
        }

        @objid ("44b90537-4a2a-4263-ac0f-1af3fa4edb99")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mOwnedBehavior = values;
        }

        @objid ("4b0ddd48-492d-4a35-a6ba-e69c3dc87244")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("505aafbb-5f5e-4c34-818d-f1c7c2c6acaf")
    public static class ReceivedSmDependency extends SmMultipleDependency {
        @objid ("0e54ca57-0e85-4c7d-ada0-f77a7fe483e0")
        private SmDependency symetricDep;

        @objid ("d7e5ef74-c718-438a-970d-6c1ead1ec357")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mReceived != null)? ((NameSpaceData)data).mReceived:SmMultipleDependency.EMPTY;
        }

        @objid ("a78023bc-562e-4dd1-9bab-8866de8129ba")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mReceived = values;
        }

        @objid ("98f7fec8-c94c-4e49-ad2a-cb552a617ccd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DataFlowSmClass)this.getTarget()).getDestinationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4c6cc3fd-b946-4bb1-9b12-8778ed028872")
    public static class OwnedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("beff1336-384c-436b-b82e-ee347903730a")
        private SmDependency symetricDep;

        @objid ("fa7355c0-239d-4c50-bd29-be020ea27ba5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mOwnedInformationFlow != null)? ((NameSpaceData)data).mOwnedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("1357beb8-40aa-40b7-accf-2b19263fc964")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mOwnedInformationFlow = values;
        }

        @objid ("3e208f99-ccba-4acd-b84a-1feea97cf202")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6d961059-4007-4424-ba72-327698591a92")
    public static class ImportingSmDependency extends SmMultipleDependency {
        @objid ("c74faf6a-b940-4d36-bea6-b1a480af30b2")
        private SmDependency symetricDep;

        @objid ("a51a59f2-9223-4336-b82c-8c5ebebe8664")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mImporting != null)? ((NameSpaceData)data).mImporting:SmMultipleDependency.EMPTY;
        }

        @objid ("2550339a-7b46-4056-be9c-d0e37dca6f2c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mImporting = values;
        }

        @objid ("5a57211b-da58-4b8a-850b-e200584be5ad")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ElementImportSmClass)this.getTarget()).getImportedElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("d31f875f-f0ee-498f-8f6b-78fc04452915")
    public static class SentSmDependency extends SmMultipleDependency {
        @objid ("4effcadd-a651-4625-ba85-57d0296ece9a")
        private SmDependency symetricDep;

        @objid ("108f6380-a07f-4964-bfda-fd4797b53b30")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mSent != null)? ((NameSpaceData)data).mSent:SmMultipleDependency.EMPTY;
        }

        @objid ("941d5d0b-7b08-429d-a1a7-e25e7b7c28c6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mSent = values;
        }

        @objid ("55e4d88e-e7bc-4613-844c-ebe546e24a9a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DataFlowSmClass)this.getTarget()).getOriginDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("54693267-9025-4a35-bed9-dcbeb0d28494")
    public static class OwnedDataFlowSmDependency extends SmMultipleDependency {
        @objid ("f7a5d7aa-2d6d-41f1-9854-5b534034b3af")
        private SmDependency symetricDep;

        @objid ("72f1bfac-006e-490d-9c22-e769c119aecc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mOwnedDataFlow != null)? ((NameSpaceData)data).mOwnedDataFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("e2ec960d-cb63-4538-9f4a-d493800f39f4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mOwnedDataFlow = values;
        }

        @objid ("89da0c30-b672-4f2e-8b6a-0b757cbdf658")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DataFlowSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9358538f-89a4-4944-8392-5a3ef2b78856")
    public static class OwnedCollaborationUseSmDependency extends SmMultipleDependency {
        @objid ("f6020eb4-a103-4b6e-9517-cf7bdc025a08")
        private SmDependency symetricDep;

        @objid ("fae469d9-aeb9-4906-9e2f-6af6f1855433")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mOwnedCollaborationUse != null)? ((NameSpaceData)data).mOwnedCollaborationUse:SmMultipleDependency.EMPTY;
        }

        @objid ("b2175cd8-0029-450c-a43c-dd803a07a10a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mOwnedCollaborationUse = values;
        }

        @objid ("f572c58e-e5f3-4edc-a073-825970d01be3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationUseSmClass)this.getTarget()).getNRepresentedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("520aa15c-8309-4879-858e-8dce21c7ffa5")
    public static class OwnedPackageImportSmDependency extends SmMultipleDependency {
        @objid ("4dbbd3a3-a8a6-413f-92fe-efa3a64a300e")
        private SmDependency symetricDep;

        @objid ("a3fd92d1-0565-4818-aed6-7ecbb8aa07c8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mOwnedPackageImport != null)? ((NameSpaceData)data).mOwnedPackageImport:SmMultipleDependency.EMPTY;
        }

        @objid ("4c4afb4a-c800-465e-ad5c-fedffe8b468a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mOwnedPackageImport = values;
        }

        @objid ("cffb7bdd-4b3a-40a6-9bc3-28c5eea56d69")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageImportSmClass)this.getTarget()).getImportingNameSpaceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a6e8b92e-78b2-4996-94cf-6e8208f369fd")
    public static class TemplateSmDependency extends SmMultipleDependency {
        @objid ("aaf53f29-3510-4acf-8b1f-b38e933c6ab5")
        private SmDependency symetricDep;

        @objid ("a3e30170-373a-47e8-aaa6-99d3c206e1ee")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mTemplate != null)? ((NameSpaceData)data).mTemplate:SmMultipleDependency.EMPTY;
        }

        @objid ("9d05af35-75ec-4939-85e4-68c318d9351f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mTemplate = values;
        }

        @objid ("a4f50937-3ed2-48e8-8eff-f1615fb3e720")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSmClass)this.getTarget()).getParameterizedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c4e526cb-be69-4b6a-899d-d38a476a66c5")
    public static class SpecializationSmDependency extends SmMultipleDependency {
        @objid ("02af9da9-4c37-4741-9b1c-42cb605856af")
        private SmDependency symetricDep;

        @objid ("56a9fce1-cdee-4d8d-ba45-5e53d977779a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mSpecialization != null)? ((NameSpaceData)data).mSpecialization:SmMultipleDependency.EMPTY;
        }

        @objid ("fdec9b2e-b486-41a7-8cd9-3a97730a3726")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mSpecialization = values;
        }

        @objid ("5170266f-9f21-436c-84b0-65548db5c70f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralizationSmClass)this.getTarget()).getSuperTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1dc657ab-2b7f-46e9-b7ef-83d713693a7c")
    public static class RealizedSmDependency extends SmMultipleDependency {
        @objid ("4c148008-d060-44e5-902c-6fc6ca36f16a")
        private SmDependency symetricDep;

        @objid ("35d5d98f-42d5-428e-9783-82666b4f54d1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mRealized != null)? ((NameSpaceData)data).mRealized:SmMultipleDependency.EMPTY;
        }

        @objid ("cbe428db-5932-48ee-9b76-0d770196e756")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mRealized = values;
        }

        @objid ("61127fea-a324-44c8-9b49-3d43d7cfb79f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InterfaceRealizationSmClass)this.getTarget()).getImplementerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("08a7b20e-ca49-4b9b-b0f0-be219d10ae6f")
    public static class DeclaredSmDependency extends SmMultipleDependency {
        @objid ("6a3cb8d4-8bff-47b4-9564-d5e974d77add")
        private SmDependency symetricDep;

        @objid ("146d0b29-9aed-408a-b1b6-137756f4c99e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mDeclared != null)? ((NameSpaceData)data).mDeclared:SmMultipleDependency.EMPTY;
        }

        @objid ("545c310c-d982-44df-9b6b-b3f15882d62f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mDeclared = values;
        }

        @objid ("53d5f84e-9b4d-45da-9c5d-da7dae28ed79")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8fd88ea7-1062-44f8-b1b2-5a9f71e4739c")
    public static class InstanciatingBindingSmDependency extends SmMultipleDependency {
        @objid ("99e68ec3-f6fa-4366-9180-5e8a9b40859f")
        private SmDependency symetricDep;

        @objid ("4c894b2b-e655-4cb7-9291-4fee9c8124b4")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mInstanciatingBinding != null)? ((NameSpaceData)data).mInstanciatingBinding:SmMultipleDependency.EMPTY;
        }

        @objid ("dca2275b-e5c7-4f12-94aa-7cae26f703e9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mInstanciatingBinding = values;
        }

        @objid ("ec5d3bea-b3dc-4303-bbb9-e0e0fe8851f7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateBindingSmClass)this.getTarget()).getInstanciatedTemplateDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("731ee4cd-c2a0-4d53-840a-ebd11dfa28d5")
    public static class OwnedImportSmDependency extends SmMultipleDependency {
        @objid ("469d0380-1c82-4ae1-8d82-581b77f4554b")
        private SmDependency symetricDep;

        @objid ("7a8df2e8-a41b-4546-809c-b97828d1d4c8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NameSpaceData)data).mOwnedImport != null)? ((NameSpaceData)data).mOwnedImport:SmMultipleDependency.EMPTY;
        }

        @objid ("4dd2dd2e-74e4-4d1a-a51e-1ec11723b90d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NameSpaceData) data).mOwnedImport = values;
        }

        @objid ("53680c4e-da60-4f32-b4d4-598ca7dab4a2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ElementImportSmClass)this.getTarget()).getImportingNameSpaceDep();
            }
            return this.symetricDep;
        }

    }

}
