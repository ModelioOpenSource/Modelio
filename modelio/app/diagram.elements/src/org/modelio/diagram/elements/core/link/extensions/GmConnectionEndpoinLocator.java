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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;

/**
 * Used to place GmNodes along the end point or starting point of a {@link GmLink}. <code>uDistance</code> represents
 * the distance from the Connection's owner to the IFigure. <code>vDistance</code> represents the distance from the node
 * to the GmLink itself.
 * 
 * @author cmarin
 */
@objid ("8001bfd2-1dec-11e2-8cad-001ec947c8cc")
public class GmConnectionEndpoinLocator extends GmAbstractLocator {
    /**
     * If <i>isEnd</i> is <code>true</code>, the location is relative to the Connection's end (or target) point. If
     * <i>isEnd</i> is <code>false</code> , the location is relative to the Connection's start (or source) point.
     */
    @objid ("8001bfd4-1dec-11e2-8cad-001ec947c8cc")
    private boolean end;

    @objid ("8001bfd6-1dec-11e2-8cad-001ec947c8cc")
    private int uDistance;

    @objid ("8001bfd7-1dec-11e2-8cad-001ec947c8cc")
    private int vDistance;

    @objid ("8001bfd8-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Returns the distance in pixels from the anchor's owner.
     * 
     * @return the offset distance from the endpoint figure
     */
    @objid ("8001bfda-1dec-11e2-8cad-001ec947c8cc")
    public int getUDistance() {
        return this.uDistance;
    }

    /**
     * Returns the distance in pixels from the connection
     * 
     * @return the offset from the connection itself
     */
    @objid ("8001bfdf-1dec-11e2-8cad-001ec947c8cc")
    public int getVDistance() {
        return this.vDistance;
    }

    /**
     * If <i>isEnd</i> is <code>true</code>, the location is relative to the Connection's end (or target) point. If
     * <i>isEnd</i> is <code>false</code> , the location is relative to the Connection's start (or source) point.
     * 
     * @return <code>true</code> is location is relative to target point.
     */
    @objid ("8001bfe4-1dec-11e2-8cad-001ec947c8cc")
    public boolean isEnd() {
        return this.end;
    }

    @objid ("8001bfe9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("8001bfef-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        super.read(in);
        
        this.uDistance = (Integer) in.readProperty("u");
        this.vDistance = (Integer) in.readProperty("v");
        this.end = (Boolean) in.readProperty("end");
    }

    /**
     * If <i>isEnd</i> is <code>true</code>, the location is relative to the Connection's end (or target) point. If
     * <i>isEnd</i> is <code>false</code> , the location is relative to the Connection's start (or source) point.
     * 
     * @param isEnd whether the location is relative to the target end or not.
     */
    @objid ("8001bff3-1dec-11e2-8cad-001ec947c8cc")
    public void setEnd(boolean isEnd) {
        this.end = isEnd;
    }

    /**
     * Sets the distance in pixels from the Connection's owner.
     * 
     * @param distance Number of pixels to place the ConnectionEndpointLocator from its owner.
     * @since 2.0
     */
    @objid ("8001bff7-1dec-11e2-8cad-001ec947c8cc")
    public void setUDistance(int distance) {
        this.uDistance = distance;
    }

    /**
     * Sets the distance in pixels from the Connection.
     * 
     * @param distance Number of pixels to place the ConnectionEndpointLocator from its Connection.
     * @since 2.0
     */
    @objid ("8001bffb-1dec-11e2-8cad-001ec947c8cc")
    public void setVDistance(int distance) {
        this.vDistance = distance;
    }

    @objid ("8001bfff-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        out.writeProperty("u", this.uDistance);
        out.writeProperty("v", this.vDistance);
        out.writeProperty("end", this.end);
    }

    @objid ("8001c003-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Copy constructor.
     * 
     * @param source the locator to copy.
     */
    @objid ("0cfbb86a-3f6d-4f9f-8214-66d2704a1a99")
    public GmConnectionEndpoinLocator(GmConnectionEndpoinLocator source) {
        super(source);
        
        this.end = source.end;
        this.uDistance = source.uDistance;
        this.vDistance = source.vDistance;
    }

    /**
     * Default constructor.
     * <p>
     * The figure will be located at the connection source.
     */
    @objid ("4c3c3f06-de5b-4694-8061-4a44b7bf9e8c")
    public GmConnectionEndpoinLocator() {
        super();
    }

    @objid ("bf9fa64a-38c0-480a-becf-603132e228f6")
    @Override
    public IGmLocator copy() {
        return new GmConnectionEndpoinLocator(this);
    }

}
