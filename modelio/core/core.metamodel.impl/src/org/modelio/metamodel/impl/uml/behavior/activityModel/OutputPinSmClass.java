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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("afb90f81-def5-4f04-bd0f-5ff0db63e929")
public class OutputPinSmClass extends PinSmClass {
    @objid ("7cbb63f4-4b93-4b67-8fac-c42c4a31d611")
    private SmDependency outputingDep;

    @objid ("eb3a60b8-d8f7-40ee-9768-4bedbd0b6f67")
    public  OutputPinSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9b044201-6f20-4b40-859e-8a881ff61bf9")
    @Override
    public String getName() {
        return "OutputPin";
        
    }

    @objid ("6744cca4-f5fb-484c-9af0-ca9a1068d21a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d1ce172a-198b-4fc9-824d-cb9b900d0725")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return OutputPin.class;
        
    }

    @objid ("5de9a49f-7ea3-4c3a-8496-c33fc1d4a1df")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("bd6dc9a5-fb30-4641-9724-5e59deecd2bc")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("92cde2cb-0a11-4831-87fc-027be8863d7a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Pin.MQNAME);
        this.registerFactory(new OutputPinObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.outputingDep = new OutputingSmDependency();
        this.outputingDep.init("Outputing", this, metamodel.getMClass(ActivityAction.MQNAME), 0, 1 );
        registerDependency(this.outputingDep);
        
        
    }

    @objid ("39e0d501-0a60-4ae4-a32a-08217c111741")
    public SmDependency getOutputingDep() {
        if (this.outputingDep == null) {
        	this.outputingDep = this.getDependencyDef("Outputing");
        }
        return this.outputingDep;
    }

    @objid ("d8975d2f-24ec-473f-b9a4-d4964e0bba79")
    private static class OutputPinObjectFactory implements ISmObjectFactory {
        @objid ("c6071bda-d6b0-47aa-83d0-c2c4fcde8cfd")
        private OutputPinSmClass smClass;

        @objid ("11c4b9ea-72c5-43e0-836a-0f5817cd8126")
        public  OutputPinObjectFactory(OutputPinSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2a5bff36-24d0-48cc-aff0-9680dae8ba4f")
        @Override
        public ISmObjectData createData() {
            return new OutputPinData(this.smClass);
        }

        @objid ("20931eb4-f9cc-49f1-ba66-1afded3778a5")
        @Override
        public SmObjectImpl createImpl() {
            return new OutputPinImpl();
        }

    }

    @objid ("19d0964d-48a9-4afa-a867-cb29e1baf945")
    public static class OutputingSmDependency extends SmSingleDependency {
        @objid ("3bf75004-c9c1-4e7a-8e5b-51ce29974f5e")
        private SmDependency symetricDep;

        @objid ("1c5df217-a0a3-4d45-ac60-0b6ca78678c4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((OutputPinData) data).mOutputing;
        }

        @objid ("6213aa65-52db-45cf-a0be-fe3a7bccdd40")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((OutputPinData) data).mOutputing = value;
        }

        @objid ("9c8771cd-8783-47f4-a46f-24ae256ba89f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityActionSmClass)this.getTarget()).getOutputDep();
            }
            return this.symetricDep;
            
        }

    }

}
