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
package org.modelio.xmi.generation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.api.XMIExtension;
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.model.objing.profile.PExportProfile;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;
import org.modelio.xmi.util.ScopeChecker;
import org.modelio.xmi.util.XMIFileUtils;

/**
 * This class provides the XMI export services.
 * @author ebrosse
 */
@objid ("49ec20f8-6d04-453a-850d-8e1c718de33b")
public class ExportServices {
    @objid ("2e7f0915-aa80-4bfc-b6da-c60b9a0adbe1")
    private static final String version = "3.0.0";

    @objid ("ced3a2bd-49db-48b2-b391-1c30ddc35e44")
    private final Shell _shell;

    /**
     * @param theShell the current shell
     */
    @objid ("b540be75-ba78-4f13-90bb-fc5a60149afc")
    public  ExportServices(Shell theShell) {
        this._shell = theShell;
    }

    /**
     * This method counts the number of ModelTree available inside a given one's
     * @param theElements The roots packages
     * @return
     * The number of sub ModelTree
     */
    @objid ("54eff7ad-eedc-4af9-b328-c82688e14199")
    public int countModelTrees(List<ModelElement> elements) {
        int sum = 1;
        
        for (ModelElement theElement : elements){
            if (theElement instanceof ModelTree){
                ModelTree theModelTree = (ModelTree) theElement;
                List<ModelTree> ownedElts = theModelTree.getOwnedElement();
                for (ModelTree elt : ownedElts) {
                    sum += countModelTrees(elt);
                }
            }
        }
        return sum;
    }

    @objid ("c8ba2398-bff2-4aaf-bc25-5d6303073178")
    public int countModelTrees(ModelTree theElement) {
        int sum = 1;
        
        List<ModelTree> ownedElts = theElement.getOwnedElement();
        for (ModelTree elt : ownedElts) {
            sum += countModelTrees(elt);
        }
        return sum;
    }

    /**
     * Save the Modelio model in the Ecore resource
     * @param resource The Ecore resource
     * @param progressBar The progress bar of the XMI dialog
     * @return
     * false if an error occurs during the export
     */
    @objid ("361a08c2-8d1a-41e5-85d0-7ddb2d6a661e")
    public Resource createEcoreModel(ProgressBarComposite progressBar) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        PartialExportMap partialMap = PartialExportMap.getInstance();
        partialMap.clear();
        TotalExportMap totalMap = TotalExportMap.getInstance();
        totalMap.clear();
        
        List<ModelElement> selectedPkgs = genProp.getRootElements();
        
        Resource resource = XMIFileUtils.createResource(genProp.getFilePath());
        
        //create Model
        Model ecoreModel = UMLFactory.eINSTANCE.createModel();
        ObjingEAnnotation.setExporterVersion(ecoreModel, version);
        ObjingEAnnotation.setIsRoundTrip(ecoreModel, genProp.isRoundtripEnabled());
        ecoreModel.setName(selectedPkgs.get(0).getName());
        genProp.setEcoreModel(ecoreModel);
        
        for (ModelElement selectedPkg : selectedPkgs){
            partialMap.put(selectedPkg.getUuid().toString(), ecoreModel);
        }
        
        XMIExportBehavior exportBehavior = new XMIExportBehavior(progressBar);
        GenericMetamodelVisitor visitObjingModel = new GenericMetamodelVisitor(exportBehavior);
        genProp.setObjingVisitor(visitObjingModel);
        
        
        boolean ecoreRootNull = true;
        
        for (ModelElement rootElt : selectedPkgs) {
            rootElt.accept(visitObjingModel);
        
            org.eclipse.uml2.uml.Namespace ecoreRoot = (org.eclipse.uml2.uml.Namespace) genProp
                    .getMappedElement(rootElt);
        
            if (ecoreRoot != null) {
                ecoreRootNull = false;
                if (!ecoreRoot.equals(ecoreModel)){
                    ecoreModel.getPackagedElements().add((PackageableElement)ecoreRoot);
                }
        
                for (org.eclipse.uml2.uml.PrimitiveType primitive :  genProp.getEcoreTypes().getPredifinedTypeList()){
                    ecoreModel.getPackagedElements().add(primitive);
                }
            }
        }
        
