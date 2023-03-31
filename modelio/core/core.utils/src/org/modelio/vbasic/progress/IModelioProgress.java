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
package org.modelio.vbasic.progress;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * The <code>IModelioProgress</code> interface is implemented
 * by objects that monitor the progress of an activity; the methods
 * in this interface are invoked by code that performs the activity.
 * <p>
 * All activity is broken down into a linear sequence of tasks against
 * which progress is reported. When a task begins, a <code>beginTask(String, int)
 * </code> notification is reported, followed by any number and mixture of
 * progress reports (<code>worked()</code>) and subtask notifications
 * (<code>subTask(String)</code>).  When the task is eventually completed, a
 * <code>done()</code> notification is reported.  After the <code>done()</code>
 * notification, the progress monitor cannot be reused;  i.e., <code>
 * beginTask(String, int)</code> cannot be called again after the call to
 * <code>done()</code>.
 * </p>
 * <p>
 * A request to cancel an operation can be signaled using the
 * <code>setCanceled</code> method.  Operations taking a progress
 * monitor are expected to poll the monitor (using <code>isCanceled</code>)
 * periodically and abort at their earliest convenience.  Operation can however
 * choose to ignore cancelation requests.
 * </p>
 * <p>
 * Since notification is synchronous with the activity itself, the listener should
 * provide a fast and robust implementation. If the handling of notifications would
 * involve blocking operations, or operations which might throw uncaught exceptions,
 * the notifications should be queued, and the actual processing deferred (or perhaps
 * delegated to a separate thread).
 * </p><p>
 * Clients may implement this interface.
 * </p>
 */
@objid ("0aa3ea6a-a4f7-11e1-aa98-001ec947ccaf")
public interface IModelioProgress {
    /**
     * Constant indicating an unknown amount of work.
     */
    @objid ("c98a8cba-a5a3-11e1-aa98-001ec947ccaf")
    public static final int UNKNOWN = -1;

    /**
     * Notifies that the main task is beginning.  This must only be called once
     * on a given progress monitor instance.
     * @param name the name (or description) of the main task
     * @param totalWork the total number of work units into which
     * the main task is been subdivided. If the value is <code>UNKNOWN</code>
     * the implementation is free to indicate progress in a way which
     * doesn't require the total number of work units in advance.
     */
    @objid ("c98a8cbd-a5a3-11e1-aa98-001ec947ccaf")
    void beginTask(String name, int totalWork);

    /**
     * Notifies that the work is done; that is, either the main task is completed
     * or the user canceled it. This method may be called more than once
     * (implementations should be prepared to handle this case).
     */
    @objid ("c98a8cc1-a5a3-11e1-aa98-001ec947ccaf")
    void done();

    /**
     * Internal method to handle scaling correctly. This method
     * must not be called by a client. Clients should
     * always use the method </code>worked(int)</code>.
     * @param work the amount of work done
     */
    @objid ("c98a8cc3-a5a3-11e1-aa98-001ec947ccaf")
    void internalWorked(double work);

    /**
     * Returns whether cancelation of current operation has been requested.
     * Long-running operations should poll to see if cancelation
     * has been requested.
     * @see #setCanceled(boolean)
     * @return <code>true</code> if cancellation has been requested,
     * and <code>false</code> otherwise
     */
    @objid ("c98a8cc6-a5a3-11e1-aa98-001ec947ccaf")
    boolean isCanceled();

    /**
     * Sets the cancel state to the given value.
     * @see #isCanceled()
     * @param value <code>true</code> indicates that cancelation has
     * been requested (but not necessarily acknowledged);
     * <code>false</code> clears this flag
     */
    @objid ("c98a8cc9-a5a3-11e1-aa98-001ec947ccaf")
    void setCanceled(boolean value);

    /**
     * Sets the task name to the given value. This method is used to
     * restore the task label after a nested operation was executed.
     * Normally there is no need for clients to call this method.
     * @see #beginTask(java.lang.String, int)
     * @param name the name (or description) of the main task
     */
    @objid ("c98a8ccc-a5a3-11e1-aa98-001ec947ccaf")
    void setTaskName(String name);

    /**
     * Notifies that a subtask of the main task is beginning.
     * Subtasks are optional; the main task might not have subtasks.
     * @param name the name (or description) of the subtask
     */
    @objid ("c98a8ccf-a5a3-11e1-aa98-001ec947ccaf")
    void subTask(String name);

    /**
     * Notifies that a given number of work unit of the main task
     * has been completed. Note that this amount represents an
     * installment, as opposed to a cumulative amount of work done
     * to date.
     * @param work a non-negative number of work units just completed
     */
    @objid ("c98a8cd2-a5a3-11e1-aa98-001ec947ccaf")
    void worked(int work);
}

