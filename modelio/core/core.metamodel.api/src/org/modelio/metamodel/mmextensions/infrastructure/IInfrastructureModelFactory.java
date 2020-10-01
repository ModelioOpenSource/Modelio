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

package org.modelio.metamodel.mmextensions.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.diagrams.GraphDiagram;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.session.api.repository.IRepository;

/**
 * Factory that creates the Infrastructure model elements.
 * <p>
 * New model elements can easily be created using <i>createXxxxx()</i> methods where Xxxxx is the name of the metaclass for which an instance has to be created. At least one method <i>createXxxxx()</i> exists for model Element. i.e.:
 * {@link IModelFactory#createElement(Class)}. This method only create an instance of the metaclass. The instance will have to be attached to the model and eventually named to be valid for the current transaction.
 * </p>
 */
@objid ("82cff19d-7059-40f8-b75b-b5a351bdae16")
public interface IInfrastructureModelFactory extends IModelFactory {
    /**
     * @return a new {@link ModuleParameter}.
     */
    @objid ("29a6ccdf-f74c-4182-93bc-fa17b0f2d893")
    ModuleParameter createConfigParam();

    /**
     * @return a new {@link Dependency}.
     */
    @objid ("a185d522-6585-47bd-a76d-5bb43f149cb2")
    Dependency createDependency();

    /**
     * @param source the origin of the dependency.
     * @param destination the destination of the dependency.
     * @param stereotype the Stereotype that extends the dependency.
     * @return An Dependency representing the Dependency in the Model.
     */
    @objid ("91c244a7-af07-4208-aa12-4c63caea59e0")
    Dependency createDependency(ModelElement source, ModelElement destination, Stereotype stereotype);

    @objid ("409b9b28-c862-4905-ab98-12d03dbdec63")
    Dependency createDependency(ModelElement source, ModelElement destination, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link DiagramSet}.
     */
    @objid ("cb410bf8-a7f4-47c0-a382-2e6f55ee3581")
    DiagramSet createDiagramSet();

    /**
     * Create a DiagramSet.
     * 
     * The DiagramSet is created on owner and has a name.
     * 
     * @param name the name of the DiagramSet to create.
     * @param owner the DiagramSet that will own the DiagramSet. f null, the created DiagramSet is owned at the project level.
     * @return An Class representing the Class in the Model.
     */
    @objid ("ebf6deff-e57c-4327-a196-f158bcaa1a85")
    DiagramSet createDiagramSet(String name, DiagramSet owner);

    /**
     * Creates a new attached document.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} or {@link Document#createExternalResource(String)} on the returned resource to initialize it.
     * 
     * @return a new {@link Document}.
     * @since 3.7
     */
    @objid ("ad3e6ae8-a80a-4044-8523-4da93ab74f51")
    Document createDocument();

    /**
     * @return a new {@link EnumeratedPropertyType}.
     */
    @objid ("4335acaf-6bed-467d-8cbb-79025b077e6d")
    EnumeratedPropertyType createEnumeratedPropertyType();

    /**
     * Creates a new rich note.
     * 
     * @return a new {@link ExternDocument}.
     * @deprecated since 3.7
     */
    @objid ("c277ce2b-611d-4c25-b186-890c1c03fe27")
    @Deprecated
    Document createExternDocument();

    /**
     * Creates an attached document.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} or {@link Document#createExternalResource(String)} on the returned resource to initialize it.
     * 
     * @param resourceType the role played by the resource.
     * @param owner the composition owner of the resource.
     * @param mimeType the MIME type of the rich note.
     * @return An {@link Document} representing the rich note in the model.
     * @deprecated since 3.7
     */
    @objid ("034811e5-387b-453b-b376-7da29c75f54b")
    @Deprecated
    Document createExternDocument(final ResourceType resourceType, final ModelElement owner, final String mimeType);

    @objid ("820e3e07-7bae-4613-aa6b-ba9914e93289")
    @Deprecated
    Document createExternDocument(String moduleName, String ownerName, String documentRole, final ModelElement owner, final String mimeType) throws ExtensionNotFoundException;

