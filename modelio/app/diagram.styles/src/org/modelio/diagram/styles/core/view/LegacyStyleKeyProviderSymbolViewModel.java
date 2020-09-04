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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.manager.StyleManager;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * Compatibility implementation of {@link ISymbolViewModel} for an {@link AbstractStyleKeyProvider}.
 * 
 * @author cma
 * @since 3.7
 * @deprecated only for ascendant compatibility for {@link AbstractStyleKeyProvider}s that don't implement {@link AbstractStyleKeyProvider#getSymbolViewModel(IStyle, org.modelio.diagram.styles.core.IStyleProvider)}.
 */
@objid ("6c17ea72-fd9b-4ce4-8644-c3baf83fbe6f")
@Deprecated
public class LegacyStyleKeyProviderSymbolViewModel implements ISymbolViewModel {
    @objid ("b9d0b1d0-e763-41d7-be84-2de75d07b814")
    private final String label;

    /**
     * The edited element diagram style.
     * <p>
     * If null, a shared/named style is edited.
     */
    @objid ("aad8bce1-4879-4a36-866a-3d8cdca8b4c5")
    private final IStyle diagramStyle;

    @objid ("efc29905-2fe7-4bc3-9279-7bf37dbe8298")
    private final List<StyleKey> styleKeys;

    /**
     * Constructor for graphic model style edition.
     * <p>
     * Adds a style chooser at first position.
     * @param input the style key provider.
     * @param diagramStyle the diagram style, added as choice in the style chooser combo.
     */
    @objid ("7cf8b9cd-f91e-4ec9-92a7-a6bcca21e990")
    public LegacyStyleKeyProviderSymbolViewModel(AbstractStyleKeyProvider input, IStyle diagramStyle) {
        this.diagramStyle = diagramStyle;
        this.styleKeys = input.getStyleKeys();
        this.label = input.getStyleKeys().get(0).getCategory();
    }

    /**
     * Constructor for Named style edition.
     * @param label the root label
     * @param styleKeys all style keys to display
     * @param diagramStyle the diagram style, added as choice in the style chooser combo.
     */
    @objid ("21587e9a-8e37-468e-bcb1-3e76865c9962")
    public LegacyStyleKeyProviderSymbolViewModel(String label, List<StyleKey> styleKeys, IStyle diagramStyle) {
        super();
        this.label = label;
        this.styleKeys = styleKeys;
        this.diagramStyle = diagramStyle;
    }

    @objid ("b54fb919-75a7-434c-9ca5-67d6db27de34")
    @Override
    public List<? extends ISymbolViewItem> getChildren(ISymbolViewItem entry) {
        // A String represents a category
        if (entry instanceof LegacyEntry) {
            return ((LegacyEntry) entry).getChildren();
        }
        return Collections.emptyList();
    }

    @objid ("72cc9e42-4fa1-44be-b3a1-0e7db9a9a2eb")
    @Override
    public List<? extends ISymbolViewItem> getElements() {
        Map<String, LegacyEntry> cache = new TreeMap<>();
        
        // Build the category tree cache
        for (StyleKey skey : this.styleKeys) {
        
            LegacyEntry catEntry = cache.computeIfAbsent(
                    skey.getCategory(),
                    cat -> new LegacyEntry(null, cat, null));
        
            catEntry.getChildren().add(new LegacyEntry(skey, skey.getLabel(), catEntry));
        }
        
        ArrayList<ISymbolViewItem> ret = new ArrayList<>();
        
        // Depending on the existence of several categories,
        // return either the categories or the style key.
        // This results in a tree structure when there are several categories
        // and a flat structure when there is only one or no category and the edited style is a named/shared style.
        if (cache.isEmpty()) {
            return Collections.emptyList();
        } else if (cache.size() == 1 && this.diagramStyle == null) {
            ret.addAll(cache.values().iterator().next().getChildren());
        } else {
            ret.addAll(cache.values());
        }
        
        if (this.diagramStyle != null) {
            ret.add(0, new ChooseStyleEntry(this.diagramStyle));
        }
        return ret;
    }

