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

package org.modelio.vcore.session.api.model.change;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Model change listener support service.
 * <p>
 * There are 4 types of model change listeners:<ul>
 * <li> model change handlers, implementing {@link IModelChangeHandler}. They can modify the model.
 * Failure of a model  handler will make the transaction commit fail and rollback.
 * <li> persistent views change listeners, implementing {@link IPersistentViewModelChangeListener}.
 * They are used to update diagrams, which need an open transaction because they persist some data in the model.
 * They can modify the model but are expected to do only non structural modifications.
 * <li> model change listeners, implementing {@link IModelChangeListener}. They cannot modify the model.
 * Failure of a transaction listener will not prevent the transaction from commit.
 * <li> Model status event change listeners. These listeners may be called asynchronously
 * when the status of some elements change.
 * </ul>
 * <p>
 * When committing a transaction tasks are done in the following order:<ol>
 * <li> model change handlers are called.
 * <li> the transaction closure is called.
 * <li> persistent view listeners are called.
 * <li> blocking audit is called.
 * <li> the transaction is closed.
 * <li> model and status listeners are called.
 * </ol>
 */
@objid ("00962eb8-5765-10c8-842f-001ec947cd2a")
public interface IModelChangeSupport {
    /**
     * Add a model change handler.
     * 
     * @param aHandler a model change handler.
     */
    @objid ("0061c06a-58c5-10c8-842f-001ec947cd2a")
    void addModelChangeHandler(IModelChangeHandler aHandler);

    /**
     * Add a model change listener.
     * 
     * @param aListener the listener.
     */
    @objid ("0061cd80-58c5-10c8-842f-001ec947cd2a")
    void addModelChangeListener(IModelChangeListener aListener);

    /**
     * Add a persistent view model change listener.
     * <p>
     * They are used to update diagrams, which need an open transaction because they persist some data in the model.
     * They can modify the model but are expected to do only non structural modifications.
     * 
     * @param aListener the listener to add
     */
    @objid ("5c1683bd-38f3-4ec3-9312-a88522d4b08d")
    void addPersistentViewListener(IPersistentViewModelChangeListener aListener);

    /**
     * @param aHandler the handler to remove.
     */
    @objid ("0061d686-58c5-10c8-842f-001ec947cd2a")
    void removeModelChangeHandler(IModelChangeHandler aHandler);

    /**
     * @param aListener the listener to remove.
     */
    @objid ("0061e05e-58c5-10c8-842f-001ec947cd2a")
    void removeModelChangeListener(IModelChangeListener aListener);

    /**
     * @param aListener the listener to remove.
     */
    @objid ("8d7f5069-d544-4958-ba67-6ba321a2c0e9")
    void removePersistentViewListener(IPersistentViewModelChangeListener aListener);

    /**
     * Add a model status change event listener.
     * 
     * @param aListener a model status change event listeners.
     */
    @objid ("0061e9d2-58c5-10c8-842f-001ec947cd2a")
    void addStatusChangeListener(final IStatusChangeListener aListener);

    /**
     * @param aListener the listener to remove.
     */
    @objid ("00620606-58c5-10c8-842f-001ec947cd2a")
    void removeStatusChangeListener(final IStatusChangeListener aListener);

}
