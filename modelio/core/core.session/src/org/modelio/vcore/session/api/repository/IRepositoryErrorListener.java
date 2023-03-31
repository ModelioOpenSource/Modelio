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
package org.modelio.vcore.session.api.repository;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Listener that is fired when an error occur on a repository.
 */
@objid ("ab3fc20b-d65b-11e1-adbb-001ec947ccaf")
public interface IRepositoryErrorListener {
    /**
     * Called when a recoverable error occurs.
     * @param repository the repository where the error occured
     * @param e the error
     */
    @objid ("0d225409-d66d-11e1-adbb-001ec947ccaf")
    void onWarning(IRepository repository, Throwable e);

    /**
     * Called when an error prevents the repository from working.
     * @param repository the repository where the error occured
     * @param e the error
     */
    @objid ("0d22540d-d66d-11e1-adbb-001ec947ccaf")
    void onError(IRepository repository, Throwable e);
}

