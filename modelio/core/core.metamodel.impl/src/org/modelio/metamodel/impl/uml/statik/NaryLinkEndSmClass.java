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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
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

@objid ("a91d81a7-d547-490d-82a6-9d566149073e")
public class NaryLinkEndSmClass extends UmlModelElementSmClass {
    @objid ("af3c636c-aea5-4695-a58d-f1f5d01c5e12")
    private SmAttribute isOrderedAtt;

    @objid ("55510c13-1f18-4eaa-a878-ae7b1377f698")
    private SmAttribute isUniqueAtt;

    @objid ("1cdbd466-61f1-44be-a552-f8b2d32d4df4")
    private SmAttribute multiplicityMaxAtt;

    @objid ("989a00eb-b9ee-4e93-ba34-31bbdca04f24")
    private SmAttribute multiplicityMinAtt;

    @objid ("3f1c6565-4421-41f3-9163-a0d04aa90038")
    private SmDependency sourceDep;

    @objid ("9311856b-1f6b-4902-9c6a-a1bdba4e7909")
    private SmDependency naryLinkDep;

    @objid ("4fd8cc9e-b7ef-413d-9683-bd056e2e4cf5")
    private SmDependency consumerDep;

    @objid ("a8fd70c2-c94f-4ff4-b8a7-d34c74ade251")
    private SmDependency providerDep;

    @objid ("beebe1e9-d7e9-4529-90b6-92d0e9af7d01")
    public  NaryLinkEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a2c8dc00-ff83-4e1f-9937-d0948ec9049d")
    @Override
    public String getName() {
        return "NaryLinkEnd";
        
    }

    @objid ("59449f7c-5e5b-4682-b46a-3dbc0b157956")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("5efccb77-5057-41df-8cde-22c364d1bde0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NaryLinkEnd.class;
        
    }

    @objid ("a3be723e-6521-407d-a748-2ca07407ad61")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("24cfee14-a6c2-4793-b2bf-c94aa1275396")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("166db1d1-5543-4371-97f4-1422a2622291")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new NaryLinkEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isOrderedAtt = new IsOrderedSmAttribute();
        this.isOrderedAtt.init("IsOrdered", this, Boolean.class );
        registerAttribute(this.isOrderedAtt);
        
        this.isUniqueAtt = new IsUniqueSmAttribute();
        this.isUniqueAtt.init("IsUnique", this, Boolean.class );
        registerAttribute(this.isUniqueAtt);
        
        this.multiplicityMaxAtt = new MultiplicityMaxSmAttribute();
        this.multiplicityMaxAtt.init("MultiplicityMax", this, String.class );
        registerAttribute(this.multiplicityMaxAtt);
        
        this.multiplicityMinAtt = new MultiplicityMinSmAttribute();
        this.multiplicityMinAtt.init("MultiplicityMin", this, String.class );
        registerAttribute(this.multiplicityMinAtt);
        
        
        // Initialize and register the SmDependency
        this.sourceDep = new SourceSmDependency();
        this.sourceDep.init("Source", this, metamodel.getMClass(Instance.MQNAME), 1, 1 );
        registerDependency(this.sourceDep);
        
        this.naryLinkDep = new NaryLinkSmDependency();
        this.naryLinkDep.init("NaryLink", this, metamodel.getMClass(NaryLink.MQNAME), 0, 1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.naryLinkDep);
        
