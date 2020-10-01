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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassAssociationData;
import org.modelio.metamodel.impl.uml.statik.ClassSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociation;
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

@objid ("0534b2ae-67e6-4bca-8aef-baaef28233e1")
public class ClassAssociationSmClass extends UmlModelElementSmClass {
    @objid ("af61f757-5b5e-428f-bd8e-48c726b19104")
    private SmDependency naryAssociationPartDep;

    @objid ("4d150e2c-9d93-4157-89b1-084868fb9fa7")
    private SmDependency classPartDep;

    @objid ("3a14a4e5-7586-4b04-a805-870ec7a8fc58")
    private SmDependency associationPartDep;

    @objid ("7a8b4540-b750-464f-be07-24053905fe20")
    public ClassAssociationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e650ca67-9fd3-4ac3-aec1-2bab1d7ab6aa")
    @Override
    public String getName() {
        return "ClassAssociation";
    }

    @objid ("6faf3e90-cb17-4d20-968d-74a01cfbd427")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("051fd9d5-64ea-4764-b726-5e1c50056665")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ClassAssociation.class;
    }

    @objid ("f076db2d-c056-47c1-951a-35248e34dc8a")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6ab8dfcc-8ea9-4a18-9493-73a172884ae7")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("2d1c657e-b0c3-4a3f-9549-e5ece27a3c98")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ClassAssociationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.naryAssociationPartDep = new NaryAssociationPartSmDependency();
        this.naryAssociationPartDep.init("NaryAssociationPart", this, metamodel.getMClass(NaryAssociation.MQNAME), 0, 1 );
        registerDependency(this.naryAssociationPartDep);
        
        this.classPartDep = new ClassPartSmDependency();
        this.classPartDep.init("ClassPart", this, metamodel.getMClass(Class.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.classPartDep);
        
        this.associationPartDep = new AssociationPartSmDependency();
        this.associationPartDep.init("AssociationPart", this, metamodel.getMClass(Association.MQNAME), 0, 1 );
        registerDependency(this.associationPartDep);
    }

    @objid ("a2bba090-3aef-4eff-aa3f-4641ab9ed5a7")
    public SmDependency getNaryAssociationPartDep() {
        if (this.naryAssociationPartDep == null) {
        	this.naryAssociationPartDep = this.getDependencyDef("NaryAssociationPart");
        }
        return this.naryAssociationPartDep;
    }

    @objid ("f7700ffd-f6b1-4e67-9b42-7f346e7a52b7")
    public SmDependency getClassPartDep() {
        if (this.classPartDep == null) {
        	this.classPartDep = this.getDependencyDef("ClassPart");
        }
        return this.classPartDep;
    }

    @objid ("8225c92b-336a-4fd9-8531-9dcbb991c53c")
    public SmDependency getAssociationPartDep() {
        if (this.associationPartDep == null) {
        	this.associationPartDep = this.getDependencyDef("AssociationPart");
        }
        return this.associationPartDep;
    }

    @objid ("fcdbfec2-c6d9-4b5b-a21c-509dcada5f94")
    private static class ClassAssociationObjectFactory implements ISmObjectFactory {
        @objid ("8f16421f-e692-4d3c-ac53-2ccad5201f94")
        private ClassAssociationSmClass smClass;

        @objid ("51d914a1-7d6b-437f-be10-bf400c0b8ce0")
        public ClassAssociationObjectFactory(ClassAssociationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b0b5a8bd-7631-41c2-ad7c-5d193fbd4f04")
        @Override
        public ISmObjectData createData() {
            return new ClassAssociationData(this.smClass);
        }

        @objid ("3ba5259c-9cc7-45ef-803c-542b9e214c08")
        @Override
        public SmObjectImpl createImpl() {
            return new ClassAssociationImpl();
        }

    }

    @objid ("09a56318-009a-4046-b7aa-b3d7d27e1070")
    public static class NaryAssociationPartSmDependency extends SmSingleDependency {
        @objid ("1bf4c0c8-a5fb-4c07-985d-ca3ccd54d41d")
        private SmDependency symetricDep;

        @objid ("639e3b5e-f8ed-404a-b46e-5e591da73946")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ClassAssociationData) data).mNaryAssociationPart;
        }

        @objid ("3073c81b-e30b-4c20-bb2b-e11fb3165de6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ClassAssociationData) data).mNaryAssociationPart = value;
        }

        @objid ("124eb9d0-fa81-48ab-98e3-132efe5010de")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryAssociationSmClass)this.getTarget()).getLinkToClassDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("db401142-043b-46ce-93f1-650f07f4cde8")
    public static class ClassPartSmDependency extends SmSingleDependency {
        @objid ("f34e5cbc-6956-41d6-b3c6-4ef5371e92c6")
        private SmDependency symetricDep;

        @objid ("9776e952-abb7-4597-ad6b-4555e262e29b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ClassAssociationData) data).mClassPart;
        }

        @objid ("216d25e5-e488-4a33-b3f0-730273028984")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ClassAssociationData) data).mClassPart = value;
        }

        @objid ("c957ee1c-27bd-4115-9231-e7f15224d913")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassSmClass)this.getTarget()).getLinkToAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("df2b7335-0752-4be1-8743-9146a96beea4")
    public static class AssociationPartSmDependency extends SmSingleDependency {
        @objid ("68cb1600-ea0b-42a7-b501-e43d1c43d00d")
        private SmDependency symetricDep;

        @objid ("900fa2e2-707c-43fc-83c9-9c3c776edda0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ClassAssociationData) data).mAssociationPart;
        }

        @objid ("7153b69d-b563-4105-9c9f-0fdc772fbc18")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ClassAssociationData) data).mAssociationPart = value;
        }

        @objid ("f82b51fb-8106-4da3-bc91-cfe52a0de7d9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationSmClass)this.getTarget()).getLinkToClassDep();
            }
            return this.symetricDep;
        }

    }

}