    /**
     * @return a new {@link ResourceType}.
     * @deprecated since 3.7
     */
    @objid ("d905de25-f737-4e83-9019-bb4d3a7b76fa")
    @Deprecated
    ResourceType createExternDocumentType();

    /**
     * @return a new {@link ExternProcessor}.
     */
    @objid ("c8ec4544-d53e-4685-bd79-4a6eb2b659ec")
    ExternProcessor createExternProcessor();

    /**
     * @return a new {@link GraphDiagram}.
     */
    @objid ("9f8a328a-2ab1-4025-b550-eae59b9a7d45")
    GraphDiagram createGraphDiagram();

    /**
     * @param name the name of the ActivityDiagram to be created.
     * @param owner the new element's composition owner.
     * @return a new {@link GraphDiagram}.
     */
    @objid ("0a41587c-4409-4cef-9992-920bac701b61")
    GraphDiagram createGraphDiagram(String name, ModelElement owner);

    /**
     * @return a new {@link ImpactDiagram}.
     */
    @objid ("b6116441-045a-483a-a576-6ff609cc1888")
    ImpactDiagram createImpactDiagram();

    /**
     * @param owner the new element's composition owner.
     * @return a new {@link ImpactDiagram}.
     */
    @objid ("ff90aa27-007e-4d7f-b66e-793c704e4e88")
    ImpactDiagram createImpactDiagram(ImpactModel owner);

    /**
     * @return a new {@link ImpactLink}.
     */
    @objid ("443e6f01-3c17-42a0-805a-13417c4282a4")
    ImpactLink createImpactLink();

    /**
     * @param owner the new element's composition owner.
     * @param source the link's source.
     * @param target the link's target.
     * @return a new {@link ImpactLink}.
     */
    @objid ("f57d3b70-796b-43f4-a638-21320cee9a7b")
    ImpactLink createImpactLink(ImpactModel owner, ModelElement source, ModelElement target);

    /**
     * @return a new {@link ImpactModel}.
     */
    @objid ("8f657566-7699-4ea9-baaf-b2ccabf6a290")
    ImpactModel createImpactModel();

    /**
     * @param owner the new element's composition owner.
     * @return a new {@link ImpactModel}.
     */
    @objid ("72d5ecb5-28a9-4ba3-ac28-48c4fc8469d9")
    ImpactModel createImpactModel(ImpactProject owner);

    /**
     * Creates an {@link ImpactProject}.
     * 
     * @param repository the repository where the model object will be stored.
     * @return a new {@link ImpactProject}.
     */
    @objid ("405cb664-2a44-4fa1-a534-a7f723c9b1ad")
    ImpactProject createImpactProject(IRepository repository);

    /**
     * @return a new {@link LocalPropertyTable}.
     */
    @objid ("52d422cd-5da1-4e05-a005-572df76e7152")
    LocalPropertyTable createLocalPropertyTable();

    /**
     * @return a new {@link MatrixDefinition}.
     */
    @objid ("2f3eff5e-ef2a-418e-b695-ea9e3080dea7")
    MatrixDefinition createMatrixDefinition();

    /**
     * @return a new {@link MatrixValueDefinition}.
     */
    @objid ("8ff84ff8-2544-4504-9907-537d793cad41")
    MatrixValueDefinition createMatrixValueDefinition();

    /**
     * @return a new {@link MetaclassReference}.
     */
    @objid ("271e7303-bf40-4134-9f0f-8218fb01c94a")
    MetaclassReference createMetaclassReference();

    /**
     * @param repository the repository where the model object will be stored.
     * @return a new {@link ModuleComponent}.
     */
    @objid ("a2eeb18e-4324-4e58-9dc5-77a10234dda3")
    ModuleComponent createModuleProject(IRepository repository);

    /**
     * @return a new {@link Note}.
     */
    @objid ("430494f3-62e0-4d1a-a624-69459e056560")
    Note createNote();

    /**
     * @param noteType the type of the Note.
     * @param owner the composition owner of the Note.
     * @param content the text of the Note.
     * @return An Note representing the Note in the Model.
     */
    @objid ("57d36c82-5fc0-4a55-8f5d-b170bb0580a6")
    Note createNote(NoteType noteType, ModelElement owner, String content);

