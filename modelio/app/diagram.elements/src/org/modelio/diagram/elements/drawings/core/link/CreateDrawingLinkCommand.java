/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.drawings.core.link;

import java.lang.reflect.InvocationTargetException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLinkable;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that creates a relationship element between the 2 node model elements represented by the given EditPart.
 */
@objid ("ea1083ec-20d2-416d-b028-7697f787bba3")
public class CreateDrawingLinkCommand extends Command {
    @objid ("32593a0c-2b94-453c-8c4d-1d8225292539")
    private String drawingIdentifier;

    @objid ("2708164e-036d-4e5c-8313-88f30efa5b06")
    private IGmPath path;

    @objid ("7a80e6ec-e172-4485-9765-af3a55789097")
    protected Class<? extends IGmDrawingLink> newLinkType;

    @objid ("59b35f31-8b5e-4b16-9819-38d8039520ce")
    protected IGmDrawingLinkable sourceNode;

    @objid ("a7a2438f-60bb-47bb-bdab-ed8fb9db1610")
    protected IGmDrawingLinkable targetNode;

    /**
     * Command constructor
     * 
     * @param newLinkType the class of the drawing link to create.
     * @param drawingIdentifier the drawing identifier
     */
    @objid ("3ed48b95-2df2-419f-99b0-03914a05b97e")
    public CreateDrawingLinkCommand(Class<? extends IGmDrawingLink> newLinkType, String drawingIdentifier) {
        this.newLinkType = newLinkType;
        this.drawingIdentifier = drawingIdentifier;
    }

    @objid ("e9f572f9-196e-4628-8392-81c23f402e10")
    @Override
    public boolean canExecute() {
        IGmDiagram sourceDiagram = this.sourceNode != null ? this.sourceNode.getDiagram() : null;
        IGmDiagram targetDiagram = this.targetNode != null ? this.targetNode.getDiagram() : null;
        
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = targetDiagram != null ? IGmDiagram.getCommonDiagramOwner(sourceDiagram, targetDiagram) : sourceDiagram;
        
        if (gmDiagram == null || !MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        
        if (sourceDiagram != null && targetDiagram != null && sourceDiagram != targetDiagram) {
            return false;
        }
        
        // All conditions are fulfilled
        return true;
    }

    @objid ("fdb4d6b8-32b8-4d4c-8691-d497cf564f44")
    @Override
    public void execute() {
        final IGmDiagram gmDiagram = this.sourceNode.getDiagram();
        
        IGmDrawingLink linkElement;
        
        try {
            linkElement = this.newLinkType.getConstructor(IGmDiagram.class, String.class)
                    .newInstance(gmDiagram, this.drawingIdentifier);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassCastException
                | NoSuchMethodException | SecurityException e) {
            // TODO : report in another way ?
            throw new RuntimeException(e.toString(), e);
        }
        
        this.sourceNode.addStartingDrawingLink(linkElement);
        this.targetNode.addEndingDrawingLink(linkElement);
        
        linkElement.setLayoutData(this.path);
    }

    /**
     * Set the link source.
     * 
     * @param sourceNode the link source.
     */
    @objid ("7b809b84-fce0-447e-b1a1-c207b02a4c6b")
    public void setSource(final IGmDrawingLinkable sourceNode) {
        this.sourceNode = sourceNode;
    }

    /**
     * Set the link destination.
     * 
     * @param targetNode the link destination.
     */
    @objid ("c404ec28-2049-4a14-9a97-35d196a17abd")
    public void setTarget(final IGmDrawingLinkable targetNode) {
        this.targetNode = targetNode;
    }

    /**
     * Set the path of the link.
     * 
     * @param path the link path.
     */
    @objid ("4fccf952-0bdd-4afc-95f9-9aa0a7be63ff")
    public void setPath(final IGmPath path) {
        this.path = path;
    }

}
