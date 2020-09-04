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

package org.modelio.api.modelio.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * Diagram contributors category object.
 * type is the type of the category
 * label is the name of the category
 * icon is the image of the category
 */
@objid ("4526214a-9fc4-49c9-b1b3-ca3a535f382a")
public class ContributorCategory {
    @objid ("1ba3d4bd-2dd2-47f3-bd8f-7f8cca15e666")
    private String type;

    @objid ("b68c7cc3-affa-4968-8a13-f653b7dc1541")
    private String label;

    @objid ("99b23d88-3742-4dc6-90c0-bc6e7d8dbc9d")
    private Image icon;

    @objid ("7ace8cbf-b587-4db3-9dbf-e5b9962c0b91")
    public ContributorCategory(String type, String label, Image icon) {
        super();
        this.type = type;
        this.label = label;
        this.icon =icon;
    }

    @objid ("cf9c55f1-8472-40eb-a417-393f6ab8e6e9")
    public String getType() {
        return this.type;
    }

    @objid ("2cd2468b-bfbb-4732-a80f-4de8bfa73da0")
    public void setType(String type) {
        this.type = type;
    }

    @objid ("7e5a507c-8aa0-4b80-9051-3935caa64c34")
    public String getLabel() {
        return this.label;
    }

    @objid ("e9d77cee-b253-4097-87e9-f353559fa7ee")
    public void setLabel(String label) {
        this.label = label;
    }

    @objid ("c76c07ca-6b17-4c81-ad82-4b16b395729b")
    public Image getIcon() {
        return this.icon;
    }

    @objid ("83ac9a6a-05d8-4bba-82d8-5bd979d9b817")
    public void setIcon(Image icon) {
        this.icon = icon;
    }

}
