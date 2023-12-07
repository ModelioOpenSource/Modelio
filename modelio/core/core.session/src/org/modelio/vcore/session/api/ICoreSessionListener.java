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
package org.modelio.vcore.session.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Listener for events on {@link ICoreSession} life cycle.
 * 
 * @since 5.4.0
 */
@objid ("1367eb68-0844-4050-92e5-3171cb567c42")
public interface ICoreSessionListener {
    /**
     * Called when a session is being closed.
     * <p>
     * The session is not accessible anymore.
     * Thrown exceptions will be logged and ignored.
     * @param session the session being closed.
     */
    @objid ("076946b0-7baa-4731-8c09-91837a333eeb")
    void sessionClosed(ICoreSession session);
}

