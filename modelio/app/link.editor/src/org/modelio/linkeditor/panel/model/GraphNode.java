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

package org.modelio.linkeditor.panel.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.Node;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Node in the LinkEditor that represents an element.
 */
@objid ("1bb03377-5e33-11e2-b81d-002564c97630")
public class GraphNode extends Node {
    @objid ("1bb0337b-5e33-11e2-b81d-002564c97630")
    private boolean isCentral = false;

    /**
     * Margin used at the top and bottom of the node (in pixels).
     */
    @objid ("1bb0337f-5e33-11e2-b81d-002564c97630")
    public static final int MARGIN_HEIGHT = 1;

    /**
     * Margin used at the left and right of the node (in pixels).
     */
    @objid ("1bb03382-5e33-11e2-b81d-002564c97630")
    public static final int MARGIN_WIDTH = 1;

    /**
     * Spacing between the metaclass icon and the label (in pixels).
     */
    @objid ("1bb03385-5e33-11e2-b81d-002564c97630")
    public static final int VERTICAL_SPACING = 1;

    /**
     * Actual Width of the node. Should be initialized before creating any Node.
     */
    @objid ("1bb03388-5e33-11e2-b81d-002564c97630")
    public static int WIDTH;

    /**
     * Actual Height of the node. Should be initialized before creating any Node.
     */
    @objid ("1bb0338a-5e33-11e2-b81d-002564c97630")
    public static int HEIGHT;

    /**
     * Creates a node representing the passed element.
     * @param element the element this node will represent.
     */
    @objid ("1bb0338c-5e33-11e2-b81d-002564c97630")
    public GraphNode(final MObject element) {
        super(element);
        this.width = GraphNode.WIDTH;
        this.height = GraphNode.HEIGHT;
    }

    /**
     * Returns this GraphNode data casted as an MObject.
     * @return this GraphNode data casted as an MObject.
     */
    @objid ("1bb03391-5e33-11e2-b81d-002564c97630")
    public MObject getData() {
        return (MObject) this.data;
    }

    /**
     * Returns whether this node is the central one.
     * @return <code>true</code> if this node is the central one, <code>false</code> otherwise.
     */
    @objid ("1bb03396-5e33-11e2-b81d-002564c97630")
    public boolean isCentral() {
        return this.isCentral;
    }

    /**
     * Sets whether this node is the central one.
     * @param isCentral <code>true</code> if this node is the central one, <code>false</code> otherwise.
     */
    @objid ("1bb0339b-5e33-11e2-b81d-002564c97630")
    public void setCentral(final boolean isCentral) {
        this.isCentral = isCentral;
    }

}
