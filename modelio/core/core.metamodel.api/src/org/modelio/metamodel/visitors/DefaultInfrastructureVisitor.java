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
    @objid ("86b68453-3221-43c2-97d0-ebb66cdcf912")
    public  DefaultInfrastructureVisitor() {
        super();
    }

    @objid ("e981f53f-2a18-4966-af3a-3bb51b1aacdc")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram obj) {
        return visitModelElement(obj);
        
    }

    @objid ("142ffb2a-3a46-4de6-bfb3-175be42640ea")
    @Override
    public Object visitAbstractProject(AbstractProject obj) {
        return visitModelElement(obj);
        
    }

    @objid ("34a4e629-83f3-4dd4-b541-8d44c18f62b0")
    @Override
    public Object visitAbstractResource(AbstractResource obj) {
        return visitModelElement(obj);
        
    }

    @objid ("289455da-4922-4137-9bb7-09d0759b9bca")
    @Override
    public Object visitDependency(Dependency obj) {
        return visitModelElement(obj);
        
    }

    @objid ("8104a1b6-689b-4ccf-bcf5-8a86372bbd50")
    @Override
    public Object visitDiagramSet(DiagramSet obj) {
        return visitModelElement(obj);
        
    }

    @objid ("419d0315-e728-4377-b204-448627faab21")
    @Override
    public Object visitDocument(Document obj) {
        return visitAbstractResource(obj);
        
    }

    @objid ("22bf675a-1f42-418a-a907-47431231ba67")
    @Override
    public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        return visitPropertyDefinition(obj);
        
    }

    @objid ("3eec5748-6372-49b4-a085-a4d9b60b001a")
    @Override
    public Object visitElement(Element obj) {
        return null;
    }

    @objid ("1d13de5a-f91a-4f27-84ad-fbc9fe1f5d19")
    @Override
    public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        return visitPropertyType(obj);
        
    }

    @objid ("2d281798-6136-482d-a598-069cea987fba")
    @Override
    public Object visitExternElement(ExternElement obj) {
        return visitModelElement(obj);
        
    }

    @objid ("852d0e2c-1870-4d67-968a-23b2f31f1c25")
    @Override
    public Object visitExternProcessor(ExternProcessor obj) {
        return visitModelElement(obj);
        
    }

    @objid ("5a1995b4-2967-4783-96c4-d7b4a325e9d5")
    @Override
    public Object visitGraphDiagram(GraphDiagram obj) {
        return visitAbstractDiagram(obj);
        
    }

    @objid ("b43b8dd2-1ad7-4b51-8a3a-a6f76c94aadd")
    @Override
    public Object visitImpactDiagram(ImpactDiagram obj) {
        return visitAbstractDiagram(obj);
        
    }

    @objid ("bdaab141-28d3-461d-98dd-e5bcc65cd0c9")
    @Override
    public Object visitImpactLink(ImpactLink obj) {
        return visitModelElement(obj);
        
    }

    @objid ("a763b4f1-56d2-413c-abdd-51fbd402b542")
    @Override
    public Object visitImpactModel(ImpactModel obj) {
        return visitModelElement(obj);
        
    }

    @objid ("05557a67-320e-4c4e-bdcb-234dcf153092")
    @Override
    public Object visitImpactProject(ImpactProject obj) {
        return visitAbstractProject(obj);
        
    }

    @objid ("ab6621e8-0b36-4e9c-a764-937659d36ba9")
    @Override
    public Object visitLocalPropertyTable(LocalPropertyTable obj) {
        return visitPropertyTable(obj);
        
    }

    @objid ("b9662b90-dbd4-4d98-831c-e74a8e8c5736")
    @Override
    public Object visitMatrixDefinition(MatrixDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("492e4b09-33fa-4331-b5bf-e8248d71ba97")
    @Override
    public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        return visitElement(obj);
        
    }

    @objid ("867f7c51-806d-4cca-921a-3ddb5d97afc0")
    @Override
    public Object visitMetaclassReference(MetaclassReference obj) {
        return visitElement(obj);
        
    }

    @objid ("7cbe7481-507a-4ee5-b0c3-cef46c1f2415")
    @Override
    public Object visitMethodologicalLink(MethodologicalLink obj) {
        return visitDependency(obj);
        
    }

    @objid ("e72f580c-a48c-4d97-ad7b-729f3eb753c3")
    @Override
    public Object visitModelElement(ModelElement obj) {
        return visitElement(obj);
        
    }

    @objid ("7f84d2ca-c526-4730-b092-0d00fe7cee3a")
    @Override
    public Object visitModuleComponent(ModuleComponent obj) {
        return visitAbstractProject(obj);
        
    }

    @objid ("36a729aa-4967-433c-8164-3715f3e31d6c")
    @Override
    public Object visitModuleParameter(ModuleParameter obj) {
        return visitModelElement(obj);
        
    }

    @objid ("bc660f12-dce0-44ba-b232-9d1a20b75470")
    @Override
    public Object visitNote(Note obj) {
        return visitModelElement(obj);
        
    }

    @objid ("1fe0f139-d1fa-4630-b382-d431eaf12311")
    @Override
    public Object visitNoteType(NoteType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("23c36603-b38f-4f9a-a959-79312e03f6b5")
    @Override
    public Object visitProfile(Profile obj) {
        return visitModelElement(obj);
        
    }

    @objid ("1e422ea5-fd0e-487b-815f-3df30f5694c1")
    @Override
    public Object visitPropertyDefinition(PropertyDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("26d5601b-e7e5-46f0-a9f5-e96ea018c351")
    @Override
    public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        return visitModelElement(obj);
        
    }

    @objid ("8e0c81a5-678c-413b-9243-4fff94793d33")
    @Override
    public Object visitPropertyTable(PropertyTable obj) {
        return visitElement(obj);
        
    }

    @objid ("37ff98a0-5742-4e06-8a58-dbb9c4e8c5c1")
    @Override
    public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        return visitModelElement(obj);
        
    }

    @objid ("04e5add9-33ad-4c30-b7bb-289bf808a24f")
    @Override
    public Object visitPropertyType(PropertyType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("e8e9b459-8fe8-4e00-8dc7-447211c69caa")
    @Override
    public Object visitQueryDefinition(QueryDefinition obj) {
        return visitElement(obj);
        
    }

    @objid ("9254a69a-da86-43d6-8ac6-2dff90c14576")
    @Override
    public Object visitResource(Resource obj) {
        return visitAbstractResource(obj);
        
    }

    @objid ("d90b3af3-69d8-4d52-b174-1789909fff65")
    @Override
    public Object visitResourceType(ResourceType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("4e0c81e9-6024-405b-a244-56270e5160c0")
    @Override
    public Object visitStereotype(Stereotype obj) {
        return visitModelElement(obj);
        
    }

    @objid ("38e3a5da-5793-4081-bb59-2e8fbcf66779")
    @Override
    public Object visitTagParameter(TagParameter obj) {
        return visitElement(obj);
        
    }

    @objid ("dfc3f470-a24f-4a59-965b-19e7ed87979f")
    @Override
    public Object visitTagType(TagType obj) {
        return visitModelElement(obj);
        
    }

    @objid ("81579285-b079-47ca-aff6-6f25acc91beb")
    @Override
    public Object visitTaggedValue(TaggedValue obj) {
        return visitModelElement(obj);
        
    }

    @objid ("e55c4876-32c0-45ed-a25d-4bc438aa6720")
    @Override
    public Object visitTypedPropertyTable(TypedPropertyTable obj) {
        return visitPropertyTable(obj);
        
    }

}
