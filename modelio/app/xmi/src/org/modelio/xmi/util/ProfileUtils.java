/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.model.objing.profile.PExportProfile;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.PartialImportMap;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.reverse.TotalImportMap;

/**
 * This class provides services for profiles import/export.
 * @author ebrosse
 */
@objid ("3e795e0c-7127-427b-bf93-74a4c4d10982")
public class ProfileUtils {
    @objid ("d85a7601-da07-4907-9399-9b58eb3e7b8c")
    private static String nameSpacingSeparator = "::";

    @objid ("cce34204-ab16-40da-a9a6-073ccd43704b")
    private static String stereotypeIdSeparator = ";";

    @objid ("0e7767a9-a354-4d7e-a5b1-1b387339d9d3")
    private static ScopeChecker scopeChecker = new ScopeChecker(GenerationProperties
            .getInstance().getRootElements());

    /**
     * Get the content of an {@link Image} as {@link org.eclipse.swt.graphics.Image}
     * @link Image} to set
     * @link org.eclipse.swt.graphics.Image} content
     * 
     * @param image the UML {
     * @return {
     */
    @objid ("cc16a180-26d3-42a1-8d7e-4b4919737f66")
    public static org.eclipse.swt.graphics.Image getContent(final Image image) {
        if (image == null) {
            return null;
        }
        
        if (image.getContent() == null) {
            return null;
        }
        
        
        String rawData = image.getContent();
        StringTokenizer strToken = new StringTokenizer(rawData, " ");
        byte[]target=new byte[strToken.countTokens()];
        
        // decoding image
        int j = 0;
        String tempString = null;
        
        while(strToken.hasMoreTokens()){
            try {
                tempString = strToken.nextToken();
                Long.valueOf(tempString,16);
                Byte tempByte = Byte.valueOf(Byte.parseByte(tempString, 16));
                target[j] = tempByte.byteValue();
            }catch(NumberFormatException e){
                int temp = - (Integer.valueOf("ff",16) - Integer.valueOf(tempString,16) + 1);
                Byte tempByte = Byte.valueOf(String.valueOf(temp), 10);
                tempByte = Byte.decode(String.valueOf(temp));
                target[j] = tempByte.byteValue();
            }
            j++;
        }
        
        org.eclipse.swt.graphics.Image decodedImage =  new org.eclipse.swt.graphics.Image(null, new ByteArrayInputStream(target));
        return decodedImage;
    }

    @objid ("599cb712-e276-48a4-847b-be6c61312e03")
    public static List<String> getObjingNameClass(final String ecoreClassName) {
        List<String> result = ReverseProperties.getInstance().getClassNames(ecoreClassName);
        
        if (result.size() == 0){
            result.add("ModelElement");
        }
        return result;
    }

    @objid ("4278ef88-cfd5-4892-9604-9d96003c54f6")
    public static List<String> getEcoreNameClass(final String objClassName) {
        List<String> result = GenerationProperties.getInstance().getClassNames(objClassName);
        
        if (result.size() == 0)
            result.add("Element");
        return result;
    }

    @objid ("a3c3b449-22c7-4032-85d8-d6eec5c497fd")
    public static org.eclipse.uml2.uml.Stereotype createStereotype(final Stereotype obStereotype) {
        Object owner = GenerationProperties.getInstance().getMappedElement(obStereotype.getOwner());
        
        if (owner instanceof org.eclipse.uml2.uml.Profile) {
            
            org.eclipse.uml2.uml.Profile profile = (org.eclipse.uml2.uml.Profile) owner;
            String name = ProfileUtils.getStereotypeName(obStereotype);
            org.eclipse.uml2.uml.Stereotype stereotype  = profile.getOwnedStereotype(name);
        
            if (stereotype == null){
                stereotype = profile.createOwnedStereotype(name, false);
                setIconsProperties(stereotype, obStereotype);
                TotalExportMap.getInstance().put(obStereotype.getUuid().toString(), stereotype);
        
            }
        
            ObjingEAnnotation.setIsNamedWithConvention(stereotype, ProfileUtils.isNamedWithConvention(obStereotype));
            ObjingEAnnotation.addObjingID(stereotype, obStereotype.getUuid().toString());
        
            return stereotype;           
        }
        return null;
    }

