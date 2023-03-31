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
package org.modelio.platform.mda.infra.service.impl.controller.remove;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * This class is in charge of deleting all annotations using annotations types provided by a module .
 */
@objid ("a0a6bbf9-8fac-4a06-9132-0d188c62ff9d")
public class ModuleRemover {
    /**
     * Remove the module from the project and optionally remove MDA annotations from the model.
     * @param rtModule the module to remove.
     * @param deleteAnnotations if <code>true</code>, remove the module MDA annotations from other elements in the model.
     * @throws ModuleException on failure
     */
    @objid ("c334963d-7a94-453a-8ac5-fce8dd9693f0")
    public static void remove(IRTModuleAccess rtModule, boolean deleteAnnotations) throws ModuleException {
        if (deleteAnnotations) {
            ModuleComponent comp = rtModule.getModel();
            ModuleRemover.removeModel(comp);
        }
        
        // Remove from project
        ModuleRemover.removeFromGProject(rtModule);
        
    }

    /**
     * Remove the {@link GModule} from the {@link IGProject}.
     * @param rtModule the module to remove.
     * @throws ModuleException on failure
     */
    @objid ("a16f4a35-969d-4ce5-a083-ba11c6375365")
    public static void removeFromGProject(IRTModuleAccess rtModule) throws ModuleException {
        GModule gModule = rtModule.getGModule();
        try {
            IGProject gProject = gModule.getProject();
            gProject.removeGPart(gModule);
        } catch (GPartException e) {
            throw new ModuleException(gModule.getId() + " module could not be removed from the project.", e);
        }
        
    }

    /**
     * Delete all annotations using annotations types provided by the module .
     * @param module the module to delete.
     * @throws ModuleException on failure
     */
    @objid ("dae2cb9b-c970-4bc5-827c-95e502aaa0ab")
    public static void removeModel(ModuleComponent module) throws ModuleException {
        if (module != null) {
            CoreSession session = CoreSession.getSession(module);
        
            try (ITransaction t = session.getTransactionSupport().createTransaction("Remove Module")) {
                deleteModel(module);
        
                t.commit();
            } catch (Error e) {
                throw new ModuleException(module.getName() + " module could not be deleted: " + e.toString(), e);
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
        obj.setRStatus(IRStatus.RMASK_MODIFIABLE_REQUIRED & ~IRStatus.MASK_CMS, IRStatus.RMASK_MODIFIABLE_FORBIDDEN & ~IRStatus.MASK_CMS, 0);
        obj.setPStatus(IPStatus.PMASK_MODIFIABLE_REQUIRED, 0, 0);
        
    }

}
