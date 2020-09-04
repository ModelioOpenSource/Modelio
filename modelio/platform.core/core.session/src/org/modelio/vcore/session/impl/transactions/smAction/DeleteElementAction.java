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

package org.modelio.vcore.session.impl.transactions.smAction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Permet la destruction d'un objet. L'objet sera marque detruit et ne sera plus relie a aucun autre objet.
 */
@objid ("006e777e-0d1e-1f20-85a5-001ec947cd2a")
public class DeleteElementAction extends SimpleAction {
    /**
     * Remet l'opbjet reference ? l'etat valide "undeleteObject"
     * L'action est dejouee uniquement si l'object n'est pas un transient objet ou si c'est un rollback.i
     */
    @objid ("006d324c-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        this.refered.getMetaOf().objUndeleted(this.refered);
    }

    /**
     * Lance la destruction, "silentActionRemove", de l'objet referencer.
     * L'action est dejouee uniquement si l'object n'est pas un transient objet
     */
    @objid ("006d32e2-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() {
        // destruction de l'objet
        this.refered.getMetaOf().silentActionRemove(this.refered);
    }

    /**
     * Constructeur de l'action des desctruction d'un objet,
     * 
     * sauvegarde l'objet detruit dans l'association "refered".
     * 
     * @param obj the deleted object
     */
    @objid ("006d3382-0d1e-1f20-85a5-001ec947cd2a")
    public DeleteElementAction(final SmObjectImpl obj) {
        super(obj);
    }

    @objid ("006d340e-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitDeleteElementAction (this);
    }

    @objid ("cb73b0f9-443f-427a-9225-315583e00415")
    @Override
    public String toString() {
        return String.format("Delete %s action", this.refered);
    }

}
