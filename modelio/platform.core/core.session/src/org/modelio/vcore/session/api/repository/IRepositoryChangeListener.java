/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vcore.session.api.repository;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Repository change listener.
 * <p>
 * Implementations can be registered with {@link IRepositorySupport#addRepositoryChangeListener(IRepositoryChangeListener)}.
 */
@objid ("44fd6f67-5bd7-4f6c-bac7-aa22b988dbd7")
public interface IRepositoryChangeListener {
    /**
     * Called when a repository has changed outside Modelio.
     * @param event the repository change event.
     */
    @objid ("4b690a37-faf3-4672-94d8-904708131bb1")
    void repositoryChanged(IRepositoryChangeEvent event);

}
