/* 
 * Copyright 2013-2019 Modeliosoft
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
 * This interface is an implementation of {@link IInfrastructureVisitor} whose default strategy consists in transmitting the visit() call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate the parent metaclass metamodel visitor if available.If not available, <code>null</code> is returned.
 */
@objid ("51e835a7-3e86-422c-8f90-e7323e4702b9")
public interface IDefaultInfrastructureVisitor extends IInfrastructureVisitor {
    @objid ("7c0cb5aa-19b2-4193-b342-603a2e2c4257")
    @Override
    default Object visitAbstractDiagram(AbstractDiagram obj) {
        return visitModelElement(obj);
    }

    @objid ("6b39a0fc-501b-47f0-b9ef-c35a0489d8e0")
    @Override
    default Object visitAbstractProject(AbstractProject obj) {
        return visitModelElement(obj);
    }

    @objid ("920fce84-05d8-4ea8-9b89-15025e4d76cc")
    @Override
    default Object visitAbstractResource(AbstractResource obj) {
        return visitModelElement(obj);
    }

    @objid ("c32ba91c-da12-4b3c-aa0c-4bffdb5ad508")
    @Override
    default Object visitDependency(Dependency obj) {
        return visitModelElement(obj);
    }

    @objid ("ca164cea-3f9a-4f20-8c31-b0c98edd198a")
    @Override
    default Object visitDiagramSet(DiagramSet obj) {
        return visitModelElement(obj);
    }

    @objid ("f501eb25-8c8a-4240-9e03-31c6c5d8b62e")
    @Override
    default Object visitDocument(Document obj) {
        return visitAbstractResource(obj);
    }

