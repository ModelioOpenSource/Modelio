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

package org.modelio.vcore.smkernel.mapi.modelshield.api;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("00368f76-b7e0-1f61-8473-001ec947cd2a")
public interface IErrorReport {
    @objid ("0036984a-b7e0-1f61-8473-001ec947cd2a")
    void addEntry(final IModelError anEntry);

    @objid ("0036adee-b7e0-1f61-8473-001ec947cd2a")
    List<IModelError> getEntries();

    @objid ("0051b260-524f-1036-812a-001ec947cd2a")
    boolean isFailed();

}
