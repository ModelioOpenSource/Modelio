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

package org.modelio.xmi.model.ecore;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("900e8f42-72ec-429f-9b2e-3df7f4e14880")
public class ELifeline extends ENamedElement {
    @objid ("282781e4-2c75-43e5-ad9a-915d858f10e4")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createLifeline();
    }

    @objid ("f66d6908-a422-4a5b-b418-886c5436fe19")
    public ELifeline(org.eclipse.uml2.uml.Lifeline element) {
        super(element);
    }

    @objid ("9f0bed69-55fe-4c12-a4bf-d6f9970c5699")
    @Override
    public void attach(Element objingElt) {
        Object objingInt = ReverseProperties.getInstance()
                .getMappedElement(((org.eclipse.uml2.uml.Lifeline)getEcoreElement()).getInteraction());
        
        if (objingInt instanceof Interaction) {
            ((Interaction) objingInt).getOwnedLine().add((Lifeline) objingElt);
        }
    }

    @objid ("e291341c-c106-47eb-b43a-007f58eb3ede")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setRepresented((Lifeline) objingElt);
    }

    @objid ("41ad24e0-502f-4a7b-9189-f5a9c5cc26e4")
    private void setRepresented(Lifeline lifeline) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        boolean isRepresented = false;
        
        if ( revProp.isRoundtripEnabled()) {
        
            for (Object dependency : ((org.eclipse.uml2.uml.Lifeline) getEcoreElement()).getClientDependencies()){
        
                if (ObjingEAnnotation.isRepresentation((org.eclipse.uml2.uml.Element)dependency)){
                    Element representation = (Element)revProp.getMappedElement(((org.eclipse.uml2.uml.Dependency)dependency).getSuppliers().get(0));
        
                    if (representation instanceof Instance){
                        lifeline.setRepresented((Instance)representation);
                        isRepresented = true;
                        break;
                    }
                }
            }
        }
        
        if (!isRepresented){
            setNormalRepresented(revProp,lifeline);
        }
    }

    @objid ("c99da5ef-8631-4acd-89ea-6f114c16ee38")
    private void setNormalRepresented(ReverseProperties revProp, Lifeline lifeline) {
        org.eclipse.uml2.uml.ConnectableElement ecoreRepresented = ((org.eclipse.uml2.uml.Lifeline) getEcoreElement()).getRepresents();
        
        if (ecoreRepresented != null) {
        
            Object objingRepresented = revProp.getMappedElement(ecoreRepresented);
            if (objingRepresented instanceof Instance)
                lifeline.setRepresented((Instance)objingRepresented);
            else if (objingRepresented instanceof List){
                for (ModelElement elt : (ArrayList<ModelElement>) objingRepresented){
                    if (elt instanceof Instance){
                        lifeline.setRepresented((Instance) elt);
                    }
                }
            }
        }
    }

}
