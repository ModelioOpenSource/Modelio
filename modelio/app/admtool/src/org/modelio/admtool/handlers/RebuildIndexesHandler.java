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

package org.modelio.admtool.handlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystemException;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ITreeSelection;
import org.modelio.admtool.plugin.AdmTool;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vstore.exml.common.AbstractExmlRepository;

/**
 * Handler that rebuild EXML indexes for the selected fragment.
 */
@objid ("6a800945-af80-4db9-b99f-4a33734c6bdb")
@SuppressWarnings("restriction")
public class RebuildIndexesHandler implements IRunnableWithProgress {
    @objid ("62dd248a-ae26-4349-bfd6-a5d45b51ee52")
    private AbstractExmlRepository repo;

    @objid ("81106bbd-8668-4d0c-bc37-7a97fde41d2a")
    @CanExecute
    boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ITreeSelection sel) {
        for (Object o : sel.toList()) {
            if (!(o instanceof IProjectFragment))
                return false;
        
            IProjectFragment fragment = (IProjectFragment) o;
            if (!((fragment.getRepository() instanceof AbstractExmlRepository) && (fragment.getState() == FragmentState.UP_FULL)))
                return false;
        }
        return true;
    }

    @objid ("1249dcb4-1be6-42f1-ae88-217e83aa6cc9")
    @Execute
    void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ITreeSelection sel, IModelioProgressService progressSvc, StatusReporter statusReporter) {
        for (Object o : sel.toList()) {
            IProjectFragment fragment = (IProjectFragment) o;
            this.repo = (AbstractExmlRepository) fragment.getRepository();
        
            String title = AdmTool.I18N.getMessage("RebuildIndexesHandler.Title");
            try {
                progressSvc.run(title, true, false, this);
            } catch (InvocationTargetException e) {
                statusReporter.show(StatusReporter.ERROR, AdmTool.I18N.getMessage("RebuildIndexesHandler.Failed"), e.getCause());
            } catch (InterruptedException e) {
                statusReporter.show(StatusReporter.ERROR, AdmTool.I18N.getMessage("RebuildIndexesHandler.Failed"), e);
            }
        }
    }

    @objid ("19c03ccc-14fd-4207-b574-a8637c4de960")
    @Override
    public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
        try {
            this.repo.getMaintenance().rebuildIndexes(new ModelioProgressAdapter(monitor));
        } catch (FileSystemException e) {
            throw new InvocationTargetException(e, FileUtils.getLocalizedMessage(e));
        } catch (IOException e) {
            throw new InvocationTargetException(e, e.getLocalizedMessage());
        } catch (RuntimeException e) {
            throw new InvocationTargetException(e, e.toString());
        }
    }

}
