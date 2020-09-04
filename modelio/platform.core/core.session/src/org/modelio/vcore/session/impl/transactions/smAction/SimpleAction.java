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
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Classe abstraite regroupant les differentes actions possible sur un objet, un attribut ou une dependance.
 * Elle connait l'objet impacte par l'action, et sauvegarde la derni?re date de modification de l'objet. La creation d'une nouvelle transition affectera la date de modification courante.
 */
@objid ("006e7abc-0d1e-1f20-85a5-001ec947cd2a")
public abstract class SimpleAction implements IAction {
    /**
     * This is the modified model object.
     */
    @objid ("006c68da-0d1e-1f20-85a5-001ec947cd2a")
    protected SmObjectImpl refered;

    /**
     * @return the modified model object.
     */
    @objid ("006d26c6-0d1e-1f20-85a5-001ec947cd2a")
    public SmObjectImpl getRefered() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.refered;
    }

    /**
     * @param refered the modified model object.
     */
    @objid ("006b12f0-0d1e-1f20-85a5-001ec947cd2a")
    public SimpleAction(final SmObjectImpl refered) {
        this.refered = refered;
    }

    @objid ("006b15c0-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public boolean isTransaction() {
        return false;
    }

    @objid ("006b182c-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void disableUndo() {
        // nothing
    }

}
