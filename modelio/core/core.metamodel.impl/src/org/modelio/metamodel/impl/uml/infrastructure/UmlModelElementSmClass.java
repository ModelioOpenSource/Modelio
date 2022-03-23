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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityPartitionSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.BindingSmClass;
import org.modelio.metamodel.impl.uml.statik.ConnectorEndSmClass;
import org.modelio.metamodel.impl.uml.statik.ManifestationSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryConnectorSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSubstitutionSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
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

@objid ("dac456b2-2978-4c10-b927-63fc6d53a265")
public class UmlModelElementSmClass extends ModelElementSmClass {
    @objid ("7cc88ab7-3cc3-4bcc-9651-784c4d398eb1")
    private SmDependency templateSubstitutionDep;

    @objid ("5722667f-5059-4569-a000-b81983950e20")
    private SmDependency defaultParameteringDep;

    @objid ("c7074b85-58ae-455a-b2b8-f48f659241fd")
    private SmDependency representsDep;

    @objid ("42390f32-9ed5-41b6-b37d-d6292451471b")
    private SmDependency ownerTemplateParameterDep;

    @objid ("9e718f1d-f470-4a56-b632-33bea5bc44bc")
    private SmDependency representingEndDep;

    @objid ("8f1ce267-67d5-4264-aa5b-2aa563492030")
    private SmDependency representingPartitionDep;

    @objid ("af75725a-036b-4df6-a055-5cc150e504bd")
    private SmDependency constraintDefinitionDep;

    @objid ("ae3de3fe-f019-43eb-9f9a-1ca40c6e1865")
    private SmDependency typingParameterDep;

    @objid ("b45643ef-10be-4bda-be2b-4b14cfb31ea0")
    private SmDependency manifestingDep;

    @objid ("a2888f81-d630-4dce-a802-e455cc0b3716")
    private SmDependency representingInstanceDep;

    @objid ("c247f204-a19d-4a34-8441-44f69da2394e")
    private SmDependency receivedInfoDep;

    @objid ("d7c6eeeb-64e9-4bda-9521-f880500127b9")
    private SmDependency sentInfoDep;

    @objid ("3b5cb25f-96a1-4d98-9cda-05697a608542")
    private SmDependency representingConnectorDep;

    @objid ("99bb8377-e362-435f-bae3-c2238e5c7773")
    public  UmlModelElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0c68ed8f-4e8c-43b1-b1e1-6b8336df948e")
    @Override
    public String getName() {
        return "UmlModelElement";
        
    }

