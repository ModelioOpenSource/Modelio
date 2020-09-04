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

package org.modelio.diagram.editor.activity.elements.partitioncontainer;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;

/**
 * Specific command that resize all Partitions at once.
 * 
 * @author fpoyer
 */
@objid ("2b37593b-55b6-11e2-877f-002564c97630")
public class ResizePartitionsCommand extends Command {
    @objid ("2b37593e-55b6-11e2-877f-002564c97630")
    private Map<GmNodeModel, Integer> newConstraints;

    @objid ("2b37593d-55b6-11e2-877f-002564c97630")
    private GmPartitionContainer container;

    /**
     * Constructor.
     * @param container the partition container.
     */
    @objid ("2b38df9a-55b6-11e2-877f-002564c97630")
    public ResizePartitionsCommand(GmPartitionContainer container) {
        this.container = container;
    }

    /**
     * Set the constraints that must be changed.
     * @param newConstraints the constraints that must be changed.
     */
    @objid ("2b38df9e-55b6-11e2-877f-002564c97630")
    public void setNewConstraints(Map<GmNodeModel, Integer> newConstraints) {
        this.newConstraints = newConstraints;
    }

    @objid ("2b38dfa6-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        for (GmNodeModel child : this.container.getChildren()) {
            Integer i = this.newConstraints.get(child);
            if (i != null)
                child.setLayoutData(i);
        }
    }

    @objid ("2b38dfa9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        return (MTools.getAuthTool().canModify(this.container.getDiagram().getRelatedElement()));
    }

}
