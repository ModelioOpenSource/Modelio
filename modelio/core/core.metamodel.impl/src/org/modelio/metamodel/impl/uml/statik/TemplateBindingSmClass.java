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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
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

@objid ("3f0d167f-f8f6-4084-b5f9-ccbcaa07e981")
public class TemplateBindingSmClass extends UmlModelElementSmClass {
    @objid ("49054ca9-3319-4b80-b709-234cf1e782fb")
    private SmDependency parameterSubstitutionDep;

    @objid ("adffedae-beba-4f37-8239-59695f2bb381")
    private SmDependency boundOperationDep;

    @objid ("8ac127a4-520a-4986-8bb2-b46d5e2eff75")
    private SmDependency instanciatedTemplateOperationDep;

    @objid ("a9eae5fc-530e-486f-bf3e-a6accd0716ef")
    private SmDependency instanciatedTemplateDep;

    @objid ("7efb8ba4-cc0b-4f83-ad4c-6daec547ca64")
    private SmDependency boundElementDep;

    @objid ("7156b5c9-f9da-4042-8c5e-347bcf899037")
    public  TemplateBindingSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("21462453-9745-4cd5-82f4-981cd5f53d45")
    @Override
    public String getName() {
        return "TemplateBinding";
        
    }

    @objid ("7b8fbecb-daa6-455e-ac39-3ab119fffb9a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("5968cb4e-4c64-45d4-942e-13a6290c2134")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TemplateBinding.class;
        
    }

    @objid ("c52b1c5b-8721-409b-8e4d-e0da67aebcf5")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("1847c9f3-195e-4eeb-93a9-2730e459172d")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7d7703bb-903d-4408-a2e0-60a86d83a4dc")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new TemplateBindingObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.parameterSubstitutionDep = new ParameterSubstitutionSmDependency();
        this.parameterSubstitutionDep.init("ParameterSubstitution", this, metamodel.getMClass(TemplateParameterSubstitution.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.parameterSubstitutionDep);
        
        this.boundOperationDep = new BoundOperationSmDependency();
        this.boundOperationDep.init("BoundOperation", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.boundOperationDep);
        
        this.instanciatedTemplateOperationDep = new InstanciatedTemplateOperationSmDependency();
        this.instanciatedTemplateOperationDep.init("InstanciatedTemplateOperation", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.instanciatedTemplateOperationDep);
        
        this.instanciatedTemplateDep = new InstanciatedTemplateSmDependency();
        this.instanciatedTemplateDep.init("InstanciatedTemplate", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.instanciatedTemplateDep);
        
        this.boundElementDep = new BoundElementSmDependency();
        this.boundElementDep.init("BoundElement", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.boundElementDep);
        
        
    }

    @objid ("ca664615-31a9-4fcf-ae8a-f8b8b3294c4c")
    public SmDependency getParameterSubstitutionDep() {
        if (this.parameterSubstitutionDep == null) {
        	this.parameterSubstitutionDep = this.getDependencyDef("ParameterSubstitution");
        }
        return this.parameterSubstitutionDep;
    }

    @objid ("d0c7b5a4-7c55-4844-a617-f2eefbcb2d82")
    public SmDependency getBoundOperationDep() {
        if (this.boundOperationDep == null) {
        	this.boundOperationDep = this.getDependencyDef("BoundOperation");
        }
        return this.boundOperationDep;
    }

    @objid ("f84cdcee-070f-4db3-8a93-81af2c6216aa")
    public SmDependency getInstanciatedTemplateOperationDep() {
        if (this.instanciatedTemplateOperationDep == null) {
        	this.instanciatedTemplateOperationDep = this.getDependencyDef("InstanciatedTemplateOperation");
        }
        return this.instanciatedTemplateOperationDep;
    }

    @objid ("881a2e48-3547-459b-a793-83ad69f959aa")
    public SmDependency getInstanciatedTemplateDep() {
        if (this.instanciatedTemplateDep == null) {
        	this.instanciatedTemplateDep = this.getDependencyDef("InstanciatedTemplate");
        }
        return this.instanciatedTemplateDep;
    }

    @objid ("595a7492-2cf2-442d-97d9-3300f0ec54a8")
    public SmDependency getBoundElementDep() {
        if (this.boundElementDep == null) {
        	this.boundElementDep = this.getDependencyDef("BoundElement");
        }
        return this.boundElementDep;
    }

