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

package org.modelio.api.impl.app.picking;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.picking.IPickingSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1d2cb330-686d-45a9-9ea8-65169b291ffb")
public class PickingSessionProxy implements IPickingSession {
    @objid ("49e93060-2f7c-4162-8092-30827ac8ef70")
    private org.modelio.app.core.picking.IPickingSession session;

    @objid ("25196193-1073-4148-8df4-70daa21354fd")
    public PickingSessionProxy(org.modelio.app.core.picking.IPickingSession session) {
        this.session = session;
    }

    @objid ("18ae9aa8-a6e9-4a90-8aaa-d312a9f00faf")
    @Override
    public boolean hoverElement(MObject target) {
        return this.session.hover(target);
    }

    @objid ("afe95326-48e9-4225-a137-b2ee8fcfd0ed")
    @Override
    public void selectElement(MObject target) {
        this.session.pick(target);
    }

    @objid ("5f0fac2d-7d2e-4a20-841a-dff4c0dda234")
    public org.modelio.app.core.picking.IPickingSession getSession() {
        return this.session;
    }

}
