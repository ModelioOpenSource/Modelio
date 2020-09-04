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

package org.modelio.diagram.styles.viewer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

@objid ("ba3cda96-9a5c-49fc-abac-320384937daa")
class StyleDataTableContentProvider implements ITreeContentProvider {
    @objid ("66597680-ece8-479f-85dc-97feaddce2e9")
    private StyleEditPanelUIData data;

    @objid ("1476b010-96f9-4d57-a937-9c97428ee5b8")
    private static final Object[] NO_OBJECTS = new Object[0];

    @objid ("dd52e01d-6e0f-44df-8a69-9c5e707344e0")
    public StyleDataTableContentProvider(StyleEditPanelUIData data) {
        this.data = data;
    }

    @objid ("02119ed7-45b4-49b4-8b93-700daef53fd2")
    @Override
    public void dispose() {
        this.data = null;
    }

    @objid ("72d7daa2-abe1-4664-a323-e1f4093149fe")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.data = (StyleEditPanelUIData) newInput;
    }

    @objid ("5cae476b-3b9b-46fe-8e0c-314ad310a7e1")
    @Override
    public Object[] getElements(Object inputElement) {
        // If there is no styledata, return 'no objects'
        if (this.data == null || this.data.getStyleData() == null) {
            return StyleDataTableContentProvider.NO_OBJECTS;
        }
        
        ISymbolViewModel styleTreeModel = this.data.getStyleTreeModel();
        List<? extends ISymbolViewItem> elements = styleTreeModel != null ? styleTreeModel.getElements() : Collections.emptyList();
        return elements
                        .stream()
                        .flatMap(item -> StyleDataTableContentProvider.getSelfAndChildren(styleTreeModel, item))
                        .toArray();
    }

    @objid ("6bf1a507-4893-4e72-99fc-9ebbf85909b5")
    @Override
    public Object[] getChildren(Object parentElement) {
        return StyleDataTableContentProvider.NO_OBJECTS;
    }

    @objid ("fa52ac64-5cf0-4766-b69e-e984da9b2d96")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("6bdd1db6-9c1e-4ce5-8ea9-f24ba2c97482")
    @Override
    public boolean hasChildren(Object element) {
        return false;
    }

    @objid ("43091f13-5951-45de-9202-ad8071135325")
    private static Stream<ISymbolViewItem> getSelfAndChildren(ISymbolViewModel styleTreeModel, ISymbolViewItem item) {
        return Stream.concat(Stream.of(item),
                        styleTreeModel.getVisibleChildren(item)
                                .stream()
                                .flatMap(child -> StyleDataTableContentProvider.getSelfAndChildren(styleTreeModel, child)));
    }

}