    @objid ("582c8ed2-8025-4e3f-a14c-4c54c5356f6f")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("b8cd215f-0433-4ad5-862d-93d9b8b8a1ed")
    private static class TemplateBindingObjectFactory implements ISmObjectFactory {
        @objid ("93f81a69-662c-448a-ac70-5bf2aaa80a41")
        private TemplateBindingSmClass smClass;

        @objid ("7759e5e8-7265-453e-a175-1c8b72b3e4ae")
        public  TemplateBindingObjectFactory(TemplateBindingSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e6e187a6-a34b-4067-8046-5271a3dbe181")
        @Override
        public ISmObjectData createData() {
            return new TemplateBindingData(this.smClass);
        }

        @objid ("f662eddd-21a8-467b-8dd9-e13dfc17253e")
        @Override
        public SmObjectImpl createImpl() {
            return new TemplateBindingImpl();
        }

    }

    @objid ("3af81550-c36a-46ea-9c25-6107e01ba5bf")
    public static class ParameterSubstitutionSmDependency extends SmMultipleDependency {
        @objid ("ef14d6f7-f4eb-4891-9f40-6934c63e6448")
        private SmDependency symetricDep;

        @objid ("b73eebb8-767f-4852-a038-bdd22cbaad22")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TemplateBindingData)data).mParameterSubstitution != null)? ((TemplateBindingData)data).mParameterSubstitution:SmMultipleDependency.EMPTY;
        }

        @objid ("c49bb8c9-9ad7-407f-ad9a-d3b5cab7d40e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TemplateBindingData) data).mParameterSubstitution = values;
            
        }

        @objid ("1d456bf1-aadb-47ff-a297-4d2f275256d8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSubstitutionSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("5370731f-9e57-467c-bc29-1212756e865e")
    public static class BoundOperationSmDependency extends SmSingleDependency {
        @objid ("377bd9ab-0d4f-4efb-ae8f-af197d9997ef")
        private SmDependency symetricDep;

        @objid ("128b0438-6fb5-43cb-8076-8418f243c826")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateBindingData) data).mBoundOperation;
        }

        @objid ("69a68639-afbb-42a4-9906-8e5851392cb5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateBindingData) data).mBoundOperation = value;
        }

        @objid ("95713b14-91c3-4ae9-a884-3671ebd5f41d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getTemplateInstanciationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("2579c941-6b53-482c-a7f4-dafe893730da")
    public static class InstanciatedTemplateOperationSmDependency extends SmSingleDependency {
        @objid ("b986434c-5732-4dc7-93d9-34856edf91d6")
        private SmDependency symetricDep;

        @objid ("5b76692f-72e0-4c7b-97c7-1d9302b24555")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateBindingData) data).mInstanciatedTemplateOperation;
        }

        @objid ("0b434dd1-cfd5-4ae2-ac42-2f7fcc107e7b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateBindingData) data).mInstanciatedTemplateOperation = value;
        }

        @objid ("01cd169d-59a3-4f8c-968c-8a018333666f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getInstanciatingBindingDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d9ed676d-eb32-423c-962f-3c85da0cb88c")
    public static class InstanciatedTemplateSmDependency extends SmSingleDependency {
        @objid ("61874bc4-777b-4cc1-92ae-7f40b43ac161")
        private SmDependency symetricDep;

        @objid ("a6898f26-4190-47eb-83ee-39164e67dd70")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateBindingData) data).mInstanciatedTemplate;
        }

        @objid ("444a75e6-1c55-4655-8828-b72ce27a9949")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateBindingData) data).mInstanciatedTemplate = value;
        }

        @objid ("ec15e778-92e5-4ce5-9914-9d5ccee5efbb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getInstanciatingBindingDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("27564942-710e-4d5d-b792-d8121468fa00")
    public static class BoundElementSmDependency extends SmSingleDependency {
        @objid ("2f431b7d-810f-4617-85aa-a2fefb322dae")
        private SmDependency symetricDep;

        @objid ("1ea969b7-3adc-42c8-90ad-aaf230e98c9b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateBindingData) data).mBoundElement;
        }

        @objid ("0cedae5f-ca28-4685-a21a-0a64c40c5534")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateBindingData) data).mBoundElement = value;
        }

        @objid ("19fc3f0e-f8fd-4823-870d-8b823c99bf68")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getTemplateInstanciationDep();
            }
            return this.symetricDep;
            
        }

    }

}
