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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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

@objid ("e0ca2607-d7fa-4215-a81e-31bcf412a838")
public class MethodologicalLinkSmClass extends DependencySmClass {
    @objid ("9137a9cc-2844-462d-8f7e-61d61bb15353")
    private SmDependency externElementDep;

    @objid ("e1ce4e25-c9eb-4b66-aa14-a0f8b3c664ef")
    public  MethodologicalLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("531049a8-ef8f-4f7a-97dd-85b114669997")
    @Override
    public String getName() {
        return "MethodologicalLink";
        
    }

    @objid ("a85dad6b-94ba-488d-8f0b-e5523e9161d1")
    @Override
    public Version getVersion() {
        return new Version("2.1.01");
    }

    @objid ("2c6626d0-24b6-4eac-85ce-71a7617ad6dd")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MethodologicalLink.class;
        
    }

    @objid ("53fdebd4-fd0d-4cea-b86d-b682802580b8")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("84f83a56-76ef-4698-8c89-805e419ef1fc")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("37c130b6-bbe0-45ef-bf0a-7370962a930f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Dependency.MQNAME);
        this.registerFactory(new MethodologicalLinkObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.externElementDep = new ExternElementSmDependency();
        this.externElementDep.init("ExternElement", this, metamodel.getMClass(ExternElement.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.externElementDep);
        
        
    }

    @objid ("59a14ea4-1797-499c-8051-f62058e6e441")
    public SmDependency getExternElementDep() {
        if (this.externElementDep == null) {
        	this.externElementDep = this.getDependencyDef("ExternElement");
        }
        return this.externElementDep;
    }

    @objid ("0df8a199-e100-41e5-ba33-0b2904bbb8e1")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("07da5a0e-44b5-4280-a583-dde4bf83a630")
    private static class MethodologicalLinkObjectFactory implements ISmObjectFactory {
        @objid ("682e2b46-c61b-469c-bec8-c21e779f15c6")
        private MethodologicalLinkSmClass smClass;

        @objid ("5e347a6d-e8ca-40e7-bbd6-88dd0067103b")
        public  MethodologicalLinkObjectFactory(MethodologicalLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b69c7111-c58f-494a-b376-fe73141e5710")
        @Override
        public ISmObjectData createData() {
            return new MethodologicalLinkData(this.smClass);
        }

        @objid ("8f394ff8-c060-4ce4-a211-4d9b2bbf01ca")
        @Override
        public SmObjectImpl createImpl() {
            return new MethodologicalLinkImpl();
        }

    }

    @objid ("ed2be7f1-f212-4b4a-a21f-5364755f0d55")
    public static class ExternElementSmDependency extends SmSingleDependency {
        @objid ("28a50ca9-502a-433b-a6ab-95c59c8467b2")
        private SmDependency symetricDep;

        @objid ("78ae6a32-2fb2-4483-b463-7b3f68ef5ea0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MethodologicalLinkData) data).mExternElement;
        }

        @objid ("48e9bca0-cfef-426c-b800-082e536c8712")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MethodologicalLinkData) data).mExternElement = value;
        }

        @objid ("0e9a73ba-8dcf-4a57-9ba3-acedde5bf1e0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExternElementSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
