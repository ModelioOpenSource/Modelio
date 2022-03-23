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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
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

@objid ("8e431131-6ece-4a04-9e8c-d76c1e35d70f")
public class AcceptChangeEventActionSmClass extends ActivityActionSmClass {
    @objid ("c4b2e51e-0e0e-471f-9e91-e095088c21a7")
    private SmAttribute changeExpresionAtt;

    @objid ("e2f86c6d-3afb-46c1-8e9f-afcb12537329")
    public  AcceptChangeEventActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7ea65ffa-0efa-43b1-9889-82fe8ea03266")
    @Override
    public String getName() {
        return "AcceptChangeEventAction";
        
    }

    @objid ("f2ca31ae-6891-4340-9910-4999000df2c4")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f4f62c42-3165-4690-80be-0715b6792ca0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AcceptChangeEventAction.class;
        
    }

    @objid ("70539c2e-3333-44d6-8be3-843eef4e906d")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("ebae726c-96ac-4809-bf13-f45ba42ac931")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b57ccc9a-7a7a-47cf-949d-5583c5ab63af")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new AcceptChangeEventActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.changeExpresionAtt = new ChangeExpresionSmAttribute();
        this.changeExpresionAtt.init("ChangeExpresion", this, String.class );
        registerAttribute(this.changeExpresionAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("40083800-335b-41a4-a475-099d444845c2")
    public SmAttribute getChangeExpresionAtt() {
        if (this.changeExpresionAtt == null) {
        	this.changeExpresionAtt = this.getAttributeDef("ChangeExpresion");
        }
        return this.changeExpresionAtt;
    }

    @objid ("13eff77b-8ae2-48eb-a40e-97fcc2abaf1a")
    private static class AcceptChangeEventActionObjectFactory implements ISmObjectFactory {
        @objid ("a98a7854-78c7-47db-a7b5-72ba1255c10d")
        private AcceptChangeEventActionSmClass smClass;

        @objid ("7f24bcb0-a03d-47b6-87b5-7709aac45faa")
        public  AcceptChangeEventActionObjectFactory(AcceptChangeEventActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("36b98df2-d015-454b-98c3-9e46cc7eb64d")
        @Override
        public ISmObjectData createData() {
            return new AcceptChangeEventActionData(this.smClass);
        }

        @objid ("389eba34-29ad-42bc-a91c-a41b033b82ce")
        @Override
        public SmObjectImpl createImpl() {
            return new AcceptChangeEventActionImpl();
        }

    }

    @objid ("28d586c8-42e6-4b69-8d2e-5f738a02e89f")
    public static class ChangeExpresionSmAttribute extends SmAttribute {
        @objid ("08ea7969-099b-4151-971b-cddf8d74f3c5")
        public Object getValue(ISmObjectData data) {
            return ((AcceptChangeEventActionData) data).mChangeExpresion;
        }

        @objid ("9dfb9534-60d2-4648-9b1e-c486baccd303")
        public void setValue(ISmObjectData data, Object value) {
            ((AcceptChangeEventActionData) data).mChangeExpresion = value;
        }

    }

}
