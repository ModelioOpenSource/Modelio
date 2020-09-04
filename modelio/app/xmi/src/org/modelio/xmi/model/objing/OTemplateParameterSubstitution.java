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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of Modelio TemplateParameterSubstitution
 * @author ebrosse
 */
@objid ("a6cd7e88-7252-4db1-ab8c-9e49a2be097e")
public class OTemplateParameterSubstitution extends OElement implements IOElement {
    @objid ("b8da72aa-5bf6-463f-8d3a-afed2c43463b")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(getObjingElement().getCompositionOwner());
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateBinding)   
            return UMLFactory.eINSTANCE.createTemplateParameterSubstitution();
        else 
            return null;
    }

    /**
     * Constructor
     * 
     * @param param : the exported Modelio TemplateParameterSubstitution
     */
    @objid ("68ec8b48-d3fb-427e-9665-3cc7b0edd72c")
    public OTemplateParameterSubstitution(final TemplateParameterSubstitution param) {
        super(param);
    }

    @objid ("14185bfb-bf28-488c-9727-c5669eca988a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(getObjingElement().getCompositionOwner());
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateBinding){              
            ((org.eclipse.uml2.uml.TemplateBinding) ecoreOwner).getParameterSubstitutions().add((org.eclipse.uml2.uml.TemplateParameterSubstitution) ecoreElt);               
        }
    }

    @objid ("ceae893e-151d-420c-83b2-3c09a88c69d2")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setValue(ecoreElt);
        setFormal(ecoreElt);
        setActual(ecoreElt);
    }

    @objid ("39f0d40d-6023-4327-a9c8-8aeece841ae5")
    private void setFormal(org.eclipse.uml2.uml.Element ecoreElt) {
        Element formal = ((TemplateParameterSubstitution) getObjingElement()).getFormalParameter();
        if (formal != null){
        
            org.eclipse.uml2.uml.Element ecoreFormal = GenerationProperties.getInstance().getMappedElement(formal);
            if ((ecoreFormal != null) && (ecoreFormal instanceof org.eclipse.uml2.uml.TemplateParameter)){
                ((org.eclipse.uml2.uml.TemplateParameterSubstitution) ecoreElt)
                .setFormal((org.eclipse.uml2.uml.TemplateParameter) ecoreFormal);
            }else{
                String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType"
                                                    , "FormalParameter", "" ,"TemplateParameterSubtitution", "TemplateParameter");
                GenerationProperties.getInstance().addWarning(message, getObjingElement());
        
            }
        }
    }

    @objid ("d6404135-446f-41e5-b591-350de27babbc")
    private void setValue(org.eclipse.uml2.uml.Element ecoreElt) {
        ObjingEAnnotation.setValue(ecoreElt, ((TemplateParameterSubstitution) getObjingElement()).getValue());
    }

    @objid ("f27c9104-0d8f-429c-a707-f59e0099be92")
    private void setActual(org.eclipse.uml2.uml.Element ecoreElt) {
        Element actual = ((TemplateParameterSubstitution) getObjingElement()).getActual();
        
        if (actual != null){
            org.eclipse.uml2.uml.Element ecoreFormal = GenerationProperties.getInstance().getMappedElement(actual);
            if ((ecoreFormal != null) && (ecoreFormal instanceof org.eclipse.uml2.uml.ParameterableElement)){
                ((org.eclipse.uml2.uml.TemplateParameterSubstitution) ecoreElt).setActual((org.eclipse.uml2.uml.ParameterableElement) ecoreFormal);
        
            }else{
                String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType",
                                                    "Actual", "" ,"TemplateParameterSubtitution" , "ParameterableElement");
                GenerationProperties.getInstance().addWarning(message, getObjingElement());
        
            }
        }
    }

}
