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

package org.modelio.diagram.styles.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.diagram.styles.core.StyleWriter;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * Registry of all named styles and themes.
 */
@objid ("85cabc38-1926-11e2-92d2-001ec947c8cc")
public final class StyleManager {
    @objid ("85cabc39-1926-11e2-92d2-001ec947c8cc")
    private Map<String, NamedStyle> styles;

    @objid ("85cabc3d-1926-11e2-92d2-001ec947c8cc")
    private Map<String, Path> files;

    @objid ("dce1a03b-1de4-11e2-8cad-001ec947c8cc")
    private Path projectStyleDir;

    @objid ("28724cf7-1538-4731-8cb3-bcf2b4ee3893")
    private NamedStyle defaultTheme;

    /**
     * Get the list of available named styles.
     * <p>
     * Includes element styles and diagram themes.
     * </p>
     * 
     * @return the names of the available named styles
     */
    @objid ("85cabc41-1926-11e2-92d2-001ec947c8cc")
    public List<String> getAvailableStyles() {
        return this.styles.entrySet()
                        .stream()
                        .filter(s -> !isMissingStyle(s.getValue()))
                        .map(en -> en.getKey())
                        .collect(Collectors.toList());
    }

    /**
     * Get a style or theme by name.
     * 
     * @param name the name of the named style to get
     * @return the 'name' NamedStyle or null if not found
     */
    @objid ("85cabc48-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle getStyle(String name) {
        NamedStyle s = findStyle(name);
        if (s == null) {
            DiagramStyles.LOG.warning("Cannot find '%s' style, instantiating a stub style.", name);
            s = new MissingNamedStyle(name);
            this.styles.put(name, s);
        }
        return s;
    }

    /**
     * Create a new named style along with its persistence file.<br>
     * The new file is placed in $PROJECTSPACE/.config/styles directory and named 'name'.style or 'name'.theme.<br>
     * The cascaded style is FactoryStyle.
     * 
     * @param name then name of the style to create
     * @param isTheme whether or not the created {@link NamedStyle} should be flagged as a diagram theme.
     * @return then newly created NamedStyle or null if the style could not be created. When the style already exists it is simply returned.
     */
    @objid ("85cabc4e-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle createStyle(String name, boolean isTheme) {
        // check agains't already defined style
        final NamedStyle existingStyle = findStyle(name);
        if (existingStyle != null && !isMissingStyle(existingStyle)) {
            return existingStyle;
        }
        
        // prepare new file storage
        if (!Files.exists(this.projectStyleDir)) {
            try {
                Files.createDirectories(this.projectStyleDir);
            } catch (IOException e) {
                DiagramStyles.LOG.error(e);
            }
        }
        final Path newStyleFile = this.projectStyleDir.resolve(name + (isTheme ? DiagramStyles.THEME_FILE_EXTENSION : DiagramStyles.STYLE_FILE_EXTENSION));
        
        // create the new style, based on factory style defaults
        final NamedStyle newStyle = new NamedStyle(name, FactoryStyle.getInstance());
        newStyle.setTheme(isTheme);
        
        // register the style on the StyleManager
        registerStyle(name, newStyle, newStyleFile);
        
        // write initial storage contents
        save(newStyle);
        return newStyle;
    }

    @objid ("85cabc56-1926-11e2-92d2-001ec947c8cc")
    protected void registerStyle(String name, NamedStyle style, Path storage) {
        this.styles.put(name, style);
        this.files.put(name, storage);
    }

    /**
     * Loads the styles found in the specified directories. The style cache is cleared each time this method is called (ie no accumulation)
     * 
     * @param stylesDir the directories where to fetch the style files from.
     */
    @objid ("85cabc5b-1926-11e2-92d2-001ec947c8cc")
    public void reloadStylesIn(Path stylesDir) {
        // Clear existing data
        this.projectStyleDir = stylesDir;
        this.styles = new HashMap<>();
        this.files = new HashMap<>();
        
        // Load styles from the given directory
        final Map<String, String> deferredBindings = new HashMap<>();
        loadStyles(stylesDir, deferredBindings, "*" + DiagramStyles.STYLE_FILE_EXTENSION);
        loadStyles(stylesDir, deferredBindings, "*" + DiagramStyles.THEME_FILE_EXTENSION);
        
        // process the deferred cascade bindings
        resolveDeferredBindings(deferredBindings);
    }

    /**
     * Default c'tor.
     */
    @objid ("85cabc61-1926-11e2-92d2-001ec947c8cc")
    public StyleManager() {
        this.styles = new HashMap<>();
        this.files = new HashMap<>();
    }

