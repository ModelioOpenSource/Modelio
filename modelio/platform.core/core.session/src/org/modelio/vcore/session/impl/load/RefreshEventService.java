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

package org.modelio.vcore.session.impl.load;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.session.impl.transactions.events.EventFactory;
import org.modelio.vcore.session.impl.transactions.events.ModelChangeSupport;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.smkernel.ISmObjectData;

/**
 * Service that accumulates model refresh actions and
 * fires model and status change events in background.
 * <p>
 * Ensures the model refresh event is fired outside transactions.
 * <p>
 * This class is thread safe and {@link #addEvent(Collection, Collection)} is non blocking.
 */
@objid ("a8d4d6a9-42ee-11e2-91c9-001ec947ccaf")
public class RefreshEventService {
    /**
     * Service data model shared with inner classes.
     */
    @objid ("f6205002-fb4f-4c05-8e42-75d5f2081f04")
    private final SvcData svcData;

    /**
     * Initialize the refresh event support
     * @param changeSupport the model change support
     * @param transactionSupport the model transaction support
     * @param schedulerService a scheduler executor service
     */
    @objid ("95a7ff6d-1ef7-414e-9adc-f0d6b65c9c10")
    public RefreshEventService(ModelChangeSupport changeSupport, final ITransactionSupport transactionSupport, ScheduledExecutorService schedulerService) {
        this.svcData = new SvcData(changeSupport, schedulerService, transactionSupport);
    }

