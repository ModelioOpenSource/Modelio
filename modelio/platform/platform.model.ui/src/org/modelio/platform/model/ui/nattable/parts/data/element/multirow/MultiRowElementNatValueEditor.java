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
package org.modelio.platform.model.ui.nattable.parts.data.element.multirow;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.platform.model.ui.panels.selectelements.SelectElementsPanel;
import org.modelio.platform.search.engine.searchers.model.ModelSearchCriteria;
import org.modelio.platform.search.engine.searchers.model.ModelSearchEngine;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link ICellEditor} implementation for multi row - element edition. Uses a {@link SelectElementsPanel} component as editor control.
 */
@objid ("e2340005-9d5e-4c5c-9543-eb37d41d53e1")
public class MultiRowElementNatValueEditor extends AbstractCellEditor {
    @objid ("c30c01aa-b3bf-4e58-9d8e-5ca17e3032bf")
    private SelectElementsPanel selectElementsPanel;

    @objid ("fe5302ed-069d-480e-9e0c-4877d4267cda")
    private ICoreSession session;

    /**
     * Build a new editor.
     * @param session a model session, needed to look for elements.
     */
    @objid ("7f8e50a4-de0a-4a12-9f85-36c4d4da7738")
    public  MultiRowElementNatValueEditor(ICoreSession session) {
        this.session = session;
    }

    @objid ("0cafb380-ca50-4b99-bbef-b96c5c9a531d")
    @Override
    public void close() {
        super.close();
        this.selectElementsPanel.dispose();
        
    }

    @objid ("aa30dc5a-7718-4daa-812e-18442857a6e8")
    @Override
    public Composite createEditorControl(Composite parentComposite) {
        return this.selectElementsPanel.createPanel(parentComposite);
    }

    @objid ("564b4043-d132-4bed-8e8d-56fa611da193")
    @Override
    public Control getEditorControl() {
        return this.selectElementsPanel.getPanel();
    }

    @objid ("b3fd9652-c02e-4932-a07b-8c77ae19683b")
    @Override
    public Object getEditorValue() {
        return this.selectElementsPanel.getInput();
    }

    @objid ("55f64245-0842-4621-90ab-7824a1cde2ce")
    @Override
    public boolean openInline(IConfigRegistry registry, List<String> configLabels) {
        return false;
    }

    @objid ("96b2833e-665b-43d5-a828-9daafeb3016a")
    @Override
    public void setEditorValue(Object value) {
        this.selectElementsPanel.setInput(value);
    }

    @objid ("82d49350-e40f-4cfe-8b9f-0bfc7883f165")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        IMultiRowElementNatValue value = originalCanonicalValue instanceof IMultiRowElementNatValue ? (IMultiRowElementNatValue) originalCanonicalValue : (IMultiRowElementNatValue) this.layerCell.getDataValue();
        
        List<MObject> values =  value.getAllValues();
        
        ModelSearchCriteria c = new ModelSearchCriteria();
        for (Class<? extends MObject> mClass : value.getAllowedClasses()) {
            c.addMetaclass(mClass);
        }
        c.setFilter(value.getElementFilter());
        c.setCaseSensitive(false);
        c.setIncludeRamc(true);
        
        this.selectElementsPanel = new SelectElementsPanel(this.session, new ModelSearchEngine(), c, SelectElementsPanel.SearchMode.USER);
        this.selectElementsPanel.setInput(values);
        return createEditorControl(parentComposite);
    }

    @objid ("614e9f81-c1f5-489c-b19f-3c6aa65ac42c")
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
