/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.parameter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;

@objid ("12e5aff3-8fa2-11dd-bbe0-001ec947ccaf")
public class FileParameterModel extends ParameterModel {
    @objid ("da1ef31e-ee96-11e0-8487-002564c97630")
    protected List<String> allowedExtensions = new ArrayList<>();

    @objid ("da1f1a2e-ee96-11e0-8487-002564c97630")
    protected List<String> allowedExtensionLabels = new ArrayList<>();

    @objid ("e45fc11f-feb1-11dd-8b31-0014222a9f79")
    public FileParameterModel(IModuleUserConfiguration conf, String name, String label, String description, String defaultValue) {
        super(conf, name, label, description, defaultValue);
    }

    @objid ("e2e28ab9-9137-11dd-9e51-001ec947ccaf")
    public String[] getAllowedExtensions() {
        return this.allowedExtensions.toArray(new String[this.allowedExtensions.size()]);
    }

    @objid ("e2e28ac7-9137-11dd-9e51-001ec947ccaf")
    public String[] getAllowedExtensionLabels() {
        return this.allowedExtensionLabels.toArray(new String[this.allowedExtensionLabels.size()]);
    }

    /**
     * Add file extension filters with their labels which a dialog will use
     * to filter the files it shows to the user.<P>
     * 
     * The extensions are platform specific. For example, on some platforms,
     * an extension filter string is typically of the form "*.extension",
     * where "*.*" matches all files. For filters with multiple extensions,
     * use semicolon as a separator, e.g. "*.jpg;*.png".<P>
     * 
     * Each label is a user-friendly short description shown for its corresponding filter.
     * The names array must be the same length as the extensions array.<P>
     * 
     * @param extensions the allowed file extension filter
     * @param labels the corresponding label for each extension
     */
    @objid ("e2e4ed11-9137-11dd-9e51-001ec947ccaf")
    public void addAllowedExtensions(String[] extensions, String[] labels) {
        if ((extensions==null) || (labels==null))
            throw new IllegalArgumentException ("extensions and labels must not be null");
        if (extensions.length != labels.length)
            throw new IllegalArgumentException ("extensions and labels must have same size (extension size is "+extensions.length+", labels size is "+labels.length+".");
        
        this.allowedExtensions.addAll (Arrays.asList(extensions));
        this.allowedExtensionLabels.addAll (Arrays.asList(labels));
    }

    /**
     * Add one file extension filter with its label which a dialog will use
     * to filter the files it shows to the argument.<P>
     * 
     * The extension filter is platform specific. For example, on some platforms,
     * an extension filter string is typically of the form "*.extension",
     * where "*.*" matches all files. For filters with multiple extensions,
     * use semicolon as a separator, e.g. "*.jpg;*.png".<P>
     * 
     * The label is a user-friendly short description shown for its corresponding filter.
     * The names array must be the same length as the extensions array.<P>
     * 
     * @param extension the allowed file extension filter
     * @param label the corresponding label
     */
    @objid ("e2e4ed2c-9137-11dd-9e51-001ec947ccaf")
    public void addAllowedExtension(String extension, String label) {
        if ((extension==null) || (label==null))
            throw new IllegalArgumentException ("extensions and labels must not be null");
        
        this.allowedExtensions.add(extension);
        this.allowedExtensionLabels.add(label);
    }

}
