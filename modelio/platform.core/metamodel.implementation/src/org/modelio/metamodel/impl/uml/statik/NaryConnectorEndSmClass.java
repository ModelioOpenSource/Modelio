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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.NaryLinkEndSmClass;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("0658041f-9f8e-4044-a285-d0ab1d710f53")
public class NaryConnectorEndSmClass extends NaryLinkEndSmClass {
    @objid ("93a4924f-cc6d-4250-b180-ec00fdf61a39")
    public NaryConnectorEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("808fce74-3798-41bb-bf4e-93f51261f1e2")
    @Override
    public String getName() {
        return "NaryConnectorEnd";
    }

    @objid ("dd8d1148-027f-41f2-98e4-df668b61dbf1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e2235826-86b0-4a55-ac17-d98333b62c6f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NaryConnectorEnd.class;
    }

    @objid ("f1871481-8b8c-40fc-9d06-c8a46aff7481")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("b709b375-00a9-4211-8485-31830a274e96")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("90a91e90-f24e-48ab-b31b-c5ee19ba4bc6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(NaryLinkEnd.MQNAME);
        this.registerFactory(new NaryConnectorEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("acff590e-f662-4755-bae5-d65d69062fe8")
    private static class NaryConnectorEndObjectFactory implements ISmObjectFactory {
        @objid ("de39ea61-0639-4a55-ac17-9d3c92f66b49")
        private NaryConnectorEndSmClass smClass;

        @objid ("0d5a8b32-3812-4638-aa78-8efebbeb03c8")
        public NaryConnectorEndObjectFactory(NaryConnectorEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("633b1038-b0c6-4486-a055-fe8e9115c2bb")
        @Override
        public ISmObjectData createData() {
            return new NaryConnectorEndData(this.smClass);
        }

        @objid ("b7d3ba25-f1cf-4c0e-b810-6d09c189160a")
        @Override
        public SmObjectImpl createImpl() {
            return new NaryConnectorEndImpl();
        }

    }

}
