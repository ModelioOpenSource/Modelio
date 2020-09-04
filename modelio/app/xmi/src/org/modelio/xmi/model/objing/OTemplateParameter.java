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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of TemplateParameter
 * @author ebrosse
 */
@objid ("d84b7e51-3e0d-4cc4-b2f2-8fafb8689e7c")
public class OTemplateParameter extends ONameSpace {
    @objid ("be6ed5bc-c9ba-452e-9ae0-6d5c8a84c81c")
    private org.eclipse.uml2.uml.TemplateSignature signature = null;

    @objid ("83a82ce4-7047-4117-b93b-9057cb0c4708")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (((ModelElement) getObjingElement()).isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CONNECTABLEELEMENTTEMPLATEPARAMETER)){
            return UMLFactory.eINSTANCE.createConnectableElementTemplateParameter();
        }else  if (((ModelElement) getObjingElement()).isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLASSIFIERTEMPLATEPARAMETER)){
            return UMLFactory.eINSTANCE.createClassifierTemplateParameter();
        }else{
            Element objOwner =  ((TemplateParameter)getObjingElement()).getParameterized();
            if (objOwner == null)
                objOwner =  ((TemplateParameter)getObjingElement()).getParameterizedOperation();
        
            org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objOwner);
        
            if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateableElement){
        
                this.signature = ((org.eclipse.uml2.uml.TemplateableElement) ecoreOwner).getOwnedTemplateSignature();
        
                if (this.signature == null){
                    try{
                        this.signature = ((org.eclipse.uml2.uml.TemplateableElement) ecoreOwner).createOwnedTemplateSignature();
                    }catch(IllegalArgumentException e){
                        Xmi.LOG.error(e);
                        return null;
                    }
                    ObjingEAnnotation.setIsDeleted(this.signature);
                }
                return this.signature.createOwnedParameter();
        
            }
            return null;
        }
    }

    /**
     * Constructor
     * 
     * @param element : the exported Modelio TemplateParameter
     */
    @objid ("9b6ba6d8-d07b-4e84-a8fe-f85c19ea4de4")
    public OTemplateParameter(final TemplateParameter element) {
        super(element);
    }

    @objid ("dea03c78-aa5d-4f5e-b17b-dd4ef3d95c20")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Element objOwner =  ((TemplateParameter) getObjingElement()).getParameterized();
        
        if (objOwner == null)
            objOwner =  ((TemplateParameter)getObjingElement()).getParameterizedOperation();
        
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objOwner);
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateableElement){
        
            org.eclipse.uml2.uml.TemplateSignature templSignature = ((org.eclipse.uml2.uml.TemplateableElement) ecoreOwner).getOwnedTemplateSignature();
        
        
            if (templSignature == null){
                templSignature = UMLFactory.eINSTANCE.createTemplateSignature();              
            }
        
            templSignature.getOwnedElements();
            templSignature.getOwnedParameters().add((org.eclipse.uml2.uml.TemplateParameter) ecoreElt);
        
            try{
                ((org.eclipse.uml2.uml.TemplateableElement) ecoreOwner).setOwnedTemplateSignature(templSignature);
        
            }catch(IllegalArgumentException e){     
                ecoreElt.destroy();
                templSignature.destroy();
                Xmi.LOG.error(e);
            }
        
        }else if ((ecoreOwner instanceof org.eclipse.uml2.uml.RedefinableTemplateSignature) && (ecoreElt instanceof org.eclipse.uml2.uml.TemplateParameter)){
            ((org.eclipse.uml2.uml.RedefinableTemplateSignature) ecoreOwner).getOwnedParameters().add((org.eclipse.uml2.uml.TemplateParameter)ecoreElt);
        }else{
            ecoreElt.destroy();
        }
    }

    @objid ("b0e9f1e6-df7e-4f00-8c86-ca60b6a34bf3")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        setTemplateType((org.eclipse.uml2.uml.TemplateParameter) ecoreElt);
        setDefaultType((org.eclipse.uml2.uml.TemplateParameter) ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled())
            setIsValueParameter((org.eclipse.uml2.uml.TemplateParameter) ecoreElt);
    }

    @objid ("0c0fd4da-33c1-43d2-a628-fffbc939c2e7")
    private void setTemplateType(org.eclipse.uml2.uml.TemplateParameter ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreType = getEcoreType(((TemplateParameter)getObjingElement()).getType());
        if (ecoreType != null)
            setParameteredElement(ecoreElt, ecoreType);
    }

    @objid ("179d3e8c-970c-422d-a9d3-a2b83e17435c")
    private org.eclipse.uml2.uml.Element getEcoreType(ModelElement type) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        // Getting type of the org.eclipse.uml2.uml.Template:
        ModelElement objingType = type;
        org.eclipse.uml2.uml.Element ecoreType = null;
        if (objingType != null) {
            ecoreType = genProp.getMappedElement(objingType);
        }
        return ecoreType;
    }

    @objid ("a4618e65-380d-4514-9ade-95f1b5c2bff5")
    private void setParameteredElement(org.eclipse.uml2.uml.TemplateParameter ecoreTemplateParam, org.eclipse.uml2.uml.Element ecoreElement) {
        if (ecoreElement instanceof org.eclipse.uml2.uml.ParameterableElement){
            try{
                org.eclipse.uml2.uml.ParameterableElement parameterableElement = (org.eclipse.uml2.uml.ParameterableElement) ecoreElement;
                ecoreTemplateParam.setParameteredElement(parameterableElement);
            }catch(IllegalArgumentException e){
                String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType",
                        "parameteredElement", getObjingElement().getName(), " ParameterableElement",  "TemplateableElement");
                GenerationProperties.getInstance().addWarning(message, getObjingElement());
                Xmi.LOG.error(e);
            }
        }
    }

    @objid ("7ecd9a05-0a56-45bc-9299-94e5e43a8211")
    private void setIsValueParameter(org.eclipse.uml2.uml.TemplateParameter ecoreElt) {
        ObjingEAnnotation.setIsValueParameter(ecoreElt, ((TemplateParameter) getObjingElement()).isIsValueParameter());
    }

    @objid ("d51d99dc-37a5-4470-9972-0510be154ea5")
    private void setDefaultType(org.eclipse.uml2.uml.TemplateParameter ecoreElt) {
        TemplateParameter templateParam = ((TemplateParameter)getObjingElement());
        ModelElement type = templateParam.getDefaultType();
        if (type != null){
            org.eclipse.uml2.uml.Element ecoreType = getEcoreType(type);
            setDefault(ecoreElt, ecoreType);
        }else{
            org.eclipse.uml2.uml.LiteralString literalString = UMLFactory.eINSTANCE.createLiteralString();
            literalString.setValue(templateParam.getDefaultValue());
            setDefault(ecoreElt, literalString);
        }
    }

    @objid ("d574d9c7-4716-4856-9ac1-e49a0c136841")
    private void setDefault(org.eclipse.uml2.uml.TemplateParameter ecoreTemplateParam, org.eclipse.uml2.uml.Element ecoreElement) {
        if (ecoreElement instanceof org.eclipse.uml2.uml.ParameterableElement){
            org.eclipse.uml2.uml.ParameterableElement parameterableElement = (org.eclipse.uml2.uml.ParameterableElement) ecoreElement;
            ecoreTemplateParam.setOwnedDefault(parameterableElement);
        }
    }

}
