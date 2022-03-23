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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("cfdce8a3-a136-4b46-ab5e-4b3c7dea4698")
public class StaticDiagramSmClass extends AbstractDiagramSmClass {
    @objid ("29230476-fdc0-4c89-a192-ad5db1ba4f65")
    public  StaticDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("8eaacab1-6d21-4705-b736-6ce2a3b74766")
    @Override
    public String getName() {
        return "StaticDiagram";
        
    }

    @objid ("d391df70-f064-4243-8515-730d96485fb5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("62d730c2-0b08-4dd3-b105-73208e8ad690")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StaticDiagram.class;
        
    }

    @objid ("daf4df01-ae0c-4bee-bf41-05ae9c8e492e")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("42556fa3-82d8-470c-a36c-b07c403f7b93")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("15265555-5624-40da-a42c-e57485b8c136")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new StaticDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("d761e3d8-2deb-4602-8c17-146ae1cce6fd")
    private static class StaticDiagramObjectFactory implements ISmObjectFactory {
        @objid ("e129e04d-c1bf-44aa-9b61-a1263cc1c668")
        private StaticDiagramSmClass smClass;

        @objid ("3e914ca3-066c-47d1-9b0d-051965045eb3")
        public  StaticDiagramObjectFactory(StaticDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4cd57c9d-c83e-4fc5-bd9d-20822bf7cb18")
        @Override
        public ISmObjectData createData() {
            return new StaticDiagramData(this.smClass);
        }

        @objid ("159d6d37-4f4f-4f5a-b507-74e803f8be22")
        @Override
        public SmObjectImpl createImpl() {
            return new StaticDiagramImpl();
        }

    }

}
