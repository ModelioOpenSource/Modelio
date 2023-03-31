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
package org.modelio.diagram.elements.core.link.path;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

@objid ("30d26247-349d-49e1-95b5-a43ed5857337")
public interface IConnectionHelperFactory {
    /**
     * Convert a path to another routing mode.
     * @param toConvert the path to convert.
     * @param mode the new mode
     * @param connection the connection for which to create a helper.
     * @return a converted path.
     */
    @objid ("78e6c384-3d48-40ea-a08c-ce813347291a")
    IConnectionHelper convert(IConnectionHelper toConvert, ConnectionRouterId mode, Connection connection);

    /**
     * Create a new connection helper from the given raw path data.
     * @see #createFromRawData(CreateBendedConnectionRequest, Connection) if you have a request.
     * @param rawData the data given by the creation tool in absolute coordinates.
     * @param connection the connection for which to create a helper.
     * @return the new connection helper.
     */
    @objid ("e03bb85a-f545-435e-bb23-d45f4f105525")
    IConnectionHelper createFromRawData(RawPathData rawData, Connection connection);

    /**
     * Create a new connection helper from the given request .
     * @see #getUpdatedConnectionHelper(CreateBendedConnectionRequest, Connection)
     * @param request a connection creation request
     * @param connection the connection figure (usually the feed back figure)
     * @return the new connection helper.
     */
    @objid ("8ecdb3c4-2b47-4ee1-aa9e-1bf8a96e1da1")
    IConnectionHelper createFromRawData(CreateBendedConnectionRequest request, Connection connection);

    /**
     * Create a new connection helper from the graphic model serialized data.
     * @param router The connection router to use
     * @param connectionEp the edit part whose model contains the serialized data.
     * @return a connection helper.
     */
    @objid ("718a349e-14e4-46e3-8ffa-f9f45d5aad14")
    IConnectionHelper createFromSerializedData(ConnectionRouterId router, ConnectionEditPart connectionEp, Connection connection);

    /**
     * Get the helper cached in the given request or create a new connection helper from the request and cache it in the request data .
     * @param request a connection creation request
     * @see #getUpdatedConnectionHelper(CreateBendedConnectionRequest, Connection)
     * @param connection the connection figure (usually the feed back figure)
     * @return the new connection helper.
     */
    @objid ("74a4b21e-18fb-4117-8328-0968e677f535")
    IConnectionHelper getUpdatedConnectionHelper(CreateBendedConnectionRequest req, Connection connection);
}

