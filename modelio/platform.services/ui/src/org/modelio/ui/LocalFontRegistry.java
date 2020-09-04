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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Control;

/**
 * Local font registry that allocates fonts in a private {@link LocalResourceManager}.
 * <p>
 * <h3>How to get a modified font:</h3><ul>
 * <li> Instanciate one {@link LocalFontRegistry} for your View or Dialog using {@link #create(Control)}.
 * <li> call {@link #builder()} or  {@link #builder(Font)} methods
 * <li> Call {@link FontBuilder#from(Font)}, {@link FontBuilder#from(FontData)} or {@link FontBuilder#from(FontData[])}
 * <li> Call {@link FontBuilder#withAllocator(ResourceManager)}
 * <li> Call as needed some of {@link FontBuilder} methods ...
 * <li> Get the final font with {@link FontBuilder#build()}.
 * </ul>
 * <h3>Example:</h3>
 * <pre><code>
 * LocalFontRegistry reg = LocalFontRegistry.create(aSwtControl); // or CoreFontRegistry.getGlobal()
 * Font newFont = reg.builder(aControl.getFont())
 * .addStyle(styleToAdd)
 * .scale(scaleFactor)
 * .build;
 * </code></pre>
 * @author cma
 * @since 3.8.1
 */
@objid ("aae6ce2b-35bd-4816-a854-1ff949b4b72f")
public class LocalFontRegistry {
    @objid ("4754e807-1a4d-4ba9-ab57-7447812fef8c")
    private final ResourceManager rm;

    /**
     * Create a font registry that uses the given resources registry.
     * 
     * @param rm a registry that handles resource allocation
     */
    @objid ("16835627-75d8-4c3c-8903-05ffccad463e")
    public LocalFontRegistry(ResourceManager rm) {
        this.rm = rm;
    }

    /**
     * Get a font builder based on the given font and this registry resource manager.
     * <p>
     * It is not necessary to call {@link FontBuilder#withAllocator(ResourceManager)} on the
     * returned FontBuilder.
     * 
     * @param font the font to be based on.
     * @return a font builder.
     */
    @objid ("ac280608-d162-4c2b-8744-2e13a01df667")
    public FontBuilder builder(final Font font) {
        return builder().from(font);
    }

    /**
     * Get a font builder based this registry resource manager.
     * <p>
     * You have to call one of the {@link FontBuilder#from(Font) from(...)} methods first.
     * <p>
     * It is not necessary to call {@link FontBuilder#withAllocator(ResourceManager)} on the
     * returned FontBuilder.
     * @param font the font to be based on.
     * 
     * @return a font builder.
     */
    @objid ("448c273a-d30d-4502-a679-f29590e6e52a")
    public FontBuilder builder() {
        return new FontBuilder().withAllocator(this.rm);
    }

    /**
     * Creates a local font registry that wraps the given global JFace resource registry.
     * <p>
     * Anything allocated by this font registry will be automatically cleaned up with the given
     * control is disposed.
     * <p>
     * Note that registries created in this way should not
     * be used to allocate any font that must outlive the given control.
     * 
     * @param parentRegistry global registry that handles resource allocation
     * @param owner control whose disposal will trigger cleanup of everything
     * in the registry.
     */
    @objid ("115b139a-19bf-4b68-bb84-267937073673")
    public static LocalFontRegistry create(ResourceManager parentRegistry, Control owner) {
        return new LocalFontRegistry(new LocalResourceManager(parentRegistry, owner));
    }

    /**
     * Creates a local registry that wraps the JFace global registry.
     * <p>
     * Anything allocated by this font registry will be automatically cleaned up with the given
     * control is disposed.
     * <p>
     * Note that registries created in this way should not
     * be used to allocate any font that must outlive the given control.
     * @param parentRegistry global registry that handles resource allocation
     * 
     * @param owner control whose disposal will trigger cleanup of everything
     * in the registry.
     */
    @objid ("7bb9c382-45ac-482c-ae73-541a988e73fc")
    public static LocalFontRegistry create(Control owner) {
        return create(JFaceResources.getResources(), owner);
    }

    /**
     * Get a font given a device and font data which describes the desired font's appearance.
     * <p>
     * {@link #getFont(FontData[])} should better be used to be fully compatible on Unix.
     * 
     * @param fontdata describes the desired font (must not be null)
     * @return the matching font
     */
    @objid ("8640fa32-59aa-45cb-9829-473bfaca3297")
    public Font getFont(FontData fontdata) {
        return (Font) this.rm.get(FontDescriptor.createFrom(fontdata));
    }

    /**
     * Get a font given an array of font data which describes the desired font's appearance.
     * 
     * @param fontdatas the array of FontData that describes the desired font (must not be null)
     * @return the matching font
     */
    @objid ("a49d1184-5522-47bf-ac4e-758e0d90056c")
    public Font getFont(final FontData[] fontdatas) {
        return (Font) this.rm.get(FontDescriptor.createFrom(fontdatas));
    }

    /**
     * @return the used resources manager.
     */
    @objid ("2dc950e8-dc60-48ec-8214-8eaf53f91be1")
    public ResourceManager getResourceManager() {
        return this.rm;
    }

}
