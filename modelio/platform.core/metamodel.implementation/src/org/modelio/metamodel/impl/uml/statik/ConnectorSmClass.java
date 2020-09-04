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
import org.modelio.metamodel.impl.uml.statik.LinkSmClass;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("d3b7192d-019b-42f0-81c8-5dc12c1e6414")
public class ConnectorSmClass extends LinkSmClass {
    @objid ("656f3545-f1c6-49e7-9c29-9a1e0676d920")
    public ConnectorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e54c412a-62f9-43e9-8955-a2679aa1abd9")
    @Override
    public String getName() {
        return "Connector";
    }

    @objid ("b0a5c270-7e9c-4d6f-9f91-006059d608be")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("4e889854-7cb1-4305-92bd-ebca18ceea44")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Connector.class;
    }

    @objid ("809e30f4-fe60-4043-bd08-bf26ceb1cdb7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cd981494-ad35-4263-bce3-2a36a049493a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("bbfe2bfd-522c-435f-949c-f458754ee887")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Link.MQNAME);
        this.registerFactory(new ConnectorObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("48972357-f2a6-4b6a-bf59-67ce2625a861")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("94ff6f0d-687c-4daf-9b09-d48d3786307d")
    private static class ConnectorObjectFactory implements ISmObjectFactory {
        @objid ("41d47851-4ff5-4502-9f64-da7079731c8c")
        private ConnectorSmClass smClass;

        @objid ("7ff6ef99-6903-46a8-bdc7-c6448165f90a")
        public ConnectorObjectFactory(ConnectorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c927f6c1-7d1f-42a4-b546-cd52374ff87d")
        @Override
        public ISmObjectData createData() {
            return new ConnectorData(this.smClass);
        }

        @objid ("7faa6e5e-8f4f-423e-a57d-9e68fb8ed164")
        @Override
        public SmObjectImpl createImpl() {
            return new ConnectorImpl();
        }

    }

}