    @objid ("f83c6b22-f8f8-4e24-ae58-9b89f877695e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2291bf98-0e9d-4fd5-9c49-f45c210f9930")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return UmlModelElement.class;
        
    }

    @objid ("dc4ba0e3-4eb2-4d8c-9a70-ed1d0edf1c90")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("ecbfb0db-1a2d-43f4-8862-75c503f4599c")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("be73d003-5e58-4f4b-b837-79ac30326ffd")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new UmlModelElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.templateSubstitutionDep = new TemplateSubstitutionSmDependency();
        this.templateSubstitutionDep.init("TemplateSubstitution", this, metamodel.getMClass(TemplateParameterSubstitution.MQNAME), 0, -1 );
        registerDependency(this.templateSubstitutionDep);
        
        this.defaultParameteringDep = new DefaultParameteringSmDependency();
        this.defaultParameteringDep.init("DefaultParametering", this, metamodel.getMClass(TemplateParameter.MQNAME), 0, -1 );
        registerDependency(this.defaultParameteringDep);
        
        this.representsDep = new RepresentsSmDependency();
        this.representsDep.init("Represents", this, metamodel.getMClass(Binding.MQNAME), 0, -1 );
        registerDependency(this.representsDep);
        
        this.ownerTemplateParameterDep = new OwnerTemplateParameterSmDependency();
        this.ownerTemplateParameterDep.init("OwnerTemplateParameter", this, metamodel.getMClass(TemplateParameter.MQNAME), 0, 1 );
        registerDependency(this.ownerTemplateParameterDep);
        
        this.representingEndDep = new RepresentingEndSmDependency();
        this.representingEndDep.init("RepresentingEnd", this, metamodel.getMClass(ConnectorEnd.MQNAME), 0, -1 );
        registerDependency(this.representingEndDep);
        
        this.representingPartitionDep = new RepresentingPartitionSmDependency();
        this.representingPartitionDep.init("RepresentingPartition", this, metamodel.getMClass(ActivityPartition.MQNAME), 0, -1 );
        registerDependency(this.representingPartitionDep);
        
        this.constraintDefinitionDep = new ConstraintDefinitionSmDependency();
        this.constraintDefinitionDep.init("ConstraintDefinition", this, metamodel.getMClass(Constraint.MQNAME), 0, -1 , SmDirective.SMCDSHAREDCOMPONENT, SmDirective.SMCDTODELETE);
        registerDependency(this.constraintDefinitionDep);
        
        this.typingParameterDep = new TypingParameterSmDependency();
        this.typingParameterDep.init("TypingParameter", this, metamodel.getMClass(TemplateParameter.MQNAME), 0, -1 );
        registerDependency(this.typingParameterDep);
        
        this.manifestingDep = new ManifestingSmDependency();
        this.manifestingDep.init("Manifesting", this, metamodel.getMClass(Manifestation.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.manifestingDep);
        
        this.representingInstanceDep = new RepresentingInstanceSmDependency();
        this.representingInstanceDep.init("RepresentingInstance", this, metamodel.getMClass(BindableInstance.MQNAME), 0, -1 );
        registerDependency(this.representingInstanceDep);
        
        this.receivedInfoDep = new ReceivedInfoSmDependency();
        this.receivedInfoDep.init("ReceivedInfo", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.receivedInfoDep);
        
        this.sentInfoDep = new SentInfoSmDependency();
        this.sentInfoDep.init("SentInfo", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.sentInfoDep);
        
        this.representingConnectorDep = new RepresentingConnectorSmDependency();
        this.representingConnectorDep.init("RepresentingConnector", this, metamodel.getMClass(NaryConnector.MQNAME), 0, -1 );
        registerDependency(this.representingConnectorDep);
        
        
    }

    @objid ("9af2ba88-e44c-42a6-887d-ff3612ad21ea")
    public SmDependency getTemplateSubstitutionDep() {
        if (this.templateSubstitutionDep == null) {
        	this.templateSubstitutionDep = this.getDependencyDef("TemplateSubstitution");
        }
        return this.templateSubstitutionDep;
    }

    @objid ("2f606a21-ceab-4076-8ff1-ee8d134f18f4")
    public SmDependency getDefaultParameteringDep() {
        if (this.defaultParameteringDep == null) {
        	this.defaultParameteringDep = this.getDependencyDef("DefaultParametering");
        }
        return this.defaultParameteringDep;
    }

    @objid ("c3c8ec48-e2a1-4a10-b7c0-215aa2d854f3")
    public SmDependency getRepresentsDep() {
        if (this.representsDep == null) {
        	this.representsDep = this.getDependencyDef("Represents");
        }
        return this.representsDep;
    }

    @objid ("84aab6a1-5b16-4fc4-b7ec-77ae9ea52dc9")
    public SmDependency getOwnerTemplateParameterDep() {
        if (this.ownerTemplateParameterDep == null) {
        	this.ownerTemplateParameterDep = this.getDependencyDef("OwnerTemplateParameter");
        }
        return this.ownerTemplateParameterDep;
    }

    @objid ("c40e21e5-c276-44ed-8c18-334d4c53b13e")
    public SmDependency getRepresentingEndDep() {
        if (this.representingEndDep == null) {
        	this.representingEndDep = this.getDependencyDef("RepresentingEnd");
        }
        return this.representingEndDep;
    }

    @objid ("717faf40-b6fb-4fbd-8d4b-848defaa6697")
    public SmDependency getRepresentingPartitionDep() {
        if (this.representingPartitionDep == null) {
        	this.representingPartitionDep = this.getDependencyDef("RepresentingPartition");
        }
        return this.representingPartitionDep;
    }

    @objid ("82449a03-7db6-433c-ab12-f9f54cbf919b")
    public SmDependency getConstraintDefinitionDep() {
        if (this.constraintDefinitionDep == null) {
        	this.constraintDefinitionDep = this.getDependencyDef("ConstraintDefinition");
        }
        return this.constraintDefinitionDep;
    }

    @objid ("1409ee0c-1bce-433a-85c9-b355b5cf358d")
    public SmDependency getTypingParameterDep() {
        if (this.typingParameterDep == null) {
        	this.typingParameterDep = this.getDependencyDef("TypingParameter");
        }
        return this.typingParameterDep;
    }

    @objid ("02b66d3a-28be-442d-b1c7-8f11d0b52d41")
    public SmDependency getManifestingDep() {
        if (this.manifestingDep == null) {
        	this.manifestingDep = this.getDependencyDef("Manifesting");
        }
        return this.manifestingDep;
    }

    @objid ("8a0ec087-5372-4848-b4e5-55214f42269b")
    public SmDependency getRepresentingInstanceDep() {
        if (this.representingInstanceDep == null) {
        	this.representingInstanceDep = this.getDependencyDef("RepresentingInstance");
        }
        return this.representingInstanceDep;
    }

    @objid ("3d857d0e-ffad-4ec7-8475-be8c7d900d8e")
    public SmDependency getReceivedInfoDep() {
        if (this.receivedInfoDep == null) {
        	this.receivedInfoDep = this.getDependencyDef("ReceivedInfo");
        }
        return this.receivedInfoDep;
    }

    @objid ("ea1dc5ed-6f20-4dae-b811-209594919042")
    public SmDependency getSentInfoDep() {
        if (this.sentInfoDep == null) {
        	this.sentInfoDep = this.getDependencyDef("SentInfo");
        }
        return this.sentInfoDep;
    }

    @objid ("c4cb2fa1-d144-4c64-a174-68c6abe98398")
    public SmDependency getRepresentingConnectorDep() {
        if (this.representingConnectorDep == null) {
        	this.representingConnectorDep = this.getDependencyDef("RepresentingConnector");
        }
        return this.representingConnectorDep;
    }

    @objid ("4a3cb52e-f85b-46cc-8e7e-5ba166bd8908")
    private static class UmlModelElementObjectFactory implements ISmObjectFactory {
        @objid ("2d3c5c4a-8ce4-46dd-bdb0-547fe4a09b6e")
        private UmlModelElementSmClass smClass;

        @objid ("76ffc1ea-8f7a-4f1c-9546-ba5cc2c85e5a")
        public  UmlModelElementObjectFactory(UmlModelElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a0dde006-caaf-41a1-b965-ec0d8eb4b056")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("0509c2c8-a5d1-49e4-a05c-0be1a85b10f9")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("15f32f2d-f9d3-4bce-a514-4f465b7f88fb")
    public static class TemplateSubstitutionSmDependency extends SmMultipleDependency {
        @objid ("0d733403-0fd9-41ec-aec3-e8c9df325c6f")
        private SmDependency symetricDep;

        @objid ("f8ec2d63-2438-47c1-9602-dcdc4e12a99a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mTemplateSubstitution != null)? ((UmlModelElementData)data).mTemplateSubstitution:SmMultipleDependency.EMPTY;
        }

        @objid ("d974bbaa-9885-47ae-bcf8-e51aa4ed37b6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mTemplateSubstitution = values;
            
        }

        @objid ("8f64b73e-b174-4214-a5e0-980ea02ad0f6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSubstitutionSmClass)this.getTarget()).getActualDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("193becd3-9601-4440-a204-ef1b4360b996")
    public static class DefaultParameteringSmDependency extends SmMultipleDependency {
        @objid ("ac279f80-57e3-4f42-9525-aaa8c5662d24")
        private SmDependency symetricDep;

        @objid ("7678e8e0-225b-4421-989a-6140e10ea27c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mDefaultParametering != null)? ((UmlModelElementData)data).mDefaultParametering:SmMultipleDependency.EMPTY;
        }

        @objid ("8e8b7678-140e-4fcd-b4b2-5531a8400baa")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mDefaultParametering = values;
            
        }

        @objid ("23edfba2-3543-4bc3-acdb-51cb471125ca")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSmClass)this.getTarget()).getDefaultTypeDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d4751fbc-ecdf-4900-800c-c79bb72062c3")
    public static class RepresentsSmDependency extends SmMultipleDependency {
        @objid ("87392a7b-fd99-46a4-97ad-45ce38aa62cd")
        private SmDependency symetricDep;

        @objid ("7f1d6048-3919-4103-9fbf-9abb2958b498")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mRepresents != null)? ((UmlModelElementData)data).mRepresents:SmMultipleDependency.EMPTY;
        }

        @objid ("0ab5b42d-0d56-4317-9717-f496894a3871")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mRepresents = values;
            
        }

        @objid ("501d1eed-ff9f-43c9-bb3a-9db1395b2634")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindingSmClass)this.getTarget()).getRepresentedFeatureDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("dffa69dc-3787-4667-b035-eac75be88c8a")
    public static class OwnerTemplateParameterSmDependency extends SmSingleDependency {
        @objid ("2002b3a1-e4da-43fd-88a9-9c1ba778ede4")
        private SmDependency symetricDep;

        @objid ("5a06e526-091f-419a-8aa3-d46ebcf2da28")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((UmlModelElementData) data).mOwnerTemplateParameter;
        }

        @objid ("1574d83c-63a8-4ba4-8ab3-600d3ba7876e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((UmlModelElementData) data).mOwnerTemplateParameter = value;
        }

        @objid ("1510ffe5-8710-44cd-b56e-b09d17a31bb5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSmClass)this.getTarget()).getOwnedParameterElementDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("40dbcade-1bff-4954-86fd-a4d40a52e021")
    public static class RepresentingEndSmDependency extends SmMultipleDependency {
        @objid ("9791cb96-6a61-442e-a2b2-e2b66f0ff5b3")
        private SmDependency symetricDep;

        @objid ("48a0dcc2-31bb-4cb7-9975-74375156af91")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mRepresentingEnd != null)? ((UmlModelElementData)data).mRepresentingEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("5fc82c42-637b-496c-9d0d-ce1d7bb79114")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mRepresentingEnd = values;
            
        }

        @objid ("d9c0adf5-6787-4f6e-85d3-3f158892a26f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConnectorEndSmClass)this.getTarget()).getRepresentedFeatureDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d38a7f4a-be92-4c06-b40e-a36e36a1d7c7")
    public static class RepresentingPartitionSmDependency extends SmMultipleDependency {
        @objid ("39923023-ad4c-4be2-8ffa-749a25a9342b")
        private SmDependency symetricDep;

        @objid ("cc3c1ae6-e8b3-4790-899e-a59bbb9d4c1f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mRepresentingPartition != null)? ((UmlModelElementData)data).mRepresentingPartition:SmMultipleDependency.EMPTY;
        }

        @objid ("1b2765ae-87d6-4a78-9678-fe312076275e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mRepresentingPartition = values;
            
        }

        @objid ("fb80b5bd-171c-4240-a401-6f0be608c2da")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityPartitionSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("69c3a2fa-e958-441d-9f22-48342e4622f6")
    public static class ConstraintDefinitionSmDependency extends SmMultipleDependency {
        @objid ("acfa2dc0-cb87-43e5-b404-78978875ab3d")
        private SmDependency symetricDep;

        @objid ("c133e127-1156-4cf3-9bd3-52bda9bb31c3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mConstraintDefinition != null)? ((UmlModelElementData)data).mConstraintDefinition:SmMultipleDependency.EMPTY;
        }

        @objid ("9f2739d7-b645-4486-a7c3-53121b555acc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mConstraintDefinition = values;
            
        }

        @objid ("a4c82cad-e1e0-4f0e-9d97-427562c8510d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConstraintSmClass)this.getTarget()).getConstrainedElementDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("20836ad0-a6e1-464e-b294-ffc6e2cb761b")
    public static class TypingParameterSmDependency extends SmMultipleDependency {
        @objid ("bad4d635-634a-4c0f-a3a3-692945e9c54d")
        private SmDependency symetricDep;

        @objid ("0c1e2c72-6549-4bd5-8115-11e3905a8386")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mTypingParameter != null)? ((UmlModelElementData)data).mTypingParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("68185084-44bd-4521-a6b9-08e8f872af7e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mTypingParameter = values;
            
        }

        @objid ("6a3666af-503d-4188-8fe8-0397ce794ca6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("84a34709-cabc-4d67-828e-67363b3b057f")
    public static class ManifestingSmDependency extends SmMultipleDependency {
        @objid ("50484d01-72c8-48bc-b631-e5c939d1cd96")
        private SmDependency symetricDep;

        @objid ("7673dc91-3fe5-42c4-82d4-a470299611ef")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mManifesting != null)? ((UmlModelElementData)data).mManifesting:SmMultipleDependency.EMPTY;
        }

        @objid ("1e18535d-38e2-45e0-b0c8-e89836c40eda")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mManifesting = values;
            
        }

        @objid ("d6c37972-1eba-4668-9070-154367f5bc8f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ManifestationSmClass)this.getTarget()).getUtilizedElementDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("328355f9-01c1-4ca3-875f-2c3a4557d0f1")
    public static class RepresentingInstanceSmDependency extends SmMultipleDependency {
        @objid ("835442b3-bca3-45d6-b0a5-130f27471569")
        private SmDependency symetricDep;

        @objid ("449c4a88-53b5-411e-9be1-b350f1df475b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mRepresentingInstance != null)? ((UmlModelElementData)data).mRepresentingInstance:SmMultipleDependency.EMPTY;
        }

        @objid ("05e1657d-48e1-4d06-8450-3f53ea61aba3")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mRepresentingInstance = values;
            
        }

        @objid ("e3811b24-032d-4ef4-9fc7-9e439e67f7af")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindableInstanceSmClass)this.getTarget()).getRepresentedFeatureDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("de1c8ea7-8f0b-4074-9bf2-c20a44e45770")
    public static class ReceivedInfoSmDependency extends SmMultipleDependency {
        @objid ("1a93d854-7485-4842-9420-151cee7d5c10")
        private SmDependency symetricDep;

        @objid ("ed47235e-d03c-4a10-9d59-472ea4157c81")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mReceivedInfo != null)? ((UmlModelElementData)data).mReceivedInfo:SmMultipleDependency.EMPTY;
        }

        @objid ("66b04838-a1ec-4c93-8f6b-472e399b1d0f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mReceivedInfo = values;
            
        }

        @objid ("d78ac929-9f26-4cc8-ad93-1e0714497dfb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getInformationTargetDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("9810647b-3c94-4022-8505-8f9ef4c80a3e")
    public static class SentInfoSmDependency extends SmMultipleDependency {
        @objid ("0c8b1b7f-84c0-4953-8f9a-c6a88b41d5df")
        private SmDependency symetricDep;

        @objid ("1a8a83b7-0771-4752-abca-40332107bf9d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mSentInfo != null)? ((UmlModelElementData)data).mSentInfo:SmMultipleDependency.EMPTY;
        }

        @objid ("e6febcfe-3492-484f-a5a5-ef95cf6cab0b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mSentInfo = values;
            
        }

        @objid ("d483ec60-274c-42bc-9b3a-931ef544da65")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getInformationSourceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a11e31d7-7a1c-445b-b5d9-4019913fef38")
    public static class RepresentingConnectorSmDependency extends SmMultipleDependency {
        @objid ("5a04a9c4-63c2-4a87-bbf1-8b26d3a15b2e")
        private SmDependency symetricDep;

        @objid ("fc949ddd-07d5-453f-89f5-2f1e213c997f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UmlModelElementData)data).mRepresentingConnector != null)? ((UmlModelElementData)data).mRepresentingConnector:SmMultipleDependency.EMPTY;
        }

        @objid ("72ee473c-f0a5-4919-8145-f4dd90b047aa")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UmlModelElementData) data).mRepresentingConnector = values;
            
        }

        @objid ("198ff6d4-cb5a-415d-9de9-136d540fbc03")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryConnectorSmClass)this.getTarget()).getRepresentedFeatureDep();
            }
            return this.symetricDep;
            
        }

    }

}
