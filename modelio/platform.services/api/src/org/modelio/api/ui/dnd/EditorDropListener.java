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

package org.modelio.api.ui.dnd;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Drop listener.
 */
@objid ("050609ea-78d3-11dd-9979-0014222a9f79")
public class EditorDropListener {
    @objid ("0ef2e0b2-79dc-11dd-ba6f-0014222a9f79")
    private IEditorDropClient client;

    /**
     * Constructor initializing an instance of EditorDropListener.
     * @param client the drop client.
     */
    @objid ("0eccba60-79dc-11dd-ba6f-0014222a9f79")
    public EditorDropListener(final IEditorDropClient client) {
        super();
        this.client = client;
    }

}
