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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * Supported document format.
 */
@objid ("889b931d-8de8-408d-a819-d1bc9fc1fb77")
public class RichNoteFormat {
    /**
     * MIME type.
     */
    @objid ("180fb009-31dd-4253-a7f0-f0fd42a28eb1")
    private String mimeType;

    @objid ("68e8fc2f-ed1c-480d-a9eb-74d47ab173f9")
    private String label;

    @objid ("ec676d10-08c8-497f-ab75-66e6659bbd5a")
    private Collection<String> fileExts;

    /**
     * Implementation dependent string data.
     */
    @objid ("fd4519af-9c35-4547-8c80-694081be61d3")
    private String data;

    @objid ("92ea908f-23b3-49c7-8104-69dc9cdcb5bf")
    private SupportLevel supportLevel;

    @objid ("6c359dda-4371-4685-8310-cc7be3b2a598")
    private IRichNoteEditorProvider editorProvider;

    @objid ("29a7a475-f211-47ab-a727-e56888b874cd")
    private Image icon;

    @objid ("48cfc491-72ac-4644-83d8-b882c78fe05b")
    private ImageDescriptor iconDescriptor;

    /**
     * @param mimeType the MIME type
     * @param fileExt matching files extensions without the dot, separated by a space, ',' or ';'.
     * @param supportLevel the support level
     */
    @objid ("6c715d92-4a38-4aef-a31f-2bba60dd467a")
    public RichNoteFormat(final String mimeType, final String fileExt, final SupportLevel supportLevel) {
        this.mimeType = mimeType;
        this.supportLevel = supportLevel;
        if (fileExt == null || fileExt.isEmpty())
            this.fileExts = Collections.emptyList();
        else {
            StringTokenizer t = new StringTokenizer(fileExt, ",; \t\n\r\f");
            this.fileExts = new ArrayList<>(t.countTokens());
            while (t.hasMoreElements())
                this.fileExts.add(t.nextToken());
        }
    }

    @objid ("5a359560-5020-4e15-840c-efae7465a921")
    @Override
    protected void finalize() throws Throwable {
        
        
        if (this.icon != null) {
            this.icon.dispose();
        }
        super.finalize();
    }

    /**
     * @return implementation dependent data.
     */
    @objid ("576fdd69-4f7c-4020-a801-83353071a567")
    public String getData() {
        return this.data;
    }

    /**
     * @return the documentEditorProvider
     */
    @objid ("4ff34397-dd10-4780-bd03-332c279d4f3a")
    public IRichNoteEditorProvider getEditorProvider() {
        return this.editorProvider;
    }

    /**
     * @return the matching file extensions.
     */
    @objid ("fa7cd7d6-4606-4f63-900a-ea0de91fe98e")
    public Collection<String> getFileExtensions() {
        return this.fileExts;
    }

    /**
     * Get the the document icon.
     * <p>
     * The icon must not be disposed, it is owned by this format.
     * 
     * @return the document icon.
     */
    @objid ("7ef8082b-d373-4c80-975e-d0a62677b82c")
    public Image getIcon() {
        if (this.icon == null && this.iconDescriptor != null)
            this.icon = this.iconDescriptor.createImage();
        return this.icon;
    }

    /**
     * @return the document format icon descriptor.
     */
    @objid ("a9f03397-fa7e-4982-ae1c-423ad845f7a0")
    public ImageDescriptor getIconDescriptor() {
        return this.iconDescriptor;
    }

    /**
     * @return the label
     */
    @objid ("efe23241-1a00-42ba-902c-a261f9baf0c7")
    public String getLabel() {
        return this.label;
    }

    /**
     * @return the MIME type.
     */
    @objid ("02330771-16be-4ddb-a0f7-4a7b238fb8e4")
    public String getMimeType() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.mimeType;
    }

    /**
     * @return the supportLevel
     */
    @objid ("8042fdf0-514e-4dcf-84c9-27c1629461eb")
    public SupportLevel getSupportLevel() {
        return this.supportLevel;
    }

    /**
     * Tells whether this rich note format can be edited in Modelio.
     * 
     * @return <code>true</code> if the rich note can be edited in Modelio, else <code>false</code>.
     */
    @objid ("10b58a01-824f-498e-b2ae-662d8afbdf89")
    public boolean isUsable() {
        return this.editorProvider.isUsable();
    }

    /**
     * Set implementation dependent data.
     * 
     * @param data implementation dependent data.
     */
    @objid ("bffea9f4-a254-4945-862a-eeebbe364157")
    public void setData(final String data) {
        this.data = data;
    }

    /**
     * @param editorProvider the documentEditorProvider to set
     */
    @objid ("f19c2428-6aaa-40b6-a776-d4565b510de9")
    public void setEditorProvider(final IRichNoteEditorProvider editorProvider) {
        this.editorProvider = editorProvider;
    }

    /**
     * Set the document icon.
     * <p>
     * Ownership of the icon is taken and the icon will be disposed on finalization.
     * 
     * @param icon the document icon.
     */
    @objid ("0d99202c-b1be-429a-aa3d-1502d7ed43e9")
    public void setIcon(final ImageDescriptor icon) {
        this.iconDescriptor = icon;
    }

    /**
     * @param label the label to set
     */
    @objid ("53529a24-9b82-478b-9c4b-0e5b66c0b17d")
    public void setLabel(final String label) {
        this.label = label;
    }

    @objid ("d7f2c5c1-f285-4aef-9410-74e39c1b85bd")
    @Override
    public String toString() {
        return this.label+" - "+this.mimeType+ " from "+this.editorProvider.getClass().getName();
    }

}
