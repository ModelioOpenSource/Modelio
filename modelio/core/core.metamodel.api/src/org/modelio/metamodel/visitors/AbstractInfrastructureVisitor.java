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
@objid ("7ce91ec3-517e-41f1-a680-3fe68fe7907f")
public class AbstractInfrastructureVisitor implements IInfrastructureVisitor {
    @objid ("c33747fd-e616-4edc-a3e9-add11c4ed292")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram obj) {
        	return null;
    }

    @objid ("126066d6-a0f7-4268-8b12-c21e580cfb91")
    @Override
    public Object visitAbstractProject(AbstractProject obj) {
        	return null;
    }

    @objid ("7dfe50b1-5c8e-4e92-acfe-037afb4af0a2")
    @Override
    public Object visitAbstractResource(AbstractResource obj) {
        	return null;
    }

    @objid ("f2d43384-654f-4aed-909f-d51c560edb68")
    @Override
    public Object visitDependency(Dependency obj) {
        	return null;
    }

    @objid ("8b71dab5-869a-437a-9e49-72ea2eb71aca")
    @Override
    public Object visitDiagramSet(DiagramSet obj) {
        	return null;
    }

    @objid ("cac24b58-c9c8-4f6c-968c-020ec8a2769b")
    @Override
    public Object visitDocument(Document obj) {
        	return null;
    }

    @objid ("cea7c04f-a0d5-420e-b5df-0efea7ca7193")
    @Override
    public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        	return null;
    }

    @objid ("a006bb28-4126-41bb-8e98-3acf69630aef")
    @Override
    public Object visitElement(Element obj) {
        	return null;
    }

    @objid ("7e0741a3-19ea-4326-891a-9efeac769d48")
    @Override
    public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        	return null;
    }

    @objid ("7d579cd5-71cb-4bde-99b3-a293502d3ae8")
    @Override
    public Object visitExternElement(ExternElement obj) {
        	return null;
    }

    @objid ("37a9c3ac-5e81-4768-b8da-bc4059a24ae4")
    @Override
    public Object visitExternProcessor(ExternProcessor obj) {
        	return null;
    }

    @objid ("1d6d727e-91b2-47c7-b0b2-b646778ae4cb")
    @Override
    public Object visitGraphDiagram(GraphDiagram obj) {
        	return null;
    }

    @objid ("756211c1-acda-4292-a046-ea5d36d71766")
    @Override
    public Object visitImpactDiagram(ImpactDiagram obj) {
        	return null;
    }

    @objid ("8150307e-5f81-42ad-828d-b73e96562468")
    @Override
    public Object visitImpactLink(ImpactLink obj) {
        	return null;
    }

    @objid ("f28c6b60-d0c3-433b-8e09-bc19a331d2ac")
    @Override
    public Object visitImpactModel(ImpactModel obj) {
        	return null;
    }

    @objid ("2be8069c-72d4-4d73-b346-cb89ed42993e")
    @Override
    public Object visitImpactProject(ImpactProject obj) {
        	return null;
    }

    @objid ("c8282388-a0a7-40b7-9242-e1d052bad9cc")
    @Override
    public Object visitLocalPropertyTable(LocalPropertyTable obj) {
        	return null;
    }

    @objid ("deb45487-1c80-400a-91e3-c35ea5b9d2df")
    @Override
    public Object visitMatrixDefinition(MatrixDefinition obj) {
        	return null;
    }

    @objid ("32483ecb-335a-4c04-a945-3f278da6ef6f")
    @Override
    public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        	return null;
    }

    @objid ("73827f43-dcc1-467d-ba79-8043779cd7da")
    @Override
    public Object visitMetaclassReference(MetaclassReference obj) {
        	return null;
    }

    @objid ("575cede9-9d43-4d19-bc6a-9e4005719128")
    @Override
    public Object visitMethodologicalLink(MethodologicalLink obj) {
        	return null;
    }

    @objid ("9b209578-ca11-4466-975b-fbb7ffd6d740")
    @Override
    public Object visitModelElement(ModelElement obj) {
        	return null;
    }

    @objid ("d4bfca49-a592-4bf4-a295-ad0531c20e7a")
    @Override
    public Object visitModuleComponent(ModuleComponent obj) {
        	return null;
    }

    @objid ("d68b9173-5224-4e92-923b-98ade02ebaac")
    @Override
    public Object visitModuleParameter(ModuleParameter obj) {
        	return null;
    }

    @objid ("64aecac8-e1e6-43b1-a4e6-81f5fcfc08d5")
    @Override
    public Object visitNote(Note obj) {
        	return null;
    }

    @objid ("9d512c2c-0e86-4c30-84ea-d9e254297613")
    @Override
    public Object visitNoteType(NoteType obj) {
        	return null;
    }

    @objid ("16d6bc1b-d591-4f98-a38f-b7d439e09901")
    @Override
    public Object visitProfile(Profile obj) {
        	return null;
    }

    @objid ("499bd56c-6f29-4c5e-be8b-368a4d1be6b2")
    @Override
    public Object visitPropertyDefinition(PropertyDefinition obj) {
        	return null;
    }

    @objid ("502fd8f6-ee08-4524-8c98-c20b24e3967a")
    @Override
    public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        	return null;
    }

    @objid ("a877a60c-601b-4e9c-b422-9605b17330dc")
    @Override
    public Object visitPropertyTable(PropertyTable obj) {
        	return null;
    }

    @objid ("ab6c1f1f-7c03-4ec8-8eb7-e03c5273da9b")
    @Override
    public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        	return null;
    }

    @objid ("46d29757-a27a-49c3-be00-7f643606c4b1")
    @Override
    public Object visitPropertyType(PropertyType obj) {
        	return null;
    }

    @objid ("28b0a766-fa8d-443e-a7dc-19a7a02f088f")
    @Override
    public Object visitQueryDefinition(QueryDefinition obj) {
        	return null;
    }

    @objid ("b22c4018-5c52-4e23-939a-b8fa234eb491")
    @Override
    public Object visitResource(Resource obj) {
        	return null;
    }

    @objid ("1b88573a-0552-4fe3-b688-a606cf0d9ca4")
    @Override
    public Object visitResourceType(ResourceType obj) {
        	return null;
    }

    @objid ("974762f5-117e-43e6-9fc4-9143b850c93d")
    @Override
    public Object visitStereotype(Stereotype obj) {
        	return null;
    }

    @objid ("54b9e86f-19ac-4e14-be86-e2555e3ace6f")
    @Override
    public Object visitTagParameter(TagParameter obj) {
        	return null;
    }

    @objid ("a3d57241-31d1-4a86-9d83-76f1595c9fbf")
    @Override
    public Object visitTagType(TagType obj) {
        	return null;
    }

    @objid ("29555350-1062-46a4-b4be-e1bb17797ee5")
    @Override
    public Object visitTaggedValue(TaggedValue obj) {
        	return null;
    }

    @objid ("98c06980-b074-4c17-b7bc-0b95a86fa314")
    @Override
    public Object visitTypedPropertyTable(TypedPropertyTable obj) {
        	return null;
    }

}
