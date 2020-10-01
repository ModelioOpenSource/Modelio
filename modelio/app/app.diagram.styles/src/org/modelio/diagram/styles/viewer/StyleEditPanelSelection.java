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

package org.modelio.diagram.styles.viewer;

import java.util.Objects;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Contains the current selection and computations about the selection.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("f7d0868b-8bd6-4e81-9069-890db41cf8ea")
public class StyleEditPanelSelection {
    @objid ("8936d984-bd67-47e4-bc1c-7869b2d97a57")
    private boolean selectionContainsOnlySymbolViewItems;

    @objid ("a5672dbe-b10f-4ea8-8c24-41efb9d9738a")
    private boolean selectionContainsModifiedProperties;

    @objid ("b0578390-a7dc-49dc-8424-0e26b89ffd5d")
    private final Supplier<IStyle> styleGetter;

    @objid ("b33bc7e2-a3bd-4e7a-8cc7-b7becb9c1523")
    private ISelection selection;

    @objid ("40d6f8c4-3102-472f-a211-ba311e9a7d03")
    protected StyleEditPanelSelection(ColumnViewer viewer, Supplier<IStyle> styleGetter) {
        this.styleGetter = Objects.requireNonNull(styleGetter);
        
        viewer.addSelectionChangedListener(event -> updateSelection(event.getSelection()));
    }

    @objid ("2066bc1b-583f-4352-a9c6-7d436c5d0c83")
    private void updateSelection(ISelection newSelection) {
        this.selection = newSelection;
        
        this.selectionContainsOnlySymbolViewItems = SelectionHelper.containsOnly(this.selection, ISymbolViewItem.class);
        
        final IStyle editedStyle = this.styleGetter.get();
        boolean isAnyModified = SelectionHelper
                .toStream(newSelection, ISymbolViewItem.class)
                .anyMatch(item -> item.getStyleKey() != null && editedStyle.isLocal(item.getStyleKey()));
        this.selectionContainsModifiedProperties = isAnyModified;
    }

    @objid ("fcf03e14-d77e-429e-b5c8-d89f2ad8d28b")
    public ISelection getSelection() {
        return this.selection;
    }

    /**
     * @return whether the current selection contains some locally modified properties.
     */
    @objid ("d96e6c82-8478-484c-b18b-7328780541f1")
    public boolean containsModifiedProperties() {
        return this.selectionContainsModifiedProperties;
    }

    /**
     * @return whether the current selection contains only {@link ISymbolViewItem}.
     */
    @objid ("861c81d1-2e5e-48c5-b2b8-daa85fe6b3d2")
    public boolean containsOnlySymbolViewItems() {
        return this.selectionContainsOnlySymbolViewItems;
    }

}
