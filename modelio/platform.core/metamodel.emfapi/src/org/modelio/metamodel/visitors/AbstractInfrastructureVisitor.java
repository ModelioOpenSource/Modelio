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

package org.modelio.metamodel.visitors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.diagrams.GraphDiagram;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;

/**
 * This class is an implementation of {@link IInfrastructureVisitor} whose visit() methods simply return <code>null</code>.
 */
@objid ("7ce91ec3-517e-41f1-a680-3fe68fe7907f")
public class AbstractInfrastructureVisitor implements IInfrastructureVisitor {
    @objid ("5e780ac3-7311-4f5f-a20a-ffdbbd50d2db")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram obj) {
        	return null;
    }

    @objid ("e561e619-f6a3-4b0d-a46e-1e0d0f6e47b3")
    @Override
    public Object visitAbstractProject(AbstractProject obj) {
        	return null;
    }

    @objid ("fa308e94-cfe8-412d-a3eb-53ad4b1e2812")
    @Override
    public Object visitAbstractResource(AbstractResource obj) {
        	return null;
    }

    @objid ("91948d04-cd69-4ffa-8924-c428c488ea01")
    @Override
    public Object visitDependency(Dependency obj) {
        	return null;
    }

    @objid ("b44d2098-1cb8-4c0e-8015-4a1fc1571249")
    @Override
    public Object visitDiagramSet(DiagramSet obj) {
        	return null;
    }

    @objid ("992ef148-f310-4dc4-928e-4eab94d8cbf1")
    @Override
    public Object visitDocument(Document obj) {
        	return null;
    }

    @objid ("515232c0-4f38-4fad-a76c-85824bfbc0ab")
    @Override
    public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        	return null;
    }

    @objid ("a9a6c41a-9a26-4930-88ad-c2ef7081b89f")
    @Override
    public Object visitElement(Element obj) {
        	return null;
    }

    @objid ("dc55cff2-c63e-45c6-bca6-399cf6051e22")
    @Override
    public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        	return null;
    }

    @objid ("6a7bf13d-9bd6-49f4-bb3e-443e60f86b4e")
    @Override
    public Object visitExternProcessor(ExternProcessor obj) {
        	return null;
    }

    @objid ("2565695f-38da-4231-98f1-e29d53affbc4")
    @Override
    public Object visitGraphDiagram(GraphDiagram obj) {
        	return null;
    }

    @objid ("0a92f221-967a-4835-ae8a-d050ed34f23b")
    @Override
    public Object visitImpactDiagram(ImpactDiagram obj) {
        	return null;
    }

    @objid ("74f0bed4-8e55-4c49-bc93-27b2f6d4979d")
    @Override
    public Object visitImpactLink(ImpactLink obj) {
        	return null;
    }

    @objid ("bb136ce3-6cff-40e0-8c09-969376fb2219")
    @Override
    public Object visitImpactModel(ImpactModel obj) {
        	return null;
    }

    @objid ("806363e6-db34-45b0-9023-6c48d005758f")
    @Override
    public Object visitImpactProject(ImpactProject obj) {
        	return null;
    }

    @objid ("d3aaf29e-cdd8-45af-bd12-f4e34f4be629")
    @Override
    public Object visitLocalPropertyTable(LocalPropertyTable obj) {
        	return null;
    }

    @objid ("60e219d8-bd0a-494b-a9d0-338fa213656b")
    @Override
    public Object visitMatrixDefinition(MatrixDefinition obj) {
        	return null;
    }

    @objid ("a72e64e6-e641-41df-a72a-8dc41ea96fad")
    @Override
    public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        	return null;
    }

    @objid ("05777564-5dd1-4f5b-87dc-5d98a85da612")
    @Override
    public Object visitMetaclassReference(MetaclassReference obj) {
        	return null;
    }

    @objid ("16879d0f-489b-4d88-b2f8-b4e1898ad89a")
    @Override
    public Object visitMethodologicalLink(MethodologicalLink obj) {
        	return null;
    }

    @objid ("11da9c51-3fde-468e-a757-f915aa175a5a")
    @Override
    public Object visitModelElement(ModelElement obj) {
        	return null;
    }

    @objid ("fb2c7d36-07ec-4975-a69e-7df7a52d635c")
    @Override
    public Object visitModuleComponent(ModuleComponent obj) {
        	return null;
    }

    @objid ("5ee3f480-8ba8-4273-bb53-e614b3bcd1be")
    @Override
    public Object visitModuleParameter(ModuleParameter obj) {
        	return null;
    }

    @objid ("1a643ce7-df60-45de-a95a-6fcef79e9d54")
    @Override
    public Object visitNote(Note obj) {
        	return null;
    }

    @objid ("5c9eeaf7-2e49-4d04-bd3e-f56fea1826ba")
    @Override
    public Object visitNoteType(NoteType obj) {
        	return null;
    }

    @objid ("02023804-7832-4342-a99c-b79de10a2e4c")
    @Override
    public Object visitProfile(Profile obj) {
        	return null;
    }

    @objid ("f9aee2b9-94c9-4ce3-900e-9c4d97b3ad9b")
    @Override
    public Object visitPropertyDefinition(PropertyDefinition obj) {
        	return null;
    }

    @objid ("fd465037-2d1c-4a60-b89d-2c4f1182e5c5")
    @Override
    public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        	return null;
    }

    @objid ("b15056ff-b321-40b5-9ca3-b5f62a1d8396")
    @Override
    public Object visitPropertyTable(PropertyTable obj) {
        	return null;
    }

    @objid ("6640d648-3c12-4dda-acb3-7bdf410f4fca")
    @Override
    public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        	return null;
    }

    @objid ("544dea84-0861-4e89-80dd-1488a5dc1285")
    @Override
    public Object visitPropertyType(PropertyType obj) {
        	return null;
    }

    @objid ("9cd43c1e-cdc8-4022-a964-d2099b5cbdd2")
    @Override
    public Object visitQueryDefinition(QueryDefinition obj) {
        	return null;
    }

    @objid ("a799f083-0739-4ae0-9fa5-cb1c1bbdef3c")
    @Override
    public Object visitResource(Resource obj) {
        	return null;
    }

    @objid ("5bb24e6a-57f0-40d0-9d9f-0e7c77e34e06")
    @Override
    public Object visitResourceType(ResourceType obj) {
        	return null;
    }

    @objid ("ccf7c06e-c6f8-4914-939d-3b1e7ed6898a")
    @Override
    public Object visitStereotype(Stereotype obj) {
        	return null;
    }

    @objid ("20a715dd-4300-406a-ba5d-a69daefddcd1")
    @Override
    public Object visitTagParameter(TagParameter obj) {
        	return null;
    }

    @objid ("d318c2bc-3f74-4a6d-8402-b64c4958aaa0")
    @Override
    public Object visitTagType(TagType obj) {
        	return null;
    }

    @objid ("5b628c60-9016-4604-9f2b-c13def2559de")
    @Override
    public Object visitTaggedValue(TaggedValue obj) {
        	return null;
    }

    @objid ("c240abca-4249-443a-98d1-4d1dfdf2b68d")
    @Override
    public Object visitTypedPropertyTable(TypedPropertyTable obj) {
        	return null;
    }

}
