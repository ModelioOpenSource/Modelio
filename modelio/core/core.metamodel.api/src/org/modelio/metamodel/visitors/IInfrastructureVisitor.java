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
    @objid ("75b2e17b-33d4-4aa9-a516-373feef49e15")
    Object visitAbstractDiagram(AbstractDiagram obj);

    @objid ("7403638b-4da8-4a62-8d3a-5de0dd3023b2")
    Object visitAbstractProject(AbstractProject obj);

    @objid ("5bb12a5d-6257-4cc0-8459-7cae424fe666")
    Object visitAbstractResource(AbstractResource obj);

    @objid ("591c67bb-216a-4239-8236-e713c004eaf5")
    Object visitDependency(Dependency obj);

    @objid ("691658a3-61f0-4843-a5cd-d91d506d9866")
    Object visitDiagramSet(DiagramSet obj);

    @objid ("8f845b30-a403-41c2-9609-e82115b8f0dc")
    Object visitDocument(Document obj);

    @objid ("1d258b3d-21d8-418e-b237-d0a67052f7a1")
    Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj);

    @objid ("4c8aa69a-733c-4f85-a66b-9ab72ac038b6")
    Object visitElement(Element obj);

    @objid ("7a257ec4-d8ce-43ce-8329-2061c8c1e297")
    Object visitEnumeratedPropertyType(EnumeratedPropertyType obj);

    @objid ("b35a8519-6f8b-4dce-8731-a360c207f554")
    Object visitExternElement(ExternElement obj);

    @objid ("3ad1ace3-fab6-4786-bfcf-89bc593ece25")
    Object visitExternProcessor(ExternProcessor obj);

    @objid ("1b9644a2-d0e0-4214-a1e8-2d6f80b0b27e")
    Object visitGraphDiagram(GraphDiagram obj);

    @objid ("09220b0d-8483-46b0-9f90-0f1f5023d033")
    Object visitImpactDiagram(ImpactDiagram obj);

    @objid ("b34181b8-da2a-49e3-8a16-3d6361f3a756")
    Object visitImpactLink(ImpactLink obj);

    @objid ("1d3f9780-ef37-4dcc-9f72-97c8f0aeb2e5")
    Object visitImpactModel(ImpactModel obj);

    @objid ("78c4adf1-24c8-4bfc-b755-8521596c9fc2")
    Object visitImpactProject(ImpactProject obj);

    @objid ("4853de9f-09ea-417b-bf3c-f724c6186c95")
    Object visitLocalPropertyTable(LocalPropertyTable obj);

    @objid ("4db5c0b8-ab7e-47d7-9d26-bade17decb50")
    Object visitMatrixDefinition(MatrixDefinition obj);

    @objid ("75f37ec3-4c99-4997-8578-41dfa3636872")
    Object visitMatrixValueDefinition(MatrixValueDefinition obj);

    @objid ("456bd7ce-2d83-4331-a5d6-4578a6fabf7b")
    Object visitMetaclassReference(MetaclassReference obj);

    @objid ("044694cc-c3de-41ff-9c91-e9d5c2466ae0")
    Object visitMethodologicalLink(MethodologicalLink obj);

    @objid ("2d161a07-b974-48f0-bfaf-fc68a99e5e53")
    Object visitModelElement(ModelElement obj);

    @objid ("0206bbf5-5fbd-4f99-82c0-2a492cc46bc9")
    Object visitModuleComponent(ModuleComponent obj);

    @objid ("ccc57a01-fee6-4efd-b0dd-43be9ce07903")
    Object visitModuleParameter(ModuleParameter obj);

    @objid ("d0219db6-7561-4791-a266-4ee1f1e40d73")
    Object visitNote(Note obj);

    @objid ("2025f4e0-4983-40f8-a1ae-deedecf671d4")
    Object visitNoteType(NoteType obj);

    @objid ("3af400f1-1085-482c-9900-01fe463ac17c")
    Object visitProfile(Profile obj);

    @objid ("9f169035-2374-4f5b-ab86-ba6831eaa66d")
    Object visitPropertyDefinition(PropertyDefinition obj);

    @objid ("c697749c-1aeb-4a18-906c-353db68ea1e2")
    Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj);

    @objid ("5bf813a4-b4ac-4786-b7b4-d5bc10e7981a")
    Object visitPropertyTable(PropertyTable obj);

    @objid ("c8d596e1-6599-4936-a416-0aeae8fea468")
    Object visitPropertyTableDefinition(PropertyTableDefinition obj);

    @objid ("98234306-d488-4089-b6bf-24716ef9d48e")
    Object visitPropertyType(PropertyType obj);

    @objid ("903d5508-00e1-4e14-82a7-dd22e53f9429")
    Object visitQueryDefinition(QueryDefinition obj);

    @objid ("e9e1ae91-60e6-4a4f-ad2d-075d08334561")
    Object visitResource(Resource obj);

    @objid ("62fdf95b-34e3-42e8-b474-ead4e7913a7d")
    Object visitResourceType(ResourceType obj);

    @objid ("13745431-7467-4e83-8c23-c5e7acd10e93")
    Object visitStereotype(Stereotype obj);

    @objid ("1284c0cf-1da2-4cf4-8b12-82a3ae6438a9")
    Object visitTagParameter(TagParameter obj);

    @objid ("65076978-cd76-43f4-8cb0-f5420f95b4b3")
    Object visitTagType(TagType obj);

    @objid ("7b004701-c3c6-46e3-accc-a8da2a16371e")
    Object visitTaggedValue(TaggedValue obj);

    @objid ("172c7bfe-286f-41e8-bb53-711c14466a82")
    Object visitTypedPropertyTable(TypedPropertyTable obj);
}

