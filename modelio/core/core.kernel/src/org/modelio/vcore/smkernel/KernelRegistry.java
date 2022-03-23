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
package org.modelio.vcore.smkernel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Registry for all {@link IKernelServiceProvider}.
 */
@objid ("004db07a-861a-1f21-85a5-001ec947cd2a")
public class KernelRegistry {
    @objid ("c90ff280-fa4c-4b34-9ba6-ed82545799f0")
    private static List<IKernelServiceProvider> registry = new ArrayList<>();

    /**
     * Get the kernel provider service for a live id.
     * @param liveId a live id
     * @return the kernel provider service
     * @throws NoSuchKernelException if the live id does not match a current or previous service, or the found service is no longer active. (The project is closed)
     */
    @objid ("00415924-8b3b-1f21-85a5-001ec947cd2a")
    public static IKernelServiceProvider getService(final long liveId) throws NoSuchKernelException {
        IKernelServiceProvider ret = getService0(liveId);
        if (ret == null) {
            NoSuchKernelException ex = new NoSuchKernelException("KernelRegistry - Provider service id " + SmLiveId.getKid(liveId)
                    + " no longer active.", null);
            if (Log.ENABLED) {
                Log.warning(ex);
            }
            throw ex;
        }
        return ret;
    }

    /**
     * Register the kernel provider service.
     * <p>
     * Calls {@link IKernelServiceProvider#setId(short)} with its identifier.
     * @param service the kernel provider service to register.
     */
    @objid ("00416e78-8b3b-1f21-85a5-001ec947cd2a")
    @SuppressWarnings("boxing")
    public static void registerService(final IKernelServiceProvider service) {
        short newId = (short) registry.size();
        registry.add(service);
        service.setId(newId);
        if (Log.ENABLED) {
            Log.trace("KernelRegistry - registered provider '%s', id=%d ", service.getName(), newId);
        }
        
    }

    /**
     * @param kid the id of the service to remove
     */
    @objid ("00418e3a-8b3b-1f21-85a5-001ec947cd2a")
    @SuppressWarnings("boxing")
    public static void removeService(final int kid) {
        if (Log.ENABLED) {
            Log.trace("KernelRegistry - removed provider id=%d ", kid);
        }
        registry.set(kid, null);
        
    }

    /**
     * Get the kernel provider service for a live id.
     * <p>
     * Returns <code>null</code> if the service provider is no longer registered.
     * @param liveId a live id
     * @return the kernel provider service or <code>null</code> if the service has been unregistered.
     * @throws NoSuchKernelException if the live id does not match a current or previous service.
     */
    @objid ("b9f44d70-7a74-11e1-9633-001ec947ccaf")
    public static IKernelServiceProvider getService0(final long liveId) throws NoSuchKernelException {
        short kid = SmLiveId.getKid(liveId);
        
        if (kid < 0) {
            // dummy id, return null
            return null;
        }
        
        try {
            return registry.get(kid);
        } catch (IndexOutOfBoundsException e) {
            NoSuchKernelException ex = new NoSuchKernelException("KernelRegistry - Invalid provider service id: "
                    + kid, e);
            if (Log.ENABLED) {
                Log.warning(ex);
            }
            throw ex;
        }
        
    }

    /**
     * Indicates the asked kernel does not exist or is not active anymore.
     */
    @objid ("5a1aed5f-0088-481a-a34e-a94d9753fd1c")
    public static class NoSuchKernelException extends IllegalArgumentException {
        @objid ("02a2da62-1f67-4a7a-9b6e-c879c29e1e18")
        private static final long serialVersionUID = 1L;

        @objid ("21b272c0-59ab-4cf9-a99c-20e154426db7")
        public  NoSuchKernelException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
