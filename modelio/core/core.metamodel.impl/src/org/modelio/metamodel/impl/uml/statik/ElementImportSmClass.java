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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
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
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("f1131a92-11a8-41a4-baeb-56e593167c82")
public class ElementImportSmClass extends UmlModelElementSmClass {
    @objid ("e225f5ce-0a1f-4d81-9369-69df1fa8745e")
    private SmAttribute visibilityAtt;

    @objid ("b94dc52e-e53f-41d5-a584-4cb2a73f139a")
    private SmDependency importingNameSpaceDep;

    @objid ("7d16e85a-55a4-4ce1-b889-6e3ed2c59791")
    private SmDependency importedElementDep;

    @objid ("ad1b8f3f-dec6-4737-9657-6abe7a742efc")
    private SmDependency importingOperationDep;

    @objid ("d7c8299b-baed-49ea-987b-ea7756a7237b")
    public  ElementImportSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("158a8592-605e-4b85-9a7f-99ae7661bed4")
    @Override
    public String getName() {
        return "ElementImport";
        
    }

    @objid ("0d4610a0-5d76-4e02-a11c-fb8b96f441ff")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a35390e7-0448-4d5a-b8f6-7633bf477166")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ElementImport.class;
        
    }

    @objid ("b10ca424-5e32-4fe0-98af-70591b6a8940")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("d3bb1e1b-6bef-4d98-8e43-fc056da95312")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("914b1935-3787-439b-9357-7f448e35834e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ElementImportObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.visibilityAtt = new VisibilitySmAttribute();
        this.visibilityAtt.init("Visibility", this, VisibilityMode.class );
        registerAttribute(this.visibilityAtt);
        
        
        // Initialize and register the SmDependency
        this.importingNameSpaceDep = new ImportingNameSpaceSmDependency();
        this.importingNameSpaceDep.init("ImportingNameSpace", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.importingNameSpaceDep);
        
        this.importedElementDep = new ImportedElementSmDependency();
        this.importedElementDep.init("ImportedElement", this, metamodel.getMClass(NameSpace.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.importedElementDep);
        
        this.importingOperationDep = new ImportingOperationSmDependency();
        this.importingOperationDep.init("ImportingOperation", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.importingOperationDep);
        
        
    }

    @objid ("e79ea572-3bd9-4a2a-8170-e8acf3232ef0")
    public SmAttribute getVisibilityAtt() {
        if (this.visibilityAtt == null) {
        	this.visibilityAtt = this.getAttributeDef("Visibility");
        }
        return this.visibilityAtt;
    }

    @objid ("19006ab4-8d78-4122-9ec3-ebf0a707e2c7")
    public SmDependency getImportingNameSpaceDep() {
        if (this.importingNameSpaceDep == null) {
        	this.importingNameSpaceDep = this.getDependencyDef("ImportingNameSpace");
        }
        return this.importingNameSpaceDep;
    }

    @objid ("40c57ada-09ba-4b01-b13a-ba86fa811538")
    public SmDependency getImportedElementDep() {
        if (this.importedElementDep == null) {
        	this.importedElementDep = this.getDependencyDef("ImportedElement");
        }
        return this.importedElementDep;
    }

    @objid ("81f3101f-8f1d-4de1-802b-e37b9e5ef350")
    public SmDependency getImportingOperationDep() {
        if (this.importingOperationDep == null) {
        	this.importingOperationDep = this.getDependencyDef("ImportingOperation");
        }
        return this.importingOperationDep;
    }

    @objid ("e1152889-c238-499d-9a18-008d54e71d7a")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("32e190a7-c510-4e4e-84e5-434396425161")
    private static class ElementImportObjectFactory implements ISmObjectFactory {
        @objid ("a5616302-bcd5-4ca5-b4a5-6810689af940")
        private ElementImportSmClass smClass;

        @objid ("0b8f5844-83bc-4c57-964c-2e0f0e59ba88")
        public  ElementImportObjectFactory(ElementImportSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2d06ae97-38ef-4b92-a1f8-fcb3deb04b50")
        @Override
        public ISmObjectData createData() {
            return new ElementImportData(this.smClass);
        }

        @objid ("06fd56b9-dae0-4cdd-ab94-d18487815f5a")
        @Override
        public SmObjectImpl createImpl() {
            return new ElementImportImpl();
        }

    }

    @objid ("53804d09-1d36-4a67-9b87-4b3591b09ae6")
    public static class VisibilitySmAttribute extends SmAttribute {
        @objid ("6ffa2035-6223-42c3-8735-00e5d6b49d4c")
        public Object getValue(ISmObjectData data) {
            return ((ElementImportData) data).mVisibility;
        }

        @objid ("25e8fd23-6ed8-4590-b7c9-12ff0f9ccd30")
        public void setValue(ISmObjectData data, Object value) {
            ((ElementImportData) data).mVisibility = value;
        }

    }

    @objid ("333334bd-a9b3-4991-905a-443ccd99346f")
    public static class ImportingNameSpaceSmDependency extends SmSingleDependency {
        @objid ("abfc6d0c-671f-4d48-9139-06d187713fb8")
        private SmDependency symetricDep;

        @objid ("e9c25c76-a4c5-4c8e-858c-a396550cc122")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ElementImportData) data).mImportingNameSpace;
        }

        @objid ("451c89bb-e13c-41a8-aa7c-4d12e96b0368")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ElementImportData) data).mImportingNameSpace = value;
        }

        @objid ("2e84615f-0db8-44b0-b9ee-10462b094f37")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getOwnedImportDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("b457ab0b-1943-4217-8dd1-a78a7572abe3")
    public static class ImportedElementSmDependency extends SmSingleDependency {
        @objid ("16ec0d54-a832-40f9-8530-79ba557fd42b")
        private SmDependency symetricDep;

        @objid ("8423677c-464e-4da0-9cc3-474ec07d4726")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ElementImportData) data).mImportedElement;
        }

        @objid ("ac3171a3-7bc7-48ef-92f4-940309636108")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ElementImportData) data).mImportedElement = value;
        }

        @objid ("7ecb26f8-a22a-4a43-af9e-7699ad8a8294")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getImportingDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("3e7a5220-d668-4ab8-9f5a-fb4178f24910")
    public static class ImportingOperationSmDependency extends SmSingleDependency {
        @objid ("3e7cb25b-72a7-4eaf-b52c-2f0d112c441e")
        private SmDependency symetricDep;

        @objid ("08d2923b-bb00-4d36-b952-430c2593aea5")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ElementImportData) data).mImportingOperation;
        }

        @objid ("277945a9-2a05-4830-a287-35eddfdf71e0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ElementImportData) data).mImportingOperation = value;
        }

        @objid ("c1bf81a5-8e08-40fd-bf96-2da1653b46f7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getOwnedImportDep();
            }
            return this.symetricDep;
            
        }

    }

}
