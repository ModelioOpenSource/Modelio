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

package org.modelio.app.ui.progress;

import java.lang.reflect.InvocationTargetException;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.modelio.ui.progress.IModelioProgressService;

@objid ("6ff6d015-cef6-46ff-95c9-43757b3e5021")
public class ModelioProgressService implements IModelioProgressService {
    @objid ("8d64a393-e953-4185-b1ff-925d33950bd4")
    @Inject
    @Named(IServiceConstants.ACTIVE_SHELL)
    public Shell activeShell;

    @objid ("9e9dd821-6b03-4a88-990a-659f61ab4f52")
    public ModelioProgressService() {
    }

    @objid ("c1a2d77c-0c86-4be2-877c-2a39ddc892ee")
    @Override
    public int getLongOperationTime() {
        return 10;
    }

    @objid ("74d0b2c2-9c78-442d-8633-e6cf9e9fed9c")
    @Override
    public void registerIconForFamily(ImageDescriptor icon, Object family) {
        throw new UnsupportedOperationException();
    }

    @objid ("c888ca11-2955-45ac-9e27-e56ff8390225")
    @Override
    public void runInUI(IRunnableContext context, IRunnableWithProgress runnable, ISchedulingRule rule) throws InterruptedException, InvocationTargetException {
        throw new UnsupportedOperationException();
    }

    @objid ("e4c2f061-29a4-40a7-b9bb-facaa3c30211")
    @Override
    public Image getIconFor(Job job) {
        return null;
    }

    @objid ("f287cf0c-5221-497f-b85e-aa20dd4cde08")
    @Override
    public void busyCursorWhile(IRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException {
        new ProgressMonitorDialog(this.activeShell).run(true, false, runnable);
    }

    @objid ("1692c6ed-bf2e-4e12-b0d7-1fd8e588d5a7")
    @Override
    public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException {
        new ProgressMonitorDialog(this.activeShell).run(fork, cancelable, runnable);
    }

    @objid ("79cd239f-9b72-416e-afb1-7a638a33f8f0")
    @Override
    public void showInDialog(Shell shell, Job job) {
        throw new UnsupportedOperationException();
    }

    @objid ("25c8601f-8b6e-4742-9d25-9c5693106b17")
    @Override
    public void run(final String title, boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException {
        new ProgressMonitorDialog(this.activeShell) {
            @Override
            protected void configureShell(Shell shell) {
                super.configureShell(shell);
                shell.setText(title);
            }
        }.run(fork, cancelable, runnable);
    }

}
