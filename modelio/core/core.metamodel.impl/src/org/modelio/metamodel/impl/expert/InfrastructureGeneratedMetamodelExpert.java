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
@objid ("51b1a926-c287-4791-978e-7fef2de23bd3")
public class InfrastructureGeneratedMetamodelExpert extends CompositeMetamodelExpert {
    /**
     * Constructor.
     * <p>
     * You need to call {@link #register()} next.
     * @param mm The metamodel.
     */
    @objid ("1c655f21-a316-4498-86c9-db822cc2eb6e")
    public  InfrastructureGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
        
        
    }

    /**
     * Initializes this expert.
     */
    @objid ("9901e1f1-c841-4fb4-9ad8-cdbb1e737b44")
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

    @objid ("8cc1a900-5430-4623-bd2d-1d4a40c5742e")
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

    @objid ("1c935152-dac4-44c9-94e1-d9b2070571e6")
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

    @objid ("0ff1efd6-df6e-4b44-8f0f-5ac592090e27")
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

    @objid ("de823aaf-eebf-4066-9a28-5c914e7970be")
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

    @objid ("19b18da9-eb0f-462a-b0ea-6fcc692a79b0")
    protected void registerMetaExpertForTagType() {
        // Infrastructure.TagType
        // -----------
        
    }

    @objid ("a93b04c4-44a6-4dc2-957e-9ac62bbf7957")
    protected void registerMetaExpertForTagParameter() {
        // Infrastructure.TagParameter
        // -----------
        
    }

    @objid ("e050ec1c-630f-4883-a3f0-9e0cef32fe20")
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

    @objid ("50fb2db1-8d10-44fb-843b-5153e3df805e")
    protected void registerMetaExpertForResourceType() {
        // Infrastructure.ResourceType
        // -----------
        
    }

    @objid ("e1a1b7d9-df2f-4bec-9bc5-d9d4fd49b37a")
    protected void registerMetaExpertForResource() {
        // Infrastructure.Resource
        // -----------
        
    }

    @objid ("299635fc-e18a-41bf-8287-98ea41ff647b")
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

    @objid ("2f5b54c2-3a71-4e57-bd4b-190581fa1ce0")
    protected void registerMetaExpertForNoteType() {
        // Infrastructure.NoteType
        // -----------
        
    }

    @objid ("307cfbe0-474d-4f7c-af91-3f707a55f8cd")
    protected void registerMetaExpertForNote() {
        // Infrastructure.Note
        // -----------
        
        // no constraint on Note.Model : NoteType from Infrastructure.Note to Infrastructure.NoteType
        this.ruleMetaExpert.addDependencyRule(Note.class, null, "Model");
        
    }

    @objid ("e2cda779-8c5d-48d3-be00-e29aac18279a")
    protected void registerMetaExpertForMethodologicalLink() {
        // Infrastructure.MethodologicalLink
        // -----------
        
        // no constraint on MethodologicalLink.ExternElement : ExternElement from Infrastructure.MethodologicalLink to Infrastructure.ExternElement
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "ExternElement");
        
    }

    @objid ("87ed41fe-84d9-4eb5-a8d5-bcbb088c6428")
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

    @objid ("5f8c3cbb-0917-44ad-9733-d99ecc4e91ba")
    protected void registerMetaExpertForExternProcessor() {
        // Infrastructure.ExternProcessor
        // -----------
        
    }

    @objid ("931d3645-6ff4-4337-999e-36b9526a030a")
    protected void registerMetaExpertForExternElement() {
        // Infrastructure.ExternElement
        // -----------
        
    }

    @objid ("71b8a5e2-ed81-40d6-8998-c32899da383a")
    protected void registerMetaExpertForDocument() {
        // Infrastructure.Document
        // -----------
        
    }

    @objid ("0eac7a45-00e5-4be6-8306-d56ad45c2d6c")
    protected void registerMetaExpertForDependency() {
        // Infrastructure.Dependency
        // -----------
        
        // no constraint on Dependency.DependsOn : ModelElement from Infrastructure.Dependency to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(Dependency.class, null, "DependsOn");
        this.ruleMetaExpert.addDependencyRule(MethodologicalLink.class, null, "DependsOn");
        
    }

    @objid ("d085cd80-113d-4248-8b60-45fae83d781e")
    protected void registerMetaExpertForAbstractResource() {
        // Infrastructure.AbstractResource
        // -----------
        
        // no constraint on AbstractResource.Type : ResourceType from Infrastructure.AbstractResource to Infrastructure.ResourceType
        this.ruleMetaExpert.addDependencyRule(AbstractResource.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Document.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Resource.class, null, "Type");
        
    }

    @objid ("d0769f3d-0467-4d9e-86d5-ce75d1edd053")
    protected void registerMetaExpertForTypedPropertyTable() {
        // Infrastructure.TypedPropertyTable
        // -----------
        
        // no constraint on TypedPropertyTable.Type : PropertyTableDefinition from Infrastructure.TypedPropertyTable to Infrastructure.PropertyTableDefinition
        this.ruleMetaExpert.addDependencyRule(TypedPropertyTable.class, null, "Type");
        
    }

    @objid ("d2dfb961-0976-4a29-a1c1-73b83de4be3e")
    protected void registerMetaExpertForPropertyTableDefinition() {
        // Infrastructure.PropertyTableDefinition
        // -----------
        
        // no constraint on PropertyTableDefinition.Owned : PropertyDefinition from Infrastructure.PropertyTableDefinition to Infrastructure.PropertyDefinition
        this.ruleMetaExpert.addDependencyRule(PropertyTableDefinition.class, null, "Owned");
        
    }

    @objid ("f262794e-86e4-4402-9b53-427549a6edb2")
    protected void registerMetaExpertForPropertyEnumerationLitteral() {
        // Infrastructure.PropertyEnumerationLitteral
        // -----------
        
    }

    @objid ("d1e4acbb-b394-4e3f-a305-3236382be3da")
    protected void registerMetaExpertForLocalPropertyTable() {
        // Infrastructure.LocalPropertyTable
        // -----------
        
        // no constraint on LocalPropertyTable.LocalAnnoted : ModelElement from Infrastructure.LocalPropertyTable to Infrastructure.ModelElement
        this.ruleMetaExpert.addDependencyRule(LocalPropertyTable.class, null, "LocalAnnoted");
        
    }

    @objid ("d990a38e-a8fb-459e-bbe8-7a18848ae68f")
    protected void registerMetaExpertForPropertyTable() {
        // Infrastructure.PropertyTable
        // -----------
        
    }

    @objid ("6f9cda7f-150f-4b8f-9877-5ec9f273ecf2")
    protected void registerMetaExpertForEnumeratedPropertyType() {
        // Infrastructure.EnumeratedPropertyType
        // -----------
        
        // no constraint on EnumeratedPropertyType.Litteral : PropertyEnumerationLitteral from Infrastructure.EnumeratedPropertyType to Infrastructure.PropertyEnumerationLitteral
        this.ruleMetaExpert.addDependencyRule(EnumeratedPropertyType.class, null, "Litteral");
        
    }

    @objid ("08fb7fe9-6580-4190-954d-fcd7dbeb188c")
    protected void registerMetaExpertForPropertyType() {
        // Infrastructure.PropertyType
        // -----------
        
    }

    @objid ("eec1b804-ce6f-4377-94cc-57b3d1deae2d")
    protected void registerMetaExpertForDynamicPropertyDefinition() {
        // Infrastructure.DynamicPropertyDefinition
        // -----------
        
    }

    @objid ("137f7c97-a402-49d8-8a84-12d1c01b7d2d")
    protected void registerMetaExpertForPropertyDefinition() {
        // Infrastructure.PropertyDefinition
        // -----------
        
        // no constraint on PropertyDefinition.Type : PropertyType from Infrastructure.PropertyDefinition to Infrastructure.PropertyType
        this.ruleMetaExpert.addDependencyRule(DynamicPropertyDefinition.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(PropertyDefinition.class, null, "Type");
        
    }

    @objid ("d67cafcb-0eef-4e0a-92e1-452ce7789a03")
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

    @objid ("9e512cbf-2bf7-4efa-86b2-c1f940c7f6a8")
    protected void registerMetaExpertForMatrixValueDefinition() {
        // Infrastructure.MatrixValueDefinition
        // -----------
        
        // no constraint on MatrixValueDefinition.Processor : ExternProcessor from Infrastructure.MatrixValueDefinition to Infrastructure.ExternProcessor
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Processor");
        
        // no constraint on MatrixValueDefinition.Parameters : PropertyTable from Infrastructure.MatrixValueDefinition to Infrastructure.PropertyTable
        this.ruleMetaExpert.addDependencyRule(MatrixValueDefinition.class, null, "Parameters");
        
    }

    @objid ("1d1982da-f4ab-4461-a329-f60f283bea7f")
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

    @objid ("7bc835ca-3a8f-468f-a0a6-cc621908ddde")
    protected void registerMetaExpertForModuleParameter() {
        // Infrastructure.ModuleParameter
        // -----------
        
        // no constraint on ModuleParameter.EnumType : EnumeratedPropertyType from Infrastructure.ModuleParameter to Infrastructure.EnumeratedPropertyType
        this.ruleMetaExpert.addDependencyRule(ModuleParameter.class, null, "EnumType");
        
    }

    @objid ("3f1b065b-a782-4cb4-a272-17bda39b6340")
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

    @objid ("92476682-8658-43aa-8663-538860198721")
    protected void registerMetaExpertForImpactProject() {
        // Infrastructure.ImpactProject
        // -----------
        
        // no constraint on ImpactProject.model : ImpactModel from Infrastructure.ImpactProject to Infrastructure.ImpactModel
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "model");
        
    }

    @objid ("088fa7df-8e19-4827-87ea-22ef0108020c")
    protected void registerMetaExpertForAbstractProject() {
        // Infrastructure.AbstractProject is abstract
        
        // -----------
        
        // no constraint on AbstractProject.DiagramRoot : DiagramSet from Infrastructure.AbstractProject to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(AbstractProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ImpactProject.class, null, "DiagramRoot");
        this.ruleMetaExpert.addDependencyRule(ModuleComponent.class, null, "DiagramRoot");
        
    }

    @objid ("fd834b68-1baf-4a6d-aa4a-256c729f0917")
    protected void registerMetaExpertForImpactModel() {
        // Infrastructure.ImpactModel
        // -----------
        
        // ModelElement.Product: AbstractDiagram allowed from Infrastructure.ImpactModel to Infrastructure.ImpactDiagram by 'Product'{667eae04-e16e-403b-9db7-774bf9a97907} Standard.InformationFlow
        this.ruleMetaExpert.addDependencyRule(ImpactModel.class, ImpactDiagram.class, "Product");
        
    }

    @objid ("5fbec101-923b-4405-84dc-9b40edad7cb0")
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

    @objid ("c137338b-3800-47bb-b98f-666a0588a3ef")
    protected void registerMetaExpertForImpactDiagram() {
        // Infrastructure.ImpactDiagram
        // -----------
        
    }

    @objid ("15a32a8c-3377-4257-9af4-1175c18e66df")
    protected void registerMetaExpertForGraphDiagram() {
        // Infrastructure.GraphDiagram
        // -----------
        
    }

    @objid ("74694320-dc6b-4a96-b1cb-0666c1553840")
    protected void registerMetaExpertForDiagramSet() {
        // Infrastructure.DiagramSet
        // -----------
        
        // no constraint on DiagramSet.Sub : DiagramSet from Infrastructure.DiagramSet to Infrastructure.DiagramSet
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "Sub");
        
        // no constraint on DiagramSet.ReferencedDiagram : AbstractDiagram from Infrastructure.DiagramSet to Infrastructure.AbstractDiagram
        this.ruleMetaExpert.addDependencyRule(DiagramSet.class, null, "ReferencedDiagram");
        
    }

    @objid ("523dfc49-ff84-4027-88fc-4d4d1bddceb6")
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

    @objid ("74355dc1-1cf9-4a18-ad06-501c5b69e85a")
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

    @objid ("a6379fc2-d637-44a1-b393-21eaf8172716")
    protected void registerMetaExpertForElement() {
        // Infrastructure.Element is abstract
        
        // -----------
        
    }

}
