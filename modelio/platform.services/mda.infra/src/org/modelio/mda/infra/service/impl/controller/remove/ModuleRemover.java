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

package org.modelio.mda.infra.service.impl.controller.remove;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * This class is in charge of deleting all annotations using annotations types
 * provided by a module .
 */
@objid ("a0a6bbf9-8fac-4a06-9132-0d188c62ff9d")
public class ModuleRemover {
    /**
     * Delete all annotations using annotations types provided by the module .
     * @param module the module to delete.
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure
     */
    @objid ("dae2cb9b-c970-4bc5-827c-95e502aaa0ab")
    public static void remove(ModuleComponent module) throws ModuleException {
        if (module != null) {
            CoreSession session = CoreSession.getSession(module);
        
            try (ITransaction t = session.getTransactionSupport().createTransaction("Remove Module")) {
                deleteModel(module);
        
                t.commit();
            } catch (Error e) {
                throw new ModuleException(module.getName()+" module could not be deleted: "+e.toString(), e);
            }
        }
    }

    @objid ("5555d3e9-5b4a-47df-986c-49c7123f6c57")
    private static void deleteModel(ModuleComponent module) {
        if (module != null) {
            // Unlock module and all children
            unlockRecursive((SmObjectImpl) module);
            module.delete();
        }
    }

    @objid ("5e126601-0a08-4e77-b7d9-463fce4bd7cc")
    private static void unlockRecursive(SmObjectImpl obj) {
        for (SmObjectImpl child : obj.getCompositionChildren()) {
            unlockRecursive(child);
        }
        obj.setRStatus(IRStatus.RMASK_MODIFIABLE_REQUIRED &~ IRStatus.MASK_CMS, IRStatus.RMASK_MODIFIABLE_FORBIDDEN &~ IRStatus.MASK_CMS, 0);
        obj.setPStatus(IPStatus.PMASK_MODIFIABLE_REQUIRED, 0, 0);
    }

}