    @objid ("e8a087bc-6e98-4574-8a8c-b3f815980560")
    @Override
    public String getLabel() {
        return this.label;
    }

    @objid ("8b42f014-fca0-439d-a4b0-4e880857f6c9")
    @Override
    public ISymbolViewItem getParent(ISymbolViewItem entry) {
        if (entry instanceof LegacyEntry) {
            return ((LegacyEntry) entry).getParent();
        }
        return null;
    }

    @objid ("54240df7-d245-4cb3-8890-73a6774de819")
    @Override
    public List<? extends ISymbolViewItem> getVisibleChildren(ISymbolViewItem entry) {
        return getChildren(entry);
    }

    @objid ("732eb88b-e38c-4ea3-9862-9796f12f45b7")
    public static class ChooseStyleEntry implements ISymbolViewItem {
        @objid ("16c3a7db-7d41-4386-b8a5-24adcca3dcfb")
        private final IStyle diagramStyle;

        /**
         * @param diagramStyle the GmXxxDiagram style
         */
        @objid ("be72df27-e611-4cc5-84dc-b827a6eb8837")
        public ChooseStyleEntry(IStyle diagramStyle) {
            this.diagramStyle = diagramStyle;
        }

        @objid ("a4fcd676-50bf-4374-b2b3-a06edf297a0c")
        @Override
        public List<Choice> getPossibleValues() {
            StyleManager styleManager = DiagramStyles.getStyleManager();
            
            List<Choice> ret = styleManager.elementStyles()
                    .sorted(Comparator.comparing(NamedStyle::getName))
                    .map(style -> new Choice(style, style.getName()))
                    .collect(Collectors.toCollection(ArrayList::new));
            
            if (this.diagramStyle != null) {
                ret.add(0, new Choice(this.diagramStyle, DiagramStyles.I18N.getString("ChooseStyleEntry.diagramStyle")));
            }
            return ret;
        }

        @objid ("92010400-525c-4e04-82ba-8f17466d1529")
        @Override
        public Object getValue(IStyle input) {
            return input.getBaseStyle();
        }

        @objid ("38443250-7414-4deb-8c52-d5be2770dcb1")
        @Override
        public void setValue(IStyle input, Object newValue) {
            input.setBaseStyle((IStyle) newValue);
        }

        @objid ("9d18ceb8-ba0c-4d65-a2f0-0ed367ccbaf2")
        @Override
        public Class<?> getType() {
            return IStyle.class;
        }

        @objid ("1c8db8f5-7201-496c-b3ae-e6929b234f3b")
        @Override
        public String getLabel() {
            return DiagramStyles.I18N.getString("ChooseStyleEntry.label");
        }

        @objid ("e2767366-efad-4954-9077-e577e8838a98")
        @Override
        public boolean isLocallyModified(IStyle input) {
            return !Objects.equals(input.getBaseStyle(), this.diagramStyle);
        }

        @objid ("0b26f32f-0faf-462d-81f9-e1152db64bfe")
        @Override
        public boolean isEditable(IStyle style) {
            return true;
        }

        /**
         * Represents no style key.
         */
        @objid ("d6d70356-c384-4369-aa35-4534f71960d6")
        @Override
        public StyleKey getStyleKey() {
            return null;
        }

        @objid ("c1d3572e-97d5-4ff6-85f9-9f916e5c66e6")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
            return result;
        }

