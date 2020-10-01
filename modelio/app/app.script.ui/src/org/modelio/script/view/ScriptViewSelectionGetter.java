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

package org.modelio.script.view;

import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.core.events.ModelioEventTopics;

@objid ("0088354c-663d-105c-84ef-001ec947cd2a")
@Creatable
public class ScriptViewSelectionGetter {
    @objid ("00886670-663d-105c-84ef-001ec947cd2a")
    private ArrayList<Element> selectedElements = new ArrayList<>();

    @objid ("0045c694-ae18-106a-adf1-001ec947cd2a")
    private ISelection selection;

    @objid ("0088be40-663d-105c-84ef-001ec947cd2a")
    public ISelection getSelection() {
        return this.selection;
    }

    @objid ("0088eb90-663d-105c-84ef-001ec947cd2a")
    public Collection<Element> getSelectedElements() {
        return this.selectedElements;
    }

    @objid ("008925ec-663d-105c-84ef-001ec947cd2a")
    @Optional
    @Inject
    public void selectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection newSelection) {
        if (newSelection == null) {
            return;
        }
        
        this.selection = newSelection;
        
        if (newSelection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) newSelection;
            this.selectedElements = new ArrayList<>(structuredSelection.size());
        
            for (Object selectionElement : structuredSelection.toList()) {
                Element el = null;
                if (selectionElement instanceof Element) {
                    el = (Element) selectionElement;
                } else if (selectionElement instanceof IAdaptable) {
                    el = ((IAdaptable) selectionElement).getAdapter(Element.class);
                }
                
                if (el != null) {
                    this.selectedElements.add(el);
                }
            }
        }
    }

    @objid ("003cf3de-9861-1069-96f6-001ec947cd2a")
    @Optional
    @SuppressWarnings("unused")
    @Inject
    void onProjectClose(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) GProject project) {
        this.selectedElements = new ArrayList<>();
        this.selection = null;
    }

}
