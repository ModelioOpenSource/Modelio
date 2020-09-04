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

package org.modelio.diagram.styles.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.core.view.LegacyStyleKeyProviderSymbolViewModel;

/**
 * {@link ISymbolViewModel} that looks for all style key providers and concatenates their symbol view models.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("af189b98-f08e-42cf-9ca9-57f4cd9061c1")
public class SharedStyleEditorModel implements ISymbolViewModel {
    @objid ("c80b2b34-b7e3-4357-a6ad-3b58f880b8d8")
    private final List<ISymbolViewItem> roots;

    @objid ("2f447a4b-ac8c-4cc6-967c-edb1e5968483")
    public SharedStyleEditorModel(IStyle style) {
        this.roots = new ArrayList<>();
        Map<String, List<StyleKey>> allStyleKeys = new HashMap<>();
        Collection<StyleKey> managedKeys = new HashSet<>();
        Collection<AbstractStyleKeyProvider> legacyProviders = new ArrayList<>();
        
        for (AbstractStyleKeyProvider styleKeyProvider : FactoryStyle.getInstance().getStyleKeyProviders()) {
            // ignore inner classes
            if (styleKeyProvider.getClass().getDeclaringClass() == null) {
                List<StyleKey> providerKeys = styleKeyProvider.getStyleKeys();
                ISymbolViewModel model = styleKeyProvider.getSymbolViewModel(style);
                if (model != null) {
                    managedKeys.addAll(providerKeys);
                    RootNode rn = new RootNode(model);
                    this.roots.add(rn);
                } else {
                    // no custom model, remember legacy provider
                    legacyProviders.add(styleKeyProvider);
                }
            }
        }
        
        for (AbstractStyleKeyProvider skp : legacyProviders) {
            List<StyleKey> providerKeys = skp.getStyleKeys();
            for (StyleKey key : providerKeys) {
                if (!managedKeys.contains(key)) {
                    List<StyleKey> keys = allStyleKeys.computeIfAbsent(key.getCategory(), cat -> new ArrayList<>());
                    if (!keys.contains(key)) {
                        keys.add(key);
                    }
                }
            }
        }
        
        for (Entry<String, List<StyleKey>> entry : allStyleKeys.entrySet()) {
            LegacyStyleKeyProviderSymbolViewModel legacyModel = new LegacyStyleKeyProviderSymbolViewModel(entry.getKey(), entry.getValue(), null);
            RootNode rn = new RootNode(legacyModel);
            this.roots.add(rn);
        }
        
        Collections.sort(this.roots, (o1, o2) -> o1.getLabel().compareTo(o2.getLabel()));
    }

    @objid ("d14ddac1-258f-463d-9bc3-cbf3c84ae57a")
    @Override
    public List<? extends ISymbolViewItem> getElements() {
        return this.roots;
    }

    @objid ("2dbb9876-77aa-4f23-b2c8-f5f255ee0cf9")
    @Override
    public List<? extends ISymbolViewItem> getChildren(ISymbolViewItem entry) {
        if (entry instanceof RootNode) {
            RootNode rn = (RootNode) entry;
            return toProxies(rn.getModel().getElements(), rn, rn.getModel());
        } else if (entry instanceof ProxyNode) {
            ProxyNode pn = (ProxyNode) entry;
            return toProxies(pn.getModel().getChildren(pn.delegate), pn, pn.getModel());
        
        }
        return Collections.emptyList();
    }

    @objid ("7717f6b6-4250-4e0b-bf30-7d0aa61730da")
    private List<ISymbolViewItem> toProxies(List<? extends ISymbolViewItem> items, ISymbolViewItem parentNode, ISymbolViewModel model) {
        List<ISymbolViewItem> ret = new ArrayList<>(items.size());
        for (ISymbolViewItem item : items) {
            ret.add(new ProxyNode(item, model, this, parentNode));
        }
        return ret;
    }

    @objid ("ce191a54-1849-4df1-b374-9653d6d77240")
    @Override
    public ISymbolViewItem getParent(ISymbolViewItem entry) {
        if (entry instanceof RootNode) {
            return null;
        } else if (entry instanceof ProxyNode) {
            ProxyNode pn = (ProxyNode) entry;
            return pn.parentNode;
        }
        return null;
    }

    @objid ("39546e37-a6fc-4a3c-a1d6-8ccadfc5027e")
    @Override
    public String getLabel() {
        // should not be called
        return "<" + getClass().getSimpleName() + ">";
    }

    @objid ("368914b2-966e-4aff-b047-af681d2ef54c")
    @Override
    public List<? extends ISymbolViewItem> getVisibleChildren(ISymbolViewItem entry) {
        // Return always all children.
        return getChildren(entry);
    }

    @objid ("c9840f58-3011-4151-ad40-57ba6031f0b9")
    private static class RootNode implements ISymbolViewItem {
        @objid ("ce91e750-458a-4a3f-91f9-e4c635d5b7cf")
        private final ISymbolViewModel model;

        @objid ("8a284828-c28a-4573-b48a-cf85c2e0bc0e")
        public RootNode(ISymbolViewModel model) {
            this.model = model;
        }

        @objid ("1d2bec85-cb67-4bb3-b020-5a693a9cd3a0")
        @Override
        public String getLabel() {
            return this.model.getLabel();
        }

        @objid ("f0b644d6-6b55-4805-b203-6f8a4c063787")
        @Override
        public List<Choice> getPossibleValues() {
            return Collections.emptyList();
        }

        @objid ("fc149525-2326-43f0-be4f-f038ec095fff")
        @Override
        public void setValue(IStyle input, Object newValue) {
            throw new UnsupportedOperationException();
        }

        @objid ("6f2030f5-f560-497e-93fa-daf4dc78b3d8")
        @Override
        public Object getValue(IStyle input) {
            return null;
        }

        @objid ("d2cb068b-e9f4-4aa4-9e46-a45b2359ce1c")
        @Override
        public boolean isLocallyModified(IStyle input) {
            for (ISymbolViewItem item : this.model.getElements()) {
                if (item.isLocallyModified(input)) {
                    return true;
                }
            }
            return false;
        }

        @objid ("369f0a4f-d8f5-4ee2-9077-d306faf5f498")
        @Override
        public StyleKey getStyleKey() {
            return null;
        }

        @objid ("78d03fd2-1a47-4716-8a21-0436112faaaf")
        @Override
        public Class<?> getType() {
            return null;
        }

        @objid ("65071de2-3ed4-4206-b205-ac0f3e11d745")
        @Override
        public boolean isEditable(IStyle style) {
            return false;
        }

        @objid ("d72b6e84-386e-499d-b712-f90685278bb6")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.model == null) ? 0 : this.model.hashCode());
            return result;
        }

        @objid ("4d283645-f2f3-4925-bac5-bbef37e2193d")
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
            RootNode other = (RootNode) obj;
            return Objects.equals(this.model, other.model);
        }

        @objid ("fd8e8938-751c-46ba-b643-d0a36d78c74d")
        @Override
        public String getDescription() {
            return "";
        }

        @objid ("98c462eb-88d6-4896-9329-2d7eb924a520")
        ISymbolViewModel getModel() {
            return this.model;
        }

    }

    @objid ("f2e66918-5728-45a3-957a-8de53d4386b9")
    private static class ProxyNode implements ISymbolViewItem {
        @objid ("ae336b86-7b82-400f-b297-a13f390ef220")
        private final ISymbolViewItem delegate;

        @objid ("e8bbbc26-a7e4-4cf1-815f-6d484d40fc16")
        private final ISymbolViewItem parentNode;

        @objid ("366c72bb-aeb4-4ec9-8990-2edd008e9418")
        private final ISymbolViewModel model;

        @objid ("c8b7234d-2b31-4ec2-bbc7-b9b3343f1387")
        private final ISymbolViewModel rootModel;

        @objid ("34633570-c267-4c52-a27e-616e5ee462d4")
        public ProxyNode(ISymbolViewItem delegate, ISymbolViewModel delegateModel, ISymbolViewModel rootModel, ISymbolViewItem parentNode) {
            super();
            this.delegate = delegate;
            this.rootModel = rootModel;
            this.model = delegateModel;
            this.parentNode = parentNode;
        }

        @objid ("9c1a0c34-401d-4835-8720-458efadaae7f")
        @Override
        public String getLabel() {
            return this.delegate.getLabel();
        }

        @objid ("ffbc3a42-c67d-43a3-aba6-497ceabb353d")
        @Override
        public List<Choice> getPossibleValues() {
            return this.delegate.getPossibleValues();
        }

        @objid ("4982a847-b638-405f-997d-a1854d5f88cc")
        @Override
        public void setValue(IStyle input, Object newValue) {
            this.delegate.setValue(input, newValue);
        }

        @objid ("949c891f-10e3-4e6c-b50c-d1f76c7e7fbf")
        @Override
        public Object getValue(IStyle input) {
            return this.delegate.getValue(input);
        }

        @objid ("b060b617-bb54-400e-9c73-837ecf8d95f4")
        @Override
        public boolean isLocallyModified(IStyle input) {
            if (this.delegate.isLocallyModified(input)) {
                return true;
            }
            
            for (ISymbolViewItem child : this.rootModel.getChildren(this)) {
                if (child.isLocallyModified(input)) {
                    return true;
                }
            }
            return false;
        }

        @objid ("350952ab-06c6-41f4-8f19-56d48fb50a65")
        @Override
        public StyleKey getStyleKey() {
            return this.delegate.getStyleKey();
        }

        @objid ("47333bf8-9eea-4d6c-ae6c-7620bf6398b0")
        @Override
        public Class<?> getType() {
            return this.delegate.getType();
        }

        @objid ("e3d22135-79b4-4e47-95a0-f324081c36c7")
        @Override
        public boolean isEditable(IStyle style) {
            return this.delegate.isEditable(style);
        }

        @objid ("ab4148aa-d502-41cc-9fac-0e50ab03b016")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.delegate == null) ? 0 : this.delegate.hashCode());
            result = prime * result + ((this.model == null) ? 0 : this.model.hashCode());
            return result;
        }

        @objid ("9ba5951d-2c7f-42d9-b565-3ecf331a74b9")
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
            ProxyNode other = (ProxyNode) obj;
            return Objects.equals(this.model, other.model)
                                && Objects.equals(this.delegate, other.delegate);
        }

        @objid ("f1022ebd-ba68-402c-9fe3-22db08599686")
        @Override
        public String getDescription() {
            return this.delegate.getDescription();
        }

        @objid ("af6357fa-176b-47e4-b829-9858c0e7ad91")
        ISymbolViewModel getModel() {
            return this.model;
        }

    }

}
