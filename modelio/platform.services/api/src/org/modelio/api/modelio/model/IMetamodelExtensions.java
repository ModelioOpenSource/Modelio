/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * This interface is used to get the module extensions.<br>
 * It's not possible to add or update such extensions using this class.
 * <p>
 * The known extensions are the following:
 * <ul>
 * <li>Stereotypes ({@link Stereotype})</li>
 * <li>Tag types ({@link TagType})</li>
 * <li>Note types ({@link NoteType})</li>
 * <li>Resource types ({@link ResourceType})</li>
 * </ul>
 * </p>
 * <p>
 * Also provides i18n for all those extensions.
 * </p>
 */
@objid ("01f40d58-0000-7337-0000-000000000000")
public interface IMetamodelExtensions {
    /**
     * Find stereotypes by name and metaclass
     * 
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass the metaclass .
     * @return the found stereotypes. Might be empty.
     */
    @objid ("c9bfbcad-113b-11e2-8ccf-002564c97630")
    List<Stereotype> findStereotypes(String stereotypeName, MClass metaclass);

    @objid ("c9c21e0c-113b-11e2-8ccf-002564c97630")
    Stereotype getStereotype(String stereotypeName, MClass metaclass);

    /**
     * Find stereotypes by name and metaclass
     * 
     * @param moduleName the name of the module owing the stereotype, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass the metaclass .
     * @return the found stereotypes. Might be empty.
     */
    @objid ("c9c21e12-113b-11e2-8ccf-002564c97630")
    List<Stereotype> findStereotypes(String moduleName, String stereotypeName, MClass metaclass);

    @objid ("c9c21e1b-113b-11e2-8ccf-002564c97630")
    Stereotype getStereotype(String moduleName, String stereotypeName, MClass metaclass);

    /**
     * Find note types by metaclass
     * 
     * @param noteTypeName the note type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any note type.
     * @param metaclass the metaclass .
     * @return the found note types. Might be empty.
     */
    @objid ("c9c21e22-113b-11e2-8ccf-002564c97630")
    List<NoteType> findNoteTypes(String noteTypeName, MClass metaclass);

    @objid ("c9c21e2a-113b-11e2-8ccf-002564c97630")
    NoteType getNoteType(String moduleName, String noteTypeName, MClass metaclass);

    /**
     * Find note types by name and metaclass
     * 
     * @param moduleName the name of the module owing the note type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param noteTypeName the note type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any note type.
     * @param metaclass the metaclass .
     * @return the found note types. Might be empty.
     */
    @objid ("c9c21e31-113b-11e2-8ccf-002564c97630")
    List<NoteType> findNoteTypes(String moduleName, String noteTypeName, MClass metaclass);

    @objid ("c9c21e3a-113b-11e2-8ccf-002564c97630")
    NoteType getNoteType(Stereotype stereotype, String noteTypeName);

    /**
     * Find resource types by name
     * 
     * @param externDocumentTypeName the resource type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any resource type.
     * @param metaclass the metaclass .
     * @return the found resource types. Might be empty.
     */
    @objid ("c9c21e3e-113b-11e2-8ccf-002564c97630")
    List<ResourceType> findExternDocumentTypes(String externDocumentTypeName, MClass metaclass);

    /**
     * Find resource types by name and metaclass
     * 
     * @param moduleName the name of the module owing the resource type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param externDocumentTypeName the resource type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any resource type.
     * @param metaclass the metaclass .
     * @return the found resource types. Might be empty.
     */
    @objid ("c9c21e46-113b-11e2-8ccf-002564c97630")
    List<ResourceType> findExternDocumentTypes(String moduleName, String externDocumentTypeName, MClass metaclass);

    @objid ("c9c21e4f-113b-11e2-8ccf-002564c97630")
    ResourceType getExternDocumentType(String moduleName, String externDocumentTypeName, MClass metaclass);

    @objid ("c9c21e56-113b-11e2-8ccf-002564c97630")
    ResourceType getExternDocumentType(Stereotype stereotype, String externDocumentTypeName);

    /**
     * Find note types by name
     * 
     * @param tagTypeName the note type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any note type.
     * @param metaclass the metaclass .
     * @return the found note types. Might be empty.
     */
    @objid ("c9c21e5a-113b-11e2-8ccf-002564c97630")
    List<TagType> findTagTypes(String tagTypeName, MClass metaclass);

    /**
     * Find note types by name and metaclass
     * 
     * @param moduleName the name of the module owing the tag type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param tagTypeName the note type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any note type.
     * @param metaclass the metaclass .
     * @return the found note types. Might be empty.
     */
    @objid ("c9c21e62-113b-11e2-8ccf-002564c97630")
    List<TagType> findTagTypes(String moduleName, String tagTypeName, MClass metaclass);

    @objid ("c9c21e6b-113b-11e2-8ccf-002564c97630")
    TagType getTagType(String moduleName, String tagTypeName, MClass metaclass);

    @objid ("c9c21e72-113b-11e2-8ccf-002564c97630")
    TagType getTagType(Stereotype stereotype, String tagTypeName);

    /**
     * Get the I18n'ed label for a {@link Stereotype}.
     * 
     * @param stereotype the stereotype to get the label for.
     * @return the stereotype's label, computed by its module.
     */
    @objid ("e9eb2474-7f9e-4e1e-ac78-cf4a8940140b")
    String getLabel(Stereotype stereotype);

    /**
     * Get the I18n'ed label for a {@link TagType}.
     * 
     * @param tagType the tag type to get the label for.
     * @return the tag type's label, computed by its module.
     */
    @objid ("08da7b03-374a-4537-ae78-b8422694c7ec")
    String getLabel(TagType tagType);

    /**
     * Get the I18n'ed label for a {@link NoteType}.
     * 
     * @param noteType the note type to get the label for.
     * @return the note type's label, computed by its module.
     */
    @objid ("624668bd-4c5f-411a-bd36-d6d025ee3c32")
    String getLabel(NoteType noteType);

    /**
     * Get the I18n'ed label for a {@link ResourceType}.
     * 
     * @param resourceType the resource type to get the label for.
     * @return the resource type's label, computed by its module.
     */
    @objid ("d276e9e7-2ba2-4892-84fc-a7684047e267")
    String getLabel(ResourceType resourceType);

    /**
     * Get the I18n'ed label for a {@link PropertyDefinition}.
     * 
     * @param pdef the property definition to get the label for.
     * @return the property definition's label, computed by its module.
     */
    @objid ("0487db12-64cd-4375-bb1a-eb4c2e7a2a7f")
    String getLabel(PropertyDefinition pdef);

    @objid ("56f77f5f-6eca-41c1-b3e6-d9b27854d919")
    String getDescription(PropertyDefinition pdef);

}
