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
package org.modelio.diagram.editor.handlers.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Handler for the "Full reset link" contextual command.
 * <p>
 * Masks/unmasks the link again.
 * </p>
 */
@objid ("f3f2af71-b081-4672-abc0-43425965cc78")
public class FullResetLinkHandler extends AbstractLinkHandler {
    /**
     * Execute the command.
     * @param selection the current diagram selection.
     */
    @objid ("15ba4a94-73e7-4715-b1de-f84222b84673")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<LinkEditPart> linkEditPaths = SelectionHelper.toList(selection, LinkEditPart.class);
        linkEditPaths.get(0).getViewer().getEditDomain().getCommandStack().execute(new FullResetLinkCommand(linkEditPaths));
        
    }

    /**
     * Makes sure the selection contains only orthogonal links
     * @param selection the current diagram selection.
     * @return <code>true</code> if the handler can be executed.
     */
    @objid ("eee64018-ffd2-4290-af6a-ac65beb3a73a")
    @Override
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        // Call super
        if (!super.canExecute(selection)) {
            return false;
        }
        
        for (final LinkEditPart linkEditpart : SelectionHelper.toList(selection, LinkEditPart.class)) {
            final GmLink link = linkEditpart.getModel();
        
            // Deactivate on non-orthogonal links
            if (link.getPath().getRouterKind() != ConnectionRouterId.ORTHOGONAL) {
                return false;
            }
        }
        return true;
    }

    @objid ("aa893e21-2cf1-4931-ad88-b152f6c088fe")
    private static class FullResetLinkCommand extends Command {
        @objid ("13bc3ca3-f9a7-4b42-9829-953275220587")
        private List<LinkEditPart> linkEditPaths;

        @objid ("448a7034-14b7-4ebc-8366-45ee63cf0b32")
        public  FullResetLinkCommand(List<LinkEditPart> linkEditPaths) {
            this.linkEditPaths = linkEditPaths;
        }

        @objid ("36bddcf6-b443-49ba-b2b6-aaa33c1b4500")
        @Override
        public void execute() {
            EditPartViewer viewer = this.linkEditPaths.get(0).getViewer();
            
            List<MObject> links = new ArrayList<>();
            for (LinkEditPart linkEditPart : this.linkEditPaths) {
                // Mask the current link
                GmLink gmLink = linkEditPart.getModel();
                IGmDiagram diagram = gmLink.getDiagram();
                UnmaskState state = new UnmaskState(gmLink, null);
            
                MObject element = gmLink.getRepresentedElement();
                links.add(element);
            
                Map<MObject, UnmaskState> toUnmask = new HashMap<>();
                for (IGmLink startingLink : new ArrayList<>(gmLink.getStartingLinks())) {
                    MObject relatedElement = startingLink.getRelatedElement();
                    toUnmask.put(relatedElement, new UnmaskState((GmModel) startingLink, startingLink.getTo()));
                }
            
                for (IGmLink endingLink : new ArrayList<>(gmLink.getEndingLinks())) {
                    MObject relatedElement = endingLink.getRelatedElement();
                    toUnmask.put(relatedElement, new UnmaskState((GmModel) endingLink, endingLink.getFrom()));
                }
            
                // Mask the link
                gmLink.delete();
            
                // Unmask the link again to reset its path and anchors
                AbstractDiagramEditPart diagramEditPart = (AbstractDiagramEditPart) viewer.getEditPartRegistry().get(diagram);
                unmask(diagramEditPart, element, 0, 0);
                state.apply(element, diagram);
            
                for (Entry<MObject, UnmaskState> entry : toUnmask.entrySet()) {
                    MObject key = entry.getKey();
                    UnmaskState value = entry.getValue();
            
                    diagramEditPart = (AbstractDiagramEditPart) viewer.getEditPartRegistry().get(value.diagram);
            
                    unmask(diagramEditPart, key, 0, 0);
                    value.apply(key, diagram);
                }
            }
            
        }

        /**
         * Unmask an element in this viewer at the given coordinates.<br>
         * Uses a ModelElementDropRequest, to emulate a standard drag & drop of the element.
         * @param diagramEditPart the diagram to unmask the element on.
         * @param element the element to unmask.
         * @param x the x coordinate for the unmasking location.
         * @param y the y coordinate for the unmasking location.
         */
        @objid ("eb6a5b7f-696a-4ac0-bf7e-c289c2daae13")
        private void unmask(final AbstractDiagramEditPart diagramEditPart, final MObject element, final int x, final int y) {
            Point dropLocation = new Point(x, y);
            
            final ModelElementDropRequest req = new ModelElementDropRequest();
            req.setDroppedElements(new MObject[] { element });
            req.setLocation(dropLocation);
            
            EditPart targetEditPart = diagramEditPart.getTargetEditPart(req);
            if (targetEditPart != null) {
                Command com = targetEditPart.getCommand(req);
                if (com != null && com.canExecute()) {
                    targetEditPart.getViewer().getEditDomain().getCommandStack().execute(com);
                }
            }
            
        }

        @objid ("df2ba265-64ca-4808-ad50-ee5da0a5d96e")
        @Override
        public boolean canExecute() {
            return !this.linkEditPaths.isEmpty();
        }

        /**
         * Keep layout data and locally defined style keys to re-apply them after masking/unmasking an element.
         */
        @objid ("a82ea9fe-b1de-4c62-9c32-a93c9fce2721")
        private class UnmaskState {
            @objid ("50ede4df-1edd-49d8-832a-7b4d9ca1b5f1")
            private Map<StyleKey, Object> localValues = new HashMap<>();

            @objid ("19064247-4b42-45e8-b932-993f2cf42de5")
            private Object layoutData;

            @objid ("6801e173-696c-46c9-8b1c-be4a2a2966f7")
            private IGmDiagram diagram;

            @objid ("a7c02d79-2a6e-458f-91f5-ee0ee81812ec")
            public  UnmaskState(GmModel model, IGmLinkable end) {
                GmModel toUnmask;
                
                if (model.getRepresentedElement() == null && model instanceof IGmLink) {
                    toUnmask = (GmModel) end;
                    while (toUnmask != null && toUnmask.getRepresentedElement() == null) {
                        toUnmask = toUnmask.getParent();
                    }
                } else {
                    toUnmask = model;
                }
                
                if (toUnmask != null) {
                    this.diagram = toUnmask.getDiagram();
                
                    IStyle style = toUnmask.getPersistedStyle();
                    for (StyleKey key : style.getLocalKeys()) {
                        this.localValues.put(key, style.getProperty(key));
                    }
                    if (!(toUnmask instanceof IGmLink)) {
                        this.layoutData = toUnmask.getLayoutData();
                    }
                }
                
            }

            @objid ("add20657-b8fd-41a9-bebc-9a0b289a5b52")
            private void apply(MObject element, IGmDiagram context) {
                // Restore local properties
                for (GmModel gm : context.getAllGMRepresenting(new MRef(element))) {
                    IStyle newPersistedStyle = gm.getPersistedStyle();
                
                    for (Entry<StyleKey, Object> entry : this.localValues.entrySet()) {
                        newPersistedStyle.setProperty(entry.getKey(), entry.getValue());
                    }
                
                    if (this.layoutData != null) {
                        gm.setLayoutData(this.layoutData);
                    }
                
                    break;
                }
                
            }

        }

    }

}
