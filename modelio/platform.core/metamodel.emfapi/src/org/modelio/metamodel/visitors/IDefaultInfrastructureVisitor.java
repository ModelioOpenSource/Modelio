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
 * This interface is an implementation of {@link IInfrastructureVisitor} whose default strategy consists in transmitting the visit() call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate the parent metaclass metamodel visitor if available.If not available, <code>null</code> is returned.
 */
@objid ("51e835a7-3e86-422c-8f90-e7323e4702b9")
public interface IDefaultInfrastructureVisitor extends IInfrastructureVisitor {
    @objid ("eba4c28b-994b-4abb-a8b8-7850c2e206e3")
    @Override
    default Object visitAbstractDiagram(AbstractDiagram obj) {
        return visitModelElement(obj);
    }

    @objid ("158bdafd-71b8-4303-a2a9-98975118d4ed")
    @Override
    default Object visitAbstractProject(AbstractProject obj) {
        return visitModelElement(obj);
    }

    @objid ("c6b36c0f-1eaf-4d0d-8afe-084b51648ce6")
    @Override
    default Object visitAbstractResource(AbstractResource obj) {
        return visitModelElement(obj);
    }

    @objid ("24e2e6df-4cdd-4ac8-9472-4e90797a0f39")
    @Override
    default Object visitDependency(Dependency obj) {
        return visitModelElement(obj);
    }

    @objid ("cdf089f5-0c68-47b3-b16e-5f4a941174de")
    @Override
    default Object visitDiagramSet(DiagramSet obj) {
        return visitModelElement(obj);
    }

    @objid ("1d93a034-b505-4faa-9147-6aa9f69691f1")
    @Override
    default Object visitDocument(Document obj) {
        return visitAbstractResource(obj);
    }

