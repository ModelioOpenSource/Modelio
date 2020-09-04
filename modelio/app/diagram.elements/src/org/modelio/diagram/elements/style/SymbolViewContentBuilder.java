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

package org.modelio.diagram.elements.style;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.IStyleProvider;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem.Choice;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.manager.StyleManager;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * Helper class to build {@link ISymbolViewModel} instances.
 * <p>
 * Usage:
 * <ul>
 * <li>First call {@link #builder(String)} to get a {@link Builder}.
 * <li>Call {@link Builder#add(ItemBuilder)} on the builder to add items.
 * <li>Use {@link #item()} to create a non configured symbol view model {@link StyleItemBuilder item builder} .
 * <li>Use {@link #item(StyleKey)} to create style key edition item.
 * <li>You may add sub items with {@link StyleItemBuilder#add(ItemBuilder)}.
 * <li>You may filter the visibility of an item with {@link StyleItemBuilder#filter(IEntryFilter)}.
 * <p>
 * You have some default filters with {@link #filterEquals(StyleKey, Object)}, {@link #filterRepresentationMode(RepresentationMode...)}
 * <li>At the end call {@link Builder#build(IStyleProvider)} to get your {@link ISymbolViewModel}.
 * </ul>
 * 
 * @author cma
 * @since 3.7 Builder helper for {@link ISymbolViewModel}.
 * 
 * Usage:
 * 
 * <ul>
 * <li>Instantiate a builder</li>
 * <li>Call {@link Builder#add(ItemBuilder)} on the builder to add items.</li>
 * <li>standard {@link Builder#add(ItemBuilder)} instances can be build from 'create' methods</li>
 * <li>At the end call {@link Builder#build(IStyleProvider)} to get a {@link ISymbolViewModel}.
 * </ul>
 * 
 * @since 3.7
 */
@objid ("2f16161e-e9b2-415b-ba24-e2ccd54cf268")
public class SymbolViewContentBuilder {
    @objid ("0b4437f3-6bb3-4239-8655-d13678790173")
    protected static final String LABEL_PEN_BRUSH_SECTION = DiagramElements.I18N.getString("SymbolViewContentBuilder.PenBrushSection");

    @objid ("13a6831f-ebab-4dfd-aafc-96e407792449")
    private String label;

    @objid ("989dc49d-6286-461e-bf5d-96f9fe9db62a")
    private final List<ItemBuilder<?>> items = new ArrayList<>();

    /**
     * a filter that checks the graphic model is neither in image mode nor in user image mode.
     */
    @objid ("f7d1ecb4-1839-46b1-a698-a4e1dc4d7b39")
    public final IEntryFilter notImageModeFilter = filterRepresentationMode(RepresentationMode.STRUCTURED, RepresentationMode.SIMPLE);

    /**
     * a filter that checks the graphic model is in structured mode.
     */
    @objid ("ca87f0fd-89fe-4a8c-b443-146b51d48696")
    public final IEntryFilter structuredModeFilter = filterRepresentationMode(RepresentationMode.STRUCTURED);

    @objid ("3ee01fc4-4a3a-47c2-99f2-4d9cfb4572af")
    public SymbolViewContentBuilder(String label) {
        this.label = label;
    }

    /**
     * Add a symbol view entry at the root level.
     * 
     * @param item a symbol view entry.
     * @return this instance.
     */
    @objid ("2a4da26a-c77a-4313-9b32-b69c8d29e870")
    public SymbolViewContentBuilder add(ItemBuilder<?> item) {
        this.items.add(item);
        return this;
    }

    /**
     * @param style the edited style
     * @param context the edited element
     * @return the built content provider.
     */
    @objid ("ef8fc0f9-bc0b-48c9-b9b5-8d82798cfd5f")
    public ISymbolViewModel build(IStyle style, IStyleProvider context) {
        List<AbstractEntry> roots = new ArrayList<>();
        for (ItemBuilder<?> buildItem : this.items) {
            roots.add(buildItem.asEntry(null, context));
        }
        return new BuiltSymbolModel(style, this.label, context, roots);
    }

    /**
     * Change the label
     * 
     * @param label the label
     * @return this instance.
     */
    @objid ("84f49454-066a-4fff-9906-ec9d5ad070fd")
    public SymbolViewContentBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    /**
     * Create a filter that test a style key value against the given {@link Predicate}.
     * 
     * @param key the style key
     * @param valueTest the value test function
     * @return the created filter
     */
    @objid ("9b6f6f9d-a166-4361-9c95-ac3ce28a4392")
    public IEntryFilter filter(StyleKey key, Predicate<Object> valueTest) {
        IEntryFilter filter = (style, obj) -> valueTest.test(style.getProperty(key));
        return filter;
    }

    /**
     * @param requiredMode the required representation mode.
     * @return a filter that checks the graphic model is in the required mode.
     */
    @objid ("a34dd25d-4a27-4fbf-a2b3-f42a72c50578")
    public IEntryFilter filterRepresentationMode(RepresentationMode requiredMode) {
        IEntryFilter filter = (style, obj) -> {
            if (obj instanceof GmPortContainer) {
                return ((GmPortContainer) obj).getMainNode().getRepresentationMode() == requiredMode;
            } else if (obj instanceof GmModel) {
                GmModel gm = (GmModel) obj;
                return (gm.getRepresentationMode() == requiredMode);
            } else {
                return true;
            }
        };
        return filter;
    }

    /**
     * Create a non configured symbol view entry from a {@link StyleKey}.
     * @param key a {@link StyleKey}.
     * 
     * @return the created symbol view entry.
     */
    @objid ("dfed236a-5300-4625-a71a-4fe4c7c6b3ff")
    public LabelItemBuilder createLabelItem(String label) {
        return new LabelItemBuilder().withLabel(label);
    }

    /**
     * @return the combo to change the cascaded theme.
     */
    @objid ("ef06619e-97b7-48b1-9376-f0c434336004")
    public ThemeChooserItemBuilder createThemeChooserItem() {
        return new ThemeChooserItemBuilder().filter((IStyle a, IStyleProvider context) -> context != null);
    }

    /**
     * Build a pre configured "Pen & brush" section from all the given style keys.
     * 
     * @param lineWidth line width style key. Cannot be <i>null</i>.
     * @param lineColor line color style key. Cannot be <i>null</i>.
     * @param fillMode fill mode style key. Cannot be <i>null</i>.
     * @param fillcolor fill color style key. Cannot be <i>null</i>.
     * @param textColor optional text color style key. May be <i>null</i>.
     * @param textFont optional text font style key. May be <i>null</i>.
     * @return the build section.
     */
    @objid ("0054dca9-a061-4c76-819d-28461fc26b67")
    public LabelItemBuilder createPenAndBrushSection(StyleKey lineWidth, StyleKey lineColor, StyleKey fillMode, StyleKey fillcolor, StyleKey textColor, StyleKey textFont) {
        LabelItemBuilder ret = createLabelItem(SymbolViewContentBuilder.LABEL_PEN_BRUSH_SECTION)
                .add(createStyleItem(lineWidth)
                        .filter(this.notImageModeFilter))
                .add(createStyleItem(lineColor)
                        .filter(this.notImageModeFilter)
                        .filter(lineWidth, v -> ((int) v) > 0))
                .add(createStyleItem(fillMode)
                        .filter(this.notImageModeFilter))
                .add(createStyleItem(fillcolor)
                        .filter(this.notImageModeFilter)
                        .filter(fillMode, v -> v != StyleKey.FillMode.TRANSPARENT));
        if (textColor != null) {
            ret.add(createStyleItem(textColor));
        }
        if (textFont != null) {
            ret.add(createStyleItem(textFont));
        }
        return ret;
    }

    /**
     * Create a symbol view entry from a {@link StyleKey}.
     * 
     * @param key a {@link StyleKey}.
     * @return the created symbol view entry.
     */
    @objid ("1b564e81-a890-47f5-8694-72ce25579fa7")
    public StyleItemBuilder createStyleItem(StyleKey key) {
        return new StyleItemBuilder().withKey(key);
    }

    /**
     * @return the combo to change the cascaded style.
     */
    @objid ("738e01af-75b1-4bf1-a732-9dd6366942a1")
    public StyleChooserItemBuilder createStyleChooserItem() {
        return new StyleChooserItemBuilder().filter((IStyle a, IStyleProvider context) -> context != null);
    }

    /**
     * Create a filter that test a style key value against the given value.
     * 
     * @param key the style key
     * @param requiredValue the accepted value.
     * @return the created filter
     */
    @objid ("4ff90878-56e0-4b16-8e9f-d5bd272a0d12")
    public IEntryFilter filterEquals(StyleKey key, Object requiredValue) {
        IEntryFilter filter = (style, obj) -> Objects.equals(
                style.getProperty(key),
                requiredValue);
        return filter;
    }

    /**
     * @param allowedModes the allowed representation modes.
     * @return a filter that checks the graphic model is one of the allowed mode.
     */
    @objid ("f192deaa-ffef-4f0d-993b-a5dcdddfd94f")
    public IEntryFilter filterRepresentationMode(RepresentationMode... allowedModes) {
        IEntryFilter filter = (style, obj) -> {
            if (obj instanceof GmPortContainer) {
                RepresentationMode gmMode = ((GmPortContainer) obj).getMainNode().getRepresentationMode();
                if (gmMode == null) {
                    return true;
                }
                for (RepresentationMode m : allowedModes) {
                    if (m == gmMode) {
                        return true;
                    }
                }
                return false;
            } else if (obj instanceof GmModel) {
                GmModel gm = (GmModel) obj;
                RepresentationMode gmMode = gm.getRepresentationMode();
                if (gmMode == null) {
                    return true;
                }
                for (RepresentationMode m : allowedModes) {
                    if (m == gmMode) {
                        return true;
                    }
                }
                return false;
            } else {
                return true;
            }
        };
        return filter;
    }

    @objid ("47aaacaa-84a8-43ec-9227-1a767c599816")
    private static abstract class AbstractEntry implements ISymbolViewItem {
        @objid ("1f312979-2330-4577-a1e2-54b953535065")
        private final List<AbstractEntry> children;

        @objid ("66de69ae-0fa8-4ff5-9921-63531a73997f")
        private final List<IEntryFilter> filters;

        @objid ("a3cffb0f-ca9e-4e14-903b-1188f9c54ccf")
        private final AbstractEntry parent;

        @objid ("4120c2e4-8598-4faf-9445-da56c03ac077")
        public AbstractEntry(AbstractEntry parent, List<IEntryFilter> filters) {
            super();
            this.children = new ArrayList<>();
            this.parent = parent;
            this.filters = filters;
        }

        @objid ("784650b2-4192-4d80-82a2-e08c51303bae")
        public List<AbstractEntry> getChildren() {
            return this.children;
        }

        /**
         * @param input the edited element, may be null for global style edition.
         * @return whether this item should be displayed.
         */
        @objid ("dd6d4752-3b3d-466e-959a-aa2a846b96d4")
        public boolean isVisible(IStyle style, IStyleProvider input) {
            for (IEntryFilter f : this.filters) {
                if (!f.accept(style, input)) {
                    return false;
                }
            }
            return true;
        }

        @objid ("602ed73d-1d95-4dce-9489-a7dd0f3583dd")
        public AbstractEntry getParent() {
            return this.parent;
        }

    }

    @objid ("03728ce5-4416-44d3-a8ed-31f2d0a9ee17")
    private static class ChooseStyleEntry extends AbstractEntry {
        @objid ("a4c57c38-7f6c-407f-96d1-b5489d230dab")
        private final IStyle diagramStyle;

        @objid ("ec638ff6-94fa-4fd4-bc4e-af3941896258")
        public ChooseStyleEntry(IStyle diagramStyle, AbstractEntry parent, List<IEntryFilter> filters) {
            super(parent, filters);
            this.diagramStyle = diagramStyle;
        }

        @objid ("7e83a3d7-d2ed-414e-9c94-c20bbd06f17f")
        @Override
        public List<Choice> getPossibleValues() {
            StyleManager styleManager = DiagramStyles.getStyleManager();
            
            List<Choice> ret = styleManager.elementStyles()
                    .sorted(Comparator.comparing(NamedStyle::getName))
                    .map(style -> new Choice(style, style.getName()))
                    .collect(Collectors.toCollection(ArrayList::new));
            
            if (this.diagramStyle != null) {
                ret.add(0, new Choice(this.diagramStyle, DiagramElements.I18N.getString("ChooseStyleEntry.diagramStyle")));
            }
            return ret;
        }

        @objid ("25338303-5b05-4afd-bce5-f35978c9cdc3")
        @Override
        public Object getValue(IStyle input) {
            return input.getBaseStyle();
        }

        @objid ("24e103f8-4714-4d38-b9bf-422d373d02c6")
        @Override
        public void setValue(IStyle input, Object newValue) {
            input.setBaseStyle((IStyle) newValue);
        }

        @objid ("c4b83063-61e8-4dfa-b6ca-e51f155a534b")
        @Override
        public Class<?> getType() {
            return IStyle.class;
        }

        @objid ("83e7ddca-38aa-4c72-bc24-5dc0f5b0289f")
        @Override
        public String getLabel() {
            return DiagramElements.I18N.getString("ChooseStyleEntry.label");
        }

        @objid ("7bd7d43e-0cf4-4f2d-b0ba-180888107fb4")
        @Override
        public boolean isLocallyModified(IStyle input) {
            return !Objects.equals(input.getBaseStyle(), this.diagramStyle);
        }

        @objid ("086d30dc-9863-4e73-93e9-7c8f5f4da3db")
        @Override
        public boolean isEditable(IStyle style) {
            return true;
        }

        /**
         * Represents no style key.
         */
        @objid ("40a7a5a2-a090-4f81-bee0-a716ddb565d5")
        @Override
        public StyleKey getStyleKey() {
            return null;
        }

        @objid ("cea9b3e4-1757-481e-8122-a5225ab4611b")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
            return result;
        }

        @objid ("f69d71e4-d8e6-4cfe-bf0b-7f92164fa8c1")
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

        @objid ("4215981c-c474-4d5b-b6b7-7e09e3de5eee")
        @Override
        public String getDescription() {
            return DiagramElements.I18N.getString("ChooseStyleEntry.description");
        }

    }

    @objid ("132521e8-5e96-451e-96fb-0b45bffc05c9")
    protected static class ChooseThemeEntry extends AbstractEntry {
        @objid ("69142901-515c-4e08-901e-2d517d0572b3")
        public ChooseThemeEntry(AbstractEntry parent, List<IEntryFilter> filters) {
            super(parent, filters);
        }

        @objid ("91809fea-783c-44fd-b19d-2ba160425e95")
        @Override
        public List<Choice> getPossibleValues() {
            StyleManager styleManager = DiagramStyles.getStyleManager();
            
            List<Choice> ret = styleManager.themes()
                    .sorted(Comparator.comparing(NamedStyle::getName))
                    .map(style -> new Choice(style, style.getName()))
                    .collect(Collectors.toList());
            return ret;
        }

        @objid ("e7f66c06-537d-4477-a6c8-03379799bafa")
        @Override
        public Object getValue(IStyle input) {
            return input.getBaseStyle();
        }

        @objid ("026b4f9b-f045-4255-aae9-95b4d5b85390")
        @Override
        public void setValue(IStyle input, Object newValue) {
            input.setBaseStyle((IStyle) newValue);
        }

        @objid ("d40e4786-a739-41c6-a19d-f6a2a8f24d7b")
        @Override
        public Class<?> getType() {
            return IStyle.class;
        }

        @objid ("a418bfda-6a97-4d26-aae6-38577115f989")
        @Override
        public String getLabel() {
            return DiagramElements.I18N.getString("ChooseThemeEntry.label");
        }

        @objid ("b38c7b2d-2597-4f04-a17d-e02bf930615a")
        @Override
        public boolean isLocallyModified(IStyle input) {
            return false;
        }

        @objid ("415f4da9-0af6-4b6c-b7ab-5b7759061d09")
        @Override
        public boolean isEditable(IStyle style) {
            return true;
        }

        /**
         * Represents no style key.
         */
        @objid ("ae2a9112-4b98-402b-ae80-dfd042850848")
        @Override
        public StyleKey getStyleKey() {
            return null;
        }

        @objid ("14d3dce7-6ae8-4077-8396-81e5119f68b1")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
            return result;
        }

        @objid ("d52a0654-772c-4693-872b-addfdaf1cc44")
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
            
            ChooseThemeEntry other = (ChooseThemeEntry) obj;
            return Objects.equals(getParent(), other.getParent());
        }

        @objid ("647060ed-a106-4938-a5bc-fa90ef1428ff")
        @Override
        public String getDescription() {
            return DiagramElements.I18N.getString("ChooseThemeEntry.description");
        }

    }

    @objid ("e14a28e4-b653-4f50-a08a-78adcaaa15cf")
    private static class LabelEntry extends AbstractEntry {
        @objid ("e5bb6d37-d1fe-4bf7-ae92-f7bd1a639eec")
        private final String label;

        @objid ("34a61c99-0dd1-49ab-8f64-043336081ce7")
        private final String description;

        @objid ("fb32aa70-4935-4157-8cfa-6d5c2b8040ba")
        public LabelEntry(String label, String description, AbstractEntry parent, List<IEntryFilter> filters) {
            super(parent, filters);
            this.label = Objects.requireNonNull(label);
            this.description = Objects.requireNonNull(label);
        }

        @objid ("cbc6c0db-910c-4f7d-bcf6-405fbd3fa6d4")
        @Override
        public StyleKey getStyleKey() {
            return null;
        }

        @objid ("864b60de-0e1d-4929-b134-cc77a1844ddf")
        @Override
        public String getLabel() {
            return this.label;
        }

        @objid ("a759489f-889a-4426-ad26-715f00bdf6ee")
        @Override
        public List<Choice> getPossibleValues() {
            return Collections.emptyList();
        }

        @objid ("e205f031-385c-4854-b370-32a8040e0303")
        @Override
        public boolean isLocallyModified(IStyle input) {
            return false;
        }

        @objid ("6149bffa-cd31-4c07-87f1-eadba67cc756")
        @Override
        public boolean isEditable(IStyle style) {
            return false;
        }

        @objid ("49f7e8d1-9dd9-449b-b6ef-957257cbd18c")
        @Override
        public void setValue(IStyle input, Object newValue) {
            throw new UnsupportedOperationException();
        }

        @objid ("3f5510b5-9cf6-4a82-b564-8da48a66d79c")
        @Override
        public Object getValue(IStyle input) {
            return null;
        }

        @objid ("6551421c-2ffb-48c9-8afa-e6d93cceca81")
        @Override
        public Class<?> getType() {
            return null;
        }

        @objid ("04514d51-630a-4b34-a0c4-b6431fe203ef")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.label == null) ? 0 : this.label.hashCode());
            result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
            return result;
        }

        @objid ("b97f6111-dfc9-4ef2-8ba5-6a30df384b59")
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
            
            LabelEntry other = (LabelEntry) obj;
            return Objects.equals(this.label, other.label) &&
                                Objects.equals(getParent(), other.getParent());
        }

        @objid ("803af27c-338d-4886-8cd9-ae88c4e9f664")
        @Override
        public String getDescription() {
            return this.description;
        }

    }

    @objid ("9c642a24-7999-4011-9831-4f9530aeb4ea")
    private static class StyleEntry extends AbstractEntry {
        @objid ("afa63f83-3850-4da8-999c-74ff6609858c")
        private final String label;

        @objid ("a8646030-b176-45bf-b00b-607ebb8dac36")
        private final String description;

        @objid ("443f33ae-1327-43d6-84d8-ead80ebf5085")
        private final StyleKey key;

        @objid ("849d282a-a040-4a79-8d02-6a2f7dbbb346")
        private final List<Choice> possibleValues;

        @objid ("1d8a3005-0d1e-4c27-b206-c40e9cd99870")
        public StyleEntry(StyleKey key, String label, String description, AbstractEntry parent, List<Choice> possibleValues, List<IEntryFilter> filters) {
            super(parent, filters);
            this.key = Objects.requireNonNull(key);
            this.label = Objects.requireNonNull(label);
            this.description = Objects.requireNonNull(description);
            this.possibleValues = Objects.requireNonNull(possibleValues);
        }

        @objid ("552f2e15-9f95-482a-9523-427261e31844")
        @Override
        public String getLabel() {
            return this.label;
        }

        @objid ("dd7db315-3fbf-4134-9c97-3ca79cf56fa2")
        @Override
        public List<Choice> getPossibleValues() {
            return this.possibleValues;
        }

        @objid ("74b600e6-aac2-4d9c-ae97-166e0264d89d")
        @Override
        public void setValue(IStyle input, Object newValue) {
            input.setProperty(getStyleKey(), newValue);
        }

        @objid ("a2a557e9-5ca4-4a7a-afe9-a4ba8040b089")
        @Override
        public Object getValue(IStyle input) {
            return input.getProperty(getStyleKey());
        }

        @objid ("42deabaa-a0d8-4a29-b762-0719f0d60188")
        @Override
        public Class<?> getType() {
            return this.key.getType();
        }

        @objid ("b39ed3a0-55e4-4136-96fc-b5c49c59c7fd")
        @Override
        public StyleKey getStyleKey() {
            return this.key;
        }

        @objid ("deb2e37b-d5c9-46e2-9d32-53413c887965")
        @Override
        public boolean isLocallyModified(IStyle input) {
            return getStyleKey() != null && input.isLocal(getStyleKey());
        }

        @objid ("fe1d88ac-28df-41db-94b9-6b5cbc3fcd01")
        @Override
        public boolean isEditable(IStyle style) {
            return getStyleKey() != null && !style.isDynamicValue(getStyleKey());
        }

        @objid ("474a6439-785d-4c35-8a22-dbb2ea5f6622")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
            result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
            return result;
        }

        @objid ("39e4bec3-0e10-4c72-8561-7d810fda1183")
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
            StyleEntry other = (StyleEntry) obj;
            return Objects.equals(this.key, other.key) &&
                                Objects.equals(getParent(), other.getParent());
        }

        @objid ("007af7a6-c433-45b7-b0de-a72f04f169c4")
        @Override
        public String getDescription() {
            return this.description;
        }

    }

    @objid ("30d1d50d-5fee-4751-ae8e-8e8bbbf17859")
    public interface IEntryFilter {
        /**
         * @param style the edited style
         * @param context the style owner, may be null if the style is a named style.
         * @return true if the entry is to be displayed.
         */
        @objid ("0b9191b7-00c8-4102-8345-504ecf6daacc")
        boolean accept(IStyle style, IStyleProvider context);

    }

    @objid ("3404107f-55a0-4b00-ab57-cf8e8c6f9a69")
    @SuppressWarnings ("unchecked")
    public static abstract class ItemBuilder<T extends ItemBuilder<T>> {
        @objid ("b8da5748-a7e4-408f-a140-8bb9b9f78acc")
        private final List<IEntryFilter> filters = new ArrayList<>();

        @objid ("5e77c7e5-df0e-451a-b614-ebd77ee11671")
        private final List<ItemBuilder<?>> children = new ArrayList<>();

        @objid ("474e4376-c04c-477d-964a-59896ef67c96")
        private final List<IEntryFilter> childFilters = new ArrayList<>();

        @objid ("0df5acb9-adff-40b6-b382-f2bae1aeb15e")
        private IEntryFilter nextChildrenFilter;

        @objid ("ab529304-eaf0-4003-872f-677bb1552622")
        public abstract AbstractEntry asEntry(AbstractEntry parent, IStyleProvider input);

        @objid ("19024665-f01b-483d-80ae-15f0457898d9")
        public T filter(IEntryFilter filter) {
            this.filters.add(filter);
            return (T) this;
        }

        @objid ("ad30a93f-9a2d-4877-a1ab-83e90d6b2e3c")
        protected List<IEntryFilter> getFilters() {
            return this.filters;
        }

        @objid ("4773608d-13ce-42b1-9bd6-9c8c38e544ae")
        public T filterChildren(IEntryFilter filter) {
            this.childFilters.add(filter);
            return (T) this;
        }

        /**
         * Add an item builder to this item children.
         * <p>
         * If {@link #setNextChildrenFilter(IEntryFilter)} has been called before, the given filter will be put on this child.
         * 
         * @param item the child item to add
         * @return this instance to chain calls.
         */
        @objid ("67ddd9df-3b29-4209-901d-945c6cb5bdb8")
        public T add(ItemBuilder<?> item) {
            this.children.add(item);
            if (this.nextChildrenFilter != null) {
                item.filter(this.nextChildrenFilter);
            }
            return (T) this;
        }

        @objid ("a7705ae3-6fe3-47e3-b753-a06825d44b39")
        public List<ItemBuilder<?>> getChildren() {
            return this.children;
        }

        @objid ("0b558aaa-c9c2-43be-8ee0-1e19b559e145")
        protected List<AbstractEntry> buildChildren(AbstractEntry parentItem, IStyleProvider input) {
            List<AbstractEntry> ret = new ArrayList<>(this.children.size());
            for (ItemBuilder<?> i : this.children) {
                // append this.childFilters to child.filters
                for (IEntryFilter cf : this.childFilters) {
                    i.filter(cf);
                }
                ret.add(i.asEntry(parentItem, input));
            }
            return ret;
        }

        /**
         * Set a filter that will be applied to next elements added with {@link #add(ItemBuilder)}.
         * 
         * @param filter a filter to put on next children.
         * @return this instance.
         */
        @objid ("70c55431-8c7d-4128-a268-923622350b08")
        public ItemBuilder<T> setNextChildrenFilter(IEntryFilter filter) {
            this.nextChildrenFilter = filter;
            return this;
        }

        @objid ("685c5d26-d341-45b0-b19b-2b98bbb150e7")
        public T filter(StyleKey key, Predicate<Object> valueTest) {
            filter((style, obj) -> valueTest.test(style.getProperty(key)));
            return (T) this;
        }

    }

    @objid ("ef42d6b4-9d9f-417a-b2a1-830ad215d3be")
    public static final class LabelItemBuilder extends ItemBuilder<LabelItemBuilder> {
        @objid ("223fd62e-b82b-4bd2-997f-fa7bed51ecbc")
        private String label;

        @objid ("7b53db06-48f3-432d-9883-83439c7eff49")
        private String description = "";

        @objid ("7ec5514f-1479-4214-8c6a-16748ad02561")
        @Override
        public AbstractEntry asEntry(AbstractEntry parent, IStyleProvider input) {
            LabelEntry ret = new LabelEntry(this.label, this.description, parent, getFilters());
            ret.getChildren().addAll(buildChildren(ret, input));
            return ret;
        }

        @objid ("c61c5017-76ea-4d66-8e0d-5de499bf44f3")
        public LabelItemBuilder withLabel(String label) {
            this.label = label;
            return this;
        }

        @objid ("4c7a6979-75db-4fcb-8c6f-a169fb7492d2")
        public LabelItemBuilder withDescription(String label) {
            this.description = label;
            return this;
        }

    }

    @objid ("0c80bd3f-4c92-407e-85f3-601b2b46870a")
    private static final class StyleChooserItemBuilder extends ItemBuilder<StyleChooserItemBuilder> {
        @objid ("d309dbc4-0dc9-4cff-b8e8-fb90ae06d797")
        @Override
        public AbstractEntry asEntry(AbstractEntry parent, IStyleProvider input) {
            IGmObject model = (IGmObject) input;
            IStyle diagramStyle = (model != null && model.getDiagram() != model) ? model.getDiagram().getPersistedStyle() : null;
            return new ChooseStyleEntry(diagramStyle, parent, getFilters());
        }

    }

    /**
     * Builder class to build {@link ISymbolViewItem} instances.
     * <p>
     * Usage:
     * <ul>
     * <li>Instantiate with {@link SymbolViewContentBuilder#item()} or {@link SymbolViewContentBuilder#item(StyleKey)}.
     * <li>Call {@link StyleItemBuilder#withKey(StyleKey)} and {@link StyleItemBuilder#withLabel(String)} to set the edited style key and the label.
     * <li>You may add sub items with {@link #add(ItemBuilder)}.
     * <li>You may filter the visibility of an item with {@link #filter(IEntryFilter)}.
     * <p>
     * You have some default filters with {@link #filterEquals(StyleKey, Object)}, {@link #filterRepresentationMode(RepresentationMode...)}, {@link StyleItemBuilder#filterChildren(IEntryFilter)}, {@link StyleItemBuilder#filterChildren(IEntryFilter)},
     * {@link StyleItemBuilder#filter(StyleKey, Predicate)}.
     * </ul>
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("c9d68ace-32ee-4fa4-a2a3-068fd46cdbcd")
    public static class StyleItemBuilder extends ItemBuilder<StyleItemBuilder> {
        @objid ("e1643ff2-7d15-4ea6-9fa0-e03b99e6728f")
        private String label;

        @objid ("cec5ef3b-439c-4cf9-9489-62cd1993f941")
        private String description;

        @objid ("f7bc6328-db96-4b96-9110-09a2767776ba")
        private StyleKey key;

        @objid ("b0d16c85-49de-49d2-acaf-8898c5061005")
        private List<Choice> possibleValues = null;

        @objid ("876852b8-3f76-4000-affd-1e780c385df8")
        public StyleItemBuilder withKey(StyleKey key) {
            this.key = Objects.requireNonNull(key);
            return this;
        }

        @objid ("92c261a2-faf9-4462-ad4f-f8e8bf8e7cc4")
        public StyleItemBuilder withLabel(String label) {
            this.label = Objects.requireNonNull(label);
            return this;
        }

        @objid ("cbc56ce3-d503-4d14-946e-4cf356ef1f9e")
        @Override
        public StyleItemBuilder filter(StyleKey key, Predicate<Object> valueTest) {
            filter((style, obj) -> valueTest.test(style.getProperty(key)));
            return this;
        }

        @objid ("c3bd1f7e-ecb5-4fd6-9437-540ba562124a")
        public StyleItemBuilder filterEquals(StyleKey key, Object requiredValue) {
            filter((style, obj) -> Objects.equals(style.getProperty(key), requiredValue));
            return this;
        }

        @objid ("a80eaa3f-4d26-41de-9b54-17da47bbeedb")
        public StyleItemBuilder withChoice(Object value, String label) {
            if (this.possibleValues == null) {
                this.possibleValues = new ArrayList<>(5);
            }
            this.possibleValues.add(new Choice(value, label));
            return this;
        }

        /**
         * Convert this {@link ItemBuilder} and all its children to a {@link StyleEntry}.
         * 
         * @param parent the parent {@link StyleEntry} or null.
         * @return the created {@link StyleEntry}.
         */
        @objid ("827cbcf0-484f-4c9c-a97b-28c7b914c5a1")
        @Override
        public AbstractEntry asEntry(AbstractEntry parent, IStyleProvider input) {
            if (this.label == null) {
                this.label = this.key.getLabel();
            }
            
            if (this.description == null) {
                this.description = this.key.getTooltip();
            }
            
            if (this.possibleValues == null) {
                if (this.key != null && this.key.getType().isEnum()) {
                    Object[] enumConstants = this.key.getType().getEnumConstants();
                    this.possibleValues = new ArrayList<>(enumConstants.length);
                    for (Object litt : enumConstants) {
                        // TODO how to translate enum literals ?
                        String clabel = DiagramStyles.I18N.getString("$" + litt.getClass().getSimpleName() + "." + litt.toString());
                        this.possibleValues.add(new Choice(litt, clabel));
                    }
                } else {
                    this.possibleValues = Collections.emptyList();
                }
            }
            
            StyleEntry ret = new StyleEntry(
                    this.key,
                    this.label,
                    this.description,
                    parent,
                    this.possibleValues,
                    getFilters().isEmpty() ? Collections.emptyList() : getFilters());
            
            ret.getChildren().addAll(buildChildren(ret, input));
            return ret;
        }

        @objid ("0a65b87e-ffa5-4172-91b2-9bfed2723532")
        public StyleItemBuilder withDescription(String label) {
            this.description = Objects.requireNonNull(label);
            return this;
        }

    }

    @objid ("fdcf1c5e-597e-4a2f-9c01-5011125c4970")
    private static final class ThemeChooserItemBuilder extends ItemBuilder<ThemeChooserItemBuilder> {
        @objid ("222e4c25-11f4-411b-a224-5cb80b8be177")
        @Override
        public AbstractEntry asEntry(AbstractEntry parent, IStyleProvider input) {
            return new ChooseThemeEntry(parent, getFilters());
        }

    }

    /**
     * The {@link ISymbolViewModel} generated from the builder.
     * 
     * @author cma
     */
    @objid ("71a86666-6bd3-4482-ba97-4672d8899cfb")
    private static class BuiltSymbolModel implements ISymbolViewModel {
        @objid ("72ceece5-7fd9-416e-8480-6b01d821194a")
        private final String label;

        @objid ("4754f293-a005-4d14-ae43-699e50ca7b45")
        private final List<AbstractEntry> items;

        @objid ("f0b48e4f-ec6c-4835-99e5-a4e0b6e98d20")
        private final IStyleProvider context;

        @objid ("37a90f98-922c-4954-b647-042cf38edbdc")
        private final IStyle style;

        @objid ("dbc1f110-504b-4542-9a1c-b143b2979b37")
        protected BuiltSymbolModel(IStyle style, String label, IStyleProvider context, List<AbstractEntry> items) {
            this.style = style;
            this.items = items;
            this.context = context;
            this.label = label;
        }

        @objid ("174ca4ba-b794-412f-a970-eab8bb91ace6")
        protected List<? extends ISymbolViewItem> filtered(List<AbstractEntry> list) {
            List<AbstractEntry> ret = new ArrayList<>(list.size());
            for (AbstractEntry item : list) {
                if (item.isVisible(this.style, this.context)) {
                    ret.add(item);
                }
            }
            return ret;
        }

        @objid ("9bef0ef2-9b67-46f3-b24e-bdd6a2d46bba")
        @Override
        public List<? extends ISymbolViewItem> getElements() {
            return filtered(this.items);
        }

        @objid ("05f87481-04fa-4eb2-8b29-595e2b9dfecf")
        @Override
        public List<? extends ISymbolViewItem> getChildren(ISymbolViewItem entry) {
            if (entry instanceof AbstractEntry) {
                return ((AbstractEntry) entry).getChildren();
            } else {
                return Collections.emptyList();
            }
        }

        @objid ("5f249fbc-a48c-49e6-8db2-97bd0ef78284")
        @Override
        public ISymbolViewItem getParent(ISymbolViewItem entry) {
            if (entry instanceof AbstractEntry) {
                return ((AbstractEntry) entry).getParent();
            } else {
                return null;
            }
        }

        @objid ("cc6a4c30-cbd1-4798-b247-418ef7534548")
        @Override
        public String getLabel() {
            return this.label;
        }

        @objid ("022982cf-f1e1-47cf-a197-f65fbf8d9291")
        @Override
        public List<? extends ISymbolViewItem> getVisibleChildren(ISymbolViewItem entry) {
            if (entry instanceof AbstractEntry) {
                return filtered(((AbstractEntry) entry).getChildren());
            } else {
                return Collections.emptyList();
            }
        }

    }

}
