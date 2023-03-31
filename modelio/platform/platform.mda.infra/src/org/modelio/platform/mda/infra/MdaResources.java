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

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.impl.ModuleManagementService;
import org.modelio.platform.ui.swt.QualifiedImage;

/**
 * Provides icons, images, translated labels and description for stereotypes and MDA extensions brought by modules, and those modules themselves.
 * <p>
 * The returned icons (or images) only represents the raw images (no additional decorations).
 */
@objid ("50ac34ae-177d-11e2-aa0d-002564c97630")
public class MdaResources {
    @objid ("c2243459-a53a-4182-8b6c-ef798e74dff2")
    private static IMdaResourceProvider mdaResourceProvider;

    @objid ("5bee62f0-02fd-49ad-bc76-ad3b4689a245")
    private static IModuleService moduleService;

    @objid ("e314baf1-eaf0-4fb4-a67f-18e37935e939")
    public static void initialize(ModuleManagementService moduleService, IMdaResourceProvider mdaResourceProvider) {
        MdaResources.moduleService = moduleService;
        MdaResources.mdaResourceProvider = mdaResourceProvider;
        
    }

    @objid ("f3c3e908-ab1a-419e-adfc-12dc2c4542bb")
    public static String getDescription(PropertyDefinition element) {
        return mdaResourceProvider.getDescription(element);
    }

    @objid ("da4694b8-085e-486d-b282-7c6b20ec7203")
    public static String getDescription(Profile element) {
        return mdaResourceProvider.getDescription(element);
    }

    @objid ("f86f62fc-22ce-4a4e-9c2d-838538bbb68e")
    public static String getDescription(Stereotype element) {
        return mdaResourceProvider.getDescription(element);
    }

    @objid ("f7f27bab-edba-4f12-9a10-a5afcdbe33ed")
    public static String getDescription(NoteType element) {
        return mdaResourceProvider.getDescription(element);
    }

    @objid ("6c8c8b98-deba-4c5d-8d64-0f82c0cce3e6")
    public static String getDescription(TagType element) {
        return mdaResourceProvider.getDescription(element);
    }

    @objid ("bb82cdb0-5860-4b19-9f30-cb4e6a54533c")
    public static String getDescription(ResourceType element) {
        return mdaResourceProvider.getDescription(element);
    }

    @objid ("df017c1e-a6c0-4323-87d4-ab391aaaab8a")
    public static Image getIcon(Stereotype stereotype) {
        return mdaResourceProvider.getIcon(stereotype);
    }

    @objid ("3720ef55-3bd7-465b-b3fc-84513ff442ce")
    public static Image getImage(Stereotype stereotype) {
        return mdaResourceProvider.getImage(stereotype);
    }

    @objid ("b7936503-aeef-4ca2-95fc-8388d6a6162e")
    public static String getLabel(Stereotype stereotype) {
        return mdaResourceProvider.getLabel(stereotype);
    }

    @objid ("50673fd0-0135-4895-b14b-c7a8c531a115")
    public static String getLabel(TagType tagType) {
        return mdaResourceProvider.getLabel(tagType);
    }

    @objid ("be6cd76b-af01-4ef5-ad3a-675e47c07972")
    public static String getLabel(NoteType noteType) {
        return mdaResourceProvider.getLabel(noteType);
    }

    @objid ("929b01f4-47aa-4baa-a17f-56b4ff7592b6")
    public static String getLabel(ResourceType resourceType) {
        return mdaResourceProvider.getLabel(resourceType);
    }

    @objid ("3caae557-036b-4c9b-9528-19fc70ebe08a")
    public static String getLabel(ModuleComponent module) {
        return mdaResourceProvider.getLabel(module);
    }

    @objid ("a2a6d85a-667f-485a-b28b-defaef463470")
    public static String getLabel(PropertyDefinition pdef) {
        return mdaResourceProvider.getLabel(pdef);
    }

