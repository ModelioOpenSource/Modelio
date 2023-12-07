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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.metamodel.impl.impact.ImpactLinkSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.LocalPropertyTableSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
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

@objid ("0fe746a0-5fbd-4e65-a5b2-a33e5981c48f")
public class ModelElementSmClass extends ElementSmClass {
    @objid ("024c0e20-a261-4bbf-8255-9fcfe4f77523")
    private SmAttribute nameAtt;

    @objid ("092c02b3-31e7-4146-8ecc-d67da1b6dcf1")
    private SmDependency localPropertiesDep;

    @objid ("4c8bb703-6d78-45ea-9835-6da10335cdb1")
    private SmDependency extensionDep;

    @objid ("39e1cfae-13d7-4096-b4b2-4b40dbcabdde")
    private SmDependency dependsOnDependencyDep;

    @objid ("200703dc-ed36-4c43-aeb7-9b7336e40368")
    private SmDependency tagDep;

    @objid ("9191d4d4-81f1-4e3c-9150-73b239695a33")
    private SmDependency impactedDependencyDep;

    @objid ("d73fc536-4f53-4afc-9929-20f9f127a569")
    private SmDependency propertiesDep;

    @objid ("b2a1cbe8-5804-4a78-8b13-2f7cd4a8fd0f")
    private SmDependency productDep;

    @objid ("ba883d3f-d169-4084-b7a8-5dc263fe6fdb")
    private SmDependency descriptorDep;

    @objid ("cba34e04-77e2-4589-92a3-20ff3224a865")
    private SmDependency matrixDep;

    @objid ("e958cada-ec90-42b8-94f2-d7bb43befc07")
    private SmDependency impactImpactedDep;

    @objid ("d967d216-749f-4b7c-9d12-5de17fc6794f")
    private SmDependency impactDependsOnDep;

    @objid ("99da5a8a-947c-429e-8ffb-f165e6965234")
    private SmDependency attachedDep;

    @objid ("88ced42a-d562-4fc9-ad92-c3213701eaca")
    public  ModelElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d3532a85-f09b-4461-928d-2e3b4deb4571")
    @Override
    public String getName() {
        return "ModelElement";
        
    }

    @objid ("e1a76740-752e-48f7-8331-4049060b65a5")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("493cf861-bc97-4820-bdda-86bd5665d9d1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModelElement.class;
        
    }

