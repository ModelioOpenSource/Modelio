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
 * This class is an implementation of {@link IInfrastructureVisitor} whose default strategy consists in transmitting the visit call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate to the configured delegation visitor if available.If not available, <code>null</code> is returned.
 */
@objid ("953b0ad0-d247-4625-b010-7a7588706101")
public class DefaultInfrastructureVisitor implements IInfrastructureVisitor {
    @objid ("3e09d7b8-41d1-43d9-be4b-45e321bd066e")
    public DefaultInfrastructureVisitor() {
        super();
    }

    @objid ("17012084-7ec1-4ee2-8f71-417bb14907a7")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram obj) {
        return visitModelElement(obj);
    }

    @objid ("8dfc0293-05e8-4cd0-9c3f-e4d94953f64f")
    @Override
    public Object visitAbstractProject(AbstractProject obj) {
        return visitModelElement(obj);
    }

    @objid ("7ab5a812-218a-437e-ba4f-17a1139d087e")
    @Override
    public Object visitAbstractResource(AbstractResource obj) {
        return visitModelElement(obj);
    }

    @objid ("360ff389-d582-4eaa-b70e-dfdda8630edd")
    @Override
    public Object visitDependency(Dependency obj) {
        return visitModelElement(obj);
    }

    @objid ("92b57090-e689-4a65-8bf1-372634cda04d")
    @Override
    public Object visitDiagramSet(DiagramSet obj) {
        return visitModelElement(obj);
    }

    @objid ("ac56190e-373f-4ba4-9791-27d947f4c1c4")
    @Override
    public Object visitDocument(Document obj) {
        return visitAbstractResource(obj);
    }

    @objid ("d867f918-af01-4277-b82a-461b1c02f166")
    @Override
    public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
        return visitPropertyDefinition(obj);
    }

    @objid ("e771434f-65c3-43c6-a266-2d5831caec94")
    @Override
    public Object visitElement(Element obj) {
        return null;
    }

    @objid ("8e9615d1-0df9-44f4-83a3-9dedd496677e")
    @Override
    public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
        return visitPropertyType(obj);
    }

    @objid ("18382fef-a58a-43e4-bb28-cae597ea7cce")
    @Override
    public Object visitExternProcessor(ExternProcessor obj) {
        return visitModelElement(obj);
    }

    @objid ("e1062f0d-0583-40f3-836e-68ae2ed6fe82")
    @Override
    public Object visitGraphDiagram(GraphDiagram obj) {
        return visitAbstractDiagram(obj);
    }

    @objid ("38436f8b-1bd7-401c-82de-f76e372222ec")
    @Override
    public Object visitImpactDiagram(ImpactDiagram obj) {
        return visitAbstractDiagram(obj);
    }

    @objid ("4261edfe-0d07-4aa2-82a5-542a2e025218")
    @Override
    public Object visitImpactLink(ImpactLink obj) {
        return visitModelElement(obj);
    }

    @objid ("315d289b-b576-4c89-986b-e481388caea7")
    @Override
    public Object visitImpactModel(ImpactModel obj) {
        return visitModelElement(obj);
    }

    @objid ("c614fc9d-7345-403d-8886-c36309c58595")
    @Override
    public Object visitImpactProject(ImpactProject obj) {
        return visitAbstractProject(obj);
    }

    @objid ("30753c51-0f54-4b6a-8824-639cf48ccc5f")
    @Override
    public Object visitLocalPropertyTable(LocalPropertyTable obj) {
        return visitPropertyTable(obj);
    }

    @objid ("0da3e641-7147-4026-aa74-3688e3c2e825")
    @Override
    public Object visitMatrixDefinition(MatrixDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("e5f22ad3-36e3-4aa7-8b2d-a1395c1be601")
    @Override
    public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
        return visitElement(obj);
    }

    @objid ("6f9f63ad-ddef-4b1a-b30d-0192304e6231")
    @Override
    public Object visitMetaclassReference(MetaclassReference obj) {
        return visitElement(obj);
    }

    @objid ("0fcfcdb8-c60c-493d-94cf-9a4056788064")
    @Override
    public Object visitMethodologicalLink(MethodologicalLink obj) {
        return visitDependency(obj);
    }

    @objid ("cb9369fa-1a47-4b01-a081-08c65894928f")
    @Override
    public Object visitModelElement(ModelElement obj) {
        return visitElement(obj);
    }

    @objid ("ddd7e56f-4a39-472c-a2aa-3113cb24c813")
    @Override
    public Object visitModuleComponent(ModuleComponent obj) {
        return visitAbstractProject(obj);
    }

    @objid ("2cda15f5-ba89-422b-9fd0-0b12f885099c")
    @Override
    public Object visitModuleParameter(ModuleParameter obj) {
        return visitModelElement(obj);
    }

    @objid ("d1a76467-dab0-4175-ad1d-ed8a05682648")
    @Override
    public Object visitNote(Note obj) {
        return visitModelElement(obj);
    }

    @objid ("ef7338b8-7f4a-4455-9189-337ee71aa536")
    @Override
    public Object visitNoteType(NoteType obj) {
        return visitModelElement(obj);
    }

    @objid ("3d2a6638-2a21-46e3-822e-1e54e250ed41")
    @Override
    public Object visitProfile(Profile obj) {
        return visitModelElement(obj);
    }

    @objid ("154994e9-684a-44d6-a83b-fcd102045b93")
    @Override
    public Object visitPropertyDefinition(PropertyDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("6aa4a008-462f-4ca5-b89e-196b11009e35")
    @Override
    public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
        return visitModelElement(obj);
    }

    @objid ("7e5ae054-8814-4b31-a7fe-84120d73d83a")
    @Override
    public Object visitPropertyTable(PropertyTable obj) {
        return visitElement(obj);
    }

    @objid ("5038ab55-797b-46b1-8e56-43cd18d940cf")
    @Override
    public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
        return visitModelElement(obj);
    }

    @objid ("63a6d8f7-5183-4a13-bea5-f872c7cd572b")
    @Override
    public Object visitPropertyType(PropertyType obj) {
        return visitModelElement(obj);
    }

    @objid ("e15ae5ae-00b3-4c70-9fcc-353b9d80d2f0")
    @Override
    public Object visitQueryDefinition(QueryDefinition obj) {
        return visitElement(obj);
    }

    @objid ("cf6bb9fc-e582-4e0c-8247-85cbeb6c053e")
    @Override
    public Object visitResource(Resource obj) {
        return visitAbstractResource(obj);
    }

    @objid ("a0de557e-d3f5-4db0-833c-f9bc12514440")
    @Override
    public Object visitResourceType(ResourceType obj) {
        return visitModelElement(obj);
    }

    @objid ("345bc6f9-efb1-4740-8e23-14e3c6347229")
    @Override
    public Object visitStereotype(Stereotype obj) {
        return visitModelElement(obj);
    }

    @objid ("b85e1f4f-32c5-4552-936f-089a7849b260")
    @Override
    public Object visitTagParameter(TagParameter obj) {
        return visitElement(obj);
    }

    @objid ("15dab4b5-cd6e-4d3a-9a19-f1b0ce91e367")
    @Override
    public Object visitTagType(TagType obj) {
        return visitModelElement(obj);
    }

    @objid ("6468bb2c-0a6f-484c-8c00-de5234591fe6")
    @Override
    public Object visitTaggedValue(TaggedValue obj) {
        return visitModelElement(obj);
    }

    @objid ("afe43974-ab93-46b5-84b7-2a17bcd6b14b")
    @Override
    public Object visitTypedPropertyTable(TypedPropertyTable obj) {
        return visitPropertyTable(obj);
    }

}
