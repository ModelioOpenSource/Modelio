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
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("9c163ab9-ebb8-4cba-afa9-cc99ba5c68b0")
public class ClassDiagramSmClass extends StaticDiagramSmClass {
    @objid ("6233d31b-1713-4885-a1b1-9d47b5fc8bd7")
    public  ClassDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("eed80849-8d14-4b87-b595-4b4344a5a286")
    @Override
    public String getName() {
        return "ClassDiagram";
        
    }

    @objid ("62524fcb-ab88-41dc-91fc-c94f7fe19f64")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0d9041a3-5a2b-4721-b63e-f0f313481841")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ClassDiagram.class;
        
    }

    @objid ("47bab0a1-b7db-407d-8dbf-91e35d32d5b2")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("090c39b7-e973-444d-9710-31a89af7dee5")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("4fb2ddf5-a9c4-4eb0-96f6-272e7234aad0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StaticDiagram.MQNAME);
        this.registerFactory(new ClassDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("ba3a511c-6d1f-4a6c-acc5-a3f8cad06792")
    private static class ClassDiagramObjectFactory implements ISmObjectFactory {
        @objid ("28729beb-945d-4e26-bd07-9fcdc21f200d")
        private ClassDiagramSmClass smClass;

        @objid ("896996e7-9f51-4008-93c1-de63ff0b248c")
        public  ClassDiagramObjectFactory(ClassDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6de3b915-2687-4003-a76b-99fcaf662b5c")
        @Override
        public ISmObjectData createData() {
            return new ClassDiagramData(this.smClass);
        }

        @objid ("59b62805-1773-423a-b3fd-9114d93eca17")
        @Override
        public SmObjectImpl createImpl() {
            return new ClassDiagramImpl();
        }

    }

}
