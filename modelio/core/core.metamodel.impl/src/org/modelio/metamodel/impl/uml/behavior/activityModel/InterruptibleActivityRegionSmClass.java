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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityEdgeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityGroupSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InterruptibleActivityRegionData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
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

@objid ("cb39a5fc-03ec-4547-aa99-c2186f242602")
public class InterruptibleActivityRegionSmClass extends ActivityGroupSmClass {
    @objid ("59dc13c1-05e8-4a50-b38a-38484358e01e")
    private SmDependency interruptingEdgeDep;

    @objid ("5f9d6274-3446-40c1-bffe-f2f2042800c1")
    public InterruptibleActivityRegionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6900d5ab-2b17-4579-ad92-e99db53cdaf1")
    @Override
    public String getName() {
        return "InterruptibleActivityRegion";
    }

    @objid ("145fa301-e7ac-43fe-98c5-c50d6c174f2c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e23e336d-eeef-441f-bd7b-4a937e377938")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InterruptibleActivityRegion.class;
    }

    @objid ("0622148d-7179-4f1a-82b3-f754108d29d6")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("dc77baad-d0d6-497f-be07-d875eabfbd77")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ecfac7cc-f2cf-40dc-90b5-6254f01fd5c1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityGroup.MQNAME);
        this.registerFactory(new InterruptibleActivityRegionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.interruptingEdgeDep = new InterruptingEdgeSmDependency();
        this.interruptingEdgeDep.init("InterruptingEdge", this, metamodel.getMClass(ActivityEdge.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.interruptingEdgeDep);
    }

    @objid ("d65c5eda-ff81-4072-b165-8a1d1921eca1")
    public SmDependency getInterruptingEdgeDep() {
        if (this.interruptingEdgeDep == null) {
        	this.interruptingEdgeDep = this.getDependencyDef("InterruptingEdge");
        }
        return this.interruptingEdgeDep;
    }

    @objid ("46d710f4-8ddb-43f0-b3b1-6c9f5d5ea253")
    private static class InterruptibleActivityRegionObjectFactory implements ISmObjectFactory {
        @objid ("c58fc0bd-40a4-4a62-9fc5-9260c52f587f")
        private InterruptibleActivityRegionSmClass smClass;

        @objid ("4ff57675-a047-414c-a6bc-9e5380788ce2")
        public InterruptibleActivityRegionObjectFactory(InterruptibleActivityRegionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ebd39ab2-14bb-4868-a1c1-84082c12af0e")
        @Override
        public ISmObjectData createData() {
            return new InterruptibleActivityRegionData(this.smClass);
        }

        @objid ("0fdf26be-4224-4ee6-9858-40fb1c89bd9f")
        @Override
        public SmObjectImpl createImpl() {
            return new InterruptibleActivityRegionImpl();
        }

    }

    @objid ("d82bf19d-0bad-4273-9d60-3eabf18f55ae")
    public static class InterruptingEdgeSmDependency extends SmMultipleDependency {
        @objid ("d48866be-ff74-4975-840c-e6ad7b31df83")
        private SmDependency symetricDep;

        @objid ("df5160db-775e-461d-8116-a91611c5d3e8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InterruptibleActivityRegionData)data).mInterruptingEdge != null)? ((InterruptibleActivityRegionData)data).mInterruptingEdge:SmMultipleDependency.EMPTY;
        }

        @objid ("f4259796-a9d2-4f94-a61f-f9ef07c3173e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InterruptibleActivityRegionData) data).mInterruptingEdge = values;
        }

        @objid ("4a08df46-7d81-438d-83d0-58acfbabea73")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityEdgeSmClass)this.getTarget()).getInterruptsDep();
            }
            return this.symetricDep;
        }

    }

}
