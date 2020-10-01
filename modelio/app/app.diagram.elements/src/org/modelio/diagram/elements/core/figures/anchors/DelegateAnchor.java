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

package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchorBase;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * Anchor that delegates all the work to another anchor.
 * <p>
 * The delegate anchor can be changed at any time.
 * 
 * @author cmarin
 */
@objid ("7f4eef9a-1dec-11e2-8cad-001ec947c8cc")
public class DelegateAnchor extends ConnectionAnchorBase implements ISlidableAnchor, AnchorListener {
    @objid ("ccc05b51-3f9c-4373-94f6-b9a7bacab0d7")
    private ConnectionAnchor delegate;

    @objid ("7f5151bd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void anchorMoved(ConnectionAnchor anchor) {
        // fires own listeners when the delgate anchor changes.
        fireAnchorMoved();
    }

    /**
     * Get the real anchor that does the work.
     * 
     * @return the real anchor.
     */
    @objid ("7f5151c3-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionAnchor getDelegate() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.delegate;
    }

    @objid ("7f5151ca-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(Point reference) {
        return this.delegate.getLocation(reference);
    }

    @objid ("7f5151d4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure getOwner() {
        return this.delegate.getOwner();
    }

    @objid ("7f5151db-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        return this.delegate.getReferencePoint();
    }

    /**
     * Set the connection anchor that does the real work.
     * 
     * @param delegate the new real anchor.
     */
    @objid ("7f5151e2-1dec-11e2-8cad-001ec947c8cc")
    public void setDelegate(ConnectionAnchor delegate) {
        // Do nothing if old and new anchors are equivalent.
        if (delegate != null && delegate.equals(this.delegate))
            return;
        
        if (this.delegate != null)
            this.delegate.removeAnchorListener(this);
        
        this.delegate = delegate;
        
        if (this.delegate != null) {
            this.delegate.addAnchorListener(this);
        }
    }

    @objid ("7f5151e8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLocation(Point newlocation) {
        if (this.delegate instanceof ISlidableAnchor) {
            ((ISlidableAnchor) this.delegate).setLocation(newlocation);
        }
    }

}
