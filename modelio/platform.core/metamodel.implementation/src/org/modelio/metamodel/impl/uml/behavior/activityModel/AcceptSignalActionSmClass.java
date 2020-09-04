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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptSignalActionData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("bc768fdd-89d5-45c3-badd-085d29c274f9")
public class AcceptSignalActionSmClass extends ActivityActionSmClass {
    @objid ("909a25c0-baf2-41a1-ad1f-abf4cce54916")
    private SmDependency acceptedDep;

    @objid ("e2afc334-9a78-46a6-9100-682ae2e54e79")
    public AcceptSignalActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a4ecd0c8-d136-417b-a22c-6146cf0535d6")
    @Override
    public String getName() {
        return "AcceptSignalAction";
    }

    @objid ("296c2152-ed8e-462d-808a-ee58983a6b96")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("01eb5b4f-2aad-48bb-9fcf-35553bc6cd41")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AcceptSignalAction.class;
    }

    @objid ("00a189f8-65f1-4e0a-bc0e-f460de17c3db")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cb1afb25-daca-499e-a4e2-a0f716822a52")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ffff984c-5335-4371-95cf-b19f879a3296")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new AcceptSignalActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.acceptedDep = new AcceptedSmDependency();
        this.acceptedDep.init("Accepted", this, metamodel.getMClass(Signal.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.acceptedDep);
    }

    @objid ("b72cfe27-cfb8-4338-b029-825540c8d575")
    public SmDependency getAcceptedDep() {
        if (this.acceptedDep == null) {
        	this.acceptedDep = this.getDependencyDef("Accepted");
        }
        return this.acceptedDep;
    }

    @objid ("2b013664-ec04-4712-9064-0075d0bcab51")
    private static class AcceptSignalActionObjectFactory implements ISmObjectFactory {
        @objid ("63639ccc-898c-4c22-ade0-3544624f477a")
        private AcceptSignalActionSmClass smClass;

        @objid ("c8c1c080-0431-492b-800f-addb2cfd6cf5")
        public AcceptSignalActionObjectFactory(AcceptSignalActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c033a63a-a3ef-4643-a6bf-4a0dcdcaa30a")
        @Override
        public ISmObjectData createData() {
            return new AcceptSignalActionData(this.smClass);
        }

        @objid ("da6eb6b9-6ec5-4bc9-bf01-b858a0a60191")
        @Override
        public SmObjectImpl createImpl() {
            return new AcceptSignalActionImpl();
        }

    }

    @objid ("13f32ee6-91d4-4ff1-8b40-72f43d424349")
    public static class AcceptedSmDependency extends SmMultipleDependency {
        @objid ("3273690f-fdab-4b17-8325-a08fabd73311")
        private SmDependency symetricDep;

        @objid ("b088363d-a52b-4401-bd50-a05c9dd1390e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AcceptSignalActionData)data).mAccepted != null)? ((AcceptSignalActionData)data).mAccepted:SmMultipleDependency.EMPTY;
        }

        @objid ("d89cd1b6-0260-4043-936a-9aec9450a070")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AcceptSignalActionData) data).mAccepted = values;
        }

        @objid ("cacd251c-be41-4f99-9b98-0e32631580de")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getReceiverDep();
            }
            return this.symetricDep;
        }

    }

}
