/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.module.context.i18n;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;

/**
 * Define the I18n services provided by the module.
 * 
 * @since 3.5
 */
@objid ("e59ed493-aa09-425b-8545-7b9a3b84b5eb")
public interface I18nSupport {
    /**
     * Get the I18n'ed description for a {@link PropertyDefinition}.
     * 
     * @param pdef the property definition to get the description for.
     * @return the property definition's description, computed from the module's i18n bundle.
     */
    @objid ("0f85d201-57c5-49c8-ba59-7e8ea0a90794")
    String getDescription(PropertyDefinition pdef);

    /**
     * Get the I18n'ed description for a {@link NoteType}.
     * @param pdef the NoteType to get the description for.
     * 
     * @return the NoteType's description, computed from the module's i18n bundle.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("504490b1-a7ad-4117-ae0d-11f5716e4f64")
    String getDescription(NoteType element);

    /**
     * Get the I18n'ed description for a {@link TagType}.
     * @param pdef the TagType to get the description for.
     * 
     * @return the TagType's description, computed from the module's i18n bundle.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("268ad375-5a65-4671-ac55-342e1f71ebac")
    String getDescription(TagType element);

    /**
     * Get the I18n'ed description for a {@link Stereotype}.
     * @param pdef the Stereotype to get the description for.
     * 
     * @return the Stereotype's description, computed from the module's i18n bundle.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("4020e891-f2b7-45a7-a923-3ed00417f322")
    String getDescription(Stereotype element);

    /**
     * Get the I18n'ed description for a {@link ResourceType}.
     * @param pdef the ResourceType to get the description for.
     * 
     * @return the ResourceType's description, computed from the module's i18n bundle.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("f06869e1-8a4e-451b-adb2-e312df710f10")
    String getDescription(ResourceType element);

    /**
     * Get the I18n'ed description for a {@link Profile}.
     * 
     * @param element the {@link Profile} to get the description for.
     * @return the profile description, computed from the module's i18n bundle.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("91666073-9899-4df4-be0e-27b210f46760")
    String getDescription(Profile element);

    /**
     * The most basic service: get the ResourceBundle of the module.
     * 
     * @return the module's i18n bundle.
     */
    @objid ("3623ca58-1888-45ee-9148-aa7a935ec02c")
    ResourceBundle getI18N();

    /**
     * Get the I18n'ed label for a Stereotype.
     * 
     * @param stereotype the stereotype to get the label for.
     * @return the stereotype's label, computed from the module's i18n bundle.
     */
    @objid ("bfb1525e-2ab1-4e6b-bd91-64c214d9531d")
    String getLabel(Stereotype stereotype);

    /**
     * Get the I18n'ed label for a TagType
     * 
     * @param tagType the tag type to get the label for.
     * @return the tag type's label, computed from the module's i18n bundle.
     */
    @objid ("943617e6-3c5a-421c-bff4-afbd7a9b4f63")
    String getLabel(TagType tagType);

    /**
     * Get the I18n'ed label for a NoteType
     * 
     * @param noteType the note type to get the label for.
     * @return the note type's label, computed from the module's i18n bundle.
     */
    @objid ("1f5a1cd0-3508-4d00-aa4f-cf09be4c6678")
    String getLabel(NoteType noteType);

    /**
     * Get the I18n'ed label for a {@link ResourceType}.
     * 
     * @param docType the resource type to get the label for.
     * @return the resource type's label, computed from the module's i18n bundle.
     */
    @objid ("a4c4e55b-4f55-4c14-a265-7b284861a0a1")
    String getLabel(ResourceType docType);

    /**
     * Get the I18n'ed label for a {@link PropertyDefinition}
     * 
     * @param pdef the property definition to get the label for.
     * @return the property definition's label, computed from the module's i18n bundle.
     */
    @objid ("60fbd824-dd3a-45f4-a200-781c9533f2d5")
    String getLabel(PropertyDefinition pdef);

    /**
     * Get the I18n'ed label for a {@link Profile}.
     * @param docType the profile to get the label for.
     * 
     * @return the profile label, computed from the module's i18n bundle.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("6ec94280-36c0-4df8-b304-db7a4c0e1cd5")
    String getLabel(Profile element);

    /**
     * Get the I18n'ed label for a given key
     * 
     * @param key the key for the desired string.
     * @return the string for the given key if any. Otherwise, the key as is.
     */
    @objid ("4c400bb4-c79c-40be-a98b-604a10df70f5")
    String getString(String key);

}
