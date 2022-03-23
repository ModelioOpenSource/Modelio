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
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator;
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

@objid ("3f397821-349f-4e2d-ae6b-17b4abaddfb2")
public class CombinedFragmentSmClass extends InteractionFragmentSmClass {
    @objid ("a655d6a4-7637-4af3-970b-bd106668087a")
    private SmAttribute operatorAtt;

    @objid ("a7eb8fe2-9312-436b-a27d-593e9944ffe2")
    private SmDependency operandDep;

    @objid ("74bb6ec9-9ee4-4d69-8d47-7c25c276d5e0")
    private SmDependency fragmentGateDep;

    @objid ("8db8ca77-60f6-4cbf-8304-110c8dbcb693")
    public  CombinedFragmentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("13e97f6f-50f2-4337-a12a-5f1b2af342b5")
    @Override
    public String getName() {
        return "CombinedFragment";
        
    }

    @objid ("2b0b39c1-a76c-4d8a-a59e-b47c919f4a29")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d5d28cd8-9e40-41f9-88be-931e70e22ce1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CombinedFragment.class;
        
    }

    @objid ("74f59d8e-2b34-47e8-bc06-36f00f786d08")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("df05748e-563a-42f8-b24a-d2c75b59df73")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("302d3f2f-e37e-48cc-a854-28b036324b51")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InteractionFragment.MQNAME);
        this.registerFactory(new CombinedFragmentObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.operatorAtt = new OperatorSmAttribute();
        this.operatorAtt.init("Operator", this, InteractionOperator.class );
        registerAttribute(this.operatorAtt);
        
        
        // Initialize and register the SmDependency
        this.operandDep = new OperandSmDependency();
        this.operandDep.init("Operand", this, metamodel.getMClass(InteractionOperand.MQNAME), 1, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.operandDep);
        
        this.fragmentGateDep = new FragmentGateSmDependency();
        this.fragmentGateDep.init("FragmentGate", this, metamodel.getMClass(Gate.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.fragmentGateDep);
        
        
    }

    @objid ("c1c886ba-65bc-420d-b444-2bfe06d73f0e")
    public SmAttribute getOperatorAtt() {
        if (this.operatorAtt == null) {
        	this.operatorAtt = this.getAttributeDef("Operator");
        }
        return this.operatorAtt;
    }

    @objid ("fffb3259-e783-4ac7-848d-7d9dcdc53aad")
    public SmDependency getOperandDep() {
        if (this.operandDep == null) {
        	this.operandDep = this.getDependencyDef("Operand");
        }
        return this.operandDep;
    }

    @objid ("d9dc8739-086e-4d9d-b19c-154f353cbe3c")
    public SmDependency getFragmentGateDep() {
        if (this.fragmentGateDep == null) {
        	this.fragmentGateDep = this.getDependencyDef("FragmentGate");
        }
        return this.fragmentGateDep;
    }

    @objid ("a705b73e-bb5e-4df9-b0d4-5104286fbb26")
    private static class CombinedFragmentObjectFactory implements ISmObjectFactory {
        @objid ("599ea795-9932-4c1f-90bc-a98086dfabab")
        private CombinedFragmentSmClass smClass;

        @objid ("8412a76d-7176-42a3-bc12-63991b36213e")
        public  CombinedFragmentObjectFactory(CombinedFragmentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ecb20bc7-d3c6-4f18-aec1-0a4b89c2e6ee")
        @Override
        public ISmObjectData createData() {
            return new CombinedFragmentData(this.smClass);
        }

        @objid ("3bcd0d13-f78b-44db-b987-f8c3ddced3b8")
        @Override
        public SmObjectImpl createImpl() {
            return new CombinedFragmentImpl();
        }

    }

    @objid ("519873df-52ec-4c8a-926e-4e3924c2dae6")
    public static class OperatorSmAttribute extends SmAttribute {
        @objid ("437669d4-44fc-4fc1-9cc4-733e64db5251")
        public Object getValue(ISmObjectData data) {
            return ((CombinedFragmentData) data).mOperator;
        }

        @objid ("8be4ea0e-bf6a-4d6a-94bc-09681505e659")
        public void setValue(ISmObjectData data, Object value) {
            ((CombinedFragmentData) data).mOperator = value;
        }

    }

    @objid ("8369428b-cf2f-4140-aa8c-cdfddaf3e661")
    public static class OperandSmDependency extends SmMultipleDependency {
        @objid ("e7efc6ed-3c50-46f4-8e28-9a02e0361f4e")
        private SmDependency symetricDep;

        @objid ("cffcab35-16bc-4c30-a6f2-e19941cbccde")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CombinedFragmentData)data).mOperand != null)? ((CombinedFragmentData)data).mOperand:SmMultipleDependency.EMPTY;
        }

        @objid ("f43f31cd-f108-40e7-bc25-924b1804f2e8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CombinedFragmentData) data).mOperand = values;
            
        }

        @objid ("141c444a-25d4-44e4-a1a7-6c6e1551a736")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionOperandSmClass)this.getTarget()).getOwnerFragmentDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c9b572c2-a77b-45a9-abe3-945e1e2ef941")
    public static class FragmentGateSmDependency extends SmMultipleDependency {
        @objid ("98443ad7-56a5-494b-8f35-c9717c874d07")
        private SmDependency symetricDep;

        @objid ("7b94e571-22ab-4403-ac4a-869ff2347383")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CombinedFragmentData)data).mFragmentGate != null)? ((CombinedFragmentData)data).mFragmentGate:SmMultipleDependency.EMPTY;
        }

        @objid ("38a082b7-d929-4859-a931-d430c4853974")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CombinedFragmentData) data).mFragmentGate = values;
            
        }

        @objid ("256c3933-2d7a-4705-ad0a-c3cb88cd6565")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GateSmClass)this.getTarget()).getOwnerFragmentDep();
            }
            return this.symetricDep;
            
        }

    }

}
