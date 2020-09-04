/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.metamodel.impl.mmextensions.infrastructure.factory;

import java.io.IOException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.diagrams.GraphDiagram;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.infrastructure.IResourceBuilder;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
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
import org.modelio.vcore.model.spi.AbstractModelFactory;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.impl.GenericFactory;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * High level model element creation factory.
 */
@objid ("ec0ff74e-de0d-49e9-91c0-62b18aae0ebe")
public class InfrastructureModelFactoryImpl extends AbstractModelFactory implements IInfrastructureModelFactory {
    /**
     * The model element initializer used by the factory.
     */
    @objid ("188bea13-2bcc-4785-b8f2-2d706e7e2e0d")
     final IInfrastructureElementInitializer elementInitializer;

    @objid ("fa8690f3-602f-44c7-8169-279926cea787")
    protected final GenericFactory genericFactory;

    @objid ("87488404-4436-4fb0-b95a-566a0c7facd9")
    protected final IModel iModel;

    @objid ("52503452-221f-4e2f-a50f-786e9749ab83")
    protected final IRepository localRepository;

    @objid ("84a50f58-6e40-4720-8cde-9d7d28a7022c")
    protected final IRepository scratchRepository;

    @objid ("51481a43-0313-4f3e-9832-a5f15875dc97")
    protected final MModelServices modelServices;

    @objid ("97b0f694-4ba7-42c0-a0f6-402eed7ecdab")
    public InfrastructureModelFactoryImpl(ICoreSession session) {
        super(session.getMetamodel());
        
        this.iModel = session.getModel();
        this.genericFactory = session.getModel().getGenericFactory();
        this.scratchRepository = session.getRepositorySupport().getRepository(IRepositorySupport.REPOSITORY_KEY_SCRATCH);
        this.localRepository = session.getRepositorySupport().getRepository(IRepositorySupport.REPOSITORY_KEY_LOCAL);
        this.modelServices = new MModelServices(session);
        
        this.elementInitializer = new InfrastructureElementInitializer(this);
    }

