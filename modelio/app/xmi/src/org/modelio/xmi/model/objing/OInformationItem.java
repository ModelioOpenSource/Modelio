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
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of InformationItem
 * @author ebrosse
 */
@objid ("b1e6fad1-29ea-4a11-ad94-33065f12013a")
public class OInformationItem extends ONameSpace {
    @objid ("2c2c7a0b-1095-4364-9c3a-02b290102d13")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("f2abd2da-13f7-4166-874b-fb54bf06c68f")
    private InformationItem objElement = null;

    @objid ("a23c8e26-225e-497f-a126-737700493651")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createInformationItem();
    }

    /**
     * Constructor
     * 
     * @param param : the exported Modelio InformationItem
     */
    @objid ("093d257d-bb01-4aa5-b297-240a23e81dff")
    public OInformationItem(final InformationItem param) {
        super(param);
        this.objElement = param;
    }

    @objid ("df033634-8835-482a-ae67-eefb67676a66")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Element objOwner = this.objElement.getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = this.genProp.getMappedElement(objOwner);
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Package){
            ((org.eclipse.uml2.uml.Package) ecoreOwner).getPackagedElements().add((org.eclipse.uml2.uml.InformationItem)ecoreElt);
        }else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component){
            ((org.eclipse.uml2.uml.Component) ecoreOwner).getPackagedElements().add((org.eclipse.uml2.uml.InformationItem)ecoreElt);
        }else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface){
            ((org.eclipse.uml2.uml.Interface) ecoreOwner).getNestedClassifiers().add((org.eclipse.uml2.uml.InformationItem)ecoreElt);
        }else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class){
            ( (org.eclipse.uml2.uml.Class) ecoreOwner).getNestedClassifiers().add((org.eclipse.uml2.uml.InformationItem)ecoreElt);
        }else{
            AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(objOwner, this.objElement, ecoreElt);
        }
    }

    @objid ("3e630334-980c-450e-86f8-f933344f11ae")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        //UML Properties
        setRepresented((org.eclipse.uml2.uml.InformationItem) ecoreElt);
        
        //Modelio Properties
        if (this.genProp.isRoundtripEnabled()){
            setLeaf((org.eclipse.uml2.uml.InformationItem) ecoreElt);
            setRoot((org.eclipse.uml2.uml.InformationItem) ecoreElt);         
        }
    }

    @objid ("d101f29c-dc8e-42e0-b057-30a5a3e97376")
    private void setRoot(org.eclipse.uml2.uml.InformationItem ecoreElt) {
        ObjingEAnnotation.setIsRoot(ecoreElt, this.objElement.isIsRoot());
    }

    @objid ("e12e4dbd-b7d9-44c1-a9c5-2ee102cbdce5")
    private void setLeaf(org.eclipse.uml2.uml.InformationItem ecoreElt) {
        ObjingEAnnotation.setIsLeaf(ecoreElt, this.objElement.isIsLeaf());
    }

    @objid ("6fb97494-ab48-449f-86a4-700b189718fb")
    private void setRepresented(org.eclipse.uml2.uml.InformationItem ecoreElt) {
        for (Classifier classifier : this.objElement.getRepresented()){
            org.eclipse.uml2.uml.Element ecoreClassifier = this.genProp.getMappedElement(classifier);
            if (ecoreClassifier instanceof org.eclipse.uml2.uml.Classifier){
                ecoreElt.getRepresenteds().add( (org.eclipse.uml2.uml.Classifier) ecoreClassifier);
            }else{
                String ecoreClassName = classifier.getClass().getSimpleName();
                String message = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.title", "Represented" );
                String description = Xmi.I18N.getMessage("logFile.warning.export.unsupportedRelation.wrongEcoreType.description",
                                                    "Represented",
                                                    this.objElement.getName(),
                                                    "Classifier",
                                                    ecoreClassName.substring(0, ecoreClassName.length() - 4));
                GenerationProperties.getInstance().addWarning(message, this.objElement, description);
            }
        }
    }

}