    /**
     * Write the current named style contents to the disk.
     * 
     * @param style the style to be saved.
     */
    @objid ("85cabc63-1926-11e2-92d2-001ec947c8cc")
    public void save(NamedStyle style) {
        if (this.styles.containsValue(style) && !isMissingStyle(style)) {
            final NamedStyle namedStyle = style;
            final Path file = this.files.get(namedStyle.getName());
        
            final StyleWriter sw = new StyleWriter();
            sw.saveAsFile(namedStyle, file);
        }
    }

    /**
     * Normalizing a style consists in removing from its local definitions the values that are currently the same as the value in cascaded style.
     * 
     * @param editedStyle the style to normalize.
     */
    @objid ("85cabc67-1926-11e2-92d2-001ec947c8cc")
    public void normalize(NamedStyle editedStyle) {
        if (this.styles.containsValue(editedStyle)) {
            final NamedStyle s = editedStyle;
        
            for (final StyleKey skey : s.getLocalKeys()) {
                final Object localValue = s.getProperty(skey);
                final Object cascadedValue = s.getCascadedStyle().getProperty(skey);
                if (localValue.equals(cascadedValue)) {
                    s.removeProperty(skey);
                }
        
            }
        }
    }

    /**
     * @return the root {@link NamedStyle} to be used for element styles, cascading to the factory settings.
     */
    @objid ("85cabc6b-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle getDefaultStyle() {
        return getStyle(DiagramStyles.ROOT_STYLE_NAME);
    }

    /**
     * Create a new named style along with its persistence file.<br>
     * The new file is placed in $PROJECTSPACE/.config/styles directory and named 'name'.style.<br>
     * The cascaded style is the named style "parentStyleName" or default style if "parentStyleName" cannot be resolved.
     * 
     * @param name then name of the style to create
     * @param parentStyleName then name of the parent style of the created style. May be null, in which case 'default' style is used as parent for the newly created style.
     * @param isTheme whether or not the created {@link NamedStyle} should be flagged as a diagram theme.
     * @return then newly created NamedStyle or null if the style could not be created or if it already exists
     */
    @objid ("85cabc6f-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle createStyle(final String name, final String parentStyleName, boolean isTheme) {
        final NamedStyle newStyle = createStyle(name, isTheme);
        if (newStyle != null && parentStyleName != null) {
            final IStyle parentStyle = getStyle(parentStyleName);
            newStyle.setCascadedStyle((parentStyle != null) ? parentStyle : getDefaultStyle());
            save(newStyle);
        }
        return newStyle;
    }

    /**
     * Create a new named style along with its persistence file.<br>
     * The new file is placed in $PROJECTSPACE/.config/styles directory and named 'name'.style.<br>
     * The cascaded style is the named style "parentStyleName" or default style if "parentStyleName" cannot be resolved.
     * 
     * @param name then name of the style to create
     * @param parentStyleName then name of the parent style of the created style. May be null, in which case 'default' style is used as parent for the newly created style.
     * @param styleData the url of the file to initialize the style from.
     * @param isTheme whether or not the created {@link NamedStyle} should be flagged as a diagram theme.
     * @return then newly created NamedStyle or null if the style could not be created or if it already exists
     */
    @objid ("85cabc7a-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle createStyle(final String name, final String parentStyleName, final URL styleData, boolean isTheme) {
        final NamedStyle newStyle = createStyle(name, parentStyleName, isTheme);
        final StyleLoader loader = new StyleLoader();
        
        if (styleData != null) {
            loader.load(styleData);
        
            for (final StyleKey key : loader.getStyleProperties().keySet()) {
                newStyle.setProperty(key, loader.getStyleProperties().get(key));
            }
        }
        
        newStyle.setProvider(loader.getStyleProvider());
        
        save(newStyle);
        return newStyle;
    }

    /**
     * @return the default theme to be used for diagrams.
     */
    @objid ("86a95a1f-029d-4fc1-a3b2-5065190a8449")
    public NamedStyle getDefaultTheme() {
        if (this.defaultTheme == null) {
            this.defaultTheme = getStyle(DiagramStyles.MODELIO_3X_THEME_NAME);
        }
        return this.defaultTheme;
    }

    @objid ("3d540d47-f647-49f6-9ebb-fa0feea97d12")
    private void loadStyles(Path stylesDir, final Map<String, String> deferredBindings, String stylePattern) {
        try (DirectoryStream<Path> styleFiles = Files.newDirectoryStream(stylesDir, stylePattern)) {
            for (final Path f : styleFiles) {
                loadStyle(f, deferredBindings);
            }
        } catch (IOException e) {
            DiagramStyles.LOG.error(e);
        }
    }

    /**
     * Set the default theme to be used for diagrams.
     * 
     * @param themeName a style name.
     */
    @objid ("dd6d507b-5e14-443c-b6ec-6a1cfb8f6124")
    public void setDefaultTheme(String themeName) {
        this.defaultTheme = getStyle(themeName);
    }

