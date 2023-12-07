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
 * This interface is an implementation of {@link IInfrastructureVisitor} whose default strategy consists in transmitting the visit() call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate the parent metaclass metamodel visitor if available.If not available, <code>null</code> is returned.
 * 
 */
@objid ("51e835a7-3e86-422c-8f90-e7323e4702b9")
public interface IDefaultInfrastructureVisitor extends IInfrastructureVisitor {
    @objid ("4b128682-5a8a-4b82-8465-01b7c8b207dc")
    @Override
    default Object visitAbstractDiagram(AbstractDiagram obj) {
        return visitModelElement(obj);
        
    }

    @objid ("d72168b6-f0a0-426d-935a-b471319a7857")
    @Override
    default Object visitAbstractProject(AbstractProject obj) {
        return visitModelElement(obj);
        
    }

    @objid ("b9e66509-6652-4d80-96e6-ba947b428c2d")
    @Override
    default Object visitAbstractResource(AbstractResource obj) {
        return visitModelElement(obj);
        
    }

    @objid ("87dd6a78-814f-4ae2-ba1a-02456f75053d")
    @Override
    default Object visitDependency(Dependency obj) {
        return visitModelElement(obj);
        
    }

    @objid ("0ab2a240-3433-437a-bd5f-6754e71bc459")
    @Override
    default Object visitDiagramSet(DiagramSet obj) {
        return visitModelElement(obj);
        
    }

    @objid ("4fbdd2bc-61f2-425d-8874-72eac9740c5e")
    @Override
    default Object visitDocument(Document obj) {
        return visitAbstractResource(obj);
        
    }

