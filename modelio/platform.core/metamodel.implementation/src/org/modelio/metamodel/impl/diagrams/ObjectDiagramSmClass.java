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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.impl.diagrams.StaticDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("8ed0684b-53ee-4f59-8535-e31ca2634c95")
public class ObjectDiagramSmClass extends StaticDiagramSmClass {
    @objid ("53434db5-db07-42c6-839e-d2d59ddab913")
    public ObjectDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d139b506-986a-4e5d-9997-c14f96107c01")
    @Override
    public String getName() {
        return "ObjectDiagram";
    }

    @objid ("e1afddaa-c45c-4070-b4fb-22b2505de381")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1c11f4c6-2f85-4998-8316-8ed7b589e33a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ObjectDiagram.class;
    }

    @objid ("203dc996-7de3-4300-ba93-3c399cf7a251")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("16c62d63-a153-49ae-b332-3298852d2012")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("b40cdc83-a646-4886-bc4a-0e9e0b9a49f5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StaticDiagram.MQNAME);
        this.registerFactory(new ObjectDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("6942dd2f-8762-43fc-9c13-06f024a09aef")
    private static class ObjectDiagramObjectFactory implements ISmObjectFactory {
        @objid ("83c3dd4f-7753-4368-90df-3bfdb181b619")
        private ObjectDiagramSmClass smClass;

        @objid ("c2acccd7-a6fd-43d5-9433-f8e1506a644f")
        public ObjectDiagramObjectFactory(ObjectDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("7ead86b5-ad80-4089-ad7e-df20a58bb8dd")
        @Override
        public ISmObjectData createData() {
            return new ObjectDiagramData(this.smClass);
        }

        @objid ("75c66bcb-3c08-43f5-8cbb-1929bcd75515")
        @Override
        public SmObjectImpl createImpl() {
            return new ObjectDiagramImpl();
        }

    }

}
