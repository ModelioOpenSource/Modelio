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

package org.modelio.api.modelio.diagram.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("24f4cc9a-20b0-49a4-b7ea-656e0b3e493e")
public class PaletteEntry {
    @objid ("0c81f23d-f1c9-4d6d-b07f-a11ed6eb9ab3")
    public String group;

    @objid ("9f5311ad-571f-47c8-9fbd-9e62b45221ab")
    public String toolId;

    @objid ("271cdf73-6d62-427c-9590-d0e4dd068534")
    public PaletteEntry(String toolId, String group) {
        this.toolId = toolId;
        this.group = group;
    }

}
