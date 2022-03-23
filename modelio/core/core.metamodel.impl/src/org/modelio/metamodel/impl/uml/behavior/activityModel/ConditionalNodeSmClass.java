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

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
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

@objid ("d1292d23-82a1-4c79-bd74-1817c49e9708")
public class ConditionalNodeSmClass extends StructuredActivityNodeSmClass {
    @objid ("5e016470-b85c-43ec-b28e-3815f355c6c9")
    private SmAttribute isDeterminateAtt;

    @objid ("109bcd26-0dfd-4f96-9814-f09530829060")
    private SmAttribute isAssuredAtt;

    @objid ("0de3660f-f774-497e-a31a-1757154b0f52")
    private SmDependency ownedClauseDep;

    @objid ("3fec8d50-6020-4186-91cf-e4a3a0785bfb")
    public  ConditionalNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("13f443b5-3659-4182-b03b-a3fb1777d91b")
    @Override
    public String getName() {
        return "ConditionalNode";
        
    }

    @objid ("5b49fda4-12f2-4f30-bf2b-2344a982e35d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f390134f-4dee-4b36-b7dd-6699370368cf")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ConditionalNode.class;
        
    }

    @objid ("87e9612a-a887-4177-a9e8-b3c091b52476")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("b2348342-5953-470a-a98f-f0f152c24387")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("2a7def09-aee7-41ce-9eec-af9d5d49085b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StructuredActivityNode.MQNAME);
        this.registerFactory(new ConditionalNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isDeterminateAtt = new IsDeterminateSmAttribute();
        this.isDeterminateAtt.init("IsDeterminate", this, Boolean.class );
        registerAttribute(this.isDeterminateAtt);
        
        this.isAssuredAtt = new IsAssuredSmAttribute();
        this.isAssuredAtt.init("IsAssured", this, Boolean.class );
        registerAttribute(this.isAssuredAtt);
        
        
        // Initialize and register the SmDependency
        this.ownedClauseDep = new OwnedClauseSmDependency();
        this.ownedClauseDep.init("OwnedClause", this, metamodel.getMClass(Clause.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedClauseDep);
        
        
    }

    @objid ("ce0d2039-e7b3-4c44-b223-6c95c99a22a9")
    public SmAttribute getIsDeterminateAtt() {
        if (this.isDeterminateAtt == null) {
        	this.isDeterminateAtt = this.getAttributeDef("IsDeterminate");
        }
        return this.isDeterminateAtt;
    }

    @objid ("9d6d08e5-94cc-4c92-91ec-2be9836a891c")
    public SmAttribute getIsAssuredAtt() {
        if (this.isAssuredAtt == null) {
        	this.isAssuredAtt = this.getAttributeDef("IsAssured");
        }
        return this.isAssuredAtt;
    }

    @objid ("6be1a2f6-4432-4ee0-89b7-fc47ebc4fa72")
    public SmDependency getOwnedClauseDep() {
        if (this.ownedClauseDep == null) {
        	this.ownedClauseDep = this.getDependencyDef("OwnedClause");
        }
        return this.ownedClauseDep;
    }

    @objid ("185b454e-d285-4032-9bda-5e8ec751211b")
    private static class ConditionalNodeObjectFactory implements ISmObjectFactory {
        @objid ("df6b660b-3bb8-4738-a03c-e6bac57e9e76")
        private ConditionalNodeSmClass smClass;

        @objid ("13029773-8c93-4c9a-a47e-7b8f56cc2538")
        public  ConditionalNodeObjectFactory(ConditionalNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ad9a83c0-466a-484a-8001-885053eddb62")
        @Override
        public ISmObjectData createData() {
            return new ConditionalNodeData(this.smClass);
        }

        @objid ("15f8da71-7f4c-413c-a23a-67a11aca0b6c")
        @Override
        public SmObjectImpl createImpl() {
            return new ConditionalNodeImpl();
        }

    }

    @objid ("3b3923a1-6bc9-476d-9338-34d0dcdf3f95")
    public static class IsDeterminateSmAttribute extends SmAttribute {
        @objid ("548d0d98-eb7f-4eb5-a1e6-353dd0e40fbf")
        public Object getValue(ISmObjectData data) {
            return ((ConditionalNodeData) data).mIsDeterminate;
        }

        @objid ("a23d0431-d36d-4c78-b405-1d83116fe700")
        public void setValue(ISmObjectData data, Object value) {
            ((ConditionalNodeData) data).mIsDeterminate = value;
        }

    }

    @objid ("428fa9b7-d1aa-469a-aa1e-27669eb37407")
    public static class IsAssuredSmAttribute extends SmAttribute {
        @objid ("7a7cb475-49df-49b9-b1e0-78a72580c6fb")
        public Object getValue(ISmObjectData data) {
            return ((ConditionalNodeData) data).mIsAssured;
        }

        @objid ("37d1bbc4-5155-4111-a1ec-8675d8fa8b5c")
        public void setValue(ISmObjectData data, Object value) {
            ((ConditionalNodeData) data).mIsAssured = value;
        }

    }

    @objid ("69eaf755-9839-4775-85c3-bbc038c347de")
    public static class OwnedClauseSmDependency extends SmMultipleDependency {
        @objid ("fb734541-d762-4675-9d3c-5a3888295ed6")
        private SmDependency symetricDep;

        @objid ("e5a98018-cdbc-4928-b53e-7a6c1f0e7c77")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ConditionalNodeData)data).mOwnedClause != null)? ((ConditionalNodeData)data).mOwnedClause:SmMultipleDependency.EMPTY;
        }

        @objid ("bdd7c82e-f315-4002-8df6-a554e8161024")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ConditionalNodeData) data).mOwnedClause = values;
            
        }

        @objid ("d3596f2e-69cd-4702-92ab-1e8db57cf8c1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClauseSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
