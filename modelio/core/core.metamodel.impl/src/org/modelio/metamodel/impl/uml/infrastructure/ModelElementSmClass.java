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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.metamodel.impl.impact.ImpactLinkSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.DependencySmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.impl.uml.infrastructure.NoteSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TaggedValueSmClass;
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
    @objid ("91838849-b4aa-467f-a82f-b6e3c08e7a13")
    private SmAttribute nameAtt;

    @objid ("528e1487-0e81-4c47-a1bc-9afb65095df6")
    private SmDependency localPropertiesDep;

    @objid ("6d2a6cfa-e473-4700-89e5-c96abab59da0")
    private SmDependency extensionDep;

    @objid ("6e6723cf-1b8a-4608-907a-c471117963f0")
    private SmDependency dependsOnDependencyDep;

    @objid ("60ab8127-c2e0-4677-a3ba-d63379c2de1c")
    private SmDependency tagDep;

    @objid ("a2092fd0-1cca-4d5f-83d6-b317a38d0ad8")
    private SmDependency impactedDependencyDep;

    @objid ("78e70873-5e68-40e8-b831-68c1ebffe578")
    private SmDependency propertiesDep;

    @objid ("637bead9-b038-4f24-bb98-38858cca96d2")
    private SmDependency productDep;

    @objid ("4758b133-ec02-4b8e-93fa-f3a98e96cb2c")
    private SmDependency descriptorDep;

    @objid ("708e4d1b-3f29-414c-b1be-5b4dc8f8eac2")
    private SmDependency matrixDep;

    @objid ("75cbd345-3840-4596-8358-9715d7b7bac8")
    private SmDependency impactImpactedDep;

    @objid ("06ab5469-4a2b-49ee-aa96-bce83f1e1cbf")
    private SmDependency impactDependsOnDep;

    @objid ("a06f896a-03d2-47e5-a495-2f0277d1f9e3")
    private SmDependency attachedDep;

    @objid ("280da67b-d140-4337-a283-69d3988c0960")
    public ModelElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d20a5013-c2f1-4f18-ad15-d783e9972bf1")
    @Override
    public String getName() {
        return "ModelElement";
    }

    @objid ("ead878ca-13b0-4dc5-a011-b8e35519c0ed")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("ca0151c3-91e0-494d-85fb-620db1fb17db")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModelElement.class;
    }

    @objid ("72ab0841-007b-49b2-839c-1867c3eb7283")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("52a2b9bd-b745-4781-b4f8-179d5e2f44fe")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("413f5ec5-e7ef-4065-acf6-eb0713ffe716")
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

    @objid ("b0542c06-62d0-4139-a419-7103408cc951")
    public SmAttribute getNameAtt() {
        if (this.nameAtt == null) {
        	this.nameAtt = this.getAttributeDef("Name");
        }
        return this.nameAtt;
    }

    @objid ("9b5f664f-4ebc-4dc4-bf4a-28546435f00e")
    public SmDependency getLocalPropertiesDep() {
        if (this.localPropertiesDep == null) {
        	this.localPropertiesDep = this.getDependencyDef("LocalProperties");
        }
        return this.localPropertiesDep;
    }

    @objid ("e0f1f896-158c-43db-afd9-5da9278075ae")
    public SmDependency getExtensionDep() {
        if (this.extensionDep == null) {
        	this.extensionDep = this.getDependencyDef("Extension");
        }
        return this.extensionDep;
    }

    @objid ("9ac18180-e760-4b71-8d17-10f908e1dd42")
    public SmDependency getDependsOnDependencyDep() {
        if (this.dependsOnDependencyDep == null) {
        	this.dependsOnDependencyDep = this.getDependencyDef("DependsOnDependency");
        }
        return this.dependsOnDependencyDep;
    }

    @objid ("6f9719db-2eda-4074-b546-ec9e23fac503")
    public SmDependency getTagDep() {
        if (this.tagDep == null) {
        	this.tagDep = this.getDependencyDef("Tag");
        }
        return this.tagDep;
    }

    @objid ("3d009da4-51fc-41ff-8e3a-77314cbd5f0f")
    public SmDependency getImpactedDependencyDep() {
        if (this.impactedDependencyDep == null) {
        	this.impactedDependencyDep = this.getDependencyDef("ImpactedDependency");
        }
        return this.impactedDependencyDep;
    }

    @objid ("d82fab28-8ab1-46d3-adee-fab7d32db7a1")
    public SmDependency getPropertiesDep() {
        if (this.propertiesDep == null) {
        	this.propertiesDep = this.getDependencyDef("Properties");
        }
        return this.propertiesDep;
    }

    @objid ("e034bc2d-70df-47e0-bd40-f81faf331009")
    public SmDependency getProductDep() {
        if (this.productDep == null) {
        	this.productDep = this.getDependencyDef("Product");
        }
        return this.productDep;
    }

    @objid ("91b8d718-19ee-4b32-9660-07030da92d94")
    public SmDependency getDescriptorDep() {
        if (this.descriptorDep == null) {
        	this.descriptorDep = this.getDependencyDef("Descriptor");
        }
        return this.descriptorDep;
    }

    @objid ("5701dd3b-49bf-4208-827f-99728753b32e")
    public SmDependency getMatrixDep() {
        if (this.matrixDep == null) {
        	this.matrixDep = this.getDependencyDef("Matrix");
        }
        return this.matrixDep;
    }

    @objid ("3e6bc6bc-75d1-4e03-b087-401312e198b7")
    public SmDependency getImpactImpactedDep() {
        if (this.impactImpactedDep == null) {
        	this.impactImpactedDep = this.getDependencyDef("impactImpacted");
        }
        return this.impactImpactedDep;
    }

    @objid ("55b58eec-0d98-44db-9ee8-9c9eff11c297")
    public SmDependency getImpactDependsOnDep() {
        if (this.impactDependsOnDep == null) {
        	this.impactDependsOnDep = this.getDependencyDef("impactDependsOn");
        }
        return this.impactDependsOnDep;
    }

    @objid ("53c6bc1f-4011-4c3a-9dde-04defd0ff4a9")
    public SmDependency getAttachedDep() {
        if (this.attachedDep == null) {
        	this.attachedDep = this.getDependencyDef("Attached");
        }
        return this.attachedDep;
    }

    @objid ("6f548e53-9af8-4740-9c66-0d30d54ee92a")
    private static class ModelElementObjectFactory implements ISmObjectFactory {
        @objid ("ab657b6b-52f7-4742-9e40-4ea67aab231b")
        private ModelElementSmClass smClass;

        @objid ("ac716c2f-11e0-4c23-8328-1d9c19cfb9bc")
        public ModelElementObjectFactory(ModelElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("63bb275f-1f9a-42c5-9ced-cd859521897f")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("edc4458d-3ca1-4027-baeb-ea2cc29649db")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("0fc91730-e4df-4324-b593-2e9cf3b5438b")
    public static class NameSmAttribute extends SmAttribute {
        @objid ("4d91b6df-3c1f-4e70-ba1b-866a93656391")
        public Object getValue(ISmObjectData data) {
            return ((ModelElementData) data).mName;
        }

        @objid ("6ddd7e8d-2aa7-4ae0-9360-c6b7403c59d4")
        public void setValue(ISmObjectData data, Object value) {
            ((ModelElementData) data).mName = value;
        }

    }

    @objid ("875c88b6-52dd-4a35-8a1a-045914be119f")
    public static class LocalPropertiesSmDependency extends SmSingleDependency {
        @objid ("1ee41356-7c28-4b4f-847d-52e568154086")
        private SmDependency symetricDep;

        @objid ("dd40c2b5-6d1d-43cb-ac5c-f99d2a5588a3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModelElementData) data).mLocalProperties;
        }

        @objid ("6ae89dce-100b-4866-a0f5-2297bd707807")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModelElementData) data).mLocalProperties = value;
        }

        @objid ("76755518-e0be-4606-a4e3-72860e1e4ee7")
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
        @objid ("1ecc0f00-fb80-48de-a894-d2580f8dcea9")
        private SmDependency symetricDep;

        @objid ("8926e1b4-c053-47b8-8e33-594add8583b7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mExtension != null)? ((ModelElementData)data).mExtension:SmMultipleDependency.EMPTY;
        }

        @objid ("d2ecdf0b-8b0a-43ab-9c83-b2b4d8580463")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mExtension = values;
        }

        @objid ("d731d31b-1f6e-4e38-90e3-aa9aa3f68639")
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
        @objid ("84cf7932-5051-4303-8797-43136cd3b794")
        private SmDependency symetricDep;

        @objid ("0646d43f-5239-4fac-8780-422793c4b317")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mDependsOnDependency != null)? ((ModelElementData)data).mDependsOnDependency:SmMultipleDependency.EMPTY;
        }

        @objid ("0ff5d77b-b951-4aea-946a-43500f7ebac0")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mDependsOnDependency = values;
        }

        @objid ("58d36e25-a12d-47c8-83a7-8e99d0d6a3b0")
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
        @objid ("6ef589d0-3200-4161-b0ec-ca92d6cf7fbc")
        private SmDependency symetricDep;

        @objid ("2d3cf524-29ff-43ec-9845-6669e6779daa")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mTag != null)? ((ModelElementData)data).mTag:SmMultipleDependency.EMPTY;
        }

        @objid ("b7b051f8-4827-4a4d-830f-d351ed47ffab")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mTag = values;
        }

        @objid ("eb04169c-9df7-4658-b124-753560fe85ee")
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
        @objid ("2975a128-c832-440c-8628-3545d26cee8c")
        private SmDependency symetricDep;

        @objid ("c8bcccd3-2bf9-47c6-a4f4-fcd0fea1dd2e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactedDependency != null)? ((ModelElementData)data).mImpactedDependency:SmMultipleDependency.EMPTY;
        }

        @objid ("24cd754e-bdd2-48a5-bb27-ab909fd784e4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactedDependency = values;
        }

        @objid ("949a6624-7002-47b5-ba91-bd97949d4fd7")
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
        @objid ("7054e35f-2e2c-432b-9908-624de1e4e646")
        private SmDependency symetricDep;

        @objid ("d3c99804-5d9d-4344-ba85-260473e6ff61")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mProperties != null)? ((ModelElementData)data).mProperties:SmMultipleDependency.EMPTY;
        }

        @objid ("04d00251-123b-4e54-ab58-dbc2195a4a8a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mProperties = values;
        }

        @objid ("78548680-a605-4799-a5f3-33c9b3564a59")
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
        @objid ("098e4c7a-f209-4b6b-9135-c33e7c4278b1")
        private SmDependency symetricDep;

        @objid ("902ace6b-5c37-4872-8e48-d8ca48d833d6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mProduct != null)? ((ModelElementData)data).mProduct:SmMultipleDependency.EMPTY;
        }

        @objid ("9c3a9a87-35c1-4835-b861-d3367e34e638")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mProduct = values;
        }

        @objid ("16f118d4-ce13-4013-8831-1410af5fd532")
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
        @objid ("d3142973-1dc9-40a7-8763-ef08ed5ca8ec")
        private SmDependency symetricDep;

        @objid ("f49de313-d9b8-4256-9bcd-2c67d8b27328")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mDescriptor != null)? ((ModelElementData)data).mDescriptor:SmMultipleDependency.EMPTY;
        }

        @objid ("cd152701-5633-47dd-9b66-2e77f01d4546")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mDescriptor = values;
        }

        @objid ("b83195a7-13c2-4849-a2a3-1d6128650f99")
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
        @objid ("80ff51bd-4218-43a3-a956-9b494e1548f4")
        private SmDependency symetricDep;

        @objid ("c487d08a-eb8b-4503-9c0b-c8b335335f78")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mMatrix != null)? ((ModelElementData)data).mMatrix:SmMultipleDependency.EMPTY;
        }

        @objid ("9b2905bf-b7e3-4567-9edb-c97054d266dd")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mMatrix = values;
        }

        @objid ("042208c8-050f-40b6-9c60-5acfee010acf")
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
        @objid ("aa7cd6cb-cd61-4e9b-8dac-9425194a37ee")
        private SmDependency symetricDep;

        @objid ("4e4709ed-fc70-437e-a233-19ceb269a136")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactImpacted != null)? ((ModelElementData)data).mImpactImpacted:SmMultipleDependency.EMPTY;
        }

        @objid ("ae1c92f7-3a11-4d60-a790-c06b29398bea")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactImpacted = values;
        }

        @objid ("88e023f8-4d36-4794-a1e6-96193aa6bdfa")
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
        @objid ("9e07ed75-3bb3-46c3-b3ef-dbedb52a96f1")
        private SmDependency symetricDep;

        @objid ("7a935088-72fe-47ba-b163-2362212991d3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactDependsOn != null)? ((ModelElementData)data).mImpactDependsOn:SmMultipleDependency.EMPTY;
        }

        @objid ("90264d4a-b6ef-40c6-9f83-bd2490227e44")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactDependsOn = values;
        }

        @objid ("1262086b-e405-4977-bb90-da038e312fd7")
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
        @objid ("f204ed17-16f3-4c31-a5f5-3b545da89eda")
        private SmDependency symetricDep;

        @objid ("80ada747-fe90-4a94-8c65-50cb778e5c6e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mAttached != null)? ((ModelElementData)data).mAttached:SmMultipleDependency.EMPTY;
        }

        @objid ("1755a8ad-33ac-429f-a72b-5902c5a7952c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mAttached = values;
        }

        @objid ("3b40d5e0-705e-4ef2-ba83-6933243c02f6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractResourceSmClass)this.getTarget()).getSubjectDep();
            }
            return this.symetricDep;
        }

    }

}
