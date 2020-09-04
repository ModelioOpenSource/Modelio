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

package org.modelio.diagram.editor.statik.elements.informationflowgroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Gets the information flows realized by a given link element.
 * 
 * @author cmarin
 */
@objid ("81629be1-1dec-11e2-8cad-001ec947c8cc")
public class GetInformationFlowExpert {
    @objid ("81629be3-1dec-11e2-8cad-001ec947c8cc")
    private static V impl = new V();

    /**
     * Get the information flows realized by the given element.
     * <p>
     * 
     * @param el a model link or a link end model.
     * @return The realized information flows.
     * @throws java.lang.IllegalArgumentException if the given element cannot realize information flows.
     */
    @objid ("81629be4-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("unchecked")
    public static Collection<InformationFlow> getRealizedFlows(final MObject el) throws IllegalArgumentException {
        final Object ret = el.accept(impl);
        
        if (ret == null)
            throw new IllegalArgumentException(el + " cannot realize information flows.");
        return (Collection<InformationFlow>) ret;
    }

    /**
     * Forbid instance creation.
     */
    @objid ("81629bee-1dec-11e2-8cad-001ec947c8cc")
    private GetInformationFlowExpert() {
    }

    @objid ("81629bf1-1dec-11e2-8cad-001ec947c8cc")
    private static class V extends DefaultModelVisitor {
        @objid ("81629bf3-1dec-11e2-8cad-001ec947c8cc")
        V() {
        }

        @objid ("81629bf5-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitAssociationEnd(final AssociationEnd role) {
            final Classifier src = role.getSource();
            
            final List<InformationFlow> flows = role.getRealizedInformationFlow();
            final List<InformationFlow> ret = new ArrayList<>(flows.size());
            
            // Binary association
            for (InformationFlow f : flows) {
                if (f.getInformationSource().contains(src))
                    ret.add(f);
            }
            return ret;
        }

        @objid ("81629bfc-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitLinkEnd(final LinkEnd role) {
            final Instance src = role.getSource();
            
            final List<InformationFlow> flows = role.getRealizedInformationFlow();
            final List<InformationFlow> ret = new ArrayList<>(flows.size());
            
            // Binary association
            for (InformationFlow f : flows) {
                if (f.getInformationSource().contains(src))
                    ret.add(f);
            }
            return ret;
        }

        @objid ("81629c03-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitMessage(final Message theMessage) {
            return theMessage.getRealizedInformationFlow();
        }

        @objid ("81629c0a-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitActivityEdge(final ActivityEdge theActivityEdge) {
            return theActivityEdge.getRealizedInformationFlow();
        }

        @objid ("81629c11-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitCommunicationMessage(final CommunicationMessage theCommunicationMessage) {
            return theCommunicationMessage.getRealizedInformationFlow();
        }

        @objid ("81629c18-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitModelTree(final ModelTree theModelTree) {
            return null;
        }

        @objid ("81629c1f-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitActivityNode(final ActivityNode theActivityNode) {
            return null;
        }

        @objid ("8164fe23-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitUmlModelElement(final UmlModelElement theUmlModelElement) {
            return null;
        }

        @objid ("cdeb35fb-d151-4baa-9957-395b65f792cb")
        @Override
        public Object visitNaryAssociationEnd(final NaryAssociationEnd role) {
            final Classifier src = role.getOwner();
            
            final List<InformationFlow> flows = role.getRealizedInformationFlow();
            final List<InformationFlow> ret = new ArrayList<>(flows.size());
            
            // Binary association
            for (InformationFlow f : flows) {
                if (f.getInformationSource().contains(src))
                    ret.add(f);
            }
            return ret;
        }

        @objid ("45b7f6f8-b683-4de3-97d7-3df65fa38446")
        @Override
        public Object visitNaryLinkEnd(final NaryLinkEnd role) {
            final Instance src = role.getSource();
            
            final List<InformationFlow> flows = role.getNaryLink().getRealizedInformationFlow();
            final List<InformationFlow> ret = new ArrayList<>(flows.size());
            
            // Binary association
            for (InformationFlow f : flows) {
                if (f.getInformationSource().contains(src))
                    ret.add(f);
            }
            return ret;
        }

    }

}
