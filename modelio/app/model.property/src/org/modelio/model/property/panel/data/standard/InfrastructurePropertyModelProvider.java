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

package org.modelio.model.property.panel.data.standard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModelProvider;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.model.property.panel.data.standard.infrastructure.DependencyPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.DiagramSetPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.DocumentPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.DynamicPropertyDefinitionPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.EnumeratedPropertyTypePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ExternElementPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ImpactLinkPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ImpactModelPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.MatrixDefinitionPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.MatrixValueDefinitionPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.MetaclassReferencePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ModuleComponentPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ModuleParameterPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.NotePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.NoteTypePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ProfilePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.PropertyDefinitionPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.PropertyEnumerationLitteralPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.PropertySetPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.PropertyTypePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.QueryDefinitionPropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ResourcePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.ResourceTypePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.StereotypePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.TagTypePropertyModel;
import org.modelio.model.property.panel.data.standard.infrastructure.TypedPropertyTablePropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default property model provider with Infrastructure elements for the property view.
 */
@objid ("94a76b52-40c2-444e-9bc6-f1f1c93403e5")
public class InfrastructurePropertyModelProvider implements IPropertyModelProvider {
    @objid ("412d3fb2-0a97-400e-85b7-4c95eb56ebbb")
    @Override
    public IPropertyModel<?> getPropertyModel(MObject element, INatTableViewerContext context) {
        return (IPropertyModel<?>) element.accept(new InfrastructurePropertyModelVisitor());
    }

    @objid ("9b470280-ccc4-4907-9092-07f8d3b7d135")
    private static class InfrastructurePropertyModelVisitor extends DefaultInfrastructureVisitor {
        @objid ("fec8a3df-9c92-4792-99a8-d36acb688a78")
        public InfrastructurePropertyModelVisitor() {
        }

        @objid ("8e23af6c-fd01-4398-93b4-efcfcd3eeacf")
        @Override
        public Object visitDependency(Dependency theDependency) {
            return new DependencyPropertyModel(theDependency);
        }

        @objid ("bb85f89d-d851-4240-ba15-d016ca609173")
        @Override
        public Object visitDiagramSet(DiagramSet theDiagramSet) {
            return new DiagramSetPropertyModel(theDiagramSet);
        }

        @objid ("0a29a53c-3bcd-4810-b60b-4792a2862f3a")
        @Override
        public Object visitDocument(Document theDocument) {
            return new DocumentPropertyModel(theDocument);
        }

        @objid ("e4087905-4978-497f-b672-a1c695272087")
        @Override
        public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition theProperty) {
            return new DynamicPropertyDefinitionPropertyModel(theProperty);
        }

        @objid ("9d5d2afe-d076-4e0f-874b-63f8662099b0")
        @Override
        public Object visitEnumeratedPropertyType(EnumeratedPropertyType theEnumeratedPropertyType) {
            return new EnumeratedPropertyTypePropertyModel(theEnumeratedPropertyType);
        }

        @objid ("d5cfa9fd-263f-458a-8725-2669f8dd2f41")
        @Override
        public Object visitExternElement(ExternElement theExternElement) {
            return new ExternElementPropertyModel(theExternElement);
        }

        @objid ("fc2ff4a7-447c-42f5-9624-1fa1ab5249fa")
        @Override
        public Object visitImpactLink(ImpactLink theImpactLink) {
            return new ImpactLinkPropertyModel(theImpactLink);
        }

        @objid ("3730abfd-7522-4806-82f6-da4f8c3e540e")
        @Override
        public Object visitImpactModel(ImpactModel theImpactModel) {
            return new ImpactModelPropertyModel(theImpactModel);
        }

        @objid ("8d41eda4-0e02-4f56-97e1-3493881b0fb3")
        @Override
        public Object visitMatrixDefinition(MatrixDefinition theMatrixDefinition) {
            return new MatrixDefinitionPropertyModel(theMatrixDefinition);
        }

