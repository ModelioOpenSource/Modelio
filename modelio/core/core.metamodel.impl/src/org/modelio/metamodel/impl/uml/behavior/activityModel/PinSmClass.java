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
import org.modelio.metamodel.impl.uml.statik.ParameterSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1ced2aab-2b41-4c35-8f1e-6ea38c33170d")
public class PinSmClass extends ObjectNodeSmClass {
    @objid ("04519f54-5e0e-4f57-b673-c90c79e141e4")
    private SmAttribute isControlAtt;

    @objid ("e201975a-9fc4-4b52-b8e9-89f2b0afd1bd")
    private SmAttribute isExpansionAtt;

    @objid ("17473fcf-c77c-4fcc-8179-bd12784b3ee9")
    private SmDependency matchedDep;

    @objid ("73b83251-6611-42be-b908-6e9238aa404a")
    public  PinSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2ac74652-1743-47ca-9706-8165ff15108a")
    @Override
    public String getName() {
        return "Pin";
        
    }

    @objid ("03d94266-e878-4b22-a807-b13dd0ff4177")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("76fb7911-a0f1-48e1-8971-e5555edfc136")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Pin.class;
        
    }

    @objid ("a4435a41-33b1-4d20-80dd-fbf1ae87a000")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("ca9047dd-66be-4ad5-8c49-84c972dfc5fb")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("79a64d4e-c547-4125-a687-1682a54db2fa")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ObjectNode.MQNAME);
        this.registerFactory(new PinObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isControlAtt = new IsControlSmAttribute();
        this.isControlAtt.init("IsControl", this, Boolean.class );
        registerAttribute(this.isControlAtt);
        
        this.isExpansionAtt = new IsExpansionSmAttribute();
        this.isExpansionAtt.init("IsExpansion", this, Boolean.class );
        registerAttribute(this.isExpansionAtt);
        
        
        // Initialize and register the SmDependency
        this.matchedDep = new MatchedSmDependency();
        this.matchedDep.init("Matched", this, metamodel.getMClass(Parameter.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.matchedDep);
        
        
    }

    @objid ("729e5c12-1ad2-4ad9-962a-624830189c99")
    public SmAttribute getIsControlAtt() {
        if (this.isControlAtt == null) {
        	this.isControlAtt = this.getAttributeDef("IsControl");
        }
        return this.isControlAtt;
    }

    @objid ("2a072a8b-a04d-4c19-83a8-320387ca700c")
    public SmAttribute getIsExpansionAtt() {
        if (this.isExpansionAtt == null) {
        	this.isExpansionAtt = this.getAttributeDef("IsExpansion");
        }
        return this.isExpansionAtt;
    }

    @objid ("e0e0b81c-9bbf-422c-8f37-95705654954c")
    public SmDependency getMatchedDep() {
        if (this.matchedDep == null) {
        	this.matchedDep = this.getDependencyDef("Matched");
        }
        return this.matchedDep;
    }

    @objid ("b579b182-bccb-4582-9062-ae8d609f4047")
    private static class PinObjectFactory implements ISmObjectFactory {
        @objid ("850aacce-8d7e-4734-9c49-c69222909f7d")
        private PinSmClass smClass;

        @objid ("b51beb86-3a8c-405a-9df9-5b71b85bb548")
        public  PinObjectFactory(PinSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3365b8c2-dab8-4d6b-8492-f11e027634ee")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("cdb2be65-0a66-4e95-961f-93bec399cd97")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("88375186-61ee-404c-9566-c6717d24e9e0")
    public static class IsControlSmAttribute extends SmAttribute {
        @objid ("97bdb04f-3206-4960-a297-e50d7a1ad834")
        public Object getValue(ISmObjectData data) {
            return ((PinData) data).mIsControl;
        }

        @objid ("5b3b4621-66a2-44fe-83fd-4d49d3ce6b90")
        public void setValue(ISmObjectData data, Object value) {
            ((PinData) data).mIsControl = value;
        }

    }

    @objid ("885548f0-1208-4f5f-bd95-081e2ec7e7e9")
    public static class IsExpansionSmAttribute extends SmAttribute {
        @objid ("29a554c3-00b5-4504-a9af-7c2d3aec27f5")
        public Object getValue(ISmObjectData data) {
            return ((PinData) data).mIsExpansion;
        }

        @objid ("3487d2d8-9d1a-41ff-aaaf-980de3bae270")
        public void setValue(ISmObjectData data, Object value) {
            ((PinData) data).mIsExpansion = value;
        }

    }

    @objid ("4432d800-6b35-48b5-8ae3-14221cfb60cb")
    public static class MatchedSmDependency extends SmSingleDependency {
        @objid ("a1a76e56-c7bb-4da8-86bc-bf1a9a5a5a29")
        private SmDependency symetricDep;

        @objid ("f1ab2edf-9fcb-4d69-a92a-afcf4126aa40")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PinData) data).mMatched;
        }

        @objid ("fd23d36e-4292-4f90-b5e3-ce436628f386")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PinData) data).mMatched = value;
        }

        @objid ("d0421029-f9fd-4035-9c69-98f197cad0be")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ParameterSmClass)this.getTarget()).getMatchingDep();
            }
            return this.symetricDep;
            
        }

    }

}
