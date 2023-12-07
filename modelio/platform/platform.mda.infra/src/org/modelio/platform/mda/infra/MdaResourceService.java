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

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.platform.mda.infra.service.impl.common.FallbackModuleI18n;

/**
 * MDA resource service provides icons, images, translated labels and description for stereotypes and MDA extensions brought by modules, and those modules themselves.
 * <p>
 * The resolution of where to find the resources is delegated to the service {@link IMdaResourceProvider} contributors registered in the service. Therefore the service implementation is based on {@link IMdaResourceProviderRegistry} where MDA contributors
 * (modules, dynamic features, ...whatever) can register their specific {@link IMdaResourceProvider} implementation in charge of providing the expected resources (I18n labels, icons and so on.)<br/>
 * When used the service first looks up for the proper provider in the registry and delegate the resource fetching to it. when no provider can be founs a fallback provider provides 'dumb' resources.
 * </p>
 * <p>
 * The returned icons (or images) only represents the raw images (no additional decorations).
 * 
 * 
 * @since 5.2
 */
@objid ("9153e358-2b5b-4ba8-80cd-e238396ebac7")
public class MdaResourceService implements IMdaResourceProvider, IMdaResourceProviderRegistry {
    @objid ("db29e8d3-d579-43d1-ac18-eef4dc31d119")
    private static final IMdaResourceProvider FALLBACK = new IMdaResourceProvider() {
    
            @Override
            public String getDescription(PropertyDefinition element) {
                return FallbackModuleI18n.instance.getDescription(element);
            }
    
            @Override
            public String getDescription(Profile element) {
                return FallbackModuleI18n.instance.getDescription(element);
            }
    
            @Override
            public String getDescription(Stereotype element) {
                return FallbackModuleI18n.instance.getDescription(element);
            }
    
            @Override
            public String getDescription(NoteType element) {
                return FallbackModuleI18n.instance.getDescription(element);
            }
    
            @Override
            public String getDescription(TagType element) {
                return FallbackModuleI18n.instance.getDescription(element);
            }
    
            @Override
            public String getDescription(ResourceType element) {
                return FallbackModuleI18n.instance.getDescription(element);
            }
    
            @Override
            public Image getIcon(Stereotype stereotype) {
                return null;
            }
    
            @Override
            public Image getImage(Stereotype stereotype) {
                return null;
            }
    
            @Override
            public String getLabel(Stereotype stereotype) {
                return FallbackModuleI18n.instance.getLabel(stereotype);
            }
    
            @Override
            public String getLabel(TagType tagType) {
                return FallbackModuleI18n.instance.getLabel(tagType);
            }
    
            @Override
            public String getLabel(NoteType noteType) {
                return FallbackModuleI18n.instance.getLabel(noteType);
            }
    
            @Override
            public String getLabel(ResourceType resourceType) {
                return FallbackModuleI18n.instance.getLabel(resourceType);
            }
    
            @Override
            public String getLabel(ModuleComponent module) {
                return module.getName();
            }
    
            @Override
            public String getLabel(PropertyDefinition pdef) {
                return FallbackModuleI18n.instance.getLabel(pdef);
            }
    
            @Override
            public String getLabel(Profile profile) {
                return FallbackModuleI18n.instance.getLabel(profile);
            }
    
            @Override
            public Image getModuleImage(ModuleComponent moduleComponent) {
                return null;
            }
    
            @Override
            public Image getIcon(Profile profile) {
                return null;
            }
    
            @Override
            public Image getImage(Profile profile) {
                return null;
            }
    
            @Override
            public Image getModuleIcon(ModuleComponent moduleComponent) {
                return null;
            }
        };

    @objid ("9592269b-4cd5-4d3e-97e1-0442c7dce6b7")
    private final Map<String, IMdaResourceProvider> mdaResourceProviders = new HashMap<>();

    @objid ("06f93516-1c33-4dce-8bff-7ecdff9060fe")
    @Override
    public String getDescription(final PropertyDefinition elt) {
        return getProvider(elt.getModule()).getDescription(elt);
    }

    @objid ("cc0fa1d0-6d57-47e0-b1f1-042f97680d08")
    @Override
    public String getDescription(final Profile elt) {
        return getProvider(elt.getOwnerModule()).getDescription(elt);
    }

    @objid ("a1eeef96-37a7-4e25-87d4-174e2163fb34")
    @Override
    public String getDescription(final Stereotype elt) {
        return getProvider(elt.getModule()).getDescription(elt);
    }

    @objid ("b24180a7-b020-4d82-8229-41db80f02aec")
    @Override
    public String getDescription(final NoteType elt) {
        return getProvider(elt.getModule()).getDescription(elt);
    }

    @objid ("6ab2f978-bfd2-4ab8-a4d7-d1f609030613")
    @Override
    public String getDescription(final TagType elt) {
        return getProvider(elt.getModule()).getDescription(elt);
    }

    @objid ("1d0cdd71-d125-4cef-b7f1-5fb55da86fd3")
    @Override
    public String getDescription(final ResourceType elt) {
        return getProvider(elt.getModule()).getDescription(elt);
    }

