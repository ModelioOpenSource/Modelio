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

package org.modelio.app.project.conf.dialog.modules.parameters.standard;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.api.module.IModule;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.api.module.parameter.IParameterGroupModel;

@objid ("dd14aa01-e2ae-4873-9fa8-bfca22ce1a09")
class ParametersContentProvider implements ITreeContentProvider {
    @objid ("ceb3d441-97bd-49df-8299-24290759612f")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do
    }

    @objid ("d91cecfb-5bc8-49ce-a502-763e8384bc05")
    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof Collection<?>) {
            return ((Collection<?>) inputElement).toArray();
        }
        return null;
    }

    @objid ("38a6333a-3dbd-4bf3-8476-020417b6a73b")
    @Override
    public Object[] getChildren(Object element) {
        if (element instanceof IModule) {
            IParameterEditionModel parametersEditionModel = ((IModule) element).getParametersEditionModel();
            if (parametersEditionModel != null) {
                return parametersEditionModel.getGroups().toArray();
            }
        } else if (element instanceof IParameterGroupModel) {
            return ((IParameterGroupModel) element).getParameters().toArray();
        }
        return null;
    }

    @objid ("e6e2450c-aa64-46e1-b05d-60dd73b96d22")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("d4a975c6-5f72-49b0-9566-b403216a6a4b")
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof IModule) {
            final IModule module = (IModule) element;
            return module.getParametersEditionModel() != null && module.getParametersEditionModel().getGroups().size() > 0;
        } else if (element instanceof IParameterGroupModel) {
            return ((IParameterGroupModel) element).getParameters().size() > 0;
        }
        return false;
    }

    @objid ("5a3560d7-ba50-4342-9ae8-65331ec44c70")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}
