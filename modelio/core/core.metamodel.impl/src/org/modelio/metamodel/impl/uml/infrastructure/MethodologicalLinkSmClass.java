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
    @objid ("8303959a-923f-4bbd-b140-7acfe3adf5ac")
    private SmDependency externElementDep;

    @objid ("bb2c4ed2-25c9-4c87-bc8e-a78659bffc99")
    public  MethodologicalLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e86aef39-f233-4a21-a29a-64712927acd7")
    @Override
    public String getName() {
        return "MethodologicalLink";
        
    }

    @objid ("ed85375f-cbd4-4b31-8b50-bae2d55bd893")
    @Override
    public Version getVersion() {
        return new Version("2.1.01");
    }

    @objid ("5f9640c3-1eb3-4945-af7f-f9495ad92e5a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MethodologicalLink.class;
        
    }

    @objid ("f03b0b98-e5d3-47d3-88bf-87f1e4c94f5a")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("d5449c47-8c35-4ae4-9f07-2c526d49a29d")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("d7165041-26b2-447f-abb9-625dea0b86a8")
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

    @objid ("84174731-bafa-4105-9f2e-214c5c2a634f")
    public SmDependency getExternElementDep() {
        if (this.externElementDep == null) {
        	this.externElementDep = this.getDependencyDef("ExternElement");
        }
        return this.externElementDep;
    }

    @objid ("52aff06f-2aa5-49d0-b658-1292f8528d9b")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("07da5a0e-44b5-4280-a583-dde4bf83a630")
    private static class MethodologicalLinkObjectFactory implements ISmObjectFactory {
        @objid ("e5fa3120-963c-42dd-b106-915b93f0c1d8")
        private MethodologicalLinkSmClass smClass;

        @objid ("4d4ae13c-da08-427f-823e-303b67d70ced")
        public  MethodologicalLinkObjectFactory(MethodologicalLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("95557ca1-b16c-458e-b825-9b82e6d57877")
        @Override
        public ISmObjectData createData() {
            return new MethodologicalLinkData(this.smClass);
        }

        @objid ("13813ee6-23f0-4244-aa61-408f6e9188ba")
        @Override
        public SmObjectImpl createImpl() {
            return new MethodologicalLinkImpl();
        }

    }

    @objid ("ed2be7f1-f212-4b4a-a21f-5364755f0d55")
    public static class ExternElementSmDependency extends SmSingleDependency {
        @objid ("55f1dd2a-460e-4780-b746-9e979bf5af5b")
        private SmDependency symetricDep;

        @objid ("caf01076-f76c-4cb4-8ce0-156abbceebac")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MethodologicalLinkData) data).mExternElement;
        }

        @objid ("401a288d-65ff-4e27-8101-df67de4f5213")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MethodologicalLinkData) data).mExternElement = value;
        }

        @objid ("b921a732-51a1-4ba4-942e-14c161e286f7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExternElementSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
