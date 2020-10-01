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
import org.modelio.metamodel.diagrams.DeploymentDiagram;
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

@objid ("102f51de-ac14-432f-9fbf-c09f03c0fdb2")
public class DeploymentDiagramSmClass extends StaticDiagramSmClass {
    @objid ("352ab7af-3114-4721-b775-209eed6eebaf")
    public DeploymentDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("bc770c0d-450f-41fd-b940-0ada4a2d635a")
    @Override
    public String getName() {
        return "DeploymentDiagram";
    }

    @objid ("10b0f60b-0891-4218-b316-e5357c4ece59")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("06d94229-09a3-4386-b437-ebdea634af24")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DeploymentDiagram.class;
    }

    @objid ("b5fb1312-12a7-49c5-a5a8-f9fd6d444801")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("0714ef1f-0279-49f9-9904-4e250b129953")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("82bb2333-7568-4ea5-bb21-9353b0a925a5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StaticDiagram.MQNAME);
        this.registerFactory(new DeploymentDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("b183f986-d5d8-4505-9f87-8cd50f540b93")
    private static class DeploymentDiagramObjectFactory implements ISmObjectFactory {
        @objid ("e8379671-1345-4a91-8345-dbbbfd1bce91")
        private DeploymentDiagramSmClass smClass;

        @objid ("8e323146-8dfc-4885-a8af-b3fc20e20ac6")
        public DeploymentDiagramObjectFactory(DeploymentDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("909d3ac7-ba47-4c14-9ca3-197d8087ddde")
        @Override
        public ISmObjectData createData() {
            return new DeploymentDiagramData(this.smClass);
        }

        @objid ("187d90a6-2f35-4397-b79d-88cb52ea7b4a")
        @Override
        public SmObjectImpl createImpl() {
            return new DeploymentDiagramImpl();
        }

    }

}
