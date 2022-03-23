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
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("17789ac7-cdee-470b-bfec-fca419117bb1")
public class CommunicationDiagramSmClass extends BehaviorDiagramSmClass {
    @objid ("57a503d7-439d-44cb-9d94-eb5535de0c58")
    public  CommunicationDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("76d600d7-0dcf-4931-b9f8-b763a7b27210")
    @Override
    public String getName() {
        return "CommunicationDiagram";
        
    }

    @objid ("a2d40773-b8a7-4846-8454-9a5fe3c6dd4e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("39e4af89-e23e-49cf-8617-f1f3b071a5ef")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CommunicationDiagram.class;
        
    }

    @objid ("c0f4db6f-da3c-41e3-b9db-9173f4cf7806")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("7d8b6220-3382-4696-b13d-8e3dfc1c0a00")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("9e5210c8-2f50-4ddb-bb18-bf382a3f2402")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehaviorDiagram.MQNAME);
        this.registerFactory(new CommunicationDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("87964011-b5f5-4194-a4db-22d7f5a953ba")
    private static class CommunicationDiagramObjectFactory implements ISmObjectFactory {
        @objid ("1c1972b8-3674-4a47-9098-4d825c875720")
        private CommunicationDiagramSmClass smClass;

        @objid ("a89f48d3-2475-47fa-95f0-17cde86d1355")
        public  CommunicationDiagramObjectFactory(CommunicationDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("389e1a8f-9aa6-4e1e-a7a4-b5913e3867e8")
        @Override
        public ISmObjectData createData() {
            return new CommunicationDiagramData(this.smClass);
        }

        @objid ("74035b7e-c6eb-4c70-8f8e-65b89c23a2ee")
        @Override
        public SmObjectImpl createImpl() {
            return new CommunicationDiagramImpl();
        }

    }

}
