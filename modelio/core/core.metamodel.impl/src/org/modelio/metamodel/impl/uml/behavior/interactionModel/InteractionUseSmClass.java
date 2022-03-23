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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
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
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("cc151a74-505a-48b1-a76f-acbe1981f23b")
public class InteractionUseSmClass extends InteractionFragmentSmClass {
    @objid ("33fc70c7-e676-428f-90df-b47930adbf77")
    private SmAttribute endLineNumberAtt;

    @objid ("53a3f045-884e-4ad4-a880-683c5e8ad168")
    private SmDependency actualGateDep;

    @objid ("dadd99d0-aca1-4959-bbb3-d8633bd24b5b")
    private SmDependency refersToDep;

    @objid ("f1bcff95-1de4-4b84-bae6-e7592f1ca5f8")
    public  InteractionUseSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f980f26a-f8e4-462d-a63b-89686e17243c")
    @Override
    public String getName() {
        return "InteractionUse";
        
    }

    @objid ("7a3325ea-bafa-4d89-bda1-a34260b9d18b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3f4a2c9a-a7af-462e-aace-1d897fcf082f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InteractionUse.class;
        
    }

    @objid ("383c449c-1535-4c0b-a554-b0fbada918af")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("cff1414b-c302-4d0a-8192-36868341fdbc")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("5eaf0b93-e15b-4310-a99b-741517e2c7ab")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InteractionFragment.MQNAME);
        this.registerFactory(new InteractionUseObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.endLineNumberAtt = new EndLineNumberSmAttribute();
        this.endLineNumberAtt.init("EndLineNumber", this, Integer.class );
        registerAttribute(this.endLineNumberAtt);
        
        
        // Initialize and register the SmDependency
        this.actualGateDep = new ActualGateSmDependency();
        this.actualGateDep.init("ActualGate", this, metamodel.getMClass(Gate.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.actualGateDep);
        
        this.refersToDep = new RefersToSmDependency();
        this.refersToDep.init("RefersTo", this, metamodel.getMClass(Interaction.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.refersToDep);
        
        
    }

    @objid ("a6b68a0c-5503-4190-a9bc-99227a3565b3")
    public SmAttribute getEndLineNumberAtt() {
        if (this.endLineNumberAtt == null) {
        	this.endLineNumberAtt = this.getAttributeDef("EndLineNumber");
        }
        return this.endLineNumberAtt;
    }

    @objid ("c19cf5c2-bcc0-462e-a3a4-b26ce5f2ca03")
    public SmDependency getActualGateDep() {
        if (this.actualGateDep == null) {
        	this.actualGateDep = this.getDependencyDef("ActualGate");
        }
        return this.actualGateDep;
    }

    @objid ("54cebb04-ae1f-49ef-9935-bdd364347343")
    public SmDependency getRefersToDep() {
        if (this.refersToDep == null) {
        	this.refersToDep = this.getDependencyDef("RefersTo");
        }
        return this.refersToDep;
    }

    @objid ("9c714291-628e-495d-a223-1a9050bef7e0")
    private static class InteractionUseObjectFactory implements ISmObjectFactory {
        @objid ("1351c26b-fe86-43ed-b5bc-22998372b0d1")
        private InteractionUseSmClass smClass;

        @objid ("2ac9eac9-d82a-43d9-b256-dc39f99da835")
        public  InteractionUseObjectFactory(InteractionUseSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2e033d2b-e871-4af5-8022-6ade16d3bbde")
        @Override
        public ISmObjectData createData() {
            return new InteractionUseData(this.smClass);
        }

        @objid ("1626bad9-f94b-4ec8-916c-eed940a34372")
        @Override
        public SmObjectImpl createImpl() {
            return new InteractionUseImpl();
        }

    }

    @objid ("403d7957-db67-495f-b44f-09eb2d72f808")
    public static class EndLineNumberSmAttribute extends SmAttribute {
        @objid ("cd371d58-753c-4f85-a01e-5c9f11ba16fa")
        public Object getValue(ISmObjectData data) {
            return ((InteractionUseData) data).mEndLineNumber;
        }

        @objid ("a923403d-3778-40bf-8845-4253dc8b7b73")
        public void setValue(ISmObjectData data, Object value) {
            ((InteractionUseData) data).mEndLineNumber = value;
        }

    }

    @objid ("4bf53c80-60ea-4450-b729-8a8138be8a2a")
    public static class ActualGateSmDependency extends SmMultipleDependency {
        @objid ("589ad44a-bdb5-4d44-87c9-c0c4e9614519")
        private SmDependency symetricDep;

        @objid ("2a64a2e4-4af2-415e-8665-c5f858c8855b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionUseData)data).mActualGate != null)? ((InteractionUseData)data).mActualGate:SmMultipleDependency.EMPTY;
        }

        @objid ("ffdd3011-145e-476e-819c-12cd4b2692ea")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionUseData) data).mActualGate = values;
            
        }

        @objid ("ffb637b1-aaa1-4007-a096-c9ddab3dd59f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GateSmClass)this.getTarget()).getOwnerUseDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("eeab4f67-e9af-47ff-a002-5385e7ad5e2f")
    public static class RefersToSmDependency extends SmSingleDependency {
        @objid ("85a1bd06-3750-471d-8f4b-d5902cd9ea76")
        private SmDependency symetricDep;

        @objid ("e0c674cd-e030-4656-9401-b424e589ea3e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InteractionUseData) data).mRefersTo;
        }

        @objid ("2de7a36c-0729-406d-8b15-2fd3db7ed4b2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InteractionUseData) data).mRefersTo = value;
        }

        @objid ("f9b68076-3fd8-4de1-be45-d03786a4c925")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionSmClass)this.getTarget()).getReferedUseDep();
            }
            return this.symetricDep;
            
        }

    }

}
