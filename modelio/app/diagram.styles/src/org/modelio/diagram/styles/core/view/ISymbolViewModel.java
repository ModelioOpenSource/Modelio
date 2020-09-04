/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.styles.core.view;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Content provider for the symbol view that allow edition of the element style.
 * @author cma
 * @since 3.7
 */
@objid ("6c23f4d7-7e79-421b-b9a6-fe035898b917")
public interface ISymbolViewModel {
    /**
     * A constant for an empty symbol model.
     */
    @objid ("dec86b42-b08f-4c29-903b-bee032a9c2fe")
    public static final ISymbolViewModel EMPTY = new Empty();

    /**
     * Get children entry of the given entry.
     * @param entry a symbol view item
     * @return the children items, never null.
     */
    @objid ("0c3f05f8-8d50-4157-9f8e-188d96d4223e")
    List<? extends ISymbolViewItem> getChildren(ISymbolViewItem entry);

    /**
     * Get the root symbol items.
     * @param input the current selection
     * @return the root items
     */
    @objid ("e3003870-8441-4794-9492-d2e79118df97")
    List<? extends ISymbolViewItem> getElements();

    /**
     * @return a label that describe the edited element.
     */
    @objid ("e61b216b-fe94-433f-9995-e050f0a648c6")
    String getLabel();

    /**
     * @param entry a symbol view item.
     * @return the parent item, null if the entry is a root one.
     */
    @objid ("282ec0e9-9678-439d-9442-fd88f31a3fba")
    ISymbolViewItem getParent(ISymbolViewItem entry);

    /**
     * Get only visible children entry of the given entry.
     * @param entry a symbol view item
     * @return the visible children items, never null.
     */
    @objid ("6b5d331b-b8ba-4920-b576-b983a79fa4c7")
    List<? extends ISymbolViewItem> getVisibleChildren(ISymbolViewItem entry);

    @objid ("ef95bee9-eb4b-40da-8ef1-82cb9b0ca6db")
    static class Empty implements ISymbolViewModel {
        @objid ("41b88af3-b1dc-48ce-b7bb-8e606663082b")
        @Override
        public List<? extends ISymbolViewItem> getElements() {
            return Collections.emptyList();
        }

        @objid ("1aaaf5b9-1204-4566-80fb-2a249adbc0f4")
        @Override
        public List<? extends ISymbolViewItem> getChildren(ISymbolViewItem entry) {
            return Collections.emptyList();
        }

        @objid ("64faf982-7388-4857-82fd-c33227c5bc58")
        @Override
        public ISymbolViewItem getParent(ISymbolViewItem entry) {
            return null;
        }

        @objid ("c8b6ab66-4f5e-484d-a97f-471cded54b34")
        @Override
        public String getLabel() {
            return "<empty>";
        }

        @objid ("8784c568-00fc-4280-9c30-2d16f849ef90")
        @Override
        public List<? extends ISymbolViewItem> getVisibleChildren(ISymbolViewItem entry) {
            return Collections.emptyList();
        }

    }

}
