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

package org.modelio.api.module;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.license.ILicenseInfos;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.version.Version;

/**
 * Default implementation of the {@link IModule} interface.
 * <p>
 * All Modelio java modules should inherit from this class.
 */
@objid ("a0456ff1-479d-11df-a533-001ec947ccaf")
public abstract class AbstractJavaModule implements IModule {
    /**
     * Parameter edition model computed from module.xml .
     */
    @objid ("4de73e92-fb7f-4a75-b1bd-dce13d4df2e9")
    private IParameterEditionModel fallBackParameterEditionModel;

    /**
     * The module image registry.
     * <p>
     * This registry contains the module stereotypes images and icons. This registry is disposed when the module is unloaded.
     * <p>
     * Sub classes may use it to store other images as well. <br>
     * Sub classes should not set this property to another registry.
     */
    @objid ("2d2d975b-caf7-4324-9f88-3b903bb09a43")
    protected ImageRegistry imageRegistry;

    @objid ("e5997763-1a7c-49ee-b143-e3146a0a3eca")
    private IModuleContext moduleContext;

    @objid ("ba8a3cf9-e6d3-490f-9d7b-b1913da80cfe")
    private Image moduleImage;

    @objid ("bcfa4f47-7aac-4624-86a6-d03efa8099ef")
    protected IParameterEditionModel parameterEditionModel;

    /**
     * Main constructor, to instantiate a new module.moduleContext
     * 
     * @param modelingSession the current session.
     * @param moduleComponent the Module representing this module in the model.
     * @param moduleConfiguration the configuration of this module.
     * @deprecated use {@link AbstractJavaModule#AbstractJavaModule(IModuleContext)} instead.
     */
    @objid ("a0457000-479d-11df-a533-001ec947ccaf")
    @Deprecated
    public AbstractJavaModule(final IModelingSession modelingSession, final ModuleComponent moduleComponent, final IModuleUserConfiguration moduleConfiguration) {
        this();
    }

    /**
     * Main constructor, to instantiate a new module.
     * 
     * @param moduleContext access point to Modelio services.
     * 
     * @since 3.5
     */
    @objid ("7b74a233-3d8e-46de-92ff-d788540043e7")
    public AbstractJavaModule(final IModuleContext moduleContext) {
        this();
        this.moduleContext = moduleContext;
    }

