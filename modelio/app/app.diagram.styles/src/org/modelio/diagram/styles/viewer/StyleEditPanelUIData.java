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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * The StyleEditPanelUIData is a data structure suited for the StyleEditPanel.<br>
 * It is built from a Style, the modeling session, a key filter and a editable flag, see C'Tor
 * 
 * Internally the StyleEditPanelUIData manages:
 * <ul>
 * <li>content providing (ITreeContentProvider) as the subset of StyleKeys obtained by filtering the full set of existing keys</li>
 * </ul>
 */
@objid ("695cb22d-b85f-4d15-a08c-ed633752052d")
public class StyleEditPanelUIData {
    @objid ("e3b28ae8-089e-4045-baa8-52a60bf0d07c")
    private boolean isEditable;

    @mdl.prop
    @objid ("8d02287b-b7ad-4ffb-9e95-40b1d023cf60")
    private IStyle styleData;

    @mdl.propgetter
    public IStyle getStyleData() {
        // Automatically generated method. Please do not modify this code.
        return this.styleData;
    }

    @mdl.prop
    @objid ("11b2ff96-4f6d-46f2-8d19-199ba270864f")
    private ISymbolViewModel styleTreeModel;

    @mdl.propgetter
    public ISymbolViewModel getStyleTreeModel() {
        // Automatically generated method. Please do not modify this code.
        return this.styleTreeModel;
    }

    @objid ("e1c1f711-9acb-497a-b046-c63a7db3b2b8")
    private static final Object[] NO_OBJECTS = new Object[0];

    @objid ("6f3f0855-20f5-42d3-b5e7-abed62fdee40")
    private final List<CascadedData> cascadedStyles = new ArrayList<>(3);

    /**
     * C'tor.
     * @param session the modeling session
     * 
     * @param styleData the edited style data
     * @param isEditable whether or not this style can be edited
     */
    @objid ("f2c901f6-9f09-4bd6-80d9-f3c2b6ac6721")
    public StyleEditPanelUIData(ISymbolViewModel styleTreeModel, IStyle styleData, boolean isEditable) {
        this.styleTreeModel = styleTreeModel;
        this.styleData = styleData;
        this.isEditable = isEditable;
    }

    /**
     * Empty model C'tor.
     * @param session the modeling session
     * @param editedStyle the edited style data
     */
    @objid ("fe83081b-115e-4745-863c-94bff8afc93c")
    public StyleEditPanelUIData() {
        this(ISymbolViewModel.EMPTY, null, false);
    }

    /**
     * @return <code>true</code> if the style is editable else <code>false</code>.
     */
    @objid ("6203e38d-03fe-434c-bcaf-7499ea3625e8")
    public boolean isEditable() {
        return this.isEditable;
    }

    @objid ("73d36f39-4f1b-437d-ae05-e033d2fb47e3")
    public List<CascadedData> getCascadedStyles() {
        return this.cascadedStyles;
    }

    @objid ("45fa599f-77b4-4642-b420-bd1d8cb2b27e")
    public void addCascadedStyle(IStyle baseStyle, String label) {
        this.cascadedStyles.add(new CascadedData(baseStyle, label));
    }

    @objid ("37cf71d2-1986-4bfb-ad1e-73bd402d5414")
    public static class CascadedData {
        @objid ("ef941e13-9e44-41c7-a6ba-a86fe4e61d8b")
        public final String label;

        @objid ("110690f2-6c80-4b34-bc60-6c8453708ede")
        public final IStyle data;

        @objid ("e250687c-eaf2-40e9-978b-6bb7979cf8f4")
        public CascadedData(IStyle data, String label) {
            super();
            this.data = data;
            if (label != null) {
                this.label = label;
            } else if (data instanceof NamedStyle) {
                this.label = "'"+((NamedStyle) data).getName()+"' style";
            } else {
                this.label = data.toString();
            }
        }

    }

}
