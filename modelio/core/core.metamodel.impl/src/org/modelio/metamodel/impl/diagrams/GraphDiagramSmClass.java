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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("1606d446-dff4-439d-9b08-f24ed3814a9b")
    public  GraphDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("12fd9a9f-d684-4902-bee2-27fca4bbba71")
    @Override
    public String getName() {
        return "GraphDiagram";
        
    }

    @objid ("f86fba42-abc1-4858-930b-dfeb61bb2fbf")
    @Override
    public Version getVersion() {
        return new Version("2.1.02");
    }

    @objid ("fe696b7e-a2d7-43a8-b39c-648bb5c24386")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return GraphDiagram.class;
        
    }

    @objid ("802fe5ea-cc8f-40e6-ba2d-73273f2d4c49")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("dfbf9a58-b667-4ee0-ae0c-fb49bace59dc")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("441cd25b-8590-4449-b95d-190bf0452354")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new GraphDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("e54bdc5a-5209-49c7-b3cb-11287d6dde79")
    private static class GraphDiagramObjectFactory implements ISmObjectFactory {
        @objid ("502c4c3b-ded8-4152-ad98-152e36a672de")
        private GraphDiagramSmClass smClass;

        @objid ("56fe2488-132e-4bbc-9bf8-0f3632baa8c1")
        public  GraphDiagramObjectFactory(GraphDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e6b029a8-6b51-4e5a-830f-3aaef88b8bdc")
        @Override
        public ISmObjectData createData() {
            return new GraphDiagramData(this.smClass);
        }

        @objid ("cb1741c4-4ee7-4885-9e2f-ac0bca368c71")
        @Override
        public SmObjectImpl createImpl() {
            return new GraphDiagramImpl();
        }

    }

}
