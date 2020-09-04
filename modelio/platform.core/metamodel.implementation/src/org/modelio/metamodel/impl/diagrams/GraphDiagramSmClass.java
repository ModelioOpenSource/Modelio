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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.GraphDiagram;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
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
    @objid ("d2badb22-709a-48fd-a226-3c2c65598286")
    public GraphDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e5494ee7-0b46-40bb-8d31-27b3dc4d8058")
    @Override
    public String getName() {
        return "GraphDiagram";
    }

    @objid ("ec604a70-dcf7-4695-a8ca-5700f527f1e8")
    @Override
    public Version getVersion() {
        return new Version("2.1.02");
    }

    @objid ("1f919729-55a3-44a7-8790-2dd05cc8a883")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return GraphDiagram.class;
    }

    @objid ("0b7e5279-07b9-4c3b-9a33-c773649c72f8")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("baace7ad-f1c8-4e37-943d-60c635fae780")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0de4618a-2070-4159-ba7d-51ae5e44c7ec")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new GraphDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("e54bdc5a-5209-49c7-b3cb-11287d6dde79")
    private static class GraphDiagramObjectFactory implements ISmObjectFactory {
        @objid ("c9adad16-cd77-416e-857c-33a5fb598197")
        private GraphDiagramSmClass smClass;

        @objid ("5dd9e8e4-c116-4052-8491-31e1dd735c44")
        public GraphDiagramObjectFactory(GraphDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1f59699b-2f04-48a7-a643-ed7419b1323b")
        @Override
        public ISmObjectData createData() {
            return new GraphDiagramData(this.smClass);
        }

        @objid ("bfa15013-75fa-4441-a85f-8714b0f7dd84")
        @Override
        public SmObjectImpl createImpl() {
            return new GraphDiagramImpl();
        }

    }

}