        @objid ("717ba002-f2a9-4558-9b17-5452d6e0f180")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            
            ChooseStyleEntry other = (ChooseStyleEntry) obj;
            return Objects.equals(getParent(), other.getParent());
        }

        @objid ("69b9719a-be73-402d-8736-6220175c07d1")
        @Override
        public String getDescription() {
            return DiagramStyles.I18N.getString("ChooseStyleEntry.description");
        }

        @objid ("4694bc00-5fdd-417a-b5cc-348ae1fdf5be")
        private Object getParent() {
            return null;
        }

    }

    @objid ("b7751756-614f-4aff-87dc-06f091323c40")
    private static class LegacyEntry implements ISymbolViewItem {
        @objid ("73228753-617a-41a6-bdac-8f560c09ec93")
        private final String label;

        @objid ("444a5b1f-dd4c-4b15-883c-05db81c48fd6")
        private final StyleKey styleKey;

        @objid ("01c00c1d-b5c5-4865-986d-850780c3d152")
        private final List<LegacyEntry> children;

        @objid ("fa1244f1-0e9e-4c3d-a6b1-253524e79ab8")
        private final LegacyEntry parent;

        @objid ("d114920e-b418-4712-a5b4-4289130dd70b")
        public LegacyEntry(StyleKey styleKey, String label, LegacyEntry parent) {
            this.styleKey = styleKey;
            this.label = label;
            this.parent = parent;
            if (styleKey == null) {
                this.children = new ArrayList<>();
            } else {
                this.children = Collections.emptyList();
            }
        }

        @objid ("7716f741-3b26-424c-98b1-8ec19268aa49")
        @Override
        public StyleKey getStyleKey() {
            return this.styleKey;
        }

        @objid ("2ca0adb0-1c34-46b9-b0ac-b9241e3fb19e")
        @Override
        public String getLabel() {
            return this.label;
        }

        @objid ("3c9e8a9a-e56d-461c-8cd0-29f7ba4d3a51")
        @Override
        public Class<?> getType() {
            return this.styleKey != null ? this.styleKey.getType() : null;
        }

        @objid ("a608de99-1188-430e-8e94-9318624f4c80")
        @Override
        public List<Choice> getPossibleValues() {
            if (this.styleKey != null && this.styleKey.getType().isEnum()) {
                Object[] enumConstants = this.styleKey.getType().getEnumConstants();
                List<Choice> ret = new ArrayList<>(enumConstants.length);
                for (Object v : enumConstants) {
                    ret.add(new Choice(v, DiagramStyles.I18N.getString("$" + this.styleKey.getType().getSimpleName() + "." + v.toString())));
                }
                return ret;
            }
            return Collections.emptyList();
        }

        @objid ("f67fbdbc-478e-436d-98b3-4257e05113b7")
        public LegacyEntry getParent() {
            return this.parent;
        }

        @objid ("9641b296-21d2-42e9-a73d-6f9df2cf853e")
        public List<LegacyEntry> getChildren() {
            return this.children;
        }

        @objid ("f70f6a11-3dc9-4b67-a13f-061f5b4de8d4")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.label == null) ? 0 : this.label.hashCode());
            result = prime * result + ((this.styleKey == null) ? 0 : this.styleKey.hashCode());
            return result;
        }

        @objid ("54327f95-5660-4ffd-88a7-dc71d51887c2")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            
            if (getClass() != obj.getClass()) {
                return false;
            }
            
            LegacyEntry other = (LegacyEntry) obj;
            return Objects.equals(this.label, other.label) &&
                                Objects.equals(this.styleKey, other.styleKey);
        }

        @objid ("05577a1d-99df-4ec3-8583-b9b0eb642c0f")
        @Override
        public void setValue(IStyle input, Object newValue) {
            if (this.styleKey == null) {
                return;
            } else {
                input.setProperty(this.styleKey, newValue);
            }
        }

        @objid ("f4ae6b57-1d5e-4e9c-a46f-1dbc0e33959b")
        @Override
        public Object getValue(IStyle input) {
            if (this.styleKey == null) {
                return null;
            } else {
                return input.getProperty(this.styleKey);
            }
        }

        @objid ("ae2031c1-37a7-4a69-a7c9-c9e31a0c7bf9")
        @Override
        public boolean isLocallyModified(IStyle input) {
            return this.styleKey != null && input.isLocal(this.styleKey);
        }

        @objid ("76c5e19e-2593-430f-9381-e37167437045")
        @Override
        public boolean isEditable(IStyle style) {
            return this.styleKey != null && !style.isDynamicValue(this.styleKey);
        }

        @objid ("91bca4fd-0e17-4fbc-b26d-78072eb15c83")
        @Override
        public String getDescription() {
            return this.styleKey != null ? this.styleKey.getTooltip() : "";
        }

    }

}
