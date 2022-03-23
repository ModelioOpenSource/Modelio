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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("cabc3fc3-c50a-4f12-8364-eadf0fa37a73")
public class StructuralFeatureSmClass extends FeatureSmClass {
    @objid ("a94b9b3e-42cf-47e0-9796-07794187661e")
    private SmAttribute changeableAtt;

    @objid ("055cbaca-5747-4fb9-8e39-cc1a352cda09")
    private SmAttribute isDerivedAtt;

    @objid ("926ffa5b-03db-4098-8a43-966b5491e64f")
    private SmAttribute isOrderedAtt;

    @objid ("c4afaf65-ecdd-4862-91ca-ce8187ac463f")
    private SmAttribute isUniqueAtt;

    @objid ("2faf2bc8-3b22-4615-9be3-eac17d58e791")
    private SmAttribute multiplicityMinAtt;

    @objid ("a6af77dc-bf67-4951-a948-6ec4b4c38288")
    private SmAttribute multiplicityMaxAtt;

    @objid ("13d4c093-587e-455e-bad2-657f89c3b9dc")
    private SmDependency realizedInformationFlowDep;

    @objid ("06ac5a57-4350-4196-bd5b-e91cfbdc961f")
    public  StructuralFeatureSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c3932a2a-cf24-40e5-8941-15d1b900b3d5")
    @Override
    public String getName() {
        return "StructuralFeature";
        
    }

