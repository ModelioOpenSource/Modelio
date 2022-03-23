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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class handles the import of Ecore org.eclipse.uml2.uml.ElementImport
 * @author ebrosse
 */
@objid ("3d69fa7c-0d9d-483b-87e4-a2de863d468a")
public class EElementImport extends EElement {
    @objid ("c0168344-8f23-446c-aae6-ccb056a037a2")
    private org.eclipse.uml2.uml.ElementImport ecoreElement;

    @objid ("1b0fc872-b10c-4d85-b9b9-07f294e2fc7f")
    @Override
    public Element createObjingElt() {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        //  take the ecore Imported and Importing
        org.eclipse.uml2.uml.PackageableElement ecoreImported = this.ecoreElement.getImportedElement();
        org.eclipse.uml2.uml.Namespace ecoreImporting = this.ecoreElement.getImportingNamespace();
        
        // with the ecore  take de ModelioElement
        
        if ((ecoreImported != null) && (ecoreImporting != null)){
            Object objingImported = revProp.getMappedElement(ecoreImported);
            //  set to the objingElt Imported Importing previously find
            if ((objingImported != null) && (objingImported instanceof Element) && !(objingImported instanceof Profile)){
                return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createElementImport();
            }
        }
        return null;
    }

    /**
     * The constructor with the imported Ecore org.eclipse.uml2.uml.ElementImport
     * @param element : the imported Ecore org.eclipse.uml2.uml.ElementImport
     */
    @objid ("31927e3e-50db-4717-9f22-4dd7b7d7100b")
    public  EElementImport(final org.eclipse.uml2.uml.ElementImport element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("ef16eaa1-69d4-42de-9fce-7ce7f5c1d1da")
    @Override
    public void attach(Element objingElt) {
        //  take the model map
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        //  take the ecore Imported and Importing
        org.eclipse.uml2.uml.PackageableElement ecoreImported = this.ecoreElement.getImportedElement();
        org.eclipse.uml2.uml.Namespace ecoreImporting = this.ecoreElement.getImportingNamespace();
        
        // with the ecore  take de ModelioElement
        Object objingImported = revProp.getMappedElement(ecoreImported);
        Object objingImporting = revProp.getMappedElement(ecoreImporting);
        
        
        if ((objingImporting != null) 
                && ((objingImporting instanceof NameSpace) 
                        || (objingImporting instanceof Operation)))         {
            ElementImport objingEIImport = (ElementImport) objingElt;
            if (objingImporting instanceof NameSpace) {
                objingEIImport
                .setImportingNameSpace((NameSpace) objingImporting);
            } else if (objingImporting instanceof Operation) {
                objingEIImport
                .setImportingOperation((Operation) objingImporting);
            }else{
                objingEIImport.delete();
        
            }
        
            //  set to the objingElt Imported Importing previously find
            if ((objingImported != null)  
                    && (objingImported instanceof NameSpace)
                    && !(objingImported instanceof Profile)){
        
                objingEIImport.setImportedElement((NameSpace)objingImported);
            }
        
        
        }else{
            String ownerName = "";
            if (ecoreImported != null)
                ownerName = ecoreImported.getName();
        
            String message = Xmi.I18N.getMessage("logFile.warning.unimportedElement.elementImport.ownerNull"
                    , this.ecoreElement.getName(), ownerName);
            ReverseProperties.getInstance().addError(message);
            objingElt.delete();
        }
        
    }

    @objid ("03625e40-2cb7-4ded-b7cb-d17218228a23")
    @Override
    public void setProperties(Element objingElt) {
        setVisibility((ElementImport) objingElt);
        setName((ElementImport) objingElt);
        
        super.setProperties(objingElt);
        
    }

    @objid ("ff3b7289-1a9e-47f6-b582-24bf4464eb5e")
    private void setVisibility(final ElementImport objingElt) {
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
        
        }
        
        if (ObjingEAnnotation.isUndefined(this.ecoreElement))
            objingElt
            .setVisibility(VisibilityMode.VISIBILITYUNDEFINED);
        
    }

    @objid ("6f60b9de-84e1-44ce-a61a-3ed9b08b3731")
    private void setName(ElementImport objingElt) {
        String name = this.ecoreElement.getAlias();
        if (!(name == null || "".equals(name)))
            objingElt.setName(name);
        
    }

}
