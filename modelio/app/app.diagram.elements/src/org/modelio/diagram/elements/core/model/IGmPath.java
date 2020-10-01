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

package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Connection path model.
 */
@objid ("8084e12e-1dec-11e2-8cad-001ec947c8cc")
public interface IGmPath extends IPersistent {
    /**
     * @return The connection routing mode.
     */
    @objid ("8084e130-1dec-11e2-8cad-001ec947c8cc")
    ConnectionRouterId getRouterKind();

    /**
     * @param routerKind The connection routing mode.
     */
    @objid ("8084e133-1dec-11e2-8cad-001ec947c8cc")
    void setRouterKind(final ConnectionRouterId routerKind);

    /**
     * Get the source anchor model. Must be serializable.
     * 
     * @return the source anchor model.
     */
    @objid ("8084e137-1dec-11e2-8cad-001ec947c8cc")
    Object getSourceAnchor();

    /**
     * Get the target anchor model. Must be serializable.
     * 
     * @return the target anchore model.
     */
    @objid ("8084e13a-1dec-11e2-8cad-001ec947c8cc")
    Object getTargetAnchor();

    /**
     * Set the source anchor model.
     * 
     * @param sourceAnchor the source anchor model.
     */
    @objid ("8084e13d-1dec-11e2-8cad-001ec947c8cc")
    void setSourceAnchor(final Object sourceAnchor);

    /**
     * Set the target anchor model.
     * 
     * @param targetAnchor the target anchor model.
     */
    @objid ("8084e141-1dec-11e2-8cad-001ec947c8cc")
    void setTargetAnchor(final Object targetAnchor);

    /**
     * Set the path data model.
     * <p>
     * The path data model is a serializable form of the draw2d routing constraint.
     * 
     * @param pathData the path data model.
     */
    @objid ("8084e145-1dec-11e2-8cad-001ec947c8cc")
    void setPathData(final Object pathData);

    /**
     * Get the path data model.
     * <p>
     * The path data model is a serializable form of the draw2d routing constraint.
     * 
     * @return the path data model.
     */
    @objid ("8084e149-1dec-11e2-8cad-001ec947c8cc")
    Object getPathData();

    /**
     * Get the source side rake.
     * 
     * @return the source side rake.
     */
    @objid ("8084e14c-1dec-11e2-8cad-001ec947c8cc")
    IGmLinkRake getSourceRake();

    /**
     * Get the target side rake.
     * 
     * @return the target side rake.
     */
    @objid ("8084e14f-1dec-11e2-8cad-001ec947c8cc")
    IGmLinkRake getTargetRake();

    /**
     * Set the source side rake.
     * 
     * @param value the source side rake.
     */
    @objid ("8084e152-1dec-11e2-8cad-001ec947c8cc")
    void setSourceRake(final IGmLinkRake value);

    /**
     * Set the target side rake.
     * 
     * @param value the target side rake.
     */
    @objid ("8084e156-1dec-11e2-8cad-001ec947c8cc")
    void setTargetRake(final IGmLinkRake value);

}
