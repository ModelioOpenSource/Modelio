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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.metamodel.impl.impact.ImpactLinkSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
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
    @objid ("baddb9b8-0fb8-4278-8d51-a30ab8bf65f9")
    private SmDependency diagramElementDep;

    @objid ("6d1b8cf4-bd33-4d3e-b4d8-589be269d81f")
    private SmDependency addedToQueryDep;

    @objid ("ea246802-903e-4b92-949c-64a326597aed")
    private SmDependency causedImpactDep;

    @objid ("e33d278e-6ae8-4305-b235-2188e48f9fb7")
    public ElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6cb9734d-5d15-4659-a01f-9dfb446f9814")
    @Override
    public String getName() {
        return "Element";
    }

    @objid ("d96f0e7c-504c-4f96-8a8b-44302ac07d60")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("60fd148d-96eb-4cb1-97c2-ef10d36ef395")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Element.class;
    }

    @objid ("7af7400c-3246-485c-8f27-689ad00277a3")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("048b4f35-fd10-44cc-ace7-3600ff4f0d76")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("a32d2176-3b0e-4ed1-a15c-a91497e00d04")
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

    @objid ("96f4aefb-0f1c-4cfd-a15f-3688e278f04d")
    public SmDependency getDiagramElementDep() {
        if (this.diagramElementDep == null) {
        	this.diagramElementDep = this.getDependencyDef("DiagramElement");
        }
        return this.diagramElementDep;
    }

    @objid ("ad161805-7251-4492-ae7a-cb8c6bcef73a")
    public SmDependency getAddedToQueryDep() {
        if (this.addedToQueryDep == null) {
        	this.addedToQueryDep = this.getDependencyDef("AddedToQuery");
        }
        return this.addedToQueryDep;
    }

    @objid ("dd747fa5-361a-4e29-bf73-33e3fbda4239")
    public SmDependency getCausedImpactDep() {
        if (this.causedImpactDep == null) {
        	this.causedImpactDep = this.getDependencyDef("causedImpact");
        }
        return this.causedImpactDep;
    }

    @objid ("f5381cfc-8807-407c-a40c-c6a5d4976ba6")
    private static class ElementObjectFactory implements ISmObjectFactory {
        @objid ("3c4f634d-6889-49b3-be91-04a49c308cb9")
        private ElementSmClass smClass;

        @objid ("806a81b5-dbf6-4b78-a886-98e6b36ba3e5")
        public ElementObjectFactory(ElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ef534dc4-796f-4dd1-87ee-013f6f642928")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("e3b185a8-7e36-40d9-bef0-6759534fbfe7")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("14a19e0d-1a35-41a5-b29f-0d1c6540de33")
    public static class DiagramElementSmDependency extends SmMultipleDependency {
        @objid ("3ebfdf80-ed9b-4abf-9e45-25d3dab9d6d5")
        private SmDependency symetricDep;

        @objid ("c56c6473-09aa-40e3-ab58-2539715c368f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mDiagramElement != null)? ((ElementData)data).mDiagramElement:SmMultipleDependency.EMPTY;
        }

        @objid ("732e2a70-f5f0-4e41-af12-ff6649ebae17")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mDiagramElement = values;
        }

        @objid ("4d822a2c-c613-46bc-8c18-0ee6703d667c")
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
        @objid ("04c2182d-09a9-4744-afa5-1f408b62e84d")
        private SmDependency symetricDep;

        @objid ("8c98c754-cdad-44b6-b501-60e4300b92c6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mAddedToQuery != null)? ((ElementData)data).mAddedToQuery:SmMultipleDependency.EMPTY;
        }

        @objid ("7c716b4d-4946-4c72-b9d1-863988a9c910")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mAddedToQuery = values;
        }

        @objid ("a49daf60-1c1b-4774-98fa-f3bda20dce0e")
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
        @objid ("3f177ade-dfbb-46d7-84c3-3af93f112b79")
        private SmDependency symetricDep;

        @objid ("310d3c2e-460d-4d7d-b779-be4f20ca7a55")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mCausedImpact != null)? ((ElementData)data).mCausedImpact:SmMultipleDependency.EMPTY;
        }

        @objid ("316f1adf-e651-4ac1-b621-00a8743c7ede")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mCausedImpact = values;
        }

        @objid ("50b4386e-d6b1-40c7-9f4a-49bc3cf6b510")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getCausesDep();
            }
            return this.symetricDep;
        }

    }

}
