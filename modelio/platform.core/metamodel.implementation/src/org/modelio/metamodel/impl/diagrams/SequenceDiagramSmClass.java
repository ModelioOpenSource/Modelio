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
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.impl.diagrams.BehaviorDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("f758ab36-5b8b-41ca-b5d8-d649b4b16796")
public class SequenceDiagramSmClass extends BehaviorDiagramSmClass {
    @objid ("01ba73c2-82fb-4f0f-b217-78849ffc5fee")
    public SequenceDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c5fc7211-1884-416a-947a-515370a943ee")
    @Override
    public String getName() {
        return "SequenceDiagram";
    }

    @objid ("9e801171-e432-490a-a81f-2ac2db0558f4")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("cca66d3a-7806-408f-ac66-3900f4f55190")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return SequenceDiagram.class;
    }

    @objid ("41f0ea07-7431-48f1-a2b7-6467ed788a08")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("ecbc6aed-41fd-4003-85ec-7819cc8dee10")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6c676e49-0109-4839-a459-e848bd6b2e9c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehaviorDiagram.MQNAME);
        this.registerFactory(new SequenceDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("6a7a94c1-4dcd-40aa-9651-dd43ba08ddf0")
    private static class SequenceDiagramObjectFactory implements ISmObjectFactory {
        @objid ("7004c17a-6080-4594-9a42-6e2803767424")
        private SequenceDiagramSmClass smClass;

        @objid ("f41be964-9388-4716-b343-05b2d2875e00")
        public SequenceDiagramObjectFactory(SequenceDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("75a1fa7c-0f9c-4e34-b48b-04073667e0a2")
        @Override
        public ISmObjectData createData() {
            return new SequenceDiagramData(this.smClass);
        }

        @objid ("f73e6f56-036e-4d81-8d67-f53612390e0c")
        @Override
        public SmObjectImpl createImpl() {
            return new SequenceDiagramImpl();
        }

    }

}
