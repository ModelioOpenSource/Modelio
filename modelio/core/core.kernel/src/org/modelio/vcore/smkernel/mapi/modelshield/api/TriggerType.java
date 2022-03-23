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
package org.modelio.vcore.smkernel.mapi.modelshield.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("002804f8-0000-0555-0000-000000000000")
public enum TriggerType {
    @objid ("00260f84-2839-1f63-8729-001ec947cd2a")
    Update,
    @objid ("002618b2-2839-1f63-8729-001ec947cd2a")
    Create,
    @objid ("002804f8-0000-0557-0000-000000000000")
    DeleteTrigger,
    @objid ("008754e2-305a-1f63-8729-001ec947cd2a")
    Move,
    @objid ("00281d48-0000-27a6-0000-000000000000")
    ReorderTrigger,
    @objid ("00281ec8-0000-0034-0000-000000000000")
    AnyTrigger;

}