    @objid ("e0fb074a-42af-47ae-981e-329e49650e60")
    public static String getLabel(Profile profile) {
        return mdaResourceProvider.getLabel(profile);
    }

    @objid ("e0bbb16d-ba4f-40a2-bf74-8dc973aa2cd7")
    public static Image getModuleImage(ModuleComponent moduleComponent) {
        return mdaResourceProvider.getModuleImage(moduleComponent);
    }

    @objid ("31fd4c40-ca23-42db-b84c-f235e6677a36")
    public static Image getModuleIcon(ModuleComponent moduleComponent) {
        return mdaResourceProvider.getModuleIcon(moduleComponent);
    }

    @objid ("c9e9ced9-c5d2-451b-9a1f-1b0499e29f6c")
    public static Image getIcon(Profile profile) {
        return mdaResourceProvider.getIcon(profile);
    }

    @objid ("2f994b48-3461-4318-a5b9-23f67250cd98")
    public static String getQualifiedLabel(ModelElement element, IPeerModule preferredProvider) {
        return SmartSolver.getStereotypeLabel(element, preferredProvider);
    }

    @objid ("717bc49b-0c18-4a11-bd00-5a09e7238181")
    public static QualifiedImage getQualifiedIcon(ModelElement element, IPeerModule preferredProvider) {
        return SmartSolver.getQualifiedIcon(element, preferredProvider);
    }

    @objid ("9baa33dc-7725-4787-aed9-49d49a7cae59")
    public static QualifiedImage getQualifiedImage(ModelElement element, IPeerModule preferredProvider) {
        return SmartSolver.getQualifiedImage(element, preferredProvider);
    }

    /**
     * Compute a priority for a module component, according to its position in the "started modules" list.
     */
    @objid ("b824b554-60ba-42c8-b85f-324ceacc99eb")
    public static int getModulePriority(final ModuleComponent moduleComponent) {
        return SmartSolver.getModulePriority(moduleComponent);
    }

    @objid ("793edc69-8d55-4d64-8eb9-b62007293880")
    private static class SmartSolver {
        @objid ("2655e537-61f8-4c38-b2f8-68a69df0caf8")
        private static String computeIconQualifier(Stereotype stereotype) {
            return String.format("ICO_%s", stereotype.getUuid());
        }

        @objid ("29b5d1c4-85db-43f2-9488-6d7c23689345")
        private static String computeImageQualifier(Stereotype stereotype) {
            return String.format("IMG_%s", stereotype.getUuid());
        }

        @objid ("f5d12d7e-1f7d-4034-985e-746c26871730")
        public static int getModulePriority(final ModuleComponent moduleComponent) {
            if (MdaResources.moduleService != null) {
                final List<IRTModule> startedModules = MdaResources.moduleService.getStartedModules();
                for (int i = 0; i < startedModules.size(); i++) {
                    final IRTModule startedModule = startedModules.get(i);
                    if (Objects.equals(moduleComponent, startedModule.getModel())) {
                        return i;
                    }
                }
            }
            return Integer.MAX_VALUE;
        }

        @objid ("26c357f3-fa2c-4076-9b96-47c8d45d2ac0")
        public static QualifiedImage getQualifiedIcon(ModelElement element, IPeerModule preferredProvider) {
            if (preferredProvider == null) {
                return element.getExtension().stream()
                        // Ignore shell and orphan stereotypes
                        .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                        // Ignore stereotypes having no image defined
                        .filter(stereotype -> stereotype.getIcon() != null && !stereotype.getIcon().isEmpty())
                        // Sort stereotypes according to the "started modules" order
                        .sorted((stereotype1, stereotype2) -> Integer.compare(getModulePriority(stereotype1.getModule()), getModulePriority(stereotype2.getModule())))
                        // Get the first stereotype
                        .findFirst()
                        // Return its QualifiedImage
                        .map(stereotype -> {
                            Image icon = MdaResources.getIcon(stereotype);
                            return icon != null ? new QualifiedImage(icon, computeIconQualifier(stereotype)) : null;
                        })
                        .orElse(null);
            } else {
                return element.getExtension().stream()
                        // Ignore shell and orphan stereotypes
                        .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                        // Keep stereotypes from the preferred provider
                        .filter(stereotype -> stereotype.getModule().getName().equals(preferredProvider.getName()))
                        // Ignore stereotypes having no image defined
                        .filter(stereotype -> stereotype.getIcon() != null && !stereotype.getIcon().isEmpty())
                        // Get the first stereotype
                        .findFirst()
                        // Return its QualifiedImage
                        .map(stereotype -> {
                            Image icon = MdaResources.getIcon(stereotype);
                            return icon != null ? new QualifiedImage(icon, computeIconQualifier(stereotype)) : null;
                        })
                        .orElse(null);
            }
            
        }

