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
package org.modelio.vcore.session.impl.cache;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.session.api.memory.IMemoryEventListener;
import org.modelio.vcore.session.api.memory.IMemoryManager;
import org.modelio.vcore.smkernel.AccessOrderer;
import org.modelio.vcore.smkernel.IKernelServiceProvider;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.KernelRegistry;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.StatusState;

/**
 * Service class that polls available memory in a background thread and
 * asks {@link CacheManager}s to free memory when available memory is low.
 * <p>
 * {@link IMemoryEventListener} listeners will be fired a {@link IMemoryEventListener#onFreeMemoryStart(MemoryUsage)} property change
 * when beginning freeing memory and {@link IMemoryEventListener#onFreeMemoryEnd(int, MemoryUsage)}
 * when finished.
 */
@objid ("5e988aaa-bebe-482a-aa6d-33e28947f269")
public class MemoryManager implements IMemoryManager, Runnable {
    @objid ("ed47c2e3-cf9e-40d0-b14d-c04e4425d99b")
    private static final String PROPERTY_DISABLE_MANAGER = "disableSwap";

    @objid ("635c78ad-6b20-4ffb-83f1-e550b2a1c9b3")
    private int lastFree = 0;

    @objid ("919f6fe5-3d51-4c86-8cf0-664490c4a4a7")
    private boolean swapEnabled;

    @objid ("b72d32de-fed4-4d98-8ebc-0f04d48962b3")
    private static final int ONE_MEGABYTE = 1_000_000;

    @objid ("c4b418bd-4e55-4824-b9d6-d7691bc44cd4")
    private static final int THREAD_PERIOD = 3000;

    @objid ("4e1ba2aa-6194-475f-b7c0-cf96a82c5456")
    private static final double LOWMEMORY_RATIO = 0.8;

    @objid ("bd0451d6-8faa-4491-84d9-ba0248e25d51")
    private static volatile MemoryManager instance = null;

    @objid ("6ecef006-3d90-4481-886b-d36c0b3abf9f")
    private Collection<Map<String, ISmObjectData>> caches = new ArrayList<>();

    @objid ("4d960c39-4d2d-418d-a62f-da0b73bc58d2")
    private Collection<IMemoryEventListener> listeners = new ArrayList<>();

    /**
     * Get the memory watcher instance.
     * @return the memory watcher.
     */
    @objid ("50d9dd95-51b3-4f18-9b03-37d5f236d667")
    public static MemoryManager get() {
        if (instance==null) {
            synchronized (MemoryManager.class){
                if (instance==null) {
                    instance = new MemoryManager();
                }
            }
        }
        return instance;
    }

    @objid ("2ca174ea-272c-4047-a6be-adfad5ccd5bd")
    @Override
    public synchronized boolean isSwapEnabled() {
        return this.swapEnabled;
    }

    /**
     * Private constructor.
     * <p>
     * Initialize the memory manager and its thread.
     */
    @objid ("d9053b8a-722a-4ea1-8e3d-20d028a5c3a0")
    private  MemoryManager() {
        this.swapEnabled = System.getProperty(PROPERTY_DISABLE_MANAGER) == null; 
        if (isSwapEnabled()) {
            createSwapThread();
        }
        
    }

    /**
     * Runs the memory manager in its thread.
     */
    @objid ("ab530554-3784-4b7a-842f-0f12b548ae58")
    @Override
    public void run() {
        // this object will be finalized just before 
        // a {@link OutOfMemoryError} is raised .
        SoftReference<LastResortGc> lastResort = new SoftReference<>(new LastResortGc());
        
        while (isSwapEnabled()) {
            try {
                synchronized(this) {
                    while (this.caches.isEmpty()) {
                        wait();
                        if (! isSwapEnabled()) {
                            return;
                        }
                    }
                }
            
                // Rebuild the last chance swapper if needed
                if (lastResort.get() == null) {
                    lastResort = new SoftReference<>(new LastResortGc());
                }
            
                // Uncomment to enable periodic memory free
                //ensureFreeMemory();
            
                // Wait 3 second at ~90% free, 100ms at 0% free.
                long toWait = THREAD_PERIOD; //(long) (3000 * (1.1 - pcUsed));
                Thread.sleep(toWait);
            
            } catch (InterruptedException e) {
                //NOOP
            }
        }
        
    }

    @objid ("cb603fa9-bf85-4964-9aa2-9cd27cc724fd")
    @Override
    public void addMemoryListener(IMemoryEventListener listener) {
        this.listeners.add(listener);
    }

