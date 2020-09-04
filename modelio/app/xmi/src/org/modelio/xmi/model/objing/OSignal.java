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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of Signal elements
 * @author ebrosse
 */
@objid ("39c24184-4fa7-4f86-8bec-22b1fb2609f4")
public class OSignal extends ONameSpace {
    @objid ("d65ac531-975e-4af2-af16-c8a98432549c")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createSignal();
    }

    /**
     * @param param : the exported Signal
     */
    @objid ("7644b6bd-a2eb-4ac1-b605-a89bd006c25f")
    public OSignal(final Signal param) {
        super(param);
    }

    @objid ("fe8458f7-3e52-4ac3-be7f-69588ae3209d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof Package) {
                Package ownerIsPkg = (Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add((PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof Component) {
                Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add((PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                ownerIsItf.getNestedClassifiers().add( (org.eclipse.uml2.uml.Signal)ecoreElt);
            } else if (ecoreOwner instanceof Class) {
                Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("dfc59042-07f2-4f78-84c4-05be69a132ef")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        //UML Properties
        setCBase((org.eclipse.uml2.uml.Signal) ecoreElt);
        setOBase((org.eclipse.uml2.uml.Signal) ecoreElt);
        setPBase();
        
        //Modelio Properties
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setIsEventEAnnotation( (org.eclipse.uml2.uml.Signal) ecoreElt);
            setIsExceptionEAnnotation( (org.eclipse.uml2.uml.Signal) ecoreElt);
        }
    }

    @objid ("64ca1160-77bd-4b96-9010-351096c1a43a")
    private void setPBase() {
        Parameter base = getObjingElement().getPBase();
        
        if (base != null) {
            String   message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.title", "Base");
            String   description = Xmi.I18N.getMessage("logFile.warning.unsupportedTypeExport.message");             
            GenerationProperties.getInstance().addWarning(message, getObjingElement(), description);
        }
    }

    @objid ("f7d87123-b60d-473d-b148-83c5c80d306d")
    private void setOBase(final org.eclipse.uml2.uml.Signal ecoreElt) {
        Operation base = getObjingElement().getOBase();
        
        if (base != null) {
        
            org.eclipse.uml2.uml.Element ecoreBase = GenerationProperties.getInstance().getMappedElement(base);
        
            if  (ecoreBase != null) {
                if  (ecoreBase instanceof org.eclipse.uml2.uml.Reception){    
                    ((org.eclipse.uml2.uml.Reception)ecoreBase).setSignal(ecoreElt);
                }else{
                    String ecoreClassName = ecoreBase.getClass().getSimpleName();
                    String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.title", "Base");
                    String description = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.description",
                            "Base",
                            getObjingElement().getName(),
                            "org.eclipse.uml2.uml.Reception",
                            ecoreClassName.substring(0, ecoreClassName.length() - 4));
                    GenerationProperties.getInstance().addWarning(message, getObjingElement(), description);
        
                }
            }else {
                String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.title", "Base");
                String description = Xmi.I18N.getMessage("logFile.warning.unsupportedTypeExport.message");
                GenerationProperties.getInstance().addWarning(message, getObjingElement(), description);
            }
        
        }
    }

    @objid ("e406a2f3-16aa-4929-8e2f-03bad04d0e14")
    private void setCBase(final org.eclipse.uml2.uml.Signal ecoreElt) {
        GeneralClass base = getObjingElement().getBase();
        
        if (base != null) {
        
            org.eclipse.uml2.uml.Element ecoreBase = GenerationProperties.getInstance().getMappedElement(base);
        
            if  (ecoreBase != null) {
                if  (ecoreBase instanceof org.eclipse.uml2.uml.Classifier){      
                    ecoreElt.getRedefinedClassifiers().add((org.eclipse.uml2.uml.Classifier) ecoreBase);      
                }else{
                    String ecoreClassName = ecoreBase.getClass().getSimpleName();
                    String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.title", "Base");
                    String description = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.description",
                            "Base",
                            getObjingElement().getName(),
                            "Classifier",
                            ecoreClassName.substring(0, ecoreClassName.length() - 4));
                    GenerationProperties.getInstance().addWarning(message, getObjingElement(), description);
        
                }
            }else {
                String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.title", "Base");
                String description = Xmi.I18N.getMessage("logFile.warning.unsupportedTypeExport.message");
                GenerationProperties.getInstance().addWarning(message, getObjingElement(), description);
            }
        
        }
    }

    @objid ("106d9024-1ed4-40d0-908a-66e7adb1d859")
    private void setIsEventEAnnotation(org.eclipse.uml2.uml.Signal signal) {
        ObjingEAnnotation.setIsEvent(signal, getObjingElement().isIsEvent());
    }

    @objid ("f4179288-f9a7-4968-8bf8-7fd918235021")
    private void setIsExceptionEAnnotation(org.eclipse.uml2.uml.Signal signal) {
        ObjingEAnnotation.setIsException(signal, getObjingElement().isIsEvent());
    }

    @objid ("f00cb485-50dc-428c-b33a-563b547adf79")
    @Override
    public Signal getObjingElement() {
        return (Signal) super.getObjingElement();
    }

}