        if (!ecoreRootNull) {
        
            if(progressBar != null)
                progressBar.setLabel(Xmi.I18N.getString("progressBar.content.export.XMIFileSave"));
        
            resource.getContents().add(ecoreModel);
        
            //define the profile structure
            profileDefinition();
        
            //save stereotype
            profileExport(progressBar);
        
            applyProfile();
        
            for (org.eclipse.uml2.uml.Element elt : partialMap.values()) {
                if (elt != null) {
                    elt.destroy();
                }
            }
            partialMap.clear();
            totalMap.clear();
        
            if(progressBar != null)
                progressBar.setLabel(Xmi.I18N.getString("progressBar.content.export.XMIFileSave"));
        
            boolean error = !(saveRessource(resource));
        
            if (error) {
                // Error when saving
                genProp.addError(Xmi.I18N.getString("info.export.result_failed.inSave"));
            }else{
                //Empty model
                if (ecoreModel.getPackagedElements().isEmpty())
                    genProp.addInfo( Xmi.I18N.getString("info.export.emptyModel.message"),
                            genProp.getRootElements().get(0),
                            Xmi.I18N.getString("info.export.emptyModel.description"));
            }
        
        } else {
            genProp.addError(Xmi.PLUGIN_ID, Xmi.I18N
                    .getString("info.export.result_failed.root_null"));
        
        }
        