    @objid ("effa1506-9f7a-40b5-92f1-e7f2c417ac33")
    @Override
    default Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        return visitPropertyDefinition(obj);
    }

    @objid ("4220bd2f-7aed-4cd5-9625-bc2059c60695")
    @Override
    default Object visitElement(Element obj) {
        return null;
    }

    @objid ("5293f389-0bf2-4ac1-bc25-9b0aca85a8c6")
    @Override
    default Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        return visitPropertyType(obj);
    }

    @objid ("5c77157e-76d1-4719-b467-05c3a8f21a74")
    @Override
    default Object visitExternProcessor(ExternProcessor obj) {
        return visitModelElement(obj);
    }

    @objid ("cf5f9a01-f202-40ab-9da7-7ed2f16de7b1")
    @Override
    default Object visitGraphDiagram(GraphDiagram obj) {
        return visitAbstractDiagram(obj);
    }

    @objid ("bebc8230-6d24-4d8f-9fe9-d02b2cfe98c9")
    @Override
    default Object visitImpactDiagram(ImpactDiagram obj) {
        return visitAbstractDiagram(obj);
    }

    @objid ("d07f6ecc-5af6-4175-a333-d99987dabcdc")
    @Override
    default Object visitImpactLink(ImpactLink obj) {
        return visitModelElement(obj);
    }

    @objid ("a4e2f1ae-647f-43f0-8d47-3df9ae904366")
    @Override
    default Object visitImpactModel(ImpactModel obj) {
        return visitModelElement(obj);
    }

    @objid ("94bb32e4-9749-4447-9d85-93f9f9a0cb0f")
    @Override
    default Object visitImpactProject(ImpactProject obj) {
        return visitAbstractProject(obj);
    }

    @objid ("4441dcce-6ac7-403e-b008-d782603c2f71")
    @Override
    default Object visitLocalPropertyTable(LocalPropertyTable obj) {
        return visitPropertyTable(obj);
    }

    @objid ("369aece0-7aa7-40e3-a39b-c4befecf686c")
    @Override
    default Object visitMatrixDefinition(MatrixDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("9fe28199-29e7-4588-9b93-c0d3378a832d")
    @Override
    default Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        return visitElement(obj);
    }

    @objid ("17115c6f-848f-4eb9-a1a3-922966ef7ba8")
    @Override
    default Object visitMetaclassReference(MetaclassReference obj) {
        return visitElement(obj);
    }

    @objid ("204a0f1e-05ce-43ab-884f-31721373a33d")
    @Override
    default Object visitMethodologicalLink(MethodologicalLink obj) {
        return visitDependency(obj);
    }

    @objid ("b01b39fd-1079-4a06-9a2a-3cddb9074972")
    @Override
    default Object visitModelElement(ModelElement obj) {
        return visitElement(obj);
    }

    @objid ("ef9a6109-9b4e-49c2-93b9-39453d6ad422")
    @Override
    default Object visitModuleComponent(ModuleComponent obj) {
        return visitAbstractProject(obj);
    }

    @objid ("9eb2027a-a45a-4cdf-b51a-0d7b99774ffc")
    @Override
    default Object visitModuleParameter(ModuleParameter obj) {
        return visitModelElement(obj);
    }

    @objid ("78e916c6-dccb-4a96-8344-c6fe728bb915")
    @Override
    default Object visitNote(Note obj) {
        return visitModelElement(obj);
    }

    @objid ("d50bac0f-b55f-4565-8458-62f00600a169")
    @Override
    default Object visitNoteType(NoteType obj) {
        return visitModelElement(obj);
    }

    @objid ("2e378809-ec25-4f54-be7c-c6b40065ffc6")
    @Override
    default Object visitProfile(Profile obj) {
        return visitModelElement(obj);
    }

    @objid ("e572dd5f-bb1d-4cac-9d32-d9d37d09941f")
    @Override
    default Object visitPropertyDefinition(PropertyDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("48c5a8e5-0e59-4c48-9a62-df015e29350e")
    @Override
    default Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        return visitModelElement(obj);
    }

    @objid ("9cc30bc7-1a86-46e4-98fa-94cdcf6b27e0")
    @Override
    default Object visitPropertyTable(PropertyTable obj) {
        return visitElement(obj);
    }

    @objid ("9351aa84-a77c-4b79-a43d-c79e7704d59f")
    @Override
    default Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("3beaa119-a5bd-4ffd-865f-c8f682950e71")
    @Override
    default Object visitPropertyType(PropertyType obj) {
        return visitModelElement(obj);
    }

    @objid ("290a66fd-fa9d-4a65-a993-6f7a236ef3f0")
    @Override
    default Object visitQueryDefinition(QueryDefinition obj) {
        return visitElement(obj);
    }

    @objid ("c5ed8e57-7ef0-4c58-bcb2-73ce7b9adbd5")
    @Override
    default Object visitResource(Resource obj) {
        return visitAbstractResource(obj);
    }

    @objid ("7cb9d295-abd7-451a-a65b-e9dfce65de0a")
    @Override
    default Object visitResourceType(ResourceType obj) {
        return visitModelElement(obj);
    }

    @objid ("6ab46153-be7a-4fcb-9ea9-4226eb0592b8")
    @Override
    default Object visitStereotype(Stereotype obj) {
        return visitModelElement(obj);
    }

    @objid ("1b0b57c9-381a-4f0c-b5fd-095cb7f25c1d")
    @Override
    default Object visitTagParameter(TagParameter obj) {
        return visitElement(obj);
    }

    @objid ("3cfd6f27-f16c-49e4-bedd-e8599d8bd014")
    @Override
    default Object visitTagType(TagType obj) {
        return visitModelElement(obj);
    }

    @objid ("f97919c4-3952-4186-aa0a-5db5b5ca0cf7")
    @Override
    default Object visitTaggedValue(TaggedValue obj) {
        return visitModelElement(obj);
    }

    @objid ("037ccf75-768a-43f6-9a07-fb7916430c99")
    @Override
    default Object visitTypedPropertyTable(TypedPropertyTable obj) {
        return visitPropertyTable(obj);
    }

}
