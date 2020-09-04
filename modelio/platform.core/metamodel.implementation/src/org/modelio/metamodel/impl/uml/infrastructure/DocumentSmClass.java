/* 
 * Copyright 2013-2019 Modeliosoft
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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("63ab18c6-0baa-40df-b62f-d7684ad6dafb")
    private SmAttribute abstractAtt;

    @objid ("0c28561f-d558-410c-a8ec-78ddd5f23e40")
    public DocumentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3d940bfb-8db7-4f51-aec5-1b7d55af24ec")
    @Override
    public String getName() {
        return "Document";
    }

    @objid ("c7117dcf-637a-42bc-84f0-8ed7b58e57ec")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("fac12355-b663-4b2f-8502-e58db551c35e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Document.class;
    }

    @objid ("df9ce3f8-502f-4c02-8b66-7838ce06049c")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("fb9cc3b5-0a6a-45c6-b97c-57a78bf665e6")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("b3195a3e-f441-407e-b5f6-701923c58488")
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

    @objid ("c2f17196-e8bf-4c2a-8258-7a7cbec7fd4c")
    public SmAttribute getAbstractAtt() {
        if (this.abstractAtt == null) {
        	this.abstractAtt = this.getAttributeDef("Abstract");
        }
        return this.abstractAtt;
    }

    @objid ("313f9381-b654-4124-acc8-c871603654b9")
    public static class AbstractSmAttribute extends SmAttribute {
        @objid ("31a1ec07-0181-47c1-a6e6-d83c22cbf642")
        public Object getValue(ISmObjectData data) {
            return ((DocumentData) data).mAbstract;
        }

        @objid ("960477ae-23f7-4e7b-b47b-07637eb48213")
        public void setValue(ISmObjectData data, Object value) {
            ((DocumentData) data).mAbstract = value;
        }

    }

    @objid ("2a598bd7-81b0-449c-96f3-e160935e8378")
    private static class DocumentObjectFactory implements ISmObjectFactory {
        @objid ("561efcdb-2ba7-465e-aca5-1015128b1b40")
        private DocumentSmClass smClass;

        @objid ("bb316e69-cfea-4115-8412-9b6b913958fa")
        public DocumentObjectFactory(DocumentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("05c8b634-4fb6-4a23-a3d6-f7f4adfdbac6")
        @Override
        public ISmObjectData createData() {
            return new DocumentData(this.smClass);
        }

        @objid ("c9a0e5ab-0c4e-4d26-9258-4164c0cf5ef9")
        @Override
        public SmObjectImpl createImpl() {
            return new DocumentImpl();
        }

    }

}
