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
    @objid ("f58f85b6-21cf-4ecf-99b8-927b1140d81a")
    private SmDependency diagramElementDep;

    @objid ("74cf3bff-4081-4def-a7a1-375b96447ed6")
    private SmDependency addedToQueryDep;

    @objid ("2d7abae7-2b55-4a79-9158-efabf74d136c")
    private SmDependency causedImpactDep;

    @objid ("832a8f94-1dac-47cf-96ed-e9afb008dbf2")
    public  ElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("400fe4e5-311d-4b55-b7f4-035bedee9a78")
    @Override
    public String getName() {
        return "Element";
        
    }

    @objid ("b56705a3-9e68-4685-bd17-1fed14fc436f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("4d5b77bb-11e5-4947-a620-bcf1564eb1da")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Element.class;
        
    }

    @objid ("70929ebc-ede0-4c95-ac99-cb9651625187")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("c03734cc-7ca1-4f9f-af77-c51070feadab")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("46b37b92-ca7c-4b6c-b909-bea0d407e243")
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

    @objid ("e14c8ff3-2092-49be-8cf3-4deee9432e76")
    public SmDependency getDiagramElementDep() {
        if (this.diagramElementDep == null) {
        	this.diagramElementDep = this.getDependencyDef("DiagramElement");
        }
        return this.diagramElementDep;
    }

    @objid ("366bd484-4adf-4050-afc3-0083f0de2576")
    public SmDependency getAddedToQueryDep() {
        if (this.addedToQueryDep == null) {
        	this.addedToQueryDep = this.getDependencyDef("AddedToQuery");
        }
        return this.addedToQueryDep;
    }

    @objid ("f4c02ba3-72f3-4497-b35c-39368ff56d44")
    public SmDependency getCausedImpactDep() {
        if (this.causedImpactDep == null) {
        	this.causedImpactDep = this.getDependencyDef("causedImpact");
        }
        return this.causedImpactDep;
    }

    @objid ("f5381cfc-8807-407c-a40c-c6a5d4976ba6")
    private static class ElementObjectFactory implements ISmObjectFactory {
        @objid ("2fe29128-d67c-4c27-be77-d31f4db3c238")
        private ElementSmClass smClass;

        @objid ("dc717e7b-d16b-49bf-9d4d-b614e527e203")
        public  ElementObjectFactory(ElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("08d999d9-30db-4154-b447-60d3b5d771c0")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("4804d5f0-d0f3-4ef7-b3d9-2d176221f29b")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("14a19e0d-1a35-41a5-b29f-0d1c6540de33")
    public static class DiagramElementSmDependency extends SmMultipleDependency {
        @objid ("e852eb01-d812-419e-820d-d99af7617c72")
        private SmDependency symetricDep;

        @objid ("50370275-b8e3-45e9-945d-e00a41d412ea")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mDiagramElement != null)? ((ElementData)data).mDiagramElement:SmMultipleDependency.EMPTY;
        }

        @objid ("72e237cd-4ce1-429c-9125-f4ce7fdd6843")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mDiagramElement = values;
            
        }

        @objid ("749d64c9-1c5a-443a-864c-38665dec0a0e")
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
        @objid ("77da965f-193c-4ced-b2f5-b6b552817038")
        private SmDependency symetricDep;

        @objid ("226aeae2-a325-4eae-9d9e-64d2aa56ca57")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mAddedToQuery != null)? ((ElementData)data).mAddedToQuery:SmMultipleDependency.EMPTY;
        }

        @objid ("a60b2890-a62f-492a-b071-4b83de201d8c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mAddedToQuery = values;
            
        }

        @objid ("ee715647-55fc-4e20-ae04-df5d634f72ff")
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
        @objid ("6b83940f-7708-45ad-bc84-400cc27de9e9")
        private SmDependency symetricDep;

        @objid ("0c6dafa1-e33f-4dc3-9431-528993c041cc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ElementData)data).mCausedImpact != null)? ((ElementData)data).mCausedImpact:SmMultipleDependency.EMPTY;
        }

        @objid ("8c6510db-68a9-440a-a98f-322e7d017687")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ElementData) data).mCausedImpact = values;
            
        }

        @objid ("396e1a7c-0a24-440b-8421-4743bdf258d5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ImpactLinkSmClass)this.getTarget()).getCausesDep();
            }
            return this.symetricDep;
            
        }

    }

}