    @objid ("0ff3f6f8-a757-4563-80cb-ead631cd4f72")
    @Override
    default Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        return visitPropertyDefinition(obj);
        
    }

    @objid ("7a1dc1fe-16ec-4800-b5b6-a5f1cc884472")
    @Override
    default Object visitElement(Element obj) {
        return null;
    }

    @objid ("1e3a8dc3-47ca-4525-9077-cdb81b284723")
    @Override
    default Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        return visitPropertyType(obj);
        
    }

    @objid ("a51bb098-60ae-413e-9dca-ea3542fbf71c")
    @Override
    default Object visitExternElement(ExternElement obj) {
        return visitModelElement(obj);
        
    }

    @objid ("627d0b25-a58f-42d0-8081-3dab3ebdc9ff")
    @Override
    default Object visitExternProcessor(ExternProcessor obj) {
        return visitModelElement(obj);
        
    }

    @objid ("10153aac-0b8e-4f36-871f-b3091f571d04")
    @Override
    default Object visitGraphDiagram(GraphDiagram obj) {
        return visitAbstractDiagram(obj);
        
    }

    @objid ("5609676f-0744-4b6f-9b48-c1bccefc7a05")
    @Override
    default Object visitImpactDiagram(ImpactDiagram obj) {
        return visitAbstractDiagram(obj);
        
    }

    @objid ("e1d92f82-55a0-4a27-8eff-9e04d41667db")
    @Override
    default Object visitImpactLink(ImpactLink obj) {
        return visitModelElement(obj);
        
    }

    @objid ("31d0edf6-9681-4647-8e1f-f7ac45f96514")
    @Override
    default Object visitImpactModel(ImpactModel obj) {
        return visitModelElement(obj);
        
    }

    @objid ("57694126-e245-4b11-9b0d-145cd0acfcb0")
    @Override
    default Object visitImpactProject(ImpactProject obj) {
        return visitAbstractProject(obj);
        
    }

    @objid ("67d71bb2-4001-47f0-bb0e-94005b881088")
    @Override
    default Object visitLocalPropertyTable(LocalPropertyTable obj) {
        return visitPropertyTable(obj);
        
    }

    @objid ("ecd739ff-7d49-42f9-800b-9623ce661bcf")
    @Override
    default Object visitMatrixDefinition(MatrixDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("789df202-b251-40f3-bd6b-c3e4f9262f64")
    @Override
    default Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        return visitElement(obj);
        
    }

    @objid ("befeb694-c537-4e5a-b8ce-f3ef85e34a55")
    @Override
    default Object visitMetaclassReference(MetaclassReference obj) {
        return visitElement(obj);
        
    }

    @objid ("925ce455-a6a8-4176-b86c-813bd1897798")
    @Override
    default Object visitMethodologicalLink(MethodologicalLink obj) {
        return visitDependency(obj);
        
    }

    @objid ("8ae7ba00-21a1-44fa-bedc-653fe6f03f75")
    @Override
    default Object visitModelElement(ModelElement obj) {
        return visitElement(obj);
        
    }

    @objid ("b46792e6-c999-42b8-b3fc-7f1ec72ea453")
    @Override
    default Object visitModuleComponent(ModuleComponent obj) {
        return visitAbstractProject(obj);
        
    }

    @objid ("9072516f-7d3f-4893-ab89-ae3eb7793342")
    @Override
    default Object visitModuleParameter(ModuleParameter obj) {
        return visitModelElement(obj);
        
    }

    @objid ("f8029b5e-f7e3-47c6-8382-653288706cd9")
    @Override
    default Object visitNote(Note obj) {
        return visitModelElement(obj);
        
    }

    @objid ("9b579bf4-897a-43fe-8bd0-d3547e128917")
    @Override
    default Object visitNoteType(NoteType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("8ecbfa7b-ffc7-4422-bfbb-8ad690f78261")
    @Override
    default Object visitProfile(Profile obj) {
        return visitModelElement(obj);
        
    }

    @objid ("3866cd33-a452-47fb-8083-176fb550239f")
    @Override
    default Object visitPropertyDefinition(PropertyDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("cba4c125-d726-4be8-8fcf-8c127e0bc586")
    @Override
    default Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        return visitModelElement(obj);
        
    }

    @objid ("e1a1d3fa-0e68-4e49-a038-d47e653980b5")
    @Override
    default Object visitPropertyTable(PropertyTable obj) {
        return visitElement(obj);
        
    }

    @objid ("e922995d-e7fe-426d-8dd0-1a9687523449")
    @Override
    default Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("55543ba8-5cb4-45a8-bfcf-b83da4a35fae")
    @Override
    default Object visitPropertyType(PropertyType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("f1bcf721-51c9-4c25-84d4-ce81ed27708f")
    @Override
    default Object visitQueryDefinition(QueryDefinition obj) {
        return visitElement(obj);
        
    }

    @objid ("39fac1c6-d805-4a9c-8555-cadb2dd10fb7")
    @Override
    default Object visitResource(Resource obj) {
        return visitAbstractResource(obj);
        
    }

    @objid ("1c0ef9dd-0a4e-4f2d-8350-f5a2502cc592")
    @Override
    default Object visitResourceType(ResourceType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("b10bbf06-4283-4c4b-b228-593427cfa392")
    @Override
    default Object visitStereotype(Stereotype obj) {
        return visitModelElement(obj);
        
    }

    @objid ("1c9d933c-b087-4496-9d9a-4cac1a6f54be")
    @Override
    default Object visitTagParameter(TagParameter obj) {
        return visitElement(obj);
        
    }

    @objid ("3fa6e79d-61c3-417b-afc5-3bbe753faea9")
    @Override
    default Object visitTagType(TagType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("187898b8-4045-4e1a-a5b4-c7d66f7975be")
    @Override
    default Object visitTaggedValue(TaggedValue obj) {
        return visitModelElement(obj);
        
    }

    @objid ("f44e6a96-8064-4610-9fb5-bd8e1acfdc4b")
    @Override
    default Object visitTypedPropertyTable(TypedPropertyTable obj) {
        return visitPropertyTable(obj);
        
    }
}

