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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("4fbfd4c1-49cd-40f5-878c-28a9c6fefa69")
public class EComponentRealization extends ENamedElement {
    @objid ("91fde119-abf5-4c41-9f94-b275eed41984")
    @Override
    public List<Element> createObjingElt() {
        List<Element> result = new ArrayList<>();
        
        org.eclipse.uml2.uml.ComponentRealization ecoreCR = (org.eclipse.uml2.uml.ComponentRealization) getEcoreElement();
        
        if (ecoreCR.getRealizingClassifiers() != null){
        
            org.eclipse.uml2.uml.Component component = ecoreCR.getAbstraction();
        
            if (component != null){
        
                Object objComponent = ReverseProperties.getInstance().getMappedElement(component);
                if (objComponent instanceof Component){
        
                    for (org.eclipse.uml2.uml.Classifier classifier : ecoreCR.getRealizingClassifiers()){
                        Object objClassifier = ReverseProperties.getInstance().getMappedElement(classifier);
                        if (objClassifier instanceof ModelElement){
                            ComponentRealization realization = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createComponentRealization();
                            result.add(realization);
                            realization.setAbstraction((Component)objComponent);
                            realization.setRealizingClassifier((Classifier) objClassifier);
                        }
        
                    }
                }
            }
        }
        return result;
    }

    @objid ("54c7a273-ec75-4b6c-bc50-5c14b5df785e")
    public EComponentRealization(org.eclipse.uml2.uml.ComponentRealization element) {
        super(element);
    }

}
