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
package org.modelio.platform.mda.infra;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.IModule;
import org.modelio.api.module.IModule.ImageType;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;

@objid ("299d8af6-0bb6-455e-914a-91b0c5075964")
public class ModuleMdaResourceProvider implements IMdaResourceProvider {
    @objid ("d190d352-f322-4481-ac0e-d37d42dd0a72")
    private final IModule iModule;

    @objid ("afde18f3-690f-4f10-9df9-d90a1e0a122c")
    public  ModuleMdaResourceProvider(IModule iModule) {
        this.iModule = iModule;
    }

    @objid ("14f02464-7275-4ffb-8df0-a5522ad7be3e")
    @Override
    public String getDescription(PropertyDefinition element) {
        return this.iModule.getModuleContext().getI18nSupport().getDescription(element);
    }

    @objid ("cb1b380c-6782-46ba-a125-992d827b25b0")
    @Override
    public String getDescription(Profile element) {
        return this.iModule.getModuleContext().getI18nSupport().getDescription(element);
    }

    @objid ("baa02d61-8318-495b-9105-0d5712541a1f")
    @Override
    public String getDescription(Stereotype element) {
        return this.iModule.getModuleContext().getI18nSupport().getDescription(element);
    }

    @objid ("cbb5b1d3-1773-442f-a4d9-1cbda416bf3f")
    @Override
    public String getDescription(NoteType element) {
        return this.iModule.getModuleContext().getI18nSupport().getDescription(element);
    }

    @objid ("aff0412b-4594-46f5-99c2-ea5fd00e8926")
    @Override
    public String getDescription(TagType element) {
        return this.iModule.getModuleContext().getI18nSupport().getDescription(element);
    }

    @objid ("4d816759-8200-4e2b-9596-b969319dcd01")
    @Override
    public String getDescription(ResourceType element) {
        return this.iModule.getModuleContext().getI18nSupport().getDescription(element);
    }

    @objid ("6f7ef07a-f2b8-4f8a-b143-669e7905dc91")
    @Override
    public Image getIcon(Stereotype stereotype) {
        return this.iModule.getImage(stereotype, ImageType.ICON);
    }

    @objid ("5d5887f7-406a-4a0d-b85d-75896eabee43")
    @Override
    public Image getImage(Stereotype stereotype) {
        return this.iModule.getImage(stereotype, ImageType.IMAGE);
    }

    @objid ("4735917b-9c44-4943-8919-d3befeb96afd")
    @Override
    public String getLabel(Stereotype stereotype) {
        return this.iModule.getModuleContext().getI18nSupport().getLabel(stereotype);
    }

    @objid ("094251d8-4f26-4025-a879-9fe3bec374a2")
    @Override
    public String getLabel(TagType tagType) {
        return this.iModule.getModuleContext().getI18nSupport().getLabel(tagType);
    }

    @objid ("dd0e566d-ec80-447d-a6c0-64331fffda45")
    @Override
    public String getLabel(NoteType noteType) {
        return this.iModule.getModuleContext().getI18nSupport().getLabel(noteType);
    }

    @objid ("d707d55c-4ea2-452e-9774-1ae1657e0e2a")
    @Override
    public String getLabel(ResourceType resourceType) {
        return this.iModule.getModuleContext().getI18nSupport().getLabel(resourceType);
    }

    @objid ("6ba76f73-ec91-4edf-bed6-6668da68158a")
    @Override
    public String getLabel(ModuleComponent module) {
        return this.iModule.getLabel();
    }

    @objid ("f57dcb4c-eee7-482f-bb48-61cc01247fc6")
    @Override
    public String getLabel(PropertyDefinition pdef) {
        return this.iModule.getModuleContext().getI18nSupport().getLabel(pdef);
    }

    @objid ("096e68df-63cc-4af6-bf33-afd64497414e")
    @Override
    public String getLabel(Profile profile) {
        return this.iModule.getModuleContext().getI18nSupport().getLabel(profile);
    }

    @objid ("77b78e55-fe76-449c-acd8-b07771153faf")
    @Override
    public Image getModuleImage(ModuleComponent moduleComponent) {
        return this.iModule.getModuleImage();
    }

    @objid ("69b9f477-2b0d-43de-8e70-b84b55708666")
    @Override
    public Image getIcon(Profile profile) {
        return this.iModule.getImage(profile, ImageType.ICON);
    }

    @objid ("2dde06ac-cdb9-48c1-8651-97889c1b27f8")
    @Override
    public Image getImage(Profile profile) {
        return this.iModule.getImage(profile, ImageType.IMAGE);
    }

    @objid ("32180ffb-6a5e-486b-9e5d-eb8ee0fd63b9")
    @Override
    public Image getModuleIcon(ModuleComponent moduleComponent) {
        return this.iModule.getModuleImage();
    }

}
