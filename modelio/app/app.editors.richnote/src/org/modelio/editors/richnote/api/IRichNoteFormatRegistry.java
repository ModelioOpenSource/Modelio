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
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;

/**
 * Registry of all {@link RichNoteFormat} provided by all plugins.
 */
@objid ("f55d0b53-e397-4d98-8c9e-12632dfb3d2f")
public interface IRichNoteFormatRegistry {
    /**
     * ID of the extension defining an external document editor provider.
     */
    @objid ("a0f79f1c-b27a-43c8-a46f-85bed38a2331")
    public static final String DOCFORMATPROVIDER_EXTENSION_ID = "org.modelio.documentformatprovider";

    /**
     * Get all supported formats with the best matching provider for which edition is possible.
     * <p>
     * Formats that are not editable because a needed software is not installed are excluded.
     * @return all editable document formats.
     */
    @objid ("e744e545-74e1-4a7a-99a6-bfa1e7efcf1a")
    Collection<RichNoteFormat> getAllEditableFormats();

    /**
     * Get all supported formats with the best matching provider.
     * <h2>Note:</h2>
     * Some of the formats may not be usable because a needed software is not installed.
     * @return all supported document formats.
     */
    @objid ("7663a117-9b9e-411b-8759-76c813223c6d")
    Collection<RichNoteFormat> getAllFormats();

    /**
     * Get the best matching registered document format for the given file.
     * <h2>Note:</h2>
     * The returned format <b>may not be usable</b> because the needed software is not installed.
     * @param aFile a file.
     * @return the best matching document format or <code>null</code>.
     */
    @objid ("2e9cbc92-1982-4335-8bf7-bafab1b12b81")
    RichNoteFormat getFileFormat(final File aFile);

    /**
     * Get the best matching registered document format for the given MIME type.
     * <h2>Note:</h2>
     * The returned format <b>may not be usable</b> because the needed software is not installed.
     * @param mimeType a MIME type.
     * @return the best matching document format or <code>null</code>.
     */
    @objid ("acf5a60a-b1f6-4c2d-a406-16effc7a07d3")
    RichNoteFormat getDocumentFormatForMime(final String mimeType);

    /**
     * Get the best matching registered document format for the given extern document.
     * <h2>Note:</h2>
     * The returned format <b>may not be usable</b> because the needed software is not installed.
     * @param target a document.
     * @return the best matching document format or <code>null</code>.
     */
    @objid ("17180dd0-7546-4a66-bac2-b1dbafd3e45a")
    RichNoteFormat getFormat(final AbstractResource target);

    /**
     * Reset the registry.
     * <p>
     * All its content will be computed on next access.
     */
    @objid ("2d7db861-618a-4f96-98cb-4875d1d83f82")
    void reset();

}
