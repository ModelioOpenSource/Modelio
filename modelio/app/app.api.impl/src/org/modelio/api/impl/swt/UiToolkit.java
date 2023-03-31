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
package org.modelio.api.impl.swt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.swt.widgets.Composite;
import org.modelio.api.impl.swt.metaclasselector.MetaclassSelectorAdapter;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.ui.swt.IUiToolkit;
import org.modelio.api.ui.swt.metaclassselect.IMetaclassSelector;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.model.ui.swt.edition.EditorActivationStrategy;

/**
 * {@link IUiToolkit} implementation.
 * 
 * @since 3.7.1
 */
@objid ("f6c01253-110f-4cf4-acb7-68b936e20f11")
public class UiToolkit implements IUiToolkit {
    @objid ("a3f000ed-6e07-4d18-bd83-6e0628922f30")
    private final IModelioServices modelioServices;

    @objid ("41a2be95-a36e-41f2-95ab-b234575afee3")
    private final IEclipseContext eclipseContext;

    @objid ("d4e6aab7-c6b6-44cb-9374-e45b93876768")
    private IGProject gProject;

    /**
     * C'tor.
     * @param modelioServices modelio services.
     * @param gProject the currently opened project.
     * @param eclipseContext the eclipse context.
     */
    @objid ("46ef2267-6bbf-4fb0-95f2-45527958e057")
    public  UiToolkit(IModelioServices modelioServices, IGProject gProject, IEclipseContext eclipseContext) {
        this.modelioServices = modelioServices;
        this.gProject = gProject;
        this.eclipseContext = eclipseContext;
        
    }

    @objid ("9336132a-ed42-43f4-b831-b89d4518cef2")
    @Override
    public IMetaclassSelector createMetaclassSelector(Composite parent, int style) {
        return new MetaclassSelectorAdapter(parent, style, this.gProject.getSession().getMetamodel());
    }

    @objid ("6f4970e2-75f9-4a5c-b47c-c88b33801f7e")
    @Override
    public ColumnViewerEditorActivationStrategy createDefaultEditionStrategy(ColumnViewer viewer, boolean withTimeDelta) {
        return new EditorActivationStrategy(viewer, withTimeDelta);
    }

}
