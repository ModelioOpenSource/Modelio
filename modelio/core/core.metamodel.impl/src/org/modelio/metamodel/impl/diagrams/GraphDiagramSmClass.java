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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.GraphDiagram;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b7bd8c6f-a843-4efb-bf69-5e27dc50bf10")
public class GraphDiagramSmClass extends AbstractDiagramSmClass {
    @objid ("7883a478-92d8-4ec7-a791-8ab0ae05f946")
    public  GraphDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("472e6f23-a397-4f6f-a44b-12710a1bda39")
    @Override
    public String getName() {
        return "GraphDiagram";
        
    }

    @objid ("f7734d9d-6b92-44da-ae82-b8fc6900098d")
    @Override
    public Version getVersion() {
        return new Version("2.1.02");
    }

    @objid ("8a6c58d0-1a8c-40d1-b185-2d6f9b448d41")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return GraphDiagram.class;
        
    }

    @objid ("eb438368-8500-41ff-8584-59b522645e5b")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("3fbd88da-4a23-459b-a80a-a43e05f8817f")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("8ff4cf78-0c8d-48a3-b181-82bbb00c7524")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new GraphDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("e54bdc5a-5209-49c7-b3cb-11287d6dde79")
    private static class GraphDiagramObjectFactory implements ISmObjectFactory {
        @objid ("e0e8c4d5-586b-4aea-b962-12d3f1fadc7f")
        private GraphDiagramSmClass smClass;

        @objid ("e7516c7f-0e69-42a9-9a01-f49aa18778fd")
        public  GraphDiagramObjectFactory(GraphDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("012bbd3b-1c56-487f-8b48-b2378cb72862")
        @Override
        public ISmObjectData createData() {
            return new GraphDiagramData(this.smClass);
        }

        @objid ("15046702-69a3-456b-b0f2-d82e202aedcc")
        @Override
        public SmObjectImpl createImpl() {
            return new GraphDiagramImpl();
        }

    }

}