    @objid ("18575807-d5e3-4bb6-bfa4-014d9008793b")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("fcd60339-a303-4772-8456-b369ee9a6cbc")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("44f7500d-d53a-40ab-b73e-91a0032d60d0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new ModelElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.nameAtt = new NameSmAttribute();
        this.nameAtt.init("Name", this, String.class );
        registerAttribute(this.nameAtt);
        
        
        // Initialize and register the SmDependency
        this.localPropertiesDep = new LocalPropertiesSmDependency();
        this.localPropertiesDep.init("LocalProperties", this, metamodel.getMClass(LocalPropertyTable.MQNAME), 0, 1 , SmDirective.SMCDTODELETE);
        registerDependency(this.localPropertiesDep);
        
        this.extensionDep = new ExtensionSmDependency();
        this.extensionDep.init("Extension", this, metamodel.getMClass(Stereotype.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.extensionDep);
        
        this.dependsOnDependencyDep = new DependsOnDependencySmDependency();
        this.dependsOnDependencyDep.init("DependsOnDependency", this, metamodel.getMClass(Dependency.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.dependsOnDependencyDep);
        
        this.tagDep = new TagSmDependency();
        this.tagDep.init("Tag", this, metamodel.getMClass(TaggedValue.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.tagDep);
        
        this.impactedDependencyDep = new ImpactedDependencySmDependency();
        this.impactedDependencyDep.init("ImpactedDependency", this, metamodel.getMClass(Dependency.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.impactedDependencyDep);
        
        this.propertiesDep = new PropertiesSmDependency();
        this.propertiesDep.init("Properties", this, metamodel.getMClass(PropertyTable.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.propertiesDep);
        
        this.productDep = new ProductSmDependency();
        this.productDep.init("Product", this, metamodel.getMClass(AbstractDiagram.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.productDep);
        
        this.descriptorDep = new DescriptorSmDependency();
        this.descriptorDep.init("Descriptor", this, metamodel.getMClass(Note.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.descriptorDep);
        
        this.matrixDep = new MatrixSmDependency();
        this.matrixDep.init("Matrix", this, metamodel.getMClass(MatrixDefinition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.matrixDep);
        
        this.impactImpactedDep = new ImpactImpactedSmDependency();
        this.impactImpactedDep.init("impactImpacted", this, metamodel.getMClass(ImpactLink.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.impactImpactedDep);
        
        this.impactDependsOnDep = new ImpactDependsOnSmDependency();
        this.impactDependsOnDep.init("impactDependsOn", this, metamodel.getMClass(ImpactLink.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.impactDependsOnDep);
        
        this.attachedDep = new AttachedSmDependency();
        this.attachedDep.init("Attached", this, metamodel.getMClass(AbstractResource.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.attachedDep);
        
        
    }

    @objid ("11427338-7912-43a6-9aca-30d0817cb8b2")
    public SmAttribute getNameAtt() {
        if (this.nameAtt == null) {
        	this.nameAtt = this.getAttributeDef("Name");
        }
        return this.nameAtt;
    }

    @objid ("e72c936f-7d27-48ce-863a-2a50030d3f64")
    public SmDependency getLocalPropertiesDep() {
        if (this.localPropertiesDep == null) {
        	this.localPropertiesDep = this.getDependencyDef("LocalProperties");
        }
        return this.localPropertiesDep;
    }

    @objid ("bd921f77-b2e0-4cc8-8c51-c6fa8eca7211")
    public SmDependency getExtensionDep() {
        if (this.extensionDep == null) {
        	this.extensionDep = this.getDependencyDef("Extension");
        }
        return this.extensionDep;
    }

    @objid ("ab2f6ec7-cc68-4278-8d75-a6766dd94be0")
    public SmDependency getDependsOnDependencyDep() {
        if (this.dependsOnDependencyDep == null) {
        	this.dependsOnDependencyDep = this.getDependencyDef("DependsOnDependency");
        }
        return this.dependsOnDependencyDep;
    }

    @objid ("26274768-364b-4ea0-928a-ca71e4765dcb")
    public SmDependency getTagDep() {
        if (this.tagDep == null) {
        	this.tagDep = this.getDependencyDef("Tag");
        }
        return this.tagDep;
    }

    @objid ("96a903cc-09ab-4a1c-ac29-0209f53f7fba")
    public SmDependency getImpactedDependencyDep() {
        if (this.impactedDependencyDep == null) {
        	this.impactedDependencyDep = this.getDependencyDef("ImpactedDependency");
        }
        return this.impactedDependencyDep;
    }

    @objid ("707dcdd2-8c04-4a50-b11e-5882c2693232")
    public SmDependency getPropertiesDep() {
        if (this.propertiesDep == null) {
        	this.propertiesDep = this.getDependencyDef("Properties");
        }
        return this.propertiesDep;
    }

    @objid ("10ea219a-dc20-4c24-957e-92cfab7f1c5a")
    public SmDependency getProductDep() {
        if (this.productDep == null) {
        	this.productDep = this.getDependencyDef("Product");
        }
        return this.productDep;
    }

    @objid ("fb46d638-41c6-4dcd-a76e-09c531b6ff4a")
    public SmDependency getDescriptorDep() {
        if (this.descriptorDep == null) {
        	this.descriptorDep = this.getDependencyDef("Descriptor");
        }
        return this.descriptorDep;
    }

    @objid ("9ad7904b-7517-411b-a113-4a53b33c96c9")
    public SmDependency getMatrixDep() {
        if (this.matrixDep == null) {
        	this.matrixDep = this.getDependencyDef("Matrix");
        }
        return this.matrixDep;
    }

    @objid ("726dbb07-7430-4377-bf3b-9679995d6278")
    public SmDependency getImpactImpactedDep() {
        if (this.impactImpactedDep == null) {
        	this.impactImpactedDep = this.getDependencyDef("impactImpacted");
        }
        return this.impactImpactedDep;
    }

    @objid ("6851e4a4-10de-4291-b06b-56345917edc7")
    public SmDependency getImpactDependsOnDep() {
        if (this.impactDependsOnDep == null) {
        	this.impactDependsOnDep = this.getDependencyDef("impactDependsOn");
        }
        return this.impactDependsOnDep;
    }

    @objid ("3da7a773-9928-485d-813d-3d9bba27890f")
    public SmDependency getAttachedDep() {
        if (this.attachedDep == null) {
        	this.attachedDep = this.getDependencyDef("Attached");
        }
        return this.attachedDep;
    }

    @objid ("6f548e53-9af8-4740-9c66-0d30d54ee92a")
    private static class ModelElementObjectFactory implements ISmObjectFactory {
        @objid ("e4c73a24-ac30-4fef-831f-5f0cc389ac8d")
        private ModelElementSmClass smClass;

        @objid ("778bb0e8-7b6d-4cb7-84d2-047ce61abbdd")
        public  ModelElementObjectFactory(ModelElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("efad85c8-17d3-4e5a-b0b1-fda6dd2926d9")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("3f67c802-30b9-4205-908f-c50f48a7eeb1")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("0fc91730-e4df-4324-b593-2e9cf3b5438b")
    public static class NameSmAttribute extends SmAttribute {
        @objid ("3a33d33a-aeb4-41c6-aae3-1f2ace01b76c")
        public Object getValue(ISmObjectData data) {
            return ((ModelElementData) data).mName;
        }

        @objid ("c7482dc7-cf6c-4e61-a4d7-979903e6f2a3")
        public void setValue(ISmObjectData data, Object value) {
            ((ModelElementData) data).mName = value;
        }

    }

    @objid ("875c88b6-52dd-4a35-8a1a-045914be119f")
    public static class LocalPropertiesSmDependency extends SmSingleDependency {
        @objid ("946dd4c2-aa09-4c98-88eb-f38583a96169")
        private SmDependency symetricDep;

        @objid ("9d46d3b2-cddf-455d-8799-df624cc43a4e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModelElementData) data).mLocalProperties;
        }

        @objid ("b91f9fe8-ab5a-4842-b9ae-5d19d22fe3f8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModelElementData) data).mLocalProperties = value;
        }

        @objid ("6714a7fd-1aa0-4f4b-bf4b-169d99ff21f6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LocalPropertyTableSmClass)this.getTarget()).getLocalAnnotedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("01a6b15f-c0fc-4d4b-877a-7f803505d2af")
    public static class ExtensionSmDependency extends SmMultipleDependency {
        @objid ("99e66186-4d59-4aae-9387-7d8873ad0df0")
        private SmDependency symetricDep;

        @objid ("9aef101d-baca-45c8-830f-9ce1baf1d7f5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mExtension != null)? ((ModelElementData)data).mExtension:SmMultipleDependency.EMPTY;
        }

        @objid ("78c327f5-e945-49d9-b876-6b449f3ef8b9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mExtension = values;
            
        }

        @objid ("bcd151b1-9b47-4adb-9722-4660ded390df")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getExtendedElementDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("9d5cc936-0490-4acb-9fa1-42b533dde4a3")
    public static class DependsOnDependencySmDependency extends SmMultipleDependency {
        @objid ("887005cb-dfb3-4cc5-85d9-bf2558d5cd79")
        private SmDependency symetricDep;

        @objid ("264b3a65-ffc5-40d6-af5d-2e7f116d6440")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mDependsOnDependency != null)? ((ModelElementData)data).mDependsOnDependency:SmMultipleDependency.EMPTY;
        }

        @objid ("0acc3a35-08b0-463e-a02a-7787b877d224")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mDependsOnDependency = values;
            
        }

        @objid ("da26a336-d013-4e02-92b1-d490b0191611")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DependencySmClass)this.getTarget()).getImpactedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d2a24d4b-d613-443a-90ff-e43cce300c90")
    public static class TagSmDependency extends SmMultipleDependency {
        @objid ("d545842c-ff9c-44e6-8338-e4f433542171")
        private SmDependency symetricDep;

        @objid ("aeecd9a1-c25f-4316-8c7b-fdf94ada6752")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mTag != null)? ((ModelElementData)data).mTag:SmMultipleDependency.EMPTY;
        }

        @objid ("71f891e7-c223-409e-894a-440931c07f84")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mTag = values;
            
        }

        @objid ("2e61ae92-e51a-438e-a264-30cd7c6846b1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TaggedValueSmClass)this.getTarget()).getAnnotedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d604d60b-4bba-4566-b9b0-61b6fa3189b7")
    public static class ImpactedDependencySmDependency extends SmMultipleDependency {
        @objid ("8299259e-db87-4d91-9248-719d70274a26")
        private SmDependency symetricDep;

        @objid ("f84e79b2-c4e8-4fa4-b750-181e4bfb5943")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactedDependency != null)? ((ModelElementData)data).mImpactedDependency:SmMultipleDependency.EMPTY;
        }

        @objid ("39e888f3-a760-4aa5-a2fe-29cecfae497e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactedDependency = values;
            
        }

        @objid ("33100a2c-cf8f-4b04-90bf-7e6b66fdfb5d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DependencySmClass)this.getTarget()).getDependsOnDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("06ec1633-0ee0-41df-a43e-2802ddc22970")
    public static class PropertiesSmDependency extends SmMultipleDependency {
        @objid ("908c6389-8491-444e-b7c5-ec6f130f5f0c")
        private SmDependency symetricDep;

        @objid ("b5fe7a08-76b7-46bd-b53c-36becf508a76")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mProperties != null)? ((ModelElementData)data).mProperties:SmMultipleDependency.EMPTY;
        }

        @objid ("6958b448-4911-4518-9860-10d65d5a72be")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mProperties = values;
            
        }

        @objid ("8ac24111-ed62-4681-a84e-eebeb287a47d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("74abd2c3-a299-43e1-860f-57622f4ed879")
    public static class ProductSmDependency extends SmMultipleDependency {
        @objid ("64e74677-af8c-4bce-8c05-a3414ecaeb28")
        private SmDependency symetricDep;

        @objid ("9a90a444-93cd-4e3e-986e-ffe9a159a123")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mProduct != null)? ((ModelElementData)data).mProduct:SmMultipleDependency.EMPTY;
        }

        @objid ("ed10631c-a3fb-428d-8ca5-e017250c5bff")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mProduct = values;
            
        }

        @objid ("fff7a7d6-f84e-4c09-9978-5f2e539fd3c7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractDiagramSmClass)this.getTarget()).getOriginDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("f16ef57a-27cc-42cd-8de5-edf695d10692")
    public static class DescriptorSmDependency extends SmMultipleDependency {
        @objid ("3b5ba454-c3b3-4d82-b707-97d4c3cf3585")
        private SmDependency symetricDep;

        @objid ("27f2a040-2bc3-4689-8f4f-ed7031253065")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mDescriptor != null)? ((ModelElementData)data).mDescriptor:SmMultipleDependency.EMPTY;
        }

        @objid ("922bcf8a-6c52-4c8b-a5b8-11a6d16a47ce")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mDescriptor = values;
            
        }

        @objid ("4d3faef7-f4a1-4460-95d4-0cc188ff2e77")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NoteSmClass)this.getTarget()).getSubjectDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("95b73595-2e5d-4a0a-8c04-b8fa82b96c4a")
    public static class MatrixSmDependency extends SmMultipleDependency {
        @objid ("f5bed9fd-b31e-472e-a50a-f177b89230f2")
        private SmDependency symetricDep;

        @objid ("798fd653-a041-4615-9fee-b7e3fb5ef6e9")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mMatrix != null)? ((ModelElementData)data).mMatrix:SmMultipleDependency.EMPTY;
        }

        @objid ("f1a2c9c0-9da3-4595-baac-2f57e8b6a7be")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mMatrix = values;
            
        }

        @objid ("f60ff9c5-a30a-4246-be55-78a5a011a0d0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("987facb1-ca79-4f21-b96c-728666213c64")
    public static class ImpactImpactedSmDependency extends SmMultipleDependency {
        @objid ("a7fb9d3e-a062-4705-8e92-1c95c95410b9")
        private SmDependency symetricDep;

        @objid ("096c7ee9-591c-44a0-991a-e393e4d44d47")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactImpacted != null)? ((ModelElementData)data).mImpactImpacted:SmMultipleDependency.EMPTY;
        }

        @objid ("6cac98ef-dd0f-4e0f-968b-93459bc071c6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactImpacted = values;
            
        }

        @objid ("f23bd262-ed7e-420e-97b1-7e7c848c5e43")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getDependsOnDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("42f53616-f7ef-49a3-84fe-5de002e6a7af")
    public static class ImpactDependsOnSmDependency extends SmMultipleDependency {
        @objid ("a7efd0c0-f2c0-4527-99e1-2fcf08469971")
        private SmDependency symetricDep;

        @objid ("0a570427-e900-433a-bd52-00f2973a6744")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactDependsOn != null)? ((ModelElementData)data).mImpactDependsOn:SmMultipleDependency.EMPTY;
        }

        @objid ("6b38f88d-51e5-44a2-afe0-2e8707461af7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactDependsOn = values;
            
        }

        @objid ("b9304e28-d6ca-44bc-8a93-f594d8552ada")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getImpactedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c168c2d7-4c07-4c36-afb8-699b9a6903d8")
    public static class AttachedSmDependency extends SmMultipleDependency {
        @objid ("90979cc9-6473-47a2-9d03-e531874a4e43")
        private SmDependency symetricDep;

        @objid ("81ef49d0-cba1-48ef-8c62-4a7103e231cf")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mAttached != null)? ((ModelElementData)data).mAttached:SmMultipleDependency.EMPTY;
        }

        @objid ("3fddcf03-ac42-42da-ac42-943a29c09458")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mAttached = values;
            
        }

        @objid ("9f26f0a9-6a1b-43a1-820b-23043a53a14d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractResourceSmClass)this.getTarget()).getSubjectDep();
            }
            return this.symetricDep;
            
        }

    }

}
