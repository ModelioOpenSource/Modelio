/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.diagram;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.DataFormatException;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class represents a DiagramGraphic of the 'node' kind.
 */
@objid ("b8a19140-644a-11e0-b650-001ec947cd2a")
public interface IDiagramNode extends IDiagramGraphic {
    /**
     * Resize this diagram node to fit its content.
     */
    @objid ("6d19c8c7-69b5-11e0-adf3-002564c97630")
    void fitToContent();

    /**
     * Return the node location and size as a Rectangle.
     * 
     * @return the node bounds.
     */
    @objid ("a4287dcc-0ecc-11e2-96c4-002564c97630")
    Rectangle getBounds();

    @objid ("a428a4de-0ecc-11e2-96c4-002564c97630")
    @Override
    MObject getElement();

    /**
     * Get the current fill color.
     * <br>
     * Might return <code>null</code> if the corresponding style key isn't defined.
     * 
     * @return The fill color.
     */
    @objid ("bfda3e2d-77e1-11e0-bfdb-002564c97630")
    String getFillColor();

    /**
     * Get the current fill mode.<br>
     * - 0 : The figure must be transparent.<br>
     * - 1 : The figure is filled with a solid color.<br>
     * - 2 : The figure is filled with a gradient.
     * 
     * @return The fill mode.
     */
    @objid ("bfda8c4e-77e1-11e0-bfdb-002564c97630")
    int getFillMode();

    /**
     * Get the first child node with the given role.
     * <p>
     * To be used when you know at most one element matches the role.
     * 
     * @param role the asked role
     * @return the asked nodes.
     * @since 3.5
     */
    @objid ("cf6bce30-3ee4-4cce-8334-a477098dc48c")
    IDiagramNode getFirstNode(Role role);

    /**
     * Get the current font, represented by font data.
     * <p>
     * A valid font data representation is a string of the form
     * <code><it>fontname</it>-<it>style</it>-<it>height</it></code> where
     * <code><it>fontname</it></code> is the name of a font,
     * <code><it>style</it></code> is a font style (one of
     * <code>"regular"</code>, <code>"bold"</code>,
     * <code>"italic"</code>, or <code>"bold italic"</code>)
     * and <code><it>height</it></code> is an integer representing the
     * font height. Example: <code>Times New Roman-bold-36</code>.
     * </p>
     * <br>
     * Might return <code>null</code> if the corresponding style key isn't defined.
     * 
     * @return The font.
     */
    @objid ("bfdb4f9e-77e1-11e0-bfdb-002564c97630")
    String getFont();

    /**
     * Get the current line color.
     * <br>
     * Might return <code>null</code> if the corresponding style key isn't defined.
     * 
     * @return The line color.
     */
    @objid ("bfdbc4ce-77e1-11e0-bfdb-002564c97630")
    String getLineColor();

    /**
     * Get the current line width.
     * 
     * @return The line width.
     */
    @objid ("bfdd245e-77e1-11e0-bfdb-002564c97630")
    int getLineWidth();

    /**
     * Return the list of children nodes of this node.
     * 
     * @return A list of nodes in any case, possibly an empty one. Never returns null
     */
    @objid ("6d1ab32d-69b5-11e0-adf3-002564c97630")
    List<IDiagramNode> getNodes();

//    /**
//     * Get the children nodes layouted as satellite of this node.
//     * @return the satellite nodes.
//     * @since 3.5
//     */
//    public Collection<IDiagramNode> getSatelliteNodes();
//
//    /**
//     * Get the child nodes layouted as port.
//     * @return the port nodes.
//     * @since 3.5
//     */
//    public Collection<IDiagramNode> getPortNodes();
//
//    /**
//     * Get the child node that represents the satellite label of this node.
//     * <p>
//     * The main satellite label, if any,  may be moved and resized.
//     * @return The main satellite label or <i>null</i>.
//     * @since 3.5
//     */
//    public IDiagramNode getMainSatelliteLabel() ;
    /**
     * Get the child nodes with the given role.
     * 
     * @param role the asked role
     * @return the asked nodes.
     * @since 3.5
     */
    @objid ("45d610ce-f2b9-4173-b866-a3eafa24c396")
    Collection<IDiagramNode> getNodes(Role role);

    /**
     * Return the location and size as a Rectangle for the node, including its satellites.
     * <p>
     * When no satellites are displayed, returns the same value as getBounds().
     * </p>
     * 
     * @return the node bounds.
     * @since 3.1
     */
    @objid ("9e0c3150-92ee-4bd8-948d-daff6a89082a")
    Rectangle getOverallBounds();

    /**
     * @return the parent node.
     */
    @objid ("6d1a650b-69b5-11e0-adf3-002564c97630")
    IDiagramGraphic getParent();

    /**
     * Get the current representation mode.<br>
     * - 0 : simple mode<br>
     * - 1 : structured mode<br>
     * - 2 : image mode
     * 
     * @return The representation mode.
     */
    @objid ("bfdd998e-77e1-11e0-bfdb-002564c97630")
    int getRepresentationMode();

