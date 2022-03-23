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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the import of Ecore org.eclipse.uml2.uml.Generalization
 * 
 * @author ebrosse
 */
@objid ("1aee77f3-3ec1-460d-aba3-c352962f43b5")
public class EGeneralization extends EElement {
    @objid ("f10d7377-c3a2-4f2a-b7a4-f7fe9abfcc3f")
    private org.eclipse.uml2.uml.Generalization ecoreElement = null;

    @objid ("19a5b006-1f94-48f7-92c0-4bca559870b9")
    private ReverseProperties revProp = ReverseProperties.getInstance();

    @objid ("0799e11a-57aa-4fc8-bb23-355eb5464c3b")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Classifier ecoreGeneral = this.ecoreElement.getGeneral();
        org.eclipse.uml2.uml.Classifier ecoreSpecific = this.ecoreElement.getSpecific();
        
        if (ecoreSpecific == null) {
            // Case Specific element (generalization owner) is missing
            String message = Xmi.I18N.getMessage("logFile.warning.generalization.needSpecific");
            this.revProp.addError(message);
            return null;
        }
        
        if (ecoreGeneral == null) {
            // Case General element is missing
            String message = Xmi.I18N.getMessage("logFile.warning.generalization.needGeneral");
            this.revProp.addError(message);
            return null;
        }
        
        Object objingSpecific = this.revProp.getMappedElement(ecoreSpecific);
        Object objingGeneral = this.revProp.getMappedElement(ecoreGeneral);
        
        if (ecoreSpecific instanceof org.eclipse.uml2.uml.Stereotype) {
            return createGeneralizationSter(objingSpecific, objingGeneral);
        } else {
            return createGeneralizationElt(ecoreSpecific, objingSpecific);
        }
        
    }

    /**
     * The EGeneralization constructor with the imported Ecore org.eclipse.uml2.uml.Generalization as parameter
     * @param element : the imported Ecore org.eclipse.uml2.uml.Generalization
     */
    @objid ("89655b78-47b1-43d3-9517-ec4da208023b")
    public  EGeneralization(final org.eclipse.uml2.uml.Generalization element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("4a1ed6cd-77af-40cb-a4bb-cf78a903f7c7")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Classifier ecoreSpecific = this.ecoreElement.getSpecific();
        
        Element objingSpecific = (Element) this.revProp.getMappedElement(ecoreSpecific);
        
        org.eclipse.uml2.uml.Classifier ecoreGeneral = this.ecoreElement.getGeneral();
        Object objingGeneral = null;
        
        if (ecoreGeneral != null) {
            objingGeneral = this.revProp.getMappedElement(ecoreGeneral);
        }
        
        if ((objingGeneral != null)
                && (objingGeneral instanceof NameSpace)
                && (objingSpecific instanceof NameSpace)
                && (objingElt instanceof Generalization)) {
        
            Generalization objingGeneneralization = ((Generalization) objingElt);
            objingGeneneralization.setSubType((NameSpace) objingSpecific);
            objingGeneneralization.setSuperType((NameSpace) objingGeneral);
        
        } else {
            objingElt.delete();
        }
        
    }

    @objid ("9ca5e39c-5ef4-4676-8d6f-67012a88a1de")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        if (this.revProp.isRoundtripEnabled()) {
            setDiscriminator((Generalization) objingElt);
        }
        
    }

    @objid ("838bc89f-728a-4b1f-8142-c86216b37779")
    private void setDiscriminator(Generalization objingElt) {
        String discriminator = ObjingEAnnotation.getDiscriminator(this.ecoreElement);
        if (discriminator != null) {
            objingElt.setDiscriminator(discriminator);
        }
        
    }

    @objid ("5635228c-e5e9-4ed4-b7f2-5edc3e652cc2")
    private Element createGeneralizationElt(org.eclipse.uml2.uml.Classifier ecoreSpecific, Object objingSpecific) {
        // General case
        if ((objingSpecific != null)
                && (objingSpecific instanceof NameSpace)) {
        
            return this.revProp.getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createGeneralization();
        
        } else {
        
            String message = Xmi.I18N.getMessage("logFile.warning.unsupportedNamedEnds", " ", "Generalization", ecoreSpecific.getName(), ecoreSpecific.getClass().getSimpleName(),
                    ecoreSpecific.getName(), ecoreSpecific.getClass().getSimpleName());
        
            this.revProp.addError(message);
            return null;
        }
        
    }

    @objid ("7b88ce6a-3ee4-46f5-b765-21bbf574bb47")
    private Element createGeneralizationSter(Object objingSpecific, Object objingGeneral) {
        // Stereotypes
        if ((objingGeneral != null)
                && (objingSpecific != null)) {
        
            // Created during the import
        
            List<Stereotype> objingGenerals = new ArrayList<>();
            List<Stereotype> objingSpecifics = new ArrayList<>();
        
            if (objingSpecific instanceof Stereotype) {
                objingSpecifics.add((Stereotype) objingSpecific);
            } else if (objingSpecific instanceof ArrayList<?>) {
                for (Object temp : (ArrayList<?>) objingSpecific ) {
                    if (temp instanceof Stereotype) {
                        objingSpecifics.add((Stereotype) temp);
                    }
                }
            }
        
            if (objingGeneral instanceof Stereotype) {
                objingGenerals.add((Stereotype) objingGeneral);
            } else if (objingGeneral instanceof ArrayList<?>) {
                for (Object temp : (ArrayList<?>) objingGeneral ) {
                    if (temp instanceof Stereotype) {
                        objingGenerals.add((Stereotype) temp);
                    }
                }
            }
        
            for (Stereotype currentSpecific : objingSpecifics) {
                for (Stereotype currentGeneral : objingGenerals) {
                    if (currentSpecific.getBaseClassName().equals(currentGeneral.getBaseClassName())
                            && currentSpecific.getStatus().isModifiable()) {
                        currentSpecific.setParent(currentGeneral);
                        break;
                    }
                }
            }
            
        }
        return null;
    }

}
