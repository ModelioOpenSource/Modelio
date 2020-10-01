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

package org.modelio.model.property.stereotype.creator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.blob.BlobChangeEvent;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * "Create stereotype" and "Edit stereotype" commands implementation.
 * <p>
 * Creates a stereotype in the local module.<br>
 * Creates the LocalModule with its LocalProfile if there is no local module.
 */
@objid ("08def6e9-5979-4cdf-93f1-0f91fb4c3f82")
public class StereotypeEditor {
    @objid ("e4c2b744-7369-411f-970a-e78a5bde0edd")
    private static final String IMAGE = ".image";

    @objid ("626462bd-e994-46fd-bc6c-2c1b8868ab13")
    private static final String ICON = ".icon";

    @objid ("4db6860d-050b-488d-b50b-dad8b367b901")
    private Collection<IBlobInfo> createdBlobs;

    @objid ("acc301a9-ae4a-4871-ad8f-4ecb5984c10f")
    private Collection<IBlobInfo> updatedBlobs;

    @objid ("4fbcbb09-64f6-4b99-9dd3-b13fa19a83ab")
    private Collection<IBlobInfo> deletedBlobs;

    @objid ("83839b9e-aaed-447f-a6d1-3352013586a3")
    private IProjectService projectService;

    @objid ("1919aab2-a9cb-4335-8508-be14e61cd0b1")
    private IMModelServices mmServices;

    /**
     * @param projectService the project services
     * @param mmServices the model services for the project
     */
    @objid ("b525246b-ef2c-4850-9b81-6b96606f1232")
    public StereotypeEditor(IProjectService projectService, IMModelServices mmServices) {
        super();
        this.projectService = projectService;
        this.mmServices = mmServices;
        this.createdBlobs = new ArrayList<>();
        this.deletedBlobs = new ArrayList<>();
        this.updatedBlobs = new ArrayList<>();
    }

    /**
     * @param selectedElements the selection
     * @return the created stereotype.
     */
    @objid ("6c877053-f95d-4575-9418-5c3a5fc37495")
    public Stereotype create(Profile profile, List<ModelElement> selectedElements, IProjectFragment fragment, boolean showApplyStereotype) {
        // Prompt the user for the new note type data
        StereotypeEditionDataModel dataModel = promptUserBeforeCreate(selectedElements, showApplyStereotype);
        Stereotype stereotype = null;
        
        if (dataModel != null) {
            stereotype = createStereotype(profile, selectedElements, fragment, dataModel);
        }
        return stereotype;
    }

    @objid ("eb77e807-8402-4a0e-aca0-d80936ab6a16")
    public void edit(Stereotype editedStereotype) {
        // Prompt the user for the new note type data
        StereotypeEditionDataModel dataModel = promptUserBeforeEdit(editedStereotype);
        
        if (dataModel != null) {
            editStereotype(editedStereotype, dataModel);
        }
    }

    /**
     * @param elements
     * 
     * @param showApplyStereotype @return
     */
    @objid ("3c8af456-467f-45d3-bd09-99a8124ea874")
    private StereotypeEditionDataModel promptUserBeforeCreate(List<ModelElement> elements, boolean showApplyStereotype) {
        boolean show = showApplyStereotype;
        ModelElement element = null;
        
        if (elements.size() == 1) {
            element = elements.get(0);
        }
        
        String metaclassName = null;
        
        ICoreSession session = this.projectService.getSession();
        
        if (element == null ||
                element instanceof ModuleComponent ||
                element instanceof Profile ||
                element instanceof Project ||
                element instanceof TagType ||
                element instanceof NoteType ||
                element instanceof Stereotype ||
                element instanceof ModuleParameter) {
            metaclassName = "ModelElement";
        
            if (session != null && element != null) {
                MStatus status = element.getStatus();
                if (show) {
                    show = status.isModifiable();
                }
            }
        } else {
            metaclassName = element.getMClass().getQualifiedName();
        
            if (session != null) {
                MStatus status = element.getStatus();
                if (show) {
                    show = status.isModifiable();
                }
            }
        }
        
        StereotypeEditionDataModel dataModel = new StereotypeEditionDataModel(metaclassName, null, this.projectService.getOpenedProject().getProjectFileStructure().getProjectRuntimePath());
        dataModel.setApplyStereotype(show);
        StereotypeEditionDialog dialog = new StereotypeEditionDialog(null, dataModel, this.projectService, this.mmServices, elements);
        
        // Open the main window
        // Don't return from open() until dialog window closes
        dialog.setBlockOnOpen(true);
        int code = dialog.open();
        
        if (code == IDialogConstants.OK_ID) {
            return dataModel;
        }
        return null;
    }

