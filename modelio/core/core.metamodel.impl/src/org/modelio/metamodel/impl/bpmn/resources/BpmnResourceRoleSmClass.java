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

package org.modelio.metamodel.impl.bpmn.resources;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeSmClass;
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

@objid ("1319bcee-ac9f-40ae-9ffb-8b062f330cac")
public class BpmnResourceRoleSmClass extends BpmnBaseElementSmClass {
    @objid ("2b65664e-9773-4593-ae42-572c9722b8fc")
    private SmDependency resourceRefDep;

    @objid ("5345a4be-8f30-4177-b1b8-3d76cd35f250")
    private SmDependency annotatedDep;

    @objid ("09b9ea4b-ab9f-4f40-b561-3a74e9f819af")
    private SmDependency resourceParameterBindingDep;

    @objid ("849afec8-eb5a-44d2-8099-23f397f6389e")
    private SmDependency processDep;

    @objid ("fe16c2c9-c4af-4c79-8a76-5042e8bfcaa2")
    public  BpmnResourceRoleSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f83ff39f-b7e3-4288-9149-ff558e4f04c9")
    @Override
    public String getName() {
        return "BpmnResourceRole";
        
    }

    @objid ("072c4b40-6621-4ba1-bae9-75b82482def9")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("46fb98fa-77c1-4645-91a0-9988ccfb8519")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnResourceRole.class;
        
    }

    @objid ("5632f85d-c5cd-40dc-830f-c02b54ce9c66")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("8f38ab06-12e0-495c-b5a3-459d58329ae9")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("d6c76a3a-b727-44e2-829e-f2ec7ace00a8")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnResourceRoleObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.resourceRefDep = new ResourceRefSmDependency();
        this.resourceRefDep.init("ResourceRef", this, metamodel.getMClass(BpmnResource.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.resourceRefDep);
        
        this.annotatedDep = new AnnotatedSmDependency();
        this.annotatedDep.init("Annotated", this, metamodel.getMClass(BpmnFlowNode.MQNAME), 0, 1 );
        registerDependency(this.annotatedDep);
        
        this.resourceParameterBindingDep = new ResourceParameterBindingSmDependency();
        this.resourceParameterBindingDep.init("ResourceParameterBinding", this, metamodel.getMClass(BpmnResourceParameterBinding.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.resourceParameterBindingDep);
        
        this.processDep = new ProcessSmDependency();
        this.processDep.init("Process", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, 1 );
        registerDependency(this.processDep);
        
        
    }

    @objid ("9530b21b-35e7-4b3f-b28f-64698effd135")
    public SmDependency getResourceRefDep() {
        if (this.resourceRefDep == null) {
        	this.resourceRefDep = this.getDependencyDef("ResourceRef");
        }
        return this.resourceRefDep;
    }

    @objid ("67020363-deb2-4e48-b826-df16cf042072")
    public SmDependency getAnnotatedDep() {
        if (this.annotatedDep == null) {
        	this.annotatedDep = this.getDependencyDef("Annotated");
        }
        return this.annotatedDep;
    }

    @objid ("d4b4d72e-a76d-4a28-873c-60d068e2c4e5")
    public SmDependency getResourceParameterBindingDep() {
        if (this.resourceParameterBindingDep == null) {
        	this.resourceParameterBindingDep = this.getDependencyDef("ResourceParameterBinding");
        }
        return this.resourceParameterBindingDep;
    }

    @objid ("5b4ffe40-ffb8-4ec1-b4d2-150459a9559a")
    public SmDependency getProcessDep() {
        if (this.processDep == null) {
        	this.processDep = this.getDependencyDef("Process");
        }
        return this.processDep;
    }

    @objid ("55a52e72-5bcf-4697-8f77-077abedf14e3")
    private static class BpmnResourceRoleObjectFactory implements ISmObjectFactory {
        @objid ("a9fe45c4-50d3-4dc3-addc-68643354ba21")
        private BpmnResourceRoleSmClass smClass;

        @objid ("00710c1d-a45c-4403-9625-879fac90e4e9")
        public  BpmnResourceRoleObjectFactory(BpmnResourceRoleSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("24fdebb8-3621-4007-9249-783ee5076664")
        @Override
        public ISmObjectData createData() {
            return new BpmnResourceRoleData(this.smClass);
        }

        @objid ("3bf89a54-29d2-421a-a613-0bfa58cce6cb")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnResourceRoleImpl();
        }

    }

    @objid ("15108910-7ea5-418c-9a09-a24de2323533")
    public static class ResourceRefSmDependency extends SmSingleDependency {
        @objid ("3aee29e2-039f-4cf0-a422-2dee9e69adbe")
        private SmDependency symetricDep;

        @objid ("1e490766-cdd9-4c9b-83f6-19256cb9f192")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceRoleData) data).mResourceRef;
        }

        @objid ("9b7ea857-2dd3-4539-a4d9-440a165da8af")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceRoleData) data).mResourceRef = value;
        }

        @objid ("92b3ede4-445d-42d5-9eb7-a0b590995d4f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceSmClass)this.getTarget()).getResourceroleRefsDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a1806a1c-7887-4067-af79-539ef6b0a584")
    public static class AnnotatedSmDependency extends SmSingleDependency {
        @objid ("44387aab-1a6a-4276-b364-cf8a75ef6c5d")
        private SmDependency symetricDep;

        @objid ("de4dcee9-8b12-458f-8c59-78a50c70dba1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceRoleData) data).mAnnotated;
        }

        @objid ("84b8aeeb-f33e-436a-80c1-6ffcf1b31b67")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceRoleData) data).mAnnotated = value;
        }

        @objid ("18587a20-b0c9-47d9-b847-3890d2a7f4ee")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowNodeSmClass)this.getTarget()).getResourceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("aa2156df-a527-46f4-a8e7-954b0db91d5f")
    public static class ResourceParameterBindingSmDependency extends SmMultipleDependency {
        @objid ("0b14f863-3bd0-48f5-9bff-c6e85382dec8")
        private SmDependency symetricDep;

        @objid ("e56ddd76-b690-489e-abf3-bdec1e485892")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnResourceRoleData)data).mResourceParameterBinding != null)? ((BpmnResourceRoleData)data).mResourceParameterBinding:SmMultipleDependency.EMPTY;
        }

        @objid ("b6171679-bc05-4e71-9131-12a8edc51f46")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnResourceRoleData) data).mResourceParameterBinding = values;
            
        }

        @objid ("16d3cebb-4195-47a7-9882-53a726a37d8c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceParameterBindingSmClass)this.getTarget()).getResourceRoleDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("54fe645b-14c9-49e3-a0ac-99c881f9051d")
    public static class ProcessSmDependency extends SmSingleDependency {
        @objid ("f9a773c6-3c44-4194-80ef-28279f123262")
        private SmDependency symetricDep;

        @objid ("69b1c9cd-4954-4e5e-95ba-eb0977725a0f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceRoleData) data).mProcess;
        }

        @objid ("42664791-f833-40ff-a19d-86ec828fa9ac")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceRoleData) data).mProcess = value;
        }

        @objid ("7ec452e5-13ea-4fa7-b993-7f91a6404165")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getResourceDep();
            }
            return this.symetricDep;
            
        }

    }

}
