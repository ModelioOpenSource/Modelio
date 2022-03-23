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
@objid ("e604b4d7-4673-4af3-8182-33cfe2af7463")
public class FullResetLinkHandler extends AbstractLinkHandler {
    /**
     * Execute the command.
     * @param selection the current diagram selection.
     */
    @objid ("4e35936e-daf4-4f5f-b1ca-7174df4ad493")
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
    @objid ("5aff29b0-147e-4082-92b1-42be572f4733")
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

    @objid ("6508043f-d8d1-4c8d-80b4-85bfea0bfceb")
    private static class FullResetLinkCommand extends Command {
        @objid ("8daba2ee-a832-4e10-bed4-d3f649e43df2")
        private List<LinkEditPart> linkEditPaths;

        @objid ("0b1f4764-2208-489f-bc74-30e4c439fddf")
        public  FullResetLinkCommand(List<LinkEditPart> linkEditPaths) {
            this.linkEditPaths = linkEditPaths;
        }

        @objid ("498f2fd8-29c5-45d6-b1fd-6f4a920a5c3e")
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
        @objid ("76e83002-6b31-4121-bb1d-548d598fa8ca")
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

        @objid ("3c6cdb18-6681-458b-b476-abc44d738e17")
        @Override
        public boolean canExecute() {
            return !this.linkEditPaths.isEmpty();
        }

        /**
         * Keep layout data and locally defined style keys to re-apply them after masking/unmasking an element.
         */
        @objid ("fcd7b7c2-c539-44e2-96f4-782b0f5461b5")
        private class UnmaskState {
            @objid ("a697c6f8-af85-4325-a3e3-27770742407e")
            private Map<StyleKey, Object> localValues = new HashMap<>();

            @objid ("0e578891-3510-441e-9550-34036e2584eb")
            private Object layoutData;

            @objid ("a7ea6a52-404a-4c31-ab3e-bf4cb248e2a8")
            private IGmDiagram diagram;

            @objid ("6372f895-ee18-4508-94e4-5d2e68feb8ef")
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

            @objid ("e2bac1c5-46f8-44e9-b38c-db5df3b7f01e")
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