    @objid ("a047d2c2-479d-11df-a533-001ec947ccaf")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final AbstractJavaModule other = (AbstractJavaModule) obj;
        final ModuleComponent otherModuleComponent = other.getModuleContext() != null ? other.getModuleContext().getModel() : null;
        final ModuleComponent moduleComponent = this.moduleContext != null ? this.moduleContext.getModel() : null;
        return Objects.equals(moduleComponent, otherModuleComponent);
    }

    /**
     * Used to return the module description.
     * 
     * @return The module description
     */
    @objid ("a047d2f5-479d-11df-a533-001ec947ccaf")
    @Override
    public String getDescription() {
        try {
            return this.moduleContext.getI18nSupport().getString(
                    "%ModuleDescription");
        } catch (final MissingResourceException e) {
            getModuleContext().getLogService().warning(e.toString());
            return "";
        }
    }

    /**
     * Get the image provided by the module for a given stereotype. The module should return an image if the stereotype is provided by itself, null in the other case. The image life cycle must be handled by the module.
     * 
     * @param stereotype a stereotype
     * @param type the image type
     * @return the stereotype image, or null if the module provides none.
     */
    @objid ("a047d2d2-479d-11df-a533-001ec947ccaf")
    @Override
    public Image getImage(final Stereotype stereotype, final ImageType type) {
        // If only the stereotype was specified we search directly from it.
        if (stereotype != null) {
            final String key = getImageKey(stereotype, type);
        
            Image image = this.imageRegistry.get(key);
        
            if (image == null) {
                final ImageDescriptor desc = getImageDescriptor(stereotype, type);
                if (desc != null) {
                    this.imageRegistry.put(key, desc);
                    image = this.imageRegistry.get(key);
                }
            }
            return image;
        }
        return null;
    }

    @objid ("2cfdedcb-4637-41f9-bdd6-58e4ebbffa4d")
    @Override
    public Image getImage(final Profile profile, final ImageType imageType) {
        if (profile != null) {
            final String key = "profile." + profile.getName() + "." + imageType.name();
        
            Image image = this.imageRegistry.get(key);
        
            if (image == null) {
                final ImageDescriptor desc = getImageDescriptor(profile, imageType);
                if (desc != null) {
                    this.imageRegistry.put(key, desc);
                    image = this.imageRegistry.get(key);
                }
            }
            return image;
        }
        return null;
    }

    /**
     * Get the module label that is displayed in dialog boxes and other GUI parts.
     * 
     * @return The module label.
     */
    @objid ("552faba7-de2d-4c53-9a68-274bfdd999e3")
    @Override
    public String getLabel() {
        if (this.moduleContext != null) {
            try {
                if (this.moduleContext.getI18nSupport().getI18N() != null) {
                    return this.moduleContext.getI18nSupport().getString("%ModuleLabel");
                }
            } catch (final MissingResourceException e) {
                getModuleContext().getLogService().error(e.getMessage());
            }
        }
        return getName();
    }

    @objid ("ce0cb21c-74f2-42ff-b7ca-21e0c8db96c8")
    @Override
    public ILicenseInfos getLicenseInfos() {
        return new FreeLicenseInfos();
    }

    @objid ("ab2ccb5b-4569-411f-a976-804c1f6779dd")
    @Override
    public final IModuleContext getModuleContext() {
        return this.moduleContext;
    }

    @objid ("9994c01e-178f-11e2-aa0d-002564c97630")
    @Override
    public final Image getModuleImage() {
        if (this.moduleImage == null) {
            loadModuleImage();
        }
        return this.moduleImage;
    }

    /**
     * Get the path to the image representing the module.
     * 
     * @return a path relative to the module's resource path.
     */
    @objid ("a0457017-479d-11df-a533-001ec947ccaf")
    @Override
    public abstract String getModuleImagePath();

    /**
     * Used to return the module name.
     * <p>
     * <p>
     * The module name corresponds to the name of the module, as defined in the <i>MDA Designer<i> tool.
     * 
     * @return The module name
     */
    @objid ("a047d250-479d-11df-a533-001ec947ccaf")
    @Override
    public String getName() {
        return this.moduleContext != null ? this.moduleContext.getModel().getName() : null;
    }

    /**
     * Get the parameters model as it must be shown in the module parameters edition dialog.
     * <p>
     * This default implementation returns a parameter model computed from the 'module.xml' file unless the module implementation filled the {@link #parameterEditionModel} member.
     */
    @objid ("4e7d7262-245a-40c9-be26-309040530e0a")
    @Override
    public IParameterEditionModel getParametersEditionModel() {
        if (this.parameterEditionModel != null) {
            return this.parameterEditionModel;
        } else {
            return this.fallBackParameterEditionModel;
        }
    }

    /**
     * Returns the minimum Modelio version that authorize the Module to be activated.
     * 
     * @return The minimum Modelio version
     */
    @objid ("a0457065-479d-11df-a533-001ec947ccaf")
    @Override
    public Version getRequiredModelioVersion() {
        if (this.moduleContext != null) {
            return new Version(this.moduleContext.getModel()
                    .getMinBinVersionCompatibility());
        } else {
            return null;
        }
    }

    /**
     * Used to return the module version.
     * 
     * @return The module version
     */
    @objid ("a0457068-479d-11df-a533-001ec947ccaf")
    @Override
    public Version getVersion() {
        if (this.moduleContext != null) {
            final ModuleComponent moduleEl = this.moduleContext.getModel();
            return new Version(moduleEl.getMajVersion()
                    + "." + moduleEl.getMinVersion() + "."
                    + moduleEl.getMinMinVersion());
        } else {
            return null;
        }
    }

    @objid ("466b0c4f-9748-11e0-8975-001ec947cd2a")
    @Override
    public int hashCode() {
        final Object moduleComponent = this.moduleContext.getModel();
        final int prime = 31;
        int result = 1;
        result = prime * result
                + (moduleComponent == null ? 0 : moduleComponent.hashCode());
        return result;
    }

    /**
     * Reserved for M3.4/M3.5 compatibility. Not intended for use.
     */
    @objid ("3d913a5c-5638-48f3-be16-1b8dba60120c")
    @Override
    public final void initModulecontext(final IModuleContext context) {
        this.moduleContext = context;
    }

    /**
     * Register the given parameter model as the fall back parameter edition model if the module implementation does not define a custom one.
     */
    @objid ("39ff30ac-98ec-4522-be81-9a77cad6ddbb")
    @Override
    public final void initParametersEditionModel(final IParameterEditionModel model) {
        this.fallBackParameterEditionModel = model;
    }

    @objid ("a0457027-479d-11df-a533-001ec947ccaf")
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(40);
        buffer.append(getClass().getSimpleName());
        buffer.append(" '");
        buffer.append(getName());
        buffer.append("' {");
        buffer.append(this.moduleContext != null ? this.moduleContext
                .getModel().getUuid() : "null");
        buffer.append("} module");
        return buffer.toString();
    }

    @objid ("76129bb9-06df-437d-903c-f08d241d7025")
    @Override
    public void uninit() {
        if (this.moduleImage != null) {
            final Display display = Display.getDefault();
            display.syncExec(() -> {
                this.moduleImage.dispose();
        
                // Give 10s to diagrams to refresh their icons before disposing all stereotypes images
                display.timerExec(10_000, () -> {
                    this.imageRegistry.dispose();
                });
            });
        
            this.moduleImage = null;
        }
    }

    @objid ("daa2701d-6652-4728-9b8d-1a591d83325a")
    private AbstractJavaModule() {
        Display.getDefault().syncExec(() -> {
            this.imageRegistry = new ImageRegistry();
        });
    }

    /**
     * Get the image descriptor provided by the module for a given stereotype. The module should return an image if the stereotype is provided by itself, null in the other case.
     * 
     * @param stereotype a stereotype
     * @param imageType the image type
     * @return the stereotype image, or null if the module provides none.
     */
    @objid ("a047d266-479d-11df-a533-001ec947ccaf")
    private ImageDescriptor getImageDescriptor(final Stereotype stereotype, final ImageType imageType) {
        // If the stereotype is not owned by the current module we return null.
        if (!isStereotypeOwner(stereotype)) {
            return null;
        }
        
        String relativePath = null;
        
        if (imageType == ImageType.ICON) {
            relativePath = stereotype.getIcon();
        } else if (imageType == ImageType.IMAGE) {
            relativePath = stereotype.getImage();
        }
        
        if (relativePath == null || relativePath.isEmpty()) {
            return null;
        }
        return getRelPathImageDescriptor(relativePath);
    }

    @objid ("671b2fd8-b8df-4be7-8256-c0fbb327c9c3")
    private ImageDescriptor getImageDescriptor(final Profile profile, final ImageType imageType) {
        // If the profile is not owned by the current module we return null.
        if (!Objects.equals(profile.getOwnerModule(), getModuleContext().getModel())) {
            return null;
        }
        
        final StringBuilder i18nKey = new StringBuilder();
        i18nKey.append("%profile.");
        i18nKey.append(profile.getName());
        i18nKey.append(imageType == ImageType.IMAGE ? ".image" : ".icon");
        
        final String relativePath = getModuleContext().getI18nSupport().getString(i18nKey.toString());
        if (relativePath == null || relativePath.isEmpty()) {
            return null;
        }
        return getRelPathImageDescriptor(relativePath);
    }

    @objid ("a045700f-479d-11df-a533-001ec947ccaf")
    private static String getImageKey(final Stereotype stereotype, final ImageType imageType) {
        final StringBuilder imageKey = new StringBuilder();
        imageKey.append("module.");
        imageKey.append(stereotype.getCompositionOwner().getName());
        imageKey.append(".");
        imageKey.append(stereotype.getBaseClassName());
        imageKey.append(".");
        imageKey.append(stereotype.getName());
        imageKey.append(".");
        imageKey.append(imageType.name());
        return imageKey.toString();
    }

    @objid ("c1bd8ed0-fe1d-459a-afe5-164a1403d6d9")
    private ImageDescriptor getRelPathImageDescriptor(final String relativePath) {
        final Path moduleDirectory = this.moduleContext.getConfiguration().getModuleResourcesPath();
        
        try {
            Path imageFile = moduleDirectory.resolve(relativePath);
            if (!Files.isRegularFile(imageFile)
                    && relativePath.startsWith(getName())) {
                // Compatibility mode with modelio 1.2, remove the module's name
                final String relativePath12 = relativePath.substring(getName().length() + 1);
                imageFile = moduleDirectory.resolve(relativePath12);
            }
        
            if (Files.isRegularFile(imageFile)) {
                final URL imageUrl = imageFile.toUri().toURL();
                final ImageDescriptor desc = ImageDescriptor.createFromURL(imageUrl);
                if (desc != ImageDescriptor.getMissingImageDescriptor()) {
                    return desc;
                }
                return null;
            }
        } catch (final MalformedURLException e) {
            getModuleContext().getLogService().error(e.getMessage());
        }
        return null;
    }

    /**
     * Returns true if the given stereotype belongs to the module.
     * 
     * @param stereotype the stereotype to test
     * @return true if the given stereotype belongs to the module.
     */
    @objid ("a0457011-479d-11df-a533-001ec947ccaf")
    private boolean isStereotypeOwner(final Stereotype stereotype) {
        final Profile profile = stereotype.getOwner();
        if (profile != null) {
            final ModuleComponent module = profile.getOwnerModule();
            if (module != null) {
                return module.equals(this.moduleContext.getModel());
            }
        }
        return false;
    }

    @objid ("947ecfb3-ac67-40c1-8296-07733f7718a3")
    private void loadModuleImage() {
        final String relativePath = getModuleImagePath();
        if (relativePath == null || relativePath.isEmpty()) {
            getModuleContext().getLogService().info("No module icon defined");
        } else {
            final Path moduleDirectory = this.moduleContext.getConfiguration().getModuleResourcesPath();
            final Path imageFile = moduleDirectory.resolve(relativePath.substring(1));
        
            if (Files.isRegularFile(imageFile)) {
                final Display display = Display.getDefault();
                display.syncExec(() -> {
                    try {
                        this.moduleImage = new Image(display, imageFile
                                .toAbsolutePath().toString());
        
                    } catch (final RuntimeException e) {
                        getModuleContext().getLogService().warning(e.toString());
                        getModuleContext().getLogService().info(e);
                    }
                });
            }
        }
    }

    @objid ("aaad7d1e-2a12-467a-aff7-d3247e7ced42")
    private static class FreeLicenseInfos implements ILicenseInfos {
        @objid ("63965856-65fb-49e9-ad81-f90b7aa72f5a")
        @Override
        public String getType() {
            return "FREE";
        }

        @objid ("bb9f6f81-6c16-4a5d-8e08-6ee1f143f87b")
        @Override
        public Date getDate() {
            return null;
        }

        @objid ("84747d2f-9183-432e-98a3-2189b701daf6")
        @Override
        public Status getStatus() {
            return Status.FREE;
        }

    }

}
