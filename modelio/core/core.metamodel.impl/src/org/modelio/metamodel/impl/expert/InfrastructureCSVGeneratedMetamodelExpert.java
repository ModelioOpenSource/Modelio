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
     * 
     * @param mm The metamodel.
     */
    @objid ("28e91033-f344-493d-9ee1-d2bff4891a15")
    public InfrastructureCSVGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
    }

    /**
     * Initializes this expert.
     */
    @objid ("dc2315ba-6716-4497-9cd3-222398b66175")
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

    @objid ("aceccc6c-8533-4dcf-88fa-3b9ef8a567ae")
    protected void registerLinkExpertForImpactLink() {
        // Infrastructure.ImpactLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ImpactLink.class);
        
        // Infrastructure.ImpactLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ImpactLink.class, "dependsOn");
        this.ruleLinkExpert.addSourceDep(ImpactLink.class, "impacted");
        
        
        // Infrastructure.ImpactLink rules:
    }

    @objid ("7723f61f-36c1-4318-ae6c-b68281dd5c40")
    protected void registerLinkExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Dependency.class);
        
        // Infrastructure.Dependency sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Dependency.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Dependency.class, "Impacted");
        
        
        // Infrastructure.Dependency rules:
    }

    @objid ("4d31b33d-ae71-441f-b54b-5e828adb1d27")
    protected void registerLinkExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(MethodologicalLink.class);
        
        // Infrastructure.MethodologicalLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(MethodologicalLink.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(MethodologicalLink.class, "Impacted");
        
        
        // Infrastructure.MethodologicalLink rules:
    }

    @objid ("5b67751a-a074-4221-a152-f09b73bef4ac")
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

    @objid ("18f355b7-3ad3-45c2-a71c-958d8bdf07a9")
    protected void registerMetaExpertForTagType() {
        // Infrastructure.TagType
        // -----------
    }

    @objid ("4eef0223-f45a-4f11-a135-bf34e71fba25")
    protected void registerMetaExpertForTagParameter() {
        // Infrastructure.TagParameter
        // -----------
    }

    @objid ("588362b0-4163-46f8-880f-8d8c30592a75")
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

    @objid ("5bb3d962-8a65-47c4-94d8-cb274b467a27")
    protected void registerMetaExpertForResourceType() {
        // Infrastructure.ResourceType
        // -----------
    }

    @objid ("1ab68ecc-47fa-441c-8b6c-cb143a82f000")
    protected void registerMetaExpertForResource() {
        // Infrastructure.Resource
        // -----------
    }

    @objid ("4ac6c957-1811-4307-a67f-513b62a98034")
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

    @objid ("6b5394d9-e8ac-4bbc-91f4-66543571f03b")
    protected void registerMetaExpertForNoteType() {
        // Infrastructure.NoteType
        // -----------
    }

    @objid ("6a252608-a0e4-4772-aadc-5bc891c7d4c6")
    protected void registerMetaExpertForNote() {
        // Infrastructure.Note
        // -----------
        
        // no constraint on Note.Model : NoteType from Infrastructure.Note to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Model");
    }

    @objid ("55253099-e954-4e90-8843-3090a69cf491")
    protected void registerMetaExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        // no constraint on MethodologicalLink.ExternElement : ExternElement from Infrastructure.MethodologicalLink to Infrastructure.ExternElement
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "ExternElement");
    }

    @objid ("e8b8df62-1de7-49b8-b0ce-01cc41ad60e0")
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

    @objid ("1e20da5e-a04f-45ac-bf7f-f2f54f064ff2")
    protected void registerMetaExpertForExternProcessor() {
        // Infrastructure.ExternProcessor
        // -----------
    }

    @objid ("7b85cf2e-5e52-4e30-88ce-3c1877870c70")
    protected void registerMetaExpertForExternElement() {
        // Infrastructure.ExternElement
        // -----------
    }

    @objid ("6a90b048-a924-49b3-afec-31fdd3d084b3")
    protected void registerMetaExpertForDocument() {
        // Infrastructure.Document
        // -----------
    }

    @objid ("5c24b9ca-38a4-483e-8fd0-581b0c501b36")
    protected void registerMetaExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        // no constraint on Dependency.DependsOn : ModelElement from Infrastructure.Dependency to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "DependsOn");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "DependsOn");
    }

    @objid ("d3ae9055-3c4b-4495-928c-039f98583d71")
    protected void registerMetaExpertForAbstractResource() {
        // Infrastructure.AbstractResource
        // -----------
        
        // no constraint on AbstractResource.Type : ResourceType from Infrastructure.AbstractResource to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Type");
    }

    @objid ("a934561b-fa3b-4472-9ce4-f97f21ec449d")
    protected void registerMetaExpertForTypedPropertyTable() {
        // Infrastructure.TypedPropertyTable
        // -----------
        
        // no constraint on TypedPropertyTable.Type : PropertyTableDefinition from Infrastructure.TypedPropertyTable to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(TypedPropertyTable.class, null, "Type");
    }

    @objid ("a80c1867-7554-4f42-be82-1cf3f6cbc999")
    protected void registerMetaExpertForPropertyTableDefinition() {
        // Infrastructure.PropertyTableDefinition
        // -----------
        
        // no constraint on PropertyTableDefinition.Owned : PropertyDefinition from Infrastructure.PropertyTableDefinition to Infrastructure.PropertyDefinition
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Owned");
    }

    @objid ("4f868fbe-8120-4001-bc75-6dc2c722c0ab")
    protected void registerMetaExpertForPropertyEnumerationLitteral() {
        // Infrastructure.PropertyEnumerationLitteral
        // -----------
    }

    @objid ("dcfa7df0-46c7-46cb-a8fa-514fdb324747")
    protected void registerMetaExpertForLocalPropertyTable() {
        // Infrastructure.LocalPropertyTable
        // -----------
        
        // no constraint on LocalPropertyTable.LocalAnnoted : ModelElement from Infrastructure.LocalPropertyTable to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(LocalPropertyTable.class, null, "LocalAnnoted");
    }

    @objid ("ea6fdef1-3a3c-4ce9-a58a-0d32d8c595a4")
    protected void registerMetaExpertForPropertyTable() {
        // Infrastructure.PropertyTable
        // -----------
    }

    @objid ("49438791-b499-41be-8343-2e6eb90ddef8")
    protected void registerMetaExpertForEnumeratedPropertyType() {
        // Infrastructure.EnumeratedPropertyType
        // -----------
        
        // no constraint on EnumeratedPropertyType.Litteral : PropertyEnumerationLitteral from Infrastructure.EnumeratedPropertyType to Infrastructure.PropertyEnumerationLitteral
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Litteral");
    }

    @objid ("5a052959-2d8e-4dc4-9cc6-12cdce5072de")
    protected void registerMetaExpertForPropertyType() {
        // Infrastructure.PropertyType
        // -----------
    }

    @objid ("72391515-77c0-4710-91f8-b2387f7c9084")
    protected void registerMetaExpertForDynamicPropertyDefinition() {
        // Infrastructure.DynamicPropertyDefinition
        // -----------
    }

    @objid ("e753ff37-b136-4903-99b1-1c3f24f24e73")
    protected void registerMetaExpertForPropertyDefinition() {
        // Infrastructure.PropertyDefinition
        // -----------
        
        // no constraint on PropertyDefinition.Type : PropertyType from Infrastructure.PropertyDefinition to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Type");
    }

    @objid ("f9f10959-b4e2-4f98-9292-907f5282bd37")
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

    @objid ("40d44528-2e3f-43e6-9404-71fe25874e1d")
    protected void registerMetaExpertForMatrixValueDefinition() {
        // Infrastructure.MatrixValueDefinition
        // -----------
        
        // no constraint on MatrixValueDefinition.Processor : ExternProcessor from Infrastructure.MatrixValueDefinition to Infrastructure.ExternProcessor
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Processor");
        
        // no constraint on MatrixValueDefinition.Parameters : PropertyTable from Infrastructure.MatrixValueDefinition to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Parameters");
    }

    @objid ("cf62eed8-ddbd-4f2c-b700-4b77bc333432")
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

    @objid ("75f64849-08e1-450e-92e4-3d662d81e2fd")
    protected void registerMetaExpertForModuleParameter() {
        // Infrastructure.ModuleParameter
        // -----------
        
        // no constraint on ModuleParameter.EnumType : EnumeratedPropertyType from Infrastructure.ModuleParameter to Infrastructure.EnumeratedPropertyType
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "EnumType");
    }

    @objid ("59b4a5e0-172a-44f2-9678-c1b50b459b7c")
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

    @objid ("509e5c66-2cd0-4492-b238-97f0154973b4")
    protected void registerMetaExpertForImpactProject() {
        // Infrastructure.ImpactProject
        // -----------
        
        // no constraint on ImpactProject.model : ImpactModel from Infrastructure.ImpactProject to Infrastructure.ImpactModel
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "model");
    }

    @objid ("eb071fdd-cd53-479f-b7a5-8e691877880d")
    protected void registerMetaExpertForAbstractProject() {
        // Infrastructure.AbstractProject is abstract
        
        // -----------
        
        // no constraint on AbstractProject.DiagramRoot : DiagramSet from Infrastructure.AbstractProject to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DiagramRoot");
    }

    @objid ("a08ab437-7d27-424b-bbcf-22582c2ce623")
    protected void registerMetaExpertForImpactModel() {
        // Infrastructure.ImpactModel
        // -----------
        
        // ModelElement.Product: AbstractDiagram allowed from Infrastructure.ImpactModel to Infrastructure.ImpactDiagram by 'Product'{667eae04-e16e-403b-9db7-774bf9a97907} Standard.InformationFlow
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, ImpactDiagram.class, "Product");
    }

    @objid ("f9c1f58b-87f6-4a69-89d3-8f5812009351")
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

    @objid ("680b44ad-c092-45ea-9a93-e0a69b973996")
    protected void registerMetaExpertForImpactDiagram() {
        // Infrastructure.ImpactDiagram
        // -----------
    }

    @objid ("b3b9759c-53b6-4b02-87dc-8d2c71cf3a4a")
    protected void registerMetaExpertForGraphDiagram() {
        // Infrastructure.GraphDiagram
        // -----------
    }

    @objid ("9f432854-515b-4950-bffb-188559eb113c")
    protected void registerMetaExpertForDiagramSet() {
        // Infrastructure.DiagramSet
        // -----------
        
        // no constraint on DiagramSet.Sub : DiagramSet from Infrastructure.DiagramSet to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Sub");
        
        // no constraint on DiagramSet.ReferencedDiagram : AbstractDiagram from Infrastructure.DiagramSet to Infrastructure.AbstractDiagram
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "ReferencedDiagram");
    }

    @objid ("20f0a7ac-8d2a-4031-a92b-7495fdd1a035")
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

    @objid ("e82090e0-88cb-470e-a870-d1189d3b1392")
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

    @objid ("0575256a-71b9-4b3a-8640-15de75833fc3")
    protected void registerMetaExpertForElement() {
        // Infrastructure.Element is abstract
        
        // -----------
    }

}
