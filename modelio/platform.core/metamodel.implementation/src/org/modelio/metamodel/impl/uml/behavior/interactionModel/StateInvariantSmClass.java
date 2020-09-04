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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.OccurrenceSpecificationSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("fcdddd87-663f-4ea8-99c7-5a84503dc557")
public class StateInvariantSmClass extends OccurrenceSpecificationSmClass {
    @objid ("32837912-475b-49db-a14e-d2d59ce09a5a")
    private SmAttribute bodyAtt;

    @objid ("9bf64c73-b760-451d-abd8-940346fff261")
    private SmAttribute endLineNumberAtt;

    @objid ("be0654d8-5292-4255-a3d7-ea7eb46dc43f")
    public StateInvariantSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0c1e37b1-84c6-4ba4-bca0-046045c2695b")
    @Override
    public String getName() {
        return "StateInvariant";
    }

    @objid ("e28ffef4-32c6-47d0-aa0d-2ec44e9721e1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f068f253-23a4-4203-b4f7-f4b16d890e30")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StateInvariant.class;
    }

    @objid ("620871bd-017f-46fb-94d2-1b99b153e695")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("10284b98-f8e2-4368-ba8d-a3b9efac1ca2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0ea76f47-ea77-48e0-aa86-99120d864656")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(OccurrenceSpecification.MQNAME);
        this.registerFactory(new StateInvariantObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.bodyAtt = new BodySmAttribute();
        this.bodyAtt.init("Body", this, String.class );
        registerAttribute(this.bodyAtt);
        
        this.endLineNumberAtt = new EndLineNumberSmAttribute();
        this.endLineNumberAtt.init("EndLineNumber", this, Integer.class );
        registerAttribute(this.endLineNumberAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("48c72b0f-07f1-48c5-aaa9-fad5914d693c")
    public SmAttribute getBodyAtt() {
        if (this.bodyAtt == null) {
        	this.bodyAtt = this.getAttributeDef("Body");
        }
        return this.bodyAtt;
    }

    @objid ("1b5d712b-1b50-428d-ba37-cdecbd1feb1d")
    public SmAttribute getEndLineNumberAtt() {
        if (this.endLineNumberAtt == null) {
        	this.endLineNumberAtt = this.getAttributeDef("EndLineNumber");
        }
        return this.endLineNumberAtt;
    }

    @objid ("8d293fe4-7306-4b52-8df2-b120276158ca")
    private static class StateInvariantObjectFactory implements ISmObjectFactory {
        @objid ("4740f8f0-b568-4cbc-b54d-2d177005cf9e")
        private StateInvariantSmClass smClass;

        @objid ("4213999d-c4f4-442e-8478-e5970544e1d5")
        public StateInvariantObjectFactory(StateInvariantSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0d389c98-0529-43dd-b24b-a4c776e22eff")
        @Override
        public ISmObjectData createData() {
            return new StateInvariantData(this.smClass);
        }

        @objid ("02139148-d507-48c5-9051-d243324114ad")
        @Override
        public SmObjectImpl createImpl() {
            return new StateInvariantImpl();
        }

    }

    @objid ("c1694ce8-f72b-4392-887b-7ad883a8aadf")
    public static class BodySmAttribute extends SmAttribute {
        @objid ("9f5d2b1e-3060-4d37-8175-77376a1c683f")
        public Object getValue(ISmObjectData data) {
            return ((StateInvariantData) data).mBody;
        }

        @objid ("a02c26e7-6ae0-4273-ad5e-dc3f87cbe9a4")
        public void setValue(ISmObjectData data, Object value) {
            ((StateInvariantData) data).mBody = value;
        }

    }

    @objid ("5b668db3-c0ce-4d3d-9317-e74f74576591")
    public static class EndLineNumberSmAttribute extends SmAttribute {
        @objid ("9a65f384-2bbf-45c6-a982-3491d99f01f1")
        public Object getValue(ISmObjectData data) {
            return ((StateInvariantData) data).mEndLineNumber;
        }

        @objid ("0f07c7c4-ab73-4fb8-be72-557b041de598")
        public void setValue(ISmObjectData data, Object value) {
            ((StateInvariantData) data).mEndLineNumber = value;
        }

    }

}
