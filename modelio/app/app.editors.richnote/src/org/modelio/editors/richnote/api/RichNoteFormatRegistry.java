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
package org.modelio.editors.richnote.api;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.osgi.framework.Bundle;

/**
 * Registry of {@link RichNoteFormat}.
 */
@objid ("57e5457a-db32-4ff4-b34a-213ced2c4be8")
public class RichNoteFormatRegistry implements IRichNoteFormatRegistry {
    /**
     * Extension point registry listener.
     */
    @objid ("8edb6a18-2de7-4c51-88f8-faed0d443fed")
    private IRegistryEventListener listener;

    /**
     * Best format for a given MIME type.
     * <p>
     * May contain a format that is not editable because of missing software.
     */
    @objid ("a8aaa803-1a3d-4511-a1cd-b6de610fc917")
    private Map<String, RichNoteFormat> bestFormats;

    /**
     * Best format for a given MIME type.
     * <p>
     * The format is guaranteed to be editable.
     */
    @objid ("0079475e-ffa4-45ce-96ab-7cd1f267333f")
    private Map<String, RichNoteFormat> bestEditableFormats;

    @objid ("6c57b6da-289a-4fb2-9b1a-e7873eb7dd11")
    private static RichNoteFormatRegistry instance;

    @objid ("fc354bfa-2d06-4f43-a26d-f152a0274342")
    private static IRichNoteEditorProvider loadDocumentProvider(final IConfigurationElement providerEl) {
        try {
            return (IRichNoteEditorProvider) providerEl.createExecutableExtension("class");
        
        } catch (final CoreException e) {
            EditorsRichNote.LOG.error(e);
        }
        return null;
    }

    /**
     * Constructor.
     */
    @objid ("8ec6a453-418a-4188-92c8-84be8ef1d942")
    public  RichNoteFormatRegistry() {
        instance = this;
        init();
        
    }

    /**
     * Get the format registry.
     * @return the format registry.
     */
    @objid ("baad7816-e378-44b2-b196-03e4ff36c1dc")
    public static RichNoteFormatRegistry getInstance() {
        if (instance == null) {
            instance = new RichNoteFormatRegistry();
        }
        return instance;
    }

    @objid ("ce663c59-fbb5-4218-872a-a99a4769e1c7")
    @Override
    public Collection<RichNoteFormat> getAllEditableFormats() {
        if (this.bestFormats == null) {
            loadSupportedFormats();
        }
        return this.bestEditableFormats.values();
    }

    @objid ("462361f8-f07b-4de3-9acb-14c620361971")
    @Override
    public Collection<RichNoteFormat> getAllFormats() {
        if (this.bestFormats == null) {
            loadSupportedFormats();
        }
        return this.bestFormats.values();
    }

    @objid ("29d19c54-9f9b-401b-9d88-0411990e9d74")
    @Override
    public RichNoteFormat getDocumentFormatForMime(final String mimeType) {
        getAllFormats();
        return this.bestFormats.get(mimeType);
    }

    @objid ("408e9391-1306-4914-850f-fb066e242ac1")
    @Override
    public RichNoteFormat getFileFormat(final File aFile) {
        final String path = aFile.getPath();
        final String extension = path.substring(path.lastIndexOf(".") + 1);
        
        RichNoteFormat ret = null;
        for (final RichNoteFormat f : getAllFormats()) {
            if (f.getFileExtensions().contains(extension)) {
                ret = f;
                if (ret.getSupportLevel() == SupportLevel.Primary) {
                    return ret;
                }
            }
        }
        return ret;
    }

    @objid ("1e640d9e-9a1b-46eb-b988-4d8d49f62897")
    @Override
    public RichNoteFormat getFormat(final AbstractResource target) {
        return getDocumentFormatForMime(target.getMimeType());
    }

    /**
     * Unregister the registry listener.
     */
    @objid ("ffb76326-cfb0-4f6a-b47b-fa6776381d0c")
    void dispose() {
        if (this.listener != null) {
            Platform.getExtensionRegistry().removeListener(this.listener);
            this.listener = null;
        }
        
    }

    /**
     * Register an extension registry listener.
     */
    @objid ("43b7641a-ef7d-4323-9f0c-fa29f8b4d913")
    void init() {
        this.listener = new RegistryListener();
        Platform.getExtensionRegistry().addListener(this.listener, DOCFORMATPROVIDER_EXTENSION_ID);
        
    }

    /**
     * Reset the registry.
     * <p>
     * All its content will be computed on next access.
     */
    @objid ("7dc5ab4a-655f-468b-a046-8dee3f2142b8")
    @Override
    public void reset() {
        this.bestFormats = null;
        this.bestEditableFormats = null;
        
    }