    @objid ("e73cce31-fcaa-4814-a545-072b1ddfb04f")
    Note createNote(String moduleName, String ownerName, String noteTypeName, ModelElement owner, String content) throws ExtensionNotFoundException;

    /**
     * @return a new {@link NoteType}.
     */
    @objid ("59a62b00-b800-4f88-867e-bf4c902b2ed9")
    NoteType createNoteType();

    /**
     * @return a new {@link Profile}.
     */
    @objid ("fde83fda-50ab-4e32-b718-b51f11dadade")
    Profile createProfile();

    /**
     * @return a new {@link PropertyDefinition}.
     */
    @objid ("cd2cc964-8053-45d9-9b92-8e47fa127d32")
    PropertyDefinition createPropertyDefinition();

    /**
     * @return a new {@link PropertyEnumerationLitteral}.
     */
    @objid ("6ee54c71-484f-4581-9c87-1790649a02f6")
    PropertyEnumerationLitteral createPropertyEnumerationLitteral();

    /**
     * @return a new {@link PropertyTable}.
     */
    @objid ("45edca9b-c0a2-4115-b65e-55cda06f5518")
    PropertyTable createPropertyTable();

    /**
     * @return a new {@link PropertyTableDefinition}.
     */
    @objid ("0d253cb9-d6f4-44cd-84c0-493606c0677b")
    PropertyTableDefinition createPropertyTableDefinition();

    /**
     * @return a new {@link PropertyType}.
     */
    @objid ("78a4d385-f8aa-401d-846e-e69d4eea91e3")
    PropertyType createPropertyType();

    /**
     * @return a new {@link QueryDefinition}.
     */
    @objid ("114df3ef-8a56-4f9b-858a-f8acb5785e0f")
    QueryDefinition createQueryDefinition();

    /**
     * @return a new {@link ResourceType}.
     * @since 3.7
     */
    @objid ("8a4d22e0-352c-440b-a0b7-c756308b55a4")
    ResourceType createResourceType();

    /**
     * @return a new {@link Stereotype}.
     */
    @objid ("d4ac1046-6754-41ae-b0fb-5cc57c1d4e2d")
    Stereotype createStereotype();

    /**
     * @return a new {@link TagParameter}.
     */
    @objid ("7f6e388d-12f7-4635-bfc1-560519aa149b")
    TagParameter createTagParameter();

    /**
     * @param value the value of the tag parameter.
     * @param owner the owner tagged value of the tag parameter
     * @return An TagParameter representing the TagParameter in the Model.
     */
    @objid ("01463ac8-fa4e-4550-bb5a-935b960220d5")
    TagParameter createTagParameter(String value, TaggedValue owner);

    /**
     * @return a new {@link TagType}.
     */
    @objid ("e371acbd-5d94-460b-964b-b446f92df34d")
    TagType createTagType();

    /**
     * @return a new {@link TaggedValue}.
     */
    @objid ("7f57a731-a7b8-4bda-bb2f-79c9163bb893")
    TaggedValue createTaggedValue();

    /**
     * @param tagType the type of the TaggedValue.
     * @param owner the ModelElement that contains the Taggedvalue.
     * @return An TaggedValue representing the TaggedValue in the Model.
     */
    @objid ("ce70c781-8181-462a-895e-df6e6637a79f")
    TaggedValue createTaggedValue(TagType tagType, ModelElement owner);

    @objid ("66df7683-f81d-404b-b86d-3691830edf4c")
    TaggedValue createTaggedValue(String moduleName, String ownerName, String tagTypeName, ModelElement owner) throws ExtensionNotFoundException;

    /**
     * @return a new {@link TypedPropertyTable}.
     */
    @objid ("82246770-c0f3-47e6-9a33-a679d5876e2c")
    TypedPropertyTable createTypedPropertyTable();

    /**
     * Build a new attached {@link Document} or {@link Resource}.
     * 
     * @return an attached resource builder.
     * @since 3.7
     */
    @objid ("1435a5ed-10c1-4c4c-9b9e-c48a56dde18a")
    IResourceBuilder resourceBuilder();

    @objid ("562b5953-2757-4f6e-b24a-c6d6638902fd")
    void setDefaultValue(String key, Object value);

}
