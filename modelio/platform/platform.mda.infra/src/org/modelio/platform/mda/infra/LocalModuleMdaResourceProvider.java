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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageDataProvider;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.project.AbstractGProject;
import org.modelio.gproject.project.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.platform.ui.UIImages;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Mda resource provider for <b>LocalModule</b>.
 * <p>
 * It relies on blobs to read images/icons associated with Stereotypes.
 * </p>
 */
@objid ("c50e5b7a-c6ed-4939-9a3a-99c72f1eacfa")
class LocalModuleMdaResourceProvider implements IMdaResourceProvider {
    @objid ("f6f78bd6-69f9-4e5e-9805-1fe971acbcfa")
    private ModuleComponent moduleComponent;

    @objid ("5207d3e2-f4db-414f-913c-f0e1e8845bf3")
    private ImageRegistry imageRegistry;

    /**
     * Use a random image as a placeholder to avoid computing non existing images over and over.
     */
    @objid ("645e1f0b-97ff-40af-ab03-bd4dee6d0125")
    private final Image NO_IMAGE_PLACEHOLDER = UIImages.DOT;

    /**
     * Default c'tor
     * @param moduleComponent the module component this provider works for.
     */
    @objid ("afab4569-b6a0-434f-aa77-f0a75f1d2589")
    public  LocalModuleMdaResourceProvider(ModuleComponent moduleComponent) {
        this.moduleComponent = moduleComponent;
        
        this.imageRegistry = new ImageRegistry();
        
        registerBlobListener(moduleComponent);
        
    }

    /**
     * Clean the image registry.
     */
    @objid ("0f2b81a5-9112-43ee-a9ab-447601b10937")
    public void dispose() {
        this.imageRegistry.dispose();
        this.imageRegistry = null;
        
    }

    @objid ("78fd7e7d-921f-4c3f-85af-9b89ebc81dd0")
    @Override
    public String getDescription(PropertyDefinition element) {
        return "";
    }

    @objid ("5d59bd4d-8eb4-4568-8f20-a10e6d846fe3")
    @Override
    public String getDescription(Profile element) {
        return "";
    }

    @objid ("7a6f4c0b-db80-43f6-a74e-850c05a94367")
    @Override
    public String getDescription(Stereotype element) {
        return "";
    }

    @objid ("eaf36091-5037-476c-a233-c3bc03cc1251")
    @Override
    public String getDescription(NoteType element) {
        return "";
    }

    @objid ("16965f80-1add-45f2-92b8-4e1edd3d7c09")
    @Override
    public String getDescription(TagType element) {
        return "";
    }

    @objid ("aa3d4606-dc22-4266-b04e-aeab2e73510c")
    @Override
    public String getDescription(ResourceType element) {
        return "";
    }

    @objid ("df7de35c-e9ad-4922-8f6d-65204a61c713")
    @Override
    public Image getIcon(Stereotype stereotype) {
        return getBlobImage(stereotype, "icon");
    }

    @objid ("9981fc57-2d97-4335-b09e-10114de0b724")
    @Override
    public Image getImage(Stereotype stereotype) {
        return getBlobImage(stereotype, "image");
    }

    @objid ("4819665a-100e-4a72-9366-3316952d1bac")
    @Override
    public Image getIcon(Profile profile) {
        return null;
    }

    @objid ("54973baa-249c-480a-a0df-d5c55d12bb1e")
    @Override
    public Image getImage(Profile profile) {
        return null;
    }

    @objid ("860ecfa0-e051-4716-96dc-24b7ab442fa0")
    @Override
    public String getLabel(Stereotype stereotype) {
        return stereotype.getName();
    }

    @objid ("312e0475-4aad-44cf-9a50-eae0a4f0f560")
    @Override
    public String getLabel(TagType tagType) {
        return tagType.getName();
    }

    @objid ("f0398acb-8513-43cb-885b-d8bf8b7a0876")
    @Override
    public String getLabel(NoteType noteType) {
        return noteType.getName();
    }

    @objid ("fdc1275d-59ad-4c0f-bb02-f45fc228d4e3")
    @Override
    public String getLabel(ResourceType resourceType) {
        return resourceType.getName();
    }

    @objid ("529ced25-920d-4f35-ad88-5d2ac0df813c")
    @Override
    public String getLabel(ModuleComponent module) {
        return module.getName();
    }

    @objid ("cb72bd39-179d-4b7f-8378-6df4a7858d9e")
    @Override
    public String getLabel(PropertyDefinition pdef) {
        return pdef.getName();
    }

    @objid ("40bfb22d-f5f7-4a6c-bbf4-8105363a7fe0")
    @Override
    public String getLabel(Profile element) {
        return element.getName();
    }

