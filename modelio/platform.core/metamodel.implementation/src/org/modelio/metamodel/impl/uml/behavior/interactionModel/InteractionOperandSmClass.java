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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.CombinedFragmentSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionOperandData;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
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

@objid ("c9d77df3-ebef-478b-a2d2-108d7bdf7579")
public class InteractionOperandSmClass extends InteractionFragmentSmClass {
    @objid ("eb1ab37d-4eeb-4b50-9f5d-2e114ab5f806")
    private SmAttribute guardAtt;

    @objid ("e0d426f5-8a83-4330-99d2-211a2efc362d")
    private SmAttribute endLineNumberAtt;

    @objid ("606dd67c-654e-449c-9381-e642411eb613")
    private SmDependency fragmentDep;

    @objid ("55aa54b0-5279-4de9-ac67-21e88d95b9ea")
    private SmDependency ownerFragmentDep;

    @objid ("76e94735-c76d-4b0c-a5f1-c0d54feba1dd")
    public InteractionOperandSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9e8d09c5-e8f9-47e4-bdd5-ca3121eef1f1")
    @Override
    public String getName() {
        return "InteractionOperand";
    }

    @objid ("fc381694-ff18-4405-a3f7-ace838336d17")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c9cb3ba8-40bf-4672-9f04-b7857ed4381c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InteractionOperand.class;
    }

    @objid ("5e9e00cd-db70-4434-994f-17a2459dfd29")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("79f17705-67ef-4475-9bf4-c8e250a507db")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("67c079ac-0dd8-4c5d-a856-6cf664f3b99c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InteractionFragment.MQNAME);
        this.registerFactory(new InteractionOperandObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.guardAtt = new GuardSmAttribute();
        this.guardAtt.init("Guard", this, String.class );
        registerAttribute(this.guardAtt);
        
        this.endLineNumberAtt = new EndLineNumberSmAttribute();
        this.endLineNumberAtt.init("EndLineNumber", this, Integer.class );
        registerAttribute(this.endLineNumberAtt);
        
        
        // Initialize and register the SmDependency
        this.fragmentDep = new FragmentSmDependency();
        this.fragmentDep.init("Fragment", this, metamodel.getMClass(InteractionFragment.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.fragmentDep);
        
        this.ownerFragmentDep = new OwnerFragmentSmDependency();
        this.ownerFragmentDep.init("OwnerFragment", this, metamodel.getMClass(CombinedFragment.MQNAME), 0, 1 );
        registerDependency(this.ownerFragmentDep);
    }

    @objid ("3d5589e1-75eb-45d3-bb6e-fcb2981b96ac")
    public SmAttribute getGuardAtt() {
        if (this.guardAtt == null) {
        	this.guardAtt = this.getAttributeDef("Guard");
        }
        return this.guardAtt;
    }

    @objid ("80d1efbe-d211-410d-806f-8b57e8ae9807")
    public SmAttribute getEndLineNumberAtt() {
        if (this.endLineNumberAtt == null) {
        	this.endLineNumberAtt = this.getAttributeDef("EndLineNumber");
        }
        return this.endLineNumberAtt;
    }

    @objid ("2cecd0c3-024f-4293-a3c3-2fef83c8a7f1")
    public SmDependency getFragmentDep() {
        if (this.fragmentDep == null) {
        	this.fragmentDep = this.getDependencyDef("Fragment");
        }
        return this.fragmentDep;
    }

    @objid ("01b2f1ed-8910-4c5c-a5ac-071f546b9d5d")
    public SmDependency getOwnerFragmentDep() {
        if (this.ownerFragmentDep == null) {
        	this.ownerFragmentDep = this.getDependencyDef("OwnerFragment");
        }
        return this.ownerFragmentDep;
    }

    @objid ("6602b9fb-b65a-43b0-a102-69b7d31dc38b")
    private static class InteractionOperandObjectFactory implements ISmObjectFactory {
        @objid ("fb08d28c-c906-4817-ad03-4c39e64f978b")
        private InteractionOperandSmClass smClass;

        @objid ("3163c5aa-3ef4-4880-b1a1-c9b8c959053f")
        public InteractionOperandObjectFactory(InteractionOperandSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b25cd698-3a57-4339-8059-b2ad49b3949f")
        @Override
        public ISmObjectData createData() {
            return new InteractionOperandData(this.smClass);
        }

        @objid ("92a46604-8f80-4368-9d31-dcb9a7705bd7")
        @Override
        public SmObjectImpl createImpl() {
            return new InteractionOperandImpl();
        }

    }

    @objid ("cc9934e5-c7f2-49ce-8c3f-8abe5343d31f")
    public static class GuardSmAttribute extends SmAttribute {
        @objid ("6497634e-d1c8-473a-9fa0-7a8e4f526ba8")
        public Object getValue(ISmObjectData data) {
            return ((InteractionOperandData) data).mGuard;
        }

        @objid ("311be9af-900a-4f55-ad33-e2dbbeb06600")
        public void setValue(ISmObjectData data, Object value) {
            ((InteractionOperandData) data).mGuard = value;
        }

    }

    @objid ("21ec3cbd-6668-4549-856e-5a4cef456587")
    public static class EndLineNumberSmAttribute extends SmAttribute {
        @objid ("6a9c248e-4758-4c83-a987-29a9c2df4c69")
        public Object getValue(ISmObjectData data) {
            return ((InteractionOperandData) data).mEndLineNumber;
        }

        @objid ("0b87bb40-2eb3-4be8-9b1a-f5d10088a49d")
        public void setValue(ISmObjectData data, Object value) {
            ((InteractionOperandData) data).mEndLineNumber = value;
        }

    }

    @objid ("0a3158ca-9a56-48c3-9115-1d2f065cbabc")
    public static class FragmentSmDependency extends SmMultipleDependency {
        @objid ("212ba4f3-9f19-4261-94f3-5d078ddafc54")
        private SmDependency symetricDep;

        @objid ("b2ad2720-e19d-45a9-8322-3cd20e313277")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionOperandData)data).mFragment != null)? ((InteractionOperandData)data).mFragment:SmMultipleDependency.EMPTY;
        }

        @objid ("54c956dd-3a32-45c1-8f6e-3a00026e35ed")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionOperandData) data).mFragment = values;
        }

        @objid ("885187ca-5bdd-4c92-be75-9b109fae6966")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionFragmentSmClass)this.getTarget()).getEnclosingOperandDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6d94f59c-3378-4ab7-a2f1-fd25ba5ba4ad")
    public static class OwnerFragmentSmDependency extends SmSingleDependency {
        @objid ("d5cf0567-7985-4229-850b-44f91bda885a")
        private SmDependency symetricDep;

        @objid ("d7753e9c-7764-4d63-a251-858b0b1fb2b2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InteractionOperandData) data).mOwnerFragment;
        }

        @objid ("c07eefc1-ffd7-4392-85e9-027efdfaabd2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InteractionOperandData) data).mOwnerFragment = value;
        }

        @objid ("60cc08d2-ada6-4144-aad6-236bbea293e2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CombinedFragmentSmClass)this.getTarget()).getOperandDep();
            }
            return this.symetricDep;
        }

    }

}
