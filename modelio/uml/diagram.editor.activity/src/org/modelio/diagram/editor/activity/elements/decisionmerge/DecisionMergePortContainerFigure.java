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

package org.modelio.diagram.editor.activity.elements.decisionmerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.portcontainer.PortContainerFigure;

/**
 * Specialization of the {@link PortContainerFigure} that draws a dash line between the Input Behaviour figure (when it is present) and the main node.
 * 
 * @author fpoyer
 */
@objid ("2a3b45bb-55b6-11e2-877f-002564c97630")
public class DecisionMergePortContainerFigure extends PortContainerFigure {
    @objid ("2a3b45bf-55b6-11e2-877f-002564c97630")
    private IFigure inputBehaviourFigure = null;

    @objid ("2a3b45c0-55b6-11e2-877f-002564c97630")
    private IFigure mainNodeFigure = null;

    @objid ("2a3b45c1-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        // Additional step: paint a dashed line between input behaviour figure (if any) and main node (if any).
        if (this.mainNodeFigure != null &&
                this.inputBehaviourFigure != null &&
                this.inputBehaviourFigure.isVisible()) {
            graphics.setLineStyle(SWT.LINE_DASH);
            graphics.drawLine(this.inputBehaviourFigure.getBounds().getCenter(),
                    this.mainNodeFigure.getBounds().getCenter());
        }
    }

    @objid ("2a3b45c5-55b6-11e2-877f-002564c97630")
    public void setInputBehaviourFigure(IFigure inputBehaviourFigure) {
        this.inputBehaviourFigure = inputBehaviourFigure;
    }

    @objid ("2a3b45c8-55b6-11e2-877f-002564c97630")
    public IFigure getInputBehaviourFigure() {
        return this.inputBehaviourFigure;
    }

    @objid ("2a3b45cc-55b6-11e2-877f-002564c97630")
    @Override
    public IFigure getMainNodeFigure() {
        return this.mainNodeFigure;
    }

    @objid ("2a3b45d0-55b6-11e2-877f-002564c97630")
    @Override
    public void setMainNodeFigure(IFigure mainNodeFigure) {
        super.setMainNodeFigure(mainNodeFigure);
        this.mainNodeFigure = mainNodeFigure;
    }

}
