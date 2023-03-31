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
    @objid ("e1a42351-58cf-43d1-8c18-1a9b653e846e")
    @Override
    default Object visitAbstractDiagram(AbstractDiagram obj) {
        	return null;
    }

    @objid ("86ab2c3d-7b02-46a5-9321-4a22d2bcd8c5")
    @Override
    default Object visitAbstractProject(AbstractProject obj) {
        	return null;
    }

    @objid ("7e9a8f36-fc98-40d5-b63d-689a4bc4f47a")
    @Override
    default Object visitAbstractResource(AbstractResource obj) {
        	return null;
    }

    @objid ("5b7b4a92-38a5-47e5-b327-b0a836e57050")
    @Override
    default Object visitDependency(Dependency obj) {
        	return null;
    }

    @objid ("aa2fd2ee-c540-440e-84fc-7e6b85d1babf")
    @Override
    default Object visitDiagramSet(DiagramSet obj) {
        	return null;
    }

    @objid ("de2557b1-c440-43a1-a59c-b00884ff76ae")
    @Override
    default Object visitDocument(Document obj) {
        	return null;
    }

    @objid ("d8fa4d1b-82bb-45aa-85a0-1230b225cb98")
    @Override
    default Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        	return null;
    }

    @objid ("1c3ba98b-c302-444c-8968-cb13f65cc821")
    @Override
    default Object visitElement(Element obj) {
        	return null;
    }

    @objid ("ca4bc09c-8c6e-4e35-8d6b-77af3bd07b3a")
    @Override
    default Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        	return null;
    }

    @objid ("28665512-3e98-4803-9c57-bc4139737747")
    @Override
    default Object visitExternElement(ExternElement obj) {
        	return null;
    }

    @objid ("49b3ebc1-ecf2-472e-a8d8-957589d9c8cd")
    @Override
    default Object visitExternProcessor(ExternProcessor obj) {
        	return null;
    }

    @objid ("c2e8165f-2835-4b9b-89e3-6d30cd538fb2")
    @Override
    default Object visitGraphDiagram(GraphDiagram obj) {
        	return null;
    }

    @objid ("9622ae0e-8e36-4c21-b753-708f4abec787")
    @Override
    default Object visitImpactDiagram(ImpactDiagram obj) {
        	return null;
    }

    @objid ("9778b149-b573-4916-ba63-1506d6d5aacd")
    @Override
    default Object visitImpactLink(ImpactLink obj) {
        	return null;
    }

    @objid ("a6599691-61d8-4589-a1c3-9969af7019f6")
    @Override
    default Object visitImpactModel(ImpactModel obj) {
        	return null;
    }

    @objid ("c6752383-dbb4-461f-9588-3531721c9de7")
    @Override
    default Object visitImpactProject(ImpactProject obj) {
        	return null;
    }

    @objid ("179f1d10-6610-458c-97f5-15a47e9e1471")
    @Override
    default Object visitLocalPropertyTable(LocalPropertyTable obj) {
        	return null;
    }

    @objid ("2561a805-c697-402d-abd0-ef3614d31a39")
    @Override
    default Object visitMatrixDefinition(MatrixDefinition obj) {
        	return null;
    }

    @objid ("77c84564-d900-4e39-b7e5-448ecf5ccb39")
    @Override
    default Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        	return null;
    }

    @objid ("351bfa19-319a-48e0-a078-7e7ebd524385")
    @Override
    default Object visitMetaclassReference(MetaclassReference obj) {
        	return null;
    }

    @objid ("45cdb243-dbf1-4fda-9a43-10d685534ed7")
    @Override
    default Object visitMethodologicalLink(MethodologicalLink obj) {
        	return null;
    }

    @objid ("7f9288c1-dc2d-450f-ba46-d8587c17acbd")
    @Override
    default Object visitModelElement(ModelElement obj) {
        	return null;
    }

    @objid ("3e832be5-b7bd-4dfa-baf5-0d2e659c68a8")
    @Override
    default Object visitModuleComponent(ModuleComponent obj) {
        	return null;
    }

    @objid ("fc8ea279-0312-4bc5-ae59-924514d9984a")
    @Override
    default Object visitModuleParameter(ModuleParameter obj) {
        	return null;
    }

    @objid ("87bc2a09-cb73-41b5-b594-0577136e5a4e")
    @Override
    default Object visitNote(Note obj) {
        	return null;
    }

    @objid ("63bd8279-a637-4133-80e1-75a3ddce0fcf")
    @Override
    default Object visitNoteType(NoteType obj) {
        	return null;
    }

    @objid ("22dcd147-db98-46d0-90b0-9f514a0f2afa")
    @Override
    default Object visitProfile(Profile obj) {
        	return null;
    }

    @objid ("030271bc-9f71-4a54-b017-0e091f9a1e93")
    @Override
    default Object visitPropertyDefinition(PropertyDefinition obj) {
        	return null;
    }

    @objid ("76e0c1a0-b03e-4c83-841b-3c5bdc04a360")
    @Override
    default Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        	return null;
    }

    @objid ("b814c1e3-6125-47d8-b10a-73682c7ea6c5")
    @Override
    default Object visitPropertyTable(PropertyTable obj) {
        	return null;
    }

    @objid ("c0599eeb-272d-4275-8253-3fb4df675ce1")
    @Override
    default Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        	return null;
    }

    @objid ("93998b32-bb8b-445e-8a95-5120a27ef7f8")
    @Override
    default Object visitPropertyType(PropertyType obj) {
        	return null;
    }

    @objid ("f456e8dd-5ae0-4013-b811-b01e87a2e35f")
    @Override
    default Object visitQueryDefinition(QueryDefinition obj) {
        	return null;
    }

    @objid ("b07fd925-b20f-45c8-ad2f-b03c448093d2")
    @Override
    default Object visitResource(Resource obj) {
        	return null;
    }

    @objid ("c3e55bab-3fc8-4b74-b4f0-482fe4f1ced5")
    @Override
    default Object visitResourceType(ResourceType obj) {
        	return null;
    }

    @objid ("004403f1-e605-43e6-bdca-7706cfcfa0cf")
    @Override
    default Object visitStereotype(Stereotype obj) {
        	return null;
    }

    @objid ("0bee908d-5d9d-4a5b-8454-a6ee97ea2251")
    @Override
    default Object visitTagParameter(TagParameter obj) {
        	return null;
    }

    @objid ("b5814189-2393-4a8f-8e31-7fcece7aca40")
    @Override
    default Object visitTagType(TagType obj) {
        	return null;
    }

    @objid ("44c29705-2e80-4745-ba98-3e6af103c1c9")
    @Override
    default Object visitTaggedValue(TaggedValue obj) {
        	return null;
    }

    @objid ("a563d52a-ac35-494b-a347-3fec438e9c22")
    @Override
    default Object visitTypedPropertyTable(TypedPropertyTable obj) {
        	return null;
    }
}

