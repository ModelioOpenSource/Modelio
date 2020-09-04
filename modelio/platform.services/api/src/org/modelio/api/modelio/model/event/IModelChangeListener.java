/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model.event;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;

/**
 * Listener of model changes.
 * <p>
 * Each time the a model transaction is commited, a {@link IModelChangeEvent} class is computed and given to each
 * {@link IModelChangeHandler}, and then each {@link IModelChangeListener} that is connected to the modeling session.<br>
 * <p>
 * A model change listener is used to refresh data or views. The model must not be modified in a model change listener, please use a {@link IModelChangeHandler} instead.
 * <p>
 * <p>
 * Example of adding a new listener in Modelio.
 * <p>
 * <code>
 * MyModelChangeListener listener = new MyModelChangeListener(...);<p>
 * Modelio.getInstance().getModelingSession().addModelListener (listener);<p><p>
 * </code>
 * 
 * Example of removing an existing listener from Modelio.
 * <p>
 * <code>
 * MyModelChangeListener listener = ...;<p>
 * Modelio.getInstance().getModelingSession().removeModelListener (listener);<p><p>
 * </code>
 * 
 * The given {@link IModelChangeEvent} is used to get the delta between the model before the transaction execution and
 * the model after.
 * 
 * @see IModelChangeEvent
 */
@objid ("00d00158-0001-61aa-0000-000000000000")
public interface IModelChangeListener {
    /**
     * Invoked when the model has changed.
     * 
     * <P>
     * The <code>session</code> parameter is the modeling session where the event has occured. modifications. The event
     * parameter provide the changes made in the model.
     * </p>
     * No model changes must be done in this method.
     * 
     * @param session The modeling session.
     * @param event Delta between the beginning and the end of the transaction.
     */
    @objid ("00d00158-0001-61ac-0000-000000000000")
    void modelChanged(IModelingSession session, IModelChangeEvent event);

}
