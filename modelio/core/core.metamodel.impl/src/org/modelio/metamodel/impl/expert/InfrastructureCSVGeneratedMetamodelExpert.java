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
    @objid ("12704fba-209c-47aa-995f-8bba01fd09c1")
    public  InfrastructureCSVGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
        
        
    }

    /**
     * Initializes this expert.
     */
    @objid ("3e559050-1a74-4162-82ee-214dfc4e9772")
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
        registerMetaExpertForExternElement();
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

    @objid ("4888ea6e-5079-484f-b92a-976a372a9d60")
    protected void registerLinkExpertForImpactLink() {
        // Infrastructure.ImpactLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ImpactLink.class);
        
        // Infrastructure.ImpactLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ImpactLink.class, "dependsOn");
        this.ruleLinkExpert.addSourceDep(ImpactLink.class, "impacted");
        
        
        // Infrastructure.ImpactLink rules:
        
        
    }

    @objid ("f92beddb-1ec6-431d-a31f-9837e916aca4")
    protected void registerLinkExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Dependency.class);
        
        // Infrastructure.Dependency sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Dependency.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Dependency.class, "Impacted");
        
        
        // Infrastructure.Dependency rules:
        
        
    }

    @objid ("2ed6865b-4ea0-4137-8c57-fb10b3ec5bc9")
    protected void registerLinkExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(MethodologicalLink.class);
        
        // Infrastructure.MethodologicalLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(MethodologicalLink.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(MethodologicalLink.class, "Impacted");
        
        
        // Infrastructure.MethodologicalLink rules:
        
        
    }

    @objid ("f262aeca-b505-40ed-8253-530bbc86c3fa")
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

    @objid ("0567cb76-f6f9-4126-a471-593a57bb15cd")
    protected void registerMetaExpertForTagType() {
        // Infrastructure.TagType
        // -----------
        
    }

    @objid ("20d84a2f-b816-4365-8e4b-bcc2eebe3961")
    protected void registerMetaExpertForTagParameter() {
        // Infrastructure.TagParameter
        // -----------
        
    }

    @objid ("7f2296be-fb7b-4181-b3c2-70a711406dc0")
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

    @objid ("f56a0f0d-2c90-4b89-8e9b-1dad9f9455c8")
    protected void registerMetaExpertForResourceType() {
        // Infrastructure.ResourceType
        // -----------
        
    }

    @objid ("8f21a163-0435-4177-a680-d0f05383f576")
    protected void registerMetaExpertForResource() {
        // Infrastructure.Resource
        // -----------
        
    }

    @objid ("ce9ebdc1-bdcc-4028-ad9a-fce30b619e47")
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

    @objid ("1427a5e8-dc8f-4c96-9179-f92c6688b013")
    protected void registerMetaExpertForNoteType() {
        // Infrastructure.NoteType
        // -----------
        
    }

    @objid ("478f170b-d0a9-4aa0-9c06-a73daf6ffc98")
    protected void registerMetaExpertForNote() {
        // Infrastructure.Note
        // -----------
        
        // no constraint on Note.Model : NoteType from Infrastructure.Note to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Model");
        
    }

    @objid ("a3299bfe-e09d-443b-8ba3-b351941d3501")
    protected void registerMetaExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        // no constraint on MethodologicalLink.ExternElement : ExternElement from Infrastructure.MethodologicalLink to Infrastructure.ExternElement
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "ExternElement");
        
    }

    @objid ("2127acd9-7062-4760-8808-4cb5027446cd")
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

    @objid ("4f04aee3-cfab-418c-aeaf-ec9a2bd9b45d")
    protected void registerMetaExpertForExternProcessor() {
        // Infrastructure.ExternProcessor
        // -----------
        
    }

    @objid ("188b5030-5853-47eb-b202-0860422b782d")
    protected void registerMetaExpertForExternElement() {
        // Infrastructure.ExternElement
        // -----------
        
    }

    @objid ("442db487-1ee7-4d24-9f3f-4e6d3e4764f3")
    protected void registerMetaExpertForDocument() {
        // Infrastructure.Document
        // -----------
        
    }

    @objid ("e5297614-0ef9-476d-81cf-9d5d6f458475")
    protected void registerMetaExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        // no constraint on Dependency.DependsOn : ModelElement from Infrastructure.Dependency to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "DependsOn");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "DependsOn");
        
    }

    @objid ("5f54af00-ee13-4ebc-b1ee-a2b63f4cba16")
    protected void registerMetaExpertForAbstractResource() {
        // Infrastructure.AbstractResource
        // -----------
        
        // no constraint on AbstractResource.Type : ResourceType from Infrastructure.AbstractResource to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Type");
        
    }

    @objid ("af57682d-d730-432c-b55d-0e9d1a064c29")
    protected void registerMetaExpertForTypedPropertyTable() {
        // Infrastructure.TypedPropertyTable
        // -----------
        
        // no constraint on TypedPropertyTable.Type : PropertyTableDefinition from Infrastructure.TypedPropertyTable to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(TypedPropertyTable.class, null, "Type");
        
    }

    @objid ("ef9f0a99-4cb2-4984-84bb-c2d9a44897e2")
    protected void registerMetaExpertForPropertyTableDefinition() {
        // Infrastructure.PropertyTableDefinition
        // -----------
        
        // no constraint on PropertyTableDefinition.Owned : PropertyDefinition from Infrastructure.PropertyTableDefinition to Infrastructure.PropertyDefinition
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Owned");
        
    }

    @objid ("c62a6a69-1f26-435c-b9a6-cd94be6f1526")
    protected void registerMetaExpertForPropertyEnumerationLitteral() {
        // Infrastructure.PropertyEnumerationLitteral
        // -----------
        
    }

    @objid ("ecd40a6d-6d95-4cec-8a46-f2084ffd1c25")
    protected void registerMetaExpertForLocalPropertyTable() {
        // Infrastructure.LocalPropertyTable
        // -----------
        
        // no constraint on LocalPropertyTable.LocalAnnoted : ModelElement from Infrastructure.LocalPropertyTable to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(LocalPropertyTable.class, null, "LocalAnnoted");
        
    }

    @objid ("49b73a49-4e93-47ad-b87f-3b79790dd2d9")
    protected void registerMetaExpertForPropertyTable() {
        // Infrastructure.PropertyTable
        // -----------
        
    }

    @objid ("acbef413-1e05-4d4d-a098-2fa8255b55a7")
    protected void registerMetaExpertForEnumeratedPropertyType() {
        // Infrastructure.EnumeratedPropertyType
        // -----------
        
        // no constraint on EnumeratedPropertyType.Litteral : PropertyEnumerationLitteral from Infrastructure.EnumeratedPropertyType to Infrastructure.PropertyEnumerationLitteral
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Litteral");
        
    }

    @objid ("3230807c-c1d5-4c6b-a3f9-678219ecfe43")
    protected void registerMetaExpertForPropertyType() {
        // Infrastructure.PropertyType
        // -----------
        
    }

    @objid ("1d92e31f-c1de-43c7-80f0-14fd363c303b")
    protected void registerMetaExpertForDynamicPropertyDefinition() {
        // Infrastructure.DynamicPropertyDefinition
        // -----------
        
    }

    @objid ("da5511bd-53ab-41df-9596-d0d3430fdae7")
    protected void registerMetaExpertForPropertyDefinition() {
        // Infrastructure.PropertyDefinition
        // -----------
        
        // no constraint on PropertyDefinition.Type : PropertyType from Infrastructure.PropertyDefinition to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Type");
        
    }

    @objid ("0618ebbb-89c2-426c-8f19-450c9cd638ae")
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

    @objid ("de8cda2b-5579-4c07-a879-dc21c971b141")
    protected void registerMetaExpertForMatrixValueDefinition() {
        // Infrastructure.MatrixValueDefinition
        // -----------
        
        // no constraint on MatrixValueDefinition.Processor : ExternProcessor from Infrastructure.MatrixValueDefinition to Infrastructure.ExternProcessor
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Processor");
        
        // no constraint on MatrixValueDefinition.Parameters : PropertyTable from Infrastructure.MatrixValueDefinition to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Parameters");
        
    }

    @objid ("740d3473-6ea7-4783-a04d-2106ff23f79b")
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

    @objid ("6aeb9e48-d5e1-4425-951e-aeaa2a9f3c12")
    protected void registerMetaExpertForModuleParameter() {
        // Infrastructure.ModuleParameter
        // -----------
        
        // no constraint on ModuleParameter.EnumType : EnumeratedPropertyType from Infrastructure.ModuleParameter to Infrastructure.EnumeratedPropertyType
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "EnumType");
        
    }

    @objid ("8937bebe-c5e2-406e-a41f-574bf852f585")
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

    @objid ("0c6ba5dc-555a-4674-971f-60ff1904d9fd")
    protected void registerMetaExpertForImpactProject() {
        // Infrastructure.ImpactProject
        // -----------
        
        // no constraint on ImpactProject.model : ImpactModel from Infrastructure.ImpactProject to Infrastructure.ImpactModel
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "model");
        
    }

    @objid ("4a80f293-4f7a-41a9-a257-d6db350b5869")
    protected void registerMetaExpertForAbstractProject() {
        // Infrastructure.AbstractProject is abstract
        
        // -----------
        
        // no constraint on AbstractProject.DiagramRoot : DiagramSet from Infrastructure.AbstractProject to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DiagramRoot");
        
    }

    @objid ("24234c94-5af8-4fbd-ba9e-b5fe88971a83")
    protected void registerMetaExpertForImpactModel() {
        // Infrastructure.ImpactModel
        // -----------
        
        // ModelElement.Product: AbstractDiagram allowed from Infrastructure.ImpactModel to Infrastructure.ImpactDiagram by 'Product'{667eae04-e16e-403b-9db7-774bf9a97907} Standard.InformationFlow
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, ImpactDiagram.class, "Product");
        
    }

    @objid ("fbf6971c-d6eb-4263-a56c-861ac6a9937f")
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

    @objid ("e6ccedc7-d8ae-4a8e-a9f0-6f1a06616915")
    protected void registerMetaExpertForImpactDiagram() {
        // Infrastructure.ImpactDiagram
        // -----------
        
    }

    @objid ("52d5894c-a01e-4f23-8937-94f3bd568f2c")
    protected void registerMetaExpertForGraphDiagram() {
        // Infrastructure.GraphDiagram
        // -----------
        
    }

    @objid ("f699873a-9004-4e24-ac60-86a178cec29a")
    protected void registerMetaExpertForDiagramSet() {
        // Infrastructure.DiagramSet
        // -----------
        
        // no constraint on DiagramSet.Sub : DiagramSet from Infrastructure.DiagramSet to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Sub");
        
        // no constraint on DiagramSet.ReferencedDiagram : AbstractDiagram from Infrastructure.DiagramSet to Infrastructure.AbstractDiagram
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "ReferencedDiagram");
        
    }

    @objid ("6987f172-d3a3-4266-bde7-3795bb7010e9")
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

    @objid ("cb0b5c4e-882e-4be5-8625-9b9bd24f05b7")
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "Extension");
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "DependsOnDependency");
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "Tag");
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "Properties");
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "Descriptor");
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "Matrix");
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
        this.ruleMetaExpert.addDependencyRule(ExternElement.class, null, "Attached");
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

    @objid ("f4e37c37-4486-4a5b-8389-1cb83925335b")
    protected void registerMetaExpertForElement() {
        // Infrastructure.Element is abstract
        
        // -----------
        
    }

}