    @objid ("60e07585-dde6-4172-ae18-a903c2d7550a")
    @Override
    default Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        return visitPropertyDefinition(obj);
    }

    @objid ("2afe734c-9987-4ee4-8aef-1a56889104b3")
    @Override
    default Object visitElement(Element obj) {
        return null;
    }

    @objid ("e2534456-b815-43bd-b478-60987c373fc1")
    @Override
    default Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        return visitPropertyType(obj);
    }

    @objid ("52eeb5f9-1b23-4eb2-b0da-a47cfd538716")
    @Override
    default Object visitExternElement(ExternElement obj) {
        return visitModelElement(obj);
    }

    @objid ("2f62103a-e735-46ce-bb97-4fed9557910c")
    @Override
    default Object visitExternProcessor(ExternProcessor obj) {
        return visitModelElement(obj);
    }

    @objid ("56e1bca8-217e-43ec-91ae-2ca33a2cb7b6")
    @Override
    default Object visitGraphDiagram(GraphDiagram obj) {
        return visitAbstractDiagram(obj);
    }

    @objid ("a4aa5e49-8889-497e-8026-452070cb926f")
    @Override
    default Object visitImpactDiagram(ImpactDiagram obj) {
        return visitAbstractDiagram(obj);
    }

    @objid ("1343b844-0cc4-4da3-a8cb-2002c2b0b9f8")
    @Override
    default Object visitImpactLink(ImpactLink obj) {
        return visitModelElement(obj);
    }

    @objid ("c4548662-e76d-41c5-a457-2393c810a77f")
    @Override
    default Object visitImpactModel(ImpactModel obj) {
        return visitModelElement(obj);
    }

    @objid ("9cdcc0ce-688b-4b2e-9b4c-79b81e6d73b5")
    @Override
    default Object visitImpactProject(ImpactProject obj) {
        return visitAbstractProject(obj);
    }

    @objid ("e9cddc71-0e0e-462c-ac83-10d56c5abfaa")
    @Override
    default Object visitLocalPropertyTable(LocalPropertyTable obj) {
        return visitPropertyTable(obj);
    }

    @objid ("8720af89-4af5-4952-9d71-6c4b9f633401")
    @Override
    default Object visitMatrixDefinition(MatrixDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("24fb4ff3-6540-41da-8a4d-9ee94327d65c")
    @Override
    default Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        return visitElement(obj);
    }

    @objid ("a1400955-14e2-475a-89f6-bfb4bd080136")
    @Override
    default Object visitMetaclassReference(MetaclassReference obj) {
        return visitElement(obj);
    }

    @objid ("81859b6b-bfe0-4a15-8184-5f21706d98a5")
    @Override
    default Object visitMethodologicalLink(MethodologicalLink obj) {
        return visitDependency(obj);
    }

    @objid ("0cb9ed00-9b98-46ab-8971-89a6d89c778d")
    @Override
    default Object visitModelElement(ModelElement obj) {
        return visitElement(obj);
    }

    @objid ("a71511ee-465c-419d-ada5-a036f1ec9a69")
    @Override
    default Object visitModuleComponent(ModuleComponent obj) {
        return visitAbstractProject(obj);
    }

    @objid ("9515385f-1175-47ef-bc2c-95009d25cec3")
    @Override
    default Object visitModuleParameter(ModuleParameter obj) {
        return visitModelElement(obj);
    }

    @objid ("88e6f109-85a7-4728-b7b0-fa1036d6ee7d")
    @Override
    default Object visitNote(Note obj) {
        return visitModelElement(obj);
    }

    @objid ("c895a773-5921-438e-93e9-70292d5b6684")
    @Override
    default Object visitNoteType(NoteType obj) {
        return visitModelElement(obj);
    }

    @objid ("d5f1878c-2830-4b49-a8f0-c595dbad2ecf")
    @Override
    default Object visitProfile(Profile obj) {
        return visitModelElement(obj);
    }

    @objid ("6a5da75d-ee08-446d-b682-f88c7c54e1f2")
    @Override
    default Object visitPropertyDefinition(PropertyDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("e6265717-7db5-4aea-93a8-985b1da7e5f0")
    @Override
    default Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        return visitModelElement(obj);
    }

    @objid ("3836aea9-2f7b-4e1c-86fc-5d77c10ad994")
    @Override
    default Object visitPropertyTable(PropertyTable obj) {
        return visitElement(obj);
    }

    @objid ("9af11851-5c3e-4d4e-b533-96047fc186cb")
    @Override
    default Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("21a724db-40de-4881-888c-cf65a74143a5")
    @Override
    default Object visitPropertyType(PropertyType obj) {
        return visitModelElement(obj);
    }

    @objid ("79730b57-50fe-4828-9b93-91f6766b3b4f")
    @Override
    default Object visitQueryDefinition(QueryDefinition obj) {
        return visitElement(obj);
    }

    @objid ("1220fc1d-0a4b-474d-b86f-f1df0ab4d7ce")
    @Override
    default Object visitResource(Resource obj) {
        return visitAbstractResource(obj);
    }

    @objid ("e42d0270-db3c-47e3-9cbe-fafe95d11a8a")
    @Override
    default Object visitResourceType(ResourceType obj) {
        return visitModelElement(obj);
    }

    @objid ("b4655aba-2d7c-40c8-8f3f-ae98bc08f56d")
    @Override
    default Object visitStereotype(Stereotype obj) {
        return visitModelElement(obj);
    }

    @objid ("10fac305-af32-40e5-b705-352f448de15c")
    @Override
    default Object visitTagParameter(TagParameter obj) {
        return visitElement(obj);
    }

    @objid ("f90b3384-fe60-4654-9978-17f16c1ab4aa")
    @Override
    default Object visitTagType(TagType obj) {
        return visitModelElement(obj);
    }

    @objid ("3303640b-49ed-491b-b674-38517da7511c")
    @Override
    default Object visitTaggedValue(TaggedValue obj) {
        return visitModelElement(obj);
    }

    @objid ("295d6213-36a5-4067-bef1-a75b01998b4a")
    @Override
    default Object visitTypedPropertyTable(TypedPropertyTable obj) {
        return visitPropertyTable(obj);
    }

}
