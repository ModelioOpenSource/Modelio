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
package org.modelio.vcore.model.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("65c33819-5841-4764-882d-8f65c212a580")
public interface IModelFactoryProvider {
    @objid ("40eef3d7-2639-4511-b2c2-50c1f7722e16")
    IModelFactory getFactory(ICoreSession session);
}

