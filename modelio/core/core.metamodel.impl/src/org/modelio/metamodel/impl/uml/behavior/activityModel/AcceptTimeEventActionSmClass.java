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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("8b1ab55a-2157-409c-a06d-4cc0df8ca847")
public class AcceptTimeEventActionSmClass extends ActivityActionSmClass {
    @objid ("d0110844-0466-41eb-a66b-c9874326d232")
    private SmAttribute timeExpresionAtt;

    @objid ("61e3c2cd-116e-4ea9-852b-e51294f07d17")
    public  AcceptTimeEventActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0321401f-9f04-4c11-82bc-dad56ce8749c")
    @Override
    public String getName() {
        return "AcceptTimeEventAction";
        
    }

    @objid ("411b46b7-204a-49df-903b-a6e73b2f2d57")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("230edf0c-b9f6-443d-986c-d3bd3a0a0795")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AcceptTimeEventAction.class;
        
    }

    @objid ("80536f32-11f4-4f87-8b1d-b58632e561b5")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("e5d34ba1-b19c-474f-ae48-fb7f5fd3f59a")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("bc464060-e539-481b-bbb0-0567ac2a78e9")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new AcceptTimeEventActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.timeExpresionAtt = new TimeExpresionSmAttribute();
        this.timeExpresionAtt.init("TimeExpresion", this, String.class );
        registerAttribute(this.timeExpresionAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("58eb47b8-2efc-44cf-a73b-cb85f8992b18")
    public SmAttribute getTimeExpresionAtt() {
        if (this.timeExpresionAtt == null) {
        	this.timeExpresionAtt = this.getAttributeDef("TimeExpresion");
        }
        return this.timeExpresionAtt;
    }

    @objid ("56dada45-3dc6-4d56-962b-9749f84b86b4")
    private static class AcceptTimeEventActionObjectFactory implements ISmObjectFactory {
        @objid ("e8f9a263-fa23-4cd8-99dc-eedfe2ef9c59")
        private AcceptTimeEventActionSmClass smClass;

        @objid ("781177f2-0ddf-4440-8ec4-4be54618ed80")
        public  AcceptTimeEventActionObjectFactory(AcceptTimeEventActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("35c1260f-e7b8-4263-9919-aa90ad8dc2d4")
        @Override
        public ISmObjectData createData() {
            return new AcceptTimeEventActionData(this.smClass);
        }

        @objid ("1cecb28a-61fb-4a66-bb5a-742fc9b0d626")
        @Override
        public SmObjectImpl createImpl() {
            return new AcceptTimeEventActionImpl();
        }

    }

    @objid ("b8697550-142a-40a1-9c1f-24989a4620fb")
    public static class TimeExpresionSmAttribute extends SmAttribute {
        @objid ("838311a3-5ec3-4f33-b8fb-4ec3fc3fafd7")
        public Object getValue(ISmObjectData data) {
            return ((AcceptTimeEventActionData) data).mTimeExpresion;
        }

        @objid ("9d8e79bb-2c46-4133-a75f-d8cc32b3e0fa")
        public void setValue(ISmObjectData data, Object value) {
            ((AcceptTimeEventActionData) data).mTimeExpresion = value;
        }

    }

}