    /**
     * Add a refresh event to process.
     * <p>
     * Add the event to a queue and returns immediately.
     * @param actions all model refresh changes.
     * @param newDeletedData deleted model object data to keep from garbage collection.
     */
    @objid ("f98731c8-7c84-40a6-8079-3d0995b5feef")
    public void addEvent(Collection<IAction> actions, Collection<ISmObjectData> newDeletedData) {
        final EventRequest req = new EventRequest(actions, newDeletedData);
        
        // Atomically replace previous queue with a copy with our request added
        Queue<EventRequest> oldQueue = this.svcData.requestQueue.getAndUpdate(new UnaryOperator<Queue<EventRequest>>() {
            @Override
            public Queue<EventRequest> apply(Queue<EventRequest> t) {
                Queue<EventRequest> q = t == null ? new LinkedList<>() : new LinkedList<>(t);
                q.add(req);
                return q;
            }
        });
        
        if (oldQueue == null) {
            this.svcData.schedulerService.schedule(new EventRequestProcessor(this.svcData), 500, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Indicates that too much model change event are waiting for being fired.
     * <p>
     * This exception should never be encountered, and indicates there is a bug
     * in {@link ModelRefresher}.
     */
    @objid ("79681cfd-abbd-4f3f-bc1e-0e361150bc8b")
    @SuppressWarnings("serial")
    public static class EventQueueOverflowError extends Error {
        /**
         * Constructs a new EventQueueOverflowError with the specified detail message.  The
         * cause is not initialized, and may subsequently be initialized by
         * a call to {@link #initCause}.
         * @param   message   the detail message. The detail message is saved for
         * later retrieval by the {@link #getMessage()} method.
         */
        @objid ("cc842533-7da9-420f-ae5a-84bb58c345c7")
        public EventQueueOverflowError(String message) {
            super(message);
        }

    }

    /**
     * Service data model shared with inner classes.
     */
    @objid ("1c6fd857-ff8f-4d9d-9b27-689eb8fae2cc")
    private static class SvcData {
        @objid ("a8d4d6ac-42ee-11e2-91c9-001ec947ccaf")
         final ModelChangeSupport changeSupport;

        @objid ("a39df791-cc1e-4607-8ede-d6272b63e4df")
         final ScheduledExecutorService schedulerService;

        @objid ("85c5aba4-9774-430e-8dc0-02210f6f087f")
         final ITransactionSupport transactionSupport;

        /**
         * Event processing requests queue.
         */
        @objid ("13862c79-3b49-4c78-9035-60f4876e34e3")
         final AtomicReference<Queue<EventRequest>> requestQueue = new AtomicReference<>(null);

        @objid ("a0263671-c533-44a2-a5f7-2ddb983d49a2")
        public SvcData(ModelChangeSupport changeSupport, ScheduledExecutorService schedulerService, ITransactionSupport transactionSupport) {
            this.changeSupport = changeSupport;
            this.schedulerService = schedulerService;
            this.transactionSupport = transactionSupport;
        }

    }

    /**
     * Request to process a model refresh.
     * <p>
     * This is an immutable data structure.
     */
    @objid ("97c80d8d-9ad7-4d65-8fff-1b1375fbb2e2")
    private static class EventRequest {
        @objid ("d63f4c8b-b0e0-49d8-a721-6386ccb44314")
         final Collection<IAction> actions;

        @objid ("e5c6bd7f-d195-4732-b873-1d433ad2612d")
         final Collection<ISmObjectData> newDeletedData;

        @objid ("703a90e9-4d64-4340-8a03-f0f96fc0bfca")
        public EventRequest(Collection<IAction> actions, Collection<ISmObjectData> newDeletedData) {
            this.actions = actions;
            this.newDeletedData = newDeletedData;
        }

    }

    /**
     * Model refresh event request processor.
     * <p>
     * Run by an {@link ScheduledExecutorService} in another thread.
     */
    @objid ("b06c9ef3-dbd8-4dcd-889e-b4cb9fa364c4")
    private static class EventRequestProcessor implements Runnable {
        @objid ("7112a4ef-2b56-4b80-81a3-35de896262dd")
        private int actionCount;

        @objid ("9f0f21ad-3ca4-47d9-ba58-3ea3c389f41e")
        private final EventFactory eventFactory = EventFactory.createEvent(ChangeCause.REPOSITORY);

        /**
         * To prevent model object data from being garbaged until change event is sent.
         */
        @objid ("7e2f241a-3ccb-49bf-8d68-492c2438a89b")
        private final Collection<Collection<ISmObjectData>> oldDeletedData = new ArrayList<>(3);

        @objid ("a53cc2ac-f142-4a9b-8715-fd2ec774556e")
        private final SvcData svcData;

        @objid ("a7142b06-97cc-4e6c-98aa-89d05fdfecb7")
        public EventRequestProcessor(SvcData svcData) {
            this.svcData = svcData;
        }

        @objid ("9094afaf-e831-490c-b684-d20b62cb7f21")
        @Override
        public void run() {
            // Get the current queue (may not be null here)
            // Allow another processor to be scheduled
            Queue<EventRequest> queue = this.svcData.requestQueue.getAndSet(null);
            
            EventRequest req;
            while ((req = queue.poll()) != null) {
                processEventRequest(req);
            }
            
            // Prepare the model change event
            this.eventFactory.postProcess();
            
            // Fire the event
            this.svcData.transactionSupport.asyncExec(new Runnable() {
                @Override
                public void run() {
                    doFireRefresh();
                }
            });
        }

        @objid ("886b48b0-c4d7-4aac-b326-92d7f1bb2733")
        private void processEventRequest(EventRequest req) {
            // Sanity check
            //if (this.actionCount > 100000)
            //    throw new EventQueueOverflowError(this.actionCount+" actions waiting for process.");
            
            this.oldDeletedData.add(req.newDeletedData);
            
            // Process recorded events
            for (IAction  a: req.actions) {
                this.eventFactory.process(a);
                this.actionCount ++;
            }
        }

        /**
         * Fires the model change event a the status change event
         * from the current event factory.
         * <p>
         * This method should be called when no model transaction is run.
         */
        @objid ("02534050-fc25-4cc2-a28a-87da3cf3d66d")
        private void doFireRefresh() {
            IModelChangeEvent modelEvent = this.eventFactory.getEvent();
            IStatusChangeEvent statusEvent = this.eventFactory.getStatusEvent();
            if (!modelEvent.isEmpty()) {
                this.svcData.changeSupport.fireModelChangeListeners(modelEvent);
            }
            if (! statusEvent.isEmpty()) {
                this.svcData.changeSupport.fireStatusChangeListeners(statusEvent);
            }
        }

    }

}
