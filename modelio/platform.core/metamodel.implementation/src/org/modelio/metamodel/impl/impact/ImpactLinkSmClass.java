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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("b84b39b5-e449-4ee7-9c7c-cdbbde7522c5")
    private SmDependency dependsOnDep;

    @objid ("ce0ffd02-693e-4249-b9c2-18fb037f8a51")
    private SmDependency impactedDep;

    @objid ("49898a77-51a5-487a-97fc-cb5a6d525c98")
    private SmDependency causesDep;

    @objid ("2e5fbd7b-af4a-461b-ba7d-87ec2de7bc04")
    private SmDependency ownerDep;

    @objid ("2a59b348-20fe-447b-9a57-5e4fbc082624")
    public ImpactLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("370c7df3-f79b-46b1-a283-bd4abc0d4a29")
    @Override
    public String getName() {
        return "ImpactLink";
    }

    @objid ("b1f61830-28d0-464e-bf92-07bf5e06ac78")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("e5ca25f6-f770-48a8-a5a3-7dbc1b2d2004")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactLink.class;
    }

    @objid ("1f90576c-93bc-4b23-91a4-6f5aa918a29a")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("590b0b92-b008-45e0-b779-30d1313b4765")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("76d8ec0d-95d4-4c34-bd93-ca9254a9315f")
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

    @objid ("461a2d1a-9b08-43cc-a238-4049736b1cb0")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("dependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("5fff4e9f-7e0e-445c-88fc-5618f35e8a05")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("impacted");
        }
        return this.impactedDep;
    }

    @objid ("973ee43d-9e5f-44ec-8f84-3fad80fcfed3")
    public SmDependency getCausesDep() {
        if (this.causesDep == null) {
        	this.causesDep = this.getDependencyDef("causes");
        }
        return this.causesDep;
    }

    @objid ("b9670211-5b52-46a6-82d6-176e45494b10")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("owner");
        }
        return this.ownerDep;
    }

    @objid ("5ea101a2-a038-4299-b641-01122c50dfa8")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("ac6d2fec-4670-4e4f-88ec-840da2acf7be")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("fc020c8d-a334-42a5-8e52-84839335983c")
    private static class ImpactLinkObjectFactory implements ISmObjectFactory {
        @objid ("ae5f0199-39db-4c23-ac24-26c9957f8efa")
        private ImpactLinkSmClass smClass;

        @objid ("5e0f30d5-9f5f-4000-addb-5f7e4e06b711")
        public ImpactLinkObjectFactory(ImpactLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8bd73e7d-6ba8-43ee-8e28-f0168d04ba35")
        @Override
        public ISmObjectData createData() {
            return new ImpactLinkData(this.smClass);
        }

        @objid ("0fc302c7-2f06-4d38-91a2-2ba6e009bc4a")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactLinkImpl();
        }

    }

    @objid ("cf21bef8-5751-463f-acb5-9400851674a3")
    public static class DependsOnSmDependency extends SmSingleDependency {
        @objid ("30a001cb-cb65-476a-98ac-76528189de2f")
        private SmDependency symetricDep;

        @objid ("4005399e-2fd8-4f79-b112-15b8ea8698e3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mDependsOn;
        }

        @objid ("f5eb1df9-7ced-4491-a18c-baf5458a6dfa")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mDependsOn = value;
        }

        @objid ("dc5adf8f-4743-46ad-9bb2-34eaee630586")
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
        @objid ("60e8098f-0ea5-4b41-a478-76041ac3cd7a")
        private SmDependency symetricDep;

        @objid ("4f2358dc-c54c-4a50-9a51-08e5ff9bddf0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mImpacted;
        }

        @objid ("97a13a08-fa47-43e5-96b1-df255ec138e8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mImpacted = value;
        }

        @objid ("e15827f4-a1b9-4cbb-a230-fae2381d5357")
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
        @objid ("aefb0e9c-d3f2-420d-9ed3-97a68275061a")
        private SmDependency symetricDep;

        @objid ("cc792c81-6002-4bc6-830d-a4bcc3796153")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ImpactLinkData)data).mCauses != null)? ((ImpactLinkData)data).mCauses:SmMultipleDependency.EMPTY;
        }

        @objid ("118dd389-a823-4007-bece-1d56914a0650")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ImpactLinkData) data).mCauses = values;
        }

        @objid ("a32e8f18-8ace-4390-a1fa-912d7021de40")
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
        @objid ("a3bc6779-ce2b-4f10-badb-9c36fe4b3cef")
        private SmDependency symetricDep;

        @objid ("cc620de1-4608-4de5-904e-446777f4f3ef")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ImpactLinkData) data).mOwner;
        }

        @objid ("92c30d50-92f3-4cec-98c3-5e6f0d465fcb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ImpactLinkData) data).mOwner = value;
        }

        @objid ("1bb161c0-445d-4e9e-9bbc-e0beb03224c8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactModelSmClass)this.getTarget()).getOwnedLinksDep();
            }
            return this.symetricDep;
        }

    }

}
