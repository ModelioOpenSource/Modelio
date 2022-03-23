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
package org.modelio.uml.statikdiagram.editor.elements.namespacinglink.redraw;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.GmCompositionLink;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that creates a relationship element between the 2 node model elements represented by the given EditPart.
 */
@objid ("35b2a59a-55b7-11e2-877f-002564c97630")
public class RedrawCompositionLinkCommand extends Command {
    @objid ("35b2a59c-55b7-11e2-877f-002564c97630")
    private IGmPath path;

    @objid ("35b2a5a0-55b7-11e2-877f-002564c97630")
    protected IGmLinkable sourceNode;

    @objid ("35b2a5a3-55b7-11e2-877f-002564c97630")
    protected IGmLinkable targetNode;

    @objid ("604dc0eb-5bd5-11e2-9e33-00137282c51b")
    protected RedrawCompositionLinkFactory context;

    /**
     * Command constructor
     * @param context Informations on the model element to create and or unmask.
     */
    @objid ("35b2a5a6-55b7-11e2-877f-002564c97630")
    public  RedrawCompositionLinkCommand(RedrawCompositionLinkFactory context) {
        this.context = context;
    }

    @objid ("35b2a5aa-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.sourceNode != null ? this.sourceNode.getDiagram() : null;
        boolean canModifyDiagram = gmDiagram != null ? MTools.getAuthTool().canModify(gmDiagram.getRelatedElement()) : false;
        boolean correctSource = this.sourceNode != null &&
                this.sourceNode.getRelatedElement().equals(this.context.getSourceElement());
        boolean correctTarget = this.targetNode != null &&
                this.targetNode.getRelatedElement().equals(this.context.getTargetElement());
        return canModifyDiagram && correctSource && correctTarget;
    }

    @objid ("35b2a5af-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Unmask the link
        final IGmDiagram gmDiagram = this.sourceNode.getDiagram();
        GmCompositionLink link = new GmCompositionLink(gmDiagram, this.sourceNode.getRepresentedRef());
        this.sourceNode.addStartingLink(link);
        this.targetNode.addEndingLink(link);
        link.setLayoutData(this.path);
        
    }

    /**
     * Sets the context
     * @param newContext the link creation context.
     */
    @objid ("35b2a5b2-55b7-11e2-877f-002564c97630")
    public void setContext(RedrawCompositionLinkFactory newContext) {
        this.context = newContext;
    }

    /**
     * Set the link source.
     * @param sourceNode the link source.
     */
    @objid ("35b2a5b6-55b7-11e2-877f-002564c97630")
    public void setSource(final IGmLinkable sourceNode) {
        this.sourceNode = sourceNode;
    }

    /**
     * Set the link destination.
     * @param targetNode the link destination.
     */
    @objid ("35b2a5bd-55b7-11e2-877f-002564c97630")
    public void setTarget(final IGmLinkable targetNode) {
        this.targetNode = targetNode;
    }

    /**
     * Set the path of the link.
     * @param path the link path.
     */
    @objid ("35b2a5c4-55b7-11e2-877f-002564c97630")
    public void setPath(final IGmPath path) {
        this.path = path;
    }

}
