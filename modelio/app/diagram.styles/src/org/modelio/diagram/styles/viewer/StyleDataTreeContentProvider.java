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

import java.util.List;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

@objid ("85fe2eec-d67b-47b8-bb6d-bc02be08537c")
class StyleDataTreeContentProvider implements ITreeContentProvider {
    @objid ("6219ee4c-2114-4c80-b4b7-54da9f8b20dc")
    private StyleEditPanelUIData data;

    @objid ("2562b2f7-e35b-4db2-812a-0147a9b7bb0c")
    private static final Object[] NO_OBJECTS = new Object[0];

    @objid ("4afa955e-0a95-4985-aa4d-a97441d741dd")
    public StyleDataTreeContentProvider(StyleEditPanelUIData data) {
        this.data = data;
    }

    @objid ("2d62a5d5-ae81-4809-b9f0-548c2ee1db19")
    @Override
    public void dispose() {
        this.data = null;
    }

    @objid ("b052d48d-3cb0-4268-bc5a-3bd9f92fbc10")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.data = (StyleEditPanelUIData) newInput;
    }

    @objid ("5612302f-43b4-4350-9f24-032582242141")
    @Override
    public Object[] getElements(Object inputElement) {
        // If there is no styledata, return 'no objects'
        if (this.data == null || this.data.getStyleData() == null) {
            return StyleDataTreeContentProvider.NO_OBJECTS;
        }
        
        ISymbolViewModel styleTreeModel = this.data.getStyleTreeModel();
        List<? extends ISymbolViewItem> elements = styleTreeModel.getElements();
        return elements.toArray();
    }

    @objid ("858fb257-c267-487b-bd54-8f8538f72f55")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof ISymbolViewItem) {
            ISymbolViewItem parentItem = (ISymbolViewItem) parentElement;
            return this.data.getStyleTreeModel().getChildren(parentItem).toArray();
        }
        return StyleDataTreeContentProvider.NO_OBJECTS;
    }

    @objid ("76016c6c-cd61-4ead-850d-7a28c9fec1b9")
    @Override
    public Object getParent(Object element) {
        if (this.data != null && element instanceof ISymbolViewItem) {
            ISymbolViewItem item = (ISymbolViewItem) element;
            return this.data.getStyleTreeModel().getParent(item);
        }
        return null;
    }

    @objid ("d44f5e1a-174d-45ed-aeb9-68a4f2a6d0dd")
    @Override
    public boolean hasChildren(Object element) {
        if (this.data != null && element instanceof ISymbolViewItem) {
            ISymbolViewItem parentItem = (ISymbolViewItem) element;
            return !this.data.getStyleTreeModel().getChildren(parentItem).isEmpty();
        }
        return false;
    }

    @objid ("036c837b-3ff5-43b5-971e-2bcabd22208b")
    private static Stream<ISymbolViewItem> getSelfAndChildren(ISymbolViewModel styleTreeModel, ISymbolViewItem item) {
        return Stream.concat(Stream.of(item),
                        styleTreeModel.getChildren(item)
                                .stream()
                                .flatMap(child -> getSelfAndChildren(styleTreeModel, child)));
    }

}
