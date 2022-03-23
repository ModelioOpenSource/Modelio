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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("ef924dc4-1d51-4c82-8501-c0f237f3fcdb")
public class OActivityParameterNode extends OObjectNode {
    @objid ("78ada1b0-5fa5-4030-b448-00980a9d8cec")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createActivityParameterNode();
    }

    @objid ("3ada1519-0198-4214-bab6-8e906513272b")
    public  OActivityParameterNode(ActivityParameterNode element) {
        super(element);
    }

    @objid ("bc46ad76-6041-4fe2-9519-65ba5db25f48")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("836454fa-ae83-42d4-9e84-bbe388969b5b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setRepresentedRealParameter((org.eclipse.uml2.uml.ActivityParameterNode) ecoreElt);
        
    }

    @objid ("2a59d76c-6869-4e00-9e3f-e3b54578c6e6")
    private void setRepresentedRealParameter(org.eclipse.uml2.uml.ActivityParameterNode node) {
        BehaviorParameter behaviorParam = ((ActivityParameterNode) getObjingElement())
                .getRepresentedRealParameter();
        if (behaviorParam != null) {
            Parameter objingParam = behaviorParam.getMapped();
            if (objingParam != null) {
                org.eclipse.uml2.uml.Element ecoreParam = GenerationProperties.getInstance().getMappedElement(objingParam);
                if (ecoreParam instanceof org.eclipse.uml2.uml.Parameter) {
                    node.setParameter((org.eclipse.uml2.uml.Parameter) ecoreParam);
                }
            }else{
                org.eclipse.uml2.uml.Element ecoreParam = GenerationProperties.getInstance().getMappedElement(behaviorParam);
                if (ecoreParam instanceof org.eclipse.uml2.uml.Parameter) {
                    node.setParameter((org.eclipse.uml2.uml.Parameter) ecoreParam);
                }
            }
        }
        
    }

}