    /**
     * @return the list of all diagram themes.
     */
    @objid ("6f42c113-f73b-4660-9c30-5d29aa0cf101")
    public List<NamedStyle> getThemes() {
        return themes().collect(Collectors.toList());
    }

    /**
     * @return the list of all element styles, aka not referencing a theme.
     */
    @objid ("ca6929cf-b90c-4d21-933f-6ab7f83db36f")
    public List<NamedStyle> getElementStyles() {
        return elementStyles().collect(Collectors.toList());
    }

    /**
     * Process the deferred cascade bindings.
     * <p>
     * The rule is to create stub styles if the parent style does not exist.
     * 
     * @param deferredBindings a map with 'child'/'parent' style names as 'key'/'value' entries.
     */
    @objid ("c6dbe853-5d1c-485a-9cd9-a2104c7fc445")
    private void resolveDeferredBindings(final Map<String, String> deferredBindings) {
        for (Entry<String, String> entry : deferredBindings.entrySet()) {
            String styleName = entry.getKey();
            if (!styleName.equals(DiagramStyles.ROOT_STYLE_NAME)) {
                final NamedStyle style = getStyle(styleName);
                final NamedStyle cascadeStyle = getStyle(entry.getValue());
                style.setCascadedStyle(cascadeStyle);
            }
        }
    }

    @objid ("989169ce-43eb-42da-8658-da2b82ba138a")
    private NamedStyle loadStyle(final Path styleFile, final Map<String, String> deferredBindings) throws MalformedURLException {
        final StyleLoader loader = new StyleLoader();
        
        loader.load(styleFile.toUri().toURL());
        
        String name = loader.getStyleName().isEmpty() ? styleFile.getFileName().toString() : loader.getStyleName();
        boolean isTheme = loader.getStyleTheme();
        String provider = loader.getStyleProvider().isEmpty() ? "Modelio" : loader.getStyleProvider();
        Set<String> applicability = loader.getStyleApplicability();
        
        String cascadedStyleName;
        if (loader.getStyleBase().isEmpty()) {
            cascadedStyleName = DiagramStyles.ROOT_STYLE_NAME;
        } else {
            cascadedStyleName = loader.getStyleBase();
        }
        
        // By default create with factory setting as parent
        final NamedStyle namedStyle = new NamedStyle(name, loader.getStyleProperties(), FactoryStyle.getInstance());
        namedStyle.setTheme(isTheme);
        namedStyle.setProvider(provider);
        namedStyle.setApplicability(applicability);
        
        // Register style
        registerStyle(name, namedStyle, styleFile);
        
        // Defer cascade binding
        deferredBindings.put(name, cascadedStyleName);
        return namedStyle;
    }

    /**
     * Import a style in the current style directory.
     * 
     * @param styleFile the style's persistence file, to be copied in the current style directory. Musn't be <code>null</code>.
     * @return a {@link NamedStyle} instanciated from the style file.
     * @throws java.io.IOException if an I/O error occurs.
     */
    @objid ("1e94bb7d-2543-4fe7-830c-fc4712578dcb")
    NamedStyle importStyle(Path styleFile) throws IOException {
        // Copy style file into the current style directory
        Path localStyleFile = this.projectStyleDir.resolve(styleFile.getFileName());
        Files.copy(styleFile, localStyleFile);
        
        // Load the style
        Map<String, String> deferredBindings = new HashMap<>();
        NamedStyle importedStyle = loadStyle(localStyleFile, deferredBindings);
        resolveDeferredBindings(deferredBindings);
        return importedStyle;
    }

    @objid ("5058a042-6018-43e7-9231-695da0d833e3")
    private static boolean isMissingStyle(NamedStyle s) {
        return s instanceof MissingNamedStyle;
    }

    /**
     * Get a style by name.
     * 
     * @param name the name of the named style to get
     * @return the 'name' NamedStyle or null if not found
     */
    @objid ("b48a66d9-8c37-4a1d-a68f-1282c9d7f1d9")
    public NamedStyle findStyle(String name) {
        return this.styles.get(name);
    }

    /**
     * @return a stream on all diagram themes.
     */
    @objid ("2f0f2841-2e79-4e02-98fa-e64d158e21d3")
    public Stream<NamedStyle> themes() {
        Stream<NamedStyle> themes = this.styles.values()
                .stream()
                .filter(s -> s.isTheme());
        // Add the root style to the themes
        return Stream.concat(Stream.of(getStyle(DiagramStyles.ROOT_STYLE_NAME)), themes);
    }

    /**
     * @return a stream on all element styles, aka not referencing a theme.
     */
    @objid ("524b3890-9b9e-4f6c-962e-36fda97ed736")
    public Stream<NamedStyle> elementStyles() {
        return this.styles.values().stream()
                        .filter(s -> !s.isTheme());
    }

}
