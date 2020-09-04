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

package org.modelio.model.browser.view.handlers.create;

import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.IElementNameEditor;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("003688c8-8902-1006-9c1d-001ec947cd2a")
public abstract class AbstractCreateElementHandler {
    @objid ("71753248-015d-429a-a7e4-c16b3c290f01")
    private static final boolean DEBUG = true;

    @objid ("9ec3b983-ccde-11e1-97e5-001ec947c8cc")
    @Inject
    protected IProjectService projectService;

    @objid ("9ec61c0b-ccde-11e1-97e5-001ec947c8cc")
    @SuppressWarnings("javadoc")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, @Named("metaclass") final String metaclassName, @Named("dependency") final String dependencyName, @Optional @Named("stereotype") final String stereotypeName, @Optional IMModelServices mmServices) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Find metaclass
        MClass metaclass = getMetaclass(metaclassName);
        if (metaclass == null) {
            return false;
        }
        
        MObject selectedOwner = getNewElementOwner(selection, metaclass, mmServices);
        if (selectedOwner == null) {
            return false;
        }
        
        // Find dependency
        MDependency dependency = getDependency(dependencyName, selectedOwner);
        if (dependency == null) {
            return false;
        }
        
        // Find stereotype
        Stereotype stereotype = null;
        if (stereotypeName != null && !stereotypeName.isEmpty()) {
            try {
                stereotype = mmServices.getStereotype("ModelerModule", stereotypeName, metaclass);
            } catch (ElementNotUniqueException e) {
                logFailure(metaclassName, dependencyName, selectedOwner, e);
                // Stereotype missing... deactivate the command
                return false;
            }
        }
        return doCanExecute(selectedOwner, metaclass, dependency, stereotype);
    }

    @objid ("0065e154-9025-1006-9c1d-001ec947cd2a")
    @SuppressWarnings("javadoc")
    @Execute
    public final void execute(final MPart part, @Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, @Named("metaclass") final String metaclassName, @Named("dependency") final String dependencyName, @Optional @Named("stereotype") final String stereotypeName, @Optional IMModelServices mmServices) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return;
        }
        
        // Find metaclass
        MClass metaclass = getMetaclass(metaclassName);
        if (metaclass == null) {
            return;
        }
        
        MObject selectedOwner = getNewElementOwner(selection, metaclass, mmServices);
        if (selectedOwner == null) {
            return;
        }
        
        // Find dependency
        MDependency dependency = getDependency(dependencyName, selectedOwner);
        if (dependency == null) {
            return;
        }
        
        // Find stereotype
        Stereotype stereotype = null;
        
        final ICoreSession session = this.projectService.getSession();
        if (stereotypeName != null && !stereotypeName.isEmpty()) {
            try {
                stereotype = mmServices.getStereotype("ModelerModule", stereotypeName, metaclass);
            } catch (ElementNotUniqueException e) {
                logFailure(metaclassName, dependencyName, selectedOwner, e);
                return;
            }
        }
        try (ITransaction t = session.getTransactionSupport().createTransaction("create " + metaclassName)) {
        
            MObject newElement = doCreate(selectedOwner, metaclass, dependency, stereotype, mmServices);
        
            if (newElement != null) {
                postCreationStep(newElement, mmServices);
                t.commit();
                postCommit(part, newElement, mmServices);
                AbstractCreateElementHandler.selectAndEditInBrowser(part, newElement);
            } else {
                logFailure(metaclassName, dependencyName, selectedOwner, null);
            }
        } catch (Exception e) {
            logFailure(metaclassName, dependencyName, selectedOwner, e);
        }
    }

    @objid ("00667542-9025-1006-9c1d-001ec947cd2a")
    protected abstract boolean doCanExecute(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype);

    @objid ("006698f6-9025-1006-9c1d-001ec947cd2a")
    protected abstract MObject doCreate(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype, IMModelServices mmServices);

    @objid ("0066e702-9025-1006-9c1d-001ec947cd2a")
    protected MDependency getDependency(final String dependencyName, MObject selectedOwner) {
        return selectedOwner.getMClass().getDependency(dependencyName);
    }

    @objid ("006716aa-9025-1006-9c1d-001ec947cd2a")
    protected final MClass getMetaclass(final String metaclassName) {
        MClass metaclass = this.projectService.getSession().getMetamodel().getMClass(metaclassName);
        if (DEBUG && metaclass == null) {
            BrowserViewActivator.LOG.error(new IllegalArgumentException(String.format("%s: Unknown metaclass '%s'.", this, metaclassName)));
        }
        return metaclass;
    }

    /**
     * Get the element under which the handler must create the asked element.
     * May return <code>null</code> in which case the command is not available.
     * <p>
     * Default implementation returns the selected element. May be redefined by sub classes.
     * 
     * @param selection the E4 selection. By default only {@link MObject} and {@link IStructuredSelection} are supported.
     * @param metaclass the metaclass of the element to create
     * @return the element that must be the owner of the created element
     */
    @objid ("9ec61c13-ccde-11e1-97e5-001ec947c8cc")
    protected MObject getNewElementOwner(final Object selection, final MClass metaclass, IMModelServices mmServices) {
        MObject selectedElement = null;
        if (selection instanceof MObject) {
            selectedElement = (MObject) selection;
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
            return SelectionHelper.getFirst(((IStructuredSelection) selection), MObject.class);
        }
        return selectedElement;
    }

    @objid ("0067631c-9025-1006-9c1d-001ec947cd2a")
    private static void selectAndEditInBrowser(MPart part, MObject elementToSelect) {
        assert (part.getObject() instanceof BrowserView) : "Handler used on a part other than BrowserView!";
        
        IPanelProvider view = ((BrowserView) part.getObject()).getContributedPanel();
        if (view instanceof IElementNameEditor) {
            ((IElementNameEditor) view).edit(elementToSelect);
        }
    }

    @objid ("00677cb2-9025-1006-9c1d-001ec947cd2a")
    protected void postCommit(MPart part, MObject element, IMModelServices mmServices) {
        // Empty default implementation.
    }

    @objid ("00679684-9025-1006-9c1d-001ec947cd2a")
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        // Empty default implementation.
    }

    @objid ("638478b9-8e68-4dbe-8509-e53694336363")
    private void logFailure(final String metaclassName, final String dependencyName, MObject selectedOwner, Throwable exception) {
        BrowserViewActivator.LOG.error("AbstractCreateElementHandler: \n\tCannot not create an instance of " + metaclassName + " in dependency " + dependencyName + " \n\tunder: " + selectedOwner);
        if (exception != null) {
            BrowserViewActivator.LOG.error(exception);
        }
    }

}
