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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.script.ScriptEngine;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageDataProvider;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.IModelioContext;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.context.i18n.I18nSupport;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.context.project.IProjectStructure;
import org.modelio.api.module.license.ILicenseInfos;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.visitors.IAbstractInfrastructureVisitor;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.impl.common.FallbackModuleI18n;
import org.modelio.platform.mda.infra.service.impl.common.UndefinedLicenseInfos;
import org.modelio.platform.ui.swt.QualifiedImage;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Provides icons, images, translated labels and description for stereotypes and MDA extensions brought by modules, and those modules themselves.
 * <p>
 * The returned icons (or images) only represents the raw images (no additional decorations).
 */
@objid ("50ac34ae-177d-11e2-aa0d-002564c97630")
public class ModuleI18NService {
    @objid ("f37e6cdc-6f5a-4d53-87b5-91ba304ab8b9")
    private static final Map<ModuleComponent, IModule> localModules = new HashMap<>();

    @objid ("b2231e15-10ac-4162-9384-382ed8ca15f7")
    private static IModuleService moduleService;

    @objid ("9f6abd2a-6a8d-4370-a184-049e4a385388")
    public static String getDescription(final PropertyDefinition element) {
        return ModuleI18NService.getI18n(element).getDescription(element);
    }

    @objid ("cac3ac76-8932-4f6c-93b2-abd1f2dc8d3b")
    public static String getDescription(final Profile element) {
        return ModuleI18NService.getI18n(element).getDescription(element);
    }

    @objid ("35a3268c-d09c-4762-b3b8-56ddfd139adc")
    public static String getDescription(final Stereotype element) {
        return ModuleI18NService.getI18n(element).getDescription(element);
    }

    @objid ("16f2f611-e3b0-4b71-acb6-74c0d753a9f5")
    public static String getDescription(final NoteType element) {
        return ModuleI18NService.getI18n(element).getDescription(element);
    }

    @objid ("72bc75e4-9482-454a-bf51-2898e5ce906c")
    public static String getDescription(final TagType element) {
        return ModuleI18NService.getI18n(element).getDescription(element);
    }

    @objid ("05e83a62-45b8-402c-90ab-316a7b800b93")
    public static String getDescription(final ResourceType element) {
        return ModuleI18NService.getI18n(element).getDescription(element);
    }

    @objid ("1502d196-2790-4403-92f4-363756e18639")
    public static I18nSupport getI18n(final ModelElement mdaElement) {
        final IModule iModule = ModuleI18NService.getIModule(ModuleI18NService.getModule(mdaElement));
        if (iModule != null) {
            return iModule.getModuleContext().getI18nSupport();
        } else {
            return FallbackModuleI18n.instance;
        }
    }

    /**
     * Get the icon provided by the module for a given stereotype. The module should return an icon if the stereotype is provided by itself, <code>null</code> in the other case. The image life cycle is handled by the module.
     * 
     * @param moduleComponent the module to get the image from.
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     * @deprecated use {@link #getIcon(Stereotype)}
     */
    @objid ("5b4f8fc7-177d-11e2-aa0d-002564c97630")
    @Deprecated
    public static Image getIcon(final ModuleComponent moduleComponent, final Stereotype stereotype) {
        IModule iModule = ModuleI18NService.getIModule(moduleComponent);
        if (iModule == null) {
            // No valid module, look for a local module instead
            iModule = ModuleI18NService.getLocalModule(moduleComponent);
        }
        return iModule.getImage(stereotype, IModule.ImageType.ICON);
    }

    /**
     * Get the icon provided by the module for a given stereotype. The life cycle of the returned image is handled by the module and the image should not be disposed.
     * 
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     */
    @objid ("110a8a4f-2480-4cad-b8bb-ea28a916f5b6")
    public static Image getIcon(final Stereotype stereotype) {
        final ModuleComponent moduleComponent = stereotype.getModule();
        if (moduleComponent == null) {
            return null;
        }
        IModule iModule = ModuleI18NService.getIModule(moduleComponent);
        if (iModule == null) {
            iModule = ModuleI18NService.getLocalModule(moduleComponent);
        }
        return iModule.getImage(stereotype, IModule.ImageType.ICON);
    }

