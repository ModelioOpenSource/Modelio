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

package org.modelio.xmi.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.xmi.gui.report.ReportModel;
import org.osgi.framework.Bundle;

@objid ("8318c518-3338-41b4-81e7-d600b072150d")
public abstract class XMIProperties {
    @objid ("d8fa05d9-de62-472f-aec8-56b5709a828e")
    public static final String modelerModuleName = "ModelerModule";

    @objid ("5f5661ce-2f94-4f8f-81f1-d420eea55023")
    protected String filePath = "";

    @objid ("595fdaf3-6012-4797-96ac-77632fcbe410")
    private final String XMIFolderName = "XMI";

    @objid ("b7f55435-b474-4c18-be14-70c1c4e78b46")
    private final String tempFolderName = "temp";

    @objid ("950f807d-a406-423c-9130-a67b736835d9")
    protected final String unlimitedNaturalName = "unlimitedNatural";

    @objid ("7e42e787-4d7a-4d4d-b39b-777e1ee3dc98")
    protected ReportModel report = null;

    @objid ("4c4504e7-b01b-40d6-b525-bca0d27338c2")
    protected IMModelServices mmServices = null;

    @objid ("71b08b80-0fe8-4ef2-9e84-60cd4f2e8886")
    protected IModelioNavigationService navigationServices = null;

    @objid ("27207c13-5db6-4e99-9387-296c44783267")
    protected ModelioTypes modelioTypes = null;

    @objid ("524e3a84-b9bf-47fb-b74b-7106e4c9e56d")
    protected List<ModelElement> rootElements = null;

    @objid ("a87e964c-5d14-4380-8388-7e11a1f969f3")
    protected File tempFolder = null;

    @objid ("0be2c451-21f7-427e-a8be-9bfda122d494")
    public ReportModel getReportModel() {
        return this.report;
    }

    @objid ("bbbb62e4-3f2d-4340-a1f6-09ce51a3c014")
    public void setReportModel(final ReportModel newReport) {
        this.report = newReport;
    }

    @objid ("c1f84218-ce6b-4d82-8215-2941c765af7d")
    public List<ModelElement> getRootElements() {
        return this.rootElements;
    }

    @objid ("74a92716-a159-4732-9f4b-84e69066144c")
    public void setRootElements(final List<? extends ModelElement> newRoots) {
        this.rootElements = new ArrayList<>();
        for (ModelElement newRoot : newRoots){
            this.rootElements.add(newRoot);
        }
    }

    /**
     * This methods specify the path of the imported file
     * @param file : the imported file
     */
    @objid ("9e69c6c6-178e-499a-802e-e41acc667838")
    public void setFilePath(final File file) {
        this.filePath = file.getAbsolutePath();
    }

    /**
     * This methods returns the path of the imported file
     * @return the imported file path
     */
    @objid ("11b6416d-b815-4e3a-9bb7-e002a8832e6e")
    public String getFilePath() {
        return this.filePath;
    }

    @objid ("a30bec69-3d2f-45f8-babd-e5258d890148")
    public String getFileDirectory() {
        File temp = new File(this.filePath);
        return temp.getParentFile().getAbsolutePath();
    }

    /**
     * This method returns the model services
     * @return the model services
     */
    @objid ("b4d1d6d1-7dfb-4246-b605-038b26afd7c4")
    public IMModelServices getMModelServices() {
        return this.mmServices;
    }

    @objid ("d5f8af50-2aa5-4fde-b586-6611b463d5de")
    public ModelioTypes getModelioTypes() {
        return this.modelioTypes;
    }

    /**
     * This methods returns the path of the imported file
     * @return the imported file path
     */
    @objid ("32481333-7de8-4a00-a7e9-8f4ecb5e0048")
    public IModelioNavigationService getNavigationServices() {
        return this.navigationServices;
    }

