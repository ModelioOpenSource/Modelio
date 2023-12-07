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

/**
 * This class is an implementation of {@link IInfrastructureVisitor} whose visit() methods simply return <code>null</code>.
 * 
 */
@objid ("4219dd4f-4355-4b63-b963-59e96185b0cf")
public interface IAbstractInfrastructureVisitor extends IInfrastructureVisitor {
    @objid ("8395114d-71db-49f5-8700-dc943bed896f")
    @Override
    default Object visitAbstractDiagram(AbstractDiagram obj) {
        	return null;
    }

    @objid ("5e5ba451-8647-4e7a-8dce-4e35041164e1")
    @Override
    default Object visitAbstractProject(AbstractProject obj) {
        	return null;
    }

    @objid ("b9b902ca-ad5c-4f4e-b8a2-b5cd2cdc2b46")
    @Override
    default Object visitAbstractResource(AbstractResource obj) {
        	return null;
    }

    @objid ("bed9eb19-c86f-43e2-8538-580c667cd959")
    @Override
    default Object visitDependency(Dependency obj) {
        	return null;
    }

    @objid ("64f71777-eb10-4314-ac00-edfa85beea03")
    @Override
    default Object visitDiagramSet(DiagramSet obj) {
        	return null;
    }

    @objid ("2464fbb4-2f55-4968-aa50-d725f9c94dc4")
    @Override
    default Object visitDocument(Document obj) {
        	return null;
    }

    @objid ("a41f607d-cff6-4f66-b556-c9177c0868fc")
    @Override
    default Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        	return null;
    }

    @objid ("b501bc00-0abc-4ee8-a7f9-a912f4727c08")
    @Override
    default Object visitElement(Element obj) {
        	return null;
    }

    @objid ("a646d028-eb3f-4c70-84cd-42abbf9d2007")
    @Override
    default Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        	return null;
    }

    @objid ("bb41cf46-eddb-4f37-90b7-a907e269ad21")
    @Override
    default Object visitExternElement(ExternElement obj) {
        	return null;
    }

    @objid ("f5a58dde-014d-4e05-92f8-cdf78dd6db0a")
    @Override
    default Object visitExternProcessor(ExternProcessor obj) {
        	return null;
    }

    @objid ("9b488818-eec3-42fd-9845-e8071914d498")
    @Override
    default Object visitGraphDiagram(GraphDiagram obj) {
        	return null;
    }

    @objid ("8733b014-9afe-4db0-8a03-6d05b19870fb")
    @Override
    default Object visitImpactDiagram(ImpactDiagram obj) {
        	return null;
    }

    @objid ("9b76bb6e-b839-4547-b719-c061dfd742f0")
    @Override
    default Object visitImpactLink(ImpactLink obj) {
        	return null;
    }

    @objid ("0ce4e9cd-19ee-487a-936a-3f5729b3e76e")
    @Override
    default Object visitImpactModel(ImpactModel obj) {
        	return null;
    }

    @objid ("5a6ce981-d6f8-4c63-9d2a-255cad39e2dd")
    @Override
    default Object visitImpactProject(ImpactProject obj) {
        	return null;
    }

    @objid ("d9fa5856-53ca-4a87-880e-e86f123c01be")
    @Override
    default Object visitLocalPropertyTable(LocalPropertyTable obj) {
        	return null;
    }

    @objid ("b57328dc-1f49-49d4-b34a-4b8700fc5bff")
    @Override
    default Object visitMatrixDefinition(MatrixDefinition obj) {
        	return null;
    }

    @objid ("1c715e82-4bdf-4e90-a288-7ab81537f3fd")
    @Override
    default Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        	return null;
    }

    @objid ("f83d1c8c-5ab0-45d8-a663-c7fa83def76c")
    @Override
    default Object visitMetaclassReference(MetaclassReference obj) {
        	return null;
    }

    @objid ("1f2fbfe5-7815-4dec-8be3-1c46909a24ec")
    @Override
    default Object visitMethodologicalLink(MethodologicalLink obj) {
        	return null;
    }

    @objid ("f51e13f2-1e71-4c8a-87f0-7b9b46697478")
    @Override
    default Object visitModelElement(ModelElement obj) {
        	return null;
    }

    @objid ("f00533a9-18c5-421c-8ebe-d96070745a38")
    @Override
    default Object visitModuleComponent(ModuleComponent obj) {
        	return null;
    }

    @objid ("32fea6ed-4ed4-4711-9b92-7d32fc781931")
    @Override
    default Object visitModuleParameter(ModuleParameter obj) {
        	return null;
    }

    @objid ("26e0527c-4355-40ae-a92f-7655f4759c1f")
    @Override
    default Object visitNote(Note obj) {
        	return null;
    }

    @objid ("f0fda943-acff-4ca1-9c3f-28aa4c254802")
    @Override
    default Object visitNoteType(NoteType obj) {
        	return null;
    }

    @objid ("c0debae4-3119-4d95-9c23-d3e10ed23820")
    @Override
    default Object visitProfile(Profile obj) {
        	return null;
    }

    @objid ("6b719b23-961c-4b2e-9339-3a38457e48c7")
    @Override
    default Object visitPropertyDefinition(PropertyDefinition obj) {
        	return null;
    }

    @objid ("31c375c9-2496-446d-b314-50d453bf1103")
    @Override
    default Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        	return null;
    }

    @objid ("836b917d-9abb-4a55-93cb-bc90ea2d2d53")
    @Override
    default Object visitPropertyTable(PropertyTable obj) {
        	return null;
    }

    @objid ("0458061a-ab86-4ff4-a65b-72cd7a06102d")
    @Override
    default Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        	return null;
    }

    @objid ("640e6272-299c-42b6-84bb-aff858b31252")
    @Override
    default Object visitPropertyType(PropertyType obj) {
        	return null;
    }

    @objid ("699fa88a-beea-4b12-ab7d-133e9e05bbbd")
    @Override
    default Object visitQueryDefinition(QueryDefinition obj) {
        	return null;
    }

    @objid ("58f4dfb7-2c7c-4092-8e4b-3de34c7230a6")
    @Override
    default Object visitResource(Resource obj) {
        	return null;
    }

    @objid ("c4d05f8f-f799-441b-a287-60dbad8f5f15")
    @Override
    default Object visitResourceType(ResourceType obj) {
        	return null;
    }

    @objid ("37951d58-2dd0-47e9-aa04-c8ed67e955d6")
    @Override
    default Object visitStereotype(Stereotype obj) {
        	return null;
    }

    @objid ("ee885eb4-9042-4569-9eb2-bedb956b9f2e")
    @Override
    default Object visitTagParameter(TagParameter obj) {
        	return null;
    }

    @objid ("51f191e9-5244-4c70-a8e8-7f1fb05d79fa")
    @Override
    default Object visitTagType(TagType obj) {
        	return null;
    }

    @objid ("c8341c9f-1dd9-4a39-b469-93c147be133a")
    @Override
    default Object visitTaggedValue(TaggedValue obj) {
        	return null;
    }

    @objid ("a8458c89-1860-4bb5-8e0b-2d67a84499fc")
    @Override
    default Object visitTypedPropertyTable(TypedPropertyTable obj) {
        	return null;
    }
}