    /**
     * @param editedStereotype @return
     */
    @objid ("dd96b3de-549f-4875-bb12-f029d69c138c")
    private StereotypeEditionDataModel promptUserBeforeEdit(Stereotype editedStereotype) {
        ICoreSession session = this.projectService.getSession();
        // Local module path
        GProject openedProject = this.projectService.getOpenedProject();
        
        StereotypeEditionDataModel dataModel = new StereotypeEditionDataModel(editedStereotype.getBaseClassName(), editedStereotype, openedProject.getProjectFileStructure().getProjectRuntimePath());
        dataModel.setStereotypeName(editedStereotype.getName());
        
        IRepository repository = session.getRepositorySupport().getRepository(editedStereotype);
        
        if (!editedStereotype.getIcon().isEmpty()) {
            Path iconPath = dataModel.getLocalPath().resolve("tempIcon.png");
            if (readStereotypeBlob(repository, editedStereotype.getUuid() + StereotypeEditor.ICON, iconPath)) {
                dataModel.setExplorerIcon(iconPath.toString());
            }
        }
        
        if (!editedStereotype.getImage().isEmpty()) {
            Path imagePath = dataModel.getLocalPath().resolve("tempImage.png");
            if (readStereotypeBlob(repository, editedStereotype.getUuid() + StereotypeEditor.IMAGE, imagePath)) {
                dataModel.setDiagramImage(imagePath.toString());
            }
        }
        
        StereotypeEditionDialog dialog = new StereotypeEditionDialog(null, dataModel, this.projectService, this.mmServices, new ArrayList<ModelElement>());
        
        // Open the main window
        // Don't return from open() until dialog window closes
        dialog.setBlockOnOpen(true);
        int code = dialog.open();
        
        if (code == IDialogConstants.OK_ID) {
            return dataModel;
        }
        return null;
    }

    /**
     * @param stereotype
     * @param dataModel
     */
    @objid ("ea52d560-a260-4de5-aa9a-f836e88d224d")
    private void addStereotypeOnSelectedElements(Stereotype stereotype, List<ModelElement> selectedElements, StereotypeEditionDataModel dataModel) {
        Class<? extends MObject> metaclass = stereotype.getMClass().getMetamodel().getMClass(dataModel.getMetaclassName()).getJavaInterface();
        
        if (metaclass == null) {
            return;
        }
        
        for (ModelElement element : selectedElements) {
            if (!(element instanceof Profile) && metaclass.isAssignableFrom(element.getClass())) {
                element.getExtension().add(stereotype);
            }
        }
    }

