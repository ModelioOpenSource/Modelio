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

package org.modelio.audit.engine.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IControl;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Contains methods to post controls and elements to check, and to check batch of controls for each posted element.
 */
@objid ("83d78770-6ff0-473a-a679-21bf79b24fc0")
public class CheckProgram {
    @objid ("8ca7c601-6174-4b49-9df1-99e432c1c984")
    private int nPostedChecks = 0;

    @objid ("84874193-426f-4d7b-bbb2-02fc922cc161")
    public Map<String, CheckBatch> controls = new HashMap<>();

    @objid ("4d44ee16-620f-4d19-b458-37b55fdee3d6")
    public LinkedList<MObject> lifo = new LinkedList<>();

    @objid ("605c460b-e9c6-48c5-b40b-035920fd9f1e")
    public synchronized void postControl(IControl controlToAdd, MObject elementTocheck, String jobId) {
        // Cannot add a null control
        if (controlToAdd == null) {
            return;
        }
        
        // Get the current batch for this element
        // if it does not exist, create it
        String id = elementTocheck.getUuid().toString();
        CheckBatch batch = this.controls.get(id);
        if (batch == null) {
            batch = new CheckBatch(elementTocheck, jobId);
            this.controls.put(id, batch);
        }
        
        // Add the control to the element batch
        // Count the possibly added controls
        this.nPostedChecks -= batch.size();
        batch.add(controlToAdd);
        this.nPostedChecks += batch.size();
        
        // Add a LIFO entry for the element
        this.lifo.addFirst(elementTocheck);
    }

    @objid ("3d6b0ea9-de44-4bea-9709-55cc2f081f0c")
    public synchronized CheckBatch getNextControlBatch() {
        while (!this.lifo.isEmpty()) {
            // Fetch the next LIFO element
            MObject element = this.lifo.removeFirst();
        
            if (!element.isDeleted()) {
                CheckBatch batch = this.controls.get(element.getUuid().toString());
                if (batch != null) {
                    // found the batch to return
                    this.nPostedChecks = this.nPostedChecks - batch.size();
                    this.controls.remove(element.getUuid().toString());
                    return batch;
                }
            }
            // If element is no longer valid or if there is no batch for the element,
            // the lifo entry was old and the element batch has already been processed
            // let the 'while' loop try to get the next one...
        
        } // end while
        
        // the lifo is empty, there are no more batch/controls available
        return null;
    }

    @objid ("e54963bf-4a15-4aff-8d95-5b053d11152a")
    public synchronized int size() {
        return this.nPostedChecks;
    }

    @objid ("5339f61e-d383-43a5-8bac-eeae2db90009")
    public synchronized void clearCleck() {
        this.lifo.clear();
        this.nPostedChecks = 0;
    }

}
