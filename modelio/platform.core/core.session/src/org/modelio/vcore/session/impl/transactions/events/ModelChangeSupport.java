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

package org.modelio.vcore.session.impl.transactions.events;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeHandler;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IPersistentViewModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;

/**
 * Implementation of {@link IModelChangeSupport}.
 * <p>
 * This implementation is thread safe.
 */
@objid ("b1ad0108-19f4-11e2-8eb9-001ec947ccaf")
public class ModelChangeSupport implements IModelChangeSupport {
    @objid ("006ebf40-0d1e-1f20-85a5-001ec947cd2a")
    private final List<IModelChangeListener> modelChangeListeners = new CopyOnWriteArrayList<>();

    @objid ("006ec31e-0d1e-1f20-85a5-001ec947cd2a")
    private final List<IModelChangeHandler> modelChangeHandlers = new CopyOnWriteArrayList<>();

    @objid ("7d7cdcf0-1c43-11e2-8eb9-001ec947ccaf")
    private final List<IStatusChangeListener> statusListeners = new CopyOnWriteArrayList<>();

    @objid ("2aaf3ef1-4c8e-46e6-822c-c9384cce2efb")
    private final List<IPersistentViewModelChangeListener> persistentViewChangeHandlers = new CopyOnWriteArrayList<>();

    @objid ("006ece0e-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void addModelChangeHandler(final IModelChangeHandler aHandler) {
        if (!this.modelChangeHandlers.contains(aHandler)) {
            this.modelChangeHandlers.add(aHandler);
        }
    }

    @objid ("006ecea4-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void addModelChangeListener(final IModelChangeListener aListener) {
        if (!this.modelChangeListeners.contains(aListener)) {
            this.modelChangeListeners.add(aListener);
        }
    }

    @objid ("e53318d4-778f-49bf-8194-d39a80a0a37a")
    @Override
    public void addPersistentViewListener(IPersistentViewModelChangeListener aHandler) {
        if (!this.persistentViewChangeHandlers.contains(aHandler)) {
            this.persistentViewChangeHandlers.add(aHandler);
        }
    }

    @objid ("7d7cdcf4-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    public void addStatusChangeListener(final IStatusChangeListener aListener) {
        if (!this.statusListeners.contains(aListener)) {
            this.statusListeners.add(aListener);
        }
    }

    @objid ("0070892e-e3b9-1fd5-b969-001ec947cd2a")
    @Override
    public void removeModelChangeHandler(final IModelChangeHandler aListener) {
        this.modelChangeHandlers.remove(aListener);
    }

    @objid ("0070d708-e3b9-1fd5-b969-001ec947cd2a")
    @Override
    public void removeModelChangeListener(final IModelChangeListener aListener) {
        this.modelChangeListeners.remove(aListener);
    }

    @objid ("48a8549d-9c47-4548-a35a-ac681c0bf011")
    @Override
    public void removePersistentViewListener(IPersistentViewModelChangeListener aHandler) {
        this.persistentViewChangeHandlers.remove(aHandler);
    }

    @objid ("7d7cdcf8-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    public void removeStatusChangeListener(final IStatusChangeListener aListener) {
        this.statusListeners.remove(aListener);
    }

    /**
     * Fires model change listeners.
     * <p>
     * Transactions are forbidden for model change listeners, ie model change listeners are not allowed to modify the model
     * Exceptions thrown by listeners are caught, added to the log and ignored.
     * @param event the model change event.
     */
    @objid ("7d7cdcfc-1c43-11e2-8eb9-001ec947ccaf")
    public void fireModelChangeListeners(final IModelChangeEvent event) {
        for (IModelChangeListener listener : this.modelChangeListeners) {
            try {
                listener.modelChanged(event);
            } catch (RuntimeException | LinkageError e) {
                Log.warning(e);
            }
        }
    }

    /**
     * Fires status change listeners.
     * <p>
     * Transactions are forbidden for status change listeners, ie status change listeners are not allowed to modify the model.
     * Exceptions thrown by listeners are caught, added to the log and ignored.
     * @param event a status change event
     */
    @objid ("7d7cdd01-1c43-11e2-8eb9-001ec947ccaf")
    public void fireStatusChangeListeners(final IStatusChangeEvent event) {
        for (IStatusChangeListener listener : this.statusListeners) {
            try {
                listener.statusChanged(event);
            } catch (RuntimeException | LinkageError e) {
                Log.warning(e);
            }
        }
    }

    /**
     * Get the model change handlers.
     * <p>
     * Do not modify the returned list!
     * @return the model change handlers.
     */
    @objid ("056c9b92-ff9d-4e4a-8731-53d3a9ea8bfa")
    public List<IModelChangeHandler> getModelChangeHandlers() {
        return this.modelChangeHandlers;
    }

    /**
     * @return persistent view change listeners.
     */
    @objid ("7fd8a57c-95df-46a1-aba8-cc957c7ab33d")
    public List<IPersistentViewModelChangeListener> getPersistentViewChangeListeners() {
        return this.persistentViewChangeHandlers;
    }

    @objid ("760a2a97-a724-4795-a34a-67da17b192d1")
    @Override
    public String toString() {
        return "ModelChangeSupport [modelChangeListeners=" + this.modelChangeListeners.size() + ", modelChangeHandlers=" + this.modelChangeHandlers.size() + ", statusListeners=" + this.statusListeners.size() + ", persistentViewChangeHandlers="
                        + this.persistentViewChangeHandlers.size() + "]";
    }

}
