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
import org.modelio.metamodel.uml.infrastructure.ExternElement;
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
import org.modelio.vcore.smkernel.mapi.MVisitor;

@objid ("49b8aa54-ca0e-4175-84a8-2c23a219be7c")
public interface IInfrastructureVisitor extends MVisitor {
    @objid ("b2802aa9-29c8-4cc1-ac09-7237e2913452")
    Object visitAbstractDiagram(AbstractDiagram obj);

    @objid ("11c22876-efd0-4cd6-a5d3-747d6834642c")
    Object visitAbstractProject(AbstractProject obj);

    @objid ("9a4b3206-f892-4850-9d74-e0ff41a05c5e")
    Object visitAbstractResource(AbstractResource obj);

    @objid ("f488beb2-8c96-4761-ba6e-10f671b3309a")
    Object visitDependency(Dependency obj);

    @objid ("b7de0bde-ea5a-40b6-8923-30c13bf61d4b")
    Object visitDiagramSet(DiagramSet obj);

    @objid ("8937405f-185d-45d0-ba2c-8e02e5f876e0")
    Object visitDocument(Document obj);

    @objid ("5d3773b7-d61a-4e05-9f9d-4decd9f47dcb")
    Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj);

    @objid ("4274227a-74f1-445b-b815-95753a3d16db")
    Object visitElement(Element obj);

    @objid ("0ebf8fec-4782-4ee5-9304-b1bd1b626d58")
    Object visitEnumeratedPropertyType(EnumeratedPropertyType obj);

    @objid ("963c3abc-bc6e-4799-a716-e57751cb7f55")
    Object visitExternElement(ExternElement obj);

    @objid ("72004812-8012-4a74-8f57-fc15cb617f28")
    Object visitExternProcessor(ExternProcessor obj);

    @objid ("c65d8531-9d6c-478b-9878-8d1326d23b80")
    Object visitGraphDiagram(GraphDiagram obj);

    @objid ("db86b987-8e1e-499f-8f2f-d540e668f25f")
    Object visitImpactDiagram(ImpactDiagram obj);

    @objid ("a557837d-2c3d-44f4-bb8a-71c31bf768c3")
    Object visitImpactLink(ImpactLink obj);

    @objid ("031a4606-44cf-46ea-8977-35da9670f7c5")
    Object visitImpactModel(ImpactModel obj);

    @objid ("bdc4aadc-33b5-4f83-8217-ae86e69595f0")
    Object visitImpactProject(ImpactProject obj);

    @objid ("edd9f825-7663-456e-a0b2-f067d343f566")
    Object visitLocalPropertyTable(LocalPropertyTable obj);

    @objid ("e1e418c9-9056-41ff-a218-7c006303801c")
    Object visitMatrixDefinition(MatrixDefinition obj);

    @objid ("bbbb4642-1279-4812-8fd6-69a80561d84a")
    Object visitMatrixValueDefinition(MatrixValueDefinition obj);

    @objid ("d6dc8863-888e-4e2c-b372-8f8911e422c8")
    Object visitMetaclassReference(MetaclassReference obj);

    @objid ("b450ef40-31c8-44cf-960c-42f4c5a18af4")
    Object visitMethodologicalLink(MethodologicalLink obj);

    @objid ("d38d68a8-312e-4aed-b804-644672df694b")
    Object visitModelElement(ModelElement obj);

    @objid ("b46b6a6c-bbe2-4411-9324-1a2309e2856a")
    Object visitModuleComponent(ModuleComponent obj);

    @objid ("fcc84c94-d0c1-4e30-828d-bfa0a6259803")
    Object visitModuleParameter(ModuleParameter obj);

    @objid ("ee8a90c8-0aa9-4e7a-a50d-fe47a795a4e4")
    Object visitNote(Note obj);

    @objid ("d8b3cd30-e7fc-446f-939d-6cb33b4125ec")
    Object visitNoteType(NoteType obj);

    @objid ("76c3c2f7-7719-4169-b9cb-8858b73007c3")
    Object visitProfile(Profile obj);

    @objid ("e7c76a4a-5eee-4442-8d49-af0f542f3649")
    Object visitPropertyDefinition(PropertyDefinition obj);

    @objid ("ef345074-a81b-492d-8aba-27d066e1b9cf")
    Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj);

    @objid ("38dd06d0-00e3-4e17-894a-d8a61b19847c")
    Object visitPropertyTable(PropertyTable obj);

    @objid ("2ba1c0df-c7b2-43d2-895a-3b22e6a6a900")
    Object visitPropertyTableDefinition(PropertyTableDefinition obj);

    @objid ("ae171cfc-cb9a-4195-bf4c-018edabb0de8")
    Object visitPropertyType(PropertyType obj);

    @objid ("4396a575-835f-46d6-8133-f771120d4c63")
    Object visitQueryDefinition(QueryDefinition obj);

    @objid ("9d7dc3c0-d100-4af5-be95-08f7b90deb36")
    Object visitResource(Resource obj);

    @objid ("f20f59b4-8281-4b1c-89ab-ca86a7912e76")
    Object visitResourceType(ResourceType obj);

    @objid ("e86dbdd3-db9f-43c3-9ab4-756339badac9")
    Object visitStereotype(Stereotype obj);

    @objid ("b3668f4e-ffc4-4989-b368-6bd6ea4d4e41")
    Object visitTagParameter(TagParameter obj);

    @objid ("e6c7e997-8685-4976-a0f1-87aecbb96841")
    Object visitTagType(TagType obj);

    @objid ("d4ceb5e4-3c60-4c43-a3cf-a32dfeaf82bb")
    Object visitTaggedValue(TaggedValue obj);

    @objid ("26d8f7b4-12eb-4258-9a5b-2486191866ec")
    Object visitTypedPropertyTable(TypedPropertyTable obj);

}
