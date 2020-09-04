/* 
 * Copyright 2013-2018 Modeliosoft
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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impl.impact.ImpactLinkData;
import org.modelio.metamodel.impl.impact.ImpactModelSmClass;
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
    @objid ("b40180ed-2ccd-4825-a89d-7b0f431bdc52")
    private SmDependency dependsOnDep;

    @objid ("ac3f99bb-fdc0-4209-81b3-86ef54459c64")
    private SmDependency impactedDep;

    @objid ("977f4e02-f922-409a-85ad-bb788ad99024")
    private SmDependency causesDep;

    @objid ("82aa970b-d9e6-46d6-9b0d-09bfb81794b8")
    private SmDependency ownerDep;

    @objid ("2e5fd23b-77b7-45d8-b164-dfd829ac5a46")
    public ImpactLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1cb20047-0ce9-4822-97f6-52aaafbd2b91")
    @Override
    public String getName() {
        return "ImpactLink";
    }

    @objid ("f1d48d99-d5d2-4e3b-8e21-b08a694d1bfc")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("73ac7c8a-a866-4e59-af6f-a46840672230")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactLink.class;
    }

    @objid ("6cc353af-153b-409b-a3ae-48dd5467fa69")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("45a3b9ba-1997-479d-b1f5-1805d2bff647")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a4fa5a9a-34bf-4250-bd51-9cf211dcb66f")
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

    @objid ("4cbb1986-1fbb-4ab2-9289-f5ccef8798b8")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("dependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("80bc0368-a24a-4842-b8b7-877b5679b3e3")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("impacted");
        }
        return this.impactedDep;
    }

    @objid ("97a6ca1e-1c14-413e-8162-51ce56ffe827")
    public SmDependency getCausesDep() {
        if (this.causesDep == null) {
        	this.causesDep = this.getDependencyDef("causes");
        }
        return this.causesDep;
    }

    @objid ("dda8ec48-3116-41e1-af7a-b81caaa53a0a")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("owner");
        }
        return this.ownerDep;
    }

    @objid ("8082df07-c8c4-44b7-80f3-6922e5f07d49")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("4e0df78e-112a-4023-aa13-a7931c19b5f2")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("fc020c8d-a334-42a5-8e52-84839335983c")
    private static class ImpactLinkObjectFactory implements ISmObjectFactory {
        @objid ("b7285780-18b1-4308-b770-889fb9cec04b")
        private ImpactLinkSmClass smClass;

        @objid ("3f7ff105-5a8b-462f-ae2e-0851d03edb5a")
        public ImpactLinkObjectFactory(ImpactLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6d472a56-60c6-4588-84c5-0e516444c272")
        @Override
        public ISmObjectData createData() {
            return new ImpactLinkData(this.smClass);
        }

        @objid ("1660f553-8f81-4cf6-9dcf-2269d0351937")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactLinkImpl();
        }

    }

    @objid ("cf21bef8-5751-463f-acb5-9400851674a3")
    public static class DependsOnSmDependency extends SmSingleDependency {
        @objid ("938bab71-20f0-442a-b0ce-5951e0bed01d")
        private SmDependency symetricDep;

        @objid ("94cabf19-7421-4002-bbcc-1ac8d1c046a8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mDependsOn;
        }

        @objid ("f9b6e124-1508-47d9-961d-a972ec0ac9f4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mDependsOn = value;
        }

        @objid ("63986fcb-cbed-4399-a305-bb2560d41c47")
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
        @objid ("94efc236-94b1-4959-be48-bfed3af115cf")
        private SmDependency symetricDep;

        @objid ("2a5ebdde-9652-4654-9de8-3afe7ab37e16")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mImpacted;
        }

        @objid ("b2611f0d-8ff9-4c5a-a1f2-d349c5819084")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mImpacted = value;
        }

        @objid ("fb5e5d50-309c-49d0-8263-ef07708c8f8e")
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
        @objid ("cbb358d7-5521-4cf0-b6db-8320e4b6a20a")
        private SmDependency symetricDep;

        @objid ("94d29433-a9b4-4247-98df-4f9db88d9767")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactLinkData)data).mCauses != null)? ((ImpactLinkData)data).mCauses:SmMultipleDependency.EMPTY;
        }

        @objid ("fba47c76-5709-48ce-a898-63e16951c9db")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactLinkData) data).mCauses = values;
        }

        @objid ("10dc44d7-ec7e-41fb-ad08-a5d4d0e168ba")
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
        @objid ("8f540e04-98c9-45ad-b9ff-8b00b137764b")
        private SmDependency symetricDep;

        @objid ("067fe5f4-b706-44b3-8124-9b4524258f51")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mOwner;
        }

        @objid ("a83ae79a-8950-40fa-ab3c-02c7eea47264")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mOwner = value;
        }

        @objid ("32bb5424-aab2-4db6-8782-19bc6ad8e9e5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactModelSmClass)this.getTarget()).getOwnedLinksDep();
            }
            return this.symetricDep;
        }

    }

}
