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
package org.modelio.diagram.elements.common.edition;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Specialization of GEF {@link DirectEditManager} that temporarily disables context key bindings during edition.<br>
 * Key bindings are restored when edition is done.
 * <p>
 * <b>Note:</b> when redefining the {@link #initCellEditor()} method, do not forget to call the super method.
 */
@objid ("7e2e72d8-1dec-11e2-8cad-001ec947c8cc")
public abstract class DirectEditManager2 extends DirectEditManager {
    @objid ("dd437a93-62fb-4fe3-a6c5-cf6022900e6a")
    private Collection<String> activeContexts;

    /**
     * Constructs a new DirectEditManager2 for the given source edit part. The cell editor will be created by
     * instantiating the type <i>editorType</i>. The cell editor will be placed using the given CellEditorLocator.
     * @param source the source edit part
     * @param editorType the cell editor type
     * @param locator the locator
     */
    @objid ("7e2e72dc-1dec-11e2-8cad-001ec947c8cc")
    public  DirectEditManager2(GraphicalEditPart source, Class<? extends CellEditor> editorType, CellEditorLocator locator) {
        super(source, editorType, locator);
    }

    /**
     * Constructs a new DirectEditManager2 for the given source edit part. The cell editor will be created by
     * instantiating the type <i>editorType</i>. The cell editor will be placed using the given CellEditorLocator.
     * @param source the source edit part
     * @param editorType the cell editor type
     * @param locator the locator
     * @param feature If the EditPart supports direct editing of multiple features, this parameter can be used to
     * discriminate among them.
     * @since phv
     */
    @objid ("7e2e72e8-1dec-11e2-8cad-001ec947c8cc")
    public  DirectEditManager2(GraphicalEditPart source, Class<? extends CellEditor> editorType, CellEditorLocator locator, Object feature) {
        super(source, editorType, locator, feature);
    }

    /**
     * <b>Note:</b> when redefining, do not forget to call the {@link DirectEditManager#initCellEditor()} super method.
     */
    @objid ("7e2e72f5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void initCellEditor() {
        // We must deactivate the active contexts during the edition, to avoid the editor's shortcuts to be triggered when entering an element's name... 
        EContextService contextService = ((IGmObject)getEditPart().getModel()).getDiagram().getModelManager().getContextService();
        
        // Store those contexts for further reactivation
        this.activeContexts = new ArrayList<>(contextService.getActiveContextIds());
        for (String contextId : this.activeContexts) {
            contextService.deactivateContext(contextId);
        }
        
    }

    @objid ("7e30d532-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void bringDown() {
        // Restore previously deactivated contexts
        EContextService contextService = ((IGmObject)getEditPart().getModel()).getDiagram().getModelManager().getContextService();
        for (String contextId : this.activeContexts) {
            contextService.activateContext(contextId);
        }
        this.activeContexts = null;
        
        super.bringDown();
        
    }

}
