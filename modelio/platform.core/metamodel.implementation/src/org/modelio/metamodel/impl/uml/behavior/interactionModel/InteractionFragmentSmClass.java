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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentData;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionOperandSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.LifelineSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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

@objid ("a59b98cb-268c-4bfd-b2e2-971cbd7c6ee9")
public class InteractionFragmentSmClass extends UmlModelElementSmClass {
    @objid ("aa6e7db5-4a97-4b5d-a4e6-1771c2c6092a")
    private SmAttribute lineNumberAtt;

    @objid ("d5a62ecc-9f4b-4959-8bbd-39d89987c912")
    private SmDependency enclosingOperandDep;

    @objid ("824ad292-1281-4385-8990-49b601583455")
    private SmDependency enclosingInteractionDep;

    @objid ("185b25a4-0206-4b9c-bd23-7a8c71d2a243")
    private SmDependency coveredDep;

    @objid ("2bc303a7-1a74-43bc-aa3e-ed6e70af37a3")
    public InteractionFragmentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7ecb6c79-f0ae-4afb-969a-1e231c47d367")
    @Override
    public String getName() {
        return "InteractionFragment";
    }

    @objid ("3ceec12f-3cc3-44f3-856e-c90867d66846")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1ecb0b90-60ae-4a8f-a8a9-672016b98359")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InteractionFragment.class;
    }

    @objid ("253fb8a8-79a4-4b14-bd6e-a6eefee1768f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("afa47490-cfbf-4e46-9465-e81c43fecbf2")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("37c910f3-c287-4163-a592-e5ce162252ce")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new InteractionFragmentObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.lineNumberAtt = new LineNumberSmAttribute();
        this.lineNumberAtt.init("LineNumber", this, Integer.class );
        registerAttribute(this.lineNumberAtt);
        
        
        // Initialize and register the SmDependency
        this.enclosingOperandDep = new EnclosingOperandSmDependency();
        this.enclosingOperandDep.init("EnclosingOperand", this, metamodel.getMClass(InteractionOperand.MQNAME), 0, 1 );
        registerDependency(this.enclosingOperandDep);
        
        this.enclosingInteractionDep = new EnclosingInteractionSmDependency();
        this.enclosingInteractionDep.init("EnclosingInteraction", this, metamodel.getMClass(Interaction.MQNAME), 0, 1 );
        registerDependency(this.enclosingInteractionDep);
        
        this.coveredDep = new CoveredSmDependency();
        this.coveredDep.init("Covered", this, metamodel.getMClass(Lifeline.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.coveredDep);
    }

    @objid ("94049e6c-c500-4e3e-834c-4b51a2290110")
    public SmAttribute getLineNumberAtt() {
        if (this.lineNumberAtt == null) {
        	this.lineNumberAtt = this.getAttributeDef("LineNumber");
        }
        return this.lineNumberAtt;
    }

    @objid ("89dc588e-bf88-40b9-b538-1ea346d6d694")
    public SmDependency getEnclosingOperandDep() {
        if (this.enclosingOperandDep == null) {
        	this.enclosingOperandDep = this.getDependencyDef("EnclosingOperand");
        }
        return this.enclosingOperandDep;
    }

    @objid ("c0389eba-3ee3-47b0-8adb-168e2b394931")
    public SmDependency getEnclosingInteractionDep() {
        if (this.enclosingInteractionDep == null) {
        	this.enclosingInteractionDep = this.getDependencyDef("EnclosingInteraction");
        }
        return this.enclosingInteractionDep;
    }

    @objid ("8eb7212c-0c26-4b65-8dcd-226a66b96dcc")
    public SmDependency getCoveredDep() {
        if (this.coveredDep == null) {
        	this.coveredDep = this.getDependencyDef("Covered");
        }
        return this.coveredDep;
    }

    @objid ("945e0f69-a69d-4df7-b43e-9cd093a35d8e")
    private static class InteractionFragmentObjectFactory implements ISmObjectFactory {
        @objid ("bc38555c-6ac7-43c8-bf49-dc3ee340956b")
        private InteractionFragmentSmClass smClass;

        @objid ("0fe2780a-54f7-48a1-af32-861b2f8024ae")
        public InteractionFragmentObjectFactory(InteractionFragmentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2221a188-296a-4dd1-9df6-262e4e2d0005")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("a51aca09-f16a-4ea0-acfa-bf15e91d2fb0")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("b6881009-6027-4428-84d6-012e8b2e12d5")
    public static class LineNumberSmAttribute extends SmAttribute {
        @objid ("72c574d2-c9d1-442b-af80-13fd043824f5")
        public Object getValue(ISmObjectData data) {
            return ((InteractionFragmentData) data).mLineNumber;
        }

        @objid ("5ad71351-d752-484f-a8c6-e2433ea2faa6")
        public void setValue(ISmObjectData data, Object value) {
            ((InteractionFragmentData) data).mLineNumber = value;
        }

    }

    @objid ("f8f351cd-fa19-4f14-86d4-26339b4d6f80")
    public static class EnclosingOperandSmDependency extends SmSingleDependency {
        @objid ("d7f5d7ab-3da5-45c2-b2cd-b1e0bd716e9b")
        private SmDependency symetricDep;

        @objid ("60d12c75-2613-4151-ad4a-e31714da5899")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InteractionFragmentData) data).mEnclosingOperand;
        }

        @objid ("3a421e00-4d3b-4f22-b303-0e38da04af80")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InteractionFragmentData) data).mEnclosingOperand = value;
        }

        @objid ("ab7dd7cf-dfab-4c31-9fb5-2a5b39b65449")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionOperandSmClass)this.getTarget()).getFragmentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a10a20df-502b-4293-b8ed-abf8e24776d5")
    public static class EnclosingInteractionSmDependency extends SmSingleDependency {
        @objid ("46a2f8bc-13da-4295-bc26-37d2c0c6fba8")
        private SmDependency symetricDep;

        @objid ("64098555-b767-45e2-931c-2cdce3668741")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InteractionFragmentData) data).mEnclosingInteraction;
        }

        @objid ("ee6f3b65-583d-4dc6-b2b1-6aa345215cc0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InteractionFragmentData) data).mEnclosingInteraction = value;
        }

        @objid ("2102905d-72ef-4da8-b29b-593e8e6508b6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionSmClass)this.getTarget()).getFragmentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0673a414-36c7-4793-9a42-192482d7cb76")
    public static class CoveredSmDependency extends SmMultipleDependency {
        @objid ("020d41c3-dbb0-49b8-87b4-36df8a96da6b")
        private SmDependency symetricDep;

        @objid ("254f6e37-8f15-4555-99fa-bfbd5318a6fa")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionFragmentData)data).mCovered != null)? ((InteractionFragmentData)data).mCovered:SmMultipleDependency.EMPTY;
        }

        @objid ("fbe78bef-d009-44c0-a812-e581e9ba143a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionFragmentData) data).mCovered = values;
        }

        @objid ("38756a58-8efb-4f11-9be6-fdeab3aa462d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LifelineSmClass)this.getTarget()).getCoveredByDep();
            }
            return this.symetricDep;
        }

    }

}
