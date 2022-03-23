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
package org.modelio.uml.sequencediagram.editor.elements.message;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.handles.ConnectionHandle;

/**
 * Selection handle specific to Message in Sequence diagram.
 * 
 * @author fpoyer
 */
@objid ("d9653107-55b6-11e2-877f-002564c97630")
public class MessageHandle extends ConnectionHandle {
    /**
     * Caches whether the handle is for the source, middle or target point.
     */
    @objid ("d9653109-55b6-11e2-877f-002564c97630")
    private int location;

    /**
     * Creates a new MessageHandle, sets its owner to <code>owner</code> , and sets its locator to a {@link ConnectionLocator}.
     * @param owner the ConnectionEditPart owner
     * @param location one of {@link ConnectionLocator#SOURCE}, {@link ConnectionLocator#MIDDLE} or {@link ConnectionLocator#TARGET}.
     */
    @objid ("d965310b-55b6-11e2-877f-002564c97630")
    public  MessageHandle(final ConnectionEditPart owner, final int location) {
        setOwner(owner);
        this.location = location;
        setLocator(new ConnectionLocator(getConnection(), location));
        
    }

    /**
     * Creates a new MessageHandle and sets its owner to <code>owner</code>. If the handle is fixed, it cannot be dragged.
     * @param owner the ConnectionEditPart owner
     * @param fixed if true, handle cannot be dragged.
     * @param location one of {@link ConnectionLocator#SOURCE}, {@link ConnectionLocator#MIDDLE} or {@link ConnectionLocator#TARGET}.
     */
    @objid ("d9653112-55b6-11e2-877f-002564c97630")
    public  MessageHandle(final ConnectionEditPart owner, final boolean fixed, final int location) {
        super(fixed);
        setOwner(owner);
        Assert.isTrue(location == ConnectionLocator.SOURCE || location == ConnectionLocator.TARGET);
        this.location = location;
        setLocator(new ConnectionLocator(getConnection(), location));
        
    }

    /**
     * Creates a new MessageHandle.
     * @param location one of {@link ConnectionLocator#SOURCE}, {@link ConnectionLocator#MIDDLE} or {@link ConnectionLocator#TARGET}.
     */
    @objid ("d965311b-55b6-11e2-877f-002564c97630")
    public  MessageHandle(final int location) {
        this.location = location;
    }

    /**
     * Return the endpoint handle.
     * @return the location, which is is either {@link ConnectionLocator#SOURCE}, {@link ConnectionLocator#MIDDLE} or {@link ConnectionLocator#TARGET}.
     * @since 3.5
     */
    @objid ("d9653120-55b6-11e2-877f-002564c97630")
    public int getEndPoint() {
        return this.location;
    }

    @objid ("d9653125-55b6-11e2-877f-002564c97630")
    @Override
    protected DragTracker createDragTracker() {
        if (isFixed())
            return null;
        MessageDragTracker tracker;
        tracker = new MessageDragTracker((ConnectionEditPart) getOwner());
        tracker.setCommandName(RequestConstants.REQ_MOVE);
        tracker.setDefaultCursor(Cursors.SIZENS);
        return tracker;
    }

}
