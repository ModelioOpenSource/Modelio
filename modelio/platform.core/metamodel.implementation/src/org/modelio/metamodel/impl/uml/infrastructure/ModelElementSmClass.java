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
    @objid ("afa19f22-a305-4b2f-a8d4-b0a4478e7350")
    private SmAttribute nameAtt;

    @objid ("b144cf3b-e718-4884-9941-0ede49e1900c")
    private SmDependency localPropertiesDep;

    @objid ("5fd372ee-0429-488b-8f10-341fb16f122c")
    private SmDependency extensionDep;

    @objid ("75812829-e0b4-4aac-8bd9-b79ffffa01af")
    private SmDependency dependsOnDependencyDep;

    @objid ("78203c6e-1854-4bde-b42d-4252ca67d780")
    private SmDependency tagDep;

    @objid ("26128b49-7849-47a1-a670-1f5acf9ff3c0")
    private SmDependency impactedDependencyDep;

    @objid ("f6530e6e-a46c-4cc9-81e4-40d31f901546")
    private SmDependency propertiesDep;

    @objid ("5987aa60-7837-430a-8802-6c1f741b95af")
    private SmDependency productDep;

    @objid ("384f3bdd-7095-47de-b979-4694388e0760")
    private SmDependency descriptorDep;

    @objid ("7853352f-5805-4d91-a1e8-c4ab7aa375e9")
    private SmDependency matrixDep;

    @objid ("19de72d2-f9df-4d67-9b2c-d606839f4f62")
    private SmDependency impactImpactedDep;

    @objid ("e393b402-05b8-4edf-801b-a8212665355e")
    private SmDependency impactDependsOnDep;

    @objid ("9c80b2bf-bde5-438f-b269-834aa06135c3")
    private SmDependency attachedDep;

    @objid ("729c554b-fd9a-40ac-9e66-4136561f1231")
    public ModelElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("502376eb-e1ac-44f3-8fe7-c4968555cd04")
    @Override
    public String getName() {
        return "ModelElement";
    }

    @objid ("ffda1ff6-e3de-4d0c-9419-5d8f4a6de77f")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("e64f2d35-8670-4332-9202-bc985131cf84")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModelElement.class;
    }

    @objid ("e7e4836d-bc88-4677-aa7d-0716b315dcdc")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cd6286f2-e25f-4e79-be2c-942c774e6347")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("952c170d-dc50-4b68-85ac-70836fcba79f")
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

    @objid ("026ad609-fef3-4493-9cb4-7596d9b9cc8d")
    public SmAttribute getNameAtt() {
        if (this.nameAtt == null) {
        	this.nameAtt = this.getAttributeDef("Name");
        }
        return this.nameAtt;
    }

    @objid ("d34e8236-8d89-4094-a88e-472d2b5f4861")
    public SmDependency getLocalPropertiesDep() {
        if (this.localPropertiesDep == null) {
        	this.localPropertiesDep = this.getDependencyDef("LocalProperties");
        }
        return this.localPropertiesDep;
    }

    @objid ("cef62605-04df-4efa-b08b-d098d1de1b64")
    public SmDependency getExtensionDep() {
        if (this.extensionDep == null) {
        	this.extensionDep = this.getDependencyDef("Extension");
        }
        return this.extensionDep;
    }

    @objid ("7008e283-345c-4508-8699-49b18634314c")
    public SmDependency getDependsOnDependencyDep() {
        if (this.dependsOnDependencyDep == null) {
        	this.dependsOnDependencyDep = this.getDependencyDef("DependsOnDependency");
        }
        return this.dependsOnDependencyDep;
    }

    @objid ("bd520812-b5e5-4481-8e39-86a96322c6f6")
    public SmDependency getTagDep() {
        if (this.tagDep == null) {
        	this.tagDep = this.getDependencyDef("Tag");
        }
        return this.tagDep;
    }

    @objid ("0439d9de-51d6-44f8-8c19-0114974d4547")
    public SmDependency getImpactedDependencyDep() {
        if (this.impactedDependencyDep == null) {
        	this.impactedDependencyDep = this.getDependencyDef("ImpactedDependency");
        }
        return this.impactedDependencyDep;
    }

    @objid ("d2d43bd8-250e-4f33-b7a9-e1ae58cc902e")
    public SmDependency getPropertiesDep() {
        if (this.propertiesDep == null) {
        	this.propertiesDep = this.getDependencyDef("Properties");
        }
        return this.propertiesDep;
    }

    @objid ("d3224736-15ec-456c-8eaa-9a3da58f2571")
    public SmDependency getProductDep() {
        if (this.productDep == null) {
        	this.productDep = this.getDependencyDef("Product");
        }
        return this.productDep;
    }

    @objid ("b895014b-1bbb-4928-a7ff-6466812ab93b")
    public SmDependency getDescriptorDep() {
        if (this.descriptorDep == null) {
        	this.descriptorDep = this.getDependencyDef("Descriptor");
        }
        return this.descriptorDep;
    }

    @objid ("396311b8-a932-465d-8d13-8238f1c73b07")
    public SmDependency getMatrixDep() {
        if (this.matrixDep == null) {
        	this.matrixDep = this.getDependencyDef("Matrix");
        }
        return this.matrixDep;
    }

    @objid ("bd915551-fbff-4029-be96-9cf0f2bcb937")
    public SmDependency getImpactImpactedDep() {
        if (this.impactImpactedDep == null) {
        	this.impactImpactedDep = this.getDependencyDef("impactImpacted");
        }
        return this.impactImpactedDep;
    }

    @objid ("034b1e2a-597c-4310-ad68-9d96b484c157")
    public SmDependency getImpactDependsOnDep() {
        if (this.impactDependsOnDep == null) {
        	this.impactDependsOnDep = this.getDependencyDef("impactDependsOn");
        }
        return this.impactDependsOnDep;
    }

    @objid ("b2084c56-0817-46a2-aa6b-8445ba898abb")
    public SmDependency getAttachedDep() {
        if (this.attachedDep == null) {
        	this.attachedDep = this.getDependencyDef("Attached");
        }
        return this.attachedDep;
    }

    @objid ("6f548e53-9af8-4740-9c66-0d30d54ee92a")
    private static class ModelElementObjectFactory implements ISmObjectFactory {
        @objid ("f6620722-b87d-490e-b46d-1f141b264116")
        private ModelElementSmClass smClass;

        @objid ("89a25170-af39-471d-8885-74ee047d01de")
        public ModelElementObjectFactory(ModelElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("14880b9e-598c-4728-8aa6-f2c1566fd18f")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("cf798393-1665-4722-a95b-f93cc13caaa4")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("0fc91730-e4df-4324-b593-2e9cf3b5438b")
    public static class NameSmAttribute extends SmAttribute {
        @objid ("82799635-cf15-4b22-be3f-4f104ea3b76c")
        public Object getValue(ISmObjectData data) {
            return ((ModelElementData) data).mName;
        }

        @objid ("205b3e96-7a9f-4e2c-8747-e81445c0090e")
        public void setValue(ISmObjectData data, Object value) {
            ((ModelElementData) data).mName = value;
        }

    }

    @objid ("875c88b6-52dd-4a35-8a1a-045914be119f")
    public static class LocalPropertiesSmDependency extends SmSingleDependency {
        @objid ("bbe7af68-5b43-4857-8e03-b3dee0bb2a6d")
        private SmDependency symetricDep;

        @objid ("38cd0198-c838-4249-954d-86e724844b69")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModelElementData) data).mLocalProperties;
        }

        @objid ("b490e143-0074-483b-849c-202c9a5cf3fd")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModelElementData) data).mLocalProperties = value;
        }

        @objid ("d75780db-14de-4eff-b79a-2d68dc5da788")
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
        @objid ("53f1895d-0609-43f3-b4a8-515e110c7e5a")
        private SmDependency symetricDep;

        @objid ("03c440ed-c864-42ca-bdf0-809d9c68bfc0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mExtension != null)? ((ModelElementData)data).mExtension:SmMultipleDependency.EMPTY;
        }

        @objid ("1de35be4-d28b-4599-b8a5-c8d5eab2b9ee")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mExtension = values;
        }

        @objid ("1ad69fbb-15b3-40c2-bd8d-e2b94a97d0c0")
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
        @objid ("2d6167e9-7f96-4313-9855-ef5879425e8f")
        private SmDependency symetricDep;

        @objid ("df74e047-33a7-4132-b53d-b5b8916ef7b2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mDependsOnDependency != null)? ((ModelElementData)data).mDependsOnDependency:SmMultipleDependency.EMPTY;
        }

        @objid ("cb07cc85-8bca-465c-802d-98937d8bdf02")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mDependsOnDependency = values;
        }

        @objid ("035b7360-9dbf-4507-afd5-cb3637874227")
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
        @objid ("0de22ab4-1a77-4051-aed8-2e06004f785f")
        private SmDependency symetricDep;

        @objid ("a64a0a49-1b75-4a1d-8b5e-52fa0a40d9f3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mTag != null)? ((ModelElementData)data).mTag:SmMultipleDependency.EMPTY;
        }

        @objid ("de99e3a4-183d-41f7-8f05-53c9e5256850")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mTag = values;
        }

        @objid ("6ac6a86f-7aa0-46b1-84b0-be956cb212bb")
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
        @objid ("7664b286-8f6b-42e2-bd79-40908ff711c0")
        private SmDependency symetricDep;

        @objid ("997d3898-1ef8-47e1-8bcb-266d7f3abd47")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactedDependency != null)? ((ModelElementData)data).mImpactedDependency:SmMultipleDependency.EMPTY;
        }

        @objid ("7145f800-0814-4dc6-9c9a-4217ceb9eeae")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactedDependency = values;
        }

        @objid ("2b9a6f2d-81b5-4385-8159-70123e2fa304")
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
        @objid ("f76e7cf0-e9b5-41e5-8843-8efe5af51404")
        private SmDependency symetricDep;

        @objid ("ab8ac37c-d323-4030-b7c7-e6e4dc99003e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mProperties != null)? ((ModelElementData)data).mProperties:SmMultipleDependency.EMPTY;
        }

        @objid ("d40ea28a-cc4a-473d-af84-ece57ade5c78")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mProperties = values;
        }

        @objid ("f721afe3-f1d2-4e01-87fa-74235f3dfbf7")
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
        @objid ("fbae71e1-6c35-4c7f-9a2e-7917702bf33d")
        private SmDependency symetricDep;

        @objid ("18e030ce-c687-43db-9ad4-057b65b9afdc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mProduct != null)? ((ModelElementData)data).mProduct:SmMultipleDependency.EMPTY;
        }

        @objid ("4403db6b-6478-4381-a4f9-9ade5e34a386")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mProduct = values;
        }

        @objid ("abf98ef2-dec7-4c60-9315-a5b545f99963")
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
        @objid ("d73370a8-3235-44d2-a637-facf63792796")
        private SmDependency symetricDep;

        @objid ("710c7382-2b3e-482c-b95d-cea52d3b65bb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mDescriptor != null)? ((ModelElementData)data).mDescriptor:SmMultipleDependency.EMPTY;
        }

        @objid ("171b7eb0-993b-4bff-a83d-464bb596ca6e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mDescriptor = values;
        }

        @objid ("1d33bd2d-c3cc-49c8-a3a2-c89d7d74a2ad")
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
        @objid ("a9c68cc1-6279-44f4-bfc2-751f11c259fd")
        private SmDependency symetricDep;

        @objid ("b712b816-f53f-41ea-99c5-b6fef4fec2f5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mMatrix != null)? ((ModelElementData)data).mMatrix:SmMultipleDependency.EMPTY;
        }

        @objid ("b28a7a69-694c-448d-a8ca-a1b3e58bd951")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mMatrix = values;
        }

        @objid ("9372eb56-1dfb-460e-81fa-45c9e29b230c")
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
        @objid ("be0e6635-66c2-4f67-87a4-dc010fdd5fbb")
        private SmDependency symetricDep;

        @objid ("10740958-6660-4fd5-9b27-e2961fa1ee51")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactImpacted != null)? ((ModelElementData)data).mImpactImpacted:SmMultipleDependency.EMPTY;
        }

        @objid ("bab97dab-4746-444d-b241-1df0b4a2ba1b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactImpacted = values;
        }

        @objid ("a7eb278c-2671-4756-92be-232fcebc7a9d")
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
        @objid ("d43f56dd-8696-4b41-842e-852c5e8565ad")
        private SmDependency symetricDep;

        @objid ("fcf1f98e-719d-4d36-ad6d-8bb87fcde11f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mImpactDependsOn != null)? ((ModelElementData)data).mImpactDependsOn:SmMultipleDependency.EMPTY;
        }

        @objid ("1e10f946-2fb2-438e-805b-33e088ed465d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mImpactDependsOn = values;
        }

        @objid ("9d4b96fb-b951-4e50-a868-bdbe8fb7e782")
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
        @objid ("adca47d9-5e77-451a-88da-d602a06bb752")
        private SmDependency symetricDep;

        @objid ("0fc9fc22-4dbf-4ea1-9506-6ec738721c2a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelElementData)data).mAttached != null)? ((ModelElementData)data).mAttached:SmMultipleDependency.EMPTY;
        }

        @objid ("5b7d01f6-85d7-47ca-9b1a-e7d2a4077639")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelElementData) data).mAttached = values;
        }

        @objid ("277f0866-e93e-47ef-ada8-574ff7966001")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractResourceSmClass)this.getTarget()).getSubjectDep();
            }
            return this.symetricDep;
        }

    }

}
