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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassAssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationData;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
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

@objid ("8713ac34-9f32-4ab4-b3ee-79e367ec168e")
public class NaryAssociationSmClass extends UmlModelElementSmClass {
    @objid ("65dead9c-7787-4d13-a8a0-8e14ef3389bd")
    private SmDependency occurenceDep;

    @objid ("44244f79-08ca-4427-8aee-b5c94a092bb3")
    private SmDependency naryEndDep;

    @objid ("8948fd88-c732-47e7-9090-f27b884eb8c7")
    private SmDependency linkToClassDep;

    @objid ("80c2a932-9358-4a79-ac7b-d94ba6c7ee29")
    public NaryAssociationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("663d27b8-fc03-407d-a41b-5424e12bce9b")
    @Override
    public String getName() {
        return "NaryAssociation";
    }

    @objid ("824dc38a-bd6f-4896-9a70-ee7d146c05a9")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("66d0361c-ac04-4a6b-b831-a7df1ba1da35")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NaryAssociation.class;
    }

    @objid ("d75a5545-4313-438f-b8c8-cc5611384339")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e53f2ea7-9b26-4d2d-bff5-fd759b713752")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e8260563-481e-4af6-b761-e961a1983ace")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new NaryAssociationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.occurenceDep = new OccurenceSmDependency();
        this.occurenceDep.init("Occurence", this, metamodel.getMClass(NaryLink.MQNAME), 0, -1 );
        registerDependency(this.occurenceDep);
        
        this.naryEndDep = new NaryEndSmDependency();
        this.naryEndDep.init("NaryEnd", this, metamodel.getMClass(NaryAssociationEnd.MQNAME), 0, -1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.naryEndDep);
        
        this.linkToClassDep = new LinkToClassSmDependency();
        this.linkToClassDep.init("LinkToClass", this, metamodel.getMClass(ClassAssociation.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.linkToClassDep);
    }

    @objid ("3e156cb9-8d97-4113-9e14-4ab9dcbe13f8")
    public SmDependency getOccurenceDep() {
        if (this.occurenceDep == null) {
        	this.occurenceDep = this.getDependencyDef("Occurence");
        }
        return this.occurenceDep;
    }

    @objid ("f932a4be-eeeb-4080-9614-bf6866b31e8e")
    public SmDependency getNaryEndDep() {
        if (this.naryEndDep == null) {
        	this.naryEndDep = this.getDependencyDef("NaryEnd");
        }
        return this.naryEndDep;
    }

    @objid ("3e77136e-de55-481b-9b29-3aa0d679abcf")
    public SmDependency getLinkToClassDep() {
        if (this.linkToClassDep == null) {
        	this.linkToClassDep = this.getDependencyDef("LinkToClass");
        }
        return this.linkToClassDep;
    }

    @objid ("07a7ebf0-e4a2-4098-8a35-d64d29d1bef0")
    private static class NaryAssociationObjectFactory implements ISmObjectFactory {
        @objid ("847a04f6-e080-46fe-a271-6ac6a12260fd")
        private NaryAssociationSmClass smClass;

        @objid ("74d954d3-cc8e-4b42-afd2-2be01a54c535")
        public NaryAssociationObjectFactory(NaryAssociationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1f7b5306-bfea-4a37-bfc8-0fcc053036f4")
        @Override
        public ISmObjectData createData() {
            return new NaryAssociationData(this.smClass);
        }

        @objid ("c63c332a-699b-4e5c-95c5-198baab39cb0")
        @Override
        public SmObjectImpl createImpl() {
            return new NaryAssociationImpl();
        }

    }

    @objid ("977c7483-29bb-4c40-acb2-131e77617136")
    public static class OccurenceSmDependency extends SmMultipleDependency {
        @objid ("9b32bf32-28de-4b9d-8928-87559bd3a52e")
        private SmDependency symetricDep;

        @objid ("4b76bd71-a8f2-421c-bdaf-686acfd6f8fa")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NaryAssociationData)data).mOccurence != null)? ((NaryAssociationData)data).mOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("c4b78766-e736-4c3e-a52b-f20b09409b9d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NaryAssociationData) data).mOccurence = values;
        }

        @objid ("a8badf9b-9ed8-43a6-b8fd-ca236cdcd799")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("68560b36-0366-4916-bcdd-ee85105f6914")
    public static class NaryEndSmDependency extends SmMultipleDependency {
        @objid ("3d8c4ed2-fd4a-4fb6-98c5-e861e1c95d42")
        private SmDependency symetricDep;

        @objid ("d194e721-a7a0-47b2-b8ad-e7217920134d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NaryAssociationData)data).mNaryEnd != null)? ((NaryAssociationData)data).mNaryEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("a9841c6f-4d3b-48ae-8c67-a71c5a3a67cb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NaryAssociationData) data).mNaryEnd = values;
        }

        @objid ("66cb3d5d-5012-4bb7-8fde-a840e88a4d5f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryAssociationEndSmClass)this.getTarget()).getNaryAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("670b99f0-4a6d-44bf-922f-06dab5a87012")
    public static class LinkToClassSmDependency extends SmSingleDependency {
        @objid ("95e5c43e-aae1-4aea-9348-107a705c9b7c")
        private SmDependency symetricDep;

        @objid ("9c88994c-fdc9-4499-8a1a-ec744d09203f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryAssociationData) data).mLinkToClass;
        }

        @objid ("08dd561b-fcbd-4c53-ab95-993001440a3d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryAssociationData) data).mLinkToClass = value;
        }

        @objid ("ed8ef6d8-b63f-4058-a08a-bfb74c210189")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassAssociationSmClass)this.getTarget()).getNaryAssociationPartDep();
            }
            return this.symetricDep;
        }

    }

}