    /**
     * Create a stereotype.
     * @param projectService
     * @param profile
     * @param fragment
     * @param dataModel
     */
    @objid ("cb85f3e9-1dbf-43d1-a79d-fd58fd8be78a")
    private Stereotype createStereotype(Profile profile, List<ModelElement> selectedElements, IProjectFragment fragment, StereotypeEditionDataModel dataModel) {
        ICoreSession session = this.projectService.getSession();
        IInfrastructureModelFactory factory = this.mmServices.getModelFactory().getFactory(IInfrastructureModelFactory.class);
        Profile ownerProfile = profile;
        Stereotype stereotype = null;
        
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Create Stereotype")) {
            if (ownerProfile == null) {
                LocalProfileCreator localModuleCreator = new LocalProfileCreator(this.projectService);
                ownerProfile = localModuleCreator.execute(fragment);
            }
            if (ownerProfile != null) {
                stereotype = factory.createStereotype();
                ownerProfile.getDefinedStereotype().add(stereotype);
                setStereotypeValues(session, stereotype, dataModel);
        
                if (dataModel.isApplyStereotype()) {
                    addStereotypeOnSelectedElements(stereotype, selectedElements, dataModel);
                }
        
                transaction.commit();
            }
        }
        return stereotype;
    }

    /**
     * Edit a stereotype.
     * @param stereotype
     * @param dataModel
     */
    @objid ("74d9f5eb-3c8a-40b0-8f85-6f209a05d37f")
    private void editStereotype(Stereotype stereotype, StereotypeEditionDataModel dataModel) {
        ICoreSession session = this.projectService.getSession();
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Edit Stereotype")) {
            setStereotypeValues(session, stereotype, dataModel);
            transaction.commit();
        } catch (Exception e) {
            ModelProperty.LOG.error(e);
        }
    }

    /**
     * Set stereotype values(name, icon, image...).
     * @param session
     * @param stereotype
     * @param dataModel
     */
    @objid ("0d02c9ea-c6c1-4806-b5ac-107c6d337068")
    private void setStereotypeValues(ICoreSession session, Stereotype stereotype, StereotypeEditionDataModel dataModel) {
        stereotype.setName(dataModel.getStereotypeName());
        stereotype.setLabelKey(dataModel.getStereotypeName());
        stereotype.setBaseClassName(dataModel.getMetaclassName());
        stereotype.setIcon(dataModel.getIconName());
        stereotype.setImage(dataModel.getImageName());
        
        IRepository repository = session.getRepositorySupport().getRepository(stereotype);
        processStereotypeIconBlob(stereotype, dataModel, repository);
        processStereotypeImageBlob(stereotype, dataModel, repository);
        
        BlobChangeEvent blobEvent = new BlobChangeEvent(this.createdBlobs, this.deletedBlobs, this.updatedBlobs);
        session.getBlobSupport().fireBlobsChanged(blobEvent);
    }

    /**
     * Process the explorer icon blob of a stereotype
     * @param stereotype
     * @param dataModel
     * @param repository
     */
    @objid ("eb3eb151-f1d6-424d-8fd8-e268d02c6f17")
    private void processStereotypeIconBlob(Stereotype stereotype, StereotypeEditionDataModel dataModel, IRepository repository) {
        try {
            File iconFile = new File(dataModel.getExplorerIcon());
            IBlobInfo iconBlob;
            iconBlob = repository.readBlobInfo(stereotype.getUuid() + StereotypeEditor.ICON);
            if (iconFile.exists()) {
                if (iconBlob == null) {
                    iconBlob = new BlobInfo(stereotype.getUuid() + StereotypeEditor.ICON);
                    createStereotypeBlob(repository, iconFile, iconBlob);
                } else {
                    updateStereotypeBlob(repository, iconFile, iconBlob);
                }
            } else {
                if (iconBlob != null) {
                    deleteStereotypeBlob(repository, iconBlob);
                }
            }
        } catch (IOException e) {
            ModelProperty.LOG.error(e);
        }
    }

    /**
     * Process the diagram image blob of a stereotype
     * @param stereotype
     * @param dataModel
     * @param repository
     */
    @objid ("3f12b33f-2105-4006-bf24-b1f8b36afc06")
    private void processStereotypeImageBlob(Stereotype stereotype, StereotypeEditionDataModel dataModel, IRepository repository) {
        try {
            File imageFile = new File(dataModel.getDiagramImage());
            IBlobInfo imageBlob;
            imageBlob = repository.readBlobInfo(stereotype.getUuid() + StereotypeEditor.IMAGE);
            if (imageFile.exists()) {
                if (imageBlob == null) {
                    imageBlob = new BlobInfo(stereotype.getUuid() + StereotypeEditor.IMAGE);
                    createStereotypeBlob(repository, imageFile, imageBlob);
                } else {
                    updateStereotypeBlob(repository, imageFile, imageBlob);
                }
            } else {
                if (imageBlob != null) {
                    deleteStereotypeBlob(repository, imageBlob);
                }
            }
        } catch (IOException e) {
            ModelProperty.LOG.error(e);
        }
    }

    /**
     * Read a blob of the given key, and copy it to a temperate file.
     */
    @objid ("997985a6-e3d0-4809-a824-78d47dd03c0d")
    private boolean readStereotypeBlob(IRepository repository, String key, Path tempPath) {
        try (InputStream in = repository.readBlob(key);) {
            if (in != null) {
                tempPath.toFile().mkdirs();
                Files.copy(in, tempPath, StandardCopyOption.REPLACE_EXISTING);
                return true;
            }
        } catch (IOException e) {
            ModelProperty.LOG.error(e);
        }
        return false;
    }

    /**
     * Write the given blob.
     * @param repository
     * @param file
     * 
     * @param blob @return
     */
    @objid ("087430e0-c507-4f52-aaa8-13edc98cc47e")
    private boolean writeStereotypeBlob(IRepository repository, File file, IBlobInfo blob) {
        try (OutputStream out = repository.writeBlob(blob);) {
            Files.copy(file.toPath(), out);
            file.delete();
            return true;
        } catch (IOException e) {
            ModelProperty.LOG.error(e);
        }
        return false;
    }

    /**
     * Add the given blob to createdBlobs collection if write it successfully.
     * @param repository
     * @param file
     * @param blob
     */
    @objid ("b5aa69e2-70c9-4da6-aebd-ddcb26dbf41e")
    private void createStereotypeBlob(IRepository repository, File file, IBlobInfo blob) {
        if (writeStereotypeBlob(repository, file, blob)) {
            this.createdBlobs.add(blob);
            ModelProperty.LOG.debug("Blob created: " + blob.getKey());
        }
    }

    /**
     * Add the given blob to updatedBlobs collection if write it successfully.
     * @param repository
     * @param file
     * @param blob
     */
    @objid ("abeb0cb6-a446-4a41-9181-6f5eda1a93c5")
    private void updateStereotypeBlob(IRepository repository, File file, IBlobInfo blob) {
        if (writeStereotypeBlob(repository, file, blob)) {
            this.updatedBlobs.add(blob);
            ModelProperty.LOG.debug("Blob updated: " + blob.getKey());
        }
    }

    /**
     * Delete given stereotype blob.
     * @param repository
     * @param blob
     */
    @objid ("0357aec6-474c-44f9-b855-bbd87da0f3b8")
    private void deleteStereotypeBlob(IRepository repository, IBlobInfo blob) {
        try {
            repository.removeBlob(blob.getKey());
            this.deletedBlobs.add(blob);
        } catch (IOException e) {
            ModelProperty.LOG.error(e);
        }
    }

}