    @objid ("558a1927-59c4-4a3b-9968-5df246447a19")
    @Override
    public Image getModuleImage(ModuleComponent mc) {
        return null;
    }

    @objid ("c7324ba3-080a-4cbb-8ef6-99ff1c9c9638")
    @Override
    public Image getModuleIcon(ModuleComponent mc) {
        return null;
    }

    @objid ("c63a2ad4-1e2a-41a5-9288-ccb70ddba4cf")
    private Image getBlobImage(Stereotype stereotype, String imageType) {
        // If only the stereotype was specified we search directly from it.
        if (stereotype == null) {
            return null;
        }
        
        final String blobKey = getBlobKey(stereotype, imageType);
        
        Image image = this.imageRegistry.get(blobKey);
        if (image == this.NO_IMAGE_PLACEHOLDER) {
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
                this.imageRegistry.put(blobKey, this.NO_IMAGE_PLACEHOLDER);
            }
        }
        return image;
    }

    @objid ("8e1039ba-db27-4b4d-b301-891edde439a4")
    private void registerBlobListener(final ModuleComponent mc) {
        AbstractGProject.getProject(mc).getSession().getBlobSupport().addBlobChangeListener(ev -> {
            final Set<IBlobInfo> blobs = new HashSet<>();
            blobs.addAll(ev.getUpdatedBlobs());
            blobs.addAll(ev.getDeletedBlobs());
        
            // Remove all modified images from the registry to force a refresh.
            for (final IBlobInfo blob : blobs) {
                this.imageRegistry.remove(blob.getKey());
            }
        });
        
    }

    /**
     * Compute the blob key
     */
    @objid ("37e715ce-f7cf-47ec-a06e-326a280b9e14")
    private String getBlobKey(final MObject mdaElement, final String imageType) {
        return mdaElement.getUuid().toString() + "." + imageType;
    }

    /**
     * Get the image descriptor provided by the module for a given stereotype.
     * <p>
     * The module should return an image if the stereotype is provided by itself, <i>null</i> in the other case.
     * @param stereotype a stereotype
     * @return the stereotype image, or <i>null</i> if the module provides none.
     */
    @objid ("ac8c98da-a154-49a4-9873-4445b84739ee")
    private ImageDescriptor getImageDescriptor(final Stereotype stereotype, final String blobKey) {
        // If the stereotype is not owned by the current module we return
        // null.
        if (!isInModule(stereotype)) {
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
     * @param stereotype the stereotype to test
     * @return true if the given stereotype belongs to the module.
     */
    @objid ("12c7594a-3d37-4ed6-9aee-b03680131e53")
    private boolean isInModule(final Stereotype stereotype) {
        final Profile profile = stereotype.getOwner();
        if (profile != null) {
            final ModuleComponent module = profile.getOwnerModule();
            if (module != null) {
                return module.equals(this.moduleComponent);
            }
        }
        return false;
    }

    /**
     * {@link ImageDescriptor} that looks for image data in the given repository blob.
     * <p>
     * Experimental: Also look for zoomed image for Hi-DPI with 'blobKey@zoom' blob key, with zoom = 150, 200, ... .
     * 
     * @since Modelio Valkyrie 3.8
     */
    @objid ("746f797e-bc56-4234-8141-d3d84edb0626")
    private static class BlobImageDescriptor extends ImageDescriptor implements ImageDataProvider {
        @objid ("883e43cc-f766-4e0d-aef9-1eb96d6e8d8d")
        private final String blobKey;

        @objid ("07b969fb-5f66-4e89-acc0-7524af1e0d05")
        private final MObject element;

        @objid ("5d6d6073-39ca-42f7-9ab3-35a806bd8894")
        public  BlobImageDescriptor(final MObject element, final String blobKey) {
            super();
            this.element = element;
            this.blobKey = blobKey;
            
        }

        @objid ("8618f259-79db-4dcc-bb8c-352df95d8214")
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
         * @return an InputStream or <i>null</i> if there is no matching blob.
         * @throws IOException on I/O failure
         */
        @objid ("c45f83c0-cca0-40ce-b45a-f597295e49c8")
        private InputStream getImageStreamFromBlob(final String aBlobKey) throws IOException {
            // Get existing image stored in a blob
            final IRepository repository = GProject.getProject(this.element).getFragment(this.element).getRepository();
            return repository.readBlob(aBlobKey);
        }

        @objid ("d6df5995-7611-423d-82ca-b6b850a3aa35")
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

        @objid ("42ea36d5-a7c8-44f1-b5c0-1bfd3b4a393b")
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
         * @return true only if the blob exists.
         */
        @objid ("e2ab267b-9af6-4594-bdc0-c7b35dc05b8b")
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

}
