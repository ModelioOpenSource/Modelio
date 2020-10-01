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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExceptionHandlerData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InputPinSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
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

@objid ("e0fb04a1-c5b8-467d-8e26-5fa7d5ce5735")
public class ExceptionHandlerSmClass extends UmlModelElementSmClass {
    @objid ("1d59dedb-183e-44f6-9eeb-cd5d3be185df")
    private SmAttribute guardAtt;

    @objid ("be853cb5-6112-443e-9aed-e6d81c198a21")
    private SmAttribute weightAtt;

    @objid ("b70330cd-6f5d-4ef2-a6b9-79f20dabfc23")
    private SmDependency protectedNodeDep;

    @objid ("faaece19-fa5c-4da3-bd7b-24ea96e02cae")
    private SmDependency exceptionInputDep;

    @objid ("3ab2a58e-d46e-427e-862e-3580854ec672")
    private SmDependency exceptionTypeDep;

    @objid ("152c567a-4fe9-45e6-a130-b92b94cdd370")
    public ExceptionHandlerSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("72407ce3-b746-455c-a0fd-2a0b0f9a8413")
    @Override
    public String getName() {
        return "ExceptionHandler";
    }

    @objid ("4e2cc18d-d127-4518-b490-f9b36622e756")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9a1f9779-9632-4988-b26f-65891610d0b2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExceptionHandler.class;
    }

    @objid ("a954ae0a-40cf-4048-92ed-db217686f009")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("7fed656a-f774-478d-ab87-e3feeef8f782")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("901b6005-47a6-4a8a-bcfd-8025589eaa4d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ExceptionHandlerObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.guardAtt = new GuardSmAttribute();
        this.guardAtt.init("Guard", this, String.class );
        registerAttribute(this.guardAtt);
        
        this.weightAtt = new WeightSmAttribute();
        this.weightAtt.init("Weight", this, String.class );
        registerAttribute(this.weightAtt);
        
        
        // Initialize and register the SmDependency
        this.protectedNodeDep = new ProtectedNodeSmDependency();
        this.protectedNodeDep.init("ProtectedNode", this, metamodel.getMClass(ActivityAction.MQNAME), 1, 1 );
        registerDependency(this.protectedNodeDep);
        
        this.exceptionInputDep = new ExceptionInputSmDependency();
        this.exceptionInputDep.init("ExceptionInput", this, metamodel.getMClass(InputPin.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.exceptionInputDep);
        
        this.exceptionTypeDep = new ExceptionTypeSmDependency();
        this.exceptionTypeDep.init("ExceptionType", this, metamodel.getMClass(GeneralClass.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.exceptionTypeDep);
    }

    @objid ("c58bcd32-374b-4f51-b57c-22c53b7229ff")
    public SmAttribute getGuardAtt() {
        if (this.guardAtt == null) {
        	this.guardAtt = this.getAttributeDef("Guard");
        }
        return this.guardAtt;
    }

    @objid ("7025f36c-21f3-4595-8e17-d3c4f1d55885")
    public SmAttribute getWeightAtt() {
        if (this.weightAtt == null) {
        	this.weightAtt = this.getAttributeDef("Weight");
        }
        return this.weightAtt;
    }

    @objid ("f8ea90d2-b737-4fcf-af33-78e9eb1e242d")
    public SmDependency getProtectedNodeDep() {
        if (this.protectedNodeDep == null) {
        	this.protectedNodeDep = this.getDependencyDef("ProtectedNode");
        }
        return this.protectedNodeDep;
    }

    @objid ("c9d45f9f-b089-402a-bdce-94d38801c36f")
    public SmDependency getExceptionInputDep() {
        if (this.exceptionInputDep == null) {
        	this.exceptionInputDep = this.getDependencyDef("ExceptionInput");
        }
        return this.exceptionInputDep;
    }

    @objid ("fcefad40-bb13-4461-a7ce-8a5f2a7f460f")
    public SmDependency getExceptionTypeDep() {
        if (this.exceptionTypeDep == null) {
        	this.exceptionTypeDep = this.getDependencyDef("ExceptionType");
        }
        return this.exceptionTypeDep;
    }

    @objid ("4a92c4ec-79a0-4140-88bc-296e905e0402")
    private static class ExceptionHandlerObjectFactory implements ISmObjectFactory {
        @objid ("d402ae61-1160-4592-a887-d6dc930a431e")
        private ExceptionHandlerSmClass smClass;

        @objid ("22c4fcd1-ab74-4762-9ddf-218606ada106")
        public ExceptionHandlerObjectFactory(ExceptionHandlerSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("261c31da-8a9e-4cce-a39c-48072966e8b9")
        @Override
        public ISmObjectData createData() {
            return new ExceptionHandlerData(this.smClass);
        }

        @objid ("e7d9adb2-c6c9-4054-bedc-8275f89c1ddd")
        @Override
        public SmObjectImpl createImpl() {
            return new ExceptionHandlerImpl();
        }

    }

    @objid ("5d4d0056-6e7c-4c0a-9aa7-244fbf9b89c4")
    public static class GuardSmAttribute extends SmAttribute {
        @objid ("66e2f22b-232e-44d8-83de-042b8f3b730e")
        public Object getValue(ISmObjectData data) {
            return ((ExceptionHandlerData) data).mGuard;
        }

        @objid ("3e76ecc6-c86e-4113-b202-61ee0123ee52")
        public void setValue(ISmObjectData data, Object value) {
            ((ExceptionHandlerData) data).mGuard = value;
        }

    }

    @objid ("44327b46-5758-4abe-a625-32ec641fdf74")
    public static class WeightSmAttribute extends SmAttribute {
        @objid ("1571fe35-6d7f-4d10-b5b4-6b07834fe636")
        public Object getValue(ISmObjectData data) {
            return ((ExceptionHandlerData) data).mWeight;
        }

        @objid ("9d30ff3a-bf89-4a9e-8be6-4ad3471370d9")
        public void setValue(ISmObjectData data, Object value) {
            ((ExceptionHandlerData) data).mWeight = value;
        }

    }

    @objid ("306eff38-058d-4baf-9d60-fac4406bb9a6")
    public static class ProtectedNodeSmDependency extends SmSingleDependency {
        @objid ("e954c070-0a1f-4350-94aa-64ff94b10826")
        private SmDependency symetricDep;

        @objid ("1966fe0b-0f07-4b83-b7f1-c95dc2f6452d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExceptionHandlerData) data).mProtectedNode;
        }

        @objid ("24992fda-2fd8-4a6a-b6a4-6568b4388aec")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExceptionHandlerData) data).mProtectedNode = value;
        }

        @objid ("04f2a3fa-e943-4ae1-97ec-b59f5dac1f01")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityActionSmClass)this.getTarget()).getHandlerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("eb0a49f6-d0c3-4854-881b-1260d0f22788")
    public static class ExceptionInputSmDependency extends SmSingleDependency {
        @objid ("97d9953b-6300-460e-82d5-bcb560aebfb4")
        private SmDependency symetricDep;

        @objid ("6a28f76a-73df-4b58-9d47-1fffd064a353")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExceptionHandlerData) data).mExceptionInput;
        }

        @objid ("0ebc9b9d-2aca-4be7-b609-e99184077468")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExceptionHandlerData) data).mExceptionInput = value;
        }

        @objid ("0a4a618f-6eed-4079-b6f2-df3a66dd49bf")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InputPinSmClass)this.getTarget()).getHandlerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("eae02582-2186-4719-8117-a5252ac1bbea")
    public static class ExceptionTypeSmDependency extends SmMultipleDependency {
        @objid ("45468393-9602-4cbf-aa76-220117935ce8")
        private SmDependency symetricDep;

        @objid ("9df39f9b-5c7f-49ce-824c-bcc670564129")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ExceptionHandlerData)data).mExceptionType != null)? ((ExceptionHandlerData)data).mExceptionType:SmMultipleDependency.EMPTY;
        }

        @objid ("cc408f0c-ee22-45b2-8d22-4342e1fd5e07")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ExceptionHandlerData) data).mExceptionType = values;
        }

        @objid ("f1f298c7-8964-4cf7-bd54-d2b3fa6308e1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralClassSmClass)this.getTarget()).getExceptionInputDep();
            }
            return this.symetricDep;
        }

    }

}
