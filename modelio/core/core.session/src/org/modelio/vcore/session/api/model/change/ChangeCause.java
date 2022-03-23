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
package org.modelio.vcore.session.api.model.change;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Cause of a model chane event.
 */
@objid ("6ff6e478-8a81-4433-be6a-b1a700eac2d0")
public enum ChangeCause {
    /**
     * The change is caused by a transaction commit.
     */
    @objid ("2961c075-d964-415b-8af6-920a0a91c590")
    TRANSACTION,
    /**
     * The change is caused by an Undo operation.
     */
    @objid ("ac1a63d8-aea0-4063-b7bb-dfb03784e81b")
    UNDO,
    /**
     * An undone transaction is being redone.
     */
    @objid ("f816e119-91c4-4438-b640-246d68cb7042")
    REDO,
    /**
     * The repository modified the model.
     * <p>
     * Usually occurs when the model is modified externally:
     * SVN update, shared repository modified remotely.
     */
    @objid ("7dbab2a7-1cee-47e2-8959-a6325e5d31f1")
    REPOSITORY,
    /**
     * The status flags of model objects is modified outside transactions.
     * <p>
     * Used by the audit to set audit flags.
     */
    @objid ("40ebb51c-be54-42cc-b5b1-36f1d34f0ca7")
    STATUS;

}
