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

package org.modelio.diagram.editor.popup.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Base abstract class for all creation handlers. Expects the following parameters (all but metaclass are optional):
 * <ul>
 * <li>metaclass of type String</li>
 * <li>dependency of type String</li>
 * <li>stereotype of type String</li>
 * </ul>
 * 
 * @author fpoyer
 */
@objid ("6683e70f-33f7-11e2-95fe-001ec947c8cc")
public abstract class AbstractCreateHandler {
    /**
     * The dependency linking the element to create to its composition owner.
     */
    @objid ("bf956cc7-3896-11e2-95fe-001ec947c8cc")
    protected String dependency;

    /**
     * The metaclass of the main element to create.
     */
    @objid ("bfa3bae1-3896-11e2-95fe-001ec947c8cc")
    protected String metaclass;

    /**
     * The optional stereotype to apply to the element to create.
     */
    @objid ("bfad4449-3896-11e2-95fe-001ec947c8cc")
    protected String stereotype;

    @objid ("8390afcc-949d-4052-b3ad-462a73b21bf5")
    protected ISelection selection;

    @objid ("6686496c-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(@Named ("metaclass") String metaclass, @Optional @Named ("dependency") String dependency, @Optional @Named ("stereotype") String stereotype, IProjectService projectService, @Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        // Get the value of the parameters:
        this.metaclass = metaclass;
        this.dependency = dependency;
        this.stereotype = stereotype;
        this.selection = selection;
        
        // Get the modelling session and open a transaction
        ICoreSession modelingSession = projectService.getSession();
        try (ITransaction transaction = modelingSession.getTransactionSupport().createTransaction("Create a " + this.metaclass)) {
            // Delegate the main creation task.
            MObject element = create(getSelectedElement());
        
            // Commit the transaction.
            transaction.commit();
        
            // Delegate the post creation tasks (like opening diagrams and so).
            postCommit(element);
        } catch (Exception e) {
            DiagramEditor.LOG.error("AbstractCreateElementHandler: \n\tCannot not create an instance of " + metaclass + " in dependency " + dependency + " \n\tunder: " + selection);
            DiagramEditor.LOG.error(e);
        }
        return null;
    }

    /**
     * Whether this handler is capable of executing at this time. This is determined by 2 conditions:
     * <ol>
     * <li>That there is only one object selected and it can be adapted into an instance of {@link MObject}.</li>
     * <li>Said instance of MObject can be modified.</li>
     * </ol>
     */
    @objid ("66864972-33f7-11e2-95fe-001ec947c8cc")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        boolean singleSelection = false;
        this.selection = selection;
        if (this.selection instanceof IStructuredSelection) {
            singleSelection = ((IStructuredSelection) this.selection).size() == 1;
        }
        MObject selectedElement = getSelectedElement();
        return singleSelection && selectedElement != null && selectedElement.isModifiable();
    }

    /**
     * <p>
     * Abstract method to be implemented by subclasses. This method <strong>MUST</strong> return the main created element (or <code>null</code> if nothing was created) so that it can be passed to the postCommitExecute method.
     * </p>
     * <p>
     * Note that although the modelling session is passed as parameter, a transaction has already been opened and will be committed after this method returns.
     * </p>
     * @param modelingSession the session this handler must be executed in.
     * 
     * @param selectedElement the element currently selected in the UML explorer view.
     * @return the created element (the main one if several are created at once) or <code>null</code> if nothing was created. This returned element will be passed to the postCommitExecute method.
     * @throws org.eclipse.core.commands.ExecutionException if either the request metaclass, the requested dependency or the requested stereotype are invalid or cannot be found.
     */
    @objid ("66864978-33f7-11e2-95fe-001ec947c8cc")
    protected abstract MObject create(MObject selectedElement) throws ExecutionException;

    /**
     * Get the first currently selected element .
     * 
     * @return the currently selected element .
     */
    @objid ("66864986-33f7-11e2-95fe-001ec947c8cc")
    protected MObject getSelectedElement() {
        if (this.selection instanceof IStructuredSelection) {
            if (((IStructuredSelection) this.selection).getFirstElement() instanceof IAdaptable) {
                IAdaptable adapter = (IAdaptable) ((IStructuredSelection) this.selection).getFirstElement();
                return adapter.getAdapter(MObject.class);
            }
        }
        return null;
    }

    /**
     * Subclasses should override this method to provide additional behaviours that should be only executed once the whole creation transaction has been safely committed. For example, they can ask for the opening of a diagram editor.
     * 
     * @param element the main created element.
     */
    @objid ("6686498b-33f7-11e2-95fe-001ec947c8cc")
    protected abstract void postCommit(MObject element);

}
