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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.modelio.admtool.plugin.AdmTool;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.project.AbstractGProject;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vstore.exml.common.AbstractExmlRepository;
import org.modelio.vstore.jdbm.JdbmRepository;

@objid ("44079e34-6835-4aea-8bcc-15a5721bd25d")
@SuppressWarnings("restriction")
public class DefragLocalModelHandler implements IRunnableWithProgress {
    @objid ("621c812c-17e5-4110-b11c-a4c101318929")
    private IGProject project;

    @objid ("4676a59a-9772-4024-9897-957c67c17f44")
    @Execute
    void execute(@Optional
    @Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection sel, IModelioProgressService progressSvc, StatusReporter reporter) {
        IGModelFragment frag = (IGModelFragment) sel.iterator().next();
        this.project = AbstractGProject.getProject(frag.getRoots().iterator().next());
        
        try {
            String title = AdmTool.I18N.getString("DefragLocalModelHandler.title");
            progressSvc.run(title, true, true, this);
        } catch (InvocationTargetException e) {
            AdmTool.LOG.error(e);
            reporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e.getCause());
        } catch (InterruptedException e) {
            AdmTool.LOG.error(e);
        }
        
    }

    @objid ("b0ffee2e-0057-48d1-8f65-ed0bd7ba77e6")
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        JdbmRepository nsUseRepo = (JdbmRepository) this.project.getSession().getRepositorySupport().getRepository(IRepositorySupport.REPOSITORY_KEY_LOCAL);
        try {
            monitor.setTaskName(AdmTool.I18N.getString("DefragLocalModelHandler.task"));
            nsUseRepo.getMaintenance().defragment(new ModelioProgressAdapter(monitor));
        } catch (IOException e) {
            throw new InvocationTargetException(e, e.getLocalizedMessage());
        }
        
    }

    @objid ("c9cd2290-497b-4e46-a89b-316cf0e198dd")
    @CanExecute
    boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ITreeSelection sel) {
        for (Object o : sel.toList()) {
            if (!(o instanceof IGModelFragment))
                return false;
        
            IGModelFragment fragment = (IGModelFragment) o;
            if (!((fragment.getRepository() instanceof AbstractExmlRepository) && (fragment.getState().getValue() == GPartStateEnum.MOUNTED)))
                return false;
        }
        return true;
    }

}
