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
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.KindOfAccess;
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
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Property){
        
            StructuralFeature structFeature = getObjingElement();
            org.eclipse.uml2.uml.Property ecoreFeature = (org.eclipse.uml2.uml.Property) ecoreElt;
        
            setMin(ecoreFeature, structFeature);
            setMax(ecoreFeature, structFeature);
            setOrdered(ecoreFeature);
            setStatic(ecoreFeature);
            setUnique(ecoreFeature);
        
            setReadOnly(ecoreFeature);
        
            if (GenerationProperties.getInstance().isRoundtripEnabled()) {
                setAbstractEAnnotation(ecoreFeature);
                setAccessModeEAnnotation(ecoreFeature);
            }
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

    @objid ("faa47d36-8c45-48f7-a567-ada6d17cc800")
    private void setReadOnly(Property ecoreProp) {
        ecoreProp.setIsReadOnly(getObjingElement().getChangeable().equals(KindOfAccess.READ));
    }

    @objid ("a1df8f98-e45b-4965-97b0-eda5c9c5ecfc")
    private void setStatic(Property ecoreProp) {
        ecoreProp.setIsStatic(getObjingElement().isIsClass());
    }

    @objid ("8aa16d68-fa61-4401-ae57-da1de88c9aa9")
    private void setOrdered(Property ecoreProp) {
        ecoreProp.setIsOrdered(getObjingElement().isIsOrdered());
    }

    @objid ("fdd5c808-ad7b-46f6-bae6-1b915765444e")
    private void setUnique(Property ecoreProp) {
        ecoreProp.setIsUnique(getObjingElement().isIsUnique());
    }

    @objid ("aae6dbf1-6fa1-4fd8-962f-5147edcd980b")
    private void setAccessModeEAnnotation(Property ecoreProp) {
        switch (getObjingElement().getChangeable()) {
        case READ:
            ObjingEAnnotation.setAccessMode(ecoreProp,
                    ObjingEAnnotation.READ_VALUE);
            break;
        case WRITE:
            ObjingEAnnotation.setAccessMode(ecoreProp,
                    ObjingEAnnotation.WRITE_VALUE);
            break;
        case READWRITE:
            ObjingEAnnotation.setAccessMode(ecoreProp,
                    ObjingEAnnotation.READ_WRITE_VALUE);
            break;
        case ACCESNONE:
            ObjingEAnnotation.setAccessMode(ecoreProp,
                    ObjingEAnnotation.ACCESS_NONE_VALUE);
            break;
        default:
            break;
        }
    }

    @objid ("3ad98c5f-d171-4462-a9d5-49134db39cd0")
    private void setAbstractEAnnotation(final org.eclipse.uml2.uml.Element ecoreElt) {
        ObjingEAnnotation.setIsAbstract(ecoreElt, ((Feature) getObjingElement()).isIsAbstract());
    }

    @objid ("c23e7122-0817-4ed8-bb46-248298eff3fe")
    @Override
    public StructuralFeature getObjingElement() {
        return (StructuralFeature) super.getObjingElement();
    }

}
