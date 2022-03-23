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
package org.modelio.vcore.session.impl.transactions.events;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Service that records status change events and fires each 2 seconds
 * a status change event unless a transaction is open.
 * <p>
 * This service must be initialized with {@link #init(ScheduledExecutorService, ITransactionSupport, ModelChangeSupport)}
 * before it works.
 */
@objid ("013199d3-335f-47f7-b75f-bf03a511f7b0")
public class StatusChangeManager {
    @objid ("f2b7bc00-49c9-4a5d-af41-baffb1891f9a")
    private volatile Map<SmObjectImpl, Long> changed;

    @objid ("daea0e0f-8a68-4dbb-8529-3d2a209171d6")
    private volatile boolean scheduled = false;

    @objid ("2dd677d3-2fab-4c56-b05f-4d94aad8221b")
    private EventFirer eventFirer;

    @objid ("163a7734-6654-41c6-99a9-8c074f7e4c2e")
    private ModelChangeSupport modelChangeSupport;

    /**
     * Constructor.
     * <p>
     * The manager won't be usable until {@link #init(ScheduledExecutorService, ITransactionSupport, ModelChangeSupport)} is called.
     */
    @objid ("30e6855f-9078-4492-a89a-fbae521044a3")
    public  StatusChangeManager() {
        
    }

    /**
     * Initialize the service.
     * @param taskService a task scheduling service.
     * @param tmgr the transaction manager.
     * @param chgSupport the model change support used to fire status change events
     */
    @objid ("3bd3dd2a-0488-4914-85ca-974272f7488a")
    public void init(ScheduledExecutorService taskService, ITransactionSupport tmgr, ModelChangeSupport chgSupport) {
        this.changed = new HashMap<>();
        this.modelChangeSupport = chgSupport;
        this.eventFirer = new EventFirer(tmgr, taskService);
        
    }

    /**
     * To be called when a model object status changed.
     * @param obj the model object whose status changed.
     * @param oldStatus the old status value for the model object.
     */
    @objid ("0c619e96-1232-49c4-9fee-2b3b87e0ccd1")
    public synchronized void objStatusChanged(SmObjectImpl obj, long oldStatus) {
        assert (this.changed != null); // means init(...) has not been called
        
        if (! this.changed.containsKey(obj)) {
            this.changed.put(obj, oldStatus);
        }
        
        if (! this.scheduled) {
            this.eventFirer.schedule();
            this.scheduled = true;
        }
        
    }

    /**
     * Retrieves and removes all status changes model objects and reset the recorded set.
     * @return the record of changed model objects.
     */
    @objid ("56e6837c-42d9-461d-840c-cef8fc8e86df")
    private Map<SmObjectImpl, Long> pollChanges() {
        assert (this.changed != null); // means init(...) has not been called
        
        if (this.changed.isEmpty()) {
            // Return the empty map singleton.
            // Avoids reallocating a new map.
            return Collections.emptyMap();
        } else {
            // Return the current map and allocate a new one.
            Map <SmObjectImpl, Long> ret = this.changed;
            this.changed = new HashMap<>();
            return ret;
        }
        
    }

    @objid ("2221d1ca-0847-466b-a9f8-9f6a1e371f9f")
    void throwStatusChangeEvent() {
        Map <SmObjectImpl, Long> changes ;
        
        synchronized (this) {
            changes = pollChanges();
            this.scheduled = false;
        }
        
        if (! changes.isEmpty()) {
            EventFactory f = EventFactory.createEvent(ChangeCause.STATUS);
        
            for (Entry<SmObjectImpl, Long> entry : changes.entrySet()) {
                final SmObjectImpl obj = entry.getKey();
                if (! obj.isDeleted()) {
                    f.processStatusChange(obj, entry.getValue(), obj.getData().getStatus());
                }
            }
        
            f.postProcess();
        
            this.modelChangeSupport.fireStatusChangeListeners(f.getStatusEvent());
        }
        
    }

    /**
     * Schedule a status change event to be fired in 2 seconds.
     * <p>
     * If a transaction is currently open, fires the notification when it ends.
     */
    @objid ("0b05e72d-1595-48d3-803e-e42d42e1d92f")
    private class EventFirer implements Runnable {
        @objid ("320a3d75-9947-4270-9c95-0b570bfb1073")
        private ITransactionSupport tmgr;

        @objid ("06be884c-0cd7-4d5e-8069-bd53bb89c9be")
        private ScheduledExecutorService taskService;

        @objid ("87d6cd15-e313-44dc-9391-d722dd611779")
        public  EventFirer(ITransactionSupport tmgr, ScheduledExecutorService taskService) {
            this.tmgr = tmgr;
            this.taskService = taskService;
            
        }

        @objid ("7bbb978a-7cd2-406d-ae61-fc8889a5c420")
        @Override
        public void run() {
            throwStatusChangeEvent();
        }

        /**
         * Schedule a status change event to be fired in 2 seconds.
         * <p>
         * If a transaction is currently open, fires the notification when it ends.
         */
        @objid ("2630e8ed-02e6-49cd-958b-f16ad62015a2")
        public void schedule() {
            if (this.tmgr.hasCurrentTransaction()) {
                // Schedule for end of transaction
                execOutsideTransaction();
            } else {
                // Schedule in 2 seconds to run outside transaction
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        execOutsideTransaction();
                    }
                };
            
                this.taskService.schedule(r, 2, TimeUnit.SECONDS);
            }
            
        }

        @objid ("2b25c514-a660-45ff-9279-9c3ddedc6025")
        void execOutsideTransaction() {
            this.tmgr.asyncExec(this);
        }

    }

}
