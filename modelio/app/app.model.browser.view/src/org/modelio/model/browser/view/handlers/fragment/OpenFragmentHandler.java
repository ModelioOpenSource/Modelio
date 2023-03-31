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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.gproject.FragmentMigrationNeededException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Handler that migrates selected fragments.
 */
@objid ("badb3b8f-113f-4c7c-adf5-321c0692a501")
public class OpenFragmentHandler {
    @objid ("9909b7c3-56bc-4aff-bb96-29a0f56382ad")
    @Execute
    void execute(IModelioProgressService progressService, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, final IProjectService projServ, @Named(IServiceConstants.ACTIVE_SHELL) final Shell parentShell, final IEclipseContext context) {
        CompletableFuture<IStatus> result = new CompletableFuture<>();
        
        List<IGModelFragment> frags = SelectionHelper.toList(selection, IGModelFragment.class);
        frags.removeIf(f -> ! isOpenable(f));
        
        String resultTitle = BrowserViewActivator.I18N.getMessage("OpenFragmentHandler.result.title", frags.size());
        
        IRunnableWithProgress runnable = monitor -> {
            String taskName = BrowserViewActivator.I18N.getMessage("OpenFragmentHandler.monitor.title", frags.size());
            SubProgress parentMon = ModelioProgressAdapter.convert(monitor, taskName, frags.size() * 10);
        
        
            String resultMsg = BrowserViewActivator.I18N.getMessage("OpenFragmentHandler.result.message", frags.size());
            MultiStatus globRes = new MultiStatus(
                    BrowserViewActivator.PLUGIN_ID, 0,
                    resultMsg,
                    null);
        
        
            for (IGModelFragment f : frags) {
                if (monitor.isCanceled()) {
                    break;
                }
        
                try {
                    BrowserViewActivator.LOG.info("Trying to open ''{0}'' {1} model fragment...", f.getId(), f.getType());
        
                    f.mount(parentMon.newChild(10));
                    String successMessage = BrowserViewActivator.I18N.getMessage("OpenFragmentHandler.fragment.success", f.getId());
                    globRes.add(new Status(Status.OK, BrowserViewActivator.PLUGIN_ID, successMessage));
        
                    BrowserViewActivator.LOG.info("  Opened ''{0}'' {1} model fragment.", f.getId(), f.getType());
                } catch (GPartException e) {
                    BrowserViewActivator.LOG.warning("  FAILED opening ''{0}'' {1} model fragment: {2}.", f.getId(), f.getType(), e.getLocalizedMessage());
                    globRes.add(new Status(Status.ERROR, BrowserViewActivator.PLUGIN_ID, e.getLocalizedMessage(), e));
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
            String msg = BrowserViewActivator.I18N.getMessage("OpenFragmentHandler.error.msg", e.getCause().toString());
        
            ErrorDialog.openError(parentShell,
                    resultTitle,
                    msg,
                    new Status(IStatus.ERROR, BrowserViewActivator.PLUGIN_ID, e.getCause().toString(), e));
        
        } catch (InterruptedException e) {
            BrowserViewActivator.LOG.info("Fragment opening cancelled");
            BrowserViewActivator.LOG.debug(e);
        }
        
    }

    @objid ("a0b0bd3c-3b9b-4124-a727-d36ac319b8b6")
    private static boolean isOpenable(IGModelFragment f) {
        switch (f.getState().getValue()) {
        case DOWN:
            // There is another command to migrate the fragment.
            return ! ( f.getState().getDownError() instanceof FragmentMigrationNeededException);
        case INSTALLED:
        case INSTANTIATED:
            return true;
        case MOUNTED:
        case MOUNTING:
        case UNINSTALLED:
        default:
            return false;
        }
        
    }

    @objid ("e29ea861-605f-4712-9164-30cea7904156")
    @CanExecute
    boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (! isVisible(selection) ) {
            return false;
        }
        return SelectionHelper.toStream(selection, IGModelFragment.class)
        .allMatch(OpenFragmentHandler::isOpenable);
        
    }

    @objid ("72d3a213-6a94-4de7-9cdb-2674b04cbc00")
    @Evaluate
    boolean isVisible(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        return SelectionHelper.containsOnly(selection, IGModelFragment.class);
    }

}
