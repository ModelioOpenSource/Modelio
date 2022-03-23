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
package org.modelio.gproject.data.module.jaxbv2;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.modelio.gproject.data.module.jaxbv2 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new Jxbv2instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@objid ("dc756144-f928-4436-8eb5-27cc36018ee3")
@XmlRegistry
public class ObjectFactory {
    @objid ("26b29072-c606-4e44-97a3-25c2e06396c8")
    private static final QName _LinkConstraintSourceScope_QNAME = new QName("", "SourceScope");

    @objid ("7762565f-f4bf-41ed-bbb3-00a88877c808")
    private static final QName _LinkConstraintTargetScope_QNAME = new QName("", "TargetScope");

    /**
     * Create a new Jxbv2ObjectFactory that can be used to create new Jxbv2instances of schema derived classes for package: org.modelio.gproject.data.module.jaxbv2
     */
    @objid ("01d0e650-b21f-43e4-8f66-7a7df44b85fd")
    public  ObjectFactory() {
        
    }

    /**
     * Create an instance of {@link Jxbv2Module }
     */
    @objid ("de8a1ae2-b351-4d3e-8155-fe119cd3e0fd")
    public Jxbv2Module createModule() {
        return new Jxbv2Module();
    }

    /**
     * Create an instance of {@link Jxbv2Handler }
     */
    @objid ("a2f2abc2-8a0c-4add-80cd-9da3500fbb07")
    public Jxbv2Handler createHandler() {
        return new Jxbv2Handler();
    }

    /**
     * Create an instance of {@link Jxbv2Enumeration }
     */
    @objid ("59d2f491-14d6-4b19-b359-4aaa86da68bb")
    public Jxbv2Enumeration createEnumeration() {
        return new Jxbv2Enumeration();
    }