        @objid ("2392fd6b-ea2c-45a1-bac2-8362c1091f08")
        public static QualifiedImage getQualifiedImage(ModelElement element, IPeerModule preferredProvider) {
            if (preferredProvider == null) {
                return element.getExtension().stream()
                        // Ignore shell and orphan stereotypes
                        .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                        // Ignore stereotypes having no image defined
                        .filter(stereotype -> stereotype.getImage() != null && !stereotype.getImage().isEmpty())
                        // Sort stereotypes according to the "started modules" order
                        .sorted((stereotype1, stereotype2) -> Integer.compare(getModulePriority(stereotype1.getModule()), getModulePriority(stereotype2.getModule())))
                        // Get the first stereotype
                        .findFirst()
                        // Return its Image
                        .map(stereotype -> {
                            Image image = MdaResources.getImage(stereotype);
                            return image != null ? new QualifiedImage(image, computeImageQualifier(stereotype)) : null;
                        })
                        .orElse(null);
            } else {
                return element.getExtension().stream()
                        // Ignore shell and orphan stereotypes
                        .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                        // Keep stereotypes from the preferred provider
                        .filter(stereotype -> stereotype.getModule().getName().equals(preferredProvider.getName()))
                        // Ignore stereotypes having no image defined
                        .filter(stereotype -> stereotype.getImage() != null && !stereotype.getImage().isEmpty())
                        // Get the first stereotype
                        .findFirst()
                        // Return its Image
                        .map(stereotype -> {
                            Image image = MdaResources.getImage(stereotype);
                            return image != null ? new QualifiedImage(image, computeImageQualifier(stereotype)) : null;
                        })
                        .orElse(null);
            }
            
        }

        /**
         * Returns the type to use for an element having stereotypes.
         * <p>
         * Choice is based on the current active expertise, giving the highest priority to the stereotypes belonging to the first started module.
         * </p>
         * <p>
         * Returns <code>null</code> if no stereotypes are found.
         * </p>
         */
        @objid ("dbafd3e5-b89c-4ecf-8bbb-c8cf0dce86bc")
        public static String getStereotypeLabel(final ModelElement modelElement, IPeerModule preferredProvider) {
            if (preferredProvider == null) {
                return modelElement.getExtension().stream()
                        // Ignore shell and orphan stereotypes
                        .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                        // Sort stereotypes according to the "started modules" order
                        .sorted((stereotype1, stereotype2) -> Integer.compare(MdaResources.getModulePriority(stereotype1.getModule()), MdaResources.getModulePriority(stereotype2.getModule())))
                        // Get the first stereotype
                        .findFirst()
                        // Return its label
                        .map(stereotype -> MdaResources.getLabel(stereotype))
                        .orElse(null);
            } else {
                return modelElement.getExtension().stream()
                        // Ignore shell and orphan stereotypes
                        .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                        // Keep stereotypes from the preferred provider
                        .filter(stereotype -> stereotype.getModule().getName().equals(preferredProvider.getName()))
                        // Get the first stereotype
                        .findFirst()
                        // Return its label
                        .map(stereotype -> {
                            return  MdaResources.getLabel(stereotype);
                        })
                        .orElse(null);
            }
            
        }

    }

}
