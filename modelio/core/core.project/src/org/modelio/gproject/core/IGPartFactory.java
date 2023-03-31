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
package org.modelio.gproject.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.GProjectPartDescriptor;

/**
 * Interface used to instantiate a {@link IGPart} from a part descriptor.
 */
@objid ("cde8bde6-d1b0-425e-8872-e5c841d0ba99")
public interface IGPartFactory {
    /**
     * Instantiate a {@link IGPart} from a part descriptor.
     * @param d The fragment descriptor
     * @return a project part instance.
     */
    @objid ("7ee82572-d729-4494-bb3a-d2c132ec9737")
    IGPart instantiate(GProjectPartDescriptor d);

    /**
     * Tells whether the factory supports the given part type.
     * @return <code>true</code> if the given type is supported by the factory, else <code>false</code>
     */
    @objid ("2efac47c-f431-4c62-b79a-6e9a0a3236cc")
    boolean supports(GProjectPartDescriptor d);
}

