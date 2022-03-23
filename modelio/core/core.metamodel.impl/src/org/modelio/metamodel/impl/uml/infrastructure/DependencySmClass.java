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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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

@objid ("0363e7b8-f4aa-486c-99ad-34c234137424")
public class DependencySmClass extends ModelElementSmClass {
    @objid ("1fa8a108-ffd5-4bce-80cc-9aeb1e9079b1")
    private SmDependency impactedDep;

    @objid ("03a48602-cbc6-4a9f-8c4b-932a67db359b")
    private SmDependency dependsOnDep;

    @objid ("ef8ac736-3e46-4e90-b1e5-6053e00f6c8e")
    public  DependencySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4e2d8892-81b2-43b5-a321-7b77f9fc325a")
    @Override
    public String getName() {
        return "Dependency";
        
    }

    @objid ("45b1a1f1-e0eb-4cb2-a942-6a1d89807bbc")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("dc507f64-3dd5-4497-8f47-a5a3ab30fbb3")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Dependency.class;
        
    }

    @objid ("f38c7348-7beb-4435-a9c5-685b5526c19d")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("6f511881-d4d2-441a-b75f-34baf963a3d8")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ad6c6acf-312b-4f12-ab4b-4e051f22e151")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new DependencyObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.impactedDep = new ImpactedSmDependency();
        this.impactedDep.init("Impacted", this, metamodel.getMClass(ModelElement.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.impactedDep);
        
        this.dependsOnDep = new DependsOnSmDependency();
        this.dependsOnDep.init("DependsOn", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.dependsOnDep);
        
        
    }

    @objid ("6d436f53-a07a-454d-b525-43b05ade843b")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("Impacted");
        }
        return this.impactedDep;
    }

    @objid ("1e2e520c-5b5d-40a7-bb91-021117d7385c")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("DependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("61852405-23c0-4869-b407-70ba0a079c2e")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("c16fdd68-3545-4462-a32f-ae7657f7c6d5")
    private static class DependencyObjectFactory implements ISmObjectFactory {
        @objid ("3292bae4-517a-4362-a0c1-829648b71c34")
        private DependencySmClass smClass;

        @objid ("9daf02e7-cbb0-4a31-a5f9-544e144b538a")
        public  DependencyObjectFactory(DependencySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d203f490-39e7-49b8-b176-10406601a65d")
        @Override
        public ISmObjectData createData() {
            return new DependencyData(this.smClass);
        }

        @objid ("817f1469-69d3-4ccc-978e-7c598d6a3f98")
        @Override
        public SmObjectImpl createImpl() {
            return new DependencyImpl();
        }

    }

    @objid ("b8bc052c-05ea-46a4-af66-746d62c47880")
    public static class DependsOnSmDependency extends SmSingleDependency {
        @objid ("7a0d9a91-5069-4d4e-b855-e4a95c0c988c")
        private SmDependency symetricDep;

        @objid ("a55b922e-739f-4d5b-b5c5-8f8b4883d35f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DependencyData) data).mDependsOn;
        }

        @objid ("85b4aa4c-e0ba-46c9-93ca-729cdb2caede")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DependencyData) data).mDependsOn = value;
        }

        @objid ("f3232bb5-17c0-4752-8638-14cac750cae4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getImpactedDependencyDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c8421b45-927d-4825-9a44-fd6aabb10c5b")
    public static class ImpactedSmDependency extends SmSingleDependency {
        @objid ("a6184af0-d562-4dd0-bc2f-6758196dd1e5")
        private SmDependency symetricDep;

        @objid ("d0463647-02cd-4071-b27b-50a3e40207a5")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DependencyData) data).mImpacted;
        }

        @objid ("ad1a2de7-5dad-48fc-a172-1c5b7e4977f9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DependencyData) data).mImpacted = value;
        }

        @objid ("097635c9-734f-4853-b74e-46426960a457")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getDependsOnDependencyDep();
            }
            return this.symetricDep;
            
        }

    }

}
