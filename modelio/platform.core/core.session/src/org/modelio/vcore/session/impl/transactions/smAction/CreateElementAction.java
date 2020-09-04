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

package org.modelio.vcore.session.impl.transactions.smAction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Permet la creation d'un objet.
 */
@objid ("006d29a0-8b2d-1f1e-85a5-001ec947cd2a")
public class CreateElementAction extends SimpleAction {
    /**
     * Lance la destruction, "silentActionRemove", de l'objet referencer.
     * L'action est dejouee uniquement si l'object n'est pas un transient objet ou si c'est un rollback.
     */
    @objid ("006af748-8b2d-1f1e-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        // remove de l'objet
        (this.refered.getMetaOf()).silentActionRemove(this.refered);
    }

    /**
     * Remet l'opbjet reference ? l'etat valide "undeleteObject"
     * L'action est rejouee uniquement si l'object n'est pas un transient objet
     */
    @objid ("006af806-8b2d-1f1e-85a5-001ec947cd2a")
    @Override
    public void redo() {
        //creation de l'objet
        this.refered.getMetaOf().objUndeleted(this.refered);
    }

    /**
     * Constructeur de l'action qui permet de creer un element.
     * <p>
     * L'element cree sera sauvegarde dans l'association "refer".
     * @param obj the created element.
     */
    @objid ("006af8a6-8b2d-1f1e-85a5-001ec947cd2a")
    public CreateElementAction(final SmObjectImpl obj) {
        super(obj);
    }

    @objid ("006af9c8-8b2d-1f1e-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitCreateElementAction (this);
    }

    @objid ("63790211-075b-43a7-b7b8-bdd49ef7c581")
    @Override
    public String toString() {
        return String.format("Create %s", this.refered);
    }

}