        this.consumerDep = new ConsumerSmDependency();
        this.consumerDep.init("Consumer", this, metamodel.getMClass(RequiredInterface.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.consumerDep);
        
        this.providerDep = new ProviderSmDependency();
        this.providerDep.init("Provider", this, metamodel.getMClass(ProvidedInterface.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.providerDep);
        
        
    }

    @objid ("929219b1-1304-4b4f-b82f-0b34a9e8148a")
    public SmAttribute getIsOrderedAtt() {
        if (this.isOrderedAtt == null) {
        	this.isOrderedAtt = this.getAttributeDef("IsOrdered");
        }
        return this.isOrderedAtt;
    }

    @objid ("3f202a98-a2f4-4a5e-a7ff-cef85363423f")
    public SmAttribute getIsUniqueAtt() {
        if (this.isUniqueAtt == null) {
        	this.isUniqueAtt = this.getAttributeDef("IsUnique");
        }
        return this.isUniqueAtt;
    }

    @objid ("c730b73d-052b-45cf-afde-f112f365145c")
    public SmAttribute getMultiplicityMaxAtt() {
        if (this.multiplicityMaxAtt == null) {
        	this.multiplicityMaxAtt = this.getAttributeDef("MultiplicityMax");
        }
        return this.multiplicityMaxAtt;
    }

    @objid ("618c173b-3a12-4c69-9df1-1a23d110c356")
    public SmAttribute getMultiplicityMinAtt() {
        if (this.multiplicityMinAtt == null) {
        	this.multiplicityMinAtt = this.getAttributeDef("MultiplicityMin");
        }
        return this.multiplicityMinAtt;
    }

    @objid ("ef78777e-5578-4e1c-819e-73c6aedb956b")
    public SmDependency getSourceDep() {
        if (this.sourceDep == null) {
        	this.sourceDep = this.getDependencyDef("Source");
        }
        return this.sourceDep;
    }

    @objid ("c4c80cf7-99be-4212-8262-de62f0168809")
    public SmDependency getNaryLinkDep() {
        if (this.naryLinkDep == null) {
        	this.naryLinkDep = this.getDependencyDef("NaryLink");
        }
        return this.naryLinkDep;
    }

    @objid ("f255de5a-c759-41f6-b246-75fd010e555c")
    public SmDependency getConsumerDep() {
        if (this.consumerDep == null) {
        	this.consumerDep = this.getDependencyDef("Consumer");
        }
        return this.consumerDep;
    }

    @objid ("d41c3313-1e16-4820-869d-9d42b926bda5")
    public SmDependency getProviderDep() {
        if (this.providerDep == null) {
        	this.providerDep = this.getDependencyDef("Provider");
        }
        return this.providerDep;
    }

    @objid ("5cecdcf9-803e-4063-b40a-d93d8d90db40")
    private static class NaryLinkEndObjectFactory implements ISmObjectFactory {
        @objid ("9aa123d3-ae93-44f2-9813-debc48ca30f1")
        private NaryLinkEndSmClass smClass;

        @objid ("0268462f-eb02-4d86-a486-ca4f0298f4b0")
        public  NaryLinkEndObjectFactory(NaryLinkEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("dce3b5c9-3fe7-412c-8b58-724f536d133e")
        @Override
        public ISmObjectData createData() {
            return new NaryLinkEndData(this.smClass);
        }

        @objid ("b3f2ca49-03e0-498d-823b-17edd7d8c149")
        @Override
        public SmObjectImpl createImpl() {
            return new NaryLinkEndImpl();
        }

    }

    @objid ("e023008b-2c85-4550-8bd7-0d95e7c9784e")
    public static class IsOrderedSmAttribute extends SmAttribute {
        @objid ("63686322-5cdc-4cd1-9d1a-1703e30b9fa8")
        public Object getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mIsOrdered;
        }

        @objid ("af66eaea-3b1b-4bc7-af45-8aca4f97868e")
        public void setValue(ISmObjectData data, Object value) {
            ((NaryLinkEndData) data).mIsOrdered = value;
        }

    }

    @objid ("5983ecc6-5889-42d5-ae1b-2532bed2d6ff")
    public static class IsUniqueSmAttribute extends SmAttribute {
        @objid ("5ba9dc72-1856-470d-950f-a80b68b482bd")
        public Object getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mIsUnique;
        }

        @objid ("c6d99bbd-5ee4-4d4a-8bd0-3a87901a48e8")
        public void setValue(ISmObjectData data, Object value) {
            ((NaryLinkEndData) data).mIsUnique = value;
        }

    }