    /**
     * Get the icon provided by the module for a given stereotype. The life cycle of the returned image is handled by the module and the image should not be disposed.
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     */
    @objid ("0bb930d5-6fb8-46d8-b7bd-65efdd5e38df")
    @Override
    public Image getIcon(final Stereotype stereotype) {
        return getProvider(stereotype.getModule()).getIcon(stereotype);
    }

    /**
     * Get the image provided by the module for a given stereotype.The life cycle of the returned image is handled by the module and the image should not be disposed.
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     */
    @objid ("e5461bff-edae-46f4-a207-8569c1702aad")
    @Override
    public Image getImage(final Stereotype stereotype) {
        return getProvider(stereotype.getModule()).getImage(stereotype);
    }

    @objid ("a8d6c15c-6ba4-4911-84e1-79e95a699566")
    @Override
    public String getLabel(final Stereotype stereotype) {
        return getProvider(stereotype.getModule()).getLabel(stereotype);
    }

    @objid ("771890cc-178f-4a5b-9925-514d7c1056da")
    @Override
    public String getLabel(final TagType tagType) {
        return getProvider(tagType.getModule()).getLabel(tagType);
    }

    @objid ("f7a2fb3a-8474-4cba-810b-0ac49b83a65b")
    @Override
    public String getLabel(final NoteType noteType) {
        return getProvider(noteType.getModule()).getLabel(noteType);
    }

    @objid ("42e8bae8-779d-4fa8-9230-41f6a419ef83")
    @Override
    public String getLabel(final ResourceType resourceType) {
        return getProvider(resourceType.getModule()).getLabel(resourceType);
    }

    @objid ("359eb3db-b351-4e13-a931-d97c5493443d")
    @Override
    public String getLabel(final ModuleComponent module) {
        return getProvider(module).getLabel(module);
    }

    @objid ("fda17d30-31ac-4a09-bda8-721ca248db13")
    @Override
    public String getLabel(final PropertyDefinition pdef) {
        return getProvider(pdef.getModule()).getLabel(pdef);
    }

    @objid ("47e7e017-c7f1-4b07-a6f5-39e93ff25d16")
    @Override
    public String getLabel(final Profile profile) {
        return getProvider(profile.getOwnerModule()).getLabel(profile);
    }

    /**
     * Returns an Image for a module. The image life cycle is handled by the module.
     * @param module the module to get the image from.
     * @return an Image for a module. Might be <code>null</code>.
     */
    @objid ("8cd1dbac-34f4-4ad0-ae36-f044a190f3dc")
    @Override
    public Image getModuleImage(final ModuleComponent module) {
        return getProvider(module).getModuleImage(module);
    }

    /**
     * Get the icon provided by the module for a given profile. The life cycle of the returned image is handled by the module and the image should not be disposed.
     * @param profile a profile
     * @return the profile image, or <code>null</code> if the module provides none.
     */
    @objid ("50373445-a6d6-48d1-ba3a-1110de407def")
    @Override
    public Image getIcon(final Profile profile) {
        return getProvider(profile.getOwnerModule()).getIcon(profile);
    }

    @objid ("7ef17f78-ddbe-4312-9acd-f2c748955d0b")
    @Override
    public void register(ModuleComponent module, IMdaResourceProvider provider) {
        this.mdaResourceProviders.put(module.getUuid(), provider);
    }

    @objid ("9d8950a9-8e59-4c3f-9f8f-6f45489cac90")
    @Override
    public void unregister(ModuleComponent module) {
        this.mdaResourceProviders.remove(module.getUuid());
    }

    @objid ("7d3cfc0c-fb70-46e7-9328-6bf075cbb5c5")
    @Override
    public IMdaResourceProvider getProvider(ModuleComponent module) {
        if (module == null) {
            return MdaResourceService.FALLBACK;
        } else {
            IMdaResourceProvider provider = this.mdaResourceProviders.get(module.getUuid());
            if (provider == null) {
                provider = new LocalModuleMdaResourceProvider(module);
                register(module, provider);
            }
            return provider;
        }
        
    }

    @objid ("9a4f2396-093d-4185-b368-492bdbdfa482")
    public void reset() {
        // Dispose LocalModuleMdaResourceProvider instances
        this.mdaResourceProviders.forEach((key, value) -> {
            if (value instanceof LocalModuleMdaResourceProvider) {
                ((LocalModuleMdaResourceProvider) value).dispose();
            }
        });
        
        // Clear all providers
        this.mdaResourceProviders.clear();
        
    }

    @objid ("6a7ec843-b8b5-4249-91d1-074b69267114")
    @Override
    public Image getImage(Profile profile) {
        return getProvider(profile.getOwnerModule()).getImage(profile);
    }

    @objid ("696685b2-83ff-44ce-8a47-dd9a512eed34")
    @Override
    public Image getModuleIcon(ModuleComponent moduleComponent) {
        return getProvider(moduleComponent).getModuleIcon(moduleComponent);
    }

}