    /**
     * Create an instance of {@link Jxbv2PropertyDefinition }
     */
    @objid ("af35ccb0-300f-4d5e-8966-565aaf40babf")
    public Jxbv2PropertyDefinition createPropertyDefinition() {
        return new Jxbv2PropertyDefinition();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources }
     */
    @objid ("85bc515a-82de-41e0-9f21-a3a8d193ee3f")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources createModuleResources() {
        return new Jxbv2Module.Jxbv2Resources();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2Styles }
     */
    @objid ("824d2210-973d-4ec4-acc3-53668da8c54b")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles createModuleResourcesStyles() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2Styles();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Dependencies }
     */
    @objid ("17d0af81-0036-408c-962f-4e8bc18eb335")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies createModuleDependencies() {
        return new Jxbv2Module.Jxbv2Dependencies();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui }
     */
    @objid ("a1edf187-b7cc-414c-a641-618fbc9360e7")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui createModuleGui() {
        return new Jxbv2Module.Jxbv2Gui();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Views }
     */
    @objid ("cdb16f94-569f-44d3-9179-0a861bee8d06")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views createModuleGuiViews() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Views();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage }
     */
    @objid ("1518a510-81cc-4ec7-8960-3fc71541c54a")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage createModuleGuiViewsPropertyPage() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams }
     */
    @objid ("e3663c7f-e821-489a-8555-8fcb3cac87cb")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams createModuleGuiDiagrams() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType }
     */
    @objid ("96398907-d201-4cac-80f0-ba874cc8d8a2")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType createModuleGuiDiagramsDiagramType() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette }
     */
    @objid ("c2517708-86b5-4a66-ad45-ecd49e2302cd")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette createModuleGuiDiagramsDiagramTypePalette() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu }
     */
    @objid ("2082ccb2-ba20-479d-849e-ce62878e05a6")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu createModuleGuiContextualMenu() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Parameters }
     */
    @objid ("b0cbb72a-4219-46d6-a634-fdc5bb874a52")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters createModuleParameters() {
        return new Jxbv2Module.Jxbv2Parameters();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles }
     */
    @objid ("714c7e49-5803-4a15-8d08-84dedde9e7b9")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles createModuleProfiles() {
        return new Jxbv2Module.Jxbv2Profiles();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile }
     */
    @objid ("167bbcc4-3f7f-4655-85b2-fb8c162c2d06")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile createModuleProfilesProfile() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference }
     */
    @objid ("16770d77-0a08-49c6-8b12-429f53971dc9")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference createModuleProfilesProfileMetaclassReference() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype }
     */
    @objid ("4ef3dd8f-7293-4cd9-bbcc-caa32ebc33bd")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype createModuleProfilesProfileStereotype() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype();
    }

    /**
     * Create an instance of {@link MultiPathes }
     */
    @objid ("484a80bb-122a-4d23-bf67-60196c258a80")
    public Jxbv2MultiPathes createMultiPathes() {
        return new Jxbv2MultiPathes();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2PropertyTypes }
     */
    @objid ("8627447c-13a1-483c-8102-51682c80d174")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes createModulePropertyTypes() {
        return new Jxbv2Module.Jxbv2PropertyTypes();
    }

    /**
     * Create an instance of {@link Command }
     */
    @objid ("c26c3dbf-736d-4e5b-9bf9-652058773fc2")
    public Jxbv2Command createCommand() {
        return new Jxbv2Command();
    }

    /**
     * Create an instance of {@link org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType }
     */
    @objid ("837377d6-b867-4e09-8506-d18c2a22c78c")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType createPropertyType() {
        return new org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType();
    }

    /**
     * Create an instance of {@link Tool }
     */
    @objid ("fa5d0caf-bc01-4ff1-83e5-139f12f1b321")
    public Jxbv2Tool createTool() {
        return new Jxbv2Tool();
    }

    /**
     * Create an instance of {@link org.modelio.gproject.data.module.jaxbv2.CommandRef }
     */
    @objid ("b6273c92-f420-484a-bc09-2f85bc846610")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2CommandRef createCommandRef() {
        return new org.modelio.gproject.data.module.jaxbv2.Jxbv2CommandRef();
    }

    /**
     * Create an instance of {@link org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal }
     */
    @objid ("c3fda985-5a73-4ad3-bcb5-61419cda44f9")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal createLiteral() {
        return new org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal();
    }

    /**
     * Create an instance of {@link Jxbv2TagType }
     */
    @objid ("52f0d4de-61fd-4d16-8017-772ee4a0ce39")
    public Jxbv2TagType createTagType() {
        return new Jxbv2TagType();
    }

    /**
     * Create an instance of {@link org.modelio.gproject.data.module.jaxbv2.ToolRef }
     */
    @objid ("51628778-b329-44de-a06c-e2e20f7cc346")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2ToolRef createToolRef() {
        return new org.modelio.gproject.data.module.jaxbv2.Jxbv2ToolRef();
    }

    /**
     * Create an instance of {@link Jxbv2ExternDocumentType }
     */
    @objid ("90ba02bc-85c2-4336-8d4b-521d7bf6734d")
    public Jxbv2ExternDocumentType createExternDocumentType() {
        return new Jxbv2ExternDocumentType();
    }

    /**
     * Create an instance of {@link Jxbv2Scope }
     */
    @objid ("c4ce4200-99e1-4223-9495-f95324f61f5c")
    public Jxbv2Scope createScope() {
        return new Jxbv2Scope();
    }

    /**
     * Create an instance of {@link Jxbv2NoteType }
     */
    @objid ("411a75fd-7792-484b-b76f-af4d328c6a6e")
    public Jxbv2NoteType createNoteType() {
        return new Jxbv2NoteType();
    }

    /**
     * Create an instance of {@link Dependency }
     */
    @objid ("e9d779bd-3d51-4be6-8e25-89baee6c02e8")
    public Jxbv2Dependency createDependency() {
        return new Jxbv2Dependency();
    }

    /**
     * Create an instance of {@link Jxbv2PropertyTableDefinition }
     */
    @objid ("6d14a09f-2110-4930-9deb-b5b39f245098")
    public Jxbv2PropertyTableDefinition createPropertyTableDefinition() {
        return new Jxbv2PropertyTableDefinition();
    }

    /**
     * Create an instance of {@link Jxbv2Handler.Jxbv2HParameter }
     */
    @objid ("180dad54-f15b-4fca-8a15-9beca7c1dee4")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Handler.Jxbv2HParameter createHandlerHParameter() {
        return new Jxbv2Handler.Jxbv2HParameter();
    }

    /**
     * Create an instance of {@link Enumeration.Literal }
     */
    @objid ("c13070d6-df2a-4d32-890a-c59d6b4ad564")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration.Jxbv2Literal createEnumerationLiteral() {
        return new Jxbv2Enumeration.Jxbv2Literal();
    }

    /**
     * Create an instance of {@link PropertyDefinition.TypeRef }
     */
    @objid ("6177aaaf-de13-40be-a2ed-113955bb637b")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2TypeRef createPropertyDefinitionTypeRef() {
        return new Jxbv2PropertyDefinition.Jxbv2TypeRef();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2Styles.Jxbv2Style }
     */
    @objid ("da366291-47b2-456e-9593-7f6f5bcc3f85")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles.Jxbv2Style createModuleResourcesStylesStyle() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2Styles.Jxbv2Style();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Dependencies.Required }
     */
    @objid ("b5fb3733-e9f6-4c46-9b6d-a08dc1f13221")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Required createModuleDependenciesRequired() {
        return new Jxbv2Module.Jxbv2Dependencies.Jxbv2Required();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Dependencies.Optional }
     */
    @objid ("0eb0e1af-a476-4dae-bcbf-1c690179ef2d")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional createModuleDependenciesOptional() {
        return new Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Commands }
     */
    @objid ("7782f90e-c858-4982-8e3a-fac08b3a5d74")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Commands createModuleGuiCommands() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Commands();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Tools }
     */
    @objid ("84a82ce4-5722-41c9-aa2b-0951863a4ce1")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Tools createModuleGuiTools() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Tools();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef }
     */
    @objid ("75cb88ac-62ef-45c9-9660-e184095ba772")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef createModuleGuiViewsPropertyPageCommandRef() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard }
     */
    @objid ("73f0767f-a651-4bcb-9f2d-d00c08b93add")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard createModuleGuiDiagramsDiagramTypeWizard() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef }
     */
    @objid ("463ba87f-c4f8-4781-9256-c3333f85d1f7")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef createModuleGuiDiagramsDiagramTypePaletteToolRef() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef }
     */
    @objid ("13ac15a9-a22f-4e89-8629-14b37dd5ce7f")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef createModuleGuiContextualMenuCommandRef() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter }
     */
    @objid ("7191567f-8297-40ff-8ab2-6d1bdcac6f35")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter createModuleParametersParameter() {
        return new Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2TagTypes }
     */
    @objid ("3ea4d792-d576-411e-bd72-6f1e6bcfc1ca")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2TagTypes createModuleProfilesProfileMetaclassReferenceTagTypes() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2TagTypes();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2NoteTypes }
     */
    @objid ("ac91784f-70d7-418e-8152-d16b2d4b6060")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2NoteTypes createModuleProfilesProfileMetaclassReferenceNoteTypes() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2NoteTypes();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2ExternDocumentTypes }
     */
    @objid ("b2718d29-cc40-4517-b5c4-633ac95d9340")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2ExternDocumentTypes createModuleProfilesProfileMetaclassReferenceExternDocumentTypes() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2ExternDocumentTypes();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon }
     */
    @objid ("e78d3a7c-9efc-45d1-adb9-3c9683ce35fe")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon createModuleProfilesProfileStereotypeIcon() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image }
     */
    @objid ("6e6ac75d-db9a-44f3-b9b9-79ac6b0af746")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image createModuleProfilesProfileStereotypeImage() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2TagTypes }
     */
    @objid ("1b48cbc9-7b81-4bef-b1f2-ede19890c7db")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2TagTypes createModuleProfilesProfileStereotypeTagTypes() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2TagTypes();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2NoteTypes }
     */
    @objid ("cabf98e1-404a-4ca5-bec7-5574503ec647")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2NoteTypes createModuleProfilesProfileStereotypeNoteTypes() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2NoteTypes();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2ExternDocumentTypes }
     */
    @objid ("fe5a897b-8b3c-4ade-a8d3-ad12757cf2c1")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2ExternDocumentTypes createModuleProfilesProfileStereotypeExternDocumentTypes() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2ExternDocumentTypes();
    }

    /**
     * Create an instance of {@link MultiPathes.PathEntry }
     */
    @objid ("83840177-5388-421c-89e3-963b2c38d8c7")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes.Jxbv2PathEntry createMultiPathesPathEntry() {
        return new Jxbv2MultiPathes.Jxbv2PathEntry();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2MetamodelFragments }
     */
    @objid ("66716fcb-a0d8-40a6-a8fa-7d46012391d2")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments createModuleMetamodelFragments() {
        return new Jxbv2Module.Jxbv2MetamodelFragments();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates }
     */
    @objid ("772a24a6-d829-4297-a7dc-1cbf6ce6db9b")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates createModuleResourcesDocTemplates() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2Patterns }
     */
    @objid ("6dd4f927-a2b0-4483-bdbc-ae4e5bcb4bc4")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns createModuleResourcesPatterns() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2Patterns();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2Macros }
     */
    @objid ("f0c619f9-afcf-4752-bb42-2a550d273f57")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros createModuleResourcesMacros() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2Macros();
    }

    /**
     * Create an instance of {@link Jxbv2Module.MetamodelFragments.MetamodelFragment }
     */
    @objid ("435e37d2-66aa-4bba-881b-7d1b83bde637")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments.Jxbv2MetamodelFragment createModuleMetamodelFragmentsMetamodelFragment() {
        return new Jxbv2Module.Jxbv2MetamodelFragments.Jxbv2MetamodelFragment();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates.DocTemplate }
     */
    @objid ("c3a87bbe-4edc-48ac-9174-ece4947f51f6")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates.Jxbv2DocTemplate createModuleResourcesDocTemplatesDocTemplate() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates.Jxbv2DocTemplate();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2Patterns.Pattern }
     */
    @objid ("d2b1eac6-e594-4ac0-8fd5-ae07027782fe")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns.Jxbv2Pattern createModuleResourcesPatternsPattern() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2Patterns.Jxbv2Pattern();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Resources.Jxbv2Macros.Jxbv2Macro }
     */
    @objid ("9bda447e-725d-4706-90f1-a92eaffa114f")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros.Jxbv2Macro createModuleResourcesMacrosMacro() {
        return new Jxbv2Module.Jxbv2Resources.Jxbv2Macros.Jxbv2Macro();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Matrices }
     */
    @objid ("a595bc71-7ff2-452b-b997-da5301408582")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices createModuleGuiMatrices() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Matrices();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType }
     */
    @objid ("a25a6016-2b06-4564-a7e9-8ddbd7d3f20a")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType createModuleGuiMatricesMatrixType() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X }
     */
    @objid ("808f338c-9c89-4918-a290-dcfc4269419e")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X createModuleGuiMatricesMatrixTypeX() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2Wizard }
     */
    @objid ("cd496ba9-5a36-4f39-abe6-df8c5e4b42e5")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2Wizard createModuleGuiMatricesMatrixTypeWizard() {
        return new Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2Wizard();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2PropertyTypes.Jxbv2PropertyType }
     */
    @objid ("441cf448-8a26-4023-9834-35616a9b84fe")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes.Jxbv2PropertyType createModulePropertyTypesPropertyType() {
        return new Jxbv2Module.Jxbv2PropertyTypes.Jxbv2PropertyType();
    }

    /**
     * Create an instance of {@link LinkConstraint }
     */
    @objid ("7109c4e8-e194-4a5f-8543-bab39ff89e8d")
    public Jxbv2LinkConstraint createLinkConstraint() {
        return new Jxbv2LinkConstraint();
    }

    /**
     * Create an instance of {@link StereotypeRef }
     */
    @objid ("7eb0c0c7-0122-425a-aea4-c97adc5e35bf")
    public Jxbv2StereotypeRef createStereotypeRef() {
        return new Jxbv2StereotypeRef();
    }

    /**
     * Create an instance of {@link PropertyTableDefinition.Extensions }
     */
    @objid ("d3eedda7-32af-4e2f-b580-e823db585dbd")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyTableDefinition.Jxbv2Extensions createPropertyTableDefinitionExtensions() {
        return new Jxbv2PropertyTableDefinition.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link org.modelio.gproject.data.module.jaxbv2.Literal.Extensions }
     */
    @objid ("64192be5-b9f8-47aa-8888-31d5cc6a5187")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal.Jxbv2Extensions createLiteralExtensions() {
        return new org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link PropertyDefinition.Parameter }
     */
    @objid ("a2f53e51-e615-44a8-9299-0c49856cfb4d")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2Parameter createPropertyDefinitionParameter() {
        return new Jxbv2PropertyDefinition.Jxbv2Parameter();
    }

    /**
     * Create an instance of {@link NoteType.Extensions }
     */
    @objid ("901aa7ef-b2e7-4637-8058-84d9ac460a36")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2NoteType.Jxbv2Extensions createNoteTypeExtensions() {
        return new Jxbv2NoteType.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link ExternDocumentType.Extensions }
     */
    @objid ("ef8094b7-ae04-4504-a059-d523169faf5a")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2ExternDocumentType.Jxbv2Extensions createExternDocumentTypeExtensions() {
        return new Jxbv2ExternDocumentType.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link org.modelio.gproject.data.module.jaxbv2.PropertyType.Extensions }
     */
    @objid ("cf33db1e-b942-4ae1-983e-c1316c88aaf6")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType.Jxbv2Extensions createPropertyTypeExtensions() {
        return new org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link TagType.Extensions }
     */
    @objid ("80089ed0-855a-4c96-a4c9-df67dd0ea9a7")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2TagType.Jxbv2Extensions createTagTypeExtensions() {
        return new Jxbv2TagType.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Constraints }
     */
    @objid ("75e8078a-e446-4b8e-b6ce-22c639902ba3")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Constraints createModuleProfilesProfileStereotypeConstraints() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Constraints();
    }

    /**
     * Create an instance of {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Extensions }
     */
    @objid ("d6b66fbf-531d-4a9d-9636-32479951a789")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Extensions createModuleProfilesProfileStereotypeExtensions() {
        return new Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Extensions();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Jxbv2Scope }{@code >}}
     */
    @objid ("3cd6ba13-9c16-47a2-888d-faee727d5c9a")
    @XmlElementDecl(namespace = "", name = "SourceScope", scope = Jxbv2LinkConstraint.class)
    public JAXBElement<Jxbv2Scope> createLinkConstraintSourceScope(Jxbv2Scope value) {
        return new JAXBElement<Jxbv2Scope>(_LinkConstraintSourceScope_QNAME, Jxbv2Scope.class, Jxbv2LinkConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Jxbv2Scope }{@code >}}
     */
    @objid ("fd00eb39-edb5-4507-8205-852756de4f35")
    @XmlElementDecl(namespace = "", name = "TargetScope", scope = Jxbv2LinkConstraint.class)
    public JAXBElement<Jxbv2Scope> createLinkConstraintTargetScope(Jxbv2Scope value) {
        return new JAXBElement<Jxbv2Scope>(_LinkConstraintTargetScope_QNAME, Jxbv2Scope.class, Jxbv2LinkConstraint.class, value);
    }

}
