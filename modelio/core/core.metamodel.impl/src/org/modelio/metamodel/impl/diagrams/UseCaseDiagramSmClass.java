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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.impl.diagrams.StaticDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1ec2bda1-fb94-4a5c-a85c-452bd832a8fc")
public class UseCaseDiagramSmClass extends StaticDiagramSmClass {
    @objid ("7db7a15e-903c-4dc9-9118-3eecacd484c1")
    public UseCaseDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("97b7f9c2-9b96-4730-8b5a-48e5c3c996a0")
    @Override
    public String getName() {
        return "UseCaseDiagram";
    }

    @objid ("06d8cbc0-82ec-49e8-a269-abb66c2fce4a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1adf4951-d691-4aec-b530-b8a3a700cea1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return UseCaseDiagram.class;
    }

    @objid ("a93ba325-adeb-454e-b789-8de1d26a0062")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("00f141f6-ea97-473a-a362-49dcb057dfea")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("108a0a08-1c27-4e3b-a0d9-b4ed0b77a955")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StaticDiagram.MQNAME);
        this.registerFactory(new UseCaseDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("bfceb265-d6f8-4aea-b23f-30f227a71eef")
    private static class UseCaseDiagramObjectFactory implements ISmObjectFactory {
        @objid ("a3b43c23-acb1-4775-bc59-fdfd8b1750d6")
        private UseCaseDiagramSmClass smClass;

        @objid ("8275905a-a554-450e-a618-1504fb7ec02c")
        public UseCaseDiagramObjectFactory(UseCaseDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8a488f88-e27a-4827-b323-b188b40d7771")
        @Override
        public ISmObjectData createData() {
            return new UseCaseDiagramData(this.smClass);
        }

        @objid ("5e29ccbb-7716-46c3-91e1-18128faee926")
        @Override
        public SmObjectImpl createImpl() {
            return new UseCaseDiagramImpl();
        }

    }

}
