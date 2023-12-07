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

package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("09aabc1b-3c73-4356-8c32-fe32f693c2c1")
public class ImpactDiagramSmClass extends AbstractDiagramSmClass {
    @objid ("e778b2e5-8835-449f-a7c9-1be6081e351f")
    public  ImpactDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3e1a0a61-98d7-4018-9075-77af3caf4426")
    @Override
    public String getName() {
        return "ImpactDiagram";
        
    }

    @objid ("efcbbec6-3cd7-44ed-bc6d-03a6413655e1")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("e55d8a28-3383-44c6-8502-cfbb70587ac8")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactDiagram.class;
        
    }

    @objid ("e345ad1a-acd4-4c66-9130-2ca55a9afaf2")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("f5b7f58e-b459-47e9-bdfd-b0c9a5940952")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("3fc03475-1748-4c2d-aa6c-0ed676c5903e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new ImpactDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("2beae2ef-f96a-4cf6-a497-966596be45ac")
    private static class ImpactDiagramObjectFactory implements ISmObjectFactory {
        @objid ("a052a45b-7e8e-4892-a4f8-9341b5de065b")
        private ImpactDiagramSmClass smClass;

        @objid ("a707999b-45fa-4bec-9f16-49fbd69eb900")
        public  ImpactDiagramObjectFactory(ImpactDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("844784b7-ba45-46c5-bf2e-6d1b69119238")
        @Override
        public ISmObjectData createData() {
            return new ImpactDiagramData(this.smClass);
        }

        @objid ("69730a33-ab0f-41cc-b46a-52bb84db3a6c")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactDiagramImpl();
        }

    }

}