    @objid ("b46fe5d0-a787-42b1-9dd6-9152c2a537d1")
    public static Image setStereotypeImage(final Path moduleResPath, final String iconPath, final org.eclipse.uml2.uml.Stereotype stereotype) {
        try {
        
            File imageFile = new File(moduleResPath.toString() + File.separator + iconPath);
        
            Image icon = stereotype.createIcon(imageFile.getAbsolutePath());
        
            icon.setFormat(iconPath.substring(iconPath.lastIndexOf(".") + 1, iconPath.length()));
        
            setContent(icon, imageFile);
        
            return icon;
        
        }catch(Exception e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);
            return null;
        }
    }

    @objid ("ea5d8042-cf6a-4302-a434-9cfbfa9c0b5f")
    public static String getNameSpaceName(final Class obStereotype) {
        String name = "";
        Package owner = (Package) obStereotype.getOwner();
        while (!(owner.getOwner() instanceof Component)){
            name = owner.getName() + nameSpacingSeparator + name;
            owner = (Package) owner.getOwner();
        }
        
        String temp = obStereotype.getName();
        if (temp.startsWith(name))
            return temp;
        else
            return (name + temp);
    }

    @objid ("f3020b80-76d3-49bf-97b6-3de99c2a5a50")
    public static String getLocalName(final Class obStereotype) {
        String path = "";
        Package owner = (Package) obStereotype.getOwner();
        while (!(owner.getOwner() instanceof Component)){
            path = owner.getName() + nameSpacingSeparator + path;
            owner = (Package)owner.getOwner();
        }
        
        String name = obStereotype.getName();
        if (name.startsWith(path))
            return name.replaceFirst(path, "");
        else
            return name ;
    }

    /**
     * Set the content of an {@link Image} with a file (containing an image)
     * @link Image} to set
     * 
     * @param image the UML {
     * @param imageFile the icon
     */
    @objid ("186a0e29-69ea-4e73-bfe4-4178c1964936")
    private static void setContent(final Image image, final File imageFile) {
        try{
        
            if (imageFile != null) {
                StringBuffer rawImageData = new StringBuffer();
                byte[] byteFlow = getBytesFromFile(imageFile);
        
                // file reading
                for (int i=0; i < byteFlow.length; i++ ) {
                    rawImageData.append(Integer.toString(byteFlow[i],0x10));
                    rawImageData.append(" ");
                }
        
                image.setContent(rawImageData.toString());
            }       
        
        
        }catch (Exception e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);
        }
    }

    /**
     * org.eclipse.uml2.uml.Read an image file content
     * @param file
     * @throws IOException
     * 
     * @return a table of bytes of the file content
     */
    @objid ("2154d545-1b59-4464-bb28-7515b7e9b9c4")
    private static byte[] getBytesFromFile(final File file) throws IOException {
        // Get the size of the file
        long length = file.length();
        
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
        
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            throw new IOException("Image too big to encode");
        }
        
        try(InputStream is = new FileInputStream(file);){
        
            //  Read in the bytes
            int offset = 0;
            int numRead = 0;
            while ((offset < bytes.length)
                    && ((numRead = is.read(bytes, offset, bytes.length-offset)) >= 0)) {
                offset += numRead;
            }
        
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        }
        return bytes;
    }

    @objid ("9033a2d5-211a-43e3-aadc-e231056d5662")
    public static List<String> getMetaclassHeritage(final NameSpace metaclass) {
        List<String> result = new  ArrayList<>();
        
        List<org.modelio.metamodel.uml.statik.Class> parentList = new  ArrayList<>();
        parentList.add((org.modelio.metamodel.uml.statik.Class) metaclass);
        result.add( metaclass.getName());
        
        while (parentList.size() != 0){
            List<org.modelio.metamodel.uml.statik.Class> temp = new  ArrayList<>();
            for(org.modelio.metamodel.uml.statik.Class parent : parentList){
                for(org.modelio.metamodel.uml.statik.Generalization generalization : parent.getParent()){
                    temp.add((org.modelio.metamodel.uml.statik.Class) generalization.getSuperType());
                }
            }
            parentList.clear();
            parentList.addAll(temp);
            for(org.modelio.metamodel.uml.statik.Class parent : parentList)
                result.add(parent.getName());
        }
        return result;
    }

    @objid ("8773c7c4-dac0-4677-a334-a78d3e0db967")
    public static String getNameSpacingSeparator() {
        return nameSpacingSeparator;
    }

    @objid ("560551e9-f432-4b2a-ab81-88454bdba5b9")
    private static MetaclassReference createReference(final org.eclipse.uml2.uml.Stereotype ecoreElt) {
        MetaclassReference objReference  = (MetaclassReference) ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createElement("MetaclassReference");
        objReference.setReferencedClassName(ecoreElt.getName());
        Object owner =  TotalImportMap.getInstance().get(ecoreElt.getOwner());
        if ((owner != null) && (owner instanceof Profile)){
            ((Profile) owner).getOwnedReference().add(objReference);
            objReference.setOwnerProfile(((Profile) owner));
        }
        TotalImportMap.getInstance().put(ecoreElt, objReference);
        return objReference;
    }

    @objid ("53ed07f0-595b-4b28-bc4e-9de0427c3e26")
    public static Object visitStereotype(final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        Object temp =  TotalImportMap.getInstance().get(ecoreElement);
        
        if (temp == null){
            if (ObjingEAnnotation.isReference(ecoreElement)){
                //Reference case
                return getReference(ecoreElement);
            }else{
                return createObjStereotype(ecoreElement);
            }
        }
        return temp;
    }

    @objid ("6513f35f-65ec-458e-a444-7b2ce5796bd8")
    public static void visitProperty(final Property ecoreElement) {
        Object result = TotalImportMap.getInstance().get(ecoreElement);
        
        if (result == null){
            org.eclipse.uml2.uml.Element ecoreOwner = ecoreElement.getOwner();
            Object owner = TotalImportMap.getInstance().get(ecoreOwner);
        
            if ((owner == null) && (ecoreOwner instanceof org.eclipse.uml2.uml.Stereotype)){
                visitStereotype((org.eclipse.uml2.uml.Stereotype) ecoreOwner);
                owner = TotalImportMap.getInstance().get(ecoreOwner);
            }
        
            if (owner != null){
                if (owner instanceof ArrayList){
                    visitStereotype((ArrayList<Stereotype>) owner, ecoreElement);
                }else{
                    visitReference((MetaclassReference)owner, ecoreElement);
                }
            }
        }
    }

    @objid ("9544f7d0-2c83-4ce3-b460-87d35ba4d7a9")
    private static void setProperties(final TagType tagType, final Property ecoreProperty) {
        setLabel(tagType, ecoreProperty);
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setHidden(tagType, ecoreProperty);
            setPartSignature(tagType, ecoreProperty);
        }
    }

    @objid ("aaeb206f-3049-471b-9526-ae5ce0f40935")
    private static void setHidden(final TagType objElt, final Property ecoreElement) {
        objElt.setIsHidden(ObjingEAnnotation.isHidden(ecoreElement));
    }

    @objid ("e34ea765-dbd5-4a0f-9142-be4a4599fc7b")
    private static void setPartSignature(final TagType objElt, final Property ecoreElement) {
        objElt.setBelongToPrototype(ObjingEAnnotation.isPartSignature(ecoreElement));
    }

    @objid ("c5c6d9cc-b292-4cb0-bc79-2bd7903908f7")
    private static void setLabel(final TagType objElt, final Property ecoreElement) {
        String name = objElt.getName();
        if (name != null)
            objElt.setLabelKey(name);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            String label = ObjingEAnnotation.getLabel(ecoreElement);
            if (label != null)
                objElt.setLabelKey(label);
        }
    }

    @objid ("66f60446-fe75-4c56-9d1a-cf8ddbd1cc44")
    private static boolean needCreation(final MetaclassReference ref) {
        MStatus status = ref.getStatus();
        return ((!status.isRamc()) || (status.isModifiable()));
    }

    @objid ("8306412d-5331-4274-b238-be7104058c0b")
    private static void visitReference(final MetaclassReference reference, final Property ecoreElement) {
        if (needCreation(reference))
            createUnderReference(reference, ecoreElement);
        else{
        
            List<String> objingIds = ObjingEAnnotation.getObjingIDs(ecoreElement);
        
            if (objingIds.size() > 0){
                String objingId = objingIds.get(0);
                if (ObjingEAnnotation.isNoteType(ecoreElement)){
                    for(NoteType tagtype : reference.getDefinedNoteType()){
                        if (tagtype.getUuid().toString().equals(objingId)){
                            PartialImportMap.getInstance().remove(ecoreElement);
                            TotalImportMap.getInstance().put(ecoreElement, tagtype);
                            break;
                        }
                    }
                }else{
                    for(TagType tagtype : reference.getDefinedTagType()){
                        if (tagtype.getUuid().toString().equals(objingId)){
                            PartialImportMap.getInstance().remove(ecoreElement);
                            TotalImportMap.getInstance().put(ecoreElement, tagtype);
                            break;
                        }
                    }
                }
            }
        
        }
    }

    @objid ("354ef187-019c-4fec-9b99-d0f080576799")
    public static void addExtension(final ModelElement objModelElement, final org.eclipse.uml2.uml.Stereotype stereotype) {
        if (!ObjingEAnnotation.isReference(stereotype)){
        
            Object imported = visitStereotype(stereotype);
        
            List<Stereotype> importedStereotypes = new ArrayList<>();
        
            if (imported instanceof Stereotype){
                importedStereotypes.add((Stereotype)imported);
            }else if (imported instanceof List){
                importedStereotypes = (List<Stereotype>) imported;
            }
        
            boolean notApply = true;
            for (Stereotype existingStereotype : importedStereotypes){
                if (objModelElement.getMClass().hasBase(existingStereotype.getMClass())){
                    objModelElement.getExtension().add(existingStereotype);
                    notApply = false;
                    break;
                }
            }
            if ((notApply) && !(ObjingEAnnotation.isReference(stereotype))) {
                String name = stereotype.getName();
        
                Stereotype objStereotype = null;
                try {
                    objStereotype = ReverseProperties.getInstance().getMModelServices().getStereotype(".*", name, objModelElement.getMClass() );
        
                    if ((objStereotype != null) && (!objModelElement.getExtension().contains(objStereotype)))
                        objModelElement.getExtension().add(objStereotype);
                } catch (ElementNotUniqueException e) {
                    Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
                }
        
            }
        }
    }

    @objid ("4d59d022-0695-44dc-9df7-7a07ef5ee453")
    private static void createUnderReference(final MetaclassReference reference, final Property ecoreElement) {
        if ((reference != null)
                && (!reference.getStatus().isRamc())
                && (!exist(reference, ecoreElement))){
        
            if (ObjingEAnnotation.isNoteType(ecoreElement)){
                createNoteType(reference, ecoreElement);
            }else{
                createTagType(reference, ecoreElement);
            }
        
        }
    }

    @objid ("6645495e-60b1-4485-b4b2-2452b81ea1cb")
    public static Profile createObjProfile(final org.eclipse.uml2.uml.Profile ecoreElement) {
        Profile objProfile = (Profile) TotalImportMap.getInstance().get(ecoreElement);
        
        if (objProfile == null){
        
            String moduleId = ObjingEAnnotation.getModule(ecoreElement);
        
            List<String> ids = ObjingEAnnotation.getObjingIDs(ecoreElement);
        
            if  (ids.size() > 0){
                MMetamodel metamodel = Modelio.getInstance().getModelingSession().getModel().getModelRoots().get(0).getMClass().getMetamodel();
                objProfile = (Profile) Modelio.getInstance().getModelingSession().findElementById(metamodel.getMClass(Profile.class), ids.get(0));
                if (objProfile == null){
                    for (MObject module : Modelio.getInstance().getModelingSession().getModel().getLibraryRoots()){
                        if (module.getUuid().toString().equals(moduleId)){
                            for (Profile ownedProfile : ((ModuleComponent) module).getOwnedProfile()){
                                if (ownedProfile.getUuid().toString().equals(ids.get(0))){
                                    objProfile =  ownedProfile;
                                }
                            }
                        }
                    }
                }
            }
        
        
            if (objProfile == null){
                objProfile = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createProfile();
                attach(objProfile, ecoreElement);
                setProperties(objProfile, ecoreElement);
            }
        
        
            PartialImportMap.getInstance().remove(ecoreElement);
            TotalImportMap.getInstance().put(ecoreElement, objProfile);
        }
        return objProfile;
    }

    @objid ("ee2511d3-fe97-43eb-b0de-314a9b668690")
    private static void setProperties(final Profile objProfile, final org.eclipse.uml2.uml.Profile ecoreElement) {
        String name = ecoreElement.getName();
        org.eclipse.uml2.uml.Element owner = ecoreElement.getOwner();
        
        while (owner instanceof org.eclipse.uml2.uml.Package){
            org.eclipse.uml2.uml.Package ownerPck = (org.eclipse.uml2.uml.Package) owner;
            name = ownerPck.getName() +  nameSpacingSeparator + name;
            owner =  ownerPck.getOwner();
        }
        
        if (name != null)
            objProfile.setName(name);
    }

    @objid ("a620562a-806d-4dbc-a116-79b55b6753af")
    private static Object createObjStereotype(final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        List<Stereotype> results = new ArrayList<>();
        List<Stereotype> parents = new ArrayList<>();       
        
        List<String> ids = ObjingEAnnotation.getObjingIDs(ecoreElement);
        IMModelServices mModelServices = ReverseProperties.getInstance().getMModelServices();
        for (String id : ids){
            Stereotype result = (Stereotype) mModelServices.findById(mModelServices.getMetamodel().getMClass(Stereotype.class), id);
            if (result != null){
                results.add(result);
            }
        }
        
        if (results.size() == 0){
            Set<String> baseClasses = ObjingEAnnotation.getBaseClass(ecoreElement);
            if (baseClasses.size() == 0){
                for (org.eclipse.uml2.uml.Element ownedElt : ecoreElement.getOwnedElements()){
                    if (ownedElt instanceof Property ){
                        Property ecoreProperty = (Property) ownedElt;
        
                        if ((ecoreProperty.getAssociation() instanceof Extension)
                                &&  (ecoreProperty.getType() instanceof org.eclipse.uml2.uml.Class) ){
        
                            String typeName = ecoreProperty.getType().getName();
        
                            if (typeName == null)
                                typeName = ((InternalEObject)ecoreProperty.getType()).eProxyURI().fragment();
        
                            for (String objMetaClass : ProfileUtils.getObjingNameClass(typeName))
                                baseClasses.add(objMetaClass);
                        }
                    }
                }
            }
        
            if (baseClasses.size() == 0){
                baseClasses.add("ModelElement");
            }
        
            for (String baseClass : baseClasses){
        
                Stereotype result = (Stereotype) mModelServices.getModelFactory().getFactory(IStandardModelFactory.class).createElement("Stereotype");
                result.setBaseClassName(baseClass);
        
                for (Stereotype parent : parents){
                    if (parent.getBaseClassName().equals(baseClass)){
                        result.setParent(parent);
                        break;
                    }
                }
        
                if ((result.getParent() == null) && (parents.size() != 0)){
                    result.setParent(parents.get(0));
                }
        
                if (result.getStatus().isModifiable()){
                    setProperties(result, ecoreElement);
                    attach(result, ecoreElement);
                }
                results.add(result);
            }
            setName(results, ecoreElement);
        }
        
        PartialImportMap.getInstance().remove(ecoreElement);
        TotalImportMap.getInstance().put(ecoreElement, results);
        return results;
    }

    @objid ("bd6626d0-d427-462d-95c8-afd29ada404c")
    private static void attach(final Stereotype objingStereotype, final org.eclipse.uml2.uml.Stereotype ecoreStereotype) {
        Object owner =  TotalImportMap.getInstance().get(ecoreStereotype.getOwner());
        
        if ((owner != null)
                && (owner instanceof Profile)
                && (((Profile) owner).getStatus().isUserWrite())){
            ((Profile)owner).getDefinedStereotype().add(objingStereotype);       
        }else{
            Profile localProfile = ReverseProperties.getInstance().getLocalProfile();
            localProfile.getDefinedStereotype().add(objingStereotype);          
        }
    }

    @objid ("4d0a71b1-3c6a-4266-b1e2-df1546eb9cd8")
    private static void attach(final Profile objProfile, final org.eclipse.uml2.uml.Profile ecoreElement) {
        boolean attach = false;
        String moduleId = ObjingEAnnotation.getModule(ecoreElement);
        
        if (!(moduleId.equals(""))){
        
            for (MObject module : Modelio.getInstance().getModelingSession().getModel().getLibraryRoots()){
        
                if ((module instanceof ModuleComponent)
                        && (module.getUuid().toString().equals(moduleId))){
        
                    List<String> ids =  ObjingEAnnotation.getObjingIDs(ecoreElement);
        
                    if (ids.size() > 0) {
                        String profileId = ids.get(0);
                        for (Profile profile : ((ModuleComponent) module).getOwnedProfile()){
                            if (profile.getUuid().toString().equals(profileId)){
                                attach = true;
                            }
                        }
        
                    }
                }
            }
        }
        
        if (!attach){
            objProfile.setOwnerModule((ReverseProperties.getInstance().getProfileRoot() ));
        }
    }

    @objid ("c9fdf191-fd9c-41e4-a302-cd544ec15901")
    private static void setProperties(final Stereotype objingElt, final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        setInheritance(objingElt, ecoreElement);
        setIcons(objingElt, ecoreElement);
        setLabel(objingElt, ecoreElement);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled())
            setHidden(objingElt, ecoreElement);
    }

    @objid ("3703a5dc-4491-4fbe-bef3-2b64204d8835")
    private static void setName(final List<Stereotype> objElts, final org.eclipse.uml2.uml.Stereotype stereotype) {
        String sterName = stereotype.getName();
        if (sterName != null){
        
            if (objElts.size() > 1){
                //List of stereotype
                for (Stereotype objElt : objElts){
                    objElt.setName(sterName + "_" + objElt.getBaseClassName());
                    objElt.setLabelKey(sterName);
                }
        
            }else{
                //Unique stereotype
                objElts.get(0).setName(sterName);
                objElts.get(0).setLabelKey(sterName);
        
            }
        }
    }

    @objid ("39a72af6-8b3b-4a34-aa9f-2d652031a214")
    private static void setHidden(final Stereotype objElt, final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        if (ReverseProperties.getInstance().isRoundtripEnabled())
            objElt.setIsHidden(ObjingEAnnotation.isHidden(ecoreElement));
    }

    @objid ("09e9b2f2-8353-4d23-a53f-b76a24a410fc")
    private static void setLabel(final Stereotype objElt, final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            String label = ObjingEAnnotation.getLabel(ecoreElement);
            if ((label != null) && (label.equals("")))
                objElt.setLabelKey(label);
        }
    }

    @objid ("9c2368bd-275b-4b7d-a6d1-ba0c1e6a96a7")
    private static void setIcons(final Stereotype objElt, final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        EList<Image> icons = ecoreElement.getIcons();
        if (icons.size() > 0 ){
            if (ReverseProperties.getInstance().isRoundtripEnabled()){
                for(Object temp : icons){
                    org.eclipse.uml2.uml.Image icon = (org.eclipse.uml2.uml.Image) temp;
                    String iconType = ObjingEAnnotation.getIconType(icon);
                    if (iconType != null){
                        setIcon(objElt, icon, iconType);
                    }
                }
            }else{
                setIcon(objElt, icons.get(0), "icon");
                setIcon(objElt, icons.get(0), "explorerIcon");
                setIcon(objElt, icons.get(0), "smallIcon");
            }
        
        }
    }

    @objid ("27b0d119-a3d2-4daa-b3d1-f02959770f4e")
    public static void write(final org.eclipse.swt.graphics.Image img, final String fileName, final String format) {
        BufferedImage buffi = convertToAWT(img.getImageData());
        File outputFile = new File(fileName);
        try{
            outputFile.createNewFile();
            ImageIO.write(buffi, format, outputFile);
        }catch(IOException e){
            Xmi.LOG.warning(Xmi.PLUGIN_ID,"File not Found");
        }
    }

    /**
     * a ver.... este m?todo fue extra?do de uno de los snippets de
     * swt para la transformaci?n de una im?gen swt en una
     * bufferedImage de awt. Sin embargo, este c?digo fue mejorado por sgurin
     * para el manejo de las transparencias. Su misi?n es que el uso:
     * 
     * Image img = new Image(null, "pepe");
     * BufferedImage bimg = convertToAWT(img.getImageData())
     * 
     * funcione correctamente para "pepe" con formato png, jpg, gif
     * 
     * @param data la ImageData (image swt) a convertir
     * @return la conversi?n de data a una BufferedImage swt
     */
    @objid ("8a8c63e5-d126-424d-a731-86245d7daf5b")
    static BufferedImage convertToAWT(final ImageData data) {
        ColorModel colorModel = null;
        PaletteData palette = data.palette;
        
        if (palette.isDirect) {
        
            // no tenemos canal alfa
            if(data.alphaData==null) {
                colorModel = new DirectColorModel(24, palette.redMask,
                        palette.greenMask, palette.blueMask);
                BufferedImage bufferedImage = new BufferedImage(colorModel,
                        colorModel.createCompatibleWritableRaster(data.width, data.height),
                        false, null);
                WritableRaster raster = bufferedImage.getRaster();
                int[] pixelArray = new int[3];
                for (int y = 0; y < data.height; y++) {
                    for (int x = 0; x < data.width; x++) {
                        int pixel = data.getPixel(x, y);
                        RGB rgb = palette.getRGB(pixel);
                        pixelArray[0] = rgb.red;
                        pixelArray[1] = rgb.green;
                        pixelArray[2] = rgb.blue;
                        raster.setPixels(x, y, 1, 1, pixelArray);
                    }
                }
        
                return bufferedImage;
            }
        
            //tenemos canal alfa
            else {
                colorModel = new DirectColorModel(32, palette.redMask,
                        palette.greenMask, palette.blueMask, 0xff000000);
                BufferedImage bufferedImage = new BufferedImage(colorModel,
                        colorModel.createCompatibleWritableRaster(data.width, data.height),
                        false, null);
                WritableRaster raster = bufferedImage.getRaster();
                int[] pixelArray = new int[4];
                for (int y = 0; y < data.height; y++) {
                    for (int x = 0; x < data.width; x++) {
                        int pixel = data.getPixel(x, y);
                        RGB rgb = palette.getRGB(pixel);
                        pixelArray[0] = rgb.red;
                        pixelArray[1] = rgb.green;
                        pixelArray[2] = rgb.blue;
                        pixelArray[3]=data.getAlpha(x,y);
                        raster.setPixels(x, y, 1, 1, pixelArray);
                    }
                }
                return bufferedImage;
            }
        
            //la paleta swt no es directa ??????
        } else { //no sabemos qu? pasa ac?
            RGB[] rgbs = palette.getRGBs();
            byte[] red = new byte[rgbs.length];
            byte[] green = new byte[rgbs.length];
            byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                RGB rgb = rgbs[i];
                red[i] = (byte)rgb.red;
                green[i] = (byte)rgb.green;
                blue[i] = (byte)rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                        green, blue, data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                        green, blue);
            }
            BufferedImage bufferedImage = new BufferedImage(colorModel,
                    colorModel.createCompatibleWritableRaster(data.width, data.height),
                    false, null);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
            return bufferedImage;
        }
    }

    @objid ("f6fe65b9-d331-4c7c-a5d1-32a058f3f4b6")
    private static void setInheritance(final Stereotype objElt, final org.eclipse.uml2.uml.Stereotype ecoreElement) {
        for (org.eclipse.uml2.uml.Stereotype  ecoreParent : getParent(ecoreElement)){
            Object parents =  visitStereotype(ecoreParent);
            objElt.setParent(((ArrayList<Stereotype>)parents).get(0));
        }
    }

    @objid ("5338fbda-f250-46b5-b1ff-3f92ce2345d3")
    private static List<org.eclipse.uml2.uml.Stereotype> getParent(final org.eclipse.uml2.uml.Stereotype stereotype) {
        List<org.eclipse.uml2.uml.Stereotype> result = new ArrayList<>();
        for (Object obj : stereotype.getGeneralizations()){
            if (obj instanceof org.eclipse.uml2.uml.Generalization){
                org.eclipse.uml2.uml.Classifier sterParent = ((org.eclipse.uml2.uml.Generalization) obj).getGeneral();
                if (sterParent instanceof org.eclipse.uml2.uml.Stereotype){
                    result.add((org.eclipse.uml2.uml.Stereotype)sterParent);
                }
            }
        }
        return result;
    }

    @objid ("feeb196e-9357-42c6-96a8-75026869957a")
    private static void setIconsProperties(final org.eclipse.uml2.uml.Stereotype stereotype, final Stereotype obStereotype) {
        String moduleName = obStereotype.getOwner().getOwnerModule().getName();
        
        for (IPeerModule pModules : Modelio.getInstance().getModuleService().getAllPeerModules()){
            if (pModules.getName().equals(moduleName)){
                Path moduleResPath = pModules.getConfiguration().getModuleResourcesPath();
        
                String iconPath = obStereotype.getIcon();
                iconPath = iconPath.replaceFirst(moduleName, "");
        
                String imagePath = obStereotype.getImage();
                imagePath = imagePath.replaceFirst(moduleName, "");
        
                if ((imagePath != null) && (!imagePath.equals("") )){
                    Image icon = setStereotypeImage(moduleResPath, imagePath, stereotype);
                    ObjingEAnnotation.setIconType(icon, "image");
                }
        
                if ((iconPath != null) && (!iconPath.equals("") ))  {
                    Image icon = setStereotypeImage(moduleResPath, iconPath, stereotype);
                    ObjingEAnnotation.setIconType(icon, "explorerIcon");
                }
            }
        }
    }

    @objid ("3a0b805e-ddb9-42c8-ac90-dfa6f9fb329f")
    private static void setProperties(final NoteType attri, final Property ecoreElement) {
        setLabel(attri, ecoreElement);
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setHidden(attri,ecoreElement);
        }
    }

    @objid ("9a21deb5-d5bf-4d4b-97a6-a3e0f46b8657")
    private static void setHidden(final NoteType objElt, final Property ecoreElement) {
        objElt.setIsHidden(ObjingEAnnotation.isHidden(ecoreElement));
    }

    @objid ("02fa0c36-7cd7-4a8c-b5ce-9adf012255a3")
    private static void setLabel(final NoteType objElt, final Property ecoreElement) {
        String name = objElt.getName();
        if (name != null)
            objElt.setLabelKey(name);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            String label = ObjingEAnnotation.getLabel(ecoreElement);
            if (label != null)
                objElt.setLabelKey(label);
        }
    }

    @objid ("6a80f9d9-8fd2-4ee4-b563-994c42dabdc3")
    private static NoteType createNoteType(final Stereotype stereotype, final Property ecoreElement) {
        NoteType noteType  = (NoteType)  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createElement("NoteType");
        stereotype.getDefinedNoteType().add(noteType);
        
        noteType.setName(ecoreElement.getName());
        
        setProperties(noteType, ecoreElement);
        return noteType;
    }

    @objid ("afd2d7b9-91df-4d70-95c1-8da4529614b5")
    private static TagType createTagType(final Stereotype stereotype, final Property ecoreElement) {
        String name = ecoreElement.getName();
        org.eclipse.uml2.uml.Type type = ecoreElement.getType();
        TagType attr = (TagType) ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createElement("TagType");
        
        stereotype.getDefinedTagType().add(attr);
        attr.setName(name);
        
        String max = EcoreModelNavigation.getMultiplicityMax(ecoreElement);
        
        if (EcorePrimitiveTypeMapper.isBoolean(type) && (max.equals("1"))){
            attr.setParamNumber("0");
        }else{
            attr.setParamNumber(max);
        }
        
        setProperties(attr, ecoreElement);
        return attr;
    }

    @objid ("f0e83182-f6d9-4f64-b552-fd42a74c186d")
    private static void createNoteType(final MetaclassReference reference, final Property ecoreElement) {
        List<String> ids =  ObjingEAnnotation.getObjingIDs(ecoreElement);
        NoteType noteType = null;
        
        if (ids.size()>0) {
            String id = ids.get(0);
            noteType = Modelio.getInstance().getModelingSession().findElementById(NoteType.class, id);
        }
        
        if (noteType == null){
            noteType = (NoteType) ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createElement("NoteType");
            reference.getDefinedNoteType().add(noteType);
        
            noteType.setName(ecoreElement.getName());
        
            ecoreElement.getType();
        
            setProperties(noteType, ecoreElement);
        }
    }

    @objid ("2f41b581-78f3-42fe-8dd2-5fb3c8dc4d23")
    private static TagType createTagType(final MetaclassReference reference, final Property ecoreElement) {
        List<String> ids = ObjingEAnnotation.getObjingIDs(ecoreElement);
        TagType tagType = null;
        
        if (ids.size() > 0){
            tagType = Modelio.getInstance().getModelingSession().findElementById(TagType.class, ObjingEAnnotation.getObjingIDs(ecoreElement).get(0));
        
            org.eclipse.uml2.uml.Type type = ecoreElement.getType();
            String name = ecoreElement.getName();
        
            if ((name != null ) && (!name.equals(""))
                    && (!name.startsWith("base_")) && (type != null)
                    && (tagType == null)){
                tagType = (TagType) ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createElement("TagType");
                reference.getDefinedTagType().add(tagType);
        
                tagType.setName(name);
        
        
                if ((((type.getName() != null) && (type.getName().equals("Boolean")))
                        || ((type instanceof PrimitiveTypeImpl) && (((PrimitiveTypeImpl) type).eProxyURI() != null)
                                && ((PrimitiveTypeImpl) type).eProxyURI().fragment().equals("Boolean")))) {
                    tagType.setParamNumber("0");
                }
        
        
                String max = String.valueOf(ecoreElement.getUpper());
                if (max != null){
                    if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE.equals(max) || (max.equals("-1")))
                        tagType.setParamNumber("*");
                    else{
                        String min = String.valueOf(ecoreElement.getLower());
                        tagType.setParamNumber(String.valueOf(Integer.valueOf(max) - Integer.valueOf(min)));
                    }
        
                }
        
                setProperties(tagType, ecoreElement);
            }
        }
        return tagType;
    }

    @objid ("82c06887-eedf-437e-867e-add98640dc24")
    private static boolean exist(final Stereotype stereotype, final Property ecoreElement) {
        String name = ecoreElement.getName();
        if (name != null){
        
            if (ObjingEAnnotation.isNoteType(ecoreElement)){
                for (NoteType noteType : stereotype.getDefinedNoteType()){
                    if (noteType.getName().equals(name)){
                        return true;
                    }
                }
            }else{
                for (TagType tagtype : stereotype.getDefinedTagType()){
                    if (tagtype.getName().equals(name)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @objid ("27dedc4f-16e4-4c5f-8bf0-1cb2b1a769d5")
    private static boolean exist(final MetaclassReference reference, final Property ecoreElement) {
        String name = ecoreElement.getName();
        if (name != null){
        
            if (ObjingEAnnotation.isNoteType(ecoreElement)){
                for (NoteType noteType : reference.getDefinedNoteType()){
                    if (noteType.getName().equals(name)){
                        return true;
                    }
                }
            }else{
                for (TagType tagtype : reference.getDefinedTagType()){
                    if (tagtype.getName().equals(name)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @objid ("192555af-1400-4289-b486-9ac115286ca6")
    private static boolean needCreation(final List<Stereotype> stereotypes) {
        for (Stereotype ster : stereotypes){
            MStatus status = ster.getStatus();
        
            if ((!status.isRamc()) || (status.isModifiable())){
                return true;
            }
        }
        return false;
    }

    @objid ("50a58c7b-68d8-4eb5-8824-a20267e2f1aa")
    private static void visitStereotype(final List<Stereotype> stereotypes, final Property ecoreElement) {
        if (needCreation(stereotypes))
            createUnderStereotypes(stereotypes, ecoreElement);
        else{
            List<String> objingIds = ObjingEAnnotation.getObjingIDs(ecoreElement);
            if (objingIds.size() >0){
                String objingId = objingIds.get(0);
                for (Stereotype ster : stereotypes){
        
                    if (ObjingEAnnotation.isNoteType(ecoreElement)){
                        for(NoteType tagtype : ster.getDefinedNoteType()){
                            if (tagtype.getUuid().toString().equals(objingId)){
                                PartialImportMap.getInstance().remove(ecoreElement);
                                TotalImportMap.getInstance().put(ecoreElement, tagtype);
                                break;
                            }
                        }
                    }else{
                        for(TagType tagtype : ster.getDefinedTagType()){
                            if (tagtype.getUuid().toString().equals(objingId)){
                                PartialImportMap.getInstance().remove(ecoreElement);
                                TotalImportMap.getInstance().put(ecoreElement, tagtype);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @objid ("3e9e6cb6-79b2-49d3-8eff-b7574fb2676a")
    private static void createUnderStereotypes(final List<Stereotype> stereotypes, final Property ecoreElement) {
        if ((stereotypes != null) ){
        
            List<ModelElement> results = new ArrayList<>();
        
            for (Stereotype stereotype : stereotypes){
        
                if ((stereotype.getStatus().isUserWrite())
                        &&  (!exist(stereotype, ecoreElement))){
        
                    String name = ecoreElement.getName();
        
                    if (ObjingEAnnotation.isNoteType(ecoreElement)){
                        results.add(createNoteType(stereotype, ecoreElement));
        
                    }else if ((name != null ) && (!name.equals(""))
                            && (!name.startsWith("base_"))
                            && (ecoreElement.getType() != null) ){
                        results.add(createTagType(stereotype, ecoreElement));
                    }
                }
            }
        
            PartialImportMap.getInstance().remove(ecoreElement);
            TotalImportMap.getInstance().put(ecoreElement, results);
        }
    }

    @objid ("c37ac558-354e-42e7-9868-77fe1a3423d8")
    private static void setIcon(final Stereotype objElt, final org.eclipse.uml2.uml.Image icon, final String tag) {
        String format = icon.getFormat();
        String location = icon.getLocation();
        
        if((format != null)
                && !format.equals("")
                && (location != null)
                && !location.equals("")){
        
            //creation du fichier image
        
            File file = new File(location);
        
            if (!file.exists()){
        
                file.mkdirs();
        
                try{
                    org.eclipse.swt.graphics.Image image = getContent(icon);
                    if (image != null) {
                        write(image, location, format);
                    }
        
                }catch(SWTException e){
        
                    if (e.getMessage().equals("Unsupported or unrecognized format")){
                        String message = Xmi.I18N.getMessage("logFile.warning.import.unsupportedFormat", location, format);
                        ReverseProperties.getInstance().addWarning(message);
                    }else{
                        String errorMsg = e.getMessage();
                        Xmi.LOG.warning(Xmi.PLUGIN_ID, errorMsg);
                    }
        
                }
            }
        
            //ajout de la taggedvalue
        
            if (tag.equals("icon")){
                objElt.setIcon(location);
            }else if (tag.equals("explorerIcon")){
                objElt.setIcon(location);
            }else {
                objElt.setImage(location);
            }
        
        }
    }

    @objid ("dcc4c7b6-0b5a-4b54-b8d3-c7658a9cd388")
    public static boolean isNamedWithConvention(final Stereotype stereotype) {
        if ((stereotype.getLabelKey() != null) &&  (stereotype.getBaseClassName() != null))
            return stereotype.getName().equals(stereotype.getLabelKey() + "_" + stereotype.getBaseClassName());
        else
            return false;
    }

    @objid ("ba9e43dc-f15c-4eef-9a5a-b0a475d5175e")
    public static String getStereotypeName(final Stereotype stereotype) {
        if (isNamedWithConvention(stereotype))
            return stereotype.getLabelKey();
        else
            return stereotype.getName();
    }

    @objid ("060ab4e2-328c-4173-907a-9bab9397b106")
    public static String getStereotypeIdSeparator() {
        return stereotypeIdSeparator;
    }

    @objid ("9ed51c78-37f8-40f1-8b7b-5d8f81c2c558")
    public static boolean isNamedWithConvention(final TagType tagtype) {
        if ((tagtype.getLabelKey() != null) && (tagtype.getOwnerStereotype() != null) && (tagtype.getOwnerStereotype().getBaseClassName() != null))
            return tagtype.getName().equals(tagtype.getOwnerStereotype().getLabelKey() + "_" + tagtype.getOwnerStereotype().getBaseClassName()+ "_"+ tagtype.getLabelKey() );
        else
            return false;
    }

    @objid ("d591eea4-5f3a-459b-ba88-277f46f30afb")
    public static String getTagTypeName(final TagType tagtype) {
        if (isNamedWithConvention(tagtype))
            return tagtype.getLabelKey();
        else
            return tagtype.getName();
    }

    @objid ("d41b7b07-7938-436c-a601-9310889f546b")
    public static void addReference(final org.eclipse.uml2.uml.Stereotype stereotype, final String metaclassName) {
        Class metaclassClass =  (org.eclipse.uml2.uml.Class) UMLMetamodel.getInstance().getUMLMetamodel().getOwnedType(metaclassName);
        
        org.eclipse.uml2.uml.Profile profile = stereotype.getProfile();
        
        if (metaclassClass != null){
        
            boolean found = false;
            Class reference = metaclassClass;
        
            for (Object metaclass : profile.getMetaclassReferences()){
                Class importedClas =  (org.eclipse.uml2.uml.Class)((org.eclipse.uml2.uml.ElementImport)metaclass).getImportedElement();
                if (importedClas != null){
                    String clasname = importedClas.getName();
                    String metaClassName = metaclassClass.getName();
                    if (metaClassName != null){
                        if (clasname.equals(metaClassName)){
                            reference =  (org.eclipse.uml2.uml.Class)((org.eclipse.uml2.uml.ElementImport)metaclass).getImportedElement();
                            found = true;
                        }
                    }
                }
            }
        
            if (!found){
                profile.createMetaclassReference(reference);
                stereotype.createExtension(reference, false);
            }else{
        
                boolean typed = false ;
                for (Object extension : reference.getExtensions())
                    for (Object extensionEnd : ((org.eclipse.uml2.uml.Extension) extension).getOwnedEnds())
                        if (((Property) extensionEnd).getType().equals(stereotype))
                            typed = true;
        
                if (!typed)
                    stereotype.createExtension(reference, false);
            }
        
        }else{
            String errorMsg = "Not found correspondance for : " + metaclassName;
            Xmi.LOG.warning(Xmi.PLUGIN_ID, errorMsg);
        }
    }

    @objid ("1597f0aa-9fe8-43b7-9392-6d09303c70b4")
    public static org.eclipse.uml2.uml.Stereotype createStereotype(final MetaclassReference reference, final org.eclipse.uml2.uml.Profile profile) {
        org.eclipse.uml2.uml.Stereotype result = profile.createOwnedStereotype(reference.getReferencedClassName(), false);
        ObjingEAnnotation.addObjingID(result, reference.getUuid().toString());
        ObjingEAnnotation.setIsReference(result);
        return result;
    }

    @objid ("01b797e4-b286-4069-b384-191b6f40dfec")
    public static boolean isInScope(final org.modelio.metamodel.uml.infrastructure.Stereotype stereotype) {
        return scopeChecker.contains(stereotype);
    }

    @objid ("8330d168-1da0-43ed-9f70-192b400488ab")
    public static String getName(final Profile profile) {
        String name = profile.getName();
        if (name.contains(nameSpacingSeparator)){
            String[] names = name.split(nameSpacingSeparator);
            return names[names.length - 1];
        }
        return name;
    }

    @objid ("9fa92530-f4c0-4587-b03c-529d8c321584")
    public static Profile getProfileOwner(final Profile profile) {
        String name = profile.getName();
        Profile owner = null;
        
        if (name.contains(nameSpacingSeparator)){
            String[] names = name.split(nameSpacingSeparator);
            StringBuffer ownerName = new StringBuffer();
            ownerName.append(names[0]);
        
            int size = names.length;
            for (int  i = 1 ;  i < size - 1; i++ ){
                ownerName.append(nameSpacingSeparator);
                ownerName.append(names[i]);
            }
        
            for (Profile otherProfile: profile.getOwnerModule().getOwnedProfile()){
                if (otherProfile.getName().equals(ownerName.toString())){
                    return otherProfile;
                }
            }
        }
        return owner;
    }

    @objid ("9106d6d5-bbf2-4e7b-b8a5-a30f2d2437ee")
    public static List<PExportProfile> getSubProfiles(final PExportProfile profile) {
        List<PExportProfile> result = new ArrayList<>();
        Profile objProfil = profile.getObjElt();
        for (Profile subprofile : objProfil.getOwnerModule().getOwnedProfile() ){
            String profileName = objProfil.getName();
            String subProfileName = subprofile.getName();
            if (subProfileName.contains(profileName)
                    && subProfileName.replaceAll(profileName, "").contains(nameSpacingSeparator)){
                result.add(new PExportProfile(subprofile));
            }
        }
        return result;
    }

    @objid ("fe21dc5f-1fcd-45e5-a341-5ed1228e8bfc")
    public static Profile getProfileRoot(final Profile profile) {
        String name = profile.getName();
        Profile owner = profile;
        
        if (name.contains(nameSpacingSeparator)){
            String[] names = name.split(nameSpacingSeparator);
            String ownerName = names[0];
        
            for (Profile otherProfile: profile.getOwnerModule().getOwnedProfile()){
                if (otherProfile.getName().equals(ownerName)){
                    return otherProfile;
                }
            }
        }
        return owner;
    }

    @objid ("e778b2cd-a192-4429-a3f9-31e21e5cbdb6")
    private static MetaclassReference getReference(final org.eclipse.uml2.uml.Stereotype ecoreElt) {
        List<String> ids = ObjingEAnnotation.getObjingIDs(ecoreElt);
        
        if (ids.size() > 0){
            String id = ids.get(0);
            Object profile = ReverseProperties.getInstance().getMappedElement(ecoreElt.getProfile());
            if ((profile != null ) && (profile instanceof Profile)){
                for (MetaclassReference reference : ((Profile) profile).getOwnedReference()){
                    if (reference.getUuid().toString().equals(id)){
                        return reference;
                    }
                }
            }
        }
        return createReference(ecoreElt);
    }

    @objid ("a801d85e-b57b-433e-bd00-f4dde99e912e")
    public static org.eclipse.uml2.uml.Profile createEcoreProfile(final Profile objingElt) {
        org.eclipse.uml2.uml.Profile ecoreProfile = UMLFactory.eINSTANCE.createProfile();
        
        ObjingEAnnotation.setModule(ecoreProfile, objingElt.getOwnerModule().getUuid().toString());
        ObjingEAnnotation.addObjingID(ecoreProfile, objingElt.getUuid().toString());
        ecoreProfile.setName(ProfileUtils.getName(objingElt));
        
        
        if (SysMLProfileUtils.isSysML(objingElt)){
            GenerationProperties.getInstance().setSysMLApplied();
            ecoreProfile.setName("SysML");
            SysMLProfileUtils.completeSysMLprofile(ecoreProfile, objingElt.getMClass().getMetamodel());
            TotalExportMap.getInstance().put(objingElt.getUuid().toString(), ecoreProfile);
        }else
            GenerationProperties.getInstance().addExportedProfile(ecoreProfile);
        return ecoreProfile;
    }

    /**
     * @return
     */
    @objid ("5d8d8cb2-e330-444f-a381-985fae704317")
    public static boolean isAnalystDeployed() {
        return false;
    }

    @objid ("895e8e8d-8a4f-4f3d-bd6b-9c1b41488db1")
    public static void setValue(ModelElement element, org.eclipse.uml2.uml.Element ecoreElement) {
        List<TaggedValue> listTag = sortByType(element);
        
        TagType tagType = null;
        
        for (TaggedValue taggedValue : listTag){
        
            if (!(taggedValue.getDefinition().equals(tagType))){
                tagType = taggedValue.getDefinition();
            }
        
            // find stereotype
            org.eclipse.uml2.uml.Stereotype stereotype = null;
            org.modelio.metamodel.uml.infrastructure.Stereotype obStereotype = taggedValue.getDefinition().getOwnerStereotype();
        
            if (obStereotype != null){
                Stereotype appliedSterotype = null;
                for (Stereotype extension : taggedValue.getAnnoted().getExtension()){
                    Stereotype temp = extension;
                    while (temp != null ){
                        if (temp.equals(obStereotype)){
                            appliedSterotype = extension;
                            stereotype = (org.eclipse.uml2.uml.Stereotype) TotalExportMap.getInstance().get(appliedSterotype.getUuid().toString());
                        }
                        temp = temp.getParent();
                    }
                }
            }else{
                MetaclassReference obReference = taggedValue.getDefinition().getOwnerReference();
                if ((obReference != null) && (obReference.getUuid() != null) && !(obReference.getUuid().equals(""))){
                    stereotype = (org.eclipse.uml2.uml.Stereotype) TotalExportMap.getInstance().get(obReference.getUuid().toString());
                }
            }
        
            if (stereotype != null){
        
                String tagTypeName = ProfileUtils.getTagTypeName(taggedValue.getDefinition());
        
                //Cas boolean
                if (taggedValue.getDefinition().getParamNumber().equals("0")){
        
                    try{
                        Object value = ecoreElement.getValue(stereotype, tagTypeName);
        
                        if ((value != null) && value.equals(false)){
                            ecoreElement.setValue(stereotype, tagTypeName, true);
                        }
        
                    }catch(IllegalArgumentException e){
                        String message = Xmi.I18N.getMessage("logFile.warning.unexportedTaggedValue",
                                taggedValue.getDefinition().getName(),
                                element.getName());
                        GenerationProperties.getInstance().addWarning(message, element);
        
                    }
        
                }else {
                    //Cas String
        
                    if ((tagType != null) && (tagType.getParamNumber().equals("1"))){
        
                        if (taggedValue.getActual().size() > 0){
                            String value = taggedValue.getActual().get(0).getValue();
                            try{
        
                                ecoreElement.setValue(stereotype, tagTypeName, value);
        
                            }catch(IllegalArgumentException e){
                                String message = Xmi.I18N.getMessage("logFile.warning.unexportedTaggedValue",
                                        taggedValue.getDefinition().getName(),
                                        element.getName());
                                GenerationProperties.getInstance().addWarning(message, element);
                            }
                        }
                    }else{
                        //Cas tableau de string
                        try{
                            EDataTypeUniqueEList<String> valueList = (EDataTypeUniqueEList<String>) ecoreElement.getValue(stereotype, tagTypeName);
                            for (TagParameter tagParameter : taggedValue.getActual() ){
                                if (tagParameter.getValue() != null)
                                    valueList.add(tagParameter.getValue());
        
                            }
                        }catch(IllegalArgumentException e){
                            String message = Xmi.I18N.getMessage("logFile.warning.unexportedTaggedValue",
                                    taggedValue.getDefinition().getName(),
                                    element.getName());
                            GenerationProperties.getInstance().addWarning(message, element);
                        }
                    }
                }
            }
        }
    }

    @objid ("6f95fe74-555a-453d-8c9d-6f49ad037c43")
    private static List<TaggedValue> sortByType(ModelElement element) {
        List<TaggedValue> listTag = element.getTag();
        List<TaggedValue> listTagSorted = new ArrayList<>();
        
        for (TaggedValue current : listTag){
            if (!(listTagSorted.contains(current))){
                listTagSorted.add(current);
                for (TaggedValue other : listTag){
                    if ((!(listTagSorted.contains(other)))
                            && (current.getDefinition().equals(other.getDefinition()) )){
                        listTagSorted.add(other);
                    }
                }
            }
        }
        return listTagSorted;
    }

}
