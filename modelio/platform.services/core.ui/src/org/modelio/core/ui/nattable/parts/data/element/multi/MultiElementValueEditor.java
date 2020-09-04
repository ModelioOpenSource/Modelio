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

package org.modelio.core.ui.nattable.parts.data.element.multi;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.core.ui.panels.selectelements.SelectElementsPanel;
import org.modelio.model.search.engine.searchers.model.ModelSearchCriteria;
import org.modelio.model.search.engine.searchers.model.ModelSearchEngine;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link ICellEditor} implementation for multi-element edition. Uses a {@link SelectElementsPanel} component as editor control.
 */
@objid ("7021c41c-75dc-4439-a254-c27a1b717bce")
public class MultiElementValueEditor extends AbstractCellEditor {
    @objid ("5d4db5e1-353d-4ad0-8b0e-4894b47cef59")
    private SelectElementsPanel selectElementsPanel;

    @objid ("22dd0f90-4415-4fc5-9db6-472ef69fbebf")
    private ICoreSession session;

    /**
     * Build a new editor.
     * 
     * @param session a model session, needed to look for elements.
     */
    @objid ("1fba6f04-4a81-4268-a9fe-b75ee9f3a7b9")
    public MultiElementValueEditor(ICoreSession session) {
        this.session = session;
    }

    @objid ("595703cd-0a93-46d5-a8a6-6b93f71a6404")
    @Override
    public void close() {
        super.close();
        this.selectElementsPanel.dispose();
    }

    @objid ("e863fe2a-238c-48b4-a4e0-31f2da86a187")
    @Override
    public Composite createEditorControl(Composite parentComposite) {
        return this.selectElementsPanel.createPanel(parentComposite);
    }

    @objid ("b4d07241-bcc7-4d09-8d98-995d733e3145")
    @Override
    public Control getEditorControl() {
        return this.selectElementsPanel.getPanel();
    }

    @objid ("72129cd6-d392-46b6-a4d9-a2524dc4a17e")
    @Override
    public Object getEditorValue() {
        return this.selectElementsPanel.getInput();
    }

    @objid ("fce1af4b-bb96-4ddd-b7c7-40be37e0aacf")
    @Override
    public boolean openInline(IConfigRegistry registry, List<String> configLabels) {
        return false;
    }

    @objid ("38fbcb46-f56a-49ba-96fb-ef26ee6ce4be")
    @Override
    public void setEditorValue(Object value) {
        this.selectElementsPanel.setInput(value);
    }

    @objid ("da04d343-f2c7-48f3-9876-11d2b9c3b596")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        IMultiElementNatValue value = originalCanonicalValue instanceof IMultiElementNatValue ? (IMultiElementNatValue) originalCanonicalValue : (IMultiElementNatValue) this.layerCell.getDataValue();
        
        ModelSearchCriteria c = new ModelSearchCriteria();
        for (Class<? extends MObject> mClass : value.getAllowedClasses()) {
            c.addMetaclass(mClass);
        }
        c.setFilter(value.getElementFilter());
        c.setCaseSensitive(false);
        c.setIncludeRamc(true);
        
        this.selectElementsPanel = new SelectElementsPanel(this.session, new ModelSearchEngine(), c, SelectElementsPanel.SearchMode.USER);
        this.selectElementsPanel.setInput(value.getValue());
        return createEditorControl(parentComposite);
    }

    @objid ("41ebaf0e-0b41-4eec-975f-a345e5b8db14")
    @Override
    public boolean commit(MoveDirectionEnum direction, boolean closeAfterCommit, boolean skipValidation) {
        boolean commit = super.commit(direction, closeAfterCommit, skipValidation);
        
        if (commit) {
            this.layerCell.getLayer().doCommand(new SelectCellCommand(
                    this.layerCell.getLayer(),
                    this.layerCell.getColumnPosition(),
                    this.layerCell.getRowPosition() + 1,
                    false,
                    false));
        }
        return commit;
    }

}
