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

package org.modelio.diagram.browser.model.bytype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.diagram.browser.model.core.VirtualFolder;
import org.modelio.diagram.browser.plugin.DiagramBrowser;
import org.modelio.gproject.gproject.GProject;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;

/**
 * Content provider for the ByTypeModel of the diagram browser tree.
 * <p>
 * 'by type' means that all the diagrams are organized by type.
 */
@objid ("0040cf9a-0d4f-10c6-842f-001ec947cd2a")
public class ByTypeContentProvider implements ITreeContentProvider {
    @objid ("007ad974-3566-10c7-842f-001ec947cd2a")
    protected Map<String, VirtualFolder> types = new HashMap<>();

    @objid ("007af9cc-3566-10c7-842f-001ec947cd2a")
    protected GProject project;

    @objid ("00411a9a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
        // Nothing to do, data is the project set in C'tor
    }

    @objid ("0041459c-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("00415c08-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getElements(Object inputElement) {
        updateTypes();
        return this.types.values().toArray(new Object[0]);
    }

    @objid ("004193da-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object getParent(Object child) {
        if (child instanceof AbstractDiagram) {
            String type = getType((AbstractDiagram) child);
            return this.types.get(type);
        }
        return null;
    }

    @objid ("0041be46-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getChildren(Object parent) {
        if (parent instanceof VirtualFolder)
            return ((VirtualFolder) parent).getChildren(parent);
        return new Object[0];
    }

    @objid ("0041f2ee-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof VirtualFolder)
            return ((VirtualFolder) parent).hasChildren(parent);
        return false;
    }

    /**
     * Constructor.
     */
    @objid ("00421d46-0d4f-10c6-842f-001ec947cd2a")
    public ByTypeContentProvider(GProject project) {
        this.project = project;
    }

    @objid ("004246b8-0d4f-10c6-842f-001ec947cd2a")
    private void updateTypes() {
        ICoreSession session = this.project.getSession();
        if (session == null)
            return;
        for (AbstractDiagram diagram : session.getModel().findByClass(AbstractDiagram.class, IModel.NODELETED)) {
            String type = getType(diagram);
        
            // Create a new virtual folder for types that are not already in the
            // type cache
            if (!this.types.containsKey(type)) {
                final String filteringType = type;
                VirtualFolder newFolder = new VirtualFolder(type) {
        
                    @Override
                    public Object[] getChildren(Object parentElement) {
                        ArrayList<Object> objects = new ArrayList<>();
                        for (AbstractDiagram diag : ByTypeContentProvider.this.project.getSession().getModel()
                                .findByClass(AbstractDiagram.class, IModel.NODELETED)) {
                            if (getType(diag).equals(filteringType)) {
                                objects.add(diag);
                            }
                        }
        
                        return objects.toArray();
                    }
        
                    @Override
                    public Object[] getElements(Object inputElement) {
                        return new Object[0];
                    }
        
                    @Override
                    public Object getParent(Object element) {
                        return null;
                    }
        
                    @Override
                    public boolean hasChildren(Object parentElement) {
                        return true;
                    }
        
                    @Override
                    public void dispose() {
                        // Nothing to dispose
        
                    }
        
                    @Override
                    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                        // Nothing to do
        
                    }
        
                };
                newFolder.setName(getI18nType(diagram));
                this.types.put(type, newFolder);
            }
        }
    }

    @objid ("00425586-0d4f-10c6-842f-001ec947cd2a")
    protected static String getType(AbstractDiagram diagram) {
        if (!diagram.getExtension().isEmpty()) {
            Stereotype s = diagram.getExtension().get(0);
            return ModuleI18NService.getLabel(s);
        }
        return diagram.getMClass().getName();
    }

    @objid ("004281aa-0d4f-10c6-842f-001ec947cd2a")
    private static String getI18nType(AbstractDiagram diagram) {
        // for Stereotype use stereotype label if possible
        if (!diagram.getExtension().isEmpty()) {
            Stereotype s = diagram.getExtension().get(0);
            return ModuleI18NService.getLabel(s);
        }
        // for native diagram metaclasses use a i18n label
        return DiagramBrowser.I18N.getString("$" + diagram.getMClass().getName() + ".label");
    }

}
