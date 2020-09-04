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
 * Handler that defragment and compresses EXML indexes for the selected
 * fragments.
 */
@objid ("b7330f51-f084-44ab-9139-4c7a47436630")
@SuppressWarnings ("restriction")
public class DefragIndexesHandler implements IRunnableWithProgress {
    @objid ("56d6ace8-cbdb-4556-8356-987812c30f9d")
    private AbstractExmlRepository repo;

    @objid ("65639072-9ebe-43e0-acab-064aef0ee5e3")
    @CanExecute
    boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ITreeSelection sel) {
        for (Object o : sel.toList()) {
            if (!(o instanceof IProjectFragment)) {
                return false;
            }
        
            IProjectFragment fragment = (IProjectFragment) o;
            if (!((fragment.getRepository() instanceof AbstractExmlRepository) && (fragment.getState() == FragmentState.UP_FULL))) {
                return false;
            }
        }
        return true;
    }

    @objid ("aba615f4-5dfa-4885-b2b7-ed4afda250fb")
    @Execute
    void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ITreeSelection sel, IModelioProgressService progressSvc, StatusReporter statusReporter) {
        for (Object o : sel.toList()) {
            IProjectFragment fragment = (IProjectFragment) o;
            this.repo = (AbstractExmlRepository) fragment.getRepository();
        
            String title = AdmTool.I18N.getMessage("DefragIndexesHandler.Title");
            try {
                progressSvc.run(title, true, false, this);
            } catch (InvocationTargetException e) {
                statusReporter.show(StatusReporter.ERROR, AdmTool.I18N.getMessage("DefragIndexesHandler.Failed"), e.getCause());
            } catch (InterruptedException e) {
                statusReporter.show(StatusReporter.ERROR, AdmTool.I18N.getMessage("DefragIndexesHandler.Failed"), e);
            }
        }
    }

    @objid ("ec4c626f-aa28-4184-8039-9c0d1ccac26d")
    @Override
    public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
        try {
            this.repo.getMaintenance().compressIndexes(new ModelioProgressAdapter(monitor));
        } catch (FileSystemException e) {
            throw new InvocationTargetException(e, FileUtils.getLocalizedMessage(e));
        } catch (IOException e) {
            throw new InvocationTargetException(e, e.getLocalizedMessage());
        } catch (RuntimeException e) {
            throw new InvocationTargetException(e, e.toString());
        }
    }

}
