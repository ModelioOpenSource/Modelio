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

package org.modelio.ui.progress;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * {@link IModelioProgress} implemented by a wrapped {@link IProgressMonitor}.
 */
@objid ("37e6206a-91b9-4a47-8cc1-65f4dae8a070")
public class ModelioProgressAdapter implements IModelioProgress {
    @objid ("12604f9b-1d5b-478f-b161-1bfe2cff8a2d")
    private IProgressMonitor wrapped;

    /**
     * <p>Converts an unknown (possibly <code>null</code>) Eclipse {@link IProgressMonitor} into a Modelio {@link SubProgress} allocated
     * with the given number of ticks. It is not necessary to call done() on the result,
     * but the caller is responsible for calling done() on the argument. Calls beginTask
     * on the argument.</p>
     * 
     * <p>This method should generally be called at the beginning of a method that accepts
     * an IModelioProgress in order to convert the IModelioProgress into a SubProgress.</p>
     * 
     * @param initialMonitor to convert into a SubProgress instance or null. If given a <code>null</code> argument,
     * the resulting SubProgress will not report its progress anywhere.
     * @param taskName user readable name to pass to monitor.beginTask(). Never <code>null</code>.
     * @param work initial number of ticks to allocate for children of the SubProgress
     * @return a new SubProgress instance that is a child of the given monitor
     */
    @objid ("9f57bc00-a611-4fcb-b62f-8a88b790618a")
    public static SubProgress convert(IProgressMonitor initialMonitor, String taskName, int work) {
        return SubProgress.convert(new ModelioProgressAdapter(initialMonitor), taskName, work);
    }

    /**
     * <p>Converts an unknown (possibly <code>null</code>) Eclipse {@link IProgressMonitor} into a Modelio {@link SubProgress} allocated
     * with the given number of ticks. It is not necessary to call done() on the result,
     * but the caller is responsible for calling done() on the argument. Calls beginTask
     * on the argument.</p>
     * 
     * <p>This method should generally be called at the beginning of a method that accepts
     * an IModelioProgress in order to convert the IModelioProgress into a SubProgress.</p>
     * 
     * @param initialMonitor to convert into a SubProgress instance or null. If given a <code>null</code> argument,
     * the resulting SubProgress will not report its progress anywhere.
     * @param work initial number of ticks to allocate for children of the SubProgress
     * @return a new SubProgress instance that is a child of the given monitor
     */
    @objid ("f131dd45-3ed0-4b64-8b0e-621fa666554f")
    public static SubProgress convert(IProgressMonitor initialMonitor, int work) {
        return SubProgress.convert(new ModelioProgressAdapter(initialMonitor), work);
    }

    /**
     * Initialize a Modelio progress monitor from an Eclipse progress monitor.
     * 
     * @param wrapped the wrapped Eclipse progress monitor.
     */
    @objid ("1dcca0a3-3a40-490a-921e-45366db1102b")
    public ModelioProgressAdapter(IProgressMonitor wrapped) {
        if (wrapped == null) {
            this.wrapped = new NullProgressMonitor();
        } else {
            this.wrapped = wrapped;
        }
    }

    @objid ("c9527d61-af33-4391-b5a1-76692048da06")
    @Override
    public void beginTask(String name, int totalWork) {
        this.wrapped.beginTask(name, totalWork);
    }

    @objid ("6cae1db6-336c-492b-8266-968975012e76")
    @Override
    public void done() {
        this.wrapped.done();
    }

    @objid ("25cd6e16-7027-43b6-b0e0-c96bf403b939")
    @Override
    public void internalWorked(double work) {
        this.wrapped.internalWorked(work);
    }

    @objid ("4cdbbbe8-48b6-48fc-b26e-631620070617")
    @Override
    public boolean isCanceled() {
        return this.wrapped.isCanceled();
    }

    @objid ("b64ed6f8-61e8-4805-a82a-38f511ddaefd")
    @Override
    public void setCanceled(boolean value) {
        this.wrapped.setCanceled(value);
    }

    @objid ("4023c42b-f88a-42f2-a9ba-73a713e0feab")
    @Override
    public void setTaskName(String name) {
        // Empty string is the default task name in SubProgress (and SubMonitor), 
        // don't propagate them to avoid letting them override an already set task name.
        if (name != null && !name.isEmpty()) {
            this.wrapped.setTaskName(name);
        }
    }

    @objid ("84dbf2a4-1c84-4124-833f-127edc94bb07")
    @Override
    public void subTask(String name) {
        this.wrapped.subTask(name);
    }

    @objid ("bf610a64-3b43-424e-87e1-66b2e1aed62b")
    @Override
    public void worked(int work) {
        this.wrapped.worked(work);
    }

}
