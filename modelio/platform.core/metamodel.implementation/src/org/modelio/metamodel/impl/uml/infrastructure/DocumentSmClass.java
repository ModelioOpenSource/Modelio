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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceSmClass;
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
    @objid ("6de0a810-ff02-4606-92f5-b344d34b4fbf")
    private SmAttribute abstractAtt;

    @objid ("a7a8beda-b22f-4382-8987-c27a9b073ba8")
    public DocumentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("83618930-262a-420f-95f9-ad5e9dbff306")
    @Override
    public String getName() {
        return "Document";
    }

    @objid ("77c04d6e-c113-48ba-b129-a787769c0eab")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("2f1cf640-9e4a-475f-b42e-da87e8f6f6fe")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Document.class;
    }

    @objid ("3d08a256-6ff0-42fb-aa7a-807bae09f2e6")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("3a3083d2-cc92-47ee-a36b-e6620445d2df")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("25d63316-37f1-44a9-af91-2283a1fa29b3")
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

    @objid ("e7a0dbc0-c959-4edd-99dc-0636bee2ef39")
    public SmAttribute getAbstractAtt() {
        if (this.abstractAtt == null) {
        	this.abstractAtt = this.getAttributeDef("Abstract");
        }
        return this.abstractAtt;
    }

    @objid ("313f9381-b654-4124-acc8-c871603654b9")
    public static class AbstractSmAttribute extends SmAttribute {
        @objid ("6eef66fa-b4f0-4df3-9e09-504a7ef080e3")
        public Object getValue(ISmObjectData data) {
            return ((DocumentData) data).mAbstract;
        }

        @objid ("9a958239-92c9-402a-932b-a288261b1c90")
        public void setValue(ISmObjectData data, Object value) {
            ((DocumentData) data).mAbstract = value;
        }

    }

    @objid ("2a598bd7-81b0-449c-96f3-e160935e8378")
    private static class DocumentObjectFactory implements ISmObjectFactory {
        @objid ("63f3ace8-6f28-4982-a24b-aaef86d176b5")
        private DocumentSmClass smClass;

        @objid ("524a8368-0fa5-4352-9933-ec74375ae662")
        public DocumentObjectFactory(DocumentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8d6c548e-cb70-4f3b-9215-c557b7925bcc")
        @Override
        public ISmObjectData createData() {
            return new DocumentData(this.smClass);
        }

        @objid ("4dae9c2c-34dc-4703-9b49-ae4d5242cd92")
        @Override
        public SmObjectImpl createImpl() {
            return new DocumentImpl();
        }

    }

}
