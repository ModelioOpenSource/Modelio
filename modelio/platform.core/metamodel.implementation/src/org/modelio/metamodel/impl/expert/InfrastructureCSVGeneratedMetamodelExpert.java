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

package org.modelio.metamodel.impl.expert;

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
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.utils.metamodel.experts.CompositeMetamodelExpert;

/**
 * This is an automatically generated metamodel expert.
 * <p>
 * All modifications will be overwritten. If you need to modify it, subclass this class instead.
 * <p>
 * You need to call {@link #register()} after instantiation to initialize the expert from the metamodel.
 */
@objid ("69dd187f-e0d3-477d-86ef-566a71532d29")
public class InfrastructureCSVGeneratedMetamodelExpert extends CompositeMetamodelExpert {
    /**
     * Constructor.
     * <p>
     * You need to call {@link #register()} next.
     * @param mm The metamodel.
     */
    @objid ("475201a1-752c-444f-bfbb-c6809f19932c")
    public InfrastructureCSVGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
    }

    /**
     * Initializes this expert.
     */
    @objid ("d818e4e6-ed28-40e4-b549-5ccc5bd72efd")
    public void register() {
        registerLinkExpertForImpactLink();
        registerLinkExpertForDependency();
        registerLinkExpertForMethodologicalLink();
        registerMetaExpertForTaggedValue();
        registerMetaExpertForTagType();
        registerMetaExpertForTagParameter();
        registerMetaExpertForStereotype();
        registerMetaExpertForResourceType();
        registerMetaExpertForResource();
        registerMetaExpertForProfile();
        registerMetaExpertForNoteType();
        registerMetaExpertForNote();
        registerMetaExpertForMethodologicalLink();
        registerMetaExpertForMetaclassReference();
        registerMetaExpertForExternProcessor();
        registerMetaExpertForDocument();
        registerMetaExpertForDependency();
        registerMetaExpertForAbstractResource();
        registerMetaExpertForTypedPropertyTable();
        registerMetaExpertForPropertyTableDefinition();
        registerMetaExpertForPropertyEnumerationLitteral();
        registerMetaExpertForLocalPropertyTable();
        registerMetaExpertForPropertyTable();
        registerMetaExpertForEnumeratedPropertyType();
        registerMetaExpertForPropertyType();
        registerMetaExpertForDynamicPropertyDefinition();
        registerMetaExpertForPropertyDefinition();
        registerMetaExpertForQueryDefinition();
        registerMetaExpertForMatrixValueDefinition();
        registerMetaExpertForMatrixDefinition();
        registerMetaExpertForModuleParameter();
        registerMetaExpertForModuleComponent();
        registerMetaExpertForImpactProject();
        registerMetaExpertForAbstractProject();
        registerMetaExpertForImpactModel();
        registerMetaExpertForImpactLink();
        registerMetaExpertForImpactDiagram();
        registerMetaExpertForGraphDiagram();
        registerMetaExpertForDiagramSet();
        registerMetaExpertForAbstractDiagram();
        registerMetaExpertForModelElement();
        registerMetaExpertForElement();
    }

    @objid ("3a0f9fd9-2c9f-4a48-94e6-a7f6e1b50464")
    protected void registerLinkExpertForImpactLink() {
        // Infrastructure.ImpactLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ImpactLink.class);
        
        // Infrastructure.ImpactLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ImpactLink.class, "dependsOn");
        this.ruleLinkExpert.addSourceDep(ImpactLink.class, "impacted");
        
        
        // Infrastructure.ImpactLink rules:
    }

    @objid ("1a43a632-0647-4db3-bdf4-e1b092e00810")
    protected void registerLinkExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Dependency.class);
        
        // Infrastructure.Dependency sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Dependency.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Dependency.class, "Impacted");
        
        
        // Infrastructure.Dependency rules:
    }

    @objid ("a412958d-2d4f-4ed0-839d-da569dbcf847")
    protected void registerLinkExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(MethodologicalLink.class);
        
        // Infrastructure.MethodologicalLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(MethodologicalLink.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(MethodologicalLink.class, "Impacted");
        
        
        // Infrastructure.MethodologicalLink rules:
    }

    @objid ("c22e684e-7065-4514-8fd5-91254988fe29")
    protected void registerMetaExpertForTaggedValue() {
        // Infrastructure.TaggedValue
        // -----------
        
        // no constraint on TaggedValue.Actual : TagParameter from Infrastructure.TaggedValue to Infrastructure.TagParameter
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Actual");
        
        // no constraint on TaggedValue.Qualifier : TagParameter from Infrastructure.TaggedValue to Infrastructure.TagParameter
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Qualifier");
        
        // no constraint on TaggedValue.Definition : TagType from Infrastructure.TaggedValue to Infrastructure.TagType
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Definition");
    }

    @objid ("1b2b4c49-8639-4bf8-8f28-1ee82bf51aad")
    protected void registerMetaExpertForTagType() {
        // Infrastructure.TagType
        // -----------
    }

    @objid ("0a4adc3d-1edf-4e5c-b169-bfcc6edc19fc")
    protected void registerMetaExpertForTagParameter() {
        // Infrastructure.TagParameter
        // -----------
    }

    @objid ("2e01eb9f-9030-4971-b349-067c10067832")
    protected void registerMetaExpertForStereotype() {
        // Infrastructure.Stereotype
        // -----------
        
        // no constraint on Stereotype.DefinedTable : PropertyTableDefinition from Infrastructure.Stereotype to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "DefinedTable");
        
        // no constraint on Stereotype.DefinedResourceType : ResourceType from Infrastructure.Stereotype to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "DefinedResourceType");
        
        // no constraint on Stereotype.Parent : Stereotype from Infrastructure.Stereotype to Infrastructure.Stereotype
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Parent");
        
        // no constraint on Stereotype.DefinedTagType : TagType from Infrastructure.Stereotype to Infrastructure.TagType
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "DefinedTagType");
        
        // no constraint on Stereotype.DefinedNoteType : NoteType from Infrastructure.Stereotype to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "DefinedNoteType");
    }

    @objid ("3e9c1509-71d2-469b-81d0-c85f1b7f7c4e")
    protected void registerMetaExpertForResourceType() {
        // Infrastructure.ResourceType
        // -----------
    }

    @objid ("f29d5960-2feb-4e2a-9499-6e0640b2e98e")
    protected void registerMetaExpertForResource() {
        // Infrastructure.Resource
        // -----------
    }

    @objid ("5aa3e341-dc4f-45ba-ada7-72a6575cc44b")
    protected void registerMetaExpertForProfile() {
        // Infrastructure.Profile
        // -----------
        
        // no constraint on Profile.DefinedStereotype : Stereotype from Infrastructure.Profile to Infrastructure.Stereotype
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "DefinedStereotype");
        
        // no constraint on Profile.OwnedReference : MetaclassReference from Infrastructure.Profile to Infrastructure.MetaclassReference
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "OwnedReference");
        
        // no constraint on Profile.DefinedType : PropertyType from Infrastructure.Profile to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "DefinedType");
    }

    @objid ("a999654d-52fc-41f5-a8e5-e8e2c50df619")
    protected void registerMetaExpertForNoteType() {
        // Infrastructure.NoteType
        // -----------
    }

    @objid ("98d2b82e-f546-4815-8b42-cbf4ab43e873")
    protected void registerMetaExpertForNote() {
        // Infrastructure.Note
        // -----------
        
        // no constraint on Note.Model : NoteType from Infrastructure.Note to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Model");
    }

    @objid ("20649f12-d859-4ae6-98e9-df3fdddc3f93")
    protected void registerMetaExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
    }

    @objid ("f7e26143-1de7-467c-ae72-16ca8d0b899f")
    protected void registerMetaExpertForMetaclassReference() {
        // Infrastructure.MetaclassReference
        // -----------
        
        // no constraint on MetaclassReference.DefinedTable : PropertyTableDefinition from Infrastructure.MetaclassReference to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(MetaclassReference.class, null, "DefinedTable");
        
        // no constraint on MetaclassReference.DefinedNoteType : NoteType from Infrastructure.MetaclassReference to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(MetaclassReference.class, null, "DefinedNoteType");
        
        // no constraint on MetaclassReference.DefinedResourceType : ResourceType from Infrastructure.MetaclassReference to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(MetaclassReference.class, null, "DefinedResourceType");
        
        // no constraint on MetaclassReference.DefinedTagType : TagType from Infrastructure.MetaclassReference to Infrastructure.TagType
        this.ruleMetaExpert.addDependencyRule(MetaclassReference.class, null, "DefinedTagType");
    }

    @objid ("934e1e92-4d64-46af-a8dd-c8758e4f1444")
    protected void registerMetaExpertForExternProcessor() {
        // Infrastructure.ExternProcessor
        // -----------
    }

    @objid ("23719f26-559b-4527-a440-08835e70af4c")
    protected void registerMetaExpertForDocument() {
        // Infrastructure.Document
        // -----------
    }

    @objid ("a189c17f-5177-434b-9789-4a983d13e5fe")
    protected void registerMetaExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        // no constraint on Dependency.DependsOn : ModelElement from Infrastructure.Dependency to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "DependsOn");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "DependsOn");
    }

    @objid ("b32d2a10-9eca-4f94-ad80-f4d455a4dd3f")
    protected void registerMetaExpertForAbstractResource() {
        // Infrastructure.AbstractResource
        // -----------
        
        // no constraint on AbstractResource.Type : ResourceType from Infrastructure.AbstractResource to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Type");
    }

    @objid ("c894fa4e-418f-4bc2-9c58-614b4e39ef22")
    protected void registerMetaExpertForTypedPropertyTable() {
        // Infrastructure.TypedPropertyTable
        // -----------
        
        // no constraint on TypedPropertyTable.Type : PropertyTableDefinition from Infrastructure.TypedPropertyTable to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(TypedPropertyTable.class, null, "Type");
    }

    @objid ("ed7903ff-1e89-40b5-ab7d-bc95e3a42c86")
    protected void registerMetaExpertForPropertyTableDefinition() {
        // Infrastructure.PropertyTableDefinition
        // -----------
        
        // no constraint on PropertyTableDefinition.Owned : PropertyDefinition from Infrastructure.PropertyTableDefinition to Infrastructure.PropertyDefinition
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Owned");
    }

    @objid ("1aafbea3-4f18-4918-815b-d6d2cf768721")
    protected void registerMetaExpertForPropertyEnumerationLitteral() {
        // Infrastructure.PropertyEnumerationLitteral
        // -----------
    }

    @objid ("5b5b9055-d503-41c9-b2eb-c08e6c6ad9f0")
    protected void registerMetaExpertForLocalPropertyTable() {
        // Infrastructure.LocalPropertyTable
        // -----------
        
        // no constraint on LocalPropertyTable.LocalAnnoted : ModelElement from Infrastructure.LocalPropertyTable to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(LocalPropertyTable.class, null, "LocalAnnoted");
    }

    @objid ("e072148a-d65c-483e-b963-232f8d64dd51")
    protected void registerMetaExpertForPropertyTable() {
        // Infrastructure.PropertyTable
        // -----------
    }

    @objid ("dbc30d79-6cf8-4e58-b727-75ce60897590")
    protected void registerMetaExpertForEnumeratedPropertyType() {
        // Infrastructure.EnumeratedPropertyType
        // -----------
        
        // no constraint on EnumeratedPropertyType.Litteral : PropertyEnumerationLitteral from Infrastructure.EnumeratedPropertyType to Infrastructure.PropertyEnumerationLitteral
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Litteral");
    }

    @objid ("06922dc3-0bdb-42b3-b8ce-07462bdf91f3")
    protected void registerMetaExpertForPropertyType() {
        // Infrastructure.PropertyType
        // -----------
    }

    @objid ("2b5b9555-f0ca-4227-a9c6-51d5668b2f92")
    protected void registerMetaExpertForDynamicPropertyDefinition() {
        // Infrastructure.DynamicPropertyDefinition
        // -----------
    }

    @objid ("7f849c9d-0819-4d6f-8eae-773f7b70bae1")
    protected void registerMetaExpertForPropertyDefinition() {
        // Infrastructure.PropertyDefinition
        // -----------
        
        // no constraint on PropertyDefinition.Type : PropertyType from Infrastructure.PropertyDefinition to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Type");
    }

    @objid ("f13fecbf-2d1e-44c7-94e5-12c8a83a145b")
    protected void registerMetaExpertForQueryDefinition() {
        // Infrastructure.QueryDefinition
        // -----------
        
        // no constraint on QueryDefinition.Added : Element from Infrastructure.QueryDefinition to Infrastructure.Element
        this.ruleMetaExpert.addDependencyRule(QueryDefinition.class, null, "Added");
        
        // no constraint on QueryDefinition.Processor : ExternProcessor from Infrastructure.QueryDefinition to Infrastructure.ExternProcessor
        this.ruleMetaExpert.addDependencyRule(QueryDefinition.class, null, "Processor");
        
        // no constraint on QueryDefinition.Parameters : PropertyTable from Infrastructure.QueryDefinition to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(QueryDefinition.class, null, "Parameters");
    }

    @objid ("c991b0e4-9779-464c-a71c-359c033db6ed")
    protected void registerMetaExpertForMatrixValueDefinition() {
        // Infrastructure.MatrixValueDefinition
        // -----------
        
        // no constraint on MatrixValueDefinition.Processor : ExternProcessor from Infrastructure.MatrixValueDefinition to Infrastructure.ExternProcessor
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Processor");
        
        // no constraint on MatrixValueDefinition.Parameters : PropertyTable from Infrastructure.MatrixValueDefinition to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Parameters");
    }

    @objid ("f6195ac0-06f9-4a94-a9e2-d379f11c37e9")
    protected void registerMetaExpertForMatrixDefinition() {
        // Infrastructure.MatrixDefinition
        // -----------
        
        // no constraint on MatrixDefinition.LinesDefinition : QueryDefinition from Infrastructure.MatrixDefinition to Infrastructure.QueryDefinition
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "LinesDefinition");
        
        // no constraint on MatrixDefinition.ColumnsDefinition : QueryDefinition from Infrastructure.MatrixDefinition to Infrastructure.QueryDefinition
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "ColumnsDefinition");
        
        // no constraint on MatrixDefinition.ValuesDefinition : MatrixValueDefinition from Infrastructure.MatrixDefinition to Infrastructure.MatrixValueDefinition
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "ValuesDefinition");
        
        // no constraint on MatrixDefinition.DepthDefinition : QueryDefinition from Infrastructure.MatrixDefinition to Infrastructure.QueryDefinition
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "DepthDefinition");
    }

    @objid ("c7eafa03-dc3f-457e-9c5d-7a462b2cb815")
    protected void registerMetaExpertForModuleParameter() {
        // Infrastructure.ModuleParameter
        // -----------
        
        // no constraint on ModuleParameter.EnumType : EnumeratedPropertyType from Infrastructure.ModuleParameter to Infrastructure.EnumeratedPropertyType
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "EnumType");
    }

    @objid ("6027497d-a1a6-4532-93bc-d40d48bd43e0")
    protected void registerMetaExpertForModuleComponent() {
        // Infrastructure.ModuleComponent
        // -----------
        
        // no constraint on ModuleComponent.DefinedPropertyType : PropertyType from Infrastructure.ModuleComponent to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DefinedPropertyType");
        
        // no constraint on ModuleComponent.OwnedProfile : Profile from Infrastructure.ModuleComponent to Infrastructure.Profile
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "OwnedProfile");
        
        // no constraint on ModuleComponent.ModuleParameter : ModuleParameter from Infrastructure.ModuleComponent to Infrastructure.ModuleParameter
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "ModuleParameter");
        
        // no constraint on ModuleComponent.DependsOn : ModuleComponent from Infrastructure.ModuleComponent to Infrastructure.ModuleComponent
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DependsOn");
    }

    @objid ("e13e98a8-84f8-4c07-a79d-1bbf8bf4c313")
    protected void registerMetaExpertForImpactProject() {
        // Infrastructure.ImpactProject
        // -----------
        
        // no constraint on ImpactProject.model : ImpactModel from Infrastructure.ImpactProject to Infrastructure.ImpactModel
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "model");
    }

    @objid ("42433ed7-d3f6-4700-ac25-fb0df53eb8c8")
    protected void registerMetaExpertForAbstractProject() {
        // Infrastructure.AbstractProject is abstract
        
        // -----------
        
        // no constraint on AbstractProject.DiagramRoot : DiagramSet from Infrastructure.AbstractProject to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DiagramRoot");
    }

    @objid ("b8cee0f3-e9a6-4402-a06a-115779756c1c")
    protected void registerMetaExpertForImpactModel() {
        // Infrastructure.ImpactModel
        // -----------
        
        // ModelElement.Product: AbstractDiagram allowed from Infrastructure.ImpactModel to Infrastructure.ImpactDiagram by 'Product'{667eae04-e16e-403b-9db7-774bf9a97907} Standard.InformationFlow
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, ImpactDiagram.class, "Product");
    }

    @objid ("c4045322-923b-4d09-92cc-6e0be12f3e8b")
    protected void registerMetaExpertForImpactLink() {
        // Infrastructure.ImpactLink
        // -----------
        
        // no constraint on ImpactLink.dependsOn : ModelElement from Infrastructure.ImpactLink to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "dependsOn");
        
        // no constraint on ImpactLink.impacted : ModelElement from Infrastructure.ImpactLink to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "impacted");
        
        // no constraint on ImpactLink.causes : Element from Infrastructure.ImpactLink to Infrastructure.Element
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "causes");
        
        // no constraint on ImpactLink.owner : ImpactModel from Infrastructure.ImpactLink to Infrastructure.ImpactModel
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "owner");
    }

    @objid ("44e9b2b3-c203-4a09-8743-49af13781454")
    protected void registerMetaExpertForImpactDiagram() {
        // Infrastructure.ImpactDiagram
        // -----------
    }

    @objid ("3d6f1f66-69ff-4a09-ba56-55106fc45806")
    protected void registerMetaExpertForGraphDiagram() {
        // Infrastructure.GraphDiagram
        // -----------
    }

    @objid ("86b7a17f-15ca-422d-9fa0-ea925e9e828f")
    protected void registerMetaExpertForDiagramSet() {
        // Infrastructure.DiagramSet
        // -----------
        
        // no constraint on DiagramSet.Sub : DiagramSet from Infrastructure.DiagramSet to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Sub");
        
        // no constraint on DiagramSet.ReferencedDiagram : AbstractDiagram from Infrastructure.DiagramSet to Infrastructure.AbstractDiagram
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "ReferencedDiagram");
    }

    @objid ("da8e29ca-a089-4e0b-8f8d-fb6e42113f7e")
    protected void registerMetaExpertForAbstractDiagram() {
        // Infrastructure.AbstractDiagram is abstract
        
        // -----------
        
        // no constraint on AbstractDiagram.Represented : Element from Infrastructure.AbstractDiagram to Infrastructure.Element
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Represented");
        
        // no constraint on AbstractDiagram.Origin : ModelElement from Infrastructure.AbstractDiagram to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Origin");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Origin");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Origin");
    }

    @objid ("d31cf558-ddff-4250-a216-ec6ce30812fa")
    protected void registerMetaExpertForModelElement() {
        // Infrastructure.ModelElement is abstract
        
        // -----------
        
        // no constraint on ModelElement.Extension : Stereotype from Infrastructure.ModelElement to Infrastructure.Stereotype
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "Extension");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Extension");
        
        // no constraint on ModelElement.DependsOnDependency : Dependency from Infrastructure.ModelElement to Infrastructure.Dependency
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "DependsOnDependency");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "DependsOnDependency");
        
        // no constraint on ModelElement.Tag : TaggedValue from Infrastructure.ModelElement to Infrastructure.TaggedValue
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "Tag");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Tag");
        
        // no constraint on ModelElement.Properties : PropertyTable from Infrastructure.ModelElement to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "Properties");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Properties");
        
        // no constraint on ModelElement.Descriptor : Note from Infrastructure.ModelElement to Infrastructure.Note
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "Descriptor");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Descriptor");
        
        // no constraint on ModelElement.Matrix : MatrixDefinition from Infrastructure.ModelElement to Infrastructure.MatrixDefinition
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "Matrix");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Matrix");
        
        // no constraint on ModelElement.Attached : AbstractResource from Infrastructure.ModelElement to Infrastructure.AbstractResource
        this.ruleMetaExpert.addDependencyRule(AbstractDiagram.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ExternProcessor.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(GraphDiagram.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ImpactDiagram.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ImpactLink.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(MatrixDefinition.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ModelElement.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(NoteType.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(Profile.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(PropertyEnumerationLitteral.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(PropertyType.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(ResourceType.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(Stereotype.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(TagType.class, null, "Attached");
        this.ruleMetaExpert.addDependencyRule(TaggedValue.class, null, "Attached");
    }

    @objid ("82bd7b18-57cc-4a8d-97e7-f1b7d33818a4")
    protected void registerMetaExpertForElement() {
        // Infrastructure.Element is abstract
        
        // -----------
    }

}
