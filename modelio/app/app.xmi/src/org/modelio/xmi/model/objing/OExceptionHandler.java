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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("50ede5d5-b268-4745-ac94-8e3a640b1ba2")
public class OExceptionHandler extends OModelElement {
    @objid ("d55adfe4-5a6e-4c1e-aa35-2fd6ed8d9c9f")
    public  OExceptionHandler(ExceptionHandler element) {
        super(element);
    }

    @objid ("a3e26c38-445c-4aba-84d7-3065d2f4d086")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        //This is done on setProtectedNode method
    }

    @objid ("bf941f4d-a9ba-4680-83c2-67bef1a240a1")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setName(ecoreElt);
        setProtectedNode((org.eclipse.uml2.uml.ExceptionHandler) ecoreElt);
        setHandlerBody((org.eclipse.uml2.uml.ExceptionHandler) ecoreElt);    
        setExceptionInput((org.eclipse.uml2.uml.ExceptionHandler) ecoreElt);
        setExceptionType((org.eclipse.uml2.uml.ExceptionHandler) ecoreElt);
        
    }

    @objid ("8d56348a-a897-410e-82d3-f930ed393543")
    private void setProtectedNode(org.eclipse.uml2.uml.ExceptionHandler ecoreElt) {
        ActivityNode protectedNode = ((ExceptionHandler)getObjingElement()).getProtectedNode();
        Object ecoreProtectedNode =  GenerationProperties.getInstance().getMappedElement(protectedNode);    
        
        if ((ecoreProtectedNode != null) && (ecoreProtectedNode instanceof org.eclipse.uml2.uml.ExecutableNode)){
            ecoreElt.setProtectedNode( (org.eclipse.uml2.uml.ExecutableNode) ecoreProtectedNode);
        }else{
            ecoreElt.destroy();
        }
        
    }

    @objid ("62cb7fc0-26a1-478c-8fad-e8211e5c68f4")
    private void setName(org.eclipse.uml2.uml.Element ecoreElt) {
        ObjingEAnnotation.setName(ecoreElt, getObjingElement().getName());
    }

    @objid ("d1abc532-05b7-40df-a749-342baaec9914")
    private void setExceptionInput(org.eclipse.uml2.uml.ExceptionHandler ecoreElt) {
        InputPin obInput = ((ExceptionHandler)getObjingElement()).getExceptionInput();
        if (obInput != null) {
            Object ecoreInput = GenerationProperties.getInstance().getMappedElement(obInput);
            if (ecoreInput instanceof org.eclipse.uml2.uml.ObjectNode)
                ecoreElt.setExceptionInput((org.eclipse.uml2.uml.ObjectNode) ecoreInput);
        }
        
    }

    @objid ("9566e534-ecb2-4fef-a26c-cefb85238af6")
    private void setHandlerBody(org.eclipse.uml2.uml.ExceptionHandler ecoreElt) {
        ActivityNode body = ((ExceptionHandler) getObjingElement()).getExceptionInput().getInputing();
        Object ecoreBody = null;
        if (body instanceof InputPin) {
            ecoreBody = GenerationProperties.getInstance().getMappedElement(body.getCompositionOwner());
        }else {
            ecoreBody = GenerationProperties.getInstance().getMappedElement(body);    
        }
        
        if (ecoreBody instanceof org.eclipse.uml2.uml.ExecutableNode)
            ecoreElt.setHandlerBody( (org.eclipse.uml2.uml.ExecutableNode) ecoreBody);
        
    }

    @objid ("d7ee6929-7834-4098-bcd9-1fb03083a8a1")
    private void setExceptionType(org.eclipse.uml2.uml.ExceptionHandler ecoreElt) {
        ExceptionHandler exHandler = ((ExceptionHandler)getObjingElement());
        
        for (GeneralClass type : exHandler.getExceptionType()){
            org.eclipse.uml2.uml.Element ecoreType = GenerationProperties.getInstance().getMappedElement(type);
            if (ecoreType != null){
                if (ecoreType instanceof org.eclipse.uml2.uml.Classifier){
                    ecoreElt.getExceptionTypes().add( (org.eclipse.uml2.uml.Classifier) ecoreType);
                }else{
                    String msg = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType", 
                            "exceptionType",  exHandler.getName(), "ExceptionHandler", "Classifier"); 
                    GenerationProperties.getInstance().addWarning(msg, getObjingElement());
                }
            }
        }
        
    }

    @objid ("3f441d1e-c227-4378-a66a-c1fcfd2d4123")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createExceptionHandler();
    }

}
