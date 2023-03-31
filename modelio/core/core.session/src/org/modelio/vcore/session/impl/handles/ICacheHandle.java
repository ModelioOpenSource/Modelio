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
package org.modelio.vcore.session.impl.handles;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Metaobject helper interface to handle the model object cache.
 */
@objid ("006f9050-0d1e-1f20-85a5-001ec947cd2a")
public interface ICacheHandle {
    @objid ("0012bfc4-e639-1f34-b94f-001ec947cd2a")
    void createObject(final SmObjectImpl obj);

    @objid ("0012cbb8-e639-1f34-b94f-001ec947cd2a")
    void deleteObject(final SmObjectImpl obj);

    @objid ("0012d52c-e639-1f34-b94f-001ec947cd2a")
    void objUndeleted(final SmObjectImpl obj);
}

