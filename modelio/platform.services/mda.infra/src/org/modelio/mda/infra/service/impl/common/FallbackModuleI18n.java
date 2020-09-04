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

package org.modelio.mda.infra.service.impl.common;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.i18n.I18nSupport;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;

/**
 * Fallback implementation of {@link I18nSupport} that makes best effort by looking at the model.
 * @author cma
 * @since Modelio 3.8 : merged many fallback implementations in this class.
 */
@objid ("bc17c6ab-d911-408e-bd29-444e624c0e73")
public final class FallbackModuleI18n implements I18nSupport {
    @objid ("6a177075-8768-4d49-a6fe-4fe64f978312")
    public static I18nSupport instance = new FallbackModuleI18n();

    @objid ("f7b665eb-6276-4210-bbfc-d901dc321202")
    @Override
    public String getLabel(Stereotype stereotype) {
        return stereotype.getLabelKey().isEmpty() ? stereotype.getName()
                                                                    : stereotype.getLabelKey();
    }

    @objid ("f927f100-15a4-4338-94bf-3ba0782f9c60")
    @Override
    public String getLabel(TagType tagType) {
        return tagType.getLabelKey().isEmpty() ? tagType.getName() : tagType
                                                                    .getLabelKey();
    }

    @objid ("74866ecd-9d1c-47a1-af90-2ecf0a85d2e3")
    @Override
    public String getLabel(NoteType noteType) {
        return noteType.getLabelKey().isEmpty() ? noteType.getName() : noteType
                                                                    .getLabelKey();
    }

    @objid ("be530a58-bf62-4b60-bcad-28faf89a9199")
    @Override
    public String getLabel(ResourceType docType) {
        return docType.getLabelKey().isEmpty() ? docType.getName() : docType
                                                                    .getLabelKey();
    }

    @objid ("3099982a-4de4-4b12-9dd3-3267afcb3fa1")
    @Override
    public String getString(String key) {
        return key;
    }

    @objid ("2a01c9ad-0a3a-4b86-b834-d3f73e48660d")
    @Override
    public ResourceBundle getI18N() {
        return null;
    }

    @objid ("3b5b7d94-2b98-4903-94fe-f6949dcc1709")
    @Override
    public String getLabel(PropertyDefinition pdef) {
        return pdef.getName();
    }

    @objid ("9e49ff91-11a4-4df2-9d60-bd89fc15b14f")
    @Override
    public String getDescription(PropertyDefinition element) {
        return element.getNoteContent("ModelerModule", "description");
    }

    @objid ("04904d88-44e9-485b-a6aa-15e8bc5316c0")
    @Override
    public String getDescription(NoteType element) {
        return element.getNoteContent("ModelerModule", "description");
    }

    @objid ("00c977ef-1b25-4f72-acf8-ed27716cf432")
    @Override
    public String getDescription(TagType element) {
        return element.getNoteContent("ModelerModule", "description");
    }

    @objid ("3fbb721f-ac54-426b-9134-dea3e461cbc2")
    @Override
    public String getDescription(Stereotype element) {
        return element.getNoteContent("ModelerModule", "description");
    }

    @objid ("9bf1ad7b-cd6d-4657-96f2-226892fd4e44")
    @Override
    public String getDescription(ResourceType element) {
        return element.getNoteContent("ModelerModule", "description");
    }

    @objid ("a9abef51-0d00-48ff-af2f-a933d767a3fa")
    @Override
    public String getDescription(Profile element) {
        return element.getNoteContent("ModelerModule", "description");
    }

    @objid ("edb00dec-b293-4ea3-84fb-bcf1dd0105f1")
    @Override
    public String getLabel(Profile element) {
        return element.getName();
    }

}