    @objid ("996078f2-408a-4ca3-974e-863a898cbee1")
    public static class MultiplicityMaxSmAttribute extends SmAttribute {
        @objid ("a7ddb80c-e426-4ce1-b3cc-29d66acf8818")
        public Object getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mMultiplicityMax;
        }

        @objid ("159ea207-29f2-4c67-b478-4c809756e077")
        public void setValue(ISmObjectData data, Object value) {
            ((NaryLinkEndData) data).mMultiplicityMax = value;
        }

    }

    @objid ("43ad4289-bd48-4028-b9e2-7da2ad5e031c")
    public static class MultiplicityMinSmAttribute extends SmAttribute {
        @objid ("5b7e565b-9569-4584-9b1a-5ba2529ebd91")
        public Object getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mMultiplicityMin;
        }

        @objid ("604fffcf-bb86-40c5-bfb1-baa76315f21e")
        public void setValue(ISmObjectData data, Object value) {
            ((NaryLinkEndData) data).mMultiplicityMin = value;
        }

    }

    @objid ("f693c9a5-5e7f-4b89-961d-6a3a548d92b4")
    public static class SourceSmDependency extends SmSingleDependency {
        @objid ("9110fd89-1ea7-42c9-8137-db9a0f7ce561")
        private SmDependency symetricDep;

        @objid ("5eac34c6-8392-4fb8-ba89-eabad2539f56")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mSource;
        }

        @objid ("80e6103d-80fa-4be2-b7ec-3a9c4da95db5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryLinkEndData) data).mSource = value;
        }

        @objid ("640eabca-c626-4e26-9f62-e2a4ce4e9315")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getOwnedNaryEndDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("2b084fa9-bdbf-4d4c-9477-d5e080d85418")
    public static class NaryLinkSmDependency extends SmSingleDependency {
        @objid ("89bf73e1-d02d-43ab-bc82-bf6ec734ff82")
        private SmDependency symetricDep;

        @objid ("159aee52-885c-4b40-bac1-d4284317553c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mNaryLink;
        }

        @objid ("eae69437-4c3f-44af-911f-7dbf0e2ca55b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryLinkEndData) data).mNaryLink = value;
        }

        @objid ("972bfbfe-4366-4e46-8701-e47d20bf3791")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkSmClass)this.getTarget()).getNaryLinkEndDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a0aa2aa6-7219-4e73-878c-db2edf33bb2f")
    public static class ConsumerSmDependency extends SmSingleDependency {
        @objid ("caaa5449-e147-4f3d-bac7-96a325ae09c5")
        private SmDependency symetricDep;

        @objid ("938f4eb4-40ea-41a4-83c7-bd53a9f71948")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mConsumer;
        }

        @objid ("7572d4fc-5313-4301-a7c8-e09d634165fb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryLinkEndData) data).mConsumer = value;
        }

        @objid ("53720add-f238-4639-b7b5-308764211e9a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RequiredInterfaceSmClass)this.getTarget()).getNaryProviderDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("01bc6c3f-362d-4558-a279-3e58ac0f80af")
    public static class ProviderSmDependency extends SmSingleDependency {
        @objid ("378a30c5-1219-4e0b-8bb3-f6d2c79a5860")
        private SmDependency symetricDep;

        @objid ("4ef65c65-08fc-4264-9c77-fd74c7c9c414")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryLinkEndData) data).mProvider;
        }

        @objid ("0d8a1af1-5d00-4199-b071-65e26b119526")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryLinkEndData) data).mProvider = value;
        }

        @objid ("151a0b1d-4a75-44cf-af04-06e6537a68ef")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProvidedInterfaceSmClass)this.getTarget()).getNaryConsumerDep();
            }
            return this.symetricDep;
            
        }

    }

}