    /**
     * Get the current text color.
     * <br>
     * Might return <code>null</code> if the corresponding style key isn't defined.
     * 
     * @return The text color.
     */
    @objid ("bfde0ebe-77e1-11e0-bfdb-002564c97630")
    String getTextColor();

    @objid ("6d1cfd2e-69b5-11e0-adf3-002564c97630")
    @Override
    boolean isPrimarySelected();

    @objid ("6d1cfd2c-69b5-11e0-adf3-002564c97630")
    @Override
    boolean isSelected();

    @objid ("6d1c60ea-69b5-11e0-adf3-002564c97630")
    @Override
    void mask();

    /**
     * This method changes both the location and the size of a node by changing its current bounds.<br>
     * Note however that the requested change might no be performed when some layout constraints are maintained by the
     * parent of the node (ie the requested changes are refused or adapted by the parent of the node).
     * 
     * @param newBounds the new node bounds
     */
    @objid ("a4294121-0ecc-11e2-96c4-002564c97630")
    void setBounds(final Rectangle newBounds);

    /**
     * Modify the current fill color.
     * 
     * @param value The new fill color.
     */
    @objid ("bfda653d-77e1-11e0-bfdb-002564c97630")
    void setFillColor(final String value);

    /**
     * Modify the current fill mode.
     * - 0 : The figure must be transparent.<br>
     * - 1 : The figure is filled with a solid color.<br>
     * - 2 : The figure is filled with a gradient.
     * 
     * @param value The new fill mode.
     */
    @objid ("bfdb288d-77e1-11e0-bfdb-002564c97630")
    void setFillMode(final int value);

    /**
     * Modify the current font.
     * <p>
     * This method fails if the value does not represent font data.
     * <p>
     * A valid font data representation is a string of the form
     * <code><it>fontname</it>-<it>style</it>-<it>height</it></code> where
     * <code><it>fontname</it></code> is the name of a font,
     * <code><it>style</it></code> is a font style (one of
     * <code>"regular"</code>, <code>"bold"</code>,
     * <code>"italic"</code>, or <code>"bold italic"</code>)
     * and <code><it>height</it></code> is an integer representing the
     * font height. Example: <code>Times New Roman-bold-36</code>.
     * </p>
     * 
     * @param value The new font.
     * @throws org.eclipse.jface.resource.DataFormatException if the given value does not represent font data
     */
    @objid ("bfdb9dbd-77e1-11e0-bfdb-002564c97630")
    void setFont(final String value) throws DataFormatException;

    /**
     * Modify the current line color.
     * 
     * @param value The new line color.
     */
    @objid ("bfdc12ed-77e1-11e0-bfdb-002564c97630")
    void setLineColor(final String value);

    /**
     * Modify the current line width.
     * 
     * @param value The new line width.
     */
    @objid ("bfdd727d-77e1-11e0-bfdb-002564c97630")
    void setLineWidth(final int value);

    /**
     * Change the location of the node, setting its new position to (x,y). Note however that the requested change might
     * no be performed when some layout constraints are maintained by the parent of the node (ie the requested change is
     * refused or adapted by the parent of the node).
     * 
     * @param x the new X position
     * @param y the new Y position
     * @return true if the change could be requested (different from 'performed' see note above)
     */
    @objid ("6d1c60eb-69b5-11e0-adf3-002564c97630")
    boolean setLocation(final int x, final int y);

    /**
     * Modify the current representation mode.<br>
     * - 0 : simple mode<br>
     * - 1 : structured mode<br>
     * - 2 : image mode
     * 
     * @param value The new representation mode.
     */
    @objid ("bfdde7ad-77e1-11e0-bfdb-002564c97630")
    void setRepresentationMode(final int value);

    /**
     * Change the size of the node to (width,height).<br>
     * Note however that the requested change might no be performed when some layout constraints are maintained by the
     * parent of the node (ie the requested change is refused or adapted by the parent of the node).
     * 
     * @param width the new width
     * @param height the new height
     * @return true if the change could be requested (different from 'performed' see note above)
     */
    @objid ("6d1caf0c-69b5-11e0-adf3-002564c97630")
    boolean setSize(final int width, final int height);

    /**
     * Modify the current text color.
     * 
     * @param value The new text color.
     */
    @objid ("bfde5cdd-77e1-11e0-bfdb-002564c97630")
    void setTextColor(final String value);

    /**
     * Role of a node within its {@link IDiagramNode#getParent() parent node}.
     * @since 3.5
     */
    @objid ("5e5d2469-e7b3-419d-9973-25af5828f77e")
    enum Role {
        /**
         * The child is contained in the parent node main figure.
         */
        INNER,
        /**
         * The child is the main floating label of the parent node.
         */
        LABEL,
        /**
         * The child is layouted as a Port of the parent node main figure.
         */
        PORT,
        /**
         * The child is a floating label.
         */
        SATELLITE;
    }

}