    /**
     * Tells whether the first given format is better than the second.
     * @param a the first format
     * @param b the second format
     * @return <code>true</code> if <i>a</i> is the best format else <code>false</code>
     */
    @objid ("5e8d5046-f619-4dc3-88ba-b3f4e6e4582b")
    private boolean isFirstBest(final RichNoteFormat a, final RichNoteFormat b) {
        if (a.isUsable() && ! b.isUsable()) {
            return true;
        }
        if (b.isUsable() && !a.isUsable()) {
            return false;
        }
        return a.getSupportLevel() == SupportLevel.Primary;
    }

    @objid ("5131595b-d1ec-4dc3-8a33-c0813694e138")
    private void loadSupportedFormats() {
        this.bestFormats = new HashMap<>();
        this.bestEditableFormats = new HashMap<>();
        
        // process registered listeners (RCP extensions)
        for (final IConfigurationElement  providerEl: new ExtensionPointContributionManager(DOCFORMATPROVIDER_EXTENSION_ID).getExtensions("provider")) {
            final IRichNoteEditorProvider docProvider = loadDocumentProvider(providerEl);
        
            if (docProvider != null) {
                final boolean providerUsable = docProvider.isUsable();
                for (final IConfigurationElement  docEl: providerEl.getChildren("format")) {
                    final String mimeType = docEl.getAttribute("mime");
                    final String label = docEl.getAttribute("label");
                    final String data = docEl.getAttribute("data");
                    final String extensions = docEl.getAttribute("extensions");
                    final boolean isAlternate = "alternate".equals(docEl.getAttribute("support"));
        
                    final RichNoteFormat f = new RichNoteFormat(mimeType, extensions, isAlternate ? SupportLevel.Alternate : SupportLevel.Primary);
                    f.setLabel(label);
                    f.setEditorProvider(docProvider);
                    f.setData(data);
                    f.setIcon(getIcon(docEl, f));
        
                    final RichNoteFormat existing = this.bestFormats.get(f.getMimeType());
                    if (existing==null || isFirstBest(f, existing)){
                        this.bestFormats.put(f.getMimeType(), f);
                        if (providerUsable) {
                            this.bestEditableFormats.put(f.getMimeType(), f);
                        }
                    }
                }
            }
        }
        
    }

    @objid ("98c52623-9922-4d89-bd48-e17db463f373")
    private ImageDescriptor loadSystemIcon(final RichNoteFormat f) {
        for (final String ext : f.getFileExtensions()) {
            final Program program = Program.findProgram(ext);
            if (program != null) {
                final ImageData data = program.getImageData();
                if (data != null) {
                    return ImageDescriptor.createFromImageData(data);
                }
            }
        }
        return null;
    }

    /**
     * Get an icon for the given rich note format.
     * @param docEl the format declaration in plugin.xml
     * @param f the rich note format
     * @return The rich note format icon descriptor
     * @throws InvalidRegistryObjectException if this format declaration is no longer valid
     */
    @objid ("7d6ce023-196f-438e-8f5d-9564dfff8a61")
    private ImageDescriptor getIcon(final IConfigurationElement docEl, final RichNoteFormat f) throws InvalidRegistryObjectException {
        final String          iconPath = docEl.getAttribute("icon");
        ImageDescriptor desc     = null;
        
        if (iconPath!=null && !iconPath.trim().isEmpty()) {
            final Bundle b = Platform.getBundle(docEl.getContributor().getName());
            final URL iconUrl = FileLocator.find(b, new Path(iconPath), null);
            if (iconUrl != null) {
                desc = ImageDescriptor.createFromURL(iconUrl);
            }
        }
        if (desc == null){
            desc = loadSystemIcon(f);
        }
        return desc;
    }

    /**
     * Extension point registry listener that reset loaded formats on any change.
     */
    @objid ("6e4d8afc-1720-4798-8273-0fa47f739e2c")
    private class RegistryListener implements IRegistryEventListener {
        @objid ("2945f34f-947c-4eb8-a031-ef677dbbb622")
        public  RegistryListener() {
            
        }

        @objid ("90ca2181-40eb-4561-8426-d29bcee366ee")
        @Override
        public void added(final IExtension[] extensions) {
            reset();
        }

        @objid ("3a7f1be4-c096-42f1-a129-ac6a5a52014b")
        @Override
        public void removed(final IExtension[] extensions) {
            reset();
        }

        @objid ("6a6d07af-6581-4bd2-8748-6cbe08ce28e2")
        @Override
        public void added(final IExtensionPoint[] extensionPoints) {
            reset();
        }

        @objid ("01610b0c-c411-4a67-b387-786edbd7c41c")
        @Override
        public void removed(final IExtensionPoint[] extensionPoints) {
            reset();
        }

    }

}
