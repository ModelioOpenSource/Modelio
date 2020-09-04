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
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Permet de retirer un objet d'une association. cet objet est sauvegarde pour
 * le undo.
 */
@objid ("006e7b52-0d1e-1f20-85a5-001ec947cd2a")
public class EraseDependencyAction extends DependencyModificationAction {
    /**
     * Constructeur de l'action "erase" sur une dependance.Sauvegarde l'objet
     * contenant la dependance dans l'association "Refer", la dependance
     * concernee dans l'association "Dep" et l'objet reference dans
     * l'association "Ref". On sauvegarde aussi l'indice de l'objet dans
     * l'association et le mode de propagation.
     */
    @objid ("006d3c7e-0d1e-1f20-85a5-001ec947cd2a")
    public EraseDependencyAction(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl ref, final int index) {
        super(obj, dep, ref);
        this.index = (index >= 0) ? index : 0;
    }

    @objid ("006d3d14-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitEraseDependencyAction(this);
    }

    /**
     * enl?ve l'objet "Ref" de la dependance. L'action est dejouee uniquement si
     * l'object n'est pas un transient objet
     */
    @objid ("006d3dbe-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() {
        // On enl?ve l'objet de la dependance sans pour cela recreer une action
        this.refered.getMetaOf().setActionRecording(false);
        this.refered.getMetaOf().eraseObjDepVal (this.refered, this.smDep, this.ref);
        this.refered.getMetaOf().setActionRecording(true);
    }

    /**
     * Reinjecte l'objet "ref" dans la dependance L'action est dejouee
     * uniquement si l'object n'est pas un transient objet ou si c'est un
     * rollback.i
     */
    @objid ("006d3e4a-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        // On remet l'objet dans la dependance sans pour cela recreer une action
        this.refered.getMetaOf().setActionRecording(false);
        this.refered.getMetaOf().appendObjDepValIndex(this.refered, this.smDep, this.ref, this.index);
        this.refered.getMetaOf().setActionRecording(true);
    }

    @objid ("b4b28c67-0094-488f-ac64-aa3ebc2254b2")
    @Override
    public String toString() {
        return String.format("remove %s from %s->%s[%d]", this.ref, this.refered, this.smDep, this.index);
    }

}
