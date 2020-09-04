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

package org.modelio.model.browser.view.handlers.fragment;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.fragment.FragmentMigrationNeededException;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.ui.progress.IModelioProgressService;

/**
 * Handler that migrates selected fragments.
 */
@objid ("a9e8696c-5c1e-4411-9e71-d9865eb9fe41")
public class MigrateFragmentHandler {
    @objid ("685c26f0-4347-4477-b706-caf446a85819")
    @Execute
    void execute(IModelioProgressService progressService, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, final IProjectService projServ, @Named(IServiceConstants.ACTIVE_SHELL) final Shell parentShell, final IEclipseContext context) {
        CompletableFuture<IStatus> result = new CompletableFuture<>();
        
        List<IProjectFragment> frags = SelectionHelper.toList(selection, IProjectFragment.class);
        frags.removeIf(f -> ! ( f.getDownError() instanceof FragmentMigrationNeededException));
        
        String resultTitle = BrowserViewActivator.I18N.getMessage("MigrateFragmentHandler.result.title", frags.size());
        
        IRunnableWithProgress runnable = monitor -> {
            String taskName = BrowserViewActivator.I18N.getMessage("MigrateFragmentHandler.monitor.title", frags.size());
            SubMonitor parentMon = SubMonitor.convert(monitor, taskName, frags.size() * 10);
        
            String resultMsg = BrowserViewActivator.I18N.getMessage("MigrateFragmentHandler.result.message", frags.size());
            MultiStatus globRes = new MultiStatus(
                    BrowserViewActivator.PLUGIN_ID, 0,
                    resultMsg,
                    null);
        
        
            for (IProjectFragment f : frags) {
                IStatus res = projServ
                        .getFragmentMigrator(context, projServ.getOpenedProject(), true)
                        .migrateFragment(f, parentShell, parentMon, 10);
                globRes.add(res);
        
                if (monitor.isCanceled()) {
                    break;
                }
            }
        
            if (globRes.getChildren().length > 1) {
                result.complete(globRes);
            } else {
                result.complete(globRes.getChildren()[0]);
            }
        };
        
        
        try {
            progressService.run(true, false, runnable);
        
            ErrorDialog.openError(parentShell,
                    resultTitle,
                    null,
                    result.get());
        
        } catch (InvocationTargetException | ExecutionException e) {
            BrowserViewActivator.LOG.error(e);
            String msg = BrowserViewActivator.I18N.getMessage("MigrateFragmentHandler.error.msg", e.getCause().toString());
        
            ErrorDialog.openError(parentShell,
                    resultTitle,
                    msg,
                    new Status(IStatus.ERROR, BrowserViewActivator.PLUGIN_ID, e.getCause().toString(), e));
        
        } catch (InterruptedException e) {
            BrowserViewActivator.LOG.info("Migration cancelled");
            BrowserViewActivator.LOG.debug(e);
        }
    }

    @objid ("1c0662e6-22a2-4505-a64a-86c446eacd2e")
    @CanExecute
    boolean isVisible(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection == null || selection.isEmpty()) {
            return false;
        }
        
        for (Object o : selection.toArray()) {
            if (o instanceof IProjectFragment) {
                IProjectFragment f = (IProjectFragment) o;
                if( !(f.getDownError() instanceof FragmentMigrationNeededException)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

}
