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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("7fd8f258-bd39-4bba-b9f1-59475256ec89")
public class DocumentSmClass extends AbstractResourceSmClass {
    @objid ("b35f67b5-e1c5-4a3f-aede-9190e2af6a00")
    private SmAttribute abstractAtt;

    @objid ("b8734317-cd72-4519-85c6-a1650086f682")
    public  DocumentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ed9ad890-37c1-4110-b121-ef7f85e1001d")
    @Override
    public String getName() {
        return "Document";
        
    }

    @objid ("5ef11957-6cd9-4704-96f2-6d686d2dbc5a")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("baf76c8d-e25d-4959-8f5d-fb74dfad94ae")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Document.class;
        
    }

    @objid ("f733418d-2cd2-4604-b875-4c0c9664dd7c")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("7af5d5dc-39cc-4a8f-a88c-57e45962e4d5")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7814a8a0-7295-4ed6-89e1-637181614e75")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractResource.MQNAME);
        this.registerFactory(new DocumentObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.abstractAtt = new AbstractSmAttribute();
        this.abstractAtt.init("Abstract", this, String.class );
        registerAttribute(this.abstractAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("eee10a86-66f5-41ac-8ee2-dbc342ca6633")
    public SmAttribute getAbstractAtt() {
        if (this.abstractAtt == null) {
        	this.abstractAtt = this.getAttributeDef("Abstract");
        }
        return this.abstractAtt;
    }

    @objid ("313f9381-b654-4124-acc8-c871603654b9")
    public static class AbstractSmAttribute extends SmAttribute {
        @objid ("26feb034-cae3-4a50-8536-54a118d4af05")
        public Object getValue(ISmObjectData data) {
            return ((DocumentData) data).mAbstract;
        }

        @objid ("cda1e42d-8efe-48dc-83de-6bd91a360464")
        public void setValue(ISmObjectData data, Object value) {
            ((DocumentData) data).mAbstract = value;
        }

    }

    @objid ("2a598bd7-81b0-449c-96f3-e160935e8378")
    private static class DocumentObjectFactory implements ISmObjectFactory {
        @objid ("931d45bf-1b16-4bee-a0d0-1bf3a8e083b4")
        private DocumentSmClass smClass;

        @objid ("495e3c2d-9247-4840-99ba-c86ccc7ba290")
        public  DocumentObjectFactory(DocumentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("02ee55b4-356f-4b8d-aad2-97ac42c7f883")
        @Override
        public ISmObjectData createData() {
            return new DocumentData(this.smClass);
        }

        @objid ("331bf1f0-392f-4772-8d4d-be1ff0ddde39")
        @Override
        public SmObjectImpl createImpl() {
            return new DocumentImpl();
        }

    }

}
