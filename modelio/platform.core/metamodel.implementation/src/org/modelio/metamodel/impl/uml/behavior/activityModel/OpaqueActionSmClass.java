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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1c8993a9-2dca-4afe-afa1-3f3e38614e0e")
public class OpaqueActionSmClass extends ActivityActionSmClass {
    @objid ("caa574b6-af3f-4951-823e-3280a65f4e26")
    private SmAttribute bodyAtt;

    @objid ("f31bc6ff-41de-4d2c-b74d-524cd7c59b08")
    public OpaqueActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b9cb1c6d-df30-4d88-9fab-c263de451b14")
    @Override
    public String getName() {
        return "OpaqueAction";
    }

    @objid ("c409b57f-51bd-4629-9a20-842c66c7516e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9f5285c8-b32a-4160-8aa4-1c84f4151ca1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return OpaqueAction.class;
    }

    @objid ("505e3913-d164-4e7e-973a-5d0be5d486b6")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9550b1dd-1b22-4cd7-9571-097a41dadf41")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("efb7d1fb-92a6-4763-b304-28c678a120ef")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new OpaqueActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.bodyAtt = new BodySmAttribute();
        this.bodyAtt.init("Body", this, String.class );
        registerAttribute(this.bodyAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("988ce2ab-449f-4787-9e6c-347cdcd76d1f")
    public SmAttribute getBodyAtt() {
        if (this.bodyAtt == null) {
        	this.bodyAtt = this.getAttributeDef("Body");
        }
        return this.bodyAtt;
    }

    @objid ("4f2d3356-8615-4dc9-bf07-c569a3062e88")
    private static class OpaqueActionObjectFactory implements ISmObjectFactory {
        @objid ("a4d0c336-c20c-47e1-82ad-4e0d3aa45cdb")
        private OpaqueActionSmClass smClass;

        @objid ("e744f604-23f3-4257-9429-aec854f5a7ab")
        public OpaqueActionObjectFactory(OpaqueActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d743aacb-0b13-43bf-815a-461ebe2f52de")
        @Override
        public ISmObjectData createData() {
            return new OpaqueActionData(this.smClass);
        }

        @objid ("f265e871-e1f6-4a3b-9f42-4001ea1e08d3")
        @Override
        public SmObjectImpl createImpl() {
            return new OpaqueActionImpl();
        }

    }

    @objid ("ec0decf0-e050-4620-ac73-25030b796fcb")
    public static class BodySmAttribute extends SmAttribute {
        @objid ("80a1a725-877b-467a-b7bd-8384a043d7b3")
        public Object getValue(ISmObjectData data) {
            return ((OpaqueActionData) data).mBody;
        }

        @objid ("6b35db0d-e392-41af-90a7-0d76d4f21d7e")
        public void setValue(ISmObjectData data, Object value) {
            ((OpaqueActionData) data).mBody = value;
        }

    }

}
