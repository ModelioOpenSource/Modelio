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
import org.modelio.metamodel.impl.uml.infrastructure.DependencySmClass;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("e0ca2607-d7fa-4215-a81e-31bcf412a838")
public class MethodologicalLinkSmClass extends DependencySmClass {
    @objid ("ec123ca0-05fc-46cd-81c7-636db2e2969a")
    public MethodologicalLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e626e236-2176-42a5-98e8-291f5e500386")
    @Override
    public String getName() {
        return "MethodologicalLink";
    }

    @objid ("20a03d31-bfd5-4723-8a26-663820bfbf34")
    @Override
    public Version getVersion() {
        return new Version("2.1.01");
    }

    @objid ("7f76a5a3-d1ef-4326-8310-c67fddae17a9")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MethodologicalLink.class;
    }

    @objid ("606ce2e0-ab45-4019-a5c3-651ac2b366de")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5d6a2974-1b8f-479f-ac9d-403eb1abb322")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("5abe9aaf-8e35-4a3f-b8af-8c2d248b50d5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Dependency.MQNAME);
        this.registerFactory(new MethodologicalLinkObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("bf28a432-bbb2-49f3-b59e-e2cd9b32069d")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("07da5a0e-44b5-4280-a583-dde4bf83a630")
    private static class MethodologicalLinkObjectFactory implements ISmObjectFactory {
        @objid ("176330b1-55c0-4221-ad5f-f680b03c7d56")
        private MethodologicalLinkSmClass smClass;

        @objid ("b52caced-8a12-46b5-a772-8459d83f2bb8")
        public MethodologicalLinkObjectFactory(MethodologicalLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("25d8d6f4-d05d-4582-a61c-b53e33783b30")
        @Override
        public ISmObjectData createData() {
            return new MethodologicalLinkData(this.smClass);
        }

        @objid ("34fa421b-4646-4ee3-a602-a197b3428df8")
        @Override
        public SmObjectImpl createImpl() {
            return new MethodologicalLinkImpl();
        }

    }

}
