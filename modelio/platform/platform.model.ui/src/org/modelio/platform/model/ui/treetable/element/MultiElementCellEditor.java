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
package org.modelio.platform.model.ui.treetable.element;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.model.ui.dialogs.selectelements.SelectElementsDialog;
import org.modelio.platform.model.ui.swt.textelement.TextElement;
import org.modelio.platform.model.ui.treetable.EditableDialogCellEditor;
import org.modelio.platform.search.engine.ISearchCriteria;
import org.modelio.platform.search.engine.ISearchEngine;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * JFace Cell editor usable in JFace table and TreeTable.
 * 
 * Allows the selection of a multiple model element. The underlying edition SWT
 * component is a {@link TextElement} that can be retrieved by
 * {@link SingleElementCellEditor#getTextElement()}. The underlying TextElement
 * is highly configurable to support filtering, completion, D&D, picking...
 * 
 * 
 * @author phv
 */
@objid ("224a6a92-4bd6-42c4-9a83-f3ffa6703dfc")
public class MultiElementCellEditor extends EditableDialogCellEditor {
    @objid ("819cba54-73bd-46b9-b1cf-2d40f4a6aa45")
    private String title;

    @objid ("bf4db688-efb5-44d0-bf5b-e6d12417caaa")
    private ICoreSession session;

    @objid ("93d478e1-ff95-483c-89ac-289ab5a96019")
    private ISearchEngine searcher;

    @objid ("d74a5bf6-430b-464e-8f40-5e356e724355")
    private ISearchCriteria searchCriteria;

    @objid ("df6f4d81-2964-4c12-9203-d6e6526ae8e1")
    public  MultiElementCellEditor(Composite parent) {
        super(parent);
    }

    @objid ("fe4e3855-c08a-48f6-8721-d1774793cf96")
    public void configureSearch(ICoreSession session, ISearchEngine searcher, ISearchCriteria searchCriteria, String titleKey) {
        this.session = session;
        this.searcher = searcher;
        this.searchCriteria = searchCriteria;
        this.title = titleKey;
        
    }

    @objid ("9f9d0871-bc32-4411-a9eb-f1eef4c9caa4")
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        SelectElementsDialog dlg = new SelectElementsDialog(cellEditorWindow.getDisplay().getActiveShell(), this.session,
                this.searcher, this.searchCriteria, this.title, normalizeValue( doGetValue() ) );
        dlg.open();
        return dlg.getContent();
    }

    /**
     * Value is expected to be a List<Element>
     */
    @objid ("01edb52e-a35b-44a7-a398-b42d000e3b5f")
    @Override
    protected String getTextRepresentation(Object value) {
        List<MObject> elements = normalizeValue(value);
        StringBuilder b = new StringBuilder();
        for (MObject e : elements) {
            b.append(((Element)e).getName());
            b.append(",");
        }
        return b.toString();
    }

    @objid ("daddfbb8-a1f7-4581-a048-7ec8828a599d")
    private List<MObject> normalizeValue(Object value) {
        List<MObject> elements = new ArrayList<>();
        if (value instanceof List) {
            for (Object o : (List<?>) value) {
                if (o instanceof MObject)
                    elements.add((MObject) o);
            }
        } else {
            if (value instanceof MObject)
                elements.add((MObject) value);
        }
        return elements;
    }

}
