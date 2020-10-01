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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c5105486-1655-4f9c-9ff6-651e4276cbcc")
public class CallActionSmClass extends ActivityActionSmClass {
    @objid ("664c7e65-910d-471c-878c-892f83ade386")
    private SmAttribute isSynchronousAtt;

    @objid ("7add2080-5e19-4db3-b914-29b49bdd7359")
    public CallActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2b5d74d1-d9f1-43c0-9e77-7c8e778761f1")
    @Override
    public String getName() {
        return "CallAction";
    }

    @objid ("cbbda484-f6a0-4c6f-8c24-e7c80205fa20")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0d7711fb-80b8-4183-be7a-48beff65bf83")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CallAction.class;
    }

    @objid ("36c99aba-2249-42e5-848e-4c1ebad9ea32")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("8a4c5954-dc8a-46ff-bb20-954199792733")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("b238f22d-41dd-4abe-8db1-0cc37177a547")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new CallActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isSynchronousAtt = new IsSynchronousSmAttribute();
        this.isSynchronousAtt.init("IsSynchronous", this, Boolean.class );
        registerAttribute(this.isSynchronousAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("65e6ea7e-f2b5-4781-9c5f-1b4976910b27")
    public SmAttribute getIsSynchronousAtt() {
        if (this.isSynchronousAtt == null) {
        	this.isSynchronousAtt = this.getAttributeDef("IsSynchronous");
        }
        return this.isSynchronousAtt;
    }

    @objid ("11bac29e-5b94-4385-b58e-58b327dd63a6")
    private static class CallActionObjectFactory implements ISmObjectFactory {
        @objid ("ed5d3e34-e88b-4a34-a9fa-8613a425bfef")
        private CallActionSmClass smClass;

        @objid ("06f96a96-0d48-48a8-8ab2-927181c19080")
        public CallActionObjectFactory(CallActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("63ec989c-8b8b-4ec7-93bb-c103b33263dd")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("7e5da912-49f1-497b-8ff7-1b1f0e7640a2")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("0c5c361c-572c-4100-810b-3dff13d1e7a0")
    public static class IsSynchronousSmAttribute extends SmAttribute {
        @objid ("dcf107ff-5c2a-45de-90b5-47f6064db986")
        public Object getValue(ISmObjectData data) {
            return ((CallActionData) data).mIsSynchronous;
        }

        @objid ("80eaa8a9-eb89-42b8-9312-9e4890812a29")
        public void setValue(ISmObjectData data, Object value) {
            ((CallActionData) data).mIsSynchronous = value;
        }

    }

}
