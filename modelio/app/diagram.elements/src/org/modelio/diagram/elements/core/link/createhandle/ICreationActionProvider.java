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

package org.modelio.diagram.elements.core.link.createhandle;

import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.CreateConnectionRequest;

@objid ("f46a3f87-c3cf-488e-b555-e5199a6aebe5")
public interface ICreationActionProvider {
    @objid ("6134cd7f-aa60-4105-bc70-c66717decf1a")
    Stream<ICreationActionDescriptor> getPaletteActions(CreateConnectionRequest req);

}
