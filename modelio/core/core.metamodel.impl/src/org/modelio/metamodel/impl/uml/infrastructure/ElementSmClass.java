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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.metamodel.impl.impact.ImpactLinkSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("5e35cfc3-aa81-4d13-bfe8-4dc4d833f6aa")
public class ElementSmClass extends SmObjectSmClass {
    @objid ("984e82aa-eb2e-4b92-b8ed-24c6c88d5d80")
    private SmDependency diagramElementDep;

    @objid ("92c34671-c661-4327-b409-52d1c9b61089")
    private SmDependency addedToQueryDep;

    @objid ("8096e984-93f4-4c87-80fe-d1ea059ec104")
    private SmDependency causedImpactDep;

    @objid ("03707016-8040-40ab-8e1b-7d488619551d")
    public  ElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("64d9799c-6a57-4ae4-98d0-ed8e92070064")
    @Override
    public String getName() {
        return "Element";
        
    }

    @objid ("0859caef-d2a4-4f7c-828a-07e52ef4e927")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("de4ebc6c-ae96-41be-86a0-c1ba819b17d1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Element.class;
        
    }

    @objid ("8482cdbf-6f50-4e1d-8387-4839209e1ac0")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("2dbba4f9-477f-49d1-84f8-348f8ea277bc")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("bb3d7b21-9ba1-43ab-a076-35cdb1c063f6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass("SmObject");
        this.registerFactory(new ElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.diagramElementDep = new DiagramElementSmDependency();
        this.diagramElementDep.init("DiagramElement", this, metamodel.getMClass(AbstractDiagram.MQNAME), 0, -1 );
        registerDependency(this.diagramElementDep);
        
        this.addedToQueryDep = new AddedToQuerySmDependency();
        this.addedToQueryDep.init("AddedToQuery", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.addedToQueryDep);
        
        this.causedImpactDep = new CausedImpactSmDependency();
        this.causedImpactDep.init("causedImpact", this, metamodel.getMClass(ImpactLink.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.causedImpactDep);
        
        
    }

    @objid ("c343d7f9-f175-4d90-a31a-e4073df5dbf1")
    public SmDependency getDiagramElementDep() {
        if (this.diagramElementDep == null) {
        	this.diagramElementDep = this.getDependencyDef("DiagramElement");
        }
        return this.diagramElementDep;
    }

    @objid ("de854bb5-99fb-4ac9-9ddc-ac29f221fc76")
    public SmDependency getAddedToQueryDep() {
        if (this.addedToQueryDep == null) {
        	this.addedToQueryDep = this.getDependencyDef("AddedToQuery");
        }
        return this.addedToQueryDep;
    }

    @objid ("0de93c10-597f-45cf-a255-5dfb8898a56b")
    public SmDependency getCausedImpactDep() {
        if (this.causedImpactDep == null) {
        	this.causedImpactDep = this.getDependencyDef("causedImpact");
        }
        return this.causedImpactDep;
    }

    @objid ("f5381cfc-8807-407c-a40c-c6a5d4976ba6")
    private static class ElementObjectFactory implements ISmObjectFactory {
        @objid ("4dc55c66-b37b-4fe1-92d2-af341a94b8dc")
        private ElementSmClass smClass;

        @objid ("db390c82-fe1a-4b03-a76c-16c0405d0bc2")
        public  ElementObjectFactory(ElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("115b190e-f574-40e6-bc38-3a902158a2a5")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("54001db7-a929-4da0-8148-bc9cb14418e6")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("14a19e0d-1a35-41a5-b29f-0d1c6540de33")
    public static class DiagramElementSmDependency extends SmMultipleDependency {
        @objid ("5e5cddf4-9a98-4d51-ad2c-55dde7586b2f")
        private SmDependency symetricDep;

        @objid ("bd46b50d-0c35-47d7-9337-ba4acaa48034")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mDiagramElement != null)? ((ElementData)data).mDiagramElement:SmMultipleDependency.EMPTY;
        }

        @objid ("9c4bc50f-a512-4e58-87d8-062b7b128f96")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mDiagramElement = values;
            
        }

        @objid ("547b3b22-3d4c-476a-a219-80ac9846cd39")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractDiagramSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("b93bb4e2-fe3a-4aab-b02a-03e50704ba32")
    public static class AddedToQuerySmDependency extends SmMultipleDependency {
        @objid ("542cc320-cf98-4f6f-abcd-eeb10dcb1c9b")
        private SmDependency symetricDep;

        @objid ("702e12a9-72ed-4207-9826-476568aba550")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mAddedToQuery != null)? ((ElementData)data).mAddedToQuery:SmMultipleDependency.EMPTY;
        }

        @objid ("d2ca5d8f-0f04-4edb-8d1f-61619683d694")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mAddedToQuery = values;
            
        }

        @objid ("eb2538b6-f8aa-4200-90ac-f26154597e79")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getAddedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("792d76ed-8d73-4343-a295-4678f4dd1f97")
    public static class CausedImpactSmDependency extends SmMultipleDependency {
        @objid ("5d69cad7-c22f-48bd-ad63-a7b42c88a651")
        private SmDependency symetricDep;

        @objid ("86264cfc-80cd-41c5-b67d-1bb882e2e903")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mCausedImpact != null)? ((ElementData)data).mCausedImpact:SmMultipleDependency.EMPTY;
        }

        @objid ("0a8b31af-e493-40a8-ba88-b0039be60e26")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mCausedImpact = values;
            
        }

        @objid ("11d87b4a-4af7-4596-b424-d5f556a2e115")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getCausesDep();
            }
            return this.symetricDep;
            
        }

    }

}
