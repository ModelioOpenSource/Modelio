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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of TemplateBinding
 * @author ebrosse
 */
@objid ("97bab9a7-2476-4bad-a9c4-2bbe6947e14e")
public class OTemplateBinding extends OElement implements IOElement {
    @objid ("2a4e4e8d-598d-4780-8f19-df5753bb2913")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createTemplateBinding();
    }

    /**
     * constructor
     * @param param : the exported Modelio TemplateBinding
     */
    @objid ("367bd5bd-c0a5-4d3a-9bbc-60f9f996f743")
    public OTemplateBinding(final TemplateBinding param) {
        super(param);
    }

    @objid ("4887a790-cb6b-4375-a062-9901723bd02e")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        TemplateBinding templateBinding = (TemplateBinding) getObjingElement();
        Element objOwner =  templateBinding.getBoundElement();
        if (objOwner == null)
            objOwner =  templateBinding.getBoundOperation();
        
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objOwner);
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateableElement){
        
            ((org.eclipse.uml2.uml.TemplateableElement) ecoreOwner).getTemplateBindings().add((org.eclipse.uml2.uml.TemplateBinding) ecoreElt);
        
        }else{
            ecoreElt.destroy();
        }
    }

    @objid ("faa7e741-7999-4747-baa1-75866a30877f")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setTemplate(ecoreElt);
    }

    @objid ("09110ca6-482b-423f-b871-02b2e8a3bc25")
    private void setTemplate(org.eclipse.uml2.uml.Element ecoreElt) {
        TemplateBinding objElt = (TemplateBinding) getObjingElement();
        org.eclipse.uml2.uml.TemplateBinding ecoreElement = (org.eclipse.uml2.uml.TemplateBinding) ecoreElt;
        
        Element  template = objElt.getInstanciatedTemplate();
        if (template == null)
            template = objElt.getInstanciatedTemplateOperation();
        
        if (template != null){
            org.eclipse.uml2.uml.Element ecoreTemplate = GenerationProperties.getInstance().getMappedElement(template);
        
            if (ecoreTemplate instanceof org.eclipse.uml2.uml.TemplateableElement){
                org.eclipse.uml2.uml.TemplateSignature signature = ((org.eclipse.uml2.uml.TemplateableElement) ecoreTemplate).getOwnedTemplateSignature();
        
                if (signature == null){
                    signature = ((org.eclipse.uml2.uml.TemplateableElement) ecoreTemplate).createOwnedTemplateSignature();
                    ObjingEAnnotation.setIsDeleted(signature);
                }
                ecoreElement.setSignature(signature);
                
                try{
                    signature.setTemplate((org.eclipse.uml2.uml.TemplateableElement) ecoreTemplate);
                }catch(IllegalArgumentException e){
                    signature.destroy();
                    ecoreTemplate.destroy();
                    String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType",
                                                        "instanciedTemplate", objElt.getName(), " TemplateBinding",  "TemplateableElement");
                    GenerationProperties.getInstance().addWarning(message, objElt);
                }
               
            }
        }
    }

}