    @objid ("a7125457-b9e1-4b37-86ff-f48a21c3c724")
    @Override
    public ModuleParameter createConfigParam() {
        ModuleParameter newElement = this.genericFactory.create(ModuleParameter.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("fe32db8f-9ccb-4f5b-82d3-17a703608999")
    @Override
    public Dependency createDependency() {
        Dependency newElement = this.genericFactory.create(Dependency.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("881ffbc5-85cb-4cb7-a26b-852e154d2236")
    @Override
    public Dependency createDependency(ModelElement source, ModelElement destination, Stereotype stereotype) {
        Dependency newElement = this.genericFactory.create(Dependency.class, source);
        newElement.setImpacted(source);
        newElement.setDependsOn(destination);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9888b8c2-e24a-4faa-ab43-927eeeb99229")
    @Override
    public Dependency createDependency(ModelElement source, ModelElement destination, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createDependency(source, destination, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Dependency.class)));
    }

    @objid ("869ad9b9-b8ad-477d-a98d-c1d7779e5d89")
    @Override
    public DiagramSet createDiagramSet() {
        DiagramSet newElement = this.genericFactory.create(DiagramSet.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7a3975cd-7154-441e-a19f-edc858f1bca6")
    @Override
    public DiagramSet createDiagramSet(String name, DiagramSet owner) {
        DiagramSet newElement = this.genericFactory.create(DiagramSet.class, this.scratchRepository);
        newElement.setName(name);
        owner.getSub().add(newElement);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5fe1d61d-8292-4df5-9698-dbbf6ffefd2c")
    @Override
    public Document createDocument() {
        Document newElement = this.genericFactory.create(Document.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("60c7b701-2d9b-4236-8866-38d99d240180")
    @Override
    public <T extends MObject> T createElement(java.lang.Class<T> metaclass) {
        T newElement = this.genericFactory.create(metaclass, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("2b8b7099-8fd4-484b-8104-ef6d37688086")
    @Override
    public MObject createElement(String metaclassName) {
        MObject newElement = this.genericFactory.create(metaclassName, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e996ac05-e28d-4eee-9b0d-1524d1b3e13f")
    @Override
    public MObject createElement(MClass metaclass) {
        MObject newElement = this.genericFactory.create(metaclass, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a5c12a08-cf6a-4db2-8337-9073c6dd552a")
    @Override
    public MObject createElement(MClass metaclass, MObject owner, MDependency dependency) {
        MObject newElement = this.genericFactory.create(metaclass, owner, dependency);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("cf601767-71a3-49a0-be3c-419c88f67f86")
    @Override
    public MObject createElement(String metaclassName, MObject owner, String dependencyName) {
        // TODO for all createElement generic methods, process errors (unknown
        // dep unknown metaclass, null objects ... )
        MObject newElement = this.genericFactory.create(metaclassName, owner, dependencyName);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6ea7bd6f-f6aa-43fe-be9b-a840ed686338")
    @Override
    public <T extends MObject> T createElement(java.lang.Class<T> metaclass, MObject owner, String dependencyName) {
        T newElement = this.genericFactory.create(metaclass, owner, dependencyName);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5be7979c-241e-4bdf-b051-f1026e0cb47b")
    @Override
    public EnumeratedPropertyType createEnumeratedPropertyType() {
        EnumeratedPropertyType newElement = this.genericFactory.create(EnumeratedPropertyType.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d4c3584a-924f-4198-a7aa-8cc2782837c0")
    @Override
    @Deprecated
    public Document createExternDocument() {
        return createDocument();
    }

    @objid ("25d45193-79db-4f67-90b8-876d0dfa0999")
    @Override
    @Deprecated
    public Document createExternDocument(ResourceType resourceType, final ModelElement owner, final String mimeType) {
        try {
            return resourceBuilder()
                    .withOwner(owner)
                    .withMimeType(mimeType)
                    .withRole(resourceType)
                    .createEmbeddedDocument();
        } catch (IOException e) {
            // Cannot happen, no location was specified
            throw new IllegalStateException(e);
        }
    }

    @objid ("73bc09d5-03ba-40ac-8021-9a8b9786b7bb")
    @Override
    @Deprecated
    public Document createExternDocument(final String moduleName, final String ownerName, final String documentRole, final ModelElement owner, final String mimeType) throws ExtensionNotFoundException {
        try {
            return resourceBuilder()
                    .withOwner(owner)
                    .withMimeType(mimeType)
                    .withRole(moduleName, ownerName, documentRole)
                    .createEmbeddedDocument();
        } catch (IOException e) {
            // Cannot happen, no location was specified
            throw new IllegalStateException(e);
        }
    }

    @objid ("a586a25c-147c-4e02-9c9c-3f33720609fe")
    @Override
    public ResourceType createExternDocumentType() {
        return createResourceType();
    }

    @objid ("d2e549e5-3a06-47bf-a269-a8f5cba89401")
    @Override
    public ExternProcessor createExternProcessor() {
        ExternProcessor newElement = this.genericFactory.create(ExternProcessor.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("35a52a09-1367-4806-b58c-6a4d903e1e73")
    @Override
    public GraphDiagram createGraphDiagram() {
        GraphDiagram newElement = this.genericFactory.create(GraphDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("8611ddd9-8596-4bc1-8320-c1ef39b7107a")
    @Override
    public GraphDiagram createGraphDiagram(String name, ModelElement owner) {
        GraphDiagram newElement = this.genericFactory.create(GraphDiagram.class, owner);
        newElement.setName(name);
        newElement.setOrigin(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("cce179ca-c698-43c9-bde9-e3a7197aa109")
    @Override
    public ImpactDiagram createImpactDiagram() {
        ImpactDiagram newElement = this.genericFactory.create(ImpactDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0efe0160-6596-4af2-8ebe-388a8864d06c")
    @Override
    public ImpactDiagram createImpactDiagram(ImpactModel owner) {
        ImpactDiagram newElement = this.genericFactory.create(ImpactDiagram.class, owner);
        newElement.setOrigin(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("8a6d792b-d1d6-4441-b6ad-b53d2e5c4c03")
    @Override
    public ImpactLink createImpactLink() {
        ImpactLink newElement = this.genericFactory.create(ImpactLink.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f3653177-c9f2-4c0c-a28f-c7c3854b3625")
    @Override
    public ImpactLink createImpactLink(ImpactModel owner, ModelElement source, ModelElement target) {
        ImpactLink newElement = this.genericFactory.create(ImpactLink.class, owner);
        newElement.setOwner(owner);
        newElement.setImpacted(source);
        newElement.setDependsOn(target);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("230c0391-ee76-4f73-ab86-f24a4e72b97d")
    @Override
    public ImpactModel createImpactModel() {
        ImpactModel newElement = this.genericFactory.create(ImpactModel.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0e1e1d59-8af7-4478-b10c-1fc830ff5af1")
    @Override
    public ImpactModel createImpactModel(ImpactProject owner) {
        ImpactModel newElement = this.genericFactory.create(ImpactModel.class, owner);
        newElement.setProject(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("01ebeba3-5119-4d3c-8327-f17929cb59c4")
    @Override
    public ImpactProject createImpactProject(IRepository repository) {
        ImpactProject newElement = this.genericFactory.create(ImpactProject.class, repository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("43e74c10-26bc-4bbb-ae45-d12e2d35c750")
    @Override
    public LocalPropertyTable createLocalPropertyTable() {
        LocalPropertyTable newElement = this.genericFactory.create(LocalPropertyTable.class, this.localRepository);
        return newElement;
    }

    @objid ("ef4cf6c7-483e-43c2-9782-efa50da3aacb")
    @Override
    public MatrixDefinition createMatrixDefinition() {
        MatrixDefinition newElement = this.genericFactory.create(MatrixDefinition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e2986d73-1faf-48c6-a2a2-78f03f51e9f8")
    @Override
    public MatrixValueDefinition createMatrixValueDefinition() {
        MatrixValueDefinition newElement = this.genericFactory.create(MatrixValueDefinition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("4ebbafc2-ee23-4f1c-9203-0b79c41815a0")
    @Override
    public MetaclassReference createMetaclassReference() {
        MetaclassReference newElement = this.genericFactory.create(MetaclassReference.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("4d8788bc-314d-4662-b90a-b968c269f809")
    @Override
    public ModuleComponent createModuleProject(IRepository repository) {
        ModuleComponent newElement = this.genericFactory.create(ModuleComponent.class, repository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("527de271-6771-4347-a3c4-2517bb0e5e01")
    @Override
    public Note createNote() {
        Note newElement = this.genericFactory.create(Note.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a3867d56-08e0-46ac-8067-edb601eb09e9")
    @Override
    public Note createNote(NoteType noteType, ModelElement owner, String content) {
        Note newElement = this.genericFactory.create(Note.class, owner);
        newElement.setSubject(owner);
        newElement.setContent(content);
        newElement.setModel(noteType);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c36dbf48-caeb-4987-bc90-f026cf839181")
    @Override
    public Note createNote(String moduleName, final String ownerName, String noteTypeName, ModelElement owner, String content) throws ExtensionNotFoundException {
        try {
            NoteType type = this.modelServices.getNoteType(moduleName, ownerName, noteTypeName, owner.getMClass());
            if (type != null) {
                return createNote(type, owner, content);
            } else {
                throw new ExtensionNotFoundException("'" + noteTypeName + "' note type not found");
            }
        } catch (ElementNotUniqueException e) {
            throw new IllegalArgumentException("'" + noteTypeName + "' note type is not unique in module '" + moduleName + "'");
        }
    }

    @objid ("e67acda0-8134-4a5e-8a50-c20eeda715a5")
    @Override
    public NoteType createNoteType() {
        NoteType newElement = this.genericFactory.create(NoteType.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7b1a4f86-73dd-4795-885f-4978f98c3707")
    @Override
    public Profile createProfile() {
        Profile newElement = this.genericFactory.create(Profile.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7a1f064c-7da9-404b-abd2-1730abb3e1e7")
    @Override
    public PropertyDefinition createPropertyDefinition() {
        PropertyDefinition newElement = this.genericFactory.create(PropertyDefinition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a028d328-b65b-41b3-b73c-b66265f68212")
    @Override
    public PropertyEnumerationLitteral createPropertyEnumerationLitteral() {
        PropertyEnumerationLitteral newElement = this.genericFactory.create(PropertyEnumerationLitteral.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0f4d1e78-b8c2-4194-856d-5c620c675258")
    @Override
    public PropertyTable createPropertyTable() {
        PropertyTable newElement = this.genericFactory.create(PropertyTable.class, this.scratchRepository);
        return newElement;
    }

    @objid ("5c114d1a-3e82-4637-aa39-6583b02b48ed")
    @Override
    public PropertyTableDefinition createPropertyTableDefinition() {
        PropertyTableDefinition newElement = this.genericFactory.create(PropertyTableDefinition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("78049a60-0c2b-4587-ba12-947d3d101af9")
    @Override
    public PropertyType createPropertyType() {
        PropertyType newElement = this.genericFactory.create(PropertyType.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e4bac148-dda9-4059-ac62-d8d496f29d8d")
    @Override
    public QueryDefinition createQueryDefinition() {
        QueryDefinition newElement = this.genericFactory.create(QueryDefinition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5f900bfd-c950-48d3-89f7-6de2632b0b73")
    @Override
    public ResourceType createResourceType() {
        ResourceType newElement = this.genericFactory.create(ResourceType.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("2077e741-6e24-48bb-8366-6819b1665827")
    @Override
    public Stereotype createStereotype() {
        Stereotype newElement = this.genericFactory.create(Stereotype.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("650612ea-a7b1-4d2e-91cf-0e9c372a8036")
    @Override
    public TagParameter createTagParameter() {
        TagParameter newElement = this.genericFactory.create(TagParameter.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9c9c75d4-47d2-404d-9f60-9b94cd6993a7")
    @Override
    public TagParameter createTagParameter(String value, TaggedValue owner) {
        TagParameter newElement = this.genericFactory.create(TagParameter.class, owner);
        newElement.setValue(value);
        newElement.setAnnoted(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c5e53795-a376-4b77-b8f5-f24dcdf19b15")
    @Override
    public TagType createTagType() {
        TagType newElement = this.genericFactory.create(TagType.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d0cfbffc-e649-4a09-8149-3525d611cd05")
    @Override
    public TaggedValue createTaggedValue() {
        TaggedValue newElement = this.genericFactory.create(TaggedValue.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d46f3106-a61b-40b8-9997-fbb6d69958f6")
    @Override
    public TaggedValue createTaggedValue(String moduleName, String ownerName, String tagTypeName, ModelElement owner) throws ExtensionNotFoundException {
        try {
            TagType type = this.modelServices.getTagType(moduleName, ownerName, tagTypeName, owner.getMClass());
            if (type != null) {
                return createTaggedValue(type, owner);
            } else {
                throw new ExtensionNotFoundException("'" + tagTypeName + "' tag type not found");
            }
        } catch (ElementNotUniqueException e) {
            throw new IllegalArgumentException("'" + tagTypeName + "' tag type is not unique in module '" + moduleName + "'");
        }
    }

    @objid ("3a0b4724-1ac0-4c2f-8344-a0d8cbc076ec")
    @Override
    public TaggedValue createTaggedValue(TagType tagType, ModelElement owner) {
        TaggedValue newElement = this.genericFactory.create(TaggedValue.class, owner);
        newElement.setAnnoted(owner);
        this.elementInitializer.initialize(newElement);
        newElement.setDefinition(tagType);
        return newElement;
    }

    @objid ("d9781cc8-097a-4995-ac36-d1e9b70be8f4")
    @Override
    public TypedPropertyTable createTypedPropertyTable() {
        TypedPropertyTable newElement = this.genericFactory.create(TypedPropertyTable.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    /**
     * Build a new attached {@link Document} or {@link Resource}.
     * 
     * @return an attached resource builder.
     */
    @objid ("1264191e-3666-4607-931f-7dee756285d1")
    @Override
    public IResourceBuilder resourceBuilder() {
        return new ResourceBuilder(this);
    }

    @objid ("038c229c-3d2e-447c-8a18-a52913ad0f8e")
    @Override
    public void setDefaultValue(String key, Object value) {
        this.elementInitializer.setDefaultValue(key, value);
    }

    @objid ("d0710937-f4d9-4d32-935d-9b936a216b29")
    @SuppressWarnings ("static-method")
    protected void attachStereotype(ModelElement newElement, final Stereotype stereotype) {
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
    }

    @objid ("9527f308-8172-4a45-89b8-8548e6a2ecdb")
    protected MClass getBaseClass(String baseName) {
        return this.metamodel.getMClass(baseName);
    }

    @objid ("d3e3deba-6647-46dd-9945-d7fff8150618")
    protected MClass getBaseClass(MetaclassReference classRef, Stereotype ste) {
        if (ste != null) {
            return getBaseClass(ste.getBaseClassName());
        } else if (classRef != null) {
            return getBaseClass(classRef.getReferencedClassName());
        }
        return null;
    }

    @objid ("9f804e12-ab15-41d5-a82f-e83600855c08")
    protected void initAbstractResource(AbstractResource newElement, ResourceType resourceType, ModelElement owner, String mimeType) {
        newElement.setSubject(owner);
        this.elementInitializer.initialize(newElement);
        newElement.setMimeType(mimeType);
        newElement.setType(resourceType);
    }

    /**
     * Find a stereotype by name and metaclass.
     * @throws IllegalArgumentException when resolution is ambiguous and several stereotypes match the given parameters
     * 
     * @param moduleName the name of the module owing the stereotype, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass a metaclass the stereotype must be applicable to.
     * @return the found stereotype. Might be <code>null</code> is no element matches the given parameters.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when no stereotype matches the given parameters
     */
    @objid ("51f38b74-58bf-40f1-95fe-af46fd70ac4a")
    protected final Stereotype resolveStereotype(String moduleName, String stereotypeName, MClass metaclass) throws ExtensionNotFoundException {
        List<Stereotype> stereotypes = this.modelServices.findStereotypes(moduleName, stereotypeName, metaclass);
        if (stereotypes.size() == 0) {
            throw new ExtensionNotFoundException("'" + stereotypeName + "' stereotype not found");
        } else if (stereotypes.size() == 1) {
            return stereotypes.get(0);
        } else {
            throw new IllegalArgumentException("'" + stereotypeName + "' stereotype is not unique in module '" + moduleName + "'");
        }
    }

    /**
     * Answer to the question: is 'stereotype' a child of the stereotype named by 'stereotypeName' Companion method of the public isStereoyped() method
     * 
     * @param stereotype a stereotype
     * @param stereotypeName the name of another stereotype.
     * @return <code>true</code> if 'stereotype' a child of the stereotype named by 'stereotypeName' else <code>false</code>.
     */
    @objid ("775fc5ae-45a2-4d16-896a-56d84dec6005")
    private boolean inheritsFrom(Stereotype stereotype, String stereotypeName) {
        if (stereotype.getName().equals(stereotypeName)) {
            return true;
        }
        
        if (stereotype.getParent() != null) {
            if (inheritsFrom(stereotype.getParent(), stereotypeName)) {
                return true;
            }
        }
        return false;
    }

}
