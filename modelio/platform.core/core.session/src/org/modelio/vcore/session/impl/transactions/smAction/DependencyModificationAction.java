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
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Classe abstraite regroupant les actions de modifications d'une association.
 * 
 * Elle poss?de un lien vers la dependance modifiee et un lien pour sauvegarde l'objet reference.
 * 
 * On sauvegarde aussi l'emplacement de l'objet reference dans la dependance
 * 
 * et le mode de propagation de l'action.
 * 
 * Sur une dependance l'action peut ?tre propage ? l'objet associacie (cas des dependances sysmetriques),
 * 
 * ou non propage (acs des dependances non sysmetriques ou retour d'une action propagee).
 */
@objid ("006e7c60-0d1e-1f20-85a5-001ec947cd2a")
public abstract class DependencyModificationAction extends SimpleAction {
    @objid ("006d39cc-0d1e-1f20-85a5-001ec947cd2a")
    public int index = -1;

    @objid ("006e7468-0d1e-1f20-85a5-001ec947cd2a")
    public SmDependency smDep;

    @objid ("006e9b5a-0d1e-1f20-85a5-001ec947cd2a")
    public SmObjectImpl ref;

    @objid ("006d3936-0d1e-1f20-85a5-001ec947cd2a")
    public DependencyModificationAction(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl ref) {
        super(obj);
        this.smDep = dep;
        this.ref = ref;
    }

    @objid ("0093e932-f11f-1f3c-aafd-001ec947cd2a")
    public SmObjectImpl getRef() {
        return this.ref;
    }

    @objid ("009402d2-f11f-1f3c-aafd-001ec947cd2a")
    public SmDependency getDep() {
        return this.smDep;
    }

}
