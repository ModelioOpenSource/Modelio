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

package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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

@objid ("44c767a5-c621-4c0d-b26c-a6c6181d4402")
public class ImpactLinkSmClass extends ModelElementSmClass {
    @objid ("ec510a93-0a91-4392-856e-954a75d5e246")
    private SmDependency dependsOnDep;

    @objid ("e075eb18-2ad3-447f-b334-3f5fae82e134")
    private SmDependency impactedDep;

    @objid ("90a0cc82-9995-4ffa-9120-62bfeb54e257")
    private SmDependency causesDep;

    @objid ("312282aa-afac-4925-b5be-dd957d62c41f")
    private SmDependency ownerDep;

    @objid ("7bdd8674-6c0d-4d19-a660-f9ae486179af")
    public  ImpactLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ff0fd955-25d3-4dd7-931a-2690b251f7b1")
    @Override
    public String getName() {
        return "ImpactLink";
        
    }

    @objid ("27d72480-970d-4e39-b0b3-42d0c3cccdf3")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("bf5036e1-2086-4d8f-b238-b4bd159cbf75")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactLink.class;
        
    }

    @objid ("45fab8a0-64df-435e-a34f-9a1b50d76e67")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("25a306f4-3572-4986-8809-e7b43d745b46")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("c16ba547-dafb-43ed-80cf-83a9d7fe42a9")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ImpactLinkObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.dependsOnDep = new DependsOnSmDependency();
        this.dependsOnDep.init("dependsOn", this, metamodel.getMClass(ModelElement.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.dependsOnDep);
        
        this.impactedDep = new ImpactedSmDependency();
        this.impactedDep.init("impacted", this, metamodel.getMClass(ModelElement.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE, SmDirective.SMCDPARTOF);
        registerDependency(this.impactedDep);
        
        this.causesDep = new CausesSmDependency();
        this.causesDep.init("causes", this, metamodel.getMClass(Element.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.causesDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("owner", this, metamodel.getMClass(ImpactModel.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.ownerDep);
        
        
    }

    @objid ("5562242f-5220-4306-aa45-9295cc955f31")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("dependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("4c4a929f-41cc-439a-9ae9-57d6bd4ca5c9")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("impacted");
        }
        return this.impactedDep;
    }

    @objid ("a6cd8a47-94cd-4c38-a7da-de2ec2c1a203")
    public SmDependency getCausesDep() {
        if (this.causesDep == null) {
        	this.causesDep = this.getDependencyDef("causes");
        }
        return this.causesDep;
    }

    @objid ("b6dd4dbe-eb6a-4b70-bdb3-7c72a127e6e5")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("owner");
        }
        return this.ownerDep;
    }

    @objid ("43646cc8-4817-4ca7-9a4c-6212e570d287")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("e348270a-e42b-4390-b0a8-79529c28c5be")
    @Override
    public boolean areOrphansAllowed() {
        return true;
        
    }

    @objid ("fc020c8d-a334-42a5-8e52-84839335983c")
    private static class ImpactLinkObjectFactory implements ISmObjectFactory {
        @objid ("9804060d-f066-44ee-8474-b180e1a3f7c0")
        private ImpactLinkSmClass smClass;

        @objid ("e2d3abef-672c-489c-97b8-51372250e929")
        public  ImpactLinkObjectFactory(ImpactLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("afacf104-ea64-4fc0-8310-e73dbd95e493")
        @Override
        public ISmObjectData createData() {
            return new ImpactLinkData(this.smClass);
        }

        @objid ("ef1fe8a2-a41f-48e8-a463-d32a355a2f5f")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactLinkImpl();
        }

    }

    @objid ("cf21bef8-5751-463f-acb5-9400851674a3")
    public static class DependsOnSmDependency extends SmSingleDependency {
        @objid ("552beb5a-ce29-41c4-99ec-d89b26d4c377")
        private SmDependency symetricDep;

        @objid ("ccc99c31-9e51-492c-94e4-88d8a8424620")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mDependsOn;
        }

        @objid ("3d8f697e-e3dc-4ad3-84fe-c08c0c279055")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mDependsOn = value;
        }

        @objid ("5dd007a1-429d-4d3b-9a12-7d059e99942c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getImpactImpactedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("f24f4534-4820-4ed5-b0a1-9a39cb56f6f5")
    public static class ImpactedSmDependency extends SmSingleDependency {
        @objid ("6bdddfa0-6d0d-475b-8f7c-4fc291749e87")
        private SmDependency symetricDep;

        @objid ("5c6119aa-0fa5-4ac0-ad32-d2a7df4e3059")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mImpacted;
        }

        @objid ("7f588894-a579-4da0-9443-51bc8c3f8ddc")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mImpacted = value;
        }

        @objid ("b0e009f5-3c0c-421e-a44d-1170c2e94c4e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getImpactDependsOnDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("0c2804e1-272e-4fb1-8e52-14e49cdfd6c1")
    public static class CausesSmDependency extends SmMultipleDependency {
        @objid ("8d3e633f-db82-4435-af11-c85eb035c5e6")
        private SmDependency symetricDep;

        @objid ("1a613b7f-c660-4056-87f6-c1c78ee34e18")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactLinkData)data).mCauses != null)? ((ImpactLinkData)data).mCauses:SmMultipleDependency.EMPTY;
        }

        @objid ("669a9c36-0100-4b57-939c-f63971442a46")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactLinkData) data).mCauses = values;
            
        }

        @objid ("b27587f9-9533-4cf4-a95e-19a1581ce2dd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ElementSmClass)this.getTarget()).getCausedImpactDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("62820774-1a4a-4baa-9f72-6b797091b405")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("f87cde07-3bcc-4cb2-97af-3e270d731a69")
        private SmDependency symetricDep;

        @objid ("10d17f4b-fb66-4908-9dc7-4e088b280bf4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mOwner;
        }

        @objid ("bf32ff18-84c8-4a18-be7c-c4229d8494b2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mOwner = value;
        }

        @objid ("3a05dae6-610b-4937-b204-7a772a211246")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactModelSmClass)this.getTarget()).getOwnedLinksDep();
            }
            return this.symetricDep;
            
        }

    }

}
