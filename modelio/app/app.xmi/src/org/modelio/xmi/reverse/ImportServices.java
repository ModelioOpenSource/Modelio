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
package org.modelio.xmi.reverse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ResourceLoader;
import org.modelio.xmi.util.XMIFileUtils;

/**
 * This class provides all import services
 * @author ebrosse
 */
@objid ("50c0c8f2-7390-46f9-b380-d79dd470008d")
public class ImportServices {
    @objid ("dd0291cc-7ad7-422f-9923-1d7a3709a175")
    private int ownedElements = 0;

    @objid ("379d1b50-de78-4b0c-8bb9-acdaf444751e")
    private List<String> profiles = new ArrayList<>();

    @objid ("178269f7-c1d6-40dd-8299-8c64c15addec")
    private Resource loadEcoreModel(File xmiFile) {
        ResourceSet resourceSet = ReverseProperties.getInstance().createResourceSet();
        URI fileURI = URI.createFileURI(xmiFile.getAbsolutePath());
        Resource result = null;
        
        try{
            result = resourceSet.getResource(fileURI, true);
        }catch(Exception e){
        
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);
            deleteUnresolvedReference(xmiFile, fileURI, resourceSet );
            result = resourceSet.getResource(fileURI, false);
        
            //ecriture du log
            if (result != null){
                for (Resource.Diagnostic diagnostic : result.getErrors()){
                    String message = Xmi.I18N.getMessage("info.import.emf.error", diagnostic.getMessage());
                    Xmi.LOG.error(Xmi.PLUGIN_ID, message);
                }
            }
        }
        return result;
    }

    @objid ("1c27fa27-dc7e-4a87-a788-f6d4dc9007bb")
    private void attachExternalElements(OwnedCompositionUml2Visitor visitEcoreModel) {
        PartialImportMap partialImportMap = PartialImportMap.getInstance();
        TotalImportMap totalImportMap = TotalImportMap.getInstance();
        
        List <org.eclipse.uml2.uml.Element> externalElements = new ArrayList <>();
        
        for (Object key : partialImportMap.keySet()) {
            if (key != null){
                if (key instanceof org.eclipse.uml2.uml.Element) {
                    externalElements.add((org.eclipse.uml2.uml.Element) key);
                }else{
                    partialImportMap.remove(key);
                }
            }
        }
        
        for (org.eclipse.uml2.uml.Element externalElt : externalElements) {
            if (EcoreModelNavigation.isModelChild(externalElt)){
                if (externalElt instanceof org.eclipse.uml2.uml.Model){
                    totalImportMap.put(externalElt, partialImportMap.remove(externalElt));
                } else if (totalImportMap.get(externalElt) != null) {
                    partialImportMap.remove(externalElt);
                } else if (externalElt instanceof org.eclipse.uml2.uml.PrimitiveType) {
                    attachExternalPrimitiveType((org.eclipse.uml2.uml.PrimitiveType) externalElt, partialImportMap, totalImportMap);
                }
            }else{
        
                Object result = partialImportMap.get(externalElt);
                if (result != null){
                    if (result instanceof Element){
                        Element importedElt = (Element) result;
                        if (((importedElt.getStatus().isModifiable())
                                && (importedElt.getCompositionOwner() == null))){
                            ((Element) result).delete();
                        }
                    }else{
                        if (result instanceof List<?>){
                            for (Object obj: (List<?>) result){
                                if (obj instanceof Element){
                                    Element importedElt = (Element) obj;
                                    if (((importedElt.getStatus().isModifiable())
                                            && (importedElt.getCompositionOwner() == null))){
                                        importedElt.delete();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            partialImportMap.remove(externalElt);
        }
        
        if (partialImportMap.size() != 0) {
            attachExternalElements(visitEcoreModel);
        }
        
    }

    /**
     * @return
     */
    @objid ("3c930297-3485-4ed3-a72c-c7fa35a33fcd")
    public Resource getResource(File xmiFile) throws Exception {
        File newFile = null;
        File tempFile = null;
        List<File> listeFile = new ArrayList<>();
        Resource newresource = null;
        
        Boolean isOMGUML  = XMIFileUtils.containsPatterns(xmiFile, getOldUMLPatterns());
        
        // get patterns from OMGUML or Ecore to target Ecore
        List<String> oldPatterns = null;
        List<String> newPatterns = null;
        
        if (isOMGUML){
            oldPatterns = getOldUMLPatterns();
            newPatterns = getNewUMLPatterns();
        }else{
            oldPatterns = getOldEMFPatterns();
            newPatterns = getNewEMFPatterns();
        }
        
        try{
            //Update UML or Ecore to target Ecore
            newFile = updateFile(xmiFile, oldPatterns, newPatterns);
        
            //Get all applied profiles
            this.profiles = XMIFileUtils.getAllAppliedProfiles(xmiFile.getAbsolutePath());
        
            //Update UML or Ecore profile to target Ecore profile
            String directory = xmiFile.getParent();
        
            for (String profile : this.profiles){
                try{
                    File profileFile =  new File(directory + java.io.File.separator + profile);
                    if (profileFile.exists()){
                        tempFile = updateFile(profileFile, oldPatterns, newPatterns);
                        listeFile.add(tempFile);
                    }
                }catch(Exception e){
                    Xmi.LOG.error(Xmi.PLUGIN_ID, e);
                }
            }
        
            //applied OMG profiles
            if ((isOMGUML) && (!this.profiles.isEmpty())){
                newFile = applyOMGProfile(newFile, listeFile);
            }
        
            newresource = loadEcoreModel(newFile);
        
            //Update UML or Ecore ressources to target Ecore profile
            ResourceSet resourceset = newresource.getResourceSet();
        
            for (Resource resource : resourceset.getResources()){
        
                if (resource.getURI().toFileString() != null){
                    tempFile = updateFile(new File(resource.getURI().toFileString()),
                            oldPatterns, newPatterns);
        
                    resource.setURI(URI.createFileURI(tempFile.getAbsolutePath()));
                    listeFile.add(tempFile);
                }
            }
        
            newresource = loadEcoreModel(newFile);
        
        }catch (Exception e){
        //            Xmi.LOG.error(Xmi.PLUGIN_ID, e);
        //            if (newFile != null){
        //                newFile.delete();
        //                for (File fileTemp : listeFile){
        //                    fileTemp.delete();
        //                }
        //            }
        }
        return newresource;
    }

    /**
     * @return false if any errors occurs
     * @param resource : the Ecore resource
     * @param progressBar : the progressBar
     * @param shell : the parent Shell
     */
    @objid ("9843ce16-2b0e-4701-8a22-b0a2c6d98ada")
    public void importEcoreModel(Resource resource, ProgressBarComposite progressBar, Shell shell) {
        if (resource != null) {
        
            boolean isAEcoreModel = isEcoreModel(resource);
        
            if (isAEcoreModel) {
                importModel(progressBar);
            } else {
                String message = Xmi.I18N.getString("info.import.result_failed.notModel");
                ReverseProperties.getInstance().addError(message);
                Xmi.LOG.error(Xmi.PLUGIN_ID, message);
            }
        
        }
        
    }

    @objid ("a30d057e-ac4d-4783-b9a1-9a28874b4c29")
    private int initProgressBar(org.eclipse.uml2.uml.Package modelToImport) {
        this.ownedElements = 0;
        
        for (Object elem : modelToImport.getOwnedMembers()) {
            if (elem instanceof org.eclipse.uml2.uml.Namespace)
                countOwnedElements((org.eclipse.uml2.uml.Namespace) elem);
        }
        return this.ownedElements * 2;
    }

    @objid ("f3a2189e-0e32-4411-a91c-f32b4949826a")
    private void countOwnedElements(org.eclipse.uml2.uml.Namespace elem) {
        this.ownedElements++;
        for (Object elt : elem.getOwnedElements()) {
            if (elt instanceof org.eclipse.uml2.uml.Namespace)
                countOwnedElements((org.eclipse.uml2.uml.Namespace) elt);
        }
        
    }

    @objid ("ce85c084-ca87-47d4-8bff-ee6c2e9c6794")
    private void attachExternalPrimitiveType(org.eclipse.uml2.uml.PrimitiveType externalElt, PartialImportMap partialImportMap, TotalImportMap totalImportMap) {
        DataType temp = (DataType) partialImportMap.remove(externalElt);
        
        if (temp.getOwner() == null){
            temp.setIsElementary(true);
            temp.setName(externalElt.getName());
        
            org.modelio.metamodel.uml.statik.Package root = ReverseProperties.getInstance().getExternalPackage();
            root.getOwnedElement().add(temp);
            temp.setOwner(root);
        
            totalImportMap.put(externalElt, partialImportMap.remove(externalElt));
        }
        
    }

    /**
     * This Method export a given profile in a given Ecore Resource
     * @param root : the root of the export
     * @param resource : the Ecore Resource
     * @param progressBar : the progressBar
     * @param shell : the current shell
     * @return true if no error happen
     */
    @objid ("db6b95e9-771e-4565-ac3b-932cd280aa2f")
    public boolean importEcoreProfile(Resource resource, ProgressBarComposite progressBar, Shell shell) {
        boolean error = false;
        
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        if (resource != null) {
            Profile profileToImport = null;
            boolean isAProfile = false;
        
            for (Object elem : resource.getContents()) {
                if (elem.getClass().getSimpleName().compareTo("ProfileImpl") == 0) {
                    isAProfile = true;
                    profileToImport = (Profile) elem;
                    revProp.setEcoreProfile(profileToImport);
                }
        
                if (elem.getClass().getSimpleName().compareTo("ModelImpl") == 0) {
                    for (Object packaged : ((org.eclipse.uml2.uml.Model) elem).getPackagedElements()) {
                        if (packaged.getClass().getSimpleName().compareTo("ProfileImpl") == 0) {
                            isAProfile = true;
                            profileToImport = (Profile) packaged;
                            revProp.setEcoreProfile(profileToImport);
                        }
                    }
                }
            }
        
            if (isAProfile) {
        
                revProp.setImportModelRoundtripMode(profileToImport);
        
                revProp.addEcoreModel(profileToImport);
        
                if(progressBar != null) progressBar.setNumberElement(initProgressBar(profileToImport));
        
                XMIImportBehavior importBehavior = new XMIImportBehavior(
                        progressBar);
                OwnedCompositionUml2Visitor visitEcoreModel = new OwnedCompositionUml2Visitor(
                        importBehavior, profileToImport);
                revProp.setEcoreVisitor(visitEcoreModel);
        
                visitEcoreModel.doSwitch(profileToImport);
        
                if(progressBar != null)  progressBar.setLabel(Xmi.I18N
                        .getString("progressBar.content.import.checkConsistency"));
        
                revProp.clean();
        
            } else {
                error = true;
        
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(), SWT.ICON_INFORMATION);
                        messageBox.setMessage(Xmi.I18N.getString("info.import.result_failed.notProfile"));
                        messageBox.setText(Xmi.I18N.getString("info.import.result_failed.notProfile"));
                        messageBox.open();
                    }
                });
                Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N
                        .getString("info.import.result_failed.notProfile"));
            }
        
        }
        return error;
    }

    @objid ("8347ea4e-1f98-466c-bdc9-283897573a5e")
    private boolean isEcoreModel(Resource resource) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        boolean result = false;
        
        for (Object elem : resource.getContents()) {
            String className = elem.getClass().getSimpleName();
            if (className.equals("ModelImpl") || className.equals("PackageImpl")) {
                revProp.addEcoreModel((org.eclipse.uml2.uml.Package) elem);
                result = true;
            }
        
        }
        return result;
    }

    @objid ("635dc388-58eb-48f0-a930-63c5edbd4f7b")
    private void importModel(ProgressBarComposite progressBar) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        PartialImportMap.getInstance().clear();
        TotalImportMap.getInstance().clear();
        
        for(org.eclipse.uml2.uml.Package modelToImport : revProp.getEcoreModels()){
        
            revProp.setImportModelRoundtripMode(modelToImport);
        
            if (progressBar != null)
                progressBar.setNumberElement(initProgressBar(modelToImport));
        
            XMIImportBehavior importBehavior = new XMIImportBehavior(
                    progressBar);
            OwnedCompositionUml2Visitor visitEcoreModel = new OwnedCompositionUml2Visitor(
                    importBehavior, modelToImport);
            revProp.setEcoreVisitor(visitEcoreModel);
        
            ModelElement objingTechnicalRoot = revProp.getRootElements().get(0);
            TotalImportMap.getInstance().put(modelToImport, objingTechnicalRoot);
            visitEcoreModel.doSwitch(modelToImport);
        
            attachExternalElements(visitEcoreModel);
        }
        
        if (progressBar != null)
            progressBar.setLabel(Xmi.I18N.getString("progressBar.content.import.checkConsistency"));
        
        // To finish when it.next working !
        
        if (progressBar != null)
            progressBar.addFinalValue();
        
        revProp.clean();
        
    }

    @objid ("3cf1765e-63aa-4aa5-8cfe-9d4004cf51a4")
    private void deleteUnresolvedReference(File currentfile, URI fileURI2, ResourceSet resourceSet2) {
        Resource result = resourceSet2.getResource(fileURI2, false);
        
        if ((result != null) && (result.getErrors() != null) && (result.getErrors().size() != 0)){
            XMIFileUtils.removeReferences(currentfile.getAbsolutePath(),result.getErrors());
        }
        
    }

    @objid ("b91a9b44-fef8-4131-8ff1-5822664649be")
    private File updateFile(final File oldFile, final List<String> oldPatterns, final List<String> newPatterns) {
        String fileName = oldFile.getName();
        
        String tempFolderPath =  ReverseProperties.getInstance().getXMITempFolderPath();
        
        String newFilePath = tempFolderPath + java.io.File.separator  + fileName;
        File newFile = new File(newFilePath);
        
        String tempPath = tempFolderPath + java.io.File.separator  + fileName + ".temp";
        File tempFile = new File(tempPath);
        
        String tempPath2 = tempFolderPath + java.io.File.separator + fileName + ".temp2";
        File tempFile2 = new File(tempPath2);
        
        String tempPath3 = tempFolderPath + java.io.File.separator + fileName  + ".temp3";
        File tempFile3 = new File(tempPath3);
        
        String tempPath4 = tempFolderPath + java.io.File.separator + fileName  + ".temp4";
        File tempFile4 = new File(tempPath4);
        
        try{
        
            if (tempFile.exists())
                tempFile.delete();
        
            if (tempFile2.exists())
                tempFile2.delete();
        
        
            XMIFileUtils.replace(oldFile.getAbsolutePath(), tempPath, oldPatterns, newPatterns);
        
            File xslImportFile = ResourceLoader.getInstance().getResource("xslt" + java.io.File.separator + "import.xsl");
            XMIFileUtils.applyXSLT(tempPath, tempPath2, xslImportFile);
        
            if (tempFile3.exists())
                tempFile3.delete();
        
            File xslImport2File = ResourceLoader.getInstance().getResource("xslt" + java.io.File.separator + "import2.xsl");
            XMIFileUtils.applyXSLT(tempPath2, tempPath3, xslImport2File);
        
            if (tempFile4.exists())
                tempFile4.delete();
        
            File xslImport3File = ResourceLoader.getInstance().getResource("xslt" + java.io.File.separator + "import3.xsl");
            XMIFileUtils.applyXSLT(tempPath3, tempPath4, xslImport3File);
        
            try{
                newFile.delete();
                newFile.createNewFile();
            }catch(IOException e){
                Xmi.LOG.error(Xmi.PLUGIN_ID, e);
            }
        
            XMIFileUtils.copyFile(tempFile4, newFile);
        
        }finally{
            tempFile.delete();
            tempFile2.delete();
            tempFile3.delete();
            tempFile4.delete();
        }
        return newFile;
    }

    @objid ("73adb37d-ba61-4908-912d-d0cb284caf22")
    private List<String> getOldUMLPatterns() {
        List<String> patterns = new LinkedList<>();
        
        patterns.add("xmi:version=[\"]?[\']?20131001[\"]?[\']?");
        patterns.add("xmi:version=[\"]?[\']?20110701[\"]?[\']?");
        
        
        patterns.add("xmlns:xmi=[\"]?[\']?http://www.omg.org/spec/XMI/20131001[\"]?[\']?");
        patterns.add("xmlns:xmi=[\"]?[\']?http://www.omg.org/spec/XMI/20110701[\"]?[\']?");
        
        patterns.add("xmlns:uml[\\s]?=[\\s]?[\"]?[\']?http://www.eclipse.org/uml2/5.0.0/UML[\"]?[\']?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?[\"]?[\']?http://schema.omg.org/spec/UML/2.1.1/uml.xml[\"]?[\']?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?[\"]?[\']?http://www.omg.org/spec/UML/20090901[\"]?[\']?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?[\"]?[\']?http://www.omg.org/spec/UML/20100901[\"]?[\']?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?[\"]?[\']?http://www.omg.org/spec/UML/20110701[\"]?[\']?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?[\"]?[\']?http://www.omg.org/spec/UML/20131001[\"]?[\']?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?\'?\"?http://schema.omg.org/spec/UML/2.[1.1]?[1.2]?[1]?[2]?[3]?\'?\"");
        
        patterns.add("http://schema.omg.org/spec/UML/2.2/ [http://www.eclipse.org/uml2/3.0.0/UML]?");
        patterns.add("http://schema.omg.org/spec/UML/2.1.1/uml.xml");
        patterns.add("http://schema.omg.org/spec/UML/2.2/uml.xml");
        
        patterns.add("http://www.omg.org/spec/UML/20090901/UML.xmi");
        patterns.add("http://www.omg.org/spec/UML/20110701/PrimitiveTypes.xmi");
        
        patterns.add("xmlns:StandardProfileL2=\"http://www.omg.org/spec/UML/20090901/StandardProfileL2\"");
        patterns.add("<StandardProfileL2:");
        patterns.add("<appliedProfile xmi:type=\"uml:Profile\" href=\"http://www.omg.org/spec/UML/20090901/StandardProfileL2.xmi#_0\"/>");
        return patterns;
    }

    @objid ("90f607a8-4379-4aa5-bb79-9dec705ed493")
    private List<String> getNewUMLPatterns() {
        List<String> patterns = new LinkedList<>();
        
        patterns.add("xmi:version=\"2.1\"");
        patterns.add("xmi:version=\"2.1\"");
        
        patterns.add("xmlns:xmi=\"http://schema.omg.org/spec/XMI/2.1\"");
        patterns.add("xmlns:xmi=\"http://schema.omg.org/spec/XMI/2.1\"");
        
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"" );
        
        patterns.add("pathmap://UML_METAMODELS/UML.metamodel.uml");
        patterns.add("pathmap://UML_METAMODELS/UML.metamodel.uml");
        patterns.add("pathmap://UML_METAMODELS/UML.metamodel.uml");
        
        patterns.add("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml");
        patterns.add("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml");
        
        patterns.add("xmlns:Standard=\"http://www.eclipse.org/uml2/schemas/Standard/1\"");
        patterns.add("<Standard:");
        patterns.add("<eAnnotations source=\"http://www.eclipse.org/uml2/2.0.0/UML\"><references href=\"http://www.eclipse.org/uml2/schemas/Standard/1#/\"/></eAnnotations><appliedProfile href=\"pathmap://UML_PROFILES/Standard.profile.uml#_0\"/>");
        return patterns;
    }

    @objid ("5b43ef46-fdcd-41f9-81ed-774baf5356ac")
    private List<String> getNewEMFPatterns() {
        List<String> patterns = new LinkedList<>();
        patterns.add("xmlns:xmi=\"http://schema.omg.org/spec/XMI/2.1");
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"");
        patterns.add("xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\"");
        patterns.add("href=\"http://www.eclipse.org/uml2/3.0.0/UML");
        return patterns;
    }

    @objid ("34de1e69-677d-4fb8-9262-83c3e60479ec")
    private List<String> getOldEMFPatterns() {
        List<String> patterns = new LinkedList<>();
        patterns.add("xmlns:xmi=\"http://www.omg.org/spec/XMI/20110701");
        patterns.add("xmlns:uml[\\s]?=[\\s]?\"?\'?http://www.eclipse.org/uml2/3.1.0/UML[/]?\"?\'?");
        patterns.add("xmlns:uml[\\s]?=[\\s]?\"?\'?http://www.eclipse.org/uml2/2.[01]+.0/UML[/]?\"?\'?");
        patterns.add("href[\\s]?=[\\s]?\"?\'?http://www.eclipse.org/uml2/2.[01]+.0/UML");
        return patterns;
    }

    @objid ("6ae77646-9a78-4335-9491-cc519c70bbc5")
    private void cleanExternalElements() {
        PartialImportMap partialImportMap = PartialImportMap.getInstance();
        
        List <org.eclipse.uml2.uml.Element> externalElements = new ArrayList <>();
        
        for (Object key : partialImportMap.keySet()) {
            if (key != null){
                if (key instanceof org.eclipse.uml2.uml.Element) {
                    externalElements.add((org.eclipse.uml2.uml.Element) key);
                }else{
                    partialImportMap.remove(key);
                }
            }
        }
        
        ReverseProperties revProp = ReverseProperties.getInstance();
        for (org.eclipse.uml2.uml.Element externalElt : externalElements) {
            Object objElts = revProp.getMappedElement(externalElt);
            if (objElts instanceof Element){
                ((Element) objElts).delete();
            }else{
                if (objElts instanceof List<?>)
        
                    for(Object objElt : (List<?>) objElts ){
                        if (objElt instanceof Element){
                            ((Element) objElt).delete();
                        }
                    }
            }
        }
        
    }

    @objid ("1c3328b1-3903-42fb-94e5-b70aade9416e")
    private File applyOMGProfile(File omgFile, List<File> listeFile) {
        File modelEmptyFile = new File(omgFile.getParentFile().getAbsolutePath() + File.separator + "empty.xmi");
        
        // Create a resource set.
        ResourceSet resourceSet = ReverseProperties.getInstance().createResourceSet();
        
        URI modelEmptyURI = URI.createFileURI(modelEmptyFile.getAbsolutePath());
        
        Resource resourceEmpty = resourceSet.createResource(modelEmptyURI);
        
        org.eclipse.uml2.uml.Package modelEmpty =  null;
        if (omgFile.getName().contains(".profile.")){
            modelEmpty = UMLFactory.eINSTANCE.createProfile();
        }else{
            modelEmpty = UMLFactory.eINSTANCE.createModel();
        }
        
        resourceEmpty.getContents().add(modelEmpty);
        org.eclipse.uml2.uml.Class clazz = UMLFactory.eINSTANCE.createClass();
        
        modelEmpty.getOwnedTypes().add(clazz);
        
        applyStandardProfile(resourceSet, resourceEmpty, modelEmpty, clazz);
        
        //other profile
        applyProfiles(listeFile, resourceSet, resourceEmpty, modelEmpty, clazz);
        
        
        // merging
        File mergedFile = new File(omgFile.getParentFile().getAbsolutePath() + File.separator + "merged.xmi");
        
        if (!mergedFile.exists()){
            mergedFile.getParentFile().mkdirs();
            try {
                mergedFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
            String startingFile = XMIFileUtils.readEmptyFile(modelEmptyFile.getAbsolutePath());
            XMIFileUtils.mergeFile(omgFile.getAbsolutePath(), mergedFile, startingFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mergedFile;
    }

    @objid ("7ca4348f-bcbe-46e2-b4f7-a255040699b6")
    private void applyProfiles(List<File> listeFile, ResourceSet resourceSet, Resource resourceEmpty, org.eclipse.uml2.uml.Package modelEmpty, org.eclipse.uml2.uml.Class clazz) {
        for(File profileFile : listeFile){
            URI profileURI = URI.createFileURI(profileFile.getAbsolutePath());
        
            Resource profileresult = null;
            Profile profile = null;
            Stereotype stereotype = null;
        
            try{
                profileresult = resourceSet.getResource(profileURI, true);
        
                for (Object elem : profileresult.getContents()) {
        
                    if (elem instanceof Profile){
                        profile = ((Profile)elem);
        
                        Resource resourcemeta = resourceSet.getResource(URI.createURI(UMLResource.UML_METAMODEL_URI), true);
                        org.eclipse.uml2.uml.Model result = (org.eclipse.uml2.uml.Model) EcoreUtil.getObjectByType(resourcemeta.getContents(), UMLPackage.Literals.PACKAGE);
        
                        org.eclipse.uml2.uml.Class metaclassClass =  (org.eclipse.uml2.uml.Class) result.getOwnedType("Class");
        
                        profile.createMetaclassReference(metaclassClass);
                        stereotype = profile.createOwnedStereotype("empty", false);
                        stereotype.createExtension(metaclassClass, false);
        
                        profile.define();
                    }
                }
                profileresult.save(null);
        
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }catch(Exception e){
                e.printStackTrace(System.err);
            }
        
            modelEmpty.applyProfile(profile);
        
            try {
                resourceEmpty.save(null);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        
        
            try {
                clazz.applyStereotype(stereotype);
                resourceEmpty.save(null);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        
    }

    @objid ("bf52dd5f-619e-4a57-8e05-e4cc99f95a3c")
    private void applyStandardProfile(ResourceSet resourceSet, Resource resourceEmpty, org.eclipse.uml2.uml.Package modelEmpty, org.eclipse.uml2.uml.Class clazz) {
        //Apply standard profile
        Profile profileStandard = null;
        Stereotype stereotypeStandard = null;
        Resource profileStandardresult = null;
        
        try{
            profileStandardresult = resourceSet.getResource(URI.createURI(UMLResource.STANDARD_PROFILE_URI), true);
            for (Object elem : profileStandardresult.getContents()) {
        
                if (elem instanceof Profile){
                    profileStandard = ((Profile)elem);
                    stereotypeStandard = profileStandard.getOwnedStereotype("Focus");
                }
            }
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
        
        modelEmpty.applyProfile(profileStandard);
        
        try {
            resourceEmpty.save(null);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        
        try {
            clazz.applyStereotype(stereotypeStandard);
            resourceEmpty.save(null);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
    }

}
