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
@objid ("4219dd4f-4355-4b63-b963-59e96185b0cf")
public interface IAbstractInfrastructureVisitor extends IInfrastructureVisitor {
    @objid ("2eb70d8f-e264-4aee-9f60-6a33e04b7fbf")
    @Override
    default Object visitAbstractDiagram(AbstractDiagram obj) {
        	return null;
    }

    @objid ("4ac57391-dad2-4d47-92e0-5ff7b9ad1c00")
    @Override
    default Object visitAbstractProject(AbstractProject obj) {
        	return null;
    }

    @objid ("b2ed2aee-858c-4069-a7df-31845b7de86b")
    @Override
    default Object visitAbstractResource(AbstractResource obj) {
        	return null;
    }

    @objid ("523b7ae2-c7ce-41fa-b092-7e4ae41d87df")
    @Override
    default Object visitDependency(Dependency obj) {
        	return null;
    }

    @objid ("20da533e-4833-4217-82d0-8baabeb517f2")
    @Override
    default Object visitDiagramSet(DiagramSet obj) {
        	return null;
    }

    @objid ("137989ae-6663-4316-80fd-03a12ffc1b24")
    @Override
    default Object visitDocument(Document obj) {
        	return null;
    }

    @objid ("c4604658-7279-494e-bdb8-eaf976eb58f5")
    @Override
    default Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        	return null;
    }

    @objid ("168d5c02-2d13-45f7-9529-5ca25834c352")
    @Override
    default Object visitElement(Element obj) {
        	return null;
    }

    @objid ("2015880d-b6f3-423e-82ed-7abced158470")
    @Override
    default Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        	return null;
    }

    @objid ("8ac0d9e0-9e79-4f87-bb8c-fdfad398901f")
    @Override
    default Object visitExternProcessor(ExternProcessor obj) {
        	return null;
    }

    @objid ("be8a0766-5c59-4ea1-8076-652bd97f7f76")
    @Override
    default Object visitGraphDiagram(GraphDiagram obj) {
        	return null;
    }

    @objid ("06d6ce6b-d2d2-4939-b049-40871b84eb26")
    @Override
    default Object visitImpactDiagram(ImpactDiagram obj) {
        	return null;
    }

    @objid ("39f7dae0-09b3-4177-be48-8115432d712c")
    @Override
    default Object visitImpactLink(ImpactLink obj) {
        	return null;
    }

    @objid ("08aa7af9-6d24-42e2-9b84-d4f5aadbbdef")
    @Override
    default Object visitImpactModel(ImpactModel obj) {
        	return null;
    }

    @objid ("36442f2e-6d5c-4090-882b-362d7b884589")
    @Override
    default Object visitImpactProject(ImpactProject obj) {
        	return null;
    }

    @objid ("4d1fa92c-762c-415f-ab96-edad78dc60d5")
    @Override
    default Object visitLocalPropertyTable(LocalPropertyTable obj) {
        	return null;
    }

    @objid ("a82be09c-7693-45b7-8ec7-3bcaf9b41d71")
    @Override
    default Object visitMatrixDefinition(MatrixDefinition obj) {
        	return null;
    }

    @objid ("9940725b-58a5-4b67-ac02-b5fe31f9a30f")
    @Override
    default Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        	return null;
    }

    @objid ("29211fdf-5cf4-4ae7-9f38-603b037cb79a")
    @Override
    default Object visitMetaclassReference(MetaclassReference obj) {
        	return null;
    }

    @objid ("3509624f-3f0e-4077-997b-33556081245e")
    @Override
    default Object visitMethodologicalLink(MethodologicalLink obj) {
        	return null;
    }

    @objid ("3502d899-e35f-4bbb-999a-f7069f991ecf")
    @Override
    default Object visitModelElement(ModelElement obj) {
        	return null;
    }

    @objid ("1c567dae-689e-429d-be30-18824cc2e0fe")
    @Override
    default Object visitModuleComponent(ModuleComponent obj) {
        	return null;
    }

    @objid ("f7322f40-0bf2-4033-87d2-9eb38a06f74e")
    @Override
    default Object visitModuleParameter(ModuleParameter obj) {
        	return null;
    }

    @objid ("13361a41-4ec0-4e31-9f1e-bf35ba328e21")
    @Override
    default Object visitNote(Note obj) {
        	return null;
    }

    @objid ("b6fe6a14-efc1-4c59-b831-d876d4a3d85d")
    @Override
    default Object visitNoteType(NoteType obj) {
        	return null;
    }

    @objid ("fd26a5b5-eddf-4d01-b3a9-6ad773554e29")
    @Override
    default Object visitProfile(Profile obj) {
        	return null;
    }

    @objid ("fe7362e0-2505-48f4-9b75-4bc94ec790d8")
    @Override
    default Object visitPropertyDefinition(PropertyDefinition obj) {
        	return null;
    }

    @objid ("8ea8c0d6-6605-43e8-b810-b2e2cbb58ecb")
    @Override
    default Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        	return null;
    }

    @objid ("8ea641b9-4806-4fc9-a568-ae8c2f776274")
    @Override
    default Object visitPropertyTable(PropertyTable obj) {
        	return null;
    }

    @objid ("55c5e378-4db1-4fe9-a2de-9e9b937f6e55")
    @Override
    default Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        	return null;
    }

    @objid ("d54bbd90-1262-4909-b4ad-fbf847563bcd")
    @Override
    default Object visitPropertyType(PropertyType obj) {
        	return null;
    }

    @objid ("04713168-d697-4543-882c-4772cc7ca8f7")
    @Override
    default Object visitQueryDefinition(QueryDefinition obj) {
        	return null;
    }

    @objid ("c62c81cd-fb20-42ce-a29d-af44f3c10adc")
    @Override
    default Object visitResource(Resource obj) {
        	return null;
    }

    @objid ("65eb3c8b-5805-4b38-978f-c3294d7b9acb")
    @Override
    default Object visitResourceType(ResourceType obj) {
        	return null;
    }

    @objid ("58321e9a-6dfe-4a54-a4f6-914bd4c945b1")
    @Override
    default Object visitStereotype(Stereotype obj) {
        	return null;
    }

    @objid ("6d865971-a49f-4312-af30-677151bbfb8a")
    @Override
    default Object visitTagParameter(TagParameter obj) {
        	return null;
    }

    @objid ("7e359324-2a7e-42d0-96e4-07ea5aedcf1c")
    @Override
    default Object visitTagType(TagType obj) {
        	return null;
    }

    @objid ("abb4f105-7677-46e4-b40d-0c40d234c62f")
    @Override
    default Object visitTaggedValue(TaggedValue obj) {
        	return null;
    }

    @objid ("50fdae9b-399d-42ad-8534-a66220d41026")
    @Override
    default Object visitTypedPropertyTable(TypedPropertyTable obj) {
        	return null;
    }

}
