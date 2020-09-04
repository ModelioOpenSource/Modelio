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

package org.modelio.api.modelio.model;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.api.modelio.model.event.IModelChangeListener;
import org.modelio.api.modelio.model.event.IStatusChangeListener;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This interface represents a project session. A modeling session is used to update a modelio project and to give the right model navigation roots.
 * <p>
 * <p>
 * The current modeling session can be accessed using the following API: <code>
 * IModelingSession session = Modelio.getInstance().getModelingSession();
 * </code>
 * <p>
 * <p>
 * The <code>IModelingSession</code> interface groups the following features:
 * <ul>
 * <li>Transaction management</li>
 * <li>Model change management</li>
 * <li>Model root access</li>
 * <li>MObject status management</li>
 * </ul>
 */
@objid ("00d00158-0001-67b8-0000-000000000000")
public interface IModelingSession {
    /**
     * Add a model change listener.
     * <p>
     * Handlers can modify the model.
     * <p>
     * This model listener is used to be notified of the model structural changes, through the use of the {@link IModelChangeHandler} listener class.
     * 
     * @param handler the model change listener to add.
     */
    @objid ("785d1e1a-f797-11dd-83f5-0014222a9f79")
    void addModelHandler(IModelChangeHandler handler);

    /**
     * Add a model change handler.
     * <p>
     * Added listeners must not modify the model. If model modifications should be done use addModelHandler() method.
     * <p>
     * This model listener is used to be notified of the model structural changes, through the use of the {@link IModelChangeListener} listener class.
     * 
     * @param listener the model change listener to add.
     */
    @objid ("785d1e18-f797-11dd-83f5-0014222a9f79")
    void addModelListener(IModelChangeListener listener);

    /**
     * Add a status handler.
     * <p>
     * Added listeners must not modify the model. If model modifications should be done use addModelHandler() method.
     * <p>
     * This model listener is used to be notified of all the model status changes (read/write), through the use of the {@link IStatusChangeListener} listener class.
     * 
     * @param listener the model change listener to add.
     */
    @objid ("785d1e16-f797-11dd-83f5-0014222a9f79")
    void addStatusListener(IStatusChangeListener listener);

    /**
     * Create a new transaction in the project.
     * <p>
     * <p>
     * All the model updates have to be carried out in the context of a transaction, which has to be created, through the use of this method, and Committed via the {@link ITransaction#commit} method or rollbacked using the {@link ITransaction#rollback}
     * method.
     * 
     * @param transactionName A transaction name. This name is just a label that might be displayed in log files, a console or dialog boxes.
     * @return the created transaction.
     */
    @objid ("00d00158-0001-67c6-0000-000000000000")
    ITransaction createTransaction(String transactionName);

    /**
     * Get elements in the model from a metaclass, a meta attribute name and its value.
     * <p>
     * If <code>Element</code> is given, every elements in the model will be checked.
     * </p>
     * 
     * @param metaclass the java interface of the metaclass to look for.
     * @param att the name of the meta attribute to check the value in.
     * @param value the value to look for in the attribute.
     * @return A collection of elements matching the parameters. Might be empty but never <code>null</code>.
     */
    @objid ("481f2bce-6cb6-11e0-a312-002564c97630")
    <T extends MObject> Collection<T> findByAtt(Class<T> metaclass, String att, String value);

    /**
     * Get elements in the model from a metaclass, a meta attribute name and its value.
     * <p>
     * If <code>Element</code> is given, every elements in the model will be checked.
     * </p>
     * 
     * @param metaclass the metaclass to look for.
     * @param att the name of the meta attribute to check the value in.
     * @param value the value to look for in the attribute.
     * @return A collection of elements matching the parameters. Might be empty but never <code>null</code>.
     */
    @objid ("9edd01df-4be9-46c6-8ae1-3c7a20afcf4e")
    Collection<? extends MObject> findByAtt(MClass metaclass, String att, String value);

    /**
     * Get all elements in the model from their metaclass.
     * 
     * @param metaclass the java interface of the metaclass to look for.
     * @return A list of all model elements, or null if no match is found.
     */
    @objid ("481f2bcf-6cb6-11e0-a312-002564c97630")
    <T extends MObject> Collection<T> findByClass(Class<T> metaclass);

    /**
     * Get all elements in the model from their metaclass.
     * 
     * @param metaclass the metaclass to look for.
     * @return A list of all model elements, or null if no match is found.
     */
    @objid ("b48c4c6c-c80a-485b-bdfe-16f7262aaf6a")
    Collection<? extends MObject> findByClass(MClass metaclass);

    /**
     * Get an element in the model from its id and metaclass.
     * 
     * @param metaclass the java interface of the metaclass to look for.
     * @param id the id to look for.
     * @return A model element, or null if no match is found.
     */
    @objid ("a426f722-0ecc-11e2-96c4-002564c97630")
    <T extends MObject> T findElementById(Class<T> metaclass, String id);

    /**
     * Get an element in the model from its id and metaclass.
     * 
     * @param metaclass the metaclass to look for.
     * @param id the element's id.
     * @return A model element, or null if no match is found.
     */
    @objid ("6026c1ba-cad7-4454-9fd8-604b56e90603")
    MObject findElementById(MClass metaclass, String id);

    /**
     * Returns the metamodel extension of the project.
     * <p>
     * <p>
     * The metamodel extensions are added by the tool when modules are deployed. It corresponds to the stereotypes, note types and tag types brought by modules.
     * 
     * @return the stereotypes, tag types and not types brought by deployed modules.
     */
    @objid ("00d00158-0001-67c3-0000-000000000000")
    IMetamodelExtensions getMetamodelExtensions();

    /**
     * Returns the UML Model of the project.
     * <p>
     * <p>
     * This method is often used to get the roots using the {@link IUmlModel#getModelRoots} method
     * 
     * @return the UML Model connected to the session.
     */
    @objid ("00d00158-0001-67bd-0000-000000000000")
    IUmlModel getModel();

    /**
     * Get the name of this session.
     * 
     * @return the name of this session.
     */
    @objid ("00d00158-0001-67ba-0000-000000000000")
    String getName();

    /**
     * Used to returns the Requirement Model of the project.
     * @deprecated replace with <code>IModelioServices#getService(IAnalystModel.class)</code> instead to get a proper way to manipulate Requirement model
     */
    @objid ("00d00158-0001-67c0-0000-000000000000")
    @Deprecated
    void getRequirementModel();

    /**
     * Remove a model change handler.
     * 
     * @param handler the model change handler to remove.
     */
    @objid ("7db5a3e6-f797-11dd-83f5-0014222a9f79")
    void removeModelHandler(IModelChangeHandler handler);

    /**
     * Remove a model change listener.
     * 
     * @param listener the model change listener to remove.
     */
    @objid ("7db5a3e7-f797-11dd-83f5-0014222a9f79")
    void removeModelListener(IModelChangeListener listener);

    /**
     * Remove a status change listener.
     * 
     * @param listener the status change listener to remove.
     */
    @objid ("7db5a3e9-f797-11dd-83f5-0014222a9f79")
    void removeStatusListener(IStatusChangeListener listener);

    /**
     * Get an element in the model from an {@link org.modelio.vcore.smkernel.mapi.MRef}.<br>
     * 
     * @param ref a model object reference.
     * @return A model element, or null if no match is found.
     * @since 3.7
     */
    @objid ("65e2d38e-4c45-43ce-957e-9e0b95a925f1")
    MObject findByRef(MRef ref);

}
