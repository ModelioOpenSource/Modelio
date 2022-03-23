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
package org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPartListener.Stub;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortContainerFigure;

@objid ("ce7874ad-d5f9-4c65-b419-4ff85dc077d9")
public abstract class SelectionHelperLinksEditPolicy extends GraphicalEditPolicy {
    @objid ("f2f669a8-4e50-4306-97eb-a2b6a3703998")
    private boolean onOff = false;

    @objid ("180b8774-dc8e-432f-943c-a24fb7c527c3")
    private EditPartListener selectionListener;

    @objid ("2f75b1b2-a560-4179-a06a-6ef80746f8d6")
    private List<PolylineConnection> focuslinks = new ArrayList<>();

    @objid ("32701abc-ae6d-4731-9072-0884174282b3")
    @Override
    public void activate() {
        super.activate();
        this.selectionListener = new SelectionListener(this);
        this.getHost().addEditPartListener(this.selectionListener);
        setSelectedState(getHost().getSelected() == EditPart.SELECTED || getHost().getSelected() == EditPart.SELECTED_PRIMARY);
        
    }

    @objid ("7eaf47d1-9804-4acf-adf0-beb5df940124")
    @Override
    public void deactivate() {
        this.getHost().removeEditPartListener(this.selectionListener);
        this.selectionListener = null;
        hideHelperLinks();
        super.deactivate();
        
    }

    /**
     * Define this method to return the pairs of edit parts that have to be linked by a highlight link
     * @param from : the current edit part for which highlight linbks are to be computed
     * @return a map of edit part lists. Each 'key' edit part will be used a link origin for a set of links towards the edit part listed in the map value for the 'key'.
     */
    @objid ("a237acfd-7a5e-4c82-86a2-7c8736a7b8e4")
    public abstract Map<EditPart, List<EditPart>> getLinkedParts(EditPart from);

    @objid ("06a4cd74-61e9-4338-a79b-c4484c4fde36")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (RequestConstants.REQ_SELECTION.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    @objid ("f469b639-dea3-4b93-a49c-c168540fb9ef")
    public void hideHelperLinks() {
        for (PolylineConnection l : this.focuslinks) {
            removeFeedback(l);
        }
        this.focuslinks.clear();
        
    }

    @objid ("5cbe53d3-f450-4514-b44a-7743b8c34222")
    public void showHelperLinks() {
        Map<EditPart, List<EditPart>> linkedParts = getLinkedParts(this.getHost());
        for (Entry<EditPart, List<EditPart>> entry : linkedParts.entrySet()) {
        
            IFigure sourceFigure = ((GraphicalEditPart) entry.getKey()).getFigure();
            if (sourceFigure instanceof PortContainerFigure) {
                List<?> children = ((PortContainerFigure) sourceFigure).getChildren();
                if (children.isEmpty()) {
                    return;
                }
                sourceFigure = (IFigure) children.get(0);
            }
            final ConnectionAnchor targetAnchor = new ChopboxAnchor(sourceFigure);
        
            for (EditPart linkedPart : entry.getValue()) {
                IFigure targetFigure = ((GraphicalEditPart) linkedPart).getFigure();
                if (targetFigure instanceof PortContainerFigure) {
                    targetFigure = (IFigure) ((PortContainerFigure) targetFigure).getChildren().get(0);
                }
                final ConnectionAnchor srcAnchor = new ChopboxAnchor(targetFigure);
                PolylineConnection focuslink = new PolylineConnection();
                focuslink.removeAllPoints();
                focuslink.setSourceAnchor(srcAnchor);
                focuslink.setTargetAnchor(targetAnchor);
                focuslink.setLineStyle(org.eclipse.swt.SWT.LINE_DOT);
                addFeedback(focuslink);
                this.focuslinks.add(focuslink);
            }
        }
        
    }

    @objid ("82c61b83-3921-4f55-a616-38f6566d4435")
    public void setSelectedState(boolean onOff) {
        if (onOff != this.onOff) {
            this.onOff = onOff;
            if (this.onOff) {
                showHelperLinks();
            } else {
                hideHelperLinks();
            }
        }
        
    }

    @objid ("b70f3bb2-1fda-42a3-ab8f-3b56d8dd54f2")
    private static class SelectionListener extends Stub {
        @objid ("91e9fc82-ac97-4c59-ad00-2976b4e9559a")
        private SelectionHelperLinksEditPolicy policy;

        @objid ("c6303654-5b06-4ddf-9625-17a0f8b3fd6a")
        public  SelectionListener(SelectionHelperLinksEditPolicy policy) {
            this.policy = policy;
        }

        @objid ("4f40fe5a-92ae-456a-b99a-6307a0c08b44")
        @Override
        public void selectedStateChanged(final EditPart part) {
            this.policy.setSelectedState(part.getSelected() == EditPart.SELECTED || part.getSelected() == EditPart.SELECTED_PRIMARY);
        }

    }

}