    /**
     * Create ecore resoureSet with UML resource Factory
     * @return resoureSet
     */
    @objid ("f59a1ad2-cc9f-4eda-9b95-b76f407f1d3c")
    public ResourceSet createResourceSet() {
        // Create a resource set.
        ResourceSet resourceSet = new ResourceSetImpl();
        // Register the default resource factory -- only needed for
        // stand-alone!
        resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
        
        
        final Bundle bundle = Platform.getBundle("org.eclipse.uml2.uml.resources");
        
        IPath libraries = new Path("/libraries");
        IPath metamodels = new Path("/metamodels");
        IPath profile = new Path("/profiles");
        
        
        URI uriLibraries = URI.createURI(FileLocator.find(bundle, libraries, null).toExternalForm());
        URI uriMetamodels = URI.createURI(FileLocator.find(bundle, metamodels, null).toExternalForm());
        URI uriProfiles = URI.createURI(FileLocator.find(bundle, profile, null).toExternalForm());
        
        
        URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uriLibraries);
        URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uriMetamodels);
        URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uriProfiles);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new UMLResourceFactoryImpl());
        return resourceSet;
    }

    @objid ("d3452be3-f823-4b79-ae41-a6c5cbcdab18")
    public void setRootElement(final ModelElement newRoot) {
        this.rootElements = new ArrayList<>();
        this.rootElements.add(newRoot);
    }

    @objid ("ac98651d-1a32-4150-b7d0-29f1fc56f983")
    public void addWarning(final String initialMessage, final Element element, final String description) {
        this.report.addWarning(initialMessage, element, description);
    }

    @objid ("f8d9eb1b-96da-4e1c-a729-fb7200f231c8")
    public void addWarning(final String initialMessage, final String description) {
        this.report.addWarning(initialMessage, this.rootElements.get(0), description);
    }

    @objid ("640c4dfe-1421-4798-8168-5da7593682ad")
    public void addError(final String initialMessage, final Element element, final String description) {
        this.report.addError(initialMessage, element, description);
    }

    @objid ("95938d65-1d74-4027-b258-d521e04b80b6")
    public void addError(final String initialMessage, final String description) {
        this.report.addError(initialMessage, this.rootElements.get(0), description);
    }

    @objid ("75b277a1-b2b8-4503-b5c0-e6bc8e59c6db")
    public void addWarning(final String initialMessage) {
        this.report.addWarning(initialMessage, this.rootElements.get(0), initialMessage);
    }

    @objid ("80aeefa4-3cc3-4764-b937-c951653bea53")
    public void addWarning(final String initialMessage, final Element element) {
        this.report.addWarning(initialMessage, element, initialMessage);
    }

    @objid ("302ae2ae-dfb5-4623-a793-5650902b3fd1")
    public void addError(final String initialMessage, final Element element) {
        this.report.addError(initialMessage, element, initialMessage);
    }

    @objid ("8ea55ee1-0675-4027-956d-2651899777fb")
    public void addError(final String initialMessage) {
        this.report.addError(initialMessage, this.rootElements.get(0), initialMessage);
    }

    @objid ("12281288-6734-4ff8-8386-279f23871dbd")
    public void addInfo(final String message, final Element element, final String description) {
        this.report.addInfo(message, element, description);
    }

    @objid ("51a4323b-9382-489a-afc9-9718a1cd349d")
    public void addInfo(final String message, final String description) {
        this.report.addInfo(message,  this.rootElements.get(0), description);
    }

    @objid ("1b36098f-3487-41f6-b162-3d2ccdf46b05")
    public void addInfo(final String message) {
        this.report.addInfo(message,  this.rootElements.get(0), message);
    }

    @objid ("c3f97cde-55a0-4d55-80a2-fede2ca4bc9e")
    public void addInfo(final String message, final Element element) {
        this.report.addInfo(message, element, message);
    }

    @objid ("d3a143ef-cac8-4a5d-9606-fccebbf28943")
    public String getLogFilePath() {
        String logFilePath = this.filePath.substring(0, this.filePath.length() - 4);
        return logFilePath + ".log";
    }

    @objid ("09f2dbb2-a3a2-4e57-af79-ca75d22e78ab")
    public String getUnlimitedNaturalName() {
        return this.unlimitedNaturalName;
    }

    @objid ("df711a3b-c239-42d4-bbcb-e537e55fade2")
    public String getProjectRoot() {
        return GProject.getProject(this.rootElements.get(0)).getProjectPath().toAbsolutePath().toString();
    }

    @objid ("9ebc3fe2-123f-46e5-9814-870d2644e68b")
    public File getXMITempFolder() {
        if (this.tempFolder == null)
            this.tempFolder = new File(getProjectRoot() + java.io.File.separator + this.XMIFolderName + java.io.File.separator + this.tempFolderName);
        
        if(!(this.tempFolder.exists())){          
            this.tempFolder.mkdirs();
            this.tempFolder.mkdir();       
        }
        return this.tempFolder;
    }

    @objid ("78ed25ef-4e12-44dd-b383-c39067f0dd5a")
    public String getXMITempFolderPath() {
        return getXMITempFolder().getAbsolutePath();
    }

    @objid ("6a236fde-c6fa-4d78-8b2f-67f126becc76")
    public String getXMIFolder() {
        return getProjectRoot() + java.io.File.separator + this.XMIFolderName;
    }

    @objid ("7c127ba1-a549-4e8f-9e6c-e1b71211c551")
    public void cleanProperties() {
        if (this.tempFolder != null){
        
            String[] children = this.tempFolder.list(); 
            for (int i=0; i<children.length; i++)
                new File(this.tempFolder, children[i]).delete();
        
            this.tempFolder.delete();
            this.tempFolder = null;
            
        }
    }

    @objid ("97f4459f-e6fc-4c13-aba9-348bb06d85e1")
    protected void initialize(final IMModelServices mmService, final MMetamodel metamodel, final IModelioNavigationService navigationService) {
        this.modelioTypes = new ModelioTypes(mmService, metamodel);      
        this.mmServices = mmService;
        this.navigationServices = navigationService;
    }

}
