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
 * This class is an implementation of {@link IInfrastructureVisitor} whose default strategy consists in transmitting the visit call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate to the configured delegation visitor if available.If not available, <code>null</code> is returned.
 * 
 */
@objid ("953b0ad0-d247-4625-b010-7a7588706101")
public class DefaultInfrastructureVisitor implements IInfrastructureVisitor {
    @objid ("b2bbe1a0-8ae4-4d0a-af4c-627c58753fc3")
    public  DefaultInfrastructureVisitor() {
        super();
    }

    @objid ("20193605-0a8c-48ab-b49b-d437a9701761")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram obj) {
        return visitModelElement(obj);
        
    }

    @objid ("089c749a-da92-482c-8b51-60ae35d2c9e7")
    @Override
    public Object visitAbstractProject(AbstractProject obj) {
        return visitModelElement(obj);
        
    }

    @objid ("1f86b683-ad76-436b-b8e3-4b6106b00bf0")
    @Override
    public Object visitAbstractResource(AbstractResource obj) {
        return visitModelElement(obj);
        
    }

    @objid ("c648ca4b-3fc6-4674-a33e-6f9967aefff9")
    @Override
    public Object visitDependency(Dependency obj) {
        return visitModelElement(obj);
        
    }

    @objid ("1397097e-288e-45d7-825e-6933ba2f8500")
    @Override
    public Object visitDiagramSet(DiagramSet obj) {
        return visitModelElement(obj);
        
    }

    @objid ("442a9600-1ce5-4635-aa10-5a4c8e34316b")
    @Override
    public Object visitDocument(Document obj) {
        return visitAbstractResource(obj);
        
    }

    @objid ("e69dbfbc-ce81-46d0-bdfc-61849e1a10f9")
    @Override
    public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        return visitPropertyDefinition(obj);
        
    }

    @objid ("a016163b-d6f1-441f-9073-d396ef30aef5")
    @Override
    public Object visitElement(Element obj) {
        return null;
    }

    @objid ("6f03e33d-7bca-4e8c-b3f1-c7c314030e5a")
    @Override
    public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        return visitPropertyType(obj);
        
    }

    @objid ("f0b35843-b06c-43c2-b4c8-459f87bd8b1e")
    @Override
    public Object visitExternElement(ExternElement obj) {
        return visitModelElement(obj);
        
    }

    @objid ("99e04657-c7a4-44fe-acd2-63c7aabccb26")
    @Override
    public Object visitExternProcessor(ExternProcessor obj) {
        return visitModelElement(obj);
        
    }

    @objid ("ce95e71a-a563-4a32-8a4b-e736a2ed104d")
    @Override
    public Object visitGraphDiagram(GraphDiagram obj) {
        return visitAbstractDiagram(obj);
        
    }

    @objid ("4b580546-b6f7-4079-a8c1-78240ac89672")
    @Override
    public Object visitImpactDiagram(ImpactDiagram obj) {
        return visitAbstractDiagram(obj);
        
    }

    @objid ("f343b04f-bc55-40a6-92fd-b5245bf68aeb")
    @Override
    public Object visitImpactLink(ImpactLink obj) {
        return visitModelElement(obj);
        
    }

    @objid ("7187f73a-929f-47fa-bfff-8fe0c944f9e4")
    @Override
    public Object visitImpactModel(ImpactModel obj) {
        return visitModelElement(obj);
        
    }

    @objid ("48c4aff3-1c9f-40ed-942a-f950db9d7b99")
    @Override
    public Object visitImpactProject(ImpactProject obj) {
        return visitAbstractProject(obj);
        
    }

    @objid ("861fd24e-939a-4f2c-9610-f4eec8709e66")
    @Override
    public Object visitLocalPropertyTable(LocalPropertyTable obj) {
        return visitPropertyTable(obj);
        
    }

    @objid ("baf3aa92-28a5-459c-9e55-03d4600a4157")
    @Override
    public Object visitMatrixDefinition(MatrixDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("c85d17d0-5d37-4de6-9fd3-88c10fdd30d2")
    @Override
    public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        return visitElement(obj);
        
    }

    @objid ("99dd5fa1-3488-4236-b11f-729e4d7abceb")
    @Override
    public Object visitMetaclassReference(MetaclassReference obj) {
        return visitElement(obj);
        
    }

    @objid ("4b36accb-4f16-46d7-ba9a-d55e45f05bcf")
    @Override
    public Object visitMethodologicalLink(MethodologicalLink obj) {
        return visitDependency(obj);
        
    }

    @objid ("2911da62-1501-43c4-9db8-1b1b7f57583a")
    @Override
    public Object visitModelElement(ModelElement obj) {
        return visitElement(obj);
        
    }

    @objid ("c5082ef3-2ffc-4b70-acf6-d022fe97ff35")
    @Override
    public Object visitModuleComponent(ModuleComponent obj) {
        return visitAbstractProject(obj);
        
    }

    @objid ("165c4746-4e37-4af9-b6ff-425e4bed0975")
    @Override
    public Object visitModuleParameter(ModuleParameter obj) {
        return visitModelElement(obj);
        
    }

    @objid ("592dc24a-90ea-4986-ae77-d64899409fb9")
    @Override
    public Object visitNote(Note obj) {
        return visitModelElement(obj);
        
    }

    @objid ("41297051-6751-4592-ac6d-582fb88c4d9f")
    @Override
    public Object visitNoteType(NoteType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("bcdd69b8-4fbd-4dff-a7a9-e9850594ed65")
    @Override
    public Object visitProfile(Profile obj) {
        return visitModelElement(obj);
        
    }

    @objid ("e3f094f9-cebd-469d-aaa0-d4d8c7f31a59")
    @Override
    public Object visitPropertyDefinition(PropertyDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("ea8b0e70-06f7-4e8e-a0d0-663d868e39f7")
    @Override
    public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        return visitModelElement(obj);
        
    }

    @objid ("ba2bd222-d100-4506-9137-a7b5f05edda3")
    @Override
    public Object visitPropertyTable(PropertyTable obj) {
        return visitElement(obj);
        
    }

    @objid ("fee788b3-2add-4db4-a908-ce9da9537483")
    @Override
    public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("288e8ef7-de6a-4feb-a72c-ba831485397a")
    @Override
    public Object visitPropertyType(PropertyType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("4bec3bf5-a80b-435a-8461-771137441909")
    @Override
    public Object visitQueryDefinition(QueryDefinition obj) {
        return visitElement(obj);
        
    }

    @objid ("fbd5a623-b339-43c9-97c5-b00f72fc903e")
    @Override
    public Object visitResource(Resource obj) {
        return visitAbstractResource(obj);
        
    }

    @objid ("ece3bcfe-193b-4ba3-96c7-1110ea4cca1c")
    @Override
    public Object visitResourceType(ResourceType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("9febb900-226d-44a9-9c2b-aca2f50dd7e4")
    @Override
    public Object visitStereotype(Stereotype obj) {
        return visitModelElement(obj);
        
    }

    @objid ("cce8dc08-737a-447c-a799-5d07d59b7708")
    @Override
    public Object visitTagParameter(TagParameter obj) {
        return visitElement(obj);
        
    }

    @objid ("a17c9fb5-1acf-41c0-9597-0357fa2f0454")
    @Override
    public Object visitTagType(TagType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("c639c50a-deaa-4439-86be-0b2c5d7d6c29")
    @Override
    public Object visitTaggedValue(TaggedValue obj) {
        return visitModelElement(obj);
        
    }

    @objid ("77f4e0ec-db28-491d-8d97-86cd60b3ecaa")
    @Override
    public Object visitTypedPropertyTable(TypedPropertyTable obj) {
        return visitPropertyTable(obj);
        
    }

}
