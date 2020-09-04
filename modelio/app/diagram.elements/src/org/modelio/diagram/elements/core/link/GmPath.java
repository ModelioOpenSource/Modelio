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

package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmLinkRake;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Unique implementation of {@link IGmPath}.
 */
@objid ("8027e567-1dec-11e2-8cad-001ec947c8cc")
public class GmPath implements IGmPath {
    @objid ("8027e56f-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("91d25c63-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId routerKind = null;

    @objid ("8027e569-1dec-11e2-8cad-001ec947c8cc")
    private Object pathData = null;

    @objid ("8027e56a-1dec-11e2-8cad-001ec947c8cc")
    private Object sourceAnchor;

    @objid ("8027e56b-1dec-11e2-8cad-001ec947c8cc")
    private Object targetAnchor;

    @objid ("8027e56d-1dec-11e2-8cad-001ec947c8cc")
    private IGmLinkRake targetRake;

    @objid ("8027e56e-1dec-11e2-8cad-001ec947c8cc")
    private IGmLinkRake sourceRake;

    @objid ("8027e571-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getPathData() {
        return this.pathData;
    }

    @objid ("8027e576-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setPathData(final Object pathData) {
        this.pathData = pathData;
    }

    @objid ("8027e57b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getSourceAnchor() {
        return this.sourceAnchor;
    }

    @objid ("8027e580-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setSourceAnchor(final Object sourceAnchor) {
        this.sourceAnchor = sourceAnchor;
    }

    @objid ("8027e585-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getTargetAnchor() {
        return this.targetAnchor;
    }

    @objid ("8027e58a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTargetAnchor(final Object targetAnchor) {
        this.targetAnchor = targetAnchor;
    }

    @objid ("8027e58f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(final IDiagramWriter out) {
        return false;
    }

    @objid ("8027e596-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(final IDiagramReader in) {
        this.sourceAnchor = in.readProperty("SourceAnchor");
        this.targetAnchor = in.readProperty("DestAnchor");
        this.pathData = in.readProperty("PathData");
        this.routerKind = (ConnectionRouterId) in.readProperty("RouterKind");
        this.sourceRake = (IGmLinkRake) in.readProperty("sourceRake");
        this.targetRake = (IGmLinkRake) in.readProperty("targetRake");
    }

    @objid ("8027e59b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(final IDiagramWriter out) {
        out.writeProperty("SourceAnchor", this.sourceAnchor);
        out.writeProperty("DestAnchor", this.targetAnchor);
        out.writeProperty("PathData", this.pathData);
        out.writeProperty("RouterKind", this.routerKind);
        out.writeProperty("sourceRake", this.sourceRake);
        out.writeProperty("targetRake", this.targetRake);
    }

    /**
     * Default constructor.
     */
    @objid ("8027e5a0-1dec-11e2-8cad-001ec947c8cc")
    public GmPath() {
    }

    /**
     * Copy constructor.
     * @param path the path to copy.
     */
    @objid ("8027e5a3-1dec-11e2-8cad-001ec947c8cc")
    public GmPath(final IGmPath path) {
        this.pathData = path.getPathData();
        this.routerKind = path.getRouterKind();
        this.sourceAnchor = path.getSourceAnchor();
        this.targetAnchor = path.getTargetAnchor();
        this.sourceRake = path.getSourceRake();
        this.targetRake = path.getTargetRake();
    }

    /**
     * Get the link connection routing mode.
     * @return The link connection routing mode.
     */
    @objid ("802a47bb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionRouterId getRouterKind() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.routerKind;
    }

    /**
     * Set the link connection routing mode.
     * @param routerKind The new link connection routing mode.
     */
    @objid ("802a47c1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setRouterKind(final ConnectionRouterId routerKind) {
        // Automatically generated method. Please delete this comment before entering specific code.
        assert (routerKind != null);
        this.routerKind = routerKind;
    }

    /**
     * Get the target side rake if the link is raked.
     * @return the target side rake or <code>null</code>.
     */
    @objid ("802a47c7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IGmLinkRake getTargetRake() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.targetRake;
    }

    @objid ("802a47cd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTargetRake(final IGmLinkRake value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.targetRake = value;
    }

    /**
     * Get the source side rake if the link is raked on its source side.
     * @return the source side rake or <code>null</code>.
     */
    @objid ("802a47d2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IGmLinkRake getSourceRake() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sourceRake;
    }

    @objid ("802a47d8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setSourceRake(final IGmLinkRake value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.sourceRake = value;
    }

    @objid ("802a47dd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmPath.MAJOR_VERSION;
    }

    @objid ("0d5b8ff4-c629-4513-ad68-76900628cdc0")
    @Override
    public String toString() {
        return "GmPath [routerKind=" + this.routerKind + ", pathData=" + this.pathData + ", sourceAnchor=" + this.sourceAnchor + ", targetAnchor=" + this.targetAnchor + ", targetRake=" + this.targetRake + ", sourceRake=" + this.sourceRake + "]";
    }

}
