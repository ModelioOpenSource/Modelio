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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the import of Ecoe org.eclipse.uml2.uml.PackageImports
 * @author ebrosse
 */
@objid ("6781e7a1-1250-4292-bb72-1440f357b39c")
public class EPackageImport extends EElement {
    @objid ("ff6b0545-c0da-4ad3-83c2-4892d010f511")
    private org.eclipse.uml2.uml.PackageImport ecoreElement = null;

    @objid ("21db33f5-4f9e-470b-997f-720fb21c2725")
    @Override
    public Element createObjingElt() {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Package ecoreImported = this.ecoreElement.getImportedPackage();
        
        org.eclipse.uml2.uml.Package model = EcoreModelNavigation.getRoot(ecoreImported);
        
        if  ((model != null)  && revProp.getEcoreModels().contains(model) ){
            return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPackageImport();
        }
        return null;
    }

    /**
     * The EPackageImport constructor.
     * It takes the imported Ecore org.eclipse.uml2.uml.PackageImport as parameter
     * 
     * @param element : the imported Ecore org.eclipse.uml2.uml.PackageImport
     */
    @objid ("c22ff0f3-3484-4ab8-96fd-886c8fa8e90c")
    public EPackageImport(final org.eclipse.uml2.uml.PackageImport element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("bb4cfefa-0d9f-44fc-9c3a-6fa97183042f")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Package ecoreImported = this.ecoreElement.getImportedPackage();
        org.eclipse.uml2.uml.Namespace ecoreImporting = this.ecoreElement.getImportingNamespace();
        
        Package objingImported = (Package) revProp
                .getMappedElement(ecoreImported);
        ModelElement objingImporting = (ModelElement) revProp
                .getMappedElement(ecoreImporting);
        
        org.eclipse.uml2.uml.Package model = EcoreModelNavigation.getRoot(ecoreImported);
        
        if  ((model != null)  && revProp.getEcoreModels().contains(model) ){
        
            if (objingImported != null && objingImporting != null) {
                PackageImport objingPkgImport = (PackageImport) objingElt;
        
                objingPkgImport.setImportedPackage(objingImported);
                if (objingImporting instanceof Operation)
                    objingPkgImport
                    .setImportingOperation((Operation) objingImporting);
                else
                    objingPkgImport
                    .setImportingNameSpace((NameSpace) objingImporting);
            }
        
            //  set to the objingElt Imported Importing previousely find
            if (objingImported != null){
                PackageImport objingPkgImport = (PackageImport) objingElt;
                objingPkgImport.setImportedPackage(objingImported);
                if (objingImporting != null) {
                    if (objingImporting instanceof NameSpace) {
                        objingPkgImport
                        .setImportingNameSpace((NameSpace) objingImporting);
                    } else {
                        objingPkgImport
                        .setImportingOperation((Operation) objingImporting);
                    }
        
                }
            }else{
                String ownerName = "";
                if (ecoreImported != null)
                    ownerName = ecoreImported.getName();
        
                
                String message = Xmi.I18N.getMessage("logFile.warning.unimportedElement.importPackage.ownerNull"
                        , ownerName);
                ReverseProperties.getInstance().addWarning(message, objingElt);
                objingElt.delete();
            }
        }else{
            objingElt.delete();
            objingImported.delete();
        }
    }

    @objid ("5551fc73-4de9-4822-947d-b067b875d763")
    @Override
    public void setProperties(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        setVisibility((PackageImport) objingElt);
                
        if (revProp.isRoundtripEnabled())
            setNameFromEAnnotation((PackageImport) objingElt);
         
                super.setProperties(objingElt);
    }

    @objid ("7ebbf165-5459-4d05-87aa-da59ab335955")
    private void setNameFromEAnnotation(PackageImport objingElt) {
        if (ObjingEAnnotation.getName(this.ecoreElement) != null) {
            objingElt.setName(ObjingEAnnotation.getName(this.ecoreElement));
        }
    }

    @objid ("164233b2-1a44-4999-b6a0-f570d56b568b")
    private void setVisibility(PackageImport objingElt) {
        switch (this.ecoreElement.getVisibility().getValue()) {
        case org.eclipse.uml2.uml.VisibilityKind.PUBLIC:
            objingElt.setVisibility(VisibilityMode.PUBLIC);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PRIVATE:
            objingElt.setVisibility(VisibilityMode.PRIVATE);
            break;
        default:
            // Visibility of a org.eclipse.uml2.uml.PackageImport should be Public or Private.
            objingElt.setVisibility(VisibilityMode.PUBLIC);
            String message = Xmi.I18N.getMessage("logFile.warning.wrongVisibility",
                    "", "PackageImport", VisibilityMode.PUBLIC.toString());
            ReverseProperties.getInstance().addError(message);
                    
        }
        
        if (ObjingEAnnotation.isUndefined(this.ecoreElement))
            objingElt
            .setVisibility(VisibilityMode.VISIBILITYUNDEFINED);
    }

}
