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

package org.modelio.ui;

import java.util.Objects;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

/**
 * Helper to get a modified font based on an initial one.
 * <p>
 * Usage: <ul>
 * <li> Instantiate one either directly or with {@link LocalFontRegistry#builder()}
 * <li> Call {@link #from(Font)}, {@link #from(FontData)} or {@link #from(FontData[])}
 * <li> Call {@link #withAllocator(ResourceManager)}
 * <li> Call as needed some of {@link #addStyle(int)}, {@link #removeStyle(int)}, {@link #scale(float)}, {@link #incHeight(int)},
 * {@link #modify(Consumer)} ...
 * <li> Get the final font with {@link #build()}.
 * </ul>
 * Example:
 * <pre><code>
 * LocalFontRegistry reg = LocalFontRegistry.create(aSwtControl); // or CoreFontRegistry.getGlobal()
 * Font newFont = reg.builder(aControl.getFont())
 * .addStyle(styleToAdd)
 * .scale(scaleFactor)
 * .build;
 * </code></pre>
 * 
 * @author cma
 * @since 3.8.1
 */
@objid ("50a945c3-e352-4ad4-bcbf-68b2ef3ee750")
public class FontBuilder {
    @objid ("3388eb81-d78f-4787-95f1-8d438c6876fb")
    private FontData[] fontdatas;

    @objid ("c58b536e-5c34-458d-bc6c-43c2526b2df7")
    private ResourceManager rm;

    /**
     * Instantiates an empty FontBuilder.
     * <p>
     * You have to call one of the {@link #from(Font)} methods and {@link #withAllocator(ResourceManager)}
     * if you intend to call {@link #build()}.
     */
    @objid ("fe80cf73-b936-40a9-92ab-47c019b67dc2")
    public FontBuilder() {
    }

    /**
     * Add style bits to the font.
     * 
     * @param styleToAdd the given style flags added (use SWT.NONE for no flags change)
     * @return this builder to chain calls.
     */
    @objid ("ff925804-8c1f-4bbe-af19-2520c36ebf3f")
    public FontBuilder addStyle(final int styleToAdd) {
        for (FontData d : this.fontdatas) {
            d.setStyle(d.getStyle() | styleToAdd);
        }
        return this;
    }

    /**
     * Build the font.
     * <p>
     * {@link #withAllocator(ResourceManager)} must have been called in order to work.
     * 
     * @return the built font.
     * @throws java.lang.IllegalStateException if {@link #withAllocator(ResourceManager)} has not been called.
     */
    @objid ("a0bea3ee-0a07-4457-8e12-cf83a2011821")
    public Font build() throws IllegalStateException {
        if (this.rm == null)
            throw new IllegalStateException("FontBuilder.withAllocator(ResourceManager) must be called first.");
        return (Font) this.rm.get(FontDescriptor.createFrom(this.fontdatas));
    }

    /**
     * Build and return a {@link FontDescriptor}.
     * 
     * @return a JFace font descriptor.
     */
    @objid ("d46cce48-7eb0-4be8-85af-7946c61ca8b1")
    public FontDescriptor buildDescriptor() {
        return FontDescriptor.createFrom(this.fontdatas);
    }

    @objid ("02f2e0cf-33eb-44ce-bd04-b2a0a5c98a45")
    public FontBuilder from(Font font) {
        FontData[] afontdatas = font.getFontData();
        return from(afontdatas);
    }

    /**
     * Initialize the font builder given a font data which describes the desired font's appearance.
     * <p>
     * {@link #FontBuilder(FontData[])} should better be used to be fully compatible on Unix.
     * @param fontdata describes the desired font (must not be null)
     */
    @objid ("734b4cd9-ed9c-41b1-b520-3b12e28d91de")
    public FontBuilder from(FontData f) {
        Objects.requireNonNull(f);
        return from(new FontData[] {f});
    }

    @objid ("e4cb0dc4-c84e-4842-b5c6-20ba8aa6d577")
    public FontBuilder from(FontData[] initFontDatas) {
        this.fontdatas = Objects.requireNonNull(initFontDatas);
        return this;
    }

    /**
     * Increase the font height by the given delta.
     * <p>
     * a negative delta will decrease font height.
     * 
     * @param delta the delta to apply .
     * @return this builder to chain calls.
     */
    @objid ("58df6088-aabe-44d2-83a1-e53bd4223d12")
    public FontBuilder incHeight(final int delta) {
        for (FontData d : this.fontdatas) {
            d.setHeight(d.getHeight() + delta);
        }
        return this;
    }

    /**
     * Apply a custom modification on the stored font datas.
     * 
     * @param modifier the modifier to apply
     * @return this builder to chain calls.
     */
    @objid ("d6207230-98aa-47f6-98a9-bd25a976ec82")
    public FontBuilder modify(Consumer<FontData[]> modifier) {
        modifier.accept(this.fontdatas);
        return this;
    }

    /**
     * remove style bits from the font.
     * 
     * @param styleToRemove the given style flags added (use SWT.NONE for no flags change)
     * @return this builder to chain calls.
     */
    @objid ("0303d4f4-7fdd-4d69-81fa-9881f85a4ebc")
    public FontBuilder removeStyle(final int styleToRemove) {
        for (FontData d : this.fontdatas) {
            d.setStyle(d.getStyle() & ~styleToRemove);
        }
        return this;
    }

    /**
     * Scale the font by the given factor.
     * <p>
     * Use {@link UIFont} constants and {@link UIFont#NORMAL_SIZE} for no size change)
     * 
     * @param scaleFactor the font height scale factor.
     * @return this builder to chain calls.
     */
    @objid ("ed7cb0b0-2d73-4789-ac6b-4d5297ef9716")
    public FontBuilder scale(final float scaleFactor) {
        for (FontData d : this.fontdatas) {
            d.setHeight((int) (d.getHeight() * scaleFactor));
        }
        return this;
    }

    @objid ("f9506ff0-3b95-4a1d-bbce-f846dd48b105")
    public FontBuilder withAllocator(ResourceManager rm) {
        this.rm = rm;
        return this;
    }

}
