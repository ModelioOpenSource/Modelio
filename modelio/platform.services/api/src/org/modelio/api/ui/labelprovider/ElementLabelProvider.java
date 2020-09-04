/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.ui.labelprovider;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Standard label provider for model element. Provide the element name for text and the stereotype icon for image.
 * 
 * @since 3.4
 */
@objid ("285fa857-f606-4ac3-b36c-a288c618ceba")
public class ElementLabelProvider extends LabelProvider {
    @objid ("482aa76a-b359-4b60-b273-58dd03df3962")
    @Override
    public Image getImage(Object obj) {
        if (obj instanceof Element) {
            return Modelio.getInstance().getImageService().getStereotypedImage((Element) obj, null, true);
        } else {
            return null;
        }
    }

    @objid ("37b70a40-02c7-43e3-bd06-72515083f91f")
    @Override
    public String getText(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getName();
        } else {
            return (obj != null) ? obj.toString() : "";
        }
    }

}
