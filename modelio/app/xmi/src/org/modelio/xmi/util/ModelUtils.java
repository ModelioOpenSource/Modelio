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

package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("03547336-46c0-4583-9a17-10731be18c69")
public class ModelUtils {
    /**
     * @param assocEnd : the tested associationEnd
     * @return true if the association is tagged as "Owned by the classifier"
     */
    @objid ("6521d87a-0903-400d-93b8-ab1f0be67d61")
    public static boolean isOwnedByClassifier(final AssociationEnd assocEnd) {
        for (TaggedValue taggedValue : assocEnd.getTag()){
            if (taggedValue.getDefinition().getName().equals(IModelerModuleTagTypes.ASSOCIATIONEND_XMIISOWNEDBYCLASSIFIER)){
                for (TagParameter actual : taggedValue.getActual()){
                   return (actual.getValue().equals("true"));
                }
            }
        }
        return false;
    }

    /**
     * @param elt : the tested Modelio element
     * @return false is tagged as "NotExported"
     */
    @objid ("d120d588-b718-4dbc-a154-c9885df7c1e5")
    public static boolean mustBeExported(final MObject elt) {
        if (elt instanceof ModelElement){
            for (TaggedValue tag : ((ModelElement) elt).getTag()){
                if (tag.getDefinition().getName().equals(IModelerModuleTagTypes.MODELELEMENT_NOTEXPORTED))
                    return false;
            }
        }
        return true;
    }

    @objid ("22521643-688d-4ace-853b-ac24f4ae4f74")
    public static TaggedValue getTaggedValue(final ModelElement element, final String tagTypeName) {
        for (TaggedValue taggedValueTemp : element.getTag()){
            if (taggedValueTemp.getDefinition().getName().equals(tagTypeName)){
                return taggedValueTemp;
            }
        }
        return null;
    }

    @objid ("1c69e1e2-eddd-4802-91b0-349ba003a30a")
    public static void transitionMapping(org.eclipse.uml2.uml.OpaqueBehavior opaqueBehavior, Transition transOwner) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        StringBuffer content = new StringBuffer();
        for (String temp : opaqueBehavior.getBodies()){
            content.append(temp);
        }
        
        transOwner.setEffect(content.toString());
        
        for (org.eclipse.uml2.uml.Behavior beh : opaqueBehavior.getRedefinedBehaviors()){
            Object objBeh = revProp.getMappedElement(beh);
            if (objBeh instanceof Behavior){
                transOwner.setBehaviorEffect((Behavior) objBeh);
                break;
            }
        }
        
        org.eclipse.uml2.uml. BehavioralFeature feature = opaqueBehavior.getSpecification();
        if (feature != null){
            Object objFeature = revProp.getMappedElement(feature);
            if (objFeature instanceof Operation){
                transOwner.setProcessed((Operation)objFeature);
            }
        }
    }

    @objid ("5ea56988-9496-4690-bdd7-d030089f185c")
    public static InternalTransition stateMapping(org.eclipse.uml2.uml.OpaqueBehavior opaqueBehavior, State state, org.eclipse.uml2.uml.State ecoreState) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        InternalTransition intTrans = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInternalTransition();
        intTrans.setSComposed(state);
        
        if ((ecoreState.getExit() != null) 
                && (ecoreState.getExit().equals(opaqueBehavior))){
            intTrans.setReceivedEvents("Exit");
        }else if ((ecoreState.getEntry() != null) 
                && (ecoreState.getEntry().equals(opaqueBehavior))){
            intTrans.setReceivedEvents("Entry");
        }else if ((ecoreState.getDoActivity() != null) 
                && (ecoreState.getDoActivity().equals(opaqueBehavior))){
            intTrans.setReceivedEvents("Do");
        }
        
        StringBuffer content = new StringBuffer();
        for (String temp : opaqueBehavior.getBodies()){
            content.append(temp);
        }
        
        intTrans.setEffect(content.toString());
        
        for (org.eclipse.uml2.uml.Behavior beh : opaqueBehavior.getRedefinedBehaviors()){
            Object objBeh = revProp.getMappedElement(beh);
        if (objBeh instanceof Behavior){
                intTrans.setBehaviorEffect((Behavior) objBeh);
                break;
            }
        }
        
        org.eclipse.uml2.uml. BehavioralFeature feature = opaqueBehavior.getSpecification();
        if (feature != null){
            Object objFeature = revProp.getMappedElement(feature);
            if (objFeature instanceof Operation){
                intTrans.setProcessed((Operation)objFeature);
            }
        }
        return intTrans;
    }

    @objid ("8a377514-93e5-48c2-8634-e73c514278e9")
    public static boolean isOwnedByClassifier(final NaryAssociationEnd assocEnd) {
        for (TaggedValue taggedValue : assocEnd.getTag()){
            if (taggedValue.getDefinition().getName().equals(IModelerModuleTagTypes.ASSOCIATIONEND_XMIISOWNEDBYCLASSIFIER)){
                for (TagParameter actual : taggedValue.getActual()){
                   return (actual.getValue().equals("true"));
                }
            }
        }
        return false;
    }

}
