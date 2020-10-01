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

package org.modelio.diagram.elements.common.ghostnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;

/**
 * Very simple "ghost" figure.
 * 
 * @author fpoyer
 */
@objid ("7e4b0f48-1dec-11e2-8cad-001ec947c8cc")
public class GhostNodeFigure extends Figure {
    @objid ("da05f4e9-401e-4cd4-9d12-f9e406285c39")
    private Label metaclassLabel;

    @objid ("b232b775-01ac-41c4-a4f3-6b7af1b77ec9")
    private Label nameLabel;

    @objid ("da23fd28-89cf-42b4-ab0e-dc74952bd416")
    private Label idLabel;

    /**
     * Constructor
     */
    @objid ("7e4b0f55-1dec-11e2-8cad-001ec947c8cc")
    public GhostNodeFigure() {
        this.setOpaque(true);
        this.setForegroundColor(ColorConstants.gray);
        this.setBackgroundColor(ColorConstants.lightGray);
        
        // Setup the layout manager
        this.setLayoutManager(new BorderLayout());
        
        // metaclass label
        this.metaclassLabel = new Label();
        this.metaclassLabel.setOpaque(false);
        this.metaclassLabel.setBorder(new MarginBorder(4));
        
        this.add(this.metaclassLabel, BorderLayout.TOP);
        
        // name label
        this.nameLabel = new Label();
        this.nameLabel.setOpaque(false);
        this.nameLabel.setBorder(new MarginBorder(4));
        this.add(this.nameLabel, BorderLayout.CENTER);
        
        // id label
        this.idLabel = new Label();
        this.idLabel.setOpaque(false);
        this.idLabel.setBorder(new MarginBorder(4));
        this.add(this.idLabel, BorderLayout.BOTTOM);
        
        this.setBorder(new LineBorder(ColorConstants.lightGray));
    }

    @objid ("7e4b0f58-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(final Graphics graphics) {
        graphics.setAlpha(64);
        super.paintFigure(graphics);
        //graphics.restoreState();
    }

    /**
     * Sets the metaclass label of the figure.
     * 
     * @param label the new label.
     */
    @objid ("7e4b0f5f-1dec-11e2-8cad-001ec947c8cc")
    public void setMetaclassName(final String label) {
        this.metaclassLabel.setText(label);
    }

    /**
     * Sets the name label of the figure.
     * 
     * @param label the new label.
     */
    @objid ("7e4d716b-1dec-11e2-8cad-001ec947c8cc")
    public void setName(final String label) {
        this.nameLabel.setText(label);
    }

    /**
     * Sets the id label of the figure.
     * 
     * @param label the new label.
     */
    @objid ("7e4d7170-1dec-11e2-8cad-001ec947c8cc")
    public void setId(final String label) {
        this.idLabel.setText(label);
    }

}
