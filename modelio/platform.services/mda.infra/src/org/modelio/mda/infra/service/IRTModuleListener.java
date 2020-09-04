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

package org.modelio.mda.infra.service;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.mda.infra.plugin.MdaInfra;

/**
 * Listener of module state change.
 */
@objid ("445b31b7-d423-4896-9848-f2f356d3f431")
public interface IRTModuleListener {
    /**
     * A module has just started.
     * 
     * @param module the started module
     */
    @objid ("2d9e7663-cec7-418a-a0d5-caa7ec170bb5")
    void moduleStarted(IRTModule module);

    /**
     * A module is stopping.
     * 
     * @param module the stopping module.
     */
    @objid ("065efe78-a4ff-44ff-818e-4da455833004")
    void moduleStopping(IRTModule module);

    /**
     * A module is now stopped.
     * 
     * @param module the stopped module.
     */
    @objid ("17c8a9cf-d49d-464d-94de-9a73e7b90c1f")
    void moduleStopped(IRTModule module);

    /**
     * A module is unloading.
     * 
     * @param module the unloading module
     */
    @objid ("355ec462-08c0-4e1a-a68e-942602bdd304")
    void moduleUnloading(IRTModule module);

    /**
     * A module has been unloaded from memory. Its IModule is not accessible anymore.
     * 
     * @param module the unloaded module
     */
    @objid ("94575d30-77c4-4612-8e21-7327497a9d19")
    void moduleUnloaded(IRTModule module);

    /**
     * A module is now loaded.
     * 
     * @param module the loaded module
     */
    @objid ("107f48fa-f444-4fdf-89d7-1151b699722c")
    void moduleLoaded(IRTModule module);

    /**
     * A module will be removed from the project
     * 
     * @param module the module that will be removed
     */
    @objid ("0d0a94c1-611e-4665-8ec7-e7a24b1c6838")
    void moduleRemoving(IRTModule module);

    /**
     * A module has been removed.
     * 
     * @param module the removed module
     */
    @objid ("bac2ace9-dc3c-45a0-82b9-5cba8a49dc75")
    void moduleRemoved(IRTModule module);

    /**
     * Helper to fire event listeners in a safe way :
     * runtime exceptions throw by listeners are caught, logged and ignored.
     */
    @objid ("b30f88a3-b03e-40ed-b024-de05fb6c679d")
    static class Poster {
        /**
         * Call {@link IRTModuleListener#moduleStarted(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("11edc470-666a-428d-b7bc-4bdcf09ff7c4")
        public static void moduleStarted(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleStarted(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleStopping(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("82953c22-8676-495f-b049-feead46cd1e8")
        public static void moduleStopping(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleStopping(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleStopped(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("bde6dd88-1817-4b58-bf01-e7b74d8d8702")
        public static void moduleStopped(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleStopped(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleUnloading(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("2dc42de9-5afe-4749-a6a9-71600f274f7c")
        public static void moduleUnloading(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleUnloading(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleUnloaded(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("468046e8-1638-4de4-8872-ce66dd536395")
        public static void moduleUnloaded(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleUnloaded(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleLoaded(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("9a0fab0f-ccdb-4a38-a86c-945c5b0b7d1f")
        public static void moduleLoaded(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleLoaded(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleRemoving(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("49796777-f1d3-4217-a1b6-1fc5aeca0ece")
        public static void moduleRemoving(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleRemoving(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

        /**
         * Call {@link IRTModuleListener#moduleRemoved(IRTModule)} on all module listeners.
         * 
         * @param module the related module
         */
        @objid ("70f9b2e2-3fa2-41b9-b0df-a3f76def3c99")
        public static void moduleRemoved(IRTModule module) {
            for (IRTModuleListener listener : module.getListeners()) {
                try {
                    listener.moduleRemoved(module);
                } catch (RuntimeException e) {
                    MdaInfra.LOG.warning(e);
                }
            }
        }

    }

}
