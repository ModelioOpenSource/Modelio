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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ConstraintSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.DurationConstraint;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("95b81311-bc2f-4843-a5b2-3126a3d8120e")
public class DurationConstraintSmClass extends ConstraintSmClass {
    @objid ("e63ebe3f-f01f-4250-96e1-3dcbd3046075")
    private SmAttribute durationMinAtt;

    @objid ("98886dda-e7ca-49f2-bba3-811f95774ec3")
    private SmAttribute durationMaxAtt;

    @objid ("bd0a1d3d-693d-49ce-bcd4-1f616bbfd907")
    public DurationConstraintSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("43fac4f7-dea9-49c4-ab95-e41a62e53531")
    @Override
    public String getName() {
        return "DurationConstraint";
    }

    @objid ("f9232b53-81fc-4dd0-adb6-c357a6869ea3")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("20c8138c-9789-4fe5-86b7-a5b69a28e997")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DurationConstraint.class;
    }

    @objid ("c6bc3520-82e7-4900-a7e1-2a577bea4564")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("0fa4ab08-2ad3-4c03-9714-c3fd5eab31b3")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("dede565b-3e1d-4fc4-8315-0a1208cb0009")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Constraint.MQNAME);
        this.registerFactory(new DurationConstraintObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.durationMinAtt = new DurationMinSmAttribute();
        this.durationMinAtt.init("DurationMin", this, String.class );
        registerAttribute(this.durationMinAtt);
        
        this.durationMaxAtt = new DurationMaxSmAttribute();
        this.durationMaxAtt.init("DurationMax", this, String.class );
        registerAttribute(this.durationMaxAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("93b777be-fc6d-4ca8-9965-9ee54b98402f")
    public SmAttribute getDurationMinAtt() {
        if (this.durationMinAtt == null) {
        	this.durationMinAtt = this.getAttributeDef("DurationMin");
        }
        return this.durationMinAtt;
    }

    @objid ("2b142883-9832-485a-958c-873638773556")
    public SmAttribute getDurationMaxAtt() {
        if (this.durationMaxAtt == null) {
        	this.durationMaxAtt = this.getAttributeDef("DurationMax");
        }
        return this.durationMaxAtt;
    }

    @objid ("1cc61ded-ad57-40f8-9e34-07ba0c439ec8")
    private static class DurationConstraintObjectFactory implements ISmObjectFactory {
        @objid ("f0d17e2f-77cd-4780-8fa8-8be85faa1c87")
        private DurationConstraintSmClass smClass;

        @objid ("28c93a0b-2a32-4792-a71f-1c48bfdd0325")
        public DurationConstraintObjectFactory(DurationConstraintSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2c5f289f-8a39-474b-895c-03d189216c18")
        @Override
        public ISmObjectData createData() {
            return new DurationConstraintData(this.smClass);
        }

        @objid ("834886e3-b407-4c1d-9ef3-1857b597cd8f")
        @Override
        public SmObjectImpl createImpl() {
            return new DurationConstraintImpl();
        }

    }

    @objid ("7fac19a6-667b-4914-82f7-86f3779f22ea")
    public static class DurationMinSmAttribute extends SmAttribute {
        @objid ("38fd3627-c86b-4af1-8a03-1b48fa7d4878")
        public Object getValue(ISmObjectData data) {
            return ((DurationConstraintData) data).mDurationMin;
        }

        @objid ("80c00a40-c78f-4793-ab58-6bef7ae4229d")
        public void setValue(ISmObjectData data, Object value) {
            ((DurationConstraintData) data).mDurationMin = value;
        }

    }

    @objid ("512e4abc-329a-4f19-bde5-036722d6c42d")
    public static class DurationMaxSmAttribute extends SmAttribute {
        @objid ("54360f33-0e98-4e4c-9429-ac03cdfb31f3")
        public Object getValue(ISmObjectData data) {
            return ((DurationConstraintData) data).mDurationMax;
        }

        @objid ("e5c5b9fb-45c2-4c0c-b536-9bfe6bd4beeb")
        public void setValue(ISmObjectData data, Object value) {
            ((DurationConstraintData) data).mDurationMax = value;
        }

    }

}