    @objid ("68df3be7-59e9-49b2-8548-c6e83b4aa554")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9c36f3ee-dcdb-4bb8-876c-580821cccf64")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StructuralFeature.class;
        
    }

    @objid ("eb0a2c13-2510-4b44-bae6-81c1eac131a4")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("2f92300e-29d6-485e-93ba-1c19ad7a0081")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("9a72c169-3435-4230-bd53-ba17665d5093")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Feature.MQNAME);
        this.registerFactory(new StructuralFeatureObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.changeableAtt = new ChangeableSmAttribute();
        this.changeableAtt.init("Changeable", this, KindOfAccess.class );
        registerAttribute(this.changeableAtt);
        
        this.isDerivedAtt = new IsDerivedSmAttribute();
        this.isDerivedAtt.init("IsDerived", this, Boolean.class );
        registerAttribute(this.isDerivedAtt);
        
        this.isOrderedAtt = new IsOrderedSmAttribute();
        this.isOrderedAtt.init("IsOrdered", this, Boolean.class );
        registerAttribute(this.isOrderedAtt);
        
        this.isUniqueAtt = new IsUniqueSmAttribute();
        this.isUniqueAtt.init("IsUnique", this, Boolean.class );
        registerAttribute(this.isUniqueAtt);
        
        this.multiplicityMinAtt = new MultiplicityMinSmAttribute();
        this.multiplicityMinAtt.init("MultiplicityMin", this, String.class );
        registerAttribute(this.multiplicityMinAtt);
        
        this.multiplicityMaxAtt = new MultiplicityMaxSmAttribute();
        this.multiplicityMaxAtt.init("MultiplicityMax", this, String.class );
        registerAttribute(this.multiplicityMaxAtt);
        
        
        // Initialize and register the SmDependency
        this.realizedInformationFlowDep = new RealizedInformationFlowSmDependency();
        this.realizedInformationFlowDep.init("RealizedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.realizedInformationFlowDep);
        
        
    }

    @objid ("665dbb48-1ab6-4f75-8322-89133d022ae4")
    public SmAttribute getChangeableAtt() {
        if (this.changeableAtt == null) {
        	this.changeableAtt = this.getAttributeDef("Changeable");
        }
        return this.changeableAtt;
    }

    @objid ("f0967b5d-2379-47b5-9fce-ca479d5db002")
    public SmAttribute getIsDerivedAtt() {
        if (this.isDerivedAtt == null) {
        	this.isDerivedAtt = this.getAttributeDef("IsDerived");
        }
        return this.isDerivedAtt;
    }

    @objid ("e4c5e325-ab84-44dd-91c7-1b45fee10547")
    public SmAttribute getIsOrderedAtt() {
        if (this.isOrderedAtt == null) {
        	this.isOrderedAtt = this.getAttributeDef("IsOrdered");
        }
        return this.isOrderedAtt;
    }

    @objid ("196a1814-7ba0-44dd-8a1f-bd6ed8304007")
    public SmAttribute getIsUniqueAtt() {
        if (this.isUniqueAtt == null) {
        	this.isUniqueAtt = this.getAttributeDef("IsUnique");
        }
        return this.isUniqueAtt;
    }

    @objid ("9cfc2823-e170-4064-bc9b-e7f209eebd9a")
    public SmAttribute getMultiplicityMinAtt() {
        if (this.multiplicityMinAtt == null) {
        	this.multiplicityMinAtt = this.getAttributeDef("MultiplicityMin");
        }
        return this.multiplicityMinAtt;
    }

    @objid ("fffabf5e-1a89-4cee-bdbc-5a947b0b7fe6")
    public SmAttribute getMultiplicityMaxAtt() {
        if (this.multiplicityMaxAtt == null) {
        	this.multiplicityMaxAtt = this.getAttributeDef("MultiplicityMax");
        }
        return this.multiplicityMaxAtt;
    }

    @objid ("7e379c2a-7bb8-4a09-854b-9235c968c84e")
    public SmDependency getRealizedInformationFlowDep() {
        if (this.realizedInformationFlowDep == null) {
        	this.realizedInformationFlowDep = this.getDependencyDef("RealizedInformationFlow");
        }
        return this.realizedInformationFlowDep;
    }

    @objid ("8a607818-28f3-40e3-a3db-c0369243d685")
    private static class StructuralFeatureObjectFactory implements ISmObjectFactory {
        @objid ("c54334a2-b3fa-4d59-8dee-b4ab95bfa793")
        private StructuralFeatureSmClass smClass;

        @objid ("59379ea6-9365-4eb8-b966-dba1487ae9f2")
        public  StructuralFeatureObjectFactory(StructuralFeatureSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3894949c-5400-43df-8498-544c6697f6ce")
        @Override
        public ISmObjectData createData() {
            return new StructuralFeatureData(this.smClass);
        }

        @objid ("c4301379-36c1-4c1a-9542-b5e72d5a4e64")
        @Override
        public SmObjectImpl createImpl() {
            return new StructuralFeatureImpl();
        }

    }

    @objid ("bc58f30a-9fd4-4ffd-a582-ea3cf433c56a")
    public static class ChangeableSmAttribute extends SmAttribute {
        @objid ("02fc4b1f-b2bb-4cb1-bbf9-ef8a2ba44fe3")
        public Object getValue(ISmObjectData data) {
            return ((StructuralFeatureData) data).mChangeable;
        }

        @objid ("ae0c596a-82f6-4f83-a3b6-e002e3534e10")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuralFeatureData) data).mChangeable = value;
        }

    }

    @objid ("07135451-fe1f-443e-9431-5cdbf690246a")
    public static class IsDerivedSmAttribute extends SmAttribute {
        @objid ("dc0140d5-eb29-4570-a6fd-de4256107fea")
        public Object getValue(ISmObjectData data) {
            return ((StructuralFeatureData) data).mIsDerived;
        }

        @objid ("cc3eaa21-e0a5-488f-b86a-5a9ac5edb0c7")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuralFeatureData) data).mIsDerived = value;
        }

    }

    @objid ("246bdcc0-318e-4885-a9e9-692e8f2d6665")
    public static class IsOrderedSmAttribute extends SmAttribute {
        @objid ("dcc82b6d-5457-4749-ba77-f2696dc970ab")
        public Object getValue(ISmObjectData data) {
            return ((StructuralFeatureData) data).mIsOrdered;
        }

        @objid ("7d37dc5f-cdf0-43f2-a31b-32044959f101")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuralFeatureData) data).mIsOrdered = value;
        }

    }

    @objid ("947c4cc3-0be8-4ec7-a026-29a54b235e0d")
    public static class IsUniqueSmAttribute extends SmAttribute {
        @objid ("8b40eae3-5698-4823-83fa-7eec392ca540")
        public Object getValue(ISmObjectData data) {
            return ((StructuralFeatureData) data).mIsUnique;
        }

        @objid ("c96f9aca-d38d-44ae-8495-b4d4381b87d8")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuralFeatureData) data).mIsUnique = value;
        }

    }

    @objid ("0d3c7616-841a-45eb-9316-cef4ecc8066a")
    public static class MultiplicityMinSmAttribute extends SmAttribute {
        @objid ("c0e69e4d-b1e6-41df-bdf1-af4c2f3438b8")
        public Object getValue(ISmObjectData data) {
            return ((StructuralFeatureData) data).mMultiplicityMin;
        }

        @objid ("2759e295-9743-41f4-a67e-f689789602dd")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuralFeatureData) data).mMultiplicityMin = value;
        }

    }

    @objid ("70ec2a7c-c59b-412d-831a-88addeb38d8d")
    public static class MultiplicityMaxSmAttribute extends SmAttribute {
        @objid ("32ded597-0391-4f21-896c-9bca2852b0a2")
        public Object getValue(ISmObjectData data) {
            return ((StructuralFeatureData) data).mMultiplicityMax;
        }

        @objid ("3a4a9cf2-1e1a-42c6-934b-b1f9590a9417")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuralFeatureData) data).mMultiplicityMax = value;
        }

    }

    @objid ("bc01aa95-32c3-457c-bfc7-c73ea25cb330")
    public static class RealizedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("b877b0c7-5038-49ca-aa82-8a50016dd85a")
        private SmDependency symetricDep;

        @objid ("5637e0c0-6b32-496a-904d-2dbcc4732582")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StructuralFeatureData)data).mRealizedInformationFlow != null)? ((StructuralFeatureData)data).mRealizedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("e68189f0-61f4-4eaf-a634-4bf40e366984")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StructuralFeatureData) data).mRealizedInformationFlow = values;
            
        }

        @objid ("f2c073bc-9795-422b-95f8-d6262439004b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getRealizingFeatureDep();
            }
            return this.symetricDep;
            
        }

    }

}