    /**
     * Returns the icon (usually 16*16) to use for an element having stereotypes.
     * <p>
     * When preferred provider is null, choice is based on the current active expertises, giving the highest priority to the stereotypes belonging to the first started module if any, or <code>null</code>.
     * </p>
     * <p>
     * Otherwise, returns the icon of the first stereotype belonging to the preferred provider if any, or <code>null</code>.
     * </p>
     * 
     * @param element the element to get the icon for.
     * @param preferredProvider the module the icon must belong to. If <code>null</code>, any module will do.
     */
    @objid ("53d2b34e-f3ed-4974-af3f-d5aab44a7e64")
    public static Image getIcon(final ModelElement element, final IPeerModule preferredProvider) {
        QualifiedImage qualifiedImage = getQualifiedIcon(element, preferredProvider);
        return qualifiedImage != null ? qualifiedImage.getImage() : null;
    }

    /**
     * Returns the image (usually 32*32) to use for an element having stereotypes.
     * <p>
     * When preferred provider is null, choice is based on the current active expertises, giving the highest priority to the stereotypes belonging to the first started module if any, or <code>null</code>.
     * </p>
     * <p>
     * Otherwise, returns the image of the first stereotype belonging to the preferred provider if any, or <code>null</code>.
     * </p>
     * 
     * @param element the element to get the image for.
     * @param preferredProvider the module the image must belong to. If <code>null</code>, any module will do.
     */
    @objid ("b2918ff7-de7e-40c3-94db-e5dc63b9d368")
    public static Image getImage(final ModelElement element, final IPeerModule preferredProvider) {
        QualifiedImage qualifiedImage = getQualifiedImage(element, preferredProvider);
        return qualifiedImage != null ? qualifiedImage.getImage() : null;
    }

    /**
     * Get the image provided by the module for a given stereotype.The life cycle of the returned image is handled by the module and the image should not be disposed.
     * 
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     */
    @objid ("1ad81b31-ffd8-4f02-924b-034b5d178672")
    public static Image getImage(final Stereotype stereotype) {
        final ModuleComponent moduleComponent = stereotype.getModule();
        if (moduleComponent == null) {
            return null;
        }
        IModule iModule = ModuleI18NService.getIModule(moduleComponent);
        if (iModule == null) {
            iModule = ModuleI18NService.getLocalModule(moduleComponent);
        }
        return iModule.getImage(stereotype, IModule.ImageType.IMAGE);
    }

    @objid ("cb0513c4-174f-4258-bae2-d9f886ff7ebe")
    public static String getLabel(final Stereotype stereotype) {
        final IModule iModule = ModuleI18NService.getIModule(stereotype.getModule());
        if (iModule != null) {
            return iModule.getModuleContext().getI18nSupport().getLabel(stereotype);
        }
        return stereotype.getName();
    }

    @objid ("44ab8de4-e165-4d2c-9bf7-9e873f964013")
    public static String getLabel(final TagType tagType) {
        final IModule iModule = ModuleI18NService.getIModule(tagType.getModule());
        if (iModule != null) {
            return iModule.getModuleContext().getI18nSupport().getLabel(tagType);
        }
        return tagType.getName();
    }

    @objid ("154229ff-25cf-44a5-8f40-e570921f1e9e")
    public static String getLabel(final NoteType noteType) {
        final IModule iModule = ModuleI18NService.getIModule(noteType.getModule());
        if (iModule != null) {
            return iModule.getModuleContext().getI18nSupport().getLabel(noteType);
        }
        return noteType.getName();
    }

    @objid ("d39152b3-5cc1-439f-b438-107b6162e400")
    public static String getLabel(final ResourceType resourceType) {
        final IModule iModule = ModuleI18NService.getIModule(resourceType.getModule());
        if (iModule != null) {
            return iModule.getModuleContext().getI18nSupport().getLabel(resourceType);
        }
        return resourceType.getName();
    }

    @objid ("a028ae95-45bf-44cc-8ab4-19bab9d7cf2c")
    public static String getLabel(final ModuleComponent module) {
        final IModule iModule = ModuleI18NService.getIModule(module);
        if (iModule != null) {
            return iModule.getLabel();
        }
        return module.getName();
    }

    @objid ("1efe5b40-7fd2-4e9c-9a7a-8a347ff1e8bc")
    public static String getLabel(final PropertyDefinition pdef) {
        return ModuleI18NService.getI18n(pdef).getLabel(pdef);
    }

    @objid ("6a26aaa7-bf68-492b-86d9-0eb7319e627f")
    public static String getLabel(final Profile element) {
        return ModuleI18NService.getI18n(element).getLabel(element);
    }

