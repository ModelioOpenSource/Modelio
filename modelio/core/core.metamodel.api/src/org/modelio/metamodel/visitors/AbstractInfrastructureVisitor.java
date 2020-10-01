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
 */
@objid ("7ce91ec3-517e-41f1-a680-3fe68fe7907f")
public class AbstractInfrastructureVisitor implements IInfrastructureVisitor {
    @objid ("202f78b9-1da0-4a0c-9458-744a09557914")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram obj) {
        	return null;
    }

    @objid ("22b2fc95-b7a7-4490-9328-605a19d00fef")
    @Override
    public Object visitAbstractProject(AbstractProject obj) {
        	return null;
    }

    @objid ("58db40d6-95b5-46ba-a90e-b4093513c210")
    @Override
    public Object visitAbstractResource(AbstractResource obj) {
        	return null;
    }

    @objid ("867112aa-1b84-44e2-a3a5-01ca38f6d900")
    @Override
    public Object visitDependency(Dependency obj) {
        	return null;
    }

    @objid ("94f743e7-e06d-476d-a265-3eebf91ffb6f")
    @Override
    public Object visitDiagramSet(DiagramSet obj) {
        	return null;
    }

    @objid ("8f773a3d-2474-4d1e-a183-99ffab42fdde")
    @Override
    public Object visitDocument(Document obj) {
        	return null;
    }

    @objid ("8f490dcf-7184-4283-be72-c0fd0013b577")
    @Override
    public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        	return null;
    }

    @objid ("d423993f-01a9-43e9-b610-91a16faa595b")
    @Override
    public Object visitElement(Element obj) {
        	return null;
    }

    @objid ("3df7ba1d-f471-4c10-b7bd-6501ece5465f")
    @Override
    public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        	return null;
    }

    @objid ("266642bd-f104-4286-99cb-c1f302e0019a")
    @Override
    public Object visitExternElement(ExternElement obj) {
        	return null;
    }

    @objid ("a1b3d21a-82ad-4213-b5a9-9dc79421a952")
    @Override
    public Object visitExternProcessor(ExternProcessor obj) {
        	return null;
    }

    @objid ("a584f5f8-3c3d-4d9a-b6a8-12ff4cb09fac")
    @Override
    public Object visitGraphDiagram(GraphDiagram obj) {
        	return null;
    }

    @objid ("48fe6f57-19dc-4a5b-ab3c-89833732f3ef")
    @Override
    public Object visitImpactDiagram(ImpactDiagram obj) {
        	return null;
    }

    @objid ("630fd541-3a28-4122-b638-caed46c10e49")
    @Override
    public Object visitImpactLink(ImpactLink obj) {
        	return null;
    }

    @objid ("7ae0fd65-ddc3-4c85-9a9a-b46751059f67")
    @Override
    public Object visitImpactModel(ImpactModel obj) {
        	return null;
    }

    @objid ("50d00f7c-a5fb-47db-a59a-d1649112d5d3")
    @Override
    public Object visitImpactProject(ImpactProject obj) {
        	return null;
    }

    @objid ("fbcf41af-3b74-4aae-a902-a6b052e3426c")
    @Override
    public Object visitLocalPropertyTable(LocalPropertyTable obj) {
        	return null;
    }

    @objid ("5ce33039-1aae-4598-9f24-3a88aa5fe9bd")
    @Override
    public Object visitMatrixDefinition(MatrixDefinition obj) {
        	return null;
    }

    @objid ("76b553b9-06f7-4eb8-ac0d-326a111cf28f")
    @Override
    public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        	return null;
    }

    @objid ("c8fc8905-9aaa-4fcb-8110-cf1ce12ce187")
    @Override
    public Object visitMetaclassReference(MetaclassReference obj) {
        	return null;
    }

    @objid ("6c1c8768-e96f-4357-82a8-45bf184d3b2a")
    @Override
    public Object visitMethodologicalLink(MethodologicalLink obj) {
        	return null;
    }

    @objid ("575fa1c1-1118-4012-8463-8fd5f4d2e98e")
    @Override
    public Object visitModelElement(ModelElement obj) {
        	return null;
    }

    @objid ("e73b54ae-eb84-4056-9b90-4fe7e698a3aa")
    @Override
    public Object visitModuleComponent(ModuleComponent obj) {
        	return null;
    }

    @objid ("a369fb58-e252-4551-acf1-be88554b641e")
    @Override
    public Object visitModuleParameter(ModuleParameter obj) {
        	return null;
    }

    @objid ("6d50f577-d223-44a9-a513-e1d9af31a800")
    @Override
    public Object visitNote(Note obj) {
        	return null;
    }

    @objid ("7e1c0e00-5ffb-41ff-89f0-031dc02db1b5")
    @Override
    public Object visitNoteType(NoteType obj) {
        	return null;
    }

    @objid ("a1c35cae-d3b5-4a80-98f3-d481178c141d")
    @Override
    public Object visitProfile(Profile obj) {
        	return null;
    }

    @objid ("3cd8681f-f1c6-4a0a-b528-d4cd3caf0aaf")
    @Override
    public Object visitPropertyDefinition(PropertyDefinition obj) {
        	return null;
    }

    @objid ("1e834dd8-14fd-4997-9bdb-9dc3921c9c8e")
    @Override
    public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        	return null;
    }

    @objid ("375e8e09-eae9-4376-a6e8-07b97c69b927")
    @Override
    public Object visitPropertyTable(PropertyTable obj) {
        	return null;
    }

    @objid ("c9b4671c-ba53-47e6-9b62-1e78b3d91196")
    @Override
    public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        	return null;
    }

    @objid ("e6e388e2-a110-4cc0-8cf9-c3b7cf66f1bb")
    @Override
    public Object visitPropertyType(PropertyType obj) {
        	return null;
    }

    @objid ("e022cc63-b3e2-407c-90c8-48a8e492e128")
    @Override
    public Object visitQueryDefinition(QueryDefinition obj) {
        	return null;
    }

    @objid ("f8a7e695-4bba-4093-b45d-dd6f79fc87e3")
    @Override
    public Object visitResource(Resource obj) {
        	return null;
    }

    @objid ("0554874a-7edf-4bfc-95fd-c0a74508fd3a")
    @Override
    public Object visitResourceType(ResourceType obj) {
        	return null;
    }

    @objid ("e3427035-e856-4f89-bd6d-1e5a4aca5280")
    @Override
    public Object visitStereotype(Stereotype obj) {
        	return null;
    }

    @objid ("1299a393-ff52-42fd-af1b-65e97dbd1123")
    @Override
    public Object visitTagParameter(TagParameter obj) {
        	return null;
    }

    @objid ("081baa3d-6b1e-4085-b4a4-7600989214f9")
    @Override
    public Object visitTagType(TagType obj) {
        	return null;
    }

    @objid ("a45ba59f-0c34-4ed1-9de9-2b3bf47634f1")
    @Override
    public Object visitTaggedValue(TaggedValue obj) {
        	return null;
    }

    @objid ("5b75d810-1ce5-4dd5-a9b0-bf3d964cbeb4")
    @Override
    public Object visitTypedPropertyTable(TypedPropertyTable obj) {
        	return null;
    }

}
