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

package org.modelio.vcore.session.impl.transactions.smAction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Permet de deplace une association. L'ancien objet conetant l'association est
 * sauvegarde pour le undo.
 */
@objid ("006e7cec-0d1e-1f20-85a5-001ec947cd2a")
public class MoveDependencyAction extends DependencyModificationAction {
    /**
     * Remet l'objet a son ancienne place dans l'association. L'action est
     * dejouee uniquement si l'object n'est pas un transient objet ou si c'est
     * un rollback.i
     */
    @objid ("006d4304-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        // On remet l'objet a son ancienne place dans la dependance
        this.smDep.moveRef(this.refered.getData(), this.ref, -this.index);
    }

    /**
     * Redeplace l'objet dans l'association. L'action est dejouee uniquement si
     * l'object n'est pas un transient objet
     */
    @objid ("006d4390-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() {
        this.smDep.moveRef(this.refered.getData(), this.ref, this.index);
    }

    /**
     * Deplace un objet dans une dependance.
     * <p>
     * Sauvegarde l'objet contenant la
     * dependance dans l'association "Refer", l'objet deplace dans l'association
     * "Ref", la dependance concerne dans l'association "Dep", le nombre de
     * deplacement effectue.
     */
    @objid ("006d4426-0d1e-1f20-85a5-001ec947cd2a")
    public MoveDependencyAction(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl moving, final int offset) {
        super(obj, dep, moving);
        this.index = offset;
    }

    @objid ("006d44bc-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitMoveDependencyAction(this);
    }

    @objid ("53cf2b6e-ed29-42a3-90a0-318449342421")
    @Override
    public String toString() {
        return String.format("Reorder %s in %s->%s[%+d] action", this.ref, this.refered, this.smDep, this.index);
    }

}
