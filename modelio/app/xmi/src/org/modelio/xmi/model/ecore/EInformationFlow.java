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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("53296c13-1b6d-4565-a462-8b5c9ce1b402")
public class EInformationFlow extends ENamedElement {
    @objid ("2907e820-4cff-41a3-ba5d-064aa44a035c")
    private org.eclipse.uml2.uml.InformationFlow ecoreElement = null;

    @objid ("ec94cb10-0e42-4ee2-af20-ad41f21a09c5")
    @Override
    public Element createObjingElt() {
        return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInformationFlow();
    }

    @objid ("6c900606-27bb-4eac-920b-1c28bc33ddd7")
    public EInformationFlow(org.eclipse.uml2.uml.InformationFlow element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("dc56e541-3384-4d5c-890b-bd36c2e41b56")
    @Override
    public void attach(Element objingElt) {
        /*
         * //  take the model map EOModelMap modelMap =
         * EOModelMap.getInstance(); ReverseProperties revProp =
         * ReverseProperties.getInstance(); //  take the ecore Imported and
         * Importing ecoreElement.getRelationships();
         * ecoreElement.getConveyeds(); ecoreElement.getRealizingConnectors();
         * ecoreElement.getRealizingActivityEdges();
         * ecoreElement.getRealizingMessages();
         * 
         * EList targetsEcore = ecoreElement.getTargets(); EList sourcesEcore =
         * ecoreElement.getSources();
         */
        List <org.eclipse.uml2.uml.Element> sourceList = this.ecoreElement.getSources();
        
        boolean attached = false;
        
        if (sourceList.size() > 0){
            for (Object ecoreSource : sourceList){
                Element objingSource = (Element) ReverseProperties.getInstance()
                        .getMappedElement((org.eclipse.uml2.uml.Element)ecoreSource);
                if (objingSource instanceof NameSpace) {
                    InformationFlow informationFlow = (InformationFlow) objingElt;
                    informationFlow.setOwner((NameSpace) objingSource);
                    attached = true;
                }
            }
        
        }
        
        if (!attached){
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
            Element objingOwner = (Element) ReverseProperties.getInstance()
                    .getMappedElement(ecoreOwner);
        
            if (objingOwner instanceof NameSpace) {
                ((InformationFlow) objingElt).setOwner((NameSpace) objingOwner);       
            }else{
                objingElt.delete();
            }
        }
    }

    @objid ("f5019d08-1027-4dc9-a8ec-7246a30e6a79")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setInformationSource((InformationFlow)objingElt);
        setInformationTarget((InformationFlow)objingElt);
        setRealizingMessage((InformationFlow)objingElt);
        setRealizingActivityEdge((InformationFlow)objingElt);
        setConveyed((InformationFlow)objingElt);
        setRealizingAssociation((InformationFlow)objingElt);
        checkSourceAndTarget((InformationFlow)objingElt);
    }

    @objid ("e0c2559f-f9f4-4891-8b57-cdc4bd761b59")
    private void setInformationSource(InformationFlow objingElt) {
        for (Object ecoreSource :  this.ecoreElement.getInformationSources()) {
            Object objingSource = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)ecoreSource);
            if (objingSource instanceof UmlModelElement) {
                objingElt.getInformationSource().add((UmlModelElement)objingSource);
            }
        }
    }

    @objid ("e393bc19-fbd4-4b50-80ec-94e0199bd5b6")
    private void setInformationTarget(InformationFlow objingElt) {
        for (Object ecoreTarget : this.ecoreElement.getInformationTargets()) {
            Object objingTarget = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)ecoreTarget);
            if (objingTarget instanceof UmlModelElement) {
                objingElt.getInformationTarget().add((UmlModelElement)objingTarget);
            }
        }
    }

    @objid ("c2285117-5f08-4cbe-8e05-821ba83eb73e")
    private void setRealizingMessage(InformationFlow objingElt) {
        for (Object ecoreMsg : this.ecoreElement.getRealizingMessages()) {
            Object objingMsg = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)ecoreMsg);
            if (objingMsg instanceof Message)
                objingElt.getRealizingMessage().add((Message) objingMsg);
        }
    }

    @objid ("15e2d9dd-9226-4fb3-bc91-6fd0741d51b4")
    private void setRealizingActivityEdge(InformationFlow objingElt) {
        for (Object ecoreEdge : this.ecoreElement.getRealizingActivityEdges()) {
            Object objingEdge = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)ecoreEdge);
            if (objingEdge instanceof ActivityEdge)
                objingElt.getRealizingActivityEdge().add((ActivityEdge)objingEdge);
        }
    }

    @objid ("6bdfdc96-12db-45d1-9a0e-5448f008ad52")
    private void setConveyed(InformationFlow objingElt) {
        for (Object ecoreConveyed : this.ecoreElement.getConveyeds()) {
            Object objingConveyed = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)ecoreConveyed);
            if (objingConveyed instanceof Classifier)
                objingElt.getConveyed().add((Classifier)objingConveyed);
        }
    }

    @objid ("0d7b22de-f4ca-4847-b953-b3e8694c50c4")
    private void setRealizingAssociation(InformationFlow objingElt) {
        for (Object ecoreRealizingAssoc : this.ecoreElement.getRealizations()) {
            Object realization = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)ecoreRealizingAssoc);
            if (realization instanceof Association)
                objingElt.setChannel(((Association)realization).getEnd().get(0));
        }
    }

    @objid ("57c9149e-c598-4f01-bb9b-e7fbc2f817e3")
    private void checkSourceAndTarget(InformationFlow objingElt) {
        List<UmlModelElement> sources = objingElt.getInformationSource();
        List<UmlModelElement> targets = objingElt.getInformationSource();
        if ((sources.size() == 0) || (targets.size() == 0))
            objingElt.delete();
    }

}