    /**
     * Returns an Image for a module. The image life cycle is handled by the module.
     * 
     * @param moduleComponent the module to get the image from.
     * @return an Image for a module. Might be <code>null</code>.
     */
    @objid ("999e5d4c-178f-11e2-aa0d-002564c97630")
    public static Image getModuleImage(final ModuleComponent moduleComponent) {
        final IModule iModule = ModuleI18NService.getIModule(moduleComponent);
        if (iModule != null) {
            return iModule.getModuleImage();
        }
        return null;
    }

    /**
     * Get the functional priority of the module.
     * 
     * The priority values define an ordering of the modules that allows Modelio to give more importance to some of them, for example to propose their commands or extensions in first positions in the GUI...
     * 
     * Values: 0 is understood as the highest priority
     * 
     * @return the current functional priority level of the module
     */
    @objid ("15bb57bc-0740-4974-950e-d308d8b2ac39")
    public static int getPriority(final ModuleComponent module) {
        for (final IRTModule rtModule : ModuleI18NService.moduleService.getStartedModules()) {
            if (rtModule.getModel().equals(module)) {
                return rtModule.getPriority();
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Returns the type to use for an element having stereotypes.
     * <p>
     * Choice is based on the current active expertises, giving the highest priority to the stereotypes belonging to the first started module.
     * </p>
     * <p>
     * Returns <code>null</code> if no stereotypes are found.
     * </p>
     */
    @objid ("d1555b10-0138-4ffc-9ed2-80f5b533aae9")
    public static String getStereotypeLabel(final ModelElement modelElement) {
        return modelElement.getExtension().stream()
                // Ignore shell stereotypes
                .filter(Stereotype::isValid)
                // Ignore orphan stereotypes
                .filter(stereotype -> stereotype.getModule() != null)
                // Sort stereotypes according to the "started modules" order
                .sorted((stereotype1, stereotype2) -> Integer.compare(ModuleI18NService.getModulePriority(stereotype1.getModule()), ModuleI18NService.getModulePriority(stereotype2.getModule())))
                // Get the first stereotype
                .findFirst()
                // Return its label
                .map(stereotype -> ModuleI18NService.getLabel(stereotype))
                .orElse(null);
    }

    /**
     * Manually initialize the current {@link IModuleService}, as this class is not injected at all.
     */
    @objid ("5b4fdde9-177d-11e2-aa0d-002564c97630")
    public static void init(final IModuleService aModuleService) {
        ModuleI18NService.moduleService = aModuleService;
    }

    /**
     * Resolve an {@link IModule} instance from a {@link ModuleComponent}.
     */
    @objid ("d305591b-74aa-4d64-abc3-30ec2715f33c")
    private static IModule getIModule(final ModuleComponent moduleComponent) {
        if (moduleComponent == null) {
            return null;
        }
        
        if (ModuleI18NService.moduleService != null) {
            // First, look for started modules
            for (final IRTModule startedModule : ModuleI18NService.moduleService.getStartedModules()) {
                if (Objects.equals(moduleComponent, startedModule.getModel())) {
                    return startedModule.getIModule();
                }
            }
        }
        return null;
    }

    @objid ("f9e608d6-1d97-45b6-82ab-0ab7b4991486")
    private static IModule getLocalModule(final ModuleComponent moduleComponent) {
        IModule iModule = ModuleI18NService.localModules.get(moduleComponent);
        if (iModule == null) {
            // Create a local module instance
            iModule = new LocalModule(moduleComponent);
            ModuleI18NService.localModules.put(moduleComponent, iModule);
        }
        return iModule;
    }

    @objid ("bbdc73f0-a48a-4292-8fe6-3932995de5c4")
    private static ModuleComponent getModule(final ModelElement element) {
        return (ModuleComponent) element.accept(OwnerModuleGetter.instance);
    }

    /**
     * Compute a priority for a module component, according to its position in the "stated modules" list.
     */
    @objid ("46b26347-d716-4ade-8e4e-e938affc07da")
    private static int getModulePriority(final ModuleComponent moduleComponent) {
        if (ModuleI18NService.moduleService != null) {
            final List<IRTModule> startedModules = ModuleI18NService.moduleService.getStartedModules();
            for (int i = 0; i < startedModules.size(); i++) {
                final IRTModule startedModule = startedModules.get(i);
                if (Objects.equals(moduleComponent, startedModule.getModel())) {
                    return i;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Get the icon provided by the module for a given profile. The life cycle of the returned image is handled by the module and the image should not be disposed.
     * 
     * @param profile a profile
     * @return the profile image, or <code>null</code> if the module provides none.
     */
    @objid ("fd174c2b-e697-462f-8a38-8e6b9372ce9d")
    public static Image getIcon(final Profile profile) {
        final ModuleComponent moduleComponent = profile.getOwnerModule();
        if (moduleComponent == null) {
            return null;
        }
        IModule iModule = ModuleI18NService.getIModule(moduleComponent);
        if (iModule == null) {
            iModule = ModuleI18NService.getLocalModule(moduleComponent);
        }
        return iModule.getImage(profile, IModule.ImageType.ICON);
    }

    @objid ("c1cbae80-3fd4-42ac-a60b-8408a6ab3f0e")
    public static QualifiedImage getQualifiedImage(ModelElement element, IPeerModule preferredProvider) {
        if (preferredProvider == null) {
            return element.getExtension().stream()
                    // Ignore shell and orphan stereotypes
                    .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                    // Ignore stereotypes having no image defined
                    .filter(stereotype -> stereotype.getImage() != null && !stereotype.getImage().isEmpty())
                    // Sort stereotypes according to the "started modules" order
                    .sorted((stereotype1, stereotype2) -> Integer.compare(ModuleI18NService.getModulePriority(stereotype1.getModule()), ModuleI18NService.getModulePriority(stereotype2.getModule())))
                    // Get the first stereotype
                    .findFirst()
                    // Return its Image
                    .map(stereotype -> {
                        Image image = ModuleI18NService.getImage(stereotype);
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
                        Image image = ModuleI18NService.getImage(stereotype);
                        return image != null ? new QualifiedImage(image, computeImageQualifier(stereotype)) : null;
                    })
                    .orElse(null);
        }
    }

    @objid ("ca79edec-b501-45a3-b036-388cbfc38a82")
    public static QualifiedImage getQualifiedIcon(ModelElement element, IPeerModule preferredProvider) {
        if (preferredProvider == null) {
            return element.getExtension().stream()
                    // Ignore shell and orphan stereotypes
                    .filter(stereotype -> stereotype.isValid() && stereotype.getModule() != null)
                    // Ignore stereotypes having no image defined
                    .filter(stereotype -> stereotype.getIcon() != null && !stereotype.getIcon().isEmpty())
                    // Sort stereotypes according to the "started modules" order
                    .sorted((stereotype1, stereotype2) -> Integer.compare(ModuleI18NService.getModulePriority(stereotype1.getModule()), ModuleI18NService.getModulePriority(stereotype2.getModule())))
                    // Get the first stereotype
                    .findFirst()
                    // Return its QualifiedImage
                    .map(stereotype -> {
                        Image icon = ModuleI18NService.getIcon(stereotype);
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
                        Image icon = ModuleI18NService.getIcon(stereotype);
                        return icon != null ? new QualifiedImage(icon, computeIconQualifier(stereotype)) : null;
                    })
                    .orElse(null);
        }
    }

    @objid ("9a3ac14d-7600-4160-ad71-fbb70428ef6c")
    private static String computeIconQualifier(Stereotype stereotype) {
        return String.format("ICO_%s", stereotype.getUuid());
    }

    @objid ("238b04b8-04aa-45f4-bbc0-bebc5dc57875")
    private static String computeImageQualifier(Stereotype stereotype) {
        return String.format("IMG_%s", stereotype.getUuid());
    }

    /**
     * {@link ImageDescriptor} that looks for image data in the given repository blob.
     * <p>
     * Experimental: Also look for zoomed image for Hi-DPI with 'blobKey@zoom' blob key, with zoom = 150, 200, ... .
     * 
     * @author cma
     * @since Modelio Valkyrie 3.8
     */
    @objid ("e4450b0b-4806-4b89-8cfd-515af471ded3")
    static class BlobImageDescriptor extends ImageDescriptor implements ImageDataProvider {
        @objid ("44ca867d-52a7-4fb9-8b20-7a6e0903abdc")
        private final String blobKey;

        @objid ("558092cc-c23d-4891-9fe4-91f789fc219f")
        private final MObject element;

        @objid ("41e2ba36-4ebe-44fc-946b-09d342bae94a")
        public BlobImageDescriptor(final MObject element, final String blobKey) {
            super();
            this.element = element;
            this.blobKey = blobKey;
        }

        @objid ("0f834579-f5b0-4bf8-9224-879975815fe8")
        @Override
        public ImageData getImageData() {
            try (InputStream is = getImageStreamFromBlob(this.blobKey);) {
                if (is != null) {
                    return new ImageData(is);
                }
            } catch (@SuppressWarnings ("unused") final IOException e) {
                // ignore
            }
            return null;
        }

        /**
         * Get an InputStream access to the image content.
         * 
         * @return an InputStream or <i>null</i> if there is no matching blob.
         * @throws java.io.IOException on I/O failure
         */
        @objid ("2d0d7f81-3c70-4aac-9ab8-ce3cc112acaa")
        private InputStream getImageStreamFromBlob(final String aBlobKey) throws IOException {
            // Get existing image stored in a blob
            final IRepository repository = GProject.getProject(this.element).getFragment(this.element).getRepository();
            return repository.readBlob(aBlobKey);
        }

        @objid ("de6444a0-f292-4e29-94d2-938671c35f1b")
        @Override
        public Image createImage(final boolean returnMissingImageOnError, final Device device) {
            // Experimental since 3.8: look for zoomed image
            // See UrlImageDescriptor implementation for reference.
            try {
                return new Image(device, this);
            } catch (final SWTException e) {
                // ignore SWT.ERROR_INVALID_IMAGE
                if (e.code != SWT.ERROR_INVALID_IMAGE) {
                    throw e;
                }
                // fall through
            } catch (@SuppressWarnings ("unused") final IllegalArgumentException e) {
                // fall through
            }
            
            // fall back
            return super.createImage(returnMissingImageOnError, device);
        }

        @objid ("809c3dd6-0bb0-4860-9368-d56a4fa82c77")
        @Override
        public ImageData getImageData(final int zoom) {
            // See UrlImageDescriptor.getxURL(...) implementation for reference.
            String kzoom;
            if (zoom >= 200) {
                kzoom = "@2x";
            } else if (zoom >= 150) {
                kzoom = "@1.5x";
            } else {
                return getImageData();
            }
            
            try (InputStream is = getImageStreamFromBlob(this.blobKey + kzoom);) {
                if (is != null) {
                    return new ImageData(is);
                }
            } catch (@SuppressWarnings ("unused") final IOException e) {
                // ignore
            }
            return getImageData();
        }

        /**
         * Tells whether the blob exists.
         * 
         * @return true only if the blob exists.
         */
        @objid ("b875fdc7-a0a3-4d27-a880-6a18f917b3ba")
        public boolean exists() {
            final IRepository repository = GProject.getProject(this.element).getFragment(this.element).getRepository();
            try {
                return repository != null && repository.readBlobInfo(this.blobKey) != null;
            } catch (@SuppressWarnings ("unused") final IOException e) {
                // ignore
            }
            return false;
        }

    }

    /**
     * Almost empty implementation of IModule, providing the {@link IModule#getImage(Stereotype, ImageType)} method only.
     * <p>
     * It relies on blobs to read the stereotype images, and stores them in an {@link ImageRegistry} using the blob key. </br>
     * A 'BlobListener' clears updated/deleted blobs from the registry, as these images can change at runtime with the 'edit stereotype' command.
     * </p>
     */
    @objid ("9118e989-9e40-47cd-8385-ec78383bcd13")
    private static class LocalModule implements IModule {
        @objid ("712b64ee-35da-42d8-b9b7-078fb77dc921")
        private final ModuleComponent moduleComponent;

        @objid ("73b42bb2-ec06-4cbe-ab14-9a7f5d97332d")
        protected ImageRegistry imageRegistry;

        /**
         * Dummy image descriptor to tell there is no image for a stereotype.
         */
        @objid ("c6b9327a-a4d1-42ab-beae-b0ecf759ccaa")
        private static final ImageDescriptor NO_IMAGE_DESC = ImageDescriptor.createFromImageData(new ImageData(2, 2, 1, new PaletteData(new RGB(0, 255, 0))));

        /**
         * Dummy image descriptor created from {@link #NO_IMAGE_DESC} to tell there is no image for a stereotype.
         */
        @objid ("8c7b2ceb-a901-45de-bbff-220df93efb68")
        private Image noImage;

        /**
         * Default c'tor
         */
        @objid ("64712871-e452-45d4-a01e-114a8f47a4e3")
        LocalModule(final ModuleComponent moduleComponent) {
            this.moduleComponent = moduleComponent;
            Display.getDefault().syncExec(() -> {
                this.imageRegistry = new ImageRegistry();
                this.imageRegistry.put("NO_IMAGE_DESC", LocalModule.NO_IMAGE_DESC);
                this.noImage = this.imageRegistry.get("NO_IMAGE_DESC");
            });
            
            registerBlobListener(moduleComponent);
        }

        @objid ("8326d29e-5d6c-42c5-ba7e-1df5190eea7b")
        @Override
        public String getDescription() {
            throw new UnsupportedOperationException();
        }

        @objid ("56304137-3a4b-4990-a47d-0b444dcc704a")
        @Override
        public Image getImage(final Stereotype stereotype, final ImageType type) {
            // If only the stereotype was specified we search directly from it.
            if (stereotype == null) {
                return null;
            }
            
            final String blobKey = LocalModule.getImageBlobKey(stereotype, type);
            
            Image image = this.imageRegistry.get(blobKey);
            
            if (image == this.noImage) {
                // there is no image
                return null;
            }
            
            if (image == null) {
                // image not yet computed
                final ImageDescriptor desc = getImageDescriptor(stereotype, blobKey);
                if (desc != null) {
                    this.imageRegistry.put(blobKey, desc);
                    image = this.imageRegistry.get(blobKey);
                } else {
                    // remember there is no image
                    this.imageRegistry.put(blobKey, LocalModule.NO_IMAGE_DESC);
                }
            }
            return image;
        }

        /**
         * @deprecated Replaced by {@link BlobImageDescriptor} since Valkyrie 3.8 .
         */
        @objid ("6cd05520-e7ca-48da-8460-bb98acdf732e")
        @Deprecated
        private static URL getImageUrlFromBlob(final MObject element, final String blobKey) {
            // Get existing image stored in a blob
            final IRepository repository = GProject.getProject(element).getFragment(element).getRepository();
            try (InputStream stream = repository.readBlob(blobKey)) {
                if (stream != null) {
                    final File imageFile = File.createTempFile(element.getUuid().toString(), blobKey);
                    Files.copy(stream, imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    // Make sure the image is deleted when leaving Modelio
                    imageFile.deleteOnExit();
            
                    return imageFile.toURI().toURL();
                }
            } catch (@SuppressWarnings ("unused") final IOException e) {
                // No image to load, ignore error...
            }
            return null;
        }

        @objid ("95895b64-767b-4d33-9806-988b6c8a429f")
        @Override
        public String getLabel() {
            return this.moduleComponent.getName();
        }

        @objid ("63cfca37-7535-411c-ab9d-3e1a06d2bab9")
        @Override
        public ILicenseInfos getLicenseInfos() {
            return new UndefinedLicenseInfos();
        }

        @objid ("5d967f9a-f575-4f1a-9230-78dd917045b0")
        @Override
        public Image getModuleImage() {
            return null;
        }

        @objid ("a317fb9e-0096-4e7e-8a30-5d5218071310")
        @Override
        public String getModuleImagePath() {
            return null;
        }

        @objid ("d030d713-591d-4a26-a8fd-e97406d18f7a")
        @Override
        public String getName() {
            return this.moduleComponent.getName();
        }

        @objid ("437d3d28-ea82-441f-8707-185331ab1489")
        @Override
        public IPeerModule getPeerModule() {
            throw new UnsupportedOperationException();
        }

        @objid ("341ee3c0-853a-4b5e-a144-761a0cd3049e")
        @Override
        public Version getRequiredModelioVersion() {
            throw new UnsupportedOperationException();
        }

        @objid ("2e6c523d-cdfa-45fc-8f88-bc498d269c34")
        @Override
        public Version getVersion() {
            throw new UnsupportedOperationException();
        }

        @objid ("d30a7aa5-1a7b-4b94-89fc-21ba24df5d1c")
        public void registerBlobListener(final ModuleComponent mc) {
            GProject.getProject(mc).getSession().getBlobSupport().addBlobChangeListener(ev -> {
                final Set<IBlobInfo> blobs = new HashSet<>();
                blobs.addAll(ev.getUpdatedBlobs());
                blobs.addAll(ev.getDeletedBlobs());
            
                // Remove all modified images from the registry.
                for (final IBlobInfo blob : blobs) {
                    this.imageRegistry.remove(blob.getKey());
                }
            });
        }

        @objid ("6108aa40-2da9-472e-8318-b1ba8b33c081")
        private static String getImageBlobKey(final MObject mdaElement, final ImageType imageType) {
            // Compute the blob key
            String blobKey = mdaElement.getUuid().toString();
            if (imageType == ImageType.ICON) {
                blobKey += ".icon";
            } else {
                blobKey += ".image";
            }
            return blobKey;
        }

        /**
         * Get the image descriptor provided by the module for a given stereotype.
         * <p>
         * The module should return an image if the stereotype is provided by itself, <i>null</i> in the other case.
         * @param imageType the image type
         * 
         * @param stereotype a stereotype
         * @return the stereotype image, or <i>null</i> if the module provides none.
         */
        @objid ("df41da1c-7f04-4060-8aca-de16196d1e1b")
        private ImageDescriptor getImageDescriptor(final Stereotype stereotype, final String blobKey) {
            // If the stereotype is not owned by the current module we return
            // null.
            if (!isStereotypeOwner(stereotype)) {
                return null;
            }
            
            final BlobImageDescriptor desc = new BlobImageDescriptor(stereotype, blobKey);
            
            if (!desc.exists()) {
                return null;
            }
            return desc;
        }

        /**
         * Returns true if the given stereotype belongs to the module.
         * 
         * @param stereotype the stereotype to test
         * @return true if the given stereotype belongs to the module.
         */
        @objid ("7c94e616-1917-40fe-a2fe-f70e25963ec1")
        private boolean isStereotypeOwner(final Stereotype stereotype) {
            final Profile profile = stereotype.getOwner();
            if (profile != null) {
                final ModuleComponent module = profile.getOwnerModule();
                if (module != null) {
                    return module.equals(this.moduleComponent);
                }
            }
            return false;
        }

        @objid ("dcd30a93-66f3-4d41-b58d-cb153f4629ba")
        @Override
        public org.modelio.api.module.context.IModuleContext getModuleContext() {
            return new LocalModuleContext();
        }

        @objid ("8a0bff87-433b-45d6-baac-603aa741f8e6")
        @Override
        public IModuleLifeCycleHandler getLifeCycleHandler() {
            throw new UnsupportedOperationException();
        }

        @objid ("d859bf39-15eb-4fab-b650-d25d661bc851")
        @Override
        public final void initModulecontext(final IModuleContext moduleContext) {
            // Nothing to do. Fake module has no ModuleContext.
        }

        @objid ("b7a5898d-ed40-4b67-bace-846de15a9c3c")
        @Override
        public Image getImage(final Profile profile, final ImageType imageType) {
            // If only the stereotype was specified we search directly from it.
            if (profile != null) {
                final String blobKey = LocalModule.getImageBlobKey(profile, imageType);
            
                Image image = this.imageRegistry.get(blobKey);
            
                if (image == null) {
                    final ImageDescriptor desc = getImageDescriptor(profile, blobKey);
                    if (desc != null) {
                        this.imageRegistry.put(blobKey, desc);
                        image = this.imageRegistry.get(blobKey);
                    }
                }
                return image;
            }
            return null;
        }

        @objid ("f1de4374-e345-425b-8acb-7cd466d8739a")
        private ImageDescriptor getImageDescriptor(final Profile profile, final String blobKey) {
            // If the stereotype is not owned by the current module we return
            // null.
            if (!Objects.equals(this.moduleComponent, profile.getOwnerModule())) {
                return null;
            }
            return new BlobImageDescriptor(profile, blobKey);
        }

        @objid ("dfa80f55-7f67-4c64-a585-78de2ee455a5")
        private final class LocalModuleContext implements IModuleContext {
            @objid ("7408a9bb-ab1b-42ba-aaac-27a42d653b5f")
            @Override
            public void setModule(final IModule iModule) {
                // Nothing to do
            }

            @objid ("a242ef87-0fa9-4438-94ec-f10a0f483a2f")
            @Override
            public IProjectStructure getProjectStructure() {
                return null;
            }

            @objid ("d48985fc-5435-4b56-a688-f354d746a09a")
            @Override
            public IModuleAPIConfiguration getPeerConfiguration() {
                return null;
            }

            @objid ("c2dc1cb8-172c-4aa5-b091-c14a0290ebec")
            @Override
            public IModelioServices getModelioServices() {
                return null;
            }

            @objid ("64099316-a8d1-41cd-86a8-42b41c9cd45a")
            @Override
            public IModelioContext getModelioContext() {
                return null;
            }

            @objid ("242e4f3b-6039-4a8c-b004-afb88b7a7af8")
            @Override
            public ILogService getLogService() {
                return null;
            }

            @objid ("e8ca3d84-8d0c-44e2-addc-6b8924a18909")
            @Override
            public I18nSupport getI18nSupport() {
                return FallbackModuleI18n.instance;
            }

            @objid ("cd6cdae8-6e0a-49fb-ac42-d5160a0395b6")
            @Override
            public IModuleUserConfiguration getConfiguration() {
                throw new UnsupportedOperationException();
            }

            @objid ("cbdf2e25-5d6a-462d-9979-5d8051259660")
            @Override
            public ScriptEngine getJythonEngine() {
                return null;
            }

            @objid ("b2d7608a-8108-4987-8616-0b3af8479cd8")
            @Override
            public ModuleComponent getModel() {
                return LocalModule.this.moduleComponent;
            }

            @objid ("9b8cf2be-c4cf-4d81-b06a-7e530bd0cddf")
            @Override
            public IModelingSession getModelingSession() {
                // No available session
                return null;
            }

        }

    }

    /**
     * Service to get the {@link ModuleComponent} owning the visited element.
     * <p>
     * Usage : {@link MObject#accept(org.modelio.vcore.smkernel.mapi.MVisitor) module = mdaElement.accept(OwnerModuleGetter.instance)}
     * 
     * @author cma
     * @since Modelio Valkyrie 3.8
     */
    @objid ("94f73d5c-d097-4fb6-ac3e-3448518765b2")
    private static final class OwnerModuleGetter implements IAbstractInfrastructureVisitor {
        @objid ("a9e96f43-3fd9-426b-bc57-c37c2622005a")
        public static final OwnerModuleGetter instance = new OwnerModuleGetter();

        @objid ("47ac8b02-54c5-475c-adc0-4c8ab9d152de")
        @Override
        public Object visitStereotype(final Stereotype obj) {
            final Profile prof = obj.getOwner();
            if (prof != null) {
                return visitProfile(prof);
            }
            return null;
        }

        @objid ("8e009208-f4c6-4dc2-a7fa-9c3bf520bc62")
        @Override
        public Object visitMetaclassReference(final MetaclassReference obj) {
            final Profile prof = obj.getOwnerProfile();
            if (prof != null) {
                return visitProfile(prof);
            }
            return null;
        }

        @objid ("0046309c-ded2-4c30-a967-5c444207c612")
        @Override
        public Object visitProfile(final Profile obj) {
            return obj.getOwnerModule();
        }

        @objid ("fdcf91d7-34ee-4e91-b90d-774e4fcf194d")
        @Override
        public Object visitModuleComponent(final ModuleComponent obj) {
            return obj;
        }

        @objid ("9d0b4224-cd66-4cc6-92f6-00e7e7fe17fc")
        @Override
        public Object visitModuleParameter(final ModuleParameter obj) {
            return obj.getOwner();
        }

        @objid ("57eed509-7912-4c82-87c4-d20d5c3d1609")
        @Override
        public Object visitTagType(final TagType obj) {
            final MObject owner = obj.getCompositionOwner();
            if (owner != null) {
                return owner.accept(this);
            }
            return null;
        }

        @objid ("3cecc167-b3b7-4896-a72d-2dff82d8a98e")
        @Override
        public Object visitNoteType(final NoteType obj) {
            final MObject owner = obj.getCompositionOwner();
            if (owner != null) {
                return owner.accept(this);
            }
            return null;
        }

        @objid ("11d3f5e6-43fe-4b6c-a642-f1c033539055")
        @Override
        public Object visitResourceType(final ResourceType obj) {
            return obj.getModule();
        }

        @objid ("e7d3f412-f2f9-463f-a7d5-be95c0969b37")
        @Override
        public Object visitPropertyDefinition(final PropertyDefinition obj) {
            final PropertyTableDefinition tableDef = obj.getOwner();
            if (tableDef != null) {
                return visitPropertyTableDefinition(tableDef);
            }
            return null;
        }

        @objid ("4d1c4f0f-e7b2-4312-b468-4beadadcbbe1")
        @Override
        public Object visitPropertyTableDefinition(final PropertyTableDefinition obj) {
            final MObject owner = obj.getCompositionOwner();
            if (owner != null) {
                return owner.accept(this);
            }
            return null;
        }

    }

}
