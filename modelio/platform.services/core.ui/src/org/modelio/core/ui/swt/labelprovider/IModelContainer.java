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

package org.modelio.core.ui.swt.labelprovider;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * A Typed container for the model browser used to organize the browser contents out of the model composition standard form (ie artificially grouping elements)
 * 
 * 
 * @param <T>
 * 
 * @Since 3.8 the returned owner is an <code>java.lang.Object</code> instead of a <code>MObject</code> for more versability of the interface.
 */
@objid ("010c8a6a-b90d-4f47-9c30-4540db2a10df")
public interface IModelContainer<T> {
    @objid ("e7549511-2cf7-45d6-b298-dac29d9d6dfd")
    Image getIcon();

    @objid ("6643d705-dc3d-43be-b6df-bbaa84967390")
    String getLabel();

    @objid ("2bec7305-aa12-4bfa-a9a6-a1c166bd1f8f")
    Object getOwner();

    @objid ("c561fed5-8677-4479-a27c-de0840e0064d")
    List<T> getContents();

}
