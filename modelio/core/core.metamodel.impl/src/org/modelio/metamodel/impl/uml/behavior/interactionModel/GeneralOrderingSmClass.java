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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("23872769-b62e-47ff-875f-f283aa96d3a6")
public class GeneralOrderingSmClass extends ElementSmClass {
    @objid ("34fc8768-bce7-4165-99ec-b06ac51d4395")
    private SmDependency beforeDep;

    @objid ("39738d74-7921-4c34-92fa-d6ef82573286")
    private SmDependency afterDep;

    @objid ("10113d2d-f34f-4081-9fac-27f2b9613a4b")
    public  GeneralOrderingSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cfc96993-b07f-46c3-b8be-2366c5847038")
    @Override
    public String getName() {
        return "GeneralOrdering";
        
    }

    @objid ("9e79557d-2ceb-4f71-af9b-46b22a3cc461")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f19933ee-7415-4e25-9406-e28e2ee065ea")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return GeneralOrdering.class;
        
    }

    @objid ("1d24facc-7938-4625-b43c-d4a9ad8b208e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("81d85ee2-5d9a-4a02-88dc-2a4f070c9599")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("887b6af9-6281-4457-8de8-68537ac72be4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new GeneralOrderingObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.beforeDep = new BeforeSmDependency();
        this.beforeDep.init("Before", this, metamodel.getMClass(OccurrenceSpecification.MQNAME), 1, 1 );
        registerDependency(this.beforeDep);
        
        this.afterDep = new AfterSmDependency();
        this.afterDep.init("After", this, metamodel.getMClass(OccurrenceSpecification.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.afterDep);
        
        
    }

    @objid ("c58539b4-3426-4f46-b6ea-544c74320286")
    public SmDependency getBeforeDep() {
        if (this.beforeDep == null) {
        	this.beforeDep = this.getDependencyDef("Before");
        }
        return this.beforeDep;
    }

    @objid ("d4412c6c-ef05-421a-82ca-76ab1b9e989f")
    public SmDependency getAfterDep() {
        if (this.afterDep == null) {
        	this.afterDep = this.getDependencyDef("After");
        }
        return this.afterDep;
    }

    @objid ("14dafb05-cf10-4fad-9b1e-ea6c7d3e666b")
    private static class GeneralOrderingObjectFactory implements ISmObjectFactory {
        @objid ("0f952d36-109b-49c6-9025-dbd7417d8537")
        private GeneralOrderingSmClass smClass;

        @objid ("8d3d132a-98c7-41de-bc73-4c265f7d4431")
        public  GeneralOrderingObjectFactory(GeneralOrderingSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6f76b80a-7dee-47dd-913c-36da82419990")
        @Override
        public ISmObjectData createData() {
            return new GeneralOrderingData(this.smClass);
        }

        @objid ("5e3be15d-6304-4b17-b50e-c879f0f32480")
        @Override
        public SmObjectImpl createImpl() {
            return new GeneralOrderingImpl();
        }

    }

    @objid ("825b707d-030f-4611-bb16-c6622d4a5334")
    public static class BeforeSmDependency extends SmSingleDependency {
        @objid ("e0af3f11-4c7f-4751-b34f-18b5d5dee725")
        private SmDependency symetricDep;

        @objid ("a7c862d5-3b4f-46cc-92c5-6696eeabc669")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GeneralOrderingData) data).mBefore;
        }

        @objid ("c434033f-f30e-4d64-8abc-9af4f6a9d88f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GeneralOrderingData) data).mBefore = value;
        }

        @objid ("e9270df0-f482-4666-82f5-393df630716a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OccurrenceSpecificationSmClass)this.getTarget()).getToAfterDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("3f6218ef-28e8-403c-93d0-17d2063e9e4d")
    public static class AfterSmDependency extends SmSingleDependency {
        @objid ("170e859c-1463-499d-901c-29921f666a9b")
        private SmDependency symetricDep;

        @objid ("1b10a78d-8657-4e6c-ac89-334060142ad1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GeneralOrderingData) data).mAfter;
        }

        @objid ("259e3bbc-02ed-455a-b9d1-44176010379d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GeneralOrderingData) data).mAfter = value;
        }

        @objid ("e0ebc5ad-65a4-4ac1-8af4-a97beede1824")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OccurrenceSpecificationSmClass)this.getTarget()).getToBeforeDep();
            }
            return this.symetricDep;
            
        }

    }

}
