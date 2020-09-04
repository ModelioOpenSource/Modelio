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
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.StringConverter;

@objid ("118126ea-3beb-4a06-853e-59983b13b181")
public class OStructuralFeature extends OFeature {
    @objid ("ef663bf1-e2c4-4fb2-82cc-553124569e47")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
    }

    @objid ("61599c5c-7a78-4c9d-bbe7-8b99971d75fc")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.StructuralFeature){
        
            StructuralFeature structFeature = (StructuralFeature) getObjingElement();
            org.eclipse.uml2.uml.StructuralFeature ecoreFeature = (org.eclipse.uml2.uml.StructuralFeature) ecoreElt;
            setMin(ecoreFeature, structFeature);
            setMax(ecoreFeature, structFeature);
        }
    }

    @objid ("59ba54c2-2346-4559-bbb8-c30223e2aeb6")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("b4c586d5-af97-4404-9447-afb752940bbf")
    public OStructuralFeature(StructuralFeature element) {
        super(element);
    }

    @objid ("dddc0056-ea3d-4fa2-9ec7-9f5692e25b67")
    private void setMax(org.eclipse.uml2.uml.StructuralFeature ecoreFeature, StructuralFeature structFeature) {
        String objingMultMax = structFeature.getMultiplicityMax();
        
        // If objingMultMax is "" then we don't set an upper multiplicity for
        // the UML2 element.
        if (!"".equals(objingMultMax)) {
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE
                    .equals(objingMultMax)) {
                ecoreFeature.setUpper(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            } else {
                StringConverter.setFilterEnabled(true);
                Integer max = StringConverter.getInteger(objingMultMax);
                if (max != null) {
                    if (max != 1) {
                        ecoreFeature.setUpper(max);
                    }
                } else {
                    String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.multiplicityMaxWithName", "StructuralFeature", structFeature.getName());       
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMax + "\"", "Integer", contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, getObjingElement());
                }
            }
        }
        
        if (GenerationProperties.getInstance().isRoundtripEnabled())
            ObjingEAnnotation.setMultiMax(ecoreFeature, objingMultMax);
    }

    @objid ("03cb5187-ff84-4496-b70c-cdd66e7fd679")
    private void setMin(org.eclipse.uml2.uml.StructuralFeature ecoreFeature, StructuralFeature structFeature) {
        String objingMultMin = structFeature.getMultiplicityMin();
        
        // If objingMultMin is "" then we don't set a lower multiplicity for the
        // UML2 element.
        if (!"".equals(objingMultMin)) {
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE
                    .equals(objingMultMin)) {
                ecoreFeature.setLower(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            } else {
                StringConverter.setFilterEnabled(true);
                Integer min = StringConverter.getInteger(objingMultMin);
                if (min != null) {
                    if (min != 1) {
                        ecoreFeature.setLower(min);
                    }
                } else {
        
                    String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.multiplicityMinWithName", "StructuralFeature", structFeature.getName());      
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMin + "\"", "Integer", contextualMsg);                
                    GenerationProperties.getInstance().addInfo(message, getObjingElement());
                }
            }
        }
        
        if (GenerationProperties.getInstance().isRoundtripEnabled())
            ObjingEAnnotation.setMultiMin(ecoreFeature, objingMultMin);
    }

}
