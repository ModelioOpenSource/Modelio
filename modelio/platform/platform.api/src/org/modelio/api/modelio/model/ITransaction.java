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
package org.modelio.api.modelio.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface defines the Modelio transactions.
 * <p>
 * A transaction is a set of individual model changes. Each transaction must succeed or fail as a complete unit; it
 * cannot remain in an intermediate state.
 * <p>
 * All the model updates have to be carried out in the context of a transaction, which has to be created, through the
 * use of the {@link IModelingSession#createTransaction(String)} method, and committed using the
 * {@link ITransaction#commit()} method.
 */
@objid ("d56951be-6449-11e0-b650-001ec947cd2a")
public interface ITransaction extends AutoCloseable {
    /**
     * Commit a transaction <br>
     * @throws: EndTransactionBadIdException if the transaction being committed is not the currently active one
     * (Sequence error)
     * @throws: EndTransactionBadIdException if there is no currently active transaction.
     */
    @objid ("f2e1f462-0e28-11e2-baba-001ec947c8cc")
    void commit();

    /**
     * Rollback the currently active transaction.
     * <p>
     * The actions of the currently active transaction are undone and the transaction removed from the active stack
     */
    @objid ("f2e1f464-0e28-11e2-baba-001ec947c8cc")
    void rollback();

    /**
     * Failsafe mechanism that will automatically rollback the transaction if it has not been commited nor rolled back.
     */
    @objid ("f2e1f466-0e28-11e2-baba-001ec947c8cc")
    @Override
    void close() throws RuntimeException;
}

