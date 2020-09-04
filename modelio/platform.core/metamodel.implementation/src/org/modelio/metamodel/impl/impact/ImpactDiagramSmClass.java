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
    @objid ("c53e129a-dfb3-4f5f-955c-2c1214cdf041")
    public ImpactDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0bc6ca12-6eea-4f9b-9d04-1f31824bfc74")
    @Override
    public String getName() {
        return "ImpactDiagram";
    }

    @objid ("fdcd9a03-ef0f-40a3-8ce6-01e5dbe19a8c")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("0ec1b7d9-0ae5-4bdf-bb67-066b1a08f804")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactDiagram.class;
    }

    @objid ("0cc991be-e26c-48eb-b51d-75c01be892e8")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("21986a62-5eb7-448f-af72-c15a052bbfb7")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6a5b804e-fcd3-427d-bcaf-4f46726803f5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new ImpactDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("2beae2ef-f96a-4cf6-a497-966596be45ac")
    private static class ImpactDiagramObjectFactory implements ISmObjectFactory {
        @objid ("ad59ec67-e908-4067-9f65-238491dfcfe4")
        private ImpactDiagramSmClass smClass;

        @objid ("565400a7-540e-4488-a832-a64c2c16bf52")
        public ImpactDiagramObjectFactory(ImpactDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("463bf4ae-b04c-4061-a503-5099fbf79ddd")
        @Override
        public ISmObjectData createData() {
            return new ImpactDiagramData(this.smClass);
        }

        @objid ("c066d855-d0fd-4cde-8e88-f4be339243e7")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactDiagramImpl();
        }

    }

}
