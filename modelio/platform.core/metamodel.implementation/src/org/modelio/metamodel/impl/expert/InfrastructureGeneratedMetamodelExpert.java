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
@objid ("51b1a926-c287-4791-978e-7fef2de23bd3")
public class InfrastructureGeneratedMetamodelExpert extends CompositeMetamodelExpert {
    /**
     * Constructor.
     * <p>
     * You need to call {@link #register()} next.
     * @param mm The metamodel.
     */
    @objid ("3e775f95-fa79-4bb0-8833-80509165ade2")
    public InfrastructureGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
    }

    /**
     * Initializes this expert.
     */
    @objid ("2093be64-f0bc-498a-a296-a01e4f24ab71")
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

    @objid ("01a9e9b9-90fd-4f19-a766-239a3c8a4720")
    protected void registerLinkExpertForImpactLink() {
        // Infrastructure.ImpactLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ImpactLink.class);
        
        // Infrastructure.ImpactLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ImpactLink.class, "dependsOn");
        this.ruleLinkExpert.addSourceDep(ImpactLink.class, "impacted");
        
        
        // Infrastructure.ImpactLink rules: all allowed.
        
        this.ruleLinkExpert.addRule(ImpactLink.class, null, null);
    }

    @objid ("ff9dcb97-ca32-4041-a578-bf4dffc17f18")
    protected void registerLinkExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Dependency.class);
        
        // Infrastructure.Dependency sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Dependency.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Dependency.class, "Impacted");
        
        
        // Infrastructure.Dependency rules: all allowed.
        
        this.ruleLinkExpert.addRule(Dependency.class, null, null);
    }

    @objid ("92f67088-ae55-4893-82a2-78bfd8ce6aaf")
    protected void registerLinkExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(MethodologicalLink.class);
        
        // Infrastructure.MethodologicalLink sources and target dependencies
        this.ruleLinkExpert.addTargetDep(MethodologicalLink.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(MethodologicalLink.class, "Impacted");
        
        
        // Infrastructure.MethodologicalLink rules: all allowed.
        
        this.ruleLinkExpert.addRule(MethodologicalLink.class, null, null);
    }

    @objid ("c30ae38f-354b-4729-aa27-04a2a21d0ffa")
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

    @objid ("753daa08-69e9-4d9e-b1ef-661903ada8bd")
    protected void registerMetaExpertForTagType() {
        // Infrastructure.TagType
        // -----------
    }

    @objid ("b1b6ebf3-ac5c-4bdb-998d-e84ddb1937ec")
    protected void registerMetaExpertForTagParameter() {
        // Infrastructure.TagParameter
        // -----------
    }

    @objid ("37238262-875f-4f96-a8cf-51d06ef3f31c")
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

    @objid ("a2b33c2a-fb1a-4571-abe1-ae178c02e074")
    protected void registerMetaExpertForResourceType() {
        // Infrastructure.ResourceType
        // -----------
    }

    @objid ("71147037-e09c-4d50-9168-8ba6812afd18")
    protected void registerMetaExpertForResource() {
        // Infrastructure.Resource
        // -----------
    }

    @objid ("4f56b6a6-fc42-4375-a2bd-d40a211a40e1")
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

    @objid ("325f428e-b080-4c29-aa75-8fafab8d0099")
    protected void registerMetaExpertForNoteType() {
        // Infrastructure.NoteType
        // -----------
    }

    @objid ("b99015ab-3a75-4a60-bcb0-31ea9c035d07")
    protected void registerMetaExpertForNote() {
        // Infrastructure.Note
        // -----------
        
        // no constraint on Note.Model : NoteType from Infrastructure.Note to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Model");
    }

    @objid ("540a5bf9-3c82-4e82-ace0-8fffb010f259")
    protected void registerMetaExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
    }

    @objid ("cba34968-1f8b-4242-9a3b-ad78227bc26f")
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

    @objid ("74cfa0c0-887c-4df1-adba-b9eef52d2c97")
    protected void registerMetaExpertForExternProcessor() {
        // Infrastructure.ExternProcessor
        // -----------
    }

    @objid ("7fdc703d-0287-4b0e-9b09-0eea195f53b2")
    protected void registerMetaExpertForDocument() {
        // Infrastructure.Document
        // -----------
    }

    @objid ("e58b3013-a9eb-4237-8159-0e141dea7222")
    protected void registerMetaExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        // no constraint on Dependency.DependsOn : ModelElement from Infrastructure.Dependency to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "DependsOn");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "DependsOn");
    }

    @objid ("0e98e6ce-1ab0-4d9c-a753-e3d9c277e50b")
    protected void registerMetaExpertForAbstractResource() {
        // Infrastructure.AbstractResource
        // -----------
        
        // no constraint on AbstractResource.Type : ResourceType from Infrastructure.AbstractResource to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Type");
    }

    @objid ("2784b67c-567a-4683-89ae-80c529e075e8")
    protected void registerMetaExpertForTypedPropertyTable() {
        // Infrastructure.TypedPropertyTable
        // -----------
        
        // no constraint on TypedPropertyTable.Type : PropertyTableDefinition from Infrastructure.TypedPropertyTable to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(TypedPropertyTable.class, null, "Type");
    }

    @objid ("f9a97e96-06d9-4061-aa35-d78c63ff0062")
    protected void registerMetaExpertForPropertyTableDefinition() {
        // Infrastructure.PropertyTableDefinition
        // -----------
        
        // no constraint on PropertyTableDefinition.Owned : PropertyDefinition from Infrastructure.PropertyTableDefinition to Infrastructure.PropertyDefinition
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Owned");
    }

    @objid ("4d28387f-7e9a-427f-b5f1-d4ba41903de1")
    protected void registerMetaExpertForPropertyEnumerationLitteral() {
        // Infrastructure.PropertyEnumerationLitteral
        // -----------
    }

    @objid ("87ee66bb-78ed-407b-8184-e87288c7d799")
    protected void registerMetaExpertForLocalPropertyTable() {
        // Infrastructure.LocalPropertyTable
        // -----------
        
        // no constraint on LocalPropertyTable.LocalAnnoted : ModelElement from Infrastructure.LocalPropertyTable to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(LocalPropertyTable.class, null, "LocalAnnoted");
    }

    @objid ("2eb7ca94-fc5c-4066-bad8-c41cf6d41af7")
    protected void registerMetaExpertForPropertyTable() {
        // Infrastructure.PropertyTable
        // -----------
    }

    @objid ("64c052f0-5742-42e0-8ba6-5712be022058")
    protected void registerMetaExpertForEnumeratedPropertyType() {
        // Infrastructure.EnumeratedPropertyType
        // -----------
        
        // no constraint on EnumeratedPropertyType.Litteral : PropertyEnumerationLitteral from Infrastructure.EnumeratedPropertyType to Infrastructure.PropertyEnumerationLitteral
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Litteral");
    }

    @objid ("b2dcb118-1819-492b-9464-c463ff5c592f")
    protected void registerMetaExpertForPropertyType() {
        // Infrastructure.PropertyType
        // -----------
    }

    @objid ("a153d145-e0cd-48d0-94f6-ed9ce74d83b3")
    protected void registerMetaExpertForDynamicPropertyDefinition() {
        // Infrastructure.DynamicPropertyDefinition
        // -----------
    }

    @objid ("eb06ba88-f8b6-4e25-ba18-86100f46fca1")
    protected void registerMetaExpertForPropertyDefinition() {
        // Infrastructure.PropertyDefinition
        // -----------
        
        // no constraint on PropertyDefinition.Type : PropertyType from Infrastructure.PropertyDefinition to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Type");
    }

    @objid ("dd6e2f65-4885-4013-91a0-2ae115a0d0fe")
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

    @objid ("524a9b10-5477-4911-8fe8-894d15cc9832")
    protected void registerMetaExpertForMatrixValueDefinition() {
        // Infrastructure.MatrixValueDefinition
        // -----------
        
        // no constraint on MatrixValueDefinition.Processor : ExternProcessor from Infrastructure.MatrixValueDefinition to Infrastructure.ExternProcessor
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Processor");
        
        // no constraint on MatrixValueDefinition.Parameters : PropertyTable from Infrastructure.MatrixValueDefinition to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Parameters");
    }

    @objid ("85b7372e-4ebe-4758-a266-485ccf397132")
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

    @objid ("caab11e5-70b5-400a-ab33-19f928c9be3d")
    protected void registerMetaExpertForModuleParameter() {
        // Infrastructure.ModuleParameter
        // -----------
        
        // no constraint on ModuleParameter.EnumType : EnumeratedPropertyType from Infrastructure.ModuleParameter to Infrastructure.EnumeratedPropertyType
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "EnumType");
    }

    @objid ("04d731d1-81fc-42f1-80cb-ebc94720ce13")
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

    @objid ("f2e8c9ab-721d-448b-81a6-2e4cddd79983")
    protected void registerMetaExpertForImpactProject() {
        // Infrastructure.ImpactProject
        // -----------
        
        // no constraint on ImpactProject.model : ImpactModel from Infrastructure.ImpactProject to Infrastructure.ImpactModel
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "model");
    }

    @objid ("8b2a6d78-ceb8-4964-be6f-5c60a7a3322a")
    protected void registerMetaExpertForAbstractProject() {
        // Infrastructure.AbstractProject is abstract
        
        // -----------
        
        // no constraint on AbstractProject.DiagramRoot : DiagramSet from Infrastructure.AbstractProject to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DiagramRoot");
    }

    @objid ("db39d486-2721-4d7c-b700-de68f7648968")
    protected void registerMetaExpertForImpactModel() {
        // Infrastructure.ImpactModel
        // -----------
        
        // ModelElement.Product: AbstractDiagram allowed from Infrastructure.ImpactModel to Infrastructure.ImpactDiagram by 'Product'{667eae04-e16e-403b-9db7-774bf9a97907} Standard.InformationFlow
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, ImpactDiagram.class, "Product");
    }

    @objid ("d00c8489-e3a2-4865-9467-36398dc62747")
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

    @objid ("94a59614-3662-4440-a162-0ddd0d97f22d")
    protected void registerMetaExpertForImpactDiagram() {
        // Infrastructure.ImpactDiagram
        // -----------
    }

    @objid ("3cfb1366-0a39-4dfe-9c2f-d4b4b1dbd724")
    protected void registerMetaExpertForGraphDiagram() {
        // Infrastructure.GraphDiagram
        // -----------
    }

    @objid ("00b89cc6-1f29-42c3-b129-97c5648392bb")
    protected void registerMetaExpertForDiagramSet() {
        // Infrastructure.DiagramSet
        // -----------
        
        // no constraint on DiagramSet.Sub : DiagramSet from Infrastructure.DiagramSet to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Sub");
        
        // no constraint on DiagramSet.ReferencedDiagram : AbstractDiagram from Infrastructure.DiagramSet to Infrastructure.AbstractDiagram
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "ReferencedDiagram");
    }

    @objid ("ad9b6e9d-1d49-42ca-81c9-1d4c134bcfd4")
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

    @objid ("bb344afd-ecc6-40e1-8597-02fbc88fe32d")
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

    @objid ("03b1398f-8dce-4ec9-b4bb-e6a57779fa00")
    protected void registerMetaExpertForElement() {
        // Infrastructure.Element is abstract
        
        // -----------
    }

}
