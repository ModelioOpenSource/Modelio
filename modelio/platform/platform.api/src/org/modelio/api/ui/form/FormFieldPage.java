/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.api.ui.form;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * This interface is used by the {@link IFieldFactory} to split a form into several pages.
 */
@objid ("b22d91ea-5233-44e2-b04a-93d06800eab5")
public interface FormFieldPage {
    /**
     * Return the page's identifier.
     */
    @objid ("4e22ec09-5d3c-4a4d-943d-21f8de9d2d7b")
    int getId();

    /**
     * Return the page's image. Might be <code>null</code>.
     */
    @objid ("86cd59b8-9fcf-4005-9143-b32750a57c2f")
    Image getImage();

    /**
     * Return the page's label.
     */
    @objid ("fefb8561-41b0-4b23-95ea-87d2ee5fbdba")
    String getLabel();
}

