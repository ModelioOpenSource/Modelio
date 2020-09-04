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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityEdgeData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InterruptibleActivityRegionSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
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

@objid ("8cf09d5a-5b45-402b-9ebd-b7d23239cb63")
public class ActivityEdgeSmClass extends UmlModelElementSmClass {
    @objid ("58d8158b-0870-4d20-80d6-58677bbeb543")
    private SmAttribute guardAtt;

    @objid ("bc0abf0e-a307-4860-9996-6b6a759cb7ba")
    private SmAttribute weightAtt;

    @objid ("302bd008-9b0d-4fbc-9194-92a90d27b448")
    private SmDependency targetDep;

    @objid ("8e4bd0b5-d000-4441-833f-f5d5ec288563")
    private SmDependency sourceDep;

    @objid ("46d2c895-8b31-404d-86c9-804bf54d174f")
    private SmDependency interruptsDep;

    @objid ("2fd2684e-e43b-4712-8445-4fe29e68cea9")
    private SmDependency realizedInformationFlowDep;

    @objid ("4693c9ad-247c-405d-b69e-0777ee63eaf9")
    public ActivityEdgeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f9138d65-bc59-47e9-ad8b-4440e1cc25d9")
    @Override
    public String getName() {
        return "ActivityEdge";
    }

