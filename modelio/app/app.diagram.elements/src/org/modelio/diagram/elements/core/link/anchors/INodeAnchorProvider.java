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
package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

/**
 * Interface for helper used by AbstractNodeEditPart to get connection anchors from connections or requests.
 * 
 * @since 5.0.2
 */
@objid ("fb8b0712-79b5-4161-b89f-f083a0427f3d")
public interface INodeAnchorProvider {
    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>source</i> rectangular node. The NodeEditPart is
     * the {@link ConnectionEditPart#getSource() source} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not
     * depend on anything all.
     * @param nodeEditPart The rectangular node to anchor from
     * @param connEditpart The connection to anchor from.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("ad64dca0-148e-4ed7-a536-a0892a87012b")
    ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart);

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>target</i> rectangular node. The NodeEditPart is
     * the {@link ConnectionEditPart#getTarget() source} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not
     * depend on anything all.
     * @param nodeEditPart The rectangular node to anchor to
     * @param connEditpart The connection to anchor to.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("35355cd4-ac77-4baf-9a6e-9e75041baed8")
    ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart);

    /**
     * Create a serializable anchor model from the given anchor.
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("64d8ac26-5011-4993-b215-e24f09252fb6")
    Object createAnchorModel(final ConnectionAnchor anchor);

    /**
     * Returns the <i>source</i> <code>ConnectionAnchor</code> for the specified Request on the given node. The returned
     * ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a
     * {@link org.eclipse.gef.requests.LocationRequest}, which provides the current mouse location.
     * @param nodeEditPart The rectangular node to anchor from
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("dc8078b7-752e-47cc-8c04-5d6b58a5693d")
    ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final Request request);

    /**
     * Returns the <i>target</i> <code>ConnectionAnchor</code> for the specified Request on the given node. The returned
     * ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a
     * {@link org.eclipse.gef.requests.LocationRequest}, which provides the current mouse location.
     * @param nodeEditPart The rectangular node to anchor from
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("cd0dcfe6-ac4c-4c4d-9cc8-b3194492e578")
    ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final Request request);

}
