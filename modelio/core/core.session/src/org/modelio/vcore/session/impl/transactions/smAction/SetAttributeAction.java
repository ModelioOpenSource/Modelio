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
import org.modelio.vcore.smkernel.SmStatus;
import org.modelio.vcore.smkernel.meta.SmAttribute;

/**
 * Permet le modfication de l'attribut. L'ancienne valeur est sauvegardee pour l'undo.
 */
@objid ("006e7bd4-0d1e-1f20-85a5-001ec947cd2a")
public class SetAttributeAction extends SimpleAction {
    @objid ("006e71de-0d1e-1f20-85a5-001ec947cd2a")
    protected SmAttribute smAtt;

    @objid ("006b3690-0d1e-1f20-85a5-001ec947cd2a")
    protected Object oldValue;

    @objid ("006b37d0-0d1e-1f20-85a5-001ec947cd2a")
    protected Object newValue;

    /**
     * Remet l'ancienne valeur de l'attribut.
     * L'action est dejouee uniquement si l'object n'est pas un transient objet ou si c'est un rollback.i
     */
    @objid ("006d35ee-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        // Affectation de la nouvelle valeur
        this.smAtt.setValue(this.refered.getData(), this.oldValue);
    }

    /**
     * remet la nouvelle valeur de l'attribut.
     * L'action est dejouee uniquement si l'object n'est pas un transient objet
     */
    @objid ("006d367a-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() {
        // Affectation de la nouvelle valeur
        this.smAtt.setValue(this.refered.getData(), this.newValue);
    }

    /**
     * Constructeur de l'action de modification d'un attribut d'un objet. L'objet est sauvegarde dans l'associartion "Refer", l'attribut concerne dans l'association "smAtt" on sauvegarde aussi l'ancienne et la nouvelle valeur de l'attribut.
     */
    @objid ("006d3710-0d1e-1f20-85a5-001ec947cd2a")
    public SetAttributeAction(final SmObjectImpl obj, final SmAttribute smAtt, final Object oldValue, final Object newValue) {
        super(obj);
        this.smAtt = smAtt;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @objid ("006d37a6-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitSetAttributeAction (this);
    }

    @objid ("0090435e-f11f-1f3c-aafd-001ec947cd2a")
    public SmAttribute getAtt() {
        return this.smAtt;
    }

    @objid ("00905dee-f11f-1f3c-aafd-001ec947cd2a")
    public Object getNewValue() {
        return this.newValue;
    }

    @objid ("0090784c-f11f-1f3c-aafd-001ec947cd2a")
    public Object getOldValue() {
        return this.oldValue;
    }

    @objid ("4b3eadfb-332f-496e-959d-8f2fa9661da0")
    @Override
    public String toString() {
        if (this.smAtt == this.refered.getClassOf().statusAtt()) {
            return String.format("Set %s.%s from \n\t%s\n to \n\t%s\n action", this.refered, this.smAtt.getName(), SmStatus.toString((long) this.oldValue), SmStatus.toString((long) this.newValue));
        } else {
            return String.format("Set %s.%s from %s to %s action", this.refered, this.smAtt.getName(), this.oldValue, this.newValue);
        }
    }

}
