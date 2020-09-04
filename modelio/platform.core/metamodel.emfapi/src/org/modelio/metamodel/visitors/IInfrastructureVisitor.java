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
import org.modelio.vcore.smkernel.mapi.MVisitor;

@objid ("49b8aa54-ca0e-4175-84a8-2c23a219be7c")
public interface IInfrastructureVisitor extends MVisitor {
    @objid ("d954139b-ef96-4e8a-8e72-5798f8cb1431")
    Object visitAbstractDiagram(AbstractDiagram obj);

    @objid ("9665a8ce-82c1-4fee-9168-e66147ab1f1c")
    Object visitAbstractProject(AbstractProject obj);

    @objid ("ff153de8-1fa6-493e-ac36-0cc7e0f94ee5")
    Object visitAbstractResource(AbstractResource obj);

    @objid ("18f357dc-6e82-4e43-b06d-f17a5b2d84bb")
    Object visitDependency(Dependency obj);

    @objid ("9a86324f-edf8-4cf3-b141-487acd4917e4")
    Object visitDiagramSet(DiagramSet obj);

    @objid ("29160fa3-c727-41c1-aafe-273da2afc3f0")
    Object visitDocument(Document obj);

    @objid ("95d7eea7-ce28-446f-8ab3-32813c6dfe69")
    Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj);

    @objid ("eb00b802-3efe-46fc-82c5-c5650701b21d")
    Object visitElement(Element obj);

    @objid ("703aa696-b563-4183-8c0d-2e048345fe3e")
    Object visitEnumeratedPropertyType(EnumeratedPropertyType obj);

    @objid ("ace6074f-462b-43fb-a17f-46762e83b569")
    Object visitExternProcessor(ExternProcessor obj);

    @objid ("d78c6c88-3268-4133-9e6a-b6c3d6f9fcfb")
    Object visitGraphDiagram(GraphDiagram obj);

    @objid ("e4cca37d-901f-4fd5-8f30-5778eda252ae")
    Object visitImpactDiagram(ImpactDiagram obj);

    @objid ("1a566c5c-4868-4686-af2d-c5fb2c3eee3e")
    Object visitImpactLink(ImpactLink obj);

    @objid ("bb88393e-1736-41ca-b879-4ee886534c90")
    Object visitImpactModel(ImpactModel obj);

    @objid ("889d8e7c-6dc6-407a-8f75-d9f25739d6ce")
    Object visitImpactProject(ImpactProject obj);

    @objid ("cc0a40de-20b5-4bd8-86c9-3496fae8f443")
    Object visitLocalPropertyTable(LocalPropertyTable obj);

    @objid ("4508bfd0-1bc6-4480-a798-5ff842d3e3f8")
    Object visitMatrixDefinition(MatrixDefinition obj);

    @objid ("fc46dad8-5a0f-4ad3-b168-ae83ad6393cd")
    Object visitMatrixValueDefinition(MatrixValueDefinition obj);

    @objid ("f81e3c51-4648-4b99-97cb-0c4d64533726")
    Object visitMetaclassReference(MetaclassReference obj);

    @objid ("63ef28b8-15b9-4535-a299-ac2d096b3498")
    Object visitMethodologicalLink(MethodologicalLink obj);

    @objid ("11c8ec8a-d716-444b-b568-1fe947ca81c7")
    Object visitModelElement(ModelElement obj);

    @objid ("710e9233-1f91-4815-b9a6-b51e70529704")
    Object visitModuleComponent(ModuleComponent obj);

    @objid ("47ea59ce-8347-424c-9233-ced18f188047")
    Object visitModuleParameter(ModuleParameter obj);

    @objid ("691c6ee3-9fb2-4f54-9b31-870ce8acf199")
    Object visitNote(Note obj);

    @objid ("6ca5ca39-1ca9-48bf-8b88-fa3be9c8c3eb")
    Object visitNoteType(NoteType obj);

    @objid ("3c10c018-6c77-4191-bab3-f42a73e2ec68")
    Object visitProfile(Profile obj);

    @objid ("5bc861d5-6fb8-4301-8278-7f53f10b8463")
    Object visitPropertyDefinition(PropertyDefinition obj);

    @objid ("cbddb33b-cf0d-4166-b3f9-91142d1177d0")
    Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj);

    @objid ("7c63f2d5-abb0-4983-b760-7c3421b1292f")
    Object visitPropertyTable(PropertyTable obj);

    @objid ("abc196bd-577c-4401-97d0-a0713745dbc5")
    Object visitPropertyTableDefinition(PropertyTableDefinition obj);

    @objid ("b7872b47-c148-4871-8bbc-fca94229beb0")
    Object visitPropertyType(PropertyType obj);

    @objid ("651ec850-5ef9-4030-a6cf-bebad2e3e162")
    Object visitQueryDefinition(QueryDefinition obj);

    @objid ("abe386b7-4bf4-4d61-8891-75cced50d1bd")
    Object visitResource(Resource obj);

    @objid ("f0ff9c7c-da39-41f2-80e9-b35075da3a30")
    Object visitResourceType(ResourceType obj);

    @objid ("7bb34301-32a6-4ed4-b68c-1f23d2ded1d3")
    Object visitStereotype(Stereotype obj);

    @objid ("2e3c9374-d7fa-4cbc-9b87-d9e611081595")
    Object visitTagParameter(TagParameter obj);

    @objid ("bc603d94-919e-46b8-86ce-b62827b280db")
    Object visitTagType(TagType obj);

    @objid ("0416fb24-99db-4582-a990-782ba587e7f7")
    Object visitTaggedValue(TaggedValue obj);

    @objid ("3ae6b87e-c555-4867-8d63-f4e66f0e7ce4")
    Object visitTypedPropertyTable(TypedPropertyTable obj);

}