        @objid ("466c459d-54db-4732-afba-c21eb3ec8520")
        @Override
        public Object visitMatrixValueDefinition(MatrixValueDefinition theMatrixValueDefinition) {
            return new MatrixValueDefinitionPropertyModel(theMatrixValueDefinition);
        }

        @objid ("1d8e134e-c63d-4486-ad1a-5af9f7b8569c")
        @Override
        public Object visitMetaclassReference(MetaclassReference theMetaclassReference) {
            return new MetaclassReferencePropertyModel(theMetaclassReference);
        }

        @objid ("ecab6318-c613-444d-a73c-c778e82d536a")
        @Override
        public Object visitModuleComponent(ModuleComponent theModule) {
            return new ModuleComponentPropertyModel(theModule);
        }

        @objid ("782ca8e5-5996-4a99-b749-5d1caf39317b")
        @Override
        public Object visitModuleParameter(ModuleParameter theConfigParam) {
            return new ModuleParameterPropertyModel(theConfigParam);
        }

        @objid ("b5fb164a-0cdd-4306-8189-77c0d2a0b33f")
        @Override
        public Object visitNote(Note theNote) {
            return new NotePropertyModel(theNote);
        }

        @objid ("91a5ed9e-4108-49fc-95e6-cb361cc3ff51")
        @Override
        public Object visitNoteType(NoteType theNoteType) {
            return new NoteTypePropertyModel(theNoteType);
        }

        @objid ("c6472d59-9936-4b35-9f27-def202ad4b11")
        @Override
        public Object visitProfile(Profile theProfile) {
            return new ProfilePropertyModel(theProfile);
        }

        @objid ("946475d1-b8d2-466c-ad04-7da178d2e123")
        @Override
        public Object visitPropertyDefinition(PropertyDefinition theProperty) {
            return new PropertyDefinitionPropertyModel(theProperty);
        }

        @objid ("d1ed61aa-142a-4cc2-85df-42ebbf0302b3")
        @Override
        public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral thePropertyEnumerationLitteral) {
            return new PropertyEnumerationLitteralPropertyModel(thePropertyEnumerationLitteral);
        }

        @objid ("b50c71a5-0d93-4562-81b6-ba7e1d0c3865")
        @Override
        public Object visitPropertyTableDefinition(PropertyTableDefinition thePropertySet) {
            return new PropertySetPropertyModel(thePropertySet);
        }

        @objid ("fe0fe3b2-feb4-4e71-9855-16bc5e94b557")
        @Override
        public Object visitPropertyType(PropertyType thePropertyType) {
            return new PropertyTypePropertyModel(thePropertyType);
        }

        @objid ("4a7b18ff-d1a0-4f9c-828f-47469b1bcec5")
        @Override
        public Object visitQueryDefinition(QueryDefinition theQueryDefinition) {
            return new QueryDefinitionPropertyModel(theQueryDefinition);
        }

        @objid ("2aaef246-b371-483d-a005-b3ce5aa218e2")
        @Override
        public Object visitResource(Resource theResource) {
            return new ResourcePropertyModel(theResource);
        }

        @objid ("8b171d73-2cab-490f-bfa3-1baa731fc8e0")
        @Override
        public Object visitResourceType(ResourceType theResourceType) {
            return new ResourceTypePropertyModel(theResourceType);
        }

        @objid ("94360462-2a6e-4102-8a2d-a51028b24ff8")
        @Override
        public Object visitStereotype(Stereotype theStereotype) {
            return new StereotypePropertyModel(theStereotype);
        }

        @objid ("0ac767bb-c41a-43f6-9580-61d5fc7a61db")
        @Override
        public Object visitTagType(TagType theTagType) {
            return new TagTypePropertyModel(theTagType);
        }

        @objid ("99e3e2eb-bdae-4b35-8cbf-392c50ef1d3f")
        @Override
        public Object visitTypedPropertyTable(TypedPropertyTable thePropertyValueSet) {
            return new TypedPropertyTablePropertyModel(thePropertyValueSet);
        }

    }

}
