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

package org.modelio.vcore.session.api.repository;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Helper used to handle storage error reporting.
 * <p>
 * This is used by {@link IRepository} to add/remove error handlers and to fire them
 * when a exception occurs.
 */
@objid ("23da3a1b-d660-11e1-adbb-001ec947ccaf")
public class StorageErrorSupport {
    @objid ("aaf3e32a-cb41-4790-81c0-1d063c275b47")
    private IRepository repository;

    @objid ("bab22e6f-cb35-4e82-8cba-ce6ad07cf892")
    private List<IRepositoryErrorListener> errorListeners = new ArrayList<>();

    /**
     * Creates a storage monitor support.
     * 
     * @param repository the handled repository.
     */
    @objid ("0d297b11-d66d-11e1-adbb-001ec947ccaf")
    public StorageErrorSupport(IRepository repository) {
        this.repository = repository;
    }

    /**
     * Add a listener that is fired when an I/O error occur on the repository.
     * 
     * @param listener the error listener.
     */
    @objid ("0d297b17-d66d-11e1-adbb-001ec947ccaf")
    public void addErrorListener(IRepositoryErrorListener listener) {
        this.errorListeners.add(listener);
    }

    /**
     * Remove an error listener.
     * 
     * @param listener the error listener to remove.
     */
    @objid ("0d297b1b-d66d-11e1-adbb-001ec947ccaf")
    public void removeErrorListener(IRepositoryErrorListener listener) {
        this.errorListeners.remove(listener);
    }

    /**
     * Fire a warning to repository listeners.
     * @param <T> the exception type
     * 
     * @param e the warning
     * @return the warning for convenience.
     */
    @objid ("0d297b1f-d66d-11e1-adbb-001ec947ccaf")
    public <T extends Throwable> T fireWarning(T e) {
        if (this.errorListeners.isEmpty()) {
            Log.warning(this.repository.toString()+" repository warning: ");
            Log.warning(e);
        } else {
            for (IRepositoryErrorListener l : this.errorListeners)
                l.onWarning(this.repository, e);
        }
        return e;
    }

    /**
     * Fire an error to repository listeners.
     * @param <T> the exception type
     * 
     * @param e the error
     * @return the error to conveniently allow the caller to throw it directly.
     */
    @objid ("0d297b25-d66d-11e1-adbb-001ec947ccaf")
    public <T extends Throwable> T fireError(T e) {
        if (this.errorListeners.isEmpty()) {
            Log.error(this.repository.toString()+" repository ERROR: ");
            Log.error(e);
        } else {
            for (IRepositoryErrorListener l : this.errorListeners)
                l.onError(this.repository, e);
        }
        return e;
    }

}