        genProp.supressProfile();
        if (genProp.isSysMLApplied())
            genProp.supressSysMLProfile();
        return resource;
    }

    /**
     * Export the Modelio model into Ecore model
     * @param selectedPkg The root of the Modelio model
     * @param progressBar The progress bar of the XMI dialog
     * @return The Ecore model
     */
    @objid ("d6620193-d27f-42c2-ba96-752cfbdb374f")
    public Model createEcoreModel(Package selectedPkg, ProgressBarComposite progressBar) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        PartialExportMap partialMap = PartialExportMap.getInstance();
        TotalExportMap totalMap = TotalExportMap.getInstance();
        
        partialMap.clear();
        totalMap.clear();
        
        Model ecoreModel = UMLFactory.eINSTANCE.createModel();
        ObjingEAnnotation.setExporterVersion(ecoreModel, version);
        ObjingEAnnotation.setIsRoundTrip(ecoreModel, genProp.isRoundtripEnabled());
        
        List<ModelTree> exportScopeElts = new ArrayList<>();
        exportScopeElts.add(selectedPkg);
        genProp.setExportScopeElts(exportScopeElts);
        
        ecoreModel.setName(selectedPkg.getName());
        partialMap.put(selectedPkg.getUuid().toString(), ecoreModel);
        
        genProp.setEcoreModel(ecoreModel);
        
        XMIExportBehavior exportBehavior = new XMIExportBehavior(progressBar);
        GenericMetamodelVisitor visitObjingModel = new GenericMetamodelVisitor(exportBehavior);
        genProp.setObjingVisitor(visitObjingModel);
        
        boolean ecoreRootNull = true;
        for (ModelTree rootElt : exportScopeElts) {
            rootElt.accept(visitObjingModel);
        
            org.eclipse.uml2.uml.Namespace ecoreRoot = (org.eclipse.uml2.uml.Namespace) genProp.getMappedElement(rootElt);
        
            if (ecoreRoot != null) {
                ecoreRootNull = false;
                if (!ecoreRoot.equals(ecoreModel))
                    ecoreModel.getPackagedElements().add((PackageableElement)ecoreRoot);
            }
        }
        
        if (ecoreRootNull) {
        
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N
                    .getString("info.export.result_failed.root_null"));
        
        }
        return ecoreModel;
    }

    /**
     * Export a org.eclipse.uml2.uml.Profile in EMF format
     * @param progressBar The progress bar of the XMI dialog
     * @return
     * resource The EMF resource which will be the profile
     */
    @objid ("d8a4ed4f-3984-4400-aeb4-073b7ab703c0")
    public Resource createEcoreProfile(ProgressBarComposite progressBar) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        List<ModelElement> rootProfiles = genProp.getRootElements();
        Resource resource = XMIFileUtils.createResource(genProp.getFilePath());
        
        if (resource != null){
        
            PartialExportMap partialMap = PartialExportMap.getInstance();
            TotalExportMap totalMap = TotalExportMap.getInstance();
            partialMap.clear();
            totalMap.clear();
        
        
            org.eclipse.uml2.uml.Profile ecoreProfile = ProfileUtils.createEcoreProfile((Profile) rootProfiles.get(0));
        
            for(ModelElement rootProfile : rootProfiles){
                if (rootProfile instanceof Profile){
                    TotalExportMap.getInstance().put(rootProfile.getUuid().toString(), ecoreProfile);
                }
            }
        
            ScopeChecker scopeChecker = new ScopeChecker(rootProfiles);
            GenerationProperties.getInstance().setScopeChecker(scopeChecker);
        
            ProfileExportVisitorImpl profileVisitor = new ProfileExportVisitorImpl(progressBar);
            PExportProfile pprofile = new PExportProfile((Profile) rootProfiles.get(0));
            profileVisitor.visit(pprofile);
        
            if(progressBar!= null)
                progressBar.setLabel(Xmi.I18N.getString("progressBar.content.export.XMIFileSave"));
        
            ObjingEAnnotation.setExporterVersion(ecoreProfile, version);
        
            resource.getContents().add(ecoreProfile);
        
            for (org.eclipse.uml2.uml.Element elt : partialMap.values()) {
                if (elt != null) {
                    elt.destroy();
                }
            }
        
            try {
                resource.save(null);
            } catch (Exception ioe) {
                Xmi.LOG.error(ioe);
                Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N
                        .getString("info.export.result_failed"));
            }
        
            //Empty model
            if (ecoreProfile.getOwnedStereotypes().isEmpty()
                    && ecoreProfile.getPackagedElements().isEmpty())
        
                genProp.addInfo( Xmi.I18N.getString("info.export.emptyProfile.message"),
                        genProp.getRootElements().get(0),
                        Xmi.I18N.getString("info.export.emptyProfile.description"));
        
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N
                    .getString("info.export.result_done"));
        
            if(progressBar!= null)
                progressBar.addFinalValue();
        
        }
        return resource;
    }

    @objid ("7c89c67b-f1f1-4fc8-b662-2adf59dba967")
    private void applyProfile() {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        Model model = genProp.getEcoreModel();
        
        for(org.eclipse.uml2.uml.Profile profile : genProp.getExportedProfiles()){
            try{
                model.applyProfile(profile);
            }catch(Exception e){
                Xmi.LOG.error(e);
            }
        }
        
        for (ModelElement element :  genProp.getStereotypesExported()){
        
            org.eclipse.uml2.uml.Element ecoreElement = genProp.getMappedElement(element);
        
            if  (ecoreElement != null){
        
                exportStereotype(element, ecoreElement);
        
                //Apply tagged Values
                ProfileUtils.setValue(element, ecoreElement);
        
            }
        }
        
        genProp.cleanStereotypesExported();
        
    }

    @objid ("bb4ffffb-65be-40cb-833e-1719c7fec64e")
    private void exportStereotype(ModelElement element, org.eclipse.uml2.uml.Element ecoreElement) {
        // apply existing stereotypes
        
        GenerationProperties genProp  = GenerationProperties.getInstance();
        
        for (Stereotype stereotype : element.getExtension()){
        
            org.eclipse.uml2.uml.Stereotype ecoreStereotype = (org.eclipse.uml2.uml.Stereotype) genProp.getMappedElement(stereotype);
        
            if ((ecoreStereotype != null) &&  (ecoreElement.isStereotypeApplicable(ecoreStereotype))
                    && (!(ecoreElement.getAppliedStereotypes().contains(ecoreStereotype)))){
        
                try{
                    ecoreElement.applyStereotype(ecoreStereotype);
        
                }catch(IllegalArgumentException e){
                    Xmi.LOG.error(e);
                }
        
            }
        }
        
        // apply exiting metaclass references
        for(TaggedValue taggedValue : element.getTag()){
        
            MetaclassReference metaclassReference = taggedValue.getDefinition().getOwnerReference();
        
            if (metaclassReference != null){
        
                org.eclipse.uml2.uml.Stereotype ecoreStereotype = (org.eclipse.uml2.uml.Stereotype) genProp.getMappedElement(metaclassReference);
        
                if ((ecoreStereotype != null)
                        &&  ecoreElement.isStereotypeApplicable(ecoreStereotype)
                        && (!ecoreElement.getAppliedStereotypes().contains(ecoreStereotype)))
                    ecoreElement.applyStereotype(ecoreStereotype);
            }
        }
        
    }

    @objid ("4b62cbc4-dd28-4087-b797-9015e612b681")
    private void profileDefinition() {
        for(org.eclipse.uml2.uml.Profile profile : GenerationProperties.getInstance().getExportedProfiles()){
            profile.define();
        }
        
        if (GenerationProperties.getInstance().isSysMLApplied())
            GenerationProperties.getInstance().getSysMLProfile().define();
        
    }

    @objid ("f6581620-7016-432d-8bab-1fd63090a190")
    private void profileExport(ProgressBarComposite progressBar) {
        for(org.eclipse.uml2.uml.Profile profile : GenerationProperties.getInstance().getExportedProfiles()){
        
            if (profile.getOwner() == null) {
        
                if(progressBar != null) progressBar.setLabel(Xmi.I18N.getMessage("progressBar.content.export.XMIProfileSave", profile.getName()));
        
                String extension = null;
        
                if (GenerationProperties.getInstance().getFileExtension().equals(XMIExtension.XMI))
                    extension = ".xmi";
                else
                    extension = ".uml";
        
                String profilePath = GenerationProperties.getInstance().getFileDirectory() + java.io.File.separator
                        +  profile.getName() +  ".profile" + extension;
        
                Resource resourceProfile = XMIFileUtils.createResource(profilePath);
        
                resourceProfile.getContents().add(profile);
        
                for (Iterator<?> allContents = UML2Util.getAllContents(profile, true, false); allContents.hasNext();) {
                    EObject eObject = (EObject) allContents.next();
                    if (eObject instanceof org.eclipse.uml2.uml.Element) {
                        resourceProfile.getContents().addAll(((org.eclipse.uml2.uml.Element) eObject).getStereotypeApplications());
                    }
                }
        
                try {
                    resourceProfile.save(null);
        
                    if (!GenerationProperties.getInstance().getExportVersion().equals(FormatExport.EMF300))
                        XMIFileUtils.changeToUML(resourceProfile.getURI().toFileString());
        
                } catch (IOException ioe) {
                    Xmi.LOG.error(ioe);
                }
            }
        }
        
        
        if (GenerationProperties.getInstance().isSysMLApplied()){
        
            org.eclipse.uml2.uml.Profile profile = GenerationProperties.getInstance().getSysMLProfile();
        
            String profileName = profile.getName();
        
            if(progressBar != null) progressBar.setLabel(Xmi.I18N.getMessage("progressBar.content.export.XMIProfileSave", profileName));
        
            String extension = null;
        
            if (GenerationProperties.getInstance().getFileExtension().equals(XMIExtension.XMI))
                extension = ".xmi";
            else
                extension = ".uml";
        
            String profilePath = GenerationProperties.getInstance().getFileDirectory() + java.io.File.separator
                    + profileName +  ".profile" + extension;
        
            Resource resourceProfile = XMIFileUtils.createResource(profilePath);
        
            resourceProfile.getContents().add(profile);
        
            for (Iterator<?> allContents = UML2Util.getAllContents(profile, true, false); allContents.hasNext();) {
                EObject eObject = (EObject) allContents.next();
                if (eObject instanceof org.eclipse.uml2.uml.Element) {
                    resourceProfile.getContents().addAll(((org.eclipse.uml2.uml.Element) eObject).getStereotypeApplications());
                }
            }
        
            try {
                resourceProfile.save(null);
        
                if (!GenerationProperties.getInstance().getExportVersion().equals(FormatExport.EMF300))
                    XMIFileUtils.changeToUML(resourceProfile.getURI().toFileString());
        
            } catch (IOException ioe) {
                Xmi.LOG.error(ioe);
            }
        }
        
    }

    @objid ("2b68d8a7-d5d7-4dfc-a726-5f35466017ad")
    private boolean saveRessource(Resource resource) {
        File file = new File(resource.getURI().toFileString());
        
        if (file.exists())
            file.delete();
        
        try {
            resource.save(null);
        } catch (IOException e) {
            Xmi.LOG.error(e);
            return false;
        }
        return true;
    }

}
