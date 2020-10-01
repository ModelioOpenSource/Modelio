/* 
 * Copyright 2013-2020 Modeliosoft
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
 * Handler of model changes.
 * <p>
 * Each time the a model transaction is commited, a {@link IModelChangeEvent} class is computed and given to each
 * {@link IModelChangeHandler}, and then each {@link IModelChangeListener} that is connected to the modeling session.<br>
 * <p>
 * A model change handler is used to trigger model changes in reaction to other model changes.
 * <p>
 * <p>
 * Example of adding a new handler in Modelio.
 * <p>
 * <code>
 * MyModelChangeHandler handler = new MyModelChangeHandler(...);<p>
 * Modelio.getInstance().getModelingSession().addModelHandler (handler);<p><p>
 * </code>
 * 
 * Example of removing an existing handler from Modelio.
 * <p>
 * <code>
 * MyModelChangeHandler handler = ...;<p>
 * Modelio.getInstance().getModelingSession().removeModelHandler (handler);<p><p>
 * </code>
 * 
 * The given {@link IModelChangeEvent} is used to get the delta between the model before the transaction execution and
 * the model after.
 * 
 * @see IModelChangeEvent
 */
@objid ("9b3cc8ca-f790-11dd-83f5-0014222a9f79")
public interface IModelChangeHandler {
    /**
     * Invoked when the model has changed.
     * 
     * <P>
     * The <code>session</code> parameter is the modeling session where the event has occured. modifications. The event
     * parameter provide the changes made in the model.
     * </p>
     * 
     * @param session The modeling session.
     * @param event Delta between the beginning and the end of the transaction.
     */
    @objid ("9b3cc8cc-f790-11dd-83f5-0014222a9f79")
    void handleModelChange(IModelingSession session, IModelChangeEvent event);

}
