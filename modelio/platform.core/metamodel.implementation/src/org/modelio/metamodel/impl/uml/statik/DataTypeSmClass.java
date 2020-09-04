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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1648b8b3-570e-4f03-92ab-6e61b22d1b02")
public class DataTypeSmClass extends GeneralClassSmClass {
    @objid ("6e734bd1-b24b-4fd2-846f-01c7337f61ee")
    public DataTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c233ac77-9d55-47aa-9d45-73f2cda0c057")
    @Override
    public String getName() {
        return "DataType";
    }

    @objid ("5eb3fbf2-9ee4-4ff4-91a4-9a3f1d2e3ae7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("448d7d85-b162-4876-b0a7-ce73d8c72785")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DataType.class;
    }

    @objid ("2af514db-befa-4b82-8f9f-5c2effa1fd12")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("832c6d86-3a9e-4b9f-9b54-0d493ac6889e")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("375b81a7-2291-4089-8552-4f6c6513d92b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new DataTypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("ff678c24-3df3-4959-91c5-f18f480c12ba")
    private static class DataTypeObjectFactory implements ISmObjectFactory {
        @objid ("181b6186-c921-4d82-b3bc-ac27d2a5e62c")
        private DataTypeSmClass smClass;

        @objid ("029e8820-4876-4455-8302-39d5fd60b4dc")
        public DataTypeObjectFactory(DataTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("847652c6-b124-4c47-aa1f-6893ffb9904a")
        @Override
        public ISmObjectData createData() {
            return new DataTypeData(this.smClass);
        }

        @objid ("bb36fab3-5905-4afa-8c7e-55c3b580fd6e")
        @Override
        public SmObjectImpl createImpl() {
            return new DataTypeImpl();
        }

    }

}
