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
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
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

@objid ("96f7f998-24f2-4040-be32-3fdb756e6161")
public class CompositeStructureDiagramSmClass extends StaticDiagramSmClass {
    @objid ("0cfab617-41fa-4571-93b3-dbec5726c874")
    public CompositeStructureDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("dd1ea90d-409f-438d-bded-809c8e547dc9")
    @Override
    public String getName() {
        return "CompositeStructureDiagram";
    }

    @objid ("0bb1a4a7-b5da-4f2a-ab1d-f6cd6f2a18f6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d3ca381c-e270-4e76-a735-656d6f9eb981")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CompositeStructureDiagram.class;
    }

    @objid ("af8e124d-023f-4f97-bea2-e093d4ed6b1d")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("8b8ee3cf-395c-46cf-99ea-56aefd5ee859")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0913cc07-8cd1-46f8-962d-2e19c4781ffd")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StaticDiagram.MQNAME);
        this.registerFactory(new CompositeStructureDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("065f73d9-72bb-46f3-89f0-776ee02e152a")
    private static class CompositeStructureDiagramObjectFactory implements ISmObjectFactory {
        @objid ("3e13c319-33ac-4d82-9077-c6d1079c64f2")
        private CompositeStructureDiagramSmClass smClass;

        @objid ("5816a8f5-1e01-4e17-8d1f-7c3c74d1738b")
        public CompositeStructureDiagramObjectFactory(CompositeStructureDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e1354df0-2cf5-4929-a7a6-e6a507317629")
        @Override
        public ISmObjectData createData() {
            return new CompositeStructureDiagramData(this.smClass);
        }

        @objid ("d767cad2-e980-46a3-ab73-a194b3885865")
        @Override
        public SmObjectImpl createImpl() {
            return new CompositeStructureDiagramImpl();
        }

    }

}
