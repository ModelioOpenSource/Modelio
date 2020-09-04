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
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;

/**
 * permet d'ajouter un objet dans une association.
 */
@objid ("006e780a-0d1e-1f20-85a5-001ec947cd2a")
public class AppendDependencyAction extends DependencyModificationAction {
    /**
     * L'enleve l'objet reference de la dependance. L'action est dejouee
     * uniquement si l'object n'est pas un transient objet ou si c'est un
     * rollback.i
     */
    @objid ("006d3fbc-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        this.refered.getMetaOf().setActionRecording(false);
        this.refered.getMetaOf().eraseObjDepVal(this.refered, this.smDep, this.ref);
        this.refered.getMetaOf().setActionRecording(true);
    }

    /**
     * Remet l'objet reference dans l'association. L'action est dejouee
     * uniquement si l'object n'est pas un transient objet
     */
    @objid ("006d4052-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() {
        // On remet l'objet dans la dependance sans pour cela recreer une action
        this.refered.getMetaOf().setActionRecording(false);
        this.refered.getMetaOf().appendObjDepValIndex(this.refered, this.smDep, this.ref, this.index);
        this.refered.getMetaOf().setActionRecording(true);
    }

    /**
     * Constructeur de l'action "append" sur une dependance. Sauvegarde l'objet
     * contenant la dependance dans l'association "Refer", la dependance dans
     * l'association "Dep" et l'objet ?a jouter dasn "Ref". On sauvegarde aussi
     * l'indice de l'objet dans l'association et le mode de propagation.
     * @param obj the source model object
     * @param dep the dependency
     * @param ref the appended model object
     */
    @objid ("006d40e8-0d1e-1f20-85a5-001ec947cd2a")
    public AppendDependencyAction(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl ref) {
        this(obj, dep, ref, (dep.isMultiple())? ((SmMultipleDependency) dep).getValueList(obj.getData()).size() - 1 :-1);
    }

    @objid ("006d417e-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitAppendDependencyAction(this);
    }

    @objid ("0082a33e-702b-1f21-85a5-001ec947cd2a")
    public AppendDependencyAction(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl ref, final int index) {
        super(obj, dep, ref);
        
        this.index = index;
    }

    @objid ("5e9830de-b382-4ad2-8589-ee9cc19e20c9")
    @Override
    public String toString() {
        return String.format("append %s to %s->%s[%d]", this.ref, this.refered, this.smDep, this.index);
    }

}
