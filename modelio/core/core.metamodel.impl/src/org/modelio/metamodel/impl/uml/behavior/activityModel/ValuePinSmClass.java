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
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("98107046-2d5a-42a3-b331-c129a966ac08")
public class ValuePinSmClass extends InputPinSmClass {
    @objid ("02af7c42-44ce-4ce1-8c77-72c92697b645")
    private SmAttribute valueAtt;

    @objid ("ef5d2cbb-1d31-45bc-bda5-6d1057edea16")
    public  ValuePinSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a630188e-10c6-4251-8627-0822146d207d")
    @Override
    public String getName() {
        return "ValuePin";
        
    }

    @objid ("65a6ef8f-d8b3-4deb-a01a-a80adf812c86")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e0817ef5-81f4-4587-8a44-f217faf0727d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ValuePin.class;
        
    }

    @objid ("02d04c14-0143-495f-8ef3-4dad54eee99f")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("02d6776e-220e-4c09-a267-d076975d529d")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("a55e8318-e2d9-4828-b16a-d19af6c925b5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InputPin.MQNAME);
        this.registerFactory(new ValuePinObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.valueAtt = new ValueSmAttribute();
        this.valueAtt.init("Value", this, String.class );
        registerAttribute(this.valueAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("d2879e24-1509-4030-8839-f4a27020c36e")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("7430f22a-c585-4726-b32d-6f1a6e36d27d")
    private static class ValuePinObjectFactory implements ISmObjectFactory {
        @objid ("491f74c8-6f1f-4b0d-9976-a6e8103746aa")
        private ValuePinSmClass smClass;

        @objid ("b68c11c0-bcc5-40c2-80c7-ee28adf87b05")
        public  ValuePinObjectFactory(ValuePinSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("afcf3b09-0b60-4c55-aee2-ef3b1f55e47f")
        @Override
        public ISmObjectData createData() {
            return new ValuePinData(this.smClass);
        }

        @objid ("6d9d104c-bcec-4fa9-9c58-a9cb4e33fccd")
        @Override
        public SmObjectImpl createImpl() {
            return new ValuePinImpl();
        }

    }

    @objid ("7d66b098-9f50-470d-b899-feb171527565")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("c1886382-1e92-4af2-aedb-cffa2bf2c151")
        public Object getValue(ISmObjectData data) {
            return ((ValuePinData) data).mValue;
        }

        @objid ("8e18e043-288f-41a7-ae06-d7a577dbcd51")
        public void setValue(ISmObjectData data, Object value) {
            ((ValuePinData) data).mValue = value;
        }

    }

}
