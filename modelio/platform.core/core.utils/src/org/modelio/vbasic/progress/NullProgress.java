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

package org.modelio.vbasic.progress;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * A default progress monitor implementation suitable for
 * subclassing.
 * <p>
 * This implementation supports cancellation. The default
 * implementations of the other methods do nothing.
 * </p>
 */
@objid ("c98cef59-a5a3-11e1-aa98-001ec947ccaf")
public class NullProgress implements IModelioProgress {
    /**
     * Indicates whether cancel has been requested.
     */
    @objid ("c98cef5b-a5a3-11e1-aa98-001ec947ccaf")
    private volatile boolean cancelled = false;

    /**
     * Constructs a new progress monitor.
     */
    @objid ("c98cef5e-a5a3-11e1-aa98-001ec947ccaf")
    public NullProgress() {
        super();
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when a task begins.
     * @see IProgressMonitor#beginTask(String, int)
     */
    @objid ("c98cef61-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void beginTask(String name, int totalWork) {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when a task is done.
     * @see IProgressMonitor#done()
     */
    @objid ("c98cef66-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void done() {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method.
     * @see IProgressMonitor#internalWorked(double)
     */
    @objid ("c98cef69-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void internalWorked(double work) {
        // do nothing
    }

    /**
     * This implementation returns the value of the internal
     * state variable set by <code>setCanceled</code>.
     * Subclasses which override this method should
     * override <code>setCanceled</code> as well.
     * @see IProgressMonitor#isCanceled()
     * @see IProgressMonitor#setCanceled(boolean)
     */
    @objid ("c98cef6d-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public boolean isCanceled() {
        return this.cancelled;
    }

    /**
     * This implementation sets the value of an internal state variable.
     * Subclasses which override this method should override
     * <code>isCanceled</code> as well.
     * @see IProgressMonitor#isCanceled()
     * @see IProgressMonitor#setCanceled(boolean)
     */
    @objid ("c98f516a-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void setCanceled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do something
     * with the name of the task.
     * @see IProgressMonitor#setTaskName(String)
     */
    @objid ("c98f516e-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void setTaskName(String name) {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when a subtask begins.
     * @see IProgressMonitor#subTask(String)
     */
    @objid ("c98f5172-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void subTask(String name) {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when some work has been completed.
     * @see IProgressMonitor#worked(int)
     */
    @objid ("c98f5176-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void worked(int work) {
        // do nothing
    }

}