    @objid ("de6f5101-a559-4a58-b8ed-7200d129dcab")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a1a00ec4-81e3-4945-9863-3d3dcb3e2aea")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityEdge.class;
    }

    @objid ("2e46210e-1431-4eb0-b02c-0ab9927d5ee5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e3adbd69-5181-4432-99ed-d8e280b630ad")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("ccec8c90-2f20-4ef0-ac68-8d5a33452a71")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ActivityEdgeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.guardAtt = new GuardSmAttribute();
        this.guardAtt.init("Guard", this, String.class );
        registerAttribute(this.guardAtt);
        
        this.weightAtt = new WeightSmAttribute();
        this.weightAtt.init("Weight", this, String.class );
        registerAttribute(this.weightAtt);
        
        
        // Initialize and register the SmDependency
        this.targetDep = new TargetSmDependency();
        this.targetDep.init("Target", this, metamodel.getMClass(ActivityNode.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetDep);
        
        this.sourceDep = new SourceSmDependency();
        this.sourceDep.init("Source", this, metamodel.getMClass(ActivityNode.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.sourceDep);
        
        this.interruptsDep = new InterruptsSmDependency();
        this.interruptsDep.init("Interrupts", this, metamodel.getMClass(InterruptibleActivityRegion.MQNAME), 0, 1 );
        registerDependency(this.interruptsDep);
        
        this.realizedInformationFlowDep = new RealizedInformationFlowSmDependency();
        this.realizedInformationFlowDep.init("RealizedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.realizedInformationFlowDep);
    }

    @objid ("f87aad65-989e-43a2-ac92-ccfa22e47194")
    public SmAttribute getGuardAtt() {
        if (this.guardAtt == null) {
        	this.guardAtt = this.getAttributeDef("Guard");
        }
        return this.guardAtt;
    }

    @objid ("af4330ef-1378-4672-b102-c8373176b936")
    public SmAttribute getWeightAtt() {
        if (this.weightAtt == null) {
        	this.weightAtt = this.getAttributeDef("Weight");
        }
        return this.weightAtt;
    }

    @objid ("abdfb9a9-79df-40f0-9af2-36eab215c805")
    public SmDependency getTargetDep() {
        if (this.targetDep == null) {
        	this.targetDep = this.getDependencyDef("Target");
        }
        return this.targetDep;
    }

    @objid ("ddc07921-633c-46c4-9817-cca7a856fff6")
    public SmDependency getSourceDep() {
        if (this.sourceDep == null) {
        	this.sourceDep = this.getDependencyDef("Source");
        }
        return this.sourceDep;
    }

    @objid ("1c41c6b0-780f-413f-b89f-3278e61d2946")
    public SmDependency getInterruptsDep() {
        if (this.interruptsDep == null) {
        	this.interruptsDep = this.getDependencyDef("Interrupts");
        }
        return this.interruptsDep;
    }

    @objid ("358d0d30-455f-4db5-ab24-6c0974ac110b")
    public SmDependency getRealizedInformationFlowDep() {
        if (this.realizedInformationFlowDep == null) {
        	this.realizedInformationFlowDep = this.getDependencyDef("RealizedInformationFlow");
        }
        return this.realizedInformationFlowDep;
    }

    @objid ("f7be5f17-8be0-4b73-9557-e1e06e791104")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("fabbc42a-95dc-4161-9ee3-3256d9f69cd1")
    private static class ActivityEdgeObjectFactory implements ISmObjectFactory {
        @objid ("f2de3550-7d52-4fef-9331-eb9b25abc825")
        private ActivityEdgeSmClass smClass;

        @objid ("7e3de592-cd26-4777-8e6c-9faf8a8a3294")
        public ActivityEdgeObjectFactory(ActivityEdgeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a1d559c2-7133-4b4c-8a03-229cfd4455b9")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("df82a86f-a024-4d95-89b4-f4b601f663b8")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("da7c0da1-83a9-4f5c-963a-04645cbe2890")
    public static class GuardSmAttribute extends SmAttribute {
        @objid ("003d4ff2-b438-4716-a925-de269f52f324")
        public Object getValue(ISmObjectData data) {
            return ((ActivityEdgeData) data).mGuard;
        }

        @objid ("8d1f8c35-305e-4707-aecd-ba50c348cc87")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityEdgeData) data).mGuard = value;
        }

    }

    @objid ("f0e617b3-212c-45c1-a785-8fd0b92df9dc")
    public static class WeightSmAttribute extends SmAttribute {
        @objid ("48e44a43-66c2-474c-99f6-503925b8a670")
        public Object getValue(ISmObjectData data) {
            return ((ActivityEdgeData) data).mWeight;
        }

        @objid ("95ec662c-0c1a-4549-8141-8ec24cd0ffce")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityEdgeData) data).mWeight = value;
        }

    }

    @objid ("b9ee5417-26c6-4ec7-a768-4189a264f245")
    public static class TargetSmDependency extends SmSingleDependency {
        @objid ("29cca0e6-0ada-4a4a-992d-85f8d7539e22")
        private SmDependency symetricDep;

        @objid ("0b477775-2fef-4d86-8ccf-ae401eb968d3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityEdgeData) data).mTarget;
        }

        @objid ("038492fe-4ab0-45a6-9bad-a57ecce32d41")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityEdgeData) data).mTarget = value;
        }

        @objid ("7ba5081f-ce9a-4459-95dd-03521d907c21")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityNodeSmClass)this.getTarget()).getIncomingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a3bb85c2-43dc-475e-810c-c7c5973266cb")
    public static class SourceSmDependency extends SmSingleDependency {
        @objid ("1e33f17a-fcf3-479a-82f4-4489e9c22d06")
        private SmDependency symetricDep;

        @objid ("59dfd8b8-1d51-4a80-9a9b-a5d8f1ecf5d0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityEdgeData) data).mSource;
        }

        @objid ("8439bbe8-5228-4cb4-adcf-55b33d29cb4c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityEdgeData) data).mSource = value;
        }

        @objid ("17f95479-9f90-4191-853d-1e3e2da9c17b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityNodeSmClass)this.getTarget()).getOutgoingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c8ad2b22-923f-4570-9b1e-0ddebbbf87ad")
    public static class InterruptsSmDependency extends SmSingleDependency {
        @objid ("9bc789e7-a21c-4ff8-8622-07e468632101")
        private SmDependency symetricDep;

        @objid ("a255d925-569b-414c-a90e-03f718888489")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityEdgeData) data).mInterrupts;
        }

        @objid ("20714193-6b24-4b20-b859-bcefdc08547c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityEdgeData) data).mInterrupts = value;
        }

        @objid ("adb88484-99a6-4955-89f0-d8484121be27")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InterruptibleActivityRegionSmClass)this.getTarget()).getInterruptingEdgeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0b0a84e7-5b62-4ed3-8c38-bb34a7c66dc2")
    public static class RealizedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("94740d3b-9a80-4293-b967-e323a70e8210")
        private SmDependency symetricDep;

        @objid ("d69fb256-7565-4886-9633-4d2f95fad0c0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityEdgeData)data).mRealizedInformationFlow != null)? ((ActivityEdgeData)data).mRealizedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("dc302470-0f3c-4e2c-84cf-f5f6b2076603")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityEdgeData) data).mRealizedInformationFlow = values;
        }

        @objid ("4aa02970-632c-4217-9d0b-f3538eefcfa5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getRealizingActivityEdgeDep();
            }
            return this.symetricDep;
        }

    }

}