    @objid ("db805a4a-64fc-419e-9252-2878ffc454c6")
    @Override
    public void removeMemoryListener(IMemoryEventListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Add a managed data cache.
     * @param dataCache a data cache.
     */
    @objid ("57b5a26f-c1d1-4692-9c85-2b36339343cf")
    public synchronized void addManagedCache(Map<String, ISmObjectData> dataCache) {
        this.caches.add(dataCache);
        notifyAll();
        
    }

    /**
     * Remove a managed cache.
     * @param dataCache a data cache.
     */
    @objid ("e3999d4b-9483-47f4-b0ed-c0a49a9e6578")
    public synchronized void removeManagedCache(Map<String, ISmObjectData> dataCache) {
        this.caches.remove(dataCache);
    }

    @objid ("14e75e5a-0bfa-423d-af44-edb40da4963d")
    synchronized void freeMemory(MemoryUsage memoryState) {
        long total = memoryState.getMax();
        long used = memoryState.getUsed();
         
        int lastAccess = AccessOrderer.getLastAccess();
        int range = lastAccess - this.lastFree;
        int toRemove;
        
        if (total - used < ONE_MEGABYTE) {
            toRemove = range * 9 / 10; // if less than 1Mo available, free 90%
        }
        else {
            toRemove = range / 3; // free 30%
        }
        
        int toRemoveIdx = this.lastFree + toRemove; 
        
        for (IMemoryEventListener  l : this.listeners) {
            l.onFreeMemoryStart(memoryState);
        }
        
        int removed = 0;
        
        for (Map<String, ISmObjectData> c : this.caches) {
            removed += freeMemory(c, toRemoveIdx);
        }
        
        this.lastFree = toRemoveIdx;
        
        for (IMemoryEventListener  l : this.listeners) {
            l.onFreeMemoryEnd(removed, ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        }
        
    }

    /**
     * Swaps and free entries in the given data cache.
     * @param cache an object data cache.
     * @param toRemoveIdx the access "time" below which an object must be freed.
     * @return the number of swapped objects.
     */
    @objid ("f5cde29e-4915-4b11-bf56-3226c41952f1")
    private int freeMemory(Map<String, ISmObjectData> cache, int toRemoveIdx) {
        int nb = 0;
        Iterator<Entry<String, ISmObjectData>> it = cache.entrySet().iterator();
        
        while (it.hasNext() ) {
            Entry<String, ISmObjectData> entry = it.next();
            int lastDataAccess = entry.getValue().getLastAccess();
            if (lastDataAccess <= toRemoveIdx) {
                ISmObjectData data = entry.getValue();
                if (data.hasAllStatus(IRStatus.LOADING) != StatusState.TRUE) {
                    IKernelServiceProvider ksp = KernelRegistry.getService0(data.getLiveId());
                    if (ksp != null) {
                        ksp.getSwap().swap((SmObjectData) data);
                    }
        
                    it.remove();
                    nb++;
                }
            }
        }
        return nb;
    }

    /**
     * Called when the last access time overflows {@link Integer#MAX_VALUE}.
     * <p>
     * Reset all access times to zero.
     */
    @objid ("1722346c-5123-4bb6-81c3-5b7defcd98f1")
    public synchronized void onAccessTimeOverflow() {
        //        // Activate listeners
        //        for (IMemoryEventListener  l : this.listeners)
        //            l.onResetAccessTimeBegin();
                
        // Reset all access times
        for (Map<String, ISmObjectData> c : this.caches) {
            for (ISmObjectData d : c.values()) {
                d.setLastAccess(0);
            }
        }
                
        //        // Fire listeners
        //        for (IMemoryEventListener  l : this.listeners)
        //            l.onResetAccessTimeEnd();
        
    }

    /**
     * Enable or disable the swap.
     * @param enable <code>true</code> to anable swapping, <code>false</code> to disable it.
     */
    @objid ("ef3f463f-9d04-4ad7-87f4-2c012a05b041")
    public synchronized void setSwapEnabled(boolean enable) {
        if (this.swapEnabled != enable) {
            this.swapEnabled = enable;
            if (enable) {
                createSwapThread();
            } else {
                notifyAll();
            }
        }
        
    }

    @objid ("62d75ba3-72e9-4a18-9c4d-5be30c593ba9")
    private void createSwapThread() {
        Thread t = new Thread(this, "Low memory watcher");
        t.setDaemon(true);
        t.setPriority(Thread.currentThread().getPriority() + 1);
        t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
              
            @Override
            public void uncaughtException(Thread deadThread, Throwable e) {
                if (! (e instanceof ThreadDeath)) {
                    Log.error(e);
                }
            }
        });
              
        AccessOrderer.addListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                onAccessTimeOverflow();
            }
        });
        
        t.start();
        
    }

    /**
     * Ensure that less than {@value #LOWMEMORY_RATIO} fraction of JVM memory is used.
     * Swaps model objects if the ratio is reached.
     */
    @objid ("c41885c2-8c08-4d3f-8328-61aeba8a067c")
    void ensureFreeMemory() {
        // Compute used memory
        MemoryUsage memState = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        
        long available = memState.getCommitted();
        long used = memState.getUsed();
        double pcUsed = (double)used / available;
        
        // Swap some objects if running low memory
        if (pcUsed > LOWMEMORY_RATIO) {
            freeMemory(memState);
        }
        
    }

    /**
     * Instance of this class is meant to be finalized before
     * a {@link OutOfMemoryError} is raised by using a {@link SoftReference}.
     * <p>
     * It calls swapping on finalization.
     */
    @objid ("3aaaff79-0d72-4e26-b3f9-192039c51320")
    private class LastResortGc {
        @objid ("404912a0-f95a-4002-baa7-41eb7b68977b")
        public  LastResortGc() {
            // NOOP
        }

        @objid ("1c5c7ba3-a344-470a-9c9b-af0ee606f400")
        @Override
        protected void finalize() throws Throwable {
            // Shield against Linux PermGen full.
            // On Linux SoftReference are freed when PermGen is full too.
            // Free memory only if 1/4 or less
            ensureFreeMemory();
            
            super.finalize();
            
        }

    }

}
